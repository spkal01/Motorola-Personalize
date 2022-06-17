package com.android.wallpaper;

import android.os.Bundle;

public class ThemesMainActivity extends ThemesBaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(C0744R.C0747id.container, ThemesMainFragment.newInstance()).commitNow();
        }
    }

    public int getContentViewId() {
        return C0744R.layout.themes_main;
    }
}
