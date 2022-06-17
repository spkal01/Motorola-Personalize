package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, mo15462d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__MergeKt$unsafeTransform$$inlined$unsafeFlow$2", "kotlinx/coroutines/flow/FlowKt__MergeKt$map$$inlined$unsafeTransform$2"}, mo15463k = 1, mo15464mv = {1, 4, 0})
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__MergeKt$flatMapMerge$$inlined$map$1 implements Flow<Flow<? extends R>> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ Function2 $transform$inlined$1;

    public FlowKt__MergeKt$flatMapMerge$$inlined$map$1(Flow flow, Function2 function2) {
        this.$this_unsafeTransform$inlined = flow;
        this.$transform$inlined$1 = function2;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_unsafeTransform$inlined.collect(new FlowCollector<T>() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x0070  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x00af A[RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
                /*
                    r10 = this;
                    boolean r0 = r12 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412.C11421
                    if (r0 == 0) goto L_0x0014
                    r0 = r12
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412.C11421) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r12 = r0.label
                    int r12 = r12 - r2
                    r0.label = r12
                    goto L_0x0019
                L_0x0014:
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1
                    r0.<init>(r10, r12)
                L_0x0019:
                    java.lang.Object r12 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L_0x0070
                    if (r2 == r4) goto L_0x004c
                    if (r2 != r3) goto L_0x0044
                    java.lang.Object r10 = r0.L$6
                    kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                    java.lang.Object r10 = r0.L$5
                    java.lang.Object r10 = r0.L$4
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r10 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412.C11421) r10
                    java.lang.Object r10 = r0.L$3
                    java.lang.Object r10 = r0.L$2
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r10 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412.C11421) r10
                    java.lang.Object r10 = r0.L$1
                    java.lang.Object r10 = r0.L$0
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2 r10 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412) r10
                    kotlin.ResultKt.throwOnFailure(r12)
                    goto L_0x00b0
                L_0x0044:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r11)
                    throw r10
                L_0x004c:
                    java.lang.Object r10 = r0.L$7
                    kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                    java.lang.Object r11 = r0.L$6
                    kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                    java.lang.Object r2 = r0.L$5
                    java.lang.Object r4 = r0.L$4
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r4 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412.C11421) r4
                    java.lang.Object r5 = r0.L$3
                    java.lang.Object r6 = r0.L$2
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 r6 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412.C11421) r6
                    java.lang.Object r7 = r0.L$1
                    java.lang.Object r8 = r0.L$0
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2 r8 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412) r8
                    kotlin.ResultKt.throwOnFailure(r12)
                    r9 = r12
                    r12 = r11
                    r11 = r7
                    r7 = r6
                    r6 = r4
                    r4 = r9
                    goto L_0x0099
                L_0x0070:
                    kotlin.ResultKt.throwOnFailure(r12)
                    kotlinx.coroutines.flow.FlowCollector r12 = r3
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1 r2 = r2
                    kotlin.jvm.functions.Function2 r2 = r2.$transform$inlined$1
                    r0.L$0 = r10
                    r0.L$1 = r11
                    r0.L$2 = r0
                    r0.L$3 = r11
                    r0.L$4 = r0
                    r0.L$5 = r11
                    r0.L$6 = r12
                    r0.L$7 = r12
                    r0.label = r4
                    java.lang.Object r2 = r2.invoke(r11, r0)
                    if (r2 != r1) goto L_0x0092
                    return r1
                L_0x0092:
                    r8 = r10
                    r5 = r11
                    r10 = r12
                    r6 = r0
                    r7 = r6
                    r4 = r2
                    r2 = r5
                L_0x0099:
                    r0.L$0 = r8
                    r0.L$1 = r11
                    r0.L$2 = r7
                    r0.L$3 = r5
                    r0.L$4 = r6
                    r0.L$5 = r2
                    r0.L$6 = r12
                    r0.label = r3
                    java.lang.Object r10 = r10.emit(r4, r0)
                    if (r10 != r1) goto L_0x00b0
                    return r1
                L_0x00b0:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1.C11412.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
