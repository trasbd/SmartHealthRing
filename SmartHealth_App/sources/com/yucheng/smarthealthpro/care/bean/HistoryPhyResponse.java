package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class HistoryPhyResponse {
    public int code;
    public List<DataBean> data;
    public String message;

    public static class DataBean {
        private String createTime;
        private String dateformat;
        private String id;
        private String mlist;
        private String rtime;
        private String uaMean;
        private String uaTotal;
        private int upCount;
        private String updateTime;
        private String userid;

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getMlist() {
            return this.mlist;
        }

        public void setMlist(String mlist) {
            this.mlist = mlist;
        }

        public String getRtime() {
            return this.rtime;
        }

        public void setRtime(String rtime) {
            this.rtime = rtime;
        }

        public String getUaMean() {
            return this.uaMean;
        }

        public void setUaMean(String uaMean) {
            this.uaMean = uaMean;
        }

        public String getUaTotal() {
            return this.uaTotal;
        }

        public void setUaTotal(String uaTotal) {
            this.uaTotal = uaTotal;
        }

        public int getUpCount() {
            return this.upCount;
        }

        public void setUpCount(int upCount) {
            this.upCount = upCount;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUserid() {
            return this.userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
