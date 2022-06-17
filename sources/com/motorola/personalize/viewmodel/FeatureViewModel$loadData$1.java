package com.motorola.personalize.viewmodel;

import android.database.Cursor;
import android.database.MatrixCursor;
import androidx.lifecycle.MutableLiveData;
import com.motorola.personalize.data.MapUtil;
import com.motorola.personalize.provider.data.provider.FamilyProvider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo15461d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, mo15462d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, mo15463k = 3, mo15464mv = {1, 5, 1}, mo15466xi = 48)
@DebugMetadata(mo16174c = "com.motorola.personalize.viewmodel.FeatureViewModel$loadData$1", mo16175f = "FeatureViewModel.kt", mo16176i = {}, mo16177l = {}, mo16178m = "invokeSuspend", mo16179n = {}, mo16180s = {})
/* compiled from: FeatureViewModel.kt */
final class FeatureViewModel$loadData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Cursor $cursor;
    final /* synthetic */ FamilyProvider $familyProvider;
    int label;
    final /* synthetic */ FeatureViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FeatureViewModel$loadData$1(FeatureViewModel featureViewModel, FamilyProvider familyProvider, Cursor cursor, Continuation<? super FeatureViewModel$loadData$1> continuation) {
        super(2, continuation);
        this.this$0 = featureViewModel;
        this.$familyProvider = familyProvider;
        this.$cursor = cursor;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FeatureViewModel$loadData$1(this.this$0, this.$familyProvider, this.$cursor, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FeatureViewModel$loadData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MutableLiveData access$get_familyData$p = this.this$0._familyData;
            MapUtil mapUtil = this.this$0.getMapUtil();
            MatrixCursor families = this.$familyProvider.getFamilies();
            Intrinsics.checkNotNullExpressionValue(families, "familyProvider.families");
            Cursor cursor = this.$cursor;
            Intrinsics.checkNotNullExpressionValue(cursor, "cursor");
            access$get_familyData$p.setValue(mapUtil.parseFamily(families, cursor));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
