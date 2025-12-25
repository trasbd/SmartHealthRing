package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class AppLogManager {
    private static final String LOG_DIR_NAME = "app_logs";
    private static final String LOG_FILE_EXT = ".txt";
    private static final String LOG_FILE_PREFIX = "app_log_";
    private static final int MAX_FILE_COUNT = 3;
    private static final int MAX_FILE_SIZE = 5242880;
    private static final String TAG = "AppLogManager";
    private static AppLogManager instance;
    private final Context context;
    private long currentFileSize;
    private File currentLogFile;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

    public static void init(Context context) {
        if (instance == null) {
            instance = new AppLogManager(context);
        }
    }

    public static AppLogManager getInstance() {
        AppLogManager appLogManager = instance;
        if (appLogManager != null) {
            return appLogManager;
        }
        throw new IllegalArgumentException("call init first");
    }

    private AppLogManager(Context context) {
        this.context = context;
        initializeLogFiles();
    }

    private void initializeLogFiles() {
        this.executor.execute(new Runnable() { // from class: com.yucheng.smarthealthpro.utils.AppLogManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initializeLogFiles$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeLogFiles$1() {
        try {
            File logDirectory = getLogDirectory();
            if (!logDirectory.exists() && !logDirectory.mkdirs()) {
                Log.e(TAG, "Failed to create log directory");
                return;
            }
            File[] fileArrListFiles = logDirectory.listFiles(new FilenameFilter() { // from class: com.yucheng.smarthealthpro.utils.AppLogManager$$ExternalSyntheticLambda3
                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str) {
                    return AppLogManager.lambda$initializeLogFiles$0(file, str);
                }
            });
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                sortFilesByModificationTime(fileArrListFiles);
                File file = fileArrListFiles[0];
                this.currentLogFile = file;
                this.currentFileSize = file.length();
                if (fileArrListFiles.length >= 3) {
                    for (int i2 = 2; i2 < fileArrListFiles.length; i2++) {
                        if (!fileArrListFiles[i2].delete()) {
                            Log.w(TAG, "Failed to delete old log file: " + fileArrListFiles[i2].getName());
                        }
                    }
                    return;
                }
                return;
            }
            createNewLogFile();
        } catch (Exception e2) {
            Log.e(TAG, "Error initializing log files", e2);
        }
    }

    static /* synthetic */ boolean lambda$initializeLogFiles$0(File file, String str) {
        return str.startsWith(LOG_FILE_PREFIX) && str.endsWith(LOG_FILE_EXT);
    }

    private File getLogDirectory() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return new File(this.context.getExternalFilesDir(null), LOG_DIR_NAME);
        }
        return new File(this.context.getFilesDir(), LOG_DIR_NAME);
    }

    private void sortFilesByModificationTime(File[] files) {
        Arrays.sort(files, new Comparator() { // from class: com.yucheng.smarthealthpro.utils.AppLogManager$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Long.compare(((File) obj2).lastModified(), ((File) obj).lastModified());
            }
        });
    }

    private void createNewLogFile() {
        try {
            File file = new File(getLogDirectory(), LOG_FILE_PREFIX + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + LOG_FILE_EXT);
            this.currentLogFile = file;
            if (!file.createNewFile()) {
                Log.e(TAG, "Failed to create new log file: " + this.currentLogFile.getName());
                return;
            }
            this.currentFileSize = 0L;
            writeToFile("Log file created: " + this.dateFormat.format(new Date()) + StringUtils.LF);
            writeToFile("Device: " + Build.MODEL + ", OS: Android " + Build.VERSION.RELEASE + "\n\n");
        } catch (IOException e2) {
            Log.e(TAG, "Error creating new log file", e2);
        }
    }

    public void log(final String level, final String tag, final String message) {
        MLog.INSTANCE.d(tag + ": " + message);
        this.executor.execute(new Runnable() { // from class: com.yucheng.smarthealthpro.utils.AppLogManager$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$log$3(level, tag, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$log$3(String str, String str2, String str3) {
        try {
            if (this.currentLogFile == null || this.currentFileSize >= 5242880) {
                rotateLogFiles();
            }
            if (this.currentLogFile != null) {
                writeToFile(String.format("%s [%s] %s: %s\n", this.dateFormat.format(new Date()), str, str2, str3));
            }
        } catch (Exception e2) {
            Log.e(TAG, "Error writing log", e2);
        }
    }

    private void writeToFile(String content) {
        try {
            FileWriter fileWriter = new FileWriter(this.currentLogFile, true);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                try {
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);
                    try {
                        printWriter.print(content);
                        printWriter.flush();
                        this.currentFileSize += content.getBytes().length;
                        printWriter.close();
                        bufferedWriter.close();
                        fileWriter.close();
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e2) {
            Log.e(TAG, "Error writing to log file", e2);
        }
    }

    private void rotateLogFiles() {
        try {
            File[] fileArrListFiles = getLogDirectory().listFiles(new FilenameFilter() { // from class: com.yucheng.smarthealthpro.utils.AppLogManager$$ExternalSyntheticLambda1
                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str) {
                    return AppLogManager.lambda$rotateLogFiles$4(file, str);
                }
            });
            if (fileArrListFiles != null) {
                sortFilesByModificationTime(fileArrListFiles);
                if (fileArrListFiles.length >= 3) {
                    for (int i2 = 2; i2 < fileArrListFiles.length; i2++) {
                        if (!fileArrListFiles[i2].delete()) {
                            Log.w(TAG, "Failed to delete old log file: " + fileArrListFiles[i2].getName());
                        }
                    }
                }
            }
            createNewLogFile();
        } catch (Exception e2) {
            Log.e(TAG, "Error rotating log files", e2);
        }
    }

    static /* synthetic */ boolean lambda$rotateLogFiles$4(File file, String str) {
        return str.startsWith(LOG_FILE_PREFIX) && str.endsWith(LOG_FILE_EXT);
    }

    public File[] getLogFiles() {
        File[] fileArrListFiles = getLogDirectory().listFiles(new FilenameFilter() { // from class: com.yucheng.smarthealthpro.utils.AppLogManager$$ExternalSyntheticLambda0
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return AppLogManager.lambda$getLogFiles$5(file, str);
            }
        });
        if (fileArrListFiles != null) {
            sortFilesByModificationTime(fileArrListFiles);
        }
        return fileArrListFiles != null ? fileArrListFiles : new File[0];
    }

    static /* synthetic */ boolean lambda$getLogFiles$5(File file, String str) {
        return str.startsWith(LOG_FILE_PREFIX) && str.endsWith(LOG_FILE_EXT);
    }

    public void clearLogs() {
        this.executor.execute(new Runnable() { // from class: com.yucheng.smarthealthpro.utils.AppLogManager$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$clearLogs$6();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clearLogs$6() {
        try {
            for (File file : getLogFiles()) {
                if (!file.delete()) {
                    Log.w(TAG, "Failed to delete log file: " + file.getName());
                }
            }
            this.currentLogFile = null;
            this.currentFileSize = 0L;
        } catch (Exception e2) {
            Log.e(TAG, "Error clearing log files", e2);
        }
    }

    public void shutdown() {
        this.executor.shutdown();
    }
}
