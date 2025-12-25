package com.yucheng.smarthealthpro.me.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.bean.MeHelpFeedBackBean;

/* loaded from: classes5.dex */
public class MeHelpFeedBackImageAdapter extends BaseQuickAdapter<MeHelpFeedBackBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(MeHelpFeedBackBean hisSearch, int position);

        void onDelClick(MeHelpFeedBackBean hisSearch, int position);

        void onLongClick(MeHelpFeedBackBean hisSearch, int position);
    }

    public MeHelpFeedBackImageAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MeHelpFeedBackBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setImageBitmap(R.id.iv_image, hisSearch.getImagePath()).setGone(R.id.iv_del, hisSearch.getIndex() == 0);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MeHelpFeedBackImageAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MeHelpFeedBackImageAdapter.this.mOnItemClickListener != null) {
                    MeHelpFeedBackImageAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
        holder.itemView.findViewById(R.id.iv_del).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MeHelpFeedBackImageAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MeHelpFeedBackImageAdapter.this.mOnItemClickListener != null) {
                    MeHelpFeedBackImageAdapter.this.mOnItemClickListener.onDelClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
