package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class CareBpWeekMonthBean {
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
        private String dbpMean;
        private String dbpTotal;
        private int id;
        private String rtime;
        private String sbpMean;
        private String sbpTotal;
        private String upCount;
        private String userId;

        public String getDateformat() {
            return this.dateformat;
        }

        public void setDateformat(String dateformat) {
            this.dateformat = dateformat;
        }

        public String getDbpMean() {
            return this.dbpMean;
        }

        public void setDbpMean(String dbpMean) {
            this.dbpMean = dbpMean;
        }

        public String getDbpTotal() {
            return this.dbpTotal;
        }

        public void setDbpTotal(String dbpTotal) {
            this.dbpTotal = dbpTotal;
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

        public String getSbpMean() {
            return this.sbpMean;
        }

        public void setSbpMean(String sbpMean) {
            this.sbpMean = sbpMean;
        }

        public String getSbpTotal() {
            return this.sbpTotal;
        }

        public void setSbpTotal(String sbpTotal) {
            this.sbpTotal = sbpTotal;
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
            return "DataBean{dateformat='" + this.dateformat + "', dbpMean='" + this.dbpMean + "', dbpTotal='" + this.dbpTotal + "', id=" + this.id + ", rtime='" + this.rtime + "', sbpMean='" + this.sbpMean + "', sbpTotal='" + this.sbpTotal + "', upCount='" + this.upCount + "', userId='" + this.userId + "'}";
        }
    }
}
