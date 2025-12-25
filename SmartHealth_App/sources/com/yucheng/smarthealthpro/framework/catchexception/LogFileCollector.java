package com.yucheng.smarthealthpro.framework.catchexception;

import android.content.Context;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/* loaded from: classes4.dex */
class LogFileCollector {
    LogFileCollector() {
    }

    public String collectLogFile(Context context, String str, int i2) throws IOException {
        LinkedList linkedList = new LinkedList();
        BufferedReader reader = getReader(context, str);
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                linkedList.add(line + "\\n");
            }
            return linkedList.toString();
        } finally {
            try {
                reader.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static BufferedReader getReader(Context context, String str) throws FileNotFoundException {
        FileInputStream fileInputStreamOpenFileInput;
        try {
            if (str.startsWith("/")) {
                fileInputStreamOpenFileInput = new FileInputStream(str);
            } else if (str.contains("/")) {
                fileInputStreamOpenFileInput = new FileInputStream(new File(context.getFilesDir(), str));
            } else {
                fileInputStreamOpenFileInput = context.openFileInput(str);
            }
            return new BufferedReader(new InputStreamReader(fileInputStreamOpenFileInput), 1024);
        } catch (Exception unused) {
            return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(new byte[0])));
        }
    }
}
