package com.android.launcher3.icons;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class GzipCompression {
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r3 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] compress(java.lang.String r3) {
        /*
            if (r3 == 0) goto L_0x003f
            int r0 = r3.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x003f
        L_0x0009:
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x002e, all -> 0x0029 }
            r1.<init>()     // Catch:{ IOException -> 0x002e, all -> 0x0029 }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x002f, all -> 0x0029 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x002f, all -> 0x0029 }
            java.lang.String r0 = "UTF-8"
            byte[] r3 = r3.getBytes(r0)     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            r2.write(r3)     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            r2.flush()     // Catch:{ IOException -> 0x0027, all -> 0x0024 }
            closeQuietly(r2)
            goto L_0x0032
        L_0x0024:
            r3 = move-exception
            r0 = r2
            goto L_0x002a
        L_0x0027:
            r0 = r2
            goto L_0x002f
        L_0x0029:
            r3 = move-exception
        L_0x002a:
            closeQuietly(r0)
            throw r3
        L_0x002e:
            r1 = r0
        L_0x002f:
            closeQuietly(r0)
        L_0x0032:
            byte[] r3 = r1.toByteArray()     // Catch:{ all -> 0x003a }
            closeQuietly(r1)
            return r3
        L_0x003a:
            r3 = move-exception
            closeQuietly(r1)
            throw r3
        L_0x003f:
            r3 = 0
            byte[] r3 = new byte[r3]
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.GzipCompression.compress(java.lang.String):byte[]");
    }

    public static String decompress(byte[] bArr) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        if (isCompressed(bArr)) {
            GZIPInputStream gZIPInputStream = null;
            try {
                GZIPInputStream gZIPInputStream2 = new GZIPInputStream(new ByteArrayInputStream(bArr));
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream2, "UTF-8"));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        } catch (IOException unused) {
                            gZIPInputStream = gZIPInputStream2;
                            closeQuietly(gZIPInputStream);
                            closeQuietly(bufferedReader);
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            gZIPInputStream = gZIPInputStream2;
                            closeQuietly(gZIPInputStream);
                            closeQuietly(bufferedReader);
                            throw th;
                        }
                    }
                    closeQuietly(gZIPInputStream2);
                } catch (IOException unused2) {
                    bufferedReader = null;
                    gZIPInputStream = gZIPInputStream2;
                    closeQuietly(gZIPInputStream);
                    closeQuietly(bufferedReader);
                    return sb.toString();
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                    gZIPInputStream = gZIPInputStream2;
                    closeQuietly(gZIPInputStream);
                    closeQuietly(bufferedReader);
                    throw th;
                }
            } catch (IOException unused3) {
                bufferedReader = null;
                closeQuietly(gZIPInputStream);
                closeQuietly(bufferedReader);
                return sb.toString();
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                closeQuietly(gZIPInputStream);
                closeQuietly(bufferedReader);
                throw th;
            }
            closeQuietly(bufferedReader);
        } else {
            sb.append(bArr);
        }
        return sb.toString();
    }

    public static boolean isCompressed(byte[] bArr) {
        return bArr[0] == 31 && bArr[1] == -117;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
