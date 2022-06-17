package com.android.launcher3.icons.cache;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.LocaleList;
import android.os.UserHandle;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.util.ComponentKey;

public interface CachingLogic<T> {
    boolean addToMemCache() {
        return true;
    }

    ComponentName getComponent(T t);

    CharSequence getDescription(T t, CharSequence charSequence) {
        return charSequence;
    }

    String getKeywords(T t, LocaleList localeList) {
        return null;
    }

    CharSequence getLabel(T t);

    UserHandle getUser(T t);

    BitmapInfo loadIcon(Context context, T t);

    Drawable processCustomIcon(Context context, ComponentKey componentKey, Drawable drawable) {
        return drawable;
    }

    long getLastUpdatedTime(T t, PackageInfo packageInfo) {
        return packageInfo.lastUpdateTime;
    }
}
