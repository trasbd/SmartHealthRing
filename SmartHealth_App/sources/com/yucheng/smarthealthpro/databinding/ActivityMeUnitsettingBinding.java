package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityMeUnitsettingBinding implements ViewBinding {
    public final LinearLayout lyBloodSugar;
    public final LinearLayout lyUricAcid;
    public final NavigationBar navigationbar;
    public final RadioGroup radioGroup;
    public final RadioGroup radioGroupBloodSugar;
    public final RadioGroup radioGroupTemp;
    public final RadioGroup radioGroupUricAcid;
    public final RadioButton rbBloodSugarMg;
    public final RadioButton rbBloodSugarMmol;
    public final RadioButton rbBloodUricAcid1;
    public final RadioButton rbBloodUricAcid2;
    public final RadioButton rbImperial;
    public final RadioButton rbImperialTemp;
    public final RadioButton rbMetric;
    public final RadioButton rbMetricTemp;
    private final LinearLayout rootView;
    public final TextView tvBloodTitle;
    public final TextView tvUricAcidTitle;

    private ActivityMeUnitsettingBinding(LinearLayout rootView, LinearLayout lyBloodSugar, LinearLayout lyUricAcid, NavigationBar navigationbar, RadioGroup radioGroup, RadioGroup radioGroupBloodSugar, RadioGroup radioGroupTemp, RadioGroup radioGroupUricAcid, RadioButton rbBloodSugarMg, RadioButton rbBloodSugarMmol, RadioButton rbBloodUricAcid1, RadioButton rbBloodUricAcid2, RadioButton rbImperial, RadioButton rbImperialTemp, RadioButton rbMetric, RadioButton rbMetricTemp, TextView tvBloodTitle, TextView tvUricAcidTitle) {
        this.rootView = rootView;
        this.lyBloodSugar = lyBloodSugar;
        this.lyUricAcid = lyUricAcid;
        this.navigationbar = navigationbar;
        this.radioGroup = radioGroup;
        this.radioGroupBloodSugar = radioGroupBloodSugar;
        this.radioGroupTemp = radioGroupTemp;
        this.radioGroupUricAcid = radioGroupUricAcid;
        this.rbBloodSugarMg = rbBloodSugarMg;
        this.rbBloodSugarMmol = rbBloodSugarMmol;
        this.rbBloodUricAcid1 = rbBloodUricAcid1;
        this.rbBloodUricAcid2 = rbBloodUricAcid2;
        this.rbImperial = rbImperial;
        this.rbImperialTemp = rbImperialTemp;
        this.rbMetric = rbMetric;
        this.rbMetricTemp = rbMetricTemp;
        this.tvBloodTitle = tvBloodTitle;
        this.tvUricAcidTitle = tvUricAcidTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMeUnitsettingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMeUnitsettingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_me_unitsetting, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityMeUnitsettingBinding bind(View rootView) {
        int i2 = R.id.ly_blood_sugar;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.ly_uric_acid;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout2 != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.radio_group;
                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                    if (radioGroup != null) {
                        i2 = R.id.radio_group_blood_sugar;
                        RadioGroup radioGroup2 = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                        if (radioGroup2 != null) {
                            i2 = R.id.radio_group_temp;
                            RadioGroup radioGroup3 = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                            if (radioGroup3 != null) {
                                i2 = R.id.radio_group_uric_acid;
                                RadioGroup radioGroup4 = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                                if (radioGroup4 != null) {
                                    i2 = R.id.rb_blood_sugar_mg;
                                    RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                    if (radioButton != null) {
                                        i2 = R.id.rb_blood_sugar_mmol;
                                        RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                        if (radioButton2 != null) {
                                            i2 = R.id.rb_blood_uric_acid1;
                                            RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                            if (radioButton3 != null) {
                                                i2 = R.id.rb_blood_uric_acid2;
                                                RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                if (radioButton4 != null) {
                                                    i2 = R.id.rb_imperial;
                                                    RadioButton radioButton5 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                    if (radioButton5 != null) {
                                                        i2 = R.id.rb_imperial_temp;
                                                        RadioButton radioButton6 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                        if (radioButton6 != null) {
                                                            i2 = R.id.rb_metric;
                                                            RadioButton radioButton7 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                            if (radioButton7 != null) {
                                                                i2 = R.id.rb_metric_temp;
                                                                RadioButton radioButton8 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                                if (radioButton8 != null) {
                                                                    i2 = R.id.tv_blood_title;
                                                                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView != null) {
                                                                        i2 = R.id.tv_uric_acid_title;
                                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (textView2 != null) {
                                                                            return new ActivityMeUnitsettingBinding((LinearLayout) rootView, linearLayout, linearLayout2, navigationBar, radioGroup, radioGroup2, radioGroup3, radioGroup4, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, textView, textView2);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
