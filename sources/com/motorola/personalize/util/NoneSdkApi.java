package com.motorola.personalize.util;

import android.app.WallpaperManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.IOException;

public class NoneSdkApi {
    public static final String EXTRA_RINGTONE_AUDIO_ATTRIBUTES_FLAGS = "android.intent.extra.ringtone.AUDIO_ATTRIBUTES_FLAGS";
    public static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;

    public static int myUserId() {
        return UserHandle.myUserId();
    }

    public static boolean isManagedProfile(Context context, int i) {
        return UserManager.get(context).isManagedProfile(i);
    }

    public static Uri getUiNightModeUri() {
        return Settings.Secure.getUriFor("ui_night_mode");
    }

    public static int getUiNightMode(ContentResolver contentResolver) {
        return Settings.Secure.getInt(contentResolver, "ui_night_mode", 0);
    }

    public static boolean isMultiSimEnabled(Context context) {
        return TelephonyManager.from(context).isMultiSimEnabled();
    }

    public static Uri getRingtoneUri(Ringtone ringtone) {
        return ringtone.getUri();
    }

    public static int getUserIdFromUri(Uri uri, int i) {
        return ContentProvider.getUserIdFromUri(uri, i);
    }

    public static Uri getUriWithoutUserId(Uri uri) {
        return ContentProvider.getUriWithoutUserId(uri);
    }

    public static Drawable getUserBadgeForDensityNoBackground(Context context, int i) {
        return context.getPackageManager().getUserBadgeForDensityNoBackground(UserHandle.of(i), -1);
    }

    public static Uri addCustomExternalRingtone(RingtoneManager ringtoneManager, Uri uri, int i) {
        try {
            return ringtoneManager.addCustomExternalRingtone(uri, i);
        } catch (IOException | IllegalArgumentException e) {
            Log.e("NoneSdkApi", "Unable to add new ringtone", e);
            return null;
        }
    }

    public static int getColorWithLStar(int i, float f) {
        return Build.VERSION.SDK_INT >= 31 ? ColorStateList.valueOf(i).withLStar(f).getColors()[0] : i;
    }

    public static ColorStateList getColorStateListWithLStar(int i, float f) {
        if (Build.VERSION.SDK_INT >= 31) {
            return ColorStateList.valueOf(i).withLStar(f);
        }
        return ColorStateList.valueOf(i);
    }

    public static Bitmap getWallpaperBitmap(WallpaperManager wallpaperManager, int i) {
        return wallpaperManager.getBitmap(i);
    }
}
