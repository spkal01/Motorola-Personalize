package com.motorola.personalize.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0017J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u0016H\u0017J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180!H\u0017R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, mo15462d2 = {"Lcom/motorola/personalize/util/LooperExecutor;", "Ljava/util/concurrent/AbstractExecutorService;", "looper", "Landroid/os/Looper;", "(Landroid/os/Looper;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "getLooper", "()Landroid/os/Looper;", "thread", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "awaitTermination", "", "l", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "execute", "", "runnable", "Ljava/lang/Runnable;", "isShutdown", "isTerminated", "post", "setThreadPriority", "priority", "", "shutdown", "shutdownNow", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: LooperExecutor.kt */
public final class LooperExecutor extends AbstractExecutorService {
    private final Handler handler;

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public LooperExecutor(Looper looper) {
        Intrinsics.checkNotNull(looper);
        this.handler = new Handler(looper);
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public void execute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        if (Intrinsics.areEqual((Object) this.handler.getLooper(), (Object) Looper.myLooper())) {
            runnable.run();
        } else {
            this.handler.post(runnable);
        }
    }

    public final void post(Runnable runnable) {
        Handler handler2 = this.handler;
        Intrinsics.checkNotNull(runnable);
        handler2.post(runnable);
    }

    @Deprecated(message = "")
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Deprecated(message = "")
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Deprecated(message = "")
    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        throw new UnsupportedOperationException();
    }

    public final Thread getThread() {
        Thread thread = this.handler.getLooper().getThread();
        Intrinsics.checkNotNullExpressionValue(thread, "handler.looper.thread");
        return thread;
    }

    public final Looper getLooper() {
        Looper looper = this.handler.getLooper();
        Intrinsics.checkNotNullExpressionValue(looper, "handler.looper");
        return looper;
    }

    public final void setThreadPriority(int i) {
        Process.setThreadPriority(((HandlerThread) getThread()).getThreadId(), i);
    }
}
