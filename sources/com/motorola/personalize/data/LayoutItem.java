package com.motorola.personalize.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo15462d2 = {"Lcom/motorola/personalize/data/LayoutItem;", "", "layoutType", "Lcom/motorola/personalize/data/LayoutType;", "(Lcom/motorola/personalize/data/LayoutType;)V", "getLayoutType", "()Lcom/motorola/personalize/data/LayoutType;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: LayoutItem.kt */
public class LayoutItem {
    private final LayoutType layoutType;

    public LayoutItem(LayoutType layoutType2) {
        Intrinsics.checkNotNullParameter(layoutType2, "layoutType");
        this.layoutType = layoutType2;
    }

    public final LayoutType getLayoutType() {
        return this.layoutType;
    }
}
