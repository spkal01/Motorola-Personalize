package com.motorola.personalize.view;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.ResourcesExtensionsKt;
import com.motorola.personalize.provider.data.provider.FeatureProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo15462d2 = {"Lcom/motorola/personalize/view/FeatureListDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "marginTop", "", "gridSize", "(II)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: FeatureListDecoration.kt */
public final class FeatureListDecoration extends RecyclerView.ItemDecoration {
    private final int gridSize;
    private final int marginTop;

    public FeatureListDecoration(int i, int i2) {
        this.marginTop = i;
        this.gridSize = i2;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("getItemOffsets - marginTop = ", Integer.valueOf(this.marginTop)));
        }
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition < this.gridSize) {
            rect.top = this.marginTop;
        }
        Resources resources = recyclerView.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "parent.resources");
        if (ResourcesExtensionsKt.isLandscape(resources)) {
            if (childAdapterPosition == FeatureProvider.THEMES_INDEX || childAdapterPosition == FeatureProvider.THEMES_INDEX + 1) {
                rect.top = recyclerView.getResources().getDimensionPixelSize(C1057R.dimen.second_decorator_margin_top);
                int dimensionPixelSize = recyclerView.getResources().getDimensionPixelSize(C1057R.dimen.decorator_margin_horizontal);
                rect.left = dimensionPixelSize;
                rect.right = dimensionPixelSize;
            }
        } else if (childAdapterPosition == FeatureProvider.THEMES_INDEX) {
            rect.top = recyclerView.getResources().getDimensionPixelSize(C1057R.dimen.second_decorator_margin_top);
        }
    }
}
