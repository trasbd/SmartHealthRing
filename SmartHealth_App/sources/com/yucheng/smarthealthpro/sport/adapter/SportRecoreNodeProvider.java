package com.yucheng.smarthealthpro.sport.adapter;

import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.sport.SportType;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes5.dex */
public class SportRecoreNodeProvider extends BaseNodeProvider {
    private String mDistance;
    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int getItemViewType() {
        return 1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int getLayoutId() {
        return R.layout.item_sport_his_list;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void convert(BaseViewHolder holder, BaseNode data) {
        if (data == null) {
            return;
        }
        SportHisListBean sportHisListBean = (SportHisListBean) data;
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.ISO)) && str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mDistance = String.format("%.3f", Float.valueOf(sportHisListBean.getDistance() / 1609.344f));
            holder.setText(R.id.tv_unit, "Mile");
        } else {
            this.mDistance = String.format("%.3f", Float.valueOf(sportHisListBean.getDistance() / 1000.0f));
            holder.setText(R.id.tv_unit, "Km");
        }
        holder.setText(R.id.tv_time, TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(sportHisListBean.getBeginDate()))).setText(R.id.tv_distance, this.mDistance).setText(R.id.tv_keep_time, sportHisListBean.getRunTime() + "");
        int[] ids = SportType.getIds(sportHisListBean.getType());
        holder.setText(R.id.tv_motorPattern, getContext().getString(ids[0])).setBackgroundResource(R.id.iv_sport_img, ids[1]);
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void onClick(BaseViewHolder helper, View view, BaseNode data, int position) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onClick(position);
            Log.i("AAAAAAAAAAA", "===1===" + position);
        }
    }
}
