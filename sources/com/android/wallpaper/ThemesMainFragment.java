package com.android.wallpaper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.wallpaper.view.ThemesAdapter;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.styles.LogConfig;
import com.motorola.styles.StylesUtilities;
import com.motorola.styles.model.ColorOption;
import com.motorola.styles.model.FontOption;
import com.motorola.styles.model.Result;
import com.motorola.styles.model.RingtoneOption;
import com.motorola.styles.model.Theme;
import com.motorola.styles.model.ThemesInfo;
import com.motorola.styles.viewmodel.ThemesViewModel;
import java.util.concurrent.atomic.AtomicReference;

public class ThemesMainFragment extends Fragment {
    /* access modifiers changed from: private */
    public View mFab;
    private AtomicReference<String> mReturnedTheme = new AtomicReference<>();
    private RecyclerView mThemeListRv;
    private ThemesAdapter mThemesAdapter;
    /* access modifiers changed from: private */
    public ThemesViewModel mViewModel;

    private void onDeleteCancel() {
    }

    public static ThemesMainFragment newInstance() {
        return new ThemesMainFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C0744R.layout.themes_main_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Utilities.setTitle(getView(), C0744R.string.themes);
        setTopBarButtons();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mViewModel = (ThemesViewModel) ViewModelProviders.m16of((Fragment) this, (ViewModelProvider.Factory) new ViewModelProvider.Factory() {
            public <T extends ViewModel> T create(Class<T> cls) {
                try {
                    return (ViewModel) cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{ThemesMainFragment.this.getContext()});
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).get(ThemesViewModel.class);
        setupUi();
        setupObserver();
    }

    private void setupUi() {
        RecyclerView.LayoutManager layoutManager;
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(C0744R.C0747id.theme_list);
        this.mThemeListRv = recyclerView;
        recyclerView.setAlpha(0.0f);
        if (Utilities.isLandscapeMode(getContext())) {
            layoutManager = new GridLayoutManager(getContext(), 1, 1, false);
        } else {
            layoutManager = new LinearLayoutManager(getContext(), 1, false);
        }
        this.mThemeListRv.setLayoutManager(layoutManager);
        this.mThemeListRv.setOnTouchListener(new FabHideHelper());
        ThemesAdapter themesAdapter = new ThemesAdapter(getContext(), new ThemesAdapter.OnThemeClickCallback() {
            public final boolean onThemeClick(int i, Theme theme) {
                return ThemesMainFragment.this.lambda$setupUi$0$ThemesMainFragment(i, theme);
            }
        });
        this.mThemesAdapter = themesAdapter;
        this.mThemeListRv.setAdapter(themesAdapter);
        View findViewById = getView().findViewById(C0744R.C0747id.fab);
        this.mFab = findViewById;
        findViewById.setAlpha(0.0f);
        this.mFab.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThemesMainFragment.this.lambda$setupUi$1$ThemesMainFragment(view);
            }
        });
    }

    public /* synthetic */ boolean lambda$setupUi$0$ThemesMainFragment(int i, Theme theme) {
        if (i == 0) {
            animHideFab();
            return true;
        } else if (i == C0744R.C0747id.apply) {
            apply(theme);
            return true;
        } else if (i == C0744R.C0747id.edit) {
            editTheme(theme);
            return true;
        } else if (i != C0744R.C0747id.delete) {
            return true;
        } else {
            showDeleteThemeDialog(theme);
            return true;
        }
    }

    public /* synthetic */ void lambda$setupUi$1$ThemesMainFragment(View view) {
        if (view.getTranslationX() != 0.0f) {
            view.animate().translationX(0.0f).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ThemesMainFragment themesMainFragment = ThemesMainFragment.this;
                    themesMainFragment.editTheme(themesMainFragment.mViewModel.newTheme());
                }
            }).start();
        } else {
            editTheme(this.mViewModel.newTheme());
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        int uiMode = ThemesViewModel.getUiMode(getContext());
        if (this.mViewModel.isUiModeChanged(uiMode)) {
            this.mViewModel.setUiMode(uiMode);
            this.mViewModel.setWallpaperUpdated();
            this.mViewModel.onLoadThemesInfo();
        }
    }

    private void setupObserver() {
        this.mViewModel.getThemesInfoLiveData().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                ThemesMainFragment.this.lambda$setupObserver$2$ThemesMainFragment((ThemesInfo) obj);
            }
        });
    }

    public /* synthetic */ void lambda$setupObserver$2$ThemesMainFragment(ThemesInfo themesInfo) {
        if (LogConfig.DBG) {
            Log.d("Styles", "Observe themes info: " + themesInfo + " | " + getActivity());
        }
        String andSet = this.mReturnedTheme.getAndSet((Object) null);
        if (this.mThemesAdapter.getItemCount() == 0) {
            andSet = themesInfo.getAppliedTheme();
        }
        final int indexOfTheme = ThemesAdapter.indexOfTheme(themesInfo.getThemes(), andSet, -1);
        if (indexOfTheme != -1) {
            themesInfo.setSelectedTheme(andSet);
        }
        this.mThemesAdapter.setThemesInfo(themesInfo);
        if (this.mThemeListRv.getAlpha() != 1.0f) {
            this.mThemeListRv.animate().alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ThemesMainFragment.this.scrollToTheme(indexOfTheme);
                }
            }).start();
        } else {
            scrollToTheme(indexOfTheme);
        }
    }

    private void showFabIfNeed() {
        if (this.mFab.getAlpha() == 0.0f) {
            this.mFab.animate().alpha(1.0f).start();
        }
    }

    /* access modifiers changed from: private */
    public void scrollToTheme(int i) {
        if (i == -1) {
            showFabIfNeed();
        } else if (i > ((LinearLayoutManager) this.mThemeListRv.getLayoutManager()).findLastCompletelyVisibleItemPosition()) {
            this.mThemeListRv.smoothScrollToPosition(i);
            animHideFab();
        } else {
            showFabIfNeed();
        }
    }

    private void showDeleteThemeDialog(Theme theme) {
        new AlertDialog.Builder(getActivity()).setTitle(C0744R.string.delete_theme_title).setMessage(getString(C0744R.string.delete_prompt, theme.getName())).setPositiveButton(C0744R.string.delete, new DialogInterface.OnClickListener(theme) {
            public final /* synthetic */ Theme f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                ThemesMainFragment.this.lambda$showDeleteThemeDialog$3$ThemesMainFragment(this.f$1, dialogInterface, i);
            }
        }).setNegativeButton(C0744R.string.cancel, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ThemesMainFragment.this.lambda$showDeleteThemeDialog$4$ThemesMainFragment(dialogInterface, i);
            }
        }).create().show();
    }

    public /* synthetic */ void lambda$showDeleteThemeDialog$3$ThemesMainFragment(Theme theme, DialogInterface dialogInterface, int i) {
        onDelete(theme);
    }

    public /* synthetic */ void lambda$showDeleteThemeDialog$4$ThemesMainFragment(DialogInterface dialogInterface, int i) {
        onDeleteCancel();
    }

    private void onDelete(Theme theme) {
        StylesUtilities.observeOnce(this.mViewModel.onDeleteTheme(theme), this, new Observer() {
            public final void onChanged(Object obj) {
                ThemesMainFragment.this.lambda$onDelete$5$ThemesMainFragment((Result) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onDelete$5$ThemesMainFragment(Result result) {
        if (result.containsError()) {
            Toast.makeText(getContext(), result.getErrorMsg(), 0).show();
            return;
        }
        this.mViewModel.onLoadThemesInfo();
        PersonalizeMetrics.deleteTheme(getContext());
    }

    public void apply(Theme theme) {
        ProgressDialog show = ProgressDialog.show(getContext(), (CharSequence) null, (CharSequence) null);
        show.getWindow().setBackgroundDrawableResource(17170445);
        show.setContentView(new ProgressBar(getContext()));
        StylesUtilities.observeOnce(this.mViewModel.onApplyTheme(theme), this, new Observer(theme, show) {
            public final /* synthetic */ Theme f$1;
            public final /* synthetic */ ProgressDialog f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onChanged(Object obj) {
                ThemesMainFragment.this.lambda$apply$6$ThemesMainFragment(this.f$1, this.f$2, (Result) obj);
            }
        });
    }

    public /* synthetic */ void lambda$apply$6$ThemesMainFragment(Theme theme, ProgressDialog progressDialog, Result result) {
        if (result.containsError()) {
            Toast.makeText(getContext(), C0744R.string.apply_theme_failed, 0).show();
        } else {
            Toast.makeText(getContext(), C0744R.string.theme_applied, 0).show();
            checkIn(theme);
        }
        progressDialog.dismiss();
    }

    private void checkIn(Theme theme) {
        Context context = getContext();
        PersonalizeMetrics.setCurrentFont(context, getCheckInFontName(theme.getFontOption()));
        PersonalizeMetrics.setCurrentColor(context, getCheckInColorType(theme.getColorOption()));
        PersonalizeMetrics.setCurrentShape(context, theme.getShape());
        PersonalizeMetrics.setCurrentGrid(context, theme.getGrid());
        PersonalizeMetrics.setCurrentRingtone(context, getCheckInRingtone(theme.getRingtoneOption()));
    }

    private String getCheckInRingtone(RingtoneOption ringtoneOption) {
        if (ringtoneOption.isNoRingtone()) {
            return null;
        }
        return ringtoneOption.getName();
    }

    private String getCheckInFontName(FontOption fontOption) {
        if ("Default".equals(fontOption.getValue())) {
            return "Default";
        }
        return fontOption.getName();
    }

    private int getCheckInColorType(ColorOption colorOption) {
        if (colorOption.isPreloadedColor()) {
            return 1;
        }
        return colorOption.isWallpaperColor() ? 2 : 3;
    }

    private void setTopBarButtons() {
        View topBarButton = Utilities.getTopBarButton(getView(), C0744R.C0747id.start_button);
        topBarButton.setContentDescription(Utilities.getActionBarUpString());
        topBarButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThemesMainFragment.this.lambda$setTopBarButtons$7$ThemesMainFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$setTopBarButtons$7$ThemesMainFragment(View view) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    /* access modifiers changed from: private */
    public void editTheme(Theme theme) {
        if (!theme.isNew() || !this.mViewModel.isCustomThemesReachedMaxCount()) {
            startThemeEditActivity(theme);
        } else {
            Toast.makeText(getContext(), C0744R.string.themes_custom_themes_reached_max_count_prompt, 0).show();
        }
    }

    public void startThemeEditActivity(Theme theme) {
        Intent intent = new Intent(getActivity(), ThemeEditActivity.class);
        intent.putExtra("theme", theme);
        startActivityForResult(intent, 101);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra(Utilities.KEY_THEME_NAME);
            if (LogConfig.DBG) {
                Log.d("Styles", "Returned theme name: " + stringExtra);
            }
            this.mReturnedTheme.set(stringExtra);
            this.mViewModel.onLoadThemesInfo();
            animHideFab();
        }
    }

    private class FabHideHelper implements View.OnTouchListener {
        private int firstTouchY;

        private FabHideHelper() {
            this.firstTouchY = 0;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
                if (this.firstTouchY == 0) {
                    this.firstTouchY = (int) motionEvent.getY();
                }
            } else if (motionEvent.getAction() == 1 && this.firstTouchY != 0) {
                int y = (int) (motionEvent.getY() - ((float) this.firstTouchY));
                if (y < 5) {
                    ThemesMainFragment.this.animHideFab();
                } else if (y > 5) {
                    ViewPropertyAnimator animate = ThemesMainFragment.this.mFab.animate();
                    animate.cancel();
                    animate.translationX(0.0f).start();
                }
                this.firstTouchY = 0;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void animHideFab() {
        Log.d("Styles", "animHideFab: " + this.mFab + " | " + this.mFab.getAlpha());
        final View findViewById = this.mFab.findViewById(C0744R.C0747id.title);
        final View findViewById2 = this.mFab.findViewById(C0744R.C0747id.icon);
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mFab.getLayoutParams();
        ViewPropertyAnimator animate = this.mFab.animate();
        animate.cancel();
        if (this.mFab.getAlpha() != 1.0f) {
            animate.alpha(1.0f);
        }
        animate.translationX((float) getFabHiddenTx(findViewById2, layoutParams)).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (findViewById.getVisibility() == 0) {
                    findViewById.setVisibility(8);
                    ThemesMainFragment.this.mFab.setTranslationX((float) ThemesMainFragment.this.getFabHiddenTx(findViewById2, layoutParams));
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public int getFabHiddenTx(View view, RelativeLayout.LayoutParams layoutParams) {
        int fabWidth = (getFabWidth() + layoutParams.getMarginEnd()) - (view.getWidth() / 2);
        return Utilities.isRtl(view) ? -fabWidth : fabWidth;
    }

    private int getFabWidth() {
        View findViewById = this.mFab.findViewById(C0744R.C0747id.title);
        View findViewById2 = this.mFab.findViewById(C0744R.C0747id.icon);
        if (findViewById.getVisibility() == 0) {
            return findViewById2.getWidth() + findViewById.getWidth();
        }
        return findViewById2.getWidth();
    }
}
