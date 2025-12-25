package com.yucheng.smarthealthpro.sport.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.sport.bean.SportRecord2;

/* loaded from: classes5.dex */
public class SportRecord2TitleHolder extends SportRecord2Holder {
    private ImageView ivSubordinate;
    private TextView tvMonth;

    public SportRecord2TitleHolder(View itemView) {
        super(itemView);
        this.tvMonth = (TextView) itemView.findViewById(R.id.tv_month);
        this.ivSubordinate = (ImageView) itemView.findViewById(R.id.iv_subordinate);
    }

    @Override // com.yucheng.smarthealthpro.sport.adapter.SportRecord2Holder
    public void bind(SportRecord2 sport) {
        this.tvMonth.setText(sport.getTitle());
        if (sport.isOpen()) {
            this.ivSubordinate.setImageResource(R.mipmap.list_ic_arrow_s);
        } else {
            this.ivSubordinate.setImageResource(R.mipmap.list_ic_arrow_n);
        }
    }
}
