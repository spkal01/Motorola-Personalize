package com.motorola.styles.model;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.android.launcher3.icons.IconPack;
import com.motorola.styles.ImageUtils;
import com.motorola.styles.LogConfig;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.regex.Pattern;

public class WallpaperOption extends Option {
    public static final String DARK_FLAG = "_dark";
    public static final String DEFAULT_WALLPAPER = "";
    public static final Pattern PACKAGE_SEPARATOR = Pattern.compile("/");
    public static final String SMALL_FLAG = "_small";
    private static SoftReference<Bitmap> sCurrentWallpaperRef;
    private static SoftReference<Bitmap> sCurrentWallpaperThumbRef;
    private boolean mIsCurrentWallpaper = false;
    private String mWallpaperName;
    private String mWallpaperPackage;
    private SoftReference<Bitmap> mWallpaperRef;
    private SoftReference<Bitmap> mWallpaperThumbRef;

    public WallpaperOption(String str, String str2) {
        super(str, str2);
        if (LogConfig.DBG) {
            Log.d("Styles", "WallpaperOption: " + str + " | " + str2);
        }
        String[] split = PACKAGE_SEPARATOR.split(str);
        if (split.length == 2) {
            this.mWallpaperPackage = split[0];
            this.mWallpaperName = split[1];
        }
    }

    public Bitmap getWallpaperThumb(Context context) {
        if (this.mIsCurrentWallpaper) {
            Bitmap currentWallpaperThumb = getCurrentWallpaperThumb();
            if (currentWallpaperThumb != null) {
                return currentWallpaperThumb;
            }
            Bitmap createScaledBitmapByWidth = ImageUtils.createScaledBitmapByWidth(getWallpaper(context), 360, ImageUtils.ScalingLogic.FIT);
            sCurrentWallpaperThumbRef = new SoftReference<>(createScaledBitmapByWidth);
            return createScaledBitmapByWidth;
        }
        Bitmap wallpaperThumb = getWallpaperThumb();
        if (wallpaperThumb != null) {
            return wallpaperThumb;
        }
        Resources wallpaperRes = getWallpaperRes(context);
        Bitmap decodeResource = BitmapFactory.decodeResource(wallpaperRes, getWallpaperId(wallpaperRes, this.mWallpaperName + SMALL_FLAG));
        this.mWallpaperThumbRef = new SoftReference<>(decodeResource);
        return decodeResource;
    }

    public Bitmap getWallpaper(Context context) {
        if (this.mIsCurrentWallpaper) {
            Bitmap currentWallpaper = getCurrentWallpaper();
            if (currentWallpaper != null) {
                return currentWallpaper;
            }
            Bitmap currentWallpaper2 = getCurrentWallpaper(context);
            sCurrentWallpaperRef = new SoftReference<>(currentWallpaper2);
            return currentWallpaper2;
        }
        Bitmap wallpaper = getWallpaper();
        if (wallpaper != null) {
            return wallpaper;
        }
        Resources wallpaperRes = getWallpaperRes(context);
        Bitmap decodeResource = BitmapFactory.decodeResource(wallpaperRes, getWallpaperId(wallpaperRes, this.mWallpaperName));
        this.mWallpaperRef = new SoftReference<>(decodeResource);
        return decodeResource;
    }

    private Bitmap getWallpaper() {
        SoftReference<Bitmap> softReference = this.mWallpaperRef;
        if (softReference == null || softReference.get() == null) {
            return null;
        }
        return this.mWallpaperRef.get();
    }

    private static Bitmap getCurrentWallpaper() {
        SoftReference<Bitmap> softReference = sCurrentWallpaperRef;
        if (softReference == null || softReference.get() == null) {
            return null;
        }
        return sCurrentWallpaperRef.get();
    }

    private Bitmap getWallpaperThumb() {
        SoftReference<Bitmap> softReference = this.mWallpaperThumbRef;
        if (softReference == null || softReference.get() == null) {
            return null;
        }
        return this.mWallpaperThumbRef.get();
    }

    private static Bitmap getCurrentWallpaperThumb() {
        SoftReference<Bitmap> softReference = sCurrentWallpaperThumbRef;
        if (softReference == null || softReference.get() == null) {
            return null;
        }
        return sCurrentWallpaperThumbRef.get();
    }

    private static Bitmap getCurrentWallpaper(Context context) {
        return WallpaperManager.getInstance(context).getBitmap(1);
    }

    private boolean isPreloadedWallpaper() {
        return this.mWallpaperPackage != null;
    }

    public boolean isCurrentWallpaper() {
        return this.mIsCurrentWallpaper;
    }

    public void setIsCurrentWallpaper(boolean z) {
        this.mIsCurrentWallpaper = z;
    }

    public Resources getWallpaperRes(Context context) {
        try {
            return context.createPackageContext(this.mWallpaperPackage, 0).getResources();
        } catch (Exception e) {
            Log.w("Styles", "getWallpaperRes error", e);
            return null;
        }
    }

    public InputStream getWallpaperStream(Context context) {
        Resources wallpaperRes = getWallpaperRes(context);
        return wallpaperRes.openRawResource(getWallpaperId(wallpaperRes, this.mWallpaperName));
    }

    public boolean exists(Context context) {
        return getWallpaperId(context) != 0;
    }

    private int getWallpaperId(Context context) {
        return getWallpaperId(getWallpaperRes(context), this.mWallpaperName);
    }

    private int getWallpaperId(Resources resources, String str) {
        return resources.getIdentifier(str, IconPack.DRAWABLE, this.mWallpaperPackage);
    }
}
