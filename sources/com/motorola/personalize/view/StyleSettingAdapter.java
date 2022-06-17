package com.motorola.personalize.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.personalize.data.AppDatabaseHelper;
import com.motorola.personalize.extensions.ContextExtensionsKt;
import com.motorola.personalize.extensions.ResourcesExtensionsKt;
import com.motorola.personalize.model.ColorOption;
import com.motorola.personalize.model.StyleSettings;
import com.motorola.personalize.util.Utilities;
import com.motorola.personalize.view.StyleSettingAdapter;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.ShapeOption;
import java.util.List;

public class StyleSettingAdapter extends RecyclerView.Adapter {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public OnOptionClickCallback mOnOptionClickCallback;
    /* access modifiers changed from: private */
    public List<Option> mOptionList;
    /* access modifiers changed from: private */
    public int mSelectedIndex;
    private String mSetting;

    public interface OnOptionClickCallback {
        boolean onOptionClick(Option option, int i, int i2, boolean z);
    }

    static /* synthetic */ int access$210(StyleSettingAdapter styleSettingAdapter) {
        int i = styleSettingAdapter.mSelectedIndex;
        styleSettingAdapter.mSelectedIndex = i - 1;
        return i;
    }

    public StyleSettingAdapter(Context context, String str, List<Option> list, String str2, OnOptionClickCallback onOptionClickCallback) {
        Log.d("Styles", "New StyleSettingAdapter : " + str + " | " + list + " | " + str2);
        this.mContext = context;
        this.mSetting = str;
        this.mOptionList = list;
        this.mSelectedIndex = indexOfOption(str2);
        Log.d("Styles", "StyleSettingAdapter: mSelectedIndex = " + this.mSelectedIndex);
        this.mOnOptionClickCallback = onOptionClickCallback;
        if (!list.isEmpty()) {
            onOptionClickCallback.onOptionClick(getOption(this.mSelectedIndex), this.mSelectedIndex, this.mOptionList.size(), true);
        }
    }

    private int indexOfOption(String str) {
        if (str == null) {
            return 0;
        }
        for (int i = 0; i < this.mOptionList.size(); i++) {
            if (str.equals(this.mOptionList.get(i).getValue())) {
                return i;
            }
        }
        return 0;
    }

    private int findPickerIndex() {
        for (int i = 0; i < this.mOptionList.size(); i++) {
            if (1 == ((ColorOption) this.mOptionList.get(i)).getType()) {
                return i;
            }
        }
        return 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int i2;
        if (StyleSettings.isColor(this.mSetting)) {
            i2 = C1057R.layout.item_color_option;
        } else if (StyleSettings.isGrid(this.mSetting)) {
            i2 = C1057R.layout.item_grid_option;
        } else {
            i2 = StyleSettings.isShape(this.mSetting) ? C1057R.layout.item_shape_option : C1057R.layout.item_font_option;
        }
        return new ItemViewHolder(LayoutInflater.from(this.mContext).inflate(i2, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d("Styles", "onBindViewHolder: ");
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        boolean z = i == this.mSelectedIndex;
        if (StyleSettings.isColor(this.mSetting)) {
            bindColorIcon(this.mContext, itemViewHolder.itemView, (ColorOption) this.mOptionList.get(i), i, z);
        } else if (StyleSettings.isGrid(this.mSetting)) {
            bindGridIcon(this.mContext, itemViewHolder.itemView, (GridOption) this.mOptionList.get(i), i, z);
        } else if (StyleSettings.isShape(this.mSetting)) {
            bindShapeIcon(this.mContext, itemViewHolder.itemView, (ShapeOption) this.mOptionList.get(i), i, z);
        } else {
            bindFontIcon(this.mContext, itemViewHolder.itemView, (FontOption) this.mOptionList.get(i), i, z);
            itemViewHolder.itemView.requestLayout();
        }
    }

    private void assignMargin(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (ResourcesExtensionsKt.isRTL(this.mContext.getResources())) {
            if (i == 0) {
                marginLayoutParams.rightMargin = i2;
                marginLayoutParams.leftMargin = 0;
            } else if (i == this.mOptionList.size() - 1) {
                marginLayoutParams.rightMargin = 0;
                marginLayoutParams.leftMargin = i2;
            } else {
                marginLayoutParams.leftMargin = 0;
                marginLayoutParams.rightMargin = 0;
            }
        } else if (i == 0) {
            marginLayoutParams.leftMargin = i2;
            marginLayoutParams.rightMargin = 0;
        } else if (i == this.mOptionList.size() - 1) {
            marginLayoutParams.leftMargin = 0;
            marginLayoutParams.rightMargin = i2;
        } else {
            marginLayoutParams.leftMargin = 0;
            marginLayoutParams.rightMargin = 0;
        }
        view.setLayoutParams(marginLayoutParams);
    }

    private void bindGridIcon(Context context, View view, GridOption gridOption, int i, boolean z) {
        int i2;
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        int dimensionPixelSize = resources.getDimensionPixelSize(C1057R.dimen.item_icon_size);
        assignMargin(view, i, ((Utilities.getDisplayWidth(context) - dimensionPixelSize) / 2) - context.getResources().getDimensionPixelSize(C1057R.dimen.half_feature_option_padding));
        CardView cardView = (CardView) view.findViewById(C1057R.C1060id.card_view);
        ImageView imageView = (ImageView) view.findViewById(C1057R.C1060id.grid_icon);
        TextView textView = (TextView) view.findViewById(C1057R.C1060id.color_title);
        Drawable drawable = context.getDrawable(gridOption.getGridDrawableId());
        if (z) {
            i2 = resources.getColor(C1057R.C1058color.grid_icon_color_active, theme);
            cardView.setCardBackgroundColor(resources.getColor(C1057R.C1058color.card_color_active, theme));
            cardView.setForeground((Drawable) null);
        } else {
            i2 = resources.getColor(C1057R.C1058color.grid_icon_color, theme);
            cardView.setCardBackgroundColor(resources.getColor(C1057R.C1058color.card_color_normal, theme));
            cardView.setForeground(resources.getDrawable(C1057R.C1059drawable.option_border, theme));
        }
        drawable.setTint(i2);
        imageView.setImageDrawable(drawable);
        textView.setText(gridOption.getName(context));
    }

    private void bindFontIcon(Context context, View view, FontOption fontOption, int i, boolean z) {
        int i2;
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        int dimensionPixelSize = resources.getDimensionPixelSize(C1057R.dimen.item_icon_size);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(C1057R.dimen.half_feature_option_padding);
        int displayWidth = Utilities.getDisplayWidth(context);
        if (this.mOptionList.size() > 4) {
            assignMargin(view, i, ((displayWidth - dimensionPixelSize) / 2) - dimensionPixelSize2);
        }
        CardView cardView = (CardView) view.findViewById(C1057R.C1060id.card_view);
        ImageView imageView = (ImageView) view.findViewById(C1057R.C1060id.store_icon);
        TextView textView = (TextView) view.findViewById(C1057R.C1060id.font_title);
        ImageView imageView2 = (ImageView) view.findViewById(C1057R.C1060id.delete_icon);
        textView.setText(fontOption.getName(context));
        TextView textView2 = (TextView) view.findViewById(C1057R.C1060id.font_text_view);
        if (fontOption.isPlaceHolder()) {
            textView2.setVisibility(8);
            imageView.setVisibility(0);
            cardView.setCardBackgroundColor(resources.getColor(C1057R.C1058color.card_color_normal, theme));
            cardView.setForeground(resources.getDrawable(C1057R.C1059drawable.option_border, theme));
        } else {
            imageView.setVisibility(8);
            textView.setVisibility(0);
            if (z) {
                i2 = resources.getColor(C1057R.C1058color.shape_icon_color_active, theme);
                cardView.setCardBackgroundColor(resources.getColor(C1057R.C1058color.card_color_active, theme));
                cardView.setForeground((Drawable) null);
            } else {
                i2 = resources.getColor(C1057R.C1058color.shape_icon_color, theme);
                cardView.setCardBackgroundColor(resources.getColor(C1057R.C1058color.card_color_normal, theme));
                cardView.setForeground(resources.getDrawable(C1057R.C1059drawable.option_border, theme));
            }
            textView2.setVisibility(0);
            textView2.setTextColor(i2);
            textView2.setTypeface(fontOption.getHeadlineFont());
        }
        if (fontOption.isUninstallable()) {
            imageView2.setVisibility(0);
            imageView2.setOnClickListener(new View.OnClickListener(fontOption) {
                public final /* synthetic */ FontOption f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    StyleSettingAdapter.this.lambda$bindFontIcon$0$StyleSettingAdapter(this.f$1, view);
                }
            });
            return;
        }
        imageView2.setVisibility(8);
        imageView2.setOnClickListener((View.OnClickListener) null);
    }

    public /* synthetic */ void lambda$bindFontIcon$0$StyleSettingAdapter(FontOption fontOption, View view) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setFlags(268435456);
        intent.setData(Uri.fromParts("package", fontOption.getValue(), ""));
        this.mContext.startActivity(intent);
    }

    public void bindColorIcon(Context context, View view, ColorOption colorOption, int i, boolean z) {
        int i2;
        Context context2 = context;
        View view2 = view;
        final ColorOption colorOption2 = colorOption;
        final int i3 = i;
        final boolean z2 = z;
        Log.d("Styles", "bindColorIcon: position = " + i3);
        Resources resources = context.getResources();
        assignMargin(view2, i3, ((Utilities.getDisplayWidth(context) - resources.getDimensionPixelSize(C1057R.dimen.item_icon_size)) / 2) - context.getResources().getDimensionPixelSize(C1057R.dimen.half_feature_option_padding));
        CardView cardView = (CardView) view2.findViewById(C1057R.C1060id.card_view);
        ImageView imageView = (ImageView) view2.findViewById(C1057R.C1060id.color_icon);
        TextView textView = (TextView) view2.findViewById(C1057R.C1060id.color_title);
        ImageView imageView2 = (ImageView) view2.findViewById(C1057R.C1060id.delete_icon);
        WallpaperIconView wallpaperIconView = (WallpaperIconView) view2.findViewById(C1057R.C1060id.wallpaper_icon_view);
        if (z2) {
            imageView.setImageResource(C1057R.C1059drawable.ic_color_select);
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(4);
        }
        boolean isDarkModeEnabled = ContextExtensionsKt.isDarkModeEnabled(context);
        if (colorOption.getType() == 0) {
            cardView.setCardBackgroundColor(isDarkModeEnabled ? colorOption2.mShowingDarkColor : colorOption2.mShowingLightColor);
        } else if (colorOption.getType() == 1) {
            cardView.setCardBackgroundColor(resources.getColor(isDarkModeEnabled ? C1057R.C1058color.n_1_800 : C1057R.C1058color.n_1_10));
        } else {
            cardView.setCardBackgroundColor(colorOption.getLightColor());
        }
        int type = colorOption.getType();
        if (type == 3) {
            imageView2.setVisibility(0);
            i2 = 4;
        } else {
            i2 = 4;
            imageView2.setVisibility(4);
        }
        if (type == 1) {
            textView.setVisibility(0);
            String string = context2.getString(C1057R.string.color_option_picker);
            textView.setText(string);
            cardView.setContentDescription(string);
        } else {
            textView.setVisibility(i2);
            textView.setText("");
        }
        if (type == 2) {
            if (wallpaperIconView == null) {
                wallpaperIconView = new WallpaperIconView(context2);
                wallpaperIconView.setId(C1057R.C1060id.wallpaper_icon_view);
            }
            wallpaperIconView.setColorScheme(colorOption.getColorScheme());
            wallpaperIconView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            cardView.removeView(wallpaperIconView);
            cardView.addView(wallpaperIconView, 0);
        } else if (wallpaperIconView != null) {
            cardView.removeView(wallpaperIconView);
        }
        if (type == 1) {
            imageView.setImageResource(C1057R.C1059drawable.ic_color_picker);
            imageView.setVisibility(0);
        } else {
            cardView.setContentDescription(colorOption.getName());
        }
        if (type == 3) {
            Log.d("Styles", "bindColorIcon: option type= CUSTOM");
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!Utilities.isDoubleClick()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(StyleSettingAdapter.this.mContext, C1057R.style.DialogTheme);
                        builder.setTitle(C1057R.string.delete_color_title).setMessage(C1057R.string.delete_color_msg).setPositiveButton(C1057R.string.btn_text_yes, new DialogInterface.OnClickListener(i3, z2, colorOption2) {
                            public final /* synthetic */ int f$1;
                            public final /* synthetic */ boolean f$2;
                            public final /* synthetic */ ColorOption f$3;

                            {
                                this.f$1 = r2;
                                this.f$2 = r3;
                                this.f$3 = r4;
                            }

                            public final void onClick(DialogInterface dialogInterface, int i) {
                                StyleSettingAdapter.C10841.this.lambda$onClick$0$StyleSettingAdapter$1(this.f$1, this.f$2, this.f$3, dialogInterface, i);
                            }
                        }).setNegativeButton(C1057R.string.btn_text_no, $$Lambda$StyleSettingAdapter$1$U9MT9nEaTXH92Q0UHX84RC3QUNs.INSTANCE);
                        builder.create().show();
                    }
                }

                public /* synthetic */ void lambda$onClick$0$StyleSettingAdapter$1(int i, boolean z, ColorOption colorOption, DialogInterface dialogInterface, int i2) {
                    PersonalizeMetrics.deleteColor(StyleSettingAdapter.this.mContext);
                    StyleSettingAdapter.this.mOptionList.remove(i);
                    if (z) {
                        StyleSettingAdapter.access$210(StyleSettingAdapter.this);
                        if (((ColorOption) StyleSettingAdapter.this.mOptionList.get(StyleSettingAdapter.this.mSelectedIndex)).getType() == 1) {
                            StyleSettingAdapter.access$210(StyleSettingAdapter.this);
                        }
                        StyleSettingAdapter styleSettingAdapter = StyleSettingAdapter.this;
                        styleSettingAdapter.notifyItemChanged(styleSettingAdapter.mSelectedIndex);
                    }
                    StyleSettingAdapter.this.notifyItemRemoved(i);
                    StyleSettingAdapter styleSettingAdapter2 = StyleSettingAdapter.this;
                    styleSettingAdapter2.notifyItemRangeChanged(i - 1, (styleSettingAdapter2.mOptionList.size() - i) + 1);
                    AppDatabaseHelper.getInstance().getDao().deleteColor(colorOption.getLightColor());
                }
            });
        }
    }

    private void bindShapeIcon(Context context, View view, ShapeOption shapeOption, int i, boolean z) {
        int i2;
        int i3;
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        int dimensionPixelSize = resources.getDimensionPixelSize(C1057R.dimen.item_icon_size);
        assignMargin(view, i, ((Utilities.getDisplayWidth(context) - dimensionPixelSize) / 2) - context.getResources().getDimensionPixelSize(C1057R.dimen.half_feature_option_padding));
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(C1057R.dimen.small_shape_icon_size);
        CardView cardView = (CardView) view.findViewById(C1057R.C1060id.card_view);
        ImageView imageView = (ImageView) view.findViewById(C1057R.C1060id.shape_icon);
        LayerDrawable layerDrawable = (LayerDrawable) shapeOption.getShape().getConstantState().newDrawable();
        layerDrawable.setBounds(0, 0, dimensionPixelSize2, dimensionPixelSize2);
        ShapeDrawable shapeDrawable = (ShapeDrawable) layerDrawable.getDrawable(0);
        shapeDrawable.setBounds(0, 0, dimensionPixelSize2, dimensionPixelSize2);
        ShapeDrawable shapeDrawable2 = (ShapeDrawable) layerDrawable.getDrawable(1);
        shapeDrawable2.setBounds(0, 0, dimensionPixelSize2, dimensionPixelSize2);
        int intrinsicHeight = (int) (((float) shapeDrawable2.getIntrinsicHeight()) * 0.85f);
        shapeDrawable2.setIntrinsicHeight(intrinsicHeight);
        shapeDrawable2.setIntrinsicWidth(intrinsicHeight);
        cardView.setContentDescription(shapeOption.getName(context));
        if (z) {
            i3 = resources.getColor(C1057R.C1058color.card_color_active, theme);
            i2 = resources.getColor(C1057R.C1058color.shape_icon_color_active, theme);
            cardView.setForeground((Drawable) null);
        } else {
            i3 = resources.getColor(C1057R.C1058color.card_color_normal, theme);
            i2 = resources.getColor(C1057R.C1058color.shape_icon_color, theme);
            cardView.setForeground(resources.getDrawable(C1057R.C1059drawable.option_border, theme));
        }
        cardView.setCardBackgroundColor(i3);
        shapeDrawable2.setTint(i3);
        shapeDrawable.setTint(i2);
        imageView.setImageDrawable(layerDrawable);
    }

    public int getItemCount() {
        return this.mOptionList.size();
    }

    public Option getOption(int i) {
        Option option = this.mOptionList.get(i);
        Log.d("Styles", "getOption: " + i + " | " + option);
        return option;
    }

    public Option getSelectedOption() {
        return this.mOptionList.get(this.mSelectedIndex);
    }

    public int getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public void insertColor(ColorOption colorOption) {
        Log.d("Styles", "insertColor: ");
        int findPickerIndex = findPickerIndex() + 1;
        this.mOptionList.add(findPickerIndex, colorOption);
        notifyItemInserted(findPickerIndex);
        notifyItemRangeChanged(findPickerIndex - 1, (this.mOptionList.size() - findPickerIndex) + 1);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View itemView;

        public ItemViewHolder(View view) {
            super(view);
            this.itemView = view;
            view.findViewById(C1057R.C1060id.card_view).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    StyleSettingAdapter.ItemViewHolder.this.onClick(view);
                }
            });
        }

        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            Log.d("Styles", "onClick: " + view + " | " + layoutPosition);
            boolean z = false;
            if (StyleSettingAdapter.this.mOnOptionClickCallback != null) {
                z = StyleSettingAdapter.this.mOnOptionClickCallback.onOptionClick(StyleSettingAdapter.this.getOption(layoutPosition), layoutPosition, 0, false);
            }
            if (z) {
                int access$200 = StyleSettingAdapter.this.mSelectedIndex;
                int unused = StyleSettingAdapter.this.mSelectedIndex = layoutPosition;
                StyleSettingAdapter styleSettingAdapter = StyleSettingAdapter.this;
                styleSettingAdapter.notifyItemChanged(access$200, styleSettingAdapter.mOptionList.get(access$200));
                StyleSettingAdapter styleSettingAdapter2 = StyleSettingAdapter.this;
                styleSettingAdapter2.notifyItemChanged(styleSettingAdapter2.mSelectedIndex, StyleSettingAdapter.this.mOptionList.get(StyleSettingAdapter.this.mSelectedIndex));
            }
        }

        public Context getContext() {
            return this.itemView.getContext();
        }
    }
}
