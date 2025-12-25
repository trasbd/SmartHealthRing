package com.yucheng.smarthealthpro.me.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes5.dex */
public class CustomYearToDateSelectors {
    private OnOneSelectorsDataListener mOnOneSelectorsDataListener;
    private PickerOptions mPickerOptions;
    private TimePickerView mTimePickerView;
    private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();

    public interface OnOneSelectorsDataListener {
        void getSelectorsDataClick(Date date);
    }

    public CustomYearToDateSelectors setOnOneSelectorsDataListener(OnOneSelectorsDataListener listener) {
        this.mOnOneSelectorsDataListener = listener;
        return this;
    }

    public void BpLevelPicker(String curr_date_value, String start_date_value, String end_date_value, final Context context) {
        if (start_date_value == null) {
            this.startDate.set(2000, 0, 1);
        } else {
            Date dateStringForDateDay = TimeStampUtils.stringForDateDay(start_date_value);
            if (dateStringForDateDay != null) {
                this.startDate.setTime(dateStringForDateDay);
            }
        }
        if (end_date_value == null) {
            this.endDate = Calendar.getInstance();
        } else {
            Date dateStringForDateDay2 = TimeStampUtils.stringForDateDay(end_date_value);
            if (dateStringForDateDay2 != null) {
                this.endDate.setTime(dateStringForDateDay2);
            }
        }
        Calendar calendar = Calendar.getInstance();
        Date dateStringForDateDay3 = TimeStampUtils.stringForDateDay(curr_date_value);
        if (dateStringForDateDay3 != null) {
            calendar.setTime(dateStringForDateDay3);
        }
        TimePickerView timePickerViewBuild = new TimePickerBuilder(context, new OnTimeSelectListener() { // from class: com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors.2
            @Override // com.bigkoo.pickerview.listener.OnTimeSelectListener
            public void onTimeSelect(Date date, View v) {
                CustomYearToDateSelectors.this.mOnOneSelectorsDataListener.getSelectorsDataClick(date);
            }
        }).setLayoutRes(R.layout.layout_year_to_date_selector, new CustomListener() { // from class: com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors.1
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public void customLayout(View v) {
                TextView textView = (TextView) v.findViewById(R.id.tv_cancel);
                TextView textView2 = (TextView) v.findViewById(R.id.tv_confirm);
                LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.ll_bottom);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomYearToDateSelectors.this.mTimePickerView.dismiss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.view.CustomYearToDateSelectors.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) throws ParseException {
                        CustomYearToDateSelectors.this.mTimePickerView.returnData();
                        CustomYearToDateSelectors.this.mTimePickerView.dismiss();
                    }
                });
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.height = AppScreenMgr.getNavigationAreaHeight(context);
                linearLayout.setLayoutParams(layoutParams);
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setOutSideCancelable(true).isCyclic(true).setDate(calendar).setRangDate(this.startDate, this.endDate).setLabel("", "", "", "", "", "").isDialog(false).setTextColorCenter(context.getResources().getColor(R.color.button_start_color)).setContentTextSize(24).setLineSpacingMultiplier(2.0f).isCenterLabel(false).setDividerType(WheelView.DividerType.FILL).setDividerColor(context.getResources().getColor(R.color.button_start_color)).setItemVisibleCount(5).build();
        this.mTimePickerView = timePickerViewBuild;
        timePickerViewBuild.show();
    }
}
