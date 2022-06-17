package com.android.wallpaper.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.wallpaper.C0744R;
import com.android.wallpaper.ThemeExt;
import com.android.wallpaper.view.ThemeOptionsAdapter;
import com.motorola.styles.LogConfig;
import com.motorola.styles.model.ColorOption;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.RingtoneOption;
import com.motorola.styles.model.ShapeOption;
import com.motorola.styles.model.ThemeSettings;
import com.motorola.styles.model.WallpaperOption;
import java.util.ArrayList;
import java.util.List;

public class ThemeOptionsAdapter extends RecyclerView.Adapter {
    public static final int VIEW_TYPE_COLOR = 3;
    public static final int VIEW_TYPE_FONT = 2;
    public static final int VIEW_TYPE_GRID = 5;
    public static final int VIEW_TYPE_RINGTONE = 6;
    public static final int VIEW_TYPE_SHAPE = 4;
    public static final int VIEW_TYPE_WALLPAPER = 1;
    private boolean mInEditMode = false;
    /* access modifiers changed from: private */
    public OnOptionActCallback mOnOptionActCallback;
    /* access modifiers changed from: private */
    public final List<Option> mOptionList = new ArrayList();
    /* access modifiers changed from: private */
    public int mSelectedIndex;
    private String mSetting = "";

    public interface OnOptionActCallback {
        boolean onClick(Option option);

        void onItemSelected(View view, Option option);

        void onLongPress(Option option);

        void onLongPressRelease(Option option);
    }

    public ThemeOptionsAdapter(OnOptionActCallback onOptionActCallback) {
        this.mOnOptionActCallback = onOptionActCallback;
    }

    public void setOptions(String str, List<? extends Option> list, String str2) {
        if (LogConfig.DBG) {
            Log.d("Styles", "New StyleSettingAdapter: " + str + " | " + list + " | " + str2);
        }
        this.mSetting = str;
        this.mOptionList.clear();
        this.mOptionList.addAll(list);
        this.mSelectedIndex = indexOfOption(str2);
        notifyDataSetChanged();
    }

    private int indexOfOption(String str) {
        int indexOfSettings;
        if (str == null) {
            return 0;
        }
        if (this.mInEditMode && (indexOfSettings = ThemeSettings.indexOfSettings(this.mOptionList, $$Lambda$ThemeOptionsAdapter$lrc4nxyZ64VUtxed6NxD9kw8jo.INSTANCE)) != -1) {
            return indexOfSettings;
        }
        for (int i = 0; i < this.mOptionList.size(); i++) {
            if (str.equals(this.mOptionList.get(i).getValue())) {
                return i;
            }
        }
        return 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_option_text_icon_view, viewGroup, false));
        }
        if (i == 3) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_option_color_icon_view, viewGroup, false));
        }
        if (i == 4) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_option_shape_icon_view, viewGroup, false));
        }
        if (i == 5) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_option_grid_icon_view, viewGroup, false));
        }
        if (i == 1) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_option_wallpaper_icon_view, viewGroup, false));
        }
        if (i == 6) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_option_ringtone_icon_view, viewGroup, false));
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (LogConfig.DBG) {
            Log.d("Styles", "onBindViewHolder: ");
        }
        if (getItemViewType(i) == 2) {
            bindFontOption(viewHolder.itemView, i);
        } else if (getItemViewType(i) == 3) {
            bindColorIcon(viewHolder.itemView, i);
        } else if (getItemViewType(i) == 4) {
            bindShapeIcon(viewHolder.itemView, i);
        } else if (getItemViewType(i) == 5) {
            bindGridIcon(viewHolder.itemView, i);
        } else if (getItemViewType(i) == 1) {
            bindWallpaperIcon(viewHolder.itemView, i);
        } else if (getItemViewType(i) == 6) {
            bindRingtoneIcon(viewHolder.itemView, i);
        }
    }

    private void bindRingtoneIcon(View view, int i) {
        RingtoneOption ringtoneOption = (RingtoneOption) getOption(i);
        ((ImageView) view.findViewById(C0744R.C0747id.icon)).setImageResource(ThemeExt.getRingtoneIcon(ringtoneOption));
        view.setSelected(getSelectedIndex() == i);
        if (view.isSelected()) {
            this.mOnOptionActCallback.onItemSelected(view, ringtoneOption);
        }
        ((TextView) view.findViewById(C0744R.C0747id.name)).setText(ThemeExt.getRingtoneName(view.getContext(), ringtoneOption));
    }

    private void bindWallpaperIcon(View view, int i) {
        ViewUtils.setWallpaperIcon(view.getContext(), (WallpaperOption) getOption(i), (ImageView) view.findViewById(C0744R.C0747id.f85bg));
        view.setSelected(getSelectedIndex() == i);
    }

    private void bindGridIcon(View view, int i) {
        GridOption gridOption = (GridOption) getOption(i);
        ((ImageView) view.findViewById(C0744R.C0747id.icon)).setImageResource(gridOption.getGridDrawableId());
        view.setSelected(getSelectedIndex() == i);
        ((TextView) view.findViewById(C0744R.C0747id.name)).setText(gridOption.getName(view.getContext()));
    }

    private void bindShapeIcon(View view, int i) {
        Context context = view.getContext();
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(C0744R.dimen.view_36dp);
        LayerDrawable layerDrawable = (LayerDrawable) ((ShapeOption) getOption(i)).getShape().getConstantState().newDrawable();
        boolean z = false;
        layerDrawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        Drawable drawable = layerDrawable.getDrawable(0);
        drawable.setTintList(resources.getColorStateList(C0744R.C0745color.themes_option_icon_color_selector, theme));
        ShapeDrawable shapeDrawable = (ShapeDrawable) layerDrawable.getDrawable(1);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(C0744R.dimen.view_10dp);
        shapeDrawable.setIntrinsicHeight(drawable.getIntrinsicHeight() - dimensionPixelSize2);
        shapeDrawable.setIntrinsicWidth(drawable.getIntrinsicWidth() - dimensionPixelSize2);
        shapeDrawable.setTint(resources.getColor(C0744R.C0745color.shape_icon_bg_color, theme));
        ShapeDrawable shapeDrawable2 = (ShapeDrawable) shapeDrawable.getConstantState().newDrawable();
        layerDrawable.addLayer(shapeDrawable2);
        layerDrawable.setLayerGravity(2, 17);
        shapeDrawable2.setTintList(resources.getColorStateList(C0744R.C0745color.themes_option_icon_bg_color_selector, theme));
        ((ImageView) view.findViewById(C0744R.C0747id.icon)).setImageDrawable(layerDrawable);
        if (getSelectedIndex() == i) {
            z = true;
        }
        view.setSelected(z);
    }

    private void bindColorIcon(View view, int i) {
        ColorOption colorOption = (ColorOption) getOption(i);
        setColorBackground(view.findViewById(C0744R.C0747id.f85bg), colorOption, colorOption.isWallpaperColor());
        view.setSelected(getSelectedIndex() == i);
    }

    private void setColorBackground(View view, ColorOption colorOption, boolean z) {
        view.findViewById(C0744R.C0747id.extra_colors).setVisibility(8);
        if (z) {
            view.setBackgroundColor(colorOption.getColorOfAccent1(2));
            view.findViewById(C0744R.C0747id.extra_color1).setBackgroundColor(getColorWithLStar(colorOption.getColorOfAccent2(6), 95.0f));
            view.findViewById(C0744R.C0747id.extra_color2).setBackgroundColor(getColorWithLStar(colorOption.getColorOfAccent3(6), 85.0f));
            view.findViewById(C0744R.C0747id.extra_colors).setVisibility(0);
            return;
        }
        view.setBackgroundColor(ColorOption.resolveColor(colorOption));
    }

    public static int getColorWithLStar(int i, float f) {
        return Build.VERSION.SDK_INT >= 31 ? ColorStateList.valueOf(i).withLStar(f).getColors()[0] : i;
    }

    private void bindFontOption(View view, int i) {
        FontOption fontOption = (FontOption) getOption(i);
        TextView textView = (TextView) view.findViewById(C0744R.C0747id.text);
        textView.setTypeface(fontOption.getHeadlineFont());
        textView.setText(C0744R.string.themes_option_font_sample);
        ((TextView) view.findViewById(C0744R.C0747id.name)).setText(fontOption.getName(view.getContext()));
        view.setSelected(getSelectedIndex() == i);
    }

    public int getItemCount() {
        return this.mOptionList.size();
    }

    public int getItemViewType(int i) {
        if (ThemeSettings.isWallpaper(this.mSetting)) {
            return 1;
        }
        if (ThemeSettings.isFont(this.mSetting)) {
            return 2;
        }
        if (ThemeSettings.isColor(this.mSetting)) {
            return 3;
        }
        if (ThemeSettings.isShape(this.mSetting)) {
            return 4;
        }
        if (ThemeSettings.isGrid(this.mSetting)) {
            return 5;
        }
        return ThemeSettings.isRingtone(this.mSetting) ? 6 : 0;
    }

    public Option getOption(int i) {
        Option option = this.mOptionList.get(i);
        if (LogConfig.DBG) {
            Log.d("Styles", "getOption: " + i + " | " + option);
        }
        return option;
    }

    public int getSelectedIndex() {
        return this.mSelectedIndex;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ItemViewHolder(View view) {
            super(view);
            View findViewById = view.findViewById(C0744R.C0747id.card);
            C0764x95ee1697 r0 = new Runnable(findViewById) {
                public final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    ThemeOptionsAdapter.ItemViewHolder.lambda$new$0(this.f$0);
                }
            };
            C0765xf7177477 r1 = new Runnable() {
                public final void run() {
                    ThemeOptionsAdapter.ItemViewHolder.this.lambda$new$1$ThemeOptionsAdapter$ItemViewHolder();
                }
            };
            view.setOnTouchListener(new View.OnTouchListener(findViewById, r0, r1) {
                public final /* synthetic */ View f$1;
                public final /* synthetic */ Runnable f$2;
                public final /* synthetic */ Runnable f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return ThemeOptionsAdapter.ItemViewHolder.this.lambda$new$3$ThemeOptionsAdapter$ItemViewHolder(this.f$1, this.f$2, this.f$3, view, motionEvent);
                }
            });
            view.setOnClickListener(this);
            if (findViewById != null) {
                findViewById.setOnClickListener(new View.OnClickListener(findViewById) {
                    public final /* synthetic */ View f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void onClick(View view) {
                        ((View) this.f$0.getParent()).performClick();
                    }
                });
                findViewById.setOnTouchListener(new View.OnTouchListener(r1) {
                    public final /* synthetic */ Runnable f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        return ThemeOptionsAdapter.ItemViewHolder.this.lambda$new$5$ThemeOptionsAdapter$ItemViewHolder(this.f$1, view, motionEvent);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$new$0(View view) {
            if (view != null) {
                view.setPressed(true);
            }
        }

        public /* synthetic */ void lambda$new$1$ThemeOptionsAdapter$ItemViewHolder() {
            Option itemOption = getItemOption();
            Log.d("Styles", "ItemViewHolder - card long press: " + itemOption);
            ThemeOptionsAdapter.this.mOnOptionActCallback.onLongPress(itemOption);
        }

        public /* synthetic */ boolean lambda$new$3$ThemeOptionsAdapter$ItemViewHolder(View view, Runnable runnable, Runnable runnable2, View view2, MotionEvent motionEvent) {
            if (view == null) {
                return false;
            }
            if (motionEvent.getAction() == 0) {
                Log.d("Styles", "ItemViewHolder - ACTION_DOWN: " + motionEvent);
                view.postDelayed(runnable, (long) ViewConfiguration.getTapTimeout());
                view.postDelayed(runnable2, (long) ViewConfiguration.getLongPressTimeout());
            } else if (motionEvent.getAction() == 1) {
                view.removeCallbacks(runnable);
                view.removeCallbacks(runnable2);
                if (motionEvent.getEventTime() - motionEvent.getDownTime() < ((long) ViewConfiguration.getTapTimeout())) {
                    view.setPressed(true);
                    view.postDelayed(new Runnable(view) {
                        public final /* synthetic */ View f$0;

                        {
                            this.f$0 = r1;
                        }

                        public final void run() {
                            this.f$0.setPressed(false);
                        }
                    }, 200);
                } else {
                    view.setPressed(false);
                }
                ThemeOptionsAdapter.this.mOnOptionActCallback.onLongPressRelease(getItemOption());
            } else if (motionEvent.getAction() == 3 || motionEvent.getAction() == 4) {
                view.removeCallbacks(runnable);
                view.removeCallbacks(runnable2);
                view.setPressed(false);
                ThemeOptionsAdapter.this.mOnOptionActCallback.onLongPressRelease(getItemOption());
            }
            return false;
        }

        public /* synthetic */ boolean lambda$new$5$ThemeOptionsAdapter$ItemViewHolder(Runnable runnable, View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                Log.d("Styles", "ItemViewHolder - Card - ACTION_DOWN: " + motionEvent);
                view.postDelayed(runnable, (long) ViewConfiguration.getLongPressTimeout());
                return false;
            } else if (motionEvent.getAction() == 1) {
                view.removeCallbacks(runnable);
                ThemeOptionsAdapter.this.mOnOptionActCallback.onLongPressRelease(getItemOption());
                return false;
            } else if (motionEvent.getAction() != 3 && motionEvent.getAction() != 4) {
                return false;
            } else {
                view.removeCallbacks(runnable);
                ThemeOptionsAdapter.this.mOnOptionActCallback.onLongPressRelease(getItemOption());
                return false;
            }
        }

        private Option getItemOption() {
            return ThemeOptionsAdapter.this.getOption(getLayoutPosition());
        }

        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            if (LogConfig.DBG) {
                Log.d("Styles", "onClick: " + view + " | " + layoutPosition);
            }
            int access$000 = ThemeOptionsAdapter.this.mSelectedIndex;
            int unused = ThemeOptionsAdapter.this.mSelectedIndex = layoutPosition;
            boolean z = false;
            if (ThemeOptionsAdapter.this.mOnOptionActCallback != null) {
                z = ThemeOptionsAdapter.this.mOnOptionActCallback.onClick(ThemeOptionsAdapter.this.getOption(layoutPosition));
            }
            if (z) {
                ThemeOptionsAdapter themeOptionsAdapter = ThemeOptionsAdapter.this;
                themeOptionsAdapter.notifyItemChanged(access$000, themeOptionsAdapter.mOptionList.get(access$000));
                ThemeOptionsAdapter themeOptionsAdapter2 = ThemeOptionsAdapter.this;
                themeOptionsAdapter2.notifyItemChanged(themeOptionsAdapter2.mSelectedIndex, ThemeOptionsAdapter.this.mOptionList.get(ThemeOptionsAdapter.this.mSelectedIndex));
            }
        }
    }
}
