package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo15462d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo15463k = 1, mo15464mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C1232xfa64eeb5 implements FlowCollector<T> {
    final /* synthetic */ Ref.ObjectRef $previousFlow$inlined;
    final /* synthetic */ CoroutineScope $this_flowScope$inlined;
    final /* synthetic */ ChannelFlowTransformLatest$flowCollect$3 this$0;

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.internal.C1232xfa64eeb5.C12331
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.internal.C1232xfa64eeb5.C12331) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0049
            if (r2 != r3) goto L_0x0041
            java.lang.Object r7 = r0.L$5
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7
            java.lang.Object r7 = r0.L$4
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7
            java.lang.Object r7 = r0.L$3
            java.lang.Object r8 = r0.L$2
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            java.lang.Object r8 = r0.L$1
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 r8 = (kotlinx.coroutines.flow.internal.C1232xfa64eeb5) r8
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r8
            r8 = r7
            r7 = r6
            goto L_0x0076
        L_0x0041:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlin.jvm.internal.Ref$ObjectRef r2 = r7.$previousFlow$inlined
            T r2 = r2.element
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            if (r2 == 0) goto L_0x0076
            kotlinx.coroutines.flow.internal.ChildCancelledException r4 = new kotlinx.coroutines.flow.internal.ChildCancelledException
            r4.<init>()
            java.util.concurrent.CancellationException r4 = (java.util.concurrent.CancellationException) r4
            r2.cancel((java.util.concurrent.CancellationException) r4)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.L$4 = r2
            r0.L$5 = r2
            r0.label = r3
            java.lang.Object r9 = r2.join(r0)
            if (r9 != r1) goto L_0x0076
            return r1
        L_0x0076:
            kotlin.jvm.internal.Ref$ObjectRef r9 = r7.$previousFlow$inlined
            kotlinx.coroutines.CoroutineScope r0 = r7.$this_flowScope$inlined
            r1 = 0
            kotlinx.coroutines.CoroutineStart r2 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1 r3 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1
            r4 = 0
            r3.<init>(r8, r4, r7)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = 1
            r5 = 0
            kotlinx.coroutines.Job r7 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r0, r1, r2, r3, r4, r5)
            r9.element = r7
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.C1232xfa64eeb5.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public C1232xfa64eeb5(ChannelFlowTransformLatest$flowCollect$3 channelFlowTransformLatest$flowCollect$3, CoroutineScope coroutineScope, Ref.ObjectRef objectRef) {
        this.this$0 = channelFlowTransformLatest$flowCollect$3;
        this.$this_flowScope$inlined = coroutineScope;
        this.$previousFlow$inlined = objectRef;
    }
}
