package com.motorola.personalize.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.motorola.personalize.C1057R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0014J\u0012\u0010$\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u000e\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0007R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, mo15462d2 = {"Lcom/motorola/personalize/view/RoundImgView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "clipRadii", "", "cornerBottomEndRadius", "cornerBottomStartRadius", "cornerRadius", "cornerTopEndRadius", "cornerTopStartRadius", "mBitmap", "Landroid/graphics/Bitmap;", "mBitmapPaint", "Landroid/graphics/Paint;", "mBitmapShader", "Landroid/graphics/BitmapShader;", "mDrawableRect", "Landroid/graphics/RectF;", "mMargin", "", "path", "Landroid/graphics/Path;", "getBitmapFromDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "initCornerRadius", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "setImageDrawable", "setMargin", "margin", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: RoundImgView.kt */
public final class RoundImgView extends AppCompatImageView {
    private final float[] clipRadii;
    private int cornerBottomEndRadius;
    private int cornerBottomStartRadius;
    private int cornerRadius;
    private int cornerTopEndRadius;
    private int cornerTopStartRadius;
    private Bitmap mBitmap;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private final RectF mDrawableRect;
    private float mMargin;
    private final Path path;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RoundImgView(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RoundImgView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RoundImgView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoundImgView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mDrawableRect = new RectF();
        this.clipRadii = new float[8];
        Paint paint = new Paint();
        this.mBitmapPaint = paint;
        this.path = new Path();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1057R.styleable.RoundImgView, i, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttributes(\n            attrs,\n            R.styleable.RoundImgView, defStyle, 0\n        )");
        this.cornerRadius = obtainStyledAttributes.getDimensionPixelSize(2, this.cornerRadius);
        this.cornerTopStartRadius = obtainStyledAttributes.getDimensionPixelSize(4, this.cornerTopStartRadius);
        this.cornerTopEndRadius = obtainStyledAttributes.getDimensionPixelSize(3, this.cornerTopEndRadius);
        this.cornerBottomStartRadius = obtainStyledAttributes.getDimensionPixelSize(1, this.cornerBottomStartRadius);
        this.cornerBottomEndRadius = obtainStyledAttributes.getDimensionPixelSize(0, this.cornerBottomEndRadius);
        obtainStyledAttributes.recycle();
        initCornerRadius();
        paint.setAntiAlias(true);
    }

    private final void initCornerRadius() {
        int i = 0;
        if (this.cornerRadius > 0) {
            int length = this.clipRadii.length - 1;
            if (length >= 0) {
                while (true) {
                    int i2 = i + 1;
                    this.clipRadii[i] = (float) this.cornerRadius;
                    if (i2 <= length) {
                        i = i2;
                    } else {
                        return;
                    }
                }
            }
        } else {
            float[] fArr = this.clipRadii;
            fArr[1] = (float) this.cornerTopStartRadius;
            fArr[0] = fArr[1];
            fArr[3] = (float) this.cornerTopEndRadius;
            fArr[2] = fArr[3];
            fArr[5] = (float) this.cornerBottomEndRadius;
            fArr[4] = fArr[5];
            fArr[7] = (float) this.cornerBottomStartRadius;
            fArr[6] = fArr[7];
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        BitmapShader bitmapShader = this.mBitmapShader;
        if (bitmapShader != null) {
            this.mBitmapPaint.setShader(bitmapShader);
        }
        this.mDrawableRect.left = this.mMargin;
        this.mDrawableRect.top = this.mMargin;
        this.mDrawableRect.right = ((float) getWidth()) - this.mMargin;
        this.mDrawableRect.bottom = (float) getHeight();
        this.path.reset();
        this.path.addRoundRect(this.mDrawableRect, this.clipRadii, Path.Direction.CCW);
        canvas.clipPath(this.path);
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mBitmapPaint);
        }
        super.onDraw(canvas);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        Bitmap bitmapFromDrawable = getBitmapFromDrawable(drawable);
        this.mBitmap = bitmapFromDrawable;
        if (bitmapFromDrawable != null) {
            Bitmap bitmap = this.mBitmap;
            Intrinsics.checkNotNull(bitmap);
            this.mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }
        invalidate();
    }

    public final void setMargin(int i) {
        this.mMargin = (float) i;
    }

    private final Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }
}
