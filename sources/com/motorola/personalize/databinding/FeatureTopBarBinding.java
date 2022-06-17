package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.motorola.personalize.C1057R;

public abstract class FeatureTopBarBinding extends ViewDataBinding {
    public final ImageView backIcon;
    public final ConstraintLayout toolbarLayout;
    public final TextView toolbarTitle;

    protected FeatureTopBarBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView) {
        super(obj, view, i);
        this.backIcon = imageView;
        this.toolbarLayout = constraintLayout;
        this.toolbarTitle = textView;
    }

    public static FeatureTopBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FeatureTopBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FeatureTopBarBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.feature_top_bar, viewGroup, z, obj);
    }

    public static FeatureTopBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FeatureTopBarBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FeatureTopBarBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.feature_top_bar, (ViewGroup) null, false, obj);
    }

    public static FeatureTopBarBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FeatureTopBarBinding bind(View view, Object obj) {
        return (FeatureTopBarBinding) bind(obj, view, C1057R.layout.feature_top_bar);
    }
}
