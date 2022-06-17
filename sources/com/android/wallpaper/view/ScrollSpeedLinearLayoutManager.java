package com.android.wallpaper.view;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class ScrollSpeedLinearLayoutManager extends LinearLayoutManager {
    /* access modifiers changed from: private */
    public final float mSpeedScale;

    public ScrollSpeedLinearLayoutManager(Context context, int i, boolean z, float f) {
        super(context, i, z);
        this.mSpeedScale = f;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        C07681 r2 = new LinearSmoothScroller(recyclerView.getContext()) {
            public PointF computeScrollVectorForPosition(int i) {
                return ScrollSpeedLinearLayoutManager.this.computeScrollVectorForPosition(i);
            }

            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return super.calculateSpeedPerPixel(displayMetrics) * ScrollSpeedLinearLayoutManager.this.mSpeedScale;
            }
        };
        r2.setTargetPosition(i);
        startSmoothScroll(r2);
    }
}
