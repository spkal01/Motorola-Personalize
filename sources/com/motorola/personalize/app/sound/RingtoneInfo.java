package com.motorola.personalize.app.sound;

import android.net.Uri;

public class RingtoneInfo {
    private final String mTitle;
    private final Uri mUri;

    public RingtoneInfo(String str, Uri uri) {
        this.mTitle = str;
        this.mUri = uri;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Uri getUri() {
        return this.mUri;
    }
}
