package com.motorola.personalize.app.sound;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.data.IntentData;
import com.motorola.personalize.data.SoundsViewData;

public class SoundUtil {
    private static final String ACTION = "com.motorola.personalize.action.SOUNDS_SETTING";
    public static final int TYPE_ALARM = 4;
    public static final int TYPE_NOTIFICATION = 2;
    public static final int TYPE_RINGTONE = 1;
    public static final int TYPE_RINGTONE_2 = 64;
    private static boolean isDoubleSIM = false;

    public static int getIcon(int i) {
        return i != 2 ? i != 4 ? C1057R.C1059drawable.ic_ringtones : C1057R.C1059drawable.ic_alarms : C1057R.C1059drawable.ic_notifications;
    }

    public static void setDoubleSIM(boolean z) {
        isDoubleSIM = z;
    }

    public static boolean isDoubleSIM() {
        return isDoubleSIM;
    }

    public static String getRingtoneTitle(Context context, Uri uri) {
        return Ringtone.getTitle(context, uri, false, true);
    }

    private static Bundle newBundle(int i, int i2, Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putInt("ringtone_type", i);
        bundle.putInt("color", i2);
        bundle.putParcelable("android.intent.extra.ringtone.EXISTING_URI", uri);
        return bundle;
    }

    public static SoundsViewData newData(Context context, int i, int i2, String str) {
        Uri actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(context, i);
        return new SoundsViewData(i, actualDefaultRingtoneUri, Integer.valueOf(i2), new IntentData(ACTION, str, newBundle(i, i2, actualDefaultRingtoneUri)));
    }
}
