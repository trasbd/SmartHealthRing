package com.yucheng.smarthealthpro.care.bean;

import com.yucheng.smarthealthpro.utils.AppDateMgr;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class FriendCareKetoneBean {
    public int code;
    public List<Data> data = new ArrayList();
    public String message;

    public class Data implements Comparable<Data> {
        public String createTime;
        public String dateformat;
        public String id;
        public String mean;
        public String mlist;
        public String rtime;
        public String total;
        public int upCount;
        public String updateTime;
        public String userid;

        public Data() {
        }

        @Override // java.lang.Comparable
        public int compareTo(Data o) {
            return (int) (AppDateMgr.string2Date(o.rtime).getTime() - AppDateMgr.string2Date(this.rtime).getTime());
        }
    }

    public class BloodKetone {
        public float bloodKetone;
        public int cMode;
        public String deviceMac;
        public String deviceModel;
        public int hour;
        public long rtime;
        public String zone;

        public BloodKetone() {
        }
    }
}
