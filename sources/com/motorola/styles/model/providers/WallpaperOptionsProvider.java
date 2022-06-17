package com.motorola.styles.model.providers;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.motorola.styles.LogConfig;
import com.motorola.styles.model.WallpaperOption;

public final class WallpaperOptionsProvider extends StyleOptionsProvider<WallpaperOption> {
    private static final String PRELOADED_WALLPAPERS_PACKAGE = "com.motorola.launcherconfig";
    private static final String TAG = "WallpaperOptionsProvider";
    public static final boolean USE_LIGHT_DARK_WALLPAPER = false;

    public static String getDefaultWallpaperValue(Context context) {
        return "";
    }

    public WallpaperOption getAppliedOption() {
        return null;
    }

    public WallpaperOptionsProvider(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void loadOptions(Bundle bundle) {
        loadPreloadedWallpapers();
    }

    private void loadPreloadedWallpapers() {
        Resources resources;
        int identifier;
        try {
            resources = this.mContext.createPackageContext("com.motorola.launcherconfig", 0).getResources();
        } catch (Exception e) {
            Log.w(TAG, "loadPreloadWallpapers error", e);
            resources = null;
        }
        if (resources != null && (identifier = resources.getIdentifier("partner_personalize_wallpapers", "array", "com.motorola.launcherconfig")) != 0) {
            for (String str : resources.getStringArray(identifier)) {
                if (LogConfig.DBG) {
                    Log.d(TAG, "Preload wallpaper: " + str);
                }
                this.mOptions.add(new WallpaperOption("com.motorola.launcherconfig/" + str, ""));
                WallpaperOption wallpaperOption = new WallpaperOption("com.motorola.launcherconfig/" + str + WallpaperOption.DARK_FLAG, "");
                if (wallpaperOption.exists(this.mContext)) {
                    this.mOptions.add(wallpaperOption);
                }
            }
        }
    }

    private void addDefault() {
        this.mOptions.add(new WallpaperOption("", ""));
    }

    public static Boolean isDarkModeEnabled(Context context) {
        return Boolean.valueOf((context.getResources().getConfiguration().uiMode & 48) == 32);
    }
}
