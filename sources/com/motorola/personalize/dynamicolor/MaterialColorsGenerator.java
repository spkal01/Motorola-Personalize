package com.motorola.personalize.dynamicolor;

import android.app.WallpaperColors;
import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import com.android.systemui.monet.ColorScheme;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import java.util.List;

public class MaterialColorsGenerator {
    public static final int[] ACCENT_RESOURCES = {17170488, 17170489, 17170490, 17170491, 17170492, 17170493, 17170494, 17170495, 17170496, 17170497, 17170498, 17170499, 17170501, 17170502, 17170503, 17170504, 17170505, 17170506, 17170507, 17170508, 17170509, 17170510, 17170511, 17170512, 17170514, 17170515, 17170516, 17170517, 17170518, 17170519, 17170520, 17170521, 17170522, 17170523, 17170524, 17170525};
    public static final int[] NEUTRAL_RESOURCES = {17170462, 17170463, 17170464, 17170465, 17170466, 17170467, 17170468, 17170469, 17170470, 17170471, 17170472, 17170473, 17170475, 17170476, 17170477, 17170478, 17170479, 17170480, 17170481, 17170482, 17170483, 17170484, 17170485, 17170486};
    public final Context context;

    public MaterialColorsGenerator(Context context2) {
        this.context = context2;
    }

    private void extractColorForShades(List<Integer> list, int[] iArr, SparseIntArray sparseIntArray) {
        if (list.size() != iArr.length) {
            Log.e("MaterialColorsGenerator", "The number of shades computed doesn't match the number of resources.");
            return;
        }
        for (int i = 0; i < iArr.length; i++) {
            sparseIntArray.put(iArr[i], -16777216 | list.get(i).intValue());
        }
    }

    public SparseIntArray extractedColors(WallpaperColors wallpaperColors) {
        ColorScheme colorScheme = new ColorScheme(wallpaperColors, ContextExtensionsKt.isDarkModeEnabled(this.context));
        SparseIntArray sparseIntArray = new SparseIntArray();
        extractColorForShades(colorScheme.getAllNeutralColors(), NEUTRAL_RESOURCES, sparseIntArray);
        extractColorForShades(colorScheme.getAllAccentColors(), ACCENT_RESOURCES, sparseIntArray);
        return sparseIntArray;
    }

    public SparseIntArray extractedColors(int i) {
        ColorScheme colorScheme = new ColorScheme(i, Integer.MIN_VALUE, ContextExtensionsKt.isDarkModeEnabled(this.context));
        SparseIntArray sparseIntArray = new SparseIntArray();
        extractColorForShades(colorScheme.getAllNeutralColors(), NEUTRAL_RESOURCES, sparseIntArray);
        extractColorForShades(colorScheme.getAllAccentColors(), ACCENT_RESOURCES, sparseIntArray);
        return sparseIntArray;
    }
}
