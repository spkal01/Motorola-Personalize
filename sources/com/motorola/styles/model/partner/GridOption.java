package com.motorola.styles.model.partner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.motorola.styles.C1087R;

public final class GridOption {
    public static final String TAG_NAME = "grid-option";
    public final String name;
    public final int numColumns;
    public final int numRows;

    public GridOption(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1087R.styleable.GridDisplayOption);
        this.name = obtainStyledAttributes.getString(C1087R.styleable.GridDisplayOption_name);
        this.numRows = obtainStyledAttributes.getInt(C1087R.styleable.GridDisplayOption_numRows, 0);
        this.numColumns = obtainStyledAttributes.getInt(C1087R.styleable.GridDisplayOption_numColumns, 0);
        obtainStyledAttributes.recycle();
    }
}
