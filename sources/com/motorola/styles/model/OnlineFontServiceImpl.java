package com.motorola.styles.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.util.AtomicFile;
import com.android.launcher3.icons.CustomAppIcons;
import com.motorola.styles.LogConfig;
import com.motorola.styles.ResourceConstants;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.XmlUtils;
import com.motorola.styles.model.OnlineFontService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

class OnlineFontServiceImpl implements OnlineFontService, Handler.Callback {
    private static final Comparator<OnlineFontService.FontInfo> DEFAULT_FONT_INFO_COMPARATOR = $$Lambda$OnlineFontServiceImpl$KvAkHlf4DFppd8eQ08wJ11I0jSU.INSTANCE;
    private static final FontFileExtension[] FONT_EXTENSIONS = {new TTF_FontExt(), new OTF_FontExt()};
    private static final int MSG_INIT = 0;
    private static final int MSG_PACKAGE_REMOVE = 2;
    private static final int MSG_PACKAGE_UPDATE = 1;
    private static final int MSG_PERSIST = 3;
    private static final int PARSE_RESULT_BROKEN_FILE = -2;
    private static final int PARSE_RESULT_INVALID_VERSION = -1;
    private static final int PARSE_RESULT_OKAY = 0;
    private static final String TAG = "FontService";
    private static final FilenameFilter WIDE_FONT_FILE_FILTER = $$Lambda$OnlineFontServiceImpl$bdviVp7oDLNU97qMAsd9W4mgmk.INSTANCE;
    private boolean featureValid;
    private final Map<String, OnlineFontService.FontInfo> mFontInfoCache = new HashMap();
    private Handler mHandler;
    private final Set<OnlineFontService.OnlineFontUpdateListener> mListeners = new HashSet();
    private final AtomicBoolean mLoading;
    private final Object mLock = new Object();
    private String mMyPackageName;
    private PackageManager mPm;
    private Handler mUiHandler;
    private final File systemFontXmlFile;
    private final File systemFontsDir;
    private final File systemThemeDir;

    private interface FontFileExtension {
        String buildFileName(String str);

        String ext();

        boolean match(String str);

        String name(String str);
    }

    private interface Fonts {
        public static final String ACTION_FONT_CATEGORY = "com.motorola.action.theme.font";
        public static final String FONTS_ATTR_VERSION = "version";
        public static final String FONT_ATTR_CHECKSUM = "checksum";
        public static final String FONT_ATTR_FILE = "file";
        public static final String FONT_ATTR_INSTALL_TIME = "installTime";
        public static final String FONT_ATTR_NAME = "name";
        public static final String FONT_ATTR_PACKAGE = "package";
        public static final String FONT_ATTR_UNINSTALLABLE = "uninstallable";
        public static final String FONT_ATTR_UPDATE_TIME = "updateTime";
        public static final String FONT_CACHE_DIR = "/data/misc/theme/";
        public static final String FONT_FILE_EXT_OTF = "otf";
        public static final String FONT_FILE_EXT_TTF = "ttf";
        public static final String FONT_FILE_NAME_TEMPLATE = "font_%s.%s";
        public static final Pattern FONT_FILE_PATTERN = Pattern.compile("font_\\S+\\.[t|o]tf");
        public static final String FONT_XML_FILE_NAME = "fonts.xml";
        public static final long MAX_SUPPORT_FONT_FILE_SIZE_IN_BYTES = 31457280;
        public static final String TAG_FONT = "font";
        public static final String TAG_FONTS = "fonts";
        public static final String VERSION = "1";
    }

    static /* synthetic */ int lambda$static$1(OnlineFontService.FontInfo fontInfo, OnlineFontService.FontInfo fontInfo2) {
        int compare = Long.compare(fontInfo.installTime, fontInfo2.installTime);
        return compare == 0 ? Long.compare(fontInfo.updateTime, fontInfo2.updateTime) : compare;
    }

    OnlineFontServiceImpl() {
        boolean z = false;
        this.mLoading = new AtomicBoolean(false);
        File file = new File(Fonts.FONT_CACHE_DIR);
        this.systemThemeDir = file;
        File file2 = new File(file, Fonts.TAG_FONTS);
        this.systemFontsDir = file2;
        this.systemFontXmlFile = new File(file2, Fonts.FONT_XML_FILE_NAME);
        try {
            throwIfInvalidTargetDirectory();
            z = true;
        } catch (IOException e) {
            trace("failed to init font feature", 6, e);
        }
        this.featureValid = z;
        Log.d(TAG, "OnlineFontServiceImpl: featureValid = " + this.featureValid);
        if (z) {
            HandlerThread handlerThread = new HandlerThread("font-service");
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), this);
            this.mUiHandler = new Handler(Looper.getMainLooper());
        }
    }

    public void onCreate(Context context) {
        if (isEnabled()) {
            trace("on service create", 2);
            this.mPm = context.getPackageManager();
            this.mMyPackageName = context.getPackageName();
            ((LauncherApps) context.getSystemService(LauncherApps.class)).registerCallback(new PackageChangeCallback(this.mHandler));
            this.mHandler.sendEmptyMessage(0);
        }
    }

    public List<OnlineFontService.FontInfo> getInstalledFontList() {
        return getInstalledFontList(DEFAULT_FONT_INFO_COMPARATOR);
    }

    public List<OnlineFontService.FontInfo> getInstalledFontList(Comparator<OnlineFontService.FontInfo> comparator) {
        ArrayList arrayList;
        if (this.mLoading.get()) {
            return Collections.emptyList();
        }
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mFontInfoCache.values());
            arrayList.sort(comparator);
        }
        return arrayList;
    }

    public boolean isEnabled() {
        return this.featureValid && StylesUtilities.PRC_BUILD;
    }

    public boolean isLoading() {
        return this.mLoading.get();
    }

    public void onApplyFont(String str) throws IOException {
        OnlineFontService.FontInfo fontInfo;
        if (isEnabled()) {
            synchronized (this.mLock) {
                fontInfo = this.mFontInfoCache.get(str);
            }
            trace("apply font for package " + str + ", info = " + fontInfo, 3);
            File currentOnlineFont = getCurrentOnlineFont();
            if (currentOnlineFont != null && currentOnlineFont.delete()) {
                trace("apply font remove the old font file " + currentOnlineFont.getName(), 3);
            }
            if (fontInfo != null) {
                File file = new File(this.systemThemeDir, fontInfo.fileName);
                trace("start copy target font file " + file.getName(), 3);
                Files.copy(fontInfo.file.toPath(), file.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
                file.setReadable(true, false);
                file.setWritable(true, true);
                trace("copy target font file done, file exits = " + file.exists(), 2);
            }
        }
    }

    public boolean isCurrentOnlineFont() {
        return getCurrentOnlineFont() != null;
    }

    public void registerOnlineFontUpdateListener(OnlineFontService.OnlineFontUpdateListener onlineFontUpdateListener) {
        this.mListeners.add(onlineFontUpdateListener);
    }

    public void unregisterOnlineFontUpdateListener(OnlineFontService.OnlineFontUpdateListener onlineFontUpdateListener) {
        this.mListeners.remove(onlineFontUpdateListener);
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            initCache();
        } else if (i == 1 || i == 2) {
            String[] strArr = message.obj instanceof String ? new String[]{(String) message.obj} : (String[]) message.obj;
            if (2 == message.what) {
                onPackageRemoved(strArr);
            } else {
                onPackageUpdated(strArr);
            }
        } else if (i == 3) {
            persistFontInfo();
        }
        return false;
    }

    private void initCache() {
        trace("start to init cache", 2);
        boolean z = true;
        this.mLoading.getAndSet(true);
        synchronized (this.mLock) {
            if (loadPersistedFontsXml() == 0) {
                z = false;
            }
            boolean scanFontPackages = z | scanFontPackages((String) null) | cleanupGhostFontFiles();
            if (scanFontPackages) {
                persistFontInfo();
            }
            this.mLoading.getAndSet(false);
            if (scanFontPackages) {
                notifyFontUpdate();
            }
        }
    }

    private void onPackageUpdated(String[] strArr) {
        List<String> list = (List) Arrays.stream(strArr).filter($$Lambda$OnlineFontServiceImpl$noUtLHGDG01uEYpcT3PVomxOSuo.INSTANCE).filter(new Predicate() {
            public final boolean test(Object obj) {
                return OnlineFontServiceImpl.this.lambda$onPackageUpdated$3$OnlineFontServiceImpl((String) obj);
            }
        }).collect(Collectors.toList());
        if (!list.isEmpty()) {
            trace("package updated " + list, 2);
            boolean z = false;
            synchronized (this.mLock) {
                for (String str : list) {
                    z = z | scanFontPackages(str) | cleanupGhostFontFileForPackage(str);
                }
            }
            if (z) {
                schedulePersist();
                notifyFontUpdate();
            }
        }
    }

    static /* synthetic */ boolean lambda$onPackageUpdated$2(String str) {
        return !Objects.equals(ResourceConstants.ANDROID_PACKAGE, str);
    }

    public /* synthetic */ boolean lambda$onPackageUpdated$3$OnlineFontServiceImpl(String str) {
        return !Objects.equals(this.mMyPackageName, str);
    }

    private void onPackageRemoved(String[] strArr) {
        boolean z;
        trace("package removed " + Arrays.toString(strArr), 2);
        synchronized (this.mLock) {
            z = false;
            for (String str : strArr) {
                OnlineFontService.FontInfo remove = this.mFontInfoCache.remove(str);
                if (remove != null) {
                    try {
                        Files.deleteIfExists(remove.file.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    z = true;
                } else {
                    z = cleanupGhostFontFileForPackage(str);
                }
            }
        }
        if (z) {
            schedulePersist();
            notifyFontUpdate();
            return;
        }
        return;
    }

    private void schedulePersist() {
        trace("schedule persist fonts", 2);
        if (this.mHandler.hasMessages(3)) {
            this.mHandler.removeMessages(3);
        }
        this.mHandler.sendEmptyMessage(3);
    }

    private void persistFontInfo() {
        trace("start to persist fonts", 4);
        AtomicFile atomicFile = new AtomicFile(this.systemFontXmlFile);
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream startWrite = atomicFile.startWrite();
            try {
                XmlSerializer newSerializer = XmlPullParserFactory.newInstance().newSerializer();
                newSerializer.setOutput(startWrite, StandardCharsets.UTF_8.name());
                newSerializer.startDocument(StandardCharsets.UTF_8.name(), (Boolean) null);
                newSerializer.startTag((String) null, Fonts.TAG_FONTS).attribute((String) null, "version", Fonts.VERSION);
                synchronized (this.mLock) {
                    Collection<OnlineFontService.FontInfo> values = this.mFontInfoCache.values();
                    trace("persist fonts, size = " + values.size(), 2);
                    for (OnlineFontService.FontInfo next : values) {
                        newSerializer.startTag((String) null, "font").attribute((String) null, "package", next.packageName).attribute((String) null, "name", next.name).attribute((String) null, Fonts.FONT_ATTR_CHECKSUM, next.checksum).attribute((String) null, Fonts.FONT_ATTR_FILE, next.fileName).attribute((String) null, Fonts.FONT_ATTR_UNINSTALLABLE, String.valueOf(next.uninstallable)).attribute((String) null, Fonts.FONT_ATTR_INSTALL_TIME, String.valueOf(next.installTime)).attribute((String) null, Fonts.FONT_ATTR_UPDATE_TIME, String.valueOf(next.updateTime));
                        newSerializer.endTag((String) null, "font");
                    }
                }
                newSerializer.endTag((String) null, Fonts.TAG_FONTS);
                newSerializer.endDocument();
                newSerializer.flush();
                atomicFile.finishWrite(startWrite);
                trace("persist fonts success", 4);
            } catch (IOException | XmlPullParserException e) {
                e = e;
                fileOutputStream = startWrite;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = startWrite;
                atomicFile.failWrite(fileOutputStream);
                trace("persist fonts failed", 5);
                throw th;
            }
        } catch (IOException | XmlPullParserException e2) {
            e = e2;
            try {
                e.printStackTrace();
                atomicFile.failWrite(fileOutputStream);
                trace("persist fonts failed", 5);
            } catch (Throwable th2) {
                th = th2;
                atomicFile.failWrite(fileOutputStream);
                trace("persist fonts failed", 5);
                throw th;
            }
        }
    }

    private void throwIfInvalidTargetDirectory() throws IOException {
        if (!this.systemThemeDir.exists()) {
            this.systemThemeDir.mkdirs();
        } else if (!this.systemThemeDir.canRead() || !this.systemThemeDir.canWrite()) {
            throw new IOException("cannot access the target dir " + this.systemThemeDir);
        }
        if (!this.systemFontsDir.exists() && !this.systemFontsDir.mkdirs()) {
            throw new IOException("cannot access the target dir " + this.systemFontsDir);
        } else if (!this.systemFontsDir.canRead() || !this.systemFontsDir.canWrite()) {
            throw new IOException("cannot access the target dir " + this.systemFontsDir);
        } else if (!this.systemFontXmlFile.exists()) {
        } else {
            if (!this.systemFontXmlFile.canRead() || !this.systemFontXmlFile.canWrite()) {
                throw new IOException("cannot access the target file " + this.systemFontXmlFile);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: char} */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r3v28 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x028d, code lost:
        r22 = r3;
        r27 = r5;
        r25 = r9;
        r26 = r10;
        r3 = 4;
        r18 = false;
        r20 = true;
        trace("scanFontPackages: invalid font file " + r7, 5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02b3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02b4, code lost:
        r22 = r3;
        r27 = r5;
        r25 = r9;
        r26 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x02b3 A[ExcHandler: NameNotFoundException | IOException (e java.lang.Throwable), Splitter:B:21:0x00af] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean scanFontPackages(java.lang.String r29) {
        /*
            r28 = this;
            r1 = r28
            r2 = r29
            java.lang.String r3 = "fonts"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "start to scan fonts for package "
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r4 = 4
            r1.trace(r0, r4)
            java.util.HashMap r5 = new java.util.HashMap
            java.util.Map<java.lang.String, com.motorola.styles.model.OnlineFontService$FontInfo> r0 = r1.mFontInfoCache
            r5.<init>(r0)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r6 = "com.motorola.action.theme.font"
            r0.<init>(r6)
            r6 = 1
            r7 = 0
            if (r2 != 0) goto L_0x003c
            java.util.Map<java.lang.String, com.motorola.styles.model.OnlineFontService$FontInfo> r8 = r1.mFontInfoCache
            boolean r8 = r8.isEmpty()
            r8 = r8 ^ r6
            java.util.Map<java.lang.String, com.motorola.styles.model.OnlineFontService$FontInfo> r9 = r1.mFontInfoCache
            r9.clear()
            goto L_0x004a
        L_0x003c:
            r0.setPackage(r2)
            java.util.Map<java.lang.String, com.motorola.styles.model.OnlineFontService$FontInfo> r8 = r1.mFontInfoCache
            java.lang.Object r8 = r8.remove(r2)
            if (r8 == 0) goto L_0x0049
            r8 = r6
            goto L_0x004a
        L_0x0049:
            r8 = r7
        L_0x004a:
            android.content.pm.PackageManager r9 = r1.mPm
            java.util.List r0 = r9.queryBroadcastReceivers(r0, r7)
            java.util.Iterator r10 = r0.iterator()
            r11 = r7
        L_0x0055:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0322
            java.lang.Object r0 = r10.next()
            android.content.pm.ResolveInfo r0 = (android.content.pm.ResolveInfo) r0
            android.content.pm.ActivityInfo r13 = r0.activityInfo
            java.lang.String r13 = r13.packageName
            android.content.pm.ActivityInfo r14 = r0.activityInfo     // Catch:{ NameNotFoundException | IOException -> 0x02ee }
            android.content.pm.ApplicationInfo r14 = r14.applicationInfo     // Catch:{ NameNotFoundException | IOException -> 0x02ee }
            android.content.res.Resources r14 = r9.getResourcesForApplication(r14)     // Catch:{ NameNotFoundException | IOException -> 0x02ee }
            android.content.res.AssetManager r15 = r14.getAssets()     // Catch:{ NameNotFoundException | IOException -> 0x02ee }
            java.lang.String[] r15 = r15.list(r3)     // Catch:{ NameNotFoundException | IOException -> 0x02ee }
            int r4 = r15.length     // Catch:{ NameNotFoundException | IOException -> 0x02df }
            if (r4 <= 0) goto L_0x02d1
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ NameNotFoundException | IOException -> 0x02df }
            r4 = r15[r7]     // Catch:{ NameNotFoundException | IOException -> 0x02df }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x02df }
            r15.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x02df }
            java.lang.StringBuilder r15 = r15.append(r3)     // Catch:{ NameNotFoundException | IOException -> 0x02df }
            char r7 = java.io.File.separatorChar     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            java.lang.StringBuilder r7 = r15.append(r7)     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            java.lang.StringBuilder r7 = r7.append(r4)     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            java.lang.String r7 = r7.toString()     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            com.motorola.styles.model.OnlineFontServiceImpl$FontFileExtension[] r15 = FONT_EXTENSIONS     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            java.util.stream.Stream r15 = java.util.Arrays.stream(r15)     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            com.motorola.styles.model.-$$Lambda$OnlineFontServiceImpl$qKdfd10FS0pTl7EKouvy08N9PdM r12 = new com.motorola.styles.model.-$$Lambda$OnlineFontServiceImpl$qKdfd10FS0pTl7EKouvy08N9PdM     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            r12.<init>(r4)     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            java.util.stream.Stream r12 = r15.filter(r12)     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            java.util.Optional r12 = r12.findAny()     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            boolean r15 = r12.isPresent()     // Catch:{ NameNotFoundException | IOException -> 0x02c2 }
            r6 = 5
            if (r15 != 0) goto L_0x00d4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r0.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.String r4 = "scanFontPackages: invalid file format "
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.StringBuilder r0 = r0.append(r7)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.String r0 = r0.toString()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r1.trace(r0, r6)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
        L_0x00c5:
            r22 = r3
            r27 = r5
            r25 = r9
            r26 = r10
            r3 = 4
            r18 = 0
            r20 = 1
            goto L_0x0313
        L_0x00d4:
            java.lang.Object r12 = r12.get()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            com.motorola.styles.model.OnlineFontServiceImpl$FontFileExtension r12 = (com.motorola.styles.model.OnlineFontServiceImpl.FontFileExtension) r12     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.String r4 = r12.name(r4)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.String r15 = "scanFontPackages: invalid font file "
            if (r4 != 0) goto L_0x00f7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r0.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.StringBuilder r0 = r0.append(r15)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.StringBuilder r0 = r0.append(r7)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.String r0 = r0.toString()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r1.trace(r0, r6)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            goto L_0x00c5
        L_0x00f7:
            android.graphics.fonts.Font$Builder r6 = new android.graphics.fonts.Font$Builder     // Catch:{ Exception -> 0x028d, NameNotFoundException | IOException -> 0x02b3 }
            android.content.res.AssetManager r14 = r14.getAssets()     // Catch:{ Exception -> 0x028d, NameNotFoundException | IOException -> 0x02b3 }
            r6.<init>(r14, r7)     // Catch:{ Exception -> 0x028d, NameNotFoundException | IOException -> 0x02b3 }
            android.graphics.fonts.Font r6 = r6.build()     // Catch:{ Exception -> 0x028d, NameNotFoundException | IOException -> 0x02b3 }
            java.nio.ByteBuffer r6 = r6.getBuffer()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            int r14 = r6.capacity()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            long r14 = (long) r14     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r22 = 31457280(0x1e00000, double:1.55419614E-316)
            int r14 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1))
            if (r14 <= 0) goto L_0x012c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r0.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.String r4 = "scanFontPackages: too big font file "
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.StringBuilder r0 = r0.append(r7)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.String r0 = r0.toString()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r4 = 5
            r1.trace(r0, r4)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            goto L_0x00c5
        L_0x012c:
            java.lang.String r7 = com.motorola.styles.StylesUtilities.md5Checksum(r6)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r6.rewind()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            java.lang.Object r14 = r5.get(r13)     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            com.motorola.styles.model.OnlineFontService$FontInfo r14 = (com.motorola.styles.model.OnlineFontService.FontInfo) r14     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            long r21 = java.lang.System.currentTimeMillis()     // Catch:{ NameNotFoundException | IOException -> 0x02b3 }
            r23 = 0
            if (r14 == 0) goto L_0x01a9
            r25 = r9
            r26 = r10
            long r9 = r14.installTime     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            java.lang.String r15 = r14.checksum     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            boolean r15 = java.util.Objects.equals(r15, r7)     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            if (r15 == 0) goto L_0x019f
            java.io.File r15 = r14.file     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            boolean r15 = r15.exists()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            if (r15 == 0) goto L_0x019a
            if (r2 != 0) goto L_0x0160
            int r0 = r5.size()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            r6 = 1
            if (r0 != r6) goto L_0x0161
        L_0x0160:
            r8 = 0
        L_0x0161:
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r0 = r14.copy()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            java.lang.String r6 = r14.name     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            boolean r6 = java.util.Objects.equals(r6, r4)     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            if (r6 != 0) goto L_0x0171
            r0.setName(r4)     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            r8 = 1
        L_0x0171:
            java.util.Map<java.lang.String, com.motorola.styles.model.OnlineFontService$FontInfo> r4 = r1.mFontInfoCache     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            com.motorola.styles.model.OnlineFontService$FontInfo r0 = r0.build()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            r4.put(r13, r0)     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            r0.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            java.lang.String r4 = "scanFontPackages: ignore to recopy the file for package "
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            java.lang.StringBuilder r0 = r0.append(r13)     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            java.lang.String r0 = r0.toString()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            r4 = 2
            r1.trace(r0, r4)     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            r9 = r25
            r10 = r26
            r4 = 4
            r6 = 1
            r7 = 0
            goto L_0x0055
        L_0x019a:
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            goto L_0x01b1
        L_0x019f:
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ NameNotFoundException | IOException -> 0x01a4 }
            goto L_0x01b1
        L_0x01a4:
            r0 = move-exception
            r22 = r3
            goto L_0x028a
        L_0x01a9:
            r25 = r9
            r26 = r10
            r9 = r21
            r14 = r23
        L_0x01b1:
            r22 = r3
            java.io.File r3 = new java.io.File     // Catch:{ NameNotFoundException | IOException -> 0x0289 }
            r27 = r5
            java.io.File r5 = r1.systemFontsDir     // Catch:{ NameNotFoundException | IOException -> 0x0287 }
            java.lang.String r12 = r12.buildFileName(r13)     // Catch:{ NameNotFoundException | IOException -> 0x0287 }
            r3.<init>(r5, r12)     // Catch:{ NameNotFoundException | IOException -> 0x0287 }
            java.nio.file.Path r5 = r3.toPath()     // Catch:{ NameNotFoundException | IOException -> 0x0287 }
            r12 = 3
            java.nio.file.OpenOption[] r12 = new java.nio.file.OpenOption[r12]     // Catch:{ NameNotFoundException | IOException -> 0x0287 }
            java.nio.file.StandardOpenOption r19 = java.nio.file.StandardOpenOption.CREATE     // Catch:{ NameNotFoundException | IOException -> 0x0287 }
            r18 = 0
            r12[r18] = r19     // Catch:{ NameNotFoundException | IOException -> 0x0284 }
            java.nio.file.StandardOpenOption r19 = java.nio.file.StandardOpenOption.WRITE     // Catch:{ NameNotFoundException | IOException -> 0x0284 }
            r20 = 1
            r12[r20] = r19     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            java.nio.file.StandardOpenOption r19 = java.nio.file.StandardOpenOption.TRUNCATE_EXISTING     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            r21 = 2
            r12[r21] = r19     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            java.nio.channels.FileChannel r5 = java.nio.channels.FileChannel.open(r5, r12)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            r5.write(r6)     // Catch:{ all -> 0x0272 }
            r6.rewind()     // Catch:{ all -> 0x0272 }
            if (r5 == 0) goto L_0x01e8
            r5.close()     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
        L_0x01e8:
            android.content.pm.ActivityInfo r0 = r0.activityInfo     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            boolean r0 = r1.applicationNonSystemApp(r0)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r5 = new com.motorola.styles.model.OnlineFontService$FontInfo$Builder     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            r5.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r5 = r5.setPackageName(r13)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r4 = r5.setName(r4)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r4 = r4.setChecksum(r7)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            java.lang.String r5 = r3.getName()     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r4 = r4.setFileName(r5)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r3 = r4.setFile(r3)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r0 = r3.setUninstallable(r0)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo$Builder r0 = r0.setInstallTime(r9)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            int r3 = (r14 > r23 ? 1 : (r14 == r23 ? 0 : -1))
            if (r3 == 0) goto L_0x021c
            r0.setUpdateTime(r14)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
        L_0x021c:
            java.util.Map<java.lang.String, com.motorola.styles.model.OnlineFontService$FontInfo> r3 = r1.mFontInfoCache     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            com.motorola.styles.model.OnlineFontService$FontInfo r0 = r0.build()     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            r3.put(r13, r0)     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            r6.clear()     // Catch:{ NameNotFoundException | IOException -> 0x0281 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x026c }
            r0.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x026c }
            java.lang.String r3 = "found new font for package "
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ NameNotFoundException | IOException -> 0x026c }
            java.lang.StringBuilder r0 = r0.append(r13)     // Catch:{ NameNotFoundException | IOException -> 0x026c }
            java.lang.String r0 = r0.toString()     // Catch:{ NameNotFoundException | IOException -> 0x026c }
            r3 = 4
            r1.trace(r0, r3)     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            r0.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            java.lang.String r4 = "found new font cost time: "
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            long r4 = r4 - r16
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            java.lang.String r4 = " ms for package "
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            java.lang.StringBuilder r0 = r0.append(r13)     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            java.lang.String r0 = r0.toString()     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            r4 = 2
            r1.trace(r0, r4)     // Catch:{ NameNotFoundException | IOException -> 0x026a }
            r11 = r20
            goto L_0x0313
        L_0x026a:
            r0 = move-exception
            goto L_0x026e
        L_0x026c:
            r0 = move-exception
            r3 = 4
        L_0x026e:
            r11 = r20
            goto L_0x02fc
        L_0x0272:
            r0 = move-exception
            r3 = 4
            r4 = r0
            if (r5 == 0) goto L_0x0280
            r5.close()     // Catch:{ all -> 0x027b }
            goto L_0x0280
        L_0x027b:
            r0 = move-exception
            r5 = r0
            r4.addSuppressed(r5)     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
        L_0x0280:
            throw r4     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
        L_0x0281:
            r0 = move-exception
            goto L_0x02ec
        L_0x0284:
            r0 = move-exception
            r3 = 4
            goto L_0x02bf
        L_0x0287:
            r0 = move-exception
            goto L_0x02bc
        L_0x0289:
            r0 = move-exception
        L_0x028a:
            r27 = r5
            goto L_0x02bc
        L_0x028d:
            r22 = r3
            r27 = r5
            r25 = r9
            r26 = r10
            r3 = 4
            r18 = 0
            r20 = 1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
            r0.<init>()     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
            java.lang.StringBuilder r0 = r0.append(r15)     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
            java.lang.StringBuilder r0 = r0.append(r7)     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
            java.lang.String r0 = r0.toString()     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
            r4 = 5
            r1.trace(r0, r4)     // Catch:{ NameNotFoundException | IOException -> 0x02b1 }
            goto L_0x0313
        L_0x02b1:
            r0 = move-exception
            goto L_0x02fc
        L_0x02b3:
            r0 = move-exception
            r22 = r3
            r27 = r5
            r25 = r9
            r26 = r10
        L_0x02bc:
            r3 = 4
            r18 = 0
        L_0x02bf:
            r20 = 1
            goto L_0x02fc
        L_0x02c2:
            r0 = move-exception
            r22 = r3
            r27 = r5
            r20 = r6
            r25 = r9
            r26 = r10
            r3 = 4
            r18 = 0
            goto L_0x02fc
        L_0x02d1:
            r22 = r3
            r27 = r5
            r20 = r6
            r18 = r7
            r25 = r9
            r26 = r10
            r3 = 4
            goto L_0x0313
        L_0x02df:
            r0 = move-exception
            r22 = r3
            r27 = r5
            r20 = r6
            r18 = r7
            r25 = r9
            r26 = r10
        L_0x02ec:
            r3 = 4
            goto L_0x02fc
        L_0x02ee:
            r0 = move-exception
            r22 = r3
            r3 = r4
            r27 = r5
            r20 = r6
            r18 = r7
            r25 = r9
            r26 = r10
        L_0x02fc:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "scanFontPackages: failed to open the font file for package "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r4 = r4.toString()
            r5 = 6
            r1.trace(r4, r5, r0)
        L_0x0313:
            r4 = r3
            r7 = r18
            r6 = r20
            r3 = r22
            r9 = r25
            r10 = r26
            r5 = r27
            goto L_0x0055
        L_0x0322:
            r20 = r6
            r18 = r7
            if (r2 != 0) goto L_0x0336
            java.util.Map<java.lang.String, com.motorola.styles.model.OnlineFontService$FontInfo> r0 = r1.mFontInfoCache
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0336
            java.lang.String r0 = "scanFontPackages: failed to find any fonts"
            r3 = 3
            r1.trace(r0, r3)
        L_0x0336:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "scan fonts for package "
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = " done"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r2 = 3
            r1.trace(r0, r2)
            if (r11 != 0) goto L_0x035b
            if (r8 == 0) goto L_0x0358
            goto L_0x035b
        L_0x0358:
            r6 = r18
            goto L_0x035d
        L_0x035b:
            r6 = r20
        L_0x035d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.styles.model.OnlineFontServiceImpl.scanFontPackages(java.lang.String):boolean");
    }

    private boolean cleanupGhostFontFiles() {
        File[] listFiles = this.systemFontsDir.listFiles(WIDE_FONT_FILE_FILTER);
        if (listFiles == null) {
            return false;
        }
        boolean z = false;
        for (File file : listFiles) {
            if (this.mFontInfoCache.values().stream().noneMatch(new Predicate(file) {
                public final /* synthetic */ File f$0;

                {
                    this.f$0 = r1;
                }

                public final boolean test(Object obj) {
                    return Objects.equals(((OnlineFontService.FontInfo) obj).fileName, this.f$0.getName());
                }
            })) {
                trace("cleanup ghost file " + file, 2);
                z |= file.delete();
            }
        }
        return z;
    }

    private boolean cleanupGhostFontFileForPackage(String str) {
        if (this.mFontInfoCache.containsKey(str)) {
            return false;
        }
        boolean z = false;
        for (FontFileExtension buildFileName : FONT_EXTENSIONS) {
            File file = new File(this.systemFontsDir, buildFileName.buildFileName(str));
            if (file.exists()) {
                trace("cleanup ghost file " + file, 2);
                z = file.delete();
            }
        }
        return z;
    }

    private int loadPersistedFontsXml() {
        InputStream newInputStream;
        trace("start to load persisted fonts", 4);
        try {
            newInputStream = Files.newInputStream(this.systemFontXmlFile.toPath(), new OpenOption[]{StandardOpenOption.READ});
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(newInputStream, StandardCharsets.UTF_8.name());
            XmlUtils.beginDocument(newPullParser, Fonts.TAG_FONTS);
            if (Fonts.VERSION.equals(newPullParser.getAttributeValue((String) null, "version"))) {
                int parseV1Fonts = parseV1Fonts(newPullParser);
                if (newInputStream != null) {
                    newInputStream.close();
                }
                return parseV1Fonts;
            }
            if (newInputStream != null) {
                newInputStream.close();
            }
            return -1;
        } catch (IOException | XmlPullParserException e) {
            this.mFontInfoCache.clear();
            trace("load persisted fonts failed ", 6, e);
            return -2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private int parseV1Fonts(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        while (true) {
            XmlUtils.nextElement(xmlPullParser);
            String name = xmlPullParser.getName();
            if (name == null) {
                return 0;
            }
            if ("font".equals(name)) {
                parseFont(xmlPullParser);
            }
            XmlUtils.skipCurrentTag(xmlPullParser);
        }
    }

    private void parseFont(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Throwable th;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String throwIfInvalidAttributeValue = XmlUtils.throwIfInvalidAttributeValue(xmlPullParser2, "package");
        String throwIfInvalidAttributeValue2 = XmlUtils.throwIfInvalidAttributeValue(xmlPullParser2, "name");
        String throwIfInvalidAttributeValue3 = XmlUtils.throwIfInvalidAttributeValue(xmlPullParser2, Fonts.FONT_ATTR_CHECKSUM);
        String throwIfInvalidAttributeValue4 = XmlUtils.throwIfInvalidAttributeValue(xmlPullParser2, Fonts.FONT_ATTR_FILE);
        boolean parseBoolean = Boolean.parseBoolean(XmlUtils.throwIfInvalidAttributeValue(xmlPullParser2, Fonts.FONT_ATTR_UNINSTALLABLE));
        long parseLong = Long.parseLong(XmlUtils.throwIfInvalidAttributeValue(xmlPullParser2, Fonts.FONT_ATTR_INSTALL_TIME));
        long parseLong2 = Long.parseLong(XmlUtils.throwIfInvalidAttributeValue(xmlPullParser2, Fonts.FONT_ATTR_UPDATE_TIME));
        File file = new File(this.systemFontsDir, throwIfInvalidAttributeValue4);
        if (!file.exists() || !file.canRead() || !file.canWrite() || !file.isFile()) {
            trace("local font file not exist, " + file, 5);
            file.delete();
            throw new XmlPullParserException("Local font file not exist, " + file);
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileChannel channel = fileInputStream.getChannel();
            if (Objects.equals(StylesUtilities.md5Checksum(channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size())), throwIfInvalidAttributeValue3)) {
                fileInputStream.close();
                try {
                    boolean applicationNonSystemApp = applicationNonSystemApp(this.mPm.getApplicationInfo(throwIfInvalidAttributeValue, 0));
                    if (parseBoolean != applicationNonSystemApp) {
                        trace("local font package flags updated " + throwIfInvalidAttributeValue, 2);
                        parseBoolean = applicationNonSystemApp;
                    }
                    if (!this.mFontInfoCache.containsKey(throwIfInvalidAttributeValue)) {
                        this.mFontInfoCache.put(throwIfInvalidAttributeValue, new OnlineFontService.FontInfo.Builder().setPackageName(throwIfInvalidAttributeValue).setName(throwIfInvalidAttributeValue2).setChecksum(throwIfInvalidAttributeValue3).setFileName(throwIfInvalidAttributeValue4).setFile(file).setUninstallable(parseBoolean).setInstallTime(parseLong).setUpdateTime(parseLong2).build());
                        return;
                    }
                    throw new XmlPullParserException("Duplicate font for package " + throwIfInvalidAttributeValue);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    trace("local font package " + throwIfInvalidAttributeValue + " removed", 5);
                    throw new XmlPullParserException("local font package " + throwIfInvalidAttributeValue + " removed");
                }
            } else {
                trace("local font file checksum changed, " + file, 5);
                file.delete();
                throw new XmlPullParserException("Font file checksum changed, " + file);
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private void notifyFontUpdate() {
        this.mUiHandler.post(new Runnable() {
            public final void run() {
                OnlineFontServiceImpl.this.lambda$notifyFontUpdate$6$OnlineFontServiceImpl();
            }
        });
    }

    public /* synthetic */ void lambda$notifyFontUpdate$6$OnlineFontServiceImpl() {
        this.mListeners.forEach($$Lambda$u7dvNzW4GmeCgZ9GHwievAd7pk.INSTANCE);
    }

    private ByteBuffer readAssetsFileAsByteBuffer(AssetManager assetManager, String str) throws IOException {
        InputStream readAssetsFileAsInputStream = readAssetsFileAsInputStream(assetManager, str);
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(readAssetsFileAsInputStream.available());
            allocateDirect.order(ByteOrder.nativeOrder());
            readAssetsFileAsInputStream.read(allocateDirect.array(), allocateDirect.arrayOffset(), readAssetsFileAsInputStream.available());
            if (readAssetsFileAsInputStream.read() == -1) {
                if (readAssetsFileAsInputStream != null) {
                    readAssetsFileAsInputStream.close();
                }
                return allocateDirect;
            }
            throw new IOException("Unable to access full contents of " + str);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private InputStream readAssetsFileAsInputStream(AssetManager assetManager, String str) throws IOException {
        return assetManager.open(str, 3);
    }

    private boolean applicationNonSystemApp(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 129) == 0;
    }

    private File getCurrentOnlineFont() {
        String str = SystemProperties.get("persist.sys.overlayfonts", (String) null);
        if (str != null) {
            File file = new File(str);
            if (file.exists() && file.isFile() && file.getAbsolutePath().startsWith(Fonts.FONT_CACHE_DIR)) {
                return file.getAbsoluteFile();
            }
        }
        return null;
    }

    public String getCurrentOnlineFontValue() {
        String str = SystemProperties.get("persist.sys.overlayfonts", (String) null);
        if (str == null) {
            return null;
        }
        return str.substring(str.lastIndexOf(CustomAppIcons.UNDERSCORE) + 1, str.lastIndexOf(".ttf"));
    }

    private void trace(String str, int i) {
        trace(str, i, (Throwable) null);
    }

    private void trace(String str, int i, Throwable th) {
        if (LogConfig.DBG || Log.isLoggable(TAG, 4)) {
            if (th != null) {
                str = str + "\n" + Log.getStackTraceString(th);
            }
            Log.println(i, TAG, str);
        }
    }

    private static class PackageChangeCallback extends LauncherApps.Callback {
        private final Handler mHandler;

        public PackageChangeCallback(Handler handler) {
            this.mHandler = handler;
        }

        public void onPackageRemoved(String str, UserHandle userHandle) {
            Message.obtain(this.mHandler, 2, str).sendToTarget();
        }

        public void onPackageAdded(String str, UserHandle userHandle) {
            Message.obtain(this.mHandler, 1, str).sendToTarget();
        }

        public void onPackageChanged(String str, UserHandle userHandle) {
            Message.obtain(this.mHandler, 1, str).sendToTarget();
        }

        public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
            Message.obtain(this.mHandler, 1, strArr).sendToTarget();
        }

        public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
            Message.obtain(this.mHandler, 2, strArr).sendToTarget();
        }
    }

    private static abstract class AbstractFontExt implements FontFileExtension {
        private final String ext;

        AbstractFontExt(String str) {
            this.ext = str;
        }

        public String name(String str) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf >= 0) {
                return str.substring(0, lastIndexOf);
            }
            return null;
        }

        public String ext() {
            return this.ext;
        }

        public boolean match(String str) {
            return str.endsWith("." + this.ext);
        }

        public String buildFileName(String str) {
            return String.format(Fonts.FONT_FILE_NAME_TEMPLATE, new Object[]{str, this.ext});
        }
    }

    private static class TTF_FontExt extends AbstractFontExt {
        public TTF_FontExt() {
            super(Fonts.FONT_FILE_EXT_TTF);
        }
    }

    private static class OTF_FontExt extends AbstractFontExt {
        public OTF_FontExt() {
            super(Fonts.FONT_FILE_EXT_OTF);
        }
    }
}
