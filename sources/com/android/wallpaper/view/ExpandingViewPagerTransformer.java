package com.android.wallpaper.view;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

public class ExpandingViewPagerTransformer implements ViewPager.PageTransformer {
    public static final float MAX_SCALE = 1.0f;
    public static final float MIN_SCALE = 1.0f;

    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            f = -1.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        float f2 = ((f < 0.0f ? f + 1.0f : 1.0f - f) * 0.0f) + 1.0f;
        view.setScaleX(f2);
        view.setScaleY(f2);
        setCardAlpha(view, f);
    }

    private void setCardAlpha(View view, float f) {
        float f2 = 0.3f;
        if (0.0f <= f && f <= 1.0f) {
            f2 = 0.3f + (1.0f - f);
        } else if (-1.0f <= f && f < 0.0f) {
            f2 = 0.3f + f + 1.0f;
        }
        view.setAlpha(f2);
    }
}
