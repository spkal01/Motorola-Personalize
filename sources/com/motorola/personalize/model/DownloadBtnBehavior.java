package com.motorola.personalize.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007JP\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J8\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0016R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo15462d2 = {"Lcom/motorola/personalize/model/DownloadBtnBehavior;", "Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;", "Lcom/google/android/material/floatingactionbutton/ExtendedFloatingActionButton;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mPreOffset", "", "onNestedScroll", "", "coordinatorLayout", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "child", "target", "Landroid/view/View;", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "type", "consumed", "", "onStartNestedScroll", "", "directTargetChild", "axes", "Companion", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: DownloadBtnBehavior.kt */
public final class DownloadBtnBehavior extends CoordinatorLayout.Behavior<ExtendedFloatingActionButton> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "DownloadBtnBehavior";
    private int mPreOffset = Integer.MAX_VALUE;

    public DownloadBtnBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view, View view2, int i, int i2) {
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(extendedFloatingActionButton, "child");
        Intrinsics.checkNotNullParameter(view, "directTargetChild");
        Intrinsics.checkNotNullParameter(view2, "target");
        return view2 instanceof RecyclerView;
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        View view2 = view;
        Intrinsics.checkNotNullParameter(coordinatorLayout, "coordinatorLayout");
        Intrinsics.checkNotNullParameter(extendedFloatingActionButton, "child");
        Intrinsics.checkNotNullParameter(view2, "target");
        int[] iArr2 = iArr;
        Intrinsics.checkNotNullParameter(iArr2, "consumed");
        super.onNestedScroll(coordinatorLayout, extendedFloatingActionButton, view, i, i2, i3, i4, i5, iArr2);
        int computeVerticalScrollOffset = ((RecyclerView) view2).computeVerticalScrollOffset();
        if (this.mPreOffset != computeVerticalScrollOffset) {
            this.mPreOffset = computeVerticalScrollOffset;
            if (computeVerticalScrollOffset == 0) {
                extendedFloatingActionButton.extend();
            } else {
                extendedFloatingActionButton.shrink();
            }
        }
    }

    @Metadata(mo15461d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo15462d2 = {"Lcom/motorola/personalize/model/DownloadBtnBehavior$Companion;", "", "()V", "TAG", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: DownloadBtnBehavior.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
