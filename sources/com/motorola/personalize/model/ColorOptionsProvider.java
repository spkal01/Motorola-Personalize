package com.motorola.personalize.model;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.monet.ColorScheme;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.AppDatabaseHelper;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import com.motorola.personalize.loader.WallpaperLoader;
import com.motorola.personalize.util.NoneSdkApi;
import com.motorola.personalize.util.Utilities;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.model.OverlayManagerCompat;
import com.motorola.styles.model.providers.StyleOptionsProvider;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

public class ColorOptionsProvider extends StyleOptionsProvider<ColorOption> {
    public static final String ACCENT3_COLOR = "moto.theme.customization.accent3_color";
    public static final String ACCENT_COLOR = "android.theme.customization.accent_color";
    public static final String COLOR_BOTH = "android.theme.customization.color_both";
    public static final String COLOR_INDEX = "android.theme.customization.color_index";
    public static final String COLOR_SOURCE = "android.theme.customization.color_source";
    public static final String COLOR_SOURCE_HOME = "home_wallpaper";
    public static final String COLOR_SOURCE_LOCK = "lock_wallpaper";
    public static final String COLOR_SOURCE_PRESET = "preset";
    public static final String PARAM_ICON_PACKAGE = "iconPackage";
    public static final String SYSTEM_PALETTE = "android.theme.customization.system_palette";
    private static final String TAG = "ColorOptionsProvider";
    private ColorOption mDefaultOption;
    private final String mDefaultThemePackage;
    private ColorOption mTempOption;

    public ColorOptionsProvider(Context context, OverlayManagerCompat overlayManagerCompat) {
        super(context, overlayManagerCompat, "android.theme.customization.accent_color");
        List<String> overlayPackagesForCategory = overlayManagerCompat.getOverlayPackagesForCategory(ResourceConstants.OVERLAY_CATEGORY_ANDROID_THEME, NoneSdkApi.myUserId(), ResourceConstants.ANDROID_PACKAGE);
        this.mDefaultThemePackage = overlayPackagesForCategory.isEmpty() ? null : overlayPackagesForCategory.get(0);
    }

    /* access modifiers changed from: protected */
    public void loadExcludedOverlayPackage(List<String> list) {
        list.addAll(Arrays.asList(getContext().getResources().getStringArray(C1057R.array.excluded_color_overlay_packages)));
    }

    public void loadOptions(Bundle bundle) {
        getStringParam(bundle, "iconPackage");
        loadOptions();
    }

    private void fillInOption(int i, boolean z) {
        List<Integer> list;
        if (z) {
            list = WallpaperLoader.getInstance().getHomeColorSeeds();
        } else {
            list = WallpaperLoader.getInstance().getLockColorSeeds();
        }
        for (int i2 = 0; i2 < Math.min(i, list.size()); i2++) {
            int intValue = list.get(i2).intValue();
            Log.d(TAG, "loadOptions: color = " + Utilities.getHexColor6(intValue) + " isHome?" + z);
            ColorOption colorOption = new ColorOption("Wallpaper", intValue, 2, z);
            colorOption.setColorScheme(new ColorScheme(intValue, Integer.MIN_VALUE, ContextExtensionsKt.isDarkModeEnabled(this.mContext)));
            colorOption.setColorIndex(i2);
            this.mOptions.add(colorOption);
        }
    }

    public void loadOptions() {
        addDefault();
        boolean isWallpaperSame = WallpaperLoader.getInstance().isWallpaperSame();
        Log.d(TAG, "loadOptions: wallpaperSame = " + isWallpaperSame);
        int i = isWallpaperSame ? 4 : 2;
        if (isWallpaperSame) {
            fillInOption(i, true);
        } else {
            boolean isHomeWallpaperNew = WallpaperLoader.getInstance().isHomeWallpaperNew();
            fillInOption(i, isHomeWallpaperNew);
            fillInOption(i, !isHomeWallpaperNew);
        }
        this.mOptions.add(new ColorOption("Color picker", -1, 1, false));
        for (OptionEntity colorOption : AppDatabaseHelper.getInstance().getDao().loadAllOptions()) {
            this.mOptions.add(colorOption.toColorOption());
        }
        Log.d(TAG, "loadOptions: " + this.mOptions.size());
    }

    public ColorOption onCustomColorAdd(int i) {
        return new ColorOption(String.valueOf(i), i, 3, false);
    }

    private ColorOption createAndFillInPresetColor(String str, String str2, String str3, String str4) {
        int parseColor = Color.parseColor(str);
        ColorOption colorOption = new ColorOption(String.valueOf(parseColor), parseColor, Color.parseColor(str2), Color.parseColor(str3), Color.parseColor(str4));
        this.mOptions.add(colorOption);
        return colorOption;
    }

    private void addDefault() {
        Log.d(TAG, "addDefault: ");
        this.mDefaultOption = createAndFillInPresetColor("#165C7D", "#654E78", "#00658D", "#C3E7FF");
        createAndFillInPresetColor("#654E78", "#874C60", "#764B9B", "#F2DAFF");
        createAndFillInPresetColor("#FF8A31", "#B7EDDF", "#984700", "#FFDBC5");
        createAndFillInPresetColor("#B7EDDF", "#165C7D", "#006B5B", "#77F8DE");
        createAndFillInPresetColor("#874C60", "#FF8A31", "#984062", "#FFD9E4");
    }

    public ColorOption getAppliedOption() {
        this.mTempOption = null;
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), ResourceConstants.THEME_SETTING);
        Log.d(TAG, "getAppliedOption: " + string);
        if (TextUtils.isEmpty(string)) {
            return this.mDefaultOption;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            String string2 = jSONObject.getString("android.theme.customization.color_source");
            if ("preset".equals(string2)) {
                String string3 = jSONObject.getString("android.theme.customization.accent_color");
                for (ColorOption colorOption : this.mOptions) {
                    if (Utilities.getHexColor6(colorOption.getLightColor()).equals(string3)) {
                        return colorOption;
                    }
                }
                Log.d(TAG, "getAppliedOption: return mDefaultOption 2");
                return this.mDefaultOption;
            } else if ("home_wallpaper".equals(string2)) {
                return findWallpaperOption(jSONObject, true);
            } else {
                if ("lock_wallpaper".equals(string2)) {
                    return findWallpaperOption(jSONObject, false);
                }
                return this.mDefaultOption;
            }
        } catch (Exception unused) {
            Log.d(TAG, "getAppliedOption: return mDefaultOption 1");
            return this.mDefaultOption;
        }
    }

    private ColorOption findWallpaperOption(JSONObject jSONObject, boolean z) {
        int i;
        this.mTempOption = null;
        try {
            i = jSONObject.getInt("android.theme.customization.color_index");
        } catch (Exception unused) {
            i = -1;
        }
        for (ColorOption colorOption : this.mOptions) {
            if (colorOption.getType() == 2 && z == colorOption.isHomeSource()) {
                if (this.mTempOption == null) {
                    Log.d(TAG, "getAppliedOption: set mTempOption 1");
                    this.mTempOption = colorOption;
                    if (i < 0) {
                        return colorOption;
                    }
                }
                if (i == colorOption.getColorIndex()) {
                    return colorOption;
                }
            }
        }
        ColorOption colorOption2 = this.mTempOption;
        return colorOption2 == null ? this.mDefaultOption : colorOption2;
    }
}
