package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo15462d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1", mo16175f = "Channels.common.kt", mo16176i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, mo16177l = {2196, 1395, 1395}, mo16178m = "invokeSuspend", mo16179n = {"$this$produce", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "$this$produce", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "it", "$this$produce", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "it"}, mo16180s = {"L$0", "L$1", "L$3", "L$4", "L$5", "L$0", "L$1", "L$3", "L$4", "L$5", "L$7", "L$8", "L$0", "L$1", "L$3", "L$4", "L$5", "L$7", "L$8"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$map$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_map;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;

    /* renamed from: p$ */
    private ProducerScope f211p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$map$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_map = receiveChannel;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$map$1 channelsKt__Channels_commonKt$map$1 = new ChannelsKt__Channels_commonKt$map$1(this.$this_map, this.$transform, continuation);
        channelsKt__Channels_commonKt$map$1.f211p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$map$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$map$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e6 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0132 A[Catch:{ all -> 0x0142 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x00ab
            if (r2 == r5) goto L_0x0082
            if (r2 == r4) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            java.lang.Object r2 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$4
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1 r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r9
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r0.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0146 }
            r16 = r1
            r1 = r0
            r0 = r9
            r9 = r7
            r7 = r2
            r2 = r16
            r17 = r8
            r8 = r6
            r6 = r11
            r11 = r10
            r10 = r17
            goto L_0x00bf
        L_0x0043:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004b:
            java.lang.Object r2 = r0.L$9
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            java.lang.Object r6 = r0.L$8
            java.lang.Object r7 = r0.L$7
            java.lang.Object r8 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r0.L$4
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            java.lang.Object r11 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            java.lang.Object r12 = r0.L$2
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1 r12 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r12
            java.lang.Object r13 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r13 = (kotlinx.coroutines.channels.ReceiveChannel) r13
            java.lang.Object r14 = r0.L$0
            kotlinx.coroutines.channels.ProducerScope r14 = (kotlinx.coroutines.channels.ProducerScope) r14
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x007d }
            r15 = r12
            r12 = r6
            r6 = r9
            r9 = r7
            r7 = r10
            r10 = r11
            r11 = r13
            r13 = r19
            goto L_0x0117
        L_0x007d:
            r0 = move-exception
            r1 = r0
            r8 = r11
            goto L_0x0148
        L_0x0082:
            java.lang.Object r2 = r0.L$6
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$5
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$4
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1 r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1) r9
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r0.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0146 }
            r12 = r19
            r16 = r8
            r8 = r6
            r6 = r11
            r11 = r10
            r10 = r16
            goto L_0x00de
        L_0x00ab:
            kotlin.ResultKt.throwOnFailure(r19)
            kotlinx.coroutines.channels.ProducerScope r2 = r0.f211p$
            kotlinx.coroutines.channels.ReceiveChannel r8 = r0.$this_map
            r6 = 0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlinx.coroutines.channels.ChannelIterator r7 = r8.iterator()     // Catch:{ all -> 0x0146 }
            r9 = r6
            r10 = r8
            r11 = r10
            r6 = r2
            r2 = r1
            r1 = r0
        L_0x00bf:
            r1.L$0 = r6     // Catch:{ all -> 0x0142 }
            r1.L$1 = r11     // Catch:{ all -> 0x0142 }
            r1.L$2 = r0     // Catch:{ all -> 0x0142 }
            r1.L$3 = r10     // Catch:{ all -> 0x0142 }
            r1.L$4 = r9     // Catch:{ all -> 0x0142 }
            r1.L$5 = r8     // Catch:{ all -> 0x0142 }
            r1.L$6 = r7     // Catch:{ all -> 0x0142 }
            r1.label = r5     // Catch:{ all -> 0x0142 }
            java.lang.Object r12 = r7.hasNext(r0)     // Catch:{ all -> 0x0142 }
            if (r12 != r2) goto L_0x00d6
            return r2
        L_0x00d6:
            r16 = r9
            r9 = r0
            r0 = r1
            r1 = r2
            r2 = r7
            r7 = r16
        L_0x00de:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ all -> 0x0142 }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x0142 }
            if (r12 == 0) goto L_0x013a
            java.lang.Object r12 = r2.next()     // Catch:{ all -> 0x0142 }
            kotlin.jvm.functions.Function2 r13 = r0.$transform     // Catch:{ all -> 0x0142 }
            r0.L$0 = r6     // Catch:{ all -> 0x0142 }
            r0.L$1 = r11     // Catch:{ all -> 0x0142 }
            r0.L$2 = r9     // Catch:{ all -> 0x0142 }
            r0.L$3 = r10     // Catch:{ all -> 0x0142 }
            r0.L$4 = r7     // Catch:{ all -> 0x0142 }
            r0.L$5 = r8     // Catch:{ all -> 0x0142 }
            r0.L$6 = r2     // Catch:{ all -> 0x0142 }
            r0.L$7 = r12     // Catch:{ all -> 0x0142 }
            r0.L$8 = r12     // Catch:{ all -> 0x0142 }
            r0.L$9 = r6     // Catch:{ all -> 0x0142 }
            r0.label = r4     // Catch:{ all -> 0x0142 }
            r14 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r14)     // Catch:{ all -> 0x0142 }
            java.lang.Object r13 = r13.invoke(r12, r0)     // Catch:{ all -> 0x0142 }
            r14 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r14)     // Catch:{ all -> 0x0142 }
            if (r13 != r1) goto L_0x0111
            return r1
        L_0x0111:
            r14 = r6
            r15 = r9
            r9 = r12
            r6 = r8
            r8 = r2
            r2 = r14
        L_0x0117:
            r0.L$0 = r14     // Catch:{ all -> 0x0142 }
            r0.L$1 = r11     // Catch:{ all -> 0x0142 }
            r0.L$2 = r15     // Catch:{ all -> 0x0142 }
            r0.L$3 = r10     // Catch:{ all -> 0x0142 }
            r0.L$4 = r7     // Catch:{ all -> 0x0142 }
            r0.L$5 = r6     // Catch:{ all -> 0x0142 }
            r0.L$6 = r8     // Catch:{ all -> 0x0142 }
            r0.L$7 = r9     // Catch:{ all -> 0x0142 }
            r0.L$8 = r12     // Catch:{ all -> 0x0142 }
            r0.label = r3     // Catch:{ all -> 0x0142 }
            java.lang.Object r2 = r2.send(r13, r0)     // Catch:{ all -> 0x0142 }
            if (r2 != r1) goto L_0x0132
            return r1
        L_0x0132:
            r2 = r1
            r9 = r7
            r7 = r8
            r1 = r0
            r8 = r6
            r6 = r14
            r0 = r15
            goto L_0x00bf
        L_0x013a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0142 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r7)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0142:
            r0 = move-exception
            r1 = r0
            r8 = r10
            goto L_0x0148
        L_0x0146:
            r0 = move-exception
            r1 = r0
        L_0x0148:
            throw r1     // Catch:{ all -> 0x0149 }
        L_0x0149:
            r0 = move-exception
            r2 = r0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$map$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
