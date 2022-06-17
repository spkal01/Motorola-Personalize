package com.motorola.personalize.app;

import android.util.Log;
import androidx.recyclerview.widget.GridLayoutManager;
import com.motorola.personalize.data.LayoutType;
import com.motorola.personalize.extensions.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, mo15462d2 = {"com/motorola/personalize/app/MainActivity$spanSizeLookup$1", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSize", "", "position", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: MainActivity.kt */
public final class MainActivity$spanSizeLookup$1 extends GridLayoutManager.SpanSizeLookup {
    final /* synthetic */ MainActivity this$0;

    MainActivity$spanSizeLookup$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public int getSpanSize(int i) {
        FeatureAdapter access$getFeatureAdapter$p = this.this$0.featureAdapter;
        if (access$getFeatureAdapter$p != null) {
            int itemViewType = access$getFeatureAdapter$p.getItemViewType(i);
            if (itemViewType == LayoutType.Icon.INSTANCE.getLayoutId()) {
                return 1;
            }
            if (itemViewType == LayoutType.Tile.INSTANCE.getLayoutId()) {
                return 4;
            }
            Log.e(Logger.INSTANCE.getTag(), "getSpanSize - invalid layout");
            return 0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("featureAdapter");
        throw null;
    }
}
