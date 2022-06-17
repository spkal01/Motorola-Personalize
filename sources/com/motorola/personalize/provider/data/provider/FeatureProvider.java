package com.motorola.personalize.provider.data.provider;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.motorola.personalize.BuildConfig;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.provider.data.Family;
import com.motorola.personalize.provider.data.FamilySection;
import com.motorola.personalize.util.Utilities;
import com.motorola.styles.ResourceConstants;

public class FeatureProvider {
    private static final String COLUMN_ACTION_EXTRA = "ACTION_EXTRA";
    private static final String COLUMN_ACTION_EXTRA_VALUE = "ACTION_EXTRA_VALUE";
    private static final String COLUMN_ACTION_INTENT = "ACTION_INTENT";
    private static final String COLUMN_ACTION_PACKAGE = "ACTION_PACKAGE";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_DISPLAY_TYPE = "DISPLAY_TYPE";
    private static final String COLUMN_ENABLED = "ENABLED";
    private static final String COLUMN_FAMILY_ID = "FAMILY_ID";
    private static final String COLUMN_FEATURE_CARD_ICON = "FEATURE_CARD_ICON";
    private static final String COLUMN_FEATURE_COLOR = "FEATURE_COLOR";
    private static final String COLUMN_FEATURE_DESCRIPTION_COLOR = "FEATURE_DESCRIPTION_COLOR";
    private static final String COLUMN_FEATURE_ICON = "FEATURE_ICON";
    private static final String COLUMN_FEATURE_SECTION_PRIORITY = "FEATURE_SECTION_PRIORITY";
    private static final String COLUMN_FEATURE_TITLE_COLOR = "FEATURE_TITLE_COLOR";
    private static final String COLUMN_ID = "_ID";
    private static final String COLUMN_PRIORITY = "PRIORITY";
    private static final String COLUMN_SECTION_ID = "SECTION_ID";
    private static final String COLUMN_STYLE = "STYLE";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_TYPE = "TYPE";
    private static final String COLUMN_URI = "URI";
    private static final String COLUMN_VALUE = "VALUE";
    private static final String COLUMN_VERSION = "VERSION";
    private static final String FEATURE_URI = "com.motorola.personalize.provider/features";
    public static int THEMES_INDEX;

    public Cursor getFeatures(String str) {
        MatrixCursor featureCursor = getFeatureCursor();
        addGridFeature(featureCursor);
        addFontsFeature(featureCursor);
        addColorsFeature(featureCursor);
        addIconShapeFeature(featureCursor);
        addSoundsFeature(featureCursor);
        addDisplaySizeFeature(featureCursor);
        addFontSizeFeature(featureCursor);
        addSystemThemeFeature(featureCursor);
        addThemesFeature(featureCursor, str);
        THEMES_INDEX = 8;
        if (Utilities.PRC_BUILD) {
            addIconPackFeature(featureCursor);
        }
        return featureCursor;
    }

    private void addThemesFeature(MatrixCursor matrixCursor, String str) {
        matrixCursor.newRow().add(COLUMN_ID, "moto_love_styles").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_VERSION, 2).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_theme_title)).add(COLUMN_DESCRIPTION, Integer.valueOf(C1057R.string.feature_theme_description)).add(COLUMN_ENABLED, 1).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.mipmap.ic_personalize_styles)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_themes)).add(COLUMN_FEATURE_SECTION_PRIORITY, 21).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.THEMES").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.APPLY_CHANGES.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.tiles_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.tiles_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.tiles_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "moto_love_styles");
    }

    private void addIconPackFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "moto_love_icon_pack").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_VERSION, 2).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_icon_pack_title)).add(COLUMN_DESCRIPTION, Integer.valueOf(C1057R.string.feature_icon_pack_description)).add(COLUMN_ENABLED, 1).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.mipmap.ic_personalize_icon_pack)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_themes)).add(COLUMN_FEATURE_SECTION_PRIORITY, 22).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.ICON_PACK").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.APPLY_CHANGES.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.tiles_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.tiles_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.tiles_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "moto_love_icon_pack");
    }

    private void addSoundsFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_sounds").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 5).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_sounds_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 1).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_sounds_black_24dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_sound)).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.SOUNDS").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_sounds");
    }

    private void addDisplaySizeFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_display_size").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 6).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_display_size_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 1).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_display_size_black_24dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_screen_size)).add(COLUMN_ACTION_INTENT, "com.motorola.settings.action.DISPLAY_DENSITY").add(COLUMN_ACTION_PACKAGE, ResourceConstants.SETTINGS_PACKAGE).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_display_size");
    }

    private void addSystemThemeFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_system_theme").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 8).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_system_theme_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 1).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_system_theme_black_20dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_font_size)).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.STYLES").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_system_theme");
    }

    private void addFontSizeFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_font_size").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 7).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_font_size_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 1).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_font_size_black_24dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_font_size)).add(COLUMN_ACTION_INTENT, "com.motorola.settings.action.FONT_SCALE").add(COLUMN_ACTION_PACKAGE, ResourceConstants.SETTINGS_PACKAGE).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_font_size");
    }

    private void addGridFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_grid").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 1).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_grid_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 0).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_grid_black_24dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_grid)).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.STYLES").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_grid");
    }

    private void addFontsFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_fonts").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 2).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_fonts_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 0).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_fonts_black_24dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_fonts)).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.STYLES").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_fonts");
    }

    private void addColorsFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_colors").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 3).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_colors_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 0).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_color_black_24dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_colors)).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.STYLES").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_colors");
    }

    private void addIconShapeFeature(MatrixCursor matrixCursor) {
        matrixCursor.newRow().add(COLUMN_ID, "personalize_icon_shape").add(COLUMN_FAMILY_ID, Family.PERSONALIZE.getId()).add(COLUMN_PRIORITY, 1).add(COLUMN_FEATURE_SECTION_PRIORITY, 4).add(COLUMN_VERSION, 1).add(COLUMN_TITLE, Integer.valueOf(C1057R.string.feature_icon_shape_title)).add(COLUMN_DESCRIPTION, -1).add(COLUMN_ENABLED, 0).add(COLUMN_TYPE, 0).add(COLUMN_VALUE, 1).add(COLUMN_STYLE, 0).add(COLUMN_URI, FEATURE_URI).add(COLUMN_FEATURE_ICON, Integer.valueOf(C1057R.C1059drawable.ic_icon_shape_black_24dp)).add(COLUMN_FEATURE_CARD_ICON, Integer.valueOf(C1057R.C1059drawable.ic_feature_card_icon_shape)).add(COLUMN_ACTION_INTENT, "com.motorola.personalize.action.STYLES").add(COLUMN_ACTION_PACKAGE, BuildConfig.APPLICATION_ID).add(COLUMN_DISPLAY_TYPE, 3).add(COLUMN_SECTION_ID, FamilySection.MAKE_ADJUSTMENTS.getId()).add(COLUMN_FEATURE_COLOR, Integer.valueOf(C1057R.C1058color.personalize_features_color)).add(COLUMN_FEATURE_TITLE_COLOR, Integer.valueOf(C1057R.C1058color.round_features_title_color)).add(COLUMN_FEATURE_DESCRIPTION_COLOR, Integer.valueOf(C1057R.C1058color.round_features_desc_color)).add(COLUMN_ACTION_EXTRA, "feature_id").add(COLUMN_ACTION_EXTRA_VALUE, "personalize_icon_shape");
    }

    private MatrixCursor getFeatureCursor() {
        return new MatrixCursor(getFeatureColumns());
    }

    private String[] getFeatureColumns() {
        return new String[]{COLUMN_ID, COLUMN_FAMILY_ID, COLUMN_PRIORITY, COLUMN_VERSION, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_ENABLED, COLUMN_URI, COLUMN_TYPE, COLUMN_VALUE, COLUMN_STYLE, COLUMN_FEATURE_ICON, COLUMN_FEATURE_CARD_ICON, COLUMN_ACTION_INTENT, COLUMN_ACTION_PACKAGE, COLUMN_DISPLAY_TYPE, COLUMN_SECTION_ID, COLUMN_FEATURE_SECTION_PRIORITY, COLUMN_FEATURE_COLOR, COLUMN_FEATURE_TITLE_COLOR, COLUMN_FEATURE_DESCRIPTION_COLOR, COLUMN_ACTION_EXTRA, COLUMN_ACTION_EXTRA_VALUE};
    }
}
