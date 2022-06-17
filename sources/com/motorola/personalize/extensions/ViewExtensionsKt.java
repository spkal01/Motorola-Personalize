package com.motorola.personalize.extensions;

import android.animation.ArgbEvaluator;
import android.content.res.Resources;
import android.graphics.Insets;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.TextView;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0003\u001a(\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\r\u001a\u00020\u0007\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0005\u001a*\u0010\u000f\u001a\u00020\u0001*\u00020\u00052\u001e\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u0011\u001a\"\u0010\u0012\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007\u001a\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\"\b\b\u0000\u0010\u001a*\u00020\u0005*\u0002H\u001a¢\u0006\u0002\u0010\u001b\u001a\n\u0010\u001c\u001a\u00020\u0001*\u00020\u0005\u001a\u0014\u0010\u001d\u001a\u00020\u0001*\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u001a\u0012\u0010 \u001a\u00020\u0001*\u00020\u00052\u0006\u0010!\u001a\u00020\u0014\u001a#\u0010\"\u001a\u00020\u0001\"\b\b\u0000\u0010\u001a*\u00020#*\u0002H\u001a2\b\b\u0001\u0010$\u001a\u00020\u0007¢\u0006\u0002\u0010%\u001a\u0012\u0010&\u001a\u00020\u0001*\u00020\u00052\u0006\u0010'\u001a\u00020(\u001a&\u0010)\u001a\u00020\u0001*\u00020#2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010*\u001a\u00020\u00072\b\b\u0001\u0010+\u001a\u00020\u0007¨\u0006,"}, mo15462d2 = {"applyAndroidInsets", "", "insets", "Landroidx/core/view/WindowInsetsCompat;", "view", "Landroid/view/View;", "originalSpaceLeft", "", "originalSpaceRight", "applyMotorolaInsets", "createStateForView", "Lcom/motorola/personalize/extensions/ViewPaddingState;", "adjustHeaderTopPadding", "insetsTop", "applyWaterfallInsets", "doOnApplyWindowInsets", "function", "Lkotlin/Function3;", "getIntBetweenColors", "offset", "", "startValue", "endValue", "onLayoutRendered", "Landroidx/lifecycle/LiveData;", "Lcom/motorola/personalize/extensions/EmptyObject;", "T", "(Landroid/view/View;)Landroidx/lifecycle/LiveData;", "requestApplyInsetsWhenAttached", "requestApplyWindowInsets", "windowInsets", "Landroid/view/WindowInsets;", "scale", "scaleFactor", "setTextMeasured", "Landroid/widget/TextView;", "textId", "(Landroid/widget/TextView;I)V", "updateStatusBarIconColor", "isWhite", "", "updateTextColor", "startId", "endId", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt {
    public static final int getIntBetweenColors(View view, float f, int i, int i2) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object evaluate = new ArgbEvaluator().evaluate(f, Integer.valueOf(i), Integer.valueOf(i2));
        Integer num = evaluate instanceof Integer ? (Integer) evaluate : null;
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static final void updateTextColor(TextView textView, float f, int i, int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Resources resources = textView.getContext().getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        Resources.Theme theme = textView.getContext().getTheme();
        Intrinsics.checkNotNullExpressionValue(theme, "context.theme");
        int color32Bits = ResourcesExtensionsKt.getColor32Bits(resources, i, theme);
        Resources resources2 = textView.getContext().getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "context.resources");
        Resources.Theme theme2 = textView.getContext().getTheme();
        Intrinsics.checkNotNullExpressionValue(theme2, "context.theme");
        textView.setTextColor(getIntBetweenColors(textView, f, ResourcesExtensionsKt.getColor32Bits(resources2, i2, theme2), color32Bits));
    }

    public static final void scale(View view, float f) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setScaleX(f);
        view.setScaleY(f);
    }

    public static final <T extends View> LiveData<EmptyObject> onLayoutRendered(T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        return new ViewExtensionsKt$onLayoutRendered$1(t);
    }

    public static final void doOnApplyWindowInsets(View view, Function3<? super View, ? super WindowInsetsCompat, ? super ViewPaddingState, Unit> function3) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(function3, "function");
        ViewCompat.setOnApplyWindowInsetsListener(view, new OnApplyWindowInsetsListener(createStateForView(view)) {
            public final /* synthetic */ ViewPaddingState f$1;

            {
                this.f$1 = r2;
            }

            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return ViewExtensionsKt.m99doOnApplyWindowInsets$lambda0(Function3.this, this.f$1, view, windowInsetsCompat);
            }
        });
        requestApplyInsetsWhenAttached(view);
    }

    /* access modifiers changed from: private */
    /* renamed from: doOnApplyWindowInsets$lambda-0  reason: not valid java name */
    public static final WindowInsetsCompat m99doOnApplyWindowInsets$lambda0(Function3 function3, ViewPaddingState viewPaddingState, View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(function3, "$function");
        Intrinsics.checkNotNullParameter(viewPaddingState, "$paddingState");
        Intrinsics.checkNotNullExpressionValue(view, "v");
        Intrinsics.checkNotNullExpressionValue(windowInsetsCompat, "insets");
        function3.invoke(view, windowInsetsCompat, viewPaddingState);
        return windowInsetsCompat;
    }

    public static final void requestApplyInsetsWhenAttached(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view.isAttachedToWindow()) {
            view.requestApplyInsets();
        } else {
            view.addOnAttachStateChangeListener(new ViewExtensionsKt$requestApplyInsetsWhenAttached$1());
        }
    }

    public static final void updateStatusBarIconColor(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        int i = 0;
        if (Build.VERSION.SDK_INT >= 30) {
            if (!z) {
                i = 8;
            }
            WindowInsetsController windowInsetsController = view.getWindowInsetsController();
            if (windowInsetsController != null) {
                windowInsetsController.setSystemBarsAppearance(i, 8);
                return;
            }
            return;
        }
        if (!z) {
            i = 8192;
        }
        view.setSystemUiVisibility(i);
    }

    public static final void applyWaterfallInsets(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        doOnApplyWindowInsets(view, new ViewExtensionsKt$applyWaterfallInsets$1(view.getPaddingLeft(), view.getPaddingRight()));
    }

    public static final <T extends TextView> void setTextMeasured(T t, int i) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        t.setText(i);
        t.requestLayout();
    }

    /* access modifiers changed from: private */
    public static final void applyAndroidInsets(WindowInsetsCompat windowInsetsCompat, View view, int i, int i2) {
        DisplayCutout displayCutout;
        WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
        Insets insets = null;
        if (!(windowInsets == null || (displayCutout = windowInsets.getDisplayCutout()) == null)) {
            insets = displayCutout.getWaterfallInsets();
        }
        if (insets != null) {
            view.setPaddingRelative(i + insets.left, view.getPaddingTop(), i2 + insets.right, view.getPaddingBottom());
        }
    }

    /* access modifiers changed from: private */
    public static final void applyMotorolaInsets(WindowInsetsCompat windowInsetsCompat, View view, int i, int i2) {
        if ((windowInsetsCompat.getStableInsetLeft() == 0 || windowInsetsCompat.getStableInsetRight() == 0) ? false : true) {
            view.setPaddingRelative(i + windowInsetsCompat.getStableInsetLeft(), view.getPaddingTop(), i2 + windowInsetsCompat.getStableInsetRight(), view.getPaddingBottom());
        }
    }

    private static final ViewPaddingState createStateForView(View view) {
        return new ViewPaddingState(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom(), view.getPaddingStart(), view.getPaddingEnd());
    }

    public static final void adjustHeaderTopPadding(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static final void requestApplyWindowInsets(View view, WindowInsets windowInsets) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "dispatchApplyWindowInsets");
        }
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(index)");
                childAt.dispatchApplyWindowInsets(windowInsets);
            }
        }
    }
}
