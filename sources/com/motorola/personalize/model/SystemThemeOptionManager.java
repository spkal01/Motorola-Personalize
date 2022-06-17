package com.motorola.personalize.model;

import android.content.Context;
import com.motorola.personalize.C1057R;
import java.util.ArrayList;
import java.util.List;

public class SystemThemeOptionManager {
    protected Context mContext;
    protected final List<SystemThemeOption> mOptions = new ArrayList();

    public SystemThemeOptionManager(Context context) {
        this.mContext = context;
    }

    public List<SystemThemeOption> getOptions() {
        return this.mOptions;
    }

    public void loadOptions(boolean z) {
        if (z) {
            this.mOptions.clear();
        }
        this.mOptions.add(new SystemThemeOption("light", C1057R.C1059drawable.ic_theme_light, C1057R.string.feature_system_theme_light));
        this.mOptions.add(new SystemThemeOption("dark", C1057R.C1059drawable.ic_theme_dark, C1057R.string.feature_system_theme_dark));
        this.mOptions.add(new SystemThemeOption("transition", C1057R.C1059drawable.ic_theme_transitions, C1057R.string.feature_system_theme_transition));
    }

    public SystemThemeOption getSystemAppliedTheme(int i) {
        if (i == 1) {
            return this.mOptions.get(0);
        }
        if (i != 2) {
            return this.mOptions.get(2);
        }
        return this.mOptions.get(1);
    }
}
