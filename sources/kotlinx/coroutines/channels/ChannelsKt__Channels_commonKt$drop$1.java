package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo15462d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$drop$1", mo16175f = "Channels.common.kt", mo16176i = {0, 0, 1, 1, 2, 2, 2}, mo16177l = {695, 700, 701}, mo16178m = "invokeSuspend", mo16179n = {"$this$produce", "remaining", "$this$produce", "remaining", "$this$produce", "remaining", "e"}, mo16180s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$drop$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

    /* renamed from: $n */
    final /* synthetic */ int f205$n;
    final /* synthetic */ ReceiveChannel $this_drop;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f206p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$drop$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        super(2, continuation);
        this.$this_drop = receiveChannel;
        this.f205$n = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$drop$1 channelsKt__Channels_commonKt$drop$1 = new ChannelsKt__Channels_commonKt$drop$1(this.$this_drop, this.f205$n, continuation);
        channelsKt__Channels_commonKt$drop$1.f206p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$drop$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$drop$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007f, code lost:
        if (r5 == 0) goto L_0x0081;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0097 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0049
            if (r1 == r4) goto L_0x003b
            if (r1 == r3) goto L_0x002c
            if (r1 != r2) goto L_0x0024
            java.lang.Object r1 = r9.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
        L_0x001e:
            r10 = r5
            r7 = r4
            r4 = r1
            r1 = r7
            goto L_0x0089
        L_0x0024:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x002c:
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x009e
        L_0x003b:
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r5 = r9.I$0
            java.lang.Object r6 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0072
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ProducerScope r10 = r9.f206p$
            int r1 = r9.f205$n
            if (r1 < 0) goto L_0x0054
            r5 = r4
            goto L_0x0055
        L_0x0054:
            r5 = 0
        L_0x0055:
            if (r5 == 0) goto L_0x00be
            if (r1 <= 0) goto L_0x0083
            kotlinx.coroutines.channels.ReceiveChannel r5 = r9.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r6 = r10
            r7 = r5
            r5 = r1
            r1 = r7
        L_0x0063:
            r9.L$0 = r6
            r9.I$0 = r5
            r9.L$1 = r1
            r9.label = r4
            java.lang.Object r10 = r1.hasNext(r9)
            if (r10 != r0) goto L_0x0072
            return r0
        L_0x0072:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x0081
            r1.next()
            int r5 = r5 + -1
            if (r5 != 0) goto L_0x0063
        L_0x0081:
            r1 = r5
            r10 = r6
        L_0x0083:
            kotlinx.coroutines.channels.ReceiveChannel r4 = r9.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
        L_0x0089:
            r9.L$0 = r10
            r9.I$0 = r1
            r9.L$1 = r4
            r9.label = r3
            java.lang.Object r5 = r4.hasNext(r9)
            if (r5 != r0) goto L_0x0098
            return r0
        L_0x0098:
            r7 = r5
            r5 = r10
            r10 = r7
            r8 = r4
            r4 = r1
            r1 = r8
        L_0x009e:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00bb
            java.lang.Object r10 = r1.next()
            r9.L$0 = r5
            r9.I$0 = r4
            r9.L$1 = r10
            r9.L$2 = r1
            r9.label = r2
            java.lang.Object r10 = r5.send(r10, r9)
            if (r10 != r0) goto L_0x001e
            return r0
        L_0x00bb:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00be:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Requested element count "
            java.lang.StringBuilder r10 = r10.append(r0)
            int r9 = r9.f205$n
            java.lang.StringBuilder r9 = r10.append(r9)
            java.lang.String r10 = " is less than zero."
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r9 = r9.toString()
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$drop$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
