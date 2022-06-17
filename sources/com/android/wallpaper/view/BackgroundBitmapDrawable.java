package com.android.wallpaper.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

public class BackgroundBitmapDrawable extends BitmapDrawable {
    private Matrix mMatrix = new Matrix();
    boolean simpleMapping = false;

    public BackgroundBitmapDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (getBitmap() != null) {
            Bitmap bitmap = getBitmap();
            RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
            if (this.simpleMapping) {
                this.mMatrix.setRectToRect(rectF, new RectF(rect), Matrix.ScaleToFit.CENTER);
                return;
            }
            float width = rectF.width();
            float height = ((float) rect.height()) / rectF.height();
            float width2 = ((float) rect.width()) / width;
            if (height <= width2) {
                height = width2;
            }
            this.mMatrix.setScale(height, height);
        }
    }

    public void draw(Canvas canvas) {
        if (getBitmap() != null) {
            canvas.drawBitmap(getBitmap(), this.mMatrix, (Paint) null);
            canvas.drawColor(Color.argb(25, 0, 0, 0));
        }
    }
}
