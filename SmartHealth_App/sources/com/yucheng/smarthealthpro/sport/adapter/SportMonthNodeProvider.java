package com.yucheng.smarthealthpro.sport.adapter;

import android.view.View;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.sport.bean.SportMonthNode;

/* loaded from: classes5.dex */
public class SportMonthNodeProvider extends BaseNodeProvider {
    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int getItemViewType() {
        return 0;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int getLayoutId() {
        return R.layout.item_sport_month_node;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void convert(final BaseViewHolder helper, BaseNode data) {
        SportMonthNode sportMonthNode = (SportMonthNode) data;
        helper.setText(R.id.tv_month, sportMonthNode.getTitle()).setImageResource(R.id.iv_subordinate, sportMonthNode.getIsExpanded() ? R.mipmap.list_ic_arrow_s : R.mipmap.list_ic_arrow_n);
        helper.itemView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeProvider.1
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                helper.itemView.setScrollX(0);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.chad.library.adapter.base.BaseNodeAdapter] */
    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void onClick(BaseViewHolder helper, View view, BaseNode data, int position) {
        getAdapter2().expandOrCollapse(position);
    }
}
