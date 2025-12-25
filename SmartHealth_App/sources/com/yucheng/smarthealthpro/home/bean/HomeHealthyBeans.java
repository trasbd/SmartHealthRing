package com.yucheng.smarthealthpro.home.bean;

import java.util.List;

/* loaded from: classes5.dex */
public class HomeHealthyBeans {
    public int code;
    public DataBean data;
    public String message;

    public static class DataBean {
        public int currentPage;
        public int isMore;
        public List<List<ItemsBean>> items;
        public int pageSize;
        public int startIndex;
        public int totalNum;
        public int totalPage;

        public static class ItemsBean {
            public String code;
            public String createBy;
            public String createTime;
            public Object date;
            public String fileUrl;
            public String healthId;
            public String imageUrl;
            public String moduleId;
            public Object moduleType;
            public String summary;
            public String title;
            public Object updateBy;
            public Object updateTime;
        }
    }
}
