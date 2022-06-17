package com.android.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SettingsMenuActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, ThemesMainActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }
}
