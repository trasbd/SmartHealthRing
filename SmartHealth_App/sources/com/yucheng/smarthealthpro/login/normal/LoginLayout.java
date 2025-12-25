package com.yucheng.smarthealthpro.login.normal;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.LinearLayout;

/* loaded from: classes5.dex */
public class LoginLayout extends LinearLayout {
    private int[] mInsets;

    public LoginLayout(Context context) {
        super(context);
        this.mInsets = new int[4];
    }

    public LoginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mInsets = new int[4];
    }

    public LoginLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mInsets = new int[4];
    }

    public LoginLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mInsets = new int[4];
    }

    public final int[] getInsets() {
        return this.mInsets;
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets insets) {
        this.mInsets[0] = insets.getSystemWindowInsetLeft();
        this.mInsets[1] = insets.getSystemWindowInsetTop();
        this.mInsets[2] = insets.getSystemWindowInsetRight();
        return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0, insets.getSystemWindowInsetBottom()));
    }

    @Override // android.view.View
    protected final boolean fitSystemWindows(Rect insets) {
        this.mInsets[0] = insets.left;
        this.mInsets[1] = insets.top;
        this.mInsets[2] = insets.right;
        return super.fitSystemWindows(insets);
    }
}
