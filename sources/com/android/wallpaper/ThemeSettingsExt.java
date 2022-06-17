package com.android.wallpaper;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class ThemeSettingsExt {
    public static final List<Pair<String, Integer>> SETTINGS = new ArrayList<Pair<String, Integer>>() {
        {
            add(new Pair("wallpaper", Integer.valueOf(C0744R.string.themes_setting_wallpaper)));
            add(new Pair("font", Integer.valueOf(C0744R.string.themes_setting_font)));
            add(new Pair("color", Integer.valueOf(C0744R.string.themes_setting_color)));
            add(new Pair("shape", Integer.valueOf(C0744R.string.themes_setting_shape)));
            add(new Pair("grid", Integer.valueOf(C0744R.string.themes_setting_grid)));
            add(new Pair("ringtone", Integer.valueOf(C0744R.string.themes_setting_ringtone)));
        }
    };
}
