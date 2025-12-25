package com.yucheng.smarthealthpro.customchart.sleep;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SleepInfo {
    public int beginTimes;
    public int endTimes;
    public List<DeepSleepInfo> mDeepList;
    public List<LightSleepInfo> mLightList;
    public List<SoberTimeInfo> mSoberList;

    public SleepInfo(int beginTimes, int endTimes, List<LightSleepInfo> mLightList, List<SoberTimeInfo> mSoberList, List<DeepSleepInfo> mDeepList) {
        this.mLightList = new ArrayList();
        this.mSoberList = new ArrayList();
        new ArrayList();
        this.beginTimes = beginTimes;
        this.endTimes = endTimes;
        this.mLightList = mLightList;
        this.mSoberList = mSoberList;
        this.mDeepList = mDeepList;
    }

    public int getBeginTimes() {
        return this.beginTimes;
    }

    public void setBeginTimes(int beginTimes) {
        this.beginTimes = beginTimes;
    }

    public int getEndTimes() {
        return this.endTimes;
    }

    public void setEndTimes(int endTimes) {
        this.endTimes = endTimes;
    }

    public List<LightSleepInfo> getmLightList() {
        return this.mLightList;
    }

    public void setmLightList(List<LightSleepInfo> mLightList) {
        this.mLightList = mLightList;
    }

    public List<SoberTimeInfo> getmSoberList() {
        return this.mSoberList;
    }

    public void setmSoberList(List<SoberTimeInfo> mSoberList) {
        this.mSoberList = mSoberList;
    }

    public List<DeepSleepInfo> getmDeepList() {
        return this.mDeepList;
    }

    public void setmDeepList(List<DeepSleepInfo> mDeepList) {
        this.mDeepList = mDeepList;
    }
}
