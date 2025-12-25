package com.yucheng.smarthealthpro.me.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.care.zxing.activity.CaptureActivity;
import com.yucheng.smarthealthpro.care.zxing.util.QrImageUtil;
import com.yucheng.smarthealthpro.databinding.ActivityMeVisitingCardBinding;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MeVisitingCardActivity extends BaseVbActivity<ActivityMeVisitingCardBinding> {
    private Bitmap bitmap;
    private String content;
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.activity.MeVisitingCardActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i2 = msg.what;
            if (i2 == 0) {
                ToastUtil.getInstance(MeVisitingCardActivity.this).toast(MeVisitingCardActivity.this.getString(R.string.setup_successful));
                MeVisitingCardActivity.this.bitmap = null;
                MeVisitingCardActivity.this.imgVisitingCardQr.setImageResource(R.mipmap.help_feedback_img_add);
                return false;
            }
            if (i2 == 1) {
                ToastUtil.getInstance(MeVisitingCardActivity.this).toast(MeVisitingCardActivity.this.getString(R.string.health_set_failed));
                return false;
            }
            if (i2 != 2 || MeVisitingCardActivity.this.bitmap == null) {
                return false;
            }
            MeVisitingCardActivity.this.imgVisitingCardQr.setImageBitmap(MeVisitingCardActivity.this.bitmap);
            return false;
        }
    });
    ImageView imgVisitingCardQr;
    TextView tvVisitingCardBunding;
    private int type;

    private void initData() {
    }

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.imgVisitingCardQr = ((ActivityMeVisitingCardBinding) this.mBinding).ivVisitingCardQr;
        TextView textView = ((ActivityMeVisitingCardBinding) this.mBinding).tvVisitingCardBunding;
        this.tvVisitingCardBunding = textView;
        textView.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeVisitingCardActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        this.imgVisitingCardQr.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeVisitingCardActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.visiting_card));
        showBack();
    }

    private void initDialog() {
        final CommonDialog commonDialog = new CommonDialog(this.context);
        commonDialog.setMessage(getString(R.string.no_add_visiting_card)).setTitle(getString(R.string.prompt)).setSingle(true).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.activity.MeVisitingCardActivity.2
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                commonDialog.dismiss();
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
                commonDialog.dismiss();
            }
        }).show();
    }

    private void sendData() {
        YCBTClient.appSendCardNumber(this.type, this.content, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeVisitingCardActivity.3
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int i2, float v, HashMap hashMap) {
                if (i2 == 0) {
                    MeVisitingCardActivity.this.handler.sendEmptyMessage(0);
                } else {
                    MeVisitingCardActivity.this.handler.sendEmptyMessage(1);
                }
            }
        });
    }

    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_visiting_card_bunding) {
            if (this.bitmap == null) {
                initDialog();
                return;
            } else {
                sendData();
                return;
            }
        }
        if (view.getId() == R.id.iv_visiting_card_qr && PermissionUtil.openCameraPermission(this)) {
            startActivityForResult(new Intent(this, (Class<?>) CaptureActivity.class).putExtra("type", 1), 10010);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Resources.NotFoundException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10010 && resultCode == 100123) {
            String stringExtra = data.getStringExtra("result");
            int i2 = 0;
            if (stringExtra.contains("https://u.wechat.com/")) {
                int i3 = R.mipmap.icon_push_wechat;
                this.type = 0;
                i2 = i3;
            } else if (stringExtra.contains("https://qm.qq.com/")) {
                i2 = R.mipmap.icon_push_qq;
                this.type = 1;
            } else if (stringExtra.contains("https://facebook.com/")) {
                i2 = R.mipmap.icon_push_facebook;
                this.type = 2;
            } else if (stringExtra.contains("https://twitter.com/")) {
                i2 = R.mipmap.icon_push_twitter;
                this.type = 3;
            } else if (stringExtra.contains("https://wa.me/")) {
                i2 = R.mipmap.icon_push_whatsapp;
                this.type = 4;
            } else if (stringExtra.contains("https://instagram.com/") || stringExtra.contains("http://instagram.com/")) {
                i2 = R.mipmap.icon_push_ins;
                this.type = 5;
            }
            if (i2 == 0) {
                ToastUtil.getInstance(this).toast(R.string.recognition_failed);
                this.imgVisitingCardQr.setImageResource(R.mipmap.help_feedback_img_add);
                return;
            }
            this.content = stringExtra;
            float f2 = getResources().getDisplayMetrics().density;
            int i4 = (int) (f2 * 250.0f);
            this.bitmap = QrImageUtil.createQRImage(this, this.content, QrImageUtil.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), i2), 70, 70, 7), i4, i4);
            this.handler.sendEmptyMessage(2);
        }
    }
}
