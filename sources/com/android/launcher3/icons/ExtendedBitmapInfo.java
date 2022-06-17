package com.android.launcher3.icons;

public class ExtendedBitmapInfo {
    public static int sOffset;
    public static float sScale;

    public static void setInfo(BitmapInfo bitmapInfo, float f) {
        sScale = f;
        sOffset = (int) Math.ceil((double) (((float) bitmapInfo.icon.getWidth()) * 0.03125f));
    }
}
