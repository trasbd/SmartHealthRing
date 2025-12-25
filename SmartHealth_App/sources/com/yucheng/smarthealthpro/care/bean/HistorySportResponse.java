package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class HistorySportResponse {
    public int code;
    public List<DataBean> data;
    public String message;

    public class DataBean {
        public String begindate;
        public int cakl;
        public int des;
        public String enddate;
        public int hour;
        public int id;
        public String mlist;
        public int step;
        public String userId;

        public DataBean() {
        }

        public String toString() {
            return "DataBean{cakl=" + this.cakl + ", des=" + this.des + ", begindate=" + this.begindate + ", enddate=" + this.enddate + ", hour=" + this.hour + ", mlist='" + this.mlist + "', step=" + this.step + ", id=" + this.id + ", userId='" + this.userId + "'}";
        }
    }
}
