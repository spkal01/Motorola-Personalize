package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

@Metadata(mo15461d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo15462d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 5, 1})
@DebugMetadata(mo16174c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", mo16175f = "SlidingWindow.kt", mo16176i = {}, mo16177l = {34, 40, 49, 55, 58}, mo16178m = "invokeSuspend", mo16179n = {}, mo16180s = {})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.$size = i;
        this.$step = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SlidingWindowKt$windowedIterator$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x012a A[SYNTHETIC] */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0056
            if (r1 == r6) goto L_0x0044
            if (r1 == r5) goto L_0x003f
            if (r1 == r4) goto L_0x002e
            if (r1 == r3) goto L_0x0021
            if (r1 != r2) goto L_0x0019
            goto L_0x003f
        L_0x0019:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0021:
            java.lang.Object r1 = r11.L$1
            kotlin.collections.RingBuffer r1 = (kotlin.collections.RingBuffer) r1
            java.lang.Object r4 = r11.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0159
        L_0x002e:
            java.lang.Object r1 = r11.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r5 = r11.L$1
            kotlin.collections.RingBuffer r5 = (kotlin.collections.RingBuffer) r5
            java.lang.Object r8 = r11.L$0
            kotlin.sequences.SequenceScope r8 = (kotlin.sequences.SequenceScope) r8
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0124
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0178
        L_0x0044:
            int r1 = r11.I$0
            java.lang.Object r2 = r11.L$2
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r11.L$1
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            java.lang.Object r4 = r11.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00a4
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlin.sequences.SequenceScope r12 = (kotlin.sequences.SequenceScope) r12
            int r1 = r11.$size
            r8 = 1024(0x400, float:1.435E-42)
            int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r1, (int) r8)
            int r8 = r11.$step
            int r9 = r11.$size
            int r8 = r8 - r9
            if (r8 < 0) goto L_0x00db
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r1)
            r1 = 0
            java.util.Iterator r3 = r11.$iterator
            r4 = r12
            r10 = r3
            r3 = r2
            r2 = r10
        L_0x0078:
            boolean r12 = r2.hasNext()
            if (r12 == 0) goto L_0x00b6
            java.lang.Object r12 = r2.next()
            if (r1 <= 0) goto L_0x0087
            int r1 = r1 + -1
            goto L_0x0078
        L_0x0087:
            r3.add(r12)
            int r12 = r3.size()
            int r9 = r11.$size
            if (r12 != r9) goto L_0x0078
            r11.L$0 = r4
            r11.L$1 = r3
            r11.L$2 = r2
            r11.I$0 = r8
            r11.label = r6
            java.lang.Object r12 = r4.yield(r3, r11)
            if (r12 != r0) goto L_0x00a3
            return r0
        L_0x00a3:
            r1 = r8
        L_0x00a4:
            boolean r12 = r11.$reuseBuffer
            if (r12 == 0) goto L_0x00ac
            r3.clear()
            goto L_0x00b4
        L_0x00ac:
            java.util.ArrayList r12 = new java.util.ArrayList
            int r3 = r11.$size
            r12.<init>(r3)
            r3 = r12
        L_0x00b4:
            r8 = r1
            goto L_0x0078
        L_0x00b6:
            r12 = r3
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            r12 = r12 ^ r6
            if (r12 == 0) goto L_0x0178
            boolean r12 = r11.$partialWindows
            if (r12 != 0) goto L_0x00cc
            int r12 = r3.size()
            int r1 = r11.$size
            if (r12 != r1) goto L_0x0178
        L_0x00cc:
            r11.L$0 = r7
            r11.L$1 = r7
            r11.L$2 = r7
            r11.label = r5
            java.lang.Object r11 = r4.yield(r3, r11)
            if (r11 != r0) goto L_0x0178
            return r0
        L_0x00db:
            kotlin.collections.RingBuffer r5 = new kotlin.collections.RingBuffer
            r5.<init>(r1)
            java.util.Iterator r1 = r11.$iterator
            r8 = r12
        L_0x00e3:
            boolean r12 = r1.hasNext()
            if (r12 == 0) goto L_0x012a
            java.lang.Object r12 = r1.next()
            r5.add(r12)
            boolean r12 = r5.isFull()
            if (r12 == 0) goto L_0x00e3
            int r12 = r5.size()
            int r9 = r11.$size
            if (r12 >= r9) goto L_0x0103
            kotlin.collections.RingBuffer r5 = r5.expanded(r9)
            goto L_0x00e3
        L_0x0103:
            boolean r12 = r11.$reuseBuffer
            if (r12 == 0) goto L_0x010b
            r12 = r5
            java.util.List r12 = (java.util.List) r12
            goto L_0x0115
        L_0x010b:
            java.util.ArrayList r12 = new java.util.ArrayList
            r9 = r5
            java.util.Collection r9 = (java.util.Collection) r9
            r12.<init>(r9)
            java.util.List r12 = (java.util.List) r12
        L_0x0115:
            r11.L$0 = r8
            r11.L$1 = r5
            r11.L$2 = r1
            r11.label = r4
            java.lang.Object r12 = r8.yield(r12, r11)
            if (r12 != r0) goto L_0x0124
            return r0
        L_0x0124:
            int r12 = r11.$step
            r5.removeFirst(r12)
            goto L_0x00e3
        L_0x012a:
            boolean r12 = r11.$partialWindows
            if (r12 == 0) goto L_0x0178
            r1 = r5
            r4 = r8
        L_0x0130:
            int r12 = r1.size()
            int r5 = r11.$step
            if (r12 <= r5) goto L_0x015f
            boolean r12 = r11.$reuseBuffer
            if (r12 == 0) goto L_0x0140
            r12 = r1
            java.util.List r12 = (java.util.List) r12
            goto L_0x014a
        L_0x0140:
            java.util.ArrayList r12 = new java.util.ArrayList
            r5 = r1
            java.util.Collection r5 = (java.util.Collection) r5
            r12.<init>(r5)
            java.util.List r12 = (java.util.List) r12
        L_0x014a:
            r11.L$0 = r4
            r11.L$1 = r1
            r11.L$2 = r7
            r11.label = r3
            java.lang.Object r12 = r4.yield(r12, r11)
            if (r12 != r0) goto L_0x0159
            return r0
        L_0x0159:
            int r12 = r11.$step
            r1.removeFirst(r12)
            goto L_0x0130
        L_0x015f:
            r12 = r1
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            r12 = r12 ^ r6
            if (r12 == 0) goto L_0x0178
            r11.L$0 = r7
            r11.L$1 = r7
            r11.L$2 = r7
            r11.label = r2
            java.lang.Object r11 = r4.yield(r1, r11)
            if (r11 != r0) goto L_0x0178
            return r0
        L_0x0178:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
