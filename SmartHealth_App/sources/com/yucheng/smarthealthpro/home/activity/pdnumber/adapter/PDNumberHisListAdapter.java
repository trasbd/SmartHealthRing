package com.yucheng.smarthealthpro.home.activity.pdnumber.adapter;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.bean.MyMonBean;
import com.yucheng.smarthealthpro.utils.YearToDayListUtils;

/* loaded from: classes5.dex */
public class PDNumberHisListAdapter extends BaseQuickAdapter<MyMonBean.Data.Values, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private String unit;

    public interface OnItemClickListener {
        void onClick(MyMonBean.Data.Values hisSearch, int position);

        void onDelClick(MyMonBean.Data.Values hisSearch, int position);

        void onLongClick(MyMonBean.Data.Values hisSearch, int position);
    }

    public PDNumberHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MyMonBean.Data.Values hisSearch) throws NumberFormatException {
        float f2;
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null && hisSearch.time != null) {
            if (hisSearch.time.contains(ExifInterface.GPS_DIRECTION_TRUE)) {
                int[] timeFromDateString = YearToDayListUtils.getTimeFromDateString(hisSearch.time);
                if (timeFromDateString != null && timeFromDateString.length == 3) {
                    int i2 = R.id.tv_time;
                    StringBuilder sb = new StringBuilder();
                    int i3 = timeFromDateString[0];
                    StringBuilder sbAppend = sb.append(i3 < 10 ? "0" + timeFromDateString[0] : Integer.valueOf(i3)).append(":");
                    int i4 = timeFromDateString[1];
                    holder.setText(i2, sbAppend.append(i4 + 1 < 10 ? "0" + timeFromDateString[1] : Integer.valueOf(i4)).toString());
                } else {
                    holder.setText(R.id.tv_time, hisSearch.time);
                }
            } else if (!hisSearch.time.contains("-")) {
                holder.setText(R.id.tv_time, YearToDayListUtils.getStringDateFromMonth(Integer.parseInt(hisSearch.time)));
            } else {
                holder.setText(R.id.tv_time, hisSearch.time);
            }
            holder.setText(R.id.tv_value, hisSearch.displayvalue + "").setText(R.id.tv_unit, this.unit);
            try {
                f2 = Float.parseFloat(hisSearch.displayvalue);
            } catch (Exception e2) {
                e2.printStackTrace();
                f2 = 0.0f;
            }
            if (f2 > 11.0f) {
                holder.setText(R.id.tv_state, getContext().getString(R.string.value_high));
            } else if (f2 > 4.0f) {
                holder.setText(R.id.tv_state, getContext().getString(R.string.value_normal));
            } else {
                holder.setText(R.id.tv_state, getContext().getString(R.string.value_low));
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.pdnumber.adapter.PDNumberHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PDNumberHisListAdapter.this.mOnItemClickListener != null) {
                    PDNumberHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
