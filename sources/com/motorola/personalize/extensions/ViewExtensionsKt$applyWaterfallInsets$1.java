package com.motorola.personalize.extensions;

import android.os.Build;
import android.view.View;
import androidx.core.view.WindowInsetsCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo15461d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n"}, mo15462d2 = {"<anonymous>", "", "view", "Landroid/view/View;", "insets", "Landroidx/core/view/WindowInsetsCompat;", "<anonymous parameter 2>", "Lcom/motorola/personalize/extensions/ViewPaddingState;"}, mo15463k = 3, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ViewExtensions.kt */
final class ViewExtensionsKt$applyWaterfallInsets$1 extends Lambda implements Function3<View, WindowInsetsCompat, ViewPaddingState, Unit> {
    final /* synthetic */ int $originalSpaceLeft;
    final /* synthetic */ int $originalSpaceRight;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ViewExtensionsKt$applyWaterfallInsets$1(int i, int i2) {
        super(3);
        this.$originalSpaceLeft = i;
        this.$originalSpaceRight = i2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((View) obj, (WindowInsetsCompat) obj2, (ViewPaddingState) obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(View view, WindowInsetsCompat windowInsetsCompat, ViewPaddingState viewPaddingState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        Intrinsics.checkNotNullParameter(viewPaddingState, "$noName_2");
        if (Build.VERSION.SDK_INT >= 30) {
            ViewExtensionsKt.applyAndroidInsets(windowInsetsCompat, view, this.$originalSpaceLeft, this.$originalSpaceRight);
        } else {
            ViewExtensionsKt.applyMotorolaInsets(windowInsetsCompat, view, this.$originalSpaceLeft, this.$originalSpaceRight);
        }
    }
}
