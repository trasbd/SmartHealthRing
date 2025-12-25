package com.yucheng.smarthealthpro.care.bean;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes4.dex */
public class UploadFileTypeBean {
    private int code;
    private DataBean data;
    private String message;

    public static class AppBeta {
        public List<AppInfo> Android;
        public List<AppInfo> iOS;
    }

    public static class AppInfo implements Serializable {
        public String appVersion;
        public String bundleID;
        public String bundleVersion;
        public Object content;
        public String link;
        public String signature;
        public boolean vestBag;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        AppBeta appBeta;
        List<String> logDeviceModel;
        String properties;
        boolean quickSelectPage;
        boolean vestBag;

        public AppBeta getAppBeta() {
            return this.appBeta;
        }

        public void setAppBeta(AppBeta appBeta) {
            this.appBeta = appBeta;
        }

        public boolean isQuickSelectPage() {
            return this.quickSelectPage;
        }

        public void setQuickSelectPage(boolean quickSelectPage) {
            this.quickSelectPage = quickSelectPage;
        }

        public List<String> getLogDeviceModel() {
            return this.logDeviceModel;
        }

        public void setLogDeviceModel(List<String> logDeviceModel) {
            this.logDeviceModel = logDeviceModel;
        }

        public boolean isVestBag() {
            return this.vestBag;
        }

        public void setVestBag(boolean vestBag) {
            this.vestBag = vestBag;
        }

        public String getProperties() {
            return this.properties;
        }

        public void setProperties(String properties) {
            this.properties = properties;
        }
    }
}
