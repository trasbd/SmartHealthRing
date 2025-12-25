package com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.bloodpressure.bean.BloodPressureHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class BloodPressureHisListAdapter extends BaseQuickAdapter<BloodPressureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(BloodPressureHisListBean hisSearch, int position);

        void onDelClick(BloodPressureHisListBean hisSearch, int position);

        void onLongClick(BloodPressureHisListBean hisSearch, int position);
    }

    public BloodPressureHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final BloodPressureHisListBean hisBean) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisBean != null) {
            holder.setText(R.id.tv_time, hisBean.getTime()).setText(R.id.tv_value, hisBean.getBloodSBP() + "/" + hisBean.getBloodDBP()).setText(R.id.tv_unit, "mmHg");
            String string = getContext().getString(R.string.value_normal);
            int color = getContext().getColor(R.color.value_normal);
            if (hisBean.getBloodDBP() > 90 || hisBean.getBloodSBP() > 140) {
                string = getContext().getString(R.string.value_high);
                color = getContext().getColor(R.color.value_exceptional_high);
            } else if (hisBean.getBloodDBP() < 60 || hisBean.getBloodSBP() < 90) {
                string = getContext().getString(R.string.value_low);
                color = getContext().getColor(R.color.value_exceptional_low);
            }
            holder.setText(R.id.tv_state, string);
            holder.setTextColor(R.id.tv_state, color);
            if (Constant.isHealthWear() || Constant.isSmartHealth()) {
                if (hisBean.getIsInflated() == 2) {
                    holder.setImageResource(R.id.iv_mode, R.mipmap.blood_item_accurate);
                    holder.setVisible(R.id.iv_mode, true);
                } else if (hisBean.getIsInflated() == -1) {
                    holder.setVisible(R.id.iv_mode, false);
                } else {
                    holder.setImageResource(R.id.iv_mode, R.mipmap.blood_item_normal);
                    holder.setVisible(R.id.iv_mode, true);
                }
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodpressure.adapter.BloodPressureHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BloodPressureHisListAdapter.this.mOnItemClickListener != null) {
                    BloodPressureHisListAdapter.this.mOnItemClickListener.onClick(hisBean, layoutPosition);
                }
            }
        });
    }
}
