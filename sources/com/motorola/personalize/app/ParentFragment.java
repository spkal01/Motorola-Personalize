package com.motorola.personalize.app;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.WindowExtKt;
import com.motorola.personalize.util.IntentChecker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H&J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010 \u001a\u00020\u0017H\u0016J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0004H&R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, mo15462d2 = {"Lcom/motorola/personalize/app/ParentFragment;", "Landroidx/fragment/app/Fragment;", "()V", "color", "", "getColor", "()Ljava/lang/Integer;", "setColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "featureId", "", "getFeatureId", "()Ljava/lang/String;", "setFeatureId", "(Ljava/lang/String;)V", "intentChecker", "Lcom/motorola/personalize/util/IntentChecker;", "getIntentChecker", "()Lcom/motorola/personalize/util/IntentChecker;", "setIntentChecker", "(Lcom/motorola/personalize/util/IntentChecker;)V", "adjustInsets", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onViewCreated", "view", "Landroid/view/View;", "setStatusBarColor", "setupFragment", "setupToolbar", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ParentFragment.kt */
public abstract class ParentFragment extends Fragment {
    private Integer color;
    private String featureId;
    public IntentChecker intentChecker;

    public abstract void adjustInsets();

    public abstract void setupToolbar(int i);

    public final Integer getColor() {
        return this.color;
    }

    public final void setColor(Integer num) {
        this.color = num;
    }

    public final String getFeatureId() {
        return this.featureId;
    }

    public final void setFeatureId(String str) {
        this.featureId = str;
    }

    public final IntentChecker getIntentChecker() {
        IntentChecker intentChecker2 = this.intentChecker;
        if (intentChecker2 != null) {
            return intentChecker2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("intentChecker");
        throw null;
    }

    public final void setIntentChecker(IntentChecker intentChecker2) {
        Intrinsics.checkNotNullParameter(intentChecker2, "<set-?>");
        this.intentChecker = intentChecker2;
    }

    public void onCreate(Bundle bundle) {
        String string;
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        String str = "";
        if (!(arguments == null || (string = arguments.getString("feature_id")) == null)) {
            str = string;
        }
        this.featureId = str;
        Bundle arguments2 = getArguments();
        this.color = arguments2 == null ? null : Integer.valueOf(arguments2.getInt("color"));
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("onCreate - familyKey = ", getFeatureId()));
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        setIntentChecker(new IntentChecker(requireContext));
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setupFragment();
        adjustInsets();
    }

    public void setupFragment() {
        Context context = getContext();
        boolean z = false;
        if (context != null && ContextExtensionsKt.isDarkModeEnabled(context)) {
            z = true;
        }
        Resources.Theme theme = null;
        if (z) {
            Resources resources = getResources();
            Context context2 = getContext();
            if (context2 != null) {
                theme = context2.getTheme();
            }
            setupToolbar(resources.getColor(C1057R.C1058color.n_1_800, theme));
            return;
        }
        Resources resources2 = getResources();
        Context context3 = getContext();
        if (context3 != null) {
            theme = context3.getTheme();
        }
        setupToolbar(resources2.getColor(C1057R.C1058color.a_1_100, theme));
    }

    private final void setStatusBarColor(int i) {
        Window window;
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, Intrinsics.stringPlus("setStatusBarColor ", Integer.valueOf(i)));
        }
        FragmentActivity activity = getActivity();
        if (activity != null && (window = activity.getWindow()) != null) {
            WindowExtKt.updateStatusColor(window, i);
        }
    }
}
