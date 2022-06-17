package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0007H@"}, mo15462d2 = {"elementAtOrNull", "", "E", "Lkotlinx/coroutines/channels/ReceiveChannel;", "index", "", "continuation", "Lkotlin/coroutines/Continuation;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", mo16175f = "Channels.common.kt", mo16176i = {0, 0, 0, 0, 0, 0}, mo16177l = {257}, mo16178m = "elementAtOrNull", mo16179n = {"$this$elementAtOrNull", "index", "$this$consume$iv", "cause$iv", "$this$consume", "count"}, mo16180s = {"L$0", "I$0", "L$1", "L$2", "L$3", "I$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$elementAtOrNull$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__Channels_commonKt$elementAtOrNull$1(Continuation continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.elementAtOrNull((ReceiveChannel) null, 0, this);
    }
}
