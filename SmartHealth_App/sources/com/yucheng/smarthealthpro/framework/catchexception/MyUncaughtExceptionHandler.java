package com.yucheng.smarthealthpro.framework.catchexception;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* loaded from: classes4.dex */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Log.d("UncaughtExceptionHandler", "uncaughtException: " + th);
        while (th != null) {
            th.printStackTrace(printWriter);
            th = th.getCause();
        }
        String str = stringWriter.toString() + ThreadCollector.collect(thread);
        printWriter.close();
    }
}
