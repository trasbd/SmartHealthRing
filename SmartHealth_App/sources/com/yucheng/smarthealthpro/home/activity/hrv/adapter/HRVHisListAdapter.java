package com.yucheng.smarthealthpro.home.activity.hrv.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;

/* loaded from: classes5.dex */
public class HRVHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public HRVHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final TemperatureHisListBean hisSearch) throws NumberFormatException {
        int i2;
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_time, hisSearch.getTime()).setText(R.id.tv_value, hisSearch.getmValue()).setText(R.id.tv_unit, "ms");
            try {
                i2 = Integer.parseInt(hisSearch.getmValue());
            } catch (Exception e2) {
                e2.printStackTrace();
                i2 = 70;
            }
            String string = getContext().getString(R.string.value_normal);
            int color = getContext().getColor(R.color.value_normal);
            if (i2 < 50) {
                string = getContext().getString(R.string.value_low);
                color = getContext().getColor(R.color.value_exceptional_low);
            } else if (i2 > 100) {
                string = getContext().getString(R.string.value_high);
                color = getContext().getColor(R.color.value_exceptional_high);
            }
            holder.setText(R.id.tv_state, string);
            holder.setTextColor(R.id.tv_state, color);
            holder.setVisible(R.id.tv_state, true);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.hrv.adapter.HRVHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (HRVHisListAdapter.this.mOnItemClickListener != null) {
                    HRVHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
