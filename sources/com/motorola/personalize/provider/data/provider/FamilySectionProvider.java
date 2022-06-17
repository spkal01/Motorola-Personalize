package com.motorola.personalize.provider.data.provider;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.provider.data.Family;
import com.motorola.personalize.provider.data.FamilySection;

public class FamilySectionProvider {
    private static final String COLUMN_DISPLAY_TYPE = "DISPLAY_TYPE";
    private static final String COLUMN_ENABLED = "ENABLED";
    private static final String COLUMN_FAMILY_ID = "FAMILY_ID";
    private static final String COLUMN_ID = "_ID";
    private static final String COLUMN_LAYOUT_TYPE = "LAYOUT_TYPE";
    private static final String COLUMN_PRIORITY = "PRIORITY";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_VERSION = "VERSION";

    public Cursor getSections() {
        MatrixCursor sectionCursor = getSectionCursor();
        addMakeAdjustmentsSection(sectionCursor);
        addApplyChangesSection(sectionCursor);
        return sectionCursor;
    }

    private void addMakeAdjustmentsSection(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.make_adjustments_section_title)).add(COLUMN_ENABLED, 1).add(COLUMN_LAYOUT_TYPE, 1).add(COLUMN_DISPLAY_TYPE, 3);
    }

    private void addApplyChangesSection(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, FamilySection.APPLY_CHANGES.getId()).add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 2).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.apply_changes_section_title)).add(COLUMN_ENABLED, 1).add(COLUMN_LAYOUT_TYPE, 2).add(COLUMN_DISPLAY_TYPE, 3);
    }

    private MatrixCursor getSectionCursor() {
        return new MatrixCursor(getSectionColumns());
    }

    private String[] getSectionColumns() {
        return new String[]{COLUMN_ID, COLUMN_FAMILY_ID, COLUMN_PRIORITY, COLUMN_VERSION, COLUMN_TITLE, COLUMN_ENABLED, COLUMN_LAYOUT_TYPE, COLUMN_DISPLAY_TYPE};
    }
}
