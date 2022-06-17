package com.android.systemui.monet;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo15462d2 = {"<anonymous>", "", "it", "", "invoke"}, mo15463k = 3, mo15464mv = {1, 4, 2})
/* compiled from: ColorScheme.kt */
final class ColorScheme$Companion$humanReadable$1 extends Lambda implements Function1<Integer, CharSequence> {
    public static final ColorScheme$Companion$humanReadable$1 INSTANCE = new ColorScheme$Companion$humanReadable$1();

    ColorScheme$Companion$humanReadable$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final CharSequence invoke(int i) {
        return "#" + Integer.toHexString(i);
    }
}
