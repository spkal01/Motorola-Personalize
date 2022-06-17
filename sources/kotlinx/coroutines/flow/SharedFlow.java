package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo15462d2 = {"Lkotlinx/coroutines/flow/SharedFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "kotlinx-coroutines-core"}, mo15463k = 1, mo15464mv = {1, 4, 0})
/* compiled from: SharedFlow.kt */
public interface SharedFlow<T> extends Flow<T> {
    List<T> getReplayCache();
}
