package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;

public abstract class FragmentSoundBinding extends ViewDataBinding {
    public final RecyclerView functionList;
    public final FeatureTopBarBinding toolbarContainer;

    protected FragmentSoundBinding(Object obj, View view, int i, RecyclerView recyclerView, FeatureTopBarBinding featureTopBarBinding) {
        super(obj, view, i);
        this.functionList = recyclerView;
        this.toolbarContainer = featureTopBarBinding;
    }

    public static FragmentSoundBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSoundBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentSoundBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.fragment_sound, viewGroup, z, obj);
    }

    public static FragmentSoundBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSoundBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentSoundBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.fragment_sound, (ViewGroup) null, false, obj);
    }

    public static FragmentSoundBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSoundBinding bind(View view, Object obj) {
        return (FragmentSoundBinding) bind(obj, view, C1057R.layout.fragment_sound);
    }
}
