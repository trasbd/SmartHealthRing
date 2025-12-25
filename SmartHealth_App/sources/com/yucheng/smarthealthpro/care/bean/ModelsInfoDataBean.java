package com.yucheng.smarthealthpro.care.bean;

/* loaded from: classes4.dex */
public class ModelsInfoDataBean {
    public int code;
    public Data data = new Data();
    public String message;

    public class Data {
        public int batteryCapacity;
        public String deviceModelId;
        public String deviceName;
        public int deviceShape;
        public int displayHeight;
        public float displaySize;
        public int displayWidth;
        public String imagesUrl;
        public Institution institution;
        public String modelName;

        public Data() {
        }
    }

    public class Institution {
        public String customerId;
        public String logoUrl;
        public String name;

        public Institution() {
        }
    }
}
