package com.motorola.personalize;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.motorola.personalize.databinding.ActivityLayoutMainBindingImpl;
import com.motorola.personalize.databinding.FeatureTopBarBindingImpl;
import com.motorola.personalize.databinding.FragmentFeatureBindingImpl;
import com.motorola.personalize.databinding.FragmentSoundBindingImpl;
import com.motorola.personalize.databinding.FragmentThemeBindingImpl;
import com.motorola.personalize.databinding.LayoutFeatureBindingImpl;
import com.motorola.personalize.databinding.LayoutFeatureRowBindingImpl;
import com.motorola.personalize.databinding.LayoutFeatureTileBindingImpl;
import com.motorola.personalize.databinding.LayoutSoundsTileBindingImpl;
import com.motorola.personalize.databinding.ToolbarFamilySectionBindingImpl;
import com.motorola.personalize.databinding.ToolbarFamilySectionBindingLandImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYLAYOUTMAIN = 1;
    private static final int LAYOUT_FEATURETOPBAR = 2;
    private static final int LAYOUT_FRAGMENTFEATURE = 3;
    private static final int LAYOUT_FRAGMENTSOUND = 4;
    private static final int LAYOUT_FRAGMENTTHEME = 5;
    private static final int LAYOUT_LAYOUTFEATURE = 6;
    private static final int LAYOUT_LAYOUTFEATUREROW = 7;
    private static final int LAYOUT_LAYOUTFEATURETILE = 8;
    private static final int LAYOUT_LAYOUTSOUNDSTILE = 9;
    private static final int LAYOUT_TOOLBARFAMILYSECTION = 10;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(10);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(C1057R.layout.activity_layout_main, 1);
        sparseIntArray.put(C1057R.layout.feature_top_bar, 2);
        sparseIntArray.put(C1057R.layout.fragment_feature, 3);
        sparseIntArray.put(C1057R.layout.fragment_sound, 4);
        sparseIntArray.put(C1057R.layout.fragment_theme, 5);
        sparseIntArray.put(C1057R.layout.layout_feature, 6);
        sparseIntArray.put(C1057R.layout.layout_feature_row, 7);
        sparseIntArray.put(C1057R.layout.layout_feature_tile, 8);
        sparseIntArray.put(C1057R.layout.layout_sounds_tile, 9);
        sparseIntArray.put(C1057R.layout.toolbar_family_section, 10);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/activity_layout_main_0".equals(tag)) {
                        return new ActivityLayoutMainBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_layout_main is invalid. Received: " + tag);
                case 2:
                    if ("layout/feature_top_bar_0".equals(tag)) {
                        return new FeatureTopBarBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for feature_top_bar is invalid. Received: " + tag);
                case 3:
                    if ("layout/fragment_feature_0".equals(tag)) {
                        return new FragmentFeatureBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_feature is invalid. Received: " + tag);
                case 4:
                    if ("layout/fragment_sound_0".equals(tag)) {
                        return new FragmentSoundBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_sound is invalid. Received: " + tag);
                case 5:
                    if ("layout/fragment_theme_0".equals(tag)) {
                        return new FragmentThemeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_theme is invalid. Received: " + tag);
                case 6:
                    if ("layout/layout_feature_0".equals(tag)) {
                        return new LayoutFeatureBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_feature is invalid. Received: " + tag);
                case 7:
                    if ("layout/layout_feature_row_0".equals(tag)) {
                        return new LayoutFeatureRowBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_feature_row is invalid. Received: " + tag);
                case 8:
                    if ("layout/layout_feature_tile_0".equals(tag)) {
                        return new LayoutFeatureTileBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_feature_tile is invalid. Received: " + tag);
                case 9:
                    if ("layout/layout_sounds_tile_0".equals(tag)) {
                        return new LayoutSoundsTileBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for layout_sounds_tile is invalid. Received: " + tag);
                case 10:
                    if ("layout/toolbar_family_section_0".equals(tag)) {
                        return new ToolbarFamilySectionBindingImpl(dataBindingComponent, view);
                    }
                    if ("layout-land/toolbar_family_section_0".equals(tag)) {
                        return new ToolbarFamilySectionBindingLandImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for toolbar_family_section is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(3);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "familyData");
            sparseArray.put(2, "featureItem");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(11);
            sKeys = hashMap;
            hashMap.put("layout/activity_layout_main_0", Integer.valueOf(C1057R.layout.activity_layout_main));
            hashMap.put("layout/feature_top_bar_0", Integer.valueOf(C1057R.layout.feature_top_bar));
            hashMap.put("layout/fragment_feature_0", Integer.valueOf(C1057R.layout.fragment_feature));
            hashMap.put("layout/fragment_sound_0", Integer.valueOf(C1057R.layout.fragment_sound));
            hashMap.put("layout/fragment_theme_0", Integer.valueOf(C1057R.layout.fragment_theme));
            hashMap.put("layout/layout_feature_0", Integer.valueOf(C1057R.layout.layout_feature));
            hashMap.put("layout/layout_feature_row_0", Integer.valueOf(C1057R.layout.layout_feature_row));
            hashMap.put("layout/layout_feature_tile_0", Integer.valueOf(C1057R.layout.layout_feature_tile));
            hashMap.put("layout/layout_sounds_tile_0", Integer.valueOf(C1057R.layout.layout_sounds_tile));
            Integer valueOf = Integer.valueOf(C1057R.layout.toolbar_family_section);
            hashMap.put("layout/toolbar_family_section_0", valueOf);
            hashMap.put("layout-land/toolbar_family_section_0", valueOf);
        }
    }
}
