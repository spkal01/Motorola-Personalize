package kotlinx.coroutines.android;

import kotlin.Metadata;
import kotlinx.coroutines.DisposableHandle;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo15462d2 = {"kotlinx/coroutines/android/HandlerContext$invokeOnTimeout$1", "Lkotlinx/coroutines/DisposableHandle;", "dispose", "", "kotlinx-coroutines-android"}, mo15463k = 1, mo15464mv = {1, 4, 0})
/* compiled from: HandlerDispatcher.kt */
public final class HandlerContext$invokeOnTimeout$1 implements DisposableHandle {
    final /* synthetic */ Runnable $block;
    final /* synthetic */ HandlerContext this$0;

    HandlerContext$invokeOnTimeout$1(HandlerContext handlerContext, Runnable runnable) {
        this.this$0 = handlerContext;
        this.$block = runnable;
    }

    public void dispose() {
        this.this$0.handler.removeCallbacks(this.$block);
    }
}