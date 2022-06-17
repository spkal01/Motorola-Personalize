package com.motorola.personalize.view;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, mo15462d2 = {"Lcom/motorola/personalize/view/BindingHolder;", "T", "Landroidx/databinding/ViewDataBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "(Landroidx/databinding/ViewDataBinding;)V", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "Landroidx/databinding/ViewDataBinding;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: BindingHolder.kt */
public final class BindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final T binding;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BindingHolder(T t) {
        super(t.getRoot());
        Intrinsics.checkNotNullParameter(t, "binding");
        this.binding = t;
    }

    public final T getBinding() {
        return this.binding;
    }
}
