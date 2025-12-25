package com.yucheng.smarthealthpro.care.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes4.dex */
public class FriendCarePhyBean {
    private int code;
    private List<DataDTO> data;
    private String message;

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

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {

        @SerializedName("dateformat")
        private String dateFormat;

        @SerializedName("durationAvg")
        private int durationAvg;

        @SerializedName("durationTotal")
        private int durationTotal;

        @SerializedName("mlist")
        private String mlist;

        @SerializedName("userid")
        private String userid;

        public String getDateFormat() {
            return this.dateFormat;
        }

        public void setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
        }

        public int getDurationAvg() {
            return this.durationAvg;
        }

        public void setDurationAvg(int durationAvg) {
            this.durationAvg = durationAvg;
        }

        public int getDurationTotal() {
            return this.durationTotal;
        }

        public void setDurationTotal(int durationTotal) {
            this.durationTotal = durationTotal;
        }

        public String getMlist() {
            return this.mlist;
        }

        public void setMlist(String mlist) {
            this.mlist = mlist;
        }

        public String getUserid() {
            return this.userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
