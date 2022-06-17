package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo15462d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo15463k = 1, mo15464mv = {1, 4, 0})
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_onCompletion$inlined = flow;
        this.$action$inlined = function3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: kotlinx.coroutines.flow.FlowCollector} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.C11231
            if (r0 == 0) goto L_0x0014
            r0 = r11
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.C11231) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1
            r0.<init>(r9, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0088
            if (r2 == r5) goto L_0x0069
            if (r2 == r4) goto L_0x0050
            if (r2 != r3) goto L_0x0048
            java.lang.Object r9 = r0.L$4
            kotlinx.coroutines.flow.internal.SafeCollector r9 = (kotlinx.coroutines.flow.internal.SafeCollector) r9
            java.lang.Object r10 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r10 = r0.L$2
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 r10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) r10
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0045 }
            goto L_0x00ca
        L_0x0045:
            r10 = move-exception
            goto L_0x00d2
        L_0x0048:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0050:
            java.lang.Object r9 = r0.L$4
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r10 = r0.L$2
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 r10 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00f6
        L_0x0069:
            java.lang.Object r9 = r0.L$3
            r10 = r9
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r9 = r0.L$2
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 r5 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) r5
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0080 }
            r11 = r9
            r9 = r5
            goto L_0x00a2
        L_0x0080:
            r11 = move-exception
            r7 = r11
            r11 = r9
            r9 = r7
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x00da
        L_0x0088:
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r0
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            kotlinx.coroutines.flow.Flow r2 = r9.$this_onCompletion$inlined     // Catch:{ all -> 0x00d6 }
            r0.L$0 = r9     // Catch:{ all -> 0x00d6 }
            r0.L$1 = r10     // Catch:{ all -> 0x00d6 }
            r0.L$2 = r11     // Catch:{ all -> 0x00d6 }
            r0.L$3 = r10     // Catch:{ all -> 0x00d6 }
            r0.label = r5     // Catch:{ all -> 0x00d6 }
            java.lang.Object r2 = r2.collect(r10, r0)     // Catch:{ all -> 0x00d6 }
            if (r2 != r1) goto L_0x00a1
            return r1
        L_0x00a1:
            r2 = r10
        L_0x00a2:
            kotlin.coroutines.CoroutineContext r4 = r0.getContext()
            kotlinx.coroutines.flow.internal.SafeCollector r5 = new kotlinx.coroutines.flow.internal.SafeCollector
            r5.<init>(r10, r4)
            kotlin.jvm.functions.Function3 r4 = r9.$action$inlined     // Catch:{ all -> 0x00d0 }
            r6 = 0
            r0.L$0 = r9     // Catch:{ all -> 0x00d0 }
            r0.L$1 = r2     // Catch:{ all -> 0x00d0 }
            r0.L$2 = r11     // Catch:{ all -> 0x00d0 }
            r0.L$3 = r10     // Catch:{ all -> 0x00d0 }
            r0.L$4 = r5     // Catch:{ all -> 0x00d0 }
            r0.label = r3     // Catch:{ all -> 0x00d0 }
            r9 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r9)     // Catch:{ all -> 0x00d0 }
            java.lang.Object r9 = r4.invoke(r5, r6, r0)     // Catch:{ all -> 0x00d0 }
            r10 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r10)     // Catch:{ all -> 0x00d0 }
            if (r9 != r1) goto L_0x00c9
            return r1
        L_0x00c9:
            r9 = r5
        L_0x00ca:
            r9.releaseIntercepted()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00d0:
            r10 = move-exception
            r9 = r5
        L_0x00d2:
            r9.releaseIntercepted()
            throw r10
        L_0x00d6:
            r2 = move-exception
            r5 = r9
            r9 = r2
            r2 = r10
        L_0x00da:
            kotlinx.coroutines.flow.ThrowingCollector r3 = new kotlinx.coroutines.flow.ThrowingCollector
            r3.<init>(r9)
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.jvm.functions.Function3 r6 = r5.$action$inlined
            r0.L$0 = r5
            r0.L$1 = r10
            r0.L$2 = r11
            r0.L$3 = r2
            r0.L$4 = r9
            r0.label = r4
            java.lang.Object r10 = kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(r3, r6, r9, r0)
            if (r10 != r1) goto L_0x00f6
            return r1
        L_0x00f6:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
