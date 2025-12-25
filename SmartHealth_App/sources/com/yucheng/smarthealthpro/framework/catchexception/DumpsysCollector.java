package com.yucheng.smarthealthpro.framework.catchexception;

/* loaded from: classes4.dex */
final class DumpsysCollector {
    private static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;
    private static final String LOG_TAG = "DumpsysCollector";

    DumpsysCollector() {
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0075: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:25:0x0075 */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String collectMemInfo() throws java.lang.Throwable {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            r2.<init>()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.String r3 = "dumpsys"
            r2.add(r3)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.String r3 = "meminfo"
            r2.add(r3)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            int r3 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            r2.add(r3)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            int r4 = r2.size()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.Object[] r2 = r2.toArray(r4)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.String[] r2 = (java.lang.String[]) r2     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.lang.Process r2 = r3.exec(r2)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            java.io.InputStream r2 = r2.getInputStream()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
            r2 = 8192(0x2000, float:1.148E-41)
            r3.<init>(r4, r2)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5b
        L44:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L74
            if (r1 != 0) goto L4e
            r3.close()     // Catch: java.lang.Exception -> L6b
            goto L6f
        L4e:
            r0.append(r1)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L74
            java.lang.String r1 = "\n"
            r0.append(r1)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L74
            goto L44
        L57:
            r1 = move-exception
            goto L5e
        L59:
            r0 = move-exception
            goto L76
        L5b:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L5e:
            java.lang.String r2 = "DumpsysCollector"
            java.lang.String r4 = "DumosysCollector.meminfo could not retrievedata"
            android.util.Log.e(r2, r4, r1)     // Catch: java.lang.Throwable -> L74
            if (r3 == 0) goto L6f
            r3.close()     // Catch: java.lang.Exception -> L6b
            goto L6f
        L6b:
            r1 = move-exception
            r1.printStackTrace()
        L6f:
            java.lang.String r0 = r0.toString()
            return r0
        L74:
            r0 = move-exception
            r1 = r3
        L76:
            if (r1 == 0) goto L80
            r1.close()     // Catch: java.lang.Exception -> L7c
            goto L80
        L7c:
            r1 = move-exception
            r1.printStackTrace()
        L80:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.framework.catchexception.DumpsysCollector.collectMemInfo():java.lang.String");
    }
}
