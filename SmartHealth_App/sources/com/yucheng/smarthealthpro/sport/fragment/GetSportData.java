package com.yucheng.smarthealthpro.sport.fragment;

import com.google.gson.Gson;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class GetSportData {
    private List<SportHisListBean> mSportHisListBean;

    public void getSportData() {
        YCBTClient.healthHistoryData(Constants.DATATYPE.Health_HistorySportMode, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.sport.fragment.GetSportData.1
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                GetSportData.this.savaMotionPatternData(hashMap);
            }
        });
    }

    public List<SportHisListBean> savaMotionPatternData(HashMap resultMap) {
        MotionPatternBean motionPatternBean = (MotionPatternBean) new Gson().fromJson(String.valueOf(resultMap), MotionPatternBean.class);
        this.mSportHisListBean = new ArrayList();
        List<MotionPatternBean.DataBean> data = motionPatternBean.getData();
        ArrayList arrayList = new ArrayList();
        SportHisListBean sportHisListBean = new SportHisListBean();
        long j2 = 0;
        int i2 = 0;
        long endTime = 0;
        float sportHeartRate = 0.0f;
        float f2 = 0.0f;
        while (i2 < data.size()) {
            MotionPatternBean.DataBean dataBean = data.get(i2);
            float f3 = 1.0f;
            if (endTime != j2 && Math.abs(dataBean.getStartTime() - endTime) < 3000) {
                sportHisListBean.setRunTime((int) (sportHisListBean.getRunTime() + ((dataBean.getEndTime() - endTime) / 1000)));
                sportHisListBean.setDistance(dataBean.sportDistances);
                sportHisListBean.setSportStep(dataBean.sportSteps);
                sportHisListBean.setCalorie(dataBean.sportCalories);
                sportHisListBean.setHeart(dataBean.sportHeartRate);
                float f4 = dataBean.sportDistances;
                if (dataBean.sportDistances > 0) {
                    int runTime = (int) ((1.0f / f4) * sportHisListBean.getRunTime());
                    int i3 = runTime % 60;
                    sportHisListBean.setMinkm((((runTime - i3) / 60) % 60) + "'" + i3 + "\"");
                }
                if (sportHisListBean.getRunTime() > 0) {
                    sportHisListBean.setKmh((f4 / 1000.0f) / (sportHisListBean.getRunTime() / 3600.0f));
                }
                if (dataBean.getSportHeartRate() >= 40 && dataBean.getSportHeartRate() <= 220) {
                    sportHeartRate += dataBean.getSportHeartRate();
                    f2 += 1.0f;
                }
            } else {
                if (arrayList.size() > 0 && sportHisListBean.getRunTime() < 60) {
                    arrayList.remove(sportHisListBean);
                }
                if (arrayList.size() > 0) {
                    sportHisListBean.setHeart((int) (sportHeartRate / f2));
                    sportHeartRate = 0.0f;
                    f2 = 0.0f;
                }
                sportHisListBean = new SportHisListBean();
                sportHisListBean.setBeginDate(dataBean.getStartTime());
                sportHisListBean.setRunTime((int) ((dataBean.getEndTime() - dataBean.getStartTime()) / 1000));
                sportHisListBean.setDistance(dataBean.sportDistances);
                sportHisListBean.setSportStep(dataBean.sportSteps);
                sportHisListBean.setCalorie(dataBean.sportCalories);
                if (dataBean.getSportHeartRate() < 40 || dataBean.getSportHeartRate() > 220) {
                    f3 = f2;
                } else {
                    sportHeartRate = dataBean.getSportHeartRate();
                }
                arrayList.add(sportHisListBean);
                f2 = f3;
            }
            endTime = dataBean.getEndTime();
            i2++;
            j2 = 0;
        }
        this.mSportHisListBean.addAll(arrayList);
        return this.mSportHisListBean;
    }

    public void deleteSportData() {
        YCBTClient.deleteHealthHistoryData(Constants.DATATYPE.Health_DeleteSportMode, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.sport.fragment.GetSportData.2
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
            }
        });
    }

    public class MotionPatternBean {
        public int code;
        public List<DataBean> data;
        public int dataType;

        public MotionPatternBean() {
        }

        public class DataBean {
            public long endTime;
            public int maxHeartRate;
            public int minHeartRate;
            public int sportCalories;
            public int sportDistances;
            public int sportHeartRate;
            public int sportMode;
            public int sportSteps;
            public long sportTime;
            public int startMethod;
            public long startTime;

            public DataBean() {
            }

            public long getStartTime() {
                return this.startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return this.endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getSportSteps() {
                return this.sportSteps;
            }

            public void setSportSteps(int sportSteps) {
                this.sportSteps = sportSteps;
            }

            public int getSportDistances() {
                return this.sportDistances;
            }

            public void setSportDistances(int sportDistances) {
                this.sportDistances = sportDistances;
            }

            public int getSportCalories() {
                return this.sportCalories;
            }

            public void setSportCalories(int sportCalories) {
                this.sportCalories = sportCalories;
            }

            public int getSportMode() {
                return this.sportMode;
            }

            public void setSportMode(int sportMode) {
                this.sportMode = sportMode;
            }

            public int getStartMethod() {
                return this.startMethod;
            }

            public void setStartMethod(int startMethod) {
                this.startMethod = startMethod;
            }

            public int getSportHeartRate() {
                return this.sportHeartRate;
            }

            public void setSportHeartRate(int sportHeartRate) {
                this.sportHeartRate = sportHeartRate;
            }

            public long getSportTime() {
                return this.sportTime;
            }

            public void setSportTime(long sportTime) {
                this.sportTime = sportTime;
            }

            public int getMinHeartRate() {
                return this.minHeartRate;
            }

            public void setMinHeartRate(int minHeartRate) {
                this.minHeartRate = minHeartRate;
            }

            public int getMaxHeartRate() {
                return this.maxHeartRate;
            }

            public void setMaxHeartRate(int maxHeartRate) {
                this.maxHeartRate = maxHeartRate;
            }
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<DataBean> getData() {
            return this.data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public int getDataType() {
            return this.dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }
    }

    public class SportHisListBean implements Serializable {
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
}
