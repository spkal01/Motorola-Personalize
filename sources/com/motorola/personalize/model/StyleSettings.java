package com.motorola.personalize.model;

import com.motorola.styles.model.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class StyleSettings {
    private static final List<String> SETTINGS_SEQUENCE;
    public static final String SETTING_COLOR = "color";
    public static final String SETTING_FONT = "font";
    public static final String SETTING_GRID = "grid";
    public static final String SETTING_NAME = "name";
    public static final String SETTING_SHAPE = "shape";
    public static final String SETTING_THEME = "theme";
    private String mCurrentSetting = "font";
    private final Map<String, List<? extends Option>> mOptionsMap = new HashMap();
    private final Style mStyle;

    static {
        ArrayList arrayList = new ArrayList();
        SETTINGS_SEQUENCE = arrayList;
        arrayList.add("font");
        arrayList.add("color");
        arrayList.add("shape");
        arrayList.add("grid");
        arrayList.add("name");
        arrayList.add("theme");
    }

    public static boolean isFont(String str) {
        return "font".equals(str);
    }

    public static boolean isColor(String str) {
        return "color".equals(str);
    }

    public static boolean isShape(String str) {
        return "shape".equals(str);
    }

    public static boolean isGrid(String str) {
        return "grid".equals(str);
    }

    public static boolean isName(String str) {
        return "name".equals(str);
    }

    public static boolean isTheme(String str) {
        return "theme".equals(str);
    }

    public StyleSettings(Style style) {
        this.mStyle = new Style(style);
    }

    public Style getStyle() {
        return this.mStyle;
    }

    public void setCurrentSetting(String str) {
        if (str != null) {
            this.mCurrentSetting = str;
        }
    }

    public String getCurrentSetting() {
        return this.mCurrentSetting;
    }

    public List<? extends Option> setOptions(String str, List<? extends Option> list) {
        if (SETTINGS_SEQUENCE.indexOf(str) == -1) {
            return null;
        }
        return this.mOptionsMap.put(str, list);
    }

    public List<? extends Option> getCurrentSettingOptions() {
        return this.mOptionsMap.getOrDefault(getCurrentSetting(), new ArrayList());
    }

    public String getCurrentSettingValue() {
        String currentSetting = getCurrentSetting();
        if (isFont(currentSetting)) {
            return this.mStyle.getFont();
        }
        if (isColor(currentSetting)) {
            return this.mStyle.getColor();
        }
        if (isShape(currentSetting)) {
            return this.mStyle.getShape();
        }
        if (isGrid(currentSetting)) {
            return this.mStyle.getGrid();
        }
        return isName(currentSetting) ? this.mStyle.getName() : "";
    }

    public void setName(String str) {
        this.mStyle.setName(str);
    }

    public void setFont(String str) {
        this.mStyle.setFont(str);
    }

    public void setColor(String str) {
        this.mStyle.setColor(str);
    }

    public void setShape(String str) {
        this.mStyle.setShape(str);
    }

    public void setGrid(String str) {
        this.mStyle.setGrid(str);
    }

    public static int indexOfSettings(List<? extends Option> list, Predicate<Option> predicate) {
        Optional<Option> findFirst = list.stream().filter(predicate).findFirst();
        Objects.requireNonNull(list);
        return ((Integer) findFirst.map(new Function(list) {
            public final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final Object apply(Object obj) {
                return Integer.valueOf(this.f$0.indexOf((Option) obj));
            }
        }).orElse(-1)).intValue();
    }
}
