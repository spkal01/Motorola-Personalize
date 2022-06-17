package com.motorola.personalize.data;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import com.motorola.personalize.extensions.Logger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u0007\u001a\u00020\nJ\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000eJ\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\fJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, mo15462d2 = {"Lcom/motorola/personalize/data/MapUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "parseFamily", "Lcom/motorola/personalize/data/FamilyData;", "cursor", "Landroid/database/MatrixCursor;", "featureCursor", "Landroid/database/Cursor;", "parseFeature", "Lcom/motorola/personalize/data/FeatureData;", "parseFeatureList", "", "sortFeatures", "features", "toBundle", "Landroid/os/Bundle;", "id", "", "color", "", "(Ljava/lang/String;Ljava/lang/Integer;)Landroid/os/Bundle;", "toFeatureTileViewData", "Lcom/motorola/personalize/data/FeatureTileViewData;", "feature", "toFeatureViewData", "Lcom/motorola/personalize/data/FeatureViewData;", "toViewData", "Lcom/motorola/personalize/data/LayoutItem;", "featureList", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: MapUtil.kt */
public final class MapUtil {
    private final Context context;

    public MapUtil(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final Bundle toBundle(String str, Integer num) {
        Intrinsics.checkNotNullParameter(str, "id");
        Bundle bundle = new Bundle();
        bundle.putString("feature_id", str);
        if (num != null) {
            bundle.putInt("color", num.intValue());
        }
        return bundle;
    }

    public final FeatureData parseFeature(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String string = cursor.getString(cursor.getColumnIndex("_ID"));
        Context context2 = this.context;
        String packageName = context2.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        Integer colorFromContext = ContextExtensionsKt.getColorFromContext(context2, packageName, Integer.valueOf(cursor.getInt(cursor.getColumnIndex("FEATURE_COLOR"))));
        Intrinsics.checkNotNullExpressionValue(string, "featureId");
        String string2 = this.context.getString(cursor.getInt(cursor.getColumnIndex("TITLE")));
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(cursor.getInt(cursor.getColumnIndex(\"TITLE\")))");
        String safeString = ContextExtensionsKt.getSafeString(this.context, Integer.valueOf(cursor.getInt(cursor.getColumnIndex("DESCRIPTION"))));
        Context context3 = this.context;
        String packageName2 = context3.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName2, "context.packageName");
        Drawable drawableFromContext = ContextExtensionsKt.getDrawableFromContext(context3, packageName2, Integer.valueOf(cursor.getInt(cursor.getColumnIndex("FEATURE_ICON"))));
        Context context4 = this.context;
        String packageName3 = context4.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName3, "context.packageName");
        Drawable drawableFromContext2 = ContextExtensionsKt.getDrawableFromContext(context4, packageName3, Integer.valueOf(cursor.getInt(cursor.getColumnIndex("FEATURE_CARD_ICON"))));
        int i = cursor.getInt(cursor.getColumnIndex("FEATURE_SECTION_PRIORITY"));
        String string3 = cursor.getString(cursor.getColumnIndex("ACTION_INTENT"));
        Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(cursor.getColumnIndex(\"ACTION_INTENT\"))");
        IntentData intentData = new IntentData(string3, cursor.getString(cursor.getColumnIndex("ACTION_PACKAGE")), toBundle(string, colorFromContext));
        String string4 = cursor.getString(cursor.getColumnIndex("SECTION_ID"));
        Intrinsics.checkNotNullExpressionValue(string4, "cursor.getString(cursor.getColumnIndex(\"SECTION_ID\"))");
        return new FeatureData(string, string2, safeString, drawableFromContext, drawableFromContext2, colorFromContext, i, intentData, string4);
    }

    public final List<FeatureData> sortFeatures(List<FeatureData> list) {
        Intrinsics.checkNotNullParameter(list, "features");
        return CollectionsKt.sortedWith(list, new MapUtil$sortFeatures$$inlined$sortedBy$1());
    }

    public final List<FeatureData> parseFeatureList(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        cursor.moveToFirst();
        List arrayList = new ArrayList();
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            arrayList.add(parseFeature(cursor));
            while (cursor.moveToNext()) {
                arrayList.add(parseFeature(cursor));
            }
        }
        cursor.close();
        return sortFeatures(arrayList);
    }

    public final FamilyData parseFamily(MatrixCursor matrixCursor, Cursor cursor) {
        Intrinsics.checkNotNullParameter(matrixCursor, "cursor");
        Intrinsics.checkNotNullParameter(cursor, "featureCursor");
        matrixCursor.moveToFirst();
        String string = matrixCursor.getString(matrixCursor.getColumnIndex("_ID"));
        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.getColumnIndex(\"_ID\"))");
        String string2 = this.context.getString(matrixCursor.getInt(matrixCursor.getColumnIndex("TITLE")));
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(cursor.getInt(cursor.getColumnIndex(\"TITLE\")))");
        String string3 = this.context.getString(matrixCursor.getInt(matrixCursor.getColumnIndex("DESCRIPTION")));
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(cursor.getInt(cursor.getColumnIndex(\"DESCRIPTION\")))");
        List<FeatureData> parseFeatureList = parseFeatureList(cursor);
        Context context2 = this.context;
        String packageName = context2.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        Drawable drawableFromContext = ContextExtensionsKt.getDrawableFromContext(context2, packageName, Integer.valueOf(matrixCursor.getInt(matrixCursor.getColumnIndex("FAMILY_IMAGE"))));
        Context context3 = this.context;
        String packageName2 = context3.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName2, "context.packageName");
        Integer colorFromContext = ContextExtensionsKt.getColorFromContext(context3, packageName2, Integer.valueOf(matrixCursor.getInt(matrixCursor.getColumnIndex("FAMILY_COLOR"))));
        Context context4 = this.context;
        String packageName3 = context4.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName3, "context.packageName");
        FamilyData familyData = new FamilyData(string, string2, string3, parseFeatureList, drawableFromContext, colorFromContext, ContextExtensionsKt.getColorFromContext(context4, packageName3, Integer.valueOf(matrixCursor.getInt(matrixCursor.getColumnIndex("FAMILY_STATUS_BAR_COLOR")))));
        matrixCursor.close();
        return familyData;
    }

    public final List<LayoutItem> toViewData(List<FeatureData> list) {
        Intrinsics.checkNotNullParameter(list, "featureList");
        List<LayoutItem> arrayList = new ArrayList<>();
        for (FeatureData featureData : list) {
            String sectionId = featureData.getSectionId();
            if (Intrinsics.areEqual((Object) sectionId, (Object) "make_adjustments")) {
                arrayList.add(toFeatureViewData(featureData));
            } else if (Intrinsics.areEqual((Object) sectionId, (Object) "apply_changes")) {
                FeatureTileViewData featureTileViewData = toFeatureTileViewData(featureData);
                if (featureTileViewData != null) {
                    arrayList.add(featureTileViewData);
                }
            } else {
                Log.e(Logger.INSTANCE.getTag(), "invalid layout type");
            }
        }
        return arrayList;
    }

    private final FeatureViewData toFeatureViewData(FeatureData featureData) {
        return new FeatureViewData(featureData.getId(), featureData.getIcon(), featureData.getTitle(), featureData.getIconBgColor(), featureData.getIntent());
    }

    public final FeatureTileViewData toFeatureTileViewData(FeatureData featureData) {
        Intrinsics.checkNotNullParameter(featureData, "feature");
        String description = featureData.getDescription();
        if (description == null) {
            return null;
        }
        return new FeatureTileViewData(featureData.getId(), featureData.getIcon(), featureData.getTitle(), description, featureData.getIconBgColor(), featureData.getIntent());
    }
}
