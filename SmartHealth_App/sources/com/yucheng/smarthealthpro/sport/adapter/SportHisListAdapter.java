package com.yucheng.smarthealthpro.sport.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.care.view.ItemTouchStatus;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.sport.SportType;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;

/* loaded from: classes5.dex */
public class SportHisListAdapter extends BaseQuickAdapter<SportHisListBean, BaseViewHolder> implements ItemTouchStatus {
    private TextView mDeleteView;
    private String mDistance;
    private OnItemClickListener mOnItemClickListener;
    private TextView mTextView;

    public interface OnItemClickListener {
        void onClick(SportHisListBean hisSearch, int position);

        void onDeleteClick(SportHisListBean hisSearch, int position);
    }

    @Override // com.yucheng.smarthealthpro.care.view.ItemTouchStatus
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override // com.yucheng.smarthealthpro.care.view.ItemTouchStatus
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {
    }

    public SportHisListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final SportHisListBean hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        String str = (String) SharedPreferencesUtils.get(getContext(), Constant.SpConstKey.UNIT, "");
        if (str == null || !str.equals(Constant.SpConstValue.INCH)) {
            this.mDistance = String.format("%.2f", Float.valueOf(hisSearch.getDistance() / 1000.0f));
            holder.setText(R.id.tv_unit, "Km");
        } else {
            this.mDistance = String.format("%.2f", Float.valueOf(hisSearch.getDistance() / 1609.344f));
            holder.setText(R.id.tv_unit, "Mile");
        }
        if (hisSearch != null) {
            int[] ids = SportType.getIds(hisSearch.getType());
            int i2 = ids[0];
            holder.setImageResource(R.id.iv_sport_img, ids[1]).setText(R.id.tv_time, TimeStampUtils.dateForStringYearToDate(TimeStampUtils.longStampForDate(hisSearch.getBeginDate()))).setText(R.id.tv_distance, this.mDistance).setText(R.id.tv_keep_time, TimeStampUtils.parseSecond(hisSearch.getRunTime()));
            if (Constant.isHealthband()) {
                ((ImageView) holder.getView(R.id.iv_sport_img)).setColorFilter(MyApplication.getInstance().getColor(R.color.colorAccent));
            }
            holder.setText(R.id.tv_motorPattern, i2);
            if (ids[3] == 1) {
                holder.setText(R.id.tv_distance, this.mDistance).setImageResource(R.id.iv_unit, R.mipmap.step_list_ic_km);
            } else {
                holder.setText(R.id.tv_distance, "" + hisSearch.getCalorie()).setImageResource(R.id.iv_unit, R.mipmap.step_list_ic_kcal).setText(R.id.tv_unit, "Kcal");
            }
        }
        ImageButton imageButton = (ImageButton) holder.itemView.findViewById(R.id.delete);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportHisListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SportHisListAdapter.this.mOnItemClickListener != null) {
                    SportHisListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportHisListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SportHisListAdapter.this.mOnItemClickListener != null) {
                    SportHisListAdapter.this.mOnItemClickListener.onDeleteClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
