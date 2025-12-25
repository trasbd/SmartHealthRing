package com.yucheng.smarthealthpro.me.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.bean.MeHelpIssueBean;

/* loaded from: classes5.dex */
public class MeHelpIssueAdapter extends BaseQuickAdapter<MeHelpIssueBean.DataBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(MeHelpIssueBean.DataBean hisSearch, int position);

        void onDelClick(MeHelpIssueBean.DataBean hisSearch, int position);

        void onLongClick(MeHelpIssueBean.DataBean hisSearch, int position);
    }

    public MeHelpIssueAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MeHelpIssueBean.DataBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_title, hisSearch.getName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MeHelpIssueAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MeHelpIssueAdapter.this.mOnItemClickListener != null) {
                    MeHelpIssueAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
