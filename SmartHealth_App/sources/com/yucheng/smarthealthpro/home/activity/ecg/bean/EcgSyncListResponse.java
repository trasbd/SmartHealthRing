package com.yucheng.smarthealthpro.home.activity.ecg.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class EcgSyncListResponse {
    public int code;
    public int collectType;
    public List<DataBean> data;

    public class DataBean {
        public int collectBlockNum;
        public int collectDigits;
        public int collectSN;
        public long collectSendTime;
        public long collectStartTime;
        public int collectTotalLen;
        public int collectType;

        public DataBean() {
        }
    }
}
