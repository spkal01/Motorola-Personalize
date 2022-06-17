package com.motorola.personalize.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.loader.WallpaperLoader;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005B\u001b\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB#\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB+\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0006\u0010\u0017\u001a\u00020\u0015J(\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0016J\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, mo15462d2 = {"Lcom/motorola/personalize/view/AnimationSurfaceView;", "Landroid/view/SurfaceView;", "Landroid/view/SurfaceHolder$Callback;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isWallpaperPainted", "", "paint", "Landroid/graphics/Paint;", "wallpaperBitmap", "Landroid/graphics/Bitmap;", "init", "", "loadWallpaper", "startPreview", "surfaceChanged", "holder", "Landroid/view/SurfaceHolder;", "i", "i1", "i2", "surfaceCreated", "surfaceDestroyed", "Companion", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: AnimationSurfaceView.kt */
public final class AnimationSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "Surface";
    private boolean isWallpaperPainted;
    private final Paint paint = new Paint();
    private Bitmap wallpaperBitmap;

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(surfaceHolder, "holder");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Intrinsics.checkNotNullParameter(surfaceHolder, "holder");
    }

    public AnimationSurfaceView(Context context) {
        super(context);
    }

    public AnimationSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimationSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AnimationSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private final void loadWallpaper() {
        this.wallpaperBitmap = WallpaperLoader.getInstance().loadWallpaper();
    }

    public final void init() {
        if (this.wallpaperBitmap == null) {
            loadWallpaper();
        }
        getHolder().addCallback(this);
    }

    @Metadata(mo15461d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo15462d2 = {"Lcom/motorola/personalize/view/AnimationSurfaceView$Companion;", "", "()V", "TAG", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: AnimationSurfaceView.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Intrinsics.checkNotNullParameter(surfaceHolder, "holder");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "surfaceCreated: ");
        }
        if (!this.isWallpaperPainted) {
            this.isWallpaperPainted = true;
            if (this.wallpaperBitmap != null) {
                Canvas lockCanvas = surfaceHolder.lockCanvas();
                Bitmap bitmap = this.wallpaperBitmap;
                Intrinsics.checkNotNull(bitmap);
                int width = bitmap.getWidth();
                Bitmap bitmap2 = this.wallpaperBitmap;
                Intrinsics.checkNotNull(bitmap2);
                Rect rect = new Rect(0, 0, width, bitmap2.getHeight());
                Rect rect2 = new Rect(0, 0, getWidth(), getHeight());
                Bitmap bitmap3 = this.wallpaperBitmap;
                Intrinsics.checkNotNull(bitmap3);
                lockCanvas.drawBitmap(bitmap3, rect, rect2, this.paint);
                surfaceHolder.unlockCanvasAndPost(lockCanvas);
            }
        }
    }

    public final void startPreview() {
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "startPreview: ");
        }
        setVisibility(0);
    }
}
