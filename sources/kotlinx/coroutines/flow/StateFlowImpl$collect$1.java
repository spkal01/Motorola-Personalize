package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H@"}, mo15462d2 = {"collect", "", "T", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.flow.StateFlowImpl", mo16175f = "StateFlow.kt", mo16176i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, mo16177l = {330, 388, 347}, mo16178m = "collect", mo16179n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "oldState", "newState", "this", "collector", "slot", "collectorJob", "oldState", "newState"}, mo16180s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: StateFlow.kt */
final class StateFlowImpl$collect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StateFlowImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StateFlowImpl$collect$1(StateFlowImpl stateFlowImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = stateFlowImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect((FlowCollector) null, this);
    }
}
