package com.motorola.personalize.app;

import android.content.pm.LauncherApps;
import android.os.UserHandle;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.icons.IconPack;
import com.android.launcher3.icons.IconPackManager;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.motorola.personalize.model.IconPacksHelper;
import com.motorola.personalize.util.Executors;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J+\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fJ+\u0010\u0010\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000f¨\u0006\u0011"}, mo15462d2 = {"com/motorola/personalize/app/IconPacksActivity$setAppUnstallCallback$1", "Landroid/content/pm/LauncherApps$Callback;", "onPackageAdded", "", "packageName", "", "user", "Landroid/os/UserHandle;", "onPackageChanged", "onPackageRemoved", "onPackagesAvailable", "packageNames", "", "replacing", "", "([Ljava/lang/String;Landroid/os/UserHandle;Z)V", "onPackagesUnavailable", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: IconPacksActivity.kt */
public final class IconPacksActivity$setAppUnstallCallback$1 extends LauncherApps.Callback {
    final /* synthetic */ IconPacksActivity this$0;

    public void onPackageChanged(String str, UserHandle userHandle) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(userHandle, "user");
    }

    public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
        Intrinsics.checkNotNullParameter(strArr, "packageNames");
        Intrinsics.checkNotNullParameter(userHandle, "user");
    }

    public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
        Intrinsics.checkNotNullParameter(strArr, "packageNames");
        Intrinsics.checkNotNullParameter(userHandle, "user");
    }

    IconPacksActivity$setAppUnstallCallback$1(IconPacksActivity iconPacksActivity) {
        this.this$0 = iconPacksActivity;
    }

    public void onPackageRemoved(String str, UserHandle userHandle) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(userHandle, "user");
        if (this.this$0.mIconPacksAdapter != null) {
            Executors.INSTANCE.getUI_HELPER_EXECUTOR().execute(new Runnable() {
                public final void run() {
                    IconPacksActivity$setAppUnstallCallback$1.m64onPackageRemoved$lambda2(IconPacksActivity.this);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onPackageRemoved$lambda-2  reason: not valid java name */
    public static final void m64onPackageRemoved$lambda2(IconPacksActivity iconPacksActivity) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        IconPacksHelper access$getMIconPacksHelper$p = iconPacksActivity.mIconPacksHelper;
        Intrinsics.checkNotNull(access$getMIconPacksHelper$p);
        if (!access$getMIconPacksHelper$p.hasIconPackApplied()) {
            IconPacksHelper access$getMIconPacksHelper$p2 = iconPacksActivity.mIconPacksHelper;
            Intrinsics.checkNotNull(access$getMIconPacksHelper$p2);
            access$getMIconPacksHelper$p2.applyAndSendBroadcast(IconPack.DEFAULT_ICON_PACK.getPackageName());
        }
        List<IconPack> availableIconPacks = IconPackManager.getAvailableIconPacks(iconPacksActivity);
        if (availableIconPacks != null) {
            iconPacksActivity.runOnUiThread(new Runnable(availableIconPacks) {
                public final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    IconPacksActivity$setAppUnstallCallback$1.m65onPackageRemoved$lambda2$lambda1(IconPacksActivity.this, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onPackageRemoved$lambda-2$lambda-1  reason: not valid java name */
    public static final void m65onPackageRemoved$lambda2$lambda1(IconPacksActivity iconPacksActivity, List list) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        Intrinsics.checkNotNullParameter(list, "$iconPacks");
        IconPacksAdapter access$getMIconPacksAdapter$p = iconPacksActivity.mIconPacksAdapter;
        if (access$getMIconPacksAdapter$p != null) {
            access$getMIconPacksAdapter$p.resetDatas(list);
        }
        RecyclerView access$getMRecyclerView$p = iconPacksActivity.mRecyclerView;
        if (access$getMRecyclerView$p != null) {
            access$getMRecyclerView$p.post(new Runnable() {
                public final void run() {
                    IconPacksActivity$setAppUnstallCallback$1.m66onPackageRemoved$lambda2$lambda1$lambda0(IconPacksActivity.this);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onPackageRemoved$lambda-2$lambda-1$lambda-0  reason: not valid java name */
    public static final void m66onPackageRemoved$lambda2$lambda1$lambda0(IconPacksActivity iconPacksActivity) {
        ExtendedFloatingActionButton access$getMDownloadPrompt$p;
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        RecyclerView access$getMRecyclerView$p = iconPacksActivity.mRecyclerView;
        boolean z = false;
        if (access$getMRecyclerView$p != null && !access$getMRecyclerView$p.canScrollVertically(-1)) {
            z = true;
        }
        if (z && (access$getMDownloadPrompt$p = iconPacksActivity.mDownloadPrompt) != null) {
            access$getMDownloadPrompt$p.extend();
        }
    }

    public void onPackageAdded(String str, UserHandle userHandle) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(userHandle, "user");
        if (this.this$0.mIconPacksAdapter != null) {
            Executors.INSTANCE.getUI_HELPER_EXECUTOR().execute(new Runnable() {
                public final void run() {
                    IconPacksActivity$setAppUnstallCallback$1.m62onPackageAdded$lambda4(IconPacksActivity.this);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onPackageAdded$lambda-4  reason: not valid java name */
    public static final void m62onPackageAdded$lambda4(IconPacksActivity iconPacksActivity) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        List<IconPack> availableIconPacks = IconPackManager.getAvailableIconPacks(iconPacksActivity);
        if (availableIconPacks != null) {
            iconPacksActivity.runOnUiThread(new Runnable(availableIconPacks) {
                public final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    IconPacksActivity$setAppUnstallCallback$1.m63onPackageAdded$lambda4$lambda3(IconPacksActivity.this, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onPackageAdded$lambda-4$lambda-3  reason: not valid java name */
    public static final void m63onPackageAdded$lambda4$lambda3(IconPacksActivity iconPacksActivity, List list) {
        Intrinsics.checkNotNullParameter(iconPacksActivity, "this$0");
        Intrinsics.checkNotNullParameter(list, "$iconPacks");
        IconPacksAdapter access$getMIconPacksAdapter$p = iconPacksActivity.mIconPacksAdapter;
        if (access$getMIconPacksAdapter$p != null) {
            access$getMIconPacksAdapter$p.resetDatas(list);
        }
    }
}
