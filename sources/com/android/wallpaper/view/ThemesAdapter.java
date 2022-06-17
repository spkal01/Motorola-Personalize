package com.android.wallpaper.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.wallpaper.C0744R;
import com.android.wallpaper.ThemeExt;
import com.google.android.material.card.MaterialCardView;
import com.motorola.styles.LogConfig;
import com.motorola.styles.model.ColorOption;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.RingtoneOption;
import com.motorola.styles.model.ShapeOption;
import com.motorola.styles.model.Theme;
import com.motorola.styles.model.ThemesInfo;
import com.motorola.styles.model.WallpaperOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ThemesAdapter extends RecyclerView.Adapter {
    public static final int DETAIL_CARD = 2;
    public static final int NORMAL_CARD = 1;
    private Context mContext;
    /* access modifiers changed from: private */
    public final OnThemeClickCallback mOnThemeClickCallback;
    private final AtomicReference<ThemesInfo> mThemesInfo = new AtomicReference<>(new ThemesInfo());

    public interface OnThemeClickCallback {
        boolean onThemeClick(int i, Theme theme);
    }

    public ThemesAdapter(Context context, OnThemeClickCallback onThemeClickCallback) {
        this.mContext = context.getApplicationContext();
        this.mOnThemeClickCallback = onThemeClickCallback;
    }

    /* access modifiers changed from: private */
    public int indexOfTheme(String str) {
        return indexOfTheme(str, 0);
    }

    private int indexOfTheme(String str, int i) {
        return indexOfTheme(getThemeList(), str, i);
    }

    public static int indexOfTheme(List<Theme> list, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (str.equals(list.get(i2).getName())) {
                return i2;
            }
        }
        return i;
    }

    private List<Theme> getThemeList() {
        return getThemesInfo().getThemes();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        if (i == 2) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_card_detail, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_card_normal, viewGroup, false);
        }
        return new ThemeViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (LogConfig.DBG) {
            Log.d("Styles", "onBindViewHolder: " + viewHolder + " | " + i);
        }
        MaterialCardView materialCardView = (MaterialCardView) viewHolder.itemView;
        Theme theme = getTheme(i);
        bindCommonItems(materialCardView, theme);
        if (getItemViewType(i) == 2) {
            bindDetailCard(materialCardView, theme);
        } else {
            bindNormalCard(materialCardView, theme);
        }
    }

    public void bindCommonItems(MaterialCardView materialCardView, Theme theme) {
        int i;
        int i2;
        Context context = materialCardView.getContext();
        Resources resources = materialCardView.getResources();
        if (ColorOption.isDarkModeEnabled().booleanValue()) {
            i = resources.getColor(C0744R.C0745color.system_accent1_100, context.getTheme());
        } else {
            i = resources.getColor(C0744R.C0745color.system_accent1_600, context.getTheme());
        }
        ColorOption colorOption = theme.getColorOption();
        if (ColorOption.isDarkModeEnabled().booleanValue()) {
            i2 = colorOption.getColorOfAccent1(2);
        } else {
            i2 = colorOption.getColorOfAccent1(7);
        }
        Typeface headlineFont = theme.getFontOption().getHeadlineFont();
        TextView textView = (TextView) materialCardView.findViewById(C0744R.C0747id.name);
        textView.setTypeface(headlineFont);
        textView.setText(theme.getName());
        boolean equals = theme.getName().equals(getAppliedTheme());
        int i3 = 0;
        if (!equals) {
            i = 0;
        }
        materialCardView.setStrokeColor(i);
        CheckBox checkBox = (CheckBox) materialCardView.findViewById(C0744R.C0747id.apply);
        checkBox.setButtonTintList(ColorStateList.valueOf(i2));
        checkBox.setChecked(equals);
        materialCardView.findViewById(C0744R.C0747id.edit).setVisibility(theme.isDefaultOrPreload() ? 8 : 0);
        View findViewById = materialCardView.findViewById(C0744R.C0747id.delete);
        if (theme.isDefaultOrPreload()) {
            i3 = 8;
        }
        findViewById.setVisibility(i3);
    }

    public void bindNormalCard(MaterialCardView materialCardView, Theme theme) {
        Context context = materialCardView.getContext();
        FontOption fontOption = theme.getFontOption();
        ColorOption colorOption = theme.getColorOption();
        ShapeOption shapeOption = theme.getShapeOption();
        WallpaperOption wallpaperOption = theme.getWallpaperOption();
        RingtoneOption ringtoneOption = theme.getRingtoneOption();
        Drawable newDrawable = shapeOption.getShape().getConstantState().newDrawable();
        newDrawable.setTint(colorOption.getColorOfAccent1(2));
        String name = fontOption.getName();
        Typeface headlineFont = theme.getFontOption().getHeadlineFont();
        ViewUtils.setWallpaperIcon(context, wallpaperOption, (ImageView) materialCardView.findViewById(C0744R.C0747id.wallpaper_icon));
        int colorOfNeutral1 = colorOption.getColorOfNeutral1(10);
        ViewUtils.bindImageIcon(materialCardView.findViewById(C0744R.C0747id.shape_icon_wrapper), newDrawable, context.getDrawable(ThemeExt.SHAPE_ICONS.get(0).intValue()), colorOfNeutral1);
        ViewUtils.bindImageIcon(materialCardView.findViewById(C0744R.C0747id.ringtone_icon_wrapper), newDrawable, context.getDrawable(ThemeExt.getRingtoneIcon(ringtoneOption)), colorOfNeutral1);
        ViewUtils.bindWrappedTextIcon(materialCardView.findViewById(C0744R.C0747id.font_icon_wrapper), newDrawable, name.substring(0, Math.min(2, name.length())), headlineFont, colorOfNeutral1);
    }

    public void bindDetailCard(MaterialCardView materialCardView, Theme theme) {
        ViewUtils.bindDetailPreview(materialCardView.findViewById(C0744R.C0747id.detail_preview), theme);
    }

    public void setThemesInfo(ThemesInfo themesInfo) {
        this.mThemesInfo.set(themesInfo);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return getThemeList().size();
    }

    public Theme getTheme(int i) {
        Theme theme = getThemeList().get(i);
        if (LogConfig.DBG) {
            Log.d("Styles", "getTheme: " + i + " | " + theme);
        }
        return theme;
    }

    class ThemeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ThemeViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            view.findViewById(C0744R.C0747id.apply).setOnClickListener(this);
            view.findViewById(C0744R.C0747id.edit).setOnClickListener(this);
            view.findViewById(C0744R.C0747id.delete).setOnClickListener(this);
        }

        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            if (LogConfig.DBG) {
                Log.d("Styles", "onClick: " + view + " | " + layoutPosition);
            }
            Theme theme = ThemesAdapter.this.getTheme(layoutPosition);
            if (view.getId() == C0744R.C0747id.edit || view.getId() == C0744R.C0747id.delete) {
                ThemesAdapter.this.mOnThemeClickCallback.onThemeClick(view.getId(), theme);
            } else if (view.getId() == C0744R.C0747id.apply) {
                ThemesAdapter.this.mOnThemeClickCallback.onThemeClick(view.getId(), theme);
                String appliedTheme = ThemesAdapter.this.getThemesInfo().getAppliedTheme();
                String name = theme.getName();
                String selectedTheme = ThemesAdapter.this.getThemesInfo().getSelectedTheme();
                ThemesAdapter.this.getThemesInfo().setAppliedTheme(name);
                ThemesAdapter themesAdapter = ThemesAdapter.this;
                themesAdapter.notifyItemChanged(themesAdapter.indexOfTheme(appliedTheme));
                ThemesAdapter themesAdapter2 = ThemesAdapter.this;
                themesAdapter2.notifyItemChanged(themesAdapter2.indexOfTheme(name));
                if (!TextUtils.isEmpty(selectedTheme)) {
                    ThemesAdapter themesAdapter3 = ThemesAdapter.this;
                    themesAdapter3.notifyItemChanged(themesAdapter3.indexOfTheme(selectedTheme));
                }
                if (theme.getName().equals(ThemesAdapter.this.getAppliedTheme())) {
                    ((CheckBox) view).setChecked(true);
                }
            } else if (!theme.getName().equals(ThemesAdapter.this.getSelectedTheme())) {
                ThemesAdapter.this.mOnThemeClickCallback.onThemeClick(0, theme);
                String selectedTheme2 = ThemesAdapter.this.getThemesInfo().getSelectedTheme();
                String name2 = theme.getName();
                ThemesAdapter.this.getThemesInfo().setSelectedTheme(name2);
                ThemesAdapter themesAdapter4 = ThemesAdapter.this;
                themesAdapter4.notifyItemChanged(themesAdapter4.indexOfTheme(selectedTheme2));
                ThemesAdapter themesAdapter5 = ThemesAdapter.this;
                themesAdapter5.notifyItemChanged(themesAdapter5.indexOfTheme(name2));
            }
        }
    }

    /* access modifiers changed from: private */
    public String getAppliedTheme() {
        return getThemesInfo().getAppliedTheme();
    }

    /* access modifiers changed from: private */
    public String getSelectedTheme() {
        return getThemesInfo().getSelectedTheme();
    }

    public ThemesInfo getThemesInfo() {
        return this.mThemesInfo.get();
    }

    public int getItemViewType(int i) {
        return getTheme(i).getName().equals(getSelectedTheme()) ? 2 : 1;
    }
}
