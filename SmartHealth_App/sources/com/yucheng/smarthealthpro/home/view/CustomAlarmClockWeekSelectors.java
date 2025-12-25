package com.yucheng.smarthealthpro.home.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import java.util.List;

/* loaded from: classes5.dex */
public class CustomAlarmClockWeekSelectors {
    private String mAlternativeDate;
    private OnOneSelectorsDataListener mOnOneSelectorsDataListener;
    private OnThreeSelectorsDataListener mOnThreeSelectorsDataListener;
    private OnTwoSelectorsDataListener mOnTwoSelectorsDataListener;
    private OptionsPickerView pvBpOptions;
    private String week;
    private int cb_monday_num = 1;
    private int cb_tuesday_num = 1;
    private int cb_wednesday_num = 1;
    private int cb_thursday_num = 1;
    private int cb_friday_num = 1;
    private int cb_saturday_num = 0;
    private int cb_weekday_num = 0;

    public interface OnOneSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, int optionsOne);
    }

    public interface OnThreeSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, String twoValue, String threeValue, int optionsOne, int optionsTwo, int optionsThree);

        void saveWeek(String week);
    }

    public interface OnTwoSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo);
    }

    public enum SelectorsDataNum {
        ONE,
        TWO,
        THREE
    }

    public CustomAlarmClockWeekSelectors setOnOneSelectorsDataListener(OnOneSelectorsDataListener listener) {
        this.mOnOneSelectorsDataListener = listener;
        return this;
    }

    public CustomAlarmClockWeekSelectors setOnTwoSelectorsDataListener(OnTwoSelectorsDataListener listener) {
        this.mOnTwoSelectorsDataListener = listener;
        return this;
    }

    public CustomAlarmClockWeekSelectors setOnThreeSelectorsDataListener(OnThreeSelectorsDataListener listener) {
        this.mOnThreeSelectorsDataListener = listener;
        return this;
    }

    public void BpLevelPicker(final List<String> firstList, final List<String> secondList, List<String> thirdList, int firstNum, int secondNum, int thirdNum, String firstLable, String secondLable, String thirdLable, Boolean isCenterLabel, final SelectorsDataNum selectorsDataNum, boolean isVisibilityDelete, String alternativeDate, final Context context) {
        this.mAlternativeDate = alternativeDate;
        Log.i("AAAAAAA", "==AAAAABpLevelPicker==" + this.mAlternativeDate);
        String str = this.mAlternativeDate;
        if (str != null) {
            char[] charArray = str.toCharArray();
            this.cb_monday_num = Integer.parseInt(charArray[0] + "");
            this.cb_tuesday_num = Integer.parseInt(charArray[1] + "");
            this.cb_wednesday_num = Integer.parseInt(charArray[2] + "");
            this.cb_thursday_num = Integer.parseInt(charArray[3] + "");
            this.cb_friday_num = Integer.parseInt(charArray[4] + "");
            this.cb_saturday_num = Integer.parseInt(charArray[5] + "");
            this.cb_weekday_num = Integer.parseInt(charArray[6] + "");
        }
        Log.i("AAAAAAA", "===week111===" + (this.cb_monday_num + "" + this.cb_tuesday_num + "" + this.cb_wednesday_num + "" + this.cb_thursday_num + "" + this.cb_friday_num + "" + this.cb_saturday_num + "" + this.cb_weekday_num + ""));
        this.pvBpOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.3
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (selectorsDataNum == SelectorsDataNum.ONE) {
                    CustomAlarmClockWeekSelectors.this.mOnOneSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), options1);
                    return;
                }
                if (selectorsDataNum == SelectorsDataNum.TWO) {
                    CustomAlarmClockWeekSelectors.this.mOnTwoSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), (String) secondList.get(options2), options1, options2);
                } else if (selectorsDataNum == SelectorsDataNum.THREE) {
                    CustomAlarmClockWeekSelectors.this.week = CustomAlarmClockWeekSelectors.this.cb_monday_num + "" + CustomAlarmClockWeekSelectors.this.cb_tuesday_num + "" + CustomAlarmClockWeekSelectors.this.cb_wednesday_num + "" + CustomAlarmClockWeekSelectors.this.cb_thursday_num + "" + CustomAlarmClockWeekSelectors.this.cb_friday_num + "" + CustomAlarmClockWeekSelectors.this.cb_saturday_num + "" + CustomAlarmClockWeekSelectors.this.cb_weekday_num + "";
                    Log.i("BBBBBBB", "===week===" + CustomAlarmClockWeekSelectors.this.week);
                    CustomAlarmClockWeekSelectors.this.mOnThreeSelectorsDataListener.saveWeek(CustomAlarmClockWeekSelectors.this.week);
                }
            }
        }).setLayoutRes(R.layout.layout_alarm_clock_week_selector, new CustomListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public void customLayout(View v) {
                LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.ll_selector);
                TextView textView = (TextView) v.findViewById(R.id.tv_cancel);
                TextView textView2 = (TextView) v.findViewById(R.id.tv_confirm);
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.cb_monday);
                CheckBox checkBox2 = (CheckBox) v.findViewById(R.id.cb_tuesday);
                CheckBox checkBox3 = (CheckBox) v.findViewById(R.id.cb_wednesday);
                CheckBox checkBox4 = (CheckBox) v.findViewById(R.id.cb_thursday);
                CheckBox checkBox5 = (CheckBox) v.findViewById(R.id.cb_friday);
                CheckBox checkBox6 = (CheckBox) v.findViewById(R.id.cb_saturday);
                CheckBox checkBox7 = (CheckBox) v.findViewById(R.id.cb_weekday);
                LinearLayout linearLayout2 = (LinearLayout) v.findViewById(R.id.ll_bottom);
                ViewGroup.LayoutParams layoutParams = linearLayout2.getLayoutParams();
                layoutParams.height = AppScreenMgr.getNavigationAreaHeight(context);
                linearLayout2.setLayoutParams(layoutParams);
                if (CustomAlarmClockWeekSelectors.this.cb_monday_num == 1) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
                if (CustomAlarmClockWeekSelectors.this.cb_tuesday_num == 1) {
                    checkBox2.setChecked(true);
                } else {
                    checkBox2.setChecked(false);
                }
                if (CustomAlarmClockWeekSelectors.this.cb_wednesday_num == 1) {
                    checkBox3.setChecked(true);
                } else {
                    checkBox3.setChecked(false);
                }
                if (CustomAlarmClockWeekSelectors.this.cb_thursday_num == 1) {
                    checkBox4.setChecked(true);
                } else {
                    checkBox4.setChecked(false);
                }
                if (CustomAlarmClockWeekSelectors.this.cb_friday_num == 1) {
                    checkBox5.setChecked(true);
                } else {
                    checkBox5.setChecked(false);
                }
                if (CustomAlarmClockWeekSelectors.this.cb_saturday_num == 1) {
                    checkBox6.setChecked(true);
                } else {
                    checkBox6.setChecked(false);
                }
                if (CustomAlarmClockWeekSelectors.this.cb_weekday_num == 1) {
                    checkBox7.setChecked(true);
                } else {
                    checkBox7.setChecked(false);
                }
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomAlarmClockWeekSelectors.this.pvBpOptions.dismiss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomAlarmClockWeekSelectors.this.pvBpOptions.returnData();
                        CustomAlarmClockWeekSelectors.this.pvBpOptions.dismiss();
                    }
                });
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.4
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CustomAlarmClockWeekSelectors.this.cb_monday_num = 1;
                        } else {
                            CustomAlarmClockWeekSelectors.this.cb_monday_num = 0;
                        }
                    }
                });
                checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.5
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CustomAlarmClockWeekSelectors.this.cb_tuesday_num = 1;
                        } else {
                            CustomAlarmClockWeekSelectors.this.cb_tuesday_num = 0;
                        }
                    }
                });
                checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.6
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CustomAlarmClockWeekSelectors.this.cb_wednesday_num = 1;
                        } else {
                            CustomAlarmClockWeekSelectors.this.cb_wednesday_num = 0;
                        }
                    }
                });
                checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.7
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CustomAlarmClockWeekSelectors.this.cb_thursday_num = 1;
                        } else {
                            CustomAlarmClockWeekSelectors.this.cb_thursday_num = 0;
                        }
                    }
                });
                checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.8
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CustomAlarmClockWeekSelectors.this.cb_friday_num = 1;
                        } else {
                            CustomAlarmClockWeekSelectors.this.cb_friday_num = 0;
                        }
                    }
                });
                checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.9
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CustomAlarmClockWeekSelectors.this.cb_saturday_num = 1;
                        } else {
                            CustomAlarmClockWeekSelectors.this.cb_saturday_num = 0;
                        }
                    }
                });
                checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.2.10
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            CustomAlarmClockWeekSelectors.this.cb_weekday_num = 1;
                        } else {
                            CustomAlarmClockWeekSelectors.this.cb_weekday_num = 0;
                        }
                    }
                });
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.1
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
            }
        }).setTextColorCenter(context.getResources().getColor(R.color.button_start_color)).setContentTextSize(24).setLineSpacingMultiplier(2.0f).setLabels(firstLable, secondLable, thirdLable).isCenterLabel(isCenterLabel.booleanValue()).setDividerType(WheelView.DividerType.FILL).setDividerColor(context.getResources().getColor(R.color.button_start_color)).setItemVisibleCount(5).build();
        if (selectorsDataNum == SelectorsDataNum.ONE) {
            this.pvBpOptions.setPicker(firstList);
            this.pvBpOptions.setSelectOptions(firstNum);
        } else if (selectorsDataNum == SelectorsDataNum.TWO) {
            this.pvBpOptions.setNPicker(firstList, secondList, null);
            this.pvBpOptions.setSelectOptions(firstNum, secondNum);
        } else if (selectorsDataNum == SelectorsDataNum.THREE) {
            this.pvBpOptions.setNPicker(firstList, secondList, thirdList);
            this.pvBpOptions.setSelectOptions(firstNum, secondNum, thirdNum);
        }
        this.pvBpOptions.show();
    }

    private static int dipToPx(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
