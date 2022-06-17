package com.android.wallpaper.view;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.wallpaper.C0744R;
import com.motorola.styles.LogConfig;
import java.util.ArrayList;
import java.util.List;

public class ThemeSettingsAdapter extends RecyclerView.Adapter {
    /* access modifiers changed from: private */
    public View.OnClickListener mOnItemClickListener;
    /* access modifiers changed from: private */
    public String mSelectedSetting;
    private final List<Pair<String, Integer>> mSettings = new ArrayList();

    public ThemeSettingsAdapter(View.OnClickListener onClickListener) {
        this.mOnItemClickListener = onClickListener;
    }

    public void setSettings(List<Pair<String, Integer>> list, String str) {
        if (LogConfig.DBG) {
            Log.d("Styles", "New ThemeSettingsAdapter: " + list + " | " + this.mSelectedSetting);
        }
        this.mSettings.clear();
        this.mSettings.addAll(list);
        this.mSelectedSetting = str;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SettingViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0744R.layout.themes_theme_setting_item_view, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView = (TextView) viewHolder.itemView;
        Pair pair = this.mSettings.get(i);
        textView.setTag(pair);
        textView.setText(((Integer) pair.second).intValue());
        textView.setSelected(((String) pair.first).equals(this.mSelectedSetting));
    }

    public int getItemCount() {
        return this.mSettings.size();
    }

    class SettingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public SettingViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            String access$000 = ThemeSettingsAdapter.this.mSelectedSetting;
            String unused = ThemeSettingsAdapter.this.mSelectedSetting = (String) ((Pair) view.getTag()).first;
            if (!access$000.equals(ThemeSettingsAdapter.this.mSelectedSetting)) {
                ThemeSettingsAdapter themeSettingsAdapter = ThemeSettingsAdapter.this;
                themeSettingsAdapter.notifyItemChanged(themeSettingsAdapter.indexOf(access$000));
                ThemeSettingsAdapter themeSettingsAdapter2 = ThemeSettingsAdapter.this;
                themeSettingsAdapter2.notifyItemChanged(themeSettingsAdapter2.indexOf(themeSettingsAdapter2.mSelectedSetting));
                if (ThemeSettingsAdapter.this.mOnItemClickListener != null) {
                    ThemeSettingsAdapter.this.mOnItemClickListener.onClick(view);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public int indexOf(String str) {
        for (int i = 0; i < this.mSettings.size(); i++) {
            if (((String) this.mSettings.get(i).first).equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public int getSelectedIndex() {
        return indexOf(this.mSelectedSetting);
    }

    public String getSettingLabel(Context context, int i) {
        return context.getString(((Integer) this.mSettings.get(i).second).intValue());
    }

    public int getFirstItemWidth(Context context) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(C0744R.layout.themes_theme_setting_item_view, (ViewGroup) null);
        textView.setText(getSettingLabel(context, 0));
        textView.measure(0, 0);
        return textView.getMeasuredWidth() + (context.getResources().getDimensionPixelSize(C0744R.dimen.view_5dp) * 2);
    }

    public int getLastItemWidth(Context context) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(C0744R.layout.themes_theme_setting_item_view, (ViewGroup) null);
        textView.setText(getSettingLabel(context, this.mSettings.size() - 1));
        textView.measure(0, 0);
        return textView.getMeasuredWidth() + (context.getResources().getDimensionPixelSize(C0744R.dimen.view_5dp) * 2);
    }

    public String getSelectedSetting() {
        return this.mSelectedSetting;
    }
}
