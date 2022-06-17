package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000323\b\u0004\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tHH"}, mo15462d2 = {"collectWhile", "", "T", "Lkotlinx/coroutines/flow/Flow;", "predicate", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "continuation", ""}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.flow.FlowKt__LimitKt", mo16175f = "Limit.kt", mo16176i = {0, 0, 0}, mo16177l = {138}, mo16178m = "collectWhile", mo16179n = {"$this$collectWhile", "predicate", "collector"}, mo16180s = {"L$0", "L$1", "L$2"})
/* compiled from: Limit.kt */
public final class FlowKt__LimitKt$collectWhile$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    public FlowKt__LimitKt$collectWhile$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.collectWhile((Flow) null, (Function2) null, this);
    }
}
