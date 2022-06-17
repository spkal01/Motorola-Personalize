package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo15462d2 = {"<anonymous>", "", "R", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", mo16175f = "Combine.kt", mo16176i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, mo16177l = {57, 79, 82}, mo16178m = "invokeSuspend", mo16179n = {"$this$flowScope", "size", "latestValues", "resultChannel", "nonClosed", "remainingAbsentValues", "lastReceivedEpoch", "currentEpoch", "$this$flowScope", "size", "latestValues", "resultChannel", "nonClosed", "remainingAbsentValues", "lastReceivedEpoch", "currentEpoch", "element", "results", "$this$flowScope", "size", "latestValues", "resultChannel", "nonClosed", "remainingAbsentValues", "lastReceivedEpoch", "currentEpoch", "element", "results"}, mo16180s = {"L$0", "I$0", "L$1", "L$2", "L$3", "I$1", "L$4", "B$0", "L$0", "I$0", "L$1", "L$2", "L$3", "I$1", "L$4", "I$2", "L$5", "L$6", "L$0", "I$0", "L$1", "L$2", "L$3", "I$1", "L$4", "I$2", "L$5", "L$6"})
/* compiled from: Combine.kt */
final class CombineKt$combineInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $arrayFactory;
    final /* synthetic */ Flow[] $flows;
    final /* synthetic */ FlowCollector $this_combineInternal;
    final /* synthetic */ Function3 $transform;
    byte B$0;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f275p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CombineKt$combineInternal$2(FlowCollector flowCollector, Flow[] flowArr, Function0 function0, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_combineInternal = flowCollector;
        this.$flows = flowArr;
        this.$arrayFactory = function0;
        this.$transform = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(this.$this_combineInternal, this.$flows, this.$arrayFactory, this.$transform, continuation);
        combineKt$combineInternal$2.f275p$ = (CoroutineScope) obj;
        return combineKt$combineInternal$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CombineKt$combineInternal$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: kotlinx.coroutines.internal.Symbol[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: kotlinx.coroutines.internal.Symbol[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: kotlinx.coroutines.internal.Symbol} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: kotlinx.coroutines.internal.Symbol[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: kotlinx.coroutines.internal.Symbol[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0129 A[LOOP:1: B:24:0x0129->B:31:0x014a, LOOP_START, PHI: r2 r4 
      PHI: (r2v5 int) = (r2v4 int), (r2v6 int) binds: [B:23:0x0127, B:31:0x014a] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v7 kotlin.collections.IndexedValue) = (r4v6 kotlin.collections.IndexedValue), (r4v12 kotlin.collections.IndexedValue) binds: [B:23:0x0127, B:31:0x014a] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) {
        /*
            r23 = this;
            r6 = r23
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.label
            r8 = 0
            r9 = 3
            r10 = 2
            r11 = 1
            if (r0 == 0) goto L_0x0098
            if (r0 == r11) goto L_0x0072
            if (r0 == r10) goto L_0x004b
            if (r0 != r9) goto L_0x0043
            java.lang.Object r0 = r6.L$6
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            java.lang.Object r0 = r6.L$5
            kotlin.collections.IndexedValue r0 = (kotlin.collections.IndexedValue) r0
            int r0 = r6.I$2
            java.lang.Object r1 = r6.L$4
            byte[] r1 = (byte[]) r1
            int r2 = r6.I$1
            java.lang.Object r3 = r6.L$3
            java.util.concurrent.atomic.AtomicInteger r3 = (java.util.concurrent.atomic.AtomicInteger) r3
            java.lang.Object r4 = r6.L$2
            kotlinx.coroutines.channels.Channel r4 = (kotlinx.coroutines.channels.Channel) r4
            java.lang.Object r5 = r6.L$1
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            int r8 = r6.I$0
            java.lang.Object r12 = r6.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlin.ResultKt.throwOnFailure(r24)
            r22 = r5
            r5 = r4
            r4 = r9
            r9 = r8
            r8 = r7
            r7 = r22
            goto L_0x01ce
        L_0x0043:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004b:
            java.lang.Object r0 = r6.L$6
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            java.lang.Object r0 = r6.L$5
            kotlin.collections.IndexedValue r0 = (kotlin.collections.IndexedValue) r0
            int r0 = r6.I$2
            java.lang.Object r1 = r6.L$4
            byte[] r1 = (byte[]) r1
            int r2 = r6.I$1
            java.lang.Object r3 = r6.L$3
            java.util.concurrent.atomic.AtomicInteger r3 = (java.util.concurrent.atomic.AtomicInteger) r3
            java.lang.Object r4 = r6.L$2
            kotlinx.coroutines.channels.Channel r4 = (kotlinx.coroutines.channels.Channel) r4
            java.lang.Object r5 = r6.L$1
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            int r8 = r6.I$0
            java.lang.Object r12 = r6.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlin.ResultKt.throwOnFailure(r24)
            goto L_0x0184
        L_0x0072:
            byte r0 = r6.B$0
            java.lang.Object r1 = r6.L$4
            byte[] r1 = (byte[]) r1
            int r2 = r6.I$1
            java.lang.Object r3 = r6.L$3
            java.util.concurrent.atomic.AtomicInteger r3 = (java.util.concurrent.atomic.AtomicInteger) r3
            java.lang.Object r4 = r6.L$2
            kotlinx.coroutines.channels.Channel r4 = (kotlinx.coroutines.channels.Channel) r4
            java.lang.Object r5 = r6.L$1
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            int r8 = r6.I$0
            java.lang.Object r12 = r6.L$0
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlin.ResultKt.throwOnFailure(r24)
            r15 = r8
            r14 = r12
            r8 = r7
            r7 = r5
            r5 = r4
            r4 = r24
            goto L_0x0125
        L_0x0098:
            kotlin.ResultKt.throwOnFailure(r24)
            kotlinx.coroutines.CoroutineScope r12 = r6.f275p$
            kotlinx.coroutines.flow.Flow[] r0 = r6.$flows
            int r15 = r0.length
            if (r15 != 0) goto L_0x00a5
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00a5:
            java.lang.Object[] r14 = new java.lang.Object[r15]
            kotlinx.coroutines.internal.Symbol r17 = kotlinx.coroutines.flow.internal.NullSurrogateKt.UNINITIALIZED
            r18 = 0
            r19 = 0
            r20 = 6
            r21 = 0
            r16 = r14
            kotlin.collections.ArraysKt.fill$default((java.lang.Object[]) r16, (java.lang.Object) r17, (int) r18, (int) r19, (int) r20, (java.lang.Object) r21)
            r0 = 6
            r1 = 0
            kotlinx.coroutines.channels.Channel r19 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r15, r1, r1, r0, r1)
            java.util.concurrent.atomic.AtomicInteger r13 = new java.util.concurrent.atomic.AtomicInteger
            r13.<init>(r15)
            r5 = r8
        L_0x00c2:
            if (r5 >= r15) goto L_0x00f7
            r16 = 0
            r17 = 0
            kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1 r18 = new kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1
            r20 = 0
            r0 = r18
            r1 = r23
            r2 = r5
            r3 = r19
            r4 = r13
            r21 = r5
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5)
            r0 = r18
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r1 = 3
            r18 = 0
            r2 = r13
            r13 = r12
            r3 = r14
            r14 = r16
            r4 = r15
            r15 = r17
            r16 = r0
            r17 = r1
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r13, r14, r15, r16, r17, r18)
            int r5 = r21 + 1
            r13 = r2
            r14 = r3
            r15 = r4
            goto L_0x00c2
        L_0x00f7:
            r2 = r13
            r3 = r14
            r4 = r15
            byte[] r0 = new byte[r4]
            r2 = r4
            r15 = r2
            r1 = r19
        L_0x0100:
            int r8 = r8 + r11
            byte r3 = (byte) r8
            r4 = r1
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            r6.L$0 = r12
            r6.I$0 = r15
            r6.L$1 = r14
            r6.L$2 = r1
            r6.L$3 = r13
            r6.I$1 = r2
            r6.L$4 = r0
            r6.B$0 = r3
            r6.label = r11
            java.lang.Object r4 = kotlinx.coroutines.channels.ChannelsKt.receiveOrNull(r4, r6)
            if (r4 != r7) goto L_0x011e
            return r7
        L_0x011e:
            r5 = r1
            r8 = r7
            r7 = r14
            r1 = r0
            r0 = r3
            r14 = r12
            r3 = r13
        L_0x0125:
            kotlin.collections.IndexedValue r4 = (kotlin.collections.IndexedValue) r4
            if (r4 == 0) goto L_0x01e3
        L_0x0129:
            int r12 = r4.getIndex()
            r13 = r7[r12]
            java.lang.Object r16 = r4.getValue()
            r7[r12] = r16
            kotlinx.coroutines.internal.Symbol r11 = kotlinx.coroutines.flow.internal.NullSurrogateKt.UNINITIALIZED
            if (r13 != r11) goto L_0x013b
            int r2 = r2 + -1
        L_0x013b:
            byte r11 = r1[r12]
            if (r11 != r0) goto L_0x0140
            goto L_0x014d
        L_0x0140:
            r1[r12] = r0
            java.lang.Object r11 = r5.poll()
            kotlin.collections.IndexedValue r11 = (kotlin.collections.IndexedValue) r11
            if (r11 == 0) goto L_0x014d
            r4 = r11
            r11 = 1
            goto L_0x0129
        L_0x014d:
            if (r2 != 0) goto L_0x01d6
            kotlin.jvm.functions.Function0 r11 = r6.$arrayFactory
            java.lang.Object r11 = r11.invoke()
            java.lang.Object[] r11 = (java.lang.Object[]) r11
            if (r11 != 0) goto L_0x018d
            kotlin.jvm.functions.Function3 r12 = r6.$transform
            kotlinx.coroutines.flow.FlowCollector r13 = r6.$this_combineInternal
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.Array<T>"
            java.util.Objects.requireNonNull(r7, r9)
            r6.L$0 = r14
            r6.I$0 = r15
            r6.L$1 = r7
            r6.L$2 = r5
            r6.L$3 = r3
            r6.I$1 = r2
            r6.L$4 = r1
            r6.I$2 = r0
            r6.L$5 = r4
            r6.L$6 = r11
            r6.label = r10
            java.lang.Object r4 = r12.invoke(r13, r7, r6)
            if (r4 != r8) goto L_0x017f
            return r8
        L_0x017f:
            r4 = r5
            r5 = r7
            r7 = r8
            r12 = r14
            r8 = r15
        L_0x0184:
            r13 = r3
            r14 = r5
            r15 = r8
            r8 = r0
            r0 = r1
            r1 = r4
            r4 = 3
            goto L_0x01de
        L_0x018d:
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.Array<T?>"
            java.util.Objects.requireNonNull(r7, r9)
            r9 = 0
            r16 = 0
            r17 = 0
            r18 = 14
            r21 = 0
            r12 = r7
            r13 = r11
            r10 = r14
            r14 = r9
            r9 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r18 = r21
            kotlin.collections.ArraysKt.copyInto$default((java.lang.Object[]) r12, (java.lang.Object[]) r13, (int) r14, (int) r15, (int) r16, (int) r17, (java.lang.Object) r18)
            kotlin.jvm.functions.Function3 r12 = r6.$transform
            kotlinx.coroutines.flow.FlowCollector r13 = r6.$this_combineInternal
            r6.L$0 = r10
            r6.I$0 = r9
            r6.L$1 = r7
            r6.L$2 = r5
            r6.L$3 = r3
            r6.I$1 = r2
            r6.L$4 = r1
            r6.I$2 = r0
            r6.L$5 = r4
            r6.L$6 = r11
            r4 = 3
            r6.label = r4
            java.lang.Object r11 = r12.invoke(r13, r11, r6)
            if (r11 != r8) goto L_0x01cd
            return r8
        L_0x01cd:
            r12 = r10
        L_0x01ce:
            r13 = r3
            r14 = r7
            r7 = r8
            r15 = r9
        L_0x01d2:
            r8 = r0
            r0 = r1
            r1 = r5
            goto L_0x01de
        L_0x01d6:
            r4 = r9
            r10 = r14
            r9 = r15
            r13 = r3
            r14 = r7
            r7 = r8
            r12 = r10
            goto L_0x01d2
        L_0x01de:
            r9 = r4
            r10 = 2
            r11 = 1
            goto L_0x0100
        L_0x01e3:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
