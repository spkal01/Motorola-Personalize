package com.android.launcher3.icons;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.FastBitmapDrawable;
import com.android.launcher3.icons.cache.BaseIconCache;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ThemedIconDrawable extends FastBitmapDrawable {
    public static final String TAG = "ThemedIconDrawable";
    final ThemedBitmapInfo bitmapInfo;
    final int colorBg;
    final int colorFg;
    private final Rect mBadgeBounds;
    private final AdaptiveIconDrawable mBgWrapper;
    private final Drawable mMonochromeIcon;

    public boolean isThemed() {
        return true;
    }

    protected ThemedIconDrawable(ThemedConstantState themedConstantState) {
        super(themedConstantState.mBitmap, themedConstantState.colorFg, themedConstantState.mIsDisabled);
        ThemedBitmapInfo themedBitmapInfo = themedConstantState.bitmapInfo;
        this.bitmapInfo = themedBitmapInfo;
        int i = themedConstantState.colorBg;
        this.colorBg = i;
        int i2 = themedConstantState.colorFg;
        this.colorFg = i2;
        this.mMonochromeIcon = themedBitmapInfo.mThemeData.loadMonochromeDrawable(i2);
        ColorDrawable colorDrawable = new ColorDrawable(i);
        Rect rect = null;
        this.mBgWrapper = new AdaptiveIconDrawable(colorDrawable, (Drawable) null);
        this.mBadgeBounds = themedBitmapInfo.mUserBadge != null ? new Rect(0, 0, themedBitmapInfo.mUserBadge.getWidth(), themedBitmapInfo.mUserBadge.getHeight()) : rect;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mBgWrapper.setBounds(rect);
        this.mMonochromeIcon.setBounds(rect);
    }

    /* access modifiers changed from: protected */
    public void drawInternal(Canvas canvas, Rect rect) {
        int save = canvas.save();
        canvas.scale(this.bitmapInfo.mNormalizationScale, this.bitmapInfo.mNormalizationScale, rect.exactCenterX(), rect.exactCenterY());
        this.mPaint.setColor(this.colorBg);
        canvas.drawPath(this.mBgWrapper.getIconMask(), this.mPaint);
        this.mMonochromeIcon.draw(canvas);
        canvas.restoreToCount(save);
        if (this.mBadgeBounds != null) {
            canvas.drawBitmap(this.bitmapInfo.mUserBadge, this.mBadgeBounds, getBounds(), this.mPaint);
        }
    }

    public Drawable.ConstantState getConstantState() {
        return new ThemedConstantState(this.bitmapInfo, this.colorBg, this.colorFg, this.mIsDisabled);
    }

    static class ThemedConstantState extends FastBitmapDrawable.FastBitmapConstantState {
        final ThemedBitmapInfo bitmapInfo;
        final int colorBg;
        final int colorFg;

        public ThemedConstantState(ThemedBitmapInfo themedBitmapInfo, int i, int i2, boolean z) {
            super(themedBitmapInfo.icon, themedBitmapInfo.color, z);
            this.bitmapInfo = themedBitmapInfo;
            this.colorBg = i;
            this.colorFg = i2;
        }

        public FastBitmapDrawable newDrawable() {
            return new ThemedIconDrawable(this);
        }
    }

    public static class ThemedBitmapInfo extends BitmapInfo {
        final float mNormalizationScale;
        final ThemeData mThemeData;
        final Bitmap mUserBadge;

        public ThemedBitmapInfo(Bitmap bitmap, int i, ThemeData themeData, float f, Bitmap bitmap2) {
            super(bitmap, i);
            this.mThemeData = themeData;
            this.mNormalizationScale = f;
            this.mUserBadge = bitmap2;
        }

        public FastBitmapDrawable newThemedIcon(Context context) {
            int[] colors = ThemedIconDrawable.getColors(context);
            FastBitmapDrawable newDrawable = new ThemedConstantState(this, colors[0], colors[1], false).newDrawable();
            newDrawable.mDisabledAlpha = GraphicsUtils.getFloat(context, C0738R.attr.disabledIconAlpha, 1.0f);
            return newDrawable;
        }

        public byte[] toByteArray() {
            if (isNullOrLowRes()) {
                return null;
            }
            String resourceName = this.mThemeData.mResources.getResourceName(this.mThemeData.mResID);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(GraphicsUtils.getExpectedBitmapSize(this.icon) + 3 + resourceName.length());
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeByte(2);
                dataOutputStream.writeFloat(this.mNormalizationScale);
                dataOutputStream.writeUTF(resourceName);
                this.icon.compress(Bitmap.CompressFormat.PNG, 100, dataOutputStream);
                dataOutputStream.flush();
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                Log.w(BitmapInfo.TAG, "Could not write bitmap");
                return null;
            }
        }

        static ThemedBitmapInfo decode(byte[] bArr, int i, BitmapFactory.Options options, UserHandle userHandle, BaseIconCache baseIconCache, Context context) {
            Bitmap bitmap;
            BaseIconFactory iconFactory;
            try {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                try {
                    dataInputStream.readByte();
                    float readFloat = dataInputStream.readFloat();
                    int identifier = context.getResources().getIdentifier(dataInputStream.readUTF(), IconPack.DRAWABLE, context.getPackageName());
                    if (identifier == 0) {
                        dataInputStream.close();
                        return null;
                    }
                    if (!Process.myUserHandle().equals(userHandle)) {
                        iconFactory = baseIconCache.getIconFactory();
                        Bitmap userBadgeBitmap = iconFactory.getUserBadgeBitmap(userHandle);
                        if (iconFactory != null) {
                            iconFactory.close();
                        }
                        bitmap = userBadgeBitmap;
                    } else {
                        bitmap = null;
                    }
                    ThemedBitmapInfo themedBitmapInfo = new ThemedBitmapInfo(BitmapFactory.decodeStream(dataInputStream, (Rect) null, options), i, new ThemeData(context.getResources(), identifier), readFloat, bitmap);
                    dataInputStream.close();
                    return themedBitmapInfo;
                } catch (Throwable th) {
                    dataInputStream.close();
                    throw th;
                }
                throw th;
            } catch (IOException unused) {
                return null;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
    }

    public static class ThemeData {
        final int mResID;
        final Resources mResources;

        public ThemeData(Resources resources, int i) {
            this.mResources = resources;
            this.mResID = i;
        }

        /* access modifiers changed from: package-private */
        public Drawable loadMonochromeDrawable(int i) {
            Drawable mutate = this.mResources.getDrawable(this.mResID).mutate();
            mutate.setTint(i);
            return new InsetDrawable(mutate, 0.2f);
        }

        public Drawable wrapDrawable(Drawable drawable, int i) {
            if (!(drawable instanceof AdaptiveIconDrawable)) {
                return drawable;
            }
            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) drawable;
            String resourceTypeName = this.mResources.getResourceTypeName(this.mResID);
            if (i == 1 && "array".equals(resourceTypeName)) {
                TypedArray obtainTypedArray = this.mResources.obtainTypedArray(this.mResID);
                int resourceId = obtainTypedArray.getResourceId(IconProvider.getDay(), 0);
                obtainTypedArray.recycle();
                return resourceId == 0 ? drawable : new ThemedAdaptiveIcon(adaptiveIconDrawable, new ThemeData(this.mResources, resourceId));
            } else if (i != 2 || !"array".equals(resourceTypeName)) {
                return IconPack.DRAWABLE.equals(resourceTypeName) ? new ThemedAdaptiveIcon(adaptiveIconDrawable, this) : drawable;
            } else {
                ((ClockDrawableWrapper) drawable).mThemeData = this;
                return drawable;
            }
        }
    }

    static class ThemedAdaptiveIcon extends AdaptiveIconDrawable implements BitmapInfo.Extender {
        protected final ThemeData mThemeData;

        public ThemedAdaptiveIcon(AdaptiveIconDrawable adaptiveIconDrawable, ThemeData themeData) {
            super(adaptiveIconDrawable.getBackground(), adaptiveIconDrawable.getForeground());
            this.mThemeData = themeData;
        }

        public BitmapInfo getExtendedInfo(Bitmap bitmap, int i, BaseIconFactory baseIconFactory, float f, UserHandle userHandle) {
            Bitmap bitmap2;
            if (Process.myUserHandle().equals(userHandle)) {
                bitmap2 = null;
            } else {
                bitmap2 = baseIconFactory.getUserBadgeBitmap(userHandle);
            }
            return new ThemedBitmapInfo(bitmap, i, this.mThemeData, f, bitmap2);
        }

        public void drawForPersistence(Canvas canvas) {
            draw(canvas);
        }

        public Drawable getThemedDrawable(Context context) {
            int[] colors = ThemedIconDrawable.getColors(context);
            return new AdaptiveIconDrawable(new ColorDrawable(colors[0]), new InsetDrawable(this.mThemeData.loadMonochromeDrawable(colors[1]), getExtraInsetFraction() / ((getExtraInsetFraction() * 2.0f) + 1.0f)));
        }
    }

    public static int[] getColors(Context context) {
        Resources resources = context.getResources();
        int[] iArr = new int[2];
        if ((resources.getConfiguration().uiMode & 48) == 32) {
            iArr[0] = resources.getColor(17170471);
            iArr[1] = resources.getColor(17170490);
        } else {
            iArr[0] = resources.getColor(17170490);
            iArr[1] = resources.getColor(17170483);
        }
        return iArr;
    }
}
