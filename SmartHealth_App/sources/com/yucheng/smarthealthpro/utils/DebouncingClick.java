package com.yucheng.smarthealthpro.utils;

import android.view.View;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DebouncingClick.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/DebouncingClick;", "", "<init>", "()V", "DEFAULT_DEBOUNCE_TIME", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", NativeProtocol.WEB_DIALOG_ACTION, "Lcom/yucheng/smarthealthpro/utils/DebouncingClick$ViewConsumer;", "debounceTime", "ViewConsumer", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DebouncingClick {
    private static final long DEFAULT_DEBOUNCE_TIME = 500;
    public static final DebouncingClick INSTANCE = new DebouncingClick();

    /* compiled from: DebouncingClick.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/yucheng/smarthealthpro/utils/DebouncingClick$ViewConsumer;", "", "accept", "", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface ViewConsumer {
        void accept(View view);
    }

    private DebouncingClick() {
    }

    @JvmStatic
    public static final View.OnClickListener listener(ViewConsumer action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return listener(DEFAULT_DEBOUNCE_TIME, action);
    }

    @JvmStatic
    public static final View.OnClickListener listener(final long debounceTime, final ViewConsumer action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.utils.DebouncingClick.listener.1
            private long lastClickTime;

            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.lastClickTime >= debounceTime) {
                    this.lastClickTime = jCurrentTimeMillis;
                    action.accept(v);
                }
            }
        };
    }
}
