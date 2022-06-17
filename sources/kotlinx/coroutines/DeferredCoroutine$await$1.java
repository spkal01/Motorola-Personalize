package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H@"}, mo15462d2 = {"await", "", "T", "continuation", "Lkotlin/coroutines/Continuation;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.DeferredCoroutine", mo16175f = "Builders.common.kt", mo16176i = {0}, mo16177l = {101}, mo16178m = "await$suspendImpl", mo16179n = {"this"}, mo16180s = {"L$0"})
/* compiled from: Builders.common.kt */
final class DeferredCoroutine$await$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DeferredCoroutine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeferredCoroutine$await$1(DeferredCoroutine deferredCoroutine, Continuation continuation) {
        super(continuation);
        this.this$0 = deferredCoroutine;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DeferredCoroutine.await$suspendImpl(this.this$0, this);
    }
}
