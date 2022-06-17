package com.motorola.styles.model.providers;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.motorola.styles.model.RingtoneOption;

public final class RingtoneOptionsProvider extends StyleOptionsProvider<RingtoneOption> {
    private static final String TAG = "RingtoneOptionsProvider";
    private RingtoneOption mDefaultOption;
    private RingtoneManager mRingtoneManager;

    public RingtoneOptionsProvider(Context context) {
        super(context);
        RingtoneManager ringtoneManager = new RingtoneManager(context);
        this.mRingtoneManager = ringtoneManager;
        ringtoneManager.setType(1);
    }

    /* access modifiers changed from: protected */
    public void loadOptions(Bundle bundle) {
        addDefault();
        Cursor cursor = this.mRingtoneManager.getCursor();
        while (cursor.moveToNext()) {
            try {
                String string = cursor.getString(cursor.getColumnIndex("title"));
                Uri ringtoneUri = this.mRingtoneManager.getRingtoneUri(cursor.getPosition());
                Log.d(TAG, "Ringtone: " + string + " | " + ringtoneUri);
                this.mOptions.add(new RingtoneOption(ringtoneUri, string));
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (cursor != null) {
            cursor.close();
            return;
        }
        return;
        throw th;
    }

    private void addDefault() {
        RingtoneOption ringtoneOption = new RingtoneOption(RingtoneOption.NO_RINGTONE, "");
        this.mOptions.add(ringtoneOption);
        this.mDefaultOption = ringtoneOption;
    }

    public RingtoneOption getAppliedOption() {
        return this.mDefaultOption;
    }

    public static String getDefaultRingtoneValue(Context context) {
        Uri actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(context, 1);
        if (actualDefaultRingtoneUri == null) {
            return RingtoneOption.NO_RINGTONE;
        }
        return actualDefaultRingtoneUri.toString();
    }
}
