package com.motorola.personalize.app;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.databinding.FeatureTopBarBinding;
import com.motorola.personalize.databinding.FragmentFeatureBinding;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.OrientationExtensionsKt;
import com.motorola.personalize.loader.WallpaperLoader;
import com.motorola.personalize.model.ColorOption;
import com.motorola.personalize.model.Style;
import com.motorola.personalize.model.StyleSettings;
import com.motorola.personalize.model.StylesViewTool;
import com.motorola.personalize.util.SPUtils;
import com.motorola.personalize.util.Utilities;
import com.motorola.personalize.view.AnimationSurfaceView;
import com.motorola.personalize.view.CenterLayoutManager;
import com.motorola.personalize.view.FreeCardView;
import com.motorola.personalize.view.PaletteLayout;
import com.motorola.personalize.view.StylePreview;
import com.motorola.personalize.view.StyleSettingAdapter;
import com.motorola.personalize.viewmodel.StyleViewModel;
import com.motorola.styles.model.OnlineFontService;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.Result;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(mo15461d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 N2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001NB\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0013H\u0002J\b\u0010$\u001a\u00020\"H\u0016J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020\"H\u0002J\b\u0010)\u001a\u00020\"H\u0002J\b\u0010*\u001a\u00020\"H\u0002J\u0012\u0010+\u001a\u00020\"2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0012\u0010.\u001a\u00020\"2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J&\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u0001042\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u00105\u001a\u00020\"2\u0006\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u00020\"H\u0016J\b\u00109\u001a\u00020\"H\u0002J\b\u0010:\u001a\u00020\"H\u0016J\u0010\u0010;\u001a\u00020\"2\u0006\u00106\u001a\u000207H\u0016J\b\u0010<\u001a\u00020\"H\u0016J\u0018\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u0002002\u0006\u0010?\u001a\u00020@H\u0002J\b\u0010A\u001a\u00020\"H\u0016J(\u0010B\u001a\u00020\u00132\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u0002072\u0006\u0010F\u001a\u0002072\u0006\u0010G\u001a\u00020\u0013H\u0003J\u001a\u0010H\u001a\u00020\"2\u0006\u0010>\u001a\u0002002\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010I\u001a\u00020\"H\u0002J\b\u0010J\u001a\u00020\"H\u0002J\b\u0010K\u001a\u00020\"H\u0002J\b\u0010L\u001a\u00020\"H\u0002J\u0010\u0010M\u001a\u00020\"2\u0006\u00106\u001a\u000207H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\rXD¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\rXD¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, mo15462d2 = {"Lcom/motorola/personalize/app/StyleFragment;", "Lcom/motorola/personalize/app/ParentFragment;", "Lcom/motorola/styles/model/OnlineFontService$OnlineFontUpdateListener;", "Lcom/motorola/personalize/view/PaletteLayout$CustomColorListener;", "()V", "adapter", "Lcom/motorola/personalize/view/StyleSettingAdapter;", "animEndCallback", "Ljava/lang/Runnable;", "animStartCallback", "animator", "Landroid/animation/Animator;", "animatorStartTimeMillis", "", "binding", "Lcom/motorola/personalize/databinding/FragmentFeatureBinding;", "colorPreview", "Lcom/motorola/personalize/view/StylePreview;", "isGridSetting", "", "isLongPressToPreviewing", "isTipAnimating", "mGridPreview", "Lcom/motorola/personalize/view/AnimationSurfaceView;", "mStyleSettings", "Lcom/motorola/personalize/model/StyleSettings;", "mViewModel", "Lcom/motorola/personalize/viewmodel/StyleViewModel;", "maxRadius", "", "previewAnimatorDelay", "previewAnimatorDuration", "showGridTip", "adjustCardView", "", "cornerBig", "adjustInsets", "attachSnapHelper", "getLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "goToLeStore", "linkStyleSettings", "linkStyleSettingsPreview", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCustomColorChanged", "color", "", "onDestroy", "onFabClick", "onOnlineFontUpdated", "onPaletteAdd", "onPaletteCancel", "onPreviewClick", "view", "motionEvent", "Landroid/view/MotionEvent;", "onResume", "onStyleOptionClick", "option", "Lcom/motorola/styles/model/Option;", "index", "size", "force", "onViewCreated", "refreshRealPreview", "setUpColorOptionsViewIfNeed", "setupPreviewButton", "setupPreviews", "setupToolbar", "Companion", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: StyleFragment.kt */
public final class StyleFragment extends ParentFragment implements OnlineFontService.OnlineFontUpdateListener, PaletteLayout.CustomColorListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "StyleFragment";
    /* access modifiers changed from: private */
    public StyleSettingAdapter adapter;
    private final Runnable animEndCallback = new Runnable() {
        public final void run() {
            StyleFragment.m69animEndCallback$lambda1(StyleFragment.this);
        }
    };
    private final Runnable animStartCallback = new Runnable() {
        public final void run() {
            StyleFragment.m70animStartCallback$lambda0(StyleFragment.this);
        }
    };
    private Animator animator;
    private long animatorStartTimeMillis = -1;
    /* access modifiers changed from: private */
    public FragmentFeatureBinding binding;
    private StylePreview colorPreview;
    private boolean isGridSetting;
    private boolean isLongPressToPreviewing;
    private boolean isTipAnimating;
    private AnimationSurfaceView mGridPreview;
    private StyleSettings mStyleSettings;
    private StyleViewModel mViewModel;
    private float maxRadius;
    private final long previewAnimatorDelay = 300;
    private final long previewAnimatorDuration = 500;
    private boolean showGridTip;

    private final void attachSnapHelper() {
    }

    /* access modifiers changed from: private */
    /* renamed from: animStartCallback$lambda-0  reason: not valid java name */
    public static final void m70animStartCallback$lambda0(StyleFragment styleFragment) {
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        FragmentFeatureBinding fragmentFeatureBinding = styleFragment.binding;
        if (fragmentFeatureBinding != null && (recyclerView = fragmentFeatureBinding.optionList) != null) {
            recyclerView.smoothScrollToPosition(1);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: animEndCallback$lambda-1  reason: not valid java name */
    public static final void m69animEndCallback$lambda1(StyleFragment styleFragment) {
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        FragmentFeatureBinding fragmentFeatureBinding = styleFragment.binding;
        if (fragmentFeatureBinding != null && (recyclerView = fragmentFeatureBinding.optionList) != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Intrinsics.areEqual((Object) getFeatureId(), (Object) "personalize_grid")) {
            this.isGridSetting = true;
            this.showGridTip = SPUtils.showGridTipNeeded(requireContext());
        }
    }

    public void onResume() {
        ImageView imageView;
        super.onResume();
        boolean isColorChanged = WallpaperLoader.getInstance().isColorChanged();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("onResume isColorChanged = ", Boolean.valueOf(isColorChanged)));
        }
        LinearLayout linearLayout = null;
        if (isColorChanged) {
            FragmentFeatureBinding fragmentFeatureBinding = this.binding;
            if (!(fragmentFeatureBinding == null || (imageView = fragmentFeatureBinding.wallpaperBg) == null)) {
                imageView.setImageBitmap(WallpaperLoader.getInstance().loadWallpaper());
            }
            StyleViewModel styleViewModel = this.mViewModel;
            if (styleViewModel != null) {
                styleViewModel.setCustomColorUpdated();
                WallpaperLoader.getInstance().resetColorChangeStatus();
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                throw null;
            }
        }
        if (Intrinsics.areEqual((Object) getFeatureId(), (Object) "personalize_grid")) {
            refreshRealPreview();
        }
        this.isLongPressToPreviewing = false;
        FragmentFeatureBinding fragmentFeatureBinding2 = this.binding;
        if (fragmentFeatureBinding2 != null) {
            linearLayout = fragmentFeatureBinding2.gridPreviewSurfaceContainer;
        }
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onStyleOptionClick$lambda-4  reason: not valid java name */
    public static final void m83onStyleOptionClick$lambda4(Checkable checkable, View view) {
        Intrinsics.checkNotNullParameter(checkable, "$checkbox");
        checkable.toggle();
    }

    /* access modifiers changed from: private */
    /* renamed from: onStyleOptionClick$lambda-5  reason: not valid java name */
    public static final void m84onStyleOptionClick$lambda5(Checkable checkable, StyleFragment styleFragment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(checkable, "$checkbox");
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        if (checkable.isChecked()) {
            SPUtils.setShowGoToLeStoreTipNeeded(styleFragment.requireContext(), false);
        }
        styleFragment.goToLeStore();
    }

    private final void setupPreviews() {
        String featureId = getFeatureId();
        if (Intrinsics.areEqual((Object) featureId, (Object) "personalize_grid")) {
            adjustCardView(false);
        } else if (Intrinsics.areEqual((Object) featureId, (Object) "personalize_fonts")) {
            FragmentFeatureBinding fragmentFeatureBinding = this.binding;
            CardView cardView = fragmentFeatureBinding == null ? null : fragmentFeatureBinding.previewFixArea;
            if (cardView != null) {
                cardView.setVisibility(0);
            }
        } else {
            adjustCardView(true);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        ImageView imageView;
        FloatingActionButton floatingActionButton;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onViewCreated");
        }
        FragmentFeatureBinding fragmentFeatureBinding = this.binding;
        if (fragmentFeatureBinding != null) {
            fragmentFeatureBinding.setLifecycleOwner(this);
        }
        FragmentFeatureBinding fragmentFeatureBinding2 = this.binding;
        ImageView imageView2 = null;
        RecyclerView recyclerView = fragmentFeatureBinding2 == null ? null : fragmentFeatureBinding2.optionList;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(getLayoutManager());
        }
        FragmentFeatureBinding fragmentFeatureBinding3 = this.binding;
        if (!(fragmentFeatureBinding3 == null || (floatingActionButton = fragmentFeatureBinding3.fab) == null)) {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    StyleFragment.m85onViewCreated$lambda11(StyleFragment.this, view);
                }
            });
        }
        if (this.isGridSetting) {
            setupPreviewButton();
        } else {
            FragmentFeatureBinding fragmentFeatureBinding4 = this.binding;
            FrameLayout frameLayout = fragmentFeatureBinding4 == null ? null : fragmentFeatureBinding4.preview;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            FragmentFeatureBinding fragmentFeatureBinding5 = this.binding;
            if (fragmentFeatureBinding5 != null) {
                imageView2 = fragmentFeatureBinding5.eyePreview;
            }
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
        }
        setupPreviews();
        setUpColorOptionsViewIfNeed();
        FragmentFeatureBinding fragmentFeatureBinding6 = this.binding;
        if (fragmentFeatureBinding6 != null && (imageView = fragmentFeatureBinding6.wallpaperBg) != null) {
            imageView.setImageBitmap(WallpaperLoader.getInstance().loadWallpaper());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-11  reason: not valid java name */
    public static final void m85onViewCreated$lambda11(StyleFragment styleFragment, View view) {
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        Intrinsics.checkNotNullParameter(view, "$noName_0");
        styleFragment.onFabClick();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onActivityCreated");
        }
        OnlineFontService.getInstance().registerOnlineFontUpdateListener(this);
        Rect bounds = requireActivity().getWindowManager().getCurrentWindowMetrics().getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "requireActivity().windowManager.currentWindowMetrics.bounds");
        this.maxRadius = (float) Math.hypot((double) bounds.width(), (double) bounds.height());
        this.mViewModel = new StyleViewModel(requireActivity().getApplication());
        linkStyleSettings();
        linkStyleSettingsPreview();
        StyleViewModel styleViewModel = this.mViewModel;
        if (styleViewModel != null) {
            Utilities.observeOnce(styleViewModel.onLoadSystemAppliedStyle(), getViewLifecycleOwner(), new Observer() {
                public final void onChanged(Object obj) {
                    StyleFragment.m80onActivityCreated$lambda15(StyleFragment.this, (Style) obj);
                }
            });
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    public void onDestroy() {
        PaletteLayout paletteLayout;
        OnlineFontService.getInstance().unregisterOnlineFontUpdateListener(this);
        FragmentFeatureBinding fragmentFeatureBinding = this.binding;
        if (!(fragmentFeatureBinding == null || (paletteLayout = fragmentFeatureBinding.paletteContainer) == null)) {
            paletteLayout.unregisterColorListener();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    /* renamed from: linkStyleSettings$lambda-20$lambda-18  reason: not valid java name */
    public static final boolean m77linkStyleSettings$lambda20$lambda18(StyleFragment styleFragment, Option option, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        Intrinsics.checkNotNull(option);
        return styleFragment.onStyleOptionClick(option, i, i2, z);
    }

    /* access modifiers changed from: private */
    /* renamed from: linkStyleSettings$lambda-20$lambda-19  reason: not valid java name */
    public static final void m78linkStyleSettings$lambda20$lambda19(StyleFragment styleFragment) {
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        FragmentFeatureBinding fragmentFeatureBinding = styleFragment.binding;
        if (fragmentFeatureBinding != null && (recyclerView = fragmentFeatureBinding.optionList) != null) {
            StyleSettingAdapter styleSettingAdapter = styleFragment.adapter;
            Intrinsics.checkNotNull(styleSettingAdapter);
            recyclerView.smoothScrollToPosition(styleSettingAdapter.getSelectedIndex());
        }
    }

    private final void setupPreviewButton() {
        ImageView imageView;
        FragmentFeatureBinding fragmentFeatureBinding = this.binding;
        FrameLayout frameLayout = null;
        FrameLayout frameLayout2 = fragmentFeatureBinding == null ? null : fragmentFeatureBinding.preview;
        if (frameLayout2 != null) {
            frameLayout2.setVisibility(0);
        }
        FragmentFeatureBinding fragmentFeatureBinding2 = this.binding;
        if (!(fragmentFeatureBinding2 == null || (imageView = fragmentFeatureBinding2.eyePreview) == null)) {
            imageView.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return StyleFragment.this.onPreviewClick(view, motionEvent);
                }
            });
        }
        if (this.showGridTip) {
            FragmentFeatureBinding fragmentFeatureBinding3 = this.binding;
            FrameLayout frameLayout3 = fragmentFeatureBinding3 == null ? null : fragmentFeatureBinding3.tipArea;
            if (frameLayout3 != null) {
                frameLayout3.setVisibility(0);
            }
            LayoutInflater from = LayoutInflater.from(requireContext());
            FragmentFeatureBinding fragmentFeatureBinding4 = this.binding;
            if (fragmentFeatureBinding4 != null) {
                frameLayout = fragmentFeatureBinding4.tipArea;
            }
            ((ImageView) from.inflate(C1057R.layout.grid_preview_tip, frameLayout, true).findViewById(C1057R.C1060id.close_grid_tip)).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    StyleFragment.m86setupPreviewButton$lambda24(StyleFragment.this, view);
                }
            });
            return;
        }
        FragmentFeatureBinding fragmentFeatureBinding5 = this.binding;
        if (fragmentFeatureBinding5 != null) {
            frameLayout = fragmentFeatureBinding5.tipArea;
        }
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setupPreviewButton$lambda-24  reason: not valid java name */
    public static final void m86setupPreviewButton$lambda24(StyleFragment styleFragment, View view) {
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        SPUtils.setShowGridTipNeeded(styleFragment.requireContext(), false);
        FragmentFeatureBinding fragmentFeatureBinding = styleFragment.binding;
        FrameLayout frameLayout = fragmentFeatureBinding == null ? null : fragmentFeatureBinding.tipArea;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    private final void setUpColorOptionsViewIfNeed() {
        PaletteLayout paletteLayout;
        RecyclerView recyclerView;
        LinearLayout linearLayout = null;
        if (Intrinsics.areEqual((Object) getFeatureId(), (Object) "personalize_colors")) {
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = -1;
            Ref.IntRef intRef2 = new Ref.IntRef();
            intRef2.element = 2;
            FragmentFeatureBinding fragmentFeatureBinding = this.binding;
            if (!(fragmentFeatureBinding == null || (recyclerView = fragmentFeatureBinding.optionList) == null)) {
                recyclerView.addOnScrollListener(new StyleFragment$setUpColorOptionsViewIfNeed$1(intRef2, this, intRef));
            }
            FragmentFeatureBinding fragmentFeatureBinding2 = this.binding;
            if (fragmentFeatureBinding2 != null) {
                linearLayout = fragmentFeatureBinding2.colorSourceLayout;
            }
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            FragmentFeatureBinding fragmentFeatureBinding3 = this.binding;
            if (fragmentFeatureBinding3 != null && (paletteLayout = fragmentFeatureBinding3.paletteContainer) != null) {
                paletteLayout.registerColorListener(this);
                return;
            }
            return;
        }
        FragmentFeatureBinding fragmentFeatureBinding4 = this.binding;
        if (fragmentFeatureBinding4 != null) {
            linearLayout = fragmentFeatureBinding4.colorSourceLayout;
        }
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setupToolbar$lambda-26  reason: not valid java name */
    public static final void m87setupToolbar$lambda26(StyleFragment styleFragment, View view) {
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        FragmentActivity activity = styleFragment.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onFabClick$lambda-34  reason: not valid java name */
    public static final void m81onFabClick$lambda34(StyleFragment styleFragment, Result result) {
        FloatingActionButton floatingActionButton;
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        if (!result.containsError()) {
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(TAG, "onFabClick successful");
            }
            FragmentActivity requireActivity = styleFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FragmentFeatureBinding fragmentFeatureBinding = styleFragment.binding;
            if (fragmentFeatureBinding != null && (floatingActionButton = fragmentFeatureBinding.fab) != null) {
                floatingActionButton.postDelayed(new Runnable() {
                    public final void run() {
                        StyleFragment.m82onFabClick$lambda34$lambda33(FragmentActivity.this);
                    }
                }, 300);
            }
        } else if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onFabClick but something wrong");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onFabClick$lambda-34$lambda-33  reason: not valid java name */
    public static final void m82onFabClick$lambda34$lambda33(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        fragmentActivity.finish();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        if (r10 != 3) goto L_0x014d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onPreviewClick(android.view.View r9, android.view.MotionEvent r10) {
        /*
            r8 = this;
            int r10 = r10.getAction()
            r0 = 0
            r1 = 1
            java.lang.String r2 = "animator"
            r3 = 0
            if (r10 == 0) goto L_0x00d1
            if (r10 == r1) goto L_0x0016
            r4 = 2
            if (r10 == r4) goto L_0x0015
            r4 = 3
            if (r10 == r4) goto L_0x0016
            goto L_0x014d
        L_0x0015:
            return r1
        L_0x0016:
            r8.isLongPressToPreviewing = r0
            android.graphics.Rect r10 = new android.graphics.Rect
            r10.<init>()
            r9.getGlobalVisibleRect(r10)
            float r9 = r8.maxRadius
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r8.animatorStartTimeMillis
            long r4 = r4 - r6
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x003b
            android.animation.Animator r8 = r8.animator
            if (r8 == 0) goto L_0x0037
            r8.cancel()
            return r1
        L_0x0037:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x003b:
            android.animation.Animator r0 = r8.animator
            if (r0 == 0) goto L_0x00cd
            boolean r0 = r0.isRunning()
            if (r0 == 0) goto L_0x0068
            com.motorola.personalize.extensions.Logger r9 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r9 = r9.getDEBUG()
            if (r9 == 0) goto L_0x0054
            java.lang.String r9 = "StyleFragment"
            java.lang.String r0 = "onPreviewClick: up, isRunning"
            android.util.Log.d(r9, r0)
        L_0x0054:
            android.animation.Animator r9 = r8.animator
            if (r9 == 0) goto L_0x0064
            r9.cancel()
            float r9 = r8.maxRadius
            float r0 = (float) r4
            float r9 = r9 * r0
            long r6 = r8.previewAnimatorDuration
            float r0 = (float) r6
            float r9 = r9 / r0
            goto L_0x006a
        L_0x0064:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x0068:
            long r4 = r8.previewAnimatorDuration
        L_0x006a:
            com.motorola.personalize.databinding.FragmentFeatureBinding r0 = r8.binding
            if (r0 != 0) goto L_0x0070
            r0 = r3
            goto L_0x0072
        L_0x0070:
            android.widget.LinearLayout r0 = r0.gridPreviewSurfaceContainer
        L_0x0072:
            android.view.View r0 = (android.view.View) r0
            int r6 = r10.centerX()
            int r10 = r10.centerY()
            r7 = 1092616192(0x41200000, float:10.0)
            android.animation.Animator r9 = android.view.ViewAnimationUtils.createCircularReveal(r0, r6, r10, r9, r7)
            android.animation.Animator r9 = r9.setDuration(r4)
            java.lang.String r10 = "createCircularReveal(binding?.gridPreviewSurfaceContainer,\n                        rect.centerX(), rect.centerY(),\n                        radius, 10f).setDuration(time)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            r8.animator = r9
            if (r9 == 0) goto L_0x00c9
            com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnStart$2 r10 = new com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnStart$2
            r10.<init>(r8)
            android.animation.Animator$AnimatorListener r10 = (android.animation.Animator.AnimatorListener) r10
            r9.addListener(r10)
            android.animation.Animator r9 = r8.animator
            if (r9 == 0) goto L_0x00c5
            com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnCancel$2 r10 = new com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnCancel$2
            r10.<init>(r8)
            android.animation.Animator$AnimatorListener r10 = (android.animation.Animator.AnimatorListener) r10
            r9.addListener(r10)
            android.animation.Animator r9 = r8.animator
            if (r9 == 0) goto L_0x00c1
            com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnEnd$1 r10 = new com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnEnd$1
            r10.<init>(r8)
            android.animation.Animator$AnimatorListener r10 = (android.animation.Animator.AnimatorListener) r10
            r9.addListener(r10)
            android.animation.Animator r8 = r8.animator
            if (r8 == 0) goto L_0x00bd
            r8.start()
            return r1
        L_0x00bd:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x00c1:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x00c5:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x00c9:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x00cd:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x00d1:
            r8.isLongPressToPreviewing = r1
            android.animation.Animator r10 = r8.animator
            if (r10 == 0) goto L_0x00e4
            if (r10 == 0) goto L_0x00e0
            boolean r10 = r10.isRunning()
            if (r10 == 0) goto L_0x00e4
            return r1
        L_0x00e0:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x00e4:
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r8.previewAnimatorDelay
            long r4 = r4 + r6
            r8.animatorStartTimeMillis = r4
            android.graphics.Rect r10 = new android.graphics.Rect
            r10.<init>()
            r9.getGlobalVisibleRect(r10)
            com.motorola.personalize.databinding.FragmentFeatureBinding r9 = r8.binding
            if (r9 != 0) goto L_0x00fb
            r9 = r3
            goto L_0x00fd
        L_0x00fb:
            android.widget.LinearLayout r9 = r9.gridPreviewSurfaceContainer
        L_0x00fd:
            android.view.View r9 = (android.view.View) r9
            int r1 = r10.centerX()
            int r10 = r10.centerY()
            r4 = 1065353216(0x3f800000, float:1.0)
            float r5 = r8.maxRadius
            android.animation.Animator r9 = android.view.ViewAnimationUtils.createCircularReveal(r9, r1, r10, r4, r5)
            long r4 = r8.previewAnimatorDuration
            android.animation.Animator r9 = r9.setDuration(r4)
            java.lang.String r10 = "createCircularReveal(binding?.gridPreviewSurfaceContainer,\n                        rect.centerX(), rect.centerY(),\n                        1f, maxRadius).setDuration(previewAnimatorDuration)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            r8.animator = r9
            if (r9 == 0) goto L_0x015a
            long r4 = r8.previewAnimatorDelay
            r9.setStartDelay(r4)
            android.animation.Animator r9 = r8.animator
            if (r9 == 0) goto L_0x0156
            com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnStart$1 r10 = new com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnStart$1
            r10.<init>(r8)
            android.animation.Animator$AnimatorListener r10 = (android.animation.Animator.AnimatorListener) r10
            r9.addListener(r10)
            android.animation.Animator r9 = r8.animator
            if (r9 == 0) goto L_0x0152
            com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnCancel$1 r10 = new com.motorola.personalize.app.StyleFragment$onPreviewClick$$inlined$doOnCancel$1
            r10.<init>(r8)
            android.animation.Animator$AnimatorListener r10 = (android.animation.Animator.AnimatorListener) r10
            r9.addListener(r10)
            android.animation.Animator r9 = r8.animator
            if (r9 == 0) goto L_0x014e
            r9.start()
            android.content.Context r8 = r8.getContext()
            com.motorola.personalize.analysis.PersonalizeMetrics.useEyeButton(r8)
        L_0x014d:
            return r0
        L_0x014e:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x0152:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x0156:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        L_0x015a:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.StyleFragment.onPreviewClick(android.view.View, android.view.MotionEvent):boolean");
    }

    private final void goToLeStore() {
        Intent intent = new Intent();
        intent.setDataAndNormalize(Uri.parse("leapp://ptn/speciallist.do?type=subject&code=24580&name=字体专区&backmain=false"));
        startActivity(intent);
    }

    public void onCustomColorChanged(int i) {
        StylePreview stylePreview = this.colorPreview;
        if (stylePreview == null) {
            return;
        }
        if (stylePreview != null) {
            stylePreview.refreshColor(i);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("colorPreview");
            throw null;
        }
    }

    public void onPaletteAdd(int i) {
        StyleSettingAdapter styleSettingAdapter = this.adapter;
        if (styleSettingAdapter != null) {
            StyleViewModel styleViewModel = this.mViewModel;
            if (styleViewModel != null) {
                styleSettingAdapter.insertColor(styleViewModel.getStylesManager().getColorOptionsProvider().onCustomColorAdd(i));
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                throw null;
            }
        }
    }

    public void onPaletteCancel() {
        FragmentFeatureBinding fragmentFeatureBinding = this.binding;
        RelativeLayout relativeLayout = fragmentFeatureBinding == null ? null : fragmentFeatureBinding.optionListContainer;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
        FragmentFeatureBinding fragmentFeatureBinding2 = this.binding;
        PaletteLayout paletteLayout = fragmentFeatureBinding2 == null ? null : fragmentFeatureBinding2.paletteContainer;
        if (paletteLayout != null) {
            paletteLayout.setVisibility(4);
        }
        FragmentFeatureBinding fragmentFeatureBinding3 = this.binding;
        LinearLayout linearLayout = fragmentFeatureBinding3 == null ? null : fragmentFeatureBinding3.colorSourceLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        if (this.colorPreview != null) {
            StyleSettingAdapter styleSettingAdapter = this.adapter;
            Option selectedOption = styleSettingAdapter == null ? null : styleSettingAdapter.getSelectedOption();
            Objects.requireNonNull(selectedOption, "null cannot be cast to non-null type com.motorola.personalize.model.ColorOption");
            ColorOption colorOption = (ColorOption) selectedOption;
            StylePreview stylePreview = this.colorPreview;
            if (stylePreview != null) {
                stylePreview.refreshColor(colorOption.getLightColor());
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("colorPreview");
                throw null;
            }
        }
    }

    @Metadata(mo15461d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo15462d2 = {"Lcom/motorola/personalize/app/StyleFragment$Companion;", "", "()V", "TAG", "", "newInstance", "Landroidx/fragment/app/Fragment;", "extras", "Landroid/os/Bundle;", "newInstance$app_release", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: StyleFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Fragment newInstance$app_release(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "extras");
            StyleFragment styleFragment = new StyleFragment();
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(StyleFragment.TAG, "new instance");
            }
            styleFragment.setArguments(bundle);
            return styleFragment;
        }
    }

    private final boolean onStyleOptionClick(Option option, int i, int i2, boolean z) {
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        FragmentFeatureBinding fragmentFeatureBinding;
        RecyclerView recyclerView3;
        FragmentFeatureBinding fragmentFeatureBinding2;
        PaletteLayout paletteLayout;
        RecyclerView recyclerView4;
        RecyclerView recyclerView5;
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("onStyleOptionClick ", Integer.valueOf(i)));
        }
        if (this.isLongPressToPreviewing) {
            return false;
        }
        if (this.isTipAnimating) {
            FragmentFeatureBinding fragmentFeatureBinding3 = this.binding;
            if (!(fragmentFeatureBinding3 == null || (recyclerView5 = fragmentFeatureBinding3.optionList) == null)) {
                recyclerView5.removeCallbacks(this.animStartCallback);
            }
            FragmentFeatureBinding fragmentFeatureBinding4 = this.binding;
            if (!(fragmentFeatureBinding4 == null || (recyclerView4 = fragmentFeatureBinding4.optionList) == null)) {
                recyclerView4.removeCallbacks(this.animEndCallback);
            }
        }
        StyleSettings styleSettings = this.mStyleSettings;
        Intrinsics.checkNotNull(styleSettings);
        String currentSetting = styleSettings.getCurrentSetting();
        if (StyleSettings.isFont(currentSetting)) {
            if (option.isPlaceHolder()) {
                if (SPUtils.showGoToLeStoreTipNeeded(requireContext())) {
                    View inflate = LayoutInflater.from(requireContext()).inflate(C1057R.layout.font_go_to_lestore_dialog, (ViewGroup) null);
                    View findViewById = inflate.findViewById(C1057R.C1060id.checkbox);
                    Intrinsics.checkNotNullExpressionValue(findViewById, "dialogView.findViewById(R.id.checkbox)");
                    Checkable checkable = (Checkable) findViewById;
                    inflate.findViewById(C1057R.C1060id.checkboxContainer).setOnClickListener(new View.OnClickListener(checkable) {
                        public final /* synthetic */ Checkable f$0;

                        {
                            this.f$0 = r1;
                        }

                        public final void onClick(View view) {
                            StyleFragment.m83onStyleOptionClick$lambda4(this.f$0, view);
                        }
                    });
                    new AlertDialog.Builder(requireContext(), C1057R.style.DialogTheme).setView(inflate).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(C1057R.string.go_lestore_confirmed, new DialogInterface.OnClickListener(checkable, this) {
                        public final /* synthetic */ Checkable f$0;
                        public final /* synthetic */ StyleFragment f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            StyleFragment.m84onStyleOptionClick$lambda5(this.f$0, this.f$1, dialogInterface, i);
                        }
                    }).show();
                } else {
                    goToLeStore();
                }
                return false;
            }
            StyleSettings styleSettings2 = this.mStyleSettings;
            Intrinsics.checkNotNull(styleSettings2);
            styleSettings2.setFont(option.getValue());
        } else if (StyleSettings.isColor(currentSetting)) {
            if (option instanceof ColorOption) {
                ColorOption colorOption = (ColorOption) option;
                if (colorOption.getType() == 1) {
                    StyleSettingAdapter styleSettingAdapter = this.adapter;
                    if ((styleSettingAdapter == null ? null : Integer.valueOf(styleSettingAdapter.getItemCount())) == null) {
                        return false;
                    }
                    StyleSettingAdapter styleSettingAdapter2 = this.adapter;
                    Integer valueOf = styleSettingAdapter2 == null ? null : Integer.valueOf(styleSettingAdapter2.getItemCount());
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.intValue() >= 20) {
                        Toast.makeText(requireContext(), C1057R.string.max_colors_tip, 0).show();
                        return false;
                    }
                    FragmentFeatureBinding fragmentFeatureBinding5 = this.binding;
                    RelativeLayout relativeLayout = fragmentFeatureBinding5 == null ? null : fragmentFeatureBinding5.optionListContainer;
                    if (relativeLayout != null) {
                        relativeLayout.setVisibility(4);
                    }
                    FragmentFeatureBinding fragmentFeatureBinding6 = this.binding;
                    PaletteLayout paletteLayout2 = fragmentFeatureBinding6 == null ? null : fragmentFeatureBinding6.paletteContainer;
                    if (paletteLayout2 != null) {
                        paletteLayout2.setVisibility(0);
                    }
                    FragmentFeatureBinding fragmentFeatureBinding7 = this.binding;
                    LinearLayout linearLayout = fragmentFeatureBinding7 == null ? null : fragmentFeatureBinding7.colorSourceLayout;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(4);
                    }
                    if (!(this.colorPreview == null || (fragmentFeatureBinding2 = this.binding) == null || (paletteLayout = fragmentFeatureBinding2.paletteContainer) == null)) {
                        int currentColor = paletteLayout.getCurrentColor();
                        StylePreview stylePreview = this.colorPreview;
                        if (stylePreview != null) {
                            stylePreview.refreshColor(currentColor);
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("colorPreview");
                            throw null;
                        }
                    }
                    return false;
                }
                Log.d(TAG, "onStyleOptionClick: " + option + ".value");
                StyleSettings styleSettings3 = this.mStyleSettings;
                Intrinsics.checkNotNull(styleSettings3);
                styleSettings3.setColor(colorOption.getValue());
            }
        } else if (StyleSettings.isShape(currentSetting)) {
            StyleSettings styleSettings4 = this.mStyleSettings;
            Intrinsics.checkNotNull(styleSettings4);
            styleSettings4.setShape(option.getValue());
        } else if (StyleSettings.isGrid(currentSetting)) {
            StyleSettings styleSettings5 = this.mStyleSettings;
            Intrinsics.checkNotNull(styleSettings5);
            styleSettings5.setGrid(option.getValue());
        }
        if (!(z || (fragmentFeatureBinding = this.binding) == null || (recyclerView3 = fragmentFeatureBinding.optionList) == null)) {
            recyclerView3.smoothScrollToPosition(i);
        }
        if (z && i == 0 && i2 >= 4) {
            this.isTipAnimating = true;
            FragmentFeatureBinding fragmentFeatureBinding8 = this.binding;
            if (!(fragmentFeatureBinding8 == null || (recyclerView2 = fragmentFeatureBinding8.optionList) == null)) {
                recyclerView2.postDelayed(this.animStartCallback, 300);
            }
            FragmentFeatureBinding fragmentFeatureBinding9 = this.binding;
            if (!(fragmentFeatureBinding9 == null || (recyclerView = fragmentFeatureBinding9.optionList) == null)) {
                recyclerView.postDelayed(this.animEndCallback, 800);
            }
        }
        StyleViewModel styleViewModel = this.mViewModel;
        if (styleViewModel != null) {
            styleViewModel.onLoadStyleSettingsPreview(this.mStyleSettings);
            return true;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        r2 = r2.getCurrentWindowMetrics();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void refreshRealPreview() {
        /*
            r7 = this;
            com.motorola.personalize.extensions.Logger r0 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r0 = r0.getDEBUG()
            java.lang.String r1 = "StyleFragment"
            if (r0 == 0) goto L_0x000f
            java.lang.String r0 = "refreshRealPreview: "
            android.util.Log.d(r1, r0)
        L_0x000f:
            android.content.Context r0 = r7.requireContext()
            androidx.fragment.app.FragmentActivity r2 = r7.requireActivity()
            android.view.WindowManager r2 = r2.getWindowManager()
            r3 = 0
            if (r2 != 0) goto L_0x0020
        L_0x001e:
            r2 = r3
            goto L_0x002b
        L_0x0020:
            android.view.WindowMetrics r2 = r2.getCurrentWindowMetrics()
            if (r2 != 0) goto L_0x0027
            goto L_0x001e
        L_0x0027:
            android.graphics.Rect r2 = r2.getBounds()
        L_0x002b:
            com.motorola.personalize.model.StyleSettings r4 = r7.mStyleSettings
            if (r4 != 0) goto L_0x0031
            r4 = r3
            goto L_0x0035
        L_0x0031:
            java.lang.String r4 = r4.getCurrentSettingValue()
        L_0x0035:
            android.view.SurfaceControlViewHost$SurfacePackage r0 = com.motorola.personalize.loader.LauncherPreviewLoader.getPreviewSurfacePackage(r0, r2, r4)
            com.motorola.personalize.view.AnimationSurfaceView r2 = new com.motorola.personalize.view.AnimationSurfaceView
            android.content.Context r4 = r7.requireContext()
            r2.<init>(r4)
            r7.mGridPreview = r2
            java.lang.String r4 = "mGridPreview"
            if (r2 == 0) goto L_0x00a9
            r2.init()
            com.motorola.personalize.databinding.FragmentFeatureBinding r2 = r7.binding
            if (r2 != 0) goto L_0x0050
            goto L_0x0058
        L_0x0050:
            android.widget.LinearLayout r2 = r2.gridPreviewSurfaceContainer
            if (r2 != 0) goto L_0x0055
            goto L_0x0058
        L_0x0055:
            r2.removeAllViews()
        L_0x0058:
            androidx.fragment.app.FragmentActivity r2 = r7.requireActivity()
            android.view.WindowManager r2 = r2.getWindowManager()
            android.view.WindowMetrics r2 = r2.getCurrentWindowMetrics()
            android.graphics.Rect r2 = r2.getBounds()
            java.lang.String r5 = "requireActivity().windowManager.currentWindowMetrics.bounds"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            android.view.ViewGroup$LayoutParams r5 = new android.view.ViewGroup$LayoutParams
            int r6 = r2.width()
            int r6 = r6 + -2
            int r2 = r2.height()
            int r2 = r2 + -1
            r5.<init>(r6, r2)
            com.motorola.personalize.databinding.FragmentFeatureBinding r2 = r7.binding
            if (r2 != 0) goto L_0x0083
            goto L_0x0091
        L_0x0083:
            android.widget.LinearLayout r2 = r2.gridPreviewSurfaceContainer
            if (r2 != 0) goto L_0x0088
            goto L_0x0091
        L_0x0088:
            com.motorola.personalize.view.AnimationSurfaceView r6 = r7.mGridPreview
            if (r6 == 0) goto L_0x00a5
            android.view.View r6 = (android.view.View) r6
            r2.addView(r6, r5)
        L_0x0091:
            if (r0 == 0) goto L_0x009f
            com.motorola.personalize.view.AnimationSurfaceView r7 = r7.mGridPreview
            if (r7 == 0) goto L_0x009b
            r7.setChildSurfacePackage(r0)
            goto L_0x00a4
        L_0x009b:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            throw r3
        L_0x009f:
            java.lang.String r7 = "refreshRealPreview: parcelable = null"
            android.util.Log.d(r1, r7)
        L_0x00a4:
            return
        L_0x00a5:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            throw r3
        L_0x00a9:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.StyleFragment.refreshRealPreview():void");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onCreateView");
        }
        FragmentFeatureBinding inflate = FragmentFeatureBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        if (inflate == null) {
            return null;
        }
        return inflate.getRoot();
    }

    private final void adjustCardView(boolean z) {
        FreeCardView freeCardView;
        FreeCardView freeCardView2;
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("adjustCardView: ", Boolean.valueOf(z)));
        }
        FragmentFeatureBinding fragmentFeatureBinding = this.binding;
        if (!(fragmentFeatureBinding == null || (freeCardView2 = fragmentFeatureBinding.previewDynamicArea) == null)) {
            freeCardView2.setCorner(z);
        }
        FragmentFeatureBinding fragmentFeatureBinding2 = this.binding;
        FreeCardView freeCardView3 = null;
        FreeCardView freeCardView4 = fragmentFeatureBinding2 == null ? null : fragmentFeatureBinding2.previewDynamicArea;
        if (freeCardView4 != null) {
            freeCardView4.setVisibility(0);
        }
        FragmentFeatureBinding fragmentFeatureBinding3 = this.binding;
        ViewGroup.LayoutParams layoutParams = (fragmentFeatureBinding3 == null || (freeCardView = fragmentFeatureBinding3.previewDynamicArea) == null) ? null : freeCardView.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            if (z) {
                ((ConstraintLayout.LayoutParams) layoutParams).matchConstraintPercentWidth = 0.9667f;
            } else {
                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
                layoutParams2.matchConstraintPercentWidth = 0.5f;
                layoutParams2.matchConstraintPercentHeight = 0.5f;
                layoutParams2.bottomToTop = C1057R.C1060id.guideline_4;
            }
        }
        FragmentFeatureBinding fragmentFeatureBinding4 = this.binding;
        if (fragmentFeatureBinding4 != null) {
            freeCardView3 = fragmentFeatureBinding4.previewDynamicArea;
        }
        if (freeCardView3 != null) {
            freeCardView3.setLayoutParams(layoutParams);
        }
    }

    private final void linkStyleSettings() {
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "linkStyleSettings");
        }
        StyleViewModel styleViewModel = this.mViewModel;
        if (styleViewModel != null) {
            styleViewModel.getStyleSettings().observe(getViewLifecycleOwner(), new Observer() {
                public final void onChanged(Object obj) {
                    StyleFragment.m76linkStyleSettings$lambda20(StyleFragment.this, (StyleSettings) obj);
                }
            });
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    private final void linkStyleSettingsPreview() {
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "linkStyleSettingsPreview");
        }
        StyleViewModel styleViewModel = this.mViewModel;
        if (styleViewModel != null) {
            styleViewModel.getStyleSettingsPreview().observe(getViewLifecycleOwner(), new Observer() {
                public final void onChanged(Object obj) {
                    StyleFragment.m79linkStyleSettingsPreview$lambda23(StyleFragment.this, (StyleSettings) obj);
                }
            });
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r0 = r0.toolbarContainer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006b, code lost:
        if (r5 == null) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006f, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0089, code lost:
        if (r5 == null) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a3, code lost:
        if (r5 == null) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bd, code lost:
        if (r5 == null) goto L_0x00e8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupToolbar(int r5) {
        /*
            r4 = this;
            com.motorola.personalize.extensions.Logger r0 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r0 = r0.getDEBUG()
            java.lang.String r1 = "StyleFragment"
            if (r0 == 0) goto L_0x000f
            java.lang.String r0 = "setupToolbar"
            android.util.Log.d(r1, r0)
        L_0x000f:
            com.motorola.personalize.databinding.FragmentFeatureBinding r0 = r4.binding
            if (r0 != 0) goto L_0x0014
            goto L_0x0026
        L_0x0014:
            com.motorola.personalize.databinding.FeatureTopBarBinding r0 = r0.toolbarContainer
            if (r0 != 0) goto L_0x0019
            goto L_0x0026
        L_0x0019:
            android.widget.ImageView r0 = r0.backIcon
            if (r0 != 0) goto L_0x001e
            goto L_0x0026
        L_0x001e:
            com.motorola.personalize.app.-$$Lambda$StyleFragment$qxPNorqb_Go8mGrOiRVyFuz3i0k r2 = new com.motorola.personalize.app.-$$Lambda$StyleFragment$qxPNorqb_Go8mGrOiRVyFuz3i0k
            r2.<init>()
            r0.setOnClickListener(r2)
        L_0x0026:
            com.motorola.personalize.databinding.FragmentFeatureBinding r0 = r4.binding
            r2 = 0
            if (r0 != 0) goto L_0x002d
        L_0x002b:
            r0 = r2
            goto L_0x0034
        L_0x002d:
            com.motorola.personalize.databinding.FeatureTopBarBinding r0 = r0.toolbarContainer
            if (r0 != 0) goto L_0x0032
            goto L_0x002b
        L_0x0032:
            androidx.constraintlayout.widget.ConstraintLayout r0 = r0.toolbarLayout
        L_0x0034:
            if (r0 != 0) goto L_0x0037
            goto L_0x0041
        L_0x0037:
            android.graphics.drawable.ColorDrawable r3 = new android.graphics.drawable.ColorDrawable
            r3.<init>(r5)
            android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
            r0.setBackground(r3)
        L_0x0041:
            java.lang.String r5 = r4.getFeatureId()
            java.lang.String r0 = ""
            if (r5 == 0) goto L_0x00c0
            int r3 = r5.hashCode()
            switch(r3) {
                case -858241647: goto L_0x00a6;
                case -836694759: goto L_0x008c;
                case -253685509: goto L_0x0072;
                case 816809574: goto L_0x0052;
                default: goto L_0x0050;
            }
        L_0x0050:
            goto L_0x00c0
        L_0x0052:
            java.lang.String r3 = "personalize_icon_shape"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x005c
            goto L_0x00c0
        L_0x005c:
            android.content.Context r5 = r4.getContext()
            if (r5 != 0) goto L_0x0064
            goto L_0x00e8
        L_0x0064:
            r1 = 2131689564(0x7f0f005c, float:1.9008147E38)
            java.lang.String r5 = r5.getString(r1)
            if (r5 != 0) goto L_0x006f
            goto L_0x00e8
        L_0x006f:
            r0 = r5
            goto L_0x00e8
        L_0x0072:
            java.lang.String r3 = "personalize_colors"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x007b
            goto L_0x00c0
        L_0x007b:
            android.content.Context r5 = r4.getContext()
            if (r5 != 0) goto L_0x0082
            goto L_0x00e8
        L_0x0082:
            r1 = 2131689554(0x7f0f0052, float:1.9008127E38)
            java.lang.String r5 = r5.getString(r1)
            if (r5 != 0) goto L_0x006f
            goto L_0x00e8
        L_0x008c:
            java.lang.String r3 = "personalize_fonts"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x0095
            goto L_0x00c0
        L_0x0095:
            android.content.Context r5 = r4.getContext()
            if (r5 != 0) goto L_0x009c
            goto L_0x00e8
        L_0x009c:
            r1 = 2131689558(0x7f0f0056, float:1.9008135E38)
            java.lang.String r5 = r5.getString(r1)
            if (r5 != 0) goto L_0x006f
            goto L_0x00e8
        L_0x00a6:
            java.lang.String r3 = "personalize_grid"
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L_0x00af
            goto L_0x00c0
        L_0x00af:
            android.content.Context r5 = r4.getContext()
            if (r5 != 0) goto L_0x00b6
            goto L_0x00e8
        L_0x00b6:
            r1 = 2131689560(0x7f0f0058, float:1.9008139E38)
            java.lang.String r5 = r5.getString(r1)
            if (r5 != 0) goto L_0x006f
            goto L_0x00e8
        L_0x00c0:
            com.motorola.personalize.extensions.Logger r5 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r5 = r5.getDEBUG()
            if (r5 == 0) goto L_0x00e8
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r3 = "setupToolbar - familyKey = "
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r3 = r4.getFeatureId()
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r3 = " is invalid"
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r1, r5)
        L_0x00e8:
            com.motorola.personalize.databinding.FragmentFeatureBinding r5 = r4.binding
            if (r5 != 0) goto L_0x00ee
        L_0x00ec:
            r5 = r2
            goto L_0x00f5
        L_0x00ee:
            com.motorola.personalize.databinding.FeatureTopBarBinding r5 = r5.toolbarContainer
            if (r5 != 0) goto L_0x00f3
            goto L_0x00ec
        L_0x00f3:
            android.widget.TextView r5 = r5.toolbarTitle
        L_0x00f5:
            if (r5 != 0) goto L_0x00f8
            goto L_0x00fe
        L_0x00f8:
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5.setText(r1)
        L_0x00fe:
            com.motorola.personalize.databinding.FragmentFeatureBinding r4 = r4.binding
            if (r4 != 0) goto L_0x0103
            goto L_0x010a
        L_0x0103:
            com.motorola.personalize.databinding.FeatureTopBarBinding r4 = r4.toolbarContainer
            if (r4 != 0) goto L_0x0108
            goto L_0x010a
        L_0x0108:
            android.widget.TextView r2 = r4.toolbarTitle
        L_0x010a:
            if (r2 != 0) goto L_0x010d
            goto L_0x0112
        L_0x010d:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2.setContentDescription(r0)
        L_0x0112:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.StyleFragment.setupToolbar(int):void");
    }

    public void adjustInsets() {
        RelativeLayout relativeLayout;
        PaletteLayout paletteLayout;
        FeatureTopBarBinding featureTopBarBinding;
        ConstraintLayout constraintLayout;
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "adjustInsets");
        }
        FragmentFeatureBinding fragmentFeatureBinding = this.binding;
        if (!(fragmentFeatureBinding == null || (featureTopBarBinding = fragmentFeatureBinding.toolbarContainer) == null || (constraintLayout = featureTopBarBinding.toolbarLayout) == null)) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(constraintLayout, true, false, true, false, 10, (Object) null);
        }
        FragmentFeatureBinding fragmentFeatureBinding2 = this.binding;
        if (!(fragmentFeatureBinding2 == null || (paletteLayout = fragmentFeatureBinding2.paletteContainer) == null)) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(paletteLayout, true, true, false, true, 4, (Object) null);
        }
        FragmentFeatureBinding fragmentFeatureBinding3 = this.binding;
        if (fragmentFeatureBinding3 != null && (relativeLayout = fragmentFeatureBinding3.optionListContainer) != null) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(relativeLayout, true, true, false, true, 4, (Object) null);
        }
    }

    private final void onFabClick() {
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onFabClick");
        }
        if (!this.isLongPressToPreviewing) {
            StyleViewModel styleViewModel = this.mViewModel;
            Option option = null;
            if (styleViewModel != null) {
                StyleSettingAdapter styleSettingAdapter = this.adapter;
                if (styleSettingAdapter != null) {
                    int selectedIndex = styleSettingAdapter.getSelectedIndex();
                    StyleSettingAdapter styleSettingAdapter2 = this.adapter;
                    if (styleSettingAdapter2 != null) {
                        option = styleSettingAdapter2.getOption(selectedIndex);
                    }
                }
                Utilities.observeOnce(styleViewModel.onApplyStyleOption(option), this, new Observer() {
                    public final void onChanged(Object obj) {
                        StyleFragment.m81onFabClick$lambda34(StyleFragment.this, (Result) obj);
                    }
                });
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    private final LinearLayoutManager getLayoutManager() {
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "getLayoutManager");
        }
        FragmentActivity activity = getActivity();
        CenterLayoutManager centerLayoutManager = new CenterLayoutManager(activity == null ? null : activity.getApplicationContext());
        centerLayoutManager.setOrientation(0);
        return centerLayoutManager;
    }

    public void onOnlineFontUpdated() {
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onOnlineFontUpdated");
        }
        StyleViewModel styleViewModel = this.mViewModel;
        if (styleViewModel != null) {
            styleViewModel.setOnlineFontUpdated();
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: onActivityCreated$lambda-15  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void m80onActivityCreated$lambda15(com.motorola.personalize.app.StyleFragment r2, com.motorola.personalize.model.Style r3) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "result"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.motorola.personalize.extensions.Logger r0 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r0 = r0.getDEBUG()
            java.lang.String r1 = "StyleFragment"
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = "onActivityCreated - LOADED System applied style: "
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r3)
            android.util.Log.d(r1, r0)
        L_0x001d:
            com.motorola.personalize.model.StyleSettings r0 = new com.motorola.personalize.model.StyleSettings
            r0.<init>(r3)
            r2.mStyleSettings = r0
            java.lang.String r3 = r2.getFeatureId()
            if (r3 == 0) goto L_0x0084
            int r0 = r3.hashCode()
            switch(r0) {
                case -858241647: goto L_0x0070;
                case -836694759: goto L_0x005c;
                case -253685509: goto L_0x0048;
                case 816809574: goto L_0x0032;
                default: goto L_0x0031;
            }
        L_0x0031:
            goto L_0x0084
        L_0x0032:
            java.lang.String r0 = "personalize_icon_shape"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x003b
            goto L_0x0084
        L_0x003b:
            com.motorola.personalize.model.StyleSettings r3 = r2.mStyleSettings
            if (r3 != 0) goto L_0x0041
            goto L_0x00ac
        L_0x0041:
            java.lang.String r0 = "shape"
            r3.setCurrentSetting(r0)
            goto L_0x00ac
        L_0x0048:
            java.lang.String r0 = "personalize_colors"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0051
            goto L_0x0084
        L_0x0051:
            com.motorola.personalize.model.StyleSettings r3 = r2.mStyleSettings
            if (r3 != 0) goto L_0x0056
            goto L_0x00ac
        L_0x0056:
            java.lang.String r0 = "color"
            r3.setCurrentSetting(r0)
            goto L_0x00ac
        L_0x005c:
            java.lang.String r0 = "personalize_fonts"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0065
            goto L_0x0084
        L_0x0065:
            com.motorola.personalize.model.StyleSettings r3 = r2.mStyleSettings
            if (r3 != 0) goto L_0x006a
            goto L_0x00ac
        L_0x006a:
            java.lang.String r0 = "font"
            r3.setCurrentSetting(r0)
            goto L_0x00ac
        L_0x0070:
            java.lang.String r0 = "personalize_grid"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0079
            goto L_0x0084
        L_0x0079:
            com.motorola.personalize.model.StyleSettings r3 = r2.mStyleSettings
            if (r3 != 0) goto L_0x007e
            goto L_0x00ac
        L_0x007e:
            java.lang.String r0 = "grid"
            r3.setCurrentSetting(r0)
            goto L_0x00ac
        L_0x0084:
            com.motorola.personalize.extensions.Logger r3 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r3 = r3.getDEBUG()
            if (r3 == 0) goto L_0x00ac
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "onCreate - familyKey = "
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r0 = r2.getFeatureId()
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r0 = " is invalid"
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r1, r3)
        L_0x00ac:
            com.motorola.personalize.viewmodel.StyleViewModel r3 = r2.mViewModel
            if (r3 == 0) goto L_0x00b6
            com.motorola.personalize.model.StyleSettings r2 = r2.mStyleSettings
            r3.onLoadStyleSetting(r2)
            return
        L_0x00b6:
            java.lang.String r2 = "mViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r2 = 0
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.StyleFragment.m80onActivityCreated$lambda15(com.motorola.personalize.app.StyleFragment, com.motorola.personalize.model.Style):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: linkStyleSettings$lambda-20  reason: not valid java name */
    public static final void m76linkStyleSettings$lambda20(StyleFragment styleFragment, StyleSettings styleSettings) {
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        Intrinsics.checkNotNullParameter(styleSettings, "styleSettings");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("linkStyleSettings - styleSettings = ", styleSettings));
        }
        styleFragment.adapter = new StyleSettingAdapter(styleFragment.getContext(), styleSettings.getCurrentSetting(), styleSettings.getCurrentSettingOptions(), styleSettings.getCurrentSettingValue(), new StyleSettingAdapter.OnOptionClickCallback() {
            public final boolean onOptionClick(Option option, int i, int i2, boolean z) {
                return StyleFragment.m77linkStyleSettings$lambda20$lambda18(StyleFragment.this, option, i, i2, z);
            }
        });
        FragmentFeatureBinding fragmentFeatureBinding = styleFragment.binding;
        RecyclerView recyclerView2 = fragmentFeatureBinding == null ? null : fragmentFeatureBinding.optionList;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(styleFragment.adapter);
        }
        StyleSettingAdapter styleSettingAdapter = styleFragment.adapter;
        Intrinsics.checkNotNull(styleSettingAdapter);
        if (styleSettingAdapter.getItemCount() > 4) {
            styleFragment.attachSnapHelper();
        }
        FragmentFeatureBinding fragmentFeatureBinding2 = styleFragment.binding;
        if (!(fragmentFeatureBinding2 == null || (recyclerView = fragmentFeatureBinding2.optionList) == null)) {
            recyclerView.postDelayed(new Runnable() {
                public final void run() {
                    StyleFragment.m78linkStyleSettings$lambda20$lambda19(StyleFragment.this);
                }
            }, 100);
        }
        StyleViewModel styleViewModel = styleFragment.mViewModel;
        if (styleViewModel != null) {
            styleViewModel.onLoadStyleSettingsPreview(styleSettings);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: linkStyleSettingsPreview$lambda-23  reason: not valid java name */
    public static final void m79linkStyleSettingsPreview$lambda23(StyleFragment styleFragment, StyleSettings styleSettings) {
        Intrinsics.checkNotNullParameter(styleFragment, "this$0");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("linkStyleSettingsPreview - styleSettings = ", styleSettings));
        }
        String featureId = styleFragment.getFeatureId();
        ViewGroup viewGroup = null;
        if (featureId != null) {
            int hashCode = featureId.hashCode();
            if (hashCode != -858241647) {
                if (hashCode != -836694759) {
                    if (hashCode == -253685509 && featureId.equals("personalize_colors")) {
                        StylePreview stylePreview = styleFragment.colorPreview;
                        if (stylePreview == null) {
                            StylePreview stylePreview2 = StylesViewTool.getStylePreview(styleFragment.getContext(), styleSettings);
                            Intrinsics.checkNotNullExpressionValue(stylePreview2, "getStylePreview(context, it)");
                            styleFragment.colorPreview = stylePreview2;
                            if (stylePreview2 != null) {
                                FragmentFeatureBinding fragmentFeatureBinding = styleFragment.binding;
                                if (fragmentFeatureBinding != null) {
                                    viewGroup = fragmentFeatureBinding.preview;
                                }
                                stylePreview2.bindPreview(viewGroup);
                                return;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("colorPreview");
                            throw null;
                        } else if (stylePreview != null) {
                            stylePreview.refreshColorPreview(styleFragment.getContext(), styleSettings);
                            return;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("colorPreview");
                            throw null;
                        }
                    }
                } else if (featureId.equals("personalize_fonts")) {
                    StylePreview stylePreview3 = StylesViewTool.getStylePreview(styleFragment.getContext(), styleSettings);
                    FragmentFeatureBinding fragmentFeatureBinding2 = styleFragment.binding;
                    if (fragmentFeatureBinding2 != null) {
                        viewGroup = fragmentFeatureBinding2.previewFixArea;
                    }
                    stylePreview3.bindPreview(viewGroup);
                    return;
                }
            } else if (featureId.equals("personalize_grid")) {
                StylePreview stylePreview4 = StylesViewTool.getStylePreview(styleFragment.getContext(), styleSettings);
                FragmentFeatureBinding fragmentFeatureBinding3 = styleFragment.binding;
                if (fragmentFeatureBinding3 != null) {
                    viewGroup = fragmentFeatureBinding3.preview;
                }
                stylePreview4.bindPreview(viewGroup);
                styleFragment.refreshRealPreview();
                return;
            }
        }
        StylePreview stylePreview5 = StylesViewTool.getStylePreview(styleFragment.getContext(), styleSettings);
        FragmentFeatureBinding fragmentFeatureBinding4 = styleFragment.binding;
        if (fragmentFeatureBinding4 != null) {
            viewGroup = fragmentFeatureBinding4.preview;
        }
        stylePreview5.bindPreview(viewGroup);
    }
}
