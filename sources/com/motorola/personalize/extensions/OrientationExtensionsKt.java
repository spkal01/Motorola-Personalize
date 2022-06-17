package com.motorola.personalize.extensions;

import android.os.Build;
import android.view.View;
import android.view.Window;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u001a2\u0010\b\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\n¨\u0006\u000b"}, mo15462d2 = {"applyViewPaddingInset", "", "Landroid/view/View;", "left", "", "right", "top", "bottom", "applyViewPaddingInsetListener", "updateWindowFitSystem", "Landroid/view/Window;", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: OrientationExtensions.kt */
public final class OrientationExtensionsKt {
    public static final void updateWindowFitSystem(Window window) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        if (Build.VERSION.SDK_INT >= 30) {
            window.setDecorFitsSystemWindows(false);
        } else {
            window.getDecorView().setSystemUiVisibility(1792);
        }
    }

    public static /* synthetic */ void applyViewPaddingInsetListener$default(View view, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            z4 = false;
        }
        applyViewPaddingInsetListener(view, z, z2, z3, z4);
    }

    public static final void applyViewPaddingInsetListener(View view, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewExtensionsKt.doOnApplyWindowInsets(view, new OrientationExtensionsKt$applyViewPaddingInsetListener$1(view, z, z3, z2, z4));
    }

    public static /* synthetic */ void applyViewPaddingInset$default(View view, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            z4 = false;
        }
        applyViewPaddingInset(view, z, z2, z3, z4);
    }

    public static final void applyViewPaddingInset(View view, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        ViewExtensionsKt.doOnApplyWindowInsets(view, new OrientationExtensionsKt$applyViewPaddingInset$1(view, z, z3, z2, z4));
    }
}
