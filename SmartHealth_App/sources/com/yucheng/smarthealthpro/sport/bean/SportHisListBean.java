package com.yucheng.smarthealthpro.sport.bean;

import com.chad.library.adapter.base.entity.node.BaseNode;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class SportHisListBean extends BaseNode implements Serializable {
    private long beginDate;
    private int calorie;
    private float distance;
    private String endPoint;
    private int heart;
    private Boolean isUpload;
    private float kmh;
    private String minkm;
    private String pathLinePoints;
    private int runTime;
    private int sportStep;
    private String startPoint;
    private String timeYearToDate;
    private int type;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public SportHisListBean() {
        this.type = -1;
    }

    public SportHisListBean(int type, long beginDate, String timeYearToDate, float distance, int calorie, String minkm, int heart, int runTime, float kmh, String startPoint, String endPoint, String pathLinePoints, Boolean isUpload, int sportStep) {
        this.type = type;
        this.beginDate = beginDate;
        this.timeYearToDate = timeYearToDate;
        this.distance = distance;
        this.calorie = calorie;
        this.minkm = minkm;
        this.heart = heart;
        this.runTime = runTime;
        this.kmh = kmh;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pathLinePoints = pathLinePoints;
        this.isUpload = isUpload;
        this.sportStep = sportStep;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public String getTimeYearToDate() {
        return this.timeYearToDate;
    }

    public void setTimeYearToDate(String timeYearToDate) {
        this.timeYearToDate = timeYearToDate;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getMinkm() {
        return this.minkm;
    }

    public void setMinkm(String minkm) {
        this.minkm = minkm;
    }

    public int getHeart() {
        return this.heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getRunTime() {
        return this.runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public float getKmh() {
        return this.kmh;
    }

    public void setKmh(float kmh) {
        this.kmh = kmh;
    }

    public String getStartPoint() {
        return this.startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getPathLinePoints() {
        return this.pathLinePoints;
    }

    public void setPathLinePoints(String pathLinePoints) {
        this.pathLinePoints = pathLinePoints;
    }

    public Boolean getUpload() {
        return this.isUpload;
    }

    public void setUpload(Boolean upload) {
        this.isUpload = upload;
    }

    public int getSportStep() {
        return this.sportStep;
    }

    public void setSportStep(int sportStep) {
        this.sportStep = sportStep;
    }
}
