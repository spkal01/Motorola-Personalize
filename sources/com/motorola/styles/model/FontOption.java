package com.motorola.styles.model;

import android.graphics.Typeface;
import android.util.Log;
import com.motorola.styles.LogConfig;

public class FontOption extends Option {
    private boolean isOnlineFont = false;
    private Typeface mBodyFont;
    private Typeface mHeadlineFont;

    public FontOption(String str, String str2, Typeface typeface, Typeface typeface2) {
        super(str, str2);
        this.mHeadlineFont = typeface;
        this.mBodyFont = typeface2;
        if (LogConfig.DBG) {
            Log.d("Styles", "FontOption: " + str + " | " + typeface + " | " + typeface2);
        }
    }

    public Typeface getHeadlineFont() {
        return this.mHeadlineFont;
    }

    public Typeface getBodyFont() {
        return this.mBodyFont;
    }

    public boolean isOnlineFont() {
        return this.isOnlineFont;
    }

    public void setOnlineFont(boolean z) {
        this.isOnlineFont = z;
    }

    public String toString() {
        return "FontOption@" + hashCode() + "{mValue='" + this.mValue + '\'' + ", mName='" + this.mName + '\'' + ", mUninstallable=" + this.mUninstallable + ", mHeadlineFont=" + this.mHeadlineFont + ", mBodyFont=" + this.mBodyFont + ", isOnlineFont=" + this.isOnlineFont + '}';
    }
}
