package com.motorola.personalize.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import com.google.android.material.card.MaterialCardView;
import com.motorola.personalize.C1057R;

public class FreeCardView extends MaterialCardView {
    private float blRadius;
    private float brRadius;
    private final Path path;
    private float tlRadius;
    private float trRadius;

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public FreeCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FreeCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1057R.attr.materialCardViewStyle);
    }

    public FreeCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.path = new Path();
        setRadius(0.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1057R.styleable.FreeCardView);
        this.tlRadius = obtainStyledAttributes.getDimension(2, 0.0f);
        this.trRadius = obtainStyledAttributes.getDimension(3, 0.0f);
        this.brRadius = obtainStyledAttributes.getDimension(1, 0.0f);
        this.blRadius = obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        setBackground(new ColorDrawable());
    }

    public void setCorner(boolean z) {
        if (z) {
            setRadius(0.0f, getContext().getResources().getDimension(C1057R.dimen.preview_card_radius_big));
        } else {
            setRadius(getContext().getResources().getDimension(C1057R.dimen.preview_card_radius));
        }
    }

    public void setRadius(float f, float f2, float f3, float f4) {
        this.tlRadius = f;
        this.trRadius = f2;
        this.brRadius = f3;
        this.blRadius = f4;
        invalidate();
    }

    public void setRadius(float f, float f2) {
        setRadius(f, f, f2, f2);
    }

    public void setRadius(float f) {
        setRadius(f, f, f, f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.path.reset();
        RectF rectF = getRectF();
        float f = this.tlRadius;
        float f2 = this.trRadius;
        float f3 = this.brRadius;
        float f4 = this.blRadius;
        this.path.addRoundRect(rectF, new float[]{f, f, f2, f2, f3, f3, f4, f4}, Path.Direction.CW);
        canvas.clipPath(this.path, Region.Op.INTERSECT);
        super.onDraw(canvas);
    }

    private RectF getRectF() {
        Rect rect = new Rect();
        getDrawingRect(rect);
        return new RectF(rect);
    }
}
