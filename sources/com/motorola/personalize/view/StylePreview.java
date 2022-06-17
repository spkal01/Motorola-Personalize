package com.motorola.personalize.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.dynamicolor.MaterialColorsGenerator;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import com.motorola.personalize.model.ColorOption;
import com.motorola.personalize.model.StyleSettings;
import com.motorola.personalize.util.NoneSdkApi;

public class StylePreview {
    private MaterialColorsGenerator colorsGenerator;
    private boolean isDarkTheme = false;
    private View mPreviewView;
    private Resources res;

    public StylePreview(View view) {
        this.mPreviewView = view;
    }

    public StylePreview(Context context, View view, int i) {
        this.res = context.getResources();
        this.isDarkTheme = ContextExtensionsKt.isDarkModeEnabled(context);
        this.mPreviewView = view;
        this.colorsGenerator = new MaterialColorsGenerator(context);
        refreshColor(i);
    }

    public void bindPreview(ViewGroup viewGroup) {
        viewGroup.removeAllViews();
        if (this.mPreviewView != null) {
            viewGroup.addView(this.mPreviewView, 0, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    public void refreshColorPreview(Context context, StyleSettings styleSettings) {
        refreshColor(ColorOption.resolveColor(this.res, styleSettings.getStyle().getColorOption()));
    }

    public void refreshColor(int i) {
        int i2;
        SparseIntArray extractedColors = this.colorsGenerator.extractedColors(i);
        View findViewById = this.mPreviewView.findViewById(C1057R.C1060id.seekbar_color_bg);
        if (this.isDarkTheme) {
            findViewById.setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170472)));
        } else {
            findViewById.setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170462)));
        }
        this.mPreviewView.findViewById(C1057R.C1060id.seekbar_progress_bg).setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170471)));
        this.mPreviewView.findViewById(C1057R.C1060id.seekbar_progress).setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170490)));
        this.mPreviewView.findViewById(C1057R.C1060id.panel_0).setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170490)));
        this.mPreviewView.findViewById(C1057R.C1060id.panel_1).setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170490)));
        View findViewById2 = this.mPreviewView.findViewById(C1057R.C1060id.toggle_bg);
        if (this.isDarkTheme) {
            findViewById2.setBackgroundTintList(NoneSdkApi.getColorStateListWithLStar(extractedColors.get(17170507), 51.0f));
        } else {
            findViewById2.setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170495)));
        }
        this.mPreviewView.findViewById(C1057R.C1060id.toggle_btn).setBackgroundTintList(ColorStateList.valueOf(extractedColors.get(17170490)));
        TextView textView = (TextView) this.mPreviewView.findViewById(C1057R.C1060id.btn_ok);
        ImageView imageView = (ImageView) this.mPreviewView.findViewById(C1057R.C1060id.color_bg3);
        Drawable newDrawable = this.res.getDrawable(C1057R.C1059drawable.color_preview_btn_bg).getConstantState().newDrawable();
        if (this.isDarkTheme) {
            i2 = extractedColors.get(17170490);
        } else {
            i2 = extractedColors.get(17170495);
        }
        newDrawable.setTint(i2);
        textView.setTextColor(i2);
        imageView.setBackground(newDrawable);
        CardView cardView = (CardView) this.mPreviewView.findViewById(C1057R.C1060id.dialog_bg);
        if (this.isDarkTheme) {
            cardView.setCardBackgroundColor(extractedColors.get(17170471));
        } else {
            cardView.setCardBackgroundColor(extractedColors.get(17170462));
        }
    }
}
