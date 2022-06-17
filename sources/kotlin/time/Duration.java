package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@JvmInline
@Metadata(mo15461d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\b@\u0018\u0000 ¥\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002¥\u0001B\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bN\u0010OJ\u001b\u0010P\u001a\u00020\t2\u0006\u0010Q\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ\u001e\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u000fH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bV\u0010WJ\u001e\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\tH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bV\u0010XJ\u001b\u0010T\u001a\u00020\u000f2\u0006\u0010Q\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\bY\u0010ZJ\u001a\u0010[\u001a\u00020\\2\b\u0010Q\u001a\u0004\u0018\u00010]HÖ\u0003¢\u0006\u0004\b^\u0010_J\u0010\u0010`\u001a\u00020\tHÖ\u0001¢\u0006\u0004\ba\u0010\rJ\r\u0010b\u001a\u00020\\¢\u0006\u0004\bc\u0010dJ\u000f\u0010e\u001a\u00020\\H\u0002¢\u0006\u0004\bf\u0010dJ\u000f\u0010g\u001a\u00020\\H\u0002¢\u0006\u0004\bh\u0010dJ\r\u0010i\u001a\u00020\\¢\u0006\u0004\bj\u0010dJ\r\u0010k\u001a\u00020\\¢\u0006\u0004\bl\u0010dJ\r\u0010m\u001a\u00020\\¢\u0006\u0004\bn\u0010dJ\u001b\u0010o\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\bp\u0010qJ\u001b\u0010r\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\bs\u0010qJ\u0017\u0010t\u001a\u00020\t2\u0006\u0010I\u001a\u00020\u000fH\u0002¢\u0006\u0004\bu\u0010vJ\u001e\u0010w\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\u000fH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bx\u0010WJ\u001e\u0010w\u001a\u00020\u00002\u0006\u0010U\u001a\u00020\tH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bx\u0010XJ£\u0001\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z2y\u0010{\u001au\u0012\u0013\u0012\u00110\t¢\u0006\f\b}\u0012\b\b~\u0012\u0004\b\b(\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u0002Hz0|H\bø\u0001\u0002\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u0001\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z2e\u0010{\u001aa\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u0002Hz0\u0001H\bø\u0001\u0002\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0001\u0010\u0001Jy\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z2O\u0010{\u001aK\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u0002Hz0\u0001H\bø\u0001\u0002\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0001\u0010\u0001Jc\u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010z29\u0010{\u001a5\u0012\u0014\u0012\u00120\u0003¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u0002Hz0\u0001H\bø\u0001\u0002\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u001e\u0010\u0001\u001a\u00020\u000f2\f\u0010\u0001\u001a\u00070Dj\u0003`\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u001e\u0010\u0001\u001a\u00020\t2\f\u0010\u0001\u001a\u00070Dj\u0003`\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u0011\u0010\u0001\u001a\u00030\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u001e\u0010\u0001\u001a\u00020\u00032\f\u0010\u0001\u001a\u00070Dj\u0003`\u0001¢\u0006\u0006\b\u0001\u0010\u0001J\u0011\u0010\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u0001\u0010\u0005J\u0011\u0010\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u0001\u0010\u0005J\u0013\u0010\u0001\u001a\u00030\u0001H\u0016¢\u0006\u0006\b \u0001\u0010\u0001J*\u0010\u0001\u001a\u00030\u00012\f\u0010\u0001\u001a\u00070Dj\u0003`\u00012\t\b\u0002\u0010¡\u0001\u001a\u00020\t¢\u0006\u0006\b \u0001\u0010¢\u0001J\u0018\u0010£\u0001\u001a\u00020\u0000H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0005\b¤\u0001\u0010\u0005R\u0017\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000f8FX\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000f8FX\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000f8FX\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000f8FX\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u000f8FX\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\u000f8FX\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\u0012R\u001a\u0010\"\u001a\u00020\u000f8FX\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\u0012R\u001a\u0010%\u001a\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b&\u0010\u000b\u001a\u0004\b'\u0010\u0005R\u001a\u0010(\u001a\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b)\u0010\u000b\u001a\u0004\b*\u0010\u0005R\u001a\u0010+\u001a\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b,\u0010\u000b\u001a\u0004\b-\u0010\u0005R\u001a\u0010.\u001a\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b/\u0010\u000b\u001a\u0004\b0\u0010\u0005R\u001a\u00101\u001a\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b2\u0010\u000b\u001a\u0004\b3\u0010\u0005R\u001a\u00104\u001a\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b5\u0010\u000b\u001a\u0004\b6\u0010\u0005R\u001a\u00107\u001a\u00020\u00038FX\u0004¢\u0006\f\u0012\u0004\b8\u0010\u000b\u001a\u0004\b9\u0010\u0005R\u001a\u0010:\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b;\u0010\u000b\u001a\u0004\b<\u0010\rR\u001a\u0010=\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b>\u0010\u000b\u001a\u0004\b?\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\bA\u0010\u000b\u001a\u0004\bB\u0010\rR\u0014\u0010C\u001a\u00020D8BX\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0015\u0010G\u001a\u00020\t8Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\rR\u0014\u0010I\u001a\u00020\u00038BX\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010\u0005\u0001\u0002\u0001\u00020\u0003ø\u0001\u0000\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b20\u0001¨\u0006¦\u0001"}, mo15462d2 = {"Lkotlin/time/Duration;", "", "rawValue", "", "constructor-impl", "(J)J", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(J)I", "inDays", "", "getInDays$annotations", "getInDays-impl", "(J)D", "inHours", "getInHours$annotations", "getInHours-impl", "inMicroseconds", "getInMicroseconds$annotations", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds$annotations", "getInMilliseconds-impl", "inMinutes", "getInMinutes$annotations", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds$annotations", "getInNanoseconds-impl", "inSeconds", "getInSeconds$annotations", "getInSeconds-impl", "inWholeDays", "getInWholeDays$annotations", "getInWholeDays-impl", "inWholeHours", "getInWholeHours$annotations", "getInWholeHours-impl", "inWholeMicroseconds", "getInWholeMicroseconds$annotations", "getInWholeMicroseconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds$annotations", "getInWholeMilliseconds-impl", "inWholeMinutes", "getInWholeMinutes$annotations", "getInWholeMinutes-impl", "inWholeNanoseconds", "getInWholeNanoseconds$annotations", "getInWholeNanoseconds-impl", "inWholeSeconds", "getInWholeSeconds$annotations", "getInWholeSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "storageUnit", "Ljava/util/concurrent/TimeUnit;", "getStorageUnit-impl", "(J)Ljava/util/concurrent/TimeUnit;", "unitDiscriminator", "getUnitDiscriminator-impl", "value", "getValue-impl", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "compareTo", "other", "compareTo-LRDsOJo", "(JJ)I", "div", "scale", "div-UwyO8pc", "(JD)J", "(JI)J", "div-LRDsOJo", "(JJ)D", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "isFinite", "isFinite-impl", "(J)Z", "isInMillis", "isInMillis-impl", "isInNanos", "isInNanos-impl", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "(JJ)J", "plus", "plus-LRDsOJo", "precision", "precision-impl", "(JD)I", "times", "times-UwyO8pc", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(JLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(JLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(J)Ljava/lang/String;", "toLong", "toLong-impl", "(JLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(JLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-UwyO8pc", "Companion", "kotlin-stdlib"}, mo15463k = 1, mo15464mv = {1, 5, 1})
/* compiled from: Duration.kt */
public final class Duration implements Comparable<Duration> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final long INFINITE = DurationKt.durationOfMillis(DurationKt.MAX_MILLIS);
    /* access modifiers changed from: private */
    public static final long NEG_INFINITE = DurationKt.durationOfMillis(-4611686018427387903L);
    /* access modifiers changed from: private */
    public static final long ZERO = m1402constructorimpl(0);
    private final long rawValue;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m1400boximpl(long j) {
        return new Duration(j);
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1406equalsimpl(long j, Object obj) {
        return (obj instanceof Duration) && j == ((Duration) obj).m1457unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1407equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    @Deprecated(message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.DAYS)", imports = {}))
    public static /* synthetic */ void getInDays$annotations() {
    }

    @Deprecated(message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.HOURS)", imports = {}))
    public static /* synthetic */ void getInHours$annotations() {
    }

    @Deprecated(message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MICROSECONDS)", imports = {}))
    public static /* synthetic */ void getInMicroseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MILLISECONDS)", imports = {}))
    public static /* synthetic */ void getInMilliseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MINUTES)", imports = {}))
    public static /* synthetic */ void getInMinutes$annotations() {
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.NANOSECONDS)", imports = {}))
    public static /* synthetic */ void getInNanoseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.SECONDS)", imports = {}))
    public static /* synthetic */ void getInSeconds$annotations() {
    }

    public static /* synthetic */ void getInWholeDays$annotations() {
    }

    public static /* synthetic */ void getInWholeHours$annotations() {
    }

    public static /* synthetic */ void getInWholeMicroseconds$annotations() {
    }

    public static /* synthetic */ void getInWholeMilliseconds$annotations() {
    }

    public static /* synthetic */ void getInWholeMinutes$annotations() {
    }

    public static /* synthetic */ void getInWholeNanoseconds$annotations() {
    }

    public static /* synthetic */ void getInWholeSeconds$annotations() {
    }

    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    /* renamed from: getUnitDiscriminator-impl  reason: not valid java name */
    private static final int m1428getUnitDiscriminatorimpl(long j) {
        return ((int) j) & 1;
    }

    /* renamed from: getValue-impl  reason: not valid java name */
    private static final long m1429getValueimpl(long j) {
        return j >> 1;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1430hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* renamed from: isInMillis-impl  reason: not valid java name */
    private static final boolean m1432isInMillisimpl(long j) {
        return (((int) j) & 1) == 1;
    }

    /* renamed from: isInNanos-impl  reason: not valid java name */
    private static final boolean m1433isInNanosimpl(long j) {
        return (((int) j) & 1) == 0;
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m1435isNegativeimpl(long j) {
        return j < 0;
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m1436isPositiveimpl(long j) {
        return j > 0;
    }

    /* renamed from: precision-impl  reason: not valid java name */
    private static final int m1439precisionimpl(long j, double d) {
        if (d < ((double) 1)) {
            return 3;
        }
        if (d < ((double) 10)) {
            return 2;
        }
        return d < ((double) 100) ? 1 : 0;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m1456compareToLRDsOJo(long j) {
        return m1401compareToLRDsOJo(this.rawValue, j);
    }

    public boolean equals(Object obj) {
        return m1406equalsimpl(this.rawValue, obj);
    }

    public int hashCode() {
        return m1430hashCodeimpl(this.rawValue);
    }

    public String toString() {
        return m1452toStringimpl(this.rawValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m1457unboximpl() {
        return this.rawValue;
    }

    private /* synthetic */ Duration(long j) {
        this.rawValue = j;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m1456compareToLRDsOJo(((Duration) obj).m1457unboximpl());
    }

    /* renamed from: getStorageUnit-impl  reason: not valid java name */
    private static final TimeUnit m1427getStorageUnitimpl(long j) {
        return m1433isInNanosimpl(j) ? TimeUnit.NANOSECONDS : TimeUnit.MILLISECONDS;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m1402constructorimpl(long j) {
        if (m1433isInNanosimpl(j)) {
            long r4 = m1429getValueimpl(j);
            if (-4611686018426999999L > r4 || DurationKt.MAX_NANOS < r4) {
                throw new AssertionError(m1429getValueimpl(j) + " ns is out of nanoseconds range");
            }
        } else {
            long r42 = m1429getValueimpl(j);
            if (-4611686018427387903L > r42 || DurationKt.MAX_MILLIS < r42) {
                throw new AssertionError(m1429getValueimpl(j) + " ms is out of milliseconds range");
            }
            long r43 = m1429getValueimpl(j);
            if (-4611686018426L <= r43 && 4611686018426L >= r43) {
                throw new AssertionError(m1429getValueimpl(j) + " ms is denormalized");
            }
        }
        return j;
    }

    @Metadata(mo15461d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u00112\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0017J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0019J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0015J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0019J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0015J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0017J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0019J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0015J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0017J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0019J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\u0015J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\u0017J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\u0019J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0015J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0017J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0019J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0015J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0017J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0019R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004X\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006&"}, mo15462d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()J", "J", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "ZERO", "getZERO-UwyO8pc", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "days", "days-UwyO8pc", "(D)J", "", "(I)J", "", "(J)J", "hours", "hours-UwyO8pc", "microseconds", "microseconds-UwyO8pc", "milliseconds", "milliseconds-UwyO8pc", "minutes", "minutes-UwyO8pc", "nanoseconds", "nanoseconds-UwyO8pc", "seconds", "seconds-UwyO8pc", "kotlin-stdlib"}, mo15463k = 1, mo15464mv = {1, 5, 1})
    /* compiled from: Duration.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: getZERO-UwyO8pc  reason: not valid java name */
        public final long m1463getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        /* renamed from: getINFINITE-UwyO8pc  reason: not valid java name */
        public final long m1461getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        /* renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib  reason: not valid java name */
        public final long m1462getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.NEG_INFINITE;
        }

        public final double convert(double d, TimeUnit timeUnit, TimeUnit timeUnit2) {
            Intrinsics.checkNotNullParameter(timeUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(timeUnit2, "targetUnit");
            return DurationUnitKt.convertDurationUnit(d, timeUnit, timeUnit2);
        }

        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m1477nanosecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, TimeUnit.NANOSECONDS);
        }

        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m1478nanosecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, TimeUnit.NANOSECONDS);
        }

        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m1476nanosecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, TimeUnit.NANOSECONDS);
        }

        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m1468microsecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, TimeUnit.MICROSECONDS);
        }

        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m1469microsecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, TimeUnit.MICROSECONDS);
        }

        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m1467microsecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, TimeUnit.MICROSECONDS);
        }

        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m1471millisecondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, TimeUnit.MILLISECONDS);
        }

        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m1472millisecondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, TimeUnit.MILLISECONDS);
        }

        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m1470millisecondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, TimeUnit.MILLISECONDS);
        }

        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m1480secondsUwyO8pc(int i) {
            return DurationKt.toDuration(i, TimeUnit.SECONDS);
        }

        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m1481secondsUwyO8pc(long j) {
            return DurationKt.toDuration(j, TimeUnit.SECONDS);
        }

        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m1479secondsUwyO8pc(double d) {
            return DurationKt.toDuration(d, TimeUnit.SECONDS);
        }

        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m1474minutesUwyO8pc(int i) {
            return DurationKt.toDuration(i, TimeUnit.MINUTES);
        }

        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m1475minutesUwyO8pc(long j) {
            return DurationKt.toDuration(j, TimeUnit.MINUTES);
        }

        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m1473minutesUwyO8pc(double d) {
            return DurationKt.toDuration(d, TimeUnit.MINUTES);
        }

        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m1465hoursUwyO8pc(int i) {
            return DurationKt.toDuration(i, TimeUnit.HOURS);
        }

        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m1466hoursUwyO8pc(long j) {
            return DurationKt.toDuration(j, TimeUnit.HOURS);
        }

        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m1464hoursUwyO8pc(double d) {
            return DurationKt.toDuration(d, TimeUnit.HOURS);
        }

        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m1459daysUwyO8pc(int i) {
            return DurationKt.toDuration(i, TimeUnit.DAYS);
        }

        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m1460daysUwyO8pc(long j) {
            return DurationKt.toDuration(j, TimeUnit.DAYS);
        }

        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m1458daysUwyO8pc(double d) {
            return DurationKt.toDuration(d, TimeUnit.DAYS);
        }
    }

    /* renamed from: unaryMinus-UwyO8pc  reason: not valid java name */
    public static final long m1455unaryMinusUwyO8pc(long j) {
        return DurationKt.durationOf(-m1429getValueimpl(j), ((int) j) & 1);
    }

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public static final long m1438plusLRDsOJo(long j, long j2) {
        if (m1434isInfiniteimpl(j)) {
            if (m1431isFiniteimpl(j2) || (j2 ^ j) >= 0) {
                return j;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        } else if (m1434isInfiniteimpl(j2)) {
            return j2;
        } else {
            if ((((int) j) & 1) == (((int) j2) & 1)) {
                long r0 = m1429getValueimpl(j) + m1429getValueimpl(j2);
                if (m1433isInNanosimpl(j)) {
                    return DurationKt.durationOfNanosNormalized(r0);
                }
                return DurationKt.durationOfMillisNormalized(r0);
            } else if (m1432isInMillisimpl(j)) {
                return m1399addValuesMixedRangesUwyO8pc(j, m1429getValueimpl(j), m1429getValueimpl(j2));
            } else {
                return m1399addValuesMixedRangesUwyO8pc(j, m1429getValueimpl(j2), m1429getValueimpl(j));
            }
        }
    }

    /* renamed from: addValuesMixedRanges-UwyO8pc  reason: not valid java name */
    private static final long m1399addValuesMixedRangesUwyO8pc(long j, long j2, long j3) {
        long access$nanosToMillis = DurationKt.nanosToMillis(j3);
        long j4 = j2 + access$nanosToMillis;
        if (-4611686018426L > j4 || 4611686018426L < j4) {
            return DurationKt.durationOfMillis(RangesKt.coerceIn(j4, -4611686018427387903L, (long) DurationKt.MAX_MILLIS));
        }
        return DurationKt.durationOfNanos(DurationKt.millisToNanos(j4) + (j3 - DurationKt.millisToNanos(access$nanosToMillis)));
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public static final long m1437minusLRDsOJo(long j, long j2) {
        return m1438plusLRDsOJo(j, m1455unaryMinusUwyO8pc(j2));
    }

    /* renamed from: times-UwyO8pc  reason: not valid java name */
    public static final long m1441timesUwyO8pc(long j, int i) {
        if (m1434isInfiniteimpl(j)) {
            if (i != 0) {
                return i > 0 ? j : m1455unaryMinusUwyO8pc(j);
            }
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
        } else if (i == 0) {
            return ZERO;
        } else {
            long r0 = m1429getValueimpl(j);
            long j2 = (long) i;
            long j3 = r0 * j2;
            if (m1433isInNanosimpl(j)) {
                if (-2147483647L <= r0 && 2147483647L >= r0) {
                    return DurationKt.durationOfNanos(j3);
                }
                if (j3 / j2 == r0) {
                    return DurationKt.durationOfNanosNormalized(j3);
                }
                long access$nanosToMillis = DurationKt.nanosToMillis(r0);
                long j4 = access$nanosToMillis * j2;
                long access$nanosToMillis2 = DurationKt.nanosToMillis((r0 - DurationKt.millisToNanos(access$nanosToMillis)) * j2) + j4;
                if (j4 / j2 != access$nanosToMillis || (access$nanosToMillis2 ^ j4) < 0) {
                    return MathKt.getSign(r0) * MathKt.getSign(i) > 0 ? INFINITE : NEG_INFINITE;
                }
                return DurationKt.durationOfMillis(RangesKt.coerceIn(access$nanosToMillis2, (ClosedRange<Long>) new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            } else if (j3 / j2 == r0) {
                return DurationKt.durationOfMillis(RangesKt.coerceIn(j3, (ClosedRange<Long>) new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            } else {
                return MathKt.getSign(r0) * MathKt.getSign(i) > 0 ? INFINITE : NEG_INFINITE;
            }
        }
    }

    /* renamed from: times-UwyO8pc  reason: not valid java name */
    public static final long m1440timesUwyO8pc(long j, double d) {
        int roundToInt = MathKt.roundToInt(d);
        if (((double) roundToInt) == d) {
            return m1441timesUwyO8pc(j, roundToInt);
        }
        TimeUnit r0 = m1427getStorageUnitimpl(j);
        return DurationKt.toDuration(m1446toDoubleimpl(j, r0) * d, r0);
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m1405divUwyO8pc(long j, int i) {
        if (i == 0) {
            if (m1436isPositiveimpl(j)) {
                return INFINITE;
            }
            if (m1435isNegativeimpl(j)) {
                return NEG_INFINITE;
            }
            throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
        } else if (m1433isInNanosimpl(j)) {
            return DurationKt.durationOfNanos(m1429getValueimpl(j) / ((long) i));
        } else {
            if (m1434isInfiniteimpl(j)) {
                return m1441timesUwyO8pc(j, MathKt.getSign(i));
            }
            long j2 = (long) i;
            long r0 = m1429getValueimpl(j) / j2;
            if (-4611686018426L > r0 || 4611686018426L < r0) {
                return DurationKt.durationOfMillis(r0);
            }
            return DurationKt.durationOfNanos(DurationKt.millisToNanos(r0) + (DurationKt.millisToNanos(m1429getValueimpl(j) - (r0 * j2)) / j2));
        }
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m1404divUwyO8pc(long j, double d) {
        int roundToInt = MathKt.roundToInt(d);
        if (((double) roundToInt) == d && roundToInt != 0) {
            return m1405divUwyO8pc(j, roundToInt);
        }
        TimeUnit r0 = m1427getStorageUnitimpl(j);
        return DurationKt.toDuration(m1446toDoubleimpl(j, r0) / d, r0);
    }

    /* renamed from: div-LRDsOJo  reason: not valid java name */
    public static final double m1403divLRDsOJo(long j, long j2) {
        TimeUnit timeUnit = (TimeUnit) ComparisonsKt.maxOf(m1427getStorageUnitimpl(j), m1427getStorageUnitimpl(j2));
        return m1446toDoubleimpl(j, timeUnit) / m1446toDoubleimpl(j2, timeUnit);
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m1434isInfiniteimpl(long j) {
        return j == INFINITE || j == NEG_INFINITE;
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m1431isFiniteimpl(long j) {
        return !m1434isInfiniteimpl(j);
    }

    /* renamed from: getAbsoluteValue-UwyO8pc  reason: not valid java name */
    public static final long m1408getAbsoluteValueUwyO8pc(long j) {
        return m1435isNegativeimpl(j) ? m1455unaryMinusUwyO8pc(j) : j;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m1401compareToLRDsOJo(long j, long j2) {
        long j3 = j ^ j2;
        if (j3 < 0 || (((int) j3) & 1) == 0) {
            return (j > j2 ? 1 : (j == j2 ? 0 : -1));
        }
        int i = (((int) j) & 1) - (((int) j2) & 1);
        return m1435isNegativeimpl(j) ? -i : i;
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1445toComponentsimpl(long j, Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function5) {
        Intrinsics.checkNotNullParameter(function5, "action");
        return function5.invoke(Integer.valueOf(m1447toIntimpl(j, TimeUnit.DAYS)), Integer.valueOf(m1409getHoursComponentimpl(j)), Integer.valueOf(m1424getMinutesComponentimpl(j)), Integer.valueOf(m1426getSecondsComponentimpl(j)), Integer.valueOf(m1425getNanosecondsComponentimpl(j)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1444toComponentsimpl(long j, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function4) {
        Intrinsics.checkNotNullParameter(function4, "action");
        return function4.invoke(Integer.valueOf(m1447toIntimpl(j, TimeUnit.HOURS)), Integer.valueOf(m1424getMinutesComponentimpl(j)), Integer.valueOf(m1426getSecondsComponentimpl(j)), Integer.valueOf(m1425getNanosecondsComponentimpl(j)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1443toComponentsimpl(long j, Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(function3, "action");
        return function3.invoke(Integer.valueOf(m1447toIntimpl(j, TimeUnit.MINUTES)), Integer.valueOf(m1426getSecondsComponentimpl(j)), Integer.valueOf(m1425getNanosecondsComponentimpl(j)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m1442toComponentsimpl(long j, Function2<? super Long, ? super Integer, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "action");
        return function2.invoke(Long.valueOf(m1423getInWholeSecondsimpl(j)), Integer.valueOf(m1425getNanosecondsComponentimpl(j)));
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m1409getHoursComponentimpl(long j) {
        if (m1434isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m1418getInWholeHoursimpl(j) % ((long) 24));
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m1424getMinutesComponentimpl(long j) {
        if (m1434isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m1421getInWholeMinutesimpl(j) % ((long) 60));
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m1426getSecondsComponentimpl(long j) {
        if (m1434isInfiniteimpl(j)) {
            return 0;
        }
        return (int) (m1423getInWholeSecondsimpl(j) % ((long) 60));
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m1425getNanosecondsComponentimpl(long j) {
        long j2;
        if (m1434isInfiniteimpl(j)) {
            return 0;
        }
        if (m1432isInMillisimpl(j)) {
            j2 = DurationKt.millisToNanos(m1429getValueimpl(j) % ((long) 1000));
        } else {
            j2 = m1429getValueimpl(j) % ((long) 1000000000);
        }
        return (int) j2;
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    public static final double m1446toDoubleimpl(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        if (j == INFINITE) {
            return Double.POSITIVE_INFINITY;
        }
        if (j == NEG_INFINITE) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt.convertDurationUnit((double) m1429getValueimpl(j), m1427getStorageUnitimpl(j), timeUnit);
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m1449toLongimpl(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        if (j == INFINITE) {
            return LongCompanionObject.MAX_VALUE;
        }
        if (j == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt.convertDurationUnit(m1429getValueimpl(j), m1427getStorageUnitimpl(j), timeUnit);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    public static final int m1447toIntimpl(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        return (int) RangesKt.coerceIn(m1449toLongimpl(j, timeUnit), (long) Integer.MIN_VALUE, (long) Integer.MAX_VALUE);
    }

    /* renamed from: getInDays-impl  reason: not valid java name */
    public static final double m1410getInDaysimpl(long j) {
        return m1446toDoubleimpl(j, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl  reason: not valid java name */
    public static final double m1411getInHoursimpl(long j) {
        return m1446toDoubleimpl(j, TimeUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl  reason: not valid java name */
    public static final double m1414getInMinutesimpl(long j) {
        return m1446toDoubleimpl(j, TimeUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl  reason: not valid java name */
    public static final double m1416getInSecondsimpl(long j) {
        return m1446toDoubleimpl(j, TimeUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl  reason: not valid java name */
    public static final double m1413getInMillisecondsimpl(long j) {
        return m1446toDoubleimpl(j, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl  reason: not valid java name */
    public static final double m1412getInMicrosecondsimpl(long j) {
        return m1446toDoubleimpl(j, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl  reason: not valid java name */
    public static final double m1415getInNanosecondsimpl(long j) {
        return m1446toDoubleimpl(j, TimeUnit.NANOSECONDS);
    }

    /* renamed from: getInWholeDays-impl  reason: not valid java name */
    public static final long m1417getInWholeDaysimpl(long j) {
        return m1449toLongimpl(j, TimeUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl  reason: not valid java name */
    public static final long m1418getInWholeHoursimpl(long j) {
        return m1449toLongimpl(j, TimeUnit.HOURS);
    }

    /* renamed from: getInWholeMinutes-impl  reason: not valid java name */
    public static final long m1421getInWholeMinutesimpl(long j) {
        return m1449toLongimpl(j, TimeUnit.MINUTES);
    }

    /* renamed from: getInWholeSeconds-impl  reason: not valid java name */
    public static final long m1423getInWholeSecondsimpl(long j) {
        return m1449toLongimpl(j, TimeUnit.SECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl  reason: not valid java name */
    public static final long m1420getInWholeMillisecondsimpl(long j) {
        return (!m1432isInMillisimpl(j) || !m1431isFiniteimpl(j)) ? m1449toLongimpl(j, TimeUnit.MILLISECONDS) : m1429getValueimpl(j);
    }

    /* renamed from: getInWholeMicroseconds-impl  reason: not valid java name */
    public static final long m1419getInWholeMicrosecondsimpl(long j) {
        return m1449toLongimpl(j, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInWholeNanoseconds-impl  reason: not valid java name */
    public static final long m1422getInWholeNanosecondsimpl(long j) {
        long r0 = m1429getValueimpl(j);
        if (m1433isInNanosimpl(j)) {
            return r0;
        }
        if (r0 > 9223372036854L) {
            return LongCompanionObject.MAX_VALUE;
        }
        if (r0 < -9223372036854L) {
            return Long.MIN_VALUE;
        }
        return DurationKt.millisToNanos(r0);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeNanoseconds", imports = {}))
    /* renamed from: toLongNanoseconds-impl  reason: not valid java name */
    public static final long m1451toLongNanosecondsimpl(long j) {
        return m1422getInWholeNanosecondsimpl(j);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeMilliseconds", imports = {}))
    /* renamed from: toLongMilliseconds-impl  reason: not valid java name */
    public static final long m1450toLongMillisecondsimpl(long j) {
        return m1420getInWholeMillisecondsimpl(j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00aa  */
    /* renamed from: toString-impl  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1452toStringimpl(long r8) {
        /*
            r0 = 0
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x000a
            java.lang.String r8 = "0s"
            goto L_0x00cd
        L_0x000a:
            long r0 = INFINITE
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0014
            java.lang.String r8 = "Infinity"
            goto L_0x00cd
        L_0x0014:
            long r0 = NEG_INFINITE
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x001e
            java.lang.String r8 = "-Infinity"
            goto L_0x00cd
        L_0x001e:
            long r0 = m1408getAbsoluteValueUwyO8pc(r8)
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.NANOSECONDS
            double r0 = m1446toDoubleimpl(r0, r2)
            r2 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = 0
            r4 = 1
            if (r2 >= 0) goto L_0x0039
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
        L_0x0035:
            r1 = r3
            r3 = r4
            goto L_0x009a
        L_0x0039:
            double r5 = (double) r4
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x0042
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r1 = 7
            goto L_0x009a
        L_0x0042:
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x004f
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
        L_0x004d:
            r1 = r3
            goto L_0x009a
        L_0x004f:
            r5 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x005b
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MICROSECONDS
            goto L_0x004d
        L_0x005b:
            r5 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x0067
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            goto L_0x004d
        L_0x0067:
            r5 = 4786511204640096256(0x426d1a94a2000000, double:1.0E12)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x0073
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            goto L_0x004d
        L_0x0073:
            r5 = 4813020802404319232(0x42cb48eb57e00000, double:6.0E13)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x007f
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MINUTES
            goto L_0x004d
        L_0x007f:
            r5 = 4839562400168542208(0x4329945ca2620000, double:3.6E15)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x008b
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.HOURS
            goto L_0x004d
        L_0x008b:
            r5 = 4920018990336211136(0x44476b344f2a78c0, double:8.64E20)
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0097
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.DAYS
            goto L_0x004d
        L_0x0097:
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.DAYS
            goto L_0x0035
        L_0x009a:
            double r4 = m1446toDoubleimpl(r8, r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            if (r3 == 0) goto L_0x00aa
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatScientific(r4)
            goto L_0x00bd
        L_0x00aa:
            if (r1 <= 0) goto L_0x00b1
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatUpToDecimals(r4, r1)
            goto L_0x00bd
        L_0x00b1:
            double r6 = java.lang.Math.abs(r4)
            int r8 = m1439precisionimpl(r8, r6)
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatToExactDecimals(r4, r8)
        L_0x00bd:
            java.lang.StringBuilder r8 = r2.append(r8)
            java.lang.String r9 = kotlin.time.DurationUnitKt.shortName(r0)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
        L_0x00cd:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.Duration.m1452toStringimpl(long):java.lang.String");
    }

    /* renamed from: toString-impl$default  reason: not valid java name */
    public static /* synthetic */ String m1454toStringimpl$default(long j, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m1453toStringimpl(j, timeUnit, i);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static final String m1453toStringimpl(long j, TimeUnit timeUnit, int i) {
        String str;
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        if (i >= 0) {
            double r5 = m1446toDoubleimpl(j, timeUnit);
            if (Double.isInfinite(r5)) {
                return String.valueOf(r5);
            }
            StringBuilder sb = new StringBuilder();
            if (Math.abs(r5) < 1.0E14d) {
                str = FormatToDecimalsKt.formatToExactDecimals(r5, RangesKt.coerceAtMost(i, 12));
            } else {
                str = FormatToDecimalsKt.formatScientific(r5);
            }
            return sb.append(str).append(DurationUnitKt.shortName(timeUnit)).toString();
        }
        throw new IllegalArgumentException(("decimals must be not negative, but was " + i).toString());
    }

    /* renamed from: toIsoString-impl  reason: not valid java name */
    public static final String m1448toIsoStringimpl(long j) {
        StringBuilder sb = new StringBuilder();
        if (m1435isNegativeimpl(j)) {
            sb.append('-');
        }
        sb.append("PT");
        long r8 = m1408getAbsoluteValueUwyO8pc(j);
        int r1 = m1447toIntimpl(r8, TimeUnit.HOURS);
        int r2 = m1424getMinutesComponentimpl(r8);
        int r3 = m1426getSecondsComponentimpl(r8);
        int r82 = m1425getNanosecondsComponentimpl(r8);
        boolean z = true;
        boolean z2 = r1 != 0;
        boolean z3 = (r3 == 0 && r82 == 0) ? false : true;
        if (r2 == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(r1).append('H');
        }
        if (z) {
            sb.append(r2).append('M');
        }
        if (z3 || (!z2 && !z)) {
            sb.append(r3);
            if (r82 != 0) {
                sb.append('.');
                String padStart = StringsKt.padStart(String.valueOf(r82), 9, '0');
                if (r82 % DurationKt.NANOS_IN_MILLIS == 0) {
                    Intrinsics.checkNotNullExpressionValue(sb.append(padStart, 0, 3), "this.append(value, startIndex, endIndex)");
                } else if (r82 % 1000 == 0) {
                    Intrinsics.checkNotNullExpressionValue(sb.append(padStart, 0, 6), "this.append(value, startIndex, endIndex)");
                } else {
                    sb.append(padStart);
                }
            }
            sb.append('S');
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
