package com.yucheng.ycbtsdk.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.HTTP;

/* loaded from: classes5.dex */
public class LogToFileUtils {
    private static final int LOG_MAX_SIZE = 10485760;
    private static final String MY_TAG = "LogToFileUtils";
    private static LogToFileUtils instance;
    private static File logFile;
    private static Context mContext;
    private static String tag;
    private static SimpleDateFormat logSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void clearLog() throws IOException {
        File file;
        if (mContext == null || instance == null || (file = logFile) == null || !file.exists()) {
            Log.e(MY_TAG, "Initialization failure !!!");
            return;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, false));
            bufferedWriter.write("");
            bufferedWriter.flush();
        } catch (Exception e2) {
            Log.e(tag, "Write failure !!! " + e2.toString());
        }
    }

    public static String currentDayDetail() {
        return formatter.format(new Date());
    }

    public static boolean deleteLogFile(String str) {
        File file = Environment.getExternalStorageState().equals("mounted") ? new File(mContext.getExternalFilesDir("YCLog").getPath() + "/") : new File(mContext.getFilesDir().getPath() + "/YCLog/");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file.getPath() + "/" + str);
        if (file2.exists()) {
            return file2.delete();
        }
        return false;
    }

    public static long getFileSize(File file) {
        if (file.exists()) {
            try {
                return new FileInputStream(file).available();
            } catch (Exception e2) {
                Log.e(MY_TAG, e2.toString());
            }
        }
        return 0L;
    }

    private static String getFunctionInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(instance.getClass().getName())) {
                tag = stackTraceElement.getFileName();
                return "[" + logSDF.format(new Date()) + "]";
            }
        }
        return null;
    }

    public static File getLogFile(String str) throws IOException {
        File file = Environment.getExternalStorageState().equals("mounted") ? new File(mContext.getExternalFilesDir("YCLog").getPath() + "/") : new File(mContext.getFilesDir().getPath() + "/YCLog/");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file.getPath() + "/" + str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception e2) {
                Log.e(MY_TAG, "Create log file failure !!! " + e2.toString());
            }
        }
        return file2;
    }

    public static String getSDPath(Context context) {
        return (Environment.getExternalStorageState().equals("mounted") ? Build.VERSION.SDK_INT >= 29 ? context.getExternalFilesDir(null) : Environment.getExternalStorageDirectory() : Environment.getRootDirectory()).toString();
    }

    public static String getYCFilePath() {
        String sDPath = getSDPath(mContext);
        File file = sDPath == null ? Environment.getExternalStorageState().equals("mounted") ? new File(mContext.getExternalFilesDir("YCLog").getPath() + "/") : new File(mContext.getFilesDir().getPath() + "/YCLog/") : new File(sDPath + "/yc_file/");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath() + "/";
    }

    public static void init(final Context context) {
        File file;
        Log.i(MY_TAG, "init ...");
        if (mContext != null && instance != null && (file = logFile) != null && file.exists()) {
            Log.i(MY_TAG, "LogToFileUtils has been init ...");
            return;
        }
        mContext = context;
        instance = new LogToFileUtils();
        new Thread(new Runnable() { // from class: com.yucheng.ycbtsdk.utils.LogToFileUtils.1
            @Override // java.lang.Runnable
            public void run() throws IOException {
                File unused = LogToFileUtils.logFile = LogToFileUtils.getLogFile("yclogs.txt");
                Log.i(LogToFileUtils.MY_TAG, "LogFilePath is: " + LogToFileUtils.logFile.getPath());
                long fileSize = LogToFileUtils.getFileSize(LogToFileUtils.logFile);
                Log.d(LogToFileUtils.MY_TAG, "Log max size is: " + Formatter.formatFileSize(context, 10485760L));
                Log.i(LogToFileUtils.MY_TAG, "log now size is: " + Formatter.formatFileSize(context, fileSize));
                if (10485760 < fileSize) {
                    LogToFileUtils.resetLogFile();
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void resetLogFile() throws IOException {
        Log.i(MY_TAG, "Reset Log File ... ");
        File file = new File(logFile.getParent() + "/lastLog.txt");
        if (file.exists()) {
            file.delete();
        }
        logFile.renameTo(file);
        try {
            logFile.createNewFile();
        } catch (Exception e2) {
            Log.e(MY_TAG, "Create log file failure !!! " + e2.toString());
        }
    }

    public static void write(Object obj) throws Throwable {
        File file;
        BufferedWriter bufferedWriter;
        if (mContext == null || instance == null || (file = logFile) == null || !file.exists()) {
            Log.e(MY_TAG, "Initialization failure !!!");
            return;
        }
        String str = getFunctionInfo() + " - " + obj.toString();
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
                    try {
                        bufferedWriter.write(str);
                        bufferedWriter.write(HTTP.CRLF);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                    } catch (Exception e2) {
                        e = e2;
                        bufferedWriter2 = bufferedWriter;
                        Log.e(tag, "Write failure !!! " + e.toString());
                        if (bufferedWriter2 != null) {
                            bufferedWriter2.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException unused) {
                                Log.e(tag, "log close fail");
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedWriter = bufferedWriter2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException unused2) {
            Log.e(tag, "log close fail");
        }
    }

    public static void writeCollectionData(String str, List<byte[]> list) throws Throwable {
        FileOutputStream fileOutputStream;
        if (list.isEmpty()) {
            return;
        }
        String str2 = getYCFilePath() + str + ".csv";
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    File file = new File(str2);
                    if (!file.exists()) {
                        File file2 = new File(file.getParent());
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        try {
                            file.createNewFile();
                        } catch (Exception e2) {
                            YCBTLog.e("create file(yc_file) failure !!! " + e2.toString());
                            return;
                        }
                    }
                    fileOutputStream = new FileOutputStream(str2, true);
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        try {
                            String str3 = new String(list.get(i2), StandardCharsets.UTF_8);
                            fileOutputStream.write(list.get(i2));
                            YCBTLog.e("写入" + str3);
                        } catch (Exception e3) {
                            e = e3;
                            fileOutputStream2 = fileOutputStream;
                            e.printStackTrace();
                            YCBTLog.e(e.getMessage());
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                    YCBTLog.e(e4.getMessage());
                                }
                            }
                            throw th;
                        }
                    }
                    list.clear();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e5) {
                    e = e5;
                }
            } catch (Exception e6) {
                e6.printStackTrace();
                YCBTLog.e(e6.getMessage());
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    public static String writeJLLog(byte[] bArr) {
        return writeJLLog(System.currentTimeMillis() + "", bArr);
    }

    public static String writeJLLog(String str, byte[] bArr) throws Throwable {
        FileOutputStream fileOutputStream;
        String str2 = getYCFilePath() + str + "_jl.bin";
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    File file = new File(str2);
                    if (file.exists()) {
                        file.delete();
                    }
                    fileOutputStream = new FileOutputStream(str2);
                    try {
                        fileOutputStream.write(bArr);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream2 = fileOutputStream;
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        return str2;
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return str2;
    }

    public static void write(String str, String str2) {
        write(str, str2, false);
    }

    public static void write(String str, String str2, boolean z) {
        write(str, str2, z, true);
    }

    public static void write(String str, String str2, boolean z, boolean z2) throws IOException {
        File file;
        Context context = mContext;
        if (context != null && instance != null) {
            String sDPath = getSDPath(context);
            if (sDPath == null) {
                file = getLogFile(str);
            } else {
                file = new File(sDPath + "/yc_file/" + str);
            }
            if (!file.exists()) {
                File file2 = new File(file.getParent());
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (Exception e2) {
                    YCBTLog.e("create file(yc_file) failure !!! " + e2.toString());
                    return;
                }
            }
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, z));
                bufferedWriter.write(str2);
                if (z2) {
                    bufferedWriter.write(HTTP.CRLF);
                }
                bufferedWriter.flush();
                return;
            } catch (Exception e3) {
                YCBTLog.e("Write failure !!! " + e3.toString());
                return;
            }
        }
        YCBTLog.e("Initialization failure !!!");
    }
}
