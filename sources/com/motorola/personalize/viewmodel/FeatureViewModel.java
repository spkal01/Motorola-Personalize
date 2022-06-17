package com.motorola.personalize.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.motorola.personalize.BuildConfig;
import com.motorola.personalize.data.FamilyData;
import com.motorola.personalize.data.MapUtil;
import com.motorola.personalize.provider.data.provider.FamilyProvider;
import com.motorola.personalize.provider.data.provider.FeatureProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(mo15461d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, mo15462d2 = {"Lcom/motorola/personalize/viewmodel/FeatureViewModel;", "Landroidx/lifecycle/ViewModel;", "mapUtil", "Lcom/motorola/personalize/data/MapUtil;", "(Lcom/motorola/personalize/data/MapUtil;)V", "_familyData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/motorola/personalize/data/FamilyData;", "family", "Landroidx/lifecycle/LiveData;", "getFamily", "()Landroidx/lifecycle/LiveData;", "getMapUtil", "()Lcom/motorola/personalize/data/MapUtil;", "loadData", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: FeatureViewModel.kt */
public final class FeatureViewModel extends ViewModel {
    /* access modifiers changed from: private */
    public final MutableLiveData<FamilyData> _familyData = new MutableLiveData<>();
    private final MapUtil mapUtil;

    public final MapUtil getMapUtil() {
        return this.mapUtil;
    }

    public FeatureViewModel(MapUtil mapUtil2) {
        Intrinsics.checkNotNullParameter(mapUtil2, "mapUtil");
        this.mapUtil = mapUtil2;
    }

    public final LiveData<FamilyData> getFamily() {
        return this._familyData;
    }

    public final void loadData() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new FeatureViewModel$loadData$1(this, new FamilyProvider(), new FeatureProvider().getFeatures(BuildConfig.APPLICATION_ID), (Continuation<? super FeatureViewModel$loadData$1>) null), 3, (Object) null);
    }
}
