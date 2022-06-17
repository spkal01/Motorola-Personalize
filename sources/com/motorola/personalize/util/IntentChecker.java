package com.motorola.personalize.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.PackageManagerExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo15462d2 = {"Lcom/motorola/personalize/util/IntentChecker;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isIntentCallable", "", "intent", "Landroid/content/Intent;", "action", "", "packageName", "isServiceIntentCallable", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: IntentChecker.kt */
public final class IntentChecker {
    private final Context context;

    public IntentChecker(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final boolean isIntentCallable(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        boolean isIntentCallable = PackageManagerExtensionsKt.isIntentCallable(packageManager, intent);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "isIntentCallable - " + intent.getPackage() + '/' + intent.getAction() + " = " + isIntentCallable);
        }
        return isIntentCallable;
    }

    public final boolean isIntentCallable(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "action");
        Intrinsics.checkNotNullParameter(str2, "packageName");
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        boolean isIntentCallable = PackageManagerExtensionsKt.isIntentCallable(packageManager, intent);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "isIntentCallable - " + str + '/' + str2 + " = " + isIntentCallable);
        }
        return isIntentCallable;
    }

    public final boolean isServiceIntentCallable(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "action");
        Intrinsics.checkNotNullParameter(str2, "packageName");
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        boolean isServiceIntentCallable = PackageManagerExtensionsKt.isServiceIntentCallable(packageManager, intent);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "isIntentCallable - " + str + '/' + str2 + " = " + isServiceIntentCallable);
        }
        return isServiceIntentCallable;
    }
}
