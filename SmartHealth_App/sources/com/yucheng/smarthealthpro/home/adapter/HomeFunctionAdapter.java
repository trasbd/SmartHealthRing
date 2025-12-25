package com.yucheng.smarthealthpro.home.adapter;

import android.widget.TextView;
import com.amap.api.col.p0003sl.jt;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.timepicker.TimeModel;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.bean.HomeFunctionBean;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.view.chart.PhyUtils;
import com.yucheng.ycbtsdk.YCBTClient;

/* loaded from: classes5.dex */
public class HomeFunctionAdapter extends BaseQuickAdapter<HomeFunctionBean, BaseViewHolder> {
    public String dateStr;
    public boolean isCare;
    public boolean showDel;
    public int tvStatusWidth;

    public HomeFunctionAdapter(int layoutResId) {
        super(layoutResId);
        this.tvStatusWidth = 0;
        this.isCare = false;
        this.dateStr = "";
    }

    public HomeFunctionAdapter(int layoutResId, boolean isCare) {
        super(layoutResId);
        this.tvStatusWidth = 0;
        this.dateStr = "";
        this.isCare = isCare;
    }

    public void setCare(boolean isCare) {
        this.isCare = isCare;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, HomeFunctionBean homeFunctionBean) throws NumberFormatException {
        int i2;
        holder.getLayoutPosition();
        boolean z = true;
        holder.setVisible(R.id.rl_layout1, true);
        holder.setVisible(R.id.ll_layout2, false);
        holder.setVisible(R.id.rl_layout3, false);
        TextView textView = (TextView) holder.getView(R.id.tv_value);
        textView.setTextSize(30.0f);
        String strReplaceAll = homeFunctionBean.getValue().replaceAll(",", ".");
        if (homeFunctionBean != null) {
            if (homeFunctionBean.getFunction().equals("睡眠")) {
                try {
                    i2 = Integer.parseInt(homeFunctionBean.getValue());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    i2 = 0;
                }
                if ("--".equals(homeFunctionBean.getValue())) {
                    holder.setText(R.id.tv_value, homeFunctionBean.getValue()).setText(R.id.tv_unit, jt.f1391g).setText(R.id.tv_value2, homeFunctionBean.getValue()).setText(R.id.tv_unit2, "min");
                } else {
                    int i3 = i2 / 60;
                    holder.setText(R.id.tv_value, String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3 / 60))).setText(R.id.tv_unit, jt.f1391g).setText(R.id.tv_value2, String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i3 % 60))).setText(R.id.tv_unit2, "min");
                }
            } else if (homeFunctionBean.getFunction().equals("理疗")) {
                if ("--".equals(homeFunctionBean.getValue())) {
                    holder.setText(R.id.tv_value, homeFunctionBean.getValue()).setText(R.id.tv_unit, homeFunctionBean.getUnit());
                } else {
                    holder.setText(R.id.tv_value, PhyUtils.parseTime(Integer.parseInt(strReplaceAll))).setText(R.id.tv_unit, homeFunctionBean.getUnit());
                }
            } else if (homeFunctionBean.getFunction().equals("血压")) {
                textView.setTextSize(25.0f);
                holder.setText(R.id.tv_value, strReplaceAll).setText(R.id.tv_unit, homeFunctionBean.getUnit());
            } else {
                holder.setText(R.id.tv_value, strReplaceAll).setText(R.id.tv_unit, homeFunctionBean.getUnit());
            }
            boolean login = Tools.readLogin(getContext());
            boolean z2 = YCBTClient.connectState() == 10;
            BaseViewHolder gone = holder.setText(R.id.tv_name, homeFunctionBean.name).setImageBitmap(R.id.iv_home_function, homeFunctionBean.getImagePath()).setGone(R.id.rl_home_function_ecg, !homeFunctionBean.getFunction().equals("心电")).setGone(R.id.iv_home_function, homeFunctionBean.getFunction().equals("心电")).setGone(R.id.tv_value2, !homeFunctionBean.getFunction().equals("睡眠")).setGone(R.id.tv_unit2, !homeFunctionBean.getFunction().equals("睡眠"));
            int i4 = R.id.iv_data_question;
            if (login && z2 && homeFunctionBean.getFunction().equals("睡眠") && "00".equals(homeFunctionBean.getValue())) {
                z = false;
            }
            gone.setGone(i4, z);
        }
    }
}
