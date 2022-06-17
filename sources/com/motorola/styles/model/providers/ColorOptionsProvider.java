package com.motorola.styles.model.providers;

import android.app.WallpaperColors;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.graphics.PathParser;
import com.android.launcher3.icons.IconPack;
import com.android.systemui.monet.ColorScheme;
import com.motorola.styles.C1087R;
import com.motorola.styles.ReflectUtils;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.ColorOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.OverlayManagerCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ColorOptionsProvider extends StyleOptionsProvider<ColorOption> {
    public static final Pattern COLOR_SPLITTER = Pattern.compile(",");
    public static final String PARAM_ICON_PACKAGE = "iconPackage";
    public static final String PARAM_WALLPAPER = "wallpaper";
    private static final String TAG = "ColorOptionsProvider";
    private ColorOption mDefaultOption;
    private final String mDefaultThemePackage;

    public ColorOptionsProvider(Context context, OverlayManagerCompat overlayManagerCompat) {
        super(context, overlayManagerCompat, "android.theme.customization.accent_color");
        List<String> overlayPackagesForCategory = overlayManagerCompat.getOverlayPackagesForCategory(ResourceConstants.OVERLAY_CATEGORY_ANDROID_THEME, UserHandle.myUserId(), ResourceConstants.ANDROID_PACKAGE);
        this.mDefaultThemePackage = overlayPackagesForCategory.isEmpty() ? null : overlayPackagesForCategory.get(0);
    }

    /* access modifiers changed from: protected */
    public void loadExcludedOverlayPackage(List<String> list) {
        list.addAll(Arrays.asList(getContext().getResources().getStringArray(C1087R.array.excluded_color_overlay_packages)));
    }

    /* access modifiers changed from: protected */
    public void loadOptions(Bundle bundle) {
        loadPreloadColors();
        loadWallpaperColors(bundle);
        loadPickerColors();
    }

    private void loadPreloadColors() {
        for (String split : this.mContext.getResources().getStringArray(C1087R.array.preload_colors)) {
            String[] split2 = COLOR_SPLITTER.split(split);
            this.mOptions.add(new ColorOption(split2[0], split2[1], split2[2], split2[3]));
        }
    }

    private void loadWallpaperColors(Bundle bundle) {
        Bitmap bitmap = (Bitmap) bundle.get("wallpaper");
        if (bitmap != null) {
            List<Integer> wallpaperColorSeeds = getWallpaperColorSeeds(bitmap);
            int min = Math.min(4, wallpaperColorSeeds.size());
            for (int i = 0; i < min; i++) {
                this.mOptions.add(new ColorOption(wallpaperColorSeeds.get(i).intValue(), i));
            }
        }
    }

    private static List<Integer> getWallpaperColorSeeds(Bitmap bitmap) {
        return ColorScheme.getSeedColors(WallpaperColors.fromBitmap(bitmap));
    }

    public static Boolean isDarkModeEnabled(Context context) {
        return Boolean.valueOf(getUiMode(context) == 32);
    }

    public static int getUiMode(Context context) {
        return context.getResources().getConfiguration().uiMode & 48;
    }

    private void loadPickerColors() {
        try {
            for (Object invokeMethod : (List) ReflectUtils.invokeMethod(ReflectUtils.invokeMethod(ReflectUtils.invokeMethod("com.motorola.personalize.data.AppDatabaseHelper", "getInstance", (Class<?>[]) null, (Object[]) null), "getDao", (Class<?>[]) null, (Object[]) null), "loadAllOptions", (Class<?>[]) null, (Object[]) null)) {
                this.mOptions.add(new ColorOption(((Integer) ReflectUtils.invokeMethod(invokeMethod, "getLightColor", (Class<?>[]) null, (Object[]) null)).intValue()));
            }
        } catch (Exception e) {
            Log.w(TAG, "loadCustomColors error", e);
        }
    }

    private void onLoadOptions(String str) {
        if (TextUtils.isEmpty(str)) {
            str = ResourceConstants.ANDROID_PACKAGE;
        }
        ArrayList arrayList = new ArrayList();
        for (String loadIconPreviewDrawable : ResourceConstants.ICONS_FOR_PREVIEW) {
            try {
                arrayList.add(loadIconPreviewDrawable(loadIconPreviewDrawable, str));
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
                Log.w(TAG, String.format("Couldn't load icon in %s for color preview, will skip it", new Object[]{str}));
            }
        }
        addDefault(arrayList);
        for (String str2 : this.mOverlayPackages) {
            try {
                Resources overlayResources = getOverlayResources(str2);
                int color = overlayResources.getColor(overlayResources.getIdentifier(ResourceConstants.ACCENT_COLOR_LIGHT_NAME, "color", str2), (Resources.Theme) null);
                int color2 = overlayResources.getColor(overlayResources.getIdentifier(ResourceConstants.ACCENT_COLOR_DARK_NAME, "color", str2), (Resources.Theme) null);
                PackageManager packageManager = this.mContext.getPackageManager();
                ColorOption colorOption = new ColorOption(str2, packageManager.getApplicationInfo(str2, 0).loadLabel(packageManager).toString(), color, color2);
                colorOption.setPreviewIcons(arrayList);
                this.mOptions.add(colorOption);
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused2) {
                Log.w(TAG, String.format("Couldn't load color overlay %s, will skip it", new Object[]{str2}));
            }
        }
        StylesUtilities.sortOrder(getContext(), this.mOptions, C1087R.array.colors_order);
    }

    private void addDefault(List<Drawable> list) {
        int i;
        int i2;
        Resources system = Resources.getSystem();
        try {
            Resources overlayResources = getOverlayResources(this.mDefaultThemePackage);
            i = overlayResources.getColor(overlayResources.getIdentifier(ResourceConstants.ACCENT_COLOR_LIGHT_NAME, "color", this.mDefaultThemePackage), (Resources.Theme) null);
            i2 = overlayResources.getColor(overlayResources.getIdentifier(ResourceConstants.ACCENT_COLOR_DARK_NAME, "color", this.mDefaultThemePackage), (Resources.Theme) null);
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
            Log.d(TAG, "Didn't find default color, will use system option", e);
            i = system.getColor(system.getIdentifier(ResourceConstants.ACCENT_COLOR_LIGHT_NAME, "color", ResourceConstants.ANDROID_PACKAGE), (Resources.Theme) null);
            i2 = system.getColor(system.getIdentifier(ResourceConstants.ACCENT_COLOR_DARK_NAME, "color", ResourceConstants.ANDROID_PACKAGE), (Resources.Theme) null);
        }
        ColorOption colorOption = new ColorOption("Default", this.mContext.getString(C1087R.string.default_option_name), i, i2);
        colorOption.setPreviewIcons(list);
        this.mOptions.add(colorOption);
        this.mDefaultOption = colorOption;
    }

    private Drawable loadIconPreviewDrawable(String str, String str2) throws PackageManager.NameNotFoundException, Resources.NotFoundException {
        Resources overlayResources = getOverlayResources(str2);
        return overlayResources.getDrawable(overlayResources.getIdentifier(str, IconPack.DRAWABLE, str2), (Resources.Theme) null);
    }

    private Drawable loadShape(String str) {
        String str2;
        try {
            str2 = ResourceConstants.getIconMask(getOverlayResources(str), str);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "Couldn't load shape icon for " + str + ", skipping.", e);
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new PathShape(PathParser.createPathFromPathData(str2), 100.0f, 100.0f));
        shapeDrawable.setIntrinsicHeight(100);
        shapeDrawable.setIntrinsicWidth(100);
        return shapeDrawable;
    }

    public ColorOption getAppliedOption() {
        String enabledPackageName = this.mOverlayManager.getEnabledPackageName(ResourceConstants.ANDROID_PACKAGE, "android.theme.customization.accent_color");
        for (Option option : this.mOptions) {
            if (option.getValue().equals(enabledPackageName)) {
                return (ColorOption) option;
            }
        }
        return this.mDefaultOption;
    }
}
