package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class HistoryEcgResponse {
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
        private int age;
        private String data;
        private String dateformat;
        private int hhhh;
        private int hrHz;
        private int id;
        private int maxb;
        private String medicalResult;
        private int minb;
        private int sex;
        private long time;
        private String userId;

        public int getHhhh() {
            return this.hhhh;
        }

        public void setHhhh(int hhhh) {
            this.hhhh = hhhh;
        }

        public int getMinb() {
            return this.minb;
        }

        public void setMinb(int minb) {
            this.minb = minb;
        }

        public String getData() {
            return this.data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getSex() {
            return this.sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getTime() {
            return this.time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getHrHz() {
            return this.hrHz;
        }

        public void setHrHz(int hrHz) {
            this.hrHz = hrHz;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getMaxb() {
            return this.maxb;
        }

        public void setMaxb(int maxb) {
            this.maxb = maxb;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getDateformat() {
            return this.dateformat;
        }

        public void setDateformat(String dateformat) {
            this.dateformat = dateformat;
        }

        public String getMedicalResult() {
            return this.medicalResult;
        }

        public void setMedicalResult(String medicalResult) {
            this.medicalResult = medicalResult;
        }
    }
}
