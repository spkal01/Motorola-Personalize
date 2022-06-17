package com.motorola.personalize.extensions;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;

@Metadata(mo15461d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000*\u0002\u0000\u0004\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0014R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, mo15462d2 = {"com/motorola/personalize/extensions/ViewExtensionsKt$onLayoutRendered$1", "Landroidx/lifecycle/MutableLiveData;", "Lcom/motorola/personalize/extensions/EmptyObject;", "listener", "com/motorola/personalize/extensions/ViewExtensionsKt$onLayoutRendered$1$listener$1", "getListener", "()Lcom/motorola/personalize/extensions/ViewExtensionsKt$onLayoutRendered$1$listener$1;", "Lcom/motorola/personalize/extensions/ViewExtensionsKt$onLayoutRendered$1$listener$1;", "onInactive", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt$onLayoutRendered$1 extends MutableLiveData<EmptyObject> {
    final /* synthetic */ T $this_onLayoutRendered;
    private final ViewExtensionsKt$onLayoutRendered$1$listener$1 listener;

    ViewExtensionsKt$onLayoutRendered$1(T t) {
        this.$this_onLayoutRendered = t;
        ViewExtensionsKt$onLayoutRendered$1$listener$1 viewExtensionsKt$onLayoutRendered$1$listener$1 = new ViewExtensionsKt$onLayoutRendered$1$listener$1(this, t);
        this.listener = viewExtensionsKt$onLayoutRendered$1$listener$1;
        t.getViewTreeObserver().addOnGlobalLayoutListener(viewExtensionsKt$onLayoutRendered$1$listener$1);
    }

    public final ViewExtensionsKt$onLayoutRendered$1$listener$1 getListener() {
        return this.listener;
    }

    /* access modifiers changed from: protected */
    public void onInactive() {
        super.onInactive();
        this.$this_onLayoutRendered.getViewTreeObserver().removeOnGlobalLayoutListener(this.listener);
    }
}
