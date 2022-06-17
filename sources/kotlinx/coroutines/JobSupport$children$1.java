package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo15462d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lkotlinx/coroutines/ChildJob;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.JobSupport$children$1", mo16175f = "JobSupport.kt", mo16176i = {0, 0, 1, 1, 1, 1, 1, 1}, mo16177l = {949, 951}, mo16178m = "invokeSuspend", mo16179n = {"$this$sequence", "state", "$this$sequence", "state", "list", "this_$iv", "cur$iv", "it"}, mo16180s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: JobSupport.kt */
final class JobSupport$children$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super ChildJob>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private SequenceScope f201p$;
    final /* synthetic */ JobSupport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JobSupport$children$1(JobSupport jobSupport, Continuation continuation) {
        super(2, continuation);
        this.this$0 = jobSupport;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(this.this$0, continuation);
        jobSupport$children$1.f201p$ = (SequenceScope) obj;
        return jobSupport$children$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((JobSupport$children$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x003a
            if (r1 == r3) goto L_0x0031
            if (r1 != r2) goto L_0x0029
            java.lang.Object r1 = r11.L$5
            kotlinx.coroutines.ChildHandleNode r1 = (kotlinx.coroutines.ChildHandleNode) r1
            java.lang.Object r1 = r11.L$4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            java.lang.Object r4 = r11.L$3
            kotlinx.coroutines.internal.LockFreeLinkedListHead r4 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r4
            java.lang.Object r5 = r11.L$2
            kotlinx.coroutines.NodeList r5 = (kotlinx.coroutines.NodeList) r5
            java.lang.Object r6 = r11.L$1
            java.lang.Object r7 = r11.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00a2
        L_0x0029:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0031:
            java.lang.Object r11 = r11.L$0
            kotlin.sequences.SequenceScope r11 = (kotlin.sequences.SequenceScope) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00a7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.sequences.SequenceScope r12 = r11.f201p$
            kotlinx.coroutines.JobSupport r1 = r11.this$0
            java.lang.Object r1 = r1.getState$kotlinx_coroutines_core()
            boolean r4 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r4 == 0) goto L_0x005b
            r2 = r1
            kotlinx.coroutines.ChildHandleNode r2 = (kotlinx.coroutines.ChildHandleNode) r2
            kotlinx.coroutines.ChildJob r2 = r2.childJob
            r11.L$0 = r12
            r11.L$1 = r1
            r11.label = r3
            java.lang.Object r11 = r12.yield(r2, r11)
            if (r11 != r0) goto L_0x00a7
            return r0
        L_0x005b:
            boolean r4 = r1 instanceof kotlinx.coroutines.Incomplete
            if (r4 == 0) goto L_0x00a7
            r4 = r1
            kotlinx.coroutines.Incomplete r4 = (kotlinx.coroutines.Incomplete) r4
            kotlinx.coroutines.NodeList r4 = r4.getList()
            if (r4 == 0) goto L_0x00a7
            r5 = r4
            kotlinx.coroutines.internal.LockFreeLinkedListHead r5 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r5
            java.lang.Object r6 = r5.getNext()
            java.lang.String r7 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            java.util.Objects.requireNonNull(r6, r7)
            kotlinx.coroutines.internal.LockFreeLinkedListNode r6 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r6
            r7 = r12
            r9 = r6
            r6 = r1
            r1 = r9
            r10 = r5
            r5 = r4
            r4 = r10
        L_0x007d:
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
            r12 = r12 ^ r3
            if (r12 == 0) goto L_0x00a7
            boolean r12 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r12 == 0) goto L_0x00a2
            r12 = r1
            kotlinx.coroutines.ChildHandleNode r12 = (kotlinx.coroutines.ChildHandleNode) r12
            kotlinx.coroutines.ChildJob r8 = r12.childJob
            r11.L$0 = r7
            r11.L$1 = r6
            r11.L$2 = r5
            r11.L$3 = r4
            r11.L$4 = r1
            r11.L$5 = r12
            r11.label = r2
            java.lang.Object r12 = r7.yield(r8, r11)
            if (r12 != r0) goto L_0x00a2
            return r0
        L_0x00a2:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = r1.getNextNode()
            goto L_0x007d
        L_0x00a7:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport$children$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
