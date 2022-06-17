package com.motorola.styles.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ThemeSettings {
    public static final String SETTING_COLOR = "color";
    public static final String SETTING_FONT = "font";
    public static final String SETTING_GRID = "grid";
    public static final String SETTING_RINGTONE = "ringtone";
    public static final String SETTING_SHAPE = "shape";
    public static final String SETTING_WALLPAPER = "wallpaper";
    private final ArrayList<Option> mOptions = new ArrayList<>();
    private String mSetting = "wallpaper";
    private final Theme mTheme;

    public static boolean isFont(String str) {
        return "font".equals(str);
    }

    public static boolean isColor(String str) {
        return "color".equals(str);
    }

    public static boolean isShape(String str) {
        return "shape".equals(str);
    }

    public static boolean isGrid(String str) {
        return "grid".equals(str);
    }

    public static boolean isWallpaper(String str) {
        return "wallpaper".equals(str);
    }

    public static boolean isRingtone(String str) {
        return "ringtone".equals(str);
    }

    public ThemeSettings(Theme theme) {
        this.mTheme = new Theme(theme);
    }

    public Theme getTheme() {
        return this.mTheme;
    }

    public void setSetting(String str) {
        this.mSetting = str;
    }

    public String getSetting() {
        return this.mSetting;
    }

    public void setOptions(List<? extends Option> list) {
        this.mOptions.clear();
        this.mOptions.addAll(list);
    }

    public List<Option> getOptions() {
        return this.mOptions;
    }

    public static int indexOfSettings(List<? extends Option> list, Predicate<Option> predicate) {
        Optional<Option> findFirst = list.stream().filter(predicate).findFirst();
        Objects.requireNonNull(list);
        return ((Integer) findFirst.map(new Function(list) {
            public final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final Object apply(Object obj) {
                return Integer.valueOf(this.f$0.indexOf((Option) obj));
            }
        }).orElse(-1)).intValue();
    }

    public String getSettingValue() {
        if (isFont(this.mSetting)) {
            return this.mTheme.getFont();
        }
        if (isColor(this.mSetting)) {
            return this.mTheme.getColor();
        }
        if (isShape(this.mSetting)) {
            return this.mTheme.getShape();
        }
        if (isGrid(this.mSetting)) {
            return this.mTheme.getGrid();
        }
        if (isWallpaper(this.mSetting)) {
            return this.mTheme.getWallpaper();
        }
        return isRingtone(this.mSetting) ? this.mTheme.getRingtone() : "";
    }

    public void setName(String str) {
        this.mTheme.setName(str);
    }

    public void setSettingValue(Option option) {
        if (option instanceof FontOption) {
            this.mTheme.setFont(option.getValue());
        } else if (option instanceof ColorOption) {
            this.mTheme.setColor(option.getValue());
        } else if (option instanceof ShapeOption) {
            this.mTheme.setShape(option.getValue());
        } else if (option instanceof GridOption) {
            this.mTheme.setGrid(option.getValue());
        } else if (option instanceof WallpaperOption) {
            this.mTheme.setWallpaper(option.getValue());
        } else if (option instanceof RingtoneOption) {
            this.mTheme.setRingtone(option.getValue());
        }
    }
}
