package com.motorola.personalize.extensions;

import android.util.Log;
import android.view.ViewTreeObserver;
import kotlin.Metadata;

@Metadata(mo15461d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo15462d2 = {"com/motorola/personalize/extensions/ViewExtensionsKt$onLayoutRendered$1$listener$1", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onGlobalLayout", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt$onLayoutRendered$1$listener$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ T $this_onLayoutRendered;
    final /* synthetic */ ViewExtensionsKt$onLayoutRendered$1 this$0;

    ViewExtensionsKt$onLayoutRendered$1$listener$1(ViewExtensionsKt$onLayoutRendered$1 viewExtensionsKt$onLayoutRendered$1, T t) {
        this.this$0 = viewExtensionsKt$onLayoutRendered$1;
        this.$this_onLayoutRendered = t;
    }

    public void onGlobalLayout() {
        T t = this.$this_onLayoutRendered;
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onGlobalLayout - width = " + t.getMeasuredHeight() + ", height = " + t.getMeasuredHeight());
        }
        this.this$0.setValue(EmptyObject.INSTANCE);
        this.$this_onLayoutRendered.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
