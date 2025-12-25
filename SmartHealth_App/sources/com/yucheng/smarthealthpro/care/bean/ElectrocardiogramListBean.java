package com.yucheng.smarthealthpro.care.bean;

import java.util.List;

/* loaded from: classes4.dex */
public class ElectrocardiogramListBean {
    public int code;
    public DataBean data;
    public String message;

    public class DataBean {
        public int currentPage;
        public int isMore;
        public List<Data> items;
        public int pageSize;
        public int startIndex;
        public int totalNum;
        public int totalPage;

        public DataBean() {
        }

        public class Data {
            public String aIDiagnosesUrl;
            public String aIMedicalResult;
            public int age;
            public String data;
            public int hhhh;
            public int hrHz;
            public int id;
            public int maxb;
            public String medicalResult;
            public int minb;
            public int sex;
            public long time;
            public String userId;

            public Data() {
            }

            public class AIResult {
                public int afflag = 0;
                public int qrstype = 1;

                public AIResult() {
                }
            }
        }
    }
}
