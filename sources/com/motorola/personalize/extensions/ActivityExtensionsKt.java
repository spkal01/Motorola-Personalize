package com.motorola.personalize.extensions;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.p003os.BundleKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0002\u001aH\u0010\u0006\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000f¨\u0006\u0011"}, mo15462d2 = {"getMetaData", "Landroid/os/Bundle;", "Landroid/app/Activity;", "setStatusBarAppearanceToDark", "", "setStatusBarAppearanceToLight", "setupToolbar", "Landroidx/appcompat/app/AppCompatActivity;", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "isHomeAsUpEnabled", "", "isShowHomeEnabled", "isShowTitleEnabled", "onClick", "Lkotlin/Function1;", "Landroid/view/View;", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ActivityExtensions.kt */
public final class ActivityExtensionsKt {
    public static /* synthetic */ void setupToolbar$default(AppCompatActivity appCompatActivity, Toolbar toolbar, boolean z, boolean z2, boolean z3, Function1 function1, int i, Object obj) {
        boolean z4 = (i & 2) != 0 ? true : z;
        boolean z5 = (i & 4) != 0 ? true : z2;
        if ((i & 8) != 0) {
            z3 = false;
        }
        boolean z6 = z3;
        if ((i & 16) != 0) {
            function1 = null;
        }
        setupToolbar(appCompatActivity, toolbar, z4, z5, z6, function1);
    }

    public static final void setupToolbar(AppCompatActivity appCompatActivity, Toolbar toolbar, boolean z, boolean z2, boolean z3, Function1<? super View, Unit> function1) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(z);
        }
        ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayShowHomeEnabled(z2);
        }
        ActionBar supportActionBar3 = appCompatActivity.getSupportActionBar();
        if (supportActionBar3 != null) {
            supportActionBar3.setDisplayShowTitleEnabled(z3);
        }
        if (function1 != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ActivityExtensionsKt.m98setupToolbar$lambda1$lambda0(Function1.this, view);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setupToolbar$lambda-1$lambda-0  reason: not valid java name */
    public static final void m98setupToolbar$lambda1$lambda0(Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(view);
    }

    public static final Bundle getMetaData(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Bundle bundle = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 128).metaData;
        return bundle == null ? BundleKt.bundleOf(new Pair[0]) : bundle;
    }

    public static final void setStatusBarAppearanceToLight(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsController insetsController = activity.getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.setSystemBarsAppearance(24, 24);
                return;
            }
            return;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(8208);
    }

    public static final void setStatusBarAppearanceToDark(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsController insetsController = activity.getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.setSystemBarsAppearance(0, 24);
                return;
            }
            return;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(8208);
    }
}
