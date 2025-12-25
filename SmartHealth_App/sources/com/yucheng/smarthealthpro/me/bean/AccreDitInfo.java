package com.yucheng.smarthealthpro.me.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class AccreDitInfo {
    private List<RespBean> resp;

    public List<RespBean> getResp() {
        return this.resp;
    }

    public void setResp(List<RespBean> resp) {
        this.resp = resp;
    }

    public static class RespBean {
        private BaseInfoBean base_info;
        private int errcode;
        private String errmsg;

        public BaseInfoBean getBase_info() {
            return this.base_info;
        }

        public void setBase_info(BaseInfoBean base_info) {
            this.base_info = base_info;
        }

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

        public static class BaseInfoBean {
            private String device_id;
            private String device_type;

            public String getDevice_type() {
                return this.device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public String getDevice_id() {
                return this.device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }
        }
    }
}
