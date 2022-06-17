package com.motorola.personalize.extensions;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\u001a\"\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\u001a\"\u0010\n\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\u001a\u0012\u0010\u000b\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\f\u001a\u00020\r\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007\u001a\u001c\u0010\u0010\u001a\u00020\u000f*\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u0001\u001a\u0014\u0010\u0013\u001a\u00020\u000f*\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007\u001a,\u0010\u0014\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo15462d2 = {"MOTO_SIGNATURE_PACKAGE_NAME", "", "disableActivity", "", "Landroid/content/pm/PackageManager;", "context", "Landroid/content/Context;", "activityClass", "Ljava/lang/Class;", "Landroid/app/Activity;", "enableActivity", "getPackageNameForIntent", "intent", "Landroid/content/Intent;", "isIntentCallable", "", "isPackageSignatureValid", "packageName", "standardPackage", "isServiceIntentCallable", "updateActivityState", "state", "", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: PackageManagerExtensions.kt */
public final class PackageManagerExtensionsKt {
    private static final String MOTO_SIGNATURE_PACKAGE_NAME = "com.motorola.motosignature.app";

    public static final boolean isIntentCallable(PackageManager packageManager, Intent intent) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        Intrinsics.checkNotNullParameter(intent, "intent");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)");
        if (queryIntentActivities.size() == 0) {
            return false;
        }
        if (queryIntentActivities.size() <= 1) {
            return queryIntentActivities.get(0).activityInfo.exported;
        }
        Log.w(Logger.INSTANCE.getTag(), "isIntentCallable - more than one activity is valid. Checking the first one");
        return queryIntentActivities.get(0).activityInfo.exported;
    }

    public static final boolean isServiceIntentCallable(PackageManager packageManager, Intent intent) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        Intrinsics.checkNotNullParameter(intent, "intent");
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentServices, "queryIntentServices(intent, 0)");
        if (queryIntentServices.size() > 0) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean isPackageSignatureValid$default(PackageManager packageManager, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = MOTO_SIGNATURE_PACKAGE_NAME;
        }
        return isPackageSignatureValid(packageManager, str, str2);
    }

    public static final boolean isPackageSignatureValid(PackageManager packageManager, String str, String str2) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(str2, "standardPackage");
        int checkSignatures = packageManager.checkSignatures(str2, str);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "isPackageSignatureValid: " + checkSignatures + ",  packageName: " + str);
        }
        return checkSignatures == 0;
    }

    public static final void disableActivity(PackageManager packageManager, Context context, Class<? extends Activity> cls) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cls, "activityClass");
        updateActivityState(packageManager, context, cls, 2);
    }

    public static final void enableActivity(PackageManager packageManager, Context context, Class<? extends Activity> cls) {
        Intrinsics.checkNotNullParameter(packageManager, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cls, "activityClass");
        updateActivityState(packageManager, context, cls, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        r1 = (r1 = r1.activityInfo).packageName;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getPackageNameForIntent(android.content.pm.PackageManager r1, android.content.Intent r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            r0 = 65536(0x10000, float:9.18355E-41)
            android.content.pm.ResolveInfo r1 = r1.resolveActivity(r2, r0)
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x0015
            goto L_0x0020
        L_0x0015:
            android.content.pm.ActivityInfo r1 = r1.activityInfo
            if (r1 != 0) goto L_0x001a
            goto L_0x0020
        L_0x001a:
            java.lang.String r1 = r1.packageName
            if (r1 != 0) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r2 = r1
        L_0x0020:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.extensions.PackageManagerExtensionsKt.getPackageNameForIntent(android.content.pm.PackageManager, android.content.Intent):java.lang.String");
    }

    private static final void updateActivityState(PackageManager packageManager, Context context, Class<? extends Activity> cls, int i) {
        packageManager.setComponentEnabledSetting(new ComponentName(context, cls), i, 1);
    }
}
