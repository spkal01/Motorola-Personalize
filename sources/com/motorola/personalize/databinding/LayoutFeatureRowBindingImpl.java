package com.motorola.personalize.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.motorola.personalize.C1057R;

public class LayoutFeatureRowBindingImpl extends LayoutFeatureRowBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"layout_feature", "layout_feature", "layout_feature", "layout_feature"}, new int[]{1, 2, 3, 4}, new int[]{C1057R.layout.layout_feature, C1057R.layout.layout_feature, C1057R.layout.layout_feature, C1057R.layout.layout_feature});
    }

    public LayoutFeatureRowBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private LayoutFeatureRowBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, objArr[1], objArr[2], objArr[3], objArr[4]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.featureItem1);
        setContainedBinding(this.featureItem2);
        setContainedBinding(this.featureItem3);
        setContainedBinding(this.featureItem4);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        this.featureItem1.invalidateAll();
        this.featureItem2.invalidateAll();
        this.featureItem3.invalidateAll();
        this.featureItem4.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.featureItem2.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.featureItem3.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.featureItem4.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.featureItem1.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0033 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            com.motorola.personalize.databinding.LayoutFeatureBinding r0 = r4.featureItem1
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.motorola.personalize.databinding.LayoutFeatureBinding r0 = r4.featureItem2
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.motorola.personalize.databinding.LayoutFeatureBinding r0 = r4.featureItem3
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            com.motorola.personalize.databinding.LayoutFeatureBinding r4 = r4.featureItem4
            boolean r4 = r4.hasPendingBindings()
            if (r4 == 0) goto L_0x0031
            return r1
        L_0x0031:
            r4 = 0
            return r4
        L_0x0033:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0033 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.databinding.LayoutFeatureRowBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.featureItem1.setLifecycleOwner(lifecycleOwner);
        this.featureItem2.setLifecycleOwner(lifecycleOwner);
        this.featureItem3.setLifecycleOwner(lifecycleOwner);
        this.featureItem4.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeFeatureItem2((LayoutFeatureBinding) obj, i2);
        }
        if (i == 1) {
            return onChangeFeatureItem1((LayoutFeatureBinding) obj, i2);
        }
        if (i == 2) {
            return onChangeFeatureItem4((LayoutFeatureBinding) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeFeatureItem3((LayoutFeatureBinding) obj, i2);
    }

    private boolean onChangeFeatureItem2(LayoutFeatureBinding layoutFeatureBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeFeatureItem1(LayoutFeatureBinding layoutFeatureBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeFeatureItem4(LayoutFeatureBinding layoutFeatureBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeFeatureItem3(LayoutFeatureBinding layoutFeatureBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.featureItem1);
        executeBindingsOn(this.featureItem2);
        executeBindingsOn(this.featureItem3);
        executeBindingsOn(this.featureItem4);
    }
}
