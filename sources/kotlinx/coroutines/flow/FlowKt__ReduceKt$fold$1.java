package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00032H\b\u0004\u0010\u0006\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00030\fHH"}, mo15462d2 = {"fold", "", "T", "R", "Lkotlinx/coroutines/flow/Flow;", "initial", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "acc", "value", "Lkotlin/coroutines/Continuation;", "continuation"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", mo16175f = "Reduce.kt", mo16176i = {0, 0, 0, 0, 0}, mo16177l = {151}, mo16178m = "fold", mo16179n = {"$this$fold", "initial", "operation", "accumulator", "$this$collect$iv"}, mo16180s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: Reduce.kt */
public final class FlowKt__ReduceKt$fold$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    public FlowKt__ReduceKt$fold$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.fold((Flow) null, null, (Function3) null, this);
    }
}
