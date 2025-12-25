package com.yucheng.smarthealthpro.home.activity.bloodsugar.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class BloodSugarHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public BloodSugarHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final TemperatureHisListBean hisSearch) throws NumberFormatException {
        double d2;
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            try {
                holder.setText(R.id.tv_time, hisSearch.getTime()).setText(R.id.tv_value, hisSearch.getmValue()).setText(R.id.tv_unit, hisSearch.unit).setText(R.id.tv_state, hisSearch.getState());
                try {
                    d2 = Double.parseDouble(hisSearch.getmValue());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    d2 = 0.0d;
                }
                if (getContext().getString(R.string.blood_sugar_unit_2).equals((String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getContext().getString(R.string.blood_sugar_unit_1)))) {
                    d2 /= 18.0d;
                }
                getContext().getString(R.string.value_normal);
                int color = getContext().getColor(R.color.value_normal);
                if (d2 < 2.8d) {
                    getContext().getString(R.string.value_low);
                    color = getContext().getColor(R.color.value_exceptional_low);
                }
                if (d2 > 7.8d) {
                    getContext().getString(R.string.value_high);
                    color = getContext().getColor(R.color.value_exceptional_high);
                }
                holder.setTextColor(R.id.tv_state, color);
                holder.setImageResource(R.id.iv_mode, R.mipmap.ic_precise_blood_sugar).setGone(R.id.iv_mode, hisSearch.model == 0);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodsugar.adapter.BloodSugarHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BloodSugarHisListAdapter.this.mOnItemClickListener != null) {
                    BloodSugarHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
