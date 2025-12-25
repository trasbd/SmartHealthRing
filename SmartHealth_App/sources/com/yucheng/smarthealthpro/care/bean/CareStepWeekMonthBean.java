package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class CareStepWeekMonthBean {
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
        private String begindate;
        private String caklTotal;
        private String dateformat;
        private String desTotal;
        private String enddate;
        private int id;
        private String stepTotal;
        private String userId;

        public String getBegindate() {
            return this.begindate;
        }

        public void setBegindate(String begindate) {
            this.begindate = begindate;
        }

        public String getCaklTotal() {
            return this.caklTotal;
        }

        public void setCaklTotal(String caklTotal) {
            this.caklTotal = caklTotal;
        }

        public String getDateformat() {
            return this.dateformat;
        }

        public void setDateformat(String dateformat) {
            this.dateformat = dateformat;
        }

        public String getDesTotal() {
            return this.desTotal;
        }

        public void setDesTotal(String desTotal) {
            this.desTotal = desTotal;
        }

        public String getEnddate() {
            return this.enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStepTotal() {
            return this.stepTotal;
        }

        public void setStepTotal(String stepTotal) {
            this.stepTotal = stepTotal;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
