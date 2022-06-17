package com.android.wallpaper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import com.motorola.styles.ResourceConstants;
import java.util.Locale;

public final class Utilities {
    public static final int EDIT_THEME_REQUEST_CODE = 101;
    public static final String KEY_RINGTONE_TOOLTIP_NEEDED = "showRingtoneTooltipNeeded";
    public static final String KEY_SHOW_INTRO_NEEDED = "showIntroNeeded";
    public static final String KEY_SHOW_LESTORE_NEEDED = "showLeStoreNeeded";
    public static final String KEY_THEME = "theme";
    public static final String KEY_THEME_NAME = "themeName";
    public static final int LAND_GRID = 4;
    public static final boolean PRC_BUILD = SystemProperties.getBoolean("ro.product.is_prc", false);
    public static final String PREFS_THEMES = "themes_prefs";

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

    public static Context getAndroidContext(Context context) {
        try {
            return context.createPackageContext(ResourceConstants.ANDROID_PACKAGE, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
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

    public static void setTitle(View view, int i) {
        TextView textView;
        if (view.findViewById(C0744R.C0747id.top_bar) == null) {
            textView = null;
        } else {
            textView = (TextView) view.findViewById(C0744R.C0747id.top_bar).findViewById(C0744R.C0747id.title);
        }
        if (textView != null) {
            textView.setText(i);
        }
    }

    public static void setTitle(View view, String str) {
        TextView textView;
        if (view.findViewById(C0744R.C0747id.top_bar) == null) {
            textView = null;
        } else {
            textView = (TextView) view.findViewById(C0744R.C0747id.top_bar).findViewById(C0744R.C0747id.title);
        }
        if (textView != null) {
            textView.setText(str);
        }
    }

    public static View getTopBarButton(View view, int i) {
        if (view.findViewById(C0744R.C0747id.top_bar) == null) {
            return null;
        }
        return view.findViewById(C0744R.C0747id.top_bar).findViewById(i);
    }

    public static int getNavigationBarHeight(Context context) {
        int i;
        int i2;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return 0;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        Point point2 = new Point();
        defaultDisplay.getSize(point2);
        if (point2.x < point.x) {
            i = point.x;
            i2 = point2.x;
        } else if (point2.y >= point.y) {
            return 0;
        } else {
            i = point.y;
            i2 = point2.y;
        }
        return i - i2;
    }

    private static SharedPreferences getThemesPrefs(Context context) {
        return context.getSharedPreferences(PREFS_THEMES, 0);
    }

    public static boolean showGoToLeStoreTipNeeded(Context context) {
        return getThemesPrefs(context).getBoolean("showLeStoreNeeded", true);
    }

    public static void setShowGoToLeStoreTipNeeded(Context context, boolean z) {
        getThemesPrefs(context).edit().putBoolean("showLeStoreNeeded", z).apply();
    }

    public static boolean isRingtoneTooltipNeeded(Context context) {
        return getThemesPrefs(context).getBoolean(KEY_RINGTONE_TOOLTIP_NEEDED, true);
    }

    public static void disableRingtoneTooltip(Context context) {
        getThemesPrefs(context).edit().putBoolean(KEY_RINGTONE_TOOLTIP_NEEDED, false).apply();
    }

    public static String getActionBarUpString() {
        Resources system = Resources.getSystem();
        return system.getString(system.getIdentifier("action_bar_up_description", "string", ResourceConstants.ANDROID_PACKAGE));
    }

    public static String getEditString() {
        Resources system = Resources.getSystem();
        return system.getString(system.getIdentifier("screenshot_edit", "string", ResourceConstants.ANDROID_PACKAGE));
    }

    public static boolean isKeyboardShowing(Activity activity) {
        if (activity == null) {
            return false;
        }
        View rootView = activity.getWindow().getDecorView().getRootView();
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        int height = rootView.getRootView().getHeight();
        if (height == 0) {
            return false;
        }
        int i = height - rect.bottom;
        Log.d("Styles", "keypadHeight: " + i);
        if (((double) i) > ((double) height) * 0.15d) {
            return true;
        }
        return false;
    }

    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static float getDisplayDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static Boolean isDarkModeEnabled(Context context) {
        return Boolean.valueOf(getUiMode(context) == 32);
    }

    public static int getUiMode(Context context) {
        return context.getResources().getConfiguration().uiMode & 48;
    }
}
