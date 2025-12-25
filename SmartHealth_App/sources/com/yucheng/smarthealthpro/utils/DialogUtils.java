package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDAlertDialog;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.dialog.MyDialog;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.CustomDialog;

/* loaded from: classes5.dex */
public class DialogUtils {
    public static void setTitle(String title) {
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, 1).show();
    }

    public static void showToast(Context context, int msgId) {
        Toast.makeText(context, msgId, 1).show();
    }

    public static void showToastShort(Context context, int msgId) {
        Toast.makeText(context, msgId, 0).show();
    }

    public static void showToastShort(Context context, String msg) {
        Toast.makeText(context, msg, 0).show();
    }

    public static Dialog createLoadingDialog(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.dialog_view);
        MyDialog myDialog = new MyDialog(context, R.style.loading_dialog);
        myDialog.setCancelable(true);
        myDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(-2, -2));
        return myDialog;
    }

    public static Dialog createLoadingDialog(Context context, boolean cancelable) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.dialog_view);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_title);
        textView.setText("");
        textView.setVisibility(8);
        MyDialog myDialog = new MyDialog(context, R.style.loading_dialog);
        myDialog.setCancelable(cancelable);
        myDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(-2, -2));
        return myDialog;
    }

    public static Dialog createLoadingDialog(Context context, int strId) {
        return createLoadingDialog(context, context.getString(strId));
    }

    public static Dialog createLoadingDialog(Context context, String str) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.dialog_view);
        ((TextView) viewInflate.findViewById(R.id.tv_title)).setText(str);
        MyDialog myDialog = new MyDialog(context, R.style.loading_dialog);
        myDialog.setCancelable(false);
        myDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(-2, -2));
        return myDialog;
    }

    public static Dialog createLoadingDialog(Context context, String str, boolean cancelable) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.dialog_view);
        ((TextView) viewInflate.findViewById(R.id.tv_title)).setText(str);
        MyDialog myDialog = new MyDialog(context, R.style.loading_dialog);
        myDialog.setCancelable(cancelable);
        myDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(-2, -2));
        return myDialog;
    }

    public static Dialog createUpgradingDialog(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.dialog_view);
        ((TextView) viewInflate.findViewById(R.id.tv_title)).setText(R.string.upgrade_software);
        MyDialog myDialog = new MyDialog(context, R.style.loading_dialog);
        myDialog.setCancelable(false);
        myDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(-2, -2));
        return myDialog;
    }

    public static Dialog createProgressDialog(Context context) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_dial_custom_installing, (ViewGroup) null).findViewById(R.id.dialog_view);
        MyDialog myDialog = new MyDialog(context, R.style.AlertDialogStyle);
        myDialog.setCancelable(true);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.setContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        return myDialog;
    }

    public static Dialog showPromptDialog(Activity activity, String content, DialogInterface.OnClickListener onClickListener) {
        return showPromptDialog(activity, activity.getString(R.string.prompt), content, activity.getString(R.string.ok), activity.getString(R.string.cancel), onClickListener, new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.utils.DialogUtils$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
    }

    public static Dialog showPromptDialog(Activity activity, String title, String content, String positiveText, String negativeText, final DialogInterface.OnClickListener onClickListener, final DialogInterface.OnClickListener onCancelListener) {
        final CommonDialog commonDialog = new CommonDialog(activity);
        commonDialog.setTitle(title).setMessage(content).setConfirm(positiveText).setCancel(negativeText).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.utils.DialogUtils.1
            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onEditTextConfirmClick(String mEditText) {
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onConfirmClick() {
                onClickListener.onClick(commonDialog, 1);
            }

            @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
            public void onCancelClick() {
                onCancelListener.onClick(commonDialog, 0);
            }
        }).show();
        return commonDialog;
    }

    public static AlertDialog setDialogCenter(Activity activity, AlertDialog dialog) {
        Window window = dialog.getWindow();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (defaultDisplay.getWidth() * 0.95d);
        attributes.gravity = 17;
        window.setAttributes(attributes);
        return dialog;
    }

    public static void showPermissionDialog(final Activity activity, String title, String content) {
        ((CustomDialog.Builder) new CustomDialog.Builder(activity).setHeight(0.21f).setWidth(0.7f).setTitleVisible(true).setTitleText(title).setTitleTextColor(R.color.black).setContentText(content).setContentTextColor(R.color.black_light).setLeftButtonText(activity.getString(R.string.cancel)).setLeftButtonTextColor(R.color.gray).setRightButtonText(activity.getString(R.string.ok)).setRightButtonTextColor(R.color.black_light).setTitleTextSize(16).setContentTextSize(14).setButtonTextSize(14).setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<MDAlertDialog>() { // from class: com.yucheng.smarthealthpro.utils.DialogUtils.2
            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickLeftButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
                activity.finish();
            }

            @Override // com.wevey.selector.dialog.DialogInterface.OnLeftAndRightClickListener
            public void clickRightButton(MDAlertDialog dialog, View view) {
                dialog.dismiss();
                PermissionUtil.gotoPermission(activity);
            }
        })).build().show();
    }
}
