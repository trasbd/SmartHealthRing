package com.yucheng.smarthealthpro.utils;

import com.yucheng.smarthealthpro.sport.bean.RunInfo;

/* loaded from: classes5.dex */
public class EventBusManualExitExerciseEvent {
    public byte[] data;
    public boolean isManualStop = false;
    public int mSportType;
    public RunInfo runInfo;

    public EventBusManualExitExerciseEvent(byte[] data) {
        this.data = data;
    }
}
