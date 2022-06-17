package com.android.launcher3.icons;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.icons.cache.CachingLogic;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.ReflectUtils;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class CustomAppIcons {
    public static final String APP_ICON_PREFIX = "appicon_";
    public static final String CUSTOM_APP_ICONS_PKG = "com.motorola.launcherconfig";
    public static final boolean DEBUG = true;
    public static final String DOT = ".";
    private static final float ICON_SIZE_DEFINED_IN_APP_DP = 48.0f;
    public static final Pattern PATTERN_DOT = Pattern.compile("\\.");
    public static final Pattern PATTERN_UNDERSCORE = Pattern.compile(UNDERSCORE);
    public static final String TAG = "CustomAppIcons";
    public static final String UNDERSCORE = "_";
    private static CustomAppIcons sInstance;
    private SoftReference<Context> mCaiContext;
    private Map<String, Integer> mCustomAppIconMap;
    private final Map<Integer, BaseIconFactory> mIconFactoryMap = new HashMap();
    private IconPack mIconPack;
    private Boolean mIsPrcProduct;

    public static CustomAppIcons getInstance() {
        if (sInstance == null) {
            synchronized (CustomAppIcons.class) {
                if (sInstance == null) {
                    sInstance = new CustomAppIcons();
                }
            }
        }
        return sInstance;
    }

    private CustomAppIcons() {
    }

    public Drawable loadIcon(Context context, ApplicationInfo applicationInfo) {
        try {
            return getCustomAppIcon(context, applicationInfo.packageName, (String) null);
        } catch (Exception unused) {
            Log.e(TAG, "Get error on getting custom icon for " + applicationInfo);
            return null;
        }
    }

    private Drawable getCustomAppIcon(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        String str3 = "";
        String lowerCase = str == null ? str3 : str.toLowerCase();
        if (str2 != null) {
            str3 = str2.toLowerCase();
        }
        Drawable drawable = null;
        Log.d(TAG, "Get custom app icon: " + context + " | " + lowerCase + " | " + str3);
        String appliedIconPack = IconPackManager.getAppliedIconPack(context);
        IconPack iconPack = this.mIconPack;
        if (iconPack == null || !appliedIconPack.equals(iconPack.getPackageName())) {
            this.mIconPack = IconPack.newIconPack(context, appliedIconPack);
        }
        if (!this.mIconPack.isDefault()) {
            drawable = this.mIconPack.getAppIcon(lowerCase, str3);
        } else if (isPrcProduct()) {
            Context caiContext = getCaiContext(context);
            Integer num = getCustomAppIconMap(caiContext).get(lowerCase);
            if (num != null) {
                drawable = caiContext.getResources().getDrawable(num.intValue(), caiContext.getTheme());
            }
        }
        Log.d(TAG, "Icon from CustomAppIcons: " + lowerCase + " | " + str3 + " | " + drawable);
        return drawable;
    }

    private Integer getAppIconId(Context context, String str) {
        String lowerCase = str == null ? "" : str.toLowerCase();
        Resources resources = context.getResources();
        String str2 = APP_ICON_PREFIX + PATTERN_DOT.matcher(lowerCase).replaceAll(UNDERSCORE);
        Log.d(TAG, "getAppIconId - appIconName: " + str2);
        return Integer.valueOf(resources.getIdentifier(str2, IconPack.DRAWABLE, CUSTOM_APP_ICONS_PKG));
    }

    public BitmapInfo loadIcon(Context context, ComponentName componentName, UserHandle userHandle, int i) {
        return loadIcon(context, componentName, userHandle, i, (CachingLogic) null);
    }

    public BitmapInfo loadIcon(Context context, ComponentName componentName, UserHandle userHandle, int i, CachingLogic cachingLogic) {
        Drawable customAppIcon;
        try {
            if (!isLauncherActivity(context.getPackageManager(), componentName) || (customAppIcon = getCustomAppIcon(context, componentName.getPackageName(), componentName.getClassName())) == null || cachingLogic == null) {
                return null;
            }
            return getIconFactory(context, i).createBadgedIconBitmap(cachingLogic.processCustomIcon(context, new ComponentKey(componentName, userHandle), customAppIcon), userHandle, true, false, (float[]) null);
        } catch (Exception unused) {
            Log.e(TAG, "Get error on getting custom icon for " + componentName);
            return null;
        }
    }

    private boolean isCertainPlugin(ComponentName componentName, String str, String str2) {
        if (componentName != null && TextUtils.equals(componentName.getPackageName(), str) && TextUtils.equals(componentName.getClassName(), str2)) {
            return true;
        }
        return false;
    }

    public Drawable loadIcon(Context context, ComponentName componentName) {
        try {
            if (isLauncherActivity(context.getPackageManager(), componentName) || isSupportAdditional(componentName)) {
                return getCustomAppIcon(context, componentName.getPackageName(), componentName.getClassName());
            }
            return null;
        } catch (Exception unused) {
            Log.e(TAG, "Get error on getting custom icon drawable for " + componentName);
            return null;
        }
    }

    private boolean isSupportAdditional(ComponentName componentName) {
        return isMyScreen(componentName) || istimeWeather(componentName) || isCertainPlugin(componentName, IconPack.COMMUTE_SUGGESTION_PACKAGE_NAME, IconPack.COMMUTE_SUGGESTION_CLASS_NAME) || isCertainPlugin(componentName, IconPack.COMMUTE_SUGGESTION_PACKAGE_NAME, IconPack.SMART_EXPRESS_CLASS_NAME) || isCertainPlugin(componentName, IconPack.SMART_TRAVEL_PACKAGE_NAME, IconPack.SMART_TRAVEL_CLASS_NAME);
    }

    private boolean isMyScreen(ComponentName componentName) {
        return TextUtils.equals(componentName.getPackageName(), IconPack.MY_SCREEN_PACKAGE);
    }

    private boolean istimeWeather(ComponentName componentName) {
        return TextUtils.equals(componentName.getPackageName(), IconPack.TIME_WEATHER_PACKAGE);
    }

    public static boolean isLauncherActivity(PackageManager packageManager, ComponentName componentName) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(componentName);
        if (packageManager.queryIntentActivities(intent, 0).size() > 0) {
            return true;
        }
        return false;
    }

    private BaseIconFactory getIconFactory(Context context, int i) {
        if (!this.mIconFactoryMap.containsKey(Integer.valueOf(i))) {
            synchronized (this) {
                if (!this.mIconFactoryMap.containsKey(Integer.valueOf(i))) {
                    this.mIconFactoryMap.put(Integer.valueOf(i), new BaseIconFactory(context, getLauncherIconDensity(i), i, true));
                }
            }
        }
        return this.mIconFactoryMap.get(Integer.valueOf(i));
    }

    private Context getCaiContext(Context context) throws PackageManager.NameNotFoundException {
        SoftReference<Context> softReference = this.mCaiContext;
        if (softReference == null || softReference.get() == null) {
            synchronized (this) {
                SoftReference<Context> softReference2 = this.mCaiContext;
                if (softReference2 == null || softReference2.get() == null) {
                    this.mCaiContext = new SoftReference<>(context.createPackageContext(CUSTOM_APP_ICONS_PKG, 0));
                }
            }
        }
        return this.mCaiContext.get();
    }

    private int getLauncherIconDensity(int i) {
        int[] iArr = {120, 160, 213, 240, 320, 480, 640};
        int i2 = 640;
        for (int i3 = 6; i3 >= 0; i3--) {
            if ((((float) iArr[i3]) * 48.0f) / 160.0f >= ((float) i)) {
                i2 = iArr[i3];
            }
        }
        return i2;
    }

    public boolean isPrcProduct() {
        if (this.mIsPrcProduct == null) {
            synchronized (this) {
                if (this.mIsPrcProduct == null) {
                    this.mIsPrcProduct = Boolean.valueOf(TextUtils.equals(getSystemProperty("ro.product.is_prc", (String) null), "true"));
                }
            }
        }
        return this.mIsPrcProduct.booleanValue();
    }

    public static String getSystemProperty(String str, String str2) {
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            return str2;
        } catch (Exception unused) {
            Log.d(TAG, "Unable to read system properties");
        }
    }

    private Map<String, Integer> getCustomAppIconMap(Context context) {
        if (this.mCustomAppIconMap == null) {
            synchronized (this) {
                if (this.mCustomAppIconMap == null) {
                    this.mCustomAppIconMap = new HashMap();
                    try {
                        Log.d(TAG, "Start cache drawables resources: " + context);
                        Resources resources = context.getResources();
                        for (String str : resources.getStringArray(resources.getIdentifier("app_icons", "array", CUSTOM_APP_ICONS_PKG))) {
                            if (str.startsWith(APP_ICON_PREFIX)) {
                                String replaceAll = PATTERN_UNDERSCORE.matcher(str.substring(8)).replaceAll(".");
                                this.mCustomAppIconMap.put(replaceAll, Integer.valueOf(resources.getIdentifier(str, IconPack.DRAWABLE, CUSTOM_APP_ICONS_PKG)));
                                Log.d(TAG, "Map app icon: " + replaceAll);
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Exception on getting app icons map", e);
                    }
                }
            }
        }
        return this.mCustomAppIconMap;
    }

    public Drawable loadIcon(Context context, Drawable drawable) {
        Drawable loadIcon;
        try {
            Log.e(TAG, "Load icon from drawable: " + drawable);
            ComponentName componentName = (ComponentName) ReflectUtils.getFieldValue((Object) drawable, IconPack.COMPONENT);
            if (componentName != null && (loadIcon = loadIcon(context, componentName)) != null) {
                return loadIcon;
            }
            Drawable drawable2 = (Drawable) ReflectUtils.getFieldValue((Object) drawable, "originDrawable");
            if (drawable2 != null) {
                return drawable2;
            }
            return drawable;
        } catch (Exception e) {
            Log.e(TAG, "Exception on loading icon from drawable: " + drawable, e);
        }
    }
}
