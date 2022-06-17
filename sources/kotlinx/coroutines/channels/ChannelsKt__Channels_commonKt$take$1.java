package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo15462d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$take$1", mo16175f = "Channels.common.kt", mo16176i = {0, 0, 1, 1, 1}, mo16177l = {989, 990}, mo16178m = "invokeSuspend", mo16179n = {"$this$produce", "remaining", "$this$produce", "remaining", "e"}, mo16180s = {"L$0", "I$0", "L$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$take$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

    /* renamed from: $n */
    final /* synthetic */ int f213$n;
    final /* synthetic */ ReceiveChannel $this_take;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f214p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$take$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        super(2, continuation);
        this.$this_take = receiveChannel;
        this.f213$n = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$take$1 channelsKt__Channels_commonKt$take$1 = new ChannelsKt__Channels_commonKt$take$1(this.$this_take, this.f213$n, continuation);
        channelsKt__Channels_commonKt$take$1.f214p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$take$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$take$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 == r3) goto L_0x0025
            if (r1 != r2) goto L_0x001d
            java.lang.Object r1 = r8.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r4 = r8.I$0
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r9)
        L_0x001b:
            r9 = r5
            goto L_0x007e
        L_0x001d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0025:
            java.lang.Object r1 = r8.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r4 = r8.I$0
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0061
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.ProducerScope r9 = r8.f214p$
            int r1 = r8.f213$n
            if (r1 != 0) goto L_0x003f
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x003f:
            if (r1 < 0) goto L_0x0043
            r4 = r3
            goto L_0x0044
        L_0x0043:
            r4 = 0
        L_0x0044:
            if (r4 == 0) goto L_0x008c
            kotlinx.coroutines.channels.ReceiveChannel r4 = r8.$this_take
            kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
        L_0x004c:
            r8.L$0 = r9
            r8.I$0 = r1
            r8.L$1 = r4
            r8.label = r3
            java.lang.Object r5 = r4.hasNext(r8)
            if (r5 != r0) goto L_0x005b
            return r0
        L_0x005b:
            r6 = r5
            r5 = r9
            r9 = r6
            r7 = r4
            r4 = r1
            r1 = r7
        L_0x0061:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x0089
            java.lang.Object r9 = r1.next()
            r8.L$0 = r5
            r8.I$0 = r4
            r8.L$1 = r9
            r8.L$2 = r1
            r8.label = r2
            java.lang.Object r9 = r5.send(r9, r8)
            if (r9 != r0) goto L_0x001b
            return r0
        L_0x007e:
            int r4 = r4 + -1
            if (r4 != 0) goto L_0x0085
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0085:
            r6 = r4
            r4 = r1
            r1 = r6
            goto L_0x004c
        L_0x0089:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x008c:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Requested element count "
            java.lang.StringBuilder r9 = r9.append(r0)
            int r8 = r8.f213$n
            java.lang.StringBuilder r8 = r9.append(r8)
            java.lang.String r9 = " is less than zero."
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$take$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
