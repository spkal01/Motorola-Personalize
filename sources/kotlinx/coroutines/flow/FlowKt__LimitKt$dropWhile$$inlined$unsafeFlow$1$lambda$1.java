package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, mo15462d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__LimitKt$$special$$inlined$collect$2"}, mo15463k = 1, mo15464mv = {1, 4, 0})
/* compiled from: Collect.kt */
public final class FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1 implements FlowCollector<T> {
    final /* synthetic */ Ref.BooleanRef $matched$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 this$0;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.C11331
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.C11331) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0059
            if (r2 == r5) goto L_0x0049
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            goto L_0x0049
        L_0x002d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0035:
            java.lang.Object r8 = r0.L$3
            java.lang.Object r7 = r0.L$2
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            java.lang.Object r2 = r0.L$1
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1 r4 = (kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1) r4
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r9
            r9 = r7
            r7 = r4
            r4 = r6
            goto L_0x0097
        L_0x0049:
            java.lang.Object r7 = r0.L$3
            java.lang.Object r7 = r0.L$2
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            java.lang.Object r7 = r0.L$1
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1 r7 = (kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00b6
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlin.jvm.internal.Ref$BooleanRef r2 = r7.$matched$inlined
            boolean r2 = r2.element
            if (r2 == 0) goto L_0x0078
            kotlinx.coroutines.flow.FlowCollector r2 = r7.$this_unsafeFlow$inlined
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.label = r5
            java.lang.Object r7 = r2.emit(r8, r0)
            if (r7 != r1) goto L_0x00b6
            return r1
        L_0x0078:
            kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 r2 = r7.this$0
            kotlin.jvm.functions.Function2 r2 = r2.$predicate$inlined
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.label = r4
            r4 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r4)
            java.lang.Object r2 = r2.invoke(r8, r0)
            r4 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r4)
            if (r2 != r1) goto L_0x0095
            return r1
        L_0x0095:
            r4 = r2
            r2 = r8
        L_0x0097:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L_0x00b6
            kotlin.jvm.internal.Ref$BooleanRef r4 = r7.$matched$inlined
            r4.element = r5
            kotlinx.coroutines.flow.FlowCollector r4 = r7.$this_unsafeFlow$inlined
            r0.L$0 = r7
            r0.L$1 = r2
            r0.L$2 = r9
            r0.L$3 = r8
            r0.label = r3
            java.lang.Object r7 = r4.emit(r8, r0)
            if (r7 != r1) goto L_0x00b6
            return r1
        L_0x00b6:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Ref.BooleanRef booleanRef, FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$matched$inlined = booleanRef;
        this.this$0 = flowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1;
    }
}
