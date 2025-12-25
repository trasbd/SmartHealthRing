package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wevey.selector.dialog.MDAlertDialog;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class CustomDialog extends MDAlertDialog {
    public CustomDialog(Builder builder) throws NoSuchFieldException {
        super(builder);
        try {
            Field declaredField = MDAlertDialog.class.getDeclaredField("mRightBtn");
            declaredField.setAccessible(true);
            TextView textView = (TextView) declaredField.get(this);
            if (textView != null) {
                textView.setMaxLines(1);
                textView.setMinWidth(100);
                textView.setMaxWidth(Integer.MAX_VALUE);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 17;
                textView.setLayoutParams(layoutParams);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static class Builder extends MDAlertDialog.Builder {
        public Builder(Context context) {
            super(context);
        }

        @Override // com.wevey.selector.dialog.MDAlertDialog.Builder
        public MDAlertDialog build() {
            return new CustomDialog(this);
        }
    }
}
