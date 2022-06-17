package com.motorola.personalize.app;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.android.launcher3.icons.IconPack;
import com.android.launcher3.icons.IconPackManager;
import com.android.wallpaper.ThemesBaseActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.model.IconPacksHelper;
import com.motorola.personalize.util.Executors;
import com.motorola.personalize.util.Utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0004H\u0002J\b\u0010\u0018\u001a\u00020\u0004H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u001aH\u0014J\b\u0010!\u001a\u00020\u001aH\u0002J\b\u0010\"\u001a\u00020\u001aH\u0002J\b\u0010#\u001a\u00020\u001aH\u0002J\b\u0010$\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, mo15462d2 = {"Lcom/motorola/personalize/app/IconPacksActivity;", "Lcom/android/wallpaper/ThemesBaseActivity;", "()V", "LANDSCAPE_COLUMNS_NUM", "", "PORTRAIT_COLUMNS_NUM", "mCallback", "Landroid/content/pm/LauncherApps$Callback;", "mDownloadPrompt", "Lcom/google/android/material/floatingactionbutton/ExtendedFloatingActionButton;", "mGridLayoutManager", "Landroidx/recyclerview/widget/GridLayoutManager;", "mIconBack", "Landroidx/appcompat/widget/AppCompatImageView;", "mIconPacks", "", "Lcom/android/launcher3/icons/IconPack;", "mIconPacksAdapter", "Lcom/motorola/personalize/app/IconPacksAdapter;", "mIconPacksHelper", "Lcom/motorola/personalize/model/IconPacksHelper;", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getColumnNum", "getContentViewId", "initRecyclerView", "", "columnNum", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "registerAppUninstallBroadcast", "setAppUnstallCallback", "unRegisterAppUninstallBroadcast", "updateViewSpacing", "SpaceItemDecoration", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: IconPacksActivity.kt */
public final class IconPacksActivity extends ThemesBaseActivity {
    private final int LANDSCAPE_COLUMNS_NUM = 2;
    private final int PORTRAIT_COLUMNS_NUM = 1;
    private LauncherApps.Callback mCallback;
    /* access modifiers changed from: private */
    public ExtendedFloatingActionButton mDownloadPrompt;
    private GridLayoutManager mGridLayoutManager;
    private AppCompatImageView mIconBack;
    private final List<IconPack> mIconPacks = new ArrayList();
    /* access modifiers changed from: private */
    public IconPacksAdapter mIconPacksAdapter;
    /* access modifiers changed from: private */
    public IconPacksHelper mIconPacksHelper;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;

    public int getContentViewId() {
        return C1057R.layout.icon_packs;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIconPacksHelper = IconPacksHelper.Companion.getInstance(this);
        initView();
        setAppUnstallCallback();
        registerAppUninstallBroadcast();
    }

    private final void initView() {
        this.mDownloadPrompt = (ExtendedFloatingActionButton) findViewById(C1057R.C1060id.download_prompt);
        this.mIconBack = (AppCompatImageView) findViewById(C1057R.C1060id.back_icon);
        initRecyclerView(getColumnNum());
        updateViewSpacing();
        ExtendedFloatingActionButton extendedFloatingActionButton = this.mDownloadPrompt;
        if (extendedFloatingActionButton != null) {
            extendedFloatingActionButton.setIconTintMode(PorterDuff.Mode.DST);
        }
        ExtendedFloatingActionButton extendedFloatingActionButton2 = this.mDownloadPrompt;
        if (extendedFloatingActionButton2 != null) {
            extendedFloatingActionButton2.setIconSize(getResources().getDimensionPixelSize(C1057R.dimen.icon_pack_item_download_prompt_img));
        }
        ExtendedFloatingActionButton extendedFloatingActionButton3 = this.mDownloadPrompt;
        if (extendedFloatingActionButton3 != null) {
            extendedFloatingActionButton3.setIcon(getDrawable(C1057R.mipmap.ic_download));
        }
        ExtendedFloatingActionButton extendedFloatingActionButton4 = this.mDownloadPrompt;
        if (extendedFloatingActionButton4 != null) {
            extendedFloatingActionButton4.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    IconPacksActivity.m56initView$lambda0(IconPacksActivity.this, view);
                }
            });
        }
        Executors.INSTANCE.getUI_HELPER_EXECUTOR().execute(new Runnable() {
            public final void run() {
                IconPacksActivity.m57initView$lambda2(IconPacksActivity.this);
            }
        });
        AppCompatImageView appCompatImageView = this.mIconBack;
        if (appCompatImageView != null) {
            appCompatImageView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    IconPacksActivity.m59initView$lambda3(IconPacksActivity.this, view);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m56initView$lambda0(IconPacksActivity iconPacksActivity, View view) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        Utilities.queryMarket(iconPacksActivity);
    }

    /* access modifiers changed from: private */
    /* renamed from: initView$lambda-2  reason: not valid java name */
    public static final void m57initView$lambda2(IconPacksActivity iconPacksActivity) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        List<IconPack> availableIconPacks = IconPackManager.getAvailableIconPacks(iconPacksActivity);
        if (availableIconPacks != null) {
            iconPacksActivity.runOnUiThread(new Runnable(availableIconPacks) {
                public final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    IconPacksActivity.m58initView$lambda2$lambda1(IconPacksActivity.this, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: initView$lambda-2$lambda-1  reason: not valid java name */
    public static final void m58initView$lambda2$lambda1(IconPacksActivity iconPacksActivity, List list) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        Intrinsics.checkNotNullParameter(list, "$iconPacks");
        IconPacksAdapter iconPacksAdapter = iconPacksActivity.mIconPacksAdapter;
        if (iconPacksAdapter != null) {
            iconPacksAdapter.resetDatas(list);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: initView$lambda-3  reason: not valid java name */
    public static final void m59initView$lambda3(IconPacksActivity iconPacksActivity, View view) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        iconPacksActivity.finish();
    }

    private final void setAppUnstallCallback() {
        this.mCallback = new IconPacksActivity$setAppUnstallCallback$1(this);
    }

    private final void updateViewSpacing() {
        Resources resources = getResources();
        if (Utilities.isLandscape(resources)) {
            int dimensionPixelOffset = resources.getDimensionPixelOffset(C1057R.dimen.icon_pack_page_landscape_padding_start);
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
                return;
            }
            return;
        }
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(C1057R.dimen.icon_pack_page_vertical_padding);
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setPadding(dimensionPixelOffset2, 0, dimensionPixelOffset2, 0);
        }
    }

    private final void initRecyclerView(int i) {
        this.mRecyclerView = (RecyclerView) findViewById(C1057R.C1060id.recycler_view);
        Context context = this;
        this.mGridLayoutManager = new GridLayoutManager(context, i);
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            Resources resources = getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "resources");
            recyclerView.addItemDecoration(new SpaceItemDecoration(this, resources));
        }
        RecyclerView recyclerView2 = this.mRecyclerView;
        RecyclerView.ItemAnimator itemAnimator = recyclerView2 == null ? null : recyclerView2.getItemAnimator();
        Objects.requireNonNull(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        RecyclerView recyclerView3 = this.mRecyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setHasFixedSize(true);
        }
        RecyclerView recyclerView4 = this.mRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.setLayoutManager(this.mGridLayoutManager);
        }
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "layoutInflater");
        IconPacksAdapter iconPacksAdapter = new IconPacksAdapter(context, layoutInflater, this.mIconPacks);
        this.mIconPacksAdapter = iconPacksAdapter;
        RecyclerView recyclerView5 = this.mRecyclerView;
        if (recyclerView5 != null) {
            recyclerView5.setAdapter(iconPacksAdapter);
        }
        RecyclerView recyclerView6 = this.mRecyclerView;
        if (recyclerView6 != null) {
            recyclerView6.addOnScrollListener(new IconPacksActivity$initRecyclerView$1(this));
        }
    }

    private final int getColumnNum() {
        if (!Utilities.isLandscape(getResources())) {
            return this.PORTRAIT_COLUMNS_NUM;
        }
        if (isInMultiWindowMode()) {
            return this.PORTRAIT_COLUMNS_NUM;
        }
        return this.LANDSCAPE_COLUMNS_NUM;
    }

    @Metadata(mo15461d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo15462d2 = {"Lcom/motorola/personalize/app/IconPacksActivity$SpaceItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "res", "Landroid/content/res/Resources;", "(Lcom/motorola/personalize/app/IconPacksActivity;Landroid/content/res/Resources;)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: IconPacksActivity.kt */
    public final class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final Resources res;
        final /* synthetic */ IconPacksActivity this$0;

        public SpaceItemDecoration(IconPacksActivity iconPacksActivity, Resources resources) {
            Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
            Intrinsics.checkNotNullParameter(resources, "res");
            this.this$0 = iconPacksActivity;
            this.res = resources;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(rect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(recyclerView, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
            if (!Utilities.isLandscape(this.res) || this.this$0.isInMultiWindowMode()) {
                if (childLayoutPosition == 0) {
                    rect.top = this.res.getDimensionPixelOffset(C1057R.dimen.icon_pack_item_apply_margin_End);
                } else {
                    rect.top = this.res.getDimensionPixelOffset(C1057R.dimen.icon_pack_page_cards_bottom_space);
                }
                rect.bottom = this.res.getDimensionPixelOffset(C1057R.dimen.icon_pack_item_title_margin_end);
                return;
            }
            int dimensionPixelOffset = this.res.getDimensionPixelOffset(C1057R.dimen.icon_pack_page_cards_landscape_space) / 2;
            if (childLayoutPosition % 2 == 1) {
                if (Utilities.isRtl(this.this$0.getResources())) {
                    rect.right = dimensionPixelOffset;
                } else {
                    rect.left = dimensionPixelOffset;
                }
            } else if (Utilities.isRtl(this.this$0.getResources())) {
                rect.left = dimensionPixelOffset;
            } else {
                rect.right = dimensionPixelOffset;
            }
            rect.top = this.res.getDimensionPixelOffset(C1057R.dimen.icon_pack_page_cards_landscape_space);
            rect.bottom = this.res.getDimensionPixelOffset(C1057R.dimen.icon_pack_item_title_margin_end);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        unRegisterAppUninstallBroadcast();
        super.onDestroy();
    }

    private final void registerAppUninstallBroadcast() {
        ((LauncherApps) getSystemService(LauncherApps.class)).registerCallback(this.mCallback);
    }

    private final void unRegisterAppUninstallBroadcast() {
        ((LauncherApps) getSystemService(LauncherApps.class)).unregisterCallback(this.mCallback);
    }
}
