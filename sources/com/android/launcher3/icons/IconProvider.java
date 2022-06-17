package com.android.launcher3.icons;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.launcher3.icons.IconProvider;
import com.android.launcher3.icons.ThemedIconDrawable;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.SafeCloseable;
import com.motorola.styles.ResourceConstants;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class IconProvider {
    private static final String ATTR_DRAWABLE = "drawable";
    private static final String ATTR_PACKAGE = "package";
    private static final int CONFIG_ICON_MASK_RES_ID = Resources.getSystem().getIdentifier(ResourceConstants.CONFIG_ICON_MASK, "string", ResourceConstants.ANDROID_PACKAGE);
    private static final boolean DEBUG = false;
    private static final Map<String, ThemedIconDrawable.ThemeData> DISABLED_MAP = Collections.emptyMap();
    private static final String ICON_METADATA_KEY_PREFIX = ".dynamic_icons";
    private static final String ICON_ROUND_METADATA_KEY_PREFIX = ".dynamic_icons_nexus_round";
    static final int ICON_TYPE_CALENDAR = 1;
    static final int ICON_TYPE_CLOCK = 2;
    static final int ICON_TYPE_DEFAULT = 0;
    private static final String SYSTEM_STATE_SEPARATOR = " ";
    private static final String TAG = "IconProvider";
    private static final String TAG_ICON = "icon";
    private static final String THEMED_ICON_MAP_FILE = "grayscale_icon_map";
    private final String ACTION_OVERLAY_CHANGED;
    /* access modifiers changed from: private */
    public final List<ComponentName> mCalendars;
    /* access modifiers changed from: private */
    public final List<ComponentName> mClocks;
    /* access modifiers changed from: private */
    public final Context mContext;
    private Map<String, ThemedIconDrawable.ThemeData> mThemedIconMap;

    public interface IconChangeListener {
        void onAppIconChanged(String str, UserHandle userHandle);

        void onSystemIconStateChanged(String str);
    }

    public IconProvider(Context context) {
        this(context, false);
    }

    public IconProvider(Context context, boolean z) {
        this.ACTION_OVERLAY_CHANGED = "android.intent.action.OVERLAY_CHANGED";
        this.mContext = context;
        this.mCalendars = parseComponents(context, C0738R.array.dynamic_icon_calendar_component_list);
        this.mClocks = parseComponents(context, C0738R.array.dynamic_icon_clock_component_list);
        if (!z) {
            this.mThemedIconMap = DISABLED_MAP;
        }
    }

    public void setIconThemeSupported(boolean z) {
        this.mThemedIconMap = z ? null : DISABLED_MAP;
    }

    public String getSystemStateForPackage(String str, String str2) {
        return this.mCalendars.stream().map($$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA.INSTANCE).anyMatch(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: RETURN  
              (wrap: java.lang.String : ?: TERNARYnull = ((wrap: boolean : 0x0011: INVOKE  (r1v4 boolean) = 
              (wrap: java.util.stream.Stream : 0x0008: INVOKE  (r1v3 java.util.stream.Stream) = 
              (wrap: java.util.stream.Stream : 0x0002: INVOKE  (r1v2 java.util.stream.Stream) = 
              (wrap: java.util.List<android.content.ComponentName> : 0x0000: IGET  (r1v1 java.util.List<android.content.ComponentName>) = 
              (r1v0 'this' com.android.launcher3.icons.IconProvider A[THIS])
             com.android.launcher3.icons.IconProvider.mCalendars java.util.List)
             java.util.List.stream():java.util.stream.Stream type: INTERFACE)
              (wrap: com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA : 0x0006: SGET  (r0v0 com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA) =  com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA.INSTANCE com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA)
             java.util.stream.Stream.map(java.util.function.Function):java.util.stream.Stream type: INTERFACE)
              (wrap: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg : 0x000e: CONSTRUCTOR  (r0v1 com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg) = (r3v0 'str2' java.lang.String) call: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg.<init>(java.lang.String):void type: CONSTRUCTOR)
             java.util.stream.Stream.anyMatch(java.util.function.Predicate):boolean type: INTERFACE) != false) ? (wrap: java.lang.String : ?: STR_CONCAT  (r1v9 java.lang.String) = 
              (r2v0 'str' java.lang.String)
              (wrap: java.lang.String : ?: SGET   com.android.launcher3.icons.IconProvider.SYSTEM_STATE_SEPARATOR java.lang.String)
              (wrap: int : 0x0026: INVOKE  (r2v2 int) =  com.android.launcher3.icons.IconProvider.getDay():int type: STATIC)
            ) : (r2v0 'str' java.lang.String))
             in method: com.android.launcher3.icons.IconProvider.getSystemStateForPackage(java.lang.String, java.lang.String):java.lang.String, dex: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: TERNARYnull = ((wrap: boolean : 0x0011: INVOKE  (r1v4 boolean) = 
              (wrap: java.util.stream.Stream : 0x0008: INVOKE  (r1v3 java.util.stream.Stream) = 
              (wrap: java.util.stream.Stream : 0x0002: INVOKE  (r1v2 java.util.stream.Stream) = 
              (wrap: java.util.List<android.content.ComponentName> : 0x0000: IGET  (r1v1 java.util.List<android.content.ComponentName>) = 
              (r1v0 'this' com.android.launcher3.icons.IconProvider A[THIS])
             com.android.launcher3.icons.IconProvider.mCalendars java.util.List)
             java.util.List.stream():java.util.stream.Stream type: INTERFACE)
              (wrap: com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA : 0x0006: SGET  (r0v0 com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA) =  com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA.INSTANCE com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA)
             java.util.stream.Stream.map(java.util.function.Function):java.util.stream.Stream type: INTERFACE)
              (wrap: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg : 0x000e: CONSTRUCTOR  (r0v1 com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg) = (r3v0 'str2' java.lang.String) call: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg.<init>(java.lang.String):void type: CONSTRUCTOR)
             java.util.stream.Stream.anyMatch(java.util.function.Predicate):boolean type: INTERFACE) != false) ? (wrap: java.lang.String : ?: STR_CONCAT  (r1v9 java.lang.String) = 
              (r2v0 'str' java.lang.String)
              (wrap: java.lang.String : ?: SGET   com.android.launcher3.icons.IconProvider.SYSTEM_STATE_SEPARATOR java.lang.String)
              (wrap: int : 0x0026: INVOKE  (r2v2 int) =  com.android.launcher3.icons.IconProvider.getDay():int type: STATIC)
            ) : (r2v0 'str' java.lang.String) in method: com.android.launcher3.icons.IconProvider.getSystemStateForPackage(java.lang.String, java.lang.String):java.lang.String, dex: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
            	... 29 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0011: INVOKE  (r1v4 boolean) = 
              (wrap: java.util.stream.Stream : 0x0008: INVOKE  (r1v3 java.util.stream.Stream) = 
              (wrap: java.util.stream.Stream : 0x0002: INVOKE  (r1v2 java.util.stream.Stream) = 
              (wrap: java.util.List<android.content.ComponentName> : 0x0000: IGET  (r1v1 java.util.List<android.content.ComponentName>) = 
              (r1v0 'this' com.android.launcher3.icons.IconProvider A[THIS])
             com.android.launcher3.icons.IconProvider.mCalendars java.util.List)
             java.util.List.stream():java.util.stream.Stream type: INTERFACE)
              (wrap: com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA : 0x0006: SGET  (r0v0 com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA) =  com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA.INSTANCE com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA)
             java.util.stream.Stream.map(java.util.function.Function):java.util.stream.Stream type: INTERFACE)
              (wrap: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg : 0x000e: CONSTRUCTOR  (r0v1 com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg) = (r3v0 'str2' java.lang.String) call: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg.<init>(java.lang.String):void type: CONSTRUCTOR)
             java.util.stream.Stream.anyMatch(java.util.function.Predicate):boolean type: INTERFACE in method: com.android.launcher3.icons.IconProvider.getSystemStateForPackage(java.lang.String, java.lang.String):java.lang.String, dex: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.ConditionGen.addCompare(ConditionGen.java:115)
            	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:57)
            	at jadx.core.codegen.ConditionGen.wrap(ConditionGen.java:84)
            	at jadx.core.codegen.ConditionGen.wrap(ConditionGen.java:50)
            	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:950)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:476)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
            	... 33 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000e: CONSTRUCTOR  (r0v1 com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg) = (r3v0 'str2' java.lang.String) call: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg.<init>(java.lang.String):void type: CONSTRUCTOR in method: com.android.launcher3.icons.IconProvider.getSystemStateForPackage(java.lang.String, java.lang.String):java.lang.String, dex: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
            	... 42 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg, state: NOT_LOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
            	... 48 more
            */
        /*
            this = this;
            java.util.List<android.content.ComponentName> r1 = r1.mCalendars
            java.util.stream.Stream r1 = r1.stream()
            com.android.launcher3.icons.-$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA r0 = com.android.launcher3.icons.$$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA.INSTANCE
            java.util.stream.Stream r1 = r1.map(r0)
            com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg r0 = new com.android.launcher3.icons.-$$Lambda$IconProvider$WD2BLBkgPx-e3_8ME2uH7U2mmmg
            r0.<init>(r3)
            boolean r1 = r1.anyMatch(r0)
            if (r1 == 0) goto L_0x0033
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " "
            java.lang.StringBuilder r1 = r1.append(r2)
            int r2 = getDay()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            return r1
        L_0x0033:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.IconProvider.getSystemStateForPackage(java.lang.String, java.lang.String):java.lang.String");
    }

    public Drawable getIcon(LauncherActivityInfo launcherActivityInfo, int i) {
        Objects.requireNonNull(launcherActivityInfo);
        return getIconWithOverrides(new Supplier(launcherActivityInfo) {
            public final /* synthetic */ LauncherActivityInfo f$0;

            {
                this.f$0 = r1;
            }

            public final Object get() {
                return this.f$0.getComponentName();
            }
        }, launcherActivityInfo.getUser(), i, new Supplier(launcherActivityInfo, i) {
            public final /* synthetic */ LauncherActivityInfo f$0;
            public final /* synthetic */ int f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object get() {
                return this.f$0.getIcon(this.f$1);
            }
        });
    }

    public Drawable getIcon(ActivityInfo activityInfo) {
        return getIcon(activityInfo, this.mContext.getResources().getConfiguration().densityDpi);
    }

    public Drawable getIcon(ActivityInfo activityInfo, int i) {
        return getIconWithOverrides(new Supplier(activityInfo) {
            public final /* synthetic */ ActivityInfo f$0;

            {
                this.f$0 = r1;
            }

            public final Object get() {
                return ComponentName.createRelative(this.f$0.packageName, this.f$0.name);
            }
        }, UserHandle.getUserHandleForUid(activityInfo.applicationInfo.uid), i, new Supplier(activityInfo, i) {
            public final /* synthetic */ ActivityInfo f$1;
            public final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object get() {
                return IconProvider.this.lambda$getIcon$3$IconProvider(this.f$1, this.f$2);
            }
        });
    }

    private Drawable getIconWithOverrides(Supplier<ComponentName> supplier, UserHandle userHandle, int i, Supplier<Drawable> supplier2) {
        int i2;
        ComponentName componentName = supplier.get();
        String packageName = componentName.getPackageName();
        Drawable loadIcon = CustomAppIcons.getInstance().loadIcon(this.mContext, componentName);
        int i3 = 0;
        if (this.mCalendars.stream().map($$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA.INSTANCE).anyMatch(new Predicate(packageName) {
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final boolean test(Object obj) {
                return Objects.equals((String) obj, this.f$0);
            }
        })) {
            if (loadIcon == null) {
                loadIcon = loadCalendarDrawable(supplier, i);
            }
            i2 = 1;
        } else if (this.mClocks.stream().map($$Lambda$IconProvider$HIlrlc_k0A5lsH1JHDjcYuK2vqA.INSTANCE).anyMatch(new Predicate(packageName) {
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final boolean test(Object obj) {
                return Objects.equals((String) obj, this.f$0);
            }
        })) {
            loadIcon = loadClockDrawable(supplier, i, loadIcon);
            i2 = 2;
        } else {
            i2 = 0;
        }
        if (loadIcon == null) {
            loadIcon = supplier2.get();
        } else {
            i3 = i2;
        }
        ThemedIconDrawable.ThemeData themeData = getThemedIconMap().get(packageName);
        return themeData != null ? themeData.wrapDrawable(loadIcon, i3) : loadIcon;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001c  */
    /* renamed from: loadActivityInfoIcon */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Drawable lambda$getIcon$3$IconProvider(android.content.pm.ActivityInfo r4, int r5) {
        /*
            r3 = this;
            int r0 = r4.getIconResource()
            if (r5 == 0) goto L_0x0019
            if (r0 == 0) goto L_0x0019
            android.content.Context r1 = r3.mContext     // Catch:{ NameNotFoundException | NotFoundException -> 0x0019 }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ NameNotFoundException | NotFoundException -> 0x0019 }
            android.content.pm.ApplicationInfo r2 = r4.applicationInfo     // Catch:{ NameNotFoundException | NotFoundException -> 0x0019 }
            android.content.res.Resources r1 = r1.getResourcesForApplication(r2)     // Catch:{ NameNotFoundException | NotFoundException -> 0x0019 }
            android.graphics.drawable.Drawable r5 = r1.getDrawableForDensity(r0, r5)     // Catch:{ NameNotFoundException | NotFoundException -> 0x0019 }
            goto L_0x001a
        L_0x0019:
            r5 = 0
        L_0x001a:
            if (r5 != 0) goto L_0x0026
            android.content.Context r3 = r3.mContext
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            android.graphics.drawable.Drawable r5 = r4.loadIcon(r3)
        L_0x0026:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.IconProvider.lambda$getIcon$3$IconProvider(android.content.pm.ActivityInfo, int):android.graphics.drawable.Drawable");
    }

    private Map<String, ThemedIconDrawable.ThemeData> getThemedIconMap() {
        Map<String, ThemedIconDrawable.ThemeData> map = this.mThemedIconMap;
        if (map != null) {
            return map;
        }
        ArrayMap arrayMap = new ArrayMap();
        try {
            Resources resources = this.mContext.getResources();
            int identifier = resources.getIdentifier(THEMED_ICON_MAP_FILE, "xml", this.mContext.getPackageName());
            if (identifier != 0) {
                XmlResourceParser xml = resources.getXml(identifier);
                int depth = xml.getDepth();
                while (true) {
                    int next = xml.next();
                    if (next == 2 || next == 1) {
                    }
                }
                while (true) {
                    int next2 = xml.next();
                    if ((next2 == 3 && xml.getDepth() <= depth) || next2 == 1) {
                        break;
                    } else if (next2 == 2) {
                        if ("icon".equals(xml.getName())) {
                            String attributeValue = xml.getAttributeValue((String) null, "package");
                            int attributeResourceValue = xml.getAttributeResourceValue((String) null, "drawable", 0);
                            if (attributeResourceValue != 0 && !TextUtils.isEmpty(attributeValue)) {
                                arrayMap.put(attributeValue, new ThemedIconDrawable.ThemeData(resources, attributeResourceValue));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Unable to parse icon map", e);
        }
        this.mThemedIconMap = arrayMap;
        return arrayMap;
    }

    private Drawable loadCalendarDrawable(Supplier<ComponentName> supplier, int i) {
        String packageName = supplier.get().getPackageName();
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            Bundle bundle = packageManager.getActivityInfo(supplier.get(), 8320).metaData;
            Resources resourcesForApplication = packageManager.getResourcesForApplication(packageName);
            int dynamicIconId = getDynamicIconId(packageName, bundle, resourcesForApplication);
            if (dynamicIconId != 0) {
                return resourcesForApplication.getDrawableForDensity(dynamicIconId, i, (Resources.Theme) null);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    private Drawable loadClockDrawable(Supplier<ComponentName> supplier, int i, Drawable drawable) {
        return ClockDrawableWrapper.forPackage(this.mContext, supplier.get().getPackageName(), i, drawable);
    }

    /* access modifiers changed from: protected */
    public boolean isClockIcon(ComponentKey componentKey) {
        return this.mClocks.contains(componentKey.componentName);
    }

    private int getDynamicIconId(String str, Bundle bundle, Resources resources) {
        if (bundle == null) {
            return 0;
        }
        int i = bundle.getInt(str + (getIsSystemUseRoundIcon() ? ICON_ROUND_METADATA_KEY_PREFIX : ICON_METADATA_KEY_PREFIX), 0);
        if (i == 0) {
            return 0;
        }
        try {
            TypedArray obtainTypedArray = resources.obtainTypedArray(i);
            int resourceId = obtainTypedArray.getResourceId(getDay(), 0);
            obtainTypedArray.recycle();
            return resourceId;
        } catch (Resources.NotFoundException unused) {
            return 0;
        }
    }

    static int getDay() {
        return Calendar.getInstance().get(5) - 1;
    }

    private boolean getIsSystemUseRoundIcon() {
        try {
            return Resources.getSystem().getBoolean(((Integer) Class.forName("com.android.internal.R$bool").getField("config_useRoundIcon").get((Object) null)).intValue());
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "getIsSystemUseRoundIcon -> Exception", e);
            return false;
        }
    }

    private static List<ComponentName> parseComponents(Context context, int i) {
        return (List) Arrays.stream(context.getResources().getStringArray(i)).filter($$Lambda$IconProvider$yPMdMcistpqVTGWe4PpIEOCcmJ8.INSTANCE).map($$Lambda$IconProvider$mVB2YB61TxO4cQ_5vWZ3Rq7CXhk.INSTANCE).filter($$Lambda$IconProvider$QykguMtrdAvt37qk5jJ34Yd4.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$parseComponents$6(String str) {
        return !TextUtils.isEmpty(str);
    }

    public String getSystemIconState() {
        String str;
        StringBuilder sb = new StringBuilder();
        int i = CONFIG_ICON_MASK_RES_ID;
        if (i == 0) {
            str = "";
        } else {
            str = this.mContext.getResources().getString(i);
        }
        return sb.append(str).append(this.mThemedIconMap == DISABLED_MAP ? ",no-theme" : ",with-theme").append(",accent:").append(this.mContext.getColor(17170488)).append(",neutral:").append(this.mContext.getColor(17170462)).toString();
    }

    public SafeCloseable registerIconChangeListener(IconChangeListener iconChangeListener, Handler handler) {
        return new IconChangeReceiver(iconChangeListener, handler);
    }

    private class IconChangeReceiver extends BroadcastReceiver implements SafeCloseable {
        private final IconChangeListener mCallback;
        private String mIconState;

        IconChangeReceiver(IconChangeListener iconChangeListener, Handler handler) {
            this.mCallback = iconChangeListener;
            this.mIconState = IconProvider.this.getSystemIconState();
            IntentFilter intentFilter = new IntentFilter("android.intent.action.OVERLAY_CHANGED");
            intentFilter.addDataScheme("package");
            intentFilter.addDataSchemeSpecificPart(ResourceConstants.ANDROID_PACKAGE, 0);
            IconProvider.this.mContext.registerReceiver(this, intentFilter, (String) null, handler);
            if (!IconProvider.this.mCalendars.isEmpty() || !IconProvider.this.mClocks.isEmpty()) {
                IntentFilter intentFilter2 = new IntentFilter("android.intent.action.TIMEZONE_CHANGED");
                if (!IconProvider.this.mCalendars.isEmpty()) {
                    intentFilter2.addAction("android.intent.action.TIME_SET");
                    intentFilter2.addAction("android.intent.action.DATE_CHANGED");
                }
                IconProvider.this.mContext.registerReceiver(this, intentFilter2, (String) null, handler);
            }
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            char c = 65535;
            switch (action.hashCode()) {
                case -1946981856:
                    if (action.equals("android.intent.action.OVERLAY_CHANGED")) {
                        c = 0;
                        break;
                    }
                    break;
                case 502473491:
                    if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                        c = 1;
                        break;
                    }
                    break;
                case 505380757:
                    if (action.equals("android.intent.action.TIME_SET")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1041332296:
                    if (action.equals("android.intent.action.DATE_CHANGED")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    String systemIconState = IconProvider.this.getSystemIconState();
                    if (!this.mIconState.equals(systemIconState)) {
                        this.mIconState = systemIconState;
                        this.mCallback.onSystemIconStateChanged(systemIconState);
                        return;
                    }
                    return;
                case 1:
                case 2:
                case 3:
                    for (UserHandle next : ((UserManager) context.getSystemService(UserManager.class)).getUserProfiles()) {
                        if ("android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                            IconProvider.this.mClocks.stream().map(C0731x2c7f08ac.INSTANCE).forEach(new Consumer(next) {
                                public final /* synthetic */ UserHandle f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void accept(Object obj) {
                                    IconProvider.IconChangeReceiver.this.lambda$onReceive$0$IconProvider$IconChangeReceiver(this.f$1, (String) obj);
                                }
                            });
                        }
                        IconProvider.this.mCalendars.stream().map(C0731x2c7f08ac.INSTANCE).forEach(new Consumer(next) {
                            public final /* synthetic */ UserHandle f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void accept(Object obj) {
                                IconProvider.IconChangeReceiver.this.lambda$onReceive$1$IconProvider$IconChangeReceiver(this.f$1, (String) obj);
                            }
                        });
                    }
                    return;
                default:
                    return;
            }
        }

        public /* synthetic */ void lambda$onReceive$0$IconProvider$IconChangeReceiver(UserHandle userHandle, String str) {
            this.mCallback.onAppIconChanged(str, userHandle);
        }

        public /* synthetic */ void lambda$onReceive$1$IconProvider$IconChangeReceiver(UserHandle userHandle, String str) {
            this.mCallback.onAppIconChanged(str, userHandle);
        }

        public void close() {
            IconProvider.this.mContext.unregisterReceiver(this);
        }
    }
}
