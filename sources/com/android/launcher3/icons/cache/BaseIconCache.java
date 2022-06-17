package com.android.launcher3.icons.cache;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.CustomAppIcons;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.SQLiteCacheHelper;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public abstract class BaseIconCache {
    private static final boolean DEBUG = false;
    public static final String EMPTY_CLASS_NAME = ".";
    private static final int INITIAL_ICON_CACHE_CAPACITY = 50;
    private static final String TAG = "BaseIconCache";
    private final Looper mBgLooper;
    private final Map<ComponentKey, CacheEntry> mCache;
    protected final Context mContext;
    private final String mDbFileName;
    private final HashMap<UserHandle, BitmapInfo> mDefaultIcons = new HashMap<>();
    protected IconDB mIconDb;
    protected int mIconDpi;
    private final int mIconPixelSize;
    protected LocaleList mLocaleList = LocaleList.getEmptyLocaleList();
    protected final PackageManager mPackageManager;
    protected String mSystemState = "";
    protected final Handler mWorkerHandler;

    public static class CacheEntry {
        public BitmapInfo bitmap = BitmapInfo.LOW_RES_INFO;
        public CharSequence contentDescription = "";
        public CharSequence title = "";
    }

    public abstract BaseIconFactory getIconFactory();

    /* access modifiers changed from: protected */
    public abstract long getSerialNumberForUser(UserHandle userHandle);

    /* access modifiers changed from: protected */
    public abstract boolean isInstantApp(ApplicationInfo applicationInfo);

    public BaseIconCache(Context context, String str, Looper looper, int i, int i2, boolean z) {
        this.mContext = context;
        this.mDbFileName = str;
        this.mPackageManager = context.getPackageManager();
        this.mBgLooper = looper;
        this.mWorkerHandler = new Handler(looper);
        if (z) {
            this.mCache = new HashMap(50);
        } else {
            this.mCache = new AbstractMap<ComponentKey, CacheEntry>() {
                public CacheEntry put(ComponentKey componentKey, CacheEntry cacheEntry) {
                    return cacheEntry;
                }

                public Set<Map.Entry<ComponentKey, CacheEntry>> entrySet() {
                    return Collections.emptySet();
                }
            };
        }
        updateSystemState();
        this.mIconDpi = i;
        this.mIconPixelSize = i2;
        this.mIconDb = new IconDB(context, str, i2);
    }

    public void updateIconParams(int i, int i2) {
        this.mWorkerHandler.post(new Runnable(i, i2) {
            public final /* synthetic */ int f$1;
            public final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                BaseIconCache.this.lambda$updateIconParams$0$BaseIconCache(this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: updateIconParamsBg */
    public synchronized void lambda$updateIconParams$0$BaseIconCache(int i, int i2) {
        this.mIconDpi = i;
        this.mDefaultIcons.clear();
        this.mIconDb.clear();
        this.mIconDb.close();
        this.mIconDb = new IconDB(this.mContext, this.mDbFileName, i2);
        this.mCache.clear();
    }

    private Drawable getFullResIcon(Resources resources, int i) {
        if (!(resources == null || i == 0)) {
            try {
                return resources.getDrawableForDensity(i, this.mIconDpi);
            } catch (Resources.NotFoundException unused) {
            }
        }
        return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
    }

    public Drawable getFullResIcon(String str, int i) {
        try {
            return getFullResIcon(this.mPackageManager.getResourcesForApplication(str), i);
        } catch (PackageManager.NameNotFoundException unused) {
            return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
        }
    }

    public Drawable getFullResIcon(ActivityInfo activityInfo) {
        try {
            return getFullResIcon(this.mPackageManager.getResourcesForApplication(activityInfo.applicationInfo), activityInfo.getIconResource());
        } catch (PackageManager.NameNotFoundException unused) {
            return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
        }
    }

    private BitmapInfo makeDefaultIcon(UserHandle userHandle) {
        BaseIconFactory iconFactory = getIconFactory();
        try {
            BitmapInfo makeDefaultIcon = iconFactory.makeDefaultIcon(userHandle);
            if (iconFactory != null) {
                iconFactory.close();
            }
            return makeDefaultIcon;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public synchronized void remove(ComponentName componentName, UserHandle userHandle) {
        this.mCache.remove(new ComponentKey(componentName, userHandle));
    }

    private void removeFromMemCacheLocked(String str, UserHandle userHandle) {
        HashSet hashSet = new HashSet();
        for (ComponentKey next : this.mCache.keySet()) {
            if (next.componentName.getPackageName().equals(str) && next.user.equals(userHandle)) {
                hashSet.add(next);
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            this.mCache.remove((ComponentKey) it.next());
        }
    }

    public synchronized void removeIconsForPkg(String str, UserHandle userHandle) {
        removeFromMemCacheLocked(str, userHandle);
        long serialNumberForUser = getSerialNumberForUser(userHandle);
        this.mIconDb.delete("componentName LIKE ? AND profileId = ?", new String[]{str + "/%", Long.toString(serialNumberForUser)});
    }

    public IconCacheUpdateHandler getUpdateHandler() {
        updateSystemState();
        return new IconCacheUpdateHandler(this);
    }

    private void updateSystemState() {
        this.mLocaleList = this.mContext.getResources().getConfiguration().getLocales();
        this.mSystemState = this.mLocaleList.toLanguageTags() + "," + Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: protected */
    public String getIconSystemState(String str) {
        return this.mSystemState;
    }

    public synchronized <T> void addIconToDBAndMemCache(T t, CachingLogic<T> cachingLogic, PackageInfo packageInfo, long j, boolean z) {
        CacheEntry cacheEntry;
        T t2 = t;
        CachingLogic<T> cachingLogic2 = cachingLogic;
        synchronized (this) {
            UserHandle user = cachingLogic.getUser(t);
            ComponentName component = cachingLogic.getComponent(t);
            ComponentKey componentKey = new ComponentKey(component, user);
            CacheEntry cacheEntry2 = null;
            if (!z && (cacheEntry = this.mCache.get(componentKey)) != null) {
                if (!cacheEntry.bitmap.isNullOrLowRes()) {
                    cacheEntry2 = cacheEntry;
                }
            }
            if (cacheEntry2 == null) {
                CacheEntry cacheEntry3 = new CacheEntry();
                BitmapInfo loadIcon = CustomAppIcons.getInstance().loadIcon(this.mContext, component, user, this.mIconPixelSize, cachingLogic);
                if (loadIcon == null) {
                    loadIcon = cachingLogic.loadIcon(this.mContext, t);
                }
                cacheEntry3.bitmap = loadIcon;
                cacheEntry2 = cacheEntry3;
            }
            if (!cacheEntry2.bitmap.isNullOrLowRes()) {
                cacheEntry2.title = cachingLogic.getLabel(t);
                cacheEntry2.contentDescription = this.mPackageManager.getUserBadgedLabel(cacheEntry2.title, user);
                if (cachingLogic.addToMemCache()) {
                    this.mCache.put(componentKey, cacheEntry2);
                }
                addIconToDB(newContentValues(cacheEntry2.bitmap, cacheEntry2.title.toString(), component.getPackageName(), cachingLogic.getKeywords(t, this.mLocaleList)), component, packageInfo, j, cachingLogic.getLastUpdatedTime(t, packageInfo));
            }
        }
    }

    private void addIconToDB(ContentValues contentValues, ComponentName componentName, PackageInfo packageInfo, long j, long j2) {
        contentValues.put(IconDB.COLUMN_COMPONENT, componentName.flattenToString());
        contentValues.put(IconDB.COLUMN_USER, Long.valueOf(j));
        contentValues.put(IconDB.COLUMN_LAST_UPDATED, Long.valueOf(j2));
        contentValues.put("version", Integer.valueOf(packageInfo.versionCode));
        this.mIconDb.insertOrReplace(contentValues);
    }

    public synchronized BitmapInfo getDefaultIcon(UserHandle userHandle) {
        if (!this.mDefaultIcons.containsKey(userHandle)) {
            this.mDefaultIcons.put(userHandle, makeDefaultIcon(userHandle));
        }
        return this.mDefaultIcons.get(userHandle);
    }

    public boolean isDefaultIcon(BitmapInfo bitmapInfo, UserHandle userHandle) {
        return getDefaultIcon(userHandle).icon == bitmapInfo.icon;
    }

    /* access modifiers changed from: protected */
    public <T> CacheEntry cacheLocked(ComponentName componentName, UserHandle userHandle, Supplier<T> supplier, CachingLogic<T> cachingLogic, boolean z, boolean z2) {
        CacheEntry entryForPackageLocked;
        assertWorkerThread();
        ComponentKey componentKey = new ComponentKey(componentName, userHandle);
        CacheEntry cacheEntry = this.mCache.get(componentKey);
        if (cacheEntry == null || (cacheEntry.bitmap.isLowRes() && !z2)) {
            cacheEntry = new CacheEntry();
            if (cachingLogic.addToMemCache()) {
                this.mCache.put(componentKey, cacheEntry);
            }
            T t = null;
            boolean entryFromDB = getEntryFromDB(componentKey, cacheEntry, z2);
            boolean z3 = false;
            if (!entryFromDB) {
                t = supplier.get();
                if (t != null) {
                    BitmapInfo loadIcon = CustomAppIcons.getInstance().loadIcon(this.mContext, componentName, userHandle, this.mIconPixelSize, cachingLogic);
                    if (loadIcon == null) {
                        loadIcon = cachingLogic.loadIcon(this.mContext, t);
                    }
                    cacheEntry.bitmap = loadIcon;
                } else {
                    if (z && (entryForPackageLocked = getEntryForPackageLocked(componentName.getPackageName(), userHandle, false)) != null) {
                        cacheEntry.bitmap = entryForPackageLocked.bitmap;
                        cacheEntry.title = entryForPackageLocked.title;
                        cacheEntry.contentDescription = entryForPackageLocked.contentDescription;
                    }
                    if (cacheEntry.bitmap == null) {
                        cacheEntry.bitmap = getDefaultIcon(userHandle);
                    }
                }
                z3 = true;
            }
            if (TextUtils.isEmpty(cacheEntry.title)) {
                if (t == null && !z3) {
                    t = supplier.get();
                }
                if (t != null) {
                    cacheEntry.title = cachingLogic.getLabel(t);
                    cacheEntry.contentDescription = this.mPackageManager.getUserBadgedLabel(cachingLogic.getDescription(t, cacheEntry.title), userHandle);
                }
            }
        }
        return cacheEntry;
    }

    public synchronized void clear() {
        assertWorkerThread();
        this.mIconDb.clear();
    }

    /* access modifiers changed from: protected */
    public synchronized void cachePackageInstallInfo(String str, UserHandle userHandle, Bitmap bitmap, CharSequence charSequence) {
        removeFromMemCacheLocked(str, userHandle);
        ComponentKey packageKey = getPackageKey(str, userHandle);
        CacheEntry cacheEntry = this.mCache.get(packageKey);
        if (cacheEntry == null) {
            cacheEntry = new CacheEntry();
        }
        if (!TextUtils.isEmpty(charSequence)) {
            cacheEntry.title = charSequence;
        }
        if (bitmap != null) {
            BaseIconFactory iconFactory = getIconFactory();
            cacheEntry.bitmap = iconFactory.createShapedIconBitmap(bitmap, userHandle);
            iconFactory.close();
        }
        if (!TextUtils.isEmpty(charSequence) && cacheEntry.bitmap.icon != null) {
            this.mCache.put(packageKey, cacheEntry);
        }
    }

    private static ComponentKey getPackageKey(String str, UserHandle userHandle) {
        return new ComponentKey(new ComponentName(str, str + "."), userHandle);
    }

    /* access modifiers changed from: protected */
    public CacheEntry getEntryForPackageLocked(String str, UserHandle userHandle, boolean z) {
        String str2 = str;
        UserHandle userHandle2 = userHandle;
        boolean z2 = z;
        assertWorkerThread();
        ComponentKey packageKey = getPackageKey(str, userHandle);
        CacheEntry cacheEntry = this.mCache.get(packageKey);
        if (cacheEntry != null && (!cacheEntry.bitmap.isLowRes() || z2)) {
            return cacheEntry;
        }
        CacheEntry cacheEntry2 = new CacheEntry();
        boolean z3 = true;
        if (!getEntryFromDB(packageKey, cacheEntry2, z2)) {
            try {
                PackageInfo packageInfo = this.mPackageManager.getPackageInfo(str2, Process.myUserHandle().equals(userHandle2) ? 0 : 8192);
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if (applicationInfo != null) {
                    BaseIconFactory iconFactory = getIconFactory();
                    Drawable loadIcon = CustomAppIcons.getInstance().loadIcon(this.mContext, applicationInfo);
                    if (loadIcon == null) {
                        loadIcon = applicationInfo.loadIcon(this.mPackageManager);
                    }
                    BitmapInfo createBadgedIconBitmap = iconFactory.createBadgedIconBitmap(loadIcon, userHandle2, applicationInfo.targetSdkVersion, isInstantApp(applicationInfo));
                    iconFactory.close();
                    cacheEntry2.title = applicationInfo.loadLabel(this.mPackageManager);
                    cacheEntry2.contentDescription = this.mPackageManager.getUserBadgedLabel(cacheEntry2.title, userHandle2);
                    cacheEntry2.bitmap = BitmapInfo.m25of(z2 ? BitmapInfo.LOW_RES_ICON : createBadgedIconBitmap.icon, createBadgedIconBitmap.color);
                    addIconToDB(newContentValues(createBadgedIconBitmap, cacheEntry2.title.toString(), str2, (String) null), packageKey.componentName, packageInfo, getSerialNumberForUser(userHandle2), packageInfo.lastUpdateTime);
                } else {
                    throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
                }
            } catch (PackageManager.NameNotFoundException unused) {
                z3 = false;
            }
        }
        if (z3) {
            this.mCache.put(packageKey, cacheEntry2);
        }
        return cacheEntry2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0084, code lost:
        if (r2 != null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0091, code lost:
        if (r2 == null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0093, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0096, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getEntryFromDB(com.android.launcher3.util.ComponentKey r11, com.android.launcher3.icons.cache.BaseIconCache.CacheEntry r12, boolean r13) {
        /*
            r10 = this;
            java.lang.String r0 = ""
            r1 = 0
            r2 = 0
            com.android.launcher3.icons.cache.BaseIconCache$IconDB r3 = r10.mIconDb     // Catch:{ SQLiteException -> 0x0089 }
            if (r13 == 0) goto L_0x000b
            java.lang.String[] r4 = com.android.launcher3.icons.cache.BaseIconCache.IconDB.COLUMNS_LOW_RES     // Catch:{ SQLiteException -> 0x0089 }
            goto L_0x000d
        L_0x000b:
            java.lang.String[] r4 = com.android.launcher3.icons.cache.BaseIconCache.IconDB.COLUMNS_HIGH_RES     // Catch:{ SQLiteException -> 0x0089 }
        L_0x000d:
            java.lang.String r5 = "componentName = ? AND profileId = ?"
            r6 = 2
            java.lang.String[] r7 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0089 }
            android.content.ComponentName r8 = r11.componentName     // Catch:{ SQLiteException -> 0x0089 }
            java.lang.String r8 = r8.flattenToString()     // Catch:{ SQLiteException -> 0x0089 }
            r7[r1] = r8     // Catch:{ SQLiteException -> 0x0089 }
            android.os.UserHandle r8 = r11.user     // Catch:{ SQLiteException -> 0x0089 }
            long r8 = r10.getSerialNumberForUser(r8)     // Catch:{ SQLiteException -> 0x0089 }
            java.lang.String r8 = java.lang.Long.toString(r8)     // Catch:{ SQLiteException -> 0x0089 }
            r9 = 1
            r7[r9] = r8     // Catch:{ SQLiteException -> 0x0089 }
            android.database.Cursor r2 = r3.query(r4, r5, r7)     // Catch:{ SQLiteException -> 0x0089 }
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0089 }
            if (r3 == 0) goto L_0x0084
            android.graphics.Bitmap r3 = com.android.launcher3.icons.BitmapInfo.LOW_RES_ICON     // Catch:{ SQLiteException -> 0x0089 }
            int r4 = r2.getInt(r1)     // Catch:{ SQLiteException -> 0x0089 }
            r5 = 255(0xff, float:3.57E-43)
            int r4 = com.android.launcher3.icons.GraphicsUtils.setColorAlphaBound(r4, r5)     // Catch:{ SQLiteException -> 0x0089 }
            com.android.launcher3.icons.BitmapInfo r3 = com.android.launcher3.icons.BitmapInfo.m25of(r3, r4)     // Catch:{ SQLiteException -> 0x0089 }
            r12.bitmap = r3     // Catch:{ SQLiteException -> 0x0089 }
            java.lang.String r3 = r2.getString(r9)     // Catch:{ SQLiteException -> 0x0089 }
            r12.title = r3     // Catch:{ SQLiteException -> 0x0089 }
            java.lang.CharSequence r3 = r12.title     // Catch:{ SQLiteException -> 0x0089 }
            if (r3 != 0) goto L_0x0052
            r12.title = r0     // Catch:{ SQLiteException -> 0x0089 }
            r12.contentDescription = r0     // Catch:{ SQLiteException -> 0x0089 }
            goto L_0x005e
        L_0x0052:
            android.content.pm.PackageManager r0 = r10.mPackageManager     // Catch:{ SQLiteException -> 0x0089 }
            java.lang.CharSequence r3 = r12.title     // Catch:{ SQLiteException -> 0x0089 }
            android.os.UserHandle r4 = r11.user     // Catch:{ SQLiteException -> 0x0089 }
            java.lang.CharSequence r0 = r0.getUserBadgedLabel(r3, r4)     // Catch:{ SQLiteException -> 0x0089 }
            r12.contentDescription = r0     // Catch:{ SQLiteException -> 0x0089 }
        L_0x005e:
            if (r13 != 0) goto L_0x0079
            byte[] r13 = r2.getBlob(r6)     // Catch:{ Exception -> 0x0073 }
            com.android.launcher3.icons.BitmapInfo r0 = r12.bitmap     // Catch:{ Exception -> 0x0073 }
            int r0 = r0.color     // Catch:{ Exception -> 0x0073 }
            android.os.UserHandle r11 = r11.user     // Catch:{ Exception -> 0x0073 }
            android.content.Context r3 = r10.mContext     // Catch:{ Exception -> 0x0073 }
            com.android.launcher3.icons.BitmapInfo r10 = com.android.launcher3.icons.BitmapInfo.fromByteArray(r13, r0, r11, r10, r3)     // Catch:{ Exception -> 0x0073 }
            r12.bitmap = r10     // Catch:{ Exception -> 0x0073 }
            goto L_0x0079
        L_0x0073:
            if (r2 == 0) goto L_0x0078
            r2.close()
        L_0x0078:
            return r1
        L_0x0079:
            com.android.launcher3.icons.BitmapInfo r10 = r12.bitmap     // Catch:{ SQLiteException -> 0x0089 }
            if (r10 == 0) goto L_0x007e
            r1 = r9
        L_0x007e:
            if (r2 == 0) goto L_0x0083
            r2.close()
        L_0x0083:
            return r1
        L_0x0084:
            if (r2 == 0) goto L_0x0096
            goto L_0x0093
        L_0x0087:
            r10 = move-exception
            goto L_0x0097
        L_0x0089:
            r10 = move-exception
            java.lang.String r11 = "BaseIconCache"
            java.lang.String r12 = "Error reading icon cache"
            android.util.Log.d(r11, r12, r10)     // Catch:{ all -> 0x0087 }
            if (r2 == 0) goto L_0x0096
        L_0x0093:
            r2.close()
        L_0x0096:
            return r1
        L_0x0097:
            if (r2 == 0) goto L_0x009c
            r2.close()
        L_0x009c:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.cache.BaseIconCache.getEntryFromDB(com.android.launcher3.util.ComponentKey, com.android.launcher3.icons.cache.BaseIconCache$CacheEntry, boolean):boolean");
    }

    public synchronized Cursor queryCacheDb(String[] strArr, String str, String[] strArr2) {
        return this.mIconDb.query(strArr, str, strArr2);
    }

    public synchronized void clearCache() {
        Map<ComponentKey, CacheEntry> map = this.mCache;
        if (map != null) {
            map.clear();
        }
    }

    public static final class IconDB extends SQLiteCacheHelper {
        public static final String[] COLUMNS_HIGH_RES = {COLUMN_ICON_COLOR, COLUMN_LABEL, COLUMN_ICON};
        public static final String[] COLUMNS_LOW_RES = {COLUMN_ICON_COLOR, COLUMN_LABEL};
        public static final String COLUMN_COMPONENT = "componentName";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_ICON_COLOR = "icon_color";
        public static final String COLUMN_KEYWORDS = "keywords";
        public static final String COLUMN_LABEL = "label";
        public static final String COLUMN_LAST_UPDATED = "lastUpdated";
        public static final String COLUMN_ROWID = "rowid";
        public static final String COLUMN_SYSTEM_STATE = "system_state";
        public static final String COLUMN_USER = "profileId";
        public static final String COLUMN_VERSION = "version";
        private static final int RELEASE_VERSION = 31;
        public static final String TABLE_NAME = "icons";

        public IconDB(Context context, String str, int i) {
            super(context, str, i + 2031616, TABLE_NAME);
        }

        /* access modifiers changed from: protected */
        public void onCreateTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_color INTEGER NOT NULL DEFAULT 0, label TEXT, system_state TEXT, keywords TEXT, PRIMARY KEY (componentName, profileId) );");
        }
    }

    private ContentValues newContentValues(BitmapInfo bitmapInfo, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IconDB.COLUMN_ICON, bitmapInfo.toByteArray());
        contentValues.put(IconDB.COLUMN_ICON_COLOR, Integer.valueOf(bitmapInfo.color));
        contentValues.put(IconDB.COLUMN_LABEL, str);
        contentValues.put(IconDB.COLUMN_SYSTEM_STATE, getIconSystemState(str2));
        contentValues.put(IconDB.COLUMN_KEYWORDS, str3);
        return contentValues;
    }

    private void assertWorkerThread() {
        if (Looper.myLooper() != this.mBgLooper) {
            throw new IllegalStateException("Cache accessed on wrong thread " + Looper.myLooper());
        }
    }
}
