package com.motorola.personalize.analysis;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.motorola.android.checkin.provider.CheckinEventWrapper;
import java.util.Hashtable;

public class PersonalizeMetrics {
    public static final boolean DEBUG = true;
    private static final String END = "\"";
    private static final String END_0 = "}";
    private static final String EVENT_DAILY = "DailyStats";
    private static final String GROUP_NAME = "MOT_PERSONALIZE";
    protected static final String KEY_ADD_ALARM = "ada";
    protected static final String KEY_ADD_ALARM_SUCCESS = "saa";
    protected static final String KEY_ADD_NTF = "adn";
    protected static final String KEY_ADD_NTF_SUCCESS = "san";
    protected static final String KEY_ADD_RINGTONE = "adr";
    protected static final String KEY_ADD_RINGTONE_SUCCESS = "sar";
    protected static final String KEY_APK_VERSION = "APKVER";
    protected static final String KEY_COLOR_TYPE = "pct";
    protected static final String KEY_CREATE_THEME = "cres";
    protected static final String KEY_DELETE_COLOR = "delc";
    protected static final String KEY_DEL_THEME = "dels";
    protected static final String KEY_EDIT_THEME = "edits";
    public static final String KEY_EYE = "eye";
    public static final String KEY_FONT = "font";
    public static final String KEY_GRID = "grid";
    protected static final String KEY_MOST_SOUND_ALARM = "msa";
    protected static final String KEY_MOST_SOUND_NTF = "msn";
    protected static final String KEY_MOST_SOUND_RINGTONE = "msr";
    protected static final String KEY_PICKER = "cp";
    protected static final String KEY_PLAY = "play";
    protected static final String KEY_SHAPE = "shape";
    protected static final String KEY_SOUND_PICK = "sp";
    protected static final String KEY_TOPIC = "topic";
    protected static final String KEY_UI_MODE = "theme";
    private static final String MIDDLE = "\":\"";
    private static final String START = ",\"";
    private static final String START_0 = "{\"";
    public static final String TAG = "PersonalizeMetrics";
    public static final String TOPIC_COLOR = "c";
    public static final String TOPIC_FONT = "f";
    public static final String TOPIC_GRID = "g";
    public static final String TOPIC_RINGTONE = "r";
    public static final String TOPIC_SHAPE = "i";
    public static final String TOPIC_WALLPAPER = "w";
    private static final String TRUE = "1";
    public static final int TYPE_ALARM = 4;
    public static final int TYPE_NOTIFICATION = 2;
    private static final String VERSION = "1.0";
    private static final String VERSION_1_0 = "1.0";
    private static final String[] iconShapes = {"Default", "com.android.theme.icon.teardrop", "com.android.theme.icon.roundedrect", "com.android.theme.icon.pebble", "com.android.theme.icon.round", "com.android.theme.icon.vessel", "com.android.theme.icon.taperedrect", "com.android.theme.icon.squircle"};
    private static final String[] keys = {KEY_EYE, KEY_PICKER, KEY_DELETE_COLOR, KEY_SOUND_PICK, KEY_PLAY, KEY_ADD_RINGTONE, KEY_ADD_NTF, KEY_ADD_ALARM, KEY_ADD_RINGTONE_SUCCESS, KEY_ADD_NTF_SUCCESS, KEY_ADD_ALARM_SUCCESS, KEY_CREATE_THEME, KEY_EDIT_THEME, KEY_DEL_THEME};
    private static volatile SharedPreferences sPref;
    private static final String[] topics = {TOPIC_WALLPAPER, TOPIC_FONT, TOPIC_COLOR, TOPIC_SHAPE, TOPIC_GRID, TOPIC_RINGTONE};

    public static SharedPreferences getAppSharedPreferences(Context context) {
        if (sPref == null) {
            sPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        }
        return sPref;
    }

    private static void buildAndSendEvent(Hashtable<String, String> hashtable, Context context) {
        new Thread(new Runnable(hashtable, context.getApplicationContext().getContentResolver(), context) {
            public final /* synthetic */ Hashtable f$0;
            public final /* synthetic */ ContentResolver f$1;
            public final /* synthetic */ Context f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                PersonalizeMetrics.lambda$buildAndSendEvent$0(this.f$0, this.f$1, this.f$2);
            }
        }).start();
    }

    static /* synthetic */ void lambda$buildAndSendEvent$0(Hashtable hashtable, ContentResolver contentResolver, Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            CheckinEventWrapper checkinEventWrapper = new CheckinEventWrapper(GROUP_NAME, EVENT_DAILY, "1.0", currentTimeMillis);
            for (String str : hashtable.keySet()) {
                checkinEventWrapper.setValue(str, (String) hashtable.get(str));
            }
            checkinEventWrapper.publish(contentResolver);
            String str2 = TAG;
            Log.d(str2, "publish event: tag =MOT_PERSONALIZE, id =DailyStats, version = 1.0, timestamp = " + currentTimeMillis);
            Log.d(str2, "table: " + hashtable.toString());
            clearData(context);
        } catch (Exception e) {
            Log.d(TAG, "check in exception " + e.toString());
        }
    }

    public static void sendEvent(Context context) {
        dLog("sendEvent");
        Hashtable hashtable = new Hashtable();
        SharedPreferences appSharedPreferences = getAppSharedPreferences(context.getApplicationContext());
        String string = appSharedPreferences.getString("grid", (String) null);
        if (string != null) {
            hashtable.put("grid", string);
        }
        String string2 = appSharedPreferences.getString("font", (String) null);
        if (string2 != null) {
            hashtable.put("font", string2);
        }
        int i = appSharedPreferences.getInt("shape", 0);
        if (i >= 0) {
            hashtable.put("shape", Integer.toString(i));
        }
        int i2 = appSharedPreferences.getInt("theme", -1);
        if (i2 >= 0) {
            hashtable.put("theme", Integer.toString(i2));
        }
        int i3 = appSharedPreferences.getInt(KEY_COLOR_TYPE, -1);
        if (i3 >= 0) {
            hashtable.put(KEY_COLOR_TYPE, Integer.toString(i3));
        }
        String string3 = appSharedPreferences.getString(KEY_MOST_SOUND_RINGTONE, (String) null);
        if (string3 != null) {
            hashtable.put(KEY_MOST_SOUND_RINGTONE, string3);
        }
        String string4 = appSharedPreferences.getString(KEY_MOST_SOUND_NTF, (String) null);
        if (string4 != null) {
            hashtable.put(KEY_MOST_SOUND_NTF, string4);
        }
        String string5 = appSharedPreferences.getString(KEY_MOST_SOUND_ALARM, (String) null);
        if (string5 != null) {
            hashtable.put(KEY_MOST_SOUND_ALARM, string5);
        }
        for (String str : keys) {
            if (appSharedPreferences.getInt(str, 0) > 0) {
                hashtable.put(str, "1");
            }
        }
        StringBuilder sb = new StringBuilder();
        int i4 = 0;
        for (String str2 : topics) {
            int i5 = appSharedPreferences.getInt(str2, 0);
            if (i5 > 0) {
                sb.append(i4 == 0 ? START_0 : START);
                i4++;
                sb.append(str2).append(MIDDLE).append(i5).append(END);
            }
        }
        if (i4 != 0) {
            sb.append("}");
        }
        String sb2 = sb.toString();
        Log.d(TAG, "sendEvent: " + sb2);
        if (sb2.length() > 0) {
            hashtable.put(KEY_TOPIC, sb2);
        }
        try {
            hashtable.put(KEY_APK_VERSION, Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "metrics hashmap:\n" + hashtable.toString());
        buildAndSendEvent(hashtable, context.getApplicationContext());
    }

    private static void dLog(String str) {
        Log.d(TAG, str);
    }

    public static void setCurrentGrid(Context context, String str) {
        dLog("setCurrentGrid: " + str);
        String[] split = str.split("_by_");
        String str2 = split[1] + "x" + split[0];
        dLog("setCurrentGrid: " + str2);
        getAppSharedPreferences(context).edit().putString("grid", str2).apply();
    }

    public static void setCurrentGrid(Context context, int i, int i2) {
        String str = i2 + "x" + i;
        dLog("setCurrentGrid: grid = " + str);
        getAppSharedPreferences(context).edit().putString("grid", str).apply();
    }

    public static void useEyeButton(Context context) {
        dLog("useEyeButton: ");
        getAppSharedPreferences(context).edit().putInt(KEY_EYE, 1).apply();
    }

    public static void setCurrentFont(Context context, String str) {
        dLog("setCurrentFont: " + str);
        getAppSharedPreferences(context).edit().putString("font", str).apply();
    }

    public static void setCurrentColor(Context context, int i) {
        dLog("setCurrentColor: " + i);
        getAppSharedPreferences(context).edit().putInt(KEY_COLOR_TYPE, i).apply();
    }

    public static void useColorPicker(Context context) {
        dLog("useColorPicker: ");
        getAppSharedPreferences(context).edit().putInt(KEY_PICKER, 1).apply();
    }

    public static void deleteColor(Context context) {
        dLog("deleteColor: ");
        getAppSharedPreferences(context).edit().putInt(KEY_DELETE_COLOR, 1).apply();
    }

    public static void setCurrentShape(Context context, String str) {
        dLog("setCurrentShape: " + str);
        int i = 0;
        for (String equals : iconShapes) {
            i++;
            if (equals.equals(str)) {
                dLog("setCurrentShape: " + i);
                getAppSharedPreferences(context).edit().putInt("shape", i).apply();
            }
        }
    }

    public static void useSounds(Context context) {
        dLog("useSounds: ");
        getAppSharedPreferences(context).edit().putInt(KEY_SOUND_PICK, 1).apply();
    }

    public static void setCurrentRingtone(Context context, String str) {
        dLog("setCurrentRingtone: " + str);
        if (TextUtils.isEmpty(str)) {
            str = "None";
        }
        getAppSharedPreferences(context).edit().putString(KEY_MOST_SOUND_RINGTONE, str).apply();
    }

    private static void setCurrentNotification(Context context, String str) {
        dLog("setCurrentNotification: " + str);
        getAppSharedPreferences(context).edit().putString(KEY_MOST_SOUND_NTF, str).apply();
    }

    public static void setSoundFromTheme(Context context, Uri uri) {
        setSound(context, 1, uri);
    }

    public static void setSound(Context context, int i, Uri uri) {
        String str;
        useSounds(context);
        if (uri == null) {
            str = "None";
        } else {
            str = uri.getQueryParameter("title");
        }
        if (i == 2) {
            setCurrentNotification(context, str);
        } else if (i == 4) {
            setCurrentAlarm(context, str);
        } else {
            setCurrentRingtone(context, str);
        }
    }

    private static void setCurrentAlarm(Context context, String str) {
        dLog("setCurrentAlarm: " + str);
        getAppSharedPreferences(context).edit().putString(KEY_MOST_SOUND_ALARM, str).apply();
    }

    public static void usePlayButton(Context context) {
        dLog("usePlayButton: ");
        getAppSharedPreferences(context).edit().putInt(KEY_PLAY, 1).apply();
    }

    public static void useAddRingtone(Context context) {
        dLog("useAddRingtone: ");
        getAppSharedPreferences(context).edit().putInt(KEY_ADD_RINGTONE, 1).apply();
    }

    public static void useAddNotification(Context context) {
        dLog("useAddNotification: ");
        getAppSharedPreferences(context).edit().putInt(KEY_ADD_NTF, 1).apply();
    }

    public static void useAddAlarm(Context context) {
        dLog("useAddAlarm: ");
        getAppSharedPreferences(context).edit().putInt(KEY_ADD_ALARM, 1).apply();
    }

    public static void successAddRingtone(Context context) {
        dLog("successAddRingtone: ");
        getAppSharedPreferences(context).edit().putInt(KEY_ADD_RINGTONE_SUCCESS, 1).apply();
    }

    public static void successAddNotification(Context context) {
        dLog("successAddNotification: ");
        getAppSharedPreferences(context).edit().putInt(KEY_ADD_NTF_SUCCESS, 1).apply();
    }

    public static void successAddAlarm(Context context) {
        dLog("successAddAlarm: ");
        getAppSharedPreferences(context).edit().putInt(KEY_ADD_ALARM_SUCCESS, 1).apply();
    }

    public static void setCurrentUiMode(Context context, int i) {
        dLog("setCurrentUiMode: " + i);
        getAppSharedPreferences(context).edit().putInt("theme", i).apply();
    }

    public static void createTheme(Context context) {
        dLog("createTheme: ");
        getAppSharedPreferences(context).edit().putInt(KEY_CREATE_THEME, 1).apply();
    }

    public static void editTheme(Context context) {
        dLog("editTheme: ");
        getAppSharedPreferences(context).edit().putInt(KEY_EDIT_THEME, 1).apply();
    }

    public static void deleteTheme(Context context) {
        dLog("deleteTheme: ");
        getAppSharedPreferences(context).edit().putInt(KEY_DEL_THEME, 1).apply();
    }

    public static void chooseTopic(Context context, String... strArr) {
        SharedPreferences appSharedPreferences = getAppSharedPreferences(context);
        SharedPreferences.Editor edit = appSharedPreferences.edit();
        for (String str : strArr) {
            dLog("chooseTopic: " + str);
            edit.putInt(str, appSharedPreferences.getInt(str, 0) + 1);
        }
        edit.apply();
    }

    public static void clearData(Context context) {
        getAppSharedPreferences(context).edit().putInt(KEY_EYE, 0).putInt(KEY_PICKER, 0).putInt(KEY_DELETE_COLOR, 0).putInt(KEY_SOUND_PICK, 0).putInt(KEY_PLAY, 0).putInt(KEY_ADD_RINGTONE, 0).putInt(KEY_ADD_NTF, 0).putInt(KEY_ADD_ALARM, 0).putInt(KEY_ADD_RINGTONE_SUCCESS, 0).putInt(KEY_ADD_NTF_SUCCESS, 0).putInt(KEY_ADD_ALARM_SUCCESS, 0).putInt(KEY_CREATE_THEME, 0).putInt(KEY_EDIT_THEME, 0).putInt(KEY_DEL_THEME, 0).putInt(TOPIC_WALLPAPER, 0).putInt(TOPIC_FONT, 0).putInt(TOPIC_COLOR, 0).putInt(TOPIC_SHAPE, 0).putInt(TOPIC_GRID, 0).putInt(TOPIC_RINGTONE, 0).apply();
    }
}
