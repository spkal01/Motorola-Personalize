package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FeatureTileViewData;

public abstract class LayoutFeatureTileBinding extends ViewDataBinding {
    public final TextView featureDescription;
    public final ImageView featureImage;
    public final CardView featureImageCard;
    public final ConstraintLayout featureLayout;
    public final TextView featureTitle;
    @Bindable
    protected FeatureTileViewData mFeatureItem;

    public abstract void setFeatureItem(FeatureTileViewData featureTileViewData);

    protected LayoutFeatureTileBinding(Object obj, View view, int i, TextView textView, ImageView imageView, CardView cardView, ConstraintLayout constraintLayout, TextView textView2) {
        super(obj, view, i);
        this.featureDescription = textView;
        this.featureImage = imageView;
        this.featureImageCard = cardView;
        this.featureLayout = constraintLayout;
        this.featureTitle = textView2;
    }

    public FeatureTileViewData getFeatureItem() {
        return this.mFeatureItem;
    }

    public static LayoutFeatureTileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureTileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutFeatureTileBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_feature_tile, viewGroup, z, obj);
    }

    public static LayoutFeatureTileBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureTileBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutFeatureTileBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_feature_tile, (ViewGroup) null, false, obj);
    }

    public static LayoutFeatureTileBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutFeatureTileBinding bind(View view, Object obj) {
        return (LayoutFeatureTileBinding) bind(obj, view, C1057R.layout.layout_feature_tile);
    }
}
