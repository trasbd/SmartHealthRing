package com.yucheng.smarthealthpro.home.activity.ecg.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgSyncListResponse;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes5.dex */
public class EcgSyncListAdapter extends BaseQuickAdapter<EcgSyncListResponse.DataBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(EcgSyncListResponse.DataBean hisSearch, int position);
    }

    public EcgSyncListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final EcgSyncListResponse.DataBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_time, TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(hisSearch.collectStartTime)));
        }
        holder.getView(R.id.tv_start_button).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgSyncListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgSyncListAdapter.this.mOnItemClickListener != null) {
                    EcgSyncListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
