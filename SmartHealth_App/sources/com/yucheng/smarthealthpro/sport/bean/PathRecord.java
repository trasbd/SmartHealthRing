package com.yucheng.smarthealthpro.sport.bean;

import com.amap.api.location.AMapLocation;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class PathRecord {
    private String mAveragespeed;
    private String mDate;
    private String mDistance;
    private String mDuration;
    private AMapLocation mEndPoint;
    private AMapLocation mStartPoint;
    private List<AMapLocation> mPathLinePoints = new ArrayList();
    private int mId = 0;

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public AMapLocation getStartpoint() {
        return this.mStartPoint;
    }

    public void setStartpoint(AMapLocation startpoint) {
        this.mStartPoint = startpoint;
    }

    public AMapLocation getEndpoint() {
        return this.mEndPoint;
    }

    public void setEndpoint(AMapLocation endpoint) {
        this.mEndPoint = endpoint;
    }

    public List<AMapLocation> getPathline() {
        return this.mPathLinePoints;
    }

    public void setPathline(List<AMapLocation> pathline) {
        this.mPathLinePoints = pathline;
    }

    public String getDistance() {
        return this.mDistance;
    }

    public void setDistance(String distance) {
        this.mDistance = distance;
    }

    public String getDuration() {
        return this.mDuration;
    }

    public void setDuration(String duration) {
        this.mDuration = duration;
    }

    public String getAveragespeed() {
        return this.mAveragespeed;
    }

    public void setAveragespeed(String averagespeed) {
        this.mAveragespeed = averagespeed;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public void addpoint(AMapLocation point) {
        this.mPathLinePoints.add(point);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("recordSize:" + getPathline().size() + ", ");
        sb.append("distance:" + getDistance() + "m, ");
        sb.append("duration:" + getDuration() + "s");
        return sb.toString();
    }
}
