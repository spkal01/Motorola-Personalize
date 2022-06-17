package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo15462d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo15463k = 3, mo15464mv = {1, 4, 0})
@DebugMetadata(mo16174c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2", mo16175f = "Channels.common.kt", mo16176i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, mo16177l = {2196, 2186, 2188}, mo16178m = "invokeSuspend", mo16179n = {"$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "element2"}, mo16180s = {"L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $other;
    final /* synthetic */ ReceiveChannel $this_zip;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$10;
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
    private ProducerScope f217p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChannelsKt__Channels_commonKt$zip$2(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_zip = receiveChannel;
        this.$other = receiveChannel2;
        this.$transform = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$zip$2 channelsKt__Channels_commonKt$zip$2 = new ChannelsKt__Channels_commonKt$zip$2(this.$this_zip, this.$other, this.$transform, continuation);
        channelsKt__Channels_commonKt$zip$2.f217p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$zip$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$zip$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ed A[Catch:{ all -> 0x015e }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x014e  */
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
            if (r2 == r5) goto L_0x0085
            if (r2 == r4) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r2 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r9
            java.lang.Object r10 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x015e }
            r16 = r1
            r1 = r0
            r0 = r9
            r9 = r2
            r2 = r16
            goto L_0x0153
        L_0x003f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0047:
            java.lang.Object r2 = r0.L$9
            java.lang.Object r6 = r0.L$8
            java.lang.Object r7 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$5
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r0.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r11 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r11
            java.lang.Object r12 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r0.L$0
            kotlinx.coroutines.channels.ProducerScope r14 = (kotlinx.coroutines.channels.ProducerScope) r14
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0080 }
            r15 = r14
            r14 = r19
            r16 = r1
            r1 = r0
            r0 = r11
            r11 = r13
            r13 = r6
            r6 = r16
            r17 = r9
            r9 = r7
            r7 = r17
            goto L_0x0117
        L_0x0080:
            r0 = move-exception
            r1 = r0
            r8 = r10
            goto L_0x0160
        L_0x0085:
            java.lang.Object r2 = r0.L$7
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r0.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r0.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r0.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r9
            java.lang.Object r10 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r0.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x015e }
            r13 = r19
            goto L_0x00e5
        L_0x00ab:
            kotlin.ResultKt.throwOnFailure(r19)
            kotlinx.coroutines.channels.ProducerScope r2 = r0.f217p$
            kotlinx.coroutines.channels.ReceiveChannel r6 = r0.$other
            kotlinx.coroutines.channels.ChannelIterator r6 = r6.iterator()
            kotlinx.coroutines.channels.ReceiveChannel r8 = r0.$this_zip
            r7 = 0
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r9 = r8.iterator()     // Catch:{ all -> 0x015e }
            r12 = r2
            r11 = r6
            r6 = r8
            r10 = r6
            r2 = r1
            r1 = r0
        L_0x00c5:
            r1.L$0 = r12     // Catch:{ all -> 0x015e }
            r1.L$1 = r11     // Catch:{ all -> 0x015e }
            r1.L$2 = r10     // Catch:{ all -> 0x015e }
            r1.L$3 = r0     // Catch:{ all -> 0x015e }
            r1.L$4 = r8     // Catch:{ all -> 0x015e }
            r1.L$5 = r7     // Catch:{ all -> 0x015e }
            r1.L$6 = r6     // Catch:{ all -> 0x015e }
            r1.L$7 = r9     // Catch:{ all -> 0x015e }
            r1.label = r5     // Catch:{ all -> 0x015e }
            java.lang.Object r13 = r9.hasNext(r0)     // Catch:{ all -> 0x015e }
            if (r13 != r2) goto L_0x00de
            return r2
        L_0x00de:
            r16 = r9
            r9 = r0
            r0 = r1
            r1 = r2
            r2 = r16
        L_0x00e5:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x015e }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x015e }
            if (r13 == 0) goto L_0x0156
            java.lang.Object r13 = r2.next()     // Catch:{ all -> 0x015e }
            r0.L$0 = r12     // Catch:{ all -> 0x015e }
            r0.L$1 = r11     // Catch:{ all -> 0x015e }
            r0.L$2 = r10     // Catch:{ all -> 0x015e }
            r0.L$3 = r9     // Catch:{ all -> 0x015e }
            r0.L$4 = r8     // Catch:{ all -> 0x015e }
            r0.L$5 = r7     // Catch:{ all -> 0x015e }
            r0.L$6 = r6     // Catch:{ all -> 0x015e }
            r0.L$7 = r2     // Catch:{ all -> 0x015e }
            r0.L$8 = r13     // Catch:{ all -> 0x015e }
            r0.L$9 = r13     // Catch:{ all -> 0x015e }
            r0.label = r4     // Catch:{ all -> 0x015e }
            java.lang.Object r14 = r11.hasNext(r0)     // Catch:{ all -> 0x015e }
            if (r14 != r1) goto L_0x010e
            return r1
        L_0x010e:
            r15 = r12
            r12 = r10
            r10 = r8
            r8 = r6
            r6 = r1
            r1 = r0
            r0 = r9
            r9 = r2
            r2 = r13
        L_0x0117:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x0080 }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x0080 }
            if (r14 != 0) goto L_0x0125
            r2 = r6
            r6 = r8
            r8 = r10
            r10 = r12
            r12 = r15
            goto L_0x00c5
        L_0x0125:
            java.lang.Object r14 = r11.next()     // Catch:{ all -> 0x0080 }
            kotlin.jvm.functions.Function2 r4 = r1.$transform     // Catch:{ all -> 0x0080 }
            java.lang.Object r4 = r4.invoke(r2, r14)     // Catch:{ all -> 0x0080 }
            r1.L$0 = r15     // Catch:{ all -> 0x0080 }
            r1.L$1 = r11     // Catch:{ all -> 0x0080 }
            r1.L$2 = r12     // Catch:{ all -> 0x0080 }
            r1.L$3 = r0     // Catch:{ all -> 0x0080 }
            r1.L$4 = r10     // Catch:{ all -> 0x0080 }
            r1.L$5 = r7     // Catch:{ all -> 0x0080 }
            r1.L$6 = r8     // Catch:{ all -> 0x0080 }
            r1.L$7 = r9     // Catch:{ all -> 0x0080 }
            r1.L$8 = r13     // Catch:{ all -> 0x0080 }
            r1.L$9 = r2     // Catch:{ all -> 0x0080 }
            r1.L$10 = r14     // Catch:{ all -> 0x0080 }
            r1.label = r3     // Catch:{ all -> 0x0080 }
            java.lang.Object r2 = r15.send(r4, r1)     // Catch:{ all -> 0x0080 }
            if (r2 != r6) goto L_0x014e
            return r6
        L_0x014e:
            r2 = r6
            r6 = r8
            r8 = r10
            r10 = r12
            r12 = r15
        L_0x0153:
            r4 = 2
            goto L_0x00c5
        L_0x0156:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x015e }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x015e:
            r0 = move-exception
            r1 = r0
        L_0x0160:
            throw r1     // Catch:{ all -> 0x0161 }
        L_0x0161:
            r0 = move-exception
            r2 = r0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
