package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.sport.view.NoScrollSwipeRecyclerView;

/* loaded from: classes4.dex */
public final class FragmentCareBinding implements ViewBinding {
    public final Button btNewFriendNum;
    public final ImageView ivAddNewFriend;
    public final LinearLayout llNewFriend;
    public final LinearLayout llNoFriend;
    public final LinearLayout llToLogin;
    public final NavigationBar navigationbar;
    public final NoScrollSwipeRecyclerView recycleView;
    public final RelativeLayout rlNewFriend;
    private final LinearLayout rootView;
    public final SmartRefreshLayout srlHome;
    public final TextView tvLogin;

    private FragmentCareBinding(LinearLayout rootView, Button btNewFriendNum, ImageView ivAddNewFriend, LinearLayout llNewFriend, LinearLayout llNoFriend, LinearLayout llToLogin, NavigationBar navigationbar, NoScrollSwipeRecyclerView recycleView, RelativeLayout rlNewFriend, SmartRefreshLayout srlHome, TextView tvLogin) {
        this.rootView = rootView;
        this.btNewFriendNum = btNewFriendNum;
        this.ivAddNewFriend = ivAddNewFriend;
        this.llNewFriend = llNewFriend;
        this.llNoFriend = llNoFriend;
        this.llToLogin = llToLogin;
        this.navigationbar = navigationbar;
        this.recycleView = recycleView;
        this.rlNewFriend = rlNewFriend;
        this.srlHome = srlHome;
        this.tvLogin = tvLogin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentCareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static FragmentCareBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.fragment_care, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static FragmentCareBinding bind(View rootView) {
        int i2 = R.id.bt_new_friend_num;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.iv_add_new_friend;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.ll_new_friend;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout != null) {
                    i2 = R.id.ll_no_friend;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout2 != null) {
                        i2 = R.id.ll_to_login;
                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (linearLayout3 != null) {
                            i2 = R.id.navigationbar;
                            NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                            if (navigationBar != null) {
                                i2 = R.id.recycle_view;
                                NoScrollSwipeRecyclerView noScrollSwipeRecyclerView = (NoScrollSwipeRecyclerView) ViewBindings.findChildViewById(rootView, i2);
                                if (noScrollSwipeRecyclerView != null) {
                                    i2 = R.id.rl_new_friend;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (relativeLayout != null) {
                                        i2 = R.id.srl_home;
                                        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (smartRefreshLayout != null) {
                                            i2 = R.id.tv_login;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                            if (textView != null) {
                                                return new FragmentCareBinding((LinearLayout) rootView, button, imageView, linearLayout, linearLayout2, linearLayout3, navigationBar, noScrollSwipeRecyclerView, relativeLayout, smartRefreshLayout, textView);
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
