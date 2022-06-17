package com.android.wallpaper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.android.wallpaper.ThemeEditFragment;
import com.android.wallpaper.view.ScrollSpeedLinearLayoutManager;
import com.android.wallpaper.view.ThemeOptionsAdapter;
import com.android.wallpaper.view.ThemeSettingsAdapter;
import com.android.wallpaper.view.ViewUtils;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.styles.LogConfig;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.ColorOption;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.GridOption;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.Result;
import com.motorola.styles.model.RingtoneOption;
import com.motorola.styles.model.ShapeOption;
import com.motorola.styles.model.Theme;
import com.motorola.styles.model.ThemeSettings;
import com.motorola.styles.model.WallpaperOption;
import com.motorola.styles.viewmodel.ThemesViewModel;
import java.util.ArrayList;
import java.util.List;

public class ThemeEditFragment extends Fragment {
    public static final int KEYBOARD_WAITING_TIME = 100;
    /* access modifiers changed from: private */
    public boolean isKeyboardShowing = false;
    private List<String> mCheckInTopics = new ArrayList();
    private Handler mHandler;
    private TextView mNameText;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    private MediaPlayer mPlayer;
    private boolean mRingtoneTooltipNeeded = true;
    /* access modifiers changed from: private */
    public Theme mTheme;
    private ThemeSettings mThemeSettings;
    private ThemesViewModel mViewModel;

    static /* synthetic */ void lambda$onClickDone$12(DialogInterface dialogInterface, int i) {
    }

    private void onDeleteCancel() {
    }

    public static ThemeEditFragment newInstance() {
        return new ThemeEditFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTheme = (Theme) getArguments().getSerializable("theme");
        this.mHandler = new Handler();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C0744R.layout.themes_edit_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        int i;
        super.onViewCreated(view, bundle);
        View view2 = getView();
        if (this.mTheme.isNew()) {
            i = C0744R.string.themes_create_theme;
        } else {
            i = C0744R.string.themes_edit_theme;
        }
        Utilities.setTitle(view2, i);
        setTopBarButtons();
        ThemesViewModel themesViewModel = (ThemesViewModel) ViewModelProviders.m16of((Fragment) this, (ViewModelProvider.Factory) new ViewModelProvider.Factory() {
            public <T extends ViewModel> T create(Class<T> cls) {
                try {
                    return (ViewModel) cls.getConstructor(new Class[]{Context.class, ThemeSettings.class}).newInstance(new Object[]{ThemeEditFragment.this.getContext(), new ThemeSettings(ThemeEditFragment.this.mTheme)});
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).get(ThemesViewModel.class);
        this.mViewModel = themesViewModel;
        this.mThemeSettings = themesViewModel.getThemeSettingsLiveData().getValue();
        this.mRingtoneTooltipNeeded = Utilities.isRingtoneTooltipNeeded(getContext());
        setupUi();
        setupObserver();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    private void setupObserver() {
        linkThemeSettings();
        linkThemePreview();
    }

    private void setupUi() {
        ((ImageView) getView().findViewById(C0744R.C0747id.done)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThemeEditFragment.this.lambda$setupUi$0$ThemeEditFragment(view);
            }
        });
        TextView textView = (TextView) getView().findViewById(C0744R.C0747id.name);
        this.mNameText = textView;
        textView.setText(this.mTheme.isNew() ? "" : this.mTheme.getName());
        this.mNameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                ThemeEditFragment.this.lambda$setupUi$1$ThemeEditFragment(view, z);
            }
        });
        this.mNameText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return ThemeEditFragment.this.lambda$setupUi$2$ThemeEditFragment(textView, i, keyEvent);
            }
        });
        ((TextView) getView().findViewById(C0744R.C0747id.options_title)).setAlpha(0.0f);
        getView().findViewById(C0744R.C0747id.theme_settings_rv).setAlpha(0.0f);
        getView().findViewById(C0744R.C0747id.detail_preview).setAlpha(0.0f);
        getView().findViewById(C0744R.C0747id.options_tooltip).setAlpha(0.0f);
    }

    public /* synthetic */ void lambda$setupUi$1$ThemeEditFragment(View view, boolean z) {
        String str;
        TextView textView = this.mNameText;
        if (z) {
            str = "";
        } else {
            str = getString(C0744R.string.themes_name_edit_text_hint);
        }
        textView.setHint(str);
    }

    public /* synthetic */ boolean lambda$setupUi$2$ThemeEditFragment(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        this.mNameText.clearFocus();
        return false;
    }

    private void _registerKeyboardChangeListener() {
        if (getActivity() != null && this.mOnGlobalLayoutListener == null) {
            this.mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
                public final void onGlobalLayout() {
                    ThemeEditFragment.this.lambda$_registerKeyboardChangeListener$3$ThemeEditFragment();
                }
            };
            getActivity().getWindow().getDecorView().getRootView().getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        }
    }

    private void _unregisterKeyboardChangeListener() {
        if (this.mOnGlobalLayoutListener != null) {
            getActivity().getWindow().getDecorView().getRootView().getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
            this.mOnGlobalLayoutListener = null;
        }
    }

    public void onResume() {
        super.onResume();
        int uiMode = ThemesViewModel.getUiMode(getContext());
        if (this.mViewModel.isUiModeChanged(uiMode)) {
            this.mViewModel.setUiMode(uiMode);
            if (ThemeSettings.isWallpaper(this.mThemeSettings.getSetting())) {
                this.mViewModel.setWallpaperUpdated();
                onLoadThemeSetting();
            }
        }
    }

    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void showOrHideSoftKeyboard(boolean z, int i) {
        if (LogConfig.DBG) {
            Log.d("Styles", "showOrHideSoftKeyboard: " + z + " | " + i);
        }
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(C0744R.C0747id.theme_options_container);
        if (z) {
            linearLayout.setPadding(linearLayout.getPaddingLeft(), linearLayout.getPaddingTop(), linearLayout.getPaddingRight(), i);
        } else {
            linearLayout.setPadding(linearLayout.getPaddingLeft(), linearLayout.getPaddingTop(), linearLayout.getPaddingRight(), getResources().getDimensionPixelSize(C0744R.dimen.view_24dp));
        }
        getView().requestLayout();
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0, new ResultReceiver(this.mHandler) {
                /* access modifiers changed from: protected */
                public void onReceiveResult(int i, Bundle bundle) {
                    super.onReceiveResult(i, bundle);
                    boolean unused = ThemeEditFragment.this.isKeyboardShowing = false;
                    ThemeEditFragment.this.showOrHideSoftKeyboard(false, 0);
                }
            });
        }
    }

    public void linkThemeSettings() {
        this.mViewModel.getThemeSettingsLiveData().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                ThemeEditFragment.this.lambda$linkThemeSettings$7$ThemeEditFragment((ThemeSettings) obj);
            }
        });
    }

    public /* synthetic */ void lambda$linkThemeSettings$7$ThemeEditFragment(ThemeSettings themeSettings) {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(C0744R.C0747id.theme_settings_rv);
        if (recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            ThemeSettingsAdapter themeSettingsAdapter = new ThemeSettingsAdapter(new View.OnClickListener() {
                public final void onClick(View view) {
                    ThemeEditFragment.this.onSettingClick(view);
                }
            });
            themeSettingsAdapter.setSettings(ThemeSettingsExt.SETTINGS, themeSettings.getSetting());
            recyclerView.setAdapter(themeSettingsAdapter);
            recyclerView.post(new Runnable(recyclerView, themeSettingsAdapter) {
                public final /* synthetic */ RecyclerView f$1;
                public final /* synthetic */ ThemeSettingsAdapter f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    ThemeEditFragment.this.lambda$linkThemeSettings$4$ThemeEditFragment(this.f$1, this.f$2);
                }
            });
        }
        RecyclerView optionsRv = getOptionsRv(themeSettings);
        if (optionsRv.getAdapter() == null) {
            ScrollSpeedLinearLayoutManager scrollSpeedLinearLayoutManager = new ScrollSpeedLinearLayoutManager(getContext(), 0, false, 3.0f);
            optionsRv.setLayoutManager(scrollSpeedLinearLayoutManager);
            ThemeOptionsAdapter themeOptionsAdapter = new ThemeOptionsAdapter(new ThemeOptionsAdapter.OnOptionActCallback() {
                public boolean onClick(Option option) {
                    return ThemeEditFragment.this.onThemeOptionClick(option);
                }

                public void onLongPress(Option option) {
                    if (option instanceof RingtoneOption) {
                        MediaPlayer unused = ThemeEditFragment.this.startPlayRingtone((RingtoneOption) option);
                        ThemeEditFragment.this.closeRingtoneTooltipIfNeed(true);
                    }
                }

                public void onLongPressRelease(Option option) {
                    if (option instanceof RingtoneOption) {
                        ThemeEditFragment.this.stopPlayRingtone();
                    }
                }

                public void onItemSelected(View view, Option option) {
                    if (LogConfig.DBG) {
                        Log.d("Styles", "onItemSelected: " + view + " | " + option);
                    }
                    if ((option instanceof RingtoneOption) && ThemeEditFragment.this.isRingtoneTooltipNeeded()) {
                        ThemeEditFragment.this.showRingtoneTooltipIfNeed(new View.OnClickListener() {
                            public final void onClick(View view) {
                                ThemeEditFragment.C07513.this.lambda$onItemSelected$0$ThemeEditFragment$3(view);
                            }
                        });
                    }
                }

                public /* synthetic */ void lambda$onItemSelected$0$ThemeEditFragment$3(View view) {
                    ThemeEditFragment.this.closeRingtoneTooltipIfNeed(true);
                }
            });
            themeOptionsAdapter.setOptions(themeSettings.getSetting(), themeSettings.getOptions(), themeSettings.getSettingValue());
            optionsRv.setAdapter(themeOptionsAdapter);
            LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
            linearSnapHelper.attachToRecyclerView(optionsRv);
            optionsRv.post(new Runnable(optionsRv, themeOptionsAdapter) {
                public final /* synthetic */ RecyclerView f$1;
                public final /* synthetic */ ThemeOptionsAdapter f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    ThemeEditFragment.this.lambda$linkThemeSettings$5$ThemeEditFragment(this.f$1, this.f$2);
                }
            });
            if (optionsRv.getId() == C0744R.C0747id.color) {
                optionsRv.setOnScrollChangeListener(new View.OnScrollChangeListener(optionsRv, scrollSpeedLinearLayoutManager, themeOptionsAdapter, linearSnapHelper) {
                    public final /* synthetic */ RecyclerView f$1;
                    public final /* synthetic */ LinearLayoutManager f$2;
                    public final /* synthetic */ ThemeOptionsAdapter f$3;
                    public final /* synthetic */ SnapHelper f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                        ThemeEditFragment.this.lambda$linkThemeSettings$6$ThemeEditFragment(this.f$1, this.f$2, this.f$3, this.f$4, view, i, i2, i3, i4);
                    }
                });
                return;
            }
            return;
        }
        ThemeOptionsAdapter themeOptionsAdapter2 = (ThemeOptionsAdapter) optionsRv.getAdapter();
        themeOptionsAdapter2.setOptions(themeSettings.getSetting(), themeSettings.getOptions(), themeSettings.getSettingValue());
        optionsRv.scrollToPosition(themeOptionsAdapter2.getSelectedIndex());
        setVisibleOptionsRv((ViewGroup) optionsRv.getParent(), optionsRv);
    }

    public /* synthetic */ void lambda$linkThemeSettings$4$ThemeEditFragment(RecyclerView recyclerView, ThemeSettingsAdapter themeSettingsAdapter) {
        int width = ((View) recyclerView.getParent()).getWidth();
        recyclerView.setPaddingRelative((width - themeSettingsAdapter.getFirstItemWidth(getContext())) / 2, 0, (width - themeSettingsAdapter.getLastItemWidth(getContext())) / 2, 0);
        new LinearSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.scrollToPosition(0);
        recyclerView.animate().alpha(1.0f).start();
    }

    public /* synthetic */ void lambda$linkThemeSettings$5$ThemeEditFragment(RecyclerView recyclerView, ThemeOptionsAdapter themeOptionsAdapter) {
        int i;
        int width = ((View) recyclerView.getParent()).getWidth();
        if (recyclerView.getId() != C0744R.C0747id.wallpaper) {
            i = getResources().getDimensionPixelSize(C0744R.dimen.view_84dp);
        } else {
            i = getResources().getDimensionPixelSize(C0744R.dimen.view_110dp);
        }
        int i2 = (width - i) / 2;
        recyclerView.setPaddingRelative(i2, 0, i2, 0);
        recyclerView.scrollToPosition(themeOptionsAdapter.getSelectedIndex());
        setVisibleOptionsRv((ViewGroup) recyclerView.getParent(), recyclerView);
    }

    public /* synthetic */ void lambda$linkThemeSettings$6$ThemeEditFragment(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, ThemeOptionsAdapter themeOptionsAdapter, SnapHelper snapHelper, View view, int i, int i2, int i3, int i4) {
        setColorTitle(recyclerView, linearLayoutManager, themeOptionsAdapter, snapHelper);
    }

    /* access modifiers changed from: private */
    public void showRingtoneTooltipIfNeed(View.OnClickListener onClickListener) {
        View findViewById = getView().findViewById(C0744R.C0747id.options_tooltip);
        if (findViewById.getAlpha() != 1.0f) {
            ((TextView) findViewById.findViewById(C0744R.C0747id.tooltip_text)).setText(C0744R.string.ringtone_tooltip);
            ((ImageView) findViewById.findViewById(C0744R.C0747id.tooltip_close_btn)).setOnClickListener(onClickListener);
            findViewById.animate().cancel();
            findViewById.animate().alpha(1.0f).start();
        }
    }

    /* access modifiers changed from: private */
    public void closeRingtoneTooltipIfNeed(boolean z) {
        View findViewById = getView().findViewById(C0744R.C0747id.options_tooltip);
        if (findViewById.getAlpha() != 0.0f) {
            findViewById.animate().cancel();
            findViewById.animate().alpha(0.0f).start();
        }
        if (z && this.mRingtoneTooltipNeeded) {
            this.mRingtoneTooltipNeeded = false;
            Utilities.disableRingtoneTooltip(getContext());
        }
    }

    /* access modifiers changed from: private */
    public boolean isRingtoneTooltipNeeded() {
        return this.mRingtoneTooltipNeeded;
    }

    private void setColorTitle(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, ThemeOptionsAdapter themeOptionsAdapter, SnapHelper snapHelper) {
        ColorOption colorOption = (ColorOption) themeOptionsAdapter.getOption(recyclerView.getChildLayoutPosition(snapHelper.findSnapView(linearLayoutManager)));
        TextView textView = (TextView) getView().findViewById(C0744R.C0747id.options_title);
        if (colorOption.isWallpaperColor()) {
            textView.setText(C0744R.string.themes_color_wallpaper_based_colors);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getContext().getDrawable(C0744R.C0746drawable.themes_ic_wallpaper_colors), (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (colorOption.isPickedColor()) {
            textView.setText(C0744R.string.themes_color_picked_colors);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getContext().getDrawable(C0744R.C0746drawable.themes_ic_picked_colors), (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            textView.setText(C0744R.string.themes_color_palette_colors);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getContext().getDrawable(C0744R.C0746drawable.themes_ic_color_palette), (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    /* access modifiers changed from: private */
    public void onSettingClick(View view) {
        this.mThemeSettings.setSetting((String) ((Pair) view.getTag()).first);
        onLoadThemeSetting();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(C0744R.C0747id.theme_settings_rv);
        recyclerView.postDelayed(new Runnable() {
            public final void run() {
                RecyclerView.this.smoothScrollToPosition(((ThemeSettingsAdapter) RecyclerView.this.getAdapter()).getSelectedIndex());
            }
        }, 100);
    }

    private void setVisibleOptionsRv(ViewGroup viewGroup, RecyclerView recyclerView) {
        float f;
        int i = 0;
        while (true) {
            f = 1.0f;
            if (i >= viewGroup.getChildCount()) {
                break;
            }
            final View childAt = viewGroup.getChildAt(i);
            boolean z = childAt == recyclerView;
            if (!z && childAt.getAlpha() > 0.0f) {
                childAt.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        childAt.setVisibility(8);
                    }
                }).start();
            } else if (z && childAt.getAlpha() < 1.0f) {
                childAt.animate().alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        childAt.setVisibility(0);
                    }
                }).start();
            }
            i++;
        }
        ViewPropertyAnimator animate = ((TextView) getView().findViewById(C0744R.C0747id.options_title)).animate();
        if (recyclerView.getId() != C0744R.C0747id.color) {
            f = 0.0f;
        }
        animate.alpha(f).start();
        if (recyclerView.getId() != C0744R.C0747id.ringtone) {
            closeRingtoneTooltipIfNeed(false);
        }
    }

    public void linkThemePreview() {
        this.mViewModel.getThemePreviewLiveData().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                ThemeEditFragment.this.lambda$linkThemePreview$9$ThemeEditFragment((Theme) obj);
            }
        });
    }

    public /* synthetic */ void lambda$linkThemePreview$9$ThemeEditFragment(Theme theme) {
        Log.d("Styles", "linkThemePreview: " + theme);
        View findViewById = getView().findViewById(C0744R.C0747id.detail_preview);
        ViewUtils.bindDetailPreview(findViewById, theme);
        if (findViewById.getAlpha() == 0.0f) {
            findViewById.animate().alpha(1.0f).start();
        }
    }

    /* access modifiers changed from: private */
    public boolean onThemeOptionClick(Option option) {
        this.mThemeSettings.setSettingValue(option);
        this.mViewModel.onLoadThemePreview(this.mThemeSettings.getTheme());
        RecyclerView optionsRv = getOptionsRv(this.mThemeSettings);
        optionsRv.postDelayed(new Runnable() {
            public final void run() {
                RecyclerView.this.smoothScrollToPosition(((ThemeOptionsAdapter) RecyclerView.this.getAdapter()).getSelectedIndex());
            }
        }, 200);
        addCheckInTopic(option);
        return true;
    }

    private void addCheckInTopic(Option option) {
        if ((option instanceof FontOption) && !this.mCheckInTopics.contains(PersonalizeMetrics.TOPIC_FONT)) {
            this.mCheckInTopics.add(PersonalizeMetrics.TOPIC_FONT);
        } else if ((option instanceof ColorOption) && !this.mCheckInTopics.contains(PersonalizeMetrics.TOPIC_COLOR)) {
            this.mCheckInTopics.add(PersonalizeMetrics.TOPIC_COLOR);
        } else if ((option instanceof ShapeOption) && !this.mCheckInTopics.contains(PersonalizeMetrics.TOPIC_SHAPE)) {
            this.mCheckInTopics.add(PersonalizeMetrics.TOPIC_SHAPE);
        } else if ((option instanceof GridOption) && !this.mCheckInTopics.contains(PersonalizeMetrics.TOPIC_GRID)) {
            this.mCheckInTopics.add(PersonalizeMetrics.TOPIC_GRID);
        } else if ((option instanceof WallpaperOption) && !this.mCheckInTopics.contains(PersonalizeMetrics.TOPIC_WALLPAPER)) {
            this.mCheckInTopics.add(PersonalizeMetrics.TOPIC_WALLPAPER);
        } else if ((option instanceof RingtoneOption) && !this.mCheckInTopics.contains(PersonalizeMetrics.TOPIC_RINGTONE)) {
            this.mCheckInTopics.add(PersonalizeMetrics.TOPIC_RINGTONE);
        }
    }

    private RecyclerView getOptionsRv(ThemeSettings themeSettings) {
        return (RecyclerView) ((ViewGroup) getView().findViewById(C0744R.C0747id.theme_options_wrapper)).findViewById(getResources().getIdentifier(themeSettings.getSetting(), "id", getContext().getPackageName()));
    }

    /* renamed from: onClickDone */
    public void lambda$setupUi$0$ThemeEditFragment(View view) {
        String charSequence = this.mNameText.getText().toString();
        if (TextUtils.isEmpty(charSequence)) {
            new AlertDialog.Builder(getActivity()).setTitle(C0744R.string.themes_save_theme_title).setView(getThemeNamePromptDialogView()).setPositiveButton(C0744R.string.f88ok, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ThemeEditFragment.this.lambda$onClickDone$11$ThemeEditFragment(dialogInterface, i);
                }
            }).setNegativeButton(C0744R.string.cancel, $$Lambda$ThemeEditFragment$MGIbyYZX8o7ngsatLXa0DnqW1jY.INSTANCE).create().show();
        } else {
            onEditTheme(charSequence);
        }
    }

    public /* synthetic */ void lambda$onClickDone$11$ThemeEditFragment(DialogInterface dialogInterface, int i) {
        onEditTheme(((EditText) ((AlertDialog) dialogInterface).findViewById(C0744R.C0747id.name)).getText().toString());
    }

    private View getThemeNamePromptDialogView() {
        View inflate = LayoutInflater.from(getContext()).inflate(C0744R.layout.themes_theme_name_prompt_dialog_view, (ViewGroup) null);
        ((TextView) inflate.findViewById(C0744R.C0747id.name)).setText(this.mTheme.getName());
        return inflate;
    }

    public void onEditTheme(String str) {
        this.mThemeSettings.setName(str);
        if (this.mTheme.isNew()) {
            StylesUtilities.observeOnce(this.mViewModel.onCreateTheme(this.mThemeSettings.getTheme()), this, new Observer() {
                public final void onChanged(Object obj) {
                    ThemeEditFragment.this.lambda$onEditTheme$13$ThemeEditFragment((Result) obj);
                }
            });
        } else {
            StylesUtilities.observeOnce(this.mViewModel.onUpdateTheme(this.mThemeSettings.getTheme(), this.mTheme), this, new Observer() {
                public final void onChanged(Object obj) {
                    ThemeEditFragment.this.lambda$onEditTheme$14$ThemeEditFragment((Result) obj);
                }
            });
        }
        checkIn(this.mTheme);
    }

    private void checkIn(Theme theme) {
        Context context = getContext();
        if (theme.isNew()) {
            PersonalizeMetrics.createTheme(context);
        } else {
            PersonalizeMetrics.editTheme(context);
        }
        PersonalizeMetrics.chooseTopic(context, (String[]) this.mCheckInTopics.toArray(new String[0]));
    }

    /* access modifiers changed from: private */
    /* renamed from: onEditThemeResult */
    public void lambda$onEditTheme$14$ThemeEditFragment(Result result) {
        if (result.containsError()) {
            Toast.makeText(getContext(), result.getErrorMsg(), 0).show();
            return;
        }
        FragmentActivity activity = getActivity();
        Intent intent = new Intent();
        intent.putExtra(Utilities.KEY_THEME_NAME, this.mThemeSettings.getTheme().getName());
        activity.setResult(-1, intent);
        getActivity().finish();
    }

    private void setTopBarButtons() {
        View topBarButton = Utilities.getTopBarButton(getView(), C0744R.C0747id.start_button);
        topBarButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThemeEditFragment.this.lambda$setTopBarButtons$16$ThemeEditFragment(view);
            }
        });
        topBarButton.setContentDescription(Utilities.getActionBarUpString());
        ImageView imageView = (ImageView) Utilities.getTopBarButton(getView(), C0744R.C0747id.end_button);
        imageView.setImageDrawable(getResources().getDrawable(C0744R.C0746drawable.themes_ic_delete, getContext().getTheme()));
        imageView.setContentDescription(getResources().getString(C0744R.string.delete));
        this.mTheme.isNew();
        imageView.setVisibility(8);
    }

    public /* synthetic */ void lambda$setTopBarButtons$16$ThemeEditFragment(View view) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            hideSoftKeyboard();
            this.mHandler.postDelayed(new Runnable(activity) {
                public final /* synthetic */ Activity f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.finish();
                }
            }, this.isKeyboardShowing ? 100 : 0);
        }
    }

    private /* synthetic */ void lambda$setTopBarButtons$20(View view) {
        hideSoftKeyboard();
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                ThemeEditFragment.this.lambda$setTopBarButtons$19$ThemeEditFragment();
            }
        }, this.isKeyboardShowing ? 100 : 0);
    }

    public /* synthetic */ void lambda$setTopBarButtons$19$ThemeEditFragment() {
        new AlertDialog.Builder(getActivity()).setTitle(C0744R.string.delete_theme_title).setMessage(getString(C0744R.string.delete_prompt, this.mTheme.getName())).setPositiveButton(C0744R.string.delete, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ThemeEditFragment.this.lambda$setTopBarButtons$17$ThemeEditFragment(dialogInterface, i);
            }
        }).setNegativeButton(C0744R.string.cancel, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ThemeEditFragment.this.lambda$setTopBarButtons$18$ThemeEditFragment(dialogInterface, i);
            }
        }).create().show();
    }

    public /* synthetic */ void lambda$setTopBarButtons$17$ThemeEditFragment(DialogInterface dialogInterface, int i) {
        onDelete();
    }

    public /* synthetic */ void lambda$setTopBarButtons$18$ThemeEditFragment(DialogInterface dialogInterface, int i) {
        onDeleteCancel();
    }

    private void onDelete() {
        StylesUtilities.observeOnce(this.mViewModel.onDeleteTheme(this.mTheme), this, new Observer() {
            public final void onChanged(Object obj) {
                ThemeEditFragment.this.lambda$onDelete$21$ThemeEditFragment((Result) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onDelete$21$ThemeEditFragment(Result result) {
        if (result.containsError()) {
            Toast.makeText(getContext(), result.getErrorMsg(), 0).show();
        } else {
            getActivity().finish();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: private */
    /* renamed from: onGlobalLayout */
    public void lambda$_registerKeyboardChangeListener$3$ThemeEditFragment() {
        Rect rect = new Rect();
        View rootView = getActivity().getWindow().getDecorView().getRootView();
        rootView.getWindowVisibleDisplayFrame(rect);
        int height = rootView.getRootView().getHeight();
        int i = height - rect.bottom;
        Log.d("Styles", "keypadHeight: " + i);
        if (((double) i) > ((double) height) * 0.15d) {
            this.isKeyboardShowing = true;
        } else {
            this.isKeyboardShowing = false;
        }
        showOrHideSoftKeyboard(this.isKeyboardShowing, i);
    }

    private void onLoadThemeSetting() {
        this.mViewModel.onLoadThemeOptions(this.mThemeSettings);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public MediaPlayer startPlayRingtone(RingtoneOption ringtoneOption) {
        Log.d("Styles", "startPlayRingtone: " + ringtoneOption);
        if (ringtoneOption == null || ringtoneOption.isNoRingtone()) {
            return null;
        }
        stopPlayRingtone();
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mPlayer = mediaPlayer;
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    ThemeEditFragment.this.onPlayRingtoneComplete(mediaPlayer);
                }
            });
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public final void onPrepared(MediaPlayer mediaPlayer) {
                    ThemeEditFragment.this.onPlayRingtonePrepared(mediaPlayer);
                }
            });
            this.mPlayer.setDataSource(getContext(), ringtoneOption.getRingtoneUri());
            this.mPlayer.setAudioAttributes(ringtoneOption.getAudioAttributes(getContext()));
            this.mPlayer.prepareAsync();
        } catch (Exception e) {
            Log.w("Styles", "startPlayRingtone error", e);
            releasePlayer();
        }
        return this.mPlayer;
    }

    /* access modifiers changed from: private */
    public void onPlayRingtonePrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    /* access modifiers changed from: private */
    public void onPlayRingtoneComplete(MediaPlayer mediaPlayer) {
        Log.d("Styles", "onPlayRingtoneComplete: " + mediaPlayer);
        releasePlayer();
    }

    /* access modifiers changed from: private */
    public void stopPlayRingtone() {
        Log.d("Styles", "stopPlayRingtone: " + this.mPlayer);
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
            } catch (Exception e) {
                Log.w("Styles", "stopPlayRingtone error", e);
            } catch (Throwable th) {
                releasePlayer();
                throw th;
            }
            releasePlayer();
        }
    }

    private void releasePlayer() {
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mPlayer = null;
        }
    }
}
