package com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;

/* loaded from: classes5.dex */
public class BloodOxygenHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public BloodOxygenHisListAdapter(int layoutResId) {
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
            holder.setText(R.id.tv_time, hisSearch.getTime()).setText(R.id.tv_value, hisSearch.getmValue()).setText(R.id.tv_unit, "%");
            try {
                i2 = Integer.parseInt(hisSearch.getmValue());
            } catch (Exception e2) {
                e2.printStackTrace();
                i2 = 0;
            }
            String string = getContext().getString(R.string.value_normal);
            holder.setTextColor(R.id.tv_state, getContext().getColor(R.color.value_normal));
            if (i2 < 70) {
                string = getContext().getString(R.string.value_low);
                holder.setTextColor(R.id.tv_state, getContext().getColor(R.color.value_exceptional_low));
            }
            holder.setText(R.id.tv_state, string);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodoxygen.adapter.BloodOxygenHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BloodOxygenHisListAdapter.this.mOnItemClickListener != null) {
                    BloodOxygenHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
