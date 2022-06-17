package com.motorola.styles.model;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.android.launcher3.icons.CustomAppIcons;
import com.android.systemui.monet.ColorScheme;
import com.motorola.styles.LogConfig;
import java.util.List;
import java.util.regex.Pattern;

public class ColorOption extends Option {
    public static final Pattern COLOR_VALUE_SEPARATOR = Pattern.compile(CustomAppIcons.UNDERSCORE);
    public static String PICKED_COLOR_PREFIX = "picked_";
    public static String PRELOADED_COLOR_PREFIX = "preloaded_";
    public static String WALLPAPER_COLOR_PREFIX = "wallpaper_";
    private Integer mAccentColor;
    private ColorScheme mColorScheme;
    private int mDarkColor;
    private boolean mIsPickedColor;
    private boolean mIsPreloadedColor;
    private boolean mIsWallpaperColor;
    private int mLightColor;
    private Integer mMainColor;
    private List<Drawable> mPreviewIcons;
    private Integer mWallpaperColorIndex;

    public ColorOption(String str, String str2, int i, int i2) {
        super(str, str2);
        this.mLightColor = i;
        this.mDarkColor = i2;
        if (LogConfig.DBG) {
            Log.d("Styles", "ColorOption: " + str + " | " + str2);
        }
    }

    public ColorOption(String str, String str2, String str3, String str4) {
        super(getPreloadedColorValue(str, str2, str3, str4), "");
        this.mMainColor = Integer.valueOf(Color.parseColor(str));
        this.mAccentColor = Integer.valueOf(Color.parseColor(str2));
        this.mLightColor = Color.parseColor(str3);
        this.mDarkColor = Color.parseColor(str4);
        this.mColorScheme = getColorScheme(this.mMainColor.intValue());
        this.mIsPreloadedColor = true;
        if (LogConfig.DBG) {
            Log.d("Styles", "ColorOption: " + getValue());
        }
    }

    private static String getPreloadedColorValue(String str, String str2, String str3, String str4) {
        return PRELOADED_COLOR_PREFIX + str + CustomAppIcons.UNDERSCORE + str2 + CustomAppIcons.UNDERSCORE + str3 + CustomAppIcons.UNDERSCORE + str4;
    }

    public ColorOption(int i, int i2) {
        super(getWallpaperColorValue(getHexColor6(i), i2), "");
        Integer valueOf = Integer.valueOf(i);
        this.mMainColor = valueOf;
        this.mLightColor = valueOf.intValue();
        this.mDarkColor = this.mMainColor.intValue();
        this.mWallpaperColorIndex = Integer.valueOf(i2);
        this.mColorScheme = getColorScheme(i);
        this.mIsWallpaperColor = true;
        if (LogConfig.DBG) {
            Log.d("Styles", "ColorOption: " + getValue());
        }
    }

    private static String getWallpaperColorValue(String str, int i) {
        return WALLPAPER_COLOR_PREFIX + str + CustomAppIcons.UNDERSCORE + i;
    }

    public ColorOption(int i) {
        super(getPickedColorValue(getHexColor6(i)), "");
        Integer valueOf = Integer.valueOf(i);
        this.mMainColor = valueOf;
        this.mLightColor = valueOf.intValue();
        this.mDarkColor = this.mMainColor.intValue();
        this.mColorScheme = getColorScheme(i);
        this.mIsPickedColor = true;
        if (LogConfig.DBG) {
            Log.d("Styles", "ColorOption: " + getValue());
        }
    }

    private static String getPickedColorValue(String str) {
        return PICKED_COLOR_PREFIX + str;
    }

    public static ColorOption getColorOption(String str) {
        if (str.startsWith(PRELOADED_COLOR_PREFIX)) {
            String[] split = COLOR_VALUE_SEPARATOR.split(str);
            return new ColorOption(split[1], split[2], split[3], split[4]);
        } else if (str.startsWith(WALLPAPER_COLOR_PREFIX)) {
            String[] split2 = COLOR_VALUE_SEPARATOR.split(str);
            return new ColorOption(Color.parseColor(split2[1]), Integer.parseInt(split2[2]));
        } else if (str.startsWith(PICKED_COLOR_PREFIX)) {
            return new ColorOption(Color.parseColor(COLOR_VALUE_SEPARATOR.split(str)[1]));
        } else {
            return new ColorOption(Color.parseColor(str));
        }
    }

    public static String getHexColor6(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)});
    }

    public static String getHexColor8(int i) {
        return String.format("#%08X", new Object[]{Integer.valueOf(i & -1)});
    }

    public static ColorScheme getColorScheme(int i) {
        return getColorScheme(i, isDarkModeEnabled().booleanValue());
    }

    public static ColorScheme getColorScheme(int i, boolean z) {
        return new ColorScheme(i, Integer.MIN_VALUE, z);
    }

    public List<Drawable> getPreviewIcons() {
        return this.mPreviewIcons;
    }

    public void setPreviewIcons(List<Drawable> list) {
        this.mPreviewIcons = list;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public int getDarkColor() {
        return this.mDarkColor;
    }

    public static int resolveColor(ColorOption colorOption) {
        return isDarkModeEnabled().booleanValue() ? colorOption.getDarkColor() : colorOption.getLightColor();
    }

    public static Boolean isDarkModeEnabled() {
        return Boolean.valueOf((Resources.getSystem().getConfiguration().uiMode & 48) == 32);
    }

    public String toString() {
        return "ColorOption" + hashCode() + "{mValue='" + this.mValue + '\'' + ", mName='" + this.mName + '\'' + ", mUninstallable=" + this.mUninstallable + '}';
    }

    public Integer getMainColor() {
        return this.mMainColor;
    }

    public Integer getAccentColor() {
        return this.mAccentColor;
    }

    public Integer getWallpaperColorIndex() {
        return this.mWallpaperColorIndex;
    }

    public int getColorOfAccent1(int i) {
        try {
            return this.mColorScheme.getAccent1().get(i).intValue();
        } catch (Exception e) {
            Log.w("Styles", "getColorOfAccent1 error", e);
            return resolveColor(this);
        }
    }

    public int getColorOfAccent2(int i) {
        try {
            return this.mColorScheme.getAccent2().get(i).intValue();
        } catch (Exception e) {
            Log.w("Styles", "getColorOfAccent2 error", e);
            return resolveColor(this);
        }
    }

    public int getColorOfAccent3(int i) {
        try {
            return this.mColorScheme.getAccent3().get(i).intValue();
        } catch (Exception e) {
            Log.w("Styles", "getColorOfAccent3 error", e);
            return resolveColor(this);
        }
    }

    public int getColorOfNeutral1(int i) {
        try {
            return this.mColorScheme.getNeutral1().get(i).intValue();
        } catch (Exception e) {
            Log.w("Styles", "getColorOfNeutral1 error", e);
            return resolveColor(this);
        }
    }

    public int getColorOfNeutral2(int i) {
        try {
            return this.mColorScheme.getNeutral2().get(i).intValue();
        } catch (Exception e) {
            Log.w("Styles", "getColorOfNeutral2 error", e);
            return resolveColor(this);
        }
    }

    public boolean isPreloadedColor() {
        return this.mIsPreloadedColor;
    }

    public boolean isWallpaperColor() {
        return this.mIsWallpaperColor;
    }

    public boolean isPickedColor() {
        return this.mIsPickedColor;
    }
}
