package com.motorola.personalize.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.view.FreeCardView;
import com.motorola.personalize.view.PaletteLayout;

public abstract class FragmentFeatureBinding extends ViewDataBinding {
    public final ImageView colorSourceIcon;
    public final LinearLayout colorSourceLayout;
    public final TextView colorSourceText;
    public final ConstraintLayout dynamic;
    public final ImageView eyePreview;
    public final FloatingActionButton fab;
    public final LinearLayout gridPreviewSurfaceContainer;
    public final Guideline guideline0;
    public final Guideline guideline1;
    public final Guideline guideline4;
    public final RecyclerView optionList;
    public final RelativeLayout optionListContainer;
    public final PaletteLayout paletteContainer;
    public final FrameLayout preview;
    public final FreeCardView previewDynamicArea;
    public final CardView previewFixArea;
    public final FrameLayout tipArea;
    public final FeatureTopBarBinding toolbarContainer;
    public final ImageView wallpaperBg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentFeatureBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, TextView textView, ConstraintLayout constraintLayout, ImageView imageView2, FloatingActionButton floatingActionButton, LinearLayout linearLayout2, Guideline guideline, Guideline guideline2, Guideline guideline3, RecyclerView recyclerView, RelativeLayout relativeLayout, PaletteLayout paletteLayout, FrameLayout frameLayout, FreeCardView freeCardView, CardView cardView, FrameLayout frameLayout2, FeatureTopBarBinding featureTopBarBinding, ImageView imageView3) {
        super(obj, view, i);
        this.colorSourceIcon = imageView;
        this.colorSourceLayout = linearLayout;
        this.colorSourceText = textView;
        this.dynamic = constraintLayout;
        this.eyePreview = imageView2;
        this.fab = floatingActionButton;
        this.gridPreviewSurfaceContainer = linearLayout2;
        this.guideline0 = guideline;
        this.guideline1 = guideline2;
        this.guideline4 = guideline3;
        this.optionList = recyclerView;
        this.optionListContainer = relativeLayout;
        this.paletteContainer = paletteLayout;
        this.preview = frameLayout;
        this.previewDynamicArea = freeCardView;
        this.previewFixArea = cardView;
        this.tipArea = frameLayout2;
        this.toolbarContainer = featureTopBarBinding;
        this.wallpaperBg = imageView3;
    }

    public static FragmentFeatureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFeatureBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentFeatureBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.fragment_feature, viewGroup, z, obj);
    }

    public static FragmentFeatureBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFeatureBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentFeatureBinding) ViewDataBinding.inflateInternal(layoutInflater, C1057R.layout.fragment_feature, (ViewGroup) null, false, obj);
    }

    public static FragmentFeatureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFeatureBinding bind(View view, Object obj) {
        return (FragmentFeatureBinding) bind(obj, view, C1057R.layout.fragment_feature);
    }
}
