package com.motorola.styles.model;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.motorola.styles.C1087R;
import com.motorola.styles.LogConfig;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.providers.ColorOptionsProvider;
import com.motorola.styles.model.providers.FontOptionsProvider;
import com.motorola.styles.model.providers.GridOptionsProvider;
import com.motorola.styles.model.providers.RingtoneOptionsProvider;
import com.motorola.styles.model.providers.ShapeOptionsProvider;
import com.motorola.styles.model.providers.StyleOptionsProvider;
import com.motorola.styles.model.providers.WallpaperOptionsProvider;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ThemesRepo {
    public static final String ACCENT3_COLOR = "moto.theme.customization.accent3_color";
    public static final String ACCENT_COLOR = "android.theme.customization.accent_color";
    public static final String APPLIED_THEME = "appliedTheme";
    public static final String APPLIED_TIMESTAMP = "_applied_timestamp";
    public static final String COLOR_BOTH = "android.theme.customization.color_both";
    public static final String COLOR_INDEX = "android.theme.customization.color_index";
    public static final String COLOR_SOURCE = "android.theme.customization.color_source";
    public static final String COLOR_SOURCE_HOME = "home_wallpaper";
    public static final String COLOR_SOURCE_LOCK = "lock_wallpaper";
    public static final String COLOR_SOURCE_PRESET = "preset";
    public static final Pattern PATTERN_SEPARATOR = Pattern.compile(",");
    public static final String SYSTEM_PALETTE = "android.theme.customization.system_palette";
    public static final String THEME_LIST = "themeList";
    private Context mAppContext;
    private BackupManager mBackupManager;
    private ColorOptionsProvider mColorOptionsProvider;
    private FontOptionsProvider mFontOptionsProvider;
    private AtomicBoolean mFontUpdated = new AtomicBoolean(false);
    private GridOptionsProvider mGridOptionsProvider;
    private RingtoneOptionsProvider mRingtoneOptionsProvider;
    private ShapeOptionsProvider mShapeOptionsProvider;
    private WallpaperOptionsProvider mWallpaperOptionsProvider;
    private AtomicBoolean mWallpaperUpdated = new AtomicBoolean(false);

    public ThemesRepo(Context context) {
        this.mAppContext = context.getApplicationContext();
        OverlayManagerCompat overlayManagerCompat = new OverlayManagerCompat(this.mAppContext);
        this.mColorOptionsProvider = new ColorOptionsProvider(this.mAppContext, overlayManagerCompat);
        this.mFontOptionsProvider = new FontOptionsProvider(this.mAppContext, overlayManagerCompat);
        this.mShapeOptionsProvider = new ShapeOptionsProvider(this.mAppContext, overlayManagerCompat);
        this.mGridOptionsProvider = new GridOptionsProvider(this.mAppContext);
        this.mWallpaperOptionsProvider = new WallpaperOptionsProvider(this.mAppContext);
        this.mRingtoneOptionsProvider = new RingtoneOptionsProvider(this.mAppContext);
    }

    public static Theme putTheme(Map<String, Theme> map, String str, Theme theme) {
        Theme theme2 = map.get(str);
        if (theme2 == null || !theme2.isDefaultOrPreload()) {
            if (LogConfig.DBG) {
                Log.d("Styles", "Put theme: " + theme);
            }
            return map.put(str, theme);
        }
        if (LogConfig.DBG) {
            Log.d("Styles", "Retain theme: " + theme2);
        }
        return theme2;
    }

    public List<Theme> getThemes() {
        return new ArrayList(getThemeMap().values());
    }

    public String getAppliedTheme(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), APPLIED_THEME);
        return string == null ? "Default" : string;
    }

    public void saveAppliedTheme(String str) {
        Settings.Secure.putString(this.mAppContext.getContentResolver(), APPLIED_THEME, str);
        backupDataChanged();
    }

    private static List<Theme> getCustomThemeList(Map<String, Theme> map) {
        return getCustomThemeList((List<Theme>) new ArrayList(map.values()));
    }

    public static List<Theme> getCustomThemeList(List<Theme> list) {
        int i = 0;
        while (i < list.size() && list.get(i).isDefaultOrPreload()) {
            i++;
        }
        return list.subList(i, list.size());
    }

    private synchronized Map<String, Theme> getThemeMap() {
        LinkedHashMap linkedHashMap;
        linkedHashMap = new LinkedHashMap();
        ArrayList<Theme> arrayList = new ArrayList<>();
        arrayList.addAll(loadDefaultAndPreload());
        arrayList.addAll(loadThemeList(getThemeListData()));
        for (Theme theme : arrayList) {
            updateOptions(theme);
            putTheme(linkedHashMap, theme.getName(), theme);
        }
        return linkedHashMap;
    }

    private List<Theme> loadDefaultAndPreload() {
        Context context = this.mAppContext;
        ArrayList arrayList = new ArrayList();
        Resources resources = context.getResources();
        String[] stringArray = resources.getStringArray(StylesUtilities.PRC_BUILD ? C1087R.array.preload_themes_prc : C1087R.array.preload_themes);
        String[] stringArray2 = resources.getStringArray(StylesUtilities.PRC_BUILD ? C1087R.array.preload_themes_config_prc : C1087R.array.preload_themes_config);
        int i = 0;
        while (i < stringArray.length && i < stringArray2.length) {
            Theme theme = new Theme();
            theme.setPreloadIndex(i);
            theme.setName(stringArray[i]);
            String[] split = PATTERN_SEPARATOR.split(stringArray2[i].replaceAll("\\s+", ""));
            for (int i2 = 0; i2 < split.length; i2++) {
                if (i2 == 0) {
                    theme.setWallpaper(split[i2]);
                } else if (i2 == 1) {
                    theme.setFont(split[i2]);
                } else if (i2 == 2) {
                    theme.setColor(split[i2]);
                } else if (i2 == 3) {
                    theme.setShape(split[i2]);
                } else if (i2 == 4) {
                    theme.setGrid(split[i2]);
                } else if (i2 == 5) {
                    theme.setRingtone(split[i2]);
                }
            }
            if (TextUtils.isEmpty(theme.getGrid())) {
                theme.setGrid(GridOptionsProvider.getDefaultGridValue(context));
            }
            if (TextUtils.isEmpty(theme.getWallpaper())) {
                theme.setWallpaper(WallpaperOptionsProvider.getDefaultWallpaperValue(context));
            }
            if (TextUtils.isEmpty(theme.getRingtone())) {
                theme.setRingtone(RingtoneOptionsProvider.getDefaultRingtoneValue(context));
            }
            if (LogConfig.DBG) {
                Log.d("Styles", "Preload theme: " + theme);
            }
            arrayList.add(theme);
            i++;
        }
        return arrayList;
    }

    public ArrayList<Theme> loadThemeList(String str) {
        Context context = this.mAppContext;
        ArrayList<Theme> arrayList = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    arrayList.add(new Theme(jSONObject.getString("name"), jSONObject.getString("font"), jSONObject.getString("color"), jSONObject.getString("shape"), getGridValue(context, jSONObject), getWallpaperValue(context, jSONObject), getRingtoneValue(context, jSONObject)));
                }
            } catch (JSONException e) {
                Log.e("Styles", "jsonToStyleList error", e);
            }
        }
        return arrayList;
    }

    private String getWallpaperValue(Context context, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("wallpaper")) {
            return jSONObject.getString("wallpaper");
        }
        return WallpaperOptionsProvider.getDefaultWallpaperValue(context);
    }

    private String getRingtoneValue(Context context, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("ringtone")) {
            return jSONObject.getString("ringtone");
        }
        return RingtoneOptionsProvider.getDefaultRingtoneValue(context);
    }

    public static String getSerializedPackagesWithTimestamp(Theme theme) {
        if (theme.isDefault()) {
            return new JSONObject().toString();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (!"Default".equals(theme.getFont())) {
                if (theme.getFontOption().isOnlineFont()) {
                    jSONObject.put(ResourceConstants.OVERLAY_CATEGORY_FONT, ((OnlineFontOption) theme.getFontOption()).getOuterPackageName());
                } else {
                    jSONObject.put(ResourceConstants.OVERLAY_CATEGORY_FONT, theme.getFont());
                }
            }
            if (!"Default".equals(theme.getShape())) {
                jSONObject.put(ResourceConstants.OVERLAY_CATEGORY_SHAPE, theme.getShape());
            }
            ColorOption colorOption = theme.getColorOption();
            if (colorOption.isWallpaperColor()) {
                String hexColor6 = ColorOption.getHexColor6(colorOption.getMainColor().intValue());
                jSONObject.put("android.theme.customization.system_palette", hexColor6);
                jSONObject.put("android.theme.customization.accent_color", hexColor6);
                jSONObject.put("android.theme.customization.color_source", "preset");
            } else if (colorOption.isPickedColor()) {
                String hexColor62 = ColorOption.getHexColor6(colorOption.getMainColor().intValue());
                jSONObject.put("android.theme.customization.system_palette", hexColor62);
                jSONObject.put("android.theme.customization.accent_color", hexColor62);
                jSONObject.put("android.theme.customization.color_source", "preset");
            } else {
                String hexColor63 = ColorOption.getHexColor6(colorOption.getMainColor().intValue());
                jSONObject.put("android.theme.customization.system_palette", hexColor63);
                jSONObject.put("android.theme.customization.accent_color", hexColor63);
                jSONObject.put("android.theme.customization.color_source", "preset");
            }
            if (jSONObject.names() != null) {
                jSONObject.put("_applied_timestamp", System.currentTimeMillis());
            }
        } catch (JSONException e) {
            Log.e("Styles", "getSerializedPackagesWithTimestamp error", e);
        }
        if (LogConfig.DBG) {
            Log.d("Styles", "getSerializedPackagesWithTimestamp - Theme settings: " + jSONObject);
        }
        return jSONObject.toString();
    }

    private String getThemeListData() {
        String string = Settings.Secure.getString(this.mAppContext.getContentResolver(), THEME_LIST);
        if (LogConfig.DBG) {
            Log.d("Styles", "getThemeListData: " + string);
        }
        return string;
    }

    public synchronized void updateOptions(Theme theme) {
        theme.setColorOption(getColorOption(theme.getColor()));
        theme.setFontOption(getFontOption(theme.getFont()));
        theme.setShapeOption(getShapeOption(theme.getShape()));
        theme.setGridOption(getGridOption(theme.getGrid()));
        theme.setWallpaperOption(getWallpaperOption(theme.getWallpaper()));
        theme.setRingtoneOption(getRingtoneOption(theme.getRingtone()));
    }

    private RingtoneOption getRingtoneOption(String str) {
        List<RingtoneOption> loadRingtoneOptions = loadRingtoneOptions();
        for (RingtoneOption next : loadRingtoneOptions) {
            if (next.getValue().contains(str)) {
                return next;
            }
        }
        return loadRingtoneOptions.get(0);
    }

    private WallpaperOption getWallpaperOption(String str) {
        List<WallpaperOption> loadWallpaperOptions = loadWallpaperOptions();
        for (WallpaperOption next : loadWallpaperOptions) {
            if (next.getValue().equals(str)) {
                return next;
            }
        }
        return loadWallpaperOptions.get(0);
    }

    public ColorOption getColorOption(String str) {
        List<ColorOption> loadColorOptions = loadColorOptions((Bitmap) null);
        for (ColorOption next : loadColorOptions) {
            if (next.getValue().equals(str)) {
                return next;
            }
        }
        try {
            return ColorOption.getColorOption(str);
        } catch (Exception e) {
            Log.w("Styles", "getColorOption - new ColorOption error: " + str, e);
            return loadColorOptions.get(0);
        }
    }

    public List<ColorOption> loadColorOptions(Bitmap bitmap) {
        boolean z;
        Bundle bundle = new Bundle();
        if (bitmap != null) {
            bundle.putParcelable("wallpaper", bitmap);
            z = true;
        } else {
            z = false;
        }
        this.mColorOptionsProvider.loadOptions(z, bundle);
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
        this.mFontOptionsProvider.loadOptions(this.mFontUpdated.getAndSet(false), StyleOptionsProvider.newParams(FontOptionsProvider.PARAM_NO_STORE, false));
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

    public List<WallpaperOption> loadWallpaperOptions() {
        this.mWallpaperOptionsProvider.loadOptions(this.mWallpaperUpdated.getAndSet(false));
        return this.mWallpaperOptionsProvider.getOptions();
    }

    public List<RingtoneOption> loadRingtoneOptions() {
        this.mRingtoneOptionsProvider.loadOptions(false);
        return this.mRingtoneOptionsProvider.getOptions();
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

    private static String getGridValue(Context context, JSONObject jSONObject) {
        try {
            return jSONObject.getString("grid");
        } catch (JSONException unused) {
            return GridOptionsProvider.getDefaultGridValue(context);
        }
    }

    public void updateTheme(List<Theme> list, Theme theme, Theme theme2) {
        Map<String, Theme> themeMap = getThemeMap(list);
        for (Theme next : list) {
            if (next.getName().equals(theme2.getName())) {
                putTheme(themeMap, next.getName(), theme);
            } else {
                putTheme(themeMap, next.getName(), next);
            }
        }
        saveThemeList(getCustomThemeList(themeMap));
    }

    public static Map<String, Theme> getThemeMap(List<Theme> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Theme next : list) {
            putTheme(linkedHashMap, next.getName(), next);
        }
        return linkedHashMap;
    }

    public void backupDataChanged() {
        if (this.mBackupManager == null) {
            this.mBackupManager = new BackupManager(this.mAppContext);
        }
        this.mBackupManager.dataChanged();
    }

    public void saveThemeList(List<Theme> list) {
        String themeListToJson = themeListToJson(list);
        if (LogConfig.DBG) {
            Log.d("Styles", "saveThemeList: " + themeListToJson);
        }
        Settings.Secure.putString(this.mAppContext.getContentResolver(), THEME_LIST, themeListToJson);
        backupDataChanged();
    }

    private static String themeListToJson(List<Theme> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Theme next : list) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", next.getName());
                jSONObject.put("font", next.getFont());
                jSONObject.put("color", next.getColor());
                jSONObject.put("shape", next.getShape());
                jSONObject.put("grid", next.getGrid());
                jSONObject.put("wallpaper", next.getWallpaper());
                jSONObject.put("ringtone", next.getRingtone());
            } catch (JSONException e) {
                Log.e("Styles", "themeListToJson error", e);
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray.toString();
    }

    public void deleteTheme(List<Theme> list, Theme theme) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Theme next : list) {
            if (!next.getName().equals(theme.getName())) {
                putTheme(linkedHashMap, next.getName(), next);
            }
        }
        saveThemeList(getCustomThemeList((Map<String, Theme>) linkedHashMap));
        if (theme.getName().equals(getAppliedTheme(this.mAppContext))) {
            saveAppliedTheme("");
        }
    }

    public void createTheme(List<Theme> list, Theme theme) {
        Map<String, Theme> themeMap = getThemeMap(list);
        putTheme(themeMap, theme.getName(), theme);
        if (LogConfig.DBG) {
            Log.d("Styles", "createTheme: " + theme);
        }
        saveThemeList(getCustomThemeList(themeMap));
    }

    public void setFontUpdated() {
        this.mFontUpdated.set(true);
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
                jSONObject.put("android.theme.customization.accent_color", option.getValue());
            }
            if (option instanceof ShapeOption) {
                jSONObject.put(ResourceConstants.OVERLAY_CATEGORY_SHAPE, option.getValue());
            }
            if (jSONObject.names() != null) {
                jSONObject.put("_applied_timestamp", System.currentTimeMillis());
            }
        } catch (JSONException e2) {
            Log.e("Styles", "getUpdatedThemeSettingValue error", e2);
        }
        if (LogConfig.DBG) {
            Log.d("Styles", "getUpdatedThemeSettingValue - Theme settings: " + jSONObject);
        }
        return jSONObject.toString();
    }

    public Theme getSystemAppliedTheme() {
        loadColorOptions((Bitmap) null);
        loadFontOptions();
        loadShapeOptions();
        loadGridOptions();
        Theme theme = new Theme();
        theme.setColor(getAppliedColor().getValue());
        theme.setFont(getAppliedFont().getValue());
        theme.setShape(getAppliedShape().getValue());
        theme.setGrid(getAppliedGrid().getValue());
        return theme;
    }

    public GridOption getAppliedGrid() {
        GridOption appliedOption = getGridOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d("Styles", "getAppliedGrid: " + appliedOption);
        }
        return appliedOption;
    }

    public ShapeOption getAppliedShape() {
        ShapeOption appliedOption = getShapeOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d("Styles", "getAppliedShape: " + appliedOption);
        }
        return appliedOption;
    }

    public FontOption getAppliedFont() {
        FontOption appliedOption = getFontOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d("Styles", "getAppliedFont: " + appliedOption);
        }
        return appliedOption;
    }

    public ColorOption getAppliedColor() {
        ColorOption appliedOption = getColorOptionsProvider().getAppliedOption();
        if (LogConfig.DBG) {
            Log.d("Styles", "getAppliedColor: " + appliedOption);
        }
        return appliedOption;
    }

    public void setWallpaperUpdated() {
        this.mWallpaperUpdated.set(true);
    }
}
