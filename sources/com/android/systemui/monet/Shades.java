package com.android.systemui.monet;

import com.android.internal.graphics.ColorUtils;

public class Shades {
    public static final float MIDDLE_LSTAR = 49.6f;

    /* renamed from: of */
    public static int[] m26of(float f, float f2) {
        int[] iArr = new int[12];
        iArr[0] = ColorUtils.CAMToColor(f, Math.min(40.0f, f2), 99.0f);
        iArr[1] = ColorUtils.CAMToColor(f, Math.min(40.0f, f2), 95.0f);
        int i = 2;
        while (i < 12) {
            float f3 = i == 6 ? 49.6f : (float) (100 - ((i - 1) * 10));
            if (f3 >= 90.0f) {
                f2 = Math.min(40.0f, f2);
            }
            iArr[i] = ColorUtils.CAMToColor(f, f2, f3);
            i++;
        }
        return iArr;
    }
}
