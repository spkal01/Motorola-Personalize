package com.motorola.styles.model.providers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.graphics.PathParser;
import com.motorola.styles.C1087R;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.DynamicAdaptiveIconDrawable;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.OverlayManagerCompat;
import com.motorola.styles.model.ShapeOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class ShapeOptionsProvider extends StyleOptionsProvider<ShapeOption> {
    private static final String TAG = "ShapeOptionsProvider";
    private ShapeOption mDefaultOption;
    private final List<String> mShapePreviewIconPackages;
    private int mShapePreviewIconSize = this.mContext.getResources().getDimensionPixelSize(C1087R.dimen.shape_preview_icon_size);

    public ShapeOptionsProvider(Context context, OverlayManagerCompat overlayManagerCompat) {
        super(context, overlayManagerCompat, ResourceConstants.OVERLAY_CATEGORY_SHAPE);
        ArrayList arrayList = new ArrayList();
        this.mShapePreviewIconPackages = arrayList;
        arrayList.addAll(Arrays.asList(context.getResources().getStringArray(C1087R.array.icon_shape_preview_packages)));
    }

    /* access modifiers changed from: protected */
    public void loadExcludedOverlayPackage(List<String> list) {
        list.addAll(Arrays.asList(getContext().getResources().getStringArray(C1087R.array.excluded_shape_overlay_packages)));
    }

    /* access modifiers changed from: protected */
    public void loadOptions(Bundle bundle) {
        addDefault();
        for (String str : this.mOverlayPackages) {
            try {
                Path loadPath = loadPath(this.mContext.getPackageManager().getResourcesForApplication(str), str);
                ShapeDrawable createShapeDrawable = createShapeDrawable(loadPath);
                PackageManager packageManager = this.mContext.getPackageManager();
                String str2 = str;
                this.mOptions.add(new ShapeOption(str2, packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString(), loadPath, createShapeDrawable, getShapedIcons(loadPath)));
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                Log.w(TAG, String.format("Couldn't load shape overlay %s, will skip it", new Object[]{str}), e);
            }
        }
        StylesUtilities.sortOrder(getContext(), this.mOptions, C1087R.array.shapes_order);
    }

    private void addDefault() {
        Path loadPath = loadPath(Resources.getSystem(), ResourceConstants.ANDROID_PACKAGE);
        ShapeOption shapeOption = new ShapeOption("Default", this.mContext.getString(C1087R.string.default_option_name), loadPath, createShapeDrawable(loadPath), getShapedIcons(loadPath));
        this.mOptions.add(shapeOption);
        this.mDefaultOption = shapeOption;
    }

    private ShapeDrawable createShapeDrawable(Path path) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new PathShape(path, 100.0f, 100.0f));
        shapeDrawable.setIntrinsicHeight(this.mShapePreviewIconSize);
        shapeDrawable.setIntrinsicWidth(this.mShapePreviewIconSize);
        return shapeDrawable;
    }

    private List<Drawable> getShapedIcons(Path path) {
        ArrayList arrayList = new ArrayList();
        Pattern compile = Pattern.compile(",");
        for (String split : this.mShapePreviewIconPackages) {
            String[] split2 = compile.split(split);
            int length = split2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str = split2[i];
                try {
                    Drawable applicationIcon = this.mContext.getPackageManager().getApplicationIcon(str);
                    if (applicationIcon instanceof AdaptiveIconDrawable) {
                        AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) applicationIcon;
                        arrayList.add(new DynamicAdaptiveIconDrawable(adaptiveIconDrawable.getBackground(), adaptiveIconDrawable.getForeground(), path));
                        break;
                    }
                    i++;
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.d(TAG, "Couldn't find app " + str + ", won't use it for icon shape preview");
                }
            }
        }
        return arrayList;
    }

    private Path loadPath(Resources resources, String str) {
        String string = resources.getString(resources.getIdentifier(ResourceConstants.CONFIG_ICON_MASK, "string", str));
        if (!TextUtils.isEmpty(string)) {
            return PathParser.createPathFromPathData(string);
        }
        return null;
    }

    public ShapeOption getAppliedOption() {
        String enabledPackageName = this.mOverlayManager.getEnabledPackageName(ResourceConstants.ANDROID_PACKAGE, ResourceConstants.OVERLAY_CATEGORY_SHAPE);
        for (Option option : this.mOptions) {
            if (option.getValue().equals(enabledPackageName)) {
                return (ShapeOption) option;
            }
        }
        return this.mDefaultOption;
    }
}
