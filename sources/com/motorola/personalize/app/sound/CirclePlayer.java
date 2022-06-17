package com.motorola.personalize.app.sound;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.motorola.personalize.C1057R;

public class CirclePlayer extends View {
    private static final String TAG = "CirclePlayer";
    private int direction;
    private Bitmap drawBitmapPlay;
    private Bitmap drawBitmapStop;
    private int height;
    private int insideColor;
    private boolean isPlay;
    private Paint mPaint;
    private float mProgressValue;
    private RectF mRectF;
    private int maxProgress;
    private int outsideColor;
    private float outsideRadius;
    private float progressWidth;
    private int width;

    public CirclePlayer(Context context) {
        super(context);
    }

    public CirclePlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public CirclePlayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        Log.i(TAG, "start init");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1057R.styleable.circle_progress_image_attrs, i, 0);
        this.outsideColor = obtainStyledAttributes.getColor(3, ContextCompat.getColor(context, C1057R.C1058color.design_primary));
        this.outsideRadius = obtainStyledAttributes.getDimension(4, 60.0f);
        this.insideColor = obtainStyledAttributes.getColor(1, ContextCompat.getColor(context, C1057R.C1058color.design_inside));
        this.progressWidth = obtainStyledAttributes.getDimension(7, 15.0f);
        this.mProgressValue = obtainStyledAttributes.getFloat(6, 0.0f);
        this.maxProgress = obtainStyledAttributes.getInt(2, 100);
        this.direction = obtainStyledAttributes.getInt(0, -90);
        int resourceId = obtainStyledAttributes.getResourceId(5, C1057R.C1059drawable.ic_play_24);
        int resourceId2 = obtainStyledAttributes.getResourceId(8, C1057R.C1059drawable.ic_stop);
        obtainStyledAttributes.recycle();
        this.drawBitmapPlay = getBitmapFromVectorDrawable(context, resourceId);
        this.drawBitmapStop = getBitmapFromVectorDrawable(context, resourceId2);
        this.mRectF = new RectF();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }

    public Bitmap getBitmapFromVectorDrawable(Context context, int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.width = measure(i);
        int measure = measure(i2);
        this.height = measure;
        setMeasuredDimension(this.width, measure);
    }

    public int measure(int i) {
        return View.MeasureSpec.getMode(i) == 1073741824 ? View.MeasureSpec.getSize(i) : (int) ((this.outsideRadius * 2.0f) + this.progressWidth);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0);
        this.mPaint.setColor(this.insideColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setDither(true);
        this.mPaint.setStrokeWidth(this.progressWidth);
        float width2 = (float) (getWidth() / 2);
        canvas.drawCircle(width2, width2, this.outsideRadius, this.mPaint);
        this.mPaint.setColor(this.outsideColor);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mRectF.left = width2 - this.outsideRadius;
        this.mRectF.top = width2 - this.outsideRadius;
        this.mRectF.bottom = this.outsideRadius + width2;
        this.mRectF.right = width2 + this.outsideRadius;
        canvas.drawArc(this.mRectF, (float) this.direction, (this.mProgressValue / ((float) this.maxProgress)) * 360.0f, false, this.mPaint);
        float width3 = (float) ((this.width - this.drawBitmapPlay.getWidth()) >> 1);
        float height2 = (float) ((this.height - this.drawBitmapPlay.getHeight()) >> 1);
        if (this.isPlay) {
            canvas.drawBitmap(this.drawBitmapStop, width3, height2, this.mPaint);
        } else {
            canvas.drawBitmap(this.drawBitmapPlay, width3, height2, this.mPaint);
        }
    }

    public void play(int i) {
        stop();
        this.isPlay = true;
        this.maxProgress = i;
    }

    public boolean isPlaying() {
        return this.isPlay;
    }

    public void setProgress(float f) {
        if (f >= 0.0f) {
            Log.i(TAG, "progress:" + f + ",maxProgress:" + this.maxProgress);
            if (f >= ((float) this.maxProgress)) {
                stop();
                return;
            }
            this.mProgressValue = f;
            postInvalidate();
            return;
        }
        throw new IllegalArgumentException("progress not less than 0");
    }

    public void stop() {
        this.isPlay = false;
        this.mProgressValue = 0.0f;
        invalidate();
    }
}
