package com.motorola.styles.model.providers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import com.motorola.personalize.BuildConfig;
import com.motorola.styles.C1087R;
import com.motorola.styles.ReflectUtils;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.OnlineFontOption;
import com.motorola.styles.model.OnlineFontService;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.OverlayManagerCompat;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FontOptionsProvider extends StyleOptionsProvider<FontOption> {
    public static final String CUSTOM_OVERLAY = "com.android.theme.font.customizesource";
    public static final String PARAM_NO_STORE = "noStore";
    private static final String TAG = "FontOptionsProvider";
    private FontOption mDefaultOption;

    public FontOptionsProvider(Context context, OverlayManagerCompat overlayManagerCompat) {
        super(context, overlayManagerCompat, ResourceConstants.OVERLAY_CATEGORY_FONT);
    }

    /* access modifiers changed from: protected */
    public void loadOptions(Bundle bundle) {
        Typeface typeface;
        String str;
        addDefault();
        Iterator it = this.mOverlayPackages.iterator();
        while (true) {
            Typeface typeface2 = null;
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            try {
                Resources overlayResources = getOverlayResources(str2);
                String fontFamily = getFontFamily(str2, overlayResources, ResourceConstants.CONFIG_HEADLINE_FONT_FAMILY);
                if (fontFamily != null) {
                    typeface = createTypefaceByName(fontFamily, 0);
                    if (typeface == null) {
                        typeface = Typeface.create(fontFamily, 0);
                    }
                } else {
                    typeface = null;
                }
                String fontFamily2 = getFontFamily(str2, overlayResources, ResourceConstants.CONFIG_BODY_FONT_FAMILY);
                if (fontFamily2 != null && (typeface2 = createTypefaceByName(fontFamily2, 0)) == null) {
                    typeface2 = Typeface.create(fontFamily2, 0);
                }
                if (typeface != null || typeface2 != null) {
                    if (typeface == null) {
                        typeface = typeface2;
                    } else if (typeface2 == null) {
                        typeface2 = typeface;
                    }
                    PackageManager packageManager = this.mContext.getPackageManager();
                    int identifier = this.mContext.getResources().getIdentifier(str2.toLowerCase().replace('.', '_'), "string", BuildConfig.APPLICATION_ID);
                    if (identifier != 0) {
                        str = this.mContext.getString(identifier);
                    } else {
                        str = packageManager.getApplicationInfo(str2, 0).loadLabel(packageManager).toString();
                    }
                    this.mOptions.add(new FontOption(str2, str, typeface, typeface2));
                }
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                Log.w(TAG, String.format("Couldn't load font overlay %s, will skip it", new Object[]{str2}), e);
            }
        }
        if (OnlineFontService.getInstance().isEnabled()) {
            for (OnlineFontService.FontInfo next : OnlineFontService.getInstance().getInstalledFontList()) {
                OnlineFontOption onlineFontOption = new OnlineFontOption(next.packageName, next.name, Typeface.createFromFile(next.file), next.file);
                onlineFontOption.setUninstallable(next.uninstallable);
                this.mOptions.add(onlineFontOption);
            }
            if (bundle.getBoolean(PARAM_NO_STORE, true)) {
                FontOption fontOption = new FontOption("", this.mContext.getString(C1087R.string.app_store_option_name), (Typeface) null, (Typeface) null);
                fontOption.setPlaceHolder(true);
                this.mOptions.add(fontOption);
            }
        }
        StylesUtilities.sortOrder(getContext(), this.mOptions, StylesUtilities.PRC_BUILD ? C1087R.array.fonts_order_prc : C1087R.array.fonts_order_row);
    }

    private void addDefault() {
        Resources system = Resources.getSystem();
        Typeface createTypefaceByName = createTypefaceByName(system.getString(system.getIdentifier(ResourceConstants.CONFIG_BODY_FONT_FAMILY, "string", ResourceConstants.ANDROID_PACKAGE)), 0);
        if (createTypefaceByName == null) {
            createTypefaceByName = Typeface.create(system.getString(system.getIdentifier(ResourceConstants.CONFIG_HEADLINE_FONT_FAMILY, "string", ResourceConstants.ANDROID_PACKAGE)), 0);
        }
        Typeface createTypefaceByName2 = createTypefaceByName(system.getString(system.getIdentifier(ResourceConstants.CONFIG_BODY_FONT_FAMILY, "string", ResourceConstants.ANDROID_PACKAGE)), 0);
        if (createTypefaceByName2 == null) {
            createTypefaceByName2 = Typeface.create(system.getString(system.getIdentifier(ResourceConstants.CONFIG_BODY_FONT_FAMILY, "string", ResourceConstants.ANDROID_PACKAGE)), 0);
        }
        FontOption fontOption = new FontOption("Default", this.mContext.getString(C1087R.string.default_option_name), createTypefaceByName, createTypefaceByName2);
        this.mOptions.add(fontOption);
        this.mDefaultOption = fontOption;
    }

    public static Typeface createTypefaceByName(String str, int i) {
        return (Typeface) ReflectUtils.invokeMethod(Typeface.class, (Object) null, "createTypefaceByName", new Class[]{String.class, Integer.TYPE}, new Object[]{str, Integer.valueOf(i)});
    }

    private String getFontFamily(String str, Resources resources, String str2) {
        try {
            return resources.getString(resources.getIdentifier(str2, "string", str));
        } catch (Exception e) {
            Log.d(TAG, "getFontFamily error: ", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void loadExcludedOverlayPackage(List<String> list) {
        list.addAll(Arrays.asList(getContext().getResources().getStringArray(C1087R.array.excluded_font_overlay_packages)));
    }

    public void applyFont(FontOption fontOption) {
        try {
            OnlineFontService.getInstance().onApplyFont(fontOption.getValue());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public FontOption getAppliedOption() {
        String enabledPackageName = this.mOverlayManager.getEnabledPackageName(ResourceConstants.ANDROID_PACKAGE, ResourceConstants.OVERLAY_CATEGORY_FONT);
        if ("com.android.theme.font.customizesource".equals(enabledPackageName)) {
            enabledPackageName = OnlineFontService.getInstance().getCurrentOnlineFontValue();
        }
        for (Option option : this.mOptions) {
            if (option.getValue().equals(enabledPackageName)) {
                return (FontOption) option;
            }
        }
        return this.mDefaultOption;
    }
}
