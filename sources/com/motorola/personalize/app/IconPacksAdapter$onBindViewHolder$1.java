package com.motorola.personalize.app;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.android.launcher3.icons.IconPack;
import com.motorola.personalize.app.IconPacksAdapter;
import com.motorola.personalize.model.IconPacksHelper;
import kotlin.Metadata;

@Metadata(mo15461d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, mo15462d2 = {"com/motorola/personalize/app/IconPacksAdapter$onBindViewHolder$1", "Lcom/motorola/personalize/model/IconPacksHelper$IconPackUiCallback;", "runOnUiThread", "", "imageView", "Landroid/widget/ImageView;", "drawable", "Landroid/graphics/drawable/Drawable;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: IconPacksAdapter.kt */
public final class IconPacksAdapter$onBindViewHolder$1 implements IconPacksHelper.IconPackUiCallback {
    final /* synthetic */ IconPacksAdapter.ViewHolder $holder;
    final /* synthetic */ IconPack $iconPack;
    final /* synthetic */ IconPacksAdapter this$0;

    IconPacksAdapter$onBindViewHolder$1(IconPacksAdapter iconPacksAdapter, IconPacksAdapter.ViewHolder viewHolder, IconPack iconPack) {
        this.this$0 = iconPacksAdapter;
        this.$holder = viewHolder;
        this.$iconPack = iconPack;
    }

    public void runOnUiThread(ImageView imageView, Drawable drawable) {
        this.this$0.setIconMargin(this.$holder.getIcon(), this.$iconPack);
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }
}
