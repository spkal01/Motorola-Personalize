package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo15462d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/flow/SharingCommand;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.flow.StartedLazily$command$1", mo16175f = "SharingStarted.kt", mo16176i = {0, 0, 0}, mo16177l = {212}, mo16178m = "invokeSuspend", mo16179n = {"$this$flow", "started", "$this$collect$iv"}, mo16180s = {"L$0", "L$1", "L$2"})
/* compiled from: SharingStarted.kt */
final class StartedLazily$command$1 extends SuspendLambda implements Function2<FlowCollector<? super SharingCommand>, Continuation<? super Unit>, Object> {
    final /* synthetic */ StateFlow $subscriptionCount;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private FlowCollector f265p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StartedLazily$command$1(StateFlow stateFlow, Continuation continuation) {
        super(2, continuation);
        this.$subscriptionCount = stateFlow;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StartedLazily$command$1 startedLazily$command$1 = new StartedLazily$command$1(this.$subscriptionCount, continuation);
        startedLazily$command$1.f265p$ = (FlowCollector) obj;
        return startedLazily$command$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((StartedLazily$command$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.f265p$;
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            Flow flow = this.$subscriptionCount;
            this.L$0 = flowCollector;
            this.L$1 = booleanRef;
            this.L$2 = flow;
            this.label = 1;
            if (flow.collect(new StartedLazily$command$1$invokeSuspend$$inlined$collect$1(flowCollector, booleanRef), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            Flow flow2 = (Flow) this.L$2;
            Ref.BooleanRef booleanRef2 = (Ref.BooleanRef) this.L$1;
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
