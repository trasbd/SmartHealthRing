package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class HistoryHRVResponse {
    public int code;
    public List<HRVBean> data;
    public String message;

    public class HRVBean {
        public int hour;
        public int hrv;
        public long id;
        public String mlist;
        public String rtime;
        public String userId;

        public HRVBean() {
        }

        public String toString() {
            return "HeartBean{heartTimes=" + this.hrv + ", rtime=" + this.rtime + ", hour=" + this.hour + ", mlist='" + this.mlist + "', id=" + this.id + ", userId='" + this.userId + "'}";
        }
    }

    public class Mlist implements Comparable<Mlist> {
        public String deviceMac;
        public String deviceModel;
        public int hour;
        public int hrv;
        public long rtime;
        public String zone;

        public Mlist() {
        }

        @Override // java.lang.Comparable
        public int compareTo(Mlist o) {
            return (int) (o.rtime - this.rtime);
        }
    }
}
