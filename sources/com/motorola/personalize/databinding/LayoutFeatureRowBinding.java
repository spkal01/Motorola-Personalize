package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.motorola.personalize.C1057R;

public abstract class LayoutFeatureRowBinding extends ViewDataBinding {
    public final LayoutFeatureBinding featureItem1;
    public final LayoutFeatureBinding featureItem2;
    public final LayoutFeatureBinding featureItem3;
    public final LayoutFeatureBinding featureItem4;

    protected LayoutFeatureRowBinding(Object obj, View view, int i, LayoutFeatureBinding layoutFeatureBinding, LayoutFeatureBinding layoutFeatureBinding2, LayoutFeatureBinding layoutFeatureBinding3, LayoutFeatureBinding layoutFeatureBinding4) {
        super(obj, view, i);
        this.featureItem1 = layoutFeatureBinding;
        this.featureItem2 = layoutFeatureBinding2;
        this.featureItem3 = layoutFeatureBinding3;
        this.featureItem4 = layoutFeatureBinding4;
    }

    public static LayoutFeatureRowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureRowBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutFeatureRowBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_feature_row, viewGroup, z, obj);
    }

    public static LayoutFeatureRowBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureRowBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutFeatureRowBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_feature_row, (ViewGroup) null, false, obj);
    }

    public static LayoutFeatureRowBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureRowBinding bind(View view, Object obj) {
        return (LayoutFeatureRowBinding) bind(obj, view, C1057R.layout.layout_feature_row);
    }
}
