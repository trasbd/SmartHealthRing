package com.yucheng.smarthealthpro.home.activity.pressure;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.temperature.bean.TemperatureHisListBean;

/* loaded from: classes5.dex */
public class PressureHisListAdapter extends BaseQuickAdapter<TemperatureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(TemperatureHisListBean hisSearch, int position);

        void onDelClick(TemperatureHisListBean hisSearch, int position);

        void onLongClick(TemperatureHisListBean hisSearch, int position);
    }

    public PressureHisListAdapter(int layoutResId) {
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
            holder.setText(R.id.tv_time, hisSearch.getTime()).setText(R.id.tv_value, hisSearch.getmValue()).setText(R.id.tv_unit, "");
            View view = holder.getView(R.id.v_dot);
            try {
                i2 = Integer.parseInt(hisSearch.getmValue());
            } catch (Exception e2) {
                e2.printStackTrace();
                i2 = 70;
            }
            String string = getContext().getString(R.string.value_normal);
            int color = getContext().getColor(R.color.value_normal);
            Drawable drawable = getContext().getDrawable(R.drawable.pressure_circle1);
            if (i2 <= 25) {
                string = getContext().getString(R.string.light);
                color = getContext().getColor(R.color.pressure_level1);
                drawable = getContext().getDrawable(R.drawable.pressure_circle1);
            } else if (i2 <= 50) {
                string = getContext().getString(R.string.value_normal);
                color = getContext().getColor(R.color.pressure_level2);
                drawable = getContext().getDrawable(R.drawable.pressure_circle2);
            } else if (i2 <= 80) {
                string = getContext().getString(R.string.medium);
                color = getContext().getColor(R.color.pressure_level3);
                drawable = getContext().getDrawable(R.drawable.pressure_circle3);
            } else if (i2 <= 100) {
                string = getContext().getString(R.string.value_high);
                color = getContext().getColor(R.color.pressure_level4);
                drawable = getContext().getDrawable(R.drawable.pressure_circle4);
            }
            holder.setText(R.id.tv_state, string);
            holder.setTextColor(R.id.tv_state, color);
            view.setBackground(drawable);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.pressure.PressureHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PressureHisListAdapter.this.mOnItemClickListener != null) {
                    PressureHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
