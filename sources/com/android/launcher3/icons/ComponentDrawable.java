package com.android.launcher3.icons;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

public class ComponentDrawable extends DrawableWrapper {
    public ComponentName component;
    public Drawable originDrawable;

    public ComponentDrawable(Drawable drawable, ComponentName componentName) {
        super(drawable);
        this.originDrawable = drawable;
        this.component = componentName;
    }
}
