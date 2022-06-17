package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\b¨\u0006\u0002"}, mo15462d2 = {"htmlEncode", "", "core-ktx_release"}, mo15463k = 2, mo15464mv = {1, 4, 2})
/* compiled from: String.kt */
public final class StringKt {
    public static final String htmlEncode(String str) {
        Intrinsics.checkNotNullParameter(str, "$this$htmlEncode");
        String htmlEncode = TextUtils.htmlEncode(str);
        Intrinsics.checkNotNullExpressionValue(htmlEncode, "TextUtils.htmlEncode(this)");
        return htmlEncode;
    }
}
