package com.yucheng.smarthealthpro.home.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class MyMonBean implements Serializable {
    public int code;
    public Data data;
    public String message;

    public class Data {
        public String displaytext;
        public String displayunits;
        public List<Values> values = new ArrayList();

        public Data() {
        }

        public class Values {
            public String displayvalue;
            public String time;

            public Values() {
            }
        }
    }
}
