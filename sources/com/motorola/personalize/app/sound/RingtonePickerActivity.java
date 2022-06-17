package com.motorola.personalize.app.sound;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.motorola.personalize.C1057R;
import com.motorola.personalize.analysis.PersonalizeMetrics;
import com.motorola.personalize.app.sound.BadgeRingtoneAdapter;
import com.motorola.personalize.extensions.OrientationExtensionsKt;
import com.motorola.personalize.extensions.WindowExtKt;
import com.motorola.personalize.util.NoneSdkApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RingtonePickerActivity extends AppCompatActivity implements BadgeRingtoneAdapter.OnRingtoneSelectedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final int ADD_FILE_REQUEST_CODE = 300;
    private static final String COLUMN_LABEL = "title";
    private static final int DELAY_MS_SELECTION_PLAYED = 300;
    private static final int POS_UNKNOWN = -1;
    private static final String SAVE_CLICKED_POS = "clicked_pos";
    private static final String SOUND_NAME_RES_PREFIX = "sound_name_";
    private static final String TAG = "RingtonePickerActivity";
    private Uri currentSelectRingtoneUri;
    /* access modifiers changed from: private */
    public boolean isPlaying = false;
    private boolean isRingtoneSelect = false;
    private BadgeRingtoneAdapter mAdapter;
    private int mAttributesFlags;
    /* access modifiers changed from: private */
    public int mCurrentPosition = -1;
    private Cursor mCursor;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private int mLastPosition = -1;
    private int mPickerUserId;
    /* access modifiers changed from: private */
    public MediaPlayer mPlayer;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    private Handler mRefreshPlayerHandler;
    /* access modifiers changed from: private */
    public RingtoneManager mRingtoneManager;
    private final Runnable mStopPlaying = new Runnable() {
        public final void run() {
            RingtonePickerActivity.this.lambda$new$0$RingtonePickerActivity();
        }
    };
    private Context mTargetContext;
    /* access modifiers changed from: private */
    public int mType;
    /* access modifiers changed from: private */
    public final Runnable mUpdatePlaying = new Runnable() {
        public void run() {
            Log.d(RingtonePickerActivity.TAG, "mUpdatePlaying : " + RingtonePickerActivity.this.mCurrentPosition);
            if (!RingtonePickerActivity.this.isPlaying) {
                Log.w(RingtonePickerActivity.TAG, "onPlayProgressUpdate return, isPlaying == false");
                return;
            }
            int access$000 = RingtonePickerActivity.this.mCurrentPosition;
            if (access$000 == -1) {
                Log.w(RingtonePickerActivity.TAG, "onPlayProgressUpdate return, position == -1");
                return;
            }
            int findFirstVisibleItemPosition = ((LinearLayoutManager) RingtonePickerActivity.this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = ((LinearLayoutManager) RingtonePickerActivity.this.mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
            if (access$000 >= findFirstVisibleItemPosition && access$000 <= findLastVisibleItemPosition) {
                View childAt = RingtonePickerActivity.this.mRecyclerView.getChildAt(access$000 - findFirstVisibleItemPosition);
                if (childAt == null) {
                    Log.w(RingtonePickerActivity.TAG, "onPlayProgressUpdate return, child == null");
                    return;
                }
                RecyclerView.ViewHolder childViewHolder = RingtonePickerActivity.this.mRecyclerView.getChildViewHolder(childAt);
                if (childViewHolder != null && (childViewHolder instanceof BadgeRingtoneAdapter.RadioWithBadgeViewHolder)) {
                    BadgeRingtoneAdapter.RadioWithBadgeViewHolder radioWithBadgeViewHolder = (BadgeRingtoneAdapter.RadioWithBadgeViewHolder) childViewHolder;
                    if (radioWithBadgeViewHolder == null) {
                        Log.w(RingtonePickerActivity.TAG, "onPlayProgressUpdate return, holder == null");
                        return;
                    }
                    if (RingtonePickerActivity.this.mPlayer == null) {
                        Log.w(RingtonePickerActivity.TAG, "onPlayProgressUpdate return, mPlayer == null");
                    }
                    if (!radioWithBadgeViewHolder.circlePlayer.isPlaying()) {
                        radioWithBadgeViewHolder.circlePlayer.play(RingtonePickerActivity.this.mPlayer.getDuration());
                    }
                    radioWithBadgeViewHolder.circlePlayer.setProgress((float) RingtonePickerActivity.this.mPlayer.getCurrentPosition());
                }
            }
            if (RingtonePickerActivity.this.mPlayer != null && RingtonePickerActivity.this.mPlayer.isPlaying()) {
                RingtonePickerActivity.this.mHandler.postDelayed(RingtonePickerActivity.this.mUpdatePlaying, 150);
            }
        }
    };
    private Uri mUriForDefaultItem;

    public /* synthetic */ void lambda$new$0$RingtonePickerActivity() {
        Log.d(TAG, "mStopPlaying : " + this.mLastPosition);
        int i = this.mLastPosition;
        if (i == -1) {
            Log.w(TAG, "onPlayProgressUpdate return, position == -1");
            return;
        }
        int findFirstVisibleItemPosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
        if (i >= findFirstVisibleItemPosition && i <= findLastVisibleItemPosition) {
            View childAt = this.mRecyclerView.getChildAt(i - findFirstVisibleItemPosition);
            if (childAt == null) {
                Log.w(TAG, "onPlayProgressUpdate return, child == null");
                return;
            }
            BadgeRingtoneAdapter.RadioWithBadgeViewHolder radioWithBadgeViewHolder = (BadgeRingtoneAdapter.RadioWithBadgeViewHolder) this.mRecyclerView.getChildViewHolder(childAt);
            if (radioWithBadgeViewHolder == null) {
                Log.w(TAG, "onPlayProgressUpdate return, holder == null");
                return;
            }
            if (this.mPlayer == null) {
                Log.w(TAG, "onPlayProgressUpdate return, mPlayer == null");
            }
            radioWithBadgeViewHolder.circlePlayer.stop();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1057R.layout.activity_sound_picker);
        WindowExtKt.customFlags(getWindow());
        Intent intent = getIntent();
        this.mType = intent.getIntExtra("ringtone_type", 1);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(C1057R.C1060id.toolbar_container);
        constraintLayout.setBackgroundColor(getResources().getColor(C1057R.C1058color.tool_bar_color, getTheme()));
        OrientationExtensionsKt.applyViewPaddingInset(constraintLayout, true, true, true, false);
        OrientationExtensionsKt.applyViewPaddingInsetListener((RecyclerView) findViewById(C1057R.C1060id.ringtone_container), true, true, false, false);
        TextView textView = (TextView) constraintLayout.findViewById(C1057R.C1060id.toolbar_title);
        int i = this.mType;
        if (i == 2) {
            textView.setText(C1057R.string.sound_title_notifications);
            this.mUriForDefaultItem = Settings.System.DEFAULT_NOTIFICATION_URI;
        } else if (i == 4) {
            textView.setText(C1057R.string.sound_title_alarms);
            this.mUriForDefaultItem = Settings.System.DEFAULT_ALARM_ALERT_URI;
        } else if (i != 64) {
            if (SoundUtil.isDoubleSIM()) {
                textView.setText(C1057R.string.sound_title_ringtones_sim_1);
            } else {
                textView.setText(C1057R.string.sound_title_ringtones);
            }
            this.mUriForDefaultItem = Settings.System.DEFAULT_RINGTONE_URI;
        } else {
            textView.setText(C1057R.string.sound_title_ringtones_sim_2);
            this.mUriForDefaultItem = Settings.System.DEFAULT_RINGTONE_URI;
        }
        findViewById(C1057R.C1060id.back_icon).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RingtonePickerActivity.this.lambda$onCreate$1$RingtonePickerActivity(view);
            }
        });
        findViewById(C1057R.C1060id.fab).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RingtonePickerActivity.this.lambda$onCreate$2$RingtonePickerActivity(view);
            }
        });
        this.mHandler = new Handler();
        this.mRefreshPlayerHandler = new Handler();
        this.mPickerUserId = NoneSdkApi.myUserId();
        this.mTargetContext = this;
        initRingtoneManager();
        this.mAttributesFlags = intent.getIntExtra(NoneSdkApi.EXTRA_RINGTONE_AUDIO_ATTRIBUTES_FLAGS, 64) | this.mAttributesFlags;
        Log.d(TAG, "onCreate: mAttributesFlags = " + this.mAttributesFlags);
        setVolumeControlStream(this.mRingtoneManager.inferStreamType());
        initView(bundle);
    }

    public /* synthetic */ void lambda$onCreate$1$RingtonePickerActivity(View view) {
        onBackPressed();
    }

    public /* synthetic */ void lambda$onCreate$2$RingtonePickerActivity(View view) {
        onFabClick();
    }

    private void onFabClick() {
        if (this.isRingtoneSelect) {
            PersonalizeMetrics.setSound(this, this.mType, this.currentSelectRingtoneUri);
            RingtoneManager.setActualDefaultRingtoneUri(this, this.mType, this.currentSelectRingtoneUri);
            Intent intent = new Intent();
            intent.putExtra("ringtone_type", this.mType);
            intent.putExtra("android.intent.extra.ringtone.PICKED_URI", this.currentSelectRingtoneUri);
            setResult(-1, intent);
        }
        finish();
    }

    private void initRingtoneManager() {
        RingtoneManager ringtoneManager = new RingtoneManager(this.mTargetContext);
        this.mRingtoneManager = ringtoneManager;
        int i = this.mType;
        if (i != -1) {
            ringtoneManager.setType(i);
        }
        this.mCursor = this.mRingtoneManager.getCursor();
    }

    private List<RingtoneInfo> initRingtones() {
        ArrayList arrayList = new ArrayList();
        while (this.mCursor.moveToNext()) {
            Cursor cursor = this.mCursor;
            String string = cursor.getString(cursor.getColumnIndex(COLUMN_LABEL));
            Uri ringtoneUri = this.mRingtoneManager.getRingtoneUri(this.mCursor.getPosition());
            Log.d(TAG, "initRingtones: " + string + " - " + ringtoneUri.toString());
            arrayList.add(new RingtoneInfo(string, ringtoneUri));
        }
        return arrayList;
    }

    private void initView(Bundle bundle) {
        boolean booleanExtra = getIntent().getBooleanExtra("android.intent.extra.ringtone.SHOW_DEFAULT", false);
        boolean booleanExtra2 = getIntent().getBooleanExtra("android.intent.extra.ringtone.SHOW_SILENT", true);
        Uri uri = (Uri) getIntent().getParcelableExtra("android.intent.extra.ringtone.EXISTING_URI");
        if (uri != null) {
            Log.d(TAG, "initView: existingUri = " + uri.toString());
        } else {
            Log.d(TAG, "initView: existingUri = null");
        }
        BadgeRingtoneAdapter badgeRingtoneAdapter = new BadgeRingtoneAdapter(this, NoneSdkApi.isManagedProfile(this, this.mPickerUserId), this.mPickerUserId, this.mRingtoneManager);
        this.mAdapter = badgeRingtoneAdapter;
        badgeRingtoneAdapter.setRingtoneList(initRingtones()).setRingtoneType(this.mType).setRingtoneSelectedListener(this).prepareAdapter(uri, this.mUriForDefaultItem, this.mAttributesFlags).prepareList(booleanExtra2, booleanExtra);
        RecyclerView recyclerView = (RecyclerView) findViewById(C1057R.C1060id.ringtone_container);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        this.mRecyclerView.setAdapter(this.mAdapter);
        registerForContextMenu(this.mRecyclerView);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 300 && i2 == -1) {
            new AsyncTask<Uri, Void, Uri>() {
                /* access modifiers changed from: protected */
                public Uri doInBackground(Uri... uriArr) {
                    return NoneSdkApi.addCustomExternalRingtone(RingtonePickerActivity.this.mRingtoneManager, uriArr[0], RingtonePickerActivity.this.mType);
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Uri uri) {
                    if (uri != null) {
                        RingtonePickerActivity.this.successAddSound();
                        RingtonePickerActivity.this.requeryForAdapter();
                        return;
                    }
                    Toast.makeText(RingtonePickerActivity.this, C1057R.string.unable_to_add_ringtone, 0).show();
                }
            }.execute(new Uri[]{intent.getData()});
            return;
        }
        requeryForAdapter();
    }

    /* access modifiers changed from: private */
    public void successAddSound() {
        int i = this.mType;
        if (i == 4) {
            PersonalizeMetrics.successAddAlarm(this.mTargetContext);
        } else if (i == 2) {
            PersonalizeMetrics.successAddNotification(this.mTargetContext);
        } else {
            PersonalizeMetrics.successAddRingtone(this.mTargetContext);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        Handler handler2 = this.mRefreshPlayerHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        finishToSet();
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            cursor.close();
            this.mCursor = null;
        }
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void requeryForAdapter() {
        initRingtoneManager();
        this.mAdapter.refreshAdapter(initRingtones(), this.mRingtoneManager);
    }

    public void finishToSet() {
        this.mRingtoneManager.stopPreviousRingtone();
    }

    public void onSelected(Uri uri, Ringtone ringtone, int i) {
        if (uri != null) {
            Log.d(TAG, "onSelected: " + uri.toString());
        }
        Log.d(TAG, "onSelected: position = " + i);
        setSuccessResultWithRingtone(uri);
    }

    public void onClickPlayer(Uri uri, Ringtone ringtone, int i) {
        if (uri != null) {
            Log.d(TAG, "onClickPlayer: " + uri.toString());
        }
        Log.d(TAG, "onClickPlayer: position = " + i);
        Log.d(TAG, "onClickPlayer: mCurrentPosition = " + this.mCurrentPosition);
        if (this.mCurrentPosition != -1) {
            Log.d(TAG, "onClickPlayer: stop last " + this.mCurrentPosition);
            this.mLastPosition = this.mCurrentPosition;
            this.mRefreshPlayerHandler.post(this.mStopPlaying);
            stopPlayback();
        }
        if (this.mCurrentPosition != i) {
            this.mCurrentPosition = i;
            Log.d(TAG, "onClickPlayer: start new " + this.mCurrentPosition);
            startPlayback(uri, ringtone);
            return;
        }
        this.mCurrentPosition = -1;
        Log.d(TAG, "onClickPlayer: clear " + this.mCurrentPosition);
    }

    private void startPlayback(Uri uri, Ringtone ringtone) {
        if (uri != null) {
            try {
                this.isPlaying = true;
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.mPlayer = mediaPlayer;
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public final void onCompletion(MediaPlayer mediaPlayer) {
                        RingtonePickerActivity.this.onCompletion(mediaPlayer);
                    }
                });
                this.mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        return RingtonePickerActivity.this.onError(mediaPlayer, i, i2);
                    }
                });
                this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public final void onPrepared(MediaPlayer mediaPlayer) {
                        RingtonePickerActivity.this.onPrepared(mediaPlayer);
                    }
                });
                this.mPlayer.setDataSource(this, uri);
                this.mPlayer.setAudioAttributes(ringtone.getAudioAttributes());
                this.mPlayer.prepareAsync();
                Log.d(TAG, "startPlayback: ");
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                e.printStackTrace();
                releasePlayer();
            }
        }
    }

    private void releasePlayer() {
        this.isPlaying = false;
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mPlayer = null;
        }
    }

    private void stopPlayback() {
        this.isPlaying = false;
        try {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            this.mPlayer.stop();
            this.mPlayer.release();
            this.mPlayer = null;
        } catch (Exception unused) {
            releasePlayer();
        }
    }

    private void updatePlayProgress() {
        this.mHandler.removeCallbacks(this.mUpdatePlaying);
        this.mHandler.post(this.mUpdatePlaying);
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        releasePlayer();
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        try {
            mediaPlayer.start();
            updatePlayProgress();
        } catch (IllegalStateException unused) {
            releasePlayer();
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.mLastPosition = this.mCurrentPosition;
        this.mRefreshPlayerHandler.post(this.mStopPlaying);
        this.mCurrentPosition = -1;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        stopPlayback();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        stopPlayback();
    }

    private void setSuccessResultWithRingtone(Uri uri) {
        Log.i(TAG, "setSuccessResultWithRingtone : " + uri);
        this.currentSelectRingtoneUri = uri;
        this.isRingtoneSelect = true;
    }
}
