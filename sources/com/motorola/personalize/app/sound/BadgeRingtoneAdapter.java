package com.motorola.personalize.app.sound;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.personalize.app.sound.BadgeRingtoneAdapter;
import com.motorola.personalize.util.NoneSdkApi;
import com.motorola.personalize.util.Utilities;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BadgeRingtoneAdapter extends RecyclerView.Adapter {
    private static final int ADD_FILE_REQUEST_CODE = 300;
    private static final int POS_UNKNOWN = -1;
    private static final String TAG = "BadgeRingtoneAdapter";
    private static final int TYPE_ADD_NEW_SOUND = 1;
    private static final int TYPE_DEFAULT_RINGTONE = 2;
    private static final int TYPE_RINGTONE = 0;
    private OnRingtoneSelectedListener listener;
    private int mAttributesFlags;
    /* access modifiers changed from: private */
    public final Activity mContext;
    private Ringtone mCurrentRingtone;
    private Ringtone mDefaultRingtone;
    private int mDefaultRingtonePos = -1;
    private int mFooterCount = 0;
    private boolean mHasSilentItem;
    /* access modifiers changed from: private */
    public int mHeaderCount = 0;
    private final boolean mIsManagedProfile;
    private final int mPickerUserId;
    private int mPlayingPos = -1;
    /* access modifiers changed from: private */
    public Uri mPlayingUri;
    /* access modifiers changed from: private */
    public List<RingtoneInfo> mRingtoneList;
    private RingtoneManager mRingtoneManager;
    private int mRingtoneType;
    /* access modifiers changed from: private */
    public int mSelectedPos;
    /* access modifiers changed from: private */
    public Uri mSelectedUri;
    /* access modifiers changed from: private */
    public int mSilentPos = -1;
    /* access modifiers changed from: private */
    public Uri mUriForDefaultItem;

    public interface OnRingtoneSelectedListener {
        void onClickPlayer(Uri uri, Ringtone ringtone, int i);

        void onSelected(Uri uri, Ringtone ringtone, int i);
    }

    public BadgeRingtoneAdapter(Activity activity, boolean z, int i, RingtoneManager ringtoneManager) {
        this.mContext = activity;
        this.mIsManagedProfile = z;
        this.mPickerUserId = i;
        this.mRingtoneManager = ringtoneManager;
    }

    public BadgeRingtoneAdapter setRingtoneType(int i) {
        this.mRingtoneType = i;
        return this;
    }

    public BadgeRingtoneAdapter setRingtoneSelectedListener(OnRingtoneSelectedListener onRingtoneSelectedListener) {
        this.listener = onRingtoneSelectedListener;
        return this;
    }

    public void refreshAdapter(List<RingtoneInfo> list, RingtoneManager ringtoneManager) {
        this.mRingtoneList = list;
        this.mRingtoneManager = ringtoneManager;
        notifyDataSetChanged();
    }

    public BadgeRingtoneAdapter setRingtoneList(List<RingtoneInfo> list) {
        this.mRingtoneList = list;
        return this;
    }

    public BadgeRingtoneAdapter prepareAdapter(Uri uri, Uri uri2, int i) {
        this.mSelectedUri = uri;
        this.mUriForDefaultItem = uri2;
        this.mAttributesFlags = i;
        return this;
    }

    public BadgeRingtoneAdapter prepareList(boolean z, boolean z2) {
        this.mHeaderCount = 0;
        this.mHasSilentItem = z;
        if (z) {
            this.mSilentPos = 0;
            this.mHeaderCount = 0 + 1;
        }
        if (z2) {
            this.mDefaultRingtonePos = 1;
            this.mHeaderCount++;
        }
        if (resolvesMediaFilePicker() && Environment.getExternalStorageState().equals("mounted")) {
            this.mFooterCount++;
        }
        return this;
    }

    /* access modifiers changed from: private */
    public void onClickPlayer(int i) {
        Ringtone ringtone;
        if (i == this.mSilentPos) {
            this.mCurrentRingtone = null;
            this.listener.onClickPlayer(this.mPlayingUri, (Ringtone) null, i);
            return;
        }
        if (i == this.mDefaultRingtonePos) {
            if (this.mDefaultRingtone == null) {
                this.mDefaultRingtone = RingtoneManager.getRingtone(this.mContext, this.mUriForDefaultItem);
            }
            Ringtone ringtone2 = this.mDefaultRingtone;
            if (ringtone2 != null) {
                ringtone2.setStreamType(this.mRingtoneManager.inferStreamType());
            }
            ringtone = this.mDefaultRingtone;
            this.mCurrentRingtone = null;
        } else {
            ringtone = this.mRingtoneManager.getRingtone(i - this.mHeaderCount);
            this.mCurrentRingtone = ringtone;
        }
        PersonalizeMetrics.usePlayButton(this.mContext);
        Log.i(TAG, "position:" + i + ",title:" + ringtone.getTitle(this.mContext) + ",uri:" + NoneSdkApi.getRingtoneUri(ringtone));
        if (!(ringtone == null || this.mAttributesFlags == 0)) {
            boolean z = false;
            boolean z2 = Settings.System.getInt(this.mContext.getContentResolver(), "vibrate_when_ringing", 0) == 1;
            boolean hasHapticChannels = hasHapticChannels(NoneSdkApi.getRingtoneUri(ringtone));
            boolean isHapticPlaybackSupported = AudioManager.isHapticPlaybackSupported();
            if (z2 && hasHapticChannels && isHapticPlaybackSupported) {
                z = true;
            }
            ringtone.setAudioAttributes(new AudioAttributes.Builder(ringtone.getAudioAttributes()).setHapticChannelsMuted(true ^ z).setFlags(this.mAttributesFlags).build());
        }
        this.listener.onClickPlayer(this.mPlayingUri, ringtone, i);
    }

    /* access modifiers changed from: private */
    public void preparePlayRingtone(int i) {
        Ringtone ringtone;
        if (i == this.mSilentPos) {
            this.mCurrentRingtone = null;
            this.listener.onSelected(this.mSelectedUri, (Ringtone) null, i);
            return;
        }
        if (i == this.mDefaultRingtonePos) {
            if (this.mDefaultRingtone == null) {
                this.mDefaultRingtone = RingtoneManager.getRingtone(this.mContext, this.mUriForDefaultItem);
            }
            Ringtone ringtone2 = this.mDefaultRingtone;
            if (ringtone2 != null) {
                ringtone2.setStreamType(this.mRingtoneManager.inferStreamType());
            }
            ringtone = this.mDefaultRingtone;
            this.mCurrentRingtone = null;
        } else {
            ringtone = this.mRingtoneManager.getRingtone(i - this.mHeaderCount);
            this.mCurrentRingtone = ringtone;
        }
        Log.i(TAG, "position:" + i + ",title:" + ringtone.getTitle(this.mContext) + ",uri:" + NoneSdkApi.getRingtoneUri(ringtone));
        this.listener.onSelected(this.mSelectedUri, ringtone, i);
    }

    private boolean hasHapticChannels(Uri uri) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            mediaExtractor.setDataSource(this.mContext, uri, (Map) null);
            for (int i = 0; i < mediaExtractor.getTrackCount(); i++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                if (trackFormat.containsKey("haptic-channel-count") && trackFormat.getInteger("haptic-channel-count") > 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "hasHapticChannels failure:" + e);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public Intent getMediaFilePickerIntent() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("audio/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"audio/*", "application/ogg"});
        return intent;
    }

    private boolean resolvesMediaFilePicker() {
        return getMediaFilePickerIntent().resolveActivity(this.mContext.getPackageManager()) != null;
    }

    public int getItemViewType(int i) {
        int i2 = this.mHeaderCount;
        if (i2 == 0 || i >= i2) {
            return (this.mFooterCount == 0 || i < i2 + this.mRingtoneList.size()) ? 0 : 1;
        }
        return 2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            return new RadioWithBadgeViewHolder(from.inflate(C1057R.layout.radio_with_work_badge, viewGroup, false));
        }
        if (i == 1) {
            return new AddNewSoundViewHolder(from.inflate(C1057R.layout.add_new_sound_item, viewGroup, false), this.mRingtoneType);
        }
        if (i != 2) {
            return null;
        }
        return new DefaultRingtoneViewHolder(from.inflate(C1057R.layout.radio_with_work_badge, viewGroup, false));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0084, code lost:
        if (r10.toString().startsWith(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString()) != false) goto L_0x0088;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder r9, int r10) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.motorola.personalize.app.sound.BadgeRingtoneAdapter.RadioWithBadgeViewHolder
            r1 = 1
            r2 = 8
            r3 = 0
            r4 = 2131296369(0x7f090071, float:1.8210653E38)
            r5 = 0
            if (r0 == 0) goto L_0x00aa
            android.view.View r0 = r9.itemView
            android.view.View r0 = r0.findViewById(r4)
            android.widget.CheckedTextView r0 = (android.widget.CheckedTextView) r0
            java.util.List<com.motorola.personalize.app.sound.RingtoneInfo> r4 = r8.mRingtoneList
            int r6 = r8.mHeaderCount
            int r6 = r10 - r6
            java.lang.Object r4 = r4.get(r6)
            com.motorola.personalize.app.sound.RingtoneInfo r4 = (com.motorola.personalize.app.sound.RingtoneInfo) r4
            java.lang.String r4 = r4.getTitle()
            r0.setText(r4)
            java.util.List<com.motorola.personalize.app.sound.RingtoneInfo> r4 = r8.mRingtoneList
            int r6 = r8.mHeaderCount
            int r6 = r10 - r6
            java.lang.Object r4 = r4.get(r6)
            com.motorola.personalize.app.sound.RingtoneInfo r4 = (com.motorola.personalize.app.sound.RingtoneInfo) r4
            android.net.Uri r4 = r4.getUri()
            java.lang.String r6 = r4.getPath()
            android.net.Uri r7 = r8.mSelectedUri
            if (r7 == 0) goto L_0x0043
            java.lang.String r3 = r7.getPath()
        L_0x0043:
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L_0x004b
            r8.mSelectedPos = r10
        L_0x004b:
            r0.setChecked(r3)
            android.net.Uri r0 = r8.mPlayingUri
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L_0x005e
            r0 = r9
            com.motorola.personalize.app.sound.BadgeRingtoneAdapter$RadioWithBadgeViewHolder r0 = (com.motorola.personalize.app.sound.BadgeRingtoneAdapter.RadioWithBadgeViewHolder) r0
            com.motorola.personalize.app.sound.CirclePlayer r0 = r0.circlePlayer
            r0.stop()
        L_0x005e:
            boolean r0 = r8.mIsManagedProfile
            if (r0 == 0) goto L_0x0087
            android.media.RingtoneManager r0 = r8.mRingtoneManager
            android.net.Uri r10 = r0.getRingtoneUri(r10)
            int r0 = r8.mPickerUserId
            int r0 = com.motorola.personalize.util.NoneSdkApi.getUserIdFromUri(r10, r0)
            android.net.Uri r10 = com.motorola.personalize.util.NoneSdkApi.getUriWithoutUserId(r10)
            int r3 = r8.mPickerUserId
            if (r0 != r3) goto L_0x0087
            java.lang.String r10 = r10.toString()
            android.net.Uri r0 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            java.lang.String r0 = r0.toString()
            boolean r10 = r10.startsWith(r0)
            if (r10 == 0) goto L_0x0087
            goto L_0x0088
        L_0x0087:
            r1 = r5
        L_0x0088:
            android.view.View r9 = r9.itemView
            r10 = 2131296841(0x7f090249, float:1.821161E38)
            android.view.View r9 = r9.findViewById(r10)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            if (r1 == 0) goto L_0x00a5
            android.app.Activity r10 = r8.mContext
            int r8 = r8.mPickerUserId
            android.graphics.drawable.Drawable r8 = com.motorola.personalize.util.NoneSdkApi.getUserBadgeForDensityNoBackground(r10, r8)
            r9.setImageDrawable(r8)
            r9.setVisibility(r5)
            goto L_0x0121
        L_0x00a5:
            r9.setVisibility(r2)
            goto L_0x0121
        L_0x00aa:
            boolean r0 = r9 instanceof com.motorola.personalize.app.sound.BadgeRingtoneAdapter.DefaultRingtoneViewHolder
            if (r0 == 0) goto L_0x0121
            android.view.View r0 = r9.itemView
            android.view.View r0 = r0.findViewById(r4)
            android.widget.CheckedTextView r0 = (android.widget.CheckedTextView) r0
            boolean r4 = r8.mHasSilentItem
            if (r4 == 0) goto L_0x00e3
            int r4 = r8.mSilentPos
            if (r10 != r4) goto L_0x00e3
            android.app.Activity r4 = r8.mContext
            java.lang.String r3 = com.motorola.personalize.app.sound.SoundUtil.getRingtoneTitle(r4, r3)
            r0.setText(r3)
            android.view.View r9 = r9.itemView
            r3 = 2131296643(0x7f090183, float:1.8211208E38)
            android.view.View r9 = r9.findViewById(r3)
            com.motorola.personalize.app.sound.CirclePlayer r9 = (com.motorola.personalize.app.sound.CirclePlayer) r9
            r9.setVisibility(r2)
            android.net.Uri r9 = r8.mSelectedUri
            if (r9 != 0) goto L_0x00da
            goto L_0x00db
        L_0x00da:
            r1 = r5
        L_0x00db:
            if (r1 == 0) goto L_0x00df
            r8.mSelectedPos = r10
        L_0x00df:
            r0.setChecked(r1)
            return
        L_0x00e3:
            int r1 = r8.mRingtoneType
            r2 = 2
            if (r1 != r2) goto L_0x00ef
            r1 = 2131689649(0x7f0f00b1, float:1.900832E38)
            r0.setText(r1)
            goto L_0x00ff
        L_0x00ef:
            r2 = 4
            if (r1 != r2) goto L_0x00f9
            r1 = 2131689502(0x7f0f001e, float:1.9008021E38)
            r0.setText(r1)
            goto L_0x00ff
        L_0x00f9:
            r1 = 2131689665(0x7f0f00c1, float:1.9008352E38)
            r0.setText(r1)
        L_0x00ff:
            android.net.Uri r1 = r8.mSelectedUri
            boolean r1 = android.media.RingtoneManager.isDefault(r1)
            if (r1 == 0) goto L_0x0109
            r8.mSelectedPos = r10
        L_0x0109:
            r0.setChecked(r1)
            android.media.Ringtone r10 = r8.mDefaultRingtone
            if (r10 == 0) goto L_0x011a
            android.net.Uri r10 = r8.mPlayingUri
            android.net.Uri r8 = r8.mUriForDefaultItem
            boolean r8 = r10.equals(r8)
            if (r8 != 0) goto L_0x0121
        L_0x011a:
            com.motorola.personalize.app.sound.BadgeRingtoneAdapter$DefaultRingtoneViewHolder r9 = (com.motorola.personalize.app.sound.BadgeRingtoneAdapter.DefaultRingtoneViewHolder) r9
            com.motorola.personalize.app.sound.CirclePlayer r8 = r9.circlePlayer
            r8.stop()
        L_0x0121:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.sound.BadgeRingtoneAdapter.onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder, int):void");
    }

    public int getItemCount() {
        return this.mHeaderCount + this.mRingtoneList.size() + this.mFooterCount;
    }

    public class RadioWithBadgeViewHolder extends RecyclerView.ViewHolder {
        public CirclePlayer circlePlayer;

        public RadioWithBadgeViewHolder(View view) {
            super(view);
            this.circlePlayer = (CirclePlayer) view.findViewById(C1057R.C1060id.player);
            view.findViewById(C1057R.C1060id.checked_text_view).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    BadgeRingtoneAdapter.RadioWithBadgeViewHolder.this.lambda$new$0$BadgeRingtoneAdapter$RadioWithBadgeViewHolder(view);
                }
            });
            this.circlePlayer.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    BadgeRingtoneAdapter.RadioWithBadgeViewHolder.this.lambda$new$1$BadgeRingtoneAdapter$RadioWithBadgeViewHolder(view);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$BadgeRingtoneAdapter$RadioWithBadgeViewHolder(View view) {
            if (!Utilities.isDoubleClick()) {
                int adapterPosition = getAdapterPosition();
                BadgeRingtoneAdapter badgeRingtoneAdapter = BadgeRingtoneAdapter.this;
                Uri unused = badgeRingtoneAdapter.mSelectedUri = ((RingtoneInfo) badgeRingtoneAdapter.mRingtoneList.get(adapterPosition - BadgeRingtoneAdapter.this.mHeaderCount)).getUri();
                BadgeRingtoneAdapter.this.preparePlayRingtone(adapterPosition);
                BadgeRingtoneAdapter badgeRingtoneAdapter2 = BadgeRingtoneAdapter.this;
                badgeRingtoneAdapter2.notifyItemChanged(badgeRingtoneAdapter2.mSelectedPos);
                BadgeRingtoneAdapter.this.notifyItemChanged(adapterPosition);
            }
        }

        public /* synthetic */ void lambda$new$1$BadgeRingtoneAdapter$RadioWithBadgeViewHolder(View view) {
            if (!Utilities.isDoubleClick()) {
                int adapterPosition = getAdapterPosition();
                BadgeRingtoneAdapter badgeRingtoneAdapter = BadgeRingtoneAdapter.this;
                Uri unused = badgeRingtoneAdapter.mPlayingUri = ((RingtoneInfo) badgeRingtoneAdapter.mRingtoneList.get(adapterPosition - BadgeRingtoneAdapter.this.mHeaderCount)).getUri();
                BadgeRingtoneAdapter.this.onClickPlayer(adapterPosition);
            }
        }
    }

    public class DefaultRingtoneViewHolder extends RecyclerView.ViewHolder {
        public CirclePlayer circlePlayer;

        public DefaultRingtoneViewHolder(View view) {
            super(view);
            this.circlePlayer = (CirclePlayer) view.findViewById(C1057R.C1060id.player);
            view.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    BadgeRingtoneAdapter.DefaultRingtoneViewHolder.this.lambda$new$0$BadgeRingtoneAdapter$DefaultRingtoneViewHolder(view);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$BadgeRingtoneAdapter$DefaultRingtoneViewHolder(View view) {
            if (!Utilities.isDoubleClick()) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition == BadgeRingtoneAdapter.this.mSilentPos) {
                    Uri unused = BadgeRingtoneAdapter.this.mSelectedUri = null;
                } else {
                    BadgeRingtoneAdapter badgeRingtoneAdapter = BadgeRingtoneAdapter.this;
                    Uri unused2 = badgeRingtoneAdapter.mSelectedUri = badgeRingtoneAdapter.mUriForDefaultItem;
                }
                BadgeRingtoneAdapter.this.preparePlayRingtone(adapterPosition);
                BadgeRingtoneAdapter badgeRingtoneAdapter2 = BadgeRingtoneAdapter.this;
                badgeRingtoneAdapter2.notifyItemChanged(badgeRingtoneAdapter2.mSelectedPos);
                BadgeRingtoneAdapter.this.notifyItemChanged(adapterPosition);
            }
        }
    }

    private class AddNewSoundViewHolder extends RecyclerView.ViewHolder {
        public AddNewSoundViewHolder(View view, int i) {
            super(view);
            TextView textView = (TextView) view.findViewById(C1057R.C1060id.add_new_sound_text);
            if (i == 4) {
                textView.setText(C1057R.string.add_alarm_text);
            } else if (i == 2) {
                textView.setText(C1057R.string.add_notification_text);
            } else {
                textView.setText(C1057R.string.add_ringtone_text);
            }
            view.setOnClickListener(new View.OnClickListener(i) {
                public final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    BadgeRingtoneAdapter.AddNewSoundViewHolder.this.lambda$new$0$BadgeRingtoneAdapter$AddNewSoundViewHolder(this.f$1, view);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$BadgeRingtoneAdapter$AddNewSoundViewHolder(int i, View view) {
            if (i == 4) {
                PersonalizeMetrics.useAddAlarm(BadgeRingtoneAdapter.this.mContext);
            } else if (i == 2) {
                PersonalizeMetrics.useAddNotification(BadgeRingtoneAdapter.this.mContext);
            } else {
                PersonalizeMetrics.useAddRingtone(BadgeRingtoneAdapter.this.mContext);
            }
            BadgeRingtoneAdapter.this.mContext.startActivityForResult(BadgeRingtoneAdapter.this.getMediaFilePickerIntent(), BadgeRingtoneAdapter.ADD_FILE_REQUEST_CODE);
        }
    }
}
