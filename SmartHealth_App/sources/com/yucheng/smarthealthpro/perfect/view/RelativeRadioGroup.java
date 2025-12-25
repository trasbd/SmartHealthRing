package com.yucheng.smarthealthpro.perfect.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

/* loaded from: classes5.dex */
public class RelativeRadioGroup extends RelativeLayout implements CompoundButton.OnCheckedChangeListener {
    private int checkId;
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;

    public RelativeRadioGroup(Context context) {
        super(context);
        this.checkId = -1;
    }

    public RelativeRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.checkId = -1;
    }

    public RelativeRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.checkId = -1;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b2) {
        super.onLayout(changed, l, t, r, b2);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt instanceof RadioButton) && !(childAt instanceof CompoundButton.OnCheckedChangeListener)) {
                ((RadioButton) childAt).setOnCheckedChangeListener(this);
            }
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setCheck(buttonView.getId(), isChecked);
    }

    public void check(int checkId) {
        if (checkId == -1 || this.checkId == checkId) {
            return;
        }
        setCheck(checkId, true);
    }

    public void clearCheck() {
        setCheck(-1, false);
    }

    public int getCheckedRadioButtonId() {
        return this.checkId;
    }

    private void setCheck(int checkId, boolean isChecked) {
        if (checkId == -1 || this.checkId != checkId) {
            if (checkId != -1) {
                CompoundButton compoundButton = (CompoundButton) findViewById(checkId);
                if (checkId != this.checkId && isChecked) {
                    this.checkId = checkId;
                    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.mChildOnCheckedChangeListener;
                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(compoundButton, true);
                    }
                    for (int i2 = 0; i2 < getChildCount(); i2++) {
                        View childAt = getChildAt(i2);
                        boolean z = childAt instanceof RadioButton;
                        if (z && childAt.getId() != checkId) {
                            ((RadioButton) childAt).setChecked(false);
                        } else if (z && childAt.getId() == checkId) {
                            ((RadioButton) childAt).setChecked(true);
                        }
                    }
                }
                if (checkId != this.checkId || isChecked) {
                    return;
                }
                this.checkId = checkId;
                CompoundButton.OnCheckedChangeListener onCheckedChangeListener2 = this.mChildOnCheckedChangeListener;
                if (onCheckedChangeListener2 != null) {
                    onCheckedChangeListener2.onCheckedChanged(compoundButton, false);
                    return;
                }
                return;
            }
            if (this.checkId != -1) {
                this.checkId = -1;
                CompoundButton compoundButton2 = (CompoundButton) findViewById(-1);
                if (compoundButton2 instanceof RadioButton) {
                    compoundButton2.setChecked(false);
                }
            }
        }
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener) {
        this.mChildOnCheckedChangeListener = mChildOnCheckedChangeListener;
    }
}
