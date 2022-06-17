package com.android.wallpaper;

import android.graphics.Color;
import android.util.Log;
import com.android.systemui.monet.ColorScheme;
import com.motorola.styles.model.ColorOption;

public class FixedThemeColor {
    private static final String TAG = "FixedThemeColor";
    private static ColorScheme sColorScheme;

    public static int getColorOfAccent1(int i) {
        return getColorScheme().getAccent1().get(i).intValue();
    }

    public static int getColorOfAccent2(int i) {
        return getColorScheme().getAccent2().get(i).intValue();
    }

    public static int getColorOfAccent3(int i) {
        return getColorScheme().getAccent3().get(i).intValue();
    }

    public static int getColorOfNeutral1(int i) {
        return getColorScheme().getNeutral1().get(i).intValue();
    }

    public static int getColorOfNeutral2(int i) {
        return getColorScheme().getNeutral2().get(i).intValue();
    }

    private static ColorScheme getColorScheme() {
        return sColorScheme;
    }

    private static void setColorScheme(int i, boolean z) {
        sColorScheme = ColorOption.getColorScheme(i, z);
    }

    public static void genColors() {
        int parseColor = Color.parseColor("#FF8A31");
        int[] iArr = {10, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        Log.d(TAG, "genColors: Light");
        setColorScheme(parseColor, false);
        Log.d(TAG, String.format("<color name=\"system_accent1_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i = 0; i < 12; i++) {
            Log.d(TAG, String.format("<color name=\"system_accent1_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i]), ColorOption.getHexColor6(getColorOfAccent1(i))}));
        }
        Log.d(TAG, String.format("<color name=\"system_accent2_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i2 = 0; i2 < 12; i2++) {
            Log.d(TAG, String.format("<color name=\"system_accent2_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i2]), ColorOption.getHexColor6(getColorOfAccent2(i2))}));
        }
        Log.d(TAG, String.format("<color name=\"system_accent3_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i3 = 0; i3 < 12; i3++) {
            Log.d(TAG, String.format("<color name=\"system_accent3_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i3]), ColorOption.getHexColor6(getColorOfAccent3(i3))}));
        }
        Log.d(TAG, String.format("<color name=\"system_neutral1_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i4 = 0; i4 < 12; i4++) {
            Log.d(TAG, String.format("<color name=\"system_neutral1_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i4]), ColorOption.getHexColor6(getColorOfNeutral1(i4))}));
        }
        Log.d(TAG, String.format("<color name=\"system_neutral2_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i5 = 0; i5 < 12; i5++) {
            Log.d(TAG, String.format("<color name=\"system_neutral2_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i5]), ColorOption.getHexColor6(getColorOfNeutral2(i5))}));
        }
        Log.d(TAG, "genColors: Dark");
        setColorScheme(parseColor, true);
        Log.d(TAG, String.format("<color name=\"system_accent1_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i6 = 0; i6 < 12; i6++) {
            Log.d(TAG, String.format("<color name=\"system_accent1_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i6]), ColorOption.getHexColor6(getColorOfAccent1(i6))}));
        }
        Log.d(TAG, String.format("<color name=\"system_accent2_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i7 = 0; i7 < 12; i7++) {
            Log.d(TAG, String.format("<color name=\"system_accent2_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i7]), ColorOption.getHexColor6(getColorOfAccent2(i7))}));
        }
        Log.d(TAG, String.format("<color name=\"system_accent3_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i8 = 0; i8 < 12; i8++) {
            Log.d(TAG, String.format("<color name=\"system_accent3_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i8]), ColorOption.getHexColor6(getColorOfAccent3(i8))}));
        }
        Log.d(TAG, String.format("<color name=\"system_neutral1_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i9 = 0; i9 < 12; i9++) {
            Log.d(TAG, String.format("<color name=\"system_neutral1_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i9]), ColorOption.getHexColor6(getColorOfNeutral1(i9))}));
        }
        Log.d(TAG, String.format("<color name=\"system_neutral2_%s\">%s</color>", new Object[]{"0", "#FFFFFF"}));
        for (int i10 = 0; i10 < 12; i10++) {
            Log.d(TAG, String.format("<color name=\"system_neutral2_%s\">%s</color>", new Object[]{Integer.valueOf(iArr[i10]), ColorOption.getHexColor6(getColorOfNeutral2(i10))}));
        }
    }
}
