package com.motorola.styles.model;

import android.content.Context;

public class Option {
    private boolean isPlaceHolder = false;
    protected String mName;
    protected boolean mUninstallable = false;
    protected String mValue;

    public Option(String str, String str2) {
        this.mValue = str;
        this.mName = str2;
    }

    public void setValue(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public String getName() {
        return this.mName;
    }

    public String getName(Context context) {
        return getName();
    }

    public boolean isUninstallable() {
        return this.mUninstallable;
    }

    public void setUninstallable(boolean z) {
        this.mUninstallable = z;
    }

    public boolean isPlaceHolder() {
        return this.isPlaceHolder;
    }

    public void setPlaceHolder(boolean z) {
        this.isPlaceHolder = z;
    }
}
