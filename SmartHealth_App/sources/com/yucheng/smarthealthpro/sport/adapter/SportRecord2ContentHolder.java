package com.yucheng.smarthealthpro.sport.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.sport.SportType;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.sport.bean.SportRecord2;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes5.dex */
public class SportRecord2ContentHolder extends SportRecord2Holder {
    ImageView ivSportImg;
    ImageView ivUnit;
    TextView tvDistance;
    TextView tvKeepTime;
    TextView tvMotorPattern;
    TextView tvTime;
    TextView tvUnit;

    public SportRecord2ContentHolder(View itemView) {
        super(itemView);
        this.tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        this.tvMotorPattern = (TextView) itemView.findViewById(R.id.tv_motorPattern);
        this.ivSportImg = (ImageView) itemView.findViewById(R.id.iv_sport_img);
        this.tvUnit = (TextView) itemView.findViewById(R.id.tv_unit);
        this.tvDistance = (TextView) itemView.findViewById(R.id.tv_distance);
        this.tvKeepTime = (TextView) itemView.findViewById(R.id.tv_keep_time);
        this.ivUnit = (ImageView) itemView.findViewById(R.id.iv_unit);
    }

    @Override // com.yucheng.smarthealthpro.sport.adapter.SportRecord2Holder
    public void bind(SportRecord2 sport) {
        String str;
        this.tvTime.setText(sport.getTitle());
        SportHisListBean sportHisListBean = (SportHisListBean) sport.getContent();
        if (sportHisListBean != null) {
            String str2 = (String) SharedPreferencesUtils.get(this.itemView.getContext(), Constant.SpConstKey.UNIT, "");
            if ((str2 == null || !str2.equals(Constant.SpConstValue.ISO)) && str2 != null && str2.equals(Constant.SpConstValue.INCH)) {
                str = String.format("%.2f", Float.valueOf(sportHisListBean.getDistance() / 1609.344f));
                this.tvUnit.setText("Mile");
            } else {
                str = String.format("%.2f", Float.valueOf(sportHisListBean.getDistance() / 1000.0f));
                this.tvUnit.setText("Km");
            }
            this.tvTime.setText(TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(sportHisListBean.getBeginDate())));
            this.tvKeepTime.setText(TimeStampUtils.parseSecond(sportHisListBean.getRunTime()));
            int[] ids = SportType.getIds(sportHisListBean.getType());
            int i2 = ids[0];
            int i3 = ids[1];
            boolean z = ids[2] == 1;
            this.tvMotorPattern.setText(i2);
            this.ivSportImg.setImageResource(i3);
            if (z) {
                this.tvDistance.setText(str);
                this.ivUnit.setImageResource(R.mipmap.step_list_ic_km);
            } else {
                this.tvDistance.setText("" + sportHisListBean.getCalorie());
                this.tvUnit.setText("Kcal");
                this.ivUnit.setImageResource(R.mipmap.step_list_ic_kcal);
            }
        }
    }
}
