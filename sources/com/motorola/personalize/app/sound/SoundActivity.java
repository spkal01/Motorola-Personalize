package com.motorola.personalize.app.sound;

import android.content.res.Configuration;
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
import com.motorola.personalize.app.sound.SoundFragment;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.WindowExtKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo15461d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014¨\u0006\u000b"}, mo15462d2 = {"Lcom/motorola/personalize/app/sound/SoundActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "loadContent", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: SoundActivity.kt */
public final class SoundActivity extends AppCompatActivity {
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
        WindowExtKt.customFlags(window);
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        LifecycleKt.getCoroutineScope(lifecycle).launchWhenCreated(new SoundActivity$onCreate$2(this, (Continuation<? super SoundActivity$onCreate$2>) null));
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: private */
    public final void loadContent() {
        String tag = Logger.INSTANCE.getTag();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(tag, "loadContent:");
        }
        SoundFragment.Companion companion = SoundFragment.Companion;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = BundleKt.bundleOf(new Pair[0]);
        }
        Intrinsics.checkNotNullExpressionValue(extras, "intent.extras ?: bundleOf()");
        Fragment newInstance$app_release = companion.newInstance$app_release(extras);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction()");
        beginTransaction.replace(C1057R.C1060id.family_fragment, newInstance$app_release);
        beginTransaction.commitNow();
    }
}
