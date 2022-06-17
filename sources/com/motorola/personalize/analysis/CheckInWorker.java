package com.motorola.personalize.analysis;

import android.content.Context;
import android.util.Log;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CheckInWorker extends Worker {
    private static final String TAG = "CheckInWorker";
    private Context mContext;

    public CheckInWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.mContext = context.getApplicationContext();
    }

    public ListenableWorker.Result doWork() {
        Log.d(TAG, "doWork");
        PersonalizeMetrics.sendEvent(this.mContext);
        return ListenableWorker.Result.success();
    }

    public static void initWorker(Context context) {
        PeriodicWorkRequest.Builder builder = new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) CheckInWorker.class, 24, TimeUnit.HOURS);
        builder.setInitialDelay(delayToMidNight(), TimeUnit.MILLISECONDS);
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("mp-check-in", ExistingPeriodicWorkPolicy.KEEP, (PeriodicWorkRequest) builder.build());
    }

    private static long getEndOfDay() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.setTimeZone(TimeZone.getDefault());
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.add(5, 1);
        return instance.getTimeInMillis();
    }

    private static long delayToMidNight() {
        return getEndOfDay() - System.currentTimeMillis();
    }
}
