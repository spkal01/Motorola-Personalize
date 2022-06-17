package com.motorola.personalize.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.util.HslColor;

public class PaletteView extends View {
    public static final String TAG = "PaletteView";
    private Paint mBorderPaint;
    private float mBorderWidth;
    private final RectF mDrawingRect;
    private float mHue;
    private Paint mHuePaint;
    private float mHuePanelHeight;
    private final RectF mHueRealRect;
    private final RectF mHueRect;
    private Shader mHueShader;
    private final RectF mHueTouchableRect;
    private float mHueTrackerHeight;
    private Paint mHueTrackerPaint;
    private final RectF mHueTrackerRect;
    private float mHueTrackerWidth;
    private final Point mHueValToPoint;
    private OnColorChangedListener mListener;
    private final float mLum;
    private final float mPreferredHeight;
    private final float mSat;
    private Point mStartTouchPoint;
    private float mTrackerRadius;
    private int mTrackerStrokeColor;
    private final float mTrackerStrokeWidth;

    interface OnColorChangedListener {
        void onColorChanged(int i);

        void onHueChanged(float f);
    }

    public PaletteView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PaletteView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PaletteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHueValToPoint = new Point();
        this.mHue = 0.0f;
        this.mSat = 1.0f;
        this.mLum = 0.5f;
        this.mDrawingRect = new RectF();
        this.mHueRect = new RectF();
        this.mHueRealRect = new RectF();
        this.mHueTrackerRect = new RectF();
        this.mHueTouchableRect = new RectF();
        this.mStartTouchPoint = null;
        setFocusable(true);
        setFocusableInTouchMode(true);
        Resources resources = context.getResources();
        this.mBorderWidth = 0.0f;
        this.mHuePanelHeight = resources.getDimension(C1057R.dimen.hue_height);
        float dimension = resources.getDimension(C1057R.dimen.sv_height);
        float dimension2 = resources.getDimension(C1057R.dimen.gap_hue_sv);
        this.mTrackerRadius = resources.getDimension(C1057R.dimen.sv_tracker_radius);
        this.mTrackerStrokeWidth = resources.getDimension(C1057R.dimen.sv_tracker_border);
        this.mHueTrackerHeight = resources.getDimension(C1057R.dimen.hue_tracker_height);
        this.mHueTrackerWidth = resources.getDimension(C1057R.dimen.hue_tracker_width);
        this.mTrackerStrokeColor = resources.getColor(C1057R.C1058color.color_picker_sv_stroker, context.getTheme());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1057R.styleable.PaletteView, i, 0);
        this.mBorderWidth = obtainStyledAttributes.getDimension(0, this.mBorderWidth);
        this.mHuePanelHeight = obtainStyledAttributes.getDimension(2, this.mHuePanelHeight);
        float dimension3 = obtainStyledAttributes.getDimension(5, dimension);
        float dimension4 = obtainStyledAttributes.getDimension(1, dimension2);
        this.mTrackerRadius = obtainStyledAttributes.getDimension(6, this.mTrackerRadius);
        this.mHueTrackerHeight = obtainStyledAttributes.getDimension(3, this.mHueTrackerHeight);
        this.mHueTrackerWidth = obtainStyledAttributes.getDimension(4, this.mHueTrackerWidth);
        this.mTrackerStrokeColor = obtainStyledAttributes.getColor(3, this.mTrackerStrokeColor);
        obtainStyledAttributes.recycle();
        this.mPreferredHeight = dimension3 + dimension4 + this.mHuePanelHeight;
        initPaintTools();
    }

    private void initPaintTools() {
        this.mHuePaint = new Paint(1);
        this.mHueTrackerPaint = new Paint(1);
        this.mBorderPaint = new Paint(1);
        this.mHueTrackerPaint.setStyle(Paint.Style.STROKE);
        this.mHueTrackerPaint.setStrokeWidth(this.mTrackerStrokeWidth);
        this.mHueTrackerPaint.setColor(this.mTrackerStrokeColor);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(View.MeasureSpec.getSize(i), (int) this.mPreferredHeight);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDrawingRect.width() > 0.0f && this.mDrawingRect.height() > 0.0f) {
            drawHuePanel(canvas);
        }
    }

    private void drawHuePanel(Canvas canvas) {
        RectF rectF = this.mHueRect;
        this.mBorderPaint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, this.mBorderPaint);
        this.mBorderPaint.setColor(0);
        canvas.drawRect((rectF.left - this.mBorderWidth) + (rectF.height() / 2.0f), rectF.top - this.mBorderWidth, (rectF.right + this.mBorderWidth) - (rectF.height() / 2.0f), rectF.bottom + this.mBorderWidth, this.mBorderPaint);
        if (this.mHueShader == null) {
            int[] iArr = new int[361];
            int i = 360;
            int i2 = 0;
            while (i2 < 361) {
                iArr[i] = Color.HSVToColor(new float[]{(float) i2, 1.0f, 1.0f});
                i2++;
                i--;
            }
            LinearGradient linearGradient = new LinearGradient(this.mHueRealRect.right, this.mHueRealRect.bottom, this.mHueRealRect.left, this.mHueRealRect.bottom, iArr, (float[]) null, Shader.TileMode.CLAMP);
            this.mHueShader = linearGradient;
            this.mHuePaint.setShader(linearGradient);
        }
        canvas.drawRect(this.mHueRealRect, this.mHuePaint);
        Point hueToPoint = hueToPoint(this.mHue, this.mHueValToPoint);
        float centerY = rectF.centerY();
        float f = this.mHueTrackerWidth / 2.0f;
        this.mHueTrackerRect.left = ((float) hueToPoint.x) - f;
        this.mHueTrackerRect.right = ((float) hueToPoint.x) + f;
        this.mHueTrackerRect.top = centerY - (this.mHueTrackerHeight / 2.0f);
        this.mHueTrackerRect.bottom = centerY + (this.mHueTrackerHeight / 2.0f);
        canvas.drawCircle((float) hueToPoint.x, (this.mHueTrackerRect.top + this.mHueTrackerRect.bottom) / 2.0f, this.mTrackerRadius, this.mHueTrackerPaint);
    }

    private Point hueToPoint(float f, Point point) {
        RectF rectF = this.mHueRealRect;
        float width = rectF.width();
        if (point == null) {
            point = new Point();
        }
        point.x = (int) (((f * width) / 360.0f) + rectF.left);
        point.y = (int) rectF.top;
        return point;
    }

    private float pointToHue(float f) {
        float f2;
        RectF rectF = this.mHueRect;
        float width = rectF.width();
        if (f < rectF.left) {
            f2 = 0.0f;
        } else if (f > rectF.right) {
            f2 = width;
        } else {
            f2 = f - rectF.left;
        }
        return 360.0f - (((width - f2) * 360.0f) / width);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mStartTouchPoint = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
            z = moveTrackersIfNeeded(motionEvent);
        } else if (action == 1) {
            this.mStartTouchPoint = null;
            z = moveTrackersIfNeeded(motionEvent);
        } else if (action != 2) {
            z = false;
        } else {
            z = moveTrackersIfNeeded(motionEvent);
            if (z && getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        if (!z) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.mListener != null) {
            Log.d(TAG, "onTouchEvent: mHue = " + this.mHue + ", " + 1.0f + ", " + 0.5f);
            this.mListener.onColorChanged(HslColor.HSLToRGB(this.mHue, 1.0f, 0.5f));
            this.mListener.onHueChanged(this.mHue);
        }
        invalidate();
        return true;
    }

    private boolean moveTrackersIfNeeded(MotionEvent motionEvent) {
        Point point = this.mStartTouchPoint;
        if (point == null) {
            return false;
        }
        if (!this.mHueTouchableRect.contains((float) point.x, (float) this.mStartTouchPoint.y)) {
            return false;
        }
        this.mHue = pointToHue(motionEvent.getX());
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Log.d(TAG, "onSizeChanged: (w,h) = (" + i + "," + i2 + "), (ow,oh) = (" + i + "," + i2 + ")");
        this.mDrawingRect.left = (float) getPaddingLeft();
        this.mDrawingRect.right = (float) (i - getPaddingRight());
        this.mDrawingRect.top = (float) getPaddingTop();
        this.mDrawingRect.bottom = (float) (i2 - getPaddingBottom());
        Log.d(TAG, "onSizeChanged: + mDrawingRect = " + this.mDrawingRect);
        setUpHueRect();
        this.mHueTouchableRect.set(this.mHueRect.left - (this.mHueTrackerWidth / 2.0f), this.mHueRect.centerY() - (this.mHueTrackerHeight / 2.0f), this.mHueRect.right + (this.mHueTrackerWidth / 2.0f), this.mHueRect.centerY() + (this.mHueTrackerHeight / 2.0f));
    }

    private void setUpHueRect() {
        RectF rectF = this.mDrawingRect;
        float f = rectF.left + this.mBorderWidth;
        float f2 = rectF.top;
        float f3 = rectF.top + this.mHuePanelHeight;
        float f4 = rectF.right - this.mBorderWidth;
        this.mHueRect.set(f, f2, f4, f3);
        float height = this.mHueRect.height() / 2.0f;
        this.mHueRealRect.set(f + height, f2, f4 - height, f3);
    }

    public void setOnColorChangedListener(OnColorChangedListener onColorChangedListener) {
        this.mListener = onColorChangedListener;
        onColorChangedListener.onColorChanged(HslColor.HSLToRGB(this.mHue, 1.0f, 0.5f));
        this.mListener.onHueChanged(this.mHue);
    }
}
