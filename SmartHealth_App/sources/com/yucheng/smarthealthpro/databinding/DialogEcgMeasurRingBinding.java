package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class DialogEcgMeasurRingBinding implements ViewBinding {
    public final TextView ecgSelectHandsNoteContent;
    public final View ecgSelectHandsNoteLine;
    public final TextView ecgSelectHandsNoteTitle;
    public final ImageView ivTips;
    public final RadioGroup radioGroup;
    public final RadioButton rbLeft;
    public final RadioButton rbRight;
    private final LinearLayout rootView;
    public final TextView tvConfirm;
    public final TextView tvLeft;
    public final View view1;
    public final View view2;

    private DialogEcgMeasurRingBinding(LinearLayout rootView, TextView ecgSelectHandsNoteContent, View ecgSelectHandsNoteLine, TextView ecgSelectHandsNoteTitle, ImageView ivTips, RadioGroup radioGroup, RadioButton rbLeft, RadioButton rbRight, TextView tvConfirm, TextView tvLeft, View view1, View view2) {
        this.rootView = rootView;
        this.ecgSelectHandsNoteContent = ecgSelectHandsNoteContent;
        this.ecgSelectHandsNoteLine = ecgSelectHandsNoteLine;
        this.ecgSelectHandsNoteTitle = ecgSelectHandsNoteTitle;
        this.ivTips = ivTips;
        this.radioGroup = radioGroup;
        this.rbLeft = rbLeft;
        this.rbRight = rbRight;
        this.tvConfirm = tvConfirm;
        this.tvLeft = tvLeft;
        this.view1 = view1;
        this.view2 = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogEcgMeasurRingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogEcgMeasurRingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_ecg_measur_ring, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogEcgMeasurRingBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        View viewFindChildViewById3;
        int i2 = R.id.ecg_select_hands_note_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.ecg_select_hands_note_line))) != null) {
            i2 = R.id.ecg_select_hands_note_title;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView2 != null) {
                i2 = R.id.iv_tips;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView != null) {
                    i2 = R.id.radio_group;
                    RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(rootView, i2);
                    if (radioGroup != null) {
                        i2 = R.id.rb_left;
                        RadioButton radioButton = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                        if (radioButton != null) {
                            i2 = R.id.rb_right;
                            RadioButton radioButton2 = (RadioButton) ViewBindings.findChildViewById(rootView, i2);
                            if (radioButton2 != null) {
                                i2 = R.id.tv_confirm;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView3 != null) {
                                    i2 = R.id.tv_left;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView4 != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i2 = R.id.view1))) != null && (viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, (i2 = R.id.view2))) != null) {
                                        return new DialogEcgMeasurRingBinding((LinearLayout) rootView, textView, viewFindChildViewById, textView2, imageView, radioGroup, radioButton, radioButton2, textView3, textView4, viewFindChildViewById2, viewFindChildViewById3);
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
