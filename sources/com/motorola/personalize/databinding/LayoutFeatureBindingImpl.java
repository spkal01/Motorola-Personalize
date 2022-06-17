package com.motorola.personalize.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FeatureViewData;

public class LayoutFeatureBindingImpl extends LayoutFeatureBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1057R.C1060id.feature_icon_background, 3);
        sparseIntArray.put(C1057R.C1060id.guideline_5_percent_vertical, 4);
        sparseIntArray.put(C1057R.C1060id.guideline_95_percent_vertical, 5);
    }

    public LayoutFeatureBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private LayoutFeatureBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[1], objArr[2], objArr[4], objArr[5]);
        this.mDirtyFlags = -1;
        this.featureIconForeground.setTag((Object) null);
        this.featureTitle.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (2 != i) {
            return false;
        }
        setFeatureItem((FeatureViewData) obj);
        return true;
    }

    public void setFeatureItem(FeatureViewData featureViewData) {
        this.mFeatureItem = featureViewData;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        FeatureViewData featureViewData = this.mFeatureItem;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        Drawable drawable = null;
        if (i == 0 || featureViewData == null) {
            str = null;
        } else {
            drawable = featureViewData.getIcon();
            str = featureViewData.getText();
        }
        if (i != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.featureIconForeground, drawable);
            TextViewBindingAdapter.setText(this.featureTitle, str);
        }
    }
}
