package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class BodyDataResponse {
    private int code;
    private List<DataBean> data;
    private int dataType;

    public class DataBean {
        public int bodyFloat;
        public int bodyInteger;
        public int hrvFloat;
        public int hrvInteger;
        public boolean isUpload;
        public int loadIndexFloat;
        public int loadIndexInteger;
        public int maximalOxygenIntake;
        public int pressureFloat;
        public int pressureInteger;
        public int sdn;
        public int sympatheticFloat;
        public int sympatheticInteger;
        public long time;

        public DataBean() {
        }

        public long getTime() {
            return this.time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getLoadIndexInteger() {
            return this.loadIndexInteger;
        }

        public void setLoadIndexInteger(int loadIndexInteger) {
            this.loadIndexInteger = loadIndexInteger;
        }

        public int getLoadIndexFloat() {
            return this.loadIndexFloat;
        }

        public void setLoadIndexFloat(int loadIndexFloat) {
            this.loadIndexFloat = loadIndexFloat;
        }

        public int getHrvInteger() {
            return this.hrvInteger;
        }

        public void setHrvInteger(int hrvInteger) {
            this.hrvInteger = hrvInteger;
        }

        public int getHrvFloat() {
            return this.hrvFloat;
        }

        public void setHrvFloat(int hrvFloat) {
            this.hrvFloat = hrvFloat;
        }

        public int getPressureInteger() {
            return this.pressureInteger;
        }

        public void setPressureInteger(int pressureInteger) {
            this.pressureInteger = pressureInteger;
        }

        public int getPressureFloat() {
            return this.pressureFloat;
        }

        public void setPressureFloat(int pressureFloat) {
            this.pressureFloat = pressureFloat;
        }

        public int getBodyInteger() {
            return this.bodyInteger;
        }

        public void setBodyInteger(int bodyInteger) {
            this.bodyInteger = bodyInteger;
        }

        public int getBodyFloat() {
            return this.bodyFloat;
        }

        public void setBodyFloat(int bodyFloat) {
            this.bodyFloat = bodyFloat;
        }

        public int getSympatheticInteger() {
            return this.sympatheticInteger;
        }

        public void setSympatheticInteger(int sympatheticInteger) {
            this.sympatheticInteger = sympatheticInteger;
        }

        public int getSympatheticFloat() {
            return this.sympatheticFloat;
        }

        public void setSympatheticFloat(int sympatheticFloat) {
            this.sympatheticFloat = sympatheticFloat;
        }

        public int getSdn() {
            return this.sdn;
        }

        public void setSdn(int sdn) {
            this.sdn = sdn;
        }

        public int getMaximalOxygenIntake() {
            return this.maximalOxygenIntake;
        }

        public void setMaximalOxygenIntake(int maximalOxygenIntake) {
            this.maximalOxygenIntake = maximalOxygenIntake;
        }

        public boolean isUpload() {
            return this.isUpload;
        }

        public void setUpload(boolean upload) {
            this.isUpload = upload;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
