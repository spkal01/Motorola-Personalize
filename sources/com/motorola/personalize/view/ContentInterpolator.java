package com.motorola.personalize.view;

import android.view.animation.LinearInterpolator;

public class ContentInterpolator extends LinearInterpolator {
    private final boolean show;
    private final float step1 = 0.45f;
    private final float step2 = 0.5f;
    private final float step3 = 0.95f;

    public ContentInterpolator(boolean z) {
        this.show = z;
    }

    public float getInterpolation(float f) {
        float f2;
        float f3 = this.step1;
        if (f < f3) {
            f2 = 1.0f;
        } else if (f >= f3 && f < this.step2) {
            f2 = 1.0f - ((f - f3) * 20.0f);
        } else if (f < this.step2 || f >= this.step3) {
            f2 = (f - this.step3) * 20.0f;
        } else {
            f2 = 0.0f;
        }
        return this.show ? f2 : 1.0f - f2;
    }
}
