package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class CareBloodSugarWeekMonthBean {
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
        private String bloodSugarMean;
        private String bloodSugarTotal;
        private String dateformat;
        private String id;
        private String rtime;
        private String upCount;
        private String userId;

        public String getBloodSugarMean() {
            return this.bloodSugarMean;
        }

        public void setBloodSugarMean(String bloodSugarMean) {
            this.bloodSugarMean = bloodSugarMean;
        }

        public String getBloodSugarTotal() {
            return this.bloodSugarTotal;
        }

        public void setBloodSugarTotal(String bloodSugarTotal) {
            this.bloodSugarTotal = bloodSugarTotal;
        }

        public String getDateformat() {
            return this.dateformat;
        }

        public void setDateformat(String dateformat) {
            this.dateformat = dateformat;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
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

        public String toString() {
            return "DataBean{bloodSugarMean='" + this.bloodSugarMean + "', bloodSugarTotal='" + this.bloodSugarTotal + "', dateformat='" + this.dateformat + "', id='" + this.id + "', rtime='" + this.rtime + "', upCount='" + this.upCount + "', userId='" + this.userId + "'}";
        }
    }
}
