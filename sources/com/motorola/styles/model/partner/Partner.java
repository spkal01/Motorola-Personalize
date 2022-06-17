package com.motorola.styles.model.partner;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;
import android.view.Display;
import android.view.WindowManager;
import com.motorola.styles.LogConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

public class Partner {
    private static final String ACTION_PARTNER_CUSTOMIZATION = "com.android.launcher3.action.PARTNER_CUSTOMIZATION";
    public static final String RES_DEFAULT_DEVICE_PROFILE = "partner_device_profiles";
    public static final String RES_DEFAULT_LAYOUT = "partner_default_layout";
    public static final String RES_DEFAULT_LAYOUT_V2 = "partner_default_layout_v2";
    public static final String RES_GRID_ICON_SIZE_DP = "grid_icon_size_dp";
    public static final String RES_GRID_NUM_COLUMNS = "grid_num_columns";
    public static final String RES_GRID_NUM_ROWS = "grid_num_rows";
    static final String TAG = "Styles.Partner";
    private static Partner sPartner = null;
    private static boolean sSearched = false;
    private final String mPackageName;
    private final Resources mResources;

    public static synchronized Partner get(PackageManager packageManager) {
        Partner partner;
        synchronized (Partner.class) {
            if (!sSearched) {
                Pair<String, Resources> findSystemApk = findSystemApk(ACTION_PARTNER_CUSTOMIZATION, packageManager);
                if (findSystemApk != null) {
                    sPartner = new Partner((String) findSystemApk.first, (Resources) findSystemApk.second);
                }
                sSearched = true;
            }
            partner = sPartner;
        }
        return partner;
    }

    private Partner(String str, Resources resources) {
        this.mPackageName = str;
        this.mResources = resources;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public boolean hasDefaultLayout() {
        return getResources().getIdentifier(RES_DEFAULT_LAYOUT, "xml", getPackageName()) != 0;
    }

    public boolean hasDefaultDeviceProfile() {
        return getResources().getIdentifier(RES_DEFAULT_DEVICE_PROFILE, "xml", getPackageName()) != 0;
    }

    public int getDefaultDeviceProfileId() {
        return getResources().getIdentifier(RES_DEFAULT_DEVICE_PROFILE, "xml", getPackageName());
    }

    public boolean hasDefaultLayoutV2() {
        return getResources().getIdentifier(RES_DEFAULT_LAYOUT_V2, "xml", getPackageName()) != 0;
    }

    static Pair<String, Resources> findSystemApk(String str, PackageManager packageManager) {
        for (ResolveInfo next : packageManager.queryBroadcastReceivers(new Intent(str), 0)) {
            if (!(next.activityInfo == null || (next.activityInfo.applicationInfo.flags & 1) == 0)) {
                String str2 = next.activityInfo.packageName;
                try {
                    return Pair.create(str2, packageManager.getResourcesForApplication(str2));
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.w(TAG, "Failed to find resources for " + str2);
                }
            }
        }
        return null;
    }

    public static String getGridName(Context context) {
        String str;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        Partner partner = get(context.getPackageManager());
        if (partner != null) {
            str = getPartnerGridName(context, displayMetrics, partner);
            if (!partner.hasDefaultLayoutV2()) {
                String partnerGridNameOverrides = getPartnerGridNameOverrides(partner);
                if (!TextUtils.isEmpty(str)) {
                    str = partnerGridNameOverrides;
                }
            }
        } else {
            str = "";
        }
        if (LogConfig.DBG) {
            Log.d(TAG, "Default gridName: " + str);
        }
        return str;
    }

    public static String getPartnerGridName(Context context, DisplayMetrics displayMetrics, Partner partner) {
        String str = "";
        if (!partner.hasDefaultDeviceProfile()) {
            return str;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getCurrentSizeRange(point, point2);
        float dpiFromPx = dpiFromPx(Math.min(point.x, point.y), displayMetrics);
        float dpiFromPx2 = dpiFromPx(Math.min(point2.x, point2.y), displayMetrics);
        ArrayList<DisplayOption> predefinedDeviceProfiles = getPredefinedDeviceProfiles(context, partner);
        if (predefinedDeviceProfiles.size() > 0) {
            Collections.sort(predefinedDeviceProfiles, new Comparator(dpiFromPx, dpiFromPx2) {
                public final /* synthetic */ float f$0;
                public final /* synthetic */ float f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final int compare(Object obj, Object obj2) {
                    return Float.compare(Partner.dist(this.f$0, this.f$1, ((DisplayOption) obj).minWidthDps, ((DisplayOption) obj).minHeightDps), Partner.dist(this.f$0, this.f$1, ((DisplayOption) obj2).minWidthDps, ((DisplayOption) obj2).minHeightDps));
                }
            });
            str = predefinedDeviceProfiles.get(0).grid.name;
        }
        InvariantDeviceProfile invariantDeviceProfile = findClosestDeviceProfiles(dpiFromPx, dpiFromPx2, getPartnerDeviceProfiles(partner.getResources(), partner.getDefaultDeviceProfileId())).get(0);
        return (invariantDeviceProfile.numRows <= 0 || invariantDeviceProfile.numColumns <= 0) ? str : getGridName(invariantDeviceProfile.numRows, invariantDeviceProfile.numColumns);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPartnerGridNameOverrides(com.motorola.styles.model.partner.Partner r5) {
        /*
            java.lang.String r0 = "integer"
            android.content.res.Resources r1 = r5.getResources()
            java.lang.String r5 = r5.getPackageName()
            r2 = -1
            java.lang.String r3 = "grid_num_rows"
            int r3 = r1.getIdentifier(r3, r0, r5)     // Catch:{ NotFoundException -> 0x0029 }
            if (r3 <= 0) goto L_0x0018
            int r3 = r1.getInteger(r3)     // Catch:{ NotFoundException -> 0x0029 }
            goto L_0x0019
        L_0x0018:
            r3 = r2
        L_0x0019:
            java.lang.String r4 = "grid_num_columns"
            int r5 = r1.getIdentifier(r4, r0, r5)     // Catch:{ NotFoundException -> 0x0027 }
            if (r5 <= 0) goto L_0x0032
            int r5 = r1.getInteger(r5)     // Catch:{ NotFoundException -> 0x0027 }
            r2 = r5
            goto L_0x0032
        L_0x0027:
            r5 = move-exception
            goto L_0x002b
        L_0x0029:
            r5 = move-exception
            r3 = r2
        L_0x002b:
            java.lang.String r0 = "Styles.Partner"
            java.lang.String r1 = "Invalid Partner grid resource!"
            android.util.Log.e(r0, r1, r5)
        L_0x0032:
            if (r3 <= 0) goto L_0x003b
            if (r2 <= 0) goto L_0x003b
            java.lang.String r5 = getGridName(r3, r2)
            goto L_0x003d
        L_0x003b:
            java.lang.String r5 = ""
        L_0x003d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.styles.model.partner.Partner.getPartnerGridNameOverrides(com.motorola.styles.model.partner.Partner):java.lang.String");
    }

    public static float dpiFromPx(int i, DisplayMetrics displayMetrics) {
        return ((float) i) / (((float) displayMetrics.densityDpi) / 160.0f);
    }

    public static float dist(float f, float f2, float f3, float f4) {
        return (float) Math.hypot((double) (f3 - f), (double) (f4 - f2));
    }

    static ArrayList<DisplayOption> getPredefinedDeviceProfiles(Context context, Partner partner) {
        XmlResourceParser xml;
        Resources resources = partner.getResources();
        int defaultDeviceProfileId = partner.getDefaultDeviceProfileId();
        ArrayList arrayList = new ArrayList();
        try {
            xml = resources.getXml(defaultDeviceProfileId);
            int depth = xml.getDepth();
            while (true) {
                int next = xml.next();
                if ((next != 3 || xml.getDepth() > depth) && next != 1) {
                    if (next == 2 && GridOption.TAG_NAME.equals(xml.getName())) {
                        GridOption gridOption = new GridOption(context, Xml.asAttributeSet(xml));
                        int depth2 = xml.getDepth();
                        while (true) {
                            int next2 = xml.next();
                            if ((next2 == 3 && xml.getDepth() <= depth2) || next2 == 1) {
                                break;
                            } else if (next2 == 2 && "display-option".equals(xml.getName())) {
                                arrayList.add(new DisplayOption(gridOption, context, Xml.asAttributeSet(xml)));
                            }
                        }
                    }
                }
            }
            if (xml != null) {
                xml.close();
            }
            ArrayList<DisplayOption> arrayList2 = new ArrayList<>();
            if (arrayList2.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    DisplayOption displayOption = (DisplayOption) it.next();
                    if (displayOption.canBeDefault) {
                        arrayList2.add(displayOption);
                    }
                }
            }
            if (arrayList2.isEmpty() && arrayList.size() > 0) {
                arrayList2.add((DisplayOption) arrayList.get(0));
            }
            return arrayList2;
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    static ArrayList<InvariantDeviceProfile> findClosestDeviceProfiles(float f, float f2, ArrayList<InvariantDeviceProfile> arrayList) {
        Collections.sort(arrayList, new Comparator(f, f2) {
            public final /* synthetic */ float f$0;
            public final /* synthetic */ float f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final int compare(Object obj, Object obj2) {
                return Float.compare(Partner.dist(this.f$0, this.f$1, ((InvariantDeviceProfile) obj).minWidthDps, ((InvariantDeviceProfile) obj).minHeightDps), Partner.dist(this.f$0, this.f$1, ((InvariantDeviceProfile) obj2).minWidthDps, ((InvariantDeviceProfile) obj2).minHeightDps));
            }
        });
        return arrayList;
    }

    static ArrayList<InvariantDeviceProfile> getPartnerDeviceProfiles(Resources resources, int i) {
        XmlResourceParser xml;
        ArrayList<InvariantDeviceProfile> arrayList = new ArrayList<>();
        try {
            xml = resources.getXml(i);
            int depth = xml.getDepth();
            while (true) {
                int next = xml.next();
                if ((next != 3 || xml.getDepth() > depth) && next != 1) {
                    if (next == 2 && "profile".equals(xml.getName())) {
                        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                        String str = "";
                        int i2 = 0;
                        int i3 = 0;
                        for (int i4 = 0; i4 < asAttributeSet.getAttributeCount(); i4++) {
                            String attributeName = asAttributeSet.getAttributeName(i4);
                            if (attributeName != null) {
                                char c = 65535;
                                switch (attributeName.hashCode()) {
                                    case -2000982401:
                                        if (attributeName.equals("numRows")) {
                                            c = 3;
                                            break;
                                        }
                                        break;
                                    case -1853251209:
                                        if (attributeName.equals("numColumns")) {
                                            c = 4;
                                            break;
                                        }
                                        break;
                                    case -32286157:
                                        if (attributeName.equals("minWidthDps")) {
                                            c = 1;
                                            break;
                                        }
                                        break;
                                    case 3373707:
                                        if (attributeName.equals("name")) {
                                            c = 0;
                                            break;
                                        }
                                        break;
                                    case 1731595406:
                                        if (attributeName.equals("minHeightDps")) {
                                            c = 2;
                                            break;
                                        }
                                        break;
                                }
                                if (c == 0) {
                                    String attributeValue = asAttributeSet.getAttributeValue(i4);
                                    if (attributeValue != null) {
                                        str = attributeValue;
                                    }
                                } else if (c == 3) {
                                    i2 = asAttributeSet.getAttributeIntValue(i4, 0);
                                } else if (c == 4) {
                                    i3 = asAttributeSet.getAttributeIntValue(i4, 0);
                                }
                            }
                        }
                        InvariantDeviceProfile invariantDeviceProfile = new InvariantDeviceProfile();
                        invariantDeviceProfile.displayName = str;
                        invariantDeviceProfile.numRows = i2;
                        invariantDeviceProfile.numColumns = i3;
                        arrayList.add(invariantDeviceProfile);
                    }
                }
            }
            if (xml != null) {
                xml.close();
            }
            return arrayList;
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static String getGridName(int i, int i2) {
        return i + "_by_" + i2;
    }
}
