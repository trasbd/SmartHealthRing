package com.yucheng.smarthealthpro.care.bean;

import com.dd.plist.ASCIIPropertyListParser;

/* loaded from: classes4.dex */
public class FriendCareModeBean {
    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private int blood;
        public int bloodFat;
        public int bloodKetone;
        private int bloodOxygen;
        public int bloodSugar;
        private int heart;
        private int heartLine;
        private int hrv;
        public int laserConditioningTherapy;
        public int pressure;
        private int respiratoryRate;
        private int sleep;
        private int sport;
        private int temperature;
        public int uricAcid;
        private String userId;

        public int getPressure() {
            return this.pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getBloodSugar() {
            return this.bloodSugar;
        }

        public int getBloodKetone() {
            return this.bloodKetone;
        }

        public void setBloodKetone(int bloodKetone) {
            this.bloodKetone = bloodKetone;
        }

        public void setBloodSugar(int bloodSugar) {
            this.bloodSugar = bloodSugar;
        }

        public int getBloodFat() {
            return this.bloodFat;
        }

        public void setBloodFat(int bloodFat) {
            this.bloodFat = bloodFat;
        }

        public int getUricAcid() {
            return this.uricAcid;
        }

        public void setUricAcid(int uricAcid) {
            this.uricAcid = uricAcid;
        }

        public int getBlood() {
            return this.blood;
        }

        public void setBlood(int blood) {
            this.blood = blood;
        }

        public int getBloodOxygen() {
            return this.bloodOxygen;
        }

        public void setBloodOxygen(int bloodOxygen) {
            this.bloodOxygen = bloodOxygen;
        }

        public int getHeart() {
            return this.heart;
        }

        public void setHeart(int heart) {
            this.heart = heart;
        }

        public int getHeartLine() {
            return this.heartLine;
        }

        public void setHeartLine(int heartLine) {
            this.heartLine = heartLine;
        }

        public int getHrv() {
            return this.hrv;
        }

        public void setHrv(int hrv) {
            this.hrv = hrv;
        }

        public int getRespiratoryRate() {
            return this.respiratoryRate;
        }

        public void setRespiratoryRate(int respiratoryRate) {
            this.respiratoryRate = respiratoryRate;
        }

        public int getSleep() {
            return this.sleep;
        }

        public void setSleep(int sleep) {
            this.sleep = sleep;
        }

        public int getSport() {
            return this.sport;
        }

        public void setSport(int sport) {
            this.sport = sport;
        }

        public int getTemperature() {
            return this.temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setLaserConditioningTherapy(int laserConditioningTherapy) {
            this.laserConditioningTherapy = laserConditioningTherapy;
        }

        public int getLaserConditioningTherapy() {
            return this.laserConditioningTherapy;
        }

        public String toString() {
            return "DataBean{blood=" + this.blood + ", bloodOxygen=" + this.bloodOxygen + ", heart=" + this.heart + ", heartLine=" + this.heartLine + ", hrv=" + this.hrv + ", respiratoryRate=" + this.respiratoryRate + ", sleep=" + this.sleep + ", sport=" + this.sport + ", temperature=" + this.temperature + ", userId='" + this.userId + "', bloodSugar=" + this.bloodSugar + ", bloodFat=" + this.bloodFat + ", uricAcid=" + this.uricAcid + ", bloodKetone=" + this.bloodKetone + ", laserConditioningTherapy=" + this.laserConditioningTherapy + ", pressure=" + this.pressure + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        }
    }

    public String toString() {
        return "FriendCareModeBean{code=" + this.code + ", data=" + this.data + ", message='" + this.message + "'}";
    }
}
