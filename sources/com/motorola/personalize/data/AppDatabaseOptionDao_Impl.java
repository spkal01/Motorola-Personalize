package com.motorola.personalize.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.p004db.SupportSQLiteStatement;
import com.motorola.personalize.data.AppDatabase;
import com.motorola.personalize.model.OptionEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class AppDatabaseOptionDao_Impl implements AppDatabase.OptionDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<OptionEntity> __deletionAdapterOfOptionEntity;
    private final EntityInsertionAdapter<OptionEntity> __insertionAdapterOfOptionEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteColor;
    private final EntityDeletionOrUpdateAdapter<OptionEntity> __updateAdapterOfOptionEntity;

    public AppDatabaseOptionDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfOptionEntity = new EntityInsertionAdapter<OptionEntity>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `options` (`color`,`time`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, OptionEntity optionEntity) {
                supportSQLiteStatement.bindLong(1, (long) optionEntity.getLightColor());
                supportSQLiteStatement.bindLong(2, optionEntity.getInsertTime());
            }
        };
        this.__deletionAdapterOfOptionEntity = new EntityDeletionOrUpdateAdapter<OptionEntity>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `options` WHERE `color` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, OptionEntity optionEntity) {
                supportSQLiteStatement.bindLong(1, (long) optionEntity.getLightColor());
            }
        };
        this.__updateAdapterOfOptionEntity = new EntityDeletionOrUpdateAdapter<OptionEntity>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `options` SET `color` = ?,`time` = ? WHERE `color` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, OptionEntity optionEntity) {
                supportSQLiteStatement.bindLong(1, (long) optionEntity.getLightColor());
                supportSQLiteStatement.bindLong(2, optionEntity.getInsertTime());
                supportSQLiteStatement.bindLong(3, (long) optionEntity.getLightColor());
            }
        };
        this.__preparedStmtOfDeleteColor = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM options where color= ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM options";
            }
        };
    }

    public void insertOptions(OptionEntity... optionEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfOptionEntity.insert((T[]) optionEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public long insertOption(OptionEntity optionEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfOptionEntity.insertAndReturnId(optionEntity);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteOptions(OptionEntity... optionEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfOptionEntity.handleMultiple((T[]) optionEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void updateOptions(OptionEntity... optionEntityArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfOptionEntity.handleMultiple((T[]) optionEntityArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteColor(int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteColor.acquire();
        acquire.bindLong(1, (long) i);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteColor.release(acquire);
        }
    }

    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(acquire);
        }
    }

    public LiveData<List<OptionEntity>> getAllOptions() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM options order by time desc", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"options"}, false, new Callable<List<OptionEntity>>() {
            public List<OptionEntity> call() throws Exception {
                Cursor query = DBUtil.query(AppDatabaseOptionDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "color");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "time");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        OptionEntity optionEntity = new OptionEntity();
                        optionEntity.setLightColor(query.getInt(columnIndexOrThrow));
                        optionEntity.setInsertTime(query.getLong(columnIndexOrThrow2));
                        arrayList.add(optionEntity);
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public List<OptionEntity> loadAllOptions() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM options  order by time desc", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "color");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "time");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                OptionEntity optionEntity = new OptionEntity();
                optionEntity.setLightColor(query.getInt(columnIndexOrThrow));
                optionEntity.setInsertTime(query.getLong(columnIndexOrThrow2));
                arrayList.add(optionEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
