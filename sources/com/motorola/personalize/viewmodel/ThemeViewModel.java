package com.motorola.personalize.viewmodel;

import android.app.Application;
import android.app.UiModeManager;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.personalize.model.SystemThemeOption;
import com.motorola.personalize.model.SystemThemeOptionManager;
import com.motorola.personalize.util.NoneSdkApi;
import java.util.ArrayList;
import java.util.List;

public class ThemeViewModel extends PublicViewModel {
    public static final int MAX_CUSTOM_STYLES_COUNT = 20;
    public static final String TAG = "ThemeViewModel";
    private ContentObserver mContentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            ThemeViewModel themeViewModel = ThemeViewModel.this;
            int unused = themeViewModel.mNightUiMode = NoneSdkApi.getUiNightMode(themeViewModel.mAppContext.getContentResolver());
            Log.d(ThemeViewModel.TAG, "onChange: mNightUiMode = " + ThemeViewModel.this.mNightUiMode);
            ThemeViewModel.this.updateTheme();
        }
    };
    private UiModeManager mModeManager;
    /* access modifiers changed from: private */
    public int mNightUiMode;
    private final MutableLiveData<List<SystemThemeOption>> mOptionListLiveData = new MutableLiveData<>();
    private List<SystemThemeOption> mOptionsList = new ArrayList();
    private final MutableLiveData<SystemThemeOption> mPreviewLiveData = new MutableLiveData<>();
    public MutableLiveData<SystemThemeOption> themeLiveData = new MutableLiveData<>();
    private final SystemThemeOptionManager themeManager;

    public List<SystemThemeOption> getOptions() {
        return this.mOptionsList;
    }

    public ThemeViewModel(Application application) {
        super(application);
        Log.d(TAG, "New ThemeViewModel: " + this);
        this.mModeManager = (UiModeManager) this.mAppContext.getSystemService(UiModeManager.class);
        this.themeManager = new SystemThemeOptionManager(this.mAppContext);
        this.mNightUiMode = this.mModeManager.getNightMode();
        this.mAppContext.getContentResolver().registerContentObserver(NoneSdkApi.getUiNightModeUri(), false, this.mContentObserver);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        Log.d(TAG, "ThemeViewModel - onCleared: " + this);
        this.mAppContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
        super.onCleared();
    }

    public MutableLiveData<List<SystemThemeOption>> getSystemTheme() {
        return this.mOptionListLiveData;
    }

    public MutableLiveData<SystemThemeOption> getPreview() {
        return this.mPreviewLiveData;
    }

    public void onLoadPreview(SystemThemeOption systemThemeOption) {
        postWorkerThread(new Runnable(systemThemeOption) {
            public final /* synthetic */ SystemThemeOption f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ThemeViewModel.this.lambda$onLoadPreview$0$ThemeViewModel(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onLoadPreview$0$ThemeViewModel(SystemThemeOption systemThemeOption) {
        this.mPreviewLiveData.postValue(systemThemeOption);
    }

    /* access modifiers changed from: private */
    public void updateTheme() {
        Log.d(TAG, "updateTheme: ");
        postWorkerThread(new Runnable() {
            public final void run() {
                ThemeViewModel.this.lambda$updateTheme$1$ThemeViewModel();
            }
        });
    }

    public /* synthetic */ void lambda$updateTheme$1$ThemeViewModel() {
        this.themeLiveData.postValue(this.themeManager.getSystemAppliedTheme(this.mNightUiMode));
    }

    public void onLoadSystemAppliedTheme() {
        postWorkerThread(new Runnable() {
            public final void run() {
                ThemeViewModel.this.lambda$onLoadSystemAppliedTheme$2$ThemeViewModel();
            }
        });
    }

    public /* synthetic */ void lambda$onLoadSystemAppliedTheme$2$ThemeViewModel() {
        this.themeManager.loadOptions(true);
        this.mOptionsList.clear();
        this.mOptionsList.addAll(this.themeManager.getOptions());
        this.themeLiveData.postValue(this.themeManager.getSystemAppliedTheme(this.mNightUiMode));
    }

    public void applyThemeChanged(SystemThemeOption systemThemeOption) {
        Log.d(TAG, "applyThemeChanged option: " + systemThemeOption);
        int i = 2;
        int i2 = 1;
        if (systemThemeOption.getTitle().equals("light")) {
            i = 1;
        } else if (systemThemeOption.getTitle().equals("dark")) {
            i2 = 2;
        } else {
            i2 = 3;
            i = 0;
        }
        this.mModeManager.setNightMode(i);
        PersonalizeMetrics.setCurrentUiMode(this.mAppContext, i2);
        Log.d(TAG, "applyThemeChanged: " + this.mModeManager.getNightMode());
    }
}
