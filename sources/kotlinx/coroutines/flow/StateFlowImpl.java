package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u0010052\b\u0012\u0004\u0012\u00028\u0000062\b\u0012\u0004\u0012\u00028\u0000072\b\u0012\u0004\u0012\u00028\u000008B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\t\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ-\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\bH\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0004\b%\u0010&J!\u0010)\u001a\u00020\r2\b\u0010'\u001a\u0004\u0018\u00010\u00022\u0006\u0010(\u001a\u00020\u0002H\u0002¢\u0006\u0004\b)\u0010\u000fR\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000*8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0016\u0010.\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010/R*\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00028\u00008V@VX\u000e¢\u0006\u0012\u0012\u0004\b3\u0010$\u001a\u0004\b0\u00101\"\u0004\b2\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u00064"}, mo15462d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "", "initialState", "<init>", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expect", "update", "", "compareAndSet", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/flow/StateFlowSlot;", "createSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "", "size", "", "createSlotArray", "(I)[Lkotlinx/coroutines/flow/StateFlowSlot;", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "context", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlinx/coroutines/flow/Flow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "resetReplayCache", "()V", "tryEmit", "(Ljava/lang/Object;)Z", "expectedState", "newState", "updateState", "", "getReplayCache", "()Ljava/util/List;", "replayCache", "sequence", "I", "getValue", "()Ljava/lang/Object;", "setValue", "getValue$annotations", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;"}, mo15463k = 1, mo15464mv = {1, 4, 0})
/* compiled from: StateFlow.kt */
final class StateFlowImpl<T> extends AbstractSharedFlow<StateFlowSlot> implements MutableStateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private volatile Object _state;
    private int sequence;

    public static /* synthetic */ void getValue$annotations() {
    }

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    public T getValue() {
        T t = NullSurrogateKt.NULL;
        T t2 = this._state;
        if (t2 == t) {
            return null;
        }
        return t2;
    }

    public void setValue(T t) {
        if (t == null) {
            t = NullSurrogateKt.NULL;
        }
        updateState((Object) null, t);
    }

    public boolean compareAndSet(T t, T t2) {
        if (t == null) {
            t = NullSurrogateKt.NULL;
        }
        if (t2 == null) {
            t2 = NullSurrogateKt.NULL;
        }
        return updateState(t, t2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        if (r8 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
        r0 = r8.length;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0036, code lost:
        if (r3 >= r0) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0038, code lost:
        r4 = r8[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003a, code lost:
        if (r4 == null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003c, code lost:
        r4.makePending();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003f, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0042, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r8 = r6.sequence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0045, code lost:
        if (r8 != r7) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0047, code lost:
        r6.sequence = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004a, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r7 = (kotlinx.coroutines.flow.StateFlowSlot[]) getSlots();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0054, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0055, code lost:
        r5 = r8;
        r8 = r7;
        r7 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean updateState(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot[] r0 = r6.getSlots()
            kotlinx.coroutines.flow.StateFlowSlot[] r0 = (kotlinx.coroutines.flow.StateFlowSlot[]) r0
            monitor-enter(r6)
            java.lang.Object r0 = r6._state     // Catch:{ all -> 0x0062 }
            r1 = 0
            r2 = 1
            if (r7 == 0) goto L_0x0016
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r7)     // Catch:{ all -> 0x0062 }
            r7 = r7 ^ r2
            if (r7 == 0) goto L_0x0016
            monitor-exit(r6)
            return r1
        L_0x0016:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r8)     // Catch:{ all -> 0x0062 }
            if (r7 == 0) goto L_0x001e
            monitor-exit(r6)
            return r2
        L_0x001e:
            r6._state = r8     // Catch:{ all -> 0x0062 }
            int r7 = r6.sequence     // Catch:{ all -> 0x0062 }
            r8 = r7 & 1
            if (r8 != 0) goto L_0x005c
            int r7 = r7 + r2
            r6.sequence = r7     // Catch:{ all -> 0x0062 }
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot[] r8 = r6.getSlots()     // Catch:{ all -> 0x0062 }
            kotlinx.coroutines.flow.StateFlowSlot[] r8 = (kotlinx.coroutines.flow.StateFlowSlot[]) r8     // Catch:{ all -> 0x0062 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0062 }
            monitor-exit(r6)
        L_0x0032:
            if (r8 == 0) goto L_0x0042
            int r0 = r8.length
            r3 = r1
        L_0x0036:
            if (r3 >= r0) goto L_0x0042
            r4 = r8[r3]
            if (r4 == 0) goto L_0x003f
            r4.makePending()
        L_0x003f:
            int r3 = r3 + 1
            goto L_0x0036
        L_0x0042:
            monitor-enter(r6)
            int r8 = r6.sequence     // Catch:{ all -> 0x0059 }
            if (r8 != r7) goto L_0x004c
            int r7 = r7 + r2
            r6.sequence = r7     // Catch:{ all -> 0x0059 }
            monitor-exit(r6)
            return r2
        L_0x004c:
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot[] r7 = r6.getSlots()     // Catch:{ all -> 0x0059 }
            kotlinx.coroutines.flow.StateFlowSlot[] r7 = (kotlinx.coroutines.flow.StateFlowSlot[]) r7     // Catch:{ all -> 0x0059 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0059 }
            monitor-exit(r6)
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x0032
        L_0x0059:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x005c:
            int r7 = r7 + 2
            r6.sequence = r7     // Catch:{ all -> 0x0062 }
            monitor-exit(r6)
            return r2
        L_0x0062:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.updateState(java.lang.Object, java.lang.Object):boolean");
    }

    public List<T> getReplayCache() {
        return CollectionsKt.listOf(getValue());
    }

    public boolean tryEmit(T t) {
        setValue(t);
        return true;
    }

    public Object emit(T t, Continuation<? super Unit> continuation) {
        setValue(t);
        return Unit.INSTANCE;
    }

    public void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: kotlinx.coroutines.flow.StateFlowSlot} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: kotlinx.coroutines.flow.FlowCollector<? super T>} */
    /* JADX WARNING: type inference failed for: r13v10, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b2 A[Catch:{ all -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c2 A[Catch:{ all -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c4 A[Catch:{ all -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d9 A[Catch:{ all -> 0x00fb }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00da A[Catch:{ all -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e6 A[Catch:{ all -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof kotlinx.coroutines.flow.StateFlowImpl$collect$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = (kotlinx.coroutines.flow.StateFlowImpl$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = new kotlinx.coroutines.flow.StateFlowImpl$collect$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x007d
            if (r2 == r6) goto L_0x006b
            if (r2 == r5) goto L_0x004f
            if (r2 != r4) goto L_0x0047
            java.lang.Object r11 = r0.L$5
            java.lang.Object r11 = r0.L$4
            java.lang.Object r12 = r0.L$3
            kotlinx.coroutines.Job r12 = (kotlinx.coroutines.Job) r12
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r8 = (kotlinx.coroutines.flow.StateFlowImpl) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0068 }
            r13 = r11
            r11 = r8
            goto L_0x00ae
        L_0x0047:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x004f:
            java.lang.Object r11 = r0.L$5
            java.lang.Object r12 = r0.L$4
            java.lang.Object r12 = r0.L$3
            kotlinx.coroutines.Job r12 = (kotlinx.coroutines.Job) r12
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r8 = (kotlinx.coroutines.flow.StateFlowImpl) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0068 }
            goto L_0x00dd
        L_0x0068:
            r11 = move-exception
            goto L_0x00fe
        L_0x006b:
            java.lang.Object r11 = r0.L$2
            r2 = r11
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r11 = r0.L$1
            r12 = r11
            kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
            java.lang.Object r11 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r11 = (kotlinx.coroutines.flow.StateFlowImpl) r11
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x00fb }
            goto L_0x009d
        L_0x007d:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot r13 = r11.allocateSlot()
            r2 = r13
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            boolean r13 = r12 instanceof kotlinx.coroutines.flow.SubscribedFlowCollector     // Catch:{ all -> 0x00fb }
            if (r13 == 0) goto L_0x009d
            r13 = r12
            kotlinx.coroutines.flow.SubscribedFlowCollector r13 = (kotlinx.coroutines.flow.SubscribedFlowCollector) r13     // Catch:{ all -> 0x00fb }
            r0.L$0 = r11     // Catch:{ all -> 0x00fb }
            r0.L$1 = r12     // Catch:{ all -> 0x00fb }
            r0.L$2 = r2     // Catch:{ all -> 0x00fb }
            r0.label = r6     // Catch:{ all -> 0x00fb }
            java.lang.Object r13 = r13.onSubscription(r0)     // Catch:{ all -> 0x00fb }
            if (r13 != r1) goto L_0x009d
            return r1
        L_0x009d:
            kotlin.coroutines.CoroutineContext r13 = r0.getContext()     // Catch:{ all -> 0x00fb }
            kotlinx.coroutines.Job$Key r7 = kotlinx.coroutines.Job.Key     // Catch:{ all -> 0x00fb }
            kotlin.coroutines.CoroutineContext$Key r7 = (kotlin.coroutines.CoroutineContext.Key) r7     // Catch:{ all -> 0x00fb }
            kotlin.coroutines.CoroutineContext$Element r13 = r13.get(r7)     // Catch:{ all -> 0x00fb }
            kotlinx.coroutines.Job r13 = (kotlinx.coroutines.Job) r13     // Catch:{ all -> 0x00fb }
            r7 = r12
            r12 = r13
            r13 = r3
        L_0x00ae:
            java.lang.Object r8 = r11._state     // Catch:{ all -> 0x00fb }
            if (r12 == 0) goto L_0x00b5
            kotlinx.coroutines.JobKt.ensureActive((kotlinx.coroutines.Job) r12)     // Catch:{ all -> 0x00fb }
        L_0x00b5:
            if (r13 == 0) goto L_0x00be
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r8)     // Catch:{ all -> 0x00fb }
            r9 = r9 ^ r6
            if (r9 == 0) goto L_0x00e0
        L_0x00be:
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch:{ all -> 0x00fb }
            if (r8 != r9) goto L_0x00c4
            r9 = r3
            goto L_0x00c5
        L_0x00c4:
            r9 = r8
        L_0x00c5:
            r0.L$0 = r11     // Catch:{ all -> 0x00fb }
            r0.L$1 = r7     // Catch:{ all -> 0x00fb }
            r0.L$2 = r2     // Catch:{ all -> 0x00fb }
            r0.L$3 = r12     // Catch:{ all -> 0x00fb }
            r0.L$4 = r13     // Catch:{ all -> 0x00fb }
            r0.L$5 = r8     // Catch:{ all -> 0x00fb }
            r0.label = r5     // Catch:{ all -> 0x00fb }
            java.lang.Object r13 = r7.emit(r9, r0)     // Catch:{ all -> 0x00fb }
            if (r13 != r1) goto L_0x00da
            return r1
        L_0x00da:
            r10 = r8
            r8 = r11
            r11 = r10
        L_0x00dd:
            r13 = r11
            r11 = r8
            r8 = r13
        L_0x00e0:
            boolean r9 = r2.takePending()     // Catch:{ all -> 0x00fb }
            if (r9 != 0) goto L_0x00ae
            r0.L$0 = r11     // Catch:{ all -> 0x00fb }
            r0.L$1 = r7     // Catch:{ all -> 0x00fb }
            r0.L$2 = r2     // Catch:{ all -> 0x00fb }
            r0.L$3 = r12     // Catch:{ all -> 0x00fb }
            r0.L$4 = r13     // Catch:{ all -> 0x00fb }
            r0.L$5 = r8     // Catch:{ all -> 0x00fb }
            r0.label = r4     // Catch:{ all -> 0x00fb }
            java.lang.Object r8 = r2.awaitPending(r0)     // Catch:{ all -> 0x00fb }
            if (r8 != r1) goto L_0x00ae
            return r1
        L_0x00fb:
            r12 = move-exception
            r8 = r11
            r11 = r12
        L_0x00fe:
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot r2 = (kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot) r2
            r8.freeSlot(r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public StateFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    /* access modifiers changed from: protected */
    public StateFlowSlot[] createSlotArray(int i) {
        return new StateFlowSlot[i];
    }

    public Flow<T> fuse(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        return StateFlowKt.fuseStateFlow(this, coroutineContext, i, bufferOverflow);
    }
}
