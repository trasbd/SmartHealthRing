package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.tabs.TabLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class FragmentTwoBinding implements ViewBinding {
    public final Button btnRunBtn;
    public final LinearLayout layNoData;
    public final LinearLayout layWeather;
    public final ListView listview;
    public final NavigationBar navigationbar;
    private final LinearLayout rootView;
    public final TabLayout tabLayout;
    public final TextView tvAddress;
    public final TextView tvStart;
    public final TextView tvTemp;
    public final TextView tvTempBetween;
    public final View view;

    private FragmentTwoBinding(LinearLayout rootView, Button btnRunBtn, LinearLayout layNoData, LinearLayout layWeather, ListView listview, NavigationBar navigationbar, TabLayout tabLayout, TextView tvAddress, TextView tvStart, TextView tvTemp, TextView tvTempBetween, View view) {
        this.rootView = rootView;
        this.btnRunBtn = btnRunBtn;
        this.layNoData = layNoData;
        this.layWeather = layWeather;
        this.listview = listview;
        this.navigationbar = navigationbar;
        this.tabLayout = tabLayout;
        this.tvAddress = tvAddress;
        this.tvStart = tvStart;
        this.tvTemp = tvTemp;
        this.tvTempBetween = tvTempBetween;
        this.view = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentTwoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentTwoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_two, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentTwoBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.btn_runBtn;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.lay_no_data;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout != null) {
                i2 = R.id.lay_weather;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout2 != null) {
                    i2 = R.id.listview;
                    ListView listView = (ListView) ViewBindings.findChildViewById(rootView, i2);
                    if (listView != null) {
                        i2 = R.id.navigationbar;
                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                        if (navigationBar != null) {
                            i2 = R.id.tabLayout;
                            TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (tabLayout != null) {
                                i2 = R.id.tv_address;
                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView != null) {
                                    i2 = R.id.tv_start;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView2 != null) {
                                        i2 = R.id.tv_temp;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                        if (textView3 != null) {
                                            i2 = R.id.tv_temp_between;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView4 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.view))) != null) {
                                                return new FragmentTwoBinding((LinearLayout) rootView, button, linearLayout, linearLayout2, listView, navigationBar, tabLayout, textView, textView2, textView3, textView4, viewFindChildViewById);
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
