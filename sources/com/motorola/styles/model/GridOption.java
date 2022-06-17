package com.motorola.styles.model;

import android.content.Context;
import android.util.Log;
import com.motorola.styles.C1087R;
import com.motorola.styles.LogConfig;
import java.text.NumberFormat;

public class GridOption extends Option {
    public static final String GRID_3_4_VALUE = "4_by_3";
    public static final String GRID_4_4_VALUE = "4_by_4";
    public static final String GRID_4_5_VALUE = "5_by_4";
    public static final String GRID_4_6_VALUE = "6_by_4";
    public static final String GRID_5_5_VALUE = "5_by_5";
    public static final String GRID_5_6_VALUE = "6_by_5";
    private final int mCol;
    private final int mGridDrawableId;
    private final int mRow;

    public GridOption(String str, int i, int i2, int i3) {
        super(str, i + "x" + i2);
        this.mCol = i;
        this.mRow = i2;
        this.mGridDrawableId = i3;
        if (LogConfig.DBG) {
            Log.d("Styles", "GridOption: " + this.mName + " | " + str);
        }
    }

    public int getCol() {
        return this.mCol;
    }

    public int getRow() {
        return this.mRow;
    }

    public int getGridDrawableId() {
        return this.mGridDrawableId;
    }

    public String getName(Context context) {
        if (context != null) {
            return getGridName(context, this.mCol, this.mRow);
        }
        return this.mName;
    }

    public static String getGridName(Context context, int i, int i2) {
        NumberFormat instance = NumberFormat.getInstance();
        return context.getString(C1087R.string.grid_name, new Object[]{instance.format((long) i), instance.format((long) i2)});
    }

    public static String getGridValue(int i, int i2) {
        return i + "_by_" + i2;
    }

    public String toString() {
        return "GridOption@" + hashCode() + "{mValue='" + this.mValue + '\'' + ", mName='" + this.mName + '\'' + ", mUninstallable=" + this.mUninstallable + ", mCol=" + this.mCol + ", mRow=" + this.mRow + '}';
    }
}
