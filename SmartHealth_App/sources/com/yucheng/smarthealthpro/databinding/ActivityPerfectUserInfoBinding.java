package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes4.dex */
public final class ActivityPerfectUserInfoBinding implements ViewBinding {
    public final EditText edUserNickname;
    public final CircleImageView headImage;
    public final NavigationBar navigationbar;
    public final RadioGroup radioGroup;
    public final RadioButton rbMan;
    public final RadioButton rbWoman;
    private final LinearLayout rootView;
    public final TextView tvNext;
    public final TextView tvUserAge;
    public final TextView userNumber;

    private ActivityPerfectUserInfoBinding(LinearLayout rootView, EditText edUserNickname, CircleImageView headImage, NavigationBar navigationbar, RadioGroup radioGroup, RadioButton rbMan, RadioButton rbWoman, TextView tvNext, TextView tvUserAge, TextView userNumber) {
        this.rootView = rootView;
        this.edUserNickname = edUserNickname;
        this.headImage = headImage;
        this.navigationbar = navigationbar;
        this.radioGroup = radioGroup;
        this.rbMan = rbMan;
        this.rbWoman = rbWoman;
        this.tvNext = tvNext;
        this.tvUserAge = tvUserAge;
        this.userNumber = userNumber;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPerfectUserInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPerfectUserInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_perfect_user_info, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPerfectUserInfoBinding bind(View rootView) {
        int i2 = R.id.ed_user_nickname;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.head_image;
            CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(rootView, i2);
            if (circleImageView != null) {
                i2 = R.id.navigationbar;
                NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                if (navigationBar != null) {
                    i2 = R.id.radio_group;
                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                    if (radioGroup != null) {
                        i2 = R.id.rb_man;
                        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                        if (radioButton != null) {
                            i2 = R.id.rb_woman;
                            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                            if (radioButton2 != null) {
                                i2 = R.id.tv_next;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView != null) {
                                    i2 = R.id.tv_user_age;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView2 != null) {
                                        i2 = R.id.user_number;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView3 != null) {
                                            return new ActivityPerfectUserInfoBinding((LinearLayout) rootView, editText, circleImageView, navigationBar, radioGroup, radioButton, radioButton2, textView, textView2, textView3);
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
