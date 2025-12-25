package com.yucheng.smarthealthpro.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes4.dex */
public class MyTextView extends TextView {
    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
