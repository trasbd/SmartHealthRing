package com.yucheng.smarthealthpro.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
public class WriteHandler extends Handler {
    private final String folder;
    private final int maxFileSize;

    public WriteHandler(Looper looper, String folder, int maxFileSize) {
        super(looper);
        this.folder = folder;
        this.maxFileSize = maxFileSize;
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        FileWriter fileWriter;
        String str = (String) msg.obj;
        FileWriter fileWriter2 = null;
        try {
            fileWriter = new FileWriter(getLogFile(this.folder, "logs"), true);
        } catch (IOException unused) {
        }
        try {
            writeLog(fileWriter, str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException unused2) {
            fileWriter2 = fileWriter;
            if (fileWriter2 != null) {
                try {
                    fileWriter2.flush();
                    fileWriter2.close();
                } catch (IOException unused3) {
                }
            }
        }
    }

    private void writeLog(FileWriter fileWriter, String content) throws IOException {
        Intrinsics.checkNotNull(fileWriter);
        Intrinsics.checkNotNull(content);
        fileWriter.append((CharSequence) content);
    }

    private File getLogFile(String folderName, String fileName) {
        Intrinsics.checkNotNull(folderName);
        Intrinsics.checkNotNull(fileName);
        File file = new File(folderName);
        if (!file.exists()) {
            file.mkdirs();
        }
        int i2 = 0;
        File file2 = new File(file, String.format("%s_%s.csv", fileName, 0));
        File file3 = null;
        while (file2.exists()) {
            i2++;
            file3 = file2;
            file2 = new File(file, String.format("%s_%s.csv", fileName, Integer.valueOf(i2)));
        }
        return (file3 == null || file3.length() >= ((long) this.maxFileSize)) ? file2 : file3;
    }
}
