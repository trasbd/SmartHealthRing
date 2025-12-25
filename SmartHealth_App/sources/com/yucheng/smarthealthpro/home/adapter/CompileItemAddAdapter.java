package com.yucheng.smarthealthpro.home.adapter;

import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.bean.HomeFunctionBean;

/* loaded from: classes5.dex */
public class CompileItemAddAdapter extends BaseQuickAdapter<HomeFunctionBean, BaseViewHolder> implements DraggableModule {
    private ClickInterface clickInterface;

    public interface ClickInterface {
        void onHideIvClick(View view, int position);
    }

    public void setOnclick(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public CompileItemAddAdapter(int layoutResId) {
        super(layoutResId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(final BaseViewHolder holder, HomeFunctionBean homeFunctionBean) {
        if (homeFunctionBean != null) {
            holder.setText(R.id.tv_name, homeFunctionBean.name);
            ((ImageView) holder.itemView.findViewById(R.id.iv_function_icon)).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.adapter.CompileItemAddAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (CompileItemAddAdapter.this.clickInterface != null) {
                        CompileItemAddAdapter.this.clickInterface.onHideIvClick(v, holder.getAdapterPosition());
                    }
                }
            });
        }
    }
}
