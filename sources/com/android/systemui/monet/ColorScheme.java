package com.android.systemui.monet;

import android.app.WallpaperColors;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 &2\u00020\u0001:\u0001&B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\b\u0010$\u001a\u00020%H\u0016R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\f8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000eR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\f8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000eR\u0011\u0010\u001a\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000eR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015¨\u0006'"}, mo15462d2 = {"Lcom/android/systemui/monet/ColorScheme;", "", "wallpaperColors", "Landroid/app/WallpaperColors;", "darkTheme", "", "(Landroid/app/WallpaperColors;Z)V", "seed", "", "brandingSeed", "(IIZ)V", "accent1", "", "getAccent1", "()Ljava/util/List;", "accent2", "getAccent2", "accent3", "getAccent3", "accentColor", "getAccentColor", "()I", "allAccentColors", "getAllAccentColors", "allNeutralColors", "getAllNeutralColors", "backgroundColor", "getBackgroundColor", "getBrandingSeed", "getDarkTheme", "()Z", "neutral1", "getNeutral1", "neutral2", "getNeutral2", "getSeed", "toString", "", "Companion", "common_ReflectApiDebug"}, mo15463k = 1, mo15464mv = {1, 4, 2})
/* compiled from: ColorScheme.kt */
public final class ColorScheme {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<Integer> accent1;
    private final List<Integer> accent2;
    private final List<Integer> accent3;
    private final int brandingSeed;
    private final boolean darkTheme;
    private final List<Integer> neutral1;
    private final List<Integer> neutral2;
    private final int seed;

    @JvmStatic
    public static final int getSeedColor(WallpaperColors wallpaperColors) {
        return Companion.getSeedColor(wallpaperColors);
    }

    @JvmStatic
    public static final List<Integer> getSeedColors(WallpaperColors wallpaperColors) {
        return Companion.getSeedColors(wallpaperColors);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0021, code lost:
        if (r5.getChroma() < ((float) 5)) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ColorScheme(int r3, int r4, boolean r5) {
        /*
            r2 = this;
            r2.<init>()
            r2.seed = r3
            r2.brandingSeed = r4
            r2.darkTheme = r5
            com.android.systemui.monet.CamProxy r5 = com.android.systemui.monet.CamProxy.fromInt(r3)
            r0 = -14979341(0xffffffffff1b6ef3, float:-2.0660642E38)
            if (r3 != 0) goto L_0x0014
        L_0x0012:
            r3 = r0
            goto L_0x0024
        L_0x0014:
            java.lang.String r1 = "proposedSeedCam"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)
            float r5 = r5.getChroma()
            r1 = 5
            float r1 = (float) r1
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 >= 0) goto L_0x0024
            goto L_0x0012
        L_0x0024:
            com.android.systemui.monet.CamProxy r3 = com.android.systemui.monet.CamProxy.fromInt(r3)
            java.lang.String r5 = "camSeed"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            float r5 = r3.getHue()
            float r3 = r3.getChroma()
            r0 = 1111490560(0x42400000, float:48.0)
            float r3 = kotlin.ranges.RangesKt.coerceAtLeast((float) r3, (float) r0)
            int[] r3 = com.android.systemui.monet.Shades.m26of(r5, r3)
            java.lang.String r0 = "Shades.of(hue, chroma)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            java.util.List r3 = kotlin.collections.ArraysKt.toList((int[]) r3)
            r2.accent1 = r3
            r3 = 1098907648(0x41800000, float:16.0)
            int[] r3 = com.android.systemui.monet.Shades.m26of(r5, r3)
            java.lang.String r0 = "Shades.of(hue, ACCENT2_CHROMA)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            java.util.List r3 = kotlin.collections.ArraysKt.toList((int[]) r3)
            r2.accent2 = r3
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = 1107296256(0x42000000, float:32.0)
            if (r4 != r3) goto L_0x0074
            r3 = 1114636288(0x42700000, float:60.0)
            float r3 = r3 + r5
            int[] r3 = com.android.systemui.monet.Shades.m26of(r3, r0)
            java.lang.String r4 = "Shades.of(hue + ACCENT3_HUE_SHIFT, ACCENT3_CHROMA)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.util.List r3 = kotlin.collections.ArraysKt.toList((int[]) r3)
            r2.accent3 = r3
            goto L_0x0090
        L_0x0074:
            com.android.systemui.monet.CamProxy r3 = com.android.systemui.monet.CamProxy.fromInt(r4)
            java.lang.String r4 = "brandingCamSeed"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            float r3 = r3.getHue()
            int[] r3 = com.android.systemui.monet.Shades.m26of(r3, r0)
            java.lang.String r4 = "Shades.of(brandingCamSeed.hue, ACCENT3_CHROMA)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.util.List r3 = kotlin.collections.ArraysKt.toList((int[]) r3)
            r2.accent3 = r3
        L_0x0090:
            r3 = 1082130432(0x40800000, float:4.0)
            int[] r3 = com.android.systemui.monet.Shades.m26of(r5, r3)
            java.lang.String r4 = "Shades.of(hue, NEUTRAL1_CHROMA)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.util.List r3 = kotlin.collections.ArraysKt.toList((int[]) r3)
            r2.neutral1 = r3
            r3 = 1090519040(0x41000000, float:8.0)
            int[] r3 = com.android.systemui.monet.Shades.m26of(r5, r3)
            java.lang.String r4 = "Shades.of(hue, NEUTRAL2_CHROMA)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.util.List r3 = kotlin.collections.ArraysKt.toList((int[]) r3)
            r2.neutral2 = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.monet.ColorScheme.<init>(int, int, boolean):void");
    }

    public final int getBrandingSeed() {
        return this.brandingSeed;
    }

    public final boolean getDarkTheme() {
        return this.darkTheme;
    }

    public final int getSeed() {
        return this.seed;
    }

    public final List<Integer> getAccent1() {
        return this.accent1;
    }

    public final List<Integer> getAccent2() {
        return this.accent2;
    }

    public final List<Integer> getAccent3() {
        return this.accent3;
    }

    public final List<Integer> getNeutral1() {
        return this.neutral1;
    }

    public final List<Integer> getNeutral2() {
        return this.neutral2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ColorScheme(WallpaperColors wallpaperColors, boolean z) {
        this(Companion.getSeedColor(wallpaperColors), Integer.MIN_VALUE, z);
        Intrinsics.checkNotNullParameter(wallpaperColors, "wallpaperColors");
    }

    public final List<Integer> getAllAccentColors() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.addAll(this.accent1);
        arrayList.addAll(this.accent2);
        arrayList.addAll(this.accent3);
        return arrayList;
    }

    public final List<Integer> getAllNeutralColors() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.addAll(this.neutral1);
        arrayList.addAll(this.neutral2);
        return arrayList;
    }

    public final int getBackgroundColor() {
        return ColorUtils.setAlphaComponent(this.neutral1.get(this.darkTheme ? 8 : 0).intValue(), 255);
    }

    public final int getAccentColor() {
        return ColorUtils.setAlphaComponent(this.accent1.get(this.darkTheme ? 2 : 6).intValue(), 255);
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("ColorScheme {\n").append("  neutral1: ");
        Companion companion = Companion;
        return append.append(companion.humanReadable(this.neutral1)).append(10).append("  neutral2: ").append(companion.humanReadable(this.neutral2)).append(10).append("  accent1: ").append(companion.humanReadable(this.accent1)).append(10).append("  accent2: ").append(companion.humanReadable(this.accent2)).append(10).append("  accent3: ").append(companion.humanReadable(this.accent3)).append(10).append("}").toString();
    }

    @Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002J6\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\u0010H\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0002J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000eH\u0002J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002¨\u0006\u001b"}, mo15462d2 = {"Lcom/android/systemui/monet/ColorScheme$Companion;", "", "()V", "getSeedColor", "", "wallpaperColors", "Landroid/app/WallpaperColors;", "getSeedColors", "", "hueDiff", "", "a", "b", "huePopulations", "", "camByColor", "", "Lcom/android/systemui/monet/CamProxy;", "populationByColor", "humanReadable", "", "colors", "score", "cam", "proportion", "wrapDegrees", "degrees", "common_ReflectApiDebug"}, mo15463k = 1, mo15464mv = {1, 4, 2})
    /* compiled from: ColorScheme.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final int getSeedColor(WallpaperColors wallpaperColors) {
            Intrinsics.checkNotNullParameter(wallpaperColors, "wallpaperColors");
            return ((Number) CollectionsKt.first(getSeedColors(wallpaperColors))).intValue();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:86:0x034e, code lost:
            if (r4 == r5) goto L_0x0352;
         */
        @kotlin.jvm.JvmStatic
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.List<java.lang.Integer> getSeedColors(android.app.WallpaperColors r19) {
            /*
                r18 = this;
                java.lang.String r0 = "wallpaperColors"
                r1 = r19
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                java.util.Map r0 = com.android.systemui.monet.WallpaperProxy.getAllColors(r19)
                java.util.Collection r0 = r0.values()
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.Iterator r0 = r0.iterator()
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x0360
                java.lang.Object r2 = r0.next()
            L_0x001f:
                boolean r3 = r0.hasNext()
                if (r3 == 0) goto L_0x0040
                java.lang.Object r3 = r0.next()
                java.lang.Integer r3 = (java.lang.Integer) r3
                java.lang.Integer r2 = (java.lang.Integer) r2
                int r2 = r2.intValue()
                java.lang.String r4 = "b"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                int r3 = r3.intValue()
                int r2 = r2 + r3
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                goto L_0x001f
            L_0x0040:
                java.lang.Number r2 = (java.lang.Number) r2
                int r0 = r2.intValue()
                double r2 = (double) r0
                r4 = 0
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                r6 = 0
                r7 = 1
                if (r0 != 0) goto L_0x0051
                r0 = r7
                goto L_0x0052
            L_0x0051:
                r0 = r6
            L_0x0052:
                r8 = -14979341(0xffffffffff1b6ef3, float:-2.0660642E38)
                r9 = 5
                if (r0 == 0) goto L_0x00e4
                java.util.List r0 = com.android.systemui.monet.WallpaperProxy.getMainColors(r19)
                java.lang.String r1 = "WallpaperProxy.getMainColors(wallpaperColors)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r2 = 10
                int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
                r1.<init>(r2)
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r0 = r0.iterator()
            L_0x0074:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x008c
                java.lang.Object r2 = r0.next()
                android.graphics.Color r2 = (android.graphics.Color) r2
                int r2 = r2.toArgb()
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                r1.add(r2)
                goto L_0x0074
            L_0x008c:
                java.util.List r1 = (java.util.List) r1
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.List r0 = kotlin.collections.CollectionsKt.distinct(r1)
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r0 = r0.iterator()
            L_0x00a1:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x00cd
                java.lang.Object r2 = r0.next()
                r3 = r2
                java.lang.Number r3 = (java.lang.Number) r3
                int r3 = r3.intValue()
                com.android.systemui.monet.CamProxy r3 = com.android.systemui.monet.CamProxy.fromInt(r3)
                java.lang.String r4 = "CamProxy.fromInt(it)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                float r3 = r3.getChroma()
                float r4 = (float) r9
                int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
                if (r3 < 0) goto L_0x00c6
                r3 = r7
                goto L_0x00c7
            L_0x00c6:
                r3 = r6
            L_0x00c7:
                if (r3 == 0) goto L_0x00a1
                r1.add(r2)
                goto L_0x00a1
            L_0x00cd:
                java.util.List r1 = (java.util.List) r1
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.List r0 = kotlin.collections.CollectionsKt.toList(r1)
                boolean r1 = r0.isEmpty()
                if (r1 == 0) goto L_0x00e3
                java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
                java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r0)
            L_0x00e3:
                return r0
            L_0x00e4:
                java.util.Map r10 = com.android.systemui.monet.WallpaperProxy.getAllColors(r19)
                java.lang.String r11 = "WallpaperProxy.getAllColors(wallpaperColors)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
                java.util.LinkedHashMap r12 = new java.util.LinkedHashMap
                int r13 = r10.size()
                int r13 = kotlin.collections.MapsKt.mapCapacity(r13)
                r12.<init>(r13)
                java.util.Map r12 = (java.util.Map) r12
                java.util.Set r10 = r10.entrySet()
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                java.util.Iterator r10 = r10.iterator()
            L_0x0106:
                boolean r13 = r10.hasNext()
                if (r13 == 0) goto L_0x012c
                java.lang.Object r13 = r10.next()
                java.util.Map$Entry r13 = (java.util.Map.Entry) r13
                java.lang.Object r14 = r13.getKey()
                java.lang.Object r13 = r13.getValue()
                java.lang.Number r13 = (java.lang.Number) r13
                int r13 = r13.intValue()
                double r4 = (double) r13
                double r4 = r4 / r2
                java.lang.Double r4 = java.lang.Double.valueOf(r4)
                r12.put(r14, r4)
                r4 = 0
                goto L_0x0106
            L_0x012c:
                java.util.Map r2 = com.android.systemui.monet.WallpaperProxy.getAllColors(r19)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
                java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
                int r4 = r2.size()
                int r4 = kotlin.collections.MapsKt.mapCapacity(r4)
                r3.<init>(r4)
                java.util.Map r3 = (java.util.Map) r3
                java.util.Set r2 = r2.entrySet()
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.Iterator r2 = r2.iterator()
            L_0x014c:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L_0x0173
                java.lang.Object r4 = r2.next()
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r5 = r4.getKey()
                java.lang.Object r4 = r4.getKey()
                java.lang.String r10 = "it.key"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r10)
                java.lang.Number r4 = (java.lang.Number) r4
                int r4 = r4.intValue()
                com.android.systemui.monet.CamProxy r4 = com.android.systemui.monet.CamProxy.fromInt(r4)
                r3.put(r5, r4)
                goto L_0x014c
            L_0x0173:
                r2 = r18
                com.android.systemui.monet.ColorScheme$Companion r2 = (com.android.systemui.monet.ColorScheme.Companion) r2
                java.util.List r2 = r2.huePopulations(r3, r12)
                java.util.Map r1 = com.android.systemui.monet.WallpaperProxy.getAllColors(r19)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
                java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
                int r5 = r1.size()
                int r5 = kotlin.collections.MapsKt.mapCapacity(r5)
                r4.<init>(r5)
                java.util.Map r4 = (java.util.Map) r4
                java.util.Set r1 = r1.entrySet()
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Iterator r1 = r1.iterator()
            L_0x019b:
                boolean r5 = r1.hasNext()
                r10 = 15
                if (r5 == 0) goto L_0x01e8
                java.lang.Object r5 = r1.next()
                java.util.Map$Entry r5 = (java.util.Map.Entry) r5
                java.lang.Object r11 = r5.getKey()
                java.lang.Object r5 = r5.getKey()
                java.lang.Object r5 = r3.get(r5)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                com.android.systemui.monet.CamProxy r5 = (com.android.systemui.monet.CamProxy) r5
                float r5 = r5.getHue()
                int r5 = kotlin.math.MathKt.roundToInt((float) r5)
                int r12 = r5 + -15
                int r5 = r5 + r10
                r13 = 0
                if (r12 > r5) goto L_0x01e0
            L_0x01c9:
                com.android.systemui.monet.ColorScheme$Companion r10 = com.android.systemui.monet.ColorScheme.Companion
                int r10 = r10.wrapDegrees(r12)
                java.lang.Object r10 = r2.get(r10)
                java.lang.Number r10 = (java.lang.Number) r10
                double r16 = r10.doubleValue()
                double r13 = r13 + r16
                if (r12 == r5) goto L_0x01e0
                int r12 = r12 + 1
                goto L_0x01c9
            L_0x01e0:
                java.lang.Double r5 = java.lang.Double.valueOf(r13)
                r4.put(r11, r5)
                goto L_0x019b
            L_0x01e8:
                java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
                r1.<init>()
                java.util.Map r1 = (java.util.Map) r1
                java.util.Set r2 = r3.entrySet()
                java.util.Iterator r2 = r2.iterator()
            L_0x01f7:
                boolean r5 = r2.hasNext()
                if (r5 == 0) goto L_0x0244
                java.lang.Object r5 = r2.next()
                java.util.Map$Entry r5 = (java.util.Map.Entry) r5
                java.lang.Object r11 = r5.getValue()
                com.android.systemui.monet.CamProxy r11 = (com.android.systemui.monet.CamProxy) r11
                java.lang.Object r12 = r5.getKey()
                java.lang.Object r12 = r4.get(r12)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
                java.lang.Number r12 = (java.lang.Number) r12
                double r12 = r12.doubleValue()
                java.lang.String r14 = "cam"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r14)
                float r11 = r11.getChroma()
                float r14 = (float) r9
                int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
                if (r11 < 0) goto L_0x0235
                if (r0 != 0) goto L_0x0233
                r14 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
                int r11 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
                if (r11 <= 0) goto L_0x0235
            L_0x0233:
                r11 = r7
                goto L_0x0236
            L_0x0235:
                r11 = r6
            L_0x0236:
                if (r11 == 0) goto L_0x01f7
                java.lang.Object r11 = r5.getKey()
                java.lang.Object r5 = r5.getValue()
                r1.put(r11, r5)
                goto L_0x01f7
            L_0x0244:
                java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
                int r2 = r1.size()
                int r2 = kotlin.collections.MapsKt.mapCapacity(r2)
                r0.<init>(r2)
                java.util.Map r0 = (java.util.Map) r0
                java.util.Set r1 = r1.entrySet()
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Iterator r1 = r1.iterator()
            L_0x025d:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x0297
                java.lang.Object r2 = r1.next()
                java.util.Map$Entry r2 = (java.util.Map.Entry) r2
                java.lang.Object r5 = r2.getKey()
                com.android.systemui.monet.ColorScheme$Companion r9 = com.android.systemui.monet.ColorScheme.Companion
                java.lang.Object r11 = r2.getValue()
                java.lang.String r12 = "it.value"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r12)
                com.android.systemui.monet.CamProxy r11 = (com.android.systemui.monet.CamProxy) r11
                java.lang.Object r2 = r2.getKey()
                java.lang.Object r2 = r4.get(r2)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                java.lang.Number r2 = (java.lang.Number) r2
                double r12 = r2.doubleValue()
                double r11 = r9.score(r11, r12)
                java.lang.Double r2 = java.lang.Double.valueOf(r11)
                r0.put(r5, r2)
                goto L_0x025d
            L_0x0297:
                java.util.Set r0 = r0.entrySet()
                java.util.Collection r0 = (java.util.Collection) r0
                java.util.List r0 = kotlin.collections.CollectionsKt.toMutableList(r0)
                int r1 = r0.size()
                if (r1 <= r7) goto L_0x02b1
                com.android.systemui.monet.ColorScheme$Companion$getSeedColors$$inlined$sortByDescending$1 r1 = new com.android.systemui.monet.ColorScheme$Companion$getSeedColors$$inlined$sortByDescending$1
                r1.<init>()
                java.util.Comparator r1 = (java.util.Comparator) r1
                kotlin.collections.CollectionsKt.sortWith(r0, r1)
            L_0x02b1:
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.List r1 = (java.util.List) r1
                r2 = 90
                kotlin.ranges.IntProgression r2 = kotlin.ranges.RangesKt.downTo((int) r2, (int) r10)
                kotlin.ranges.IntProgression r2 = kotlin.ranges.RangesKt.step((kotlin.ranges.IntProgression) r2, (int) r7)
                int r4 = r2.getFirst()
                int r5 = r2.getLast()
                int r2 = r2.getStep()
                if (r2 < 0) goto L_0x02d3
                if (r4 > r5) goto L_0x0352
                goto L_0x02d5
            L_0x02d3:
                if (r4 < r5) goto L_0x0352
            L_0x02d5:
                r1.clear()
                java.util.Iterator r9 = r0.iterator()
            L_0x02dc:
                boolean r10 = r9.hasNext()
                if (r10 == 0) goto L_0x034e
                java.lang.Object r10 = r9.next()
                java.util.Map$Entry r10 = (java.util.Map.Entry) r10
                java.lang.Object r10 = r10.getKey()
                java.lang.Integer r10 = (java.lang.Integer) r10
                r11 = r1
                java.lang.Iterable r11 = (java.lang.Iterable) r11
                java.util.Iterator r11 = r11.iterator()
            L_0x02f5:
                boolean r12 = r11.hasNext()
                if (r12 == 0) goto L_0x0335
                java.lang.Object r12 = r11.next()
                r13 = r12
                java.lang.Number r13 = (java.lang.Number) r13
                int r13 = r13.intValue()
                java.lang.Object r14 = r3.get(r10)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
                com.android.systemui.monet.CamProxy r14 = (com.android.systemui.monet.CamProxy) r14
                float r14 = r14.getHue()
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                java.lang.Object r13 = r3.get(r13)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
                com.android.systemui.monet.CamProxy r13 = (com.android.systemui.monet.CamProxy) r13
                float r13 = r13.getHue()
                com.android.systemui.monet.ColorScheme$Companion r15 = com.android.systemui.monet.ColorScheme.Companion
                float r13 = r15.hueDiff(r14, r13)
                float r14 = (float) r4
                int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
                if (r13 >= 0) goto L_0x0331
                r13 = r7
                goto L_0x0332
            L_0x0331:
                r13 = r6
            L_0x0332:
                if (r13 == 0) goto L_0x02f5
                goto L_0x0336
            L_0x0335:
                r12 = 0
            L_0x0336:
                if (r12 == 0) goto L_0x033a
                r11 = r7
                goto L_0x033b
            L_0x033a:
                r11 = r6
            L_0x033b:
                if (r11 == 0) goto L_0x033e
                goto L_0x02dc
            L_0x033e:
                java.lang.String r11 = "int"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
                r1.add(r10)
                int r10 = r1.size()
                r11 = 4
                if (r10 < r11) goto L_0x02dc
                goto L_0x0352
            L_0x034e:
                if (r4 == r5) goto L_0x0352
                int r4 = r4 + r2
                goto L_0x02d5
            L_0x0352:
                boolean r0 = r1.isEmpty()
                if (r0 == 0) goto L_0x035f
                java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
                r1.add(r0)
            L_0x035f:
                return r1
            L_0x0360:
                java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
                java.lang.String r1 = "Empty collection can't be reduced."
                r0.<init>(r1)
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.monet.ColorScheme.Companion.getSeedColors(android.app.WallpaperColors):java.util.List");
        }

        private final int wrapDegrees(int i) {
            if (i < 0) {
                return (i % 360) + 360;
            }
            return i >= 360 ? i % 360 : i;
        }

        private final float hueDiff(float f, float f2) {
            return 180.0f - Math.abs(Math.abs(f - f2) - 180.0f);
        }

        /* access modifiers changed from: private */
        public final String humanReadable(List<Integer> list) {
            return CollectionsKt.joinToString$default(list, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, ColorScheme$Companion$humanReadable$1.INSTANCE, 31, (Object) null);
        }

        private final double score(CamProxy camProxy, double d) {
            float f;
            double d2;
            double d3 = d * 70.0d;
            if (camProxy.getChroma() < 48.0f) {
                d2 = 0.1d;
                f = camProxy.getChroma();
            } else {
                d2 = 0.3d;
                f = camProxy.getChroma();
            }
            return (((double) (f - 48.0f)) * d2) + d3;
        }

        private final List<Double> huePopulations(Map<Integer, ? extends CamProxy> map, Map<Integer, Double> map2) {
            ArrayList arrayList = new ArrayList(360);
            for (int i = 0; i < 360; i++) {
                arrayList.add(Double.valueOf(0.0d));
            }
            List<Double> mutableList = CollectionsKt.toMutableList(arrayList);
            for (Map.Entry next : map2.entrySet()) {
                Double d = map2.get(next.getKey());
                Intrinsics.checkNotNull(d);
                double doubleValue = d.doubleValue();
                Object obj = map.get(next.getKey());
                Intrinsics.checkNotNull(obj);
                int roundToInt = MathKt.roundToInt(((CamProxy) obj).getHue()) % 360;
                mutableList.set(roundToInt, Double.valueOf(mutableList.get(roundToInt).doubleValue() + doubleValue));
            }
            return mutableList;
        }
    }
}
