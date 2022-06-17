package com.motorola.styles.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public interface OnlineFontService {
    public static final String ONLINE_FONT_TARGET_RRO_PACKAGE = "com.android.theme.font.customizesource";

    public static class OnlineFontServiceHolder {
        /* access modifiers changed from: private */
        public static final OnlineFontService INSTANCE = new OnlineFontServiceImpl();
    }

    public interface OnlineFontUpdateListener {
        void onOnlineFontUpdated();
    }

    String getCurrentOnlineFontValue();

    List<FontInfo> getInstalledFontList();

    List<FontInfo> getInstalledFontList(Comparator<FontInfo> comparator);

    boolean isCurrentOnlineFont();

    boolean isEnabled();

    boolean isLoading();

    void onApplyFont(String str) throws IOException;

    void onCreate(Context context);

    void registerOnlineFontUpdateListener(OnlineFontUpdateListener onlineFontUpdateListener);

    void unregisterOnlineFontUpdateListener(OnlineFontUpdateListener onlineFontUpdateListener);

    static OnlineFontService getInstance() {
        return OnlineFontServiceHolder.INSTANCE;
    }

    public static class FontInfo implements Parcelable {
        public static final Parcelable.Creator<FontInfo> CREATOR = new Parcelable.Creator<FontInfo>() {
            public FontInfo createFromParcel(Parcel parcel) {
                return new FontInfo(parcel);
            }

            public FontInfo[] newArray(int i) {
                return new FontInfo[i];
            }
        };
        public final String checksum;
        public final File file;
        public final String fileName;
        public final long installTime;
        public final String name;
        public final String packageName;
        public final boolean uninstallable;
        public final long updateTime;

        public int describeContents() {
            return 0;
        }

        public static class Builder {
            private String checksum;
            private File file;
            private String fileName;
            private long installTime;
            private String name;
            private String packageName;
            private boolean uninstallable;
            private long updateTime;

            public Builder setPackageName(String str) {
                this.packageName = str;
                return this;
            }

            public Builder setName(String str) {
                this.name = str;
                return this;
            }

            public Builder setChecksum(String str) {
                this.checksum = str;
                return this;
            }

            public Builder setFileName(String str) {
                this.fileName = str;
                return this;
            }

            public Builder setFile(File file2) {
                this.file = file2;
                return this;
            }

            public Builder setUninstallable(boolean z) {
                this.uninstallable = z;
                return this;
            }

            public Builder setInstallTime(long j) {
                this.installTime = j;
                this.updateTime = j;
                return this;
            }

            public Builder setUpdateTime(long j) {
                this.updateTime = j;
                return this;
            }

            public FontInfo build() {
                return new FontInfo(this.packageName, this.name, this.checksum, this.fileName, this.file, this.uninstallable, this.installTime, this.updateTime);
            }
        }

        private FontInfo(String str, String str2, String str3, String str4, File file2, boolean z, long j, long j2) {
            this.packageName = str;
            this.name = str2;
            this.checksum = str3;
            this.fileName = str4;
            this.file = file2;
            this.uninstallable = z;
            this.installTime = j;
            this.updateTime = j2;
        }

        private FontInfo(Parcel parcel) {
            this.packageName = parcel.readString();
            this.name = parcel.readString();
            this.checksum = parcel.readString();
            this.fileName = parcel.readString();
            this.file = (File) parcel.readSerializable();
            this.uninstallable = parcel.readBoolean();
            this.installTime = parcel.readLong();
            this.updateTime = parcel.readLong();
        }

        public Builder copy() {
            return new Builder().setPackageName(this.packageName).setName(this.name).setChecksum(this.checksum).setFileName(this.fileName).setFile(this.file).setUninstallable(this.uninstallable).setInstallTime(this.installTime).setUpdateTime(this.updateTime);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.packageName);
            parcel.writeString(this.name);
            parcel.writeString(this.checksum);
            parcel.writeString(this.fileName);
            parcel.writeSerializable(this.file);
            parcel.writeBoolean(this.uninstallable);
            parcel.writeLong(this.installTime);
            parcel.writeLong(this.updateTime);
        }
    }
}
