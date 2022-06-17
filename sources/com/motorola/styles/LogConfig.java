package com.motorola.styles;

import android.os.Build;
import java.util.Locale;

public class LogConfig {
    public static final boolean DBG = (Build.TYPE.toLowerCase(Locale.ROOT).contains("debug") || Build.TYPE.toLowerCase(Locale.ROOT).equals("eng") || Build.TAGS.toLowerCase(Locale.ROOT).contains("intcfg") || Build.TAGS.toLowerCase(Locale.ROOT).contains("bldccfg"));
    public static final String TAG = "Styles";
}
