package com.yucheng.smarthealthpro.home.adapter;

import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.bean.HomeFunctionBean;

/* loaded from: classes5.dex */
public class CompileItemDragAdapter extends BaseQuickAdapter<HomeFunctionBean, BaseViewHolder> implements DraggableModule {
    private ClickInterface clickInterface;

    public interface ClickInterface {
        void onDownIvClick(View view, int position);

        void onShowIvClick(View view, int position);

        void onUpIvClick(View view, int position);
    }

    public void setOnclick(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public CompileItemDragAdapter(int layoutResId) {
        super(layoutResId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, HomeFunctionBean homeFunctionBean) {
        if (homeFunctionBean != null) {
            holder.setText(R.id.tv_name, homeFunctionBean.name);
            ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.iv_hide_icon);
            ImageView imageView2 = (ImageView) holder.itemView.findViewById(R.id.iv_adjust_up);
            ImageView imageView3 = (ImageView) holder.itemView.findViewById(R.id.iv_adjust_down);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.adapter.CompileItemDragAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (CompileItemDragAdapter.this.clickInterface != null) {
                        CompileItemDragAdapter.this.clickInterface.onShowIvClick(v, holder.getAdapterPosition());
                    }
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.adapter.CompileItemDragAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (CompileItemDragAdapter.this.clickInterface != null) {
                        CompileItemDragAdapter.this.clickInterface.onUpIvClick(v, holder.getAdapterPosition());
                    }
                }
            });
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.adapter.CompileItemDragAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (CompileItemDragAdapter.this.clickInterface != null) {
                        CompileItemDragAdapter.this.clickInterface.onDownIvClick(v, holder.getAdapterPosition());
                    }
                }
            });
        }
    }
}
