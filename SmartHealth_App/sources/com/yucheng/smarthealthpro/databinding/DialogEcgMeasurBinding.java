package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class DialogEcgMeasurBinding implements ViewBinding {
    public final TextView ecgSelectHandsNoteContent;
    public final RelativeLayout ecgSelectHandsNoteIv;
    public final View ecgSelectHandsNoteLine;
    public final View ecgSelectHandsNoteLine2;
    public final TextView ecgSelectHandsNoteTitle;
    public final ImageView ivTips;
    public final RadioGroup radioGroup;
    public final RadioButton rbLeft;
    public final RadioButton rbRight;
    private final LinearLayout rootView;
    public final TextView tvConfirm;

    private DialogEcgMeasurBinding(LinearLayout rootView, TextView ecgSelectHandsNoteContent, RelativeLayout ecgSelectHandsNoteIv, View ecgSelectHandsNoteLine, View ecgSelectHandsNoteLine2, TextView ecgSelectHandsNoteTitle, ImageView ivTips, RadioGroup radioGroup, RadioButton rbLeft, RadioButton rbRight, TextView tvConfirm) {
        this.rootView = rootView;
        this.ecgSelectHandsNoteContent = ecgSelectHandsNoteContent;
        this.ecgSelectHandsNoteIv = ecgSelectHandsNoteIv;
        this.ecgSelectHandsNoteLine = ecgSelectHandsNoteLine;
        this.ecgSelectHandsNoteLine2 = ecgSelectHandsNoteLine2;
        this.ecgSelectHandsNoteTitle = ecgSelectHandsNoteTitle;
        this.ivTips = ivTips;
        this.radioGroup = radioGroup;
        this.rbLeft = rbLeft;
        this.rbRight = rbRight;
        this.tvConfirm = tvConfirm;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogEcgMeasurBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogEcgMeasurBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_ecg_measur, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogEcgMeasurBinding bind(View rootView) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i2 = R.id.ecg_select_hands_note_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
        if (textView != null) {
            i2 = R.id.ecg_select_hands_note_iv;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
            if (relativeLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.ecg_select_hands_note_line))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, (i2 = R.id.ecg_select_hands_note_line2))) != null) {
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
                                        return new DialogEcgMeasurBinding((LinearLayout) rootView, textView, relativeLayout, viewFindChildViewById, viewFindChildViewById2, textView2, imageView, radioGroup, radioButton, radioButton2, textView3);
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
