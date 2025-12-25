package com.yucheng.smarthealthpro.sport.adapter;

import android.util.Log;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.yucheng.smarthealthpro.sport.adapter.SportRecoreNodeProvider;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.sport.bean.SportMonthNode;
import java.util.List;

/* loaded from: classes5.dex */
public class SportRecordAdapter extends BaseNodeAdapter {
    private OnItemClickListener mOnItemClickListener = null;
    private SportRecoreNodeProvider mSportRecoreNodeProvider = new SportRecoreNodeProvider();

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public SportRecordAdapter() {
        addFullSpanNodeProvider(new SportMonthNodeProvider());
        addNodeProvider(this.mSportRecoreNodeProvider);
        this.mSportRecoreNodeProvider.setOnItemClickListener(new SportRecoreNodeProvider.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportRecordAdapter.1
            @Override // com.yucheng.smarthealthpro.sport.adapter.SportRecoreNodeProvider.OnItemClickListener
            public void onClick(int position) {
                if (SportRecordAdapter.this.mOnItemClickListener != null) {
                    SportRecordAdapter.this.mOnItemClickListener.onClick(position);
                    Log.i("AAAAAAAAAAA", "===2===" + position);
                }
            }
        });
    }

    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    protected int getItemType(List<? extends BaseNode> data, int position) {
        BaseNode baseNode = data.get(position);
        if (baseNode instanceof SportMonthNode) {
            return 0;
        }
        return baseNode instanceof SportHisListBean ? 1 : -1;
    }
}
