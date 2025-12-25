package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class FriendListBean {
    public int code;
    public List<DataBean> data;
    public String message;

    public static class DataBean {
        public String createTime;
        public String devId;
        public String deviceName;
        public int friendId;
        public String friendName;
        public String headImg;
        public int id;
        public String nickName;
        public String sex;
        public String updateTime;
        public int userId;
    }
}
