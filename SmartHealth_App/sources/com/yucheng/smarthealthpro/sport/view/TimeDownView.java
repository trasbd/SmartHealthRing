package com.yucheng.smarthealthpro.sport.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class TimeDownView extends TextView {
    private final int AFTER_LAST_TIME_DIMISS;
    private final int AFTER_LAST_TIME_NODIMISS;
    private final int DRAW_TEXT_NO;
    private final int DRAW_TEXT_YES;
    private int afterDownDimissFlag;
    private AnimationSet animationSet;
    private long delayMills;
    private int downCount;
    private DownHandler downHandler;
    private DownTimeWatcher downTimeWatcher;
    private DownTimerTask downTimerTask;
    private int drawTextFlag;
    private long intervalMills;
    private int lastDown;
    private boolean startDefaultAnimFlag;
    private Timer timer;

    public interface DownTimeWatcher {
        void onLastTime(int num);

        void onLastTimeFinish(int num);

        void onTime(int num);
    }

    public TimeDownView(Context context) {
        this(context, null);
    }

    public TimeDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.downTimeWatcher = null;
        this.DRAW_TEXT_YES = 1;
        this.DRAW_TEXT_NO = 0;
        this.drawTextFlag = 1;
        this.AFTER_LAST_TIME_DIMISS = 1;
        this.AFTER_LAST_TIME_NODIMISS = 0;
        this.afterDownDimissFlag = 1;
        this.startDefaultAnimFlag = true;
        init();
    }

    public TimeDownView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.downTimeWatcher = null;
        this.DRAW_TEXT_YES = 1;
        this.DRAW_TEXT_NO = 0;
        this.drawTextFlag = 1;
        this.AFTER_LAST_TIME_DIMISS = 1;
        this.AFTER_LAST_TIME_NODIMISS = 0;
        this.afterDownDimissFlag = 1;
        this.startDefaultAnimFlag = true;
        init();
    }

    private void init() {
        if (this.timer == null) {
            this.timer = new Timer();
        }
        if (this.animationSet == null) {
            this.animationSet = new AnimationSet(true);
        }
        if (this.downHandler == null) {
            this.downHandler = new DownHandler();
        }
        setGravity(17);
    }

    public void downSecond(int seconds) {
        downTime(seconds, 0, 0L, 1000L);
    }

    public void downTime(int downCount, int lastDown, long delayMills, long intervalMills) {
        this.downCount = downCount;
        this.lastDown = lastDown;
        this.delayMills = delayMills;
        this.intervalMills = intervalMills;
        initDefaultAnimate();
        if (this.downTimerTask == null) {
            this.downTimerTask = new DownTimerTask();
        }
        this.timer.schedule(this.downTimerTask, delayMills, intervalMills);
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (8 == visibility) {
            this.timer.cancel();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.drawTextFlag == 0) {
            return;
        }
        super.onDraw(canvas);
    }

    public void cancel() {
        this.timer.cancel();
    }

    private class DownTimerTask extends TimerTask {
        private DownTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (TimeDownView.this.downCount >= TimeDownView.this.lastDown - 1) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                TimeDownView.this.downHandler.sendMessage(messageObtain);
            }
        }
    }

    public void setOnTimeDownListener(DownTimeWatcher downTimeWatcher) {
        this.downTimeWatcher = downTimeWatcher;
    }

    private class DownHandler extends Handler {
        private DownHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (TimeDownView.this.downTimeWatcher != null) {
                    TimeDownView.this.downTimeWatcher.onTime(TimeDownView.this.downCount);
                }
                if (TimeDownView.this.downCount >= TimeDownView.this.lastDown - 1) {
                    TimeDownView.this.drawTextFlag = 1;
                    if (TimeDownView.this.downCount >= TimeDownView.this.lastDown) {
                        TimeDownView.this.setText(TimeDownView.this.downCount + "");
                        TimeDownView.this.startDefaultAnimate();
                        if (TimeDownView.this.downCount == TimeDownView.this.lastDown && TimeDownView.this.downTimeWatcher != null) {
                            TimeDownView.this.downTimeWatcher.onLastTime(TimeDownView.this.downCount);
                        }
                    } else if (TimeDownView.this.downCount == TimeDownView.this.lastDown - 1) {
                        if (TimeDownView.this.downTimeWatcher != null) {
                            TimeDownView.this.downTimeWatcher.onLastTimeFinish(TimeDownView.this.downCount);
                        }
                        if (TimeDownView.this.afterDownDimissFlag == 1) {
                            TimeDownView.this.drawTextFlag = 0;
                        }
                        TimeDownView.this.invalidate();
                        TimeDownView.this.timer.cancel();
                    }
                    TimeDownView.this.downCount--;
                }
            }
        }
    }

    public void setAfterDownNoDimiss() {
        this.afterDownDimissFlag = 0;
    }

    public void setAferDownDimiss() {
        this.afterDownDimissFlag = 1;
    }

    public void closeDefaultAnimate() {
        this.animationSet.reset();
        this.startDefaultAnimFlag = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDefaultAnimate() {
        if (this.startDefaultAnimFlag) {
            this.animationSet.startNow();
        }
    }

    private void initDefaultAnimate() {
        if (this.animationSet == null) {
            this.animationSet = new AnimationSet(true);
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(this.intervalMills);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
        alphaAnimation.setDuration(this.intervalMills);
        this.animationSet.addAnimation(scaleAnimation);
        this.animationSet.addAnimation(alphaAnimation);
        this.animationSet.setInterpolator(new AccelerateInterpolator());
        setAnimation(this.animationSet);
    }
}
