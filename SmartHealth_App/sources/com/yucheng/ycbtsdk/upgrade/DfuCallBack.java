package com.yucheng.ycbtsdk.upgrade;

/* loaded from: classes5.dex */
public interface DfuCallBack {
    void connected();

    void connecting();

    void disconnect();

    void error(String str);

    void failed(String str);

    void latest();

    void onNeedReconnect(String str, boolean z);

    void progress(int i2);

    void success();
}
