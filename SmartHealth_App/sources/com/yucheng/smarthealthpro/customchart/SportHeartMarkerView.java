package com.yucheng.smarthealthpro.customchart;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.TypedValue;
import android.widget.TextView;
import com.google.android.material.timepicker.TimeModel;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.charts.Chart;
import com.yucheng.smarthealthpro.customchart.components.MarkerView;
import com.yucheng.smarthealthpro.customchart.data.BarEntry;
import com.yucheng.smarthealthpro.customchart.data.CandleEntry;
import com.yucheng.smarthealthpro.customchart.data.Entry;
import com.yucheng.smarthealthpro.customchart.highlight.Highlight;
import com.yucheng.smarthealthpro.customchart.klinechart.BloodPressureBean;
import com.yucheng.smarthealthpro.customchart.sleep.DeepSleepInfo;
import com.yucheng.smarthealthpro.customchart.sleep.LightSleepInfo;
import com.yucheng.smarthealthpro.customchart.sleep.SoberTimeInfo;
import com.yucheng.smarthealthpro.customchart.temperature.TemperBean;
import com.yucheng.smarthealthpro.customchart.utils.MPPointF;
import com.yucheng.smarthealthpro.customchart.utils.Utils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.phy.bean.PhyDayInfo;
import com.yucheng.smarthealthpro.home.activity.running.bean.StepBean;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepDayInfo;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class SportHeartMarkerView extends MarkerView {
    private final int ARROW_HEIGHT;
    private final float ARROW_OFFSET;
    private final int ARROW_WIDTH;
    private final float BG_CORNER;
    private final int DEFAULT_INDICATOR_COLOR;
    private int beginTimes;
    private Bitmap bitmapForDot;
    private int bitmapHeight;
    private int bitmapWidth;
    private int centerX;
    DecimalFormat df;
    private int endTimes;
    private List<BloodPressureBean> mBloodPressureBean;
    private String mBloodSugarUnit;
    private List<DeepSleepInfo> mDeepSleepInfo;
    private List<DeepSleepInfo> mDeepSleepList;
    private List<LightSleepInfo> mLightSleepInfo;
    private List<LightSleepInfo> mLightSleepList;
    private List<PhyDayInfo> mPhyInfo;
    private List<SleepDayInfo> mSleepInfo;
    private List<SoberTimeInfo> mSoberTimeInfo;
    private List<SoberTimeInfo> mSoberTimeList;
    private List<StepBean> mStepBean;
    private String mTempUnit;
    private List<TemperBean> mTemperBean;
    private String mUricAcitUnit;
    private final TextView tvContent;

    public SportHeartMarkerView(Context context, int layoutResource, List<TemperBean> mTemperBean, List<BloodPressureBean> mBloodPressureBean, List<SleepDayInfo> mSleepInfo, List<StepBean> mStepBean) {
        super(context, layoutResource);
        this.DEFAULT_INDICATOR_COLOR = -3218945;
        this.ARROW_HEIGHT = dp2px(10);
        this.ARROW_WIDTH = dp2px(15);
        this.ARROW_OFFSET = dp2px(2);
        this.BG_CORNER = dp2px(2);
        this.df = new DecimalFormat("0.00");
        this.mTempUnit = " ℃";
        this.mTemperBean = mTemperBean;
        this.mBloodPressureBean = mBloodPressureBean;
        this.mSleepInfo = mSleepInfo;
        this.mStepBean = mStepBean;
        TextView textView = (TextView) findViewById(R.id.tvContent);
        this.tvContent = textView;
        int iDp2px = (int) DpUtil.dp2px(getContext(), 10.0f);
        textView.setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
        String str = (String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.TEMP_UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.TEMP_ISO)) && str != null && str.equals(Constant.SpConstValue.TEMP_INCH)) {
            this.mTempUnit = Constant.SpConstValue.TEMP_INCH;
        } else {
            this.mTempUnit = Constant.SpConstValue.TEMP_ISO;
        }
        String str2 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "");
        if (str2 != null && str2.equals("mg/dL")) {
            this.mBloodSugarUnit = context.getString(R.string.blood_sugar_unit_2);
        } else {
            this.mBloodSugarUnit = context.getString(R.string.blood_sugar_unit_1);
        }
        String str3 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.URIC_ACID_UNIT, "");
        if (str3 != null && str3.equals("mg/dL")) {
            this.mUricAcitUnit = context.getString(R.string.uric_acid_unit_2);
        } else {
            this.mUricAcitUnit = context.getString(R.string.uric_acid_unit_1);
        }
    }

    public SportHeartMarkerView(Context context, int layoutResource, List<TemperBean> mTemperBean, List<BloodPressureBean> mBloodPressureBean, List<SleepDayInfo> mSleepInfo, List<StepBean> mStepBean, List<PhyDayInfo> mPhyInfo) {
        super(context, layoutResource);
        this.DEFAULT_INDICATOR_COLOR = -3218945;
        this.ARROW_HEIGHT = dp2px(10);
        this.ARROW_WIDTH = dp2px(15);
        this.ARROW_OFFSET = dp2px(2);
        this.BG_CORNER = dp2px(2);
        this.df = new DecimalFormat("0.00");
        this.mTempUnit = " ℃";
        this.mTemperBean = mTemperBean;
        this.mBloodPressureBean = mBloodPressureBean;
        this.mSleepInfo = mSleepInfo;
        this.mStepBean = mStepBean;
        this.mPhyInfo = mPhyInfo;
        TextView textView = (TextView) findViewById(R.id.tvContent);
        this.tvContent = textView;
        int iDp2px = (int) DpUtil.dp2px(getContext(), 10.0f);
        textView.setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
        String str = (String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.TEMP_UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.TEMP_ISO)) && str != null && str.equals(Constant.SpConstValue.TEMP_INCH)) {
            this.mTempUnit = Constant.SpConstValue.TEMP_INCH;
        } else {
            this.mTempUnit = Constant.SpConstValue.TEMP_ISO;
        }
        String str2 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, "");
        if (str2 != null && str2.equals("mg/dL")) {
            this.mBloodSugarUnit = context.getString(R.string.blood_sugar_unit_2);
        } else {
            this.mBloodSugarUnit = context.getString(R.string.blood_sugar_unit_1);
        }
        String str3 = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.URIC_ACID_UNIT, "");
        if (str3 != null && str3.equals("mg/dL")) {
            this.mUricAcitUnit = context.getString(R.string.uric_acid_unit_2);
        } else {
            this.mUricAcitUnit = context.getString(R.string.uric_acid_unit_1);
        }
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContent(Entry e2, Highlight highlight) {
        new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            this.tvContent.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) % 60)) + StringUtils.SPACE + ((int) e2.getY()) + "°");
        }
        super.refreshContent(e2, highlight);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentTemp(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            this.tvContent.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) % 60)) + StringUtils.SPACE + new BigDecimal(e2.getY()).setScale(1, 4).toString() + this.mTempUnit);
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentTempWeekMohth(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else if (temperBeans != null) {
            for (int i2 = 0; i2 < temperBeans.size(); i2++) {
                if (i2 == ((int) e2.getX())) {
                    this.tvContent.setText(temperBeans.get(i2).getmMonthDay() + StringUtils.SPACE + new BigDecimal(e2.getY()).setScale(1, 4).doubleValue() + this.mTempUnit);
                }
            }
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentDayBloodOxygen(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        BigDecimal bigDecimal = new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            this.tvContent.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) % 60)) + StringUtils.SPACE + bigDecimal.setScale(1, 4).toString() + " %");
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentWeekMonthBloodOxygen(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else if (temperBeans != null) {
            for (int i2 = 0; i2 < temperBeans.size(); i2++) {
                if (i2 == ((int) e2.getX())) {
                    this.tvContent.setText(temperBeans.get(i2).getmMonthDay() + StringUtils.SPACE + ((int) e2.getY()) + " %");
                }
            }
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentDayBloodSugar(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            this.tvContent.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) % 60)) + StringUtils.SPACE + e2.getY() + this.mBloodSugarUnit);
        }
        super.refreshContentDayBloodSugar(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentWeekMonthBloodSugar(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else if (temperBeans != null) {
            for (int i2 = 0; i2 < temperBeans.size(); i2++) {
                if (i2 == ((int) e2.getX())) {
                    this.tvContent.setText(temperBeans.get(i2).getmMonthDay() + StringUtils.SPACE + e2.getY() + this.mBloodSugarUnit);
                }
            }
        }
        super.refreshContentWeekMonthBloodSugar(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentDayUricAcid(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            float y = e2.getY();
            String strKeep1 = ((int) y) + "";
            if (getContext().getString(R.string.uric_acid_unit_2).equals(this.mUricAcitUnit)) {
                strKeep1 = FormatUtil.keep1(y);
            }
            this.tvContent.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) % 60)) + StringUtils.SPACE + strKeep1 + this.mUricAcitUnit);
        }
        super.refreshContentDayUricAcid(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentWeekMonthUricAcid(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        BigDecimal bigDecimal = new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            bigDecimal.setScale(0, 4).toString();
            if (temperBeans != null) {
                for (int i2 = 0; i2 < temperBeans.size(); i2++) {
                    if (i2 == ((int) e2.getX())) {
                        float y = e2.getY();
                        String strKeep1 = ((int) y) + "";
                        if (getContext().getString(R.string.uric_acid_unit_2).equals(this.mUricAcitUnit)) {
                            strKeep1 = FormatUtil.keep1(y);
                        }
                        this.tvContent.setText(temperBeans.get(i2).getmMonthDay() + StringUtils.SPACE + strKeep1 + this.mUricAcitUnit);
                    }
                }
            }
        }
        super.refreshContentDayUricAcid(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentDayHeartRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        BigDecimal bigDecimal = new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            this.tvContent.setText(TimeStampUtils.cal((int) e2.getX()) + StringUtils.SPACE + bigDecimal + " bpm");
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentWeekMonthHeartRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else if (temperBeans != null) {
            for (int i2 = 0; i2 < temperBeans.size(); i2++) {
                if (i2 == ((int) e2.getX())) {
                    this.tvContent.setText(temperBeans.get(i2).getmMonthDay() + StringUtils.SPACE + ((int) e2.getY()) + " bpm");
                }
            }
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentDayHRV(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        BigDecimal bigDecimal = new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            this.tvContent.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) % 60)) + StringUtils.SPACE + bigDecimal.setScale(0, 4).toString() + " ms");
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentWeekMonthHRV(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else if (temperBeans != null) {
            for (int i2 = 0; i2 < temperBeans.size(); i2++) {
                if (i2 == ((int) e2.getX())) {
                    this.tvContent.setText(temperBeans.get(i2).getmMonthDay() + StringUtils.SPACE + ((int) e2.getY()) + " ms");
                }
            }
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentDayRespiratoryRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        BigDecimal bigDecimal = new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else {
            this.tvContent.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) / 60)) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(((int) e2.getX()) % 60)) + StringUtils.SPACE + bigDecimal.setScale(0, 4).toString() + " rpm");
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentWeekMonthRespiratoryRate(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        new BigDecimal(e2.getY());
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else if (temperBeans != null) {
            for (int i2 = 0; i2 < temperBeans.size(); i2++) {
                if (i2 == ((int) e2.getX())) {
                    this.tvContent.setText(temperBeans.get(i2).getmMonthDay() + StringUtils.SPACE + ((int) e2.getY()) + " rpm");
                }
            }
        }
        super.refreshContentTemp(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentDayBloodPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        if (e2 instanceof CandleEntry) {
            CandleEntry candleEntry = (CandleEntry) e2;
            new BigDecimal(e2.getX());
            new BigDecimal(e2.getY());
            new BigDecimal(candleEntry.getHigh());
            new BigDecimal(candleEntry.getLow());
            BigDecimal bigDecimal = new BigDecimal(candleEntry.getOpen());
            BigDecimal bigDecimal2 = new BigDecimal(candleEntry.getClose());
            candleEntry.getmTime();
            this.tvContent.setText(candleEntry.getTimeStr() + StringUtils.SPACE + bigDecimal.setScale(0, 4).toString() + "/" + bigDecimal2.setScale(0, 4).toString());
        } else {
            this.tvContent.setText(e2.getMaxtemper() + StringUtils.SPACE + e2.getMintemper() + "" + e2.getmOpen() + StringUtils.SPACE + e2.getmClose());
        }
        super.refreshContentDayBloodPressure(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentWeekMonthBloodPressure(Entry e2, Highlight highlight, List<TemperBean> temperBeans) {
        if (e2 instanceof CandleEntry) {
            CandleEntry candleEntry = (CandleEntry) e2;
            new BigDecimal(e2.getX());
            new BigDecimal(e2.getY());
            new BigDecimal(candleEntry.getHigh());
            new BigDecimal(candleEntry.getLow());
            BigDecimal bigDecimal = new BigDecimal(candleEntry.getOpen());
            BigDecimal bigDecimal2 = new BigDecimal(candleEntry.getClose());
            candleEntry.getmTime();
            this.tvContent.setText(candleEntry.getTimeStr() + StringUtils.SPACE + bigDecimal2.setScale(0, 4).toString() + "/" + bigDecimal.setScale(0, 4).toString());
        } else {
            this.tvContent.setText(e2.getMaxtemper() + StringUtils.SPACE + e2.getMintemper() + "" + e2.getmOpen() + StringUtils.SPACE + e2.getmClose());
        }
        super.refreshContentWeekMonthBloodPressure(e2, highlight, temperBeans);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentSleep(Entry e2, Highlight highlight, List<SleepDayInfo> mSleepInfo) throws Resources.NotFoundException {
        if (mSleepInfo != null && mSleepInfo.size() > 0) {
            int i2 = mSleepInfo.get(0).beginTime;
            for (int i3 = 0; i3 < mSleepInfo.size(); i3++) {
                int i4 = mSleepInfo.get(i3).beginTime - i2;
                int i5 = mSleepInfo.get(i3).endTime - i2;
                if (i4 < 0) {
                    i4 += 1440;
                }
                if (i5 < 0) {
                    i5 += 1440;
                }
                if (((int) e2.getX()) >= i4 && ((int) e2.getX()) < i5) {
                    String string = getResources().getString(R.string.wakening);
                    if (e2.getY() == 5.0f) {
                        string = getResources().getString(R.string.deep_sleep);
                    } else if (e2.getY() == 10.0f) {
                        string = getResources().getString(R.string.light_sleep);
                    } else if (e2.getY() == 15.0f) {
                        string = getResources().getString(R.string.sleep_rem_time);
                    } else if (e2.getY() == 20.0f) {
                        string = getResources().getString(R.string.wakening);
                    }
                    this.tvContent.setText(mSleepInfo.get(i3).beginTimes + "-" + mSleepInfo.get(i3).endTimes + StringUtils.SPACE + string);
                } else {
                    int i6 = i3 + 1;
                    if (i6 < mSleepInfo.size()) {
                        int i7 = mSleepInfo.get(i6).beginTime - i2;
                        if (i7 < 0) {
                            i7 += 1440;
                        }
                        if (((int) e2.getX()) > i5 && ((int) e2.getX()) < i7) {
                            this.tvContent.setText(mSleepInfo.get(i3).endTimes + "-" + mSleepInfo.get(i6).beginTimes + StringUtils.SPACE + getResources().getString(R.string.wakening));
                        }
                    }
                }
            }
        }
        super.refreshContentSleep(e2, highlight, mSleepInfo);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentSleepWeekMonth(Entry e2, Highlight highlight, Chart.MarkerLabel markerLabel) {
        ArrayList<String> pastStringArray;
        String str;
        Calendar calendar = Calendar.getInstance();
        String str2 = calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5);
        if (markerLabel == Chart.MarkerLabel.SLEEP_WEEK_CHART) {
            pastStringArray = YearToDayListUtils.getPastStringArray(str2, 6);
        } else {
            pastStringArray = YearToDayListUtils.getPastStringArray(str2, 29);
        }
        if (e2 instanceof BarEntry) {
            BarEntry barEntry = (BarEntry) e2;
            if (barEntry.getYVals() != null) {
                if (barEntry.getYVals()[2] == 0.0f) {
                    str = "";
                } else {
                    str = getContext().getString(R.string.sleep_rem_time) + StringUtils.SPACE + new BigDecimal(barEntry.getYVals()[2]).setScale(1, 4).doubleValue() + StringUtils.SPACE + getContext().getString(R.string.time_hour_unit) + StringUtils.LF;
                }
                this.tvContent.setText(TimeStampUtils.dateForStringDates(TimeStampUtils.stringForDateDay(pastStringArray.get((int) barEntry.getX()))) + StringUtils.LF + str + getContext().getString(R.string.light_sleep) + StringUtils.SPACE + new BigDecimal(barEntry.getYVals()[1]).setScale(1, 4).doubleValue() + StringUtils.SPACE + getContext().getString(R.string.time_hour_unit) + StringUtils.LF + getContext().getString(R.string.deep_sleep) + StringUtils.SPACE + new BigDecimal(barEntry.getYVals()[0]).setScale(1, 4).doubleValue() + StringUtils.SPACE + getContext().getString(R.string.time_hour_unit));
            } else {
                this.tvContent.setText(Utils.formatNumber(barEntry.getY(), 0, true));
            }
        } else {
            this.tvContent.setText(Utils.formatNumber(e2.getY(), 0, true));
        }
        super.refreshContentSleepWeekMonth(e2, highlight, markerLabel);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void refreshContentStep(Entry e2, Highlight highlight, List<StepBean> mStepBean) throws Resources.NotFoundException {
        if (e2 instanceof CandleEntry) {
            this.tvContent.setText(Utils.formatNumber(((CandleEntry) e2).getHigh(), 0, true));
        } else if (mStepBean != null) {
            for (int i2 = 0; i2 < mStepBean.size(); i2++) {
                if (i2 == ((int) e2.getX())) {
                    String string = mStepBean.get(i2).unit;
                    if (string == null) {
                        string = getResources().getString(R.string.step_unit);
                    }
                    this.tvContent.setText(mStepBean.get(i2).getBeginhhmm() + StringUtils.SPACE + e2.getY() + string);
                }
            }
        }
        super.refreshContentStep(e2, highlight, mStepBean);
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    @Override // com.yucheng.smarthealthpro.customchart.components.MarkerView, com.yucheng.smarthealthpro.customchart.components.IMarker
    public void draw(Canvas canvas, float posX, float posY) {
        if (getChartView() == null) {
            super.draw(canvas, posX, posY);
            return;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(-3218945);
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setColor(-3218945);
        float width = getWidth();
        float height = getHeight();
        int iSave = canvas.save();
        canvas.translate(posX, (this.ARROW_HEIGHT * 2) + height);
        Path path = new Path();
        int i2 = this.ARROW_HEIGHT;
        int i3 = this.bitmapHeight;
        if (posY < i2 + height + (i3 / 2.0f)) {
            canvas.translate(0.0f, i2 + height + (i3 / 2.0f));
            float f2 = width / 2.0f;
            if (posX > r0.getWidth() - f2) {
                canvas.translate(-(f2 - (r0.getWidth() - posX)), 0.0f);
                float f3 = this.ARROW_OFFSET;
                path.moveTo((f2 - (r0.getWidth() - posX)) - f3, -(this.ARROW_HEIGHT + height + f3));
            } else if (posX > f2) {
                path.moveTo(0.0f, -(this.ARROW_HEIGHT + height));
            } else {
                float f4 = f2 - posX;
                canvas.translate(f4, 0.0f);
                float f5 = this.ARROW_OFFSET;
                path.moveTo((-f4) - f5, -(this.ARROW_HEIGHT + height + f5));
            }
            float f6 = (-width) / 2.0f;
            float f7 = -height;
            RectF rectF = new RectF(f6, f7, f2, 0.0f);
            canvas.drawPath(path, paint2);
            float f8 = this.BG_CORNER;
            canvas.drawRoundRect(rectF, f8, f8, paint);
            canvas.translate(f6, f7);
        } else {
            canvas.translate(0.0f, ((-height) - i2) - (i3 / 2.0f));
            float f9 = width / 2.0f;
            if (posX < f9) {
                float f10 = f9 - posX;
                canvas.translate(f10, 0.0f);
                float f11 = this.ARROW_OFFSET;
                path.moveTo((-f10) + f11, this.ARROW_HEIGHT + height + f11);
            } else if (posX > r0.getWidth() - f9) {
                canvas.translate(-(f9 - (r0.getWidth() - posX)), 0.0f);
                float width2 = f9 - (r0.getWidth() - posX);
                float f12 = this.ARROW_OFFSET;
                path.moveTo(width2 + f12, this.ARROW_HEIGHT + height + f12);
            } else {
                path.moveTo(0.0f, this.ARROW_HEIGHT + height);
            }
            float f13 = (-width) / 2.0f;
            RectF rectF2 = new RectF(f13, 0.0f, f9, height);
            canvas.drawPath(path, paint2);
            float f14 = this.BG_CORNER;
            canvas.drawRoundRect(rectF2, f14, f14, paint);
            canvas.translate(f13, 0.0f);
        }
        draw(canvas);
        canvas.restoreToCount(iSave);
    }

    private int dp2px(int dpValues) {
        return (int) TypedValue.applyDimension(1, dpValues, getResources().getDisplayMetrics());
    }
}
