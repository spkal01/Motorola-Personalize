package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FamilyData;

public abstract class ActivityLayoutMainBinding extends ViewDataBinding {
    public final RecyclerView featureList;
    @Bindable
    protected FamilyData mFamilyData;
    public final ToolbarFamilySectionBinding toolbarContainer;

    public abstract void setFamilyData(FamilyData familyData);

    protected ActivityLayoutMainBinding(Object obj, View view, int i, RecyclerView recyclerView, ToolbarFamilySectionBinding toolbarFamilySectionBinding) {
        super(obj, view, i);
        this.featureList = recyclerView;
        this.toolbarContainer = toolbarFamilySectionBinding;
    }

    public FamilyData getFamilyData() {
        return this.mFamilyData;
    }

    public static ActivityLayoutMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLayoutMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityLayoutMainBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.activity_layout_main, viewGroup, z, obj);
    }

    public static ActivityLayoutMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLayoutMainBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityLayoutMainBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.activity_layout_main, (ViewGroup) null, false, obj);
    }

    public static ActivityLayoutMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLayoutMainBinding bind(View view, Object obj) {
        return (ActivityLayoutMainBinding) bind(obj, view, C1057R.layout.activity_layout_main);
    }
}
