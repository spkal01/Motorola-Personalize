package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ChannelIterator;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002 \u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H@"}, mo15462d2 = {"next0", "", "E", "continuation", "Lkotlin/coroutines/Continuation;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.ChannelIterator$DefaultImpls", mo16175f = "Channel.kt", mo16176i = {0}, mo16177l = {458}, mo16178m = "next", mo16179n = {"$this"}, mo16180s = {"L$0"})
/* compiled from: Channel.kt */
final class ChannelIterator$next0$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ChannelIterator this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelIterator$next0$1(ChannelIterator channelIterator, Continuation continuation) {
        super(continuation);
        this.this$0 = channelIterator;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelIterator.DefaultImpls.next((ChannelIterator) null, this);
    }
}
