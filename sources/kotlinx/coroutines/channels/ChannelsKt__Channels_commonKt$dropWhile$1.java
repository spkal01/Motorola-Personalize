package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo15462d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$dropWhile$1", mo16175f = "Channels.common.kt", mo16176i = {0, 1, 1, 2, 2, 3, 4, 4}, mo16177l = {720, 721, 722, 726, 727}, mo16178m = "invokeSuspend", mo16179n = {"$this$produce", "$this$produce", "e", "$this$produce", "e", "$this$produce", "$this$produce", "e"}, mo16180s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$dropWhile$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_dropWhile;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f207p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$dropWhile$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_dropWhile = receiveChannel;
        this.$predicate = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$dropWhile$1 channelsKt__Channels_commonKt$dropWhile$1 = new ChannelsKt__Channels_commonKt$dropWhile$1(this.$this_dropWhile, this.$predicate, continuation);
        channelsKt__Channels_commonKt$dropWhile$1.f207p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$dropWhile$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$dropWhile$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0079 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0061
            if (r1 == r6) goto L_0x0055
            if (r1 == r5) goto L_0x0044
            if (r1 == r4) goto L_0x003b
            if (r1 == r3) goto L_0x002e
            if (r1 != r2) goto L_0x0026
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
        L_0x0022:
            r11 = r1
            r7 = r4
            goto L_0x00b7
        L_0x0026:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x002e:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c8
        L_0x003b:
            java.lang.Object r1 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00b0
        L_0x0044:
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r7 = r10.L$1
            java.lang.Object r8 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r8
            r8 = r7
            r7 = r9
            goto L_0x009a
        L_0x0055:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r7 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x007a
        L_0x0061:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.channels.ProducerScope r11 = r10.f207p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r10.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r7 = r11
        L_0x006d:
            r10.L$0 = r7
            r10.L$1 = r1
            r10.label = r6
            java.lang.Object r11 = r1.hasNext(r10)
            if (r11 != r0) goto L_0x007a
            return r0
        L_0x007a:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00b1
            java.lang.Object r11 = r1.next()
            kotlin.jvm.functions.Function2 r8 = r10.$predicate
            r10.L$0 = r7
            r10.L$1 = r11
            r10.L$2 = r1
            r10.label = r5
            java.lang.Object r8 = r8.invoke(r11, r10)
            if (r8 != r0) goto L_0x0097
            return r0
        L_0x0097:
            r9 = r8
            r8 = r11
            r11 = r9
        L_0x009a:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 != 0) goto L_0x006d
            r10.L$0 = r7
            r10.L$1 = r8
            r10.label = r4
            java.lang.Object r11 = r7.send(r8, r10)
            if (r11 != r0) goto L_0x00af
            return r0
        L_0x00af:
            r1 = r7
        L_0x00b0:
            r7 = r1
        L_0x00b1:
            kotlinx.coroutines.channels.ReceiveChannel r11 = r10.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r11 = r11.iterator()
        L_0x00b7:
            r10.L$0 = r7
            r10.L$1 = r11
            r10.label = r3
            java.lang.Object r1 = r11.hasNext(r10)
            if (r1 != r0) goto L_0x00c4
            return r0
        L_0x00c4:
            r4 = r7
            r9 = r1
            r1 = r11
            r11 = r9
        L_0x00c8:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00e3
            java.lang.Object r11 = r1.next()
            r10.L$0 = r4
            r10.L$1 = r11
            r10.L$2 = r1
            r10.label = r2
            java.lang.Object r11 = r4.send(r11, r10)
            if (r11 != r0) goto L_0x0022
            return r0
        L_0x00e3:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$dropWhile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
