package com.yucheng.smarthealthpro.home.adapter;

import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.bean.HomeHealthyBeans;

/* loaded from: classes5.dex */
public class HomeHealthyAdapter extends BaseQuickAdapter<HomeHealthyBeans.DataBean.ItemsBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(HomeHealthyBeans.DataBean.ItemsBean hisSearch, int position);

        void onDelClick(HomeHealthyBeans.DataBean.ItemsBean hisSearch, int position);

        void onLongClick(HomeHealthyBeans.DataBean.ItemsBean hisSearch, int position);
    }

    public HomeHealthyAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final HomeHealthyBeans.DataBean.ItemsBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_title, hisSearch.title).setText(R.id.tv_body, hisSearch.summary).setText(R.id.tv_time, "" + hisSearch.date);
            Glide.with(getContext()).load(hisSearch.imageUrl).into((ImageView) holder.getView(R.id.iv_image));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.adapter.HomeHealthyAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (HomeHealthyAdapter.this.mOnItemClickListener != null) {
                    HomeHealthyAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
