package com.motorola.styles;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;

public interface ResourceConstants {
    public static final String ACCENT_COLOR_DARK_NAME = "accent_device_default_dark";
    public static final String ACCENT_COLOR_LIGHT_NAME = "accent_device_default_light";
    public static final String ANDROID_PACKAGE = "android";
    public static final String CONFIG_BODY_FONT_FAMILY = "config_bodyFontFamily";
    public static final String CONFIG_CORNERRADIUS = "config_bottomDialogCornerRadius";
    public static final String CONFIG_HEADLINE_FONT_FAMILY = "config_headlineFontFamily";
    public static final String CONFIG_ICON_MASK = "config_icon_mask";
    public static final String DEFAULT_PACKAGE = "Default";
    public static final String[] ICONS_FOR_PREVIEW = {"ic_wifi_signal_3", "ic_qs_bluetooth", "ic_signal_location", "ic_qs_dnd", "ic_qs_flashlight", "ic_qs_auto_rotate", "ic_qs_airplane", "ic_signal_cellular_3_4_bar", "ic_battery_80_24dp"};
    public static final String OVERLAY_CATEGORY_ANDROID_THEME = "android.theme";
    public static final String OVERLAY_CATEGORY_COLOR = "android.theme.customization.accent_color";
    public static final String OVERLAY_CATEGORY_FONT = "android.theme.customization.font";
    public static final String OVERLAY_CATEGORY_ICON_ANDROID = "android.theme.customization.icon_pack.android";
    public static final String OVERLAY_CATEGORY_ICON_LAUNCHER = "android.theme.customization.icon_pack.launcher";
    public static final String OVERLAY_CATEGORY_ICON_SETTINGS = "android.theme.customization.icon_pack.settings";
    public static final String OVERLAY_CATEGORY_ICON_SYSUI = "android.theme.customization.icon_pack.systemui";
    public static final String OVERLAY_CATEGORY_ICON_THEMEPICKER = "android.theme.customization.icon_pack.themepicker";
    public static final String OVERLAY_CATEGORY_SHAPE = "android.theme.customization.adaptive_icon_shape";
    public static final float PATH_SIZE = 100.0f;
    public static final String SETTINGS_PACKAGE = "com.android.settings";
    public static final String SYSUI_PACKAGE = "com.android.systemui";
    public static final String THEME_SETTING = "theme_customization_overlay_packages";
    public static final ArrayList<String> sTargetPackages = new ArrayList<>();

    static String[] getPackagesToOverlay(Context context) {
        ArrayList<String> arrayList = sTargetPackages;
        if (arrayList.isEmpty()) {
            arrayList.addAll(Arrays.asList(new String[]{ANDROID_PACKAGE, SETTINGS_PACKAGE, SYSUI_PACKAGE}));
            String launcherPackage = getLauncherPackage(context);
            if (!TextUtils.isEmpty(launcherPackage)) {
                arrayList.add(launcherPackage);
            }
            arrayList.add(context.getPackageName());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    static String getLauncherPackage(Context context) {
        return context.getString(C1087R.string.launcher_overlayable_package);
    }

    static String getIconMask(Resources resources, String str) {
        return resources.getString(resources.getIdentifier(CONFIG_ICON_MASK, "string", str));
    }
}
