package com.yucheng.smarthealthpro.sport.weathers;

import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class WeatherBean implements Serializable, Comparable<ScanDeviceBean> {
    public int code;
    public Data data;
    public String message;

    @Override // java.lang.Comparable
    public int compareTo(ScanDeviceBean o) {
        return 0;
    }

    public class Data {
        public int condCode;
        public String condTxt;
        public String fl;
        public String location;
        public String satuts;
        public String tmp;
        public Weather[] weather;
        public String windDir;
        public String windSc;

        public Data() {
        }

        public class Weather {
            public int condCodeD;
            public int condCodeN;
            public String condTxtD;
            public String condTxtN;
            public String date;
            public String tmpMax;
            public String tmpMin;
            public String windDir;
            public String windSc;

            public Weather() {
            }
        }
    }
}
