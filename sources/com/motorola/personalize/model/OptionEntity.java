package com.motorola.personalize.model;

public class OptionEntity {
    private long insertTime;
    private int lightColor;

    public long getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(long j) {
        this.insertTime = j;
    }

    public int getLightColor() {
        return this.lightColor;
    }

    public void setLightColor(int i) {
        this.lightColor = i;
    }

    public OptionEntity init(int i) {
        this.lightColor = i;
        this.insertTime = System.currentTimeMillis();
        return this;
    }

    public ColorOption toColorOption() {
        return new ColorOption(String.valueOf(this.lightColor), this.lightColor, 3, false);
    }
}
