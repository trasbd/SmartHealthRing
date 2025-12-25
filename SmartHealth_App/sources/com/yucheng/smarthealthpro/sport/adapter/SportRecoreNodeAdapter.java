package com.yucheng.smarthealthpro.sport.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.sport.SportType;
import com.yucheng.smarthealthpro.sport.activity.SportRunningHisMapActivity;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.sport.utils.GoogleUtil;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes5.dex */
public class SportRecoreNodeAdapter extends BaseQuickAdapter<SportHisListBean, BaseViewHolder> {
    private Context mContext;
    private String mDistance;
    private OnItemClickListener mOnItemClickListener;
    public boolean showDel;

    public interface OnItemClickListener {
        void onClick(SportHisListBean hisSearch, int position);

        void onDelClick(SportHisListBean hisSearch, int position);

        void onLongClick(SportHisListBean hisSearch, int position);
    }

    public SportRecoreNodeAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.mOnItemClickListener = null;
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final SportHisListBean hisSearch) {
        holder.getLayoutPosition();
        if (hisSearch != null) {
            String str = (String) SharedPreferencesUtils.get(this.mContext, Constant.SpConstKey.UNIT, "");
            if (str == null || !str.equals(Constant.SpConstValue.INCH)) {
                this.mDistance = String.format("%.2f", Float.valueOf(hisSearch.getDistance() / 1000.0f));
                holder.setText(R.id.tv_unit, "Km");
            } else {
                this.mDistance = String.format("%.2f", Float.valueOf(hisSearch.getDistance() / 1609.344f));
                holder.setText(R.id.tv_unit, "Mile");
            }
            holder.setText(R.id.tv_time, TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(hisSearch.getBeginDate()))).setText(R.id.tv_keep_time, TimeStampUtils.parseSecond(hisSearch.getRunTime()));
            int[] ids = SportType.getIds(hisSearch.getType());
            holder.setText(R.id.tv_motorPattern, getContext().getString(ids[0])).setImageResource(R.id.iv_sport_img, ids[1]);
            if (ids[3] == 1) {
                holder.setText(R.id.tv_distance, this.mDistance).setImageResource(R.id.iv_unit, R.mipmap.step_list_ic_km);
            } else {
                holder.setText(R.id.tv_distance, "" + hisSearch.getCalorie()).setImageResource(R.id.iv_unit, R.mipmap.step_list_ic_kcal).setText(R.id.tv_unit, "Kcal");
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportRecoreNodeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (GoogleUtil.checkGoogleAvailable()) {
                    Intent intent = new Intent(SportRecoreNodeAdapter.this.mContext, (Class<?>) SportRunningHisMapActivity.class);
                    intent.putExtra("hislist", hisSearch);
                    intent.putExtra("map", "googleMap");
                    SportRecoreNodeAdapter.this.mContext.startActivity(intent);
                    return;
                }
                Intent intent2 = new Intent(SportRecoreNodeAdapter.this.mContext, (Class<?>) SportRunningHisMapActivity.class);
                intent2.putExtra("hislist", hisSearch);
                intent2.putExtra("map", "aMap");
                SportRecoreNodeAdapter.this.mContext.startActivity(intent2);
            }
        });
    }
}
