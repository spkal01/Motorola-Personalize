package com.motorola.personalize.data;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0018\u001a\u00020\bHÆ\u0003J:\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006!"}, mo15462d2 = {"Lcom/motorola/personalize/data/SoundsViewData;", "", "type", "", "uri", "Landroid/net/Uri;", "iconBackgroundColor", "intentData", "Lcom/motorola/personalize/data/IntentData;", "(ILandroid/net/Uri;Ljava/lang/Integer;Lcom/motorola/personalize/data/IntentData;)V", "getIconBackgroundColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIntentData", "()Lcom/motorola/personalize/data/IntentData;", "getType", "()I", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "component1", "component2", "component3", "component4", "copy", "(ILandroid/net/Uri;Ljava/lang/Integer;Lcom/motorola/personalize/data/IntentData;)Lcom/motorola/personalize/data/SoundsViewData;", "equals", "", "other", "hashCode", "toString", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: SoundsViewData.kt */
public final class SoundsViewData {
    private final Integer iconBackgroundColor;
    private final IntentData intentData;
    private final int type;
    private Uri uri;

    public static /* synthetic */ SoundsViewData copy$default(SoundsViewData soundsViewData, int i, Uri uri2, Integer num, IntentData intentData2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = soundsViewData.type;
        }
        if ((i2 & 2) != 0) {
            uri2 = soundsViewData.uri;
        }
        if ((i2 & 4) != 0) {
            num = soundsViewData.iconBackgroundColor;
        }
        if ((i2 & 8) != 0) {
            intentData2 = soundsViewData.intentData;
        }
        return soundsViewData.copy(i, uri2, num, intentData2);
    }

    public final int component1() {
        return this.type;
    }

    public final Uri component2() {
        return this.uri;
    }

    public final Integer component3() {
        return this.iconBackgroundColor;
    }

    public final IntentData component4() {
        return this.intentData;
    }

    public final SoundsViewData copy(int i, Uri uri2, Integer num, IntentData intentData2) {
        Intrinsics.checkNotNullParameter(intentData2, "intentData");
        return new SoundsViewData(i, uri2, num, intentData2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SoundsViewData)) {
            return false;
        }
        SoundsViewData soundsViewData = (SoundsViewData) obj;
        return this.type == soundsViewData.type && Intrinsics.areEqual((Object) this.uri, (Object) soundsViewData.uri) && Intrinsics.areEqual((Object) this.iconBackgroundColor, (Object) soundsViewData.iconBackgroundColor) && Intrinsics.areEqual((Object) this.intentData, (Object) soundsViewData.intentData);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.type) * 31;
        Uri uri2 = this.uri;
        int i = 0;
        int hashCode2 = (hashCode + (uri2 == null ? 0 : uri2.hashCode())) * 31;
        Integer num = this.iconBackgroundColor;
        if (num != null) {
            i = num.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.intentData.hashCode();
    }

    public String toString() {
        return "SoundsViewData(type=" + this.type + ", uri=" + this.uri + ", iconBackgroundColor=" + this.iconBackgroundColor + ", intentData=" + this.intentData + ')';
    }

    public SoundsViewData(int i, Uri uri2, Integer num, IntentData intentData2) {
        Intrinsics.checkNotNullParameter(intentData2, "intentData");
        this.type = i;
        this.uri = uri2;
        this.iconBackgroundColor = num;
        this.intentData = intentData2;
    }

    public final int getType() {
        return this.type;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(Uri uri2) {
        this.uri = uri2;
    }

    public final Integer getIconBackgroundColor() {
        return this.iconBackgroundColor;
    }

    public final IntentData getIntentData() {
        return this.intentData;
    }
}
