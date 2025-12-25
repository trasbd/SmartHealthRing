package com.yucheng.smarthealthpro.home.activity.sleep.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.customchart.utils.SleepUtil;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepHisListBean;
import com.yucheng.smarthealthpro.utils.FormatUtil;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes5.dex */
public class SleepHisListAdapter extends BaseQuickAdapter<SleepHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(SleepHisListBean hisSearch, int position);

        void onDelClick(SleepHisListBean hisSearch, int position);

        void onLongClick(SleepHisListBean hisSearch, int position);
    }

    public SleepHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final SleepHisListBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            if (hisSearch.isMonthWeekDay()) {
                holder.setText(R.id.tv_data, TimeStampUtils.dateForStringDate(TimeStampUtils.longStampForDate(hisSearch.getStartTime() + 14400))).setText(R.id.tv_sleep_total_time, FormatUtil.getBigDecimal(hisSearch.getTotalTime() / 3600.0f).setScale(1, 4).doubleValue() + " h").setText(R.id.tv_light_sleep_time, FormatUtil.getBigDecimal(hisSearch.getLightSleepTotal() / 3600.0f).setScale(1, 4).doubleValue() + " h").setText(R.id.tv_deep_sleep_time, FormatUtil.getBigDecimal(hisSearch.getDeepSleepTotal() / 3600.0f).setScale(1, 4).doubleValue() + " h").setText(R.id.tv_rem_time, FormatUtil.getBigDecimal(hisSearch.remTimes / 3600.0f).setScale(1, 4).doubleValue() + " h");
                int state = SleepUtil.getState(hisSearch.getTotalTime(), hisSearch.getDeepSleepTotal());
                if (state == -1) {
                    holder.setText(R.id.tv_state, getContext().getString(R.string.sleep_quality_none));
                } else if (state == 5) {
                    holder.setText(R.id.tv_state, getContext().getString(R.string.sleep_quality_good)).setTextColor(R.id.tv_state, getContext().getColor(R.color.value_exceptional_low));
                } else if (state >= 3 && state < 5) {
                    holder.setText(R.id.tv_state, getContext().getString(R.string.sleep_quality_ok)).setTextColor(R.id.tv_state, getContext().getColor(R.color.value_normal));
                } else {
                    holder.setText(R.id.tv_state, getContext().getString(R.string.sleep_quality_poor)).setTextColor(R.id.tv_state, getContext().getColor(R.color.value_exceptional_high));
                }
            } else {
                holder.setText(R.id.tv_data, TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(hisSearch.getStartTime())) + "-" + TimeStampUtils.dateForStringToDate(TimeStampUtils.longStampForDate(hisSearch.getEndTime()))).setText(R.id.tv_sleep_total_time, FormatUtil.getBigDecimal(hisSearch.getTotalTime() / 3600.0f).setScale(1, 4).doubleValue() + " h").setText(R.id.tv_light_sleep_time, FormatUtil.getBigDecimal(hisSearch.getLightSleepTotal() / 3600.0f).setScale(1, 4).doubleValue() + " h").setText(R.id.tv_deep_sleep_time, FormatUtil.getBigDecimal(hisSearch.getDeepSleepTotal() / 3600.0f).setScale(1, 4).doubleValue() + " h").setText(R.id.tv_rem_time, FormatUtil.getBigDecimal(hisSearch.remTimes / 3600.0f).setScale(1, 4).doubleValue() + " h").setText(R.id.tv_state, "");
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.sleep.adapter.SleepHisListAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (SleepHisListAdapter.this.mOnItemClickListener != null) {
                        SleepHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                    }
                }
            });
        }
    }
}
