package com.yucheng.smarthealthpro.me.bean;

/* loaded from: classes5.dex */
public class DeviceInfo {
    private BaseRespBean base_resp;
    private String deviceid;
    private String qrticket;

    public BaseRespBean getBase_resp() {
        return this.base_resp;
    }

    public void setBase_resp(BaseRespBean base_resp) {
        this.base_resp = base_resp;
    }

    public String getDeviceid() {
        return this.deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getQrticket() {
        return this.qrticket;
    }

    public void setQrticket(String qrticket) {
        this.qrticket = qrticket;
    }

    public static class BaseRespBean {
        private int errcode;
        private String errmsg;

        public int getErrcode() {
            return this.errcode;
        }

        public void setErrcode(int errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return this.errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }
}
