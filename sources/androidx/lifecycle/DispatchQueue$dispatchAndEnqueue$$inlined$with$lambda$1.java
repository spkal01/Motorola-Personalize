package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo15462d2 = {"<anonymous>", "", "run", "androidx/lifecycle/DispatchQueue$dispatchAndEnqueue$1$1"}, mo15463k = 3, mo15464mv = {1, 4, 1})
/* compiled from: DispatchQueue.kt */
final class DispatchQueue$dispatchAndEnqueue$$inlined$with$lambda$1 implements Runnable {
    final /* synthetic */ CoroutineContext $context$inlined;
    final /* synthetic */ Runnable $runnable$inlined;
    final /* synthetic */ DispatchQueue this$0;

    DispatchQueue$dispatchAndEnqueue$$inlined$with$lambda$1(DispatchQueue dispatchQueue, CoroutineContext coroutineContext, Runnable runnable) {
        this.this$0 = dispatchQueue;
        this.$context$inlined = coroutineContext;
        this.$runnable$inlined = runnable;
    }

    public final void run() {
        this.this$0.enqueue(this.$runnable$inlined);
    }
}
