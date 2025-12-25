package com.yucheng.smarthealthpro.care.bean;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class FriendCareDataBean {
    public int code;
    public List<Data> data = new ArrayList();
    public String message;

    public class Data {
        public int hour;
        public String id;
        public String mlist;
        public String userId;

        public Data() {
        }
    }
}
