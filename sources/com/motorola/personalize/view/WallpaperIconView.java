package com.motorola.personalize.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOverlay;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.monet.ColorScheme;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.util.NoneSdkApi;

public class WallpaperIconView extends ConstraintLayout {
    private final View endBottomView;
    private final View endTopView;
    private final View startBottomView;
    private final View startTopView;

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public WallpaperIconView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WallpaperIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WallpaperIconView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public WallpaperIconView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(context).inflate(C1057R.layout.wallpaper_icon_view, this);
        this.startTopView = findViewById(C1057R.C1060id.start_top);
        this.startBottomView = findViewById(C1057R.C1060id.start_bottom);
        this.endTopView = findViewById(C1057R.C1060id.end_top);
        this.endBottomView = findViewById(C1057R.C1060id.end_bottom);
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.startTopView.setBackgroundColor(colorScheme.getAccent1().get(2).intValue());
        this.startBottomView.setBackgroundColor(colorScheme.getAccent1().get(2).intValue());
        this.endTopView.setBackgroundColor(NoneSdkApi.getColorWithLStar(colorScheme.getAccent2().get(6).intValue(), 95.0f));
        this.endBottomView.setBackgroundColor(NoneSdkApi.getColorWithLStar(colorScheme.getAccent3().get(6).intValue(), 85.0f));
    }
}
