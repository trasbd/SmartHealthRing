package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class HistoryHeartResponse {
    public int code;
    public List<HeartBean> data;
    public String message;

    public class HeartBean {
        public int heartTimes;
        public int hour;
        public int id;
        public String mlist;
        public String rtime;
        public String userId;

        public HeartBean() {
        }

        public String toString() {
            return "HeartBean{heartTimes=" + this.heartTimes + ", rtime=" + this.rtime + ", hour=" + this.hour + ", mlist='" + this.mlist + "', id=" + this.id + ", userId='" + this.userId + "'}";
        }
    }

    public class Mlist {
        public int heartTimes;
        public int hour;
        public long rtime;
        public String zone;

        public Mlist() {
        }
    }
}
