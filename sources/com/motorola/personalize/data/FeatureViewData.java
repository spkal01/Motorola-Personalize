package com.motorola.personalize.data;

import android.graphics.drawable.Drawable;
import com.motorola.personalize.data.LayoutType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001a\u001a\u00020\nHÆ\u0003JD\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\bHÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014¨\u0006#"}, mo15462d2 = {"Lcom/motorola/personalize/data/FeatureViewData;", "Lcom/motorola/personalize/data/LayoutItem;", "key", "", "icon", "Landroid/graphics/drawable/Drawable;", "text", "iconBackgroundColor", "", "intentData", "Lcom/motorola/personalize/data/IntentData;", "(Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/Integer;Lcom/motorola/personalize/data/IntentData;)V", "getIcon", "()Landroid/graphics/drawable/Drawable;", "getIconBackgroundColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIntentData", "()Lcom/motorola/personalize/data/IntentData;", "getKey", "()Ljava/lang/String;", "getText", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/Integer;Lcom/motorola/personalize/data/IntentData;)Lcom/motorola/personalize/data/FeatureViewData;", "equals", "", "other", "", "hashCode", "toString", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: FeatureViewData.kt */
public final class FeatureViewData extends LayoutItem {
    private final Drawable icon;
    private final Integer iconBackgroundColor;
    private final IntentData intentData;
    private final String key;
    private final String text;

    public static /* synthetic */ FeatureViewData copy$default(FeatureViewData featureViewData, String str, Drawable drawable, String str2, Integer num, IntentData intentData2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = featureViewData.key;
        }
        if ((i & 2) != 0) {
            drawable = featureViewData.icon;
        }
        Drawable drawable2 = drawable;
        if ((i & 4) != 0) {
            str2 = featureViewData.text;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            num = featureViewData.iconBackgroundColor;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            intentData2 = featureViewData.intentData;
        }
        return featureViewData.copy(str, drawable2, str3, num2, intentData2);
    }

    public final String component1() {
        return this.key;
    }

    public final Drawable component2() {
        return this.icon;
    }

    public final String component3() {
        return this.text;
    }

    public final Integer component4() {
        return this.iconBackgroundColor;
    }

    public final IntentData component5() {
        return this.intentData;
    }

    public final FeatureViewData copy(String str, Drawable drawable, String str2, Integer num, IntentData intentData2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "text");
        Intrinsics.checkNotNullParameter(intentData2, "intentData");
        return new FeatureViewData(str, drawable, str2, num, intentData2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeatureViewData)) {
            return false;
        }
        FeatureViewData featureViewData = (FeatureViewData) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) featureViewData.key) && Intrinsics.areEqual((Object) this.icon, (Object) featureViewData.icon) && Intrinsics.areEqual((Object) this.text, (Object) featureViewData.text) && Intrinsics.areEqual((Object) this.iconBackgroundColor, (Object) featureViewData.iconBackgroundColor) && Intrinsics.areEqual((Object) this.intentData, (Object) featureViewData.intentData);
    }

    public int hashCode() {
        int hashCode = this.key.hashCode() * 31;
        Drawable drawable = this.icon;
        int i = 0;
        int hashCode2 = (((hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31) + this.text.hashCode()) * 31;
        Integer num = this.iconBackgroundColor;
        if (num != null) {
            i = num.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.intentData.hashCode();
    }

    public String toString() {
        return "FeatureViewData(key=" + this.key + ", icon=" + this.icon + ", text=" + this.text + ", iconBackgroundColor=" + this.iconBackgroundColor + ", intentData=" + this.intentData + ')';
    }

    public final String getKey() {
        return this.key;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final String getText() {
        return this.text;
    }

    public final Integer getIconBackgroundColor() {
        return this.iconBackgroundColor;
    }

    public final IntentData getIntentData() {
        return this.intentData;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeatureViewData(String str, Drawable drawable, String str2, Integer num, IntentData intentData2) {
        super(LayoutType.Icon.INSTANCE);
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "text");
        Intrinsics.checkNotNullParameter(intentData2, "intentData");
        this.key = str;
        this.icon = drawable;
        this.text = str2;
        this.iconBackgroundColor = num;
        this.intentData = intentData2;
    }
}
