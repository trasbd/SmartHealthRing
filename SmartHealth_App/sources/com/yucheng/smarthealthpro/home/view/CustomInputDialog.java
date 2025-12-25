package com.yucheng.smarthealthpro.home.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class CustomInputDialog {
    private OnInputDataListener mOnInputDataListener;
    private OptionsPickerView pvBpOptions;

    public interface OnInputDataListener {
        void getDataClick(String value);
    }

    public CustomInputDialog setOnInputDataListener(OnInputDataListener listener) {
        this.mOnInputDataListener = listener;
        return this;
    }

    public void showBloodFatPicker(final Context context) {
        OptionsPickerView optionsPickerViewBuild = new OptionsPickerBuilder(context, new OnOptionsSelectListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.2
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        }).setLayoutRes(R.layout.layout_blood_fat_calibration_dialog, new CustomListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.1
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public void customLayout(View v) {
                TextView textView = (TextView) v.findViewById(R.id.tv_cancel);
                final TextView textView2 = (TextView) v.findViewById(R.id.tv_confirm);
                TextView textView3 = (TextView) v.findViewById(R.id.tv_range);
                final EditText editText = (EditText) v.findViewById(R.id.et_input);
                editText.addTextChangedListener(new TextWatcher() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.1.1
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
                    textView3.setText(string + ": 38.60 ~ 366.80 " + str);
                    editText.setHint("193.05");
                } else {
                    textView3.setText(string + ": 1.0 ~ 9.5 " + str);
                    editText.setHint("5.0");
                }
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomInputDialog.this.pvBpOptions.dismiss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.1.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        if (TextUtils.isEmpty(editText.getText().toString())) {
                            return;
                        }
                        CustomInputDialog.this.mOnInputDataListener.getDataClick(editText.getText().toString());
                        CustomInputDialog.this.pvBpOptions.dismiss();
                    }
                });
            }
        }).isDialog(false).setOutSideCancelable(true).build();
        this.pvBpOptions = optionsPickerViewBuild;
        optionsPickerViewBuild.setOnDismissListener(new OnDismissListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.3
            @Override // com.bigkoo.pickerview.listener.OnDismissListener
            public void onDismiss(Object o) {
                Context context2 = context;
                ((InputMethodManager) context2.getSystemService("input_method")).hideSoftInputFromWindow(((Activity) context2).getCurrentFocus().getWindowToken(), 0);
            }
        });
        this.pvBpOptions.show();
    }

    public void showUricAcidPicker(final Context context) {
        OptionsPickerView optionsPickerViewBuild = new OptionsPickerBuilder(context, new OnOptionsSelectListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.5
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
            }
        }).setLayoutRes(R.layout.layout_uric_acid_calibration_dialog, new CustomListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.4
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public void customLayout(View v) {
                TextView textView = (TextView) v.findViewById(R.id.tv_cancel);
                final TextView textView2 = (TextView) v.findViewById(R.id.tv_confirm);
                TextView textView3 = (TextView) v.findViewById(R.id.tv_range);
                final EditText editText = (EditText) v.findViewById(R.id.et_input);
                String str = (String) SharedPreferencesUtils.get(context, Constant.SpConstKey.URIC_ACID_UNIT, Constant.SpConstValue.URIC_ACID_UMOL);
                String string = context.getString(R.string.setting_health_uric_acid_calibration);
                editText.addTextChangedListener(new TextWatcher() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.4.1
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
                    textView3.setText(string + ": 1.5 ~ 16.8 " + str);
                    editText.setHint("3.4");
                } else {
                    textView3.setText(string + ": 90 ~ 1000 " + str);
                    editText.setHint("200");
                }
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.4.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        CustomInputDialog.this.pvBpOptions.dismiss();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.4.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        if (TextUtils.isEmpty(editText.getText().toString())) {
                            return;
                        }
                        CustomInputDialog.this.mOnInputDataListener.getDataClick(editText.getText().toString());
                        CustomInputDialog.this.pvBpOptions.dismiss();
                    }
                });
            }
        }).build();
        this.pvBpOptions = optionsPickerViewBuild;
        optionsPickerViewBuild.setOnDismissListener(new OnDismissListener() { // from class: com.yucheng.smarthealthpro.home.view.CustomInputDialog.6
            @Override // com.bigkoo.pickerview.listener.OnDismissListener
            public void onDismiss(Object o) {
                ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
            }
        });
        this.pvBpOptions.show();
    }
}
