package com.yucheng.smarthealthpro.care.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CareSleepWeekMonthBean implements Serializable {
    public int code;
    public List<DataBean> data = new ArrayList();
    public String message;

    public static class DataBean {
        public int count;
        public String dateformat;
        public int dsStatistics;
        public int id;
        public int qsStatistics;
        public int remTotalDuration;
        public String userId;
        public String wakeTotal;
        public String wakeTotalDuration;
    }
}
