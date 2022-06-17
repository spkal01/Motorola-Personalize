package com.motorola.personalize.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.databinding.FeatureTopBarBinding;
import com.motorola.personalize.databinding.FragmentThemeBinding;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.OrientationExtensionsKt;
import com.motorola.personalize.model.SystemThemeOption;
import com.motorola.personalize.util.Utilities;
import com.motorola.personalize.view.ContentInterpolator;
import com.motorola.personalize.view.ThemeOptionAdapter;
import com.motorola.personalize.viewmodel.ThemeViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u001a\u001a\u00020\fH\u0002J\u0018\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001a\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u001fH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, mo15462d2 = {"Lcom/motorola/personalize/app/ThemeFragment;", "Lcom/motorola/personalize/app/ParentFragment;", "()V", "adapter", "Lcom/motorola/personalize/view/ThemeOptionAdapter;", "binding", "Lcom/motorola/personalize/databinding/FragmentThemeBinding;", "mViewModel", "Lcom/motorola/personalize/viewmodel/ThemeViewModel;", "toast", "Landroid/widget/Toast;", "adjustInsets", "", "animationCreator", "Landroid/view/animation/Animation;", "first", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onFabClick", "onStyleOptionClick", "option", "Lcom/motorola/personalize/model/SystemThemeOption;", "index", "", "onViewCreated", "view", "setupToolbar", "color", "Companion", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ThemeFragment.kt */
public final class ThemeFragment extends ParentFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "ThemeFragment";
    private ThemeOptionAdapter adapter;
    private FragmentThemeBinding binding;
    private ThemeViewModel mViewModel;
    private Toast toast;

    public void onViewCreated(View view, Bundle bundle) {
        FloatingActionButton floatingActionButton;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onViewCreated");
        }
        FragmentThemeBinding fragmentThemeBinding = this.binding;
        if (fragmentThemeBinding != null) {
            fragmentThemeBinding.setLifecycleOwner(this);
        }
        FragmentActivity activity = getActivity();
        RecyclerView recyclerView = null;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity == null ? null : activity.getApplicationContext());
        linearLayoutManager.setOrientation(0);
        FragmentThemeBinding fragmentThemeBinding2 = this.binding;
        if (fragmentThemeBinding2 != null) {
            recyclerView = fragmentThemeBinding2.optionList;
        }
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        FragmentThemeBinding fragmentThemeBinding3 = this.binding;
        if (fragmentThemeBinding3 != null && (floatingActionButton = fragmentThemeBinding3.fab) != null) {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ThemeFragment.m91onViewCreated$lambda3(ThemeFragment.this, view);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-3  reason: not valid java name */
    public static final void m91onViewCreated$lambda3(ThemeFragment themeFragment, View view) {
        Intrinsics.checkNotNullParameter(themeFragment, "this$0");
        Intrinsics.checkNotNullParameter(view, "$noName_0");
        themeFragment.onFabClick();
    }

    private final Animation animationCreator(boolean z) {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(6000);
        alphaAnimation.setFillBefore(true);
        alphaAnimation.setInterpolator(new ContentInterpolator(z));
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(1);
        return alphaAnimation;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onActivityCreated");
        }
        ThemeViewModel themeViewModel = new ThemeViewModel(requireActivity().getApplication());
        this.mViewModel = themeViewModel;
        if (themeViewModel != null) {
            themeViewModel.getPreview().observe(getViewLifecycleOwner(), new Observer() {
                public final void onChanged(Object obj) {
                    ThemeFragment.m90onActivityCreated$lambda8(ThemeFragment.this, (SystemThemeOption) obj);
                }
            });
            ThemeViewModel themeViewModel2 = this.mViewModel;
            if (themeViewModel2 != null) {
                themeViewModel2.themeLiveData.observe(getViewLifecycleOwner(), new Observer() {
                    public final void onChanged(Object obj) {
                        ThemeFragment.m88onActivityCreated$lambda11(ThemeFragment.this, (SystemThemeOption) obj);
                    }
                });
                ThemeViewModel themeViewModel3 = this.mViewModel;
                if (themeViewModel3 != null) {
                    themeViewModel3.onLoadSystemAppliedTheme();
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    throw null;
                }
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                throw null;
            }
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onActivityCreated$lambda-8  reason: not valid java name */
    public static final void m90onActivityCreated$lambda8(ThemeFragment themeFragment, SystemThemeOption systemThemeOption) {
        FrameLayout frameLayout;
        FrameLayout frameLayout2;
        FrameLayout frameLayout3;
        FrameLayout frameLayout4;
        FrameLayout frameLayout5;
        FrameLayout frameLayout6;
        Intrinsics.checkNotNullParameter(themeFragment, "this$0");
        Intrinsics.checkNotNullParameter(systemThemeOption, "option");
        String title = systemThemeOption.getTitle();
        FrameLayout frameLayout7 = null;
        if (Intrinsics.areEqual((Object) title, (Object) "light")) {
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(TAG, "transition");
            }
            FragmentThemeBinding fragmentThemeBinding = themeFragment.binding;
            if (!(fragmentThemeBinding == null || (frameLayout6 = fragmentThemeBinding.darkPreview) == null)) {
                frameLayout6.clearAnimation();
            }
            FragmentThemeBinding fragmentThemeBinding2 = themeFragment.binding;
            if (!(fragmentThemeBinding2 == null || (frameLayout5 = fragmentThemeBinding2.lightPreview) == null)) {
                frameLayout5.clearAnimation();
            }
            FragmentThemeBinding fragmentThemeBinding3 = themeFragment.binding;
            FrameLayout frameLayout8 = fragmentThemeBinding3 == null ? null : fragmentThemeBinding3.darkPreview;
            if (frameLayout8 != null) {
                frameLayout8.setVisibility(8);
            }
            FragmentThemeBinding fragmentThemeBinding4 = themeFragment.binding;
            if (fragmentThemeBinding4 != null) {
                frameLayout7 = fragmentThemeBinding4.lightPreview;
            }
            if (frameLayout7 != null) {
                frameLayout7.setVisibility(0);
            }
        } else if (Intrinsics.areEqual((Object) title, (Object) "dark")) {
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(TAG, "transition");
            }
            FragmentThemeBinding fragmentThemeBinding5 = themeFragment.binding;
            if (!(fragmentThemeBinding5 == null || (frameLayout4 = fragmentThemeBinding5.darkPreview) == null)) {
                frameLayout4.clearAnimation();
            }
            FragmentThemeBinding fragmentThemeBinding6 = themeFragment.binding;
            if (!(fragmentThemeBinding6 == null || (frameLayout3 = fragmentThemeBinding6.lightPreview) == null)) {
                frameLayout3.clearAnimation();
            }
            FragmentThemeBinding fragmentThemeBinding7 = themeFragment.binding;
            FrameLayout frameLayout9 = fragmentThemeBinding7 == null ? null : fragmentThemeBinding7.darkPreview;
            if (frameLayout9 != null) {
                frameLayout9.setVisibility(0);
            }
            FragmentThemeBinding fragmentThemeBinding8 = themeFragment.binding;
            if (fragmentThemeBinding8 != null) {
                frameLayout7 = fragmentThemeBinding8.lightPreview;
            }
            if (frameLayout7 != null) {
                frameLayout7.setVisibility(8);
            }
        } else {
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(TAG, "light/dark");
            }
            FragmentThemeBinding fragmentThemeBinding9 = themeFragment.binding;
            FrameLayout frameLayout10 = fragmentThemeBinding9 == null ? null : fragmentThemeBinding9.lightPreview;
            if (frameLayout10 != null) {
                frameLayout10.setVisibility(0);
            }
            FragmentThemeBinding fragmentThemeBinding10 = themeFragment.binding;
            if (fragmentThemeBinding10 != null) {
                frameLayout7 = fragmentThemeBinding10.darkPreview;
            }
            if (frameLayout7 != null) {
                frameLayout7.setVisibility(0);
            }
            FragmentThemeBinding fragmentThemeBinding11 = themeFragment.binding;
            if (!(fragmentThemeBinding11 == null || (frameLayout2 = fragmentThemeBinding11.lightPreview) == null)) {
                frameLayout2.startAnimation(themeFragment.animationCreator(true));
            }
            FragmentThemeBinding fragmentThemeBinding12 = themeFragment.binding;
            if (fragmentThemeBinding12 != null && (frameLayout = fragmentThemeBinding12.darkPreview) != null) {
                frameLayout.startAnimation(themeFragment.animationCreator(false));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onActivityCreated$lambda-11$lambda-10  reason: not valid java name */
    public static final boolean m89onActivityCreated$lambda11$lambda10(ThemeFragment themeFragment, SystemThemeOption systemThemeOption, int i) {
        Intrinsics.checkNotNullParameter(themeFragment, "this$0");
        Intrinsics.checkNotNull(systemThemeOption);
        return themeFragment.onStyleOptionClick(systemThemeOption, i);
    }

    public void adjustInsets() {
        RelativeLayout relativeLayout;
        FeatureTopBarBinding featureTopBarBinding;
        ConstraintLayout constraintLayout;
        FragmentThemeBinding fragmentThemeBinding = this.binding;
        if (!(fragmentThemeBinding == null || (featureTopBarBinding = fragmentThemeBinding.toolbarContainer) == null || (constraintLayout = featureTopBarBinding.toolbarLayout) == null)) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(constraintLayout, true, false, true, false, 10, (Object) null);
        }
        FragmentThemeBinding fragmentThemeBinding2 = this.binding;
        if (fragmentThemeBinding2 != null && (relativeLayout = fragmentThemeBinding2.optionListContainer) != null) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(relativeLayout, true, true, false, true, 4, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setupToolbar$lambda-13  reason: not valid java name */
    public static final void m92setupToolbar$lambda13(ThemeFragment themeFragment, View view) {
        Intrinsics.checkNotNullParameter(themeFragment, "this$0");
        FragmentActivity activity = themeFragment.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Metadata(mo15461d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo15462d2 = {"Lcom/motorola/personalize/app/ThemeFragment$Companion;", "", "()V", "TAG", "", "newInstance", "Landroidx/fragment/app/Fragment;", "extras", "Landroid/os/Bundle;", "newInstance$app_release", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: ThemeFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Fragment newInstance$app_release(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "extras");
            ThemeFragment themeFragment = new ThemeFragment();
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(ThemeFragment.TAG, "new instance");
            }
            themeFragment.setArguments(bundle);
            return themeFragment;
        }
    }

    private final boolean onStyleOptionClick(SystemThemeOption systemThemeOption, int i) {
        TextView textView;
        TextView textView2;
        TextView textView3;
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("onStyleOptionClick ", Integer.valueOf(i)));
        }
        if (i == 0) {
            FragmentThemeBinding fragmentThemeBinding = this.binding;
            if (!(fragmentThemeBinding == null || (textView = fragmentThemeBinding.themeDesc) == null)) {
                textView.setText(C1057R.string.feature_system_theme_light_desc);
            }
            Toast toast2 = this.toast;
            if (toast2 != null) {
                toast2.cancel();
            }
            this.toast = null;
        } else if (i != 1) {
            FragmentThemeBinding fragmentThemeBinding2 = this.binding;
            if (!(fragmentThemeBinding2 == null || (textView3 = fragmentThemeBinding2.themeDesc) == null)) {
                textView3.setText(C1057R.string.feature_system_theme_transition_desc);
            }
            if (!Utilities.anyLocationProviderEnabled(getContext()) && this.toast == null) {
                Toast makeText = Toast.makeText(getContext(), C1057R.string.feature_system_theme_transition_tip, 0);
                this.toast = makeText;
                Intrinsics.checkNotNull(makeText);
                makeText.show();
            }
        } else {
            FragmentThemeBinding fragmentThemeBinding3 = this.binding;
            if (!(fragmentThemeBinding3 == null || (textView2 = fragmentThemeBinding3.themeDesc) == null)) {
                textView2.setText(C1057R.string.feature_system_theme_dark_desc);
            }
            Toast toast3 = this.toast;
            if (toast3 != null) {
                toast3.cancel();
            }
            this.toast = null;
        }
        ThemeViewModel themeViewModel = this.mViewModel;
        if (themeViewModel != null) {
            themeViewModel.onLoadPreview(systemThemeOption);
            return true;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        throw null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onCreateView");
        }
        FragmentThemeBinding inflate = FragmentThemeBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        if (inflate == null) {
            return null;
        }
        return inflate.getRoot();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r0 = r0.toolbarContainer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupToolbar(int r4) {
        /*
            r3 = this;
            com.motorola.personalize.extensions.Logger r0 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r0 = r0.getDEBUG()
            if (r0 == 0) goto L_0x000f
            java.lang.String r0 = "ThemeFragment"
            java.lang.String r1 = "setupToolbar"
            android.util.Log.d(r0, r1)
        L_0x000f:
            com.motorola.personalize.databinding.FragmentThemeBinding r0 = r3.binding
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
            com.motorola.personalize.app.-$$Lambda$ThemeFragment$uoV6cMyRC1VoUPWC_HbRXgBwqQY r1 = new com.motorola.personalize.app.-$$Lambda$ThemeFragment$uoV6cMyRC1VoUPWC_HbRXgBwqQY
            r1.<init>()
            r0.setOnClickListener(r1)
        L_0x0026:
            com.motorola.personalize.databinding.FragmentThemeBinding r0 = r3.binding
            r1 = 0
            if (r0 != 0) goto L_0x002d
        L_0x002b:
            r0 = r1
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
            android.graphics.drawable.ColorDrawable r2 = new android.graphics.drawable.ColorDrawable
            r2.<init>(r4)
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            r0.setBackground(r2)
        L_0x0041:
            android.content.Context r4 = r3.getContext()
            if (r4 != 0) goto L_0x0049
            r4 = r1
            goto L_0x0050
        L_0x0049:
            r0 = 2131689570(0x7f0f0062, float:1.900816E38)
            java.lang.String r4 = r4.getString(r0)
        L_0x0050:
            com.motorola.personalize.databinding.FragmentThemeBinding r0 = r3.binding
            if (r0 != 0) goto L_0x0056
        L_0x0054:
            r0 = r1
            goto L_0x005d
        L_0x0056:
            com.motorola.personalize.databinding.FeatureTopBarBinding r0 = r0.toolbarContainer
            if (r0 != 0) goto L_0x005b
            goto L_0x0054
        L_0x005b:
            android.widget.TextView r0 = r0.toolbarTitle
        L_0x005d:
            if (r0 != 0) goto L_0x0060
            goto L_0x0066
        L_0x0060:
            r2 = r4
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
        L_0x0066:
            com.motorola.personalize.databinding.FragmentThemeBinding r3 = r3.binding
            if (r3 != 0) goto L_0x006b
            goto L_0x0072
        L_0x006b:
            com.motorola.personalize.databinding.FeatureTopBarBinding r3 = r3.toolbarContainer
            if (r3 != 0) goto L_0x0070
            goto L_0x0072
        L_0x0070:
            android.widget.TextView r1 = r3.toolbarTitle
        L_0x0072:
            if (r1 != 0) goto L_0x0075
            goto L_0x007a
        L_0x0075:
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r1.setContentDescription(r4)
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.ThemeFragment.setupToolbar(int):void");
    }

    private final void onFabClick() {
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onFabClick");
        }
        ThemeViewModel themeViewModel = this.mViewModel;
        SystemThemeOption systemThemeOption = null;
        if (themeViewModel != null) {
            ThemeOptionAdapter themeOptionAdapter = this.adapter;
            if (themeOptionAdapter != null) {
                int selectedIndex = themeOptionAdapter.getSelectedIndex();
                ThemeOptionAdapter themeOptionAdapter2 = this.adapter;
                if (themeOptionAdapter2 != null) {
                    systemThemeOption = themeOptionAdapter2.getOption(selectedIndex);
                }
            }
            themeViewModel.applyThemeChanged(systemThemeOption);
            requireActivity().finish();
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        throw null;
    }

    /* access modifiers changed from: private */
    /* renamed from: onActivityCreated$lambda-11  reason: not valid java name */
    public static final void m88onActivityCreated$lambda11(ThemeFragment themeFragment, SystemThemeOption systemThemeOption) {
        Intrinsics.checkNotNullParameter(themeFragment, "this$0");
        Intrinsics.checkNotNullParameter(systemThemeOption, "option");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "themeLiveData.observe");
        }
        Context context = themeFragment.getContext();
        ThemeViewModel themeViewModel = themeFragment.mViewModel;
        RecyclerView recyclerView = null;
        if (themeViewModel != null) {
            themeFragment.adapter = new ThemeOptionAdapter(context, themeViewModel.getOptions(), systemThemeOption.getTitle(), new ThemeOptionAdapter.OnOptionClickCallback() {
                public final boolean onOptionClick(SystemThemeOption systemThemeOption, int i) {
                    return ThemeFragment.m89onActivityCreated$lambda11$lambda10(ThemeFragment.this, systemThemeOption, i);
                }
            });
            FragmentThemeBinding fragmentThemeBinding = themeFragment.binding;
            if (fragmentThemeBinding != null) {
                recyclerView = fragmentThemeBinding.optionList;
            }
            if (recyclerView != null) {
                recyclerView.setAdapter(themeFragment.adapter);
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        throw null;
    }
}
