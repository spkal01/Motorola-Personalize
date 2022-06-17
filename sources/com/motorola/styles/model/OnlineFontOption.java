package com.motorola.styles.model;

import android.graphics.Typeface;
import java.io.File;

public class OnlineFontOption extends FontOption {
    private final File fontFile;

    public String getOuterPackageName() {
        return "com.android.theme.font.customizesource";
    }

    public OnlineFontOption(String str, String str2, Typeface typeface, File file) {
        super(str, str2, typeface, typeface);
        setOnlineFont(true);
        this.fontFile = file;
    }

    public File getFontFile() {
        return this.fontFile;
    }
}
