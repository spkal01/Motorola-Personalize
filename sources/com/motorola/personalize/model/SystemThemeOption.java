package com.motorola.personalize.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, mo15462d2 = {"Lcom/motorola/personalize/model/SystemThemeOption;", "", "title", "", "iconRes", "", "nameRes", "(Ljava/lang/String;II)V", "getIconRes", "()I", "setIconRes", "(I)V", "getNameRes", "setNameRes", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "toString", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: SystemThemeOption.kt */
public final class SystemThemeOption {
    private int iconRes;
    private int nameRes;
    private String title;

    public SystemThemeOption(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "title");
        this.title = str;
        this.iconRes = i;
        this.nameRes = i2;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final int getNameRes() {
        return this.nameRes;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setIconRes(int i) {
        this.iconRes = i;
    }

    public final void setNameRes(int i) {
        this.nameRes = i;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String toString() {
        return "SystemThemeOption{title=" + this.title + '}';
    }
}
