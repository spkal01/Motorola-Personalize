package com.motorola.personalize.app;

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
@DebugMetadata(mo16174c = "com.motorola.personalize.app.StylesActivity$onCreate$2", mo16175f = "StylesActivity.kt", mo16176i = {}, mo16177l = {}, mo16178m = "invokeSuspend", mo16179n = {}, mo16180s = {})
/* compiled from: StylesActivity.kt */
final class StylesActivity$onCreate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ StylesActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StylesActivity$onCreate$2(StylesActivity stylesActivity, Continuation<? super StylesActivity$onCreate$2> continuation) {
        super(2, continuation);
        this.this$0 = stylesActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StylesActivity$onCreate$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StylesActivity$onCreate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            StylesActivity stylesActivity = this.this$0;
            stylesActivity.loadContent(stylesActivity.getIntent());
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
