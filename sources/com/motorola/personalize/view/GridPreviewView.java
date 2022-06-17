package com.motorola.personalize.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.extensions.Logger;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0014J\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo15462d2 = {"Lcom/motorola/personalize/view/GridPreviewView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mGridX", "mGridY", "onAttachedToWindow", "", "setGridValue", "gridX", "gridY", "setGuidePercent", "percent", "", "setHeightPercent", "id", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: GridPreviewView.kt */
public final class GridPreviewView extends ConstraintLayout {
    private int mGridX;
    private int mGridY;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GridPreviewView(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GridPreviewView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GridPreviewView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mGridX = 5;
        this.mGridY = 6;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GridPreviewView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GridPreviewView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "GridPreviewView Attached: grid x = " + this.mGridX + ", y = " + this.mGridY);
        }
        int i = this.mGridY;
        float f = 164.0f;
        float f2 = 310.0f;
        float f3 = 142.0f;
        if (i == 4) {
            setGuidePercent(0.4365f);
            f2 = 460.0f;
            if (this.mGridX != 4) {
                f = 188.0f;
            }
        } else if (i != 5) {
            if (i != 6) {
                String tag2 = Logger.INSTANCE.getTag();
                if (Logger.INSTANCE.getDEBUG()) {
                    Log.d(tag2, "onAttachedToWindow: error grid Y");
                }
            } else {
                setGuidePercent(0.5556f);
                if (this.mGridX != 5) {
                    f3 = 154.0f;
                }
            }
            setHeightPercent(C1057R.C1060id.preview_icons_hotseat, 0.1706f);
            float f4 = f2 / 2520.0f;
            setHeightPercent(C1057R.C1060id.preview_icons_workspace, f4);
            setHeightPercent(C1057R.C1060id.grid_preview_widget, f4);
            setHeightPercent(C1057R.C1060id.preview_icons_ws, f3 / f2);
            setHeightPercent(C1057R.C1060id.preview_icons_hs, f3 / 430.0f);
            setHeightPercent(C1057R.C1060id.grid_image_widget, ((float) 130) / f2);
        } else {
            setGuidePercent(0.5079f);
            f2 = 372.0f;
            if (this.mGridX == 5) {
                f = 142.0f;
            }
        }
        f3 = f;
        setHeightPercent(C1057R.C1060id.preview_icons_hotseat, 0.1706f);
        float f42 = f2 / 2520.0f;
        setHeightPercent(C1057R.C1060id.preview_icons_workspace, f42);
        setHeightPercent(C1057R.C1060id.grid_preview_widget, f42);
        setHeightPercent(C1057R.C1060id.preview_icons_ws, f3 / f2);
        setHeightPercent(C1057R.C1060id.preview_icons_hs, f3 / 430.0f);
        setHeightPercent(C1057R.C1060id.grid_image_widget, ((float) 130) / f2);
    }

    private final void setGuidePercent(float f) {
        View findViewById = findViewById(C1057R.C1060id.guideline_base);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.guidePercent = f;
        findViewById.setLayoutParams(layoutParams2);
    }

    private final void setHeightPercent(int i, float f) {
        View findViewById = findViewById(i);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.matchConstraintPercentHeight = f;
        findViewById.setLayoutParams(layoutParams2);
    }

    public final void setGridValue(int i, int i2) {
        this.mGridX = i;
        this.mGridY = i2;
    }
}
