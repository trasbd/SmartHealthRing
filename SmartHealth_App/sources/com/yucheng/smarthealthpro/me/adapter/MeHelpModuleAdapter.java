package com.yucheng.smarthealthpro.me.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.bean.MeHelpModuleBean;

/* loaded from: classes5.dex */
public class MeHelpModuleAdapter extends BaseQuickAdapter<MeHelpModuleBean.DataBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(MeHelpModuleBean.DataBean hisSearch, int position);

        void onDelClick(MeHelpModuleBean.DataBean hisSearch, int position);

        void onLongClick(MeHelpModuleBean.DataBean hisSearch, int position);
    }

    public MeHelpModuleAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MeHelpModuleBean.DataBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_module, hisSearch.name);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MeHelpModuleAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MeHelpModuleAdapter.this.mOnItemClickListener != null) {
                    MeHelpModuleAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
