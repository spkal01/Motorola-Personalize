package com.android.wallpaper.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Insets;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.android.wallpaper.Utilities;

public class MainContainer extends FrameLayout {
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

    public MainContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    public MainContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MainContainer(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MainContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        Log.d("Styles", "onApplyWindowInsets: " + windowInsets);
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        if (Utilities.isKeyboardShowing((Activity) getContext())) {
            return onApplyWindowInsets;
        }
        Insets of = Insets.of(onApplyWindowInsets.getSystemWindowInsetLeft(), onApplyWindowInsets.getInsets(WindowInsets.Type.statusBars()).top, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom);
        Log.d("Styles", "windowInsets: " + of);
        post(new Runnable(of) {
            public final /* synthetic */ Insets f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainContainer.this.lambda$onApplyWindowInsets$0$MainContainer(this.f$1);
            }
        });
        return onApplyWindowInsets;
    }

    public /* synthetic */ void lambda$onApplyWindowInsets$0$MainContainer(Insets insets) {
        setPadding(insets.left, 0, insets.right, 0);
        View findViewWithTag = findViewWithTag("content");
        if (findViewWithTag != null) {
            findViewWithTag.setPaddingRelative(findViewWithTag.getPaddingStart(), findViewWithTag.getPaddingTop(), findViewWithTag.getPaddingEnd(), insets.bottom + findViewWithTag.getPaddingTop());
        }
        View findViewWithTag2 = findViewWithTag("fab");
        if (findViewWithTag2 != null) {
            findViewWithTag2.setTranslationY((float) (-insets.bottom));
        }
        View findViewWithTag3 = findViewWithTag("top_bar");
        if (findViewWithTag3 != null) {
            ((RelativeLayout.LayoutParams) findViewWithTag3.getLayoutParams()).height = insets.top + getActionBarHeight();
            findViewWithTag3.setPaddingRelative(findViewWithTag3.getPaddingStart(), insets.top, findViewWithTag3.getPaddingEnd(), findViewWithTag3.getPaddingBottom());
        }
        requestLayout();
    }

    public int getActionBarHeight() {
        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        return 0;
    }
}
