package com.motorola.styles.model;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;

public class RingtoneOption extends Option {
    public static final String NO_RINGTONE = "noRingtone";
    private Uri mRingtoneUri;

    public RingtoneOption(String str, String str2) {
        super(str, str2);
    }

    public RingtoneOption(Uri uri, String str) {
        super(uri.toString(), str);
        this.mRingtoneUri = uri;
    }

    public boolean isNoRingtone() {
        return NO_RINGTONE.equals(getValue());
    }

    public String toString() {
        return "ringtoneOption@" + hashCode() + "{mValue='" + this.mValue + '\'' + ", mName='" + this.mName + '\'' + '}';
    }

    public Uri getRingtoneUri() {
        return this.mRingtoneUri;
    }

    public AudioAttributes getAudioAttributes(Context context) {
        Uri uri;
        if (context == null || (uri = this.mRingtoneUri) == null) {
            return null;
        }
        return RingtoneManager.getRingtone(context, uri).getAudioAttributes();
    }
}
