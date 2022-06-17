package com.android.wallpaper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewOverlay;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.android.wallpaper.Utilities;

public class RtlViewPager extends ViewPager {
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public RtlViewPager(Context context) {
        this(context, (AttributeSet) null);
    }

    public RtlViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        super.setAdapter(pagerAdapter);
        resetCurrentItem();
    }

    public void resetCurrentItem() {
        PagerAdapter adapter = getAdapter();
        if (adapter != null) {
            int i = 0;
            if (Utilities.isRtl(this)) {
                i = (adapter.getCount() - 1) + 0;
            }
            setCurrentItem(i);
        }
    }

    public int resolvePosition(int i) {
        PagerAdapter adapter = getAdapter();
        return (adapter == null || !Utilities.isRtl(this)) ? i : (adapter.getCount() - 1) - i;
    }
}
