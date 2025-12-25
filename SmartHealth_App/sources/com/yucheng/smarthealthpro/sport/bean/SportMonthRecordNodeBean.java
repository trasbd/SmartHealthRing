package com.yucheng.smarthealthpro.sport.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class SportMonthRecordNodeBean {
    private List<SportHisListBean> data;
    private String month;

    public SportMonthRecordNodeBean(String month, List<SportHisListBean> data) {
        this.month = month;
        this.data = data;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<SportHisListBean> getData() {
        return this.data;
    }

    public void setData(List<SportHisListBean> data) {
        this.data = data;
    }
}
