package com.motorola.personalize.util;

import android.content.Context;

public class SPUtils {
    public static final String KEY_SHOW_GRID_TIP = "showGridPreviewTip";
    public static final String KEY_SHOW_LESTORE_NEEDED = "showLeStoreNeeded";
    public static final String PREFS_FILE = "personalize_prefs";

    public static boolean showGoToLeStoreTipNeeded(Context context) {
        return context.getSharedPreferences(PREFS_FILE, 0).getBoolean("showLeStoreNeeded", true);
    }

    public static void setShowGoToLeStoreTipNeeded(Context context, boolean z) {
        context.getSharedPreferences(PREFS_FILE, 0).edit().putBoolean("showLeStoreNeeded", z).apply();
    }

    public static boolean showGridTipNeeded(Context context) {
        return context.getSharedPreferences(PREFS_FILE, 0).getBoolean(KEY_SHOW_GRID_TIP, true);
    }

    public static void setShowGridTipNeeded(Context context, boolean z) {
        context.getSharedPreferences(PREFS_FILE, 0).edit().putBoolean(KEY_SHOW_GRID_TIP, z).apply();
    }
}
