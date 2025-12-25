package com.yucheng.ycbtsdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
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
    Context context;
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

    public interface DialProgressListener {
        void onDialProgress(int i2, float f2);
    }

    private void deleteDial() {
        if (YCBTClient.connectState() != 10) {
            return;
        }
        YCBTClient.watchDialDelete(this.customDialId, new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.5
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float f2, HashMap map) throws Throwable {
                if (i2 == 0) {
                    DialUtils.this.setDial();
                }
            }
        });
    }

    private String formatSeq(int i2) {
        return i2 < 10 ? "00" + i2 : i2 < 100 ? "0" + i2 : String.valueOf(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getBgBitmaps() throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            r2.<init>()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            android.content.Context r3 = r6.context     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r4 = "health/dial"
            java.lang.String r3 = com.yucheng.ycbtsdk.utils.SystemUiUtil.isExistDir(r3, r4)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r3 = "/"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r3 = r6.imgName     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r3 = ".bmp"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            int r0 = r1.available()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r6.bgBitmaps = r0     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r1.read(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r1.close()     // Catch: java.lang.Exception -> L52
            goto L56
        L3c:
            r0 = move-exception
            goto L57
        L3e:
            r0 = move-exception
            goto L49
        L40:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L57
        L45:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L49:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L3c
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.lang.Exception -> L52
            goto L56
        L52:
            r0 = move-exception
            r0.printStackTrace()
        L56:
            return
        L57:
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.lang.Exception -> L5d
            goto L61
        L5d:
            r1 = move-exception
            r1.printStackTrace()
        L61:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.utils.DialUtils.getBgBitmaps():void");
    }

    private String getCustomBgName(String str) throws NumberFormatException {
        int i2 = 0;
        if (str == null) {
            return "BGP_W" + formatSeq(0) + "";
        }
        String upperCase = str.toUpperCase();
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

    private String getCustomThumbnailBgName(String str) throws NumberFormatException {
        int i2 = 0;
        if (str == null) {
            return "VIE_W" + formatSeq(0) + "";
        }
        String upperCase = str.toUpperCase();
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

    public static DialUtils getInstance() {
        if (dialUtils == null) {
            dialUtils = new DialUtils();
        }
        return dialUtils;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getThumbnails() throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            r2.<init>()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            android.content.Context r3 = r6.context     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r4 = "health/dial"
            java.lang.String r3 = com.yucheng.ycbtsdk.utils.SystemUiUtil.isExistDir(r3, r4)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r3 = "/new_"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r3 = r6.imgName     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r3 = ".bmp"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L45
            int r0 = r1.available()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r6.thumbnails = r0     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r1.read(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r1.close()     // Catch: java.lang.Exception -> L52
            goto L56
        L3c:
            r0 = move-exception
            goto L57
        L3e:
            r0 = move-exception
            goto L49
        L40:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L57
        L45:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L49:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L3c
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.lang.Exception -> L52
            goto L56
        L52:
            r0 = move-exception
            r0.printStackTrace()
        L56:
            return
        L57:
            if (r1 == 0) goto L61
            r1.close()     // Catch: java.lang.Exception -> L5d
            goto L61
        L5d:
            r1 = move-exception
            r1.printStackTrace()
        L61:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.utils.DialUtils.getThumbnails():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0070 -> B:55:0x0073). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initDialData(java.lang.String r5) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.<init>()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
        L14:
            int r0 = r1.read(r5)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r3 = -1
            if (r0 == r3) goto L20
            r3 = 0
            r2.write(r5, r3, r0)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            goto L14
        L20:
            r2.flush()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            byte[] r5 = r2.toByteArray()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r4.bins = r5     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            com.yucheng.ycbtsdk.AITools r5 = com.yucheng.ycbtsdk.AITools.getInstance()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            byte[] r0 = r4.bins     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            com.yucheng.ycbtsdk.bean.ImageBean r5 = r5.getBmpSize(r0)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r4.bgImageBean = r5     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            com.yucheng.ycbtsdk.AITools r5 = com.yucheng.ycbtsdk.AITools.getInstance()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            byte[] r0 = r4.bins     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            com.yucheng.ycbtsdk.bean.ImageBean r5 = r5.getCompressionBmpSize(r0)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r4.cpImageBean = r5     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r1.close()     // Catch: java.lang.Exception -> L45
            goto L49
        L45:
            r5 = move-exception
            r5.printStackTrace()
        L49:
            r2.close()     // Catch: java.lang.Exception -> L6f
            goto L73
        L4d:
            r5 = move-exception
            goto L76
        L4f:
            r5 = move-exception
            goto L55
        L51:
            r5 = move-exception
            goto L77
        L53:
            r5 = move-exception
            r2 = r0
        L55:
            r0 = r1
            goto L5c
        L57:
            r5 = move-exception
            r2 = r0
            goto L75
        L5a:
            r5 = move-exception
            r2 = r0
        L5c:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L74
            if (r0 == 0) goto L69
            r0.close()     // Catch: java.lang.Exception -> L65
            goto L69
        L65:
            r5 = move-exception
            r5.printStackTrace()
        L69:
            if (r2 == 0) goto L73
            r2.close()     // Catch: java.lang.Exception -> L6f
            goto L73
        L6f:
            r5 = move-exception
            r5.printStackTrace()
        L73:
            return
        L74:
            r5 = move-exception
        L75:
            r1 = r0
        L76:
            r0 = r2
        L77:
            if (r1 == 0) goto L81
            r1.close()     // Catch: java.lang.Exception -> L7d
            goto L81
        L7d:
            r1 = move-exception
            r1.printStackTrace()
        L81:
            if (r0 == 0) goto L8b
            r0.close()     // Catch: java.lang.Exception -> L87
            goto L8b
        L87:
            r0 = move-exception
            r0.printStackTrace()
        L8b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.utils.DialUtils.initDialData(java.lang.String):void");
    }

    private void installDial(boolean z) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(SystemUiUtil.isExistDir(this.context, "health/dial") + "/new_" + this.imgName));
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 == -1) {
                    byteArrayOutputStream.flush();
                    YCBTLog.d("chong-----开始安装表盘");
                    YCBTClient.watchDialDownload(1, byteArrayOutputStream.toByteArray(), this.customDialId, 0, 0, new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.6
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i3, float f2, HashMap map) {
                            if (((Integer) map.get("dataType")).intValue() == 2308) {
                                return;
                            }
                            if (i3 != 0) {
                                if (i3 == 2) {
                                    DialUtils.this.dialProgressListener.onDialProgress(2, 0.0f);
                                    return;
                                } else {
                                    DialUtils.this.dialProgressListener.onDialProgress(3, 0.0f);
                                    return;
                                }
                            }
                            if (((Integer) map.get("dataType")).intValue() != 39168) {
                                DialUtils.this.dialProgressListener.onDialProgress(1, 100.0f);
                                return;
                            }
                            Message message = new Message();
                            message.what = 8;
                            message.arg1 = (int) ((Float) map.get("progress")).floatValue();
                            DialUtils.this.dialProgressListener.onDialProgress(0, (int) ((Float) map.get("progress")).floatValue());
                        }
                    });
                    return;
                }
                byteArrayOutputStream.write(bArr, 0, i2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            YCBTLog.d("chong-----开始安装表盘报错");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jlInstallCustomizeDial() {
        YCBTClient.jlInstallCustomizeDial(SystemUiUtil.isExistDir(this.context, "health/dial") + "/" + getCustomBgName(this.name), new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.2
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float f2, HashMap map) throws NumberFormatException {
                if (i2 != 0) {
                    if (i2 == 2) {
                        DialUtils.this.dialProgressListener.onDialProgress(2, 0.0f);
                        return;
                    } else {
                        DialUtils.this.dialProgressListener.onDialProgress(3, 0.0f);
                        return;
                    }
                }
                if (map == null || ((Integer) map.get("dataType")).intValue() != 39168) {
                    DialUtils.this.jlInstallCustomizeThumbnailDial();
                    return;
                }
                Message message = new Message();
                message.what = 8;
                float fFloatValue = (((Float) map.get("progress")).floatValue() / 100.0f) * 60.0f;
                message.arg1 = (int) fFloatValue;
                DialUtils.this.dialProgressListener.onDialProgress(0, fFloatValue);
                YCBTLog.e("setDialCustomize  onDialProgress=" + fFloatValue);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jlInstallCustomizeThumbnailDial() throws NumberFormatException {
        String customThumbnailBgName = getCustomThumbnailBgName(this.name);
        final String str = SystemUiUtil.isExistDir(this.context, "health/dial") + "/" + customThumbnailBgName;
        YCBTClient.jlWatchDialDelete(customThumbnailBgName, new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float f2, HashMap map) {
                if (i2 == 0) {
                    YCBTClient.jlInstallCustomizeDial(str, new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.3.1
                        @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                        public void onDataResponse(int i3, float f3, HashMap map2) {
                            if (i3 != 0) {
                                if (i3 == 2) {
                                    DialUtils.this.dialProgressListener.onDialProgress(2, 0.0f);
                                    return;
                                } else {
                                    DialUtils.this.dialProgressListener.onDialProgress(3, 0.0f);
                                    return;
                                }
                            }
                            if (map2 == null || ((Integer) map2.get("dataType")).intValue() != 39168) {
                                DialUtils.this.dialProgressListener.onDialProgress(1, 100.0f);
                                return;
                            }
                            Message message = new Message();
                            message.what = 8;
                            float fFloatValue = ((((Float) map2.get("progress")).floatValue() / 100.0f) * 40.0f) + 60.0f;
                            message.arg1 = (int) fFloatValue;
                            DialUtils.this.dialProgressListener.onDialProgress(0, fFloatValue);
                            YCBTLog.e("setDialCustomize  onDialProgress=" + fFloatValue);
                        }
                    });
                }
            }
        });
    }

    private void saveJlBgImage(String str) {
        YCBTClient.jlSaveCustomizeDialBg(str, SystemUiUtil.isExistDir(this.context, "health/dial") + "/" + getCustomBgName(this.name), new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.4
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float f2, HashMap map) {
                if (i2 != 0 || map == null || map.get("path") == null) {
                    return;
                }
                DialUtils dialUtils2 = DialUtils.this;
                dialUtils2.saveJlThumbnailImage(dialUtils2.thumbnailPath);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveJlThumbnailImage(String str) {
        YCBTClient.jlSaveCustomizeDialBg(str, SystemUiUtil.isExistDir(this.context, "health/dial") + "/" + getCustomThumbnailBgName(this.name), new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.1
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float f2, HashMap map) {
                if (i2 != 0 || map == null || map.get("path") == null) {
                    return;
                }
                YCBTLog.d("chong----path==" + ((String) map.get("path")));
                DialUtils dialUtils2 = DialUtils.this;
                YCBTClient.jieliSetDialText(dialUtils2.position, dialUtils2.parseColor, new BleDataResponse() { // from class: com.yucheng.ycbtsdk.utils.DialUtils.1.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int i3, float f3, HashMap map2) {
                        if (i3 == 0) {
                            DialUtils.this.jlInstallCustomizeDial();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDial() throws Throwable {
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(this.imgPath);
        Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(this.thumbnailPath);
        SaveBitmap888Util.saveBitmap888(bitmapDecodeFile, SystemUiUtil.isExistDir(this.context, "health/dial") + "/" + this.imgName + ".bmp");
        SaveBitmap888Util.saveBitmap888(bitmapDecodeFile2, SystemUiUtil.isExistDir(this.context, "health/dial") + "/new_" + this.imgName + ".bmp");
        getBgBitmaps();
        getThumbnails();
        byte[] bArr = this.bgBitmaps;
        if ((bArr == null && this.position == 9 && this.parseColor == 16777215) || this.thumbnails == null) {
            installDial(false);
            return;
        }
        byte[] bmp565 = bArr != null ? AITools.getInstance().toBmp565(this.bgBitmaps, this.bgImageBean.size, YCBTClient.isSupportFunction(Constants.FunctionConstant.ISFLIPDIALIMAGE)) : null;
        byte[] bArr2 = this.bins;
        if (bArr2 == null) {
            YCBTLog.d("bins == null");
            return;
        }
        int i2 = this.parseColor;
        byte[] bArr3 = new byte[bArr2.length];
        if (AITools.getInstance().modifyBinFile(bArr3, this.bins, bmp565, AITools.getInstance().toBmp565Thumb(this.thumbnails, this.cpImageBean.size, YCBTClient.isSupportFunction(Constants.FunctionConstant.ISFLIPDIALIMAGE)), this.pointX, this.pointY, (byte) (i2 >> 16), (byte) (i2 >> 8), (byte) i2)) {
            SystemUiUtil.saveBinFile(bArr3, SystemUiUtil.isExistDir(this.context, "health/dial") + "/new_" + this.imgName);
            installDial(true);
        }
    }

    public int convertRGB888toRGB565(int i2) {
        return (((i2 & 255) >> 3) & 31) | (((((i2 >> 16) & 255) >> 3) & 31) << 11) | (((((i2 >> 8) & 255) >> 2) & 63) << 5);
    }

    public void setDialCustomize(Context context, String str, String str2, int i2, String str3, int i3, int i4, int i5, int i6, boolean z, DialProgressListener dialProgressListener) {
        int i7;
        this.context = context;
        this.imgPath = str;
        this.thumbnailPath = str2;
        this.customDialId = i2;
        this.pointX = i3;
        this.pointY = i4;
        this.parseColor = i6;
        this.position = i5;
        this.isCanDelete = z;
        this.dialProgressListener = dialProgressListener;
        this.name = str3;
        if (YCBTClient.getChipScheme() == 3) {
            if (str3.contains(".")) {
                this.imgName = str3.substring(0, str3.lastIndexOf("."));
            } else {
                this.imgName = str3;
            }
            saveJlBgImage(str);
            return;
        }
        int iLastIndexOf = str.lastIndexOf("/");
        int iLastIndexOf2 = str.lastIndexOf(".");
        if (iLastIndexOf != -1 && iLastIndexOf2 != -1 && iLastIndexOf2 > (i7 = iLastIndexOf + 1)) {
            this.imgName = str.substring(i7, iLastIndexOf2);
        }
        initDialData(str3);
        if (!z || YCBTClient.getChipScheme() == 3) {
            setDial();
        } else {
            deleteDial();
        }
    }
}
