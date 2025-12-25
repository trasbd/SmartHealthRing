package com.yucheng.smarthealthpro.me.dfu.scanner;

import android.app.Activity;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes5.dex */
public class DfuService extends DfuBaseService {
    @Override // no.nordicsemi.android.dfu.DfuBaseService
    protected boolean isDebug() {
        return true;
    }

    @Override // no.nordicsemi.android.dfu.DfuBaseService
    protected Class<? extends Activity> getNotificationTarget() {
        return NotificationActivity.class;
    }
}
