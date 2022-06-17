package com.motorola.personalize.extensions;

import android.os.Build;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo15461d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\rR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo15462d2 = {"Lcom/motorola/personalize/extensions/Logger;", "", "()V", "ANONYMOUS_CLASS_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "CLASS_STACK_INDEX", "", "DEBUG", "", "getDEBUG", "()Z", "DEFAULT_TAG", "", "TAG_PREFIX", "isProductBuild", "getTag", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: Logger.kt */
public final class Logger {
    private static final Pattern ANONYMOUS_CLASS_PATTERN = Pattern.compile("(\\$\\d+)+$");
    private static final int CLASS_STACK_INDEX = 3;
    private static final boolean DEBUG;
    private static final String DEFAULT_TAG = "PERSONALIZE.MotoApp";
    public static final Logger INSTANCE = new Logger();
    private static final String TAG_PREFIX = "PERSONALIZE.";
    private static final boolean isProductBuild;

    private Logger() {
    }

    static {
        boolean z = false;
        if (Intrinsics.areEqual((Object) Build.TYPE, (Object) "user")) {
            String str = Build.TAGS;
            Intrinsics.checkNotNullExpressionValue(str, "TAGS");
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "intcfg", false, 2, (Object) null)) {
                z = true;
            }
        }
        isProductBuild = z;
        DEBUG = !z;
    }

    public final boolean getDEBUG() {
        return DEBUG;
    }

    public final String getTag() {
        if (!DEBUG) {
            return DEFAULT_TAG;
        }
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        Matcher matcher = ANONYMOUS_CLASS_PATTERN.matcher(className);
        if (matcher.find()) {
            className = matcher.replaceAll("");
        }
        Intrinsics.checkNotNullExpressionValue(className, "tag");
        Intrinsics.checkNotNullExpressionValue(className, "tag");
        String substring = className.substring(StringsKt.lastIndexOf$default((CharSequence) className, '.', 0, false, 6, (Object) null) + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
        return Intrinsics.stringPlus(TAG_PREFIX, substring);
    }
}
