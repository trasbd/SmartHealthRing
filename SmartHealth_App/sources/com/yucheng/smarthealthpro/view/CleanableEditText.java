package com.yucheng.smarthealthpro.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

/* loaded from: classes5.dex */
public class CleanableEditText extends EditText {
    private CustomDeletedCallback customDeletedCallback;
    private boolean isHasFocus;
    private Drawable mRightDrawable;

    public interface CustomDeletedCallback {
        void onDeleted(CleanableEditText cleanableEditText);
    }

    public CleanableEditText(Context context) {
        super(context);
        init();
    }

    public CleanableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CleanableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.mRightDrawable = getCompoundDrawables()[2];
        setOnFocusChangeListener(new FocusChangeListenerImpl());
        addTextChangedListener(new TextWatcherImpl());
        setClearDrawableVisible(false);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1 && event.getX() > getWidth() - getTotalPaddingRight() && event.getX() < getWidth() - getPaddingRight()) {
            setText("");
            this.customDeletedCallback.onDeleted(this);
        }
        return super.onTouchEvent(event);
    }

    private class FocusChangeListenerImpl implements View.OnFocusChangeListener {
        private FocusChangeListenerImpl() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View v, boolean hasFocus) {
            CleanableEditText.this.isHasFocus = hasFocus;
            if (CleanableEditText.this.isHasFocus) {
                CleanableEditText.this.setClearDrawableVisible(CleanableEditText.this.getText().toString().length() >= 1);
            } else {
                CleanableEditText.this.setClearDrawableVisible(false);
            }
        }
    }

    private class TextWatcherImpl implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        private TextWatcherImpl() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            CleanableEditText.this.setClearDrawableVisible(CleanableEditText.this.getText().toString().length() >= 1);
        }
    }

    protected void setClearDrawableVisible(boolean isVisible) {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], isVisible ? this.mRightDrawable : null, getCompoundDrawables()[3]);
    }

    public void setShakeAnimation() {
        setAnimation(shakeAnimation(5));
    }

    public Animation shakeAnimation(int CycleTimes) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 10.0f, 0.0f, 10.0f);
        translateAnimation.setInterpolator(new CycleInterpolator(CycleTimes));
        translateAnimation.setDuration(1000L);
        return translateAnimation;
    }

    public CleanableEditText setCustomDeletedCallback(CustomDeletedCallback customDeletedCallback) {
        this.customDeletedCallback = customDeletedCallback;
        return this;
    }
}
