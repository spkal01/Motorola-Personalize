package com.android.launcher3.icons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.UserHandle;
import android.util.Log;
import com.android.launcher3.icons.ThemedIconDrawable;
import com.android.launcher3.icons.cache.BaseIconCache;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapInfo {
    public static final Bitmap LOW_RES_ICON;
    public static final BitmapInfo LOW_RES_INFO;
    public static final String TAG = "BitmapInfo";
    protected static final byte TYPE_DEFAULT = 1;
    protected static final byte TYPE_THEMED = 2;
    public final int color;
    public final Bitmap icon;

    public interface Extender {
        void drawForPersistence(Canvas canvas);

        BitmapInfo getExtendedInfo(Bitmap bitmap, int i, BaseIconFactory baseIconFactory, float f, UserHandle userHandle);

        Drawable getThemedDrawable(Context context);
    }

    static {
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        LOW_RES_ICON = createBitmap;
        LOW_RES_INFO = fromBitmap(createBitmap);
    }

    public BitmapInfo(Bitmap bitmap, int i) {
        this.icon = bitmap;
        this.color = i;
    }

    public final boolean isNullOrLowRes() {
        Bitmap bitmap = this.icon;
        return bitmap == null || bitmap == LOW_RES_ICON;
    }

    public final boolean isLowRes() {
        return LOW_RES_ICON == this.icon;
    }

    public byte[] toByteArray() {
        if (isNullOrLowRes()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(GraphicsUtils.getExpectedBitmapSize(this.icon) + 1);
        try {
            byteArrayOutputStream.write(1);
            this.icon.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.w(TAG, "Could not write bitmap");
            return null;
        }
    }

    public FastBitmapDrawable newThemedIcon(Context context) {
        return newIcon(context);
    }

    public FastBitmapDrawable newIcon(Context context) {
        FastBitmapDrawable fastBitmapDrawable;
        if (isLowRes()) {
            fastBitmapDrawable = new PlaceHolderIconDrawable(this, context);
        } else {
            fastBitmapDrawable = new FastBitmapDrawable(this);
        }
        fastBitmapDrawable.mDisabledAlpha = GraphicsUtils.getFloat(context, C0738R.attr.disabledIconAlpha, 1.0f);
        return fastBitmapDrawable;
    }

    public static BitmapInfo fromByteArray(byte[] bArr, int i, UserHandle userHandle, BaseIconCache baseIconCache, Context context) {
        BitmapFactory.Options options;
        if (bArr == null) {
            return null;
        }
        if (!BitmapRenderer.USE_HARDWARE_BITMAP || Build.VERSION.SDK_INT < 26) {
            options = null;
        } else {
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inPreferredConfig = Bitmap.Config.HARDWARE;
            options = options2;
        }
        if (bArr[0] == 1) {
            return m25of(BitmapFactory.decodeByteArray(bArr, 1, bArr.length - 1, options), i);
        }
        if (bArr[0] == 2) {
            return ThemedIconDrawable.ThemedBitmapInfo.decode(bArr, i, options, userHandle, baseIconCache, context);
        }
        return null;
    }

    public static BitmapInfo fromBitmap(Bitmap bitmap) {
        return m25of(bitmap, 0);
    }

    /* renamed from: of */
    public static BitmapInfo m25of(Bitmap bitmap, int i) {
        return new BitmapInfo(bitmap, i);
    }
}
