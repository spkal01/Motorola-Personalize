package com.motorola.personalize.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.view.GridPreviewView;
import com.motorola.personalize.view.ShapeIconsView;
import com.motorola.personalize.view.StylePreview;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.ShapeOption;

public class StylesViewTool {
    public static StylePreview getGridPreview(Context context, Style style) {
        GridPreviewView gridPreviewView;
        String grid = style.getGrid();
        if (grid.equals(GridOption.GRID_3_4_VALUE)) {
            gridPreviewView = (GridPreviewView) LayoutInflater.from(context).inflate(C1057R.layout.preview_grid_c3, (ViewGroup) null);
            gridPreviewView.setGridValue(3, 4);
        } else if (grid.equals(GridOption.GRID_4_4_VALUE)) {
            gridPreviewView = (GridPreviewView) LayoutInflater.from(context).inflate(C1057R.layout.preview_grid_c4, (ViewGroup) null);
            gridPreviewView.setGridValue(4, 4);
        } else if (grid.equals(GridOption.GRID_4_5_VALUE)) {
            gridPreviewView = (GridPreviewView) LayoutInflater.from(context).inflate(C1057R.layout.preview_grid_c4, (ViewGroup) null);
            gridPreviewView.setGridValue(4, 5);
        } else if (grid.equals(GridOption.GRID_4_6_VALUE)) {
            gridPreviewView = (GridPreviewView) LayoutInflater.from(context).inflate(C1057R.layout.preview_grid_c4, (ViewGroup) null);
            gridPreviewView.setGridValue(4, 6);
        } else if (grid.equals(GridOption.GRID_5_5_VALUE)) {
            gridPreviewView = (GridPreviewView) LayoutInflater.from(context).inflate(C1057R.layout.preview_grid_c5, (ViewGroup) null);
            gridPreviewView.setGridValue(5, 5);
        } else {
            gridPreviewView = (GridPreviewView) LayoutInflater.from(context).inflate(C1057R.layout.preview_grid_c5, (ViewGroup) null);
            gridPreviewView.setGridValue(5, 6);
        }
        ShapeDrawable shapeDrawable = (ShapeDrawable) style.getShapeOption().getShape().getDrawable(1);
        shapeDrawable.setTint(-1);
        setGridIcons(context, (ViewGroup) gridPreviewView.findViewById(C1057R.C1060id.preview_icons_ws), shapeDrawable, true);
        setGridIcons(context, (ViewGroup) gridPreviewView.findViewById(C1057R.C1060id.preview_icons_hs), shapeDrawable, false);
        return new StylePreview(gridPreviewView);
    }

    public static void setGridIcons(Context context, ViewGroup viewGroup, Drawable drawable, boolean z) {
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(z ? C1057R.array.workspace_icons : C1057R.array.hotseat_icons);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ((ImageView) childAt.findViewById(C1057R.C1060id.preview_setting_icon_bg)).setImageDrawable(drawable);
            ((ImageView) childAt.findViewById(C1057R.C1060id.preview_setting_icon)).setImageResource(obtainTypedArray.getResourceId(i, 0));
        }
        obtainTypedArray.recycle();
    }

    public static StylePreview getFontPreview(Context context, Style style) {
        return new StylePreview(getFontPreviewView(context, style.getFontOption()));
    }

    public static View getFontPreviewView(Context context, FontOption fontOption) {
        View inflate = LayoutInflater.from(context).inflate(C1057R.layout.preview_font, (ViewGroup) null);
        ((TextView) inflate.findViewById(C1057R.C1060id.tv_headline)).setTypeface(fontOption.getHeadlineFont());
        ((TextView) inflate.findViewById(C1057R.C1060id.tv_body)).setTypeface(fontOption.getBodyFont());
        return inflate;
    }

    public static StylePreview getColorPreview(Context context, Style style) {
        return new StylePreview(context, LayoutInflater.from(context).inflate(C1057R.layout.preview_color, (ViewGroup) null), ColorOption.resolveColor(context.getResources(), style.getColorOption()));
    }

    public static StylePreview getShapePreview(Context context, Style style) {
        return new StylePreview(getShapePreviewView(context, style.getShapeOption()));
    }

    public static View getShapePreviewView(Context context, ShapeOption shapeOption) {
        View inflate = LayoutInflater.from(context).inflate(C1057R.layout.preview_shape, (ViewGroup) null);
        ShapeIconsView shapeIconsView = (ShapeIconsView) inflate.findViewById(C1057R.C1060id.shape_app_icons1);
        shapeIconsView.setShapeOption(shapeOption);
        shapeIconsView.showAppIcons();
        return inflate;
    }

    public static StylePreview getStylePreview(Context context, StyleSettings styleSettings) {
        String currentSetting = styleSettings.getCurrentSetting();
        if (StyleSettings.isFont(currentSetting)) {
            return getFontPreview(context, styleSettings.getStyle());
        }
        if (StyleSettings.isColor(currentSetting)) {
            return getColorPreview(context, styleSettings.getStyle());
        }
        if (StyleSettings.isShape(currentSetting)) {
            return getShapePreview(context, styleSettings.getStyle());
        }
        if (StyleSettings.isGrid(currentSetting)) {
            return getGridPreview(context, styleSettings.getStyle());
        }
        return new StylePreview((View) null);
    }
}
