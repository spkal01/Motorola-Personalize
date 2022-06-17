package com.motorola.personalize.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PublicViewModel extends AndroidViewModel {
    private static ExecutorService sWorker;
    protected Context mAppContext;

    public PublicViewModel(Application application) {
        super(application);
        this.mAppContext = application.getApplicationContext();
    }

    public static ExecutorService getWorker() {
        if (sWorker == null) {
            synchronized (SoundsViewModel.class) {
                if (sWorker == null) {
                    sWorker = Executors.newSingleThreadExecutor();
                }
            }
        }
        return sWorker;
    }

    public static void shutdownWorker() {
        if (sWorker != null) {
            synchronized (ThemeViewModel.class) {
                ExecutorService executorService = sWorker;
                if (executorService != null) {
                    executorService.shutdownNow();
                    sWorker = null;
                }
            }
        }
    }

    public static void postWorkerThread(Runnable runnable) {
        getWorker().submit(runnable);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        this.mAppContext = null;
    }
}
