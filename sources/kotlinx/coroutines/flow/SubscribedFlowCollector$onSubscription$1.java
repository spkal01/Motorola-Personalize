package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H@"}, mo15462d2 = {"onSubscription", "", "T", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.flow.SubscribedFlowCollector", mo16175f = "Share.kt", mo16176i = {0, 0, 1, 1}, mo16177l = {407, 411}, mo16178m = "onSubscription", mo16179n = {"this", "safeCollector", "this", "safeCollector"}, mo16180s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: Share.kt */
final class SubscribedFlowCollector$onSubscription$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SubscribedFlowCollector this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubscribedFlowCollector$onSubscription$1(SubscribedFlowCollector subscribedFlowCollector, Continuation continuation) {
        super(continuation);
        this.this$0 = subscribedFlowCollector;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onSubscription(this);
    }
}
