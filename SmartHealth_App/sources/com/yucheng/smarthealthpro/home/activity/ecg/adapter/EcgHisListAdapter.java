package com.yucheng.smarthealthpro.home.activity.ecg.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgMeasureHisListBean;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes5.dex */
public class EcgHisListAdapter extends BaseQuickAdapter<EcgMeasureHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(EcgMeasureHisListBean hisSearch, int position);

        void onDiagnoseClick(EcgMeasureHisListBean hisSearch, int position);

        void onEcgClick(EcgMeasureHisListBean hisSearch, int position);

        void onPlayBackClick(EcgMeasureHisListBean hisSearch, int position);
    }

    public EcgHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final EcgMeasureHisListBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            long time = hisSearch.getTime();
            if (((int) (time % 1000)) > 500) {
                time += 1000;
            }
            holder.setText(R.id.tv_time, TimeStampUtils.dateForString(TimeStampUtils.longStampForDate(time), getContext().getString(R.string.history_date_format)));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgHisListAdapter.this.mOnItemClickListener != null) {
                    EcgHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
        ((LinearLayout) holder.itemView.findViewById(R.id.ll_ecg)).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgHisListAdapter.this.mOnItemClickListener != null) {
                    EcgHisListAdapter.this.mOnItemClickListener.onEcgClick(hisSearch, layoutPosition);
                }
            }
        });
        ((LinearLayout) holder.itemView.findViewById(R.id.ll_diagnose)).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgHisListAdapter.this.mOnItemClickListener != null) {
                    EcgHisListAdapter.this.mOnItemClickListener.onDiagnoseClick(hisSearch, layoutPosition);
                }
            }
        });
        ((ImageView) holder.itemView.findViewById(R.id.iv_playback)).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgHisListAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgHisListAdapter.this.mOnItemClickListener != null) {
                    EcgHisListAdapter.this.mOnItemClickListener.onPlayBackClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
