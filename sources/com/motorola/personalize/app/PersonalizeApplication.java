package com.motorola.personalize.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.work.Configuration;
import com.motorola.personalize.analysis.CheckInWorker;
import com.motorola.personalize.data.AppDatabaseHelper;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.loader.WallpaperLoader;
import com.motorola.personalize.util.Debug;
import com.motorola.styles.model.OnlineFontService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0002¨\u0006\t"}, mo15462d2 = {"Lcom/motorola/personalize/app/PersonalizeApplication;", "Landroid/app/Application;", "Landroidx/work/Configuration$Provider;", "()V", "getWorkManagerConfiguration", "Landroidx/work/Configuration;", "onCreate", "", "printVersionInfo", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: PersonalizeApplication.kt */
public final class PersonalizeApplication extends Application implements Configuration.Provider {
    public void onCreate() {
        super.onCreate();
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onCreate()");
        }
        Debug.debug();
        Context context = this;
        OnlineFontService.getInstance().onCreate(context);
        AppDatabaseHelper.init(context);
        WallpaperLoader.getInstance().init(context);
        printVersionInfo();
        CheckInWorker.initWorker(context);
    }

    private final void printVersionInfo() {
        Log.i(Logger.INSTANCE.getTag(), "Personalize started, version: " + ContextExtensionsKt.getVersionName$default(this, (String) null, 1, (Object) null) + ", flavor = " + "release");
    }

    public Configuration getWorkManagerConfiguration() {
        Configuration build = new Configuration.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().build()");
        return build;
    }
}
