package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.motorola.personalize.C1057R;

public abstract class FragmentThemeBinding extends ViewDataBinding {
    public final FrameLayout darkPreview;
    public final FloatingActionButton fab;
    public final Guideline guideline0;
    public final Guideline guideline1;
    public final Guideline guideline2;
    public final FrameLayout lightPreview;
    public final RecyclerView optionList;
    public final RelativeLayout optionListContainer;
    public final RelativeLayout preview;
    public final TextView themeDesc;
    public final FeatureTopBarBinding toolbarContainer;

    protected FragmentThemeBinding(Object obj, View view, int i, FrameLayout frameLayout, FloatingActionButton floatingActionButton, Guideline guideline, Guideline guideline3, Guideline guideline4, FrameLayout frameLayout2, RecyclerView recyclerView, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, FeatureTopBarBinding featureTopBarBinding) {
        super(obj, view, i);
        this.darkPreview = frameLayout;
        this.fab = floatingActionButton;
        this.guideline0 = guideline;
        this.guideline1 = guideline3;
        this.guideline2 = guideline4;
        this.lightPreview = frameLayout2;
        this.optionList = recyclerView;
        this.optionListContainer = relativeLayout;
        this.preview = relativeLayout2;
        this.themeDesc = textView;
        this.toolbarContainer = featureTopBarBinding;
    }

    public static FragmentThemeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentThemeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentThemeBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.fragment_theme, viewGroup, z, obj);
    }

    public static FragmentThemeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentThemeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentThemeBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.fragment_theme, (ViewGroup) null, false, obj);
    }

    public static FragmentThemeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentThemeBinding bind(View view, Object obj) {
        return (FragmentThemeBinding) bind(obj, view, C1057R.layout.fragment_theme);
    }
}
