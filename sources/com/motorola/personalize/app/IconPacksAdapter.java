package com.motorola.personalize.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.icons.IconPack;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.model.IconPacksHelper;
import com.motorola.personalize.view.RoundImgView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001%B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\b\u0010\u0014\u001a\u00020\rH\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\rH\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\rH\u0016J\u0016\u0010 \u001a\u00020\u00162\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tJ\u0018\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\nH\u0002R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u0011X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, mo15462d2 = {"Lcom/motorola/personalize/app/IconPacksAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/motorola/personalize/app/IconPacksAdapter$ViewHolder;", "Landroid/view/View$OnClickListener;", "mContext", "Landroid/content/Context;", "mLayoutInflater", "Landroid/view/LayoutInflater;", "datas", "", "Lcom/android/launcher3/icons/IconPack;", "(Landroid/content/Context;Landroid/view/LayoutInflater;Ljava/util/List;)V", "dataSize", "", "getDataSize", "()I", "mDatas", "", "mIconPacksHelper", "Lcom/motorola/personalize/model/IconPacksHelper;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onClick", "v", "Landroid/view/View;", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "resetDatas", "setIconMargin", "imageView", "Lcom/motorola/personalize/view/RoundImgView;", "iconPack", "ViewHolder", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: IconPacksAdapter.kt */
public final class IconPacksAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {
    private final Context mContext;
    private final List<IconPack> mDatas;
    private final IconPacksHelper mIconPacksHelper;
    private final LayoutInflater mLayoutInflater;

    public IconPacksAdapter(Context context, LayoutInflater layoutInflater, List<? extends IconPack> list) {
        Intrinsics.checkNotNullParameter(context, "mContext");
        Intrinsics.checkNotNullParameter(layoutInflater, "mLayoutInflater");
        this.mContext = context;
        this.mLayoutInflater = layoutInflater;
        List<IconPack> arrayList = new ArrayList<>();
        this.mDatas = arrayList;
        this.mIconPacksHelper = IconPacksHelper.Companion.getInstance(context);
        Intrinsics.checkNotNull(list);
        arrayList.addAll(list);
    }

    public final void resetDatas(List<? extends IconPack> list) {
        this.mDatas.clear();
        List<IconPack> list2 = this.mDatas;
        Intrinsics.checkNotNull(list);
        list2.addAll(list);
        notifyDataSetChanged();
    }

    public final int getDataSize() {
        return this.mDatas.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = this.mLayoutInflater.inflate(C1057R.layout.icon_pack_item, viewGroup, false);
        Objects.requireNonNull(inflate, "null cannot be cast to non-null type android.view.ViewGroup");
        return new ViewHolder((ViewGroup) inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        IconPack iconPack = this.mDatas.get(i);
        if (iconPack != null) {
            IconPacksHelper iconPacksHelper = this.mIconPacksHelper;
            Intrinsics.checkNotNull(iconPacksHelper);
            if (iconPacksHelper.isDefaultIconPack(iconPack)) {
                viewHolder.getUninstall().setVisibility(8);
                setIconMargin(viewHolder.getIcon(), iconPack);
                viewHolder.getIcon().setImageDrawable(this.mIconPacksHelper.getDefaultPackCoverDrawable());
            } else {
                viewHolder.getUninstall().setVisibility(0);
                viewHolder.getIcon().setTag(iconPack.getPackageName());
                this.mIconPacksHelper.getOtherPackCoverDrawables(iconPack, viewHolder.getIcon(), new IconPacksAdapter$onBindViewHolder$1(this, viewHolder, iconPack));
            }
            if (this.mIconPacksHelper.isIconPackAppied(iconPack)) {
                viewHolder.getApply().setImageResource(C1057R.C1059drawable.ic_checkmark_select);
                viewHolder.itemView.setBackground(this.mContext.getDrawable(C1057R.C1059drawable.icon_pack_item_selected_bg));
            } else {
                viewHolder.getApply().setImageResource(C1057R.C1059drawable.ic_checkmark_unselect);
                viewHolder.itemView.setBackground(this.mContext.getDrawable(C1057R.C1059drawable.icon_pack_item_bg));
            }
            viewHolder.getTitle().setText(this.mIconPacksHelper.getIconPackTitle(iconPack));
            viewHolder.getUninstall().setTag(iconPack.getPackageName());
            viewHolder.getUninstall().setTag(C1057R.C1060id.uninstall, viewHolder);
            View.OnClickListener onClickListener = this;
            viewHolder.getUninstall().setOnClickListener(onClickListener);
            viewHolder.itemView.setTag(iconPack.getPackageName());
            viewHolder.itemView.setTag(C1057R.C1060id.root_view, viewHolder);
            viewHolder.itemView.setOnClickListener(onClickListener);
        }
    }

    /* access modifiers changed from: private */
    public final void setIconMargin(RoundImgView roundImgView, IconPack iconPack) {
        IconPacksHelper iconPacksHelper = this.mIconPacksHelper;
        boolean z = true;
        if (iconPacksHelper == null || !iconPacksHelper.isIconPackAppied(iconPack)) {
            z = false;
        }
        if (z) {
            roundImgView.setMargin(this.mContext.getResources().getDimensionPixelOffset(C1057R.dimen.icon_pack_item_selected_stroke));
        } else {
            roundImgView.setMargin(0);
        }
    }

    public int getItemCount() {
        return this.mDatas.size();
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (view.getId() == C1057R.C1060id.uninstall) {
            IconPacksHelper iconPacksHelper = this.mIconPacksHelper;
            Intrinsics.checkNotNull(iconPacksHelper);
            Object tag = view.getTag();
            Objects.requireNonNull(tag, "null cannot be cast to non-null type kotlin.String");
            iconPacksHelper.uninstallAPK((String) tag);
        } else if (view.getId() == C1057R.C1060id.root_view) {
            IconPacksHelper iconPacksHelper2 = this.mIconPacksHelper;
            Intrinsics.checkNotNull(iconPacksHelper2);
            Object tag2 = view.getTag();
            Objects.requireNonNull(tag2, "null cannot be cast to non-null type kotlin.String");
            iconPacksHelper2.applyAndSendBroadcast((String) tag2);
            notifyItemRangeChanged(0, this.mDatas.size());
        }
    }

    @Metadata(mo15461d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\n¨\u0006\u001a"}, mo15462d2 = {"Lcom/motorola/personalize/app/IconPacksAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "v", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "apply", "Landroid/widget/ImageView;", "getApply", "()Landroid/widget/ImageView;", "setApply", "(Landroid/widget/ImageView;)V", "icon", "Lcom/motorola/personalize/view/RoundImgView;", "getIcon", "()Lcom/motorola/personalize/view/RoundImgView;", "setIcon", "(Lcom/motorola/personalize/view/RoundImgView;)V", "title", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "setTitle", "(Landroid/widget/TextView;)V", "uninstall", "getUninstall", "setUninstall", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: IconPacksAdapter.kt */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView apply;
        private RoundImgView icon;
        private TextView title;
        private ImageView uninstall;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(ViewGroup viewGroup) {
            super(viewGroup);
            Intrinsics.checkNotNullParameter(viewGroup, "v");
            View findViewById = viewGroup.findViewById(C1057R.C1060id.icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "v.findViewById(R.id.icon)");
            this.icon = (RoundImgView) findViewById;
            View findViewById2 = viewGroup.findViewById(C1057R.C1060id.title);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "v.findViewById(R.id.title)");
            this.title = (TextView) findViewById2;
            View findViewById3 = viewGroup.findViewById(C1057R.C1060id.apply);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "v.findViewById(R.id.apply)");
            this.apply = (ImageView) findViewById3;
            View findViewById4 = viewGroup.findViewById(C1057R.C1060id.uninstall);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "v.findViewById(R.id.uninstall)");
            this.uninstall = (ImageView) findViewById4;
        }

        public final RoundImgView getIcon() {
            return this.icon;
        }

        public final void setIcon(RoundImgView roundImgView) {
            Intrinsics.checkNotNullParameter(roundImgView, "<set-?>");
            this.icon = roundImgView;
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final void setTitle(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.title = textView;
        }

        public final ImageView getApply() {
            return this.apply;
        }

        public final void setApply(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.apply = imageView;
        }

        public final ImageView getUninstall() {
            return this.uninstall;
        }

        public final void setUninstall(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.uninstall = imageView;
        }
    }
}
