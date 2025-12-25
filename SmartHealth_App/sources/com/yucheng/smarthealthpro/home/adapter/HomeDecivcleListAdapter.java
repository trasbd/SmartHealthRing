package com.yucheng.smarthealthpro.home.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.ycbtsdk.bean.ScanDeviceBean;

/* loaded from: classes5.dex */
public class HomeDecivcleListAdapter extends BaseQuickAdapter<ScanDeviceBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(ScanDeviceBean hisSearch, int position);

        void onDelClick(ScanDeviceBean hisSearch, int position);

        void onLongClick(ScanDeviceBean hisSearch, int position);
    }

    public HomeDecivcleListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final ScanDeviceBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_model_number, hisSearch.getDeviceName() + "").setText(R.id.tv_mac, hisSearch.getDeviceMac()).setText(R.id.tv_dbm, hisSearch.getDeviceRssi() + " dBm");
        }
        holder.getView(R.id.device_list_item).setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.adapter.HomeDecivcleListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (HomeDecivcleListAdapter.this.mOnItemClickListener != null) {
                    HomeDecivcleListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
