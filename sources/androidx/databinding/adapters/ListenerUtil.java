package androidx.databinding.adapters;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ListenerUtil {
    private static final SparseArray<WeakHashMap<View, WeakReference<?>>> sListeners = new SparseArray<>();

    public static <T> T trackListener(View view, T t, int i) {
        WeakReference weakReference;
        if (Build.VERSION.SDK_INT >= 14) {
            T tag = view.getTag(i);
            view.setTag(i, t);
            return tag;
        }
        SparseArray<WeakHashMap<View, WeakReference<?>>> sparseArray = sListeners;
        synchronized (sparseArray) {
            WeakHashMap weakHashMap = sparseArray.get(i);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap();
                sparseArray.put(i, weakHashMap);
            }
            if (t == null) {
                weakReference = (WeakReference) weakHashMap.remove(view);
            } else {
                weakReference = (WeakReference) weakHashMap.put(view, new WeakReference(t));
            }
            if (weakReference == null) {
                return null;
            }
            T t2 = weakReference.get();
            return t2;
        }
    }

    public static <T> T getListener(View view, int i) {
        if (Build.VERSION.SDK_INT >= 14) {
            return view.getTag(i);
        }
        SparseArray<WeakHashMap<View, WeakReference<?>>> sparseArray = sListeners;
        synchronized (sparseArray) {
            WeakHashMap weakHashMap = sparseArray.get(i);
            if (weakHashMap == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) weakHashMap.get(view);
            if (weakReference == null) {
                return null;
            }
            T t = weakReference.get();
            return t;
        }
    }
}
