package com.motorola.personalize.model;

import java.util.List;

public class StyleList {
    private String mAppliedStyle;
    private List<Style> mList;

    public List<Style> getList() {
        return this.mList;
    }

    public void setList(List<Style> list) {
        this.mList = list;
    }

    public String getAppliedStyle() {
        return this.mAppliedStyle;
    }

    public void setAppliedStyle(String str) {
        this.mAppliedStyle = str;
    }
}
