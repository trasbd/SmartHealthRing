package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.perfect.view.RelativeRadioGroup;

/* loaded from: classes4.dex */
public final class ActivityPerfectSkincolorBinding implements ViewBinding {
    public final NavigationBar navigationbar;
    public final RelativeRadioGroup radioGroup;
    public final RadioButton rbBlack;
    public final RadioButton rbBrown;
    public final RadioButton rbBrownness;
    public final RadioButton rbWhite;
    public final RadioButton rbWhiteBetweenYellow;
    public final RadioButton rbYellow;
    private final LinearLayout rootView;
    public final TextView tvNext;

    private ActivityPerfectSkincolorBinding(LinearLayout rootView, NavigationBar navigationbar, RelativeRadioGroup radioGroup, RadioButton rbBlack, RadioButton rbBrown, RadioButton rbBrownness, RadioButton rbWhite, RadioButton rbWhiteBetweenYellow, RadioButton rbYellow, TextView tvNext) {
        this.rootView = rootView;
        this.navigationbar = navigationbar;
        this.radioGroup = radioGroup;
        this.rbBlack = rbBlack;
        this.rbBrown = rbBrown;
        this.rbBrownness = rbBrownness;
        this.rbWhite = rbWhite;
        this.rbWhiteBetweenYellow = rbWhiteBetweenYellow;
        this.rbYellow = rbYellow;
        this.tvNext = tvNext;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPerfectSkincolorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPerfectSkincolorBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_perfect_skincolor, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPerfectSkincolorBinding bind(View rootView) {
        int i2 = R.id.navigationbar;
        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
        if (navigationBar != null) {
            i2 = R.id.radio_group;
            RelativeRadioGroup relativeRadioGroup = (RelativeRadioGroup) ViewBindings.findChildViewById(rootView, i2);
            if (relativeRadioGroup != null) {
                i2 = R.id.rb_black;
                RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                if (radioButton != null) {
                    i2 = R.id.rb_brown;
                    RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                    if (radioButton2 != null) {
                        i2 = R.id.rb_brownness;
                        RadioButton radioButton3 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                        if (radioButton3 != null) {
                            i2 = R.id.rb_white;
                            RadioButton radioButton4 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                            if (radioButton4 != null) {
                                i2 = R.id.rb_white_between_yellow;
                                RadioButton radioButton5 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                if (radioButton5 != null) {
                                    i2 = R.id.rb_yellow;
                                    RadioButton radioButton6 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                                    if (radioButton6 != null) {
                                        i2 = R.id.tv_next;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView != null) {
                                            return new ActivityPerfectSkincolorBinding((LinearLayout) rootView, navigationBar, relativeRadioGroup, radioButton, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, textView);
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
