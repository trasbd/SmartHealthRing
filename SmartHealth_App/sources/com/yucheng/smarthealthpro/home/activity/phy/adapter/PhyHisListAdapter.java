package com.yucheng.smarthealthpro.home.activity.phy.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.databinding.ItemPhyHisListBinding;
import com.yucheng.smarthealthpro.home.activity.phy.bean.PhyHisListBean;
import com.yucheng.smarthealthpro.utils.AppDateMgr;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes5.dex */
public class PhyHisListAdapter extends BaseQuickAdapter<PhyHisListBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(PhyHisListBean hisSearch, int position);

        void onDelClick(PhyHisListBean hisSearch, int position);

        void onLongClick(PhyHisListBean hisSearch, int position);
    }

    public PhyHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private void setDayTotalText(TextView tv, int totalLen) {
        String str;
        String str2;
        if (totalLen <= 0) {
            str = "0";
            str2 = "00";
        } else {
            int i2 = totalLen / 60;
            str = "" + (i2 / 60);
            str2 = "" + i2;
            String str3 = "" + (totalLen % 60);
        }
        tv.setText(HtmlCompat.fromHtml(String.format("<big>%s</big><small> h </small><big>%s</big><small> min</small>", str, str2), 0));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final PhyHisListBean bean) {
        final int layoutPosition = holder.getLayoutPosition();
        ItemPhyHisListBinding itemPhyHisListBindingBind = ItemPhyHisListBinding.bind(holder.itemView);
        itemPhyHisListBindingBind.tvDate.setText(new SimpleDateFormat(AppDateMgr.DF_YYYY_MM_DD, Locale.ENGLISH).format(Long.valueOf(bean.getDateTime())));
        setDayTotalText(itemPhyHisListBindingBind.tvItemGear1, (int) bean.getLevel1Duration());
        setDayTotalText(itemPhyHisListBindingBind.tvItemGear2, (int) bean.getLevel2Duration());
        setDayTotalText(itemPhyHisListBindingBind.tvItemGear3, (int) bean.getLevel3Duration());
        setDayTotalText(itemPhyHisListBindingBind.tvItemGear4, (int) bean.getLevel4Duration());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.phy.adapter.PhyHisListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$convert$0(bean, layoutPosition, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$convert$0(PhyHisListBean phyHisListBean, int i2, View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onClick(phyHisListBean, i2);
        }
    }
}
