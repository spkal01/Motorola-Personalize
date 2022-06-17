package com.motorola.personalize.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FeatureTileViewData;

public class LayoutFeatureTileBindingImpl extends LayoutFeatureTileBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CardView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1057R.C1060id.feature_layout, 4);
        sparseIntArray.put(C1057R.C1060id.feature_image_card, 5);
    }

    public LayoutFeatureTileBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private LayoutFeatureTileBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[1], objArr[5], objArr[4], objArr[2]);
        this.mDirtyFlags = -1;
        this.featureDescription.setTag((Object) null);
        this.featureImage.setTag((Object) null);
        this.featureTitle.setTag((Object) null);
        CardView cardView = objArr[0];
        this.mboundView0 = cardView;
        cardView.setTag((Object) null);
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
        setFeatureItem((FeatureTileViewData) obj);
        return true;
    }

    public void setFeatureItem(FeatureTileViewData featureTileViewData) {
        this.mFeatureItem = featureTileViewData;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        Drawable drawable;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        FeatureTileViewData featureTileViewData = this.mFeatureItem;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        String str2 = null;
        if (i == 0 || featureTileViewData == null) {
            str = null;
            drawable = null;
        } else {
            String title = featureTileViewData.getTitle();
            String description = featureTileViewData.getDescription();
            drawable = featureTileViewData.getIcon();
            String str3 = description;
            str = title;
            str2 = str3;
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.featureDescription, str2);
            ImageViewBindingAdapter.setImageDrawable(this.featureImage, drawable);
            TextViewBindingAdapter.setText(this.featureTitle, str);
        }
    }
}
