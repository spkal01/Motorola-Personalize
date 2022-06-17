package com.android.systemui.monet;

import android.app.WallpaperColors;
import android.graphics.Color;
import java.util.List;
import java.util.Map;

class WallpaperProxy {
    WallpaperProxy() {
    }

    public static Map<Integer, Integer> getAllColors(WallpaperColors wallpaperColors) {
        return wallpaperColors.getAllColors();
    }

    public static List<Color> getMainColors(WallpaperColors wallpaperColors) {
        return wallpaperColors.getMainColors();
    }
}
