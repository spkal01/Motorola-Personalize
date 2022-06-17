package com.motorola.personalize.extensions;

import android.view.View;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo15461d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n"}, mo15462d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "windowInsetsCompat", "Landroidx/core/view/WindowInsetsCompat;", "<anonymous parameter 2>", "Lcom/motorola/personalize/extensions/ViewPaddingState;"}, mo15463k = 3, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: OrientationExtensions.kt */
final class OrientationExtensionsKt$applyViewPaddingInset$1 extends Lambda implements Function3<View, WindowInsetsCompat, ViewPaddingState, Unit> {
    final /* synthetic */ boolean $bottom;
    final /* synthetic */ boolean $left;
    final /* synthetic */ boolean $right;
    final /* synthetic */ View $this_applyViewPaddingInset;
    final /* synthetic */ boolean $top;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OrientationExtensionsKt$applyViewPaddingInset$1(View view, boolean z, boolean z2, boolean z3, boolean z4) {
        super(3);
        this.$this_applyViewPaddingInset = view;
        this.$left = z;
        this.$top = z2;
        this.$right = z3;
        this.$bottom = z4;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((View) obj, (WindowInsetsCompat) obj2, (ViewPaddingState) obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(View view, WindowInsetsCompat windowInsetsCompat, ViewPaddingState viewPaddingState) {
        Intrinsics.checkNotNullParameter(view, "$noName_0");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "windowInsetsCompat");
        Intrinsics.checkNotNullParameter(viewPaddingState, "$noName_2");
        Insets systemWindowInsets = windowInsetsCompat.getSystemWindowInsets();
        Intrinsics.checkNotNullExpressionValue(systemWindowInsets, "windowInsetsCompat.systemWindowInsets");
        int i = windowInsetsCompat.getInsets(WindowInsets.Type.statusBars()).top;
        View view2 = this.$this_applyViewPaddingInset;
        view2.setPadding(this.$left ? systemWindowInsets.left : view2.getPaddingLeft(), this.$top ? Math.max(systemWindowInsets.top, i) : this.$this_applyViewPaddingInset.getPaddingTop(), this.$right ? systemWindowInsets.right : this.$this_applyViewPaddingInset.getPaddingRight(), this.$bottom ? systemWindowInsets.bottom : this.$this_applyViewPaddingInset.getPaddingBottom());
    }
}
