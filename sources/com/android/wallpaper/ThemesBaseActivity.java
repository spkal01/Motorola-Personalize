package com.android.wallpaper;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;

public abstract class ThemesBaseActivity extends FragmentActivity {
    public abstract int getContentViewId();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentViewId());
        getWindow().addFlags(512);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 512 | 256);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
