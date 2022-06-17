package com.motorola.personalize.app;

import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.FeatureData;
import com.motorola.personalize.data.FeatureTileViewData;
import com.motorola.personalize.data.FeatureViewData;
import com.motorola.personalize.data.IntentData;
import com.motorola.personalize.data.LayoutItem;
import com.motorola.personalize.data.LayoutType;
import com.motorola.personalize.data.MapUtil;
import com.motorola.personalize.databinding.LayoutFeatureBinding;
import com.motorola.personalize.databinding.LayoutFeatureTileBinding;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.view.BindingHolder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001BM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00126\u0010\u0007\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\b¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u001c\u0010\u0018\u001a\u00020\u000f2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001c\u0010\u001c\u001a\u00020\u000f2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0006H\u0016J\u0010\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0006H\u0016J\u001c\u0010!\u001a\u00020\u000f2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010 \u001a\u00020\u0006H\u0016J\u001c\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R>\u0010\u0007\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, mo15462d2 = {"Lcom/motorola/personalize/app/FeatureAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/motorola/personalize/view/BindingHolder;", "mapUtil", "Lcom/motorola/personalize/data/MapUtil;", "color", "", "onItemClicked", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "featureKey", "Lcom/motorola/personalize/data/IntentData;", "intentData", "", "(Lcom/motorola/personalize/data/MapUtil;ILkotlin/jvm/functions/Function2;)V", "familySectionItemList", "", "Lcom/motorola/personalize/data/LayoutItem;", "addItems", "items", "", "Lcom/motorola/personalize/data/FeatureData;", "bindIcon", "holder", "feature", "Lcom/motorola/personalize/data/FeatureViewData;", "bindTile", "Lcom/motorola/personalize/data/FeatureTileViewData;", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: FeatureAdapter.kt */
public final class FeatureAdapter extends RecyclerView.Adapter<BindingHolder<?>> {
    private final int color;
    private final List<LayoutItem> familySectionItemList = new ArrayList();
    private final MapUtil mapUtil;
    private final Function2<String, IntentData, Unit> onItemClicked;

    public FeatureAdapter(MapUtil mapUtil2, int i, Function2<? super String, ? super IntentData, Unit> function2) {
        Intrinsics.checkNotNullParameter(mapUtil2, "mapUtil");
        Intrinsics.checkNotNullParameter(function2, "onItemClicked");
        this.mapUtil = mapUtil2;
        this.color = i;
        this.onItemClicked = function2;
    }

    public void onBindViewHolder(BindingHolder<?> bindingHolder, int i) {
        Intrinsics.checkNotNullParameter(bindingHolder, "holder");
        LayoutItem layoutItem = this.familySectionItemList.get(i);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("onBindViewHolder - feature = ", layoutItem));
        }
        if (layoutItem instanceof FeatureViewData) {
            bindIcon(bindingHolder, (FeatureViewData) layoutItem);
        } else if (layoutItem instanceof FeatureTileViewData) {
            bindTile(bindingHolder, (FeatureTileViewData) layoutItem);
        }
    }

    private final void bindIcon(BindingHolder<?> bindingHolder, FeatureViewData featureViewData) {
        LayoutFeatureBinding layoutFeatureBinding = (LayoutFeatureBinding) bindingHolder.getBinding();
        layoutFeatureBinding.setFeatureItem(featureViewData);
        layoutFeatureBinding.featureIconBackground.setBackgroundTintList(ColorStateList.valueOf(this.color));
        layoutFeatureBinding.getRoot().setOnClickListener(new View.OnClickListener(featureViewData) {
            public final /* synthetic */ FeatureViewData f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                FeatureAdapter.m54bindIcon$lambda4(FeatureAdapter.this, this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: bindIcon$lambda-4  reason: not valid java name */
    public static final void m54bindIcon$lambda4(FeatureAdapter featureAdapter, FeatureViewData featureViewData, View view) {
        Intrinsics.checkNotNullParameter(featureAdapter, "this$0");
        Intrinsics.checkNotNullParameter(featureViewData, "$feature");
        featureAdapter.onItemClicked.invoke(featureViewData.getKey(), featureViewData.getIntentData());
    }

    private final void bindTile(BindingHolder<?> bindingHolder, FeatureTileViewData featureTileViewData) {
        LayoutFeatureTileBinding layoutFeatureTileBinding = (LayoutFeatureTileBinding) bindingHolder.getBinding();
        layoutFeatureTileBinding.setFeatureItem(featureTileViewData);
        layoutFeatureTileBinding.getRoot().setOnClickListener(new View.OnClickListener(featureTileViewData) {
            public final /* synthetic */ FeatureTileViewData f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                FeatureAdapter.m55bindTile$lambda5(FeatureAdapter.this, this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: bindTile$lambda-5  reason: not valid java name */
    public static final void m55bindTile$lambda5(FeatureAdapter featureAdapter, FeatureTileViewData featureTileViewData, View view) {
        Intrinsics.checkNotNullParameter(featureAdapter, "this$0");
        Intrinsics.checkNotNullParameter(featureTileViewData, "$feature");
        featureAdapter.onItemClicked.invoke(featureTileViewData.getKey(), featureTileViewData.getIntentData());
    }

    public int getItemCount() {
        return this.familySectionItemList.size();
    }

    public int getItemViewType(int i) {
        return this.familySectionItemList.get(i).getLayoutType().getLayoutId();
    }

    public final void addItems(List<FeatureData> list) {
        Intrinsics.checkNotNullParameter(list, "items");
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("addItems - items = ", list));
        }
        this.familySectionItemList.clear();
        this.familySectionItemList.addAll(this.mapUtil.toViewData(list));
        notifyDataSetChanged();
    }

    public BindingHolder<?> onCreateViewHolder(ViewGroup viewGroup, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onCreateViewHolder");
        }
        if (i == LayoutType.Tile.INSTANCE.getLayoutId()) {
            i2 = C1057R.layout.layout_feature_tile;
        } else if (i == LayoutType.Icon.INSTANCE.getLayoutId()) {
            i2 = C1057R.layout.layout_feature;
        } else {
            Log.e(Logger.INSTANCE.getTag(), "onCreateViewHolder - invalid layout");
            i2 = 0;
        }
        return new BindingHolder<>(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), i2, viewGroup, false));
    }
}
