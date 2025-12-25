package com.yucheng.smarthealthpro.care.zxing.decoding;

import android.app.Activity;
import android.content.DialogInterface;

/* loaded from: classes4.dex */
public final class FinishListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {
    private final Activity activityToFinish;

    public FinishListener(Activity activityToFinish) {
        this.activityToFinish = activityToFinish;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        run();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.activityToFinish.finish();
    }
}
