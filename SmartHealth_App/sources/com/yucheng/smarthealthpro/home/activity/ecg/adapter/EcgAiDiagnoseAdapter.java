package com.yucheng.smarthealthpro.home.activity.ecg.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgAiDiagnoseItemBean;

/* loaded from: classes5.dex */
public class EcgAiDiagnoseAdapter extends BaseQuickAdapter<EcgAiDiagnoseItemBean, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(EcgAiDiagnoseItemBean hisSearch, int position);
    }

    public EcgAiDiagnoseAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final EcgAiDiagnoseItemBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_ai_diagnosis_type_item, hisSearch.getType()).setText(R.id.tv_ai_value_diagnosis_type_item, hisSearch.getResult());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgAiDiagnoseAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EcgAiDiagnoseAdapter.this.mOnItemClickListener != null) {
                    EcgAiDiagnoseAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
