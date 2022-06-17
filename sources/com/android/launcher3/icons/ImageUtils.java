package com.android.launcher3.icons;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ImageUtils {
    private static final Paint PAINT = new Paint(7);

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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: int[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v9, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v5, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r35v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r35v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v13, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: android.renderscript.RenderScript} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: android.renderscript.ScriptIntrinsicBlur} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: android.renderscript.RenderScript} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap fastblur(android.content.Context r36, android.graphics.Bitmap r37, int r38) {
        /*
            r0 = r37
            r1 = r38
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 0
            r4 = 1
            r5 = 19
            if (r2 <= r5) goto L_0x007a
            android.graphics.Bitmap$Config r2 = r37.getConfig()
            android.graphics.Bitmap r2 = r0.copy(r2, r4)
            android.renderscript.RenderScript r5 = android.renderscript.RenderScript.create(r36)     // Catch:{ all -> 0x0060 }
            android.renderscript.Allocation$MipmapControl r6 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ all -> 0x0059 }
            android.renderscript.Allocation r4 = android.renderscript.Allocation.createFromBitmap(r5, r0, r6, r4)     // Catch:{ all -> 0x0059 }
            android.renderscript.Type r0 = r4.getType()     // Catch:{ all -> 0x0054 }
            android.renderscript.Allocation r6 = android.renderscript.Allocation.createTyped(r5, r0)     // Catch:{ all -> 0x0054 }
            android.renderscript.Element r0 = android.renderscript.Element.U8_4(r5)     // Catch:{ all -> 0x0050 }
            android.renderscript.ScriptIntrinsicBlur r3 = android.renderscript.ScriptIntrinsicBlur.create(r5, r0)     // Catch:{ all -> 0x0050 }
            float r0 = (float) r1     // Catch:{ all -> 0x0050 }
            r3.setRadius(r0)     // Catch:{ all -> 0x0050 }
            r3.setInput(r4)     // Catch:{ all -> 0x0050 }
            r3.forEach(r6)     // Catch:{ all -> 0x0050 }
            r6.copyTo(r2)     // Catch:{ all -> 0x0050 }
            if (r5 == 0) goto L_0x0040
            r5.destroy()
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.destroy()
        L_0x0045:
            if (r4 == 0) goto L_0x004a
            r4.destroy()
        L_0x004a:
            if (r6 == 0) goto L_0x004f
            r6.destroy()
        L_0x004f:
            return r2
        L_0x0050:
            r0 = move-exception
            r1 = r0
            r0 = r3
            goto L_0x005e
        L_0x0054:
            r0 = move-exception
            r1 = r0
            r0 = r3
            r6 = r0
            goto L_0x005e
        L_0x0059:
            r0 = move-exception
            r1 = r0
            r0 = r3
            r4 = r0
            r6 = r4
        L_0x005e:
            r3 = r5
            goto L_0x0065
        L_0x0060:
            r0 = move-exception
            r1 = r0
            r0 = r3
            r4 = r0
            r6 = r4
        L_0x0065:
            if (r3 == 0) goto L_0x006a
            r3.destroy()
        L_0x006a:
            if (r0 == 0) goto L_0x006f
            r0.destroy()
        L_0x006f:
            if (r4 == 0) goto L_0x0074
            r4.destroy()
        L_0x0074:
            if (r6 == 0) goto L_0x0079
            r6.destroy()
        L_0x0079:
            throw r1
        L_0x007a:
            android.graphics.Bitmap$Config r2 = r37.getConfig()
            android.graphics.Bitmap r0 = r0.copy(r2, r4)
            if (r1 >= r4) goto L_0x0085
            return r3
        L_0x0085:
            int r2 = r0.getWidth()
            int r3 = r0.getHeight()
            int r13 = r2 * r3
            int[] r14 = new int[r13]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r5 = r5.append(r2)
            java.lang.String r6 = " "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r13)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "pix"
            android.util.Log.e(r6, r5)
            r7 = 0
            r9 = 0
            r10 = 0
            r5 = r0
            r6 = r14
            r8 = r2
            r11 = r2
            r12 = r3
            r5.getPixels(r6, r7, r8, r9, r10, r11, r12)
            int r5 = r2 + -1
            int r6 = r3 + -1
            int r7 = r1 + r1
            int r7 = r7 + r4
            int[] r8 = new int[r13]
            int[] r9 = new int[r13]
            int[] r10 = new int[r13]
            int r11 = java.lang.Math.max(r2, r3)
            int[] r11 = new int[r11]
            int r12 = r7 + 1
            int r12 = r12 >> r4
            int r12 = r12 * r12
            int r13 = r12 * 256
            int[] r15 = new int[r13]
            r4 = 0
        L_0x00dc:
            if (r4 >= r13) goto L_0x00e5
            int r17 = r4 / r12
            r15[r4] = r17
            int r4 = r4 + 1
            goto L_0x00dc
        L_0x00e5:
            r4 = 3
            r12 = 2
            int[] r13 = new int[r12]
            r16 = 1
            r13[r16] = r4
            r4 = 0
            r13[r4] = r7
            java.lang.Class<int> r4 = int.class
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r4, r13)
            int[][] r4 = (int[][]) r4
            int r13 = r1 + 1
            r12 = 0
            r17 = 0
            r18 = 0
        L_0x00ff:
            if (r12 >= r3) goto L_0x0230
            r19 = r0
            int r0 = -r1
            r28 = r3
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r3 = r0
            r0 = 0
        L_0x0118:
            r29 = 65280(0xff00, float:9.1477E-41)
            r30 = 16711680(0xff0000, float:2.3418052E-38)
            if (r3 > r1) goto L_0x0182
            r31 = r6
            r32 = r11
            r6 = 0
            int r11 = java.lang.Math.max(r3, r6)
            int r11 = java.lang.Math.min(r5, r11)
            int r11 = r17 + r11
            r11 = r14[r11]
            int r33 = r3 + r1
            r33 = r4[r33]
            r30 = r11 & r30
            int r30 = r30 >> 16
            r33[r6] = r30
            r29 = r11 & r29
            int r29 = r29 >> 8
            r16 = 1
            r33[r16] = r29
            r11 = r11 & 255(0xff, float:3.57E-43)
            r29 = 2
            r33[r29] = r11
            int r11 = java.lang.Math.abs(r3)
            int r11 = r13 - r11
            r30 = r33[r6]
            int r30 = r30 * r11
            int r0 = r0 + r30
            r30 = r33[r16]
            int r30 = r30 * r11
            int r20 = r20 + r30
            r30 = r33[r29]
            int r30 = r30 * r11
            int r21 = r21 + r30
            if (r3 <= 0) goto L_0x016f
            r11 = r33[r6]
            int r25 = r25 + r11
            r11 = r33[r16]
            int r26 = r26 + r11
            r11 = r33[r29]
            int r27 = r27 + r11
            goto L_0x017b
        L_0x016f:
            r11 = r33[r6]
            int r22 = r22 + r11
            r6 = r33[r16]
            int r23 = r23 + r6
            r6 = r33[r29]
            int r24 = r24 + r6
        L_0x017b:
            int r3 = r3 + 1
            r6 = r31
            r11 = r32
            goto L_0x0118
        L_0x0182:
            r31 = r6
            r32 = r11
            r3 = r0
            r6 = r1
            r0 = 0
        L_0x0189:
            if (r0 >= r2) goto L_0x0220
            r11 = r15[r3]
            r8[r17] = r11
            r11 = r15[r20]
            r9[r17] = r11
            r11 = r15[r21]
            r10[r17] = r11
            int r3 = r3 - r22
            int r20 = r20 - r23
            int r21 = r21 - r24
            int r11 = r6 - r1
            int r11 = r11 + r7
            int r11 = r11 % r7
            r11 = r4[r11]
            r33 = 0
            r34 = r11[r33]
            int r22 = r22 - r34
            r16 = 1
            r33 = r11[r16]
            int r23 = r23 - r33
            r33 = 2
            r34 = r11[r33]
            int r24 = r24 - r34
            if (r12 != 0) goto L_0x01c4
            int r33 = r0 + r1
            r34 = r15
            int r15 = r33 + 1
            int r15 = java.lang.Math.min(r15, r5)
            r32[r0] = r15
            goto L_0x01c6
        L_0x01c4:
            r34 = r15
        L_0x01c6:
            r15 = r32[r0]
            int r15 = r18 + r15
            r15 = r14[r15]
            r33 = r15 & r30
            int r33 = r33 >> 16
            r35 = 0
            r11[r35] = r33
            r33 = r15 & r29
            int r33 = r33 >> 8
            r16 = 1
            r11[r16] = r33
            r15 = r15 & 255(0xff, float:3.57E-43)
            r33 = 2
            r11[r33] = r15
            r15 = r11[r35]
            int r25 = r25 + r15
            r15 = r11[r16]
            int r26 = r26 + r15
            r11 = r11[r33]
            int r27 = r27 + r11
            int r3 = r3 + r25
            int r20 = r20 + r26
            int r21 = r21 + r27
            int r6 = r6 + 1
            int r6 = r6 % r7
            int r11 = r6 % r7
            r11 = r4[r11]
            r15 = 0
            r33 = r11[r15]
            int r22 = r22 + r33
            r16 = 1
            r33 = r11[r16]
            int r23 = r23 + r33
            r33 = 2
            r35 = r11[r33]
            int r24 = r24 + r35
            r35 = r11[r15]
            int r25 = r25 - r35
            r15 = r11[r16]
            int r26 = r26 - r15
            r11 = r11[r33]
            int r27 = r27 - r11
            int r17 = r17 + 1
            int r0 = r0 + 1
            r15 = r34
            goto L_0x0189
        L_0x0220:
            r34 = r15
            int r18 = r18 + r2
            int r12 = r12 + 1
            r0 = r19
            r3 = r28
            r6 = r31
            r11 = r32
            goto L_0x00ff
        L_0x0230:
            r19 = r0
            r28 = r3
            r31 = r6
            r32 = r11
            r34 = r15
            r0 = 0
        L_0x023b:
            if (r0 >= r2) goto L_0x0372
            int r3 = -r1
            int r5 = r3 * r2
            r21 = r7
            r22 = r14
            r6 = 0
            r11 = 0
            r12 = 0
            r15 = 0
            r17 = 0
            r18 = 0
            r20 = 0
            r7 = r3
            r14 = r5
            r3 = 0
            r5 = 0
        L_0x0252:
            if (r7 > r1) goto L_0x02b9
            r23 = r2
            r2 = 0
            int r24 = java.lang.Math.max(r2, r14)
            int r24 = r24 + r0
            int r25 = r7 + r1
            r25 = r4[r25]
            r26 = r8[r24]
            r25[r2] = r26
            r2 = r9[r24]
            r16 = 1
            r25[r16] = r2
            r2 = r10[r24]
            r26 = 2
            r25[r26] = r2
            int r2 = java.lang.Math.abs(r7)
            int r2 = r13 - r2
            r26 = r8[r24]
            int r26 = r26 * r2
            int r3 = r3 + r26
            r26 = r9[r24]
            int r26 = r26 * r2
            int r5 = r5 + r26
            r24 = r10[r24]
            int r24 = r24 * r2
            int r6 = r6 + r24
            if (r7 <= 0) goto L_0x029d
            r2 = 0
            r24 = r25[r2]
            int r17 = r17 + r24
            r16 = 1
            r24 = r25[r16]
            int r18 = r18 + r24
            r24 = 2
            r25 = r25[r24]
            int r20 = r20 + r25
            goto L_0x02ac
        L_0x029d:
            r2 = 0
            r16 = 1
            r24 = 2
            r26 = r25[r2]
            int r11 = r11 + r26
            r2 = r25[r16]
            int r12 = r12 + r2
            r2 = r25[r24]
            int r15 = r15 + r2
        L_0x02ac:
            r2 = r31
            if (r7 >= r2) goto L_0x02b2
            int r14 = r14 + r23
        L_0x02b2:
            int r7 = r7 + 1
            r31 = r2
            r2 = r23
            goto L_0x0252
        L_0x02b9:
            r23 = r2
            r2 = r31
            r24 = r0
            r25 = r1
            r7 = r6
            r14 = r28
            r6 = r5
            r5 = 0
        L_0x02c6:
            if (r5 >= r14) goto L_0x035c
            r26 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r27 = r22[r24]
            r26 = r27 & r26
            r27 = r34[r3]
            int r27 = r27 << 16
            r26 = r26 | r27
            r27 = r34[r6]
            int r27 = r27 << 8
            r26 = r26 | r27
            r27 = r34[r7]
            r26 = r26 | r27
            r22[r24] = r26
            int r3 = r3 - r11
            int r6 = r6 - r12
            int r7 = r7 - r15
            int r26 = r25 - r1
            int r26 = r26 + r21
            int r26 = r26 % r21
            r26 = r4[r26]
            r27 = 0
            r28 = r26[r27]
            int r11 = r11 - r28
            r16 = 1
            r27 = r26[r16]
            int r12 = r12 - r27
            r27 = 2
            r28 = r26[r27]
            int r15 = r15 - r28
            if (r0 != 0) goto L_0x0309
            int r1 = r5 + r13
            int r1 = java.lang.Math.min(r1, r2)
            int r1 = r1 * r23
            r32[r5] = r1
        L_0x0309:
            r1 = r32[r5]
            int r1 = r1 + r0
            r27 = r8[r1]
            r28 = 0
            r26[r28] = r27
            r27 = r9[r1]
            r16 = 1
            r26[r16] = r27
            r1 = r10[r1]
            r27 = 2
            r26[r27] = r1
            r1 = r26[r28]
            int r17 = r17 + r1
            r1 = r26[r16]
            int r18 = r18 + r1
            r1 = r26[r27]
            int r20 = r20 + r1
            int r3 = r3 + r17
            int r6 = r6 + r18
            int r7 = r7 + r20
            int r25 = r25 + 1
            int r25 = r25 % r21
            r1 = r4[r25]
            r26 = 0
            r27 = r1[r26]
            int r11 = r11 + r27
            r16 = 1
            r27 = r1[r16]
            int r12 = r12 + r27
            r27 = 2
            r28 = r1[r27]
            int r15 = r15 + r28
            r28 = r1[r26]
            int r17 = r17 - r28
            r28 = r1[r16]
            int r18 = r18 - r28
            r1 = r1[r27]
            int r20 = r20 - r1
            int r24 = r24 + r23
            int r5 = r5 + 1
            r1 = r38
            goto L_0x02c6
        L_0x035c:
            r16 = 1
            r26 = 0
            r27 = 2
            int r0 = r0 + 1
            r1 = r38
            r31 = r2
            r28 = r14
            r7 = r21
            r14 = r22
            r2 = r23
            goto L_0x023b
        L_0x0372:
            r23 = r2
            r22 = r14
            r14 = r28
            r7 = 0
            r9 = 0
            r10 = 0
            r5 = r19
            r6 = r22
            r8 = r23
            r11 = r23
            r12 = r14
            r5.setPixels(r6, r7, r8, r9, r10, r11, r12)
            return r19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.ImageUtils.fastblur(android.content.Context, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }

    public static Bitmap setAlpha(Bitmap bitmap, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAlpha((int) (f * 255.0f));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap((Bitmap) null);
        return createBitmap;
    }

    public static Bitmap takeScreenshot(View view) {
        return takeScreenshot(view, 1.0f);
    }

    public static Bitmap takeScreenshot(View view, float f) {
        return takeScreenshot(view, f, true);
    }

    public static Bitmap takeScreenshot(View view, float f, boolean z) {
        Bitmap createBitmap = Bitmap.createBitmap((int) (((float) view.getWidth()) * f), (int) (((float) view.getHeight()) * f), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(f, f);
        if (z) {
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            canvas.drawBitmap(view.getDrawingCache(), 0.0f, 0.0f, new Paint(4));
            canvas.setBitmap((Bitmap) null);
            view.destroyDrawingCache();
            view.setDrawingCacheEnabled(false);
        } else {
            view.draw(canvas);
            canvas.setBitmap((Bitmap) null);
        }
        return createBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052 A[SYNTHETIC, Splitter:B:14:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveBitmap(android.graphics.Bitmap r4) {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x004c }
            r1.<init>()     // Catch:{ all -> 0x004c }
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ all -> 0x004c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x004c }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x004c }
            java.lang.String r2 = "/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x004c }
            java.lang.String r2 = "yyyy-MM-dd_hh:mm:ss"
            java.util.Date r3 = new java.util.Date     // Catch:{ all -> 0x004c }
            r3.<init>()     // Catch:{ all -> 0x004c }
            java.lang.CharSequence r2 = android.text.format.DateFormat.format(r2, r3)     // Catch:{ all -> 0x004c }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x004c }
            java.lang.String r2 = ".png"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x004c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004c }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x004c }
            r2.<init>(r1)     // Catch:{ all -> 0x004c }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x004c }
            r1.<init>(r2)     // Catch:{ all -> 0x004c }
            r0 = 100
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0049 }
            r4.compress(r2, r0, r1)     // Catch:{ all -> 0x0049 }
            r1.flush()     // Catch:{ all -> 0x0049 }
            r1.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0055
        L_0x0049:
            r4 = move-exception
            r0 = r1
            goto L_0x004d
        L_0x004c:
            r4 = move-exception
        L_0x004d:
            r4.printStackTrace()     // Catch:{ all -> 0x0056 }
            if (r0 == 0) goto L_0x0055
            r0.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0055:
            return
        L_0x0056:
            r4 = move-exception
            if (r0 == 0) goto L_0x005c
            r0.close()     // Catch:{ IOException -> 0x005c }
        L_0x005c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.ImageUtils.saveBitmap(android.graphics.Bitmap):void");
    }

    public static void takeScreenshotAndSave(View view, float f) {
        saveBitmap(takeScreenshot(view, f));
    }
}
