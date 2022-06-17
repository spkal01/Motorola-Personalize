package com.motorola.personalize.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.personalize.data.AppDatabaseHelper;
import com.motorola.personalize.model.OptionEntity;
import com.motorola.personalize.view.PaletteLayout;
import com.motorola.personalize.view.PaletteView;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class PaletteLayout extends RelativeLayout implements PaletteView.OnColorChangedListener {
    private final ImageView colorPreviewIv;
    private final TextView colorValueTv;
    private int mCurrentColor;
    private final Set<CustomColorListener> mListeners;
    private final Handler mUiHandler;

    public interface CustomColorListener {
        void onCustomColorChanged(int i);

        void onPaletteAdd(int i);

        void onPaletteCancel();
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public int getCurrentColor() {
        return this.mCurrentColor;
    }

    public void onColorChanged(int i) {
        this.mCurrentColor = i;
        this.colorPreviewIv.setBackgroundColor(i);
        this.mUiHandler.post(new Runnable() {
            public final void run() {
                PaletteLayout.this.lambda$onColorChanged$1$PaletteLayout();
            }
        });
    }

    public /* synthetic */ void lambda$onColorChanged$0$PaletteLayout(CustomColorListener customColorListener) {
        customColorListener.onCustomColorChanged(this.mCurrentColor);
    }

    public /* synthetic */ void lambda$onColorChanged$1$PaletteLayout() {
        this.mListeners.forEach(new Consumer() {
            public final void accept(Object obj) {
                PaletteLayout.this.lambda$onColorChanged$0$PaletteLayout((PaletteLayout.CustomColorListener) obj);
            }
        });
    }

    public void onHueChanged(float f) {
        this.colorValueTv.setText("Hue:" + Math.round(f));
    }

    public void registerColorListener(CustomColorListener customColorListener) {
        this.mListeners.add(customColorListener);
    }

    public void unregisterColorListener() {
        this.mListeners.clear();
    }

    private void notifyColorUpdate() {
        PersonalizeMetrics.useColorPicker(getContext());
        if (AppDatabaseHelper.getInstance().getDao().insertOption(new OptionEntity().init(this.mCurrentColor)) != -1) {
            this.mUiHandler.post(new Runnable() {
                public final void run() {
                    PaletteLayout.this.lambda$notifyColorUpdate$3$PaletteLayout();
                }
            });
        } else {
            Toast.makeText(getContext(), C1057R.string.picked_color_exist, 0).show();
        }
        this.mUiHandler.post(new Runnable() {
            public final void run() {
                PaletteLayout.this.lambda$notifyColorUpdate$4$PaletteLayout();
            }
        });
    }

    public /* synthetic */ void lambda$notifyColorUpdate$2$PaletteLayout(CustomColorListener customColorListener) {
        customColorListener.onPaletteAdd(this.mCurrentColor);
    }

    public /* synthetic */ void lambda$notifyColorUpdate$3$PaletteLayout() {
        this.mListeners.forEach(new Consumer() {
            public final void accept(Object obj) {
                PaletteLayout.this.lambda$notifyColorUpdate$2$PaletteLayout((PaletteLayout.CustomColorListener) obj);
            }
        });
    }

    public /* synthetic */ void lambda$notifyColorUpdate$4$PaletteLayout() {
        this.mListeners.forEach($$Lambda$F7m9buRmJIAZ_1PGFpi21uKQiSY.INSTANCE);
    }

    public PaletteLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public PaletteLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PaletteLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PaletteLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mListeners = new HashSet();
        LayoutInflater.from(context).inflate(C1057R.layout.palette_layout, this);
        this.mUiHandler = new Handler(Looper.getMainLooper());
        this.colorPreviewIv = (ImageView) findViewById(C1057R.C1060id.palette_result_bg);
        this.colorValueTv = (TextView) findViewById(C1057R.C1060id.palette_result_text);
        ((PaletteView) findViewById(C1057R.C1060id.palette_view)).setOnColorChangedListener(this);
        ((Button) findViewById(C1057R.C1060id.add_color)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PaletteLayout.this.lambda$new$5$PaletteLayout(view);
            }
        });
        ((TextView) findViewById(C1057R.C1060id.cancel_button)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PaletteLayout.this.lambda$new$7$PaletteLayout(view);
            }
        });
    }

    public /* synthetic */ void lambda$new$5$PaletteLayout(View view) {
        notifyColorUpdate();
    }

    public /* synthetic */ void lambda$new$6$PaletteLayout() {
        this.mListeners.forEach($$Lambda$F7m9buRmJIAZ_1PGFpi21uKQiSY.INSTANCE);
    }

    public /* synthetic */ void lambda$new$7$PaletteLayout(View view) {
        this.mUiHandler.post(new Runnable() {
            public final void run() {
                PaletteLayout.this.lambda$new$6$PaletteLayout();
            }
        });
    }
}
