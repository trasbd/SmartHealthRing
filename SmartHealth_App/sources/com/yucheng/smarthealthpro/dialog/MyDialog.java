package com.yucheng.smarthealthpro.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setProgress(int progress) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.dial_item_progress);
        TextView textView = (TextView) findViewById(R.id.dial_item_progress_tv);
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
        if (textView != null) {
            textView.setText(progress + "%");
        }
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    public void setTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(title);
        }
    }
}
