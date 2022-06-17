package com.motorola.personalize.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.model.SystemThemeOption;
import java.util.List;

public class ThemeOptionAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final OnOptionClickCallback mOnOptionClickCallback;
    /* access modifiers changed from: private */
    public final List<SystemThemeOption> mOptionList;
    /* access modifiers changed from: private */
    public int mSelectedIndex;

    public interface OnOptionClickCallback {
        boolean onOptionClick(SystemThemeOption systemThemeOption, int i);
    }

    public ThemeOptionAdapter(Context context, List<SystemThemeOption> list, String str, OnOptionClickCallback onOptionClickCallback) {
        this.mContext = context;
        this.mOptionList = list;
        this.mSelectedIndex = indexOfOption(str);
        Log.d("Styles", "ThemeOptionAdapter: mSelectedIndex = " + this.mSelectedIndex);
        this.mOnOptionClickCallback = onOptionClickCallback;
        if (!list.isEmpty()) {
            onOptionClickCallback.onOptionClick(getOption(this.mSelectedIndex), this.mSelectedIndex);
        }
    }

    private int indexOfOption(String str) {
        if (str == null) {
            return 0;
        }
        for (int i = 0; i < this.mOptionList.size(); i++) {
            if (str.equals(this.mOptionList.get(i).getTitle())) {
                return i;
            }
        }
        return 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1057R.layout.item_theme_option, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d("Styles", "onBindViewHolder: ");
        bindThemeOption(this.mContext, ((CardViewHolder) viewHolder).view, this.mOptionList.get(i), i == this.mSelectedIndex);
    }

    private void bindThemeOption(Context context, View view, SystemThemeOption systemThemeOption, boolean z) {
        int i;
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        CardView cardView = (CardView) view.findViewById(C1057R.C1060id.card_view);
        cardView.setCardBackgroundColor(0);
        ImageView imageView = (ImageView) view.findViewById(C1057R.C1060id.theme_icon);
        Drawable drawable = context.getDrawable(systemThemeOption.getIconRes());
        String string = resources.getString(systemThemeOption.getNameRes());
        ((TextView) view.findViewById(C1057R.C1060id.theme_title)).setText(string);
        view.setContentDescription(string);
        if (z) {
            i = resources.getColor(C1057R.C1058color.grid_icon_color_active, theme);
            cardView.setCardBackgroundColor(resources.getColor(C1057R.C1058color.card_color_active, theme));
            cardView.setForeground((Drawable) null);
        } else {
            i = resources.getColor(C1057R.C1058color.grid_icon_color, theme);
            cardView.setCardBackgroundColor(resources.getColor(C1057R.C1058color.card_color_normal, theme));
            cardView.setForeground(resources.getDrawable(C1057R.C1059drawable.option_border, theme));
        }
        drawable.setTint(i);
        imageView.setImageDrawable(drawable);
    }

    public int getItemCount() {
        return this.mOptionList.size();
    }

    public SystemThemeOption getOption(int i) {
        SystemThemeOption systemThemeOption = this.mOptionList.get(i);
        Log.d("Styles", "getOption: " + i + " | " + systemThemeOption);
        return systemThemeOption;
    }

    public int getSelectedIndex() {
        return this.mSelectedIndex;
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;

        public CardViewHolder(View view2) {
            super(view2);
            this.view = view2;
            view2.setOnClickListener(this);
        }

        public void onClick(View view2) {
            int layoutPosition = getLayoutPosition();
            Log.d("Styles", "onClick: " + view2 + " | " + layoutPosition);
            if (ThemeOptionAdapter.this.mOnOptionClickCallback.onOptionClick(ThemeOptionAdapter.this.getOption(layoutPosition), layoutPosition)) {
                int access$100 = ThemeOptionAdapter.this.mSelectedIndex;
                int unused = ThemeOptionAdapter.this.mSelectedIndex = layoutPosition;
                ThemeOptionAdapter themeOptionAdapter = ThemeOptionAdapter.this;
                themeOptionAdapter.notifyItemChanged(access$100, themeOptionAdapter.mOptionList.get(access$100));
                ThemeOptionAdapter themeOptionAdapter2 = ThemeOptionAdapter.this;
                themeOptionAdapter2.notifyItemChanged(themeOptionAdapter2.mSelectedIndex, ThemeOptionAdapter.this.mOptionList.get(ThemeOptionAdapter.this.mSelectedIndex));
            }
        }

        public Context getContext() {
            return this.view.getContext();
        }
    }
}
