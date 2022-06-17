package com.motorola.personalize.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.location.LocationManager;
import android.net.Uri;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.motorola.styles.ResourceConstants;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utilities {
    public static final boolean PRC_BUILD = SystemProperties.getBoolean("ro.product.is_prc", false);
    private static final int SPACE_TIME = 300;
    private static long lastClickTime = 0;

    private Utilities() {
    }

    public static int getColorAccent(Context context) {
        return getAttrColor(context, 16843829);
    }

    public static int getAttrColor(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static boolean isLandscapeMode(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static boolean isWaterfallDisplay() {
        Resources system = Resources.getSystem();
        int identifier = system.getIdentifier("config_support_waterfall_display", "bool", ResourceConstants.ANDROID_PACKAGE);
        if (identifier == 0) {
            return false;
        }
        return system.getBoolean(identifier);
    }

    public static boolean isXMLocale(Context context) {
        return context.getResources().getConfiguration().locale.getCountry().equals("XM");
    }

    public static String removeAllBidiClass(String str) {
        Matcher matcher = Pattern.compile("[\\u200b-\\u200f]|[\\u200e-\\u200f]|[\\u202a-\\u202e]|[\\u2066-\\u2069]").matcher(str);
        return matcher.find() ? matcher.replaceAll("") : str;
    }

    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static synchronized boolean isDoubleClick() {
        boolean z;
        synchronized (Utilities.class) {
            long currentTimeMillis = System.currentTimeMillis();
            z = currentTimeMillis - lastClickTime <= 300;
            lastClickTime = currentTimeMillis;
        }
        return z;
    }

    public static boolean isLandscape(Resources resources) {
        return resources.getConfiguration().orientation == 2;
    }

    public static void queryMarket(Context context) {
        Intent intent = new Intent();
        intent.setDataAndNormalize(Uri.parse("leapp://ptn/speciallist.do?type=subject&code=24582&name=图标专区&backmain=false"));
        try {
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public static boolean isRtl(View view) {
        if (ViewCompat.isLayoutDirectionResolved(view)) {
            if (ViewCompat.getLayoutDirection(view) == 1) {
                return true;
            }
            return false;
        } else if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isRtl(Resources resources) {
        return resources.getConfiguration().getLayoutDirection() == 1;
    }

    public static <T> void observeOnce(final LiveData<T> liveData, LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        liveData.observe(lifecycleOwner, new Observer<T>() {
            public void onChanged(T t) {
                Observer.this.onChanged(t);
                liveData.removeObserver(this);
            }
        });
    }

    public static boolean checkExternalStoragePermission(Context context) {
        return checkSelfPermissions(context, "android.permission.READ_EXTERNAL_STORAGE");
    }

    public static boolean checkSelfPermissions(Context context, String str) {
        return context.checkSelfPermission(str) == 0;
    }

    public static String getHexColor6(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)});
    }

    public static boolean anyLocationProviderEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager == null) {
            return false;
        }
        List<String> providers = locationManager.getProviders(true);
        int i = 0;
        while (i < providers.size()) {
            String str = providers.get(i);
            if (str == null || !(str.compareTo("gps") == 0 || str.compareTo("network") == 0)) {
                i++;
            } else {
                Log.d("Styles", "Found location provider " + str);
                return true;
            }
        }
        return false;
    }
}
