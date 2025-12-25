package com.yucheng.smarthealthpro.framework.catchexception;

import android.os.Process;
import android.util.Log;
import com.jieli.jl_rcsp.util.JL_Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class LogcatCollector {
    private static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;
    private static final int DEFAULT_TAIL_COUNT = 100;
    private static final String LOG_TAG = "LogcatCollector";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public String collectLogcat(String str, boolean z, String[] strArr) throws Throwable {
        int iMyPid = Process.myPid();
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2 = null;
        String str2 = (!z || iMyPid <= 0) ? null : Integer.toString(iMyPid) + ":";
        ArrayList arrayList = new ArrayList();
        arrayList.add(JL_Log.f4171b);
        if (str != null) {
            arrayList.add("-b");
            arrayList.add(str);
        }
        ArrayList arrayList2 = new ArrayList(Arrays.asList(strArr));
        int iIndexOf = arrayList2.indexOf("-t");
        final int i2 = -1;
        if (iIndexOf > -1 && iIndexOf < arrayList2.size()) {
            i2 = Integer.parseInt((String) arrayList2.get(iIndexOf + 1));
        }
        LinkedList linkedList = new LinkedList();
        if (i2 <= 0) {
            i2 = 100;
        }
        arrayList.addAll(arrayList2);
        try {
            try {
                final Process processExec = Runtime.getRuntime().exec((String[]) arrayList.toArray(new String[arrayList.size()]));
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                try {
                    Thread thread = new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.framework.catchexception.LogcatCollector.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                do {
                                } while (processExec.getErrorStream().read(new byte[i2]) >= 0);
                            } catch (Exception unused) {
                            }
                        }
                    });
                    thread.start();
                    while (true) {
                        String line = bufferedReader3.readLine();
                        if (line == null) {
                            break;
                        }
                        if (str2 == null || (thread = line.contains(str2)) != 0) {
                            StringBuilder sbAppend = new StringBuilder().append(line);
                            thread = StringUtils.LF;
                            linkedList.add(sbAppend.append(StringUtils.LF).toString());
                        }
                    }
                    bufferedReader3.close();
                    bufferedReader = thread;
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader2 = bufferedReader3;
                    Log.e(LOG_TAG, "LogcatCollector.collectLogcat could not retrieve data.", e);
                    bufferedReader = bufferedReader2;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                        bufferedReader = bufferedReader2;
                    }
                    return linkedList.toString();
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
            return linkedList.toString();
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
