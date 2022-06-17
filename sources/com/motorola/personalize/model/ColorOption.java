package com.motorola.personalize.model;

import android.content.res.Resources;
import com.android.systemui.monet.ColorScheme;
import com.motorola.personalize.util.Utilities;
import com.motorola.styles.model.Option;

public class ColorOption extends Option {
    public static final int TYPE_CUSTOM = 3;
    public static final int TYPE_PICKER = 1;
    public static final int TYPE_PRESET = 0;
    public static final int TYPE_WALLPAPER = 2;
    private int colorIndex;
    private int colorOptionType;
    private boolean isHomeSource;
    private int mLightColor;
    public int mPresetAccentColor;
    private ColorScheme mScheme;
    public int mShowingDarkColor;
    public int mShowingLightColor;

    public boolean isHomeSource() {
        return this.isHomeSource;
    }

    public void setColorIndex(int i) {
        this.colorIndex = i;
    }

    public int getColorIndex() {
        return this.colorIndex;
    }

    public int getType() {
        return this.colorOptionType;
    }

    public ColorOption(String str, int i, int i2, boolean z) {
        super("", str);
        this.isHomeSource = false;
        this.colorIndex = 0;
        this.colorOptionType = 0;
        this.mLightColor = i;
        this.colorOptionType = i2;
        this.isHomeSource = z;
        setValue(valueGenerator(i, i2, z));
    }

    public ColorOption(String str, int i, int i2, int i3, int i4) {
        super("", str);
        this.isHomeSource = false;
        this.colorIndex = 0;
        this.colorOptionType = 0;
        this.colorOptionType = 0;
        this.mLightColor = i;
        this.mPresetAccentColor = i2;
        this.mShowingLightColor = i3;
        this.mShowingDarkColor = i4;
        setValue(valueGenerator(i, 0, false));
    }

    private String valueGenerator(int i, int i2, boolean z) {
        StringBuilder sb = new StringBuilder(Utilities.getHexColor6(i));
        sb.append(",");
        if (i2 == 0) {
            sb.append("D");
        } else if (i2 == 1) {
            sb.append("N");
        } else if (i2 != 2) {
            if (i2 == 3) {
                sb.append("P");
            }
        } else if (z) {
            sb.append("WH");
        } else {
            sb.append("WL");
        }
        return sb.toString();
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.mScheme = colorScheme;
        this.mLightColor = colorScheme.getSeed();
    }

    public ColorScheme getColorScheme() {
        return this.mScheme;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public static int resolveColor(Resources resources, ColorOption colorOption) {
        return colorOption.getLightColor();
    }

    public String toString() {
        return "ColorOption" + hashCode() + "{mValue='" + this.mValue + '\'' + "type='" + this.colorOptionType + '\'' + "colorIndex='" + this.colorIndex + '\'' + ", mName='" + this.mName + '\'' + ", isHomeSource='" + this.isHomeSource + '\'' + '}';
    }
}
