package com.motorola.styles;

import android.content.Context;
import android.os.SystemProperties;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.motorola.styles.model.Option;
import com.motorola.styles.model.Theme;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StylesUtilities {
    public static final boolean PRC_BUILD = SystemProperties.getBoolean("ro.product.is_prc", false);

    public static String md5Checksum(ByteBuffer byteBuffer) throws IOException {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(byteBuffer);
            return convertBytesToHexString(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IOException("Cannot check the md5 of the contents");
        }
    }

    private static String convertBytesToHexString(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr[i2] = Character.forDigit((b >> 4) & 15, 16);
            cArr[i2 + 1] = Character.forDigit(b & 15, 16);
        }
        return new String(cArr);
    }

    public static <T extends Option> void sortOrder(Context context, List<T> list, int i) {
        Collections.sort(list, new Comparator(Arrays.asList(context.getResources().getStringArray(i))) {
            public final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final int compare(Object obj, Object obj2) {
                return StylesUtilities.lambda$sortOrder$0(this.f$0, (Option) obj, (Option) obj2);
            }
        });
    }

    static /* synthetic */ int lambda$sortOrder$0(List list, Option option, Option option2) {
        int indexOf = list.indexOf(option.getValue());
        if (indexOf < 0) {
            indexOf = list.size();
        }
        int indexOf2 = list.indexOf(option2.getValue());
        if (indexOf2 < 0) {
            indexOf2 = list.size();
        }
        return indexOf - indexOf2;
    }

    public static boolean isXMLocale(Context context) {
        return context.getResources().getConfiguration().locale.getCountry().equals("XM");
    }

    public static String removeAllBidiClass(String str) {
        Matcher matcher = Pattern.compile("[\\u200b-\\u200f]|[\\u200e-\\u200f]|[\\u202a-\\u202e]|[\\u2066-\\u2069]").matcher(str);
        return matcher.find() ? matcher.replaceAll("") : str;
    }

    public static String getStyleName(Context context, Theme theme) {
        if (theme.isDefault()) {
            return context.getString(C1087R.string.default_theme_name);
        }
        if (theme.isPreload()) {
            return context.getResources().getStringArray(C1087R.array.preload_themes)[theme.getPreloadIndex()];
        }
        return theme.getName();
    }

    public static <T> void observeOnce(final LiveData<T> liveData, LifecycleOwner lifecycleOwner, final Observer<? super T> observer) {
        liveData.observe(lifecycleOwner, new Observer<T>() {
            public void onChanged(T t) {
                Observer.this.onChanged(t);
                liveData.removeObserver(this);
            }
        });
    }
}
