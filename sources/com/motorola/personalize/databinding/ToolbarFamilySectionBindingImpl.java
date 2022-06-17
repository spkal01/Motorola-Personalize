package com.motorola.personalize.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FamilyData;

public class ToolbarFamilySectionBindingImpl extends ToolbarFamilySectionBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1057R.C1060id.detail_feature_family_toolbar, 3);
    }

    public ToolbarFamilySectionBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private ToolbarFamilySectionBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[2], objArr[0], objArr[1]);
        this.mDirtyFlags = -1;
        this.toolbarDescription.setTag((Object) null);
        this.toolbarLayout.setTag((Object) null);
        this.toolbarTitle.setTag((Object) null);
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
        if (1 != i) {
            return false;
        }
        setFamilyData((FamilyData) obj);
        return true;
    }

    public void setFamilyData(FamilyData familyData) {
        this.mFamilyData = familyData;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
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
        FamilyData familyData = this.mFamilyData;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        String str2 = null;
        if (i == 0 || familyData == null) {
            str = null;
        } else {
            str = familyData.getTitle();
            str2 = familyData.getDescription();
        }
        if (i != 0) {
            if (getBuildSdkInt() >= 4) {
                this.toolbarDescription.setContentDescription(str2);
                this.toolbarTitle.setContentDescription(str);
            }
            TextViewBindingAdapter.setText(this.toolbarDescription, str2);
            TextViewBindingAdapter.setText(this.toolbarTitle, str);
        }
    }
}
