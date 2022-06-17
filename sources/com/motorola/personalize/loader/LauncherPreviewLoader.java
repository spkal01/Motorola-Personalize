package com.motorola.personalize.loader;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControlViewHost;
import com.motorola.personalize.view.AnimationSurfaceView;

public class LauncherPreviewLoader {
    private static final Uri GRID_URI = Uri.parse("content://com.motorola.launcher3.grid_control");

    public static SurfaceControlViewHost.SurfacePackage getPreviewSurfacePackage(Context context, Rect rect, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        Log.d(AnimationSurfaceView.TAG, "getPreviewSurfacePackage: name = " + str);
        bundle.putBinder("host_token", new Binder());
        bundle.putInt("width", rect.width());
        bundle.putInt("height", rect.height());
        bundle.putInt("display_id", 0);
        bundle.putBoolean("widget_preview", false);
        Bundle call = context.getContentResolver().call(GRID_URI, "get_preview", "", bundle);
        if (call != null) {
            return call.getParcelable("surface_package");
        }
        return null;
    }
}
