package com.yucheng.smarthealthpro.me.bean;

/* loaded from: classes5.dex */
public class UpgradeBean {
    public int code;
    public Data data = new Data();
    public String message;

    public class Data {
        public String deviceName;
        public String mac;
        public int upStatus;
        public String version;

        public Data() {
        }
    }
}
