package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;

@Metadata(mo15461d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo15462d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"}, mo15463k = 3, mo15464mv = {1, 5, 1})
/* compiled from: Comparisons.kt */
final class ComparisonsKt__ComparisonsKt$thenDescending$1<T> implements Comparator<T> {
    final /* synthetic */ Comparator $comparator;
    final /* synthetic */ Comparator $this_thenDescending;

    ComparisonsKt__ComparisonsKt$thenDescending$1(Comparator comparator, Comparator comparator2) {
        this.$this_thenDescending = comparator;
        this.$comparator = comparator2;
    }

    public final int compare(T t, T t2) {
        int compare = this.$this_thenDescending.compare(t, t2);
        return compare != 0 ? compare : this.$comparator.compare(t2, t);
    }
}
