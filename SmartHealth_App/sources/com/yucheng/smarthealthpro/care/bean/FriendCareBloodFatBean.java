package com.yucheng.smarthealthpro.care.bean;

import com.yucheng.smarthealthpro.utils.AppDateMgr;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class FriendCareBloodFatBean {
    public int code;
    public List<Data> data = new ArrayList();
    public String message;

    public class Data implements Comparable<Data> {
        public String dateformat;
        public String hdlcMean;
        public String hdlcTotal;
        public String id;
        public String ldlcMean;
        public String ldlcTotal;
        public String mlist;
        public String rtime;
        public String tcMean;
        public String tcTotal;
        public String tgMean;
        public String tgTotal;
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

    public class BloodFat {
        public int cMode;
        public String deviceMac;
        public String deviceModel;
        public float hdlc;
        public int hour;
        public float ldlc;
        public long rtime;
        public float tc;
        public float tg;
        public String zone;

        public BloodFat() {
        }
    }
}
