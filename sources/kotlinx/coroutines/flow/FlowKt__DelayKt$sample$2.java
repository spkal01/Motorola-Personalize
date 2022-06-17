package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo15462d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", mo16175f = "Delay.kt", mo16176i = {0, 0, 0, 0, 0}, mo16177l = {355}, mo16178m = "invokeSuspend", mo16179n = {"$this$scopedFlow", "downstream", "values", "lastValue", "ticker"}, mo16180s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$sample$2 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $periodMillis;
    final /* synthetic */ Flow $this_sample;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f225p$;
    private FlowCollector p$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2(Flow flow, long j, Continuation continuation) {
        super(3, continuation);
        this.$this_sample = flow;
        this.$periodMillis = j;
    }

    public final Continuation<Unit> create(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$sample$2 flowKt__DelayKt$sample$2 = new FlowKt__DelayKt$sample$2(this.$this_sample, this.$periodMillis, continuation);
        flowKt__DelayKt$sample$2.f225p$ = coroutineScope;
        flowKt__DelayKt$sample$2.p$0 = flowCollector;
        return flowKt__DelayKt$sample$2;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((FlowKt__DelayKt$sample$2) create((CoroutineScope) obj, (FlowCollector) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:9|10|11|12|13|14|19|(1:21)|(1:23)(4:24|25|7|(1:26)(0))|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00c4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0074  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r2 = r0.L$5
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2 r2 = (kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2) r2
            java.lang.Object r2 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r5 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            kotlin.ResultKt.throwOnFailure(r18)
            r10 = r2
            r13 = r4
            r12 = r5
            r11 = r6
            r14 = r7
            r2 = r1
            r1 = r0
            goto L_0x00df
        L_0x0031:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r18)
            kotlinx.coroutines.CoroutineScope r2 = r0.f225p$
            kotlinx.coroutines.flow.FlowCollector r11 = r0.p$0
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            r5 = 0
            r6 = -1
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1 r4 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1
            r10 = 0
            r4.<init>(r0, r10)
            r7 = r4
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 1
            r9 = 0
            r4 = r2
            kotlinx.coroutines.channels.ReceiveChannel r4 = kotlinx.coroutines.channels.ProduceKt.produce$default(r4, r5, r6, r7, r8, r9)
            r12.element = r4
            kotlin.jvm.internal.Ref$ObjectRef r13 = new kotlin.jvm.internal.Ref$ObjectRef
            r13.<init>()
            r13.element = r10
            long r5 = r0.$periodMillis
            r7 = 0
            r9 = 2
            r4 = r2
            kotlinx.coroutines.channels.ReceiveChannel r4 = kotlinx.coroutines.flow.FlowKt__DelayKt.fixedPeriodTicker$default(r4, r5, r7, r9, r10)
            r14 = r2
            r10 = r4
            r2 = r1
            r1 = r0
        L_0x006e:
            T r0 = r13.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.DONE
            if (r0 == r4) goto L_0x00e1
            r1.L$0 = r14
            r1.L$1 = r11
            r1.L$2 = r12
            r1.L$3 = r13
            r1.L$4 = r10
            r1.L$5 = r1
            r1.label = r3
            r15 = r1
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            kotlinx.coroutines.selects.SelectBuilderImpl r9 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r9.<init>(r15)
            r0 = r9
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ all -> 0x00c6 }
            T r4 = r12.element     // Catch:{ all -> 0x00c6 }
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4     // Catch:{ all -> 0x00c6 }
            kotlinx.coroutines.selects.SelectClause1 r8 = r4.getOnReceiveOrNull()     // Catch:{ all -> 0x00c6 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1 r16 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1     // Catch:{ all -> 0x00c6 }
            r5 = 0
            r4 = r16
            r6 = r12
            r7 = r10
            r3 = r8
            r8 = r13
            r17 = r1
            r1 = r9
            r9 = r11
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00c4 }
            r4 = r16
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch:{ all -> 0x00c4 }
            r0.invoke(r3, r4)     // Catch:{ all -> 0x00c4 }
            kotlinx.coroutines.selects.SelectClause1 r3 = r10.getOnReceive()     // Catch:{ all -> 0x00c4 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2 r16 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2     // Catch:{ all -> 0x00c4 }
            r5 = 0
            r4 = r16
            r6 = r12
            r7 = r10
            r8 = r13
            r9 = r11
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00c4 }
            r4 = r16
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch:{ all -> 0x00c4 }
            r0.invoke(r3, r4)     // Catch:{ all -> 0x00c4 }
            goto L_0x00cd
        L_0x00c4:
            r0 = move-exception
            goto L_0x00ca
        L_0x00c6:
            r0 = move-exception
            r17 = r1
            r1 = r9
        L_0x00ca:
            r1.handleBuilderException(r0)
        L_0x00cd:
            java.lang.Object r0 = r1.getResult()
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r1) goto L_0x00da
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r15)
        L_0x00da:
            if (r0 != r2) goto L_0x00dd
            return r2
        L_0x00dd:
            r1 = r17
        L_0x00df:
            r3 = 1
            goto L_0x006e
        L_0x00e1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
