package com.motorola.personalize.model;

import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.ShapeOption;
import java.io.Serializable;

public class Style implements Serializable {
    public static final String COLOR = "color";
    public static final String DEFAULT = "Default";
    public static final String FONT = "font";
    public static final String GRID = "grid";
    public static final String NAME = "name";
    public static final String SHAPE = "shape";
    public static final String UNAVAILABLE = "";
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
    private String mShape;
    private transient ShapeOption mShapeOption;

    public Style() {
    }

    public Style(String str, String str2, String str3, String str4, String str5) {
        this.mName = str;
        this.mFont = str2;
        this.mColor = str3;
        this.mShape = str4;
        this.mGrid = str5;
    }

    public Style(Style style) {
        this.mName = style.mName;
        this.mFont = style.mFont;
        this.mColor = style.mColor;
        this.mShape = style.mShape;
        this.mGrid = style.mGrid;
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
    }

    public ColorOption getColorOption() {
        return this.mColorOption;
    }

    public void setColorOption(ColorOption colorOption) {
        this.mColorOption = colorOption;
    }

    public ShapeOption getShapeOption() {
        return this.mShapeOption;
    }

    public GridOption getGridOption() {
        return this.mGridOption;
    }

    public void setGridOption(GridOption gridOption) {
        this.mGridOption = gridOption;
    }

    public void setShapeOption(ShapeOption shapeOption) {
        this.mShapeOption = shapeOption;
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
        return "Style@" + hashCode() + "{" + this.mName + "," + this.mFont + "," + this.mColor + "," + this.mShape + "," + this.mGrid + "," + this.mDefault + "," + this.mNew + "," + this.mPreloadIndex + "}";
    }
}
