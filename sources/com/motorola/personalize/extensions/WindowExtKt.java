package com.motorola.personalize.extensions;

import android.util.Log;
import android.view.Window;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, mo15462d2 = {"customFlags", "", "Landroid/view/Window;", "updateStatusColor", "color", "", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: WindowExt.kt */
public final class WindowExtKt {
    public static final void customFlags(Window window) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        window.addFlags(512);
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 512 | 256);
    }

    public static final void updateStatusColor(Window window, int i) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("updateStatusColor ", Integer.valueOf(i)));
        }
        window.setStatusBarColor(i);
    }
}
