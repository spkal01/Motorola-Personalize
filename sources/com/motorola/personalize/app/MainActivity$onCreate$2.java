package com.motorola.personalize.app;

import com.motorola.personalize.data.IntentData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo15461d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, mo15462d2 = {"<anonymous>", "", "featureKey", "", "intentData", "Lcom/motorola/personalize/data/IntentData;"}, mo15463k = 3, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: MainActivity.kt */
final class MainActivity$onCreate$2 extends Lambda implements Function2<String, IntentData, Unit> {
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainActivity$onCreate$2(MainActivity mainActivity) {
        super(2);
        this.this$0 = mainActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (IntentData) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(String str, IntentData intentData) {
        Intrinsics.checkNotNullParameter(str, "featureKey");
        Intrinsics.checkNotNullParameter(intentData, "intentData");
        this.this$0.onFeatureClicked(str, intentData);
    }
}
