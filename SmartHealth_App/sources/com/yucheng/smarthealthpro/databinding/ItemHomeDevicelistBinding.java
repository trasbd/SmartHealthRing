package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemHomeDevicelistBinding implements ViewBinding {
    public final RelativeLayout deviceListItem;
    private final RelativeLayout rootView;
    public final TextView tvDbm;
    public final TextView tvMac;
    public final TextView tvModelNumber;

    private ItemHomeDevicelistBinding(RelativeLayout rootView, RelativeLayout deviceListItem, TextView tvDbm, TextView tvMac, TextView tvModelNumber) {
        this.rootView = rootView;
        this.deviceListItem = deviceListItem;
        this.tvDbm = tvDbm;
        this.tvMac = tvMac;
        this.tvModelNumber = tvModelNumber;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ItemHomeDevicelistBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemHomeDevicelistBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_home_devicelist, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemHomeDevicelistBinding bind(View rootView) {
        int i2 = R.id.device_list_item;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
        if (relativeLayout != null) {
            i2 = R.id.tv_dbm;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.tv_mac;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView2 != null) {
                    i2 = R.id.tv_model_number;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView3 != null) {
                        return new ItemHomeDevicelistBinding((RelativeLayout) rootView, relativeLayout, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
