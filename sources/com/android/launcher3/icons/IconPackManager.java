package com.android.launcher3.icons;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class IconPackManager {
    private static final String ICON_PACK_PREFERENCES_KEY = "icon_pack.prefs";
    public static final String PREF_ICON_PACK = "pref_icon_pack";
    private static final String TAG = "IconPackManager";

    public static List<IconPack> getAvailableIconPacks(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(IconPack.DEFAULT_ICON_PACK);
        PackageManager packageManager = context.getPackageManager();
        ArrayList<ResolveInfo> arrayList2 = new ArrayList<>();
        for (String intent : context.getResources().getStringArray(C0738R.array.icon_pack_actions)) {
            arrayList2.addAll(packageManager.queryIntentActivities(new Intent(intent), 128));
        }
        Log.d(TAG, "IconPack resolve infos: " + arrayList2);
        for (ResolveInfo resolveInfo : arrayList2) {
            IconPack newIconPack = IconPack.newIconPack(context, resolveInfo.activityInfo.packageName);
            try {
                if (!arrayList.contains(newIconPack)) {
                    newIconPack.setName(context.getPackageManager().getApplicationLabel(packageManager.getApplicationInfo(newIconPack.getPackageName(), 128)).toString());
                    arrayList.add(newIconPack);
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d(TAG, "Cannot find icon package " + newIconPack.getPackageName());
            }
        }
        return arrayList;
    }

    public static SharedPreferences getPrefs(Context context) {
        return context.getApplicationContext().getSharedPreferences(ICON_PACK_PREFERENCES_KEY, 0);
    }

    public static String getAppliedIconPack(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), PREF_ICON_PACK);
        return string == null ? IconPack.DEFAULT_ICON_PACK_PKG_NAME : string;
    }

    public static void saveAppliedIconPack(Context context, String str) {
        Log.d(TAG, "Save applied iconPack into SharedPreferences: " + str);
        Settings.Secure.putString(context.getContentResolver(), PREF_ICON_PACK, str);
    }

    public static boolean isIconPackChanged(Context context, String str) {
        return !getAppliedIconPack(context).equals(str);
    }

    public static String getName(Context context, IconPack iconPack) {
        if (iconPack.isDefault()) {
            return context.getString(C0738R.string.system);
        }
        return iconPack.getName();
    }
}
