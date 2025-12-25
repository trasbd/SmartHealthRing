package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class HistorySleepResponse {
    public int code;
    public List<SleepBean> data;
    public String message;

    public static class SleepBean {
        public String beginTime;
        public String dateformat2;
        public int dsCount;
        public int dsTimes;
        public String endTime;
        public int id;
        public String mlist;
        public int qsCount;
        public int qsTimes;
        public int remTimes;
        public String sleepArr2;
        public String userId;
        public int version;
        public String wakeCount;
        public String wakeDuration;
    }
}
