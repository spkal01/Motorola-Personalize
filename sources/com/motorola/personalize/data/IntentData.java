package com.motorola.personalize.data;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, mo15462d2 = {"Lcom/motorola/personalize/data/IntentData;", "", "intent", "", "intentPackage", "intentExtra", "Landroid/os/Bundle;", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getIntent", "()Ljava/lang/String;", "getIntentExtra", "()Landroid/os/Bundle;", "getIntentPackage", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: IntentData.kt */
public final class IntentData {
    private final String intent;
    private final Bundle intentExtra;
    private final String intentPackage;

    public static /* synthetic */ IntentData copy$default(IntentData intentData, String str, String str2, Bundle bundle, int i, Object obj) {
        if ((i & 1) != 0) {
            str = intentData.intent;
        }
        if ((i & 2) != 0) {
            str2 = intentData.intentPackage;
        }
        if ((i & 4) != 0) {
            bundle = intentData.intentExtra;
        }
        return intentData.copy(str, str2, bundle);
    }

    public final String component1() {
        return this.intent;
    }

    public final String component2() {
        return this.intentPackage;
    }

    public final Bundle component3() {
        return this.intentExtra;
    }

    public final IntentData copy(String str, String str2, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "intent");
        return new IntentData(str, str2, bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntentData)) {
            return false;
        }
        IntentData intentData = (IntentData) obj;
        return Intrinsics.areEqual((Object) this.intent, (Object) intentData.intent) && Intrinsics.areEqual((Object) this.intentPackage, (Object) intentData.intentPackage) && Intrinsics.areEqual((Object) this.intentExtra, (Object) intentData.intentExtra);
    }

    public int hashCode() {
        int hashCode = this.intent.hashCode() * 31;
        String str = this.intentPackage;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Bundle bundle = this.intentExtra;
        if (bundle != null) {
            i = bundle.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "IntentData(intent=" + this.intent + ", intentPackage=" + this.intentPackage + ", intentExtra=" + this.intentExtra + ')';
    }

    public IntentData(String str, String str2, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "intent");
        this.intent = str;
        this.intentPackage = str2;
        this.intentExtra = bundle;
    }

    public final String getIntent() {
        return this.intent;
    }

    public final String getIntentPackage() {
        return this.intentPackage;
    }

    public final Bundle getIntentExtra() {
        return this.intentExtra;
    }
}
