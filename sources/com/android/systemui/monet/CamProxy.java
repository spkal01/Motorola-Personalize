package com.android.systemui.monet;

import com.android.internal.graphics.cam.Cam;
import com.android.internal.graphics.cam.CamUtils;

class CamProxy {
    private final Cam cam;

    private CamProxy(int i) {
        this.cam = Cam.fromInt(i);
    }

    public float getHue() {
        return this.cam.getHue();
    }

    public float getChroma() {
        return this.cam.getChroma();
    }

    public static CamProxy fromInt(int i) {
        return new CamProxy(i);
    }

    public static float lstarFromInt(int i) {
        return CamUtils.lstarFromInt(i);
    }
}
