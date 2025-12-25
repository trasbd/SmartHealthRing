package com.yucheng.smarthealthpro.me.setting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.setting.dial.util.SaveBitmap888Util;
import com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.ImageBean;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class DialUtils {
    private static DialUtils dialUtils;
    private byte[] bgBitmaps;
    ImageBean bgImageBean;
    private byte[] bins;
    String colorStr;
    private Context context;
    ImageBean cpImageBean;
    private int customDialId;
    DialProgressListener dialProgressListener;
    String imgName;
    private String imgPath;
    boolean isCanDelete;
    String name;
    int parseColor;
    private int pointX;
    private int pointY;
    int position;
    String saveFileName;
    private String thumbnailPath;
    private byte[] thumbnails;
    String crop2 = SystemUiUtil.isExistDir("health/dial") + "/thumbnail";
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.4
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i2 = msg.what;
            return false;
        }
    });

    public interface DialProgressListener {
        void onDialProgress(int status, int progress);
    }

    public int convertRGB888toRGB565(int rgb888) {
        return (((rgb888 & 255) >> 3) & 31) | (((((rgb888 >> 16) & 255) >> 3) & 31) << 11) | (((((rgb888 >> 8) & 255) >> 2) & 63) << 5);
    }

    public static DialUtils getInstance() {
        if (dialUtils == null) {
            DialUtils dialUtils2 = new DialUtils();
            dialUtils = dialUtils2;
            dialUtils2.context = MyApplication.getInstance().getApplicationContext();
        }
        return dialUtils;
    }

    public void setDialCustomize(String imgPath, String thumbnailPath, int customDialId, int pointX, int pointY, int position, int parseColor, String saveFileName, boolean isCanDelete, DialProgressListener dialProgressListener) throws Throwable {
        this.imgPath = imgPath;
        this.thumbnailPath = thumbnailPath;
        this.customDialId = customDialId;
        this.pointX = pointX;
        this.pointY = pointY;
        this.parseColor = parseColor;
        this.position = position;
        this.isCanDelete = isCanDelete;
        this.dialProgressListener = dialProgressListener;
        if (saveFileName.contains(".")) {
            this.imgName = saveFileName.substring(0, saveFileName.lastIndexOf("."));
        } else {
            this.imgName = saveFileName;
        }
        this.name = saveFileName;
        initDialData();
        if (YCBTClient.isJieLi()) {
            saveJlBgImage(imgPath);
        } else if (isCanDelete && YCBTClient.getChipScheme() != 3) {
            deleteDial();
        } else {
            setDial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveJlThumbnailImage(String imagePath) {
        YCBTClient.jlSaveCustomizeDialBg(imagePath, SystemUiUtil.isExistDir("health/dial") + "/" + getCustomThumbnailBgName(this.name), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.1
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 != 0 || hashMap == null || hashMap.get("path") == null) {
                    return;
                }
                Logger.d("chong----path==" + ((String) hashMap.get("path")));
                DialUtils dialUtils2 = DialUtils.this;
                YCBTClient.jieliSetDialText(DialUtils.this.position, dialUtils2.convertRGB888toRGB565(dialUtils2.parseColor), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.1.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int i3, float v2, HashMap hashMap2) {
                        if (i3 == 0) {
                            DialUtils.this.jlInstallCustomizeDial();
                        }
                    }
                });
            }
        });
    }

    private void getThumbnails() throws Throwable {
        FileInputStream fileInputStream;
        Exception e2;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(SystemUiUtil.isExistDir("health/dial") + "/new_" + this.imgName + ".bmp");
                } catch (Exception e3) {
                    fileInputStream = null;
                    e2 = e3;
                } catch (Throwable th) {
                    fileInputStream = null;
                    th = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    this.thumbnails = bArr;
                    fileInputStream.read(bArr);
                    fileInputStream.close();
                } catch (Exception e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                }
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jlInstallCustomizeDial() {
        YCBTClient.jlInstallCustomizeDial(SystemUiUtil.isExistDir("health/dial") + "/" + getCustomBgName(this.name), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.2
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) throws NumberFormatException {
                if (i2 != 0) {
                    if (i2 == 2) {
                        DialUtils.this.dialProgressListener.onDialProgress(2, 0);
                        DialUtils.this.handler.sendEmptyMessage(6);
                        return;
                    } else {
                        DialUtils.this.dialProgressListener.onDialProgress(3, 0);
                        DialUtils.this.handler.sendEmptyMessage(5);
                        return;
                    }
                }
                if (hashMap != null && ((Integer) hashMap.get("dataType")).intValue() == 39168) {
                    Message message = new Message();
                    message.what = 8;
                    int iFloatValue = (int) ((((Float) hashMap.get("progress")).floatValue() / 100.0f) * 60.0f);
                    message.arg1 = iFloatValue;
                    DialUtils.this.dialProgressListener.onDialProgress(0, iFloatValue);
                    DialUtils.this.handler.sendMessage(message);
                    return;
                }
                DialUtils.this.jlInstallCustomizeThumbnailDial();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jlInstallCustomizeThumbnailDial() throws NumberFormatException {
        String customThumbnailBgName = getCustomThumbnailBgName(this.name);
        final String str = SystemUiUtil.isExistDir("health/dial") + "/" + customThumbnailBgName;
        YCBTClient.jlWatchDialDelete(customThumbnailBgName, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 == 0) {
                    YCBTClient.jlInstallCustomizeDial(str, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.3.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i3, float v2, HashMap hashMap2) {
                            if (i3 != 0) {
                                if (i3 == 2) {
                                    DialUtils.this.dialProgressListener.onDialProgress(2, 0);
                                    DialUtils.this.handler.sendEmptyMessage(6);
                                    return;
                                } else {
                                    DialUtils.this.dialProgressListener.onDialProgress(3, 0);
                                    DialUtils.this.handler.sendEmptyMessage(5);
                                    return;
                                }
                            }
                            if (hashMap2 != null && ((Integer) hashMap2.get("dataType")).intValue() == 39168) {
                                Message message = new Message();
                                message.what = 8;
                                int iFloatValue = (int) (((((Float) hashMap2.get("progress")).floatValue() / 100.0f) * 40.0f) + 60.0f);
                                message.arg1 = iFloatValue;
                                DialUtils.this.dialProgressListener.onDialProgress(0, iFloatValue);
                                DialUtils.this.handler.sendMessage(message);
                                return;
                            }
                            DialUtils.this.dialProgressListener.onDialProgress(1, 100);
                            DialUtils.this.handler.sendEmptyMessage(7);
                        }
                    });
                }
            }
        });
    }

    private String getCustomBgName(String name) throws NumberFormatException {
        int i2 = 0;
        if (name == null) {
            return "BGP_W" + formatSeq(0) + "";
        }
        String upperCase = name.toUpperCase();
        if (!upperCase.contains("WATCH")) {
            return "BGP_W" + formatSeq(0) + "";
        }
        if (!upperCase.equals("WATCH")) {
            String strReplaceAll = upperCase.replaceAll("WATCH", "");
            if (strReplaceAll.contains("(")) {
                strReplaceAll = strReplaceAll.substring(0, strReplaceAll.indexOf("("));
            }
            try {
                i2 = Integer.parseInt(strReplaceAll);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "BGP_W" + formatSeq(i2) + "";
    }

    private String getCustomThumbnailBgName(String name) throws NumberFormatException {
        int i2 = 0;
        if (name == null) {
            return "VIE_W" + formatSeq(0) + "";
        }
        String upperCase = name.toUpperCase();
        if (!upperCase.contains("WATCH")) {
            return "VIE_W" + formatSeq(0) + "";
        }
        if (!upperCase.equals("WATCH")) {
            String strReplaceAll = upperCase.replaceAll("WATCH", "");
            if (strReplaceAll.contains("(")) {
                strReplaceAll = strReplaceAll.substring(0, strReplaceAll.indexOf("("));
            }
            try {
                i2 = Integer.parseInt(strReplaceAll);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "VIE_W" + formatSeq(i2) + "";
    }

    private String formatSeq(int seq) {
        if (seq < 10) {
            return "00" + seq;
        }
        if (seq < 100) {
            return "0" + seq;
        }
        return String.valueOf(seq);
    }

    private void saveJlBgImage(String imagePath) {
        YCBTClient.jlSaveCustomizeDialBg(imagePath, SystemUiUtil.isExistDir("health/dial") + "/" + getCustomBgName(this.name), new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.5
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 != 0 || hashMap == null || hashMap.get("path") == null) {
                    return;
                }
                Logger.d("chong----path==" + ((String) hashMap.get("path")));
                DialUtils dialUtils2 = DialUtils.this;
                dialUtils2.saveJlThumbnailImage(dialUtils2.thumbnailPath);
            }
        });
    }

    private void deleteDial() {
        if (YCBTClient.connectState() != 10) {
            Tools.showAlert3(this.context, getString(R.string.please_connect_the_device));
        } else {
            YCBTClient.watchDialDelete(this.customDialId, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.6
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) throws Throwable {
                    if (i2 == 0) {
                        DialUtils.this.setDial();
                    } else {
                        DialUtils.this.handler.sendEmptyMessage(10);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDial() throws Throwable {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(this.imgPath);
        Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(this.thumbnailPath);
        SaveBitmap888Util.saveBitmap888(bitmapDecodeFile, SystemUiUtil.isExistDir("health/dial") + "/" + this.imgName + ".bmp");
        SaveBitmap888Util.saveBitmap888(bitmapDecodeFile2, SystemUiUtil.isExistDir("health/dial") + "/new_" + this.imgName + ".bmp");
        getBgBitmaps();
        getThumbnails();
        byte[] bArr = this.bgBitmaps;
        if ((bArr != null || this.position != 9 || this.parseColor != 16777215) && this.thumbnails != null) {
            byte[] bmp565 = bArr != null ? AITools.getInstance().toBmp565(this.bgBitmaps, this.bgImageBean.size, YCBTClient.isSupportFunction(Constants.FunctionConstant.ISFLIPDIALIMAGE)) : null;
            byte[] bArr2 = this.bins;
            if (bArr2 == null) {
                Logger.d("bins == null");
                return;
            }
            int i2 = this.parseColor;
            byte[] bArr3 = new byte[bArr2.length];
            if (AITools.getInstance().modifyBinFile(bArr3, this.bins, bmp565, AITools.getInstance().toBmp565Thumb(this.thumbnails, this.cpImageBean.size, YCBTClient.isSupportFunction(Constants.FunctionConstant.ISFLIPDIALIMAGE)), this.pointX, this.pointY, (byte) (i2 >> 16), (byte) (i2 >> 8), (byte) i2)) {
                SystemUiUtil.saveBinFile(bArr3, SystemUiUtil.isExistDir("health/dial") + "/new_" + this.name);
                installDial(true);
                return;
            }
            return;
        }
        installDial(false);
    }

    private void installDial(boolean isNew) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(SystemUiUtil.isExistDir("health/dial") + "/new_" + this.name));
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 != -1) {
                    byteArrayOutputStream.write(bArr, 0, i2);
                } else {
                    byteArrayOutputStream.flush();
                    Logger.d("chong-----开始安装表盘");
                    YCBTClient.watchDialDownload(1, byteArrayOutputStream.toByteArray(), this.customDialId, 0, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.DialUtils.7
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i3, float v, HashMap hashMap) {
                            if (((Integer) hashMap.get("dataType")).intValue() == 2308) {
                                return;
                            }
                            if (i3 != 0) {
                                if (i3 == 2) {
                                    DialUtils.this.dialProgressListener.onDialProgress(2, 0);
                                    DialUtils.this.handler.sendEmptyMessage(6);
                                    return;
                                } else {
                                    DialUtils.this.dialProgressListener.onDialProgress(3, 0);
                                    DialUtils.this.handler.sendEmptyMessage(5);
                                    return;
                                }
                            }
                            if (hashMap != null && ((Integer) hashMap.get("dataType")).intValue() == 39168) {
                                Message message = new Message();
                                message.what = 8;
                                message.arg1 = (int) ((Float) hashMap.get("progress")).floatValue();
                                DialUtils.this.dialProgressListener.onDialProgress(0, (int) ((Float) hashMap.get("progress")).floatValue());
                                DialUtils.this.handler.sendMessage(message);
                                return;
                            }
                            DialUtils.this.dialProgressListener.onDialProgress(1, 100);
                            DialUtils.this.handler.sendEmptyMessage(7);
                        }
                    });
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Logger.d("chong-----开始安装表盘报错");
        }
    }

    public String getString(int id) {
        return this.context.getString(id);
    }

    private void getBgBitmaps() throws Throwable {
        FileInputStream fileInputStream;
        Exception e2;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(SystemUiUtil.isExistDir("health/dial") + "/" + this.imgName + ".bmp");
                } catch (Exception e3) {
                    fileInputStream = null;
                    e2 = e3;
                } catch (Throwable th) {
                    fileInputStream = null;
                    th = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    this.bgBitmaps = bArr;
                    fileInputStream.read(bArr);
                    fileInputStream.close();
                } catch (Exception e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                }
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0093 -> B:61:0x0096). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initDialData() throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r3.<init>()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r4 = "health/dial"
            java.lang.String r4 = com.yucheng.smarthealthpro.me.setting.dial.util.SystemUiUtil.isExistDir(r4)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r4 = "/"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r4 = r5.name     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L7b
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L72
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L72
            r3.<init>()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L72
        L33:
            int r0 = r1.read(r2)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            r4 = -1
            if (r0 == r4) goto L3f
            r4 = 0
            r3.write(r2, r4, r0)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            goto L33
        L3f:
            r3.flush()     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            byte[] r0 = r3.toByteArray()     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            r5.bins = r0     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            com.yucheng.ycbtsdk.AITools r0 = com.yucheng.ycbtsdk.AITools.getInstance()     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            byte[] r2 = r5.bins     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            com.yucheng.ycbtsdk.bean.ImageBean r0 = r0.getBmpSize(r2)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            r5.bgImageBean = r0     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            com.yucheng.ycbtsdk.AITools r0 = com.yucheng.ycbtsdk.AITools.getInstance()     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            byte[] r2 = r5.bins     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            com.yucheng.ycbtsdk.bean.ImageBean r0 = r0.getCompressionBmpSize(r2)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            r5.cpImageBean = r0     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L97
            r1.close()     // Catch: java.lang.Exception -> L64
            goto L68
        L64:
            r0 = move-exception
            r0.printStackTrace()
        L68:
            r3.close()     // Catch: java.lang.Exception -> L92
            goto L96
        L6c:
            r0 = move-exception
            goto L7f
        L6e:
            r2 = move-exception
            r3 = r0
            r0 = r2
            goto L98
        L72:
            r2 = move-exception
            r3 = r0
            r0 = r2
            goto L7f
        L76:
            r1 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
            goto L98
        L7b:
            r1 = move-exception
            r3 = r0
            r0 = r1
            r1 = r3
        L7f:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L8c
            r1.close()     // Catch: java.lang.Exception -> L88
            goto L8c
        L88:
            r0 = move-exception
            r0.printStackTrace()
        L8c:
            if (r3 == 0) goto L96
            r3.close()     // Catch: java.lang.Exception -> L92
            goto L96
        L92:
            r0 = move-exception
            r0.printStackTrace()
        L96:
            return
        L97:
            r0 = move-exception
        L98:
            if (r1 == 0) goto La2
            r1.close()     // Catch: java.lang.Exception -> L9e
            goto La2
        L9e:
            r1 = move-exception
            r1.printStackTrace()
        La2:
            if (r3 == 0) goto Lac
            r3.close()     // Catch: java.lang.Exception -> La8
            goto Lac
        La8:
            r1 = move-exception
            r1.printStackTrace()
        Lac:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.me.setting.DialUtils.initDialData():void");
    }
}
