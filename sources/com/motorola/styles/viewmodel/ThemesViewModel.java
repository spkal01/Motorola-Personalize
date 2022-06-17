package com.motorola.styles.viewmodel;

import android.content.Context;
import android.media.RingtoneManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.motorola.styles.C1087R;
import com.motorola.styles.LogConfig;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.Result;
import com.motorola.styles.model.Theme;
import com.motorola.styles.model.ThemeSettings;
import com.motorola.styles.model.ThemesInfo;
import com.motorola.styles.model.ThemesRepo;
import com.motorola.styles.model.providers.GridOptionsProvider;
import com.motorola.styles.model.providers.RingtoneOptionsProvider;
import com.motorola.styles.model.providers.WallpaperOptionsProvider;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThemesViewModel extends ViewModel {
    public static final ThemesInfo DEFAULT_THEME_LIST = new ThemesInfo();
    public static final int MAX_CUSTOM_THEMES_COUNT = 20;
    public static final int TYPE_RINGTONE = 1;
    public static final int TYPE_RINGTONE_2 = 64;
    private static ExecutorService sWorker;
    private final Context mAppContext;
    private final MutableLiveData<Theme> mThemePreviewLiveData;
    private final MutableLiveData<ThemeSettings> mThemeSettingsLiveData;
    private final MutableLiveData<ThemesInfo> mThemesInfoLiveData;
    private final ThemesRepo mThemesRepo;
    private int mUiMode;

    public static ExecutorService getWorker() {
        if (sWorker == null) {
            synchronized (ThemesViewModel.class) {
                if (sWorker == null) {
                    sWorker = Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors() / 2, 2));
                }
            }
        }
        return sWorker;
    }

    public static void shutdownWorker() {
        if (sWorker != null) {
            synchronized (ThemesViewModel.class) {
                ExecutorService executorService = sWorker;
                if (executorService != null) {
                    executorService.shutdownNow();
                    sWorker = null;
                }
            }
        }
    }

    public static void runOnWorkerThread(Runnable runnable) {
        getWorker().execute(runnable);
    }

    public ThemesViewModel(Context context) {
        this.mThemesInfoLiveData = new MutableLiveData<>();
        this.mThemePreviewLiveData = new MutableLiveData<>();
        this.mThemeSettingsLiveData = new MutableLiveData<>();
        if (LogConfig.DBG) {
            Log.d("Styles", "New ThemesViewModel: " + this);
        }
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mThemesRepo = new ThemesRepo(applicationContext);
        this.mUiMode = getUiMode(context);
        onLoadThemesInfo();
    }

    public ThemesViewModel(Context context, ThemeSettings themeSettings) {
        this.mThemesInfoLiveData = new MutableLiveData<>();
        this.mThemePreviewLiveData = new MutableLiveData<>();
        MutableLiveData<ThemeSettings> mutableLiveData = new MutableLiveData<>();
        this.mThemeSettingsLiveData = mutableLiveData;
        if (LogConfig.DBG) {
            Log.d("Styles", "New ThemesViewModel: " + this + " | " + themeSettings);
        }
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mThemesRepo = new ThemesRepo(applicationContext);
        mutableLiveData.setValue(themeSettings);
        this.mUiMode = getUiMode(context);
        onLoadThemesInfo();
        onLoadThemeOptions(themeSettings);
        onLoadThemePreview(themeSettings.getTheme());
    }

    public void setOnlineFontUpdated() {
        this.mThemesRepo.setFontUpdated();
        onLoadThemesInfo();
        onLoadThemeOptions();
    }

    private void onLoadThemeOptions() {
        ThemeSettings value = this.mThemeSettingsLiveData.getValue();
        if (value != null) {
            onLoadThemeOptions(value);
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        if (LogConfig.DBG) {
            Log.d("Styles", "ThemesViewModel - onCleared: " + this);
        }
    }

    public MutableLiveData<ThemesInfo> getThemesInfoLiveData() {
        return this.mThemesInfoLiveData;
    }

    public MutableLiveData<Theme> getThemePreviewLiveData() {
        return this.mThemePreviewLiveData;
    }

    public MutableLiveData<ThemeSettings> getThemeSettingsLiveData() {
        return this.mThemeSettingsLiveData;
    }

    public void onLoadThemesInfo() {
        runOnWorkerThread(new Runnable() {
            public final void run() {
                ThemesViewModel.this.lambda$onLoadThemesInfo$0$ThemesViewModel();
            }
        });
    }

    public /* synthetic */ void lambda$onLoadThemesInfo$0$ThemesViewModel() {
        ThemesInfo themesInfo = new ThemesInfo();
        themesInfo.setThemes(this.mThemesRepo.getThemes());
        themesInfo.setAppliedTheme(this.mThemesRepo.getAppliedTheme(this.mAppContext));
        if (LogConfig.DBG) {
            Log.d("Styles", "Load themes info: " + themesInfo);
        }
        this.mThemesInfoLiveData.postValue(themesInfo);
    }

    public void onLoadThemeList() {
        runOnWorkerThread(new Runnable() {
            public final void run() {
                ThemesViewModel.this.lambda$onLoadThemeList$1$ThemesViewModel();
            }
        });
    }

    public /* synthetic */ void lambda$onLoadThemeList$1$ThemesViewModel() {
        ThemesInfo value = this.mThemesInfoLiveData.getValue();
        if (value == null) {
            onLoadThemesInfo();
            return;
        }
        value.setThemes(loadThemeList());
        this.mThemesInfoLiveData.postValue(value);
    }

    private List<Theme> loadThemeList() {
        List<Theme> themes = this.mThemesRepo.getThemes();
        if (LogConfig.DBG) {
            Log.d("Styles", "Load theme list: " + themes);
        }
        return themes;
    }

    public Theme newTheme() {
        Theme theme = new Theme(getThemeIndexName(), "Default", "Default", "Default", GridOptionsProvider.getDefaultGridValue(this.mAppContext), WallpaperOptionsProvider.getDefaultWallpaperValue(this.mAppContext), RingtoneOptionsProvider.getDefaultRingtoneValue(this.mAppContext));
        theme.setNew(true);
        return theme;
    }

    public String getThemeIndexName() {
        return getThemeIndexName(ThemesRepo.getCustomThemeList(getThemeList()).size());
    }

    public String getThemeIndexName(int i) {
        String string;
        Map<String, Theme> themeMap = ThemesRepo.getThemeMap(getThemeList());
        do {
            i++;
            string = this.mAppContext.getString(C1087R.string.theme_name, new Object[]{NumberFormat.getInstance().format((long) i)});
        } while (themeMap.containsKey(string));
        return string;
    }

    public List<Theme> getThemeList() {
        ThemesInfo value = this.mThemesInfoLiveData.getValue();
        if (value == null) {
            value = DEFAULT_THEME_LIST;
        }
        return value.getThemes();
    }

    public void onLoadThemePreview(Theme theme) {
        runOnWorkerThread(new Runnable(theme) {
            public final /* synthetic */ Theme f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onLoadThemePreview$2$ThemesViewModel(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onLoadThemePreview$2$ThemesViewModel(Theme theme) {
        this.mThemesRepo.updateOptions(theme);
        this.mThemePreviewLiveData.postValue(theme);
    }

    public LiveData<Result> onApplyTheme(Theme theme) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        runOnWorkerThread(new Runnable(theme, mutableLiveData) {
            public final /* synthetic */ Theme f$1;
            public final /* synthetic */ MutableLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onApplyTheme$3$ThemesViewModel(this.f$1, this.f$2);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onApplyTheme$3$ThemesViewModel(Theme theme, MutableLiveData mutableLiveData) {
        try {
            mutableLiveData.postValue(applyTheme(theme));
        } catch (Exception e) {
            Log.e("Styles", "Apply theme " + theme.getName() + " exception", e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.net.Uri} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0077, code lost:
        if (r6 != null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0063, code lost:
        if (r6 != null) goto L_0x0065;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d5 A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d8 A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ea A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0143 A[SYNTHETIC, Splitter:B:64:0x0143] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.motorola.styles.model.Result applyTheme(com.motorola.styles.model.Theme r11) throws java.lang.InterruptedException {
        /*
            r10 = this;
            com.motorola.styles.model.Result r0 = new com.motorola.styles.model.Result
            r0.<init>()
            boolean r1 = com.motorola.styles.LogConfig.DBG
            if (r1 == 0) goto L_0x0021
            java.lang.String r1 = "Styles"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Apply theme: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r11)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
        L_0x0021:
            java.lang.Object r1 = new java.lang.Object
            r1.<init>()
            r2 = 0
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            android.content.Context r5 = r10.mAppContext     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            android.app.WallpaperManager r5 = android.app.WallpaperManager.getInstance(r5)     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            com.motorola.styles.model.WallpaperOption r6 = r11.getWallpaperOption()     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            android.content.Context r7 = r10.mAppContext     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            java.io.InputStream r6 = r6.getWallpaperStream(r7)     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            com.motorola.styles.viewmodel.ThemesViewModel$1 r7 = new com.motorola.styles.viewmodel.ThemesViewModel$1     // Catch:{ Exception -> 0x0069 }
            r7.<init>(r5, r1)     // Catch:{ Exception -> 0x0069 }
            r5.addOnColorsChangedListener(r7, r2)     // Catch:{ Exception -> 0x0069 }
            r5.setStream(r6)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r5 = "Styles"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0069 }
            r7.<init>()     // Catch:{ Exception -> 0x0069 }
            java.lang.String r8 = "Apply wallpaper consumed: "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Exception -> 0x0069 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0069 }
            long r8 = r8 - r3
            java.lang.StringBuilder r3 = r7.append(r8)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0069 }
            android.util.Log.d(r5, r3)     // Catch:{ Exception -> 0x0069 }
            if (r6 == 0) goto L_0x007a
        L_0x0065:
            r6.close()     // Catch:{ IOException -> 0x007a }
            goto L_0x007a
        L_0x0069:
            r3 = move-exception
            goto L_0x0070
        L_0x006b:
            r10 = move-exception
            goto L_0x0141
        L_0x006e:
            r3 = move-exception
            r6 = r2
        L_0x0070:
            java.lang.String r4 = "Styles"
            java.lang.String r5 = "Applying wallpaper error"
            android.util.Log.w(r4, r5, r3)     // Catch:{ all -> 0x013f }
            if (r6 == 0) goto L_0x007a
            goto L_0x0065
        L_0x007a:
            boolean r3 = com.motorola.styles.LogConfig.DBG
            if (r3 == 0) goto L_0x0085
            java.lang.String r3 = "Styles"
            java.lang.String r4 = "applyTheme - Wait for wallpaper colors changed"
            android.util.Log.d(r3, r4)
        L_0x0085:
            monitor-enter(r1)
            r3 = 5000(0x1388, double:2.4703E-320)
            r1.wait(r3)     // Catch:{ all -> 0x013c }
            monitor-exit(r1)     // Catch:{ all -> 0x013c }
            boolean r1 = com.motorola.styles.LogConfig.DBG
            if (r1 == 0) goto L_0x0097
            java.lang.String r1 = "Styles"
            java.lang.String r3 = "applyTheme - Continue applying theme"
            android.util.Log.d(r1, r3)
        L_0x0097:
            com.motorola.styles.model.ThemesRepo r1 = r10.mThemesRepo
            com.motorola.styles.model.providers.GridOptionsProvider r1 = r1.getGridOptionsProvider()
            java.lang.String r3 = r11.getGrid()     // Catch:{ Exception -> 0x00a5 }
            r1.applyGrid(r3)     // Catch:{ Exception -> 0x00a5 }
            goto L_0x00c8
        L_0x00a5:
            r1 = move-exception
            java.lang.String r3 = "Styles"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "applyGrid of "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = r11.getName()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " exception"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4, r1)
        L_0x00c8:
            r1 = 1
            android.content.Context r3 = r10.mAppContext     // Catch:{ Exception -> 0x00fe }
            com.motorola.styles.model.RingtoneOption r4 = r11.getRingtoneOption()     // Catch:{ Exception -> 0x00fe }
            boolean r4 = r4.isNoRingtone()     // Catch:{ Exception -> 0x00fe }
            if (r4 == 0) goto L_0x00d8
            r4 = r2
        L_0x00d6:
            r5 = r4
            goto L_0x00e1
        L_0x00d8:
            java.lang.String r4 = r11.getRingtone()     // Catch:{ Exception -> 0x00fe }
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x00fe }
            goto L_0x00d6
        L_0x00e1:
            android.media.RingtoneManager.setActualDefaultRingtoneUri(r3, r1, r4)     // Catch:{ Exception -> 0x00fe }
            boolean r3 = isDualSimRingtoneSupported()     // Catch:{ Exception -> 0x00fe }
            if (r3 == 0) goto L_0x0106
            android.content.Context r3 = r10.mAppContext     // Catch:{ Exception -> 0x00fe }
            r4 = 64
            com.motorola.styles.model.RingtoneOption r6 = r11.getRingtoneOption()     // Catch:{ Exception -> 0x00fe }
            boolean r6 = r6.isNoRingtone()     // Catch:{ Exception -> 0x00fe }
            if (r6 == 0) goto L_0x00f9
            goto L_0x00fa
        L_0x00f9:
            r2 = r5
        L_0x00fa:
            android.media.RingtoneManager.setActualDefaultRingtoneUri(r3, r4, r2)     // Catch:{ Exception -> 0x00fe }
            goto L_0x0106
        L_0x00fe:
            r2 = move-exception
            java.lang.String r3 = "Styles"
            java.lang.String r4 = "Applying ringtone error"
            android.util.Log.w(r3, r4, r2)
        L_0x0106:
            com.motorola.styles.model.ThemesRepo r2 = r10.mThemesRepo
            com.motorola.styles.model.providers.FontOptionsProvider r2 = r2.getFontOptionsProvider()
            com.motorola.styles.model.FontOption r3 = r11.getFontOption()
            r2.applyFont(r3)
            android.content.Context r2 = r10.mAppContext
            android.content.ContentResolver r2 = r2.getContentResolver()
            java.lang.String r3 = "theme_customization_overlay_packages"
            java.lang.String r4 = com.motorola.styles.model.ThemesRepo.getSerializedPackagesWithTimestamp(r11)
            boolean r2 = android.provider.Settings.Secure.putString(r2, r3, r4)
            if (r2 == 0) goto L_0x0132
            r10.logThemeApplied((com.motorola.styles.model.Theme) r11, (boolean) r1)
            com.motorola.styles.model.ThemesRepo r10 = r10.mThemesRepo
            java.lang.String r11 = r11.getName()
            r10.saveAppliedTheme(r11)
            goto L_0x013b
        L_0x0132:
            r1 = 0
            r10.logThemeApplied((com.motorola.styles.model.Theme) r11, (boolean) r1)
            java.lang.String r10 = "Failed to apply theme"
            r0.putError(r10)
        L_0x013b:
            return r0
        L_0x013c:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x013c }
            throw r10
        L_0x013f:
            r10 = move-exception
            r2 = r6
        L_0x0141:
            if (r2 == 0) goto L_0x0146
            r2.close()     // Catch:{ IOException -> 0x0146 }
        L_0x0146:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.styles.viewmodel.ThemesViewModel.applyTheme(com.motorola.styles.model.Theme):com.motorola.styles.model.Result");
    }

    private void logThemeApplied(Theme theme, boolean z) {
        if (z) {
            Log.d("Styles", "Success to apply theme: " + theme);
        } else {
            Log.d("Styles", "Failed to apply theme: " + theme);
        }
    }

    public void onLoadThemeOptions(ThemeSettings themeSettings) {
        runOnWorkerThread(new Runnable(themeSettings) {
            public final /* synthetic */ ThemeSettings f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onLoadThemeOptions$4$ThemesViewModel(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onLoadThemeOptions$4$ThemesViewModel(ThemeSettings themeSettings) {
        loadThemeOptions(themeSettings);
        this.mThemeSettingsLiveData.postValue(themeSettings);
    }

    private synchronized void loadThemeOptions(ThemeSettings themeSettings) {
        String setting = themeSettings.getSetting();
        if (ThemeSettings.isFont(setting)) {
            themeSettings.setOptions(this.mThemesRepo.loadFontOptions());
        } else if (ThemeSettings.isColor(setting)) {
            themeSettings.setOptions(this.mThemesRepo.loadColorOptions(themeSettings.getTheme().getWallpaperOption().getWallpaper(this.mAppContext)));
        } else if (ThemeSettings.isShape(setting)) {
            themeSettings.setOptions(this.mThemesRepo.loadShapeOptions());
        } else if (ThemeSettings.isGrid(setting)) {
            themeSettings.setOptions(this.mThemesRepo.loadGridOptions());
        } else if (ThemeSettings.isWallpaper(setting)) {
            themeSettings.setOptions(this.mThemesRepo.loadWallpaperOptions());
        } else if (ThemeSettings.isRingtone(setting)) {
            themeSettings.setOptions(this.mThemesRepo.loadRingtoneOptions());
        }
    }

    public LiveData<Result> onCreateTheme(Theme theme) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        runOnWorkerThread(new Runnable(theme, mutableLiveData) {
            public final /* synthetic */ Theme f$1;
            public final /* synthetic */ MutableLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onCreateTheme$5$ThemesViewModel(this.f$1, this.f$2);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onCreateTheme$5$ThemesViewModel(Theme theme, MutableLiveData mutableLiveData) {
        Context context = this.mAppContext;
        Result result = new Result();
        String name = theme.getName();
        if (StylesUtilities.isXMLocale(context)) {
            name = StylesUtilities.removeAllBidiClass(name);
        }
        if (TextUtils.isEmpty(name.trim())) {
            result.putError(context.getString(C1087R.string.error_theme_name_blank));
        } else if (hasTheme(name)) {
            result.putError(context.getString(C1087R.string.error_theme_name_exists, new Object[]{name}));
        } else if (name.length() > 50) {
            result.putError(context.getString(C1087R.string.error_theme_name_too_long));
        } else {
            createTheme(theme);
        }
        mutableLiveData.postValue(result);
    }

    private boolean hasTheme(String str) {
        return ThemesRepo.getThemeMap(getThemeList()).containsKey(str);
    }

    private void createTheme(Theme theme) {
        this.mThemesRepo.updateOptions(theme);
        this.mThemesRepo.createTheme(getThemeList(), theme);
    }

    public LiveData<Result> onUpdateTheme(Theme theme, Theme theme2) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        runOnWorkerThread(new Runnable(theme, theme2, mutableLiveData) {
            public final /* synthetic */ Theme f$1;
            public final /* synthetic */ Theme f$2;
            public final /* synthetic */ MutableLiveData f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onUpdateTheme$6$ThemesViewModel(this.f$1, this.f$2, this.f$3);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onUpdateTheme$6$ThemesViewModel(Theme theme, Theme theme2, MutableLiveData mutableLiveData) {
        Context context = this.mAppContext;
        Result result = new Result();
        String name = theme.getName();
        String name2 = theme2.getName();
        if (TextUtils.isEmpty(name.trim())) {
            result.putError(context.getString(C1087R.string.error_theme_name_blank));
        } else if (name.equals(name2) || !hasTheme(name)) {
            updateTheme(theme, theme2);
        } else {
            result.putError(context.getString(C1087R.string.error_theme_name_exists, new Object[]{name}));
        }
        mutableLiveData.postValue(result);
    }

    private void updateTheme(Theme theme, Theme theme2) {
        this.mThemesRepo.updateOptions(theme);
        this.mThemesRepo.updateTheme(getThemeList(), theme, theme2);
        if (isThemeChanged(theme, theme2)) {
            this.mThemesRepo.saveAppliedTheme("");
        } else if (this.mThemesRepo.getAppliedTheme(this.mAppContext).equals(theme2.getName()) && isThemeNameChanged(theme, theme2)) {
            this.mThemesRepo.saveAppliedTheme(theme.getName());
        }
    }

    private boolean isThemeNameChanged(Theme theme, Theme theme2) {
        return !theme.getName().equals(theme2.getName());
    }

    private boolean isThemeChanged(Theme theme, Theme theme2) {
        return isValueChanged(theme.getFont(), theme2.getFont()) || isValueChanged(theme.getColor(), theme2.getColor()) || isValueChanged(theme.getShape(), theme2.getShape()) || isValueChanged(theme.getGrid(), theme2.getGrid()) || isValueChanged(theme.getWallpaper(), theme2.getWallpaper()) || isValueChanged(theme.getRingtone(), theme2.getRingtone());
    }

    private boolean isValueChanged(String str, String str2) {
        return !Objects.equals(str, str2);
    }

    public LiveData<Result> onDeleteTheme(Theme theme) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        runOnWorkerThread(new Runnable(theme, mutableLiveData) {
            public final /* synthetic */ Theme f$1;
            public final /* synthetic */ MutableLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onDeleteTheme$7$ThemesViewModel(this.f$1, this.f$2);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onDeleteTheme$7$ThemesViewModel(Theme theme, MutableLiveData mutableLiveData) {
        Result result = new Result();
        deleteTheme(theme);
        mutableLiveData.postValue(result);
    }

    private void deleteTheme(Theme theme) {
        this.mThemesRepo.deleteTheme(getThemeList(), theme);
    }

    public LiveData<Result> onApplyThemeOption(Option option) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        runOnWorkerThread(new Runnable(option, mutableLiveData) {
            public final /* synthetic */ Option f$1;
            public final /* synthetic */ MutableLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onApplyThemeOption$8$ThemesViewModel(this.f$1, this.f$2);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onApplyThemeOption$8$ThemesViewModel(Option option, MutableLiveData mutableLiveData) {
        try {
            mutableLiveData.postValue(applyThemeOption(option));
        } catch (Exception e) {
            Log.e("Styles", "Apply theme option " + option + " exception", e);
        }
    }

    private Result applyThemeOption(Option option) {
        Result result = new Result();
        if (LogConfig.DBG) {
            Log.d("Styles", "Apply theme option: " + option);
        }
        if (option instanceof GridOption) {
            try {
                this.mThemesRepo.getGridOptionsProvider().applyGrid(option.getValue());
            } catch (Exception e) {
                Log.e("Styles", "applyGrid of " + option.getValue() + " exception", e);
                result.putError("Failed to apply grid option");
            }
            return result;
        }
        if (option instanceof FontOption) {
            this.mThemesRepo.getFontOptionsProvider().applyFont((FontOption) option);
        }
        if (Settings.Secure.putString(this.mAppContext.getContentResolver(), ResourceConstants.THEME_SETTING, ThemesRepo.getUpdatedThemeSettingValue(Settings.Secure.getString(this.mAppContext.getContentResolver(), ResourceConstants.THEME_SETTING), option))) {
            logThemeApplied(option, true);
        } else {
            logThemeApplied(option, false);
            result.putError("Failed to apply theme option");
        }
        return result;
    }

    private void logThemeApplied(Option option, boolean z) {
        if (z) {
            Log.d("Styles", "Success to apply theme option: " + option);
        } else {
            Log.d("Styles", "Failed to apply theme option: " + option);
        }
    }

    public MutableLiveData<Theme> onLoadSystemAppliedTheme() {
        MutableLiveData<Theme> mutableLiveData = new MutableLiveData<>();
        runOnWorkerThread(new Runnable(mutableLiveData) {
            public final /* synthetic */ MutableLiveData f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ThemesViewModel.this.lambda$onLoadSystemAppliedTheme$9$ThemesViewModel(this.f$1);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onLoadSystemAppliedTheme$9$ThemesViewModel(MutableLiveData mutableLiveData) {
        mutableLiveData.postValue(this.mThemesRepo.getSystemAppliedTheme());
    }

    public boolean isCustomThemesReachedMaxCount() {
        return ThemesRepo.getCustomThemeList(getThemeList()).size() >= 20;
    }

    public static boolean isDualSimRingtoneSupported() {
        return RingtoneManager.getDefaultUri(64) != null;
    }

    public void setWallpaperUpdated() {
        this.mThemesRepo.setWallpaperUpdated();
    }

    public boolean isUiModeChanged(int i) {
        return this.mUiMode != i;
    }

    public static int getUiMode(Context context) {
        return context.getResources().getConfiguration().uiMode & 48;
    }

    public void setUiMode(int i) {
        this.mUiMode = i;
    }
}
