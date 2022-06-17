package com.motorola.styles.model.partner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.motorola.styles.C1087R;

public class DisplayOption {
    boolean canBeDefault;
    GridOption grid;
    float iconSize;
    float iconTextSize;
    float landscapeIconSize;
    float minHeightDps;
    float minWidthDps;
    String name;

    DisplayOption() {
        this.grid = null;
        this.name = null;
        this.minWidthDps = 0.0f;
        this.minHeightDps = 0.0f;
        this.canBeDefault = false;
    }

    DisplayOption(GridOption gridOption, Context context, AttributeSet attributeSet) {
        this.grid = gridOption;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1087R.styleable.ProfileDisplayOption);
        this.name = obtainStyledAttributes.getString(C1087R.styleable.ProfileDisplayOption_name);
        this.minWidthDps = obtainStyledAttributes.getFloat(C1087R.styleable.ProfileDisplayOption_minWidthDps, 0.0f);
        this.minHeightDps = obtainStyledAttributes.getFloat(C1087R.styleable.ProfileDisplayOption_minHeightDps, 0.0f);
        this.canBeDefault = obtainStyledAttributes.getBoolean(C1087R.styleable.ProfileDisplayOption_canBeDefault, false);
        this.iconSize = obtainStyledAttributes.getFloat(C1087R.styleable.ProfileDisplayOption_iconImageSize, 0.0f);
        this.landscapeIconSize = obtainStyledAttributes.getFloat(C1087R.styleable.ProfileDisplayOption_landscapeIconSize, this.iconSize);
        this.iconTextSize = obtainStyledAttributes.getFloat(C1087R.styleable.ProfileDisplayOption_iconTextSize, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private DisplayOption multiply(float f) {
        this.iconSize *= f;
        this.landscapeIconSize *= f;
        this.iconTextSize *= f;
        return this;
    }

    private DisplayOption add(DisplayOption displayOption) {
        this.iconSize += displayOption.iconSize;
        this.landscapeIconSize += displayOption.landscapeIconSize;
        this.iconTextSize += displayOption.iconTextSize;
        return this;
    }
}
