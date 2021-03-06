package androidx.core.graphics;

import android.graphics.Path;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15460bv = {1, 0, 3}, mo15461d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f¨\u0006\f"}, mo15462d2 = {"and", "Landroid/graphics/Path;", "p", "flatten", "", "Landroidx/core/graphics/PathSegment;", "error", "", "minus", "or", "plus", "xor", "core-ktx_release"}, mo15463k = 2, mo15464mv = {1, 4, 2})
/* compiled from: Path.kt */
public final class PathKt {
    public static /* synthetic */ Iterable flatten$default(Path path, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.5f;
        }
        return flatten(path, f);
    }

    public static final Iterable<PathSegment> flatten(Path path, float f) {
        Intrinsics.checkNotNullParameter(path, "$this$flatten");
        Collection<PathSegment> flatten = PathUtils.flatten(path, f);
        Intrinsics.checkNotNullExpressionValue(flatten, "PathUtils.flatten(this, error)");
        return flatten;
    }

    public static final Path plus(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$plus");
        Intrinsics.checkNotNullParameter(path2, "p");
        Path path3 = new Path(path);
        path3.op(path2, Path.Op.UNION);
        return path3;
    }

    public static final Path minus(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$minus");
        Intrinsics.checkNotNullParameter(path2, "p");
        Path path3 = new Path(path);
        path3.op(path2, Path.Op.DIFFERENCE);
        return path3;
    }

    public static final Path and(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$and");
        Intrinsics.checkNotNullParameter(path2, "p");
        Path path3 = new Path();
        path3.op(path, path2, Path.Op.INTERSECT);
        return path3;
    }

    public static final Path xor(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$xor");
        Intrinsics.checkNotNullParameter(path2, "p");
        Path path3 = new Path(path);
        path3.op(path2, Path.Op.XOR);
        return path3;
    }

    /* renamed from: or */
    public static final Path m6or(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "$this$or");
        Intrinsics.checkNotNullParameter(path2, "p");
        Path path3 = new Path(path);
        path3.op(path2, Path.Op.UNION);
        return path3;
    }
}
