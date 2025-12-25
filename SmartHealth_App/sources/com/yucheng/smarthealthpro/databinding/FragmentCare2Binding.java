package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;

/* loaded from: classes4.dex */
public final class FragmentCare2Binding implements ViewBinding {
    public final ListView careListView;
    public final TextView careTvNum;
    public final LinearLayout layLogin;
    public final LinearLayout lyError;
    public final LinearLayout lyNewFriend;
    public final NavigationBar navigationbar;
    public final SmartRefreshLayout refreshLayout;
    private final RelativeLayout rootView;
    public final TextView tvServiceError;

    private FragmentCare2Binding(RelativeLayout rootView, ListView careListView, TextView careTvNum, LinearLayout layLogin, LinearLayout lyError, LinearLayout lyNewFriend, NavigationBar navigationbar, SmartRefreshLayout refreshLayout, TextView tvServiceError) {
        this.rootView = rootView;
        this.careListView = careListView;
        this.careTvNum = careTvNum;
        this.layLogin = layLogin;
        this.lyError = lyError;
        this.lyNewFriend = lyNewFriend;
        this.navigationbar = navigationbar;
        this.refreshLayout = refreshLayout;
        this.tvServiceError = tvServiceError;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCare2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCare2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_care2, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCare2Binding bind(View rootView) {
        int i2 = R.id.care_listView;
        ListView listView = (ListView) ViewBindings.findChildViewById(rootView, i2);
        if (listView != null) {
            i2 = R.id.care_tv_num;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.lay_login;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.ly_error;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout2 != null) {
                        i2 = R.id.ly_new_friend;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (linearLayout3 != null) {
                            i2 = R.id.navigationbar;
                            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                            if (navigationBar != null) {
                                i2 = R.id.refresh_layout;
                                SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (smartRefreshLayout != null) {
                                    i2 = R.id.tv_service_error;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView2 != null) {
                                        return new FragmentCare2Binding((RelativeLayout) rootView, listView, textView, linearLayout, linearLayout2, linearLayout3, navigationBar, smartRefreshLayout, textView2);
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
