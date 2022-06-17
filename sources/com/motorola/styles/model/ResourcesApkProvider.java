package com.motorola.styles.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.icons.IconPack;

public abstract class ResourcesApkProvider {
    private static final String TAG = "ResourcesApkProvider";
    protected final Context mContext;
    protected final Resources mStubApkResources;
    protected final String mStubPackageName;

    public ResourcesApkProvider(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mStubPackageName = str;
        Resources resources = null;
        if (TextUtils.isEmpty(str)) {
            this.mStubApkResources = null;
            return;
        }
        try {
            PackageManager packageManager = applicationContext.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 1048704);
            if (applicationInfo != null) {
                resources = packageManager.getResourcesForApplication(applicationInfo);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w(TAG, String.format("Stub APK for %s not found.", new Object[]{this.mStubPackageName}));
        } catch (Throwable th) {
            this.mStubApkResources = null;
            throw th;
        }
        this.mStubApkResources = resources;
    }

    /* access modifiers changed from: protected */
    public String[] getItemsFromStub(String str) {
        return this.mStubApkResources.getStringArray(this.mStubApkResources.getIdentifier(str, "array", this.mStubPackageName));
    }

    /* access modifiers changed from: protected */
    public String getItemStringFromStub(String str, String str2) {
        return this.mStubApkResources.getString(this.mStubApkResources.getIdentifier(String.format("%s%s", new Object[]{str, str2}), "string", this.mStubPackageName));
    }

    /* access modifiers changed from: protected */
    public Drawable getItemDrawableFromStub(String str, String str2) {
        return this.mStubApkResources.getDrawable(this.mStubApkResources.getIdentifier(String.format("%s%s", new Object[]{str, str2}), IconPack.DRAWABLE, this.mStubPackageName), (Resources.Theme) null);
    }

    public boolean isAvailable() {
        return this.mStubApkResources != null;
    }
}
