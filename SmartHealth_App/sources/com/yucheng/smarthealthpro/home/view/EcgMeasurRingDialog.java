package com.yucheng.smarthealthpro.home.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class EcgMeasurRingDialog extends BaseDialog<EcgMeasurRingDialog> {
    private OnDialogButtonClickListener buttonClickListner;
    private TextView confirm;
    private int isLeftRight;
    private RadioButton leftRb;
    private RadioGroup mRadioGroup;
    private RadioButton rightRb;
    private ImageView tips;

    public interface OnDialogButtonClickListener {
        void onLeftClick();

        void onRightClick();
    }

    public void setOnButtonClickListener(OnDialogButtonClickListener listener) {
        this.buttonClickListner = listener;
    }

    public EcgMeasurRingDialog(Context context) {
        super(context);
        this.isLeftRight = 0;
    }

    @Override // com.flyco.dialog.widget.base.BaseDialog
    public View onCreateView() {
        widthScale(0.85f);
        View viewInflate = View.inflate(getContext(), R.layout.dialog_ecg_measur_ring, null);
        this.mRadioGroup = (RadioGroup) viewInflate.findViewById(R.id.radio_group);
        this.leftRb = (RadioButton) viewInflate.findViewById(R.id.rb_left);
        this.rightRb = (RadioButton) viewInflate.findViewById(R.id.rb_right);
        this.confirm = (TextView) viewInflate.findViewById(R.id.tv_confirm);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_tips);
        this.tips = imageView;
        imageView.setBackgroundResource(R.mipmap.ecg_tips_pic_left_ring);
        viewInflate.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5.0f)));
        return viewInflate;
    }

    @Override // com.flyco.dialog.widget.base.BaseDialog
    public void setUiBeforShow() {
        this.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.home.view.EcgMeasurRingDialog.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_left) {
                    EcgMeasurRingDialog.this.isLeftRight = 0;
                    EcgMeasurRingDialog.this.tips.setBackgroundResource(R.mipmap.ecg_tips_pic_left_ring);
                } else if (checkedId == R.id.rb_right) {
                    EcgMeasurRingDialog.this.isLeftRight = 1;
                    EcgMeasurRingDialog.this.tips.setBackgroundResource(R.mipmap.ecg_tips_pic_right_ring);
                }
            }
        });
        if (((Integer) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.ecgWearHand, 0)).intValue() == 0) {
            this.leftRb.setChecked(true);
        } else {
            this.rightRb.setChecked(true);
        }
        this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.view.EcgMeasurRingDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgMeasurRingDialog.this.isLeftRight == 0) {
                    EcgMeasurRingDialog.this.buttonClickListner.onLeftClick();
                } else {
                    EcgMeasurRingDialog.this.buttonClickListner.onRightClick();
                }
                EcgMeasurRingDialog.this.dismiss();
            }
        });
    }
}
