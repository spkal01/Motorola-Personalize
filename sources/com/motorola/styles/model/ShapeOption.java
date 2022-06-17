package com.motorola.styles.model;

import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import com.motorola.styles.LogConfig;
import java.util.List;

public class ShapeOption extends Option {
    private final List<Drawable> mAppIcons;
    private final Path mPath;
    private final LayerDrawable mShape;

    public ShapeOption(String str, String str2, Path path, Drawable drawable, List<Drawable> list) {
        super(str, str2);
        this.mAppIcons = list;
        this.mPath = path;
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable.getConstantState().newDrawable(), drawable.getConstantState().newDrawable()});
        this.mShape = layerDrawable;
        layerDrawable.setLayerGravity(0, 17);
        layerDrawable.setLayerGravity(1, 17);
        if (LogConfig.DBG) {
            Log.d("Styles", "ShapeOption: " + str + " | " + str2 + " | " + layerDrawable);
        }
    }

    public LayerDrawable getShape() {
        return this.mShape;
    }

    public List<Drawable> getAppIcons() {
        return this.mAppIcons;
    }

    public Path getPath() {
        return this.mPath;
    }

    public String toString() {
        return "ShapeOption@" + hashCode() + "{mValue='" + this.mValue + '\'' + ", mName='" + this.mName + '\'' + ", mUninstallable=" + this.mUninstallable + '}';
    }
}
