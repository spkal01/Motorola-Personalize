package com.motorola.personalize.viewmodel;

import android.app.Application;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.app.sound.SoundUtil;
import com.motorola.personalize.data.SoundsViewData;
import com.motorola.personalize.util.NoneSdkApi;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SoundsViewModel extends PublicViewModel {
    public static final String TAG = "SoundsViewModel";
    private final List<SoundsViewData> items = new ArrayList();
    private final boolean mMultiSimEnabled;
    public MutableLiveData<List<SoundsViewData>> soundItemsLiveData = new MutableLiveData<>();

    public SoundsViewModel(Application application) {
        super(application);
        Log.d(TAG, "New ThemeViewModel: " + this);
        this.mMultiSimEnabled = NoneSdkApi.isMultiSimEnabled(this.mAppContext);
    }

    public static boolean isDualSimRingtoneSupported() {
        return RingtoneManager.getDefaultUri(64) != null;
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        Log.d(TAG, "ThemeViewModel - onCleared: " + this);
        super.onCleared();
    }

    public void loadSoundsItems() {
        postWorkerThread(new Runnable() {
            public final void run() {
                SoundsViewModel.this.lambda$loadSoundsItems$0$SoundsViewModel();
            }
        });
    }

    public /* synthetic */ void lambda$loadSoundsItems$0$SoundsViewModel() {
        this.items.clear();
        String packageName = this.mAppContext.getPackageName();
        int color = ContextCompat.getColor(this.mAppContext, C1057R.C1058color.personalize_features_color);
        this.items.add(SoundUtil.newData(this.mAppContext, 1, color, packageName));
        if (this.mMultiSimEnabled && isDualSimRingtoneSupported()) {
            this.items.add(SoundUtil.newData(this.mAppContext, 64, color, packageName));
        }
        this.items.add(SoundUtil.newData(this.mAppContext, 2, color, packageName));
        this.items.add(SoundUtil.newData(this.mAppContext, 4, color, packageName));
        this.soundItemsLiveData.postValue(this.items);
    }

    public void updateRingtone(int i, Uri uri) {
        Log.d(TAG, "updateRingtone: " + uri);
        boolean z = false;
        for (SoundsViewData next : this.items) {
            if (next.getType() == i) {
                z = true;
                ((Bundle) Objects.requireNonNull(next.getIntentData().getIntentExtra())).putParcelable("android.intent.extra.ringtone.EXISTING_URI", uri);
                next.setUri(uri);
            }
        }
        if (z) {
            this.soundItemsLiveData.postValue(this.items);
        }
    }

    public void refreshRingtoneListIfNeed() {
        boolean z = false;
        for (SoundsViewData next : this.items) {
            Uri actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this.mAppContext, next.getType());
            if (!Objects.equals((Uri) ((Bundle) Objects.requireNonNull(next.getIntentData().getIntentExtra())).getParcelable("android.intent.extra.ringtone.EXISTING_URI"), actualDefaultRingtoneUri)) {
                z = true;
                next.getIntentData().getIntentExtra().putParcelable("android.intent.extra.ringtone.EXISTING_URI", actualDefaultRingtoneUri);
                next.setUri(actualDefaultRingtoneUri);
            }
        }
        if (z) {
            this.soundItemsLiveData.postValue(this.items);
        }
    }
}
