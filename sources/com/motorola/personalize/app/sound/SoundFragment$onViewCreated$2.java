package com.motorola.personalize.app.sound;

import com.motorola.personalize.data.IntentData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, mo15462d2 = {"<anonymous>", "", "type", "", "intentData", "Lcom/motorola/personalize/data/IntentData;"}, mo15463k = 3, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: SoundFragment.kt */
final class SoundFragment$onViewCreated$2 extends Lambda implements Function2<Integer, IntentData, Unit> {
    final /* synthetic */ SoundFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SoundFragment$onViewCreated$2(SoundFragment soundFragment) {
        super(2);
        this.this$0 = soundFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (IntentData) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, IntentData intentData) {
        Intrinsics.checkNotNullParameter(intentData, "intentData");
        this.this$0.onFeatureClicked(i, intentData);
    }
}
