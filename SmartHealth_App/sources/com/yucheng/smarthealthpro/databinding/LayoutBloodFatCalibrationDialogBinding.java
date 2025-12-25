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
import com.contrarywind.view.WheelView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class LayoutBloodFatCalibrationDialogBinding implements ViewBinding {
    public final EditText etInput;
    public final LinearLayout llBottom;
    public final LinearLayout llContainer;
    public final TextView oneText;
    public final WheelView options1;
    public final WheelView options2;
    public final WheelView options3;
    public final LinearLayout optionspicker;
    public final RelativeLayout rlBottomConfirm;
    public final RelativeLayout rlBp;
    public final RelativeLayout rlTopConfirmCancel;
    public final RelativeLayout rlTopShape;
    private final LinearLayout rootView;
    public final TextView tvBottomConfirm;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvRange;
    public final TextView twoText;

    private LayoutBloodFatCalibrationDialogBinding(LinearLayout rootView, EditText etInput, LinearLayout llBottom, LinearLayout llContainer, TextView oneText, WheelView options1, WheelView options2, WheelView options3, LinearLayout optionspicker, RelativeLayout rlBottomConfirm, RelativeLayout rlBp, RelativeLayout rlTopConfirmCancel, RelativeLayout rlTopShape, TextView tvBottomConfirm, TextView tvCancel, TextView tvConfirm, TextView tvRange, TextView twoText) {
        this.rootView = rootView;
        this.etInput = etInput;
        this.llBottom = llBottom;
        this.llContainer = llContainer;
        this.oneText = oneText;
        this.options1 = options1;
        this.options2 = options2;
        this.options3 = options3;
        this.optionspicker = optionspicker;
        this.rlBottomConfirm = rlBottomConfirm;
        this.rlBp = rlBp;
        this.rlTopConfirmCancel = rlTopConfirmCancel;
        this.rlTopShape = rlTopShape;
        this.tvBottomConfirm = tvBottomConfirm;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
        this.tvRange = tvRange;
        this.twoText = twoText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutBloodFatCalibrationDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static LayoutBloodFatCalibrationDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.layout_blood_fat_calibration_dialog, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutBloodFatCalibrationDialogBinding bind(View rootView) {
        int i2 = R.id.et_input;
        EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
        if (editText != null) {
            i2 = R.id.ll_bottom;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
            if (linearLayout != null) {
                LinearLayout linearLayout2 = (LinearLayout) rootView;
                i2 = R.id.one_text;
                TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                if (textView != null) {
                    i2 = R.id.options1;
                    WheelView wheelView = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                    if (wheelView != null) {
                        i2 = R.id.options2;
                        WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                        if (wheelView2 != null) {
                            i2 = R.id.options3;
                            WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(rootView, i2);
                            if (wheelView3 != null) {
                                i2 = R.id.optionspicker;
                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (linearLayout3 != null) {
                                    i2 = R.id.rl_bottom_confirm;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                    if (relativeLayout != null) {
                                        i2 = R.id.rl_bp;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                        if (relativeLayout2 != null) {
                                            i2 = R.id.rl_top_confirm_cancel;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                            if (relativeLayout3 != null) {
                                                i2 = R.id.rl_top_shape;
                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                                if (relativeLayout4 != null) {
                                                    i2 = R.id.tv_bottom_confirm;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                    if (textView2 != null) {
                                                        i2 = R.id.tv_cancel;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                        if (textView3 != null) {
                                                            i2 = R.id.tv_confirm;
                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                            if (textView4 != null) {
                                                                i2 = R.id.tv_range;
                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                if (textView5 != null) {
                                                                    i2 = R.id.two_text;
                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                                                    if (textView6 != null) {
                                                                        return new LayoutBloodFatCalibrationDialogBinding(linearLayout2, editText, linearLayout, linearLayout2, textView, wheelView, wheelView2, wheelView3, linearLayout3, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, textView2, textView3, textView4, textView5, textView6);
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
