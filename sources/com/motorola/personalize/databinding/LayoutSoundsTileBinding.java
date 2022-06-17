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

public abstract class LayoutSoundsTileBinding extends ViewDataBinding {
    public final TextView description;
    public final ConstraintLayout featureLayout;
    public final ImageView image;
    public final TextView title;

    protected LayoutSoundsTileBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, ImageView imageView, TextView textView2) {
        super(obj, view, i);
        this.description = textView;
        this.featureLayout = constraintLayout;
        this.image = imageView;
        this.title = textView2;
    }

    public static LayoutSoundsTileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSoundsTileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutSoundsTileBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_sounds_tile, viewGroup, z, obj);
    }

    public static LayoutSoundsTileBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSoundsTileBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutSoundsTileBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.layout_sounds_tile, (ViewGroup) null, false, obj);
    }

    public static LayoutSoundsTileBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutSoundsTileBinding bind(View view, Object obj) {
        return (LayoutSoundsTileBinding) bind(obj, view, C1057R.layout.layout_sounds_tile);
    }
}
