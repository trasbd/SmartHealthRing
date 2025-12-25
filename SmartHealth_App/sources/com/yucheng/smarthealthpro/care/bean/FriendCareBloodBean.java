package com.yucheng.smarthealthpro.care.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class FriendCareBloodBean {

    @SerializedName(alternate = {"DBP"}, value = "dbp")
    public int dbp;
    public int hour;
    public int isInflated;
    public long rtime;

    @SerializedName(alternate = {"SBP"}, value = "sbp")
    public int sbp;
    public String zone;
}
