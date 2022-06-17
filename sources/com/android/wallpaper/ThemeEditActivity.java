package com.android.wallpaper;

import android.os.Bundle;
import com.motorola.styles.model.Theme;

public class ThemeEditActivity extends ThemesBaseActivity {
    private Theme mTheme;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null || !extras.containsKey("theme")) {
                finish();
                return;
            }
            this.mTheme = (Theme) extras.get("theme");
            ThemeEditFragment newInstance = ThemeEditFragment.newInstance();
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("theme", this.mTheme);
            newInstance.setArguments(bundle2);
            getSupportFragmentManager().beginTransaction().replace(C0744R.C0747id.container, newInstance).commitNow();
        }
    }

    public int getContentViewId() {
        return C0744R.layout.themes_edit;
    }
}
