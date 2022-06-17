package com.motorola.personalize.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.motorola.personalize.C1057R;

public class FragmentFeatureBindingImpl extends FragmentFeatureBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(20);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"feature_top_bar"}, new int[]{1}, new int[]{C1057R.layout.feature_top_bar});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1057R.C1060id.guideline_0, 2);
        sparseIntArray.put(C1057R.C1060id.guideline_4, 3);
        sparseIntArray.put(C1057R.C1060id.guideline_1, 4);
        sparseIntArray.put(C1057R.C1060id.preview_fix_area, 5);
        sparseIntArray.put(C1057R.C1060id.preview_dynamic_area, 6);
        sparseIntArray.put(C1057R.C1060id.dynamic, 7);
        sparseIntArray.put(C1057R.C1060id.wallpaper_bg, 8);
        sparseIntArray.put(C1057R.C1060id.preview, 9);
        sparseIntArray.put(C1057R.C1060id.color_source_layout, 10);
        sparseIntArray.put(C1057R.C1060id.color_source_icon, 11);
        sparseIntArray.put(C1057R.C1060id.color_source_text, 12);
        sparseIntArray.put(C1057R.C1060id.option_list_container, 13);
        sparseIntArray.put(C1057R.C1060id.option_list, 14);
        sparseIntArray.put(C1057R.C1060id.eye_preview, 15);
        sparseIntArray.put(C1057R.C1060id.tip_area, 16);
        sparseIntArray.put(C1057R.C1060id.fab, 17);
        sparseIntArray.put(C1057R.C1060id.palette_container, 18);
        sparseIntArray.put(C1057R.C1060id.grid_preview_surface_container, 19);
    }

    public FragmentFeatureBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private FragmentFeatureBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[11], objArr[10], objArr[12], objArr[7], objArr[15], objArr[17], objArr[19], objArr[2], objArr[4], objArr[3], objArr[14], objArr[13], objArr[18], objArr[9], objArr[6], objArr[5], objArr[16], objArr[1], objArr[8]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setContainedBinding(this.toolbarContainer);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.toolbarContainer.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.toolbarContainer.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            com.motorola.personalize.databinding.FeatureTopBarBinding r4 = r4.toolbarContainer
            boolean r4 = r4.hasPendingBindings()
            if (r4 == 0) goto L_0x0016
            return r1
        L_0x0016:
            r4 = 0
            return r4
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.databinding.FragmentFeatureBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.toolbarContainer.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeToolbarContainer((FeatureTopBarBinding) obj, i2);
    }

    private boolean onChangeToolbarContainer(FeatureTopBarBinding featureTopBarBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.toolbarContainer);
    }
}
