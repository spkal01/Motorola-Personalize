package com.motorola.styles.model;

import java.io.Serializable;

public class Theme implements Serializable {
    public static final String COLOR = "color";
    public static final String DEFAULT = "Default";
    public static final String FONT = "font";
    public static final String GRID = "grid";
    public static final String NAME = "name";
    public static final String RINGTONE = "ringtone";
    public static final String SHAPE = "shape";
    public static final String UNAVAILABLE = "";
    public static final String WALLPAPER = "wallpaper";
    private String mColor;
    private transient ColorOption mColorOption;
    private boolean mDefault = false;
    private String mFont;
    private transient FontOption mFontOption;
    private String mGrid;
    private transient GridOption mGridOption;
    private String mName;
    private boolean mNew = false;
    private int mPreloadIndex = -1;
    private String mRingtone;
    private transient RingtoneOption mRingtoneOption;
    private String mShape;
    private transient ShapeOption mShapeOption;
    private String mWallpaper;
    private transient WallpaperOption mWallpaperOption;

    public Theme() {
    }

    public Theme(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mName = str;
        this.mFont = str2;
        this.mColor = str3;
        this.mShape = str4;
        this.mGrid = str5;
        this.mWallpaper = str6;
        this.mRingtone = str7;
    }

    public Theme(Theme theme) {
        this.mName = theme.mName;
        this.mFont = theme.mFont;
        this.mColor = theme.mColor;
        this.mShape = theme.mShape;
        this.mGrid = theme.mGrid;
        this.mWallpaper = theme.mWallpaper;
        this.mRingtone = theme.mRingtone;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getFont() {
        return this.mFont;
    }

    public void setFont(String str) {
        this.mFont = str;
    }

    public String getColor() {
        return this.mColor;
    }

    public void setColor(String str) {
        this.mColor = str;
    }

    public String getShape() {
        return this.mShape;
    }

    public void setShape(String str) {
        this.mShape = str;
    }

    public String getGrid() {
        return this.mGrid;
    }

    public void setGrid(String str) {
        this.mGrid = str;
    }

    public FontOption getFontOption() {
        return this.mFontOption;
    }

    public void setFontOption(FontOption fontOption) {
        this.mFontOption = fontOption;
        this.mFont = fontOption.getValue();
    }

    public ColorOption getColorOption() {
        return this.mColorOption;
    }

    public void setColorOption(ColorOption colorOption) {
        this.mColorOption = colorOption;
        this.mColor = colorOption.getValue();
    }

    public ShapeOption getShapeOption() {
        return this.mShapeOption;
    }

    public GridOption getGridOption() {
        return this.mGridOption;
    }

    public void setGridOption(GridOption gridOption) {
        this.mGridOption = gridOption;
        this.mGrid = gridOption.getValue();
    }

    public void setShapeOption(ShapeOption shapeOption) {
        this.mShapeOption = shapeOption;
        this.mShape = shapeOption.getValue();
    }

    public boolean isDefault() {
        if ("Default".equals(this.mName)) {
            return true;
        }
        return this.mDefault;
    }

    public void setDefault(boolean z) {
        this.mDefault = z;
    }

    public boolean isNew() {
        return this.mNew;
    }

    public void setNew(boolean z) {
        this.mNew = z;
    }

    public int getPreloadIndex() {
        return this.mPreloadIndex;
    }

    public void setPreloadIndex(int i) {
        this.mPreloadIndex = i;
    }

    public boolean isPreload() {
        return this.mPreloadIndex > -1;
    }

    public boolean isDefaultOrPreload() {
        return this.mDefault || isPreload();
    }

    public String toString() {
        return "Style@" + hashCode() + "{" + this.mName + "," + this.mFont + "," + this.mColor + "," + this.mShape + "," + this.mGrid + "," + this.mWallpaper + "," + this.mRingtone + "," + this.mDefault + "," + this.mNew + "," + this.mPreloadIndex + "}";
    }

    public void setWallpaperOption(WallpaperOption wallpaperOption) {
        this.mWallpaperOption = wallpaperOption;
        this.mWallpaper = wallpaperOption.getValue();
    }

    public WallpaperOption getWallpaperOption() {
        return this.mWallpaperOption;
    }

    public String getWallpaper() {
        return this.mWallpaper;
    }

    public void setWallpaper(String str) {
        this.mWallpaper = str;
    }

    public String getRingtone() {
        return this.mRingtone;
    }

    public void setRingtone(String str) {
        this.mRingtone = str;
    }

    public RingtoneOption getRingtoneOption() {
        return this.mRingtoneOption;
    }

    public void setRingtoneOption(RingtoneOption ringtoneOption) {
        this.mRingtoneOption = ringtoneOption;
        this.mRingtone = ringtoneOption.getValue();
    }
}
