package com.android.wallpaper.view;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

public class GradientTopDrawable extends DrawableWrapper {
    private int mColor;
    private int mHeight;

    public GradientTopDrawable(Drawable drawable) {
        super(drawable);
    }

    public GradientTopDrawable setGradient(int i, int i2) {
        this.mHeight = i;
        this.mColor = i2;
        return this;
    }

    public void draw(Canvas canvas) {
        if (getDrawable() != null) {
            getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        super.draw(canvas);
        if (this.mColor != 0 && this.mHeight != 0) {
            Paint paint = new Paint(1);
            paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) this.mHeight, this.mColor, 0, Shader.TileMode.CLAMP));
            canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) this.mHeight, paint);
        }
    }
}
