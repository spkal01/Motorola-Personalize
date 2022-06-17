package com.motorola.personalize.data;

import android.graphics.drawable.Drawable;
import com.motorola.personalize.data.LayoutType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JN\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\tHÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006&"}, mo15462d2 = {"Lcom/motorola/personalize/data/FeatureTileViewData;", "Lcom/motorola/personalize/data/LayoutItem;", "key", "", "icon", "Landroid/graphics/drawable/Drawable;", "title", "description", "iconBackgroundColor", "", "intentData", "Lcom/motorola/personalize/data/IntentData;", "(Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/motorola/personalize/data/IntentData;)V", "getDescription", "()Ljava/lang/String;", "getIcon", "()Landroid/graphics/drawable/Drawable;", "getIconBackgroundColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIntentData", "()Lcom/motorola/personalize/data/IntentData;", "getKey", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Landroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/motorola/personalize/data/IntentData;)Lcom/motorola/personalize/data/FeatureTileViewData;", "equals", "", "other", "", "hashCode", "toString", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: FeatureTileViewData.kt */
public final class FeatureTileViewData extends LayoutItem {
    private final String description;
    private final Drawable icon;
    private final Integer iconBackgroundColor;
    private final IntentData intentData;
    private final String key;
    private final String title;

    public static /* synthetic */ FeatureTileViewData copy$default(FeatureTileViewData featureTileViewData, String str, Drawable drawable, String str2, String str3, Integer num, IntentData intentData2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = featureTileViewData.key;
        }
        if ((i & 2) != 0) {
            drawable = featureTileViewData.icon;
        }
        Drawable drawable2 = drawable;
        if ((i & 4) != 0) {
            str2 = featureTileViewData.title;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            str3 = featureTileViewData.description;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            num = featureTileViewData.iconBackgroundColor;
        }
        Integer num2 = num;
        if ((i & 32) != 0) {
            intentData2 = featureTileViewData.intentData;
        }
        return featureTileViewData.copy(str, drawable2, str4, str5, num2, intentData2);
    }

    public final String component1() {
        return this.key;
    }

    public final Drawable component2() {
        return this.icon;
    }

    public final String component3() {
        return this.title;
    }

    public final String component4() {
        return this.description;
    }

    public final Integer component5() {
        return this.iconBackgroundColor;
    }

    public final IntentData component6() {
        return this.intentData;
    }

    public final FeatureTileViewData copy(String str, Drawable drawable, String str2, String str3, Integer num, IntentData intentData2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(str3, "description");
        Intrinsics.checkNotNullParameter(intentData2, "intentData");
        return new FeatureTileViewData(str, drawable, str2, str3, num, intentData2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeatureTileViewData)) {
            return false;
        }
        FeatureTileViewData featureTileViewData = (FeatureTileViewData) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) featureTileViewData.key) && Intrinsics.areEqual((Object) this.icon, (Object) featureTileViewData.icon) && Intrinsics.areEqual((Object) this.title, (Object) featureTileViewData.title) && Intrinsics.areEqual((Object) this.description, (Object) featureTileViewData.description) && Intrinsics.areEqual((Object) this.iconBackgroundColor, (Object) featureTileViewData.iconBackgroundColor) && Intrinsics.areEqual((Object) this.intentData, (Object) featureTileViewData.intentData);
    }

    public int hashCode() {
        int hashCode = this.key.hashCode() * 31;
        Drawable drawable = this.icon;
        int i = 0;
        int hashCode2 = (((((hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31) + this.title.hashCode()) * 31) + this.description.hashCode()) * 31;
        Integer num = this.iconBackgroundColor;
        if (num != null) {
            i = num.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.intentData.hashCode();
    }

    public String toString() {
        return "FeatureTileViewData(key=" + this.key + ", icon=" + this.icon + ", title=" + this.title + ", description=" + this.description + ", iconBackgroundColor=" + this.iconBackgroundColor + ", intentData=" + this.intentData + ')';
    }

    public final String getKey() {
        return this.key;
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final Integer getIconBackgroundColor() {
        return this.iconBackgroundColor;
    }

    public final IntentData getIntentData() {
        return this.intentData;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeatureTileViewData(String str, Drawable drawable, String str2, String str3, Integer num, IntentData intentData2) {
        super(LayoutType.Tile.INSTANCE);
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(str3, "description");
        Intrinsics.checkNotNullParameter(intentData2, "intentData");
        this.key = str;
        this.icon = drawable;
        this.title = str2;
        this.description = str3;
        this.iconBackgroundColor = num;
        this.intentData = intentData2;
    }
}
