package com.yucheng.smarthealthpro.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.LayoutSleepQualityWrapBinding;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepStageBean;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.view.SleepQualityView;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* compiled from: SleepQualityWrapView.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\u000bH\u0002J\u0014\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/yucheng/smarthealthpro/view/SleepQualityWrapView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "binding", "Lcom/yucheng/smarthealthpro/databinding/LayoutSleepQualityWrapBinding;", "onFinishInflate", "", "initView", "setData", "data", "", "Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepStageBean;", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SleepQualityWrapView extends ConstraintLayout {
    private LayoutSleepQualityWrapBinding binding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepQualityWrapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.binding = LayoutSleepQualityWrapBinding.inflate(LayoutInflater.from(getContext()), this, true);
        initView();
    }

    private final void initView() {
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding = this.binding;
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding2 = null;
        if (layoutSleepQualityWrapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding = null;
        }
        layoutSleepQualityWrapBinding.sleepQualityView.setDebug(false);
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding3 = this.binding;
        if (layoutSleepQualityWrapBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            layoutSleepQualityWrapBinding2 = layoutSleepQualityWrapBinding3;
        }
        layoutSleepQualityWrapBinding2.sleepQualityView.setListener(new SleepQualityView.OnSleepQualityListener() { // from class: com.yucheng.smarthealthpro.view.SleepQualityWrapView$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.view.SleepQualityView.OnSleepQualityListener
            public final void onSleepQuality(int i2, int i3, String str, long j2) {
                SleepQualityWrapView.initView$lambda$0(this.f$0, i2, i3, str, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(SleepQualityWrapView sleepQualityWrapView, int i2, int i3, String intervalTimeStr, long j2) {
        String string;
        Intrinsics.checkNotNullParameter(intervalTimeStr, "intervalTimeStr");
        if (i3 == 1) {
            string = sleepQualityWrapView.getContext().getString(R.string.deep_sleep);
        } else if (i3 == 2) {
            string = sleepQualityWrapView.getContext().getString(R.string.light_sleep);
        } else if (i3 == 3) {
            string = sleepQualityWrapView.getContext().getString(R.string.sleep_rem_time);
        } else if (i3 == 4) {
            string = sleepQualityWrapView.getContext().getString(R.string.wakening);
        } else if (i3 == 5) {
            string = sleepQualityWrapView.getContext().getString(R.string.nap);
        } else {
            string = "";
        }
        Intrinsics.checkNotNull(string);
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding = sleepQualityWrapView.binding;
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding2 = null;
        if (layoutSleepQualityWrapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding = null;
        }
        layoutSleepQualityWrapBinding.tvSleepDetail.setText(intervalTimeStr + StringUtils.SPACE + string);
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding3 = sleepQualityWrapView.binding;
        if (layoutSleepQualityWrapBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding3 = null;
        }
        layoutSleepQualityWrapBinding3.moveSleepCard.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding4 = sleepQualityWrapView.binding;
        if (layoutSleepQualityWrapBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding4 = null;
        }
        ViewGroup.LayoutParams layoutParams = layoutSleepQualityWrapBinding4.moveSleepCard.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding5 = sleepQualityWrapView.binding;
        if (layoutSleepQualityWrapBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding5 = null;
        }
        float measuredWidth = layoutSleepQualityWrapBinding5.moveSleepCard.getMeasuredWidth() / 2.0f;
        float f2 = i2;
        float measuredWidth2 = f2 - measuredWidth;
        if (measuredWidth2 < 0.0f) {
            measuredWidth2 = 0.0f;
        }
        int i4 = sleepQualityWrapView.getResources().getDisplayMetrics().widthPixels;
        if (f2 + measuredWidth > i4) {
            LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding6 = sleepQualityWrapView.binding;
            if (layoutSleepQualityWrapBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                layoutSleepQualityWrapBinding6 = null;
            }
            measuredWidth2 = i4 - layoutSleepQualityWrapBinding6.moveSleepCard.getMeasuredWidth();
        }
        layoutParams2.leftMargin = (int) measuredWidth2;
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding7 = sleepQualityWrapView.binding;
        if (layoutSleepQualityWrapBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            layoutSleepQualityWrapBinding2 = layoutSleepQualityWrapBinding7;
        }
        layoutSleepQualityWrapBinding2.moveSleepCard.setLayoutParams(layoutParams2);
    }

    public final void setData(List<SleepStageBean> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding = this.binding;
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding2 = null;
        if (layoutSleepQualityWrapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding = null;
        }
        layoutSleepQualityWrapBinding.sleepQualityView.setSleepAllBean(data);
        if (data.size() >= 2) {
            LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding3 = this.binding;
            if (layoutSleepQualityWrapBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                layoutSleepQualityWrapBinding3 = null;
            }
            layoutSleepQualityWrapBinding3.tvSleepStart.setText(TimeStampUtils.formatHourMinute(Long.valueOf(((SleepStageBean) CollectionsKt.first((List) data)).getTime())));
            LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding4 = this.binding;
            if (layoutSleepQualityWrapBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                layoutSleepQualityWrapBinding4 = null;
            }
            layoutSleepQualityWrapBinding4.tvSleepEnd.setText(TimeStampUtils.formatHourMinute(Long.valueOf(((SleepStageBean) CollectionsKt.last((List) data)).getTime())));
            LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding5 = this.binding;
            if (layoutSleepQualityWrapBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                layoutSleepQualityWrapBinding2 = layoutSleepQualityWrapBinding5;
            }
            layoutSleepQualityWrapBinding2.moveSleepCard.setVisibility(0);
            return;
        }
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding6 = this.binding;
        if (layoutSleepQualityWrapBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding6 = null;
        }
        layoutSleepQualityWrapBinding6.tvSleepStart.setText("");
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding7 = this.binding;
        if (layoutSleepQualityWrapBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            layoutSleepQualityWrapBinding7 = null;
        }
        layoutSleepQualityWrapBinding7.tvSleepEnd.setText("");
        LayoutSleepQualityWrapBinding layoutSleepQualityWrapBinding8 = this.binding;
        if (layoutSleepQualityWrapBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            layoutSleepQualityWrapBinding2 = layoutSleepQualityWrapBinding8;
        }
        layoutSleepQualityWrapBinding2.moveSleepCard.setVisibility(8);
    }
}
