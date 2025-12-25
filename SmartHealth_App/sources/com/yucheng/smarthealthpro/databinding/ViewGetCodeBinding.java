package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ViewGetCodeBinding implements ViewBinding {
    public final EditText etCode;
    public final LinearLayout llCode;
    private final RelativeLayout rootView;
    public final TextView tvCode1;
    public final TextView tvCode2;
    public final TextView tvCode3;
    public final TextView tvCode4;
    public final TextView tvCode5;
    public final TextView tvCode6;
    public final View v1;
    public final View v2;
    public final View v3;
    public final View v4;
    public final View v5;
    public final View v6;

    private ViewGetCodeBinding(RelativeLayout rootView, EditText etCode, LinearLayout llCode, TextView tvCode1, TextView tvCode2, TextView tvCode3, TextView tvCode4, TextView tvCode5, TextView tvCode6, View v1, View v2, View v3, View v4, View v5, View v6) {
        this.rootView = rootView;
        this.etCode = etCode;
        this.llCode = llCode;
        this.tvCode1 = tvCode1;
        this.tvCode2 = tvCode2;
        this.tvCode3 = tvCode3;
        this.tvCode4 = tvCode4;
        this.tvCode5 = tvCode5;
        this.tvCode6 = tvCode6;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
        this.v6 = v6;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ViewGetCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ViewGetCodeBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.view_get_code, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ViewGetCodeBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        View viewFindChildViewById4;
        View viewFindChildViewById5;
        View viewFindChildViewById6;
        int i2 = R.id.et_code;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.ll_code;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout != null) {
                i2 = R.id.tv_code1;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.tv_code2;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        i2 = R.id.tv_code3;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView3 != null) {
                            i2 = R.id.tv_code4;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView4 != null) {
                                i2 = R.id.tv_code5;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView5 != null) {
                                    i2 = R.id.tv_code6;
                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView6 != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.v1))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i2 = R.id.v2))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, (i2 = R.id.v3))) != null && (viewFindChildViewById4 = ViewBindings.findChildViewById(rootView, (i2 = R.id.v4))) != null && (viewFindChildViewById5 = ViewBindings.findChildViewById(rootView, (i2 = R.id.v5))) != null && (viewFindChildViewById6 = ViewBindings.findChildViewById(rootView, (i2 = R.id.v6))) != null) {
                                        return new ViewGetCodeBinding((RelativeLayout) rootView, editText, linearLayout, textView, textView2, textView3, textView4, textView5, textView6, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, viewFindChildViewById4, viewFindChildViewById5, viewFindChildViewById6);
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
