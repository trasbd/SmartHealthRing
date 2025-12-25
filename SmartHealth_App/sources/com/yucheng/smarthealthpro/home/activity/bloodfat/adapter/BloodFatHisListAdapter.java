package com.yucheng.smarthealthpro.home.activity.bloodfat.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TransUtils;

/* loaded from: classes5.dex */
public class BloodFatHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public BloodFatHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final TemperatureHisListBean hisSearch) throws NumberFormatException {
        float f2;
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_time, hisSearch.getTime()).setText(R.id.tv_value, hisSearch.getmValue()).setText(R.id.tv_unit, (String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getContext().getString(R.string.blood_sugar_unit_1)));
            try {
                f2 = Float.parseFloat(hisSearch.getmValue());
            } catch (Exception e2) {
                e2.printStackTrace();
                f2 = 0.0f;
            }
            if (getContext().getString(R.string.blood_sugar_unit_2).equals((String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.BLOOD_SUGAR_AND_BLOOD_FAT_UNIT, getContext().getString(R.string.blood_sugar_unit_1)))) {
                f2 /= TransUtils.BLOOD_FAT_TRANS;
            }
            String string = getContext().getString(R.string.value_normal);
            holder.setTextColor(R.id.tv_state, getContext().getColor(R.color.value_normal));
            if (f2 > 5.2f) {
                string = getContext().getString(R.string.value_high);
                holder.setTextColor(R.id.tv_state, getContext().getColor(R.color.value_exceptional_high));
            }
            holder.setText(R.id.tv_state, string);
            holder.setImageResource(R.id.iv_mode, R.mipmap.ic_precise_blood_fat).setGone(R.id.iv_mode, hisSearch.model == 0);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.bloodfat.adapter.BloodFatHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BloodFatHisListAdapter.this.mOnItemClickListener != null) {
                    BloodFatHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
