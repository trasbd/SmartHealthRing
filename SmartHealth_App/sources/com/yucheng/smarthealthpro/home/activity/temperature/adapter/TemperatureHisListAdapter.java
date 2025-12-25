package com.yucheng.smarthealthpro.home.activity.temperature.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.FormatUtil;

/* loaded from: classes5.dex */
public class TemperatureHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private int mTemp;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public TemperatureHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final TemperatureHisListBean hisSearch) throws NumberFormatException {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_time, hisSearch.getTime());
            String string = getContext().getString(R.string.value_normal);
            int color = getContext().getColor(R.color.value_normal);
            String strReplaceAll = hisSearch.getmValue().replaceAll(",", ".");
            float f2 = Float.parseFloat(strReplaceAll);
            float f3 = Float.parseFloat(strReplaceAll);
            String str = (String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.TEMP_UNIT, "");
            if (str != null && str.equals(Constant.SpConstValue.TEMP_INCH)) {
                holder.setText(R.id.tv_unit, Constant.SpConstValue.TEMP_INCH);
                f2 = (f2 - 32.0f) / 1.8f;
            } else {
                holder.setText(R.id.tv_unit, Constant.SpConstValue.TEMP_ISO);
            }
            if (f2 < 36.0f) {
                string = getContext().getString(R.string.value_low);
                color = getContext().getColor(R.color.value_exceptional_low);
            } else if (f2 > 37.3d) {
                string = getContext().getString(R.string.value_high);
                color = getContext().getColor(R.color.value_exceptional_high);
            }
            holder.setText(R.id.tv_value, FormatUtil.keep1NoZero(f3)).setText(R.id.tv_state, string).setTextColor(R.id.tv_state, color);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.temperature.adapter.TemperatureHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (TemperatureHisListAdapter.this.mOnItemClickListener != null) {
                    TemperatureHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
