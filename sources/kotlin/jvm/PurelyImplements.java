package kotlin.jvm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@MustBeDocumented
@Target(allowedTargets = {AnnotationTarget.CLASS})
@Retention(AnnotationRetention.RUNTIME)
@Documented
@java.lang.annotation.Target({ElementType.TYPE})
@Metadata(mo15461d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, mo15462d2 = {"Lkotlin/jvm/PurelyImplements;", "", "value", "", "()Ljava/lang/String;", "kotlin-stdlib"}, mo15463k = 1, mo15464mv = {1, 5, 1})
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
/* compiled from: PurelyImplements.kt */
public @interface PurelyImplements {
    String value();
}
