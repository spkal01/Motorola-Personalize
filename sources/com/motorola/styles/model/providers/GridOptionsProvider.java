package com.motorola.styles.model.providers;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.motorola.styles.C1087R;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.partner.Partner;

public class GridOptionsProvider extends StyleOptionsProvider<GridOption> {
    private static final String DEFAULT_GRID = "default_grid";
    private static final Object DEFAULT_GRID_VALUE_LOCK = new Object();
    private static final Uri LAUNCHER_SETTINGS_URI = Uri.parse("content://com.motorola.launcher3.settings/settings");
    private static final String METHOD_GET_SCREEN_ROWS_COLUMNS = "get_home_screen_rows_columns";
    private static final String TAG = "GridOptionsProvider";
    private static String sDefaultGridValue = null;
    private final String mGridProviderAuthority;
    private final ProviderInfo mProviderInfo;

    public GridOptionsProvider(Context context) {
        super(context);
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME"), 65664);
        ProviderInfo providerInfo = null;
        if (resolveActivity == null || resolveActivity.activityInfo == null || resolveActivity.activityInfo.metaData == null) {
            this.mGridProviderAuthority = null;
        } else {
            this.mGridProviderAuthority = resolveActivity.activityInfo.metaData.getString(context.getString(C1087R.string.grid_control_metadata_name));
        }
        this.mProviderInfo = !TextUtils.isEmpty(this.mGridProviderAuthority) ? this.mContext.getPackageManager().resolveContentProvider(this.mGridProviderAuthority, 0) : providerInfo;
    }

    /* access modifiers changed from: protected */
    public void loadOptions(Bundle bundle) {
        getDefaultGridValue(getContext());
        this.mOptions.add(new GridOption(GridOption.GRID_3_4_VALUE, 3, 4, C1087R.C1089drawable.grid_3x4));
        this.mOptions.add(new GridOption(GridOption.GRID_4_4_VALUE, 4, 4, C1087R.C1089drawable.grid_4x4));
        this.mOptions.add(new GridOption(GridOption.GRID_4_5_VALUE, 4, 5, C1087R.C1089drawable.grid_4x5));
        this.mOptions.add(new GridOption(GridOption.GRID_4_6_VALUE, 4, 6, C1087R.C1089drawable.grid_4x6));
        this.mOptions.add(new GridOption(GridOption.GRID_5_5_VALUE, 5, 5, C1087R.C1089drawable.grid_5x5));
        this.mOptions.add(new GridOption(GridOption.GRID_5_6_VALUE, 5, 6, C1087R.C1089drawable.grid_5x6));
        StylesUtilities.sortOrder(getContext(), this.mOptions, C1087R.array.grids_order);
    }

    public static String getDefaultGridValue(Context context) {
        if (sDefaultGridValue == null) {
            synchronized (DEFAULT_GRID_VALUE_LOCK) {
                if (sDefaultGridValue == null) {
                    String gridName = Partner.getGridName(context);
                    if (TextUtils.isEmpty(gridName)) {
                        gridName = GridOption.GRID_5_5_VALUE;
                    }
                    sDefaultGridValue = gridName;
                }
            }
        }
        return sDefaultGridValue;
    }

    public int applyGrid(String str) {
        Uri build = new Uri.Builder().scheme("content").authority(getGridAuthority()).appendPath(DEFAULT_GRID).build();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        return this.mContext.getContentResolver().update(build, contentValues, (String) null, (String[]) null);
    }

    private String getGridAuthority() {
        ProviderInfo providerInfo = this.mProviderInfo;
        if (providerInfo == null) {
            return "com.motorola.launcher3.grid_control";
        }
        return providerInfo.authority;
    }

    public GridOption getAppliedOption() {
        Bundle call = this.mContext.getContentResolver().call(LAUNCHER_SETTINGS_URI, METHOD_GET_SCREEN_ROWS_COLUMNS, (String) null, (Bundle) null);
        GridOption gridOption = getGridOption(GridOption.getGridValue(call.getInt("rows"), call.getInt("columns")));
        return gridOption == null ? getGridOption(getDefaultGridValue(this.mContext)) : gridOption;
    }

    private GridOption getGridOption(String str) {
        for (Option option : this.mOptions) {
            if (option.getValue().equals(str)) {
                return (GridOption) option;
            }
        }
        return null;
    }
}
