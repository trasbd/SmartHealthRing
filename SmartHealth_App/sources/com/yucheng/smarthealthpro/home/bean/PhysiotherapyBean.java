package com.yucheng.smarthealthpro.home.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes5.dex */
public class PhysiotherapyBean {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private List<DataDTO> data;

    @SerializedName("dataType")
    private int dataType;

    @SerializedName("type")
    private int type;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return this.data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class DataDTO {

        @SerializedName("physiotherapyDuration")
        private int physiotherapyDuration;

        @SerializedName("physiotherapyDurationLevel")
        private int physiotherapyDurationLevel;

        @SerializedName("physiotherapyPowerLevel")
        private int physiotherapyPowerLevel;

        @SerializedName("physiotherapyStartTime")
        private long physiotherapyStartTime;

        @SerializedName("physiotherapyStartType")
        private int physiotherapyStartType;

        @SerializedName("physiotherapyType")
        private int physiotherapyType;

        public long getPhysiotherapyStartTime() {
            return this.physiotherapyStartTime;
        }

        public void setPhysiotherapyStartTime(int physiotherapyStartTime) {
            this.physiotherapyStartTime = physiotherapyStartTime;
        }

        public int getPhysiotherapyDurationLevel() {
            return this.physiotherapyDurationLevel;
        }

        public void setPhysiotherapyDurationLevel(int physiotherapyDurationLevel) {
            this.physiotherapyDurationLevel = physiotherapyDurationLevel;
        }

        public int getPhysiotherapyPowerLevel() {
            return this.physiotherapyPowerLevel;
        }

        public void setPhysiotherapyPowerLevel(int physiotherapyPowerLevel) {
            this.physiotherapyPowerLevel = physiotherapyPowerLevel;
        }

        public int getPhysiotherapyStartType() {
            return this.physiotherapyStartType;
        }

        public void setPhysiotherapyStartType(int physiotherapyStartType) {
            this.physiotherapyStartType = physiotherapyStartType;
        }

        public int getPhysiotherapyType() {
            return this.physiotherapyType;
        }

        public void setPhysiotherapyType(int physiotherapyType) {
            this.physiotherapyType = physiotherapyType;
        }

        public int getPhysiotherapyDuration() {
            return this.physiotherapyDuration;
        }

        public void setPhysiotherapyDuration(int physiotherapyDuration) {
            this.physiotherapyDuration = physiotherapyDuration;
        }
    }
}
