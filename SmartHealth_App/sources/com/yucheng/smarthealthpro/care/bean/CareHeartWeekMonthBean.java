package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class CareHeartWeekMonthBean {
    private int code;
    private List<DataBean> data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String dateformat;
        private String heartMean;
        private String heartTotal;
        private int id;
        private String rtime;
        private String upCount;
        private String userId;

        public String getDateformat() {
            return this.dateformat;
        }

        public void setDateformat(String dateformat) {
            this.dateformat = dateformat;
        }

        public String getHeartMean() {
            return this.heartMean;
        }

        public void setHeartMean(String heartMean) {
            this.heartMean = heartMean;
        }

        public String getHeartTotal() {
            return this.heartTotal;
        }

        public void setHeartTotal(String heartTotal) {
            this.heartTotal = heartTotal;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRtime() {
            return this.rtime;
        }

        public void setRtime(String rtime) {
            this.rtime = rtime;
        }

        public String getUpCount() {
            return this.upCount;
        }

        public void setUpCount(String upCount) {
            this.upCount = upCount;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
