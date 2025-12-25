package com.yucheng.smarthealthpro.home.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class CustomAlarmClockSelectors {
    private String alarmHour;
    private String alarmMin;
    private String mAlternativeDate;
    private String mIsSwitch;
    private OnOneSelectorsDataListener mOnOneSelectorsDataListener;
    private OnThreeSelectorsDataListener mOnThreeSelectorsDataListener;
    private OnTwoSelectorsDataListener mOnTwoSelectorsDataListener;
    private String mWeek = "1111100";
    private OptionsPickerView pvBpOptions;
    private TextView tv_label;

    public interface OnOneSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, int optionsOne);
    }

    public interface OnThreeSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, String twoValue, String threeValue, int optionsOne, int optionsTwo, int optionsThree);
    }

    public interface OnTwoSelectorsDataListener {
        void deleteClock();

        void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo);

        void saveClock(String oneValue, String twoValue, String mWeek);
    }

    public enum SelectorsDataNum {
        ONE,
        TWO,
        THREE
    }

    public CustomAlarmClockSelectors setOnOneSelectorsDataListener(OnOneSelectorsDataListener listener) {
        this.mOnOneSelectorsDataListener = listener;
        return this;
    }

    public CustomAlarmClockSelectors setOnTwoSelectorsDataListener(OnTwoSelectorsDataListener listener) {
        this.mOnTwoSelectorsDataListener = listener;
        return this;
    }

    public CustomAlarmClockSelectors setOnThreeSelectorsDataListener(OnThreeSelectorsDataListener listener) {
        this.mOnThreeSelectorsDataListener = listener;
        return this;
    }

    public void BpLevelPicker(final List<String> firstList, final List<String> secondList, final List<String> thirdList, int firstNum, int secondNum, int thirdNum, String firstLable, String secondLable, String thirdLable, Boolean isCenterLabel, final SelectorsDataNum selectorsDataNum, boolean isVisibilityDelete, boolean isCompileAdd, String alternativeDate, String isSwitch, Context context, String title) {
        this.mAlternativeDate = alternativeDate;
        this.mIsSwitch = isSwitch;
        Log.i("AAAAAAA", "==BpLevelPicker==" + this.mAlternativeDate + this.mIsSwitch);
        this.pvBpOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.3
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (selectorsDataNum == SelectorsDataNum.ONE) {
                    CustomAlarmClockSelectors.this.mOnOneSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), options1);
                    return;
                }
                if (selectorsDataNum == SelectorsDataNum.TWO) {
                    CustomAlarmClockSelectors.this.alarmHour = (String) firstList.get(options1);
                    CustomAlarmClockSelectors.this.alarmMin = (String) secondList.get(options2);
                    CustomAlarmClockSelectors.this.mOnTwoSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), (String) secondList.get(options2), options1, options2);
                    return;
                }
                if (selectorsDataNum == SelectorsDataNum.THREE) {
                    CustomAlarmClockSelectors.this.mOnThreeSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), (String) secondList.get(options2), (String) thirdList.get(options3), options1, options2, options3);
                }
            }
        }).setLayoutRes(R.layout.layout_alarm_clock_selector, new AnonymousClass2(context, isVisibilityDelete, title, firstList, secondList, thirdList, firstNum, secondNum)).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.1
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
            }
        }).setTextColorCenter(context.getResources().getColor(R.color.button_start_color)).setContentTextSize(24).setLineSpacingMultiplier(2.5f).setLabels(firstLable, secondLable, thirdLable).setCyclic(true, true, true).isCenterLabel(isCenterLabel.booleanValue()).setDividerType(WheelView.DividerType.FILL).setDividerColor(context.getResources().getColor(R.color.item_septal_line_color)).setItemVisibleCount(5).build();
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

    /* renamed from: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors$2, reason: invalid class name */
    class AnonymousClass2 implements CustomListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ List val$firstList;
        final /* synthetic */ int val$firstNum;
        final /* synthetic */ boolean val$isVisibilityDelete;
        final /* synthetic */ List val$secondList;
        final /* synthetic */ int val$secondNum;
        final /* synthetic */ List val$thirdList;
        final /* synthetic */ String val$title;

        AnonymousClass2(final Context val$context, final boolean val$isVisibilityDelete, final String val$title, final List val$firstList, final List val$secondList, final List val$thirdList, final int val$firstNum, final int val$secondNum) {
            this.val$context = val$context;
            this.val$isVisibilityDelete = val$isVisibilityDelete;
            this.val$title = val$title;
            this.val$firstList = val$firstList;
            this.val$secondList = val$secondList;
            this.val$thirdList = val$thirdList;
            this.val$firstNum = val$firstNum;
            this.val$secondNum = val$secondNum;
        }

        @Override // com.bigkoo.pickerview.listener.CustomListener
        public void customLayout(View v) {
            LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.ll_selector);
            RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.rl_label);
            RelativeLayout relativeLayout2 = (RelativeLayout) v.findViewById(R.id.rl_repetition);
            LinearLayout linearLayout2 = (LinearLayout) v.findViewById(R.id.ll_delete_clock);
            final TextView textView = (TextView) v.findViewById(R.id.tv_repetition);
            CustomAlarmClockSelectors.this.tv_label = (TextView) v.findViewById(R.id.tv_label);
            linearLayout.setPadding(0, AppScreenMgr.getStatusHeight((Activity) this.val$context), 0, 0);
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.height = AppScreenMgr.getScreenHeight(this.val$context) + AppScreenMgr.getNavigationAreaHeight(this.val$context);
            linearLayout.setLayoutParams(layoutParams);
            if (CustomAlarmClockSelectors.this.mAlternativeDate != null) {
                CustomAlarmClockSelectors customAlarmClockSelectors = CustomAlarmClockSelectors.this;
                customAlarmClockSelectors.setClock(this.val$context, customAlarmClockSelectors.mAlternativeDate.toCharArray(), textView);
            }
            if (this.val$isVisibilityDelete) {
                linearLayout2.setVisibility(0);
            } else {
                linearLayout2.setVisibility(8);
            }
            NavigationBar navigationBar = (NavigationBar) v.findViewById(R.id.navigationbar);
            navigationBar.setTitle(this.val$title);
            navigationBar.setBackgroundColor("#FFFFFF");
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                }
            });
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                }
            });
            relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    CustomAlarmClockWeekSelectors customAlarmClockWeekSelectors = new CustomAlarmClockWeekSelectors();
                    customAlarmClockWeekSelectors.BpLevelPicker(AnonymousClass2.this.val$firstList, AnonymousClass2.this.val$secondList, AnonymousClass2.this.val$thirdList, 1, 1, 1, "", "", "", false, CustomAlarmClockWeekSelectors.SelectorsDataNum.THREE, true, CustomAlarmClockSelectors.this.mAlternativeDate, AnonymousClass2.this.val$context);
                    customAlarmClockWeekSelectors.setOnThreeSelectorsDataListener(new CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.3.1
                        @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
                        public void getSelectorsDataClick(String oneValue, String twoValue, String threeValue, int optionsOne, int optionsTwo, int optionsThree) {
                        }

                        @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockWeekSelectors.OnThreeSelectorsDataListener
                        public void saveWeek(String week) {
                            if (week != null) {
                                CustomAlarmClockSelectors.this.setClock(AnonymousClass2.this.val$context, week.toCharArray(), textView);
                            }
                            CustomAlarmClockSelectors.this.mWeek = week;
                            CustomAlarmClockSelectors.this.mAlternativeDate = week;
                        }
                    });
                }
            });
            linearLayout2.setOnClickListener(new AnonymousClass4());
            navigationBar.showLeftbtn(0);
            navigationBar.setLeftOnClickListener(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.5
                @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
                public void onClick(View btn) {
                    CustomAlarmClockSelectors.this.pvBpOptions.dismiss();
                }
            });
            navigationBar.setRightText(this.val$context.getString(R.string.save));
            navigationBar.setRight1OnClickListener(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.6
                @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
                public void onClick(View btn) {
                    CustomAlarmClockSelectors.this.pvBpOptions.returnData();
                    CustomAlarmClockSelectors.this.mOnTwoSelectorsDataListener.saveClock(CustomAlarmClockSelectors.this.alarmHour, CustomAlarmClockSelectors.this.alarmMin, CustomAlarmClockSelectors.this.mWeek);
                    CustomAlarmClockSelectors.this.pvBpOptions.dismiss();
                }
            });
        }

        /* renamed from: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors$2$4, reason: invalid class name */
        class AnonymousClass4 implements View.OnClickListener {
            AnonymousClass4() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                YCBTClient.settingDeleteAlarm(AnonymousClass2.this.val$firstNum, AnonymousClass2.this.val$secondNum, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.4.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 2) {
                            CustomAlarmClockSelectors.this.pvBpOptions.dismiss();
                            CustomAlarmClockSelectors.this.mOnTwoSelectorsDataListener.deleteClock();
                            if (AnonymousClass2.this.val$context instanceof Activity) {
                                ((Activity) AnonymousClass2.this.val$context).runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.2.4.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Toast.makeText(AnonymousClass2.this.val$context, AnonymousClass2.this.val$context.getString(R.string.dial_delete_success), 0).show();
                                    }
                                });
                            }
                        }
                    }
                });
            }
        }
    }

    private void initExitLoginDialog(Context context) {
        final CommonDialog commonDialog = new CommonDialog(context);
        commonDialog.setTitle(context.getString(R.string.clock_name_lable)).setEditTextResId(1).setMessageResId(1).setCancelColor("#00C495").setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.4
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                if (CustomAlarmClockSelectors.this.tv_label != null) {
                    CustomAlarmClockSelectors.this.tv_label.setText(mEditText);
                }
                commonDialog.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClock(Context context, char[] ar, TextView tv_repetition) {
        if (Integer.parseInt(ar[0] + "") == 1 && Integer.parseInt(ar[1] + "") == 1 && Integer.parseInt(ar[2] + "") == 1 && Integer.parseInt(ar[3] + "") == 1 && Integer.parseInt(ar[4] + "") == 1 && Integer.parseInt(ar[5] + "") == 1 && Integer.parseInt(ar[6] + "") == 1) {
            tv_repetition.setText(context.getString(R.string.clock_repeat_content_every_day));
            return;
        }
        if (Integer.parseInt(ar[0] + "") == 1 && Integer.parseInt(ar[1] + "") == 1 && Integer.parseInt(ar[2] + "") == 1 && Integer.parseInt(ar[3] + "") == 1 && Integer.parseInt(ar[4] + "") == 1 && Integer.parseInt(ar[5] + "") == 1 && Integer.parseInt(ar[6] + "") == 0) {
            tv_repetition.setText(context.getString(R.string.clock_repeat_content_mon) + " - " + context.getString(R.string.clock_repeat_content_sat));
            return;
        }
        if (Integer.parseInt(ar[0] + "") == 1 && Integer.parseInt(ar[1] + "") == 1 && Integer.parseInt(ar[2] + "") == 1 && Integer.parseInt(ar[3] + "") == 1 && Integer.parseInt(ar[4] + "") == 1 && Integer.parseInt(ar[5] + "") == 0 && Integer.parseInt(ar[6] + "") == 0) {
            tv_repetition.setText(context.getString(R.string.clock_repeat_content_weekday));
            return;
        }
        String string = Integer.parseInt(new StringBuilder().append(ar[0]).append("").toString()) == 1 ? context.getString(R.string.clock_repeat_content_mon) : "";
        if (Integer.parseInt(ar[1] + "") == 1) {
            string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_tue);
        }
        if (Integer.parseInt(ar[2] + "") == 1) {
            string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_wed);
        }
        if (Integer.parseInt(ar[3] + "") == 1) {
            string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_thur);
        }
        if (Integer.parseInt(ar[4] + "") == 1) {
            string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_fri);
        }
        if (Integer.parseInt(ar[5] + "") == 1) {
            string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_sat);
        }
        if (Integer.parseInt(ar[6] + "") == 1) {
            string = string + StringUtils.SPACE + context.getString(R.string.clock_repeat_content_sun);
        }
        tv_repetition.setText(string);
    }
}
