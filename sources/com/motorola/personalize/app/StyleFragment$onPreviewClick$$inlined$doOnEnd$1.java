package com.motorola.personalize.app;

import android.animation.Animator;
import android.widget.LinearLayout;
import com.motorola.personalize.databinding.FragmentFeatureBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t¸\u0006\n"}, mo15462d2 = {"androidx/core/animation/AnimatorKt$addListener$listener$1", "Landroid/animation/Animator$AnimatorListener;", "onAnimationCancel", "", "animator", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "core-ktx_release", "androidx/core/animation/AnimatorKt$doOnEnd$$inlined$addListener$1"}, mo15463k = 1, mo15464mv = {1, 5, 1})
/* compiled from: Animator.kt */
public final class StyleFragment$onPreviewClick$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    final /* synthetic */ StyleFragment this$0;

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
    }

    public void onAnimationRepeat(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
    }

    public void onAnimationStart(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
    }

    public StyleFragment$onPreviewClick$$inlined$doOnEnd$1(StyleFragment styleFragment) {
        this.this$0 = styleFragment;
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        FragmentFeatureBinding access$getBinding$p = this.this$0.binding;
        LinearLayout linearLayout = access$getBinding$p == null ? null : access$getBinding$p.gridPreviewSurfaceContainer;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }
}
