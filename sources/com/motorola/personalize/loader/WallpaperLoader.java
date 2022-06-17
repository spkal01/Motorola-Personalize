package com.motorola.personalize.loader;

import android.app.WallpaperColors;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.Log;
import com.android.systemui.monet.ColorScheme;
import com.motorola.personalize.util.NoneSdkApi;
import com.motorola.styles.LogConfig;
import java.util.ArrayList;
import java.util.List;

public class WallpaperLoader implements WallpaperManager.OnColorsChangedListener {
    public static final String TAG = "WallpaperLoader";
    private static WallpaperLoader sLoader;
    private Context appContext;
    private boolean colorChange = false;
    private final List<Integer> homeColorSeeds = new ArrayList();
    private int lastWhich = -1;
    private final List<Integer> lockColorSeeds = new ArrayList();
    private PackageManager packageManager;
    private Bitmap sWallpaperBitmap;
    private WallpaperManager wallpaperManager;

    public boolean isColorChanged() {
        return this.colorChange;
    }

    public void resetColorChangeStatus() {
        this.colorChange = false;
    }

    public static WallpaperLoader getInstance() {
        if (sLoader == null) {
            sLoader = new WallpaperLoader();
        }
        return sLoader;
    }

    public void onColorsChanged(WallpaperColors wallpaperColors, int i) {
        WallpaperInfo wallpaperInfo;
        if (LogConfig.DBG) {
            Log.d(TAG, "onColorsChanged which = " + i + ", lastWhich = " + this.lastWhich);
        }
        if (wallpaperColors != null && LogConfig.DBG) {
            Log.d(TAG, "onColorsChanged colors = " + wallpaperColors.toString());
        }
        this.colorChange = true;
        if (i == 3) {
            if (wallpaperColors == null) {
                this.colorChange = false;
                return;
            }
            List<Integer> generateColorSeeds = generateColorSeeds(wallpaperColors);
            if (analysisColorSeeds(this.homeColorSeeds, generateColorSeeds)) {
                this.homeColorSeeds.clear();
                this.homeColorSeeds.addAll(generateColorSeeds);
            }
            if (analysisColorSeeds(this.lockColorSeeds, generateColorSeeds)) {
                this.lockColorSeeds.clear();
                this.lockColorSeeds.addAll(generateColorSeeds);
            }
        } else if (i != 2) {
            if (wallpaperColors == null && (wallpaperInfo = this.wallpaperManager.getWallpaperInfo()) != null) {
                if (this.packageManager == null) {
                    this.packageManager = this.appContext.getPackageManager();
                }
                wallpaperColors = WallpaperColors.fromDrawable(wallpaperInfo.loadThumbnail(this.packageManager));
            }
            if (wallpaperColors != null) {
                List<Integer> generateColorSeeds2 = generateColorSeeds(wallpaperColors);
                if (analysisColorSeeds(this.homeColorSeeds, generateColorSeeds2)) {
                    this.homeColorSeeds.clear();
                    this.homeColorSeeds.addAll(generateColorSeeds2);
                }
            }
        } else if (wallpaperColors == null) {
            this.colorChange = false;
            return;
        } else {
            List<Integer> generateColorSeeds3 = generateColorSeeds(wallpaperColors);
            if (analysisColorSeeds(this.lockColorSeeds, generateColorSeeds3)) {
                this.lockColorSeeds.clear();
                this.lockColorSeeds.addAll(generateColorSeeds3);
            }
        }
        if (i != 2) {
            genWallpaperPreview(getWallpaperBitmap(this.appContext));
        }
        this.lastWhich = i;
    }

    private void initWallpaperManager(Context context) {
        if (this.wallpaperManager == null) {
            this.wallpaperManager = WallpaperManager.getInstance(context);
            HandlerThread handlerThread = new HandlerThread("SubThread", 10);
            handlerThread.start();
            this.wallpaperManager.addOnColorsChangedListener(this, new Handler(handlerThread.getLooper()));
        }
    }

    private void genWallpaperPreview(Bitmap bitmap) {
        DisplayMetrics displayMetrics = this.appContext.getResources().getDisplayMetrics();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (LogConfig.DBG) {
            Log.d(TAG, "init: " + width + " - " + height);
        }
        int min = Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels);
        int max = Math.max(displayMetrics.heightPixels, displayMetrics.widthPixels);
        if (width < height) {
            int i = (int) (((((float) width) * 1.0f) * ((float) max)) / ((float) min));
            if (i > height) {
                this.sWallpaperBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height);
            } else {
                this.sWallpaperBitmap = Bitmap.createBitmap(bitmap, 0, Math.abs((i - height) / 2), width, i);
            }
        } else {
            this.sWallpaperBitmap = Bitmap.createBitmap(bitmap, 0, 0, (int) (((((float) height) * 1.0f) * ((float) min)) / ((float) max)), height);
        }
    }

    private Bitmap getWallpaperBitmap(Context context) {
        WallpaperInfo wallpaperInfo = this.wallpaperManager.getWallpaperInfo();
        if (wallpaperInfo == null) {
            return NoneSdkApi.getWallpaperBitmap(this.wallpaperManager, 1);
        }
        if (this.packageManager == null) {
            this.packageManager = context.getPackageManager();
        }
        return drawableToBitmap(wallpaperInfo.loadThumbnail(this.packageManager));
    }

    public void init(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        if (LogConfig.DBG) {
            Log.d(TAG, "init: start @" + currentTimeMillis);
        }
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        initWallpaperManager(applicationContext);
        Bitmap wallpaperBitmap = getWallpaperBitmap(context);
        this.homeColorSeeds.clear();
        if (wallpaperBitmap != null) {
            genWallpaperPreview(wallpaperBitmap);
            this.homeColorSeeds.addAll(generateColorSeeds(WallpaperColors.fromBitmap(wallpaperBitmap)));
        }
        generateLockColorSeeds();
        if (LogConfig.DBG) {
            Log.d(TAG, "init: end @" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    private void generateLockColorSeeds() {
        Bitmap wallpaperBitmap = NoneSdkApi.getWallpaperBitmap(this.wallpaperManager, 2);
        if (wallpaperBitmap == null) {
            if (LogConfig.DBG) {
                Log.d(TAG, "can't generate LockColorSeeds");
            }
            WallpaperColors wallpaperColors = this.wallpaperManager.getWallpaperColors(2);
            if (wallpaperColors != null) {
                this.lockColorSeeds.clear();
                this.lockColorSeeds.addAll(generateColorSeeds(wallpaperColors));
                return;
            }
            if (LogConfig.DBG) {
                Log.d(TAG, "use HomeColorSeeds to instead");
            }
            this.lockColorSeeds.clear();
            this.lockColorSeeds.addAll(this.homeColorSeeds);
            return;
        }
        WallpaperColors fromBitmap = WallpaperColors.fromBitmap(wallpaperBitmap);
        this.lockColorSeeds.clear();
        this.lockColorSeeds.addAll(generateColorSeeds(fromBitmap));
    }

    private List<Integer> generateColorSeeds(WallpaperColors wallpaperColors) {
        if (wallpaperColors == null) {
            return new ArrayList();
        }
        if (LogConfig.DBG) {
            Log.d(TAG, "generateColorSeeds: " + wallpaperColors.toString());
        }
        List<Integer> seedColors = ColorScheme.getSeedColors(wallpaperColors);
        if (LogConfig.DBG) {
            Log.d(TAG, "generateColorSeeds: " + seedColors.size());
        }
        return seedColors;
    }

    public Bitmap loadWallpaper() {
        return this.sWallpaperBitmap;
    }

    public List<Integer> getHomeColorSeeds() {
        return this.homeColorSeeds;
    }

    public List<Integer> getLockColorSeeds() {
        return this.lockColorSeeds;
    }

    private boolean analysisColorSeeds(List<Integer> list, List<Integer> list2) {
        int size = list.size();
        boolean z = true;
        if (size == 0) {
            return true;
        }
        int min = Math.min(Math.min(size, list2.size()), 4);
        int i = 0;
        while (true) {
            if (i >= min) {
                z = false;
                break;
            }
            if (list2.get(i).intValue() != list.get(i).intValue()) {
                break;
            }
            i++;
        }
        if (LogConfig.DBG) {
            Log.d(TAG, "analysisColorSeeds: dif = " + z);
        }
        return z;
    }

    public boolean isHomeWallpaperNew() {
        int wallpaperId = this.wallpaperManager.getWallpaperId(1);
        int wallpaperId2 = this.wallpaperManager.getWallpaperId(2);
        if (LogConfig.DBG) {
            Log.d(TAG, "isHomeWallpaperNew:homeId = " + wallpaperId + ", lockId = " + wallpaperId2);
        }
        if (wallpaperId >= wallpaperId2) {
            return true;
        }
        return false;
    }

    public boolean isWallpaperSame() {
        if (this.homeColorSeeds.size() == this.lockColorSeeds.size()) {
            boolean z = !analysisColorSeeds(this.homeColorSeeds, this.lockColorSeeds);
            if (LogConfig.DBG) {
                Log.d(TAG, "isWallpaperSame 2: " + z);
            }
            return z;
        } else if (!LogConfig.DBG) {
            return false;
        } else {
            Log.d(TAG, "isWallpaperSame 1: false");
            return false;
        }
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        if (intrinsicHeight <= 0) {
            intrinsicHeight = 1;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}
