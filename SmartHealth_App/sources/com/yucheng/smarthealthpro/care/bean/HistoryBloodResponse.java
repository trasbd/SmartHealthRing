package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class HistoryBloodResponse {
    public int code;
    public List<DataBean> data;
    public String message;

    public static class DataBean {
        public int DBP;
        public int SBP;
        public int id;
        public String mlist;
        public String rtime;
        public String userId;

        public String toString() {
            return "DataBean{SBP=" + this.SBP + ", DBP=" + this.DBP + ", rtime=" + this.rtime + ", mlist='" + this.mlist + "', id=" + this.id + ", userId='" + this.userId + "'}";
        }
    }

    public class Mlist {
        public int dbp;
        public int isInflated;
        public long rtime;
        public int sbp;
        public String zone;

        public Mlist() {
        }
    }
}
