package com.android.launcher3.icons;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.icons.ImageUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class IconPack {
    public static final String APP_ICON = "AppIcon";
    public static final String BACK_HUE_COLOR_BUNDLE = "backHueColorBundle";
    public static final String BACK_IMAGE_NAMES = "backImageNames";
    public static final String CALENDAR = "calendar";
    public static final String CALENDAR_COMPONENT_DRAWABLES = "calendarComponentDrawables";
    public static final String CALENDAR_PACKAGE_DRAWABLES = "calendarPackageDrawables";
    public static final String COMMUTE_SUGGESTION_CLASS_NAME = "com.motorola.plugins.CommuteSuggestionsPlugin";
    public static final String COMMUTE_SUGGESTION_PACKAGE_NAME = "com.motorola.cn.smartservice";
    public static final String COMPONENT = "component";
    public static final String COMPONENT_DRAWABLES = "componentDrawables";
    public static final Pattern COMPONENT_DRAWABLE_NAME_PATTERN = Pattern.compile("[.|/]");
    public static final String COMPONENT_PREFIX = "ComponentInfo{";
    public static final String COMPONENT_SUFFIX = "}";
    public static final IconPack DEFAULT_ICON_PACK;
    public static final String DEFAULT_ICON_PACK_PKG_NAME = "system";
    public static final Pattern DOUBLE_UNDERSCORE_PATTERN = Pattern.compile("__");
    public static final String DRAWABLE = "drawable";
    public static final String DRAWABLE_PREFIX = "@drawable/";
    public static final String FACTOR = "factor";
    public static final String FORCE = "force";
    public static final String FORCE_ICON_BACK = "forceIconBack";
    public static final String FRONT_IMAGE_NAME = "frontImageName";
    public static final String ICON_BACK = "iconback";
    public static final String ICON_MASK = "iconmask";
    public static final String ICON_PACK = "iconPack";
    public static final String ICON_UPON = "iconupon";
    public static final String IMAGE = "image";
    public static final String IMG = "img";
    public static final String IMG_1 = "img1";
    public static final String ITEM = "item";
    public static final String LANDSCAPE = "landscape";
    public static final String MASK_IMAGE_NAME = "maskImageName";
    public static final int MAX_ICON_SIZE = 256;
    public static final int MIN_ICON_SIZE = 128;
    public static final String MY_SCREEN_PACKAGE = "com.motorola.myscreen";
    public static final String NAME = "name";
    public static final String ORIENTATION = "orientation";
    public static final String PACKAGE = "package";
    public static final String PACKAGE_PREFIX = "PackageInfo{";
    public static final String PACKAGE_SUFFIX = "}";
    public static final String PREFIX = "prefix";
    public static final String PREVIEW = "preview";
    public static final String SCALE = "scale";
    public static final String SMART_EXPRESS_CLASS_NAME = "com.motorola.plugins.SmartExpressPlugin";
    public static final String SMART_TRAVEL_CLASS_NAME = "com.motorola.plugins.TripPlugin";
    public static final String SMART_TRAVEL_PACKAGE_NAME = "com.motorola.cn.calendar";
    private static final String TAG = "IconPack";
    public static final String TIME_WEATHER_PACKAGE = "com.motorola.timeweatherwidget";
    public static final Pattern UNDERSCORE_PATTERN = Pattern.compile(CustomAppIcons.UNDERSCORE);
    public static final String VERTICAL = "vertical";
    private static Paint sMaskPaint;
    private static Paint sMaskPaint2;
    private static Paint sPaint = new Paint(7);
    private Point[] mBackEdgePoints;
    private final Object mBackEdgePointsLock;
    private Bundle mBackHueColorBundle;
    private Map<Bitmap, Float> mBackHueColorMap;
    private ArrayList<String> mBackImageNames;
    private List<Bitmap> mBackImages;
    private Map<String, String> mCalendarComponentDrawables;
    private Map<String, String> mCalendarPackageDrawables;
    private Map<String, String> mComponentDrawables;
    private float mFactor;
    private boolean mForceIconBack;
    private Bitmap mFrontImage;
    private String mFrontImageName;
    private Context mIconPackContext;
    private Resources mIconPackRes;
    private boolean mIsDefault;
    private boolean mLoaded;
    private Bitmap mMaskImage;
    private String mMaskImageName;
    private String mName;
    private Map<String, String> mPackageDrawables;
    private String mPackageName;
    private PackageManager mPm;
    private Map<String, String> mPreviewDrawables;
    private Resources mRes;
    private final Bitmap mTestIcon;
    private final Canvas mTestIconCanvas;

    public static int getPaletteColorFromBitmap(Bitmap bitmap) {
        return 0;
    }

    static {
        Paint paint = new Paint(7);
        sMaskPaint = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Paint paint2 = new Paint(7);
        sMaskPaint2 = paint2;
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        IconPack iconPack = new IconPack(DEFAULT_ICON_PACK_PKG_NAME);
        DEFAULT_ICON_PACK = iconPack;
        iconPack.mLoaded = true;
        iconPack.mIsDefault = true;
    }

    private IconPack(Context context, String str) throws PackageManager.NameNotFoundException {
        this(context, str, (String) null);
    }

    private IconPack(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        this.mLoaded = false;
        this.mPreviewDrawables = new HashMap();
        this.mComponentDrawables = new HashMap();
        this.mPackageDrawables = new HashMap();
        this.mCalendarComponentDrawables = new HashMap();
        this.mCalendarPackageDrawables = new HashMap();
        this.mBackImageNames = new ArrayList<>();
        this.mBackImages = new ArrayList();
        this.mForceIconBack = false;
        this.mBackEdgePointsLock = new Object();
        this.mBackHueColorBundle = new Bundle();
        this.mBackHueColorMap = new HashMap();
        this.mMaskImageName = null;
        this.mMaskImage = null;
        this.mFrontImageName = null;
        this.mFrontImage = null;
        this.mFactor = 1.0f;
        this.mIconPackRes = null;
        this.mIsDefault = false;
        Bitmap createBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        this.mTestIcon = createBitmap;
        this.mTestIconCanvas = new Canvas(createBitmap);
        this.mPm = context.getPackageManager();
        this.mRes = context.getResources();
        this.mPackageName = str;
        this.mName = str2;
        this.mIconPackContext = context.createPackageContext(str, 0);
        logd("new IconPack - packageName=" + str + " | name=" + str2, new boolean[0]);
    }

    private IconPack(String str) {
        this.mLoaded = false;
        this.mPreviewDrawables = new HashMap();
        this.mComponentDrawables = new HashMap();
        this.mPackageDrawables = new HashMap();
        this.mCalendarComponentDrawables = new HashMap();
        this.mCalendarPackageDrawables = new HashMap();
        this.mBackImageNames = new ArrayList<>();
        this.mBackImages = new ArrayList();
        this.mForceIconBack = false;
        this.mBackEdgePointsLock = new Object();
        this.mBackHueColorBundle = new Bundle();
        this.mBackHueColorMap = new HashMap();
        this.mMaskImageName = null;
        this.mMaskImage = null;
        this.mFrontImageName = null;
        this.mFrontImage = null;
        this.mFactor = 1.0f;
        this.mIconPackRes = null;
        this.mIsDefault = false;
        Bitmap createBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        this.mTestIcon = createBitmap;
        this.mTestIconCanvas = new Canvas(createBitmap);
        this.mPackageName = str;
        logd("new IconPack - packageName=" + str, new boolean[0]);
    }

    public boolean hasResources() {
        return this.mIconPackRes != null;
    }

    public void load() {
        load((Bundle) null);
    }

    public synchronized void load(Bundle bundle) {
        if (!this.mLoaded) {
            if (bundle != null) {
                loadFromIconPackInfo(this.mPm, bundle);
            } else {
                loadFromXml(this.mPm);
            }
            this.mLoaded = true;
            Log.d(TAG, "Loaded icons: " + getComponentDrawables());
        }
    }

    private void loadFromIconPackInfo(PackageManager packageManager, Bundle bundle) {
        try {
            this.mIconPackRes = packageManager.getResourcesForApplication(this.mPackageName);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.mComponentDrawables.putAll(getComponentDrawables(bundle));
        this.mCalendarComponentDrawables.putAll(getCalendarComponentDrawables(bundle));
        this.mCalendarPackageDrawables.putAll(getCalendarPackageDrawables(bundle));
        ArrayList<String> backImageNames = getBackImageNames(bundle);
        if (backImageNames != null) {
            this.mBackImageNames.addAll(backImageNames);
        }
        this.mForceIconBack = getForceIconBack(bundle);
        Bundle backHueColorBundle = getBackHueColorBundle(bundle);
        if (backHueColorBundle != null) {
            this.mBackHueColorBundle.putAll(backHueColorBundle);
        }
        this.mMaskImageName = getMaskImageName(bundle);
        this.mFrontImageName = getFrontImageName(bundle);
        this.mFactor = getFactor(bundle);
        Iterator<String> it = this.mBackImageNames.iterator();
        while (it.hasNext()) {
            String next = it.next();
            Bitmap resizeMaxSized = resizeMaxSized(loadBitmap(next), 256);
            if (resizeMaxSized != null) {
                this.mBackImages.add(resizeMaxSized);
                this.mBackHueColorMap.put(resizeMaxSized, Float.valueOf(this.mBackHueColorBundle.getFloat(next)));
            }
        }
        this.mMaskImage = resizeMaxSized(loadBitmap(this.mMaskImageName), 256);
        this.mFrontImage = resizeMaxSized(loadBitmap(this.mFrontImageName), 256);
    }

    private Bitmap resizeMaxSized(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap.getWidth() > i || bitmap.getHeight() > i) {
            return ImageUtils.createScaledBitmapByWidth(bitmap, i, ImageUtils.ScalingLogic.CROP);
        }
        return bitmap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        logd("Cannot find iconPack " + r2.mPackageName, new boolean[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008b, code lost:
        ((android.content.res.XmlResourceParser) r0.xpp).close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0094, code lost:
        if (r0.f84is != null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0096, code lost:
        com.android.launcher3.icons.GzipCompression.closeQuietly(r0.f84is);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadFromXml(android.content.pm.PackageManager r3) {
        /*
            r2 = this;
            r0 = 0
            java.lang.String r1 = r2.mPackageName     // Catch:{ NameNotFoundException -> 0x004f }
            android.content.res.Resources r3 = r3.getResourcesForApplication(r1)     // Catch:{ NameNotFoundException -> 0x004f }
            r2.mIconPackRes = r3     // Catch:{ NameNotFoundException -> 0x004f }
            java.lang.String r3 = "icon_config"
            com.android.launcher3.icons.IconPack$XppHolder r0 = r2.loadXpp(r3)     // Catch:{ NameNotFoundException -> 0x004f }
            if (r0 == 0) goto L_0x0017
            org.xmlpull.v1.XmlPullParser r3 = r0.xpp     // Catch:{ NameNotFoundException -> 0x004f }
            r2.loadIcons(r3)     // Catch:{ NameNotFoundException -> 0x004f }
            goto L_0x0032
        L_0x0017:
            java.lang.String r3 = "theme_resources"
            com.android.launcher3.icons.IconPack$XppHolder r0 = r2.loadXpp(r3)     // Catch:{ NameNotFoundException -> 0x004f }
            if (r0 == 0) goto L_0x0025
            org.xmlpull.v1.XmlPullParser r3 = r0.xpp     // Catch:{ NameNotFoundException -> 0x004f }
            r2.loadIcons(r3)     // Catch:{ NameNotFoundException -> 0x004f }
            goto L_0x0032
        L_0x0025:
            java.lang.String r3 = "icons"
            com.android.launcher3.icons.IconPack$XppHolder r0 = r2.loadXpp(r3)     // Catch:{ NameNotFoundException -> 0x004f }
            if (r0 == 0) goto L_0x0032
            org.xmlpull.v1.XmlPullParser r3 = r0.xpp     // Catch:{ NameNotFoundException -> 0x004f }
            r2.loadIcons(r3)     // Catch:{ NameNotFoundException -> 0x004f }
        L_0x0032:
            org.xmlpull.v1.XmlPullParser r2 = r0.xpp     // Catch:{ Exception -> 0x0080 }
            if (r2 == 0) goto L_0x0043
            org.xmlpull.v1.XmlPullParser r2 = r0.xpp     // Catch:{ Exception -> 0x0080 }
            boolean r2 = r2 instanceof android.content.res.XmlResourceParser     // Catch:{ Exception -> 0x0080 }
            if (r2 == 0) goto L_0x0043
            org.xmlpull.v1.XmlPullParser r2 = r0.xpp     // Catch:{ Exception -> 0x0080 }
            android.content.res.XmlResourceParser r2 = (android.content.res.XmlResourceParser) r2     // Catch:{ Exception -> 0x0080 }
            r2.close()     // Catch:{ Exception -> 0x0080 }
        L_0x0043:
            java.io.InputStream r2 = r0.f84is     // Catch:{ Exception -> 0x0080 }
            if (r2 == 0) goto L_0x0080
        L_0x0047:
            java.io.InputStream r2 = r0.f84is     // Catch:{ Exception -> 0x0080 }
            com.android.launcher3.icons.GzipCompression.closeQuietly(r2)     // Catch:{ Exception -> 0x0080 }
            goto L_0x0080
        L_0x004d:
            r2 = move-exception
            goto L_0x0081
        L_0x004f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x004d }
            r3.<init>()     // Catch:{ all -> 0x004d }
            java.lang.String r1 = "Cannot find iconPack "
            java.lang.StringBuilder r3 = r3.append(r1)     // Catch:{ all -> 0x004d }
            java.lang.String r2 = r2.mPackageName     // Catch:{ all -> 0x004d }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x004d }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x004d }
            r3 = 0
            boolean[] r3 = new boolean[r3]     // Catch:{ all -> 0x004d }
            logd(r2, r3)     // Catch:{ all -> 0x004d }
            org.xmlpull.v1.XmlPullParser r2 = r0.xpp     // Catch:{ Exception -> 0x0080 }
            if (r2 == 0) goto L_0x007b
            org.xmlpull.v1.XmlPullParser r2 = r0.xpp     // Catch:{ Exception -> 0x0080 }
            boolean r2 = r2 instanceof android.content.res.XmlResourceParser     // Catch:{ Exception -> 0x0080 }
            if (r2 == 0) goto L_0x007b
            org.xmlpull.v1.XmlPullParser r2 = r0.xpp     // Catch:{ Exception -> 0x0080 }
            android.content.res.XmlResourceParser r2 = (android.content.res.XmlResourceParser) r2     // Catch:{ Exception -> 0x0080 }
            r2.close()     // Catch:{ Exception -> 0x0080 }
        L_0x007b:
            java.io.InputStream r2 = r0.f84is     // Catch:{ Exception -> 0x0080 }
            if (r2 == 0) goto L_0x0080
            goto L_0x0047
        L_0x0080:
            return
        L_0x0081:
            org.xmlpull.v1.XmlPullParser r3 = r0.xpp     // Catch:{ Exception -> 0x009b }
            if (r3 == 0) goto L_0x0092
            org.xmlpull.v1.XmlPullParser r3 = r0.xpp     // Catch:{ Exception -> 0x009b }
            boolean r3 = r3 instanceof android.content.res.XmlResourceParser     // Catch:{ Exception -> 0x009b }
            if (r3 == 0) goto L_0x0092
            org.xmlpull.v1.XmlPullParser r3 = r0.xpp     // Catch:{ Exception -> 0x009b }
            android.content.res.XmlResourceParser r3 = (android.content.res.XmlResourceParser) r3     // Catch:{ Exception -> 0x009b }
            r3.close()     // Catch:{ Exception -> 0x009b }
        L_0x0092:
            java.io.InputStream r3 = r0.f84is     // Catch:{ Exception -> 0x009b }
            if (r3 == 0) goto L_0x009b
            java.io.InputStream r3 = r0.f84is     // Catch:{ Exception -> 0x009b }
            com.android.launcher3.icons.GzipCompression.closeQuietly(r3)     // Catch:{ Exception -> 0x009b }
        L_0x009b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.IconPack.loadFromXml(android.content.pm.PackageManager):void");
    }

    public Drawable getAppIcon(String str, String str2) {
        Resources resources;
        int identifier;
        String str3 = "";
        String lowerCase = str == null ? str3 : str.toLowerCase();
        if (str2 != null) {
            str3 = str2.toLowerCase();
        }
        String drawableName = getDrawableName(new ComponentName(lowerCase, str3));
        if (drawableName == null || (identifier = resources.getIdentifier(drawableName, "mipmap", this.mPackageName)) == 0) {
            return null;
        }
        Drawable drawable = (resources = this.mIconPackContext.getResources()).getDrawable(identifier, this.mIconPackContext.getTheme());
        if (drawable instanceof AdaptiveIconDrawable) {
            return drawable;
        }
        return null;
    }

    private class XppHolder {

        /* renamed from: is */
        InputStream f84is;
        XmlPullParser xpp;

        private XppHolder() {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.launcher3.icons.IconPack.XppHolder loadXpp(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = ".xml failed"
            java.lang.String r1 = "Load xpp for "
            java.lang.String r2 = "IconPack"
            r3 = 0
            android.content.res.Resources r4 = r9.mIconPackRes     // Catch:{ IOException -> 0x0083, XmlPullParserException -> 0x0067 }
            java.lang.String r5 = "xml"
            java.lang.String r6 = r9.mPackageName     // Catch:{ IOException -> 0x0083, XmlPullParserException -> 0x0067 }
            int r4 = r4.getIdentifier(r10, r5, r6)     // Catch:{ IOException -> 0x0083, XmlPullParserException -> 0x0067 }
            if (r4 == 0) goto L_0x001a
            android.content.res.Resources r5 = r9.mIconPackRes     // Catch:{ IOException -> 0x0083, XmlPullParserException -> 0x0067 }
            android.content.res.XmlResourceParser r4 = r5.getXml(r4)     // Catch:{ IOException -> 0x0083, XmlPullParserException -> 0x0067 }
            goto L_0x001b
        L_0x001a:
            r4 = r3
        L_0x001b:
            if (r4 != 0) goto L_0x0065
            android.content.res.Resources r5 = r9.mIconPackRes     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            r6.<init>()     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            java.lang.StringBuilder r6 = r6.append(r10)     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            java.lang.String r7 = ".xml"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            java.io.InputStream r5 = r5.open(r6)     // Catch:{ IOException -> 0x005f, XmlPullParserException -> 0x0059 }
            org.xmlpull.v1.XmlPullParserFactory r6 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ IOException -> 0x0053, XmlPullParserException -> 0x004d }
            r7 = 1
            r6.setNamespaceAware(r7)     // Catch:{ IOException -> 0x0053, XmlPullParserException -> 0x004d }
            org.xmlpull.v1.XmlPullParser r4 = r6.newPullParser()     // Catch:{ IOException -> 0x0053, XmlPullParserException -> 0x004d }
            java.lang.String r6 = "utf-8"
            r4.setInput(r5, r6)     // Catch:{ IOException -> 0x0053, XmlPullParserException -> 0x004d }
            goto L_0x00a0
        L_0x004d:
            r6 = move-exception
            r8 = r5
            r5 = r4
            r4 = r6
            r6 = r8
            goto L_0x006a
        L_0x0053:
            r6 = move-exception
            r8 = r5
            r5 = r4
            r4 = r6
            r6 = r8
            goto L_0x0086
        L_0x0059:
            r5 = move-exception
            r6 = r3
            r8 = r5
            r5 = r4
            r4 = r8
            goto L_0x006a
        L_0x005f:
            r5 = move-exception
            r6 = r3
            r8 = r5
            r5 = r4
            r4 = r8
            goto L_0x0086
        L_0x0065:
            r5 = r3
            goto L_0x00a0
        L_0x0067:
            r4 = move-exception
            r5 = r3
            r6 = r5
        L_0x006a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r1 = r7.append(r1)
            java.lang.StringBuilder r10 = r1.append(r10)
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r2, r10, r4)
            goto L_0x009e
        L_0x0083:
            r4 = move-exception
            r5 = r3
            r6 = r5
        L_0x0086:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r1 = r7.append(r1)
            java.lang.StringBuilder r10 = r1.append(r10)
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r2, r10, r4)
        L_0x009e:
            r4 = r5
            r5 = r6
        L_0x00a0:
            if (r4 == 0) goto L_0x00ac
            com.android.launcher3.icons.IconPack$XppHolder r10 = new com.android.launcher3.icons.IconPack$XppHolder
            r10.<init>()
            r10.xpp = r4
            r10.f84is = r5
            r3 = r10
        L_0x00ac:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.IconPack.loadXpp(java.lang.String):com.android.launcher3.icons.IconPack$XppHolder");
    }

    private void loadIcons(XmlPullParser xmlPullParser) {
        if (xmlPullParser != null) {
            try {
                int eventType = xmlPullParser.getEventType();
                while (eventType != 1) {
                    if (eventType == 2) {
                        if (xmlPullParser.getName().equalsIgnoreCase(PREVIEW)) {
                            loadPreviewRes(xmlPullParser);
                        }
                        if (xmlPullParser.getName().equalsIgnoreCase(ITEM)) {
                            loadComponentRes(xmlPullParser);
                        } else if (xmlPullParser.getName().equalsIgnoreCase(CALENDAR)) {
                            loadCalendarRes(xmlPullParser);
                        } else if (xmlPullParser.getName().equalsIgnoreCase(APP_ICON)) {
                            loadAppIconRes(xmlPullParser);
                        } else if (xmlPullParser.getName().equals(ICON_BACK)) {
                            loadIconBack(xmlPullParser);
                        } else if (xmlPullParser.getName().equals(ICON_MASK)) {
                            loadIconMask(xmlPullParser);
                        } else if (xmlPullParser.getName().equals(ICON_UPON)) {
                            loadIconUpon(xmlPullParser);
                        } else if (xmlPullParser.getName().equals(SCALE)) {
                            loadScale(xmlPullParser);
                        } else if (xmlPullParser.getName().equalsIgnoreCase(DRAWABLE)) {
                            loadDrawableRes(xmlPullParser);
                        }
                    }
                    eventType = xmlPullParser.next();
                }
            } catch (IOException e) {
                Log.d(TAG, "Load icons failed", e);
            } catch (XmlPullParserException e2) {
                Log.d(TAG, "Load icons failed", e2);
            }
            for (int i = 0; i < this.mBackImages.size(); i++) {
                Bitmap bitmap = this.mBackImages.get(i);
                float hueColorFromColor = getHueColorFromColor(getPaletteColorFromBitmap(bitmap));
                this.mBackHueColorMap.put(bitmap, Float.valueOf(hueColorFromColor));
                this.mBackHueColorBundle.putFloat(this.mBackImageNames.get(i), hueColorFromColor);
            }
        }
    }

    private void loadAppIconRes(XmlPullParser xmlPullParser) {
        String str = null;
        String str2 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equalsIgnoreCase("name")) {
                str = getComponentNameFlattenString(xmlPullParser.getAttributeValue(i));
            } else if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(IMAGE)) {
                str2 = xmlPullParser.getAttributeValue(i);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            if (!this.mComponentDrawables.containsKey(str)) {
                this.mComponentDrawables.put(str, str2);
            }
            ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
            if (unflattenFromString != null) {
                String flattenToString = new ComponentName(unflattenFromString.getPackageName(), "").flattenToString();
                if (!this.mComponentDrawables.containsKey(flattenToString)) {
                    this.mComponentDrawables.put(flattenToString, str2);
                }
            }
        }
    }

    private void loadIconBack(XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).startsWith(IMG)) {
                String attributeValue = xmlPullParser.getAttributeValue(i);
                Bitmap loadBitmap = loadBitmap(attributeValue);
                if (loadBitmap != null) {
                    this.mBackImages.add(loadBitmap);
                    this.mBackImageNames.add(attributeValue);
                }
            } else if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(FORCE)) {
                this.mForceIconBack = Boolean.valueOf(xmlPullParser.getAttributeValue(i)).booleanValue();
            }
        }
    }

    private void loadIconMask(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getAttributeCount() > 0 && xmlPullParser.getAttributeName(0).equals(IMG_1)) {
            String attributeValue = xmlPullParser.getAttributeValue(0);
            this.mMaskImage = loadBitmap(attributeValue);
            this.mMaskImageName = attributeValue;
        }
    }

    private void loadIconUpon(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getAttributeCount() > 0 && xmlPullParser.getAttributeName(0).equals(IMG_1)) {
            String attributeValue = xmlPullParser.getAttributeValue(0);
            this.mFrontImage = loadBitmap(attributeValue);
            this.mFrontImageName = attributeValue;
        }
    }

    private void loadScale(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getAttributeCount() > 0 && xmlPullParser.getAttributeName(0).equals(FACTOR)) {
            try {
                this.mFactor = Float.valueOf(xmlPullParser.getAttributeValue(0)).floatValue();
            } catch (NumberFormatException unused) {
                this.mFactor = 1.0f;
            }
        }
    }

    private void loadDrawableRes(XmlPullParser xmlPullParser) {
        String str = null;
        String str2 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equalsIgnoreCase("name")) {
                str2 = UNDERSCORE_PATTERN.matcher(DOUBLE_UNDERSCORE_PATTERN.split(xmlPullParser.getAttributeValue(i))[0]).replaceAll(".");
            }
        }
        try {
            str = xmlPullParser.nextText();
            if (str.startsWith(DRAWABLE_PREFIX)) {
                str = str.substring(10);
            }
        } catch (Exception e) {
            Log.d(TAG, "Get drawable value failed", e);
        }
        if (!TextUtils.isEmpty(str2)) {
            String flattenToString = new ComponentName(str2, "").flattenToString();
            if (!this.mComponentDrawables.containsKey(flattenToString)) {
                this.mComponentDrawables.put(flattenToString, str);
            }
        }
    }

    private void loadPreviewRes(XmlPullParser xmlPullParser) {
        String str = null;
        String str2 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(ORIENTATION)) {
                str = xmlPullParser.getAttributeValue(i);
            } else if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(DRAWABLE)) {
                str2 = xmlPullParser.getAttributeValue(i);
            }
        }
        if (!TextUtils.isEmpty(str) && !this.mPreviewDrawables.containsKey(str)) {
            this.mPreviewDrawables.put(str, str2);
        }
    }

    private void loadComponentRes(XmlPullParser xmlPullParser) {
        String str = null;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(COMPONENT)) {
                str = getComponentNameFlattenString(xmlPullParser.getAttributeValue(i));
            } else if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(DRAWABLE)) {
                str2 = xmlPullParser.getAttributeValue(i);
            } else if (xmlPullParser.getAttributeName(i).equalsIgnoreCase("package")) {
                str3 = getPackageNameFlattenString(xmlPullParser.getAttributeValue(i));
            }
        }
        if (!TextUtils.isEmpty(str)) {
            if (!this.mComponentDrawables.containsKey(str)) {
                this.mComponentDrawables.put(str, str2);
            }
            ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
            if (unflattenFromString != null) {
                String flattenToString = new ComponentName(unflattenFromString.getPackageName(), "").flattenToString();
                if (!this.mComponentDrawables.containsKey(flattenToString)) {
                    this.mComponentDrawables.put(flattenToString, str2);
                }
            }
        } else if (!TextUtils.isEmpty(str3) && !this.mPackageDrawables.containsKey(str3)) {
            this.mPackageDrawables.put(str3, str2);
        }
    }

    private void loadCalendarRes(XmlPullParser xmlPullParser) {
        String str = null;
        String str2 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(COMPONENT)) {
                str = getComponentNameFlattenString(xmlPullParser.getAttributeValue(i));
            } else if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(PREFIX)) {
                str2 = xmlPullParser.getAttributeValue(i);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            if (!this.mCalendarComponentDrawables.containsKey(str)) {
                this.mCalendarComponentDrawables.put(str, str2);
            }
            ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
            if (unflattenFromString != null && !this.mCalendarPackageDrawables.containsKey(unflattenFromString.getPackageName())) {
                this.mCalendarPackageDrawables.put(unflattenFromString.getPackageName(), str2);
            }
        }
    }

    private Bitmap loadBitmap(String str) {
        return loadBitmap(str, 0);
    }

    private Bitmap loadBitmap(String str, int i) {
        Resources resources;
        if (str == null || (resources = this.mIconPackRes) == null) {
            return null;
        }
        return loadBitmap(resources.getIdentifier(str, "mipmap", this.mPackageName), i);
    }

    private Bitmap loadBitmap(int i) {
        return loadBitmap(i, 0);
    }

    private Bitmap loadBitmap(int i, int i2) {
        if (i == 0) {
            return null;
        }
        return decodeIconBitmapFromResource(this.mIconPackRes, i, i2);
    }

    public static ComponentName getComponentName(PackageManager packageManager, String str) {
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            return launchIntentForPackage.getComponent();
        }
        return null;
    }

    public boolean hasDrawable(String str) {
        if (isDefault()) {
            return true;
        }
        String drawableName = getDrawableName(getComponentName(this.mPm, str));
        if (drawableName == null) {
            return false;
        }
        if (this.mIconPackRes.getIdentifier(drawableName, "mipmap", this.mPackageName) != 0) {
            return true;
        }
        return false;
    }

    private String getDrawableName(ComponentName componentName) {
        if (componentName == null) {
            return null;
        }
        String flattenToString = componentName.flattenToString();
        String calendarDrawable = getCalendarDrawable(flattenToString);
        if (calendarDrawable == null) {
            calendarDrawable = this.mComponentDrawables.get(flattenToString);
        }
        if (calendarDrawable == null) {
            calendarDrawable = this.mComponentDrawables.get(new ComponentName(componentName.getPackageName(), "").flattenToString());
        }
        if (calendarDrawable == null) {
            calendarDrawable = this.mPackageDrawables.get(componentName.getPackageName());
        }
        return (calendarDrawable != null || flattenToString == null) ? calendarDrawable : COMPONENT_DRAWABLE_NAME_PATTERN.matcher(flattenToString.toLowerCase()).replaceAll(CustomAppIcons.UNDERSCORE);
    }

    public Drawable getPreview(String str) {
        Bitmap loadBitmap = loadBitmap(this.mPreviewDrawables.get(str));
        if (loadBitmap != null) {
            return new BitmapDrawable(this.mRes, loadBitmap);
        }
        return null;
    }

    public Drawable getIconDrawable(String str, String str2, Bitmap bitmap) {
        Bitmap iconBitmap = getIconBitmap(str, str2, bitmap);
        if (iconBitmap != null) {
            return new BitmapDrawable(this.mRes, iconBitmap);
        }
        return null;
    }

    public Bitmap getIconBitmap(String str, String str2, Bitmap bitmap) {
        ComponentName componentName;
        if (!this.mLoaded) {
            load((Bundle) null);
        }
        if (isDefault() || TextUtils.isEmpty(str)) {
            return bitmap;
        }
        if (!TextUtils.isEmpty(str2)) {
            componentName = new ComponentName(str, str2);
        } else {
            componentName = getComponentName(this.mPm, str);
        }
        Bitmap loadBitmap = loadBitmap(getDrawableName(componentName));
        if (loadBitmap != null) {
            return this.mForceIconBack ? generateIconBitmap(loadBitmap) : loadBitmap;
        }
        return generateIconBitmap(bitmap);
    }

    public Drawable generateIconDrawable(Drawable drawable) {
        Bitmap bitmap = toBitmap(drawable);
        Bitmap generateIconBitmap = generateIconBitmap(bitmap);
        return (generateIconBitmap == null || generateIconBitmap == bitmap) ? drawable : new BitmapDrawable(this.mRes, generateIconBitmap);
    }

    public Bitmap generateIconBitmap(Bitmap bitmap) {
        int i;
        if (!this.mLoaded) {
            load((Bundle) null);
        }
        if (!(bitmap == null || isDefault() || (this.mBackImages.size() == 0 && this.mMaskImage == null && this.mFrontImage == null))) {
            long currentTimeMillis = System.currentTimeMillis();
            Bitmap mostAppropriateBackImage = getMostAppropriateBackImage(bitmap);
            logd("generateIconBitmap - Get back image consumed: " + (System.currentTimeMillis() - currentTimeMillis), new boolean[0]);
            if (mostAppropriateBackImage == null) {
                i = Math.min(bitmap.getWidth(), bitmap.getHeight());
            } else {
                i = Math.min(Math.min(bitmap.getWidth(), bitmap.getHeight()), Math.min(mostAppropriateBackImage.getWidth(), mostAppropriateBackImage.getHeight()));
            }
            int max = Math.max(128, i);
            logd("generateIconBitmap - iconWidth=" + max + " | iconHeight=" + max + " | iconSize=" + max, new boolean[0]);
            try {
                Bitmap createBitmap = Bitmap.createBitmap(max, max, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), getDestRect(calculateFactor(bitmap), max, max), sPaint);
                if (this.mMaskImage != null) {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    canvas.drawBitmap(this.mMaskImage, new Rect(0, 0, this.mMaskImage.getWidth(), this.mMaskImage.getHeight()), new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), sMaskPaint);
                    logd("generateIconBitmap - Draw mask image consumed: " + (System.currentTimeMillis() - currentTimeMillis2), new boolean[0]);
                }
                if (mostAppropriateBackImage != null) {
                    long currentTimeMillis3 = System.currentTimeMillis();
                    canvas.drawBitmap(mostAppropriateBackImage, new Rect(0, 0, mostAppropriateBackImage.getWidth(), mostAppropriateBackImage.getHeight()), new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), sMaskPaint2);
                    logd("generateIconBitmap - Draw back image consumed: " + (System.currentTimeMillis() - currentTimeMillis3), new boolean[0]);
                }
                if (this.mFrontImage != null) {
                    long currentTimeMillis4 = System.currentTimeMillis();
                    canvas.drawBitmap(this.mFrontImage, new Rect(0, 0, this.mFrontImage.getWidth(), this.mFrontImage.getHeight()), new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), sPaint);
                    logd("generateIconBitmap - Draw front image consumed: " + (System.currentTimeMillis() - currentTimeMillis4), new boolean[0]);
                }
                logd("generateIconBitmap - Consumed: " + (System.currentTimeMillis() - currentTimeMillis), new boolean[0]);
                return createBitmap;
            } catch (OutOfMemoryError e) {
                logd("Out of memory to create result icon in generateIconBitmap", new boolean[0]);
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private RectF getDestRect(float f, int i, int i2) {
        float f2 = (float) i;
        float f3 = f * f2;
        float f4 = (float) i2;
        float f5 = f * f4;
        float f6 = f2 / 2.0f;
        float f7 = f3 / 2.0f;
        float f8 = f4 / 2.0f;
        float f9 = f5 / 2.0f;
        return new RectF(f6 - f7, f8 - f9, f6 + f7, f8 + f9);
    }

    private float calculateFactor(Bitmap bitmap) {
        float f = this.mFactor;
        if (f > 0.0f) {
            return f;
        }
        Float bestFactor = getBestFactor(bitmap, 1.1f, 1.2f, 1.3f, 1.4f);
        if (bestFactor == null) {
            return 0.8f;
        }
        return bestFactor.floatValue() + 0.05f;
    }

    private Float getBestFactor(Bitmap bitmap, float... fArr) {
        for (float f : fArr) {
            if (!isEdgeTransparent(bitmap, f)) {
                return Float.valueOf(f);
            }
        }
        return null;
    }

    private boolean isEdgeTransparent(Bitmap bitmap, float f) {
        this.mTestIconCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        this.mTestIconCanvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), getDestRect(f, this.mTestIconCanvas.getWidth(), this.mTestIconCanvas.getHeight()), (Paint) null);
        return isEdgeTransparent(this.mTestIcon, getBackEdgePoints());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:19|20) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r8.mBackEdgePoints = new android.graphics.Point[0];
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Point[] getBackEdgePoints() {
        /*
            r8 = this;
            java.util.List<android.graphics.Bitmap> r0 = r8.mBackImages
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0059
            java.util.List<android.graphics.Bitmap> r0 = r8.mBackImages
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            int r2 = r0.getWidth()
            if (r2 <= 0) goto L_0x0059
            int r2 = r0.getHeight()
            if (r2 > 0) goto L_0x001e
            goto L_0x0059
        L_0x001e:
            android.graphics.Point[] r2 = r8.mBackEdgePoints
            if (r2 != 0) goto L_0x0056
            java.lang.Object r2 = r8.mBackEdgePointsLock
            monitor-enter(r2)
            android.graphics.Point[] r3 = r8.mBackEdgePoints     // Catch:{ all -> 0x0053 }
            if (r3 != 0) goto L_0x0051
            r3 = 8
            android.graphics.Point[] r4 = new android.graphics.Point[r3]     // Catch:{ all -> 0x0053 }
            r8.mBackEdgePoints = r4     // Catch:{ all -> 0x0053 }
            r4 = 1065353216(0x3f800000, float:1.0)
            android.graphics.Bitmap r5 = r8.mTestIcon     // Catch:{ all -> 0x0053 }
            int r5 = r5.getWidth()     // Catch:{ all -> 0x0053 }
            float r5 = (float) r5     // Catch:{ all -> 0x0053 }
            float r5 = r5 * r4
            int r4 = r0.getWidth()     // Catch:{ all -> 0x0053 }
            float r4 = (float) r4
            float r5 = r5 / r4
            r4 = r1
        L_0x0040:
            if (r4 >= r3) goto L_0x0051
            android.graphics.Point[] r6 = r8.mBackEdgePoints     // Catch:{ Exception -> 0x004d }
            android.graphics.Point r7 = r8.getEdgePoint(r0, r5, r4)     // Catch:{ Exception -> 0x004d }
            r6[r4] = r7     // Catch:{ Exception -> 0x004d }
            int r4 = r4 + 1
            goto L_0x0040
        L_0x004d:
            android.graphics.Point[] r0 = new android.graphics.Point[r1]     // Catch:{ all -> 0x0053 }
            r8.mBackEdgePoints = r0     // Catch:{ all -> 0x0053 }
        L_0x0051:
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            goto L_0x0056
        L_0x0053:
            r8 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            throw r8
        L_0x0056:
            android.graphics.Point[] r8 = r8.mBackEdgePoints
            return r8
        L_0x0059:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.IconPack.getBackEdgePoints():android.graphics.Point[]");
    }

    private Point getEdgePoint(Bitmap bitmap, float f, int i) {
        if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            throw new RuntimeException("Get edge point error for bitmap is invalid.");
        }
        int width = bitmap.getWidth() - 1;
        int i2 = width / 2;
        int height = bitmap.getHeight() - 1;
        int i3 = height / 2;
        Point point = null;
        int i4 = 0;
        if (i == 0) {
            point = new Point(0, 0);
            int i5 = 0;
            while (i4 <= i2 && i5 <= i3 && bitmap.getPixel(i4, i5) == 0) {
                point.x = i4;
                point.y = i5;
                i4++;
                i5++;
            }
        } else if (i == 1) {
            point = new Point(i2, 0);
            while (i4 <= i3 && bitmap.getPixel(i2, i4) == 0) {
                point.y = i4;
                i4++;
            }
        } else if (i == 2) {
            point = new Point(width, 0);
            while (width > i2 && i4 <= i3 && bitmap.getPixel(width, i4) == 0) {
                point.x = width;
                point.y = i4;
                width--;
                i4++;
            }
        } else if (i == 3) {
            point = new Point(0, i3);
            while (i4 <= i2 && bitmap.getPixel(i4, i3) == 0) {
                point.x = i4;
                i4++;
            }
        } else if (i == 4) {
            point = new Point(width, i3);
            while (width > i2) {
                if (bitmap.getPixel(width, i3) == 0) {
                    point.x = width;
                }
                width--;
            }
        } else if (i == 5) {
            point = new Point(0, height);
            while (i4 <= i2 && height > i3 && bitmap.getPixel(i4, height) == 0) {
                point.x = i4;
                point.y = height;
                i4++;
                height--;
            }
        } else if (i == 6) {
            point = new Point(i2, height);
            while (height > i3) {
                if (bitmap.getPixel(i2, height) == 0) {
                    point.y = height;
                }
                height--;
            }
        } else if (i == 7) {
            point = new Point(width, height);
            while (width > i2 && height > i3 && bitmap.getPixel(width, height) == 0) {
                point.x = width;
                point.y = height;
                width--;
                height--;
            }
        }
        Log.d(TAG, "Edge point " + i + ": " + point);
        point.x = (int) (((float) point.x) * f);
        point.y = (int) (((float) point.y) * f);
        return point;
    }

    private boolean isEdgeTransparent(Bitmap bitmap, Point... pointArr) {
        if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            return false;
        }
        if (pointArr == null || pointArr.length < 8) {
            int width = bitmap.getWidth() - 1;
            int i = width / 2;
            int height = bitmap.getHeight() - 1;
            int i2 = height / 2;
            if (bitmap.getPixel(0, 0) == 0 || bitmap.getPixel(i, 0) == 0 || bitmap.getPixel(width, 0) == 0 || bitmap.getPixel(0, i2) == 0 || bitmap.getPixel(width, i2) == 0 || bitmap.getPixel(0, height) == 0 || bitmap.getPixel(i, height) == 0 || bitmap.getPixel(width, height) == 0) {
                return true;
            }
        } else {
            for (int i3 = 0; i3 < 8; i3++) {
                if (bitmap.getPixel(pointArr[i3].x, pointArr[i3].y) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private Bitmap getMostAppropriateBackImage(Bitmap bitmap) {
        if (this.mBackImages.size() == 0) {
            return null;
        }
        if (this.mBackImages.size() == 1) {
            return this.mBackImages.get(0);
        }
        float hueColorFromColor = getHueColorFromColor(getPaletteColorFromBitmap(bitmap));
        float f = Float.MAX_VALUE;
        int i = 0;
        for (int i2 = 0; i2 < this.mBackImages.size(); i2++) {
            float abs = Math.abs(hueColorFromColor - this.mBackHueColorMap.get(this.mBackImages.get(i2)).floatValue());
            if (abs < f) {
                i = i2;
                f = abs;
            }
        }
        return this.mBackImages.get(i);
    }

    public boolean equals(Object obj) {
        String str;
        if (!(obj instanceof IconPack) || (str = this.mPackageName) == null || !str.equals(((IconPack) obj).mPackageName)) {
            return super.equals(obj);
        }
        return true;
    }

    public List<Bitmap> getBackImages() {
        return this.mBackImages;
    }

    public boolean hasBackImage() {
        return this.mBackImages.size() > 0;
    }

    public Bitmap getFrontImage() {
        return this.mFrontImage;
    }

    public Bitmap getMaskImage() {
        return this.mMaskImage;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public boolean isDefault() {
        return this.mIsDefault;
    }

    public boolean isCalendarApp(String str) {
        return this.mCalendarPackageDrawables.containsKey(str);
    }

    private String getCalendarDrawable(String str) {
        String str2 = this.mCalendarComponentDrawables.get(str);
        return str2 != null ? str2 + generateResourceIndexWithCalendarDate() : str2;
    }

    public String[] getCalendarApps(List<LauncherActivityInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (LauncherActivityInfo componentName : list) {
            ComponentName componentName2 = componentName.getComponentName();
            if (this.mCalendarComponentDrawables.containsKey(componentName2.flattenToString())) {
                arrayList.add(componentName2.getPackageName());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public Map<String, String> getComponentDrawables() {
        return this.mComponentDrawables;
    }

    public Map<String, String> getCalendarComponentDrawables() {
        return this.mCalendarComponentDrawables;
    }

    public Map<String, String> getCalendarPackageDrawables() {
        return this.mCalendarPackageDrawables;
    }

    public ArrayList<String> getBackImageNames() {
        return this.mBackImageNames;
    }

    public boolean isForceIconBack() {
        return this.mForceIconBack;
    }

    public Bundle getBackHueColorBundle() {
        return this.mBackHueColorBundle;
    }

    public String getMaskImageName() {
        return this.mMaskImageName;
    }

    public String getFrontImageName() {
        return this.mFrontImageName;
    }

    public float getFactor() {
        return this.mFactor;
    }

    public static IconPack newIconPack(Context context, String str) {
        return newIconPack(context, str, (String) null);
    }

    public static IconPack newIconPack(Context context, String str, String str2) {
        if (str == null || DEFAULT_ICON_PACK_PKG_NAME.equals(str) || !isAppEnabled(context.getPackageManager(), str)) {
            return DEFAULT_ICON_PACK;
        }
        try {
            IconPack iconPack = new IconPack(context, str, str2);
            iconPack.load();
            return iconPack;
        } catch (Exception unused) {
            Log.w(TAG, "Cannot new Icon pack: " + str);
            return DEFAULT_ICON_PACK;
        }
    }

    public static IconPack newIconPack(Context context, Bundle bundle) {
        String packageName = getPackageName(bundle);
        Log.d(TAG, "newIconPack - packageName: " + packageName);
        IconPack newIconPack = newIconPack(context, packageName);
        if (!newIconPack.isDefault() && bundle.size() > 0) {
            newIconPack.load(bundle);
        }
        return newIconPack;
    }

    public static String getPackageName(Bundle bundle) {
        String string = bundle.getString(ICON_PACK);
        bundle.remove(ICON_PACK);
        return string;
    }

    public static Map<String, String> getComponentDrawables(Bundle bundle) {
        return JsonUtils.jsonToMap(GzipCompression.decompress(bundle.getByteArray(COMPONENT_DRAWABLES)));
    }

    public static Map<String, String> getCalendarComponentDrawables(Bundle bundle) {
        return JsonUtils.jsonToMap(GzipCompression.decompress(bundle.getByteArray(CALENDAR_COMPONENT_DRAWABLES)));
    }

    public static Map<String, String> getCalendarPackageDrawables(Bundle bundle) {
        return JsonUtils.jsonToMap(GzipCompression.decompress(bundle.getByteArray(CALENDAR_PACKAGE_DRAWABLES)));
    }

    public static ArrayList<String> getBackImageNames(Bundle bundle) {
        return bundle.getStringArrayList(BACK_IMAGE_NAMES);
    }

    public static boolean getForceIconBack(Bundle bundle) {
        return bundle.getBoolean(FORCE_ICON_BACK);
    }

    public static Bundle getBackHueColorBundle(Bundle bundle) {
        return bundle.getBundle(BACK_HUE_COLOR_BUNDLE);
    }

    public static String getMaskImageName(Bundle bundle) {
        return bundle.getString(MASK_IMAGE_NAME);
    }

    public static String getFrontImageName(Bundle bundle) {
        return bundle.getString(FRONT_IMAGE_NAME);
    }

    public static float getFactor(Bundle bundle) {
        return bundle.getFloat(FACTOR);
    }

    public static boolean isAppEnabled(PackageManager packageManager, String str) {
        return isAppEnabled(packageManager, str, 0);
    }

    public static boolean isAppEnabled(PackageManager packageManager, String str, int i) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, i);
            if (applicationInfo == null || !applicationInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static ComponentName unflattenComponentNameFromString(String str) {
        if (str == null) {
            return null;
        }
        return ComponentName.unflattenFromString(getComponentNameFlattenString(str));
    }

    public static String getComponentNameFlattenString(String str) {
        return (!str.contains(COMPONENT_PREFIX) || !str.contains("}")) ? str : str.substring(14, str.indexOf("}")).toLowerCase();
    }

    private static String getPackageNameFlattenString(String str) {
        return (!str.contains(PACKAGE_PREFIX) || !str.contains(PACKAGE_PREFIX)) ? str : str.substring(12, str.indexOf("}")).toLowerCase();
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int i, int i2, int i3) {
        if (i2 <= 0 || i3 <= 0) {
            return BitmapFactory.decodeResource(resources, i);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap decodeIconBitmapFromResource(Resources resources, int i, int i2) {
        return decodeSampledBitmapFromResource(resources, i, i2, i2);
    }

    public static int generateResourceIndexWithCalendarDate() {
        return generateResourceIndexWithCalendarDate(false);
    }

    public static int generateResourceIndexWithCalendarDate(boolean z) {
        return Calendar.getInstance().get(5) - (z ? 1 : 0);
    }

    public static void logd(String str, boolean... zArr) {
        logd(true, TAG, str, zArr);
    }

    public static void logd(boolean z, String str, String str2, boolean... zArr) {
        if (!z) {
            return;
        }
        if (zArr == null || zArr.length <= 0 || !zArr[0]) {
            Log.d(str, str2);
        } else {
            Log.d(str, str2, new RuntimeException("" + System.currentTimeMillis()));
        }
    }

    public static float getHueColorFromColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr[0];
    }

    public static Bitmap toBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int i = 1;
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public Drawable getIconDrawable(ActivityInfo activityInfo, String str) {
        Drawable iconDrawable = activityInfo != null ? getIconDrawable(activityInfo.packageName, activityInfo.targetActivity, (Bitmap) null) : null;
        if (iconDrawable != null) {
            return iconDrawable;
        }
        if ((activityInfo == null || activityInfo.packageName == null) && str == null) {
            return iconDrawable;
        }
        if (str == null) {
            str = activityInfo.packageName;
        }
        return getIconDrawable(str, (String) null, (Bitmap) null);
    }

    public boolean isLoaded() {
        return this.mLoaded;
    }

    public String getAppName() {
        try {
            return (String) this.mPm.getApplicationLabel(this.mPm.getApplicationInfo(this.mPackageName, 0));
        } catch (Exception e) {
            logd("getAppName e.getmessage = " + e.getMessage(), new boolean[0]);
            return "";
        }
    }
}
