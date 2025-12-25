package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.allen.library.SuperTextView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class CommonDialogLayoutBinding implements ViewBinding {
    public final Button cancel;
    public final View columnLine;
    public final Button confirm;
    public final EditText edText;
    public final ImageView ivImage;
    private final LinearLayout rootView;
    public final TextView tvMessage;
    public final SuperTextView tvMessage2;
    public final TextView tvTitle;

    private CommonDialogLayoutBinding(LinearLayout rootView, Button cancel, View columnLine, Button confirm, EditText edText, ImageView ivImage, TextView tvMessage, SuperTextView tvMessage2, TextView tvTitle) {
        this.rootView = rootView;
        this.cancel = cancel;
        this.columnLine = columnLine;
        this.confirm = confirm;
        this.edText = edText;
        this.ivImage = ivImage;
        this.tvMessage = tvMessage;
        this.tvMessage2 = tvMessage2;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static CommonDialogLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static CommonDialogLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.common_dialog_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static CommonDialogLayoutBinding bind(View rootView) {
        View viewFindChildViewById;
        int i2 = R.id.cancel;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null && (viewFindChildViewById = ViewBindings.findChildViewById(rootView, (i2 = R.id.column_line))) != null) {
            i2 = R.id.confirm;
            Button button2 = (Button) ViewBindings.findChildViewById(rootView, i2);
            if (button2 != null) {
                i2 = R.id.ed_text;
                EditText editText = (EditText) ViewBindings.findChildViewById(rootView, i2);
                if (editText != null) {
                    i2 = R.id.iv_image;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                    if (imageView != null) {
                        i2 = R.id.tv_message;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            i2 = R.id.tv_message2;
                            SuperTextView superTextView = (SuperTextView) ViewBindings.findChildViewById(rootView, i2);
                            if (superTextView != null) {
                                i2 = R.id.tv_title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView2 != null) {
                                    return new CommonDialogLayoutBinding((LinearLayout) rootView, button, viewFindChildViewById, button2, editText, imageView, textView, superTextView, textView2);
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
