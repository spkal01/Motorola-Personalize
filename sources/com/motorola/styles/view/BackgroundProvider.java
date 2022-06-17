package com.motorola.styles.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.motorola.styles.C1087R;
import com.motorola.styles.LogConfig;
import com.motorola.styles.ResourceBasedOverride;

public class BackgroundProvider implements ResourceBasedOverride {
    private static BackgroundProvider sInstance;
    /* access modifiers changed from: protected */
    public Drawable mBackground;
    protected Context mContext;

    /* access modifiers changed from: protected */
    public void onClear() {
    }

    private static BackgroundProvider newInstance(Context context) {
        return (BackgroundProvider) ResourceBasedOverride.Overrides.getObject(BackgroundProvider.class, context, C1087R.string.styles_background_provider);
    }

    public static void clearInstance() {
        BackgroundProvider backgroundProvider = sInstance;
        sInstance = null;
        if (backgroundProvider != null) {
            backgroundProvider.onClear();
        }
    }

    public static BackgroundProvider getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BackgroundProvider.class) {
                if (sInstance == null && context != null) {
                    sInstance = newInstance(context);
                }
            }
        }
        return sInstance;
    }

    public BackgroundProvider() {
    }

    public BackgroundProvider(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public Drawable getBackground(Context context, int i, int i2) {
        return new ColorDrawable(Color.argb(100, 0, 0, 0));
    }

    public void setBackground(View view) {
        if (LogConfig.DBG) {
            Log.d("Styles", "setBackground: " + this.mBackground);
        }
        if (this.mBackground == null) {
            new Thread(new Runnable(view) {
                public final /* synthetic */ View f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BackgroundProvider.this.lambda$setBackground$1$BackgroundProvider(this.f$1);
                }
            }).start();
        } else {
            view.post(new Runnable(view) {
                public final /* synthetic */ View f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BackgroundProvider.this.lambda$setBackground$2$BackgroundProvider(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setBackground$1$BackgroundProvider(View view) {
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        int min = Math.min(point.x, point.y);
        this.mBackground = getBackground(this.mContext, Math.max(point.x, point.y), min);
        view.post(new Runnable(view) {
            public final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                BackgroundProvider.this.lambda$setBackground$0$BackgroundProvider(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$setBackground$0$BackgroundProvider(View view) {
        view.setBackground(this.mBackground);
    }

    public /* synthetic */ void lambda$setBackground$2$BackgroundProvider(View view) {
        view.setBackground(this.mBackground);
    }
}
