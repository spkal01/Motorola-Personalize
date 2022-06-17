package com.motorola.personalize.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.icons.IconPack;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FamilyData;
import com.motorola.personalize.data.IntentData;
import com.motorola.personalize.data.MapUtil;
import com.motorola.personalize.databinding.ActivityLayoutMainBinding;
import com.motorola.personalize.databinding.ToolbarFamilySectionBinding;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.OrientationExtensionsKt;
import com.motorola.personalize.extensions.WindowExtKt;
import com.motorola.personalize.util.IntentChecker;
import com.motorola.personalize.view.FeatureListDecoration;
import com.motorola.personalize.viewmodel.FeatureViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo15461d1 = {"\u0000q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001\u0011\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u000fH\u0002J\u0010\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0010\u0010*\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u000fH\u0002J\b\u0010+\u001a\u00020\u0014H\u0002J\b\u0010,\u001a\u00020\u0014H\u0002J\u0018\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006/"}, mo15462d2 = {"Lcom/motorola/personalize/app/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/motorola/personalize/databinding/ActivityLayoutMainBinding;", "featureAdapter", "Lcom/motorola/personalize/app/FeatureAdapter;", "intentChecker", "Lcom/motorola/personalize/util/IntentChecker;", "mViewModel", "Lcom/motorola/personalize/viewmodel/FeatureViewModel;", "mapUtil", "Lcom/motorola/personalize/data/MapUtil;", "observer", "Landroidx/lifecycle/Observer;", "Lcom/motorola/personalize/data/FamilyData;", "spanSizeLookup", "com/motorola/personalize/app/MainActivity$spanSizeLookup$1", "Lcom/motorola/personalize/app/MainActivity$spanSizeLookup$1;", "adjustInsets", "", "getLayoutManager", "Landroidx/recyclerview/widget/GridLayoutManager;", "layoutGridSize", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFeatureClicked", "featureKey", "", "intentData", "Lcom/motorola/personalize/data/IntentData;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "openActivity", "setup", "familydata", "setupDarkToolbar", "family", "setupLightToolbar", "setupRecyclerView", "setupToolbar", "familyData", "newColor", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: MainActivity.kt */
public final class MainActivity extends AppCompatActivity {
    private ActivityLayoutMainBinding binding;
    /* access modifiers changed from: private */
    public FeatureAdapter featureAdapter;
    private IntentChecker intentChecker;
    private FeatureViewModel mViewModel;
    private MapUtil mapUtil;
    private final Observer<FamilyData> observer = new Observer() {
        public final void onChanged(Object obj) {
            MainActivity.m68observer$lambda4(MainActivity.this, (FamilyData) obj);
        }
    };
    private final MainActivity$spanSizeLookup$1 spanSizeLookup = new MainActivity$spanSizeLookup$1(this);

    private final void openActivity(IntentData intentData) {
        Intent intent = new Intent(intentData.getIntent());
        Bundle intentExtra = intentData.getIntentExtra();
        if (intentExtra != null) {
            intent.putExtras(intentExtra);
        }
        CharSequence intentPackage = intentData.getIntentPackage();
        if (!(intentPackage == null || StringsKt.isBlank(intentPackage))) {
            intent.setPackage(intentData.getIntentPackage());
        }
        IntentChecker intentChecker2 = this.intentChecker;
        if (intentChecker2 != null) {
            boolean isIntentCallable = intentChecker2.isIntentCallable(intent);
            String tag = Logger.INSTANCE.getTag();
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(tag, "openActivity - intent = " + intent + " - isIntentCallable = " + isIntentCallable);
            }
            if (isIntentCallable) {
                startActivity(intent);
                return;
            }
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("intentChecker");
        throw null;
    }

    /* access modifiers changed from: private */
    /* renamed from: observer$lambda-4  reason: not valid java name */
    public static final void m68observer$lambda4(MainActivity mainActivity, FamilyData familyData) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        if (familyData != null) {
            Intrinsics.checkNotNullExpressionValue(familyData, "date");
            mainActivity.setup(familyData);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onCreate");
        }
        Window window = getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "window");
        OrientationExtensionsKt.updateWindowFitSystem(window);
        ActivityLayoutMainBinding activityLayoutMainBinding = (ActivityLayoutMainBinding) DataBindingUtil.setContentView(this, C1057R.layout.activity_layout_main);
        this.binding = activityLayoutMainBinding;
        if (activityLayoutMainBinding != null) {
            activityLayoutMainBinding.setLifecycleOwner(this);
        }
        setupToolbar();
        setupRecyclerView();
        adjustInsets();
        Context context = this;
        this.mapUtil = new MapUtil(context);
        this.intentChecker = new IntentChecker(context);
        int color = getResources().getColor(C1057R.C1058color.a_1_100, getTheme());
        MapUtil mapUtil2 = this.mapUtil;
        if (mapUtil2 != null) {
            this.featureAdapter = new FeatureAdapter(mapUtil2, color, new MainActivity$onCreate$2(this));
            MapUtil mapUtil3 = this.mapUtil;
            if (mapUtil3 != null) {
                FeatureViewModel featureViewModel = new FeatureViewModel(mapUtil3);
                this.mViewModel = featureViewModel;
                if (featureViewModel != null) {
                    featureViewModel.loadData();
                    FeatureViewModel featureViewModel2 = this.mViewModel;
                    if (featureViewModel2 != null) {
                        featureViewModel2.getFamily().observe(this, this.observer);
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                        throw null;
                    }
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                    throw null;
                }
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("mapUtil");
                throw null;
            }
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mapUtil");
            throw null;
        }
    }

    private final void setupRecyclerView() {
        RecyclerView recyclerView;
        int integer = getResources().getInteger(C1057R.integer.grid_layout_manager_size);
        ActivityLayoutMainBinding activityLayoutMainBinding = this.binding;
        RecyclerView recyclerView2 = activityLayoutMainBinding == null ? null : activityLayoutMainBinding.featureList;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(getLayoutManager(integer));
        }
        ActivityLayoutMainBinding activityLayoutMainBinding2 = this.binding;
        if (activityLayoutMainBinding2 != null && (recyclerView = activityLayoutMainBinding2.featureList) != null) {
            recyclerView.addItemDecoration(new FeatureListDecoration(getResources().getDimensionPixelSize(C1057R.dimen.main_decorator_margin_top), integer));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, IconPack.ITEM);
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: private */
    public final void onFeatureClicked(String str, IntentData intentData) {
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("onFeatureClicker - ", str));
        }
        openActivity(intentData);
    }

    private final GridLayoutManager getLayoutManager(int i) {
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("getLayoutManager - layoutGridSize = ", Integer.valueOf(i)));
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), i);
        gridLayoutManager.setSpanSizeLookup(this.spanSizeLookup);
        return gridLayoutManager;
    }

    private final void setup(FamilyData familyData) {
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "setupFragment");
        }
        boolean isDarkModeEnabled = ContextExtensionsKt.isDarkModeEnabled(this);
        FeatureAdapter featureAdapter2 = this.featureAdapter;
        if (featureAdapter2 != null) {
            featureAdapter2.addItems(familyData.getFeatures());
            ActivityLayoutMainBinding activityLayoutMainBinding = this.binding;
            RecyclerView recyclerView = activityLayoutMainBinding == null ? null : activityLayoutMainBinding.featureList;
            if (recyclerView != null) {
                FeatureAdapter featureAdapter3 = this.featureAdapter;
                if (featureAdapter3 != null) {
                    recyclerView.setAdapter(featureAdapter3);
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("featureAdapter");
                    throw null;
                }
            }
            if (isDarkModeEnabled) {
                setupDarkToolbar(familyData);
            } else {
                setupLightToolbar(familyData);
            }
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("featureAdapter");
            throw null;
        }
    }

    private final void setupDarkToolbar(FamilyData familyData) {
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "setupDarkToolbar");
        }
        setupToolbar(FamilyData.copy$default(familyData, (String) null, (String) null, (String) null, (List) null, (Drawable) null, Integer.valueOf(getResources().getColor(C1057R.C1058color.toolbar_background_color_dark_theme, (Resources.Theme) null)), (Integer) null, 95, (Object) null), getResources().getColor(C1057R.C1058color.n_1_800, getTheme()));
    }

    private final void setupLightToolbar(FamilyData familyData) {
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "setupLightToolbar");
        }
        Window window = getWindow();
        if (window != null) {
            Integer familyStatusBarColor = familyData.getFamilyStatusBarColor();
            Intrinsics.checkNotNull(familyStatusBarColor);
            WindowExtKt.updateStatusColor(window, familyStatusBarColor.intValue());
        }
        setupToolbar(familyData, getResources().getColor(C1057R.C1058color.a_1_100, getTheme()));
    }

    private final void setupToolbar(FamilyData familyData, int i) {
        ToolbarFamilySectionBinding toolbarFamilySectionBinding;
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "setupToolbar");
        }
        ActivityLayoutMainBinding activityLayoutMainBinding = this.binding;
        if (activityLayoutMainBinding != null) {
            activityLayoutMainBinding.setFamilyData(familyData);
        }
        ActivityLayoutMainBinding activityLayoutMainBinding2 = this.binding;
        ConstraintLayout constraintLayout = null;
        if (!(activityLayoutMainBinding2 == null || (toolbarFamilySectionBinding = activityLayoutMainBinding2.toolbarContainer) == null)) {
            constraintLayout = toolbarFamilySectionBinding.toolbarLayout;
        }
        if (constraintLayout != null) {
            constraintLayout.setBackground(new ColorDrawable(i));
        }
    }

    private final void adjustInsets() {
        RecyclerView recyclerView;
        ToolbarFamilySectionBinding toolbarFamilySectionBinding;
        Toolbar toolbar;
        ToolbarFamilySectionBinding toolbarFamilySectionBinding2;
        TextView textView;
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "adjustInsets");
        }
        ActivityLayoutMainBinding activityLayoutMainBinding = this.binding;
        if (!(activityLayoutMainBinding == null || (toolbarFamilySectionBinding2 = activityLayoutMainBinding.toolbarContainer) == null || (textView = toolbarFamilySectionBinding2.toolbarTitle) == null)) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(textView, true, true, true, false, 8, (Object) null);
        }
        ActivityLayoutMainBinding activityLayoutMainBinding2 = this.binding;
        if (!(activityLayoutMainBinding2 == null || (toolbarFamilySectionBinding = activityLayoutMainBinding2.toolbarContainer) == null || (toolbar = toolbarFamilySectionBinding.detailFeatureFamilyToolbar) == null)) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(toolbar, true, true, true, false, 8, (Object) null);
        }
        ActivityLayoutMainBinding activityLayoutMainBinding3 = this.binding;
        if (activityLayoutMainBinding3 != null && (recyclerView = activityLayoutMainBinding3.featureList) != null) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(recyclerView, true, true, false, true, 4, (Object) null);
        }
    }

    private final void setupToolbar() {
        ToolbarFamilySectionBinding toolbarFamilySectionBinding;
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "setupToolbar");
        }
        ActivityLayoutMainBinding activityLayoutMainBinding = this.binding;
        Toolbar toolbar = null;
        if (!(activityLayoutMainBinding == null || (toolbarFamilySectionBinding = activityLayoutMainBinding.toolbarContainer) == null)) {
            toolbar = toolbarFamilySectionBinding.detailFeatureFamilyToolbar;
        }
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayShowHomeEnabled(true);
        }
        ActionBar supportActionBar3 = getSupportActionBar();
        if (supportActionBar3 != null) {
            supportActionBar3.setDisplayShowTitleEnabled(false);
        }
    }
}
