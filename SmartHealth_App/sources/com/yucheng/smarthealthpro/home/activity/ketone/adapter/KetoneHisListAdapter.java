package com.yucheng.smarthealthpro.home.activity.ketone.adapter;

import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TransUtils;

/* loaded from: classes5.dex */
public class KetoneHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    public static float HIGH = 3.0f;
    public static float LOW = 0.5f;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public KetoneHisListAdapter(int layoutResId) {
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
            holder.setText(R.id.tv_time, hisSearch.getTime()).setText(R.id.tv_value, hisSearch.getmValue()).setText(R.id.tv_unit, (String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.URIC_ACID_UNIT, getContext().getString(R.string.uric_acid_unit_1)));
            try {
                f2 = Float.parseFloat(hisSearch.getmValue());
            } catch (Exception e2) {
                e2.printStackTrace();
                f2 = 0.0f;
            }
            if (getContext().getString(R.string.uric_acid_unit_2).equals((String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.URIC_ACID_UNIT, getContext().getString(R.string.uric_acid_unit_1)))) {
                f2 /= TransUtils.KETONE_TRANS;
            }
            String string = getContext().getString(R.string.value_normal);
            holder.setTextColor(R.id.tv_state, getContext().getColor(R.color.value_normal));
            if (f2 < LOW) {
                string = getContext().getString(R.string.value_low);
                holder.setTextColor(R.id.tv_state, getContext().getColor(R.color.value_exceptional_low));
            } else if (f2 > HIGH) {
                string = getContext().getString(R.string.value_high);
                holder.setTextColor(R.id.tv_state, getContext().getColor(R.color.value_exceptional_high));
            }
            holder.setText(R.id.tv_state, string);
            Log.e("model=======", "" + hisSearch.model);
            holder.setImageResource(R.id.iv_mode, R.mipmap.ic_precise_ketone).setGone(R.id.iv_mode, hisSearch.model == 0);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ketone.adapter.KetoneHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (KetoneHisListAdapter.this.mOnItemClickListener != null) {
                    KetoneHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
