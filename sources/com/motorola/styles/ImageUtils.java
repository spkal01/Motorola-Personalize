package com.motorola.styles;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageUtils {
    private static final Paint PAINT = new Paint(6);

    public enum ScalingLogic {
        CROP,
        FIT
    }

    public static Bitmap createScaledBitmapByWidth(Bitmap bitmap, int i, ScalingLogic scalingLogic) {
        return createScaledBitmap(bitmap, i, (int) ((((float) i) / ((float) bitmap.getWidth())) * ((float) bitmap.getHeight())), scalingLogic);
    }

    public static Bitmap createScaledBitmapByHeight(Bitmap bitmap, int i, ScalingLogic scalingLogic) {
        return createScaledBitmap(bitmap, (int) ((((float) i) / ((float) bitmap.getHeight())) * ((float) bitmap.getWidth())), i, scalingLogic);
    }

    public static Bitmap decodeResource(Resources resources, int i, int i2, int i3, ScalingLogic scalingLogic) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, i2, i3, scalingLogic);
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i, int i2, ScalingLogic scalingLogic) {
        Rect calculateSrcRect = calculateSrcRect(bitmap.getWidth(), bitmap.getHeight(), i, i2, scalingLogic);
        Rect calculateDstRect = calculateDstRect(bitmap.getWidth(), bitmap.getHeight(), i, i2, scalingLogic);
        Bitmap createBitmap = Bitmap.createBitmap(calculateDstRect.width(), calculateDstRect.height(), bitmap.getConfig());
        Canvas canvas = new Canvas();
        canvas.setBitmap(createBitmap);
        canvas.drawBitmap(bitmap, calculateSrcRect, calculateDstRect, PAINT);
        canvas.setBitmap((Bitmap) null);
        return createBitmap;
    }

    public static int calculateSampleSize(int i, int i2, int i3, int i4, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            if (((float) i) / ((float) i2) > ((float) i3) / ((float) i4)) {
                return i / i3;
            }
            return i2 / i4;
        } else if (((float) i) / ((float) i2) > ((float) i3) / ((float) i4)) {
            return i2 / i4;
        } else {
            return i / i3;
        }
    }

    public static Rect calculateSrcRect(int i, int i2, int i3, int i4, ScalingLogic scalingLogic) {
        if (scalingLogic != ScalingLogic.CROP) {
            return new Rect(0, 0, i, i2);
        }
        float f = (float) i;
        float f2 = (float) i2;
        float f3 = ((float) i3) / ((float) i4);
        if (f / f2 > f3) {
            int i5 = (int) (f2 * f3);
            int i6 = (i - i5) / 2;
            return new Rect(i6, 0, i5 + i6, i2);
        }
        int i7 = (int) (f / f3);
        int i8 = (i2 - i7) / 2;
        return new Rect(0, i8, i, i7 + i8);
    }

    public static Rect calculateDstRect(int i, int i2, int i3, int i4, ScalingLogic scalingLogic) {
        if (scalingLogic != ScalingLogic.FIT) {
            return new Rect(0, 0, i3, i4);
        }
        float f = ((float) i) / ((float) i2);
        float f2 = (float) i3;
        float f3 = (float) i4;
        if (f > f2 / f3) {
            return new Rect(0, 0, i3, (int) (f2 / f));
        }
        return new Rect(0, 0, (int) (f3 * f), i4);
    }

    public static Bitmap toBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int i = 1;
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.renderscript.Allocation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.renderscript.Allocation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.renderscript.Allocation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: android.renderscript.RenderScript} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap fastblur(android.content.Context r5, android.graphics.Bitmap r6, int r7) {
        /*
            android.graphics.Bitmap$Config r0 = r6.getConfig()
            r1 = 1
            android.graphics.Bitmap r0 = r6.copy(r0, r1)
            r2 = 0
            android.renderscript.RenderScript r5 = android.renderscript.RenderScript.create(r5)     // Catch:{ all -> 0x0054 }
            android.renderscript.Allocation$MipmapControl r3 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ all -> 0x004e }
            android.renderscript.Allocation r6 = android.renderscript.Allocation.createFromBitmap(r5, r6, r3, r1)     // Catch:{ all -> 0x004e }
            android.renderscript.Type r1 = r6.getType()     // Catch:{ all -> 0x004b }
            android.renderscript.Allocation r1 = android.renderscript.Allocation.createTyped(r5, r1)     // Catch:{ all -> 0x004b }
            android.renderscript.Element r3 = android.renderscript.Element.U8_4(r5)     // Catch:{ all -> 0x0046 }
            android.renderscript.ScriptIntrinsicBlur r2 = android.renderscript.ScriptIntrinsicBlur.create(r5, r3)     // Catch:{ all -> 0x0046 }
            float r7 = (float) r7     // Catch:{ all -> 0x0046 }
            r2.setRadius(r7)     // Catch:{ all -> 0x0046 }
            r2.setInput(r6)     // Catch:{ all -> 0x0046 }
            r2.forEach(r1)     // Catch:{ all -> 0x0046 }
            r1.copyTo(r0)     // Catch:{ all -> 0x0046 }
            if (r5 == 0) goto L_0x0036
            r5.destroy()
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.destroy()
        L_0x003b:
            if (r6 == 0) goto L_0x0040
            r6.destroy()
        L_0x0040:
            if (r1 == 0) goto L_0x0045
            r1.destroy()
        L_0x0045:
            return r0
        L_0x0046:
            r7 = move-exception
            r4 = r2
            r2 = r5
            r5 = r4
            goto L_0x0058
        L_0x004b:
            r7 = move-exception
            r1 = r2
            goto L_0x0051
        L_0x004e:
            r7 = move-exception
            r6 = r2
            r1 = r6
        L_0x0051:
            r2 = r5
            r5 = r1
            goto L_0x0058
        L_0x0054:
            r7 = move-exception
            r5 = r2
            r6 = r5
            r1 = r6
        L_0x0058:
            if (r2 == 0) goto L_0x005d
            r2.destroy()
        L_0x005d:
            if (r5 == 0) goto L_0x0062
            r5.destroy()
        L_0x0062:
            if (r6 == 0) goto L_0x0067
            r6.destroy()
        L_0x0067:
            if (r1 == 0) goto L_0x006c
            r1.destroy()
        L_0x006c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.styles.ImageUtils.fastblur(android.content.Context, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }
}
