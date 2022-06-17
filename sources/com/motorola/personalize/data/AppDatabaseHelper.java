package com.motorola.personalize.data;

import android.content.Context;
import androidx.room.Room;

public class AppDatabaseHelper {

    /* renamed from: db */
    private static AppDatabase f162db;

    public static void init(Context context) {
        if (f162db == null) {
            f162db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "personalize.db").allowMainThreadQueries().build();
        }
    }

    public static AppDatabase getInstance() {
        return f162db;
    }
}
