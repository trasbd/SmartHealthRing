package com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;

/* loaded from: classes5.dex */
public class RespiratoryRateHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public RespiratoryRateHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final TemperatureHisListBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_time, hisSearch.getTime()).setText(R.id.tv_unit, "rpm");
            String string = getContext().getString(R.string.value_normal);
            int i2 = (int) Float.parseFloat(hisSearch.getmValue().replaceAll(",", "."));
            int color = getContext().getColor(R.color.value_normal);
            if (i2 < 10) {
                string = getContext().getString(R.string.value_low);
                color = getContext().getColor(R.color.value_exceptional_low);
            } else if (i2 > 30) {
                string = getContext().getString(R.string.value_high);
                color = getContext().getColor(R.color.value_exceptional_high);
            }
            holder.setText(R.id.tv_state, string);
            holder.setTextColor(R.id.tv_state, color);
            holder.setText(R.id.tv_value, i2 + "");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.respiratoryrate.adapter.RespiratoryRateHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (RespiratoryRateHisListAdapter.this.mOnItemClickListener != null) {
                    RespiratoryRateHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
