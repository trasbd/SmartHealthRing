package com.yucheng.smarthealthpro.home.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.AppScreenMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.TransUtils;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class CustomSelectors {
    private OnInputDataListener mOnInputDataListener;
    private OnOneSelectorsDataListener mOnOneSelectorsDataListener;
    private OnThreeSelectorsDataListener mOnThreeSelectorsDataListener;
    private OnTwoSelectorsDataListener mOnTwoSelectorsDataListener;
    private OnSelectChange onSelectChange;
    private OptionsPickerView pvBpOptions;

    public enum IsShow {
        TOP_CONFIRM_CANCEL,
        BP,
        BOTTOM_CONFIRM,
        NEW_BLOOD_SUGAR
    }

    public interface OnInputDataListener {
        void getDataClick(String value);
    }

    public interface OnOneSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, int optionsOne);
    }

    public interface OnSelectChange {
        void onChange(OptionsPickerView pvBpOptions, int options1, int options2, int options3);
    }

    public interface OnThreeSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, String twoValue, String threeValue, int optionsOne, int optionsTwo, int optionsThree);
    }

    public interface OnTwoSelectorsDataListener {
        void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo);
    }

    public enum SelectorsDataNum {
        ONE,
        TWO,
        THREE
    }

    public CustomSelectors setOnOneSelectorsDataListener(OnOneSelectorsDataListener listener) {
        this.mOnOneSelectorsDataListener = listener;
        return this;
    }

    public CustomSelectors setOnTwoSelectorsDataListener(OnTwoSelectorsDataListener listener) {
        this.mOnTwoSelectorsDataListener = listener;
        return this;
    }

    public CustomSelectors setOnSelectChange(OnSelectChange onSelectChange) {
        this.onSelectChange = onSelectChange;
        return this;
    }

    public CustomSelectors setOnThreeSelectorsDataListener(OnThreeSelectorsDataListener listener) {
        this.mOnThreeSelectorsDataListener = listener;
        return this;
    }

    public CustomSelectors setOnInputDataListener(OnInputDataListener listener) {
        this.mOnInputDataListener = listener;
        return this;
    }

    public void showBloodFatPicker(final Context context) {
        OptionsPickerView optionsPickerViewBuild = new OptionsPickerBuilder(context, new OnOptionsSelectListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.2
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        }).setLayoutRes(R.layout.layout_blood_fat_calibration_dialog, new CustomListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.1
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public void customLayout(View v) {
                TextView textView = (TextView) v.findViewById(R.id.tv_cancel);
                final TextView textView2 = (TextView) v.findViewById(R.id.tv_confirm);
                TextView textView3 = (TextView) v.findViewById(R.id.tv_range);
                final EditText editText = (EditText) v.findViewById(R.id.et_input);
                LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.ll_bottom);
                textView2.setEnabled(true);
                editText.addTextChangedListener(new TextWatcher() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.1.1
                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable s) {
                        textView2.setEnabled(!TextUtils.isEmpty(s.toString()));
                    }
                });
                editText.requestFocus();
                ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(editText, 0);
                String str = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, Constant.SpConstValue.BLOOD_SUGAR_AND_BLOOD_FAT_MMOL);
                String string = context.getString(R.string.setting_health_blood_fat_calibration);
                if ("mg/dL".equals(str)) {
                    textView3.setText(string + ": " + TransUtils.BLOOD_FAT_MIN_MG + " ~ " + TransUtils.BLOOD_FAT_MAX_MG + StringUtils.SPACE + str);
                    editText.setHint(FormatUtil.keep2(((Float) SharedPreferencesUtils.get(context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_MG_VALUE, Float.valueOf(193.05f))).floatValue()) + "");
                } else {
                    textView3.setText(string + ": " + TransUtils.BLOOD_FAT_MIN + " ~ " + TransUtils.BLOOD_FAT_MAX + StringUtils.SPACE + str);
                    editText.setHint(FormatUtil.keep2(((Float) SharedPreferencesUtils.get(context, Constant.SpConstKey.BLOOD_FAT_CALIBRATION_VALUE, Float.valueOf(5.0f))).floatValue()) + "");
                }
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.height = AppScreenMgr.getNavigationAreaHeight(context);
                linearLayout.setLayoutParams(layoutParams);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.1.2
                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(View v2, boolean hasFocus) {
                        if (hasFocus) {
                            editText.setHint("");
                            textView2.setEnabled(!TextUtils.isEmpty(editText.getText().toString()));
                        }
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.1.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomSelectors.this.pvBpOptions.dismiss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.1.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        if (!TextUtils.isEmpty(editText.getText().toString())) {
                            CustomSelectors.this.mOnInputDataListener.getDataClick(editText.getText().toString());
                            CustomSelectors.this.pvBpOptions.dismiss();
                        } else {
                            if (TextUtils.isEmpty(editText.getHint().toString())) {
                                return;
                            }
                            CustomSelectors.this.mOnInputDataListener.getDataClick(editText.getHint().toString());
                            CustomSelectors.this.pvBpOptions.dismiss();
                        }
                    }
                });
            }
        }).isDialog(false).setOutSideCancelable(true).build();
        this.pvBpOptions = optionsPickerViewBuild;
        optionsPickerViewBuild.setOnDismissListener(new OnDismissListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.3
            @Override // com.bigkoo.pickerview.listener.OnDismissListener
            public void onDismiss(Object o) {
                Context context2 = context;
                Activity activity = (Activity) context2;
                try {
                    ((InputMethodManager) context2.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.pvBpOptions.show();
    }

    public void showUricAcidPicker(final Context context) {
        OptionsPickerView optionsPickerViewBuild = new OptionsPickerBuilder(context, new OnOptionsSelectListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.5
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        }).setLayoutRes(R.layout.layout_uric_acid_calibration_dialog, new CustomListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.4
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public void customLayout(View v) {
                TextView textView = (TextView) v.findViewById(R.id.tv_cancel);
                final TextView textView2 = (TextView) v.findViewById(R.id.tv_confirm);
                TextView textView3 = (TextView) v.findViewById(R.id.tv_range);
                final EditText editText = (EditText) v.findViewById(R.id.et_input);
                LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.ll_bottom);
                String str = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.URIC_ACID_UNIT, Constant.SpConstValue.URIC_ACID_UMOL);
                String string = context.getString(R.string.setting_health_uric_acid_calibration);
                textView2.setEnabled(true);
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.height = AppScreenMgr.getNavigationAreaHeight(context);
                linearLayout.setLayoutParams(layoutParams);
                editText.addTextChangedListener(new TextWatcher() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.4.1
                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable s) {
                        textView2.setEnabled(!TextUtils.isEmpty(s.toString()));
                    }
                });
                if ("mg/dL".equals(str)) {
                    textView3.setText(string + ": " + TransUtils.URIC_ACID_MIN_MG + " ~ " + TransUtils.URIC_ACID_MAX_MG + StringUtils.SPACE + str);
                    editText.setHint(((Float) SharedPreferencesUtils.get(context, Constant.SpConstKey.URIC_ACID_CALIBRATION_MG_VALUE, Float.valueOf(3.4f))).floatValue() + "");
                } else {
                    textView3.setText(string + ": " + TransUtils.URIC_ACID_MIN + " ~ " + TransUtils.URIC_ACID_MAX + StringUtils.SPACE + str);
                    editText.setHint(((int) ((Float) SharedPreferencesUtils.get(context, Constant.SpConstKey.URIC_ACID_CALIBRATION_VALUE, Float.valueOf(200.0f))).floatValue()) + "");
                }
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.4.2
                    @Override // android.view.View.OnFocusChangeListener
                    public void onFocusChange(View v2, boolean hasFocus) {
                        if (hasFocus) {
                            editText.setHint("");
                            textView2.setEnabled(!TextUtils.isEmpty(editText.getText().toString()));
                        }
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.4.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomSelectors.this.pvBpOptions.dismiss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.4.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        if (!TextUtils.isEmpty(editText.getText().toString())) {
                            CustomSelectors.this.mOnInputDataListener.getDataClick(editText.getText().toString());
                            CustomSelectors.this.pvBpOptions.dismiss();
                        } else {
                            if (TextUtils.isEmpty(editText.getHint().toString())) {
                                return;
                            }
                            CustomSelectors.this.mOnInputDataListener.getDataClick(editText.getHint().toString());
                            CustomSelectors.this.pvBpOptions.dismiss();
                        }
                    }
                });
            }
        }).build();
        this.pvBpOptions = optionsPickerViewBuild;
        optionsPickerViewBuild.setOnDismissListener(new OnDismissListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.6
            @Override // com.bigkoo.pickerview.listener.OnDismissListener
            public void onDismiss(Object o) {
                try {
                    ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.pvBpOptions.show();
    }

    public void BpLevelPicker(final List<String> firstList, final List<String> secondList, final List<String> thirdList, int firstNum, int secondNum, int thirdNum, String firstLable, String secondLable, String thirdLable, Boolean isCenterLabel, final IsShow isShow, final SelectorsDataNum selectorsDataNum, final Context context) {
        this.pvBpOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.9
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (selectorsDataNum == SelectorsDataNum.ONE) {
                    if (options1 < firstList.size()) {
                        CustomSelectors.this.mOnOneSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), options1);
                    }
                } else {
                    if (selectorsDataNum == SelectorsDataNum.TWO) {
                        if (options1 >= firstList.size() || options2 >= secondList.size()) {
                            return;
                        }
                        CustomSelectors.this.mOnTwoSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), (String) secondList.get(options2), options1, options2);
                        return;
                    }
                    if (selectorsDataNum != SelectorsDataNum.THREE || options1 >= firstList.size() || options2 >= secondList.size() || options3 >= thirdList.size()) {
                        return;
                    }
                    CustomSelectors.this.mOnThreeSelectorsDataListener.getSelectorsDataClick((String) firstList.get(options1), (String) secondList.get(options2), (String) thirdList.get(options3), options1, options2, options3);
                }
            }
        }).setLayoutRes(R.layout.layout_blood_pressure_calibration_selector, new CustomListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.8
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public void customLayout(View v) {
                RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.rl_top_confirm_cancel);
                RelativeLayout relativeLayout2 = (RelativeLayout) v.findViewById(R.id.rl_bp);
                RelativeLayout relativeLayout3 = (RelativeLayout) v.findViewById(R.id.rl_top_shape);
                RelativeLayout relativeLayout4 = (RelativeLayout) v.findViewById(R.id.rl_bottom_confirm);
                TextView textView = (TextView) v.findViewById(R.id.tv_cancel);
                TextView textView2 = (TextView) v.findViewById(R.id.tv_confirm);
                TextView textView3 = (TextView) v.findViewById(R.id.tv_bottom_confirm);
                LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.ll_bottom);
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                layoutParams.height = AppScreenMgr.getNavigationAreaHeight(context);
                linearLayout.setLayoutParams(layoutParams);
                if (isShow == IsShow.TOP_CONFIRM_CANCEL) {
                    relativeLayout2.setVisibility(8);
                    relativeLayout4.setVisibility(8);
                } else if (isShow == IsShow.BP) {
                    relativeLayout4.setVisibility(8);
                } else if (isShow == IsShow.BOTTOM_CONFIRM) {
                    relativeLayout.setVisibility(8);
                    relativeLayout2.setVisibility(8);
                    relativeLayout3.setVisibility(0);
                } else if (isShow == IsShow.NEW_BLOOD_SUGAR) {
                    ((TextView) relativeLayout2.findViewById(R.id.one_text)).setText(context.getString(R.string.health_blood_sugar_calibration_type1));
                    ((TextView) relativeLayout2.findViewById(R.id.two_text)).setText(context.getString(R.string.health_blood_sugar_calibration_type2));
                    relativeLayout4.setVisibility(8);
                }
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.8.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomSelectors.this.pvBpOptions.dismiss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.8.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomSelectors.this.pvBpOptions.returnData();
                        CustomSelectors.this.pvBpOptions.dismiss();
                    }
                });
                textView3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.8.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomSelectors.this.pvBpOptions.returnData();
                        CustomSelectors.this.pvBpOptions.dismiss();
                    }
                });
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomSelectors.7
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
                if (CustomSelectors.this.onSelectChange != null) {
                    CustomSelectors.this.onSelectChange.onChange(CustomSelectors.this.pvBpOptions, options1, options2, options3);
                }
            }
        }).setTextColorCenter(context.getResources().getColor(R.color.button_start_color)).setContentTextSize(24).setLineSpacingMultiplier(2.5f).setLabels(firstLable, secondLable, thirdLable).isCenterLabel(isCenterLabel.booleanValue()).setDividerType(WheelView.DividerType.FILL).setDividerColor(context.getResources().getColor(R.color.item_septal_line_color)).setItemVisibleCount(5).build();
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
}
