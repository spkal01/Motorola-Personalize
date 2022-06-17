package com.motorola.personalize.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.p003os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.app.StyleFragment;
import com.motorola.personalize.app.ThemeFragment;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.OrientationExtensionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u000b"}, mo15462d2 = {"Lcom/motorola/personalize/app/StylesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "loadContent", "", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: StylesActivity.kt */
public final class StylesActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1057R.layout.activity_family);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onCreate");
        }
        Window window = getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "window");
        OrientationExtensionsKt.updateWindowFitSystem(window);
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        LifecycleKt.getCoroutineScope(lifecycle).launchWhenCreated(new StylesActivity$onCreate$2(this, (Continuation<? super StylesActivity$onCreate$2>) null));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "onNewIntent");
        }
        if (intent != null) {
            loadContent(intent);
        }
    }

    /* access modifiers changed from: private */
    public final void loadContent(Intent intent) {
        Fragment fragment;
        String stringExtra;
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "loadContent:");
        }
        String str = "";
        if (!(intent == null || (stringExtra = intent.getStringExtra("feature_id")) == null)) {
            str = stringExtra;
        }
        Bundle bundle = null;
        if (Intrinsics.areEqual((Object) str, (Object) "personalize_system_theme")) {
            ThemeFragment.Companion companion = ThemeFragment.Companion;
            if (intent != null) {
                bundle = intent.getExtras();
            }
            if (bundle == null) {
                bundle = BundleKt.bundleOf(new Pair[0]);
            }
            Intrinsics.checkNotNullExpressionValue(bundle, "intent?.extras ?: bundleOf()");
            fragment = companion.newInstance$app_release(bundle);
        } else {
            StyleFragment.Companion companion2 = StyleFragment.Companion;
            if (intent != null) {
                bundle = intent.getExtras();
            }
            if (bundle == null) {
                bundle = BundleKt.bundleOf(new Pair[0]);
            }
            Intrinsics.checkNotNullExpressionValue(bundle, "intent?.extras ?: bundleOf()");
            fragment = companion2.newInstance$app_release(bundle);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction()");
        if (fragment != null) {
            beginTransaction.replace(C1057R.C1060id.family_fragment, fragment);
        }
        beginTransaction.commitNow();
    }
}
