package com.yucheng.smarthealthpro.care.bean;

import com.dd.plist.ASCIIPropertyListParser;

/* loaded from: classes4.dex */
public class FriendCareBloodSugarBean {
    public float bloodSugar;
    public int hour;
    public long rtime;

    public String toString() {
        return "FriendCareBloodSugarBean{rtime=" + this.rtime + ", hour=" + this.hour + ", bloodSugar=" + this.bloodSugar + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
