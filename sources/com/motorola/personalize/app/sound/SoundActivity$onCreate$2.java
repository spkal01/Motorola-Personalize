package com.motorola.personalize.app.sound;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo15461d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo15462d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo15463k = 3, mo15464mv = {1, 5, 1}, mo15466xi = 48)
@DebugMetadata(mo16174c = "com.motorola.personalize.app.sound.SoundActivity$onCreate$2", mo16175f = "SoundActivity.kt", mo16176i = {}, mo16177l = {}, mo16178m = "invokeSuspend", mo16179n = {}, mo16180s = {})
/* compiled from: SoundActivity.kt */
final class SoundActivity$onCreate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SoundActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SoundActivity$onCreate$2(SoundActivity soundActivity, Continuation<? super SoundActivity$onCreate$2> continuation) {
        super(2, continuation);
        this.this$0 = soundActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SoundActivity$onCreate$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SoundActivity$onCreate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.loadContent();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
