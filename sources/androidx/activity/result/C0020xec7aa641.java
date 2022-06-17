package androidx.activity.result;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0003H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo15462d2 = {"<anonymous>", "", "I", "O", "it", "kotlin.jvm.PlatformType", "onActivityResult", "(Ljava/lang/Object;)V"}, mo15463k = 3, mo15464mv = {1, 4, 1})
/* renamed from: androidx.activity.result.ActivityResultCallerKt$registerForActivityResult$resultLauncher$2 */
/* compiled from: ActivityResultCaller.kt */
final class C0020xec7aa641<O> implements ActivityResultCallback<O> {
    final /* synthetic */ Function1 $callback;

    C0020xec7aa641(Function1 function1) {
        this.$callback = function1;
    }

    public final void onActivityResult(O o) {
        this.$callback.invoke(o);
    }
}
