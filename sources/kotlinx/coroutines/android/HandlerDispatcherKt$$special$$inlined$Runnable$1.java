package kotlinx.coroutines.android;

import kotlin.Metadata;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo15462d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, mo15463k = 3, mo15464mv = {1, 4, 0})
/* compiled from: Runnable.kt */
public final class HandlerDispatcherKt$$special$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ CancellableContinuation $cont$inlined;

    public HandlerDispatcherKt$$special$$inlined$Runnable$1(CancellableContinuation cancellableContinuation) {
        this.$cont$inlined = cancellableContinuation;
    }

    public final void run() {
        HandlerDispatcherKt.updateChoreographerAndPostFrameCallback(this.$cont$inlined);
    }
}
