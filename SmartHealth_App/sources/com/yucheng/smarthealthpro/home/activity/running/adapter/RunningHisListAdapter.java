package com.yucheng.smarthealthpro.home.activity.running.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.activity.running.bean.RunningHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class RunningHisListAdapter extends BaseQuickAdapter<RunningHisListBean, BaseViewHolder> {
    private String mDistance;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(RunningHisListBean hisSearch, int position);

        void onDelClick(RunningHisListBean hisSearch, int position);

        void onLongClick(RunningHisListBean hisSearch, int position);
    }

    public RunningHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final RunningHisListBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        String str = (String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.UNIT, "");
        if ((str == null || !str.equals(Constant.SpConstValue.ISO)) && str != null && str.equals(Constant.SpConstValue.INCH)) {
            this.mDistance = String.format("%.3f", Float.valueOf(hisSearch.getSportDistance() / 1609.344f));
            holder.setText(R.id.tv_unit, "Mile");
        } else {
            this.mDistance = String.format("%.3f", Float.valueOf(hisSearch.getSportDistance() / 1000.0f));
            holder.setText(R.id.tv_unit, "Km");
        }
        if (hisSearch != null) {
            holder.setText(R.id.tv_data, hisSearch.getSportStartTime()).setText(R.id.tv_step_number, hisSearch.getSportStep() + "").setText(R.id.tv_heat, hisSearch.getSportCalorie() + "").setText(R.id.tv_distance, this.mDistance);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.running.adapter.RunningHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (RunningHisListAdapter.this.mOnItemClickListener != null) {
                    RunningHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }

    public void addData(List<RunningHisListBean> list) {
        Collections.sort(list);
        super.addData((Collection) list);
    }

    public void replaceData(List<RunningHisListBean> list) {
        Collections.sort(list);
        super.replaceData((Collection) list);
    }
}
