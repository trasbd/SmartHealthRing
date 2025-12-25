package com.yucheng.smarthealthpro.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.bean.HomeRankingBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.view.CircleImageView;

/* loaded from: classes5.dex */
public class HomeRankingAdapter extends BaseQuickAdapter<HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean, BaseViewHolder> {
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void liked(HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch, int position);

        void onClick(HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch, int position);

        void unLiked(HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch, int position);
    }

    public HomeRankingAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.mOnItemClickListener = null;
        this.context = context;
        setAnimationEnable(false);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, final HomeRankingBean.DataBean.OtherRankInfoBean.ItemsBean hisSearch) {
        int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_serial_number, (layoutPosition + 1) + "").setText(R.id.tv_name, hisSearch.getNickName()).setText(R.id.tv_step_number, hisSearch.getRankingList() + "").setText(R.id.tv_like_number, hisSearch.getLikedCount() + "");
            Glide.with(holder.itemView).load(hisSearch.getHeadImg()).placeholder(R.mipmap.icon_head).error(R.mipmap.icon_head).into((CircleImageView) holder.itemView.findViewById(R.id.iv_head_portrait));
            ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.like_button);
            LinearLayout linearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_end_like_number);
            if (hisSearch.getIsLike() == 0) {
                imageView.setImageResource(R.mipmap.ranking_icon_high);
            } else {
                imageView.setImageResource(R.mipmap.ranking_icon_n);
            }
            if (hisSearch.getUserId().equals((String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.DEV_ID, "1"))) {
                imageView.setEnabled(false);
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.adapter.HomeRankingAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (HomeRankingAdapter.this.mOnItemClickListener != null) {
                        HomeRankingAdapter.this.mOnItemClickListener.onClick(hisSearch, holder.getAdapterPosition());
                    }
                }
            });
        }
    }
}
