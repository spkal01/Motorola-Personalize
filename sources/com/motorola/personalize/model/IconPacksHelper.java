package com.motorola.personalize.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.android.launcher3.icons.IconPack;
import com.android.launcher3.icons.IconPackManager;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.model.IconPacksHelper;
import com.motorola.personalize.util.Executors;
import com.motorola.personalize.util.Utilities;
import com.motorola.personalize.viewmodel.StyleViewModel;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0003\u001c\u001d\u001eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u000e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\"\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u001a\u001a\u00020\u00182\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u001b\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, mo15462d2 = {"Lcom/motorola/personalize/model/IconPacksHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "defaultPackCoverDrawable", "Landroid/graphics/drawable/Drawable;", "getDefaultPackCoverDrawable", "()Landroid/graphics/drawable/Drawable;", "mContext", "apply", "", "packageName", "", "applyAndSendBroadcast", "getIconPackTitle", "iconPack", "Lcom/android/launcher3/icons/IconPack;", "getOtherPackCoverDrawables", "imageView", "Landroid/widget/ImageView;", "callback", "Lcom/motorola/personalize/model/IconPacksHelper$IconPackUiCallback;", "hasIconPackApplied", "", "isDefaultIconPack", "isIconPackAppied", "uninstallAPK", "Companion", "IconPackRunnable", "IconPackUiCallback", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: IconPacksHelper.kt */
public final class IconPacksHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ICON_PACK_CHANGED = "com.motorola.launcher3.ICON_PACK_CHANGED";
    /* access modifiers changed from: private */
    public static IconPacksHelper mIconPacksHelper;
    private final Context mContext;

    @Metadata(mo15461d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, mo15462d2 = {"Lcom/motorola/personalize/model/IconPacksHelper$IconPackUiCallback;", "", "runOnUiThread", "", "imageView", "Landroid/widget/ImageView;", "drawable", "Landroid/graphics/drawable/Drawable;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: IconPacksHelper.kt */
    public interface IconPackUiCallback {
        void runOnUiThread(ImageView imageView, Drawable drawable);
    }

    public /* synthetic */ IconPacksHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @JvmStatic
    public static final IconPacksHelper getInstance(Context context) {
        return Companion.getInstance(context);
    }

    private IconPacksHelper(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        this.mContext = applicationContext;
    }

    public final void apply(String str) {
        if (TextUtils.isEmpty(str)) {
            IconPack.logd("iconPack is empty", new boolean[0]);
        } else {
            IconPackManager.saveAppliedIconPack(this.mContext, str);
        }
    }

    public final boolean isIconPackAppied(IconPack iconPack) {
        if (iconPack == null) {
            IconPack.logd("iconPack == null", new boolean[0]);
            return false;
        }
        return TextUtils.equals(iconPack.getPackageName(), IconPackManager.getAppliedIconPack(this.mContext));
    }

    public final Drawable getDefaultPackCoverDrawable() {
        return Utilities.isLandscape(this.mContext.getResources()) ? this.mContext.getDrawable(C1057R.mipmap.card_horizontal) : this.mContext.getDrawable(C1057R.mipmap.card_vertical);
    }

    public final void getOtherPackCoverDrawables(IconPack iconPack, ImageView imageView, IconPackUiCallback iconPackUiCallback) {
        Intrinsics.checkNotNullParameter(iconPack, IconPack.ICON_PACK);
        Executors.INSTANCE.getUI_HELPER_EXECUTOR().submit(new IconPackRunnable(this.mContext, iconPack, imageView, iconPackUiCallback));
    }

    public final String getIconPackTitle(IconPack iconPack) {
        Intrinsics.checkNotNullParameter(iconPack, IconPack.ICON_PACK);
        if (isDefaultIconPack(iconPack)) {
            String string = this.mContext.getString(C1057R.string.icon_default_pack_title);
            Intrinsics.checkNotNullExpressionValue(string, "{\n            mContext.getString(R.string.icon_default_pack_title)\n        }");
            return string;
        }
        String appName = iconPack.getAppName();
        Intrinsics.checkNotNullExpressionValue(appName, "{\n            iconPack.appName\n        }");
        return appName;
    }

    public final boolean isDefaultIconPack(IconPack iconPack) {
        Intrinsics.checkNotNullParameter(iconPack, IconPack.ICON_PACK);
        return iconPack == IconPack.DEFAULT_ICON_PACK;
    }

    public final void uninstallAPK(String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent("android.intent.action.DELETE", Uri.fromParts("package", str, (String) null));
            intent.setFlags(268435456);
            this.mContext.startActivity(intent);
        }
    }

    public final boolean hasIconPackApplied() {
        for (IconPack isIconPackAppied : IconPackManager.getAvailableIconPacks(this.mContext)) {
            if (isIconPackAppied(isIconPackAppied)) {
                return true;
            }
        }
        return false;
    }

    @Metadata(mo15461d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo15462d2 = {"Lcom/motorola/personalize/model/IconPacksHelper$IconPackRunnable;", "Ljava/lang/Runnable;", "mContext", "Landroid/content/Context;", "mIconPack", "Lcom/android/launcher3/icons/IconPack;", "mImageView", "Landroid/widget/ImageView;", "mCallback", "Lcom/motorola/personalize/model/IconPacksHelper$IconPackUiCallback;", "(Landroid/content/Context;Lcom/android/launcher3/icons/IconPack;Landroid/widget/ImageView;Lcom/motorola/personalize/model/IconPacksHelper$IconPackUiCallback;)V", "run", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: IconPacksHelper.kt */
    public static final class IconPackRunnable implements Runnable {
        private final IconPackUiCallback mCallback;
        private final Context mContext;
        private final IconPack mIconPack;
        private final ImageView mImageView;

        public IconPackRunnable(Context context, IconPack iconPack, ImageView imageView, IconPackUiCallback iconPackUiCallback) {
            Intrinsics.checkNotNullParameter(context, "mContext");
            Intrinsics.checkNotNullParameter(iconPack, "mIconPack");
            this.mContext = context;
            this.mIconPack = iconPack;
            this.mImageView = imageView;
            this.mCallback = iconPackUiCallback;
        }

        public void run() {
            ImageView imageView;
            Drawable preview = this.mIconPack.getPreview(Utilities.isLandscape(this.mContext.getResources()) ? IconPack.LANDSCAPE : IconPack.VERTICAL);
            String packageName = this.mIconPack.getPackageName();
            if (this.mCallback != null && (imageView = this.mImageView) != null && preview != null && (imageView.getTag() instanceof String)) {
                Object tag = this.mImageView.getTag();
                Objects.requireNonNull(tag, "null cannot be cast to non-null type kotlin.String");
                if (TextUtils.equals((String) tag, packageName)) {
                    Executors.INSTANCE.getMAIN_EXECUTOR().submit(new Runnable(preview) {
                        public final /* synthetic */ Drawable f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            IconPacksHelper.IconPackRunnable.m102run$lambda0(IconPacksHelper.IconPackRunnable.this, this.f$1);
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: run$lambda-0  reason: not valid java name */
        public static final void m102run$lambda0(IconPackRunnable iconPackRunnable, Drawable drawable) {
            Intrinsics.checkNotNullParameter(iconPackRunnable, "this$0");
            iconPackRunnable.mCallback.runOnUiThread(iconPackRunnable.mImageView, drawable);
        }
    }

    public final void applyAndSendBroadcast(String str) {
        apply(str);
        this.mContext.sendBroadcast(new Intent(ICON_PACK_CHANGED));
    }

    @Metadata(mo15461d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, mo15462d2 = {"Lcom/motorola/personalize/model/IconPacksHelper$Companion;", "", "()V", "ICON_PACK_CHANGED", "", "mIconPacksHelper", "Lcom/motorola/personalize/model/IconPacksHelper;", "getInstance", "context", "Landroid/content/Context;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: IconPacksHelper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final IconPacksHelper getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (IconPacksHelper.mIconPacksHelper == null) {
                synchronized (StyleViewModel.class) {
                    if (IconPacksHelper.mIconPacksHelper == null) {
                        Companion companion = IconPacksHelper.Companion;
                        IconPacksHelper.mIconPacksHelper = new IconPacksHelper(context, (DefaultConstructorMarker) null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return IconPacksHelper.mIconPacksHelper;
        }
    }
}
