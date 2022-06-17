package com.android.wallpaper.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.wallpaper.C0744R;
import com.android.wallpaper.ThemeExt;
import com.motorola.styles.model.ColorOption;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.RingtoneOption;
import com.motorola.styles.model.ShapeOption;
import com.motorola.styles.model.Theme;
import com.motorola.styles.model.WallpaperOption;
import com.motorola.styles.viewmodel.ThemesViewModel;

public class ViewUtils {
    private static final String TAG = "ViewUtils";

    public static void bindDetailPreview(View view, Theme theme) {
        Context context = view.getContext();
        FontOption fontOption = theme.getFontOption();
        ColorOption colorOption = theme.getColorOption();
        ShapeOption shapeOption = theme.getShapeOption();
        GridOption gridOption = theme.getGridOption();
        WallpaperOption wallpaperOption = theme.getWallpaperOption();
        RingtoneOption ringtoneOption = theme.getRingtoneOption();
        Log.d(TAG, "bindDetailPreview - ringtoneOption: " + ringtoneOption);
        int colorOfAccent1 = colorOption.getColorOfAccent1(2);
        Drawable newDrawable = shapeOption.getShape().getConstantState().newDrawable();
        newDrawable.setTint(colorOfAccent1);
        String name = fontOption.getName();
        Typeface headlineFont = theme.getFontOption().getHeadlineFont();
        setWallpaperIcon(context, wallpaperOption, (ImageView) view.findViewById(C0744R.C0747id.wallpaper_icon));
        int colorOfNeutral1 = colorOption.getColorOfNeutral1(10);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(C0744R.C0747id.grid_icons);
        for (int i = 0; i < gridOption.getCol(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ((ImageView) childAt.findViewById(C0744R.C0747id.f85bg)).setImageDrawable(newDrawable);
            Drawable drawable = context.getDrawable(ThemeExt.GRID_ICONS.get(i).intValue());
            ImageView imageView = (ImageView) childAt.findViewById(C0744R.C0747id.icon);
            imageView.setImageDrawable(drawable);
            imageView.setImageTintList(ColorStateList.valueOf(colorOfNeutral1));
            childAt.setVisibility(0);
        }
        for (int col = gridOption.getCol(); col < viewGroup.getChildCount(); col++) {
            viewGroup.getChildAt(col).setVisibility(8);
        }
        TextView textView = (TextView) view.findViewById(C0744R.C0747id.font_icon);
        int i2 = colorOfAccent1;
        Typeface typeface = headlineFont;
        int i3 = colorOfNeutral1;
        bindTextIcon(textView, name, i2, (Drawable) null, typeface, i3);
        textView.post(new Runnable(textView) {
            public final /* synthetic */ TextView f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.setSelected(true);
            }
        });
        bindImageIcon(view.findViewById(C0744R.C0747id.shape_icon_wrapper1), newDrawable, context.getDrawable(ThemeExt.SHAPE_ICONS.get(0).intValue()), colorOfNeutral1);
        bindImageIcon(view.findViewById(C0744R.C0747id.shape_icon_wrapper2), newDrawable, context.getDrawable(ThemeExt.SHAPE_ICONS.get(1).intValue()), colorOfNeutral1);
        TextView textView2 = (TextView) view.findViewById(C0744R.C0747id.ringtone_icon);
        bindTextIcon(textView2, ThemeExt.getRingtoneName(context, ringtoneOption), i2, context.getDrawable(ThemeExt.getRingtoneIcon(ringtoneOption)), typeface, i3);
        textView2.post(new Runnable(textView2) {
            public final /* synthetic */ TextView f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.setSelected(true);
            }
        });
    }

    public static void bindImageIcon(View view, Drawable drawable, Drawable drawable2, int i) {
        ((ImageView) view.findViewById(C0744R.C0747id.background)).setImageDrawable(drawable);
        ImageView imageView = (ImageView) view.findViewById(C0744R.C0747id.icon);
        imageView.setImageDrawable(drawable2);
        imageView.setImageTintList(ColorStateList.valueOf(i));
        imageView.setVisibility(0);
    }

    public static void bindWrappedTextIcon(View view, Drawable drawable, String str, Typeface typeface, int i) {
        ((ImageView) view.findViewById(C0744R.C0747id.background)).setImageDrawable(drawable);
        TextView textView = (TextView) view.findViewById(C0744R.C0747id.text);
        textView.setTypeface(typeface);
        textView.setText(str);
        textView.setTextColor(i);
        textView.setVisibility(0);
    }

    public static void bindImageIcon(ImageView imageView, Drawable drawable, int i) {
        imageView.setBackgroundTintList(ColorStateList.valueOf(i));
        imageView.setImageDrawable(drawable);
        imageView.setVisibility(0);
    }

    public static void bindTextIcon(TextView textView, String str, int i, Drawable drawable, Typeface typeface, int i2) {
        textView.setBackgroundTintList(ColorStateList.valueOf(i));
        if (drawable != null) {
            int dimensionPixelSize = textView.getResources().getDimensionPixelSize(C0744R.dimen.view_24dp);
            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            textView.setCompoundDrawablesRelative(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            textView.setCompoundDrawableTintList(ColorStateList.valueOf(i2));
        }
        textView.setTypeface(typeface);
        textView.setText(str);
        textView.setTextColor(i2);
        textView.setVisibility(0);
    }

    public static void setWallpaperIcon(Context context, WallpaperOption wallpaperOption, ImageView imageView) {
        ThemesViewModel.runOnWorkerThread(new Runnable(context, imageView) {
            public final /* synthetic */ Context f$1;
            public final /* synthetic */ ImageView f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                this.f$2.post(new Runnable(this.f$2, WallpaperOption.this.getWallpaperThumb(this.f$1)) {
                    public final /* synthetic */ ImageView f$0;
                    public final /* synthetic */ Bitmap f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        this.f$0.setImageBitmap(this.f$1);
                    }
                });
            }
        });
    }
}
