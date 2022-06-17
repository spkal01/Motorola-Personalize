package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FamilyData;

public abstract class ToolbarFamilySectionBinding extends ViewDataBinding {
    public final Toolbar detailFeatureFamilyToolbar;
    @Bindable
    protected FamilyData mFamilyData;
    public final TextView toolbarDescription;
    public final ConstraintLayout toolbarLayout;
    public final TextView toolbarTitle;

    public abstract void setFamilyData(FamilyData familyData);

    protected ToolbarFamilySectionBinding(Object obj, View view, int i, Toolbar toolbar, TextView textView, ConstraintLayout constraintLayout, TextView textView2) {
        super(obj, view, i);
        this.detailFeatureFamilyToolbar = toolbar;
        this.toolbarDescription = textView;
        this.toolbarLayout = constraintLayout;
        this.toolbarTitle = textView2;
    }

    public FamilyData getFamilyData() {
        return this.mFamilyData;
    }

    public static ToolbarFamilySectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarFamilySectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ToolbarFamilySectionBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.toolbar_family_section, viewGroup, z, obj);
    }

    public static ToolbarFamilySectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarFamilySectionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ToolbarFamilySectionBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.toolbar_family_section, (ViewGroup) null, false, obj);
    }

    public static ToolbarFamilySectionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarFamilySectionBinding bind(View view, Object obj) {
        return (ToolbarFamilySectionBinding) bind(obj, view, C1057R.layout.toolbar_family_section);
    }
}
