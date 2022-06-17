package com.motorola.personalize.app;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.databinding.FragmentFeatureBinding;
import com.motorola.personalize.model.ColorOption;
import com.motorola.personalize.view.StyleSettingAdapter;
import com.motorola.styles.model.Option;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(mo15461d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, mo15462d2 = {"com/motorola/personalize/app/StyleFragment$setUpColorOptionsViewIfNeed$1", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrolled", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: StyleFragment.kt */
public final class StyleFragment$setUpColorOptionsViewIfNeed$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ Ref.IntRef $lastMiddleIndex;
    final /* synthetic */ Ref.IntRef $maxVisibleItemCount;
    final /* synthetic */ StyleFragment this$0;

    StyleFragment$setUpColorOptionsViewIfNeed$1(Ref.IntRef intRef, StyleFragment styleFragment, Ref.IntRef intRef2) {
        this.$maxVisibleItemCount = intRef;
        this.this$0 = styleFragment;
        this.$lastMiddleIndex = intRef2;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        int i3;
        TextView textView;
        ImageView imageView;
        TextView textView2;
        ImageView imageView2;
        TextView textView3;
        ImageView imageView3;
        boolean z;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Objects.requireNonNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        Ref.IntRef intRef = this.$maxVisibleItemCount;
        intRef.element = Math.max(intRef.element, Math.abs(findFirstVisibleItemPosition - findLastVisibleItemPosition) + 1);
        StyleSettingAdapter access$getAdapter$p = this.this$0.adapter;
        Option option = null;
        Integer valueOf = access$getAdapter$p == null ? null : Integer.valueOf(access$getAdapter$p.getItemCount());
        int i4 = 0;
        if (findFirstVisibleItemPosition == 0) {
            i3 = findLastVisibleItemPosition - Math.max(this.$maxVisibleItemCount.element / 2, 2);
        } else {
            if (valueOf != null && findLastVisibleItemPosition == valueOf.intValue() - 1) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                i3 = Math.max(this.$maxVisibleItemCount.element / 2, 2) + findFirstVisibleItemPosition;
            } else {
                i3 = (findFirstVisibleItemPosition + findLastVisibleItemPosition) / 2;
            }
        }
        Intrinsics.checkNotNull(valueOf);
        if (i3 >= valueOf.intValue()) {
            i3 = valueOf.intValue() - 1;
        }
        if (i3 >= 0) {
            i4 = i3;
        }
        if (this.$lastMiddleIndex.element != i4) {
            this.$lastMiddleIndex.element = i4;
            StyleSettingAdapter access$getAdapter$p2 = this.this$0.adapter;
            if (access$getAdapter$p2 != null) {
                option = access$getAdapter$p2.getOption(i4);
            }
            Objects.requireNonNull(option, "null cannot be cast to non-null type com.motorola.personalize.model.ColorOption");
            int type = ((ColorOption) option).getType();
            if (type == 0) {
                FragmentFeatureBinding access$getBinding$p = this.this$0.binding;
                if (!(access$getBinding$p == null || (imageView = access$getBinding$p.colorSourceIcon) == null)) {
                    imageView.setImageResource(C1057R.C1059drawable.ic_cs_palette);
                }
                FragmentFeatureBinding access$getBinding$p2 = this.this$0.binding;
                if (!(access$getBinding$p2 == null || (textView = access$getBinding$p2.colorSourceText) == null)) {
                    textView.setText(C1057R.string.color_source_preset);
                }
            } else if (type != 3) {
                FragmentFeatureBinding access$getBinding$p3 = this.this$0.binding;
                if (!(access$getBinding$p3 == null || (imageView3 = access$getBinding$p3.colorSourceIcon) == null)) {
                    imageView3.setImageResource(C1057R.C1059drawable.ic_cs_wallpaper);
                }
                FragmentFeatureBinding access$getBinding$p4 = this.this$0.binding;
                if (!(access$getBinding$p4 == null || (textView3 = access$getBinding$p4.colorSourceText) == null)) {
                    textView3.setText(C1057R.string.color_source_wallpaper);
                }
            } else {
                FragmentFeatureBinding access$getBinding$p5 = this.this$0.binding;
                if (!(access$getBinding$p5 == null || (imageView2 = access$getBinding$p5.colorSourceIcon) == null)) {
                    imageView2.setImageResource(C1057R.C1059drawable.ic_cs_picked_color);
                }
                FragmentFeatureBinding access$getBinding$p6 = this.this$0.binding;
                if (!(access$getBinding$p6 == null || (textView2 = access$getBinding$p6.colorSourceText) == null)) {
                    textView2.setText(C1057R.string.color_source_palette);
                }
            }
        }
        super.onScrolled(recyclerView, i, i2);
    }
}
