package com.motorola.styles.model;

import android.content.Context;
import android.content.om.OverlayInfo;
import android.content.om.OverlayManager;
import android.os.UserHandle;
import com.motorola.styles.ResourceConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OverlayManagerCompat {
    private Map<Integer, Map<String, List<OverlayInfo>>> mOverlayByUser;
    private final OverlayManager mOverlayManager;
    private final String[] mTargetPackages;

    public OverlayManagerCompat(Context context) {
        this.mOverlayManager = (OverlayManager) context.getSystemService(OverlayManager.class);
        this.mTargetPackages = ResourceConstants.getPackagesToOverlay(context);
    }

    public boolean isAvailable() {
        return this.mOverlayManager != null;
    }

    public boolean setEnabledExclusiveInCategory(String str, int i) {
        this.mOverlayManager.setEnabledExclusiveInCategory(str, UserHandle.of(i));
        return true;
    }

    public boolean disableOverlay(String str, int i) {
        this.mOverlayManager.setEnabled(str, false, UserHandle.of(i));
        return true;
    }

    public String getEnabledPackageName(String str, String str2) {
        for (OverlayInfo next : getOverlayInfosForTarget(str, UserHandle.myUserId())) {
            if (str2.equals(next.getCategory()) && next.isEnabled()) {
                return next.getPackageName();
            }
        }
        return null;
    }

    public Map<String, String> getEnabledOverlaysForTargets(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String addAllEnabledOverlaysForTarget : strArr) {
            addAllEnabledOverlaysForTarget(hashMap, addAllEnabledOverlaysForTarget);
        }
        return hashMap;
    }

    public List<String> getOverlayPackagesForCategory(String str, int i, String... strArr) {
        ArrayList arrayList = new ArrayList();
        ensureCategoryMapForUser(i);
        for (String orDefault : strArr) {
            for (OverlayInfo overlayInfo : (List) this.mOverlayByUser.get(Integer.valueOf(i)).getOrDefault(orDefault, Collections.emptyList())) {
                if (str.equals(overlayInfo.getCategory())) {
                    arrayList.add(overlayInfo.getPackageName());
                }
            }
        }
        return arrayList;
    }

    private void ensureCategoryMapForUser(int i) {
        if (this.mOverlayByUser == null) {
            this.mOverlayByUser = new HashMap();
        }
        if (!this.mOverlayByUser.containsKey(Integer.valueOf(i))) {
            HashMap hashMap = new HashMap();
            for (String str : this.mTargetPackages) {
                hashMap.put(str, getOverlayInfosForTarget(str, i));
            }
            this.mOverlayByUser.put(Integer.valueOf(i), hashMap);
        }
    }

    private List<OverlayInfo> getOverlayInfosForTarget(String str, int i) {
        return this.mOverlayManager.getOverlayInfosForTarget(str, UserHandle.of(i));
    }

    private void addAllEnabledOverlaysForTarget(Map<String, String> map, String str) {
        for (OverlayInfo next : getOverlayInfosForTarget(str, UserHandle.myUserId())) {
            if (next.isEnabled()) {
                map.put(next.getCategory(), next.getPackageName());
            }
        }
    }
}
