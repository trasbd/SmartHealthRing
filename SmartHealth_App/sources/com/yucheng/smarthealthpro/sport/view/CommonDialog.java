package com.yucheng.smarthealthpro.sport.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.allen.library.SuperTextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;

/* loaded from: classes5.dex */
public class CommonDialog extends Dialog {
    private boolean alignStart;
    private String cancel;
    private String cancelColor;
    private String confirm;
    private String confirmColor;
    private int editTextResId;
    private int imageResId;
    private boolean isSingle;
    private Button mCancel;
    private View mColumnLineView;
    private Button mConfirm;
    private EditText mEditText;
    private ImageView mImage;
    private TextView mMessage;
    private SuperTextView mMessage2;
    private int mMessageResId;
    private TextView mTitle;
    private String message;
    private String message2;
    public OnClickBottomListener onClickBottomListener;
    boolean outSideTouch;
    private boolean smallMessage;
    private String title;

    public interface OnClickBottomListener {
        void onCancelClick();

        void onConfirmClick();

        void onEditTextConfirmClick(String mEditText);
    }

    public CommonDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.isSingle = false;
        this.imageResId = -1;
        this.editTextResId = -1;
        this.mMessageResId = -1;
        this.alignStart = false;
        this.smallMessage = false;
        this.outSideTouch = true;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_layout);
        setCanceledOnTouchOutside(this.outSideTouch);
        initView();
        refreshView();
        initEvent();
    }

    private void initEvent() {
        this.mConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.view.CommonDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CommonDialog.this.onClickBottomListener != null) {
                    if (CommonDialog.this.editTextResId != -1) {
                        CommonDialog.this.onClickBottomListener.onEditTextConfirmClick(CommonDialog.this.mEditText.getText().toString());
                    } else {
                        CommonDialog.this.onClickBottomListener.onConfirmClick();
                    }
                }
            }
        });
        this.mCancel.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.view.CommonDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CommonDialog.this.onClickBottomListener != null) {
                    CommonDialog.this.onClickBottomListener.onCancelClick();
                }
            }
        });
    }

    private void refreshView() {
        if (!TextUtils.isEmpty(this.title)) {
            this.mTitle.setText(this.title);
            this.mTitle.setVisibility(0);
        } else {
            this.mTitle.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.message)) {
            this.mMessage.setText(this.message);
            if (this.alignStart) {
                this.mMessage.setTextAlignment(2);
            }
            if (this.smallMessage) {
                this.mMessage.setTextSize(1, 14.0f);
            }
        }
        if (!TextUtils.isEmpty(this.message2)) {
            this.mMessage2.setCenterString(this.message2);
            this.mMessage2.setVisibility(0);
            this.mMessage.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.confirm)) {
            this.mConfirm.setText(this.confirm);
        } else {
            this.mConfirm.setText(getContext().getString(R.string.ok));
        }
        if (!TextUtils.isEmpty(this.cancel)) {
            this.mCancel.setText(this.cancel);
        } else {
            this.mCancel.setText(getContext().getString(R.string.cancel));
        }
        if (!TextUtils.isEmpty(this.confirmColor)) {
            this.mConfirm.setTextColor(Color.parseColor(this.confirmColor));
        }
        if (!TextUtils.isEmpty(this.cancelColor)) {
            this.mCancel.setTextColor(Color.parseColor(this.cancelColor));
        }
        int i2 = this.imageResId;
        if (i2 != -1) {
            this.mImage.setImageResource(i2);
            this.mImage.setVisibility(0);
        } else {
            this.mImage.setVisibility(8);
        }
        if (this.editTextResId != -1) {
            this.mEditText.setVisibility(0);
        } else {
            this.mEditText.setVisibility(8);
        }
        if (this.isSingle) {
            this.mColumnLineView.setVisibility(8);
            this.mCancel.setVisibility(8);
        } else {
            this.mCancel.setVisibility(0);
            this.mColumnLineView.setVisibility(0);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        refreshView();
    }

    private void initView() {
        this.mCancel = (Button) findViewById(R.id.cancel);
        this.mConfirm = (Button) findViewById(R.id.confirm);
        this.mTitle = (TextView) findViewById(R.id.tv_title);
        this.mMessage = (TextView) findViewById(R.id.tv_message);
        SuperTextView superTextView = (SuperTextView) findViewById(R.id.tv_message2);
        this.mMessage2 = superTextView;
        superTextView.getCenterTextView().setLineSpacing(DpUtil.dp2px(getContext(), 3.0f), 1.2f);
        this.mImage = (ImageView) findViewById(R.id.iv_image);
        this.mEditText = (EditText) findViewById(R.id.ed_text);
        this.mColumnLineView = findViewById(R.id.column_line);
    }

    public CommonDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public CommonDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonDialog setMessage2(String message) {
        this.message2 = message;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public CommonDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommonDialog setAlignStart(boolean isAlignStart) {
        this.alignStart = isAlignStart;
        return this;
    }

    public CommonDialog setSmallerMessage(boolean isSmallerMessage) {
        this.smallMessage = isSmallerMessage;
        return this;
    }

    public String getConfirm() {
        return this.confirm;
    }

    public CommonDialog setConfirm(String confirm) {
        this.confirm = confirm;
        return this;
    }

    public CommonDialog setConfirmColor(String confirmColor) {
        this.confirmColor = confirmColor;
        return this;
    }

    public CommonDialog setCancelColor(String cancelColor) {
        this.cancelColor = cancelColor;
        return this;
    }

    public String getCancel() {
        return this.cancel;
    }

    public CommonDialog setCancel(String cancel) {
        this.cancel = cancel;
        return this;
    }

    public boolean isSingle() {
        return this.isSingle;
    }

    public CommonDialog setSingle(boolean single) {
        this.isSingle = single;
        return this;
    }

    public int getImageResId() {
        return this.imageResId;
    }

    public CommonDialog setImageResId(int imageResId) {
        this.imageResId = imageResId;
        return this;
    }

    public int getEditTextResId() {
        return this.editTextResId;
    }

    public CommonDialog setEditTextResId(int editTextResId) {
        this.editTextResId = editTextResId;
        return this;
    }

    public int getMessageResId() {
        return this.mMessageResId;
    }

    public CommonDialog setMessageResId(int mMessageResId) {
        this.mMessageResId = mMessageResId;
        return this;
    }

    public CommonDialog setOutSideTouch(boolean outSideTouch) {
        this.outSideTouch = outSideTouch;
        return this;
    }
}
