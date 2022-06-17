package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H@"}, mo15462d2 = {"receiveOrClosed", "", "E", "continuation", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/channels/ValueOrClosed;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.AbstractChannel", mo16175f = "AbstractChannel.kt", mo16176i = {0, 0}, mo16177l = {624}, mo16178m = "receiveOrClosed-ZYPwvRU", mo16179n = {"this", "result"}, mo16180s = {"L$0", "L$1"})
/* compiled from: AbstractChannel.kt */
final class AbstractChannel$receiveOrClosed$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AbstractChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AbstractChannel$receiveOrClosed$1(AbstractChannel abstractChannel, Continuation continuation) {
        super(continuation);
        this.this$0 = abstractChannel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m1499receiveOrClosedZYPwvRU(this);
    }
}
