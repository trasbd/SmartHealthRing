package com.yucheng.smarthealthpro.sport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.ScaleAnimation;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityCountdownBinding;
import com.yucheng.smarthealthpro.sport.bean.SportTabItem;
import com.yucheng.smarthealthpro.sport.view.TimeDownView;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.ycbtsdk.YCBTClient;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class SportCountDownActivity extends BaseVbActivity<ActivityCountdownBinding> {
    private Handler mHandler = new Handler();
    private int mTime = 3;
    private Runnable runnable = new Runnable() { // from class: com.yucheng.smarthealthpro.sport.activity.SportCountDownActivity.1
        @Override // java.lang.Runnable
        public void run() {
            if (SportCountDownActivity.this.isFinishing()) {
                return;
            }
            if (SportCountDownActivity.this.mTime == 0) {
                SportCountDownActivity.this.tv.setText(SportCountDownActivity.this.getString(R.string.sport_countdown_go));
                Intent intent = new Intent(SportCountDownActivity.this.context, (Class<?>) SportRunningActivity.class);
                intent.putExtra("data", SportCountDownActivity.this.sportTabItem);
                intent.putExtra("isLocalSport", SportCountDownActivity.this.getIntent().getBooleanExtra("isLocalSport", true));
                SportCountDownActivity.this.startActivity(intent);
                SportCountDownActivity.this.finish();
                return;
            }
            SportCountDownActivity.this.tv.setText("" + SportCountDownActivity.this.mTime);
            SportCountDownActivity.this.initAnimationData();
            SportCountDownActivity.this.mHandler.postDelayed(SportCountDownActivity.this.runnable, 1000L);
            SportCountDownActivity.this.mTime--;
        }
    };
    private SportTabItem sportTabItem;
    TimeDownView tv;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
        this.sportTabItem = (SportTabItem) getIntent().getSerializableExtra("data");
        if (Constant.isHeGe() && YCBTClient.connectState() == 10) {
            YCBTClient.appRunMode(0, this.sportTabItem.sportType, null);
        }
        initAnimationData();
        this.mHandler.postDelayed(this.runnable, 1000L);
        hideBottomUIMenu();
    }

    private void initView() {
        this.tv = ((ActivityCountdownBinding) this.mBinding).tv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAnimationData() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(1000L);
        this.tv.setAnimation(scaleAnimation);
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
    }
}
