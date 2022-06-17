package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FeatureViewData;

public abstract class LayoutFeatureBinding extends ViewDataBinding {
    public final ImageView featureIconBackground;
    public final ImageView featureIconForeground;
    public final TextView featureTitle;
    public final Guideline guideline5PercentVertical;
    public final Guideline guideline95PercentVertical;
    @Bindable
    protected FeatureViewData mFeatureItem;

    public abstract void setFeatureItem(FeatureViewData featureViewData);

    protected LayoutFeatureBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, Guideline guideline, Guideline guideline2) {
        super(obj, view, i);
        this.featureIconBackground = imageView;
        this.featureIconForeground = imageView2;
        this.featureTitle = textView;
        this.guideline5PercentVertical = guideline;
        this.guideline95PercentVertical = guideline2;
    }

    public FeatureViewData getFeatureItem() {
        return this.mFeatureItem;
    }

    public static LayoutFeatureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutFeatureBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_feature, viewGroup, z, obj);
    }

    public static LayoutFeatureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutFeatureBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_feature, (ViewGroup) null, false, obj);
    }

    public static LayoutFeatureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureBinding bind(View view, Object obj) {
        return (LayoutFeatureBinding) bind(obj, view, C1057R.layout.layout_feature);
    }
}
