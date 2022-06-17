package com.motorola.personalize.util;

import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u001c\u0010\u0018\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\n¨\u0006\u001a"}, mo15462d2 = {"Lcom/motorola/personalize/util/Executors;", "", "()V", "CORE_POOL_SIZE", "", "CPU_COUNT", "KEEP_ALIVE", "MAIN_EXECUTOR", "Lcom/motorola/personalize/util/LooperExecutor;", "getMAIN_EXECUTOR", "()Lcom/motorola/personalize/util/LooperExecutor;", "MAXIMUM_POOL_SIZE", "MODEL_EXECUTOR", "getMODEL_EXECUTOR", "THREAD_POOL_EXECUTOR", "Ljava/util/concurrent/ThreadPoolExecutor;", "getTHREAD_POOL_EXECUTOR", "()Ljava/util/concurrent/ThreadPoolExecutor;", "UI_HELPER_EXECUTOR", "getUI_HELPER_EXECUTOR", "createAndStartNewForegroundLooper", "Landroid/os/Looper;", "name", "", "createAndStartNewLooper", "priority", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: Executors.kt */
public final class Executors {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    public static final Executors INSTANCE;
    private static final int KEEP_ALIVE = 1;
    private static final LooperExecutor MAIN_EXECUTOR = new LooperExecutor(Looper.getMainLooper());
    private static final int MAXIMUM_POOL_SIZE;
    private static final LooperExecutor MODEL_EXECUTOR;
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;
    private static final LooperExecutor UI_HELPER_EXECUTOR;

    public final Looper createAndStartNewLooper(String str) {
        return createAndStartNewLooper$default(this, str, 0, 2, (Object) null);
    }

    private Executors() {
    }

    static {
        Executors executors = new Executors();
        INSTANCE = executors;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        int i = availableProcessors + 1;
        CORE_POOL_SIZE = i;
        int i2 = (availableProcessors * 2) + 1;
        MAXIMUM_POOL_SIZE = i2;
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(i, i2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
        UI_HELPER_EXECUTOR = new LooperExecutor(executors.createAndStartNewForegroundLooper("UiThreadHelper"));
        MODEL_EXECUTOR = new LooperExecutor(createAndStartNewLooper$default(executors, "launcher-loader", 0, 2, (Object) null));
    }

    public final ThreadPoolExecutor getTHREAD_POOL_EXECUTOR() {
        return THREAD_POOL_EXECUTOR;
    }

    public final LooperExecutor getMAIN_EXECUTOR() {
        return MAIN_EXECUTOR;
    }

    public final LooperExecutor getUI_HELPER_EXECUTOR() {
        return UI_HELPER_EXECUTOR;
    }

    public static /* synthetic */ Looper createAndStartNewLooper$default(Executors executors, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return executors.createAndStartNewLooper(str, i);
    }

    public final Looper createAndStartNewLooper(String str, int i) {
        HandlerThread handlerThread = new HandlerThread(str, i);
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Intrinsics.checkNotNullExpressionValue(looper, "thread.looper");
        return looper;
    }

    public final Looper createAndStartNewForegroundLooper(String str) {
        return createAndStartNewLooper(str, -2);
    }

    public final LooperExecutor getMODEL_EXECUTOR() {
        return MODEL_EXECUTOR;
    }
}
