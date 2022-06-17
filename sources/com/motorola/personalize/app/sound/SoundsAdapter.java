package com.motorola.personalize.app.sound;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.IntentData;
import com.motorola.personalize.data.SoundsViewData;
import com.motorola.personalize.databinding.LayoutSoundsTileBinding;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.view.BindingHolder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00126\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u0015\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017J\u001c\u0010\u0018\u001a\u00020\r2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0007H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\u0007H\u0002J\u001c\u0010\u001e\u001a\u00020\r2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u001f\u001a\u00020\u0007H\u0016J\u001c\u0010 \u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0007H\u0016R\u000e\u0010\u000f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R>\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, mo15462d2 = {"Lcom/motorola/personalize/app/sound/SoundsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/motorola/personalize/view/BindingHolder;", "context", "Landroid/content/Context;", "onItemClicked", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "type", "Lcom/motorola/personalize/data/IntentData;", "intentData", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function2;)V", "color", "isDoubleSimCardSupport", "", "sectionItemList", "", "Lcom/motorola/personalize/data/SoundsViewData;", "addItems", "items", "", "bindTile", "holder", "soundItem", "getItemCount", "getTitle", "", "onBindViewHolder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: SoundsAdapter.kt */
public final class SoundsAdapter extends RecyclerView.Adapter<BindingHolder<?>> {
    private final int color;
    private final Context context;
    private boolean isDoubleSimCardSupport;
    private final Function2<Integer, IntentData, Unit> onItemClicked;
    private final List<SoundsViewData> sectionItemList = new ArrayList();

    public SoundsAdapter(Context context2, Function2<? super Integer, ? super IntentData, Unit> function2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(function2, "onItemClicked");
        this.context = context2;
        this.onItemClicked = function2;
        this.color = context2.getResources().getColor(C1057R.C1058color.a_1_100);
    }

    public void onBindViewHolder(BindingHolder<?> bindingHolder, int i) {
        Intrinsics.checkNotNullParameter(bindingHolder, "holder");
        SoundsViewData soundsViewData = this.sectionItemList.get(i);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("onBindViewHolder - soundItem = ", soundsViewData));
        }
        bindTile(bindingHolder, soundsViewData);
    }

    private final void bindTile(BindingHolder<?> bindingHolder, SoundsViewData soundsViewData) {
        LayoutSoundsTileBinding layoutSoundsTileBinding = (LayoutSoundsTileBinding) bindingHolder.getBinding();
        layoutSoundsTileBinding.title.setText(getTitle(soundsViewData.getType()));
        layoutSoundsTileBinding.description.setText(SoundUtil.getRingtoneTitle(this.context, soundsViewData.getUri()));
        layoutSoundsTileBinding.image.setImageResource(SoundUtil.getIcon(soundsViewData.getType()));
        layoutSoundsTileBinding.image.setBackgroundTintList(ColorStateList.valueOf(this.color));
        layoutSoundsTileBinding.getRoot().setOnClickListener(new View.OnClickListener(soundsViewData) {
            public final /* synthetic */ SoundsViewData f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SoundsAdapter.m97bindTile$lambda3(SoundsAdapter.this, this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: bindTile$lambda-3  reason: not valid java name */
    public static final void m97bindTile$lambda3(SoundsAdapter soundsAdapter, SoundsViewData soundsViewData, View view) {
        Intrinsics.checkNotNullParameter(soundsAdapter, "this$0");
        Intrinsics.checkNotNullParameter(soundsViewData, "$soundItem");
        soundsAdapter.onItemClicked.invoke(Integer.valueOf(soundsViewData.getType()), soundsViewData.getIntentData());
    }

    private final String getTitle(int i) {
        boolean z = this.isDoubleSimCardSupport;
        int i2 = C1057R.string.sound_title_alarms;
        if (z) {
            Context context2 = this.context;
            if (i == 1) {
                i2 = C1057R.string.sound_title_ringtones_sim_1;
            } else if (i == 2) {
                i2 = C1057R.string.sound_title_notifications;
            } else if (i != 4) {
                i2 = C1057R.string.sound_title_ringtones_sim_2;
            }
            String string = context2.getString(i2);
            Intrinsics.checkNotNullExpressionValue(string, "{\n            context.getString(when (type) {\n                RingtoneManager.TYPE_NOTIFICATION -> R.string.sound_title_notifications\n                RingtoneManager.TYPE_ALARM -> R.string.sound_title_alarms\n                RingtoneManager.TYPE_RINGTONE -> R.string.sound_title_ringtones_sim_1\n                else -> R.string.sound_title_ringtones_sim_2\n            })\n        }");
            return string;
        }
        Context context3 = this.context;
        if (i == 2) {
            i2 = C1057R.string.sound_title_notifications;
        } else if (i != 4) {
            i2 = C1057R.string.sound_title_ringtones;
        }
        String string2 = context3.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string2, "{\n         context.getString(when (type) {\n                RingtoneManager.TYPE_NOTIFICATION -> R.string.sound_title_notifications\n                RingtoneManager.TYPE_ALARM -> R.string.sound_title_alarms\n                else -> R.string.sound_title_ringtones\n            })\n        }");
        return string2;
    }

    public int getItemCount() {
        return this.sectionItemList.size();
    }

    public final void addItems(List<SoundsViewData> list) {
        Intrinsics.checkNotNullParameter(list, "items");
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("addItems - items = ", list));
        }
        this.sectionItemList.clear();
        this.sectionItemList.addAll(list);
        for (SoundsViewData type : list) {
            if (type.getType() == 64) {
                this.isDoubleSimCardSupport = true;
                SoundUtil.setDoubleSIM(true);
            }
        }
        notifyDataSetChanged();
    }

    public BindingHolder<?> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onCreateViewHolder");
        }
        return new BindingHolder<>(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), C1057R.layout.layout_sounds_tile, viewGroup, false));
    }
}
