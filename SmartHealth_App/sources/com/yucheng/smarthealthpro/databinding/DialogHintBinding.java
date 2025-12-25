package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class DialogHintBinding implements ViewBinding {
    public final Button dialogCancle;
    public final TextView dialogContent;
    public final Button dialogDone;
    public final TextView dialogTitle;
    private final LinearLayout rootView;

    private DialogHintBinding(LinearLayout rootView, Button dialogCancle, TextView dialogContent, Button dialogDone, TextView dialogTitle) {
        this.rootView = rootView;
        this.dialogCancle = dialogCancle;
        this.dialogContent = dialogContent;
        this.dialogDone = dialogDone;
        this.dialogTitle = dialogTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static DialogHintBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogHintBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_hint, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogHintBinding bind(View rootView) {
        int i2 = R.id.dialog_cancle;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.dialog_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
            if (textView != null) {
                i2 = R.id.dialog_done;
                Button button2 = (Button) ViewBindings.findChildViewById(rootView, i2);
                if (button2 != null) {
                    i2 = R.id.dialog_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                    if (textView2 != null) {
                        return new DialogHintBinding((LinearLayout) rootView, button, textView, button2, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
