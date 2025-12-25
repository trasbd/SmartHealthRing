package com.yucheng.smarthealthpro.sport.utils;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class PathSmoothTool {
    private double currentLocation_x;
    private double currentLocation_y;
    private double estimate_x;
    private double estimate_y;
    private double gauss_x;
    private double gauss_y;
    private double kalmanGain_x;
    private double kalmanGain_y;
    private double lastLocation_x;
    private double lastLocation_y;
    private double mdelt_x;
    private double mdelt_y;
    private double pdelt_x;
    private double pdelt_y;
    private int mIntensity = 3;
    private float mThreshhold = 0.3f;
    private float mNoiseThreshhold = 10.0f;
    private double m_R = 0.0d;
    private double m_Q = 0.0d;

    public int getIntensity() {
        return this.mIntensity;
    }

    public void setIntensity(int mIntensity) {
        this.mIntensity = mIntensity;
    }

    public float getThreshhold() {
        return this.mThreshhold;
    }

    public void setThreshhold(float mThreshhold) {
        this.mThreshhold = mThreshhold;
    }

    public void setNoiseThreshhold(float mnoiseThreshhold) {
        this.mNoiseThreshhold = mnoiseThreshhold;
    }

    public List<LatLng> pathOptimize(List<LatLng> originlist) {
        List<LatLng> listReducerVerticalThreshold = reducerVerticalThreshold(kalmanFilterPath(removeNoisePoint(originlist), this.mIntensity), this.mThreshhold);
        return listReducerVerticalThreshold == null ? new ArrayList() : listReducerVerticalThreshold;
    }

    public List<LatLng> kalmanFilterPath(List<LatLng> originlist) {
        return kalmanFilterPath(originlist, this.mIntensity);
    }

    public List<LatLng> removeNoisePoint(List<LatLng> originlist) {
        return reduceNoisePoint(originlist, this.mNoiseThreshhold);
    }

    public LatLng kalmanFilterPoint(LatLng lastLoc, LatLng curLoc) {
        return kalmanFilterPoint(lastLoc, curLoc, this.mIntensity);
    }

    public List<LatLng> reducerVerticalThreshold(List<LatLng> inPoints) {
        return reducerVerticalThreshold(inPoints, this.mThreshhold);
    }

    private List<LatLng> kalmanFilterPath(List<LatLng> originlist, int intensity) {
        ArrayList arrayList = new ArrayList();
        if (originlist != null && originlist.size() > 2) {
            initial();
            LatLng latLng = originlist.get(0);
            arrayList.add(latLng);
            for (int i2 = 1; i2 < originlist.size(); i2++) {
                LatLng latLngKalmanFilterPoint = kalmanFilterPoint(latLng, originlist.get(i2), intensity);
                if (latLngKalmanFilterPoint != null) {
                    arrayList.add(latLngKalmanFilterPoint);
                    latLng = latLngKalmanFilterPoint;
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001a A[PHI: r1
  0x001a: PHI (r1v5 int) = (r1v0 int), (r1v1 int) binds: [B:12:0x0018, B:15:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0023 A[LOOP:0: B:18:0x0021->B:19:0x0023, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amap.api.maps.model.LatLng kalmanFilterPoint(com.amap.api.maps.model.LatLng r12, com.amap.api.maps.model.LatLng r13, int r14) {
        /*
            r11 = this;
            double r0 = r11.pdelt_x
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Le
            double r0 = r11.pdelt_y
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L11
        Le:
            r11.initial()
        L11:
            r0 = 0
            if (r12 == 0) goto L34
            if (r13 != 0) goto L17
            goto L34
        L17:
            r1 = 1
            if (r14 >= r1) goto L1c
        L1a:
            r14 = r1
            goto L20
        L1c:
            r1 = 5
            if (r14 <= r1) goto L20
            goto L1a
        L20:
            r1 = 0
        L21:
            if (r1 >= r14) goto L34
            double r3 = r12.longitude
            double r5 = r13.longitude
            double r7 = r12.latitude
            double r9 = r13.latitude
            r2 = r11
            com.amap.api.maps.model.LatLng r0 = r2.kalmanFilter(r3, r5, r7, r9)
            int r1 = r1 + 1
            r13 = r0
            goto L21
        L34:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.sport.utils.PathSmoothTool.kalmanFilterPoint(com.amap.api.maps.model.LatLng, com.amap.api.maps.model.LatLng, int):com.amap.api.maps.model.LatLng");
    }

    private void initial() {
        this.pdelt_x = 0.001d;
        this.pdelt_y = 0.001d;
        this.mdelt_x = 5.698402909980532E-4d;
        this.mdelt_y = 5.698402909980532E-4d;
    }

    private LatLng kalmanFilter(double oldValue_x, double value_x, double oldValue_y, double value_y) {
        this.lastLocation_x = oldValue_x;
        this.currentLocation_x = value_x;
        double d2 = this.pdelt_x;
        double d3 = this.mdelt_x;
        double dSqrt = Math.sqrt((d2 * d2) + (d3 * d3)) + this.m_Q;
        this.gauss_x = dSqrt;
        double d4 = this.pdelt_x;
        double dSqrt2 = Math.sqrt((dSqrt * dSqrt) / ((dSqrt * dSqrt) + (d4 * d4))) + this.m_R;
        this.kalmanGain_x = dSqrt2;
        double d5 = this.currentLocation_x;
        double d6 = this.lastLocation_x;
        this.estimate_x = ((d5 - d6) * dSqrt2) + d6;
        double d7 = this.gauss_x;
        this.mdelt_x = Math.sqrt((1.0d - dSqrt2) * d7 * d7);
        this.lastLocation_y = oldValue_y;
        this.currentLocation_y = value_y;
        double d8 = this.pdelt_y;
        double d9 = this.mdelt_y;
        double dSqrt3 = Math.sqrt((d8 * d8) + (d9 * d9)) + this.m_Q;
        this.gauss_y = dSqrt3;
        double d10 = this.pdelt_y;
        double dSqrt4 = Math.sqrt((dSqrt3 * dSqrt3) / ((dSqrt3 * dSqrt3) + (d10 * d10))) + this.m_R;
        this.kalmanGain_y = dSqrt4;
        double d11 = this.currentLocation_y;
        double d12 = this.lastLocation_y;
        this.estimate_y = ((d11 - d12) * dSqrt4) + d12;
        double d13 = 1.0d - dSqrt4;
        double d14 = this.gauss_y;
        this.mdelt_y = Math.sqrt(d13 * d14 * d14);
        return new LatLng(this.estimate_y, this.estimate_x);
    }

    private List<LatLng> reducerVerticalThreshold(List<LatLng> inPoints, float threshHold) {
        if (inPoints == null) {
            return null;
        }
        if (inPoints.size() <= 2) {
            return inPoints;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < inPoints.size(); i2++) {
            LatLng lastLocation = getLastLocation(arrayList);
            LatLng latLng = inPoints.get(i2);
            if (lastLocation == null || i2 == inPoints.size() - 1) {
                arrayList.add(latLng);
            } else if (calculateDistanceFromPoint(latLng, lastLocation, inPoints.get(i2 + 1)) > threshHold) {
                arrayList.add(latLng);
            }
        }
        return arrayList;
    }

    private static LatLng getLastLocation(List<LatLng> oneGraspList) {
        if (oneGraspList == null || oneGraspList.size() == 0) {
            return null;
        }
        return oneGraspList.get(oneGraspList.size() - 1);
    }

    private static double calculateDistanceFromPoint(LatLng p, LatLng lineBegin, LatLng lineEnd) {
        double d2;
        double d3;
        double d4 = p.longitude - lineBegin.longitude;
        double d5 = p.latitude - lineBegin.latitude;
        double d6 = lineEnd.longitude - lineBegin.longitude;
        double d7 = lineEnd.latitude - lineBegin.latitude;
        double d8 = ((d4 * d6) + (d5 * d7)) / ((d6 * d6) + (d7 * d7));
        if (d8 < 0.0d || (lineBegin.longitude == lineEnd.longitude && lineBegin.latitude == lineEnd.latitude)) {
            d2 = lineBegin.longitude;
            d3 = lineBegin.latitude;
        } else if (d8 > 1.0d) {
            d2 = lineEnd.longitude;
            d3 = lineEnd.latitude;
        } else {
            double d9 = lineBegin.longitude + (d6 * d8);
            d3 = lineBegin.latitude + (d8 * d7);
            d2 = d9;
        }
        return AMapUtils.calculateLineDistance(p, new LatLng(d3, d2));
    }

    private List<LatLng> reduceNoisePoint(List<LatLng> inPoints, float threshHold) {
        if (inPoints == null) {
            return null;
        }
        if (inPoints.size() <= 2) {
            return inPoints;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < inPoints.size(); i2++) {
            LatLng lastLocation = getLastLocation(arrayList);
            LatLng latLng = inPoints.get(i2);
            if (lastLocation == null || i2 == inPoints.size() - 1) {
                arrayList.add(latLng);
            } else if (calculateDistanceFromPoint(latLng, lastLocation, inPoints.get(i2 + 1)) < threshHold) {
                arrayList.add(latLng);
            }
        }
        return arrayList;
    }
}
