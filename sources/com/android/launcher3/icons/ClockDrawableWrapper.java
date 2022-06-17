package com.android.launcher3.icons;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Log;
import android.util.TypedValue;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.FastBitmapDrawable;
import com.android.launcher3.icons.ThemedIconDrawable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.function.IntFunction;

public class ClockDrawableWrapper extends AdaptiveIconDrawable implements BitmapInfo.Extender {
    private static final String DEFAULT_HOUR_METADATA_KEY = "com.android.launcher3.DEFAULT_HOUR";
    private static final String DEFAULT_MINUTE_METADATA_KEY = "com.android.launcher3.DEFAULT_MINUTE";
    private static final String DEFAULT_SECOND_METADATA_KEY = "com.android.launcher3.DEFAULT_SECOND";
    private static final boolean DISABLE_SECONDS = true;
    private static final String HOUR_INDEX_METADATA_KEY = "com.android.launcher3.HOUR_LAYER_INDEX";
    public static final int INVALID_VALUE = -1;
    private static final String LAUNCHER_PACKAGE = "com.android.launcher3";
    private static final int LEVELS_PER_SECOND = 10;
    private static final String MINUTE_INDEX_METADATA_KEY = "com.android.launcher3.MINUTE_LAYER_INDEX";
    private static final String ROUND_ICON_METADATA_KEY = "com.android.launcher3.LEVEL_PER_TICK_ICON_ROUND";
    private static final String SECOND_INDEX_METADATA_KEY = "com.android.launcher3.SECOND_LAYER_INDEX";
    private static final String TAG = "ClockDrawableWrapper";
    public static final long TICK_MS = TimeUnit.MINUTES.toMillis(1);
    /* access modifiers changed from: private */
    public final AnimationInfo mAnimationInfo = new AnimationInfo();
    private int mTargetSdkVersion;
    protected ThemedIconDrawable.ThemeData mThemeData;

    public ClockDrawableWrapper(AdaptiveIconDrawable adaptiveIconDrawable) {
        super(adaptiveIconDrawable.getBackground(), adaptiveIconDrawable.getForeground());
    }

    public static ClockDrawableWrapper forPackage(Context context, String str, int i, Drawable drawable) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8320);
            return forExtras(applicationInfo, applicationInfo.metaData, new IntFunction(packageManager.getResourcesForApplication(applicationInfo), i) {
                public final /* synthetic */ Resources f$0;
                public final /* synthetic */ int f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final Object apply(int i) {
                    return this.f$0.getDrawableForDensity(i, this.f$1);
                }
            }, drawable);
        } catch (Exception e) {
            Log.d(TAG, "Unable to load clock drawable info", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static ClockDrawableWrapper fromThemeData(Context context, ThemedIconDrawable.ThemeData themeData) {
        try {
            TypedArray obtainTypedArray = themeData.mResources.obtainTypedArray(themeData.mResID);
            int length = obtainTypedArray.length();
            Bundle bundle = new Bundle();
            for (int i = 0; i < length; i += 2) {
                TypedValue peekValue = obtainTypedArray.peekValue(i + 1);
                bundle.putInt(obtainTypedArray.getString(i), (peekValue.type < 16 || peekValue.type > 31) ? peekValue.resourceId : peekValue.data);
            }
            obtainTypedArray.recycle();
            ClockDrawableWrapper forExtras = forExtras(context.getApplicationInfo(), bundle, new IntFunction(context, themeData) {
                public final /* synthetic */ Context f$0;
                public final /* synthetic */ ThemedIconDrawable.ThemeData f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final Object apply(int i) {
                    return ClockDrawableWrapper.lambda$fromThemeData$1(this.f$0, this.f$1, i);
                }
            }, (Drawable) null);
            if (forExtras != null) {
                return forExtras;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error loading themed clock", e);
        }
    }

    static /* synthetic */ Drawable lambda$fromThemeData$1(Context context, ThemedIconDrawable.ThemeData themeData, int i) {
        int[] colors = ThemedIconDrawable.getColors(context);
        ColorDrawable colorDrawable = new ColorDrawable(colors[0]);
        Drawable mutate = themeData.mResources.getDrawable(i).mutate();
        mutate.setTint(colors[1]);
        return new AdaptiveIconDrawable(colorDrawable, mutate);
    }

    private static ClockDrawableWrapper forExtras(ApplicationInfo applicationInfo, Bundle bundle, IntFunction<Drawable> intFunction, Drawable drawable) {
        int i;
        if (drawable == null || !(drawable instanceof AdaptiveIconDrawable)) {
            if (bundle == null || (i = bundle.getInt(ROUND_ICON_METADATA_KEY, 0)) == 0) {
                return null;
            }
            drawable = intFunction.apply(i).mutate();
        }
        if (!(drawable instanceof AdaptiveIconDrawable)) {
            return null;
        }
        ClockDrawableWrapper clockDrawableWrapper = new ClockDrawableWrapper((AdaptiveIconDrawable) drawable);
        clockDrawableWrapper.mTargetSdkVersion = applicationInfo.targetSdkVersion;
        AnimationInfo animationInfo = clockDrawableWrapper.mAnimationInfo;
        animationInfo.baseDrawableState = drawable.getConstantState();
        animationInfo.hourLayerIndex = bundle.getInt(HOUR_INDEX_METADATA_KEY, -1);
        animationInfo.minuteLayerIndex = bundle.getInt(MINUTE_INDEX_METADATA_KEY, -1);
        animationInfo.secondLayerIndex = bundle.getInt(SECOND_INDEX_METADATA_KEY, -1);
        animationInfo.defaultHour = bundle.getInt(DEFAULT_HOUR_METADATA_KEY, 0);
        animationInfo.defaultMinute = bundle.getInt(DEFAULT_MINUTE_METADATA_KEY, 0);
        animationInfo.defaultSecond = bundle.getInt(DEFAULT_SECOND_METADATA_KEY, 0);
        LayerDrawable layerDrawable = (LayerDrawable) clockDrawableWrapper.getForeground();
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        if (animationInfo.hourLayerIndex < 0 || animationInfo.hourLayerIndex >= numberOfLayers) {
            animationInfo.hourLayerIndex = -1;
        }
        if (animationInfo.minuteLayerIndex < 0 || animationInfo.minuteLayerIndex >= numberOfLayers) {
            animationInfo.minuteLayerIndex = -1;
        }
        if (animationInfo.secondLayerIndex < 0 || animationInfo.secondLayerIndex >= numberOfLayers) {
            animationInfo.secondLayerIndex = -1;
        } else {
            layerDrawable.setDrawable(animationInfo.secondLayerIndex, (Drawable) null);
            animationInfo.secondLayerIndex = -1;
        }
        animationInfo.applyTime(Calendar.getInstance(), layerDrawable);
        return clockDrawableWrapper;
    }

    public ClockBitmapInfo getExtendedInfo(Bitmap bitmap, int i, BaseIconFactory baseIconFactory, float f, UserHandle userHandle) {
        baseIconFactory.disableColorExtraction();
        AdaptiveIconDrawable adaptiveIconDrawable = new AdaptiveIconDrawable(getBackground().getConstantState().newDrawable(), (Drawable) null);
        if (userHandle == null) {
            userHandle = Process.myUserHandle();
        }
        return new ClockBitmapInfo(bitmap, i, f, this.mAnimationInfo, baseIconFactory.createBadgedIconBitmap(adaptiveIconDrawable, userHandle, this.mTargetSdkVersion, false).icon, this.mThemeData);
    }

    public void drawForPersistence(Canvas canvas) {
        LayerDrawable layerDrawable = (LayerDrawable) getForeground();
        resetLevel(layerDrawable, this.mAnimationInfo.hourLayerIndex);
        resetLevel(layerDrawable, this.mAnimationInfo.minuteLayerIndex);
        resetLevel(layerDrawable, this.mAnimationInfo.secondLayerIndex);
        draw(canvas);
        this.mAnimationInfo.applyTime(Calendar.getInstance(), (LayerDrawable) getForeground());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r2 = fromThemeData(r2, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Drawable getThemedDrawable(android.content.Context r2) {
        /*
            r1 = this;
            com.android.launcher3.icons.ThemedIconDrawable$ThemeData r0 = r1.mThemeData
            if (r0 == 0) goto L_0x000c
            com.android.launcher3.icons.ClockDrawableWrapper r2 = fromThemeData(r2, r0)
            if (r2 != 0) goto L_0x000b
            goto L_0x000c
        L_0x000b:
            r1 = r2
        L_0x000c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.ClockDrawableWrapper.getThemedDrawable(android.content.Context):android.graphics.drawable.Drawable");
    }

    private void resetLevel(LayerDrawable layerDrawable, int i) {
        if (i != -1) {
            layerDrawable.getDrawable(i).setLevel(0);
        }
    }

    private static class AnimationInfo {
        public Drawable.ConstantState baseDrawableState;
        public int defaultHour;
        public int defaultMinute;
        public int defaultSecond;
        public int hourLayerIndex;
        public int minuteLayerIndex;
        public int secondLayerIndex;

        private AnimationInfo() {
        }

        /* access modifiers changed from: package-private */
        public boolean applyTime(Calendar calendar, LayerDrawable layerDrawable) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            int i = (calendar.get(10) + (12 - this.defaultHour)) % 12;
            int i2 = (calendar.get(12) + (60 - this.defaultMinute)) % 60;
            int i3 = (calendar.get(13) + (60 - this.defaultSecond)) % 60;
            int i4 = this.hourLayerIndex;
            boolean z = i4 != -1 && layerDrawable.getDrawable(i4).setLevel((i * 60) + calendar.get(12));
            int i5 = this.minuteLayerIndex;
            if (i5 != -1 && layerDrawable.getDrawable(i5).setLevel((calendar.get(10) * 60) + i2)) {
                z = true;
            }
            int i6 = this.secondLayerIndex;
            if (i6 == -1 || !layerDrawable.getDrawable(i6).setLevel(i3 * 10)) {
                return z;
            }
            return true;
        }
    }

    static class ClockBitmapInfo extends BitmapInfo {
        public final AnimationInfo animInfo;
        public final ColorFilter bgFilter;
        public final Bitmap mFlattenedBackground;
        public final int offset;
        public final float scale;
        public final ThemedIconDrawable.ThemeData themeData;

        public byte[] toByteArray() {
            return null;
        }

        ClockBitmapInfo(Bitmap bitmap, int i, float f, AnimationInfo animationInfo, Bitmap bitmap2, ThemedIconDrawable.ThemeData themeData2) {
            this(bitmap, i, f, animationInfo, bitmap2, themeData2, (ColorFilter) null);
        }

        ClockBitmapInfo(Bitmap bitmap, int i, float f, AnimationInfo animationInfo, Bitmap bitmap2, ThemedIconDrawable.ThemeData themeData2, ColorFilter colorFilter) {
            super(bitmap, i);
            this.scale = f;
            this.animInfo = animationInfo;
            this.offset = (int) Math.ceil((double) (((float) bitmap.getWidth()) * 0.03125f));
            this.mFlattenedBackground = bitmap2;
            this.themeData = themeData2;
            this.bgFilter = colorFilter;
        }

        public FastBitmapDrawable newThemedIcon(Context context) {
            ClockDrawableWrapper access$100;
            ThemedIconDrawable.ThemeData themeData2 = this.themeData;
            if (themeData2 == null || (access$100 = ClockDrawableWrapper.fromThemeData(context, themeData2)) == null) {
                return super.newThemedIcon(context);
            }
            int[] colors = ThemedIconDrawable.getColors(context);
            return new ClockBitmapInfo(this.icon, colors[1], this.scale, access$100.mAnimationInfo, this.mFlattenedBackground, this.themeData, new PorterDuffColorFilter(colors[0], PorterDuff.Mode.SRC_ATOP)).newIcon(context);
        }

        public FastBitmapDrawable newIcon(Context context) {
            ClockIconDrawable clockIconDrawable = new ClockIconDrawable(this);
            clockIconDrawable.mDisabledAlpha = GraphicsUtils.getFloat(context, C0738R.attr.disabledIconAlpha, 1.0f);
            return clockIconDrawable;
        }

        /* access modifiers changed from: package-private */
        public void drawBackground(Canvas canvas, Rect rect, Paint paint) {
            ColorFilter colorFilter = paint.getColorFilter();
            ColorFilter colorFilter2 = this.bgFilter;
            if (colorFilter2 != null) {
                paint.setColorFilter(colorFilter2);
            }
            canvas.drawBitmap(this.mFlattenedBackground, (Rect) null, rect, paint);
            paint.setColorFilter(colorFilter);
        }
    }

    public static class ClockIconDrawable extends FastBitmapDrawable implements Runnable {
        private final LayerDrawable mForeground;
        private final AdaptiveIconDrawable mFullDrawable;
        private final ClockBitmapInfo mInfo;
        private final Calendar mTime = Calendar.getInstance();

        ClockIconDrawable(ClockBitmapInfo clockBitmapInfo) {
            super((BitmapInfo) clockBitmapInfo);
            this.mInfo = clockBitmapInfo;
            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) clockBitmapInfo.animInfo.baseDrawableState.newDrawable().mutate();
            this.mFullDrawable = adaptiveIconDrawable;
            this.mForeground = (LayerDrawable) adaptiveIconDrawable.getForeground();
        }

        /* access modifiers changed from: protected */
        public void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            this.mFullDrawable.setBounds(rect);
        }

        public void drawInternal(Canvas canvas, Rect rect) {
            ClockBitmapInfo clockBitmapInfo = this.mInfo;
            if (clockBitmapInfo == null) {
                super.drawInternal(canvas, rect);
                return;
            }
            clockBitmapInfo.drawBackground(canvas, rect, this.mPaint);
            this.mInfo.animInfo.applyTime(this.mTime, this.mForeground);
            canvas.scale(this.mInfo.scale, this.mInfo.scale, rect.exactCenterX() + ((float) this.mInfo.offset), rect.exactCenterY() + ((float) this.mInfo.offset));
            canvas.clipPath(this.mFullDrawable.getIconMask());
            this.mForeground.draw(canvas);
            reschedule();
        }

        public boolean isThemed() {
            return this.mInfo.bgFilter != null;
        }

        /* access modifiers changed from: protected */
        public void updateFilter() {
            super.updateFilter();
            this.mFullDrawable.setColorFilter(this.mPaint.getColorFilter());
        }

        public void run() {
            if (this.mInfo.animInfo.applyTime(this.mTime, this.mForeground)) {
                invalidateSelf();
            } else {
                reschedule();
            }
        }

        public boolean setVisible(boolean z, boolean z2) {
            boolean visible = super.setVisible(z, z2);
            if (z) {
                reschedule();
            } else {
                unscheduleSelf(this);
            }
            return visible;
        }

        private void reschedule() {
            if (isVisible()) {
                unscheduleSelf(this);
                long uptimeMillis = SystemClock.uptimeMillis();
                long j = ClockDrawableWrapper.TICK_MS;
                scheduleSelf(this, (uptimeMillis - (uptimeMillis % j)) + j);
            }
        }

        public Drawable.ConstantState getConstantState() {
            return new ClockConstantState(this.mInfo, isDisabled());
        }

        private static class ClockConstantState extends FastBitmapDrawable.FastBitmapConstantState {
            private final ClockBitmapInfo mInfo;

            ClockConstantState(ClockBitmapInfo clockBitmapInfo, boolean z) {
                super(clockBitmapInfo.icon, clockBitmapInfo.color, z);
                this.mInfo = clockBitmapInfo;
            }

            public FastBitmapDrawable newDrawable() {
                ClockIconDrawable clockIconDrawable = new ClockIconDrawable(this.mInfo);
                clockIconDrawable.setIsDisabled(this.mIsDisabled);
                return clockIconDrawable;
            }
        }
    }
}
