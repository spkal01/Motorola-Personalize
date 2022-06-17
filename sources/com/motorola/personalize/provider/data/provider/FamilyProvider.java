package com.motorola.personalize.provider.data.provider;

import android.database.MatrixCursor;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.provider.data.Family;

public class FamilyProvider {
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_DISPLAY_TYPE = "DISPLAY_TYPE";
    private static final String COLUMN_ENABLED = "ENABLED";
    private static final String COLUMN_FAMILY_COLOR = "FAMILY_COLOR";
    private static final String COLUMN_FAMILY_HEADER = "FAMILY_HEADER_TEXT";
    private static final String COLUMN_FAMILY_IMAGE = "FAMILY_IMAGE";
    private static final String COLUMN_FAMILY_LAYOUT = "FAMILY_LAYOUT";
    private static final String COLUMN_FAMILY_STATUS_BAR_COLOR = "FAMILY_STATUS_BAR_COLOR";
    private static final String COLUMN_FAMILY_SUPPORT = "FAMILY_SUPPORT_TEXT";
    private static final String COLUMN_ICON = "ICON";
    private static final String COLUMN_ID = "_ID";
    private static final String COLUMN_PRIORITY = "PRIORITY";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_VALUE_PROP_DESC = "VALUE_PROP_DESC";
    private static final String COLUMN_VALUE_PROP_IMAGE = "VALUE_PROP_IMAGE";
    private static final String COLUMN_VALUE_PROP_TITLE = "VALUE_PROP_TITLE";
    private static final String COLUMN_VERSION = "VERSION";
    public static final int DESKTOP = 2;
    public static final int PHONE = 1;
    public static final int PHONE_AND_DESKTOP = 3;

    public MatrixCursor getFamilies() {
        MatrixCursor familyCursor = getFamilyCursor();
        addPersonalizeFamily(familyCursor);
        return familyCursor;
    }

    private void addPersonalizeFamily(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.personalize_family_name)).add(COLUMN_DESCRIPTION, Integer.valueOf(C1057R.string.personalize_family_description)).add(COLUMN_ENABLED, 1).add(COLUMN_FAMILY_IMAGE, Integer.valueOf(C1057R.C1059drawable.ic_family_personalize_card)).add(COLUMN_FAMILY_COLOR, Integer.valueOf(C1057R.C1058color.personalize_family_color)).add(COLUMN_ICON, Integer.valueOf(C1057R.C1059drawable.ic_personalize_flat)).add(COLUMN_VALUE_PROP_TITLE, Integer.valueOf(C1057R.string.value_prop_love_title)).add(COLUMN_VALUE_PROP_DESC, Integer.valueOf(C1057R.string.value_prop_love_description)).add(COLUMN_VALUE_PROP_IMAGE, Integer.valueOf(C1057R.C1059drawable.ic_personalize_value_prop)).add(COLUMN_FAMILY_LAYOUT, 1).add(COLUMN_DISPLAY_TYPE, 1).add(COLUMN_FAMILY_HEADER, Integer.valueOf(C1057R.string.personalize_family_header)).add(COLUMN_FAMILY_SUPPORT, Integer.valueOf(C1057R.string.personalize_family_support)).add(COLUMN_FAMILY_STATUS_BAR_COLOR, Integer.valueOf(C1057R.C1058color.personalize_family_status_bar_color));
    }

    private MatrixCursor getFamilyCursor() {
        return new MatrixCursor(getFamilyColumns());
    }

    private String[] getFamilyColumns() {
        return new String[]{COLUMN_ID, COLUMN_PRIORITY, COLUMN_VERSION, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_ENABLED, COLUMN_FAMILY_COLOR, COLUMN_FAMILY_IMAGE, COLUMN_ICON, COLUMN_VALUE_PROP_TITLE, COLUMN_VALUE_PROP_DESC, COLUMN_VALUE_PROP_IMAGE, COLUMN_FAMILY_LAYOUT, COLUMN_DISPLAY_TYPE, COLUMN_FAMILY_HEADER, COLUMN_FAMILY_SUPPORT, COLUMN_FAMILY_STATUS_BAR_COLOR};
    }
}
