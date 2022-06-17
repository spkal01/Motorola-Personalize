package com.motorola.personalize.extensions;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000l\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0003\u001a\u0012\u0010\u0006\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b\u001a\n\u0010\t\u001a\u00020\b*\u00020\u0004\u001a\f\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0004\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\u001a\u0014\u0010\u000f\u001a\u00020\u0001*\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0001\u001a\u0017\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011*\u00020\u0004¢\u0006\u0002\u0010\u0012\u001a%\u0010\u0013\u001a\u0004\u0018\u00010\b*\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0015\u001a\u0012\u0010\u0016\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0001\u001a%\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0019\u001a\f\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u0004\u001a \u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u001e\u001a\u00020\b\u001a\f\u0010\u001f\u001a\u0004\u0018\u00010 *\u00020\u0004\u001a\u001b\u0010!\u001a\u0004\u0018\u00010\u0001*\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010#\u001a\u0014\u0010$\u001a\u00020\u0001*\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0001\u001a\n\u0010%\u001a\u00020&*\u00020\u0004\u001a\n\u0010'\u001a\u00020&*\u00020\u0004\u001a\u0012\u0010(\u001a\u00020&*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0001\u001a-\u0010)\u001a\u0004\u0018\u00010**\u00020\u00042\u0006\u0010+\u001a\u00020,2\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010.\u001a\u001a\u0010/\u001a\u00020\b*\u00020\u00042\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u000201\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u00062"}, mo15462d2 = {"INVALID_VERSION_MESSAGE", "", "convertToPx", "", "Landroid/content/Context;", "dp", "dpToPx", "value", "", "getActionBarHeight", "getAlarmManager", "Landroid/app/AlarmManager;", "getApplicationInfo", "Landroid/content/pm/ApplicationInfo;", "fullPackageName", "getApplicationName", "getAssetList", "", "(Landroid/content/Context;)[Ljava/lang/String;", "getColorFromContext", "resId", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;", "getContextFromPackage", "getDrawableFromContext", "Landroid/graphics/drawable/Drawable;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;)Landroid/graphics/drawable/Drawable;", "getNotificationManager", "Landroid/app/NotificationManager;", "getPackageInfo", "Landroid/content/pm/PackageInfo;", "flags", "getPowerManager", "Landroid/os/PowerManager;", "getSafeString", "stringId", "(Landroid/content/Context;Ljava/lang/Integer;)Ljava/lang/String;", "getVersionName", "isAppCompatible", "", "isDarkModeEnabled", "isPackageAvailable", "queryContentResolver", "Landroid/database/Cursor;", "uri", "Landroid/net/Uri;", "selection", "(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;)Landroid/database/Cursor;", "updateContentResolver", "values", "Landroid/content/ContentValues;", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ContextExtensions.kt */
public final class ContextExtensionsKt {
    private static final String INVALID_VERSION_MESSAGE = "Error when fetching version name";

    public static final boolean isPackageAvailable(Context context, String str) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fullPackageName");
        ApplicationInfo applicationInfo = getApplicationInfo(context, str);
        if (applicationInfo == null) {
            z = false;
        } else {
            z = applicationInfo.enabled;
        }
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "isPackageAvailable: " + str + " returned: " + z);
        }
        return z;
    }

    public static /* synthetic */ String getVersionName$default(Context context, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "fun Context.getVersionName(fullPackageName: String = packageName): String {\n    val packageInfo = getPackageInfo(fullPackageName)\n    val versionName = packageInfo?.versionName ?: INVALID_VERSION_MESSAGE\n    logD { \"getVersionName returned: $versionName\" }\n    return versionName\n}");
        }
        return getVersionName(context, str);
    }

    public static final String getVersionName(Context context, String str) {
        String str2;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fullPackageName");
        PackageInfo packageInfo$default = getPackageInfo$default(context, str, 0, 2, (Object) null);
        String str3 = INVALID_VERSION_MESSAGE;
        if (!(packageInfo$default == null || (str2 = packageInfo$default.versionName) == null)) {
            str3 = str2;
        }
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("getVersionName returned: ", str3));
        }
        return str3;
    }

    public static /* synthetic */ String getApplicationName$default(Context context, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "fun Context.getApplicationName(fullPackageName: String = packageName): String {\n    val applicationInfo = getApplicationInfo(fullPackageName) ?: return \"\"\n    return packageManager.getApplicationLabel(applicationInfo).toString()\n}");
        }
        return getApplicationName(context, str);
    }

    public static final String getApplicationName(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fullPackageName");
        ApplicationInfo applicationInfo = getApplicationInfo(context, str);
        if (applicationInfo == null) {
            return "";
        }
        return context.getPackageManager().getApplicationLabel(applicationInfo).toString();
    }

    public static final boolean isAppCompatible(Context context) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "<this>");
        FeatureInfo[] featureInfoArr = null;
        boolean z2 = true;
        PackageInfo packageInfo$default = getPackageInfo$default(context, (String) null, 16384, 1, (Object) null);
        if (packageInfo$default != null) {
            featureInfoArr = packageInfo$default.reqFeatures;
        }
        if (featureInfoArr != null) {
            Collection arrayList = new ArrayList();
            for (FeatureInfo featureInfo : featureInfoArr) {
                if ((featureInfo.flags & 1) != 0) {
                    arrayList.add(featureInfo);
                }
            }
            Iterable<FeatureInfo> iterable = (List) arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (FeatureInfo featureInfo2 : iterable) {
                arrayList2.add(featureInfo2.name);
            }
            Iterable iterable2 = (List) arrayList2;
            if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                Iterator it = iterable2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String str = (String) it.next();
                    if (TextUtils.isEmpty(str) || context.getPackageManager().hasSystemFeature(str)) {
                        z = false;
                        continue;
                    } else {
                        z = true;
                        continue;
                    }
                    if (z) {
                        z2 = false;
                        break;
                    }
                }
            }
        }
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("isAppCompatible returned: ", Boolean.valueOf(z2)));
        }
        return z2;
    }

    private static final ApplicationInfo getApplicationInfo(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(Logger.INSTANCE.getTag(), Intrinsics.stringPlus("PackageManager.NameNotFoundException: ", e.getMessage()));
            return null;
        }
    }

    public static /* synthetic */ PackageInfo getPackageInfo$default(Context context, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "fun Context.getPackageInfo(fullPackageName: String = packageName, flags: Int = 0) =\n    with(fullPackageName) {\n        try {\n            packageManager.getPackageInfo(this, flags)\n        } catch (e: PackageManager.NameNotFoundException) {\n            logE { \"PackageManager.NameNotFoundException: ${e.message}\" }\n            null\n        }\n    }");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return getPackageInfo(context, str, i);
    }

    public static final PackageInfo getPackageInfo(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fullPackageName");
        try {
            return context.getPackageManager().getPackageInfo(str, i);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(Logger.INSTANCE.getTag(), Intrinsics.stringPlus("PackageManager.NameNotFoundException: ", e.getMessage()));
            return null;
        }
    }

    public static final Context getContextFromPackage(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fullPackageName");
        Context createPackageContext = context.createPackageContext(str, 2);
        Intrinsics.checkNotNullExpressionValue(createPackageContext, "createPackageContext(fullPackageName, Context.CONTEXT_IGNORE_SECURITY)");
        return createPackageContext;
    }

    public static /* synthetic */ Cursor queryContentResolver$default(Context context, Uri uri, String[] strArr, int i, Object obj) {
        if ((i & 2) != 0) {
            strArr = null;
        }
        return queryContentResolver(context, uri, strArr);
    }

    public static final Cursor queryContentResolver(Context context, Uri uri, String[] strArr) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            return context.getContentResolver().query(uri, strArr, (Bundle) null, (CancellationSignal) null);
        } catch (IllegalArgumentException e) {
            Log.e(Logger.INSTANCE.getTag(), Intrinsics.stringPlus("IllegalArgumentException: ", e.getMessage()));
            return null;
        } catch (SecurityException e2) {
            Log.e(Logger.INSTANCE.getTag(), Intrinsics.stringPlus("SecurityException: ", e2.getMessage()));
            return null;
        } catch (IllegalStateException e3) {
            Log.e(Logger.INSTANCE.getTag(), Intrinsics.stringPlus("IllegalStateException: ", e3.getMessage()));
            return null;
        }
    }

    public static final int updateContentResolver(Context context, Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(contentValues, "values");
        return context.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
    }

    public static final Integer getColorFromContext(Context context, String str, Integer num) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fullPackageName");
        if (num == null) {
            return null;
        }
        num.intValue();
        try {
            return Integer.valueOf(ContextCompat.getColor(getContextFromPackage(context, str), num.intValue()));
        } catch (Resources.NotFoundException e) {
            String tag = Logger.INSTANCE.getTag();
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(tag, Intrinsics.stringPlus("Resources.NotFoundException: ", e.getMessage()));
            }
            return null;
        }
    }

    public static /* synthetic */ Integer getColorFromContext$default(Context context, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "fun Context.getColorFromContext(fullPackageName: String = packageName, resId: Int?) = resId?.let {\n    try {\n        val context = getContextFromPackage(fullPackageName)\n        ContextCompat.getColor(context, resId)\n    } catch (e: Resources.NotFoundException) {\n        logD { \"Resources.NotFoundException: ${e.message}\" }\n        null\n    }\n}");
        }
        return getColorFromContext(context, str, num);
    }

    public static /* synthetic */ Drawable getDrawableFromContext$default(Context context, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(str, "fun Context.getDrawableFromContext(fullPackageName: String = packageName, resId: Int?) =\n    resId?.let {\n        try {\n            val context = getContextFromPackage(fullPackageName)\n            ContextCompat.getDrawable(context, resId)\n        } catch (e: Resources.NotFoundException) {\n            logD { \"Resources.NotFoundException: ${e.message}\" }\n            null\n        }\n    }");
        }
        return getDrawableFromContext(context, str, num);
    }

    public static final Drawable getDrawableFromContext(Context context, String str, Integer num) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fullPackageName");
        if (num == null) {
            return null;
        }
        num.intValue();
        try {
            return ContextCompat.getDrawable(getContextFromPackage(context, str), num.intValue());
        } catch (Resources.NotFoundException e) {
            String tag = Logger.INSTANCE.getTag();
            if (Logger.INSTANCE.getDEBUG()) {
                Log.d(tag, Intrinsics.stringPlus("Resources.NotFoundException: ", e.getMessage()));
            }
            return null;
        }
    }

    public static final String[] getAssetList(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getAssets().list("");
    }

    public static final NotificationManager getNotificationManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("notification");
        if (systemService instanceof NotificationManager) {
            return (NotificationManager) systemService;
        }
        return null;
    }

    public static final PowerManager getPowerManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("power");
        if (systemService instanceof PowerManager) {
            return (PowerManager) systemService;
        }
        return null;
    }

    public static final AlarmManager getAlarmManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (systemService instanceof AlarmManager) {
            return (AlarmManager) systemService;
        }
        return null;
    }

    public static final float convertToPx(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / ((float) 160));
    }

    public static final boolean isDarkModeEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static final String getSafeString(Context context, Integer num) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (num == null || num.intValue() <= 0) {
            return null;
        }
        return context.getString(num.intValue());
    }

    public static final float dpToPx(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static final int getActionBarHeight(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }
}
