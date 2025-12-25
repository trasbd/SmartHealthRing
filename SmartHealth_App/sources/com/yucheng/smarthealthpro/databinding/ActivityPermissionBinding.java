package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.view.SwitchButton;

/* loaded from: classes4.dex */
public final class ActivityPermissionBinding implements ViewBinding {
    public final LinearLayout layoutPermissionSd;
    public final LinearLayout llPermissionCall;
    public final LinearLayout llPermissionCallRecode;
    public final LinearLayout llPermissionContacts;
    public final LinearLayout llPermissionNotify;
    public final LinearLayout llPermissionService;
    public final LinearLayout llPermissionSms;
    public final LinearLayout lyNearbyDevice;
    public final NavigationBar navigationbar;
    public final LinearLayout permissionBackgroundRunning;
    public final TextView permissionInternetState;
    public final SwitchButton permissionSwitchButtonCall;
    public final SwitchButton permissionSwitchButtonCallRecord;
    public final SwitchButton permissionSwitchButtonCamare;
    public final SwitchButton permissionSwitchButtonContants;
    public final SwitchButton permissionSwitchButtonInternet;
    public final SwitchButton permissionSwitchButtonNearbyDevice;
    public final SwitchButton permissionSwitchButtonNotify;
    public final SwitchButton permissionSwitchButtonPosition;
    public final SwitchButton permissionSwitchButtonSd;
    public final SwitchButton permissionSwitchButtonService;
    public final SwitchButton permissionSwitchButtonSms;
    private final LinearLayout rootView;

    private ActivityPermissionBinding(LinearLayout rootView, LinearLayout layoutPermissionSd, LinearLayout llPermissionCall, LinearLayout llPermissionCallRecode, LinearLayout llPermissionContacts, LinearLayout llPermissionNotify, LinearLayout llPermissionService, LinearLayout llPermissionSms, LinearLayout lyNearbyDevice, NavigationBar navigationbar, LinearLayout permissionBackgroundRunning, TextView permissionInternetState, SwitchButton permissionSwitchButtonCall, SwitchButton permissionSwitchButtonCallRecord, SwitchButton permissionSwitchButtonCamare, SwitchButton permissionSwitchButtonContants, SwitchButton permissionSwitchButtonInternet, SwitchButton permissionSwitchButtonNearbyDevice, SwitchButton permissionSwitchButtonNotify, SwitchButton permissionSwitchButtonPosition, SwitchButton permissionSwitchButtonSd, SwitchButton permissionSwitchButtonService, SwitchButton permissionSwitchButtonSms) {
        this.rootView = rootView;
        this.layoutPermissionSd = layoutPermissionSd;
        this.llPermissionCall = llPermissionCall;
        this.llPermissionCallRecode = llPermissionCallRecode;
        this.llPermissionContacts = llPermissionContacts;
        this.llPermissionNotify = llPermissionNotify;
        this.llPermissionService = llPermissionService;
        this.llPermissionSms = llPermissionSms;
        this.lyNearbyDevice = lyNearbyDevice;
        this.navigationbar = navigationbar;
        this.permissionBackgroundRunning = permissionBackgroundRunning;
        this.permissionInternetState = permissionInternetState;
        this.permissionSwitchButtonCall = permissionSwitchButtonCall;
        this.permissionSwitchButtonCallRecord = permissionSwitchButtonCallRecord;
        this.permissionSwitchButtonCamare = permissionSwitchButtonCamare;
        this.permissionSwitchButtonContants = permissionSwitchButtonContants;
        this.permissionSwitchButtonInternet = permissionSwitchButtonInternet;
        this.permissionSwitchButtonNearbyDevice = permissionSwitchButtonNearbyDevice;
        this.permissionSwitchButtonNotify = permissionSwitchButtonNotify;
        this.permissionSwitchButtonPosition = permissionSwitchButtonPosition;
        this.permissionSwitchButtonSd = permissionSwitchButtonSd;
        this.permissionSwitchButtonService = permissionSwitchButtonService;
        this.permissionSwitchButtonSms = permissionSwitchButtonSms;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPermissionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityPermissionBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_permission, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityPermissionBinding bind(View rootView) {
        int i2 = R.id.layout_permission_sd;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
        if (linearLayout != null) {
            i2 = R.id.ll_permission_call;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout2 != null) {
                i2 = R.id.ll_permission_call_recode;
                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                if (linearLayout3 != null) {
                    i2 = R.id.ll_permission_contacts;
                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (linearLayout4 != null) {
                        i2 = R.id.ll_permission_notify;
                        LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                        if (linearLayout5 != null) {
                            i2 = R.id.ll_permission_service;
                            LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                            if (linearLayout6 != null) {
                                i2 = R.id.ll_permission_sms;
                                LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout7 != null) {
                                    i2 = R.id.ly_nearby_device;
                                    LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (linearLayout8 != null) {
                                        i2 = R.id.navigationbar;
                                        NavigationBar navigationBar = (NavigationBar) ViewBindings.findChildViewById(rootView, i2);
                                        if (navigationBar != null) {
                                            i2 = R.id.permission_background_running;
                                            LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (linearLayout9 != null) {
                                                i2 = R.id.permission_internet_state;
                                                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                if (textView != null) {
                                                    i2 = R.id.permission_switchButton_call;
                                                    SwitchButton switchButton = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                    if (switchButton != null) {
                                                        i2 = R.id.permission_switchButton_call_record;
                                                        SwitchButton switchButton2 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                        if (switchButton2 != null) {
                                                            i2 = R.id.permission_switchButton_camare;
                                                            SwitchButton switchButton3 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                            if (switchButton3 != null) {
                                                                i2 = R.id.permission_switchButton_contants;
                                                                SwitchButton switchButton4 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                if (switchButton4 != null) {
                                                                    i2 = R.id.permission_switchButton_internet;
                                                                    SwitchButton switchButton5 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (switchButton5 != null) {
                                                                        i2 = R.id.permission_switchButton_nearby_device;
                                                                        SwitchButton switchButton6 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                        if (switchButton6 != null) {
                                                                            i2 = R.id.permission_switchButton_notify;
                                                                            SwitchButton switchButton7 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                            if (switchButton7 != null) {
                                                                                i2 = R.id.permission_switchButton_position;
                                                                                SwitchButton switchButton8 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                                if (switchButton8 != null) {
                                                                                    i2 = R.id.permission_switchButton_sd;
                                                                                    SwitchButton switchButton9 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                                    if (switchButton9 != null) {
                                                                                        i2 = R.id.permission_switchButton_service;
                                                                                        SwitchButton switchButton10 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                                        if (switchButton10 != null) {
                                                                                            i2 = R.id.permission_switchButton_sms;
                                                                                            SwitchButton switchButton11 = (SwitchButton) ViewBindings.findChildViewById(rootView, i2);
                                                                                            if (switchButton11 != null) {
                                                                                                return new ActivityPermissionBinding((LinearLayout) rootView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, navigationBar, linearLayout9, textView, switchButton, switchButton2, switchButton3, switchButton4, switchButton5, switchButton6, switchButton7, switchButton8, switchButton9, switchButton10, switchButton11);
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
