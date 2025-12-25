package com.yucheng.smarthealthpro.sport.bean;

import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.dd.plist.ASCIIPropertyListParser;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class RunInfo implements Serializable {
    public int calorie;
    public float distance;
    public float distances;
    public int heart;
    public int heartValue;
    public Boolean isUpload;
    public float kmh;
    public String mAMapLocationString;
    public LatLng mEndLatLng;
    public LatLng mStartLatLng;
    public String mapCoordinatesList;
    public int runTime;
    public int sportCalorie;
    public int sportDistance;
    public int sportStep;
    public int type = -1;
    public long beginDate = System.currentTimeMillis();
    public String minkm = "0'0\"";

    public void makeKmh() {
        int i2 = this.runTime;
        if (i2 > 0) {
            this.kmh = this.distances / (i2 / 3600.0f);
        }
        float f2 = this.distances;
        int i3 = f2 > 0.0f ? (int) ((1.0f / f2) * i2) : 0;
        Log.i("RunInfo", "--makeKmh--" + this.distances + "--" + this.runTime);
        makeTimePs(i3);
    }

    public void makeTimePs(int time) {
        Log.i("RunInfo", "--makeTimePs--" + time);
        int i2 = time % 60;
        int i3 = ((time - i2) / 60) % 60;
        int i4 = (time - (i3 * 60)) / 60;
        if (i4 == 0) {
            this.minkm = i3 + "'" + i2 + "\"";
        } else {
            this.minkm = i4 + "'" + i3 + "'" + i2 + "\"";
        }
    }

    public String toString() {
        return "RunInfo{beginDate=" + this.beginDate + ", distance=" + this.distance + ", distances=" + this.distances + ", calorie=" + this.calorie + ", heart=" + this.heart + ", runTime=" + this.runTime + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
