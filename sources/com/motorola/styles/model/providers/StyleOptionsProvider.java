package com.motorola.styles.model.providers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.OverlayManagerCompat;
import java.util.ArrayList;
import java.util.List;

public abstract class StyleOptionsProvider<T extends Option> {
    protected final Context mContext;
    private final List<String> mExcludedOverlayPackages;
    protected final List<T> mOptions;
    protected OverlayManagerCompat mOverlayManager;
    protected final List<String> mOverlayPackages;

    public abstract Option getAppliedOption();

    /* access modifiers changed from: protected */
    public void loadExcludedOverlayPackage(List<String> list) {
    }

    /* access modifiers changed from: protected */
    public abstract void loadOptions(Bundle bundle);

    public StyleOptionsProvider(Context context) {
        this.mOverlayPackages = new ArrayList();
        this.mOptions = new ArrayList();
        this.mExcludedOverlayPackages = new ArrayList();
        this.mContext = context.getApplicationContext();
    }

    public StyleOptionsProvider(Context context, OverlayManagerCompat overlayManagerCompat, String... strArr) {
        ArrayList arrayList = new ArrayList();
        this.mOverlayPackages = arrayList;
        this.mOptions = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.mExcludedOverlayPackages = arrayList2;
        this.mContext = context.getApplicationContext();
        this.mOverlayManager = overlayManagerCompat;
        loadExcludedOverlayPackage(arrayList2);
        arrayList.clear();
        for (String overlayPackagesForCategory : strArr) {
            for (String next : overlayManagerCompat.getOverlayPackagesForCategory(overlayPackagesForCategory, UserHandle.myUserId(), ResourceConstants.getPackagesToOverlay(this.mContext))) {
                if (!isExcludedOverlayPackage(next)) {
                    this.mOverlayPackages.add(next);
                }
            }
        }
    }

    public final void loadOptions(boolean z) {
        loadOptions(z, (Bundle) null);
    }

    public final synchronized void loadOptions(boolean z, Bundle bundle) {
        if (z) {
            this.mOptions.clear();
        } else if (this.mOptions.size() > 0) {
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        loadOptions(bundle);
    }

    public Context getContext() {
        return this.mContext;
    }

    public List<String> getOverlayPackages() {
        return this.mOverlayPackages;
    }

    public List<T> getOptions() {
        return this.mOptions;
    }

    /* access modifiers changed from: protected */
    public Resources getOverlayResources(String str) throws PackageManager.NameNotFoundException {
        return this.mContext.getPackageManager().getResourcesForApplication(str);
    }

    public static String getStringParam(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        return bundle.getString(str, (String) null);
    }

    /* access modifiers changed from: protected */
    public boolean isExcludedOverlayPackage(String str) {
        return this.mExcludedOverlayPackages.contains(str);
    }

    public static Bundle newParams(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(str, z);
        return bundle;
    }
}
