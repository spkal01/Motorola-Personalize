package com.android.wallpaper;

import android.content.Context;
import com.motorola.styles.model.RingtoneOption;
import java.util.ArrayList;
import java.util.List;

public class ThemeExt {
    public static List<Integer> GRID_ICONS = new ArrayList<Integer>() {
        {
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_business_center));
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_drive_file_move));
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_camera));
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_cloud_done));
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_attach_file));
        }
    };
    public static List<Integer> RINGTONE_ICONS = new ArrayList<Integer>() {
        {
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_no_ringtone));
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_ringtone));
        }
    };
    public static List<Integer> SHAPE_ICONS = new ArrayList<Integer>() {
        {
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_alarm_clock));
            add(Integer.valueOf(C0744R.C0746drawable.themes_ic_person));
        }
    };

    public static int getRingtoneIcon(RingtoneOption ringtoneOption) {
        Integer num;
        if (ringtoneOption.isNoRingtone()) {
            num = RINGTONE_ICONS.get(0);
        } else {
            num = RINGTONE_ICONS.get(1);
        }
        return num.intValue();
    }

    public static String getRingtoneName(Context context, RingtoneOption ringtoneOption) {
        if (ringtoneOption.isNoRingtone()) {
            return context.getString(C0744R.string.themes_no_ringtone);
        }
        return ringtoneOption.getName();
    }
}
