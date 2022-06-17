package com.motorola.styles.model;

import com.motorola.styles.model.OnlineFontServiceImpl;
import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.motorola.styles.model.-$$Lambda$OnlineFontServiceImpl$bdviVp7oDLNU97qMAs-d9W4mgmk  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$OnlineFontServiceImpl$bdviVp7oDLNU97qMAsd9W4mgmk implements FilenameFilter {
    public static final /* synthetic */ $$Lambda$OnlineFontServiceImpl$bdviVp7oDLNU97qMAsd9W4mgmk INSTANCE = new $$Lambda$OnlineFontServiceImpl$bdviVp7oDLNU97qMAsd9W4mgmk();

    private /* synthetic */ $$Lambda$OnlineFontServiceImpl$bdviVp7oDLNU97qMAsd9W4mgmk() {
    }

    public final boolean accept(File file, String str) {
        return OnlineFontServiceImpl.Fonts.FONT_FILE_PATTERN.matcher(str).matches();
    }
}
