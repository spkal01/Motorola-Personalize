package com.motorola.personalize.model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.motorola.personalize.util.Utilities;
import com.motorola.styles.LogConfig;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.OnlineFontOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.OverlayManagerCompat;
import com.motorola.styles.model.ShapeOption;
import com.motorola.styles.model.providers.FontOptionsProvider;
import com.motorola.styles.model.providers.GridOptionsProvider;
import com.motorola.styles.model.providers.ShapeOptionsProvider;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public final class StylesManager {
    public static final String APPLIED_TIMESTAMP = "_applied_timestamp";
    public static final String STYLE_LIST = "styleList";
    public static final String TAG = "StylesManager";
    private Context mAppContext;
    private ColorOptionsProvider mColorOptionsProvider;
    private AtomicBoolean mColorUpdated = new AtomicBoolean(false);
    private FontOptionsProvider mFontOptionsProvider;
    private AtomicBoolean mFontUpdated = new AtomicBoolean(false);
    private GridOptionsProvider mGridOptionsProvider;
    private ShapeOptionsProvider mShapeOptionsProvider;

    public StylesManager(Context context) {
        this.mAppContext = context.getApplicationContext();
        OverlayManagerCompat overlayManagerCompat = new OverlayManagerCompat(this.mAppContext);
        this.mColorOptionsProvider = new ColorOptionsProvider(this.mAppContext, overlayManagerCompat);
        this.mFontOptionsProvider = new FontOptionsProvider(this.mAppContext, overlayManagerCompat);
        this.mShapeOptionsProvider = new ShapeOptionsProvider(this.mAppContext, overlayManagerCompat);
        this.mGridOptionsProvider = new GridOptionsProvider(this.mAppContext);
    }

    public void updateOptions(Style style) {
        style.setColorOption(getColorOption(style.getColor()));
        style.setFontOption(getFontOption(style.getFont()));
        style.setShapeOption(getShapeOption(style.getShape()));
        style.setGridOption(getGridOption(style.getGrid()));
    }

    public ColorOption getColorOption(String str) {
        List<ColorOption> loadColorOptions = loadColorOptions();
        for (ColorOption next : loadColorOptions) {
            if (next.getValue().equals(str)) {
                return next;
            }
        }
        return loadColorOptions.get(0);
    }

    public List<ColorOption> loadColorOptions() {
        Log.d(TAG, "loadColorOptions: ");
        this.mColorOptionsProvider.loadOptions(this.mColorUpdated.getAndSet(false));
        return this.mColorOptionsProvider.getOptions();
    }

    private FontOption getFontOption(String str) {
        List<FontOption> loadFontOptions = loadFontOptions();
        for (FontOption next : loadFontOptions) {
            if (!next.isPlaceHolder() && next.getValue().equals(str)) {
                return next;
            }
        }
        return loadFontOptions.get(0);
    }

    public List<FontOption> loadFontOptions() {
        this.mFontOptionsProvider.loadOptions(this.mFontUpdated.getAndSet(false));
        return this.mFontOptionsProvider.getOptions();
    }

    private ShapeOption getShapeOption(String str) {
        List<ShapeOption> loadShapeOptions = loadShapeOptions();
        for (ShapeOption next : loadShapeOptions) {
            if (next.getValue().equals(str)) {
                return next;
            }
        }
        return loadShapeOptions.get(0);
    }

    public List<ShapeOption> loadShapeOptions() {
        this.mShapeOptionsProvider.loadOptions(false);
        return this.mShapeOptionsProvider.getOptions();
    }

    public List<GridOption> loadGridOptions() {
        this.mGridOptionsProvider.loadOptions(false);
        return this.mGridOptionsProvider.getOptions();
    }

    private GridOption getGridOption(String str) {
        List<GridOption> loadGridOptions = loadGridOptions();
        for (GridOption next : loadGridOptions) {
            if (next.getValue().equals(str)) {
                return next;
            }
        }
        return loadGridOptions.get(0);
    }

    public void setFontUpdated() {
        this.mFontUpdated.set(true);
    }

    public void setColorChanged() {
        this.mColorUpdated.set(true);
    }

    public ColorOptionsProvider getColorOptionsProvider() {
        return this.mColorOptionsProvider;
    }

    public FontOptionsProvider getFontOptionsProvider() {
        return this.mFontOptionsProvider;
    }

    public ShapeOptionsProvider getShapeOptionsProvider() {
        return this.mShapeOptionsProvider;
    }

    public GridOptionsProvider getGridOptionsProvider() {
        return this.mGridOptionsProvider;
    }

    public static String getUpdatedThemeSettingValue(String str, Option option) {
        JSONObject jSONObject;
        Log.d(TAG, "getUpdatedThemeSettingValue: " + str);
        if (TextUtils.isEmpty(str)) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(str);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            if (option instanceof FontOption) {
                if (((FontOption) option).isOnlineFont()) {
                    jSONObject.put(ResourceConstants.OVERLAY_CATEGORY_FONT, ((OnlineFontOption) option).getOuterPackageName());
                } else {
                    jSONObject.put(ResourceConstants.OVERLAY_CATEGORY_FONT, option.getValue());
                }
            }
            if (option instanceof ColorOption) {
                ColorOption colorOption = (ColorOption) option;
                if (colorOption.getType() == 2) {
                    String hexColor6 = Utilities.getHexColor6(colorOption.getLightColor());
                    jSONObject.put("android.theme.customization.system_palette", hexColor6);
                    jSONObject.put("android.theme.customization.accent_color", hexColor6);
                    jSONObject.remove("moto.theme.customization.accent3_color");
                    jSONObject.put("android.theme.customization.color_source", colorOption.isHomeSource() ? "home_wallpaper" : "lock_wallpaper");
                    jSONObject.put("android.theme.customization.color_index", colorOption.getColorIndex());
                } else if (colorOption.getType() == 0) {
                    String hexColor62 = Utilities.getHexColor6(colorOption.getLightColor());
                    jSONObject.put("android.theme.customization.system_palette", hexColor62);
                    jSONObject.put("android.theme.customization.accent_color", hexColor62);
                    jSONObject.remove("moto.theme.customization.accent3_color");
                    jSONObject.put("android.theme.customization.color_source", "preset");
                } else {
                    String hexColor63 = Utilities.getHexColor6(colorOption.getLightColor());
                    jSONObject.put("android.theme.customization.system_palette", hexColor63);
                    jSONObject.put("android.theme.customization.accent_color", hexColor63);
                    jSONObject.remove("moto.theme.customization.accent3_color");
                    jSONObject.put("android.theme.customization.color_source", "preset");
                }
            }
            if (option instanceof ShapeOption) {
                jSONObject.put(ResourceConstants.OVERLAY_CATEGORY_SHAPE, option.getValue());
            }
            if (jSONObject.names() != null) {
                jSONObject.put("_applied_timestamp", System.currentTimeMillis());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (LogConfig.DBG) {
            Log.d(TAG, "getUpdatedThemeSettingValue - Theme settings: " + jSONObject);
        }
        return jSONObject.toString();
    }

    public Style getSystemAppliedStyle() {
        loadColorOptions();
        loadFontOptions();
        loadShapeOptions();
        loadGridOptions();
        Style style = new Style();
        style.setColor(getAppliedColor().getValue());
        style.setFont(getAppliedFont().getValue());
        style.setShape(getAppliedShape().getValue());
        style.setGrid(getAppliedGrid().getValue());
        return style;
    }

    public GridOption getAppliedGrid() {
        GridOption appliedOption = getGridOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d(TAG, "getAppliedGrid: " + appliedOption);
        }
        return appliedOption;
    }

    public ShapeOption getAppliedShape() {
        ShapeOption appliedOption = getShapeOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d(TAG, "getAppliedShape: " + appliedOption);
        }
        return appliedOption;
    }

    public FontOption getAppliedFont() {
        FontOption appliedOption = getFontOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d(TAG, "getAppliedFont: " + appliedOption);
        }
        return appliedOption;
    }

    public ColorOption getAppliedColor() {
        ColorOption appliedOption = getColorOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d(TAG, "getAppliedColor: " + appliedOption);
        }
        return appliedOption;
    }
}
