package com.yucheng.smarthealthpro.care.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.care.bean.FriendListBean;
import com.yucheng.smarthealthpro.care.view.ItemTouchStatus;

/* loaded from: classes4.dex */
public class CareNewFriendListAdapter extends BaseQuickAdapter<FriendListBean.DataBean, BaseViewHolder> implements ItemTouchStatus {
    private TextView mDeleteView;
    private OnItemClickListener mOnItemClickListener;
    private TextView mTextView;
    private RequestOptions requestOptions;

    public interface OnItemClickListener {
        void onClick(FriendListBean.DataBean hisSearch, int position);

        void onDeleteClick(FriendListBean.DataBean hisSearch, int position);

        void onPassClick(FriendListBean.DataBean hisSearch, int position);
    }

    @Override // com.yucheng.smarthealthpro.care.view.ItemTouchStatus
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override // com.yucheng.smarthealthpro.care.view.ItemTouchStatus
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {
    }

    public CareNewFriendListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
        this.requestOptions = new RequestOptions().error(R.mipmap.icon_head).apply(RequestOptions.circleCropTransform()).placeholder(R.mipmap.icon_head);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final FriendListBean.DataBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_name, hisSearch.friendName);
        }
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.iv_head);
        ImageButton imageButton = (ImageButton) holder.itemView.findViewById(R.id.delete);
        TextView textView = (TextView) holder.itemView.findViewById(R.id.tv_pass);
        Glide.with(getContext()).applyDefaultRequestOptions(this.requestOptions).load(hisSearch.headImg).into(imageView);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.care.adapter.CareNewFriendListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CareNewFriendListAdapter.this.mOnItemClickListener != null) {
                    CareNewFriendListAdapter.this.mOnItemClickListener.onPassClick(hisSearch, layoutPosition);
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.care.adapter.CareNewFriendListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CareNewFriendListAdapter.this.mOnItemClickListener != null) {
                    CareNewFriendListAdapter.this.mOnItemClickListener.onDeleteClick(hisSearch, layoutPosition);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.care.adapter.CareNewFriendListAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CareNewFriendListAdapter.this.mOnItemClickListener != null) {
                    CareNewFriendListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
