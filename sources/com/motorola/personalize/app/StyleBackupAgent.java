package com.motorola.personalize.app;

import android.app.backup.BackupAgent;

public class StyleBackupAgent extends BackupAgent {
    public static final String APPLIEDSTYLE = "com.motorola.personalize.applied";
    public static final String CUSTOMSTYLELIST = "com.motorola.personalize.custom";
    public static final String TAG = "StyleBackupAgent";

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b3 A[SYNTHETIC, Splitter:B:42:0x00b3] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bb A[Catch:{ IOException -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c6 A[SYNTHETIC, Splitter:B:51:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ce A[Catch:{ IOException -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBackup(android.os.ParcelFileDescriptor r5, android.app.backup.BackupDataOutput r6, android.os.ParcelFileDescriptor r7) {
        /*
            r4 = this;
            java.lang.String r5 = "onBackup"
            r7 = 0
            android.content.ContentResolver r0 = r4.getContentResolver()     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.String r1 = "themeList"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r1)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.String r1 = "appliedTheme"
            java.lang.String r4 = android.provider.Settings.Secure.getString(r4, r1)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.String r1 = TAG     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            r2.<init>()     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            android.util.Log.d(r1, r2)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            r2.<init>()     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.StringBuilder r5 = r2.append(r5)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.StringBuilder r5 = r5.append(r4)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            android.util.Log.d(r1, r5)     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            r5.<init>()     // Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x00a7 }
            r1.<init>(r5)     // Catch:{ IOException -> 0x00a7 }
            boolean r7 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            if (r7 == 0) goto L_0x0053
            java.lang.String r0 = ""
        L_0x0053:
            r1.writeUTF(r0)     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            byte[] r7 = r5.toByteArray()     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            int r0 = r7.length     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            java.lang.String r2 = "com.motorola.personalize.custom"
            r6.writeEntityHeader(r2, r0)     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            r6.writeEntityData(r7, r0)     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            boolean r7 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            if (r7 != 0) goto L_0x0096
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            r7.<init>()     // Catch:{ IOException -> 0x00a4, all -> 0x00a1 }
            java.io.DataOutputStream r5 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            r5.<init>(r7)     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            r5.writeUTF(r4)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            byte[] r4 = r7.toByteArray()     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            int r0 = r4.length     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            java.lang.String r1 = "com.motorola.personalize.applied"
            r6.writeEntityHeader(r1, r0)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            r6.writeEntityData(r4, r0)     // Catch:{ IOException -> 0x008b, all -> 0x0086 }
            r1 = r5
            r5 = r7
            goto L_0x0096
        L_0x0086:
            r4 = move-exception
            r3 = r7
            r7 = r5
            r5 = r3
            goto L_0x00c4
        L_0x008b:
            r4 = move-exception
            r3 = r7
            r7 = r5
            r5 = r3
            goto L_0x00ae
        L_0x0090:
            r4 = move-exception
            r5 = r7
            goto L_0x00a2
        L_0x0093:
            r4 = move-exception
            r5 = r7
            goto L_0x00a5
        L_0x0096:
            if (r1 == 0) goto L_0x009b
            r1.close()     // Catch:{ IOException -> 0x00b7 }
        L_0x009b:
            if (r5 == 0) goto L_0x00c2
            r5.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00c2
        L_0x00a1:
            r4 = move-exception
        L_0x00a2:
            r7 = r1
            goto L_0x00c4
        L_0x00a4:
            r4 = move-exception
        L_0x00a5:
            r7 = r1
            goto L_0x00ae
        L_0x00a7:
            r4 = move-exception
            goto L_0x00ae
        L_0x00a9:
            r4 = move-exception
            r5 = r7
            goto L_0x00c4
        L_0x00ac:
            r4 = move-exception
            r5 = r7
        L_0x00ae:
            r4.printStackTrace()     // Catch:{ all -> 0x00c3 }
            if (r7 == 0) goto L_0x00b9
            r7.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00b9
        L_0x00b7:
            r4 = move-exception
            goto L_0x00bf
        L_0x00b9:
            if (r5 == 0) goto L_0x00c2
            r5.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00c2
        L_0x00bf:
            r4.printStackTrace()
        L_0x00c2:
            return
        L_0x00c3:
            r4 = move-exception
        L_0x00c4:
            if (r7 == 0) goto L_0x00cc
            r7.close()     // Catch:{ IOException -> 0x00ca }
            goto L_0x00cc
        L_0x00ca:
            r5 = move-exception
            goto L_0x00d2
        L_0x00cc:
            if (r5 == 0) goto L_0x00d5
            r5.close()     // Catch:{ IOException -> 0x00ca }
            goto L_0x00d5
        L_0x00d2:
            r5.printStackTrace()
        L_0x00d5:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.StyleBackupAgent.onBackup(android.os.ParcelFileDescriptor, android.app.backup.BackupDataOutput, android.os.ParcelFileDescriptor):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c5 A[SYNTHETIC, Splitter:B:47:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ca A[Catch:{ IOException -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d0 A[SYNTHETIC, Splitter:B:52:0x00d0] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d8 A[Catch:{ IOException -> 0x00d4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onRestore(android.app.backup.BackupDataInput r5, int r6, android.os.ParcelFileDescriptor r7) {
        /*
            r4 = this;
            r6 = 0
            r7 = r6
        L_0x0002:
            boolean r0 = r5.readNextHeader()     // Catch:{ IOException -> 0x00bf }
            if (r0 == 0) goto L_0x00ab
            java.lang.String r0 = r5.getKey()     // Catch:{ IOException -> 0x00bf }
            java.lang.String r1 = "com.motorola.personalize.custom"
            boolean r0 = r0.equals(r1)     // Catch:{ IOException -> 0x00bf }
            r1 = 0
            if (r0 == 0) goto L_0x005f
            int r0 = r5.getDataSize()     // Catch:{ IOException -> 0x00bf }
            byte[] r2 = new byte[r0]     // Catch:{ IOException -> 0x00bf }
            r5.readEntityData(r2, r1, r0)     // Catch:{ IOException -> 0x00bf }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x00bf }
            r0.<init>(r2)     // Catch:{ IOException -> 0x00bf }
            java.io.DataInputStream r6 = new java.io.DataInputStream     // Catch:{ IOException -> 0x005c, all -> 0x0058 }
            r6.<init>(r0)     // Catch:{ IOException -> 0x005c, all -> 0x0058 }
            java.lang.String r7 = r6.readUTF()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r1 = TAG     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r2.<init>()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r3 = "onRestore_custom:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            android.util.Log.d(r1, r2)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            if (r7 == 0) goto L_0x004f
            android.content.ContentResolver r1 = r4.getContentResolver()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r2 = "themeList"
            android.provider.Settings.Secure.putString(r1, r2, r7)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
        L_0x004f:
            r7 = r6
            r6 = r0
            goto L_0x0002
        L_0x0052:
            r4 = move-exception
            r7 = r6
            goto L_0x0059
        L_0x0055:
            r4 = move-exception
            r7 = r6
            goto L_0x005d
        L_0x0058:
            r4 = move-exception
        L_0x0059:
            r6 = r0
            goto L_0x00ce
        L_0x005c:
            r4 = move-exception
        L_0x005d:
            r6 = r0
            goto L_0x00c0
        L_0x005f:
            java.lang.String r0 = r5.getKey()     // Catch:{ IOException -> 0x00bf }
            java.lang.String r2 = "com.motorola.personalize.applied"
            boolean r0 = r0.equals(r2)     // Catch:{ IOException -> 0x00bf }
            if (r0 == 0) goto L_0x00a6
            int r0 = r5.getDataSize()     // Catch:{ IOException -> 0x00bf }
            byte[] r2 = new byte[r0]     // Catch:{ IOException -> 0x00bf }
            r5.readEntityData(r2, r1, r0)     // Catch:{ IOException -> 0x00bf }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x00bf }
            r0.<init>(r2)     // Catch:{ IOException -> 0x00bf }
            java.io.DataInputStream r6 = new java.io.DataInputStream     // Catch:{ IOException -> 0x005c, all -> 0x0058 }
            r6.<init>(r0)     // Catch:{ IOException -> 0x005c, all -> 0x0058 }
            java.lang.String r7 = r6.readUTF()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r1 = TAG     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            r2.<init>()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r3 = "onRestore_applied:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            android.util.Log.d(r1, r2)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            if (r7 == 0) goto L_0x004f
            android.content.ContentResolver r1 = r4.getContentResolver()     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r2 = "appliedTheme"
            android.provider.Settings.Secure.putString(r1, r2, r7)     // Catch:{ IOException -> 0x0055, all -> 0x0052 }
            goto L_0x004f
        L_0x00a6:
            r5.skipEntityData()     // Catch:{ IOException -> 0x00bf }
            goto L_0x0002
        L_0x00ab:
            if (r6 == 0) goto L_0x00b3
            r6.close()     // Catch:{ IOException -> 0x00b1 }
            goto L_0x00b3
        L_0x00b1:
            r4 = move-exception
            goto L_0x00b9
        L_0x00b3:
            if (r7 == 0) goto L_0x00cd
            r7.close()     // Catch:{ IOException -> 0x00b1 }
            goto L_0x00cd
        L_0x00b9:
            r4.printStackTrace()
            goto L_0x00cd
        L_0x00bd:
            r4 = move-exception
            goto L_0x00ce
        L_0x00bf:
            r4 = move-exception
        L_0x00c0:
            r4.printStackTrace()     // Catch:{ all -> 0x00bd }
            if (r6 == 0) goto L_0x00c8
            r6.close()     // Catch:{ IOException -> 0x00b1 }
        L_0x00c8:
            if (r7 == 0) goto L_0x00cd
            r7.close()     // Catch:{ IOException -> 0x00b1 }
        L_0x00cd:
            return
        L_0x00ce:
            if (r6 == 0) goto L_0x00d6
            r6.close()     // Catch:{ IOException -> 0x00d4 }
            goto L_0x00d6
        L_0x00d4:
            r5 = move-exception
            goto L_0x00dc
        L_0x00d6:
            if (r7 == 0) goto L_0x00df
            r7.close()     // Catch:{ IOException -> 0x00d4 }
            goto L_0x00df
        L_0x00dc:
            r5.printStackTrace()
        L_0x00df:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.motorola.personalize.app.StyleBackupAgent.onRestore(android.app.backup.BackupDataInput, int, android.os.ParcelFileDescriptor):void");
    }
}
