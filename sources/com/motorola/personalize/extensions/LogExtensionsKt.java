package com.motorola.personalize.extensions;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a$\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\bø\u0001\u0000\u001a$\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\bø\u0001\u0000\u001a$\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\bø\u0001\u0000\u001a$\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\bø\u0001\u0000\u001a$\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\n"}, mo15462d2 = {"logD", "", "tag", "", "desc", "Lkotlin/Function0;", "logE", "logI", "logV", "logW", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: LogExtensions.kt */
public final class LogExtensionsKt {
    public static /* synthetic */ void logD$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Logger.INSTANCE.getTag();
        }
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(str, (String) function0.invoke());
        }
    }

    public static final void logD(String str, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(str, function0.invoke());
        }
    }

    public static /* synthetic */ void logV$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Logger.INSTANCE.getTag();
        }
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.v(str, (String) function0.invoke());
        }
    }

    public static final void logV(String str, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.v(str, function0.invoke());
        }
    }

    public static /* synthetic */ void logE$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Logger.INSTANCE.getTag();
        }
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        Log.e(str, (String) function0.invoke());
    }

    public static final void logE(String str, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        Log.e(str, function0.invoke());
    }

    public static /* synthetic */ void logW$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Logger.INSTANCE.getTag();
        }
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        Log.w(str, (String) function0.invoke());
    }

    public static final void logW(String str, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        Log.w(str, function0.invoke());
    }

    public static /* synthetic */ void logI$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = Logger.INSTANCE.getTag();
        }
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        Log.i(str, (String) function0.invoke());
    }

    public static final void logI(String str, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "desc");
        Log.i(str, function0.invoke());
    }
}
