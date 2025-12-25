package com.yucheng.smarthealthpro.care.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class FriendPhyBean {

    @SerializedName("dateformat")
    private String dateformat;

    @SerializedName(TypedValues.TransitionType.S_DURATION)
    private int duration;

    @SerializedName("durationLevel")
    private int durationLevel;

    @SerializedName("id")
    private long id;

    @SerializedName("mode")
    private int mode;

    @SerializedName("powerLevel")
    private int powerLevel;

    @SerializedName("rtime")
    private long rtime;

    @SerializedName("startupType")
    private int startupType;

    @SerializedName("userid")
    private String userid;

    public String getDateformat() {
        return this.dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDurationLevel() {
        return this.durationLevel;
    }

    public void setDurationLevel(int durationLevel) {
        this.durationLevel = durationLevel;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public long getRtime() {
        return this.rtime;
    }

    public void setRtime(long rtime) {
        this.rtime = rtime;
    }

    public int getStartupType() {
        return this.startupType;
    }

    public void setStartupType(int startupType) {
        this.startupType = startupType;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
