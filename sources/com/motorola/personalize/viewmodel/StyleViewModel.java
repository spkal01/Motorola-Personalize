package com.motorola.personalize.viewmodel;

import android.app.Application;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.personalize.data.AppDatabase;
import com.motorola.personalize.data.AppDatabaseHelper;
import com.motorola.personalize.model.ColorOption;
import com.motorola.personalize.model.OptionEntity;
import com.motorola.personalize.model.Style;
import com.motorola.personalize.model.StyleSettings;
import com.motorola.personalize.model.StylesManager;
import com.motorola.styles.LogConfig;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.Result;
import com.motorola.styles.model.ShapeOption;
import java.util.List;

public class StyleViewModel extends PublicViewModel {
    public static final int MAX_COLOR_STYLES_COUNT = 20;

    /* renamed from: db */
    private AppDatabase f167db;
    private LiveData<List<OptionEntity>> liveDataDbOptions;
    private final MutableLiveData<StyleSettings> mStyleSettingsLiveData = new MutableLiveData<>();
    private final ContentObserver mStyleSettingsObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            StyleViewModel.this.onLoadStyleSetting();
        }
    };
    private final MutableLiveData<StyleSettings> mStyleSettingsPreviewLiveData = new MutableLiveData<>();
    private final StylesManager stylesManager;

    public StyleViewModel(Application application) {
        super(application);
        if (LogConfig.DBG) {
            Log.d("Styles", "New StylesViewModel: " + this);
        }
        AppDatabase instance = AppDatabaseHelper.getInstance();
        this.f167db = instance;
        this.liveDataDbOptions = instance.getDao().getAllOptions();
        this.stylesManager = new StylesManager(this.mAppContext);
    }

    public void setOnlineFontUpdated() {
        this.stylesManager.setFontUpdated();
        onLoadStyleSetting();
    }

    public void setCustomColorUpdated() {
        this.stylesManager.setColorChanged();
        onLoadStyleSetting();
    }

    public void onLoadStyleSetting() {
        StyleSettings value = this.mStyleSettingsLiveData.getValue();
        if (value != null) {
            onLoadStyleSetting(value);
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        if (LogConfig.DBG) {
            Log.d("Styles", "StylesViewModel - onCleared: " + this);
        }
        this.mAppContext.getContentResolver().unregisterContentObserver(this.mStyleSettingsObserver);
        super.onCleared();
    }

    public MutableLiveData<StyleSettings> getStyleSettings() {
        return this.mStyleSettingsLiveData;
    }

    public MutableLiveData<StyleSettings> getStyleSettingsPreview() {
        return this.mStyleSettingsPreviewLiveData;
    }

    public void onLoadStyleSetting(StyleSettings styleSettings) {
        postWorkerThread(new Runnable(styleSettings) {
            public final /* synthetic */ StyleSettings f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StyleViewModel.this.lambda$onLoadStyleSetting$0$StyleViewModel(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onLoadStyleSetting$0$StyleViewModel(StyleSettings styleSettings) {
        loadStyleSetting(styleSettings);
        this.mStyleSettingsLiveData.postValue(styleSettings);
    }

    private void loadStyleSetting(StyleSettings styleSettings) {
        String currentSetting = styleSettings.getCurrentSetting();
        if (StyleSettings.isFont(currentSetting)) {
            styleSettings.setOptions(currentSetting, this.stylesManager.loadFontOptions());
        } else if (StyleSettings.isColor(currentSetting)) {
            List<ColorOption> loadColorOptions = this.stylesManager.loadColorOptions();
            Log.d("Styles", "loadStyleSetting: " + loadColorOptions.size());
            styleSettings.setOptions(currentSetting, loadColorOptions);
        } else if (StyleSettings.isShape(currentSetting)) {
            styleSettings.setOptions(currentSetting, this.stylesManager.loadShapeOptions());
        } else if (StyleSettings.isGrid(currentSetting)) {
            styleSettings.setOptions(currentSetting, this.stylesManager.loadGridOptions());
        }
    }

    public void onLoadStyleSettingsPreview(StyleSettings styleSettings) {
        postWorkerThread(new Runnable(styleSettings) {
            public final /* synthetic */ StyleSettings f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StyleViewModel.this.lambda$onLoadStyleSettingsPreview$1$StyleViewModel(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onLoadStyleSettingsPreview$1$StyleViewModel(StyleSettings styleSettings) {
        this.stylesManager.updateOptions(styleSettings.getStyle());
        this.mStyleSettingsPreviewLiveData.postValue(styleSettings);
    }

    public LiveData<Result> onApplyStyleOption(Option option) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        postWorkerThread(new Runnable(option, mutableLiveData) {
            public final /* synthetic */ Option f$1;
            public final /* synthetic */ MutableLiveData f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                StyleViewModel.this.lambda$onApplyStyleOption$2$StyleViewModel(this.f$1, this.f$2);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onApplyStyleOption$2$StyleViewModel(Option option, MutableLiveData mutableLiveData) {
        try {
            mutableLiveData.postValue(applyStyleOption(option));
        } catch (Exception e) {
            Log.e("Styles", "Apply style option " + option + " exception", e);
        }
    }

    private Result applyStyleOption(Option option) {
        Result result = new Result();
        if (LogConfig.DBG) {
            Log.d("Styles", "Apply style option: " + option);
        }
        if (option instanceof GridOption) {
            try {
                this.stylesManager.getGridOptionsProvider().applyGrid(option.getValue());
                GridOption gridOption = (GridOption) option;
                PersonalizeMetrics.setCurrentGrid(this.mAppContext, gridOption.getRow(), gridOption.getCol());
            } catch (Exception e) {
                Log.e("Styles", "applyGrid of " + option.getValue() + " exception", e);
                result.putError("Failed to apply grid option");
            }
            return result;
        }
        if (option instanceof FontOption) {
            this.stylesManager.getFontOptionsProvider().applyFont((FontOption) option);
            if (option.getValue().equals("Default")) {
                PersonalizeMetrics.setCurrentFont(this.mAppContext, "Default");
            } else {
                PersonalizeMetrics.setCurrentFont(this.mAppContext, option.getName());
            }
        }
        if (option instanceof ColorOption) {
            int type = ((ColorOption) option).getType();
            if (type == 0) {
                PersonalizeMetrics.setCurrentColor(this.mAppContext, 1);
            } else if (type == 2) {
                PersonalizeMetrics.setCurrentColor(this.mAppContext, 2);
            } else {
                PersonalizeMetrics.setCurrentColor(this.mAppContext, 3);
            }
        }
        if (option instanceof ShapeOption) {
            PersonalizeMetrics.setCurrentShape(this.mAppContext, option.getValue());
        }
        if (Settings.Secure.putString(this.mAppContext.getContentResolver(), ResourceConstants.THEME_SETTING, StylesManager.getUpdatedThemeSettingValue(Settings.Secure.getString(this.mAppContext.getContentResolver(), ResourceConstants.THEME_SETTING), option))) {
            logStyleOptionApplied(option, true);
        } else {
            logStyleOptionApplied(option, false);
            result.putError("Failed to apply style option");
        }
        return result;
    }

    private void logStyleOptionApplied(Option option, boolean z) {
        if (z) {
            Log.d("Styles", "Success to apply style option: " + option);
        } else {
            Log.d("Styles", "Failed to apply style option: " + option);
        }
    }

    public StylesManager getStylesManager() {
        return this.stylesManager;
    }

    public MutableLiveData<Style> onLoadSystemAppliedStyle() {
        MutableLiveData<Style> mutableLiveData = new MutableLiveData<>();
        postWorkerThread(new Runnable(mutableLiveData) {
            public final /* synthetic */ MutableLiveData f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StyleViewModel.this.lambda$onLoadSystemAppliedStyle$3$StyleViewModel(this.f$1);
            }
        });
        return mutableLiveData;
    }

    public /* synthetic */ void lambda$onLoadSystemAppliedStyle$3$StyleViewModel(MutableLiveData mutableLiveData) {
        mutableLiveData.postValue(this.stylesManager.getSystemAppliedStyle());
    }
}
