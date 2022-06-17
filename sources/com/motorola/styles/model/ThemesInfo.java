package com.motorola.styles.model;

import java.util.ArrayList;
import java.util.List;

public class ThemesInfo {
    private String mAppliedTheme = "";
    private String mSelectedTheme = "";
    private final List<Theme> mThemes = new ArrayList();

    public List<Theme> getThemes() {
        return this.mThemes;
    }

    public void setThemes(List<Theme> list) {
        this.mThemes.clear();
        this.mThemes.addAll(list);
    }

    public String getAppliedTheme() {
        return this.mAppliedTheme;
    }

    public void setAppliedTheme(String str) {
        this.mAppliedTheme = str;
    }

    public String getSelectedTheme() {
        return this.mSelectedTheme;
    }

    public void setSelectedTheme(String str) {
        this.mSelectedTheme = str;
    }

    public void set(ThemesInfo themesInfo) {
        setThemes(themesInfo.getThemes());
        setAppliedTheme(themesInfo.getAppliedTheme());
        setSelectedTheme(themesInfo.getSelectedTheme());
    }
}
