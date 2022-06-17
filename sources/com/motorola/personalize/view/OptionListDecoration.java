package com.motorola.personalize.view;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.extensions.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo15462d2 = {"Lcom/motorola/personalize/view/OptionListDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "itemsCount", "", "(I)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: OptionListDecoration.kt */
public final class OptionListDecoration extends RecyclerView.ItemDecoration {
    private final int itemsCount;

    public OptionListDecoration(int i) {
        this.itemsCount = i;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        int measuredWidth = (recyclerView.getMeasuredWidth() - view.getWidth()) / 2;
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("getItemOffsets - marginHorizontal = ", Integer.valueOf(measuredWidth)));
        }
        int i = 0;
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            rect.left = measuredWidth - (layoutParams instanceof ViewGroup.MarginLayoutParams ? MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) layoutParams) : 0);
        }
        if (recyclerView.getChildAdapterPosition(view) == this.itemsCount - 1) {
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                i = MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) layoutParams2);
            }
            rect.right = measuredWidth - i;
        }
    }
}
