package com.motorola.personalize.app.sound;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.app.ParentFragment;
import com.motorola.personalize.data.IntentData;
import com.motorola.personalize.databinding.FeatureTopBarBinding;
import com.motorola.personalize.databinding.FragmentSoundBinding;
import com.motorola.personalize.extensions.Logger;
import com.motorola.personalize.extensions.OrientationExtensionsKt;
import com.motorola.personalize.viewmodel.SoundsViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo15461d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0010H\u0016J\b\u0010 \u001a\u00020\u0010H\u0016J\u001a\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010#\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u001cH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, mo15462d2 = {"Lcom/motorola/personalize/app/sound/SoundFragment;", "Lcom/motorola/personalize/app/ParentFragment;", "()V", "adapter", "Lcom/motorola/personalize/app/sound/SoundsAdapter;", "binding", "Lcom/motorola/personalize/databinding/FragmentSoundBinding;", "isFragmentPaused", "", "mViewModel", "Lcom/motorola/personalize/viewmodel/SoundsViewModel;", "picker", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "adjustInsets", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onFeatureClicked", "type", "", "intentData", "Lcom/motorola/personalize/data/IntentData;", "onPause", "onResume", "onViewCreated", "view", "openActivity", "setupToolbar", "color", "Companion", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: SoundFragment.kt */
public final class SoundFragment extends ParentFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "SoundFragment";
    private SoundsAdapter adapter;
    private FragmentSoundBinding binding;
    private boolean isFragmentPaused;
    private SoundsViewModel mViewModel;
    private final ActivityResultLauncher<Intent> picker;

    public SoundFragment() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            public final void onActivityResult(Object obj) {
                SoundFragment.m95picker$lambda1(SoundFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {\n            val data = it.data\n            val resultCode = it.resultCode\n            logD(TAG) { \"registerForActivityResult: $resultCode , $data\" }\n            isFragmentPaused = false\n            if (resultCode == RESULT_OK && data != null) {\n                val type = data.getIntExtra(\"ringtone_type\", RM.TYPE_RINGTONE)\n                val uri = data.getParcelableExtra<Uri>(RM.EXTRA_RINGTONE_PICKED_URI)\n                mViewModel.updateRingtone(type, uri)\n            }\n        }");
        this.picker = registerForActivityResult;
    }

    /* access modifiers changed from: private */
    /* renamed from: picker$lambda-1  reason: not valid java name */
    public static final void m95picker$lambda1(SoundFragment soundFragment, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(soundFragment, "this$0");
        Intent data = activityResult.getData();
        int resultCode = activityResult.getResultCode();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "registerForActivityResult: " + resultCode + " , " + data);
        }
        soundFragment.isFragmentPaused = false;
        if (resultCode == -1 && data != null) {
            int intExtra = data.getIntExtra("ringtone_type", 1);
            Uri uri = (Uri) data.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            SoundsViewModel soundsViewModel = soundFragment.mViewModel;
            if (soundsViewModel != null) {
                soundsViewModel.updateRingtone(intExtra, uri);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                throw null;
            }
        }
    }

    public void onResume() {
        super.onResume();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("onResume ", Boolean.valueOf(this.isFragmentPaused)));
        }
        if (this.isFragmentPaused) {
            SoundsViewModel soundsViewModel = this.mViewModel;
            if (soundsViewModel != null) {
                soundsViewModel.refreshRingtoneListIfNeed();
                this.isFragmentPaused = false;
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    public void onPause() {
        super.onPause();
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onPause");
        }
        this.isFragmentPaused = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onViewCreated");
        }
        FragmentSoundBinding fragmentSoundBinding = this.binding;
        if (fragmentSoundBinding != null) {
            fragmentSoundBinding.setLifecycleOwner(this);
        }
        FragmentActivity activity = getActivity();
        RecyclerView recyclerView = null;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity == null ? null : activity.getApplicationContext());
        linearLayoutManager.setOrientation(1);
        FragmentSoundBinding fragmentSoundBinding2 = this.binding;
        RecyclerView recyclerView2 = fragmentSoundBinding2 == null ? null : fragmentSoundBinding2.functionList;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(linearLayoutManager);
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.adapter = new SoundsAdapter(requireContext, new SoundFragment$onViewCreated$2(this));
        FragmentSoundBinding fragmentSoundBinding3 = this.binding;
        if (fragmentSoundBinding3 != null) {
            recyclerView = fragmentSoundBinding3.functionList;
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(this.adapter);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onActivityCreated");
        }
        SoundsViewModel soundsViewModel = new SoundsViewModel(requireActivity().getApplication());
        this.mViewModel = soundsViewModel;
        if (soundsViewModel != null) {
            soundsViewModel.soundItemsLiveData.observe(getViewLifecycleOwner(), new Observer() {
                public final void onChanged(Object obj) {
                    SoundFragment.m94onActivityCreated$lambda7(SoundFragment.this, (List) obj);
                }
            });
            SoundsViewModel soundsViewModel2 = this.mViewModel;
            if (soundsViewModel2 != null) {
                soundsViewModel2.loadSoundsItems();
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                throw null;
            }
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            throw null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onActivityCreated$lambda-7  reason: not valid java name */
    public static final void m94onActivityCreated$lambda7(SoundFragment soundFragment, List list) {
        Intrinsics.checkNotNullParameter(soundFragment, "this$0");
        Intrinsics.checkNotNullParameter(list, "items");
        SoundsAdapter soundsAdapter = soundFragment.adapter;
        Intrinsics.checkNotNull(soundsAdapter);
        soundsAdapter.addItems(list);
    }

    private final void openActivity(IntentData intentData) {
        Intent intent = new Intent(intentData.getIntent());
        Bundle intentExtra = intentData.getIntentExtra();
        if (intentExtra != null) {
            intent.putExtras(intentExtra);
        }
        CharSequence intentPackage = intentData.getIntentPackage();
        if (!(intentPackage == null || StringsKt.isBlank(intentPackage))) {
            intent.setPackage(intentData.getIntentPackage());
        }
        this.picker.launch(intent);
    }

    public void adjustInsets() {
        RecyclerView recyclerView;
        FeatureTopBarBinding featureTopBarBinding;
        ConstraintLayout constraintLayout;
        FragmentSoundBinding fragmentSoundBinding = this.binding;
        if (!(fragmentSoundBinding == null || (featureTopBarBinding = fragmentSoundBinding.toolbarContainer) == null || (constraintLayout = featureTopBarBinding.toolbarLayout) == null)) {
            OrientationExtensionsKt.applyViewPaddingInset$default(constraintLayout, true, true, true, false, 8, (Object) null);
        }
        FragmentSoundBinding fragmentSoundBinding2 = this.binding;
        if (fragmentSoundBinding2 != null && (recyclerView = fragmentSoundBinding2.functionList) != null) {
            OrientationExtensionsKt.applyViewPaddingInsetListener$default(recyclerView, true, true, false, false, 12, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: setupToolbar$lambda-12  reason: not valid java name */
    public static final void m96setupToolbar$lambda12(SoundFragment soundFragment, View view) {
        Intrinsics.checkNotNullParameter(soundFragment, "this$0");
        FragmentActivity activity = soundFragment.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Metadata(mo15461d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, mo15462d2 = {"Lcom/motorola/personalize/app/sound/SoundFragment$Companion;", "", "()V", "TAG", "", "newInstance", "Landroidx/fragment/app/Fragment;", "extras", "Landroid/os/Bundle;", "newInstance$app_release", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: SoundFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Fragment newInstance$app_release(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "extras");
            SoundFragment soundFragment = new SoundFragment();
            soundFragment.setArguments(bundle);
            return soundFragment;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, "onCreateView");
        }
        FragmentSoundBinding inflate = FragmentSoundBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        if (inflate == null) {
            return null;
        }
        return inflate.getRoot();
    }

    /* access modifiers changed from: private */
    public final void onFeatureClicked(int i, IntentData intentData) {
        if (Logger.INSTANCE.getDEBUG()) {
            Log.d(TAG, Intrinsics.stringPlus("onFeatureClicker - ", Integer.valueOf(i)));
        }
        openActivity(intentData);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r0 = r0.toolbarContainer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupToolbar(int r4) {
        /*
            r3 = this;
            com.motorola.personalize.extensions.Logger r0 = com.motorola.personalize.extensions.Logger.INSTANCE
            boolean r0 = r0.getDEBUG()
            if (r0 == 0) goto L_0x000f
            java.lang.String r0 = "SoundFragment"
            java.lang.String r1 = "setupToolbar"
            android.util.Log.d(r0, r1)
        L_0x000f:
            com.motorola.personalize.databinding.FragmentSoundBinding r0 = r3.binding
            if (r0 != 0) goto L_0x0014
            goto L_0x0026
        L_0x0014:
            com.motorola.personalize.databinding.FeatureTopBarBinding r0 = r0.toolbarContainer
            if (r0 != 0) goto L_0x0019
            goto L_0x0026
        L_0x0019:
            android.widget.ImageView r0 = r0.backIcon
            if (r0 != 0) goto L_0x001e
            goto L_0x0026
        L_0x001e:
            com.motorola.personalize.app.sound.-$$Lambda$SoundFragment$DJh1HgYyZfH2GClZN1GQHLh819I r1 = new com.motorola.personalize.app.sound.-$$Lambda$SoundFragment$DJh1HgYyZfH2GClZN1GQHLh819I
            r1.<init>()
            r0.setOnClickListener(r1)
        L_0x0026:
            com.motorola.personalize.databinding.FragmentSoundBinding r0 = r3.binding
            r1 = 0
            if (r0 != 0) goto L_0x002d
        L_0x002b:
            r0 = r1
            goto L_0x0034
        L_0x002d:
            com.motorola.personalize.databinding.FeatureTopBarBinding r0 = r0.toolbarContainer
            if (r0 != 0) goto L_0x0032
            goto L_0x002b
        L_0x0032:
            androidx.constraintlayout.widget.ConstraintLayout r0 = r0.toolbarLayout
        L_0x0034:
            if (r0 != 0) goto L_0x0037
            goto L_0x0041
        L_0x0037:
            android.graphics.drawable.ColorDrawable r2 = new android.graphics.drawable.ColorDrawable
            r2.<init>(r4)
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            r0.setBackground(r2)
        L_0x0041:
            android.content.Context r4 = r3.getContext()
            if (r4 != 0) goto L_0x0049
            r4 = r1
            goto L_0x0050
        L_0x0049:
            r0 = 2131689565(0x7f0f005d, float:1.9008149E38)
            java.lang.String r4 = r4.getString(r0)
        L_0x0050:
            com.motorola.personalize.databinding.FragmentSoundBinding r0 = r3.binding
            if (r0 != 0) goto L_0x0056
        L_0x0054:
            r0 = r1
            goto L_0x005d
        L_0x0056:
            com.motorola.personalize.databinding.FeatureTopBarBinding r0 = r0.toolbarContainer
            if (r0 != 0) goto L_0x005b
            goto L_0x0054
        L_0x005b:
            android.widget.TextView r0 = r0.toolbarTitle
        L_0x005d:
            if (r0 != 0) goto L_0x0060
            goto L_0x0066
        L_0x0060:
            r2 = r4
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
        L_0x0066:
            com.motorola.personalize.databinding.FragmentSoundBinding r3 = r3.binding
            if (r3 != 0) goto L_0x006b
            goto L_0x0072
        L_0x006b:
            com.motorola.personalize.databinding.FeatureTopBarBinding r3 = r3.toolbarContainer
            if (r3 != 0) goto L_0x0070
            goto L_0x0072
        L_0x0070:
            android.widget.TextView r1 = r3.toolbarTitle
        L_0x0072:
            if (r1 != 0) goto L_0x0075
            goto L_0x007a
        L_0x0075:
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r1.setContentDescription(r4)
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.sound.SoundFragment.setupToolbar(int):void");
    }
}
