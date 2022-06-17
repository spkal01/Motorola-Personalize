package com.motorola.personalize.extensions;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\b\u001a \u0010\f\u001a\u00020\u0001*\u00020\b2\b\b\u0001\u0010\r\u001a\u00020\u00012\n\u0010\u000e\u001a\u00060\u000fR\u00020\b\u001a\u0012\u0010\u0010\u001a\u00020\u0011*\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0001\u001a\n\u0010\u0013\u001a\u00020\u0001*\u00020\b\u001a\n\u0010\u0014\u001a\u00020\u0015*\u00020\b\u001a\n\u0010\u0016\u001a\u00020\u0015*\u00020\b\u001a\u0012\u0010\u0017\u001a\u00020\u0015*\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo15462d2 = {"DEFAULT_STATUS_BAR_HEIGHT", "", "NAVIGATION_BAR_DIMEN", "", "NAVIGATION_BAR_HEIGHT", "NAVIGATION_BAR_PACKAGE", "adjustLayoutMarginTop", "", "Landroid/content/res/Resources;", "view", "Landroid/view/View;", "getBottomBarHeight", "getColor32Bits", "colorId", "theme", "Landroid/content/res/Resources$Theme;", "getFloatValue", "", "resId", "getStatusBarHeight", "isLandscape", "", "isRTL", "isValidResourceId", "app_release"}, mo15463k = 2, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ResourcesExtensions.kt */
public final class ResourcesExtensionsKt {
    private static final int DEFAULT_STATUS_BAR_HEIGHT = 24;
    private static final String NAVIGATION_BAR_DIMEN = "dimen";
    private static final String NAVIGATION_BAR_HEIGHT = "navigation_bar_height";
    private static final String NAVIGATION_BAR_PACKAGE = "android";

    public static final int getColor32Bits(Resources resources, int i, Resources.Theme theme) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        Intrinsics.checkNotNullParameter(theme, "theme");
        return Color.parseColor(Intrinsics.stringPlus("#", Integer.toHexString(resources.getColor(i, theme))));
    }

    public static final void adjustLayoutMarginTop(Resources resources, View view) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, getStatusBarHeight(resources), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            view.setLayoutParams(layoutParams);
        }
    }

    public static final int getStatusBarHeight(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        int identifier = resources.getIdentifier("status_bar_height", NAVIGATION_BAR_DIMEN, "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 24;
    }

    public static final int getBottomBarHeight(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        int identifier = resources.getIdentifier(NAVIGATION_BAR_HEIGHT, NAVIGATION_BAR_DIMEN, "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static final boolean isValidResourceId(Resources resources, int i) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        try {
            String resourceName = resources.getResourceName(i);
            Intrinsics.checkNotNullExpressionValue(resourceName, "getResourceName(resId)");
            if (resourceName.length() > 0) {
                return true;
            }
            return false;
        } catch (Resources.NotFoundException e) {
            Log.e(Logger.INSTANCE.getTag(), Intrinsics.stringPlus("Resources.NotFoundException: ", e.getMessage()));
            Log.w(Logger.INSTANCE.getTag(), Intrinsics.stringPlus("Invalid resource Id: ", Integer.valueOf(i)));
            return false;
        }
    }

    public static final boolean isLandscape(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        return resources.getConfiguration().orientation == 2;
    }

    public static final boolean isRTL(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        return resources.getConfiguration().getLayoutDirection() == 1;
    }

    public static final float getFloatValue(Resources resources, int i) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        if (Build.VERSION.SDK_INT >= 29) {
            return resources.getFloat(i);
        }
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        return typedValue.getFloat();
    }
}
