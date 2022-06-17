package com.android.wallpaper.view;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.motorola.styles.ImageUtils;
import com.motorola.styles.LogConfig;
import com.motorola.styles.view.BackgroundProvider;

public class WallpaperBackgroundProvider extends BackgroundProvider {
    static final int BIG_WALLPAPER_HEIGHT = 1280;
    static final int MIN_WALLPAPER_HEIGHT = 480;
    private BroadcastReceiver mWallpaperChangedReceiver;

    public static int getScaleX(int i, int i2, int i3) {
        return (int) ((((float) i) / ((float) i2)) * ((float) i3));
    }

    public WallpaperBackgroundProvider(Context context) {
        this.mContext = context.getApplicationContext();
        registerWallpaperChangedReceiver(this.mContext);
    }

    public Drawable getBackground(Context context, int i, int i2) {
        Bitmap bitmap;
        Bitmap bitmap2 = ImageUtils.toBitmap(WallpaperManager.getInstance(this.mContext).getDrawable());
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        if (height >= i2) {
            bitmap = ImageUtils.createScaledBitmapByWidth(bitmap2, (int) (((float) width) * (((float) Math.min(MIN_WALLPAPER_HEIGHT, height)) / ((float) height))), ImageUtils.ScalingLogic.FIT);
        } else if (width > i2) {
            int scaleX = getScaleX(i, i2, height);
            float f = (float) height;
            float min = ((float) Math.min(MIN_WALLPAPER_HEIGHT, height)) / f;
            bitmap = ImageUtils.createScaledBitmap(bitmap2, (int) (((float) scaleX) * min), (int) (f * min), ImageUtils.ScalingLogic.CROP);
        } else {
            bitmap = ImageUtils.createScaledBitmapByWidth(bitmap2, (int) (((float) width) * (((float) Math.min(MIN_WALLPAPER_HEIGHT, height)) / ((float) height))), ImageUtils.ScalingLogic.FIT);
        }
        return new BackgroundBitmapDrawable(context.getResources(), ImageUtils.createScaledBitmapByHeight(ImageUtils.fastblur(context, bitmap, 8), Math.min(BIG_WALLPAPER_HEIGHT, i), ImageUtils.ScalingLogic.CROP));
    }

    private void registerWallpaperChangedReceiver(Context context) {
        if (this.mWallpaperChangedReceiver == null) {
            this.mWallpaperChangedReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (LogConfig.DBG) {
                        Log.d("Styles", "WallpaperBackgroundProvider - onReceive: " + intent);
                    }
                    Drawable unused = WallpaperBackgroundProvider.this.mBackground = null;
                }
            };
        }
        context.registerReceiver(this.mWallpaperChangedReceiver, new IntentFilter("android.intent.action.WALLPAPER_CHANGED"));
    }

    /* access modifiers changed from: protected */
    public void onClear() {
        if (LogConfig.DBG) {
            Log.d("Styles", "WallpaperBackgroundProvider - onClear");
        }
        if (this.mWallpaperChangedReceiver != null) {
            try {
                this.mContext.unregisterReceiver(this.mWallpaperChangedReceiver);
            } catch (Exception unused) {
            }
            this.mWallpaperChangedReceiver = null;
        }
        super.onClear();
    }
}
