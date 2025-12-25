package com.yucheng.smarthealthpro.me.adapter;

import android.widget.CompoundButton;
import android.widget.Switch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.bean.HealthFunctionUnitBean;

/* loaded from: classes5.dex */
public class HealthAssistanceFunctionAdapter extends BaseQuickAdapter<HealthFunctionUnitBean, BaseViewHolder> {
    private OnItemCheckedChangeListener mOnItemCheckedChangeListener;

    public interface OnItemCheckedChangeListener {
        void OnCheckedChangeListener(HealthFunctionUnitBean functionUnitBean, CompoundButton buttonView, boolean isChecked);
    }

    public HealthAssistanceFunctionAdapter() {
        super(R.layout.item_health_assistance_function);
        this.mOnItemCheckedChangeListener = null;
    }

    public void setOnItemCheckedChangeListener(OnItemCheckedChangeListener onItemClickListener) {
        this.mOnItemCheckedChangeListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final HealthFunctionUnitBean functionUnitBean) {
        holder.setText(R.id.tv_title, functionUnitBean.getTitle());
        Switch r3 = (Switch) holder.getView(R.id.switch_function);
        r3.setChecked(functionUnitBean.getEnable());
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.me.adapter.HealthAssistanceFunctionAdapter.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                HealthAssistanceFunctionAdapter.this.mOnItemCheckedChangeListener.OnCheckedChangeListener(functionUnitBean, buttonView, isChecked);
            }
        });
    }
}
