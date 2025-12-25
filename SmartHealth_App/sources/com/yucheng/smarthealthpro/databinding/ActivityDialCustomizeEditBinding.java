package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class ActivityDialCustomizeEditBinding implements ViewBinding {
    public final ImageView dialCustomBgImg;
    public final TextClock dialCustomDate;
    public final TextView dialCustomDefault;
    public final TextClock dialCustomInvisibleTime;
    public final TextClock dialCustomInvisibleTime2;
    public final LinearLayout dialCustomLyNordic;
    public final LinearLayout dialCustomLyTimePosition;
    public final RadioButton dialCustomRadio1;
    public final RadioButton dialCustomRadio10;
    public final RadioButton dialCustomRadio11;
    public final RadioButton dialCustomRadio12;
    public final RadioButton dialCustomRadio2;
    public final RadioButton dialCustomRadio3;
    public final RadioButton dialCustomRadio4;
    public final RadioButton dialCustomRadio5;
    public final RadioButton dialCustomRadio6;
    public final RadioButton dialCustomRadio7;
    public final RadioButton dialCustomRadio8;
    public final RadioButton dialCustomRadio9;
    public final RadioGroup dialCustomRg1;
    public final RadioGroup dialCustomRg2;
    public final RelativeLayout dialCustomRl;
    public final TextView dialCustomSelectPicture;
    public final TextClock dialCustomTime;
    public final TextView dialCustomTvTimePosition;
    public final TextClock dialCustomWeek;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;

    private ActivityDialCustomizeEditBinding(LinearLayout rootView, ImageView dialCustomBgImg, TextClock dialCustomDate, TextView dialCustomDefault, TextClock dialCustomInvisibleTime, TextClock dialCustomInvisibleTime2, LinearLayout dialCustomLyNordic, LinearLayout dialCustomLyTimePosition, RadioButton dialCustomRadio1, RadioButton dialCustomRadio10, RadioButton dialCustomRadio11, RadioButton dialCustomRadio12, RadioButton dialCustomRadio2, RadioButton dialCustomRadio3, RadioButton dialCustomRadio4, RadioButton dialCustomRadio5, RadioButton dialCustomRadio6, RadioButton dialCustomRadio7, RadioButton dialCustomRadio8, RadioButton dialCustomRadio9, RadioGroup dialCustomRg1, RadioGroup dialCustomRg2, RelativeLayout dialCustomRl, TextView dialCustomSelectPicture, TextClock dialCustomTime, TextView dialCustomTvTimePosition, TextClock dialCustomWeek, NavigationBar navigationbar) {
        this.rootView = rootView;
        this.dialCustomBgImg = dialCustomBgImg;
        this.dialCustomDate = dialCustomDate;
        this.dialCustomDefault = dialCustomDefault;
        this.dialCustomInvisibleTime = dialCustomInvisibleTime;
        this.dialCustomInvisibleTime2 = dialCustomInvisibleTime2;
        this.dialCustomLyNordic = dialCustomLyNordic;
        this.dialCustomLyTimePosition = dialCustomLyTimePosition;
        this.dialCustomRadio1 = dialCustomRadio1;
        this.dialCustomRadio10 = dialCustomRadio10;
        this.dialCustomRadio11 = dialCustomRadio11;
        this.dialCustomRadio12 = dialCustomRadio12;
        this.dialCustomRadio2 = dialCustomRadio2;
        this.dialCustomRadio3 = dialCustomRadio3;
        this.dialCustomRadio4 = dialCustomRadio4;
        this.dialCustomRadio5 = dialCustomRadio5;
        this.dialCustomRadio6 = dialCustomRadio6;
        this.dialCustomRadio7 = dialCustomRadio7;
        this.dialCustomRadio8 = dialCustomRadio8;
        this.dialCustomRadio9 = dialCustomRadio9;
        this.dialCustomRg1 = dialCustomRg1;
        this.dialCustomRg2 = dialCustomRg2;
        this.dialCustomRl = dialCustomRl;
        this.dialCustomSelectPicture = dialCustomSelectPicture;
        this.dialCustomTime = dialCustomTime;
        this.dialCustomTvTimePosition = dialCustomTvTimePosition;
        this.dialCustomWeek = dialCustomWeek;
        this.navigationbar = navigationbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDialCustomizeEditBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityDialCustomizeEditBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_dial_customize_edit, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityDialCustomizeEditBinding bind(View rootView) {
        int i2 = R.id.dial_custom_bg_img;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.dial_custom_date;
            TextClock textClock = (TextClock) ViewBindings.findChildViewById(rootView, i2);
            if (textClock != null) {
                i2 = R.id.dial_custom_default;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.dial_custom_invisible_time;
                    TextClock textClock2 = (TextClock) ViewBindings.findChildViewById(rootView, i2);
                    if (textClock2 != null) {
                        i2 = R.id.dial_custom_invisible_time2;
                        TextClock textClock3 = (TextClock) ViewBindings.findChildViewById(rootView, i2);
                        if (textClock3 != null) {
                            i2 = R.id.dial_custom_ly_nordic;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout != null) {
                                i2 = R.id.dial_custom_ly_time_position;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout2 != null) {
                                    i2 = R.id.dial_custom_radio1;
                                    RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                    if (radioButton != null) {
                                        i2 = R.id.dial_custom_radio10;
                                        RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                        if (radioButton2 != null) {
                                            i2 = R.id.dial_custom_radio11;
                                            RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                            if (radioButton3 != null) {
                                                i2 = R.id.dial_custom_radio12;
                                                RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                if (radioButton4 != null) {
                                                    i2 = R.id.dial_custom_radio2;
                                                    RadioButton radioButton5 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                    if (radioButton5 != null) {
                                                        i2 = R.id.dial_custom_radio3;
                                                        RadioButton radioButton6 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                        if (radioButton6 != null) {
                                                            i2 = R.id.dial_custom_radio4;
                                                            RadioButton radioButton7 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                            if (radioButton7 != null) {
                                                                i2 = R.id.dial_custom_radio5;
                                                                RadioButton radioButton8 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                                if (radioButton8 != null) {
                                                                    i2 = R.id.dial_custom_radio6;
                                                                    RadioButton radioButton9 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (radioButton9 != null) {
                                                                        i2 = R.id.dial_custom_radio7;
                                                                        RadioButton radioButton10 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (radioButton10 != null) {
                                                                            i2 = R.id.dial_custom_radio8;
                                                                            RadioButton radioButton11 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (radioButton11 != null) {
                                                                                i2 = R.id.dial_custom_radio9;
                                                                                RadioButton radioButton12 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (radioButton12 != null) {
                                                                                    i2 = R.id.dial_custom_rg1;
                                                                                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (radioGroup != null) {
                                                                                        i2 = R.id.dial_custom_rg2;
                                                                                        RadioGroup radioGroup2 = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (radioGroup2 != null) {
                                                                                            i2 = R.id.dial_custom_rl;
                                                                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (relativeLayout != null) {
                                                                                                i2 = R.id.dial_custom_select_picture;
                                                                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                if (textView2 != null) {
                                                                                                    i2 = R.id.dial_custom_time;
                                                                                                    TextClock textClock4 = (TextClock) ViewBindings.findChildViewById(rootView, i2);
                                                                                                    if (textClock4 != null) {
                                                                                                        i2 = R.id.dial_custom_tv_time_position;
                                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                                                        if (textView3 != null) {
                                                                                                            i2 = R.id.dial_custom_week;
                                                                                                            TextClock textClock5 = (TextClock) ViewBindings.findChildViewById(rootView, i2);
                                                                                                            if (textClock5 != null) {
                                                                                                                i2 = R.id.navigationbar;
                                                                                                                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                                                                                                if (navigationBar != null) {
                                                                                                                    return new ActivityDialCustomizeEditBinding((LinearLayout) rootView, imageView, textClock, textView, textClock2, textClock3, linearLayout, linearLayout2, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, radioButton9, radioButton10, radioButton11, radioButton12, radioGroup, radioGroup2, relativeLayout, textView2, textClock4, textView3, textClock5, navigationBar);
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
