package com.motorola.personalize.view;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DynamicAdaptiveIconDrawable extends Drawable implements Drawable.Callback {
    private static final int BACKGROUND_ID = 0;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final float EXTRA_INSET_PERCENTAGE = 0.25f;
    private static final int FOREGROUND_ID = 1;
    private static final float MASK_SIZE = 100.0f;
    private final Canvas mCanvas;
    private boolean mChildRequestedInvalidation;
    private Rect mHotspotBounds;
    private LayerState mLayerState;
    private Bitmap mLayersBitmap;
    private Shader mLayersShader;
    private final Path mMask;
    private final Matrix mMaskMatrix;
    private final Path mMaskScaleOnly;
    private boolean mMutated;
    private final Path mOriginalMask;
    private Paint mPaint;
    private boolean mSuspendChildInvalidation;
    private final Rect mTmpOutRect;
    private final Region mTransparentRegion;

    public int getOpacity() {
        return -3;
    }

    DynamicAdaptiveIconDrawable() {
        this((LayerState) null, (Resources) null, (Path) null);
    }

    private DynamicAdaptiveIconDrawable(LayerState layerState, Resources resources, Path path) {
        this.mTmpOutRect = new Rect();
        this.mPaint = new Paint(7);
        this.mLayerState = createConstantState(layerState, resources);
        this.mOriginalMask = path;
        Path path2 = new Path(path);
        this.mMask = path2;
        this.mMaskScaleOnly = new Path(path2);
        this.mMaskMatrix = new Matrix();
        this.mCanvas = new Canvas();
        this.mTransparentRegion = new Region();
    }

    private ChildDrawable createChildDrawable(Drawable drawable) {
        ChildDrawable childDrawable = new ChildDrawable(this.mLayerState.mDensity);
        childDrawable.mDrawable = drawable;
        childDrawable.mDrawable.setCallback(this);
        this.mLayerState.mChildrenChangingConfigurations |= childDrawable.mDrawable.getChangingConfigurations();
        return childDrawable;
    }

    private LayerState createConstantState(LayerState layerState, Resources resources) {
        return new LayerState(layerState, this, resources);
    }

    public DynamicAdaptiveIconDrawable(Drawable drawable, Drawable drawable2, Path path) {
        this((LayerState) null, (Resources) null, path);
        if (drawable != null) {
            addLayer(0, createChildDrawable(drawable));
        }
        if (drawable2 != null) {
            addLayer(1, createChildDrawable(drawable2));
        }
    }

    private void addLayer(int i, ChildDrawable childDrawable) {
        this.mLayerState.mChildren[i] = childDrawable;
        this.mLayerState.invalidateCache();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        if (this.mLayerState != null) {
            inflateLayers(resources, xmlPullParser, attributeSet, theme);
        }
    }

    public Path getIconMask() {
        return this.mMask;
    }

    public Drawable getForeground() {
        return this.mLayerState.mChildren[1].mDrawable;
    }

    public Drawable getBackground() {
        return this.mLayerState.mChildren[0].mDrawable;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (!rect.isEmpty()) {
            updateLayerBounds(rect);
        }
    }

    private void updateLayerBounds(Rect rect) {
        if (!rect.isEmpty()) {
            try {
                suspendChildInvalidation();
                updateLayerBoundsInternal(rect);
                updateMaskBoundsInternal(rect);
            } finally {
                resumeChildInvalidation();
            }
        }
    }

    private void updateLayerBoundsInternal(Rect rect) {
        Drawable drawable;
        int width = rect.width() / 2;
        int height = rect.height() / 2;
        for (int i = 0; i < 2; i++) {
            ChildDrawable childDrawable = this.mLayerState.mChildren[i];
            if (!(childDrawable == null || (drawable = childDrawable.mDrawable) == null)) {
                int width2 = (int) (((float) rect.width()) / 1.3333334f);
                int height2 = (int) (((float) rect.height()) / 1.3333334f);
                Rect rect2 = this.mTmpOutRect;
                rect2.set(width - width2, height - height2, width2 + width, height2 + height);
                drawable.setBounds(rect2);
            }
        }
    }

    private void updateMaskBoundsInternal(Rect rect) {
        this.mMaskMatrix.setScale(((float) rect.width()) / 100.0f, ((float) rect.height()) / 100.0f);
        this.mOriginalMask.transform(this.mMaskMatrix, this.mMaskScaleOnly);
        this.mMaskMatrix.postTranslate((float) rect.left, (float) rect.top);
        this.mOriginalMask.transform(this.mMaskMatrix, this.mMask);
        Bitmap bitmap = this.mLayersBitmap;
        if (!(bitmap != null && bitmap.getWidth() == rect.width() && this.mLayersBitmap.getHeight() == rect.height())) {
            this.mLayersBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        }
        this.mPaint.setShader((Shader) null);
        this.mTransparentRegion.setEmpty();
        this.mLayersShader = null;
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        Bitmap bitmap = this.mLayersBitmap;
        if (bitmap != null) {
            if (this.mLayersShader == null) {
                this.mCanvas.setBitmap(bitmap);
                this.mCanvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
                int i = 0;
                while (true) {
                    LayerState layerState = this.mLayerState;
                    if (i >= 2) {
                        break;
                    }
                    if (!(layerState.mChildren[i] == null || (drawable = this.mLayerState.mChildren[i].mDrawable) == null)) {
                        drawable.draw(this.mCanvas);
                    }
                    i++;
                }
                BitmapShader bitmapShader = new BitmapShader(this.mLayersBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                this.mLayersShader = bitmapShader;
                this.mPaint.setShader(bitmapShader);
            }
            if (this.mMaskScaleOnly != null) {
                Rect bounds = getBounds();
                canvas.translate((float) bounds.left, (float) bounds.top);
                canvas.drawPath(this.mMaskScaleOnly, this.mPaint);
                canvas.translate((float) (-bounds.left), (float) (-bounds.top));
            }
        }
    }

    public void invalidateSelf() {
        this.mLayersShader = null;
        super.invalidateSelf();
    }

    public void getOutline(Outline outline) {
        outline.setConvexPath(this.mMask);
    }

    public Region getTransparentRegion() {
        if (this.mTransparentRegion.isEmpty()) {
            this.mMask.toggleInverseFillType();
            this.mTransparentRegion.set(getBounds());
            Region region = this.mTransparentRegion;
            region.setPath(this.mMask, region);
            this.mMask.toggleInverseFillType();
        }
        return this.mTransparentRegion;
    }

    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        LayerState layerState = this.mLayerState;
        if (layerState != null) {
            ChildDrawable[] childDrawableArr = layerState.mChildren;
            for (int i = 0; i < 2; i++) {
                Drawable drawable = childDrawableArr[i].mDrawable;
                if (drawable != null && drawable.canApplyTheme()) {
                    drawable.applyTheme(theme);
                    layerState.mChildrenChangingConfigurations = drawable.getChangingConfigurations() | layerState.mChildrenChangingConfigurations;
                }
            }
        }
    }

    private void inflateLayers(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int i;
        int next;
        LayerState layerState = this.mLayerState;
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next2 = xmlPullParser.next();
            if (next2 != 1) {
                int depth2 = xmlPullParser.getDepth();
                if (depth2 < depth && next2 == 3) {
                    return;
                }
                if (next2 == 2 && depth2 <= depth) {
                    String name = xmlPullParser.getName();
                    if (name.equals("background")) {
                        i = 0;
                    } else if (name.equals("foreground")) {
                        i = 1;
                    } else {
                        continue;
                    }
                    ChildDrawable childDrawable = new ChildDrawable(layerState.mDensity);
                    if (childDrawable.mDrawable == null && childDrawable.mThemeAttrs == null) {
                        do {
                            next = xmlPullParser.next();
                        } while (next == 4);
                        if (next == 2) {
                            childDrawable.mDrawable = Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
                            childDrawable.mDrawable.setCallback(this);
                            layerState.mChildrenChangingConfigurations |= childDrawable.mDrawable.getChangingConfigurations();
                        } else {
                            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <foreground> or <background> tag requires a 'drawable'attribute or child tag defining a drawable");
                        }
                    }
                    addLayer(i, childDrawable);
                }
            } else {
                return;
            }
        }
    }

    public boolean canApplyTheme() {
        LayerState layerState = this.mLayerState;
        return (layerState != null && layerState.canApplyTheme()) || super.canApplyTheme();
    }

    public boolean isProjected() {
        if (super.isProjected()) {
            return true;
        }
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            if (childDrawableArr[i].mDrawable != null && childDrawableArr[i].mDrawable.isProjected()) {
                return true;
            }
        }
        return false;
    }

    private void suspendChildInvalidation() {
        this.mSuspendChildInvalidation = true;
    }

    private void resumeChildInvalidation() {
        this.mSuspendChildInvalidation = false;
        if (this.mChildRequestedInvalidation) {
            this.mChildRequestedInvalidation = false;
            invalidateSelf();
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mSuspendChildInvalidation) {
            this.mChildRequestedInvalidation = true;
        } else {
            invalidateSelf();
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public int getChangingConfigurations() {
        return this.mLayerState.getChangingConfigurations() | super.getChangingConfigurations();
    }

    public void setHotspot(float f, float f2) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.setHotspot(f, f2);
            }
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i5 = 0; i5 < 2; i5++) {
            Drawable drawable = childDrawableArr[i5].mDrawable;
            if (drawable != null) {
                drawable.setHotspotBounds(i, i2, i3, i4);
            }
        }
        Rect rect = this.mHotspotBounds;
        if (rect == null) {
            this.mHotspotBounds = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
    }

    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.mHotspotBounds;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.setVisible(z, z2);
            }
        }
        return visible;
    }

    public void setDither(boolean z) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.setTintList(colorStateList);
            }
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.setTintMode(mode);
            }
        }
    }

    public void setOpacity(int i) {
        this.mLayerState.mOpacityOverride = i;
    }

    public void setAutoMirrored(boolean z) {
        boolean unused = this.mLayerState.mAutoMirrored = z;
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.setAutoMirrored(z);
            }
        }
    }

    public boolean isAutoMirrored() {
        return this.mLayerState.mAutoMirrored;
    }

    public void jumpToCurrentState() {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null) {
                drawable.jumpToCurrentState();
            }
        }
    }

    public boolean isStateful() {
        return this.mLayerState.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        boolean z = false;
        for (int i = 0; i < 2; i++) {
            Drawable drawable = childDrawableArr[i].mDrawable;
            if (drawable != null && drawable.isStateful() && drawable.setState(iArr)) {
                z = true;
            }
        }
        if (z) {
            updateLayerBounds(getBounds());
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        ChildDrawable[] childDrawableArr = this.mLayerState.mChildren;
        boolean z = false;
        for (int i2 = 0; i2 < 2; i2++) {
            Drawable drawable = childDrawableArr[i2].mDrawable;
            if (drawable != null && drawable.setLevel(i)) {
                z = true;
            }
        }
        if (z) {
            updateLayerBounds(getBounds());
        }
        return z;
    }

    public int getIntrinsicWidth() {
        return (int) (((float) getMaxIntrinsicWidth()) * DEFAULT_VIEW_PORT_SCALE);
    }

    private int getMaxIntrinsicWidth() {
        int intrinsicWidth;
        int i = -1;
        int i2 = 0;
        while (true) {
            LayerState layerState = this.mLayerState;
            if (i2 >= 2) {
                return i;
            }
            ChildDrawable childDrawable = layerState.mChildren[i2];
            if (childDrawable.mDrawable != null && (intrinsicWidth = childDrawable.mDrawable.getIntrinsicWidth()) > i) {
                i = intrinsicWidth;
            }
            i2++;
        }
    }

    public int getIntrinsicHeight() {
        return (int) (((float) getMaxIntrinsicHeight()) * DEFAULT_VIEW_PORT_SCALE);
    }

    private int getMaxIntrinsicHeight() {
        int intrinsicHeight;
        int i = -1;
        int i2 = 0;
        while (true) {
            LayerState layerState = this.mLayerState;
            if (i2 >= 2) {
                return i;
            }
            ChildDrawable childDrawable = layerState.mChildren[i2];
            if (childDrawable.mDrawable != null && (intrinsicHeight = childDrawable.mDrawable.getIntrinsicHeight()) > i) {
                i = intrinsicHeight;
            }
            i2++;
        }
    }

    public Drawable.ConstantState getConstantState() {
        if (!this.mLayerState.canConstantState()) {
            return null;
        }
        this.mLayerState.mChangingConfigurations = getChangingConfigurations();
        return this.mLayerState;
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mLayerState = createConstantState(this.mLayerState, (Resources) null);
            int i = 0;
            while (true) {
                LayerState layerState = this.mLayerState;
                if (i >= 2) {
                    break;
                }
                Drawable drawable = layerState.mChildren[i].mDrawable;
                if (drawable != null) {
                    drawable.mutate();
                }
                i++;
            }
            this.mMutated = true;
        }
        return this;
    }

    static class ChildDrawable {
        public int mDensity = 160;
        public Drawable mDrawable;
        public int[] mThemeAttrs;

        ChildDrawable(int i) {
            this.mDensity = i;
        }

        ChildDrawable(ChildDrawable childDrawable, DynamicAdaptiveIconDrawable dynamicAdaptiveIconDrawable, Resources resources) {
            Drawable drawable;
            Drawable drawable2 = childDrawable.mDrawable;
            if (drawable2 != null) {
                Drawable.ConstantState constantState = drawable2.getConstantState();
                if (constantState == null) {
                    drawable = drawable2;
                } else if (resources != null) {
                    drawable = constantState.newDrawable(resources);
                } else {
                    drawable = constantState.newDrawable();
                }
                drawable.setCallback(dynamicAdaptiveIconDrawable);
                drawable.setBounds(drawable2.getBounds());
                drawable.setLevel(drawable2.getLevel());
            } else {
                drawable = null;
            }
            this.mDrawable = drawable;
            this.mThemeAttrs = childDrawable.mThemeAttrs;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
            r1 = r1.mDrawable;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean canApplyTheme() {
            /*
                r1 = this;
                int[] r0 = r1.mThemeAttrs
                if (r0 != 0) goto L_0x0011
                android.graphics.drawable.Drawable r1 = r1.mDrawable
                if (r1 == 0) goto L_0x000f
                boolean r1 = r1.canApplyTheme()
                if (r1 == 0) goto L_0x000f
                goto L_0x0011
            L_0x000f:
                r1 = 0
                goto L_0x0012
            L_0x0011:
                r1 = 1
            L_0x0012:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.view.DynamicAdaptiveIconDrawable.ChildDrawable.canApplyTheme():boolean");
        }

        public final void setDensity(int i) {
            if (this.mDensity != i) {
                this.mDensity = i;
            }
        }
    }

    static class LayerState extends Drawable.ConstantState {
        static final int N_CHILDREN = 2;
        /* access modifiers changed from: private */
        public boolean mAutoMirrored = false;
        int mChangingConfigurations;
        private boolean mCheckedOpacity;
        private boolean mCheckedStateful;
        ChildDrawable[] mChildren = new ChildDrawable[2];
        int mChildrenChangingConfigurations;
        int mDensity;
        private boolean mIsStateful;
        private int mOpacity;
        int mOpacityOverride = 0;
        int mSrcDensityOverride = 0;
        private int[] mThemeAttrs;

        LayerState(LayerState layerState, DynamicAdaptiveIconDrawable dynamicAdaptiveIconDrawable, Resources resources) {
            int i = 0;
            if (layerState != null) {
                ChildDrawable[] childDrawableArr = layerState.mChildren;
                this.mChangingConfigurations = layerState.mChangingConfigurations;
                this.mChildrenChangingConfigurations = layerState.mChildrenChangingConfigurations;
                while (i < 2) {
                    this.mChildren[i] = new ChildDrawable(childDrawableArr[i], dynamicAdaptiveIconDrawable, resources);
                    i++;
                }
                this.mCheckedOpacity = layerState.mCheckedOpacity;
                this.mOpacity = layerState.mOpacity;
                this.mCheckedStateful = layerState.mCheckedStateful;
                this.mIsStateful = layerState.mIsStateful;
                this.mAutoMirrored = layerState.mAutoMirrored;
                this.mThemeAttrs = layerState.mThemeAttrs;
                this.mOpacityOverride = layerState.mOpacityOverride;
                this.mSrcDensityOverride = layerState.mSrcDensityOverride;
                return;
            }
            while (i < 2) {
                this.mChildren[i] = new ChildDrawable(this.mDensity);
                i++;
            }
        }

        public final void setDensity(int i) {
            if (this.mDensity != i) {
                this.mDensity = i;
            }
        }

        public boolean canApplyTheme() {
            if (this.mThemeAttrs != null || super.canApplyTheme()) {
                return true;
            }
            ChildDrawable[] childDrawableArr = this.mChildren;
            for (int i = 0; i < 2; i++) {
                if (childDrawableArr[i].canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        public Drawable newDrawable() {
            return new DynamicAdaptiveIconDrawable(this, (Resources) null, (Path) null);
        }

        public Drawable newDrawable(Resources resources) {
            return new DynamicAdaptiveIconDrawable(this, resources, (Path) null);
        }

        public int getChangingConfigurations() {
            return this.mChildrenChangingConfigurations | this.mChangingConfigurations;
        }

        public final int getOpacity() {
            if (this.mCheckedOpacity) {
                return this.mOpacity;
            }
            ChildDrawable[] childDrawableArr = this.mChildren;
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= 2) {
                    break;
                } else if (childDrawableArr[i2].mDrawable != null) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            int opacity = i >= 0 ? childDrawableArr[i].mDrawable.getOpacity() : -2;
            for (int i3 = i + 1; i3 < 2; i3++) {
                Drawable drawable = childDrawableArr[i3].mDrawable;
                if (drawable != null) {
                    opacity = Drawable.resolveOpacity(opacity, drawable.getOpacity());
                }
            }
            this.mOpacity = opacity;
            this.mCheckedOpacity = true;
            return opacity;
        }

        public final boolean isStateful() {
            if (this.mCheckedStateful) {
                return this.mIsStateful;
            }
            ChildDrawable[] childDrawableArr = this.mChildren;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i < 2) {
                    Drawable drawable = childDrawableArr[i].mDrawable;
                    if (drawable != null && drawable.isStateful()) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            this.mIsStateful = z;
            this.mCheckedStateful = true;
            return z;
        }

        public final boolean canConstantState() {
            ChildDrawable[] childDrawableArr = this.mChildren;
            for (int i = 0; i < 2; i++) {
                Drawable drawable = childDrawableArr[i].mDrawable;
                if (drawable != null && drawable.getConstantState() == null) {
                    return false;
                }
            }
            return true;
        }

        public void invalidateCache() {
            this.mCheckedOpacity = false;
            this.mCheckedStateful = false;
        }
    }
}
