package com.yucheng.smarthealthpro.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.bean.MeListBean;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.BatteryUtil;
import com.yucheng.smarthealthpro.utils.Constant;

/* loaded from: classes5.dex */
public class MeListAdapter extends BaseQuickAdapter<MeListBean, BaseViewHolder> {
    private Context context;
    private AppImageMgr mAppImageMgr;
    private OnItemClickListener mOnItemClickListener;
    private int type;

    public interface OnItemClickListener {
        void onClick(MeListBean hisSearch, int position);
    }

    public MeListAdapter(int layoutResId, int type, Context context) {
        super(layoutResId);
        this.mOnItemClickListener = null;
        this.type = type;
        this.context = context;
        this.mAppImageMgr = new AppImageMgr(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MeListBean hisSearch) throws NumberFormatException {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            String string = getContext().getString(R.string.me_personal_details_skin_color);
            String string2 = getContext().getString(R.string.me_personal_details_qr_cord_title);
            int i2 = this.type;
            if (i2 == 1) {
                holder.setText(R.id.tv_title, hisSearch.getTitle()).setImageBitmap(R.id.iv_left_icon_one, hisSearch.getLeftImagePath()).setImageBitmap(R.id.iv_right_icon_one, hisSearch.getRightImagePath()).setImageBitmap(R.id.iv_left_icon_two, hisSearch.getLeftImagePath()).setVisible(R.id.tv_versions, true).setText(R.id.tv_versions, hisSearch.getRightText()).setTextColor(R.id.tv_versions, Color.parseColor("#666666")).setGone(R.id.iv_right_icon_two, !hisSearch.getTitle().equals(string));
                View view = holder.getView(R.id.iv_left_icon_one);
                ImageView imageView = (ImageView) holder.getView(R.id.iv_left_icon_two);
                if (Constant.isRing() && hisSearch.getTitle().equals(this.context.getString(R.string.me_my_device_title))) {
                    view.setVisibility(8);
                    imageView.setVisibility(0);
                    imageView.setImageBitmap(hisSearch.getLeftImagePath());
                } else {
                    view.setVisibility(0);
                    imageView.setVisibility(8);
                }
                if (layoutPosition == 0) {
                    if (hisSearch.getRightText().equals("-")) {
                        holder.setGone(R.id.tv_versions, true).setGone(R.id.iv_right_icon_Three, true).setVisible(R.id.iv_right_icon_two, true).setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.icon_me_watch_link_off));
                    } else {
                        int i3 = Integer.parseInt(hisSearch.getRightText().replaceAll("%", ""));
                        if (i3 != -1) {
                            holder.setVisible(R.id.iv_right_icon_Three, true).setTextColor(R.id.tv_versions, Color.parseColor("#666666"));
                            holder.setImageBitmap(R.id.iv_right_icon_Three, this.mAppImageMgr.getBitmap(BatteryUtil.getBatteryId(i3)));
                            holder.setVisible(R.id.tv_versions, true);
                        } else {
                            holder.setVisible(R.id.iv_right_icon_Three, false);
                            holder.setVisible(R.id.tv_versions, false);
                        }
                    }
                } else {
                    holder.setVisible(R.id.iv_right_icon_Three, false);
                    if (layoutPosition == 1) {
                        holder.setVisible(R.id.tv_versions, true);
                    }
                }
            } else if (i2 == 2) {
                holder.setText(R.id.tv_title, hisSearch.getTitle()).setGone(R.id.iv_left_icon_one, true).setImageBitmap(R.id.iv_right_icon_one, hisSearch.getRightImagePath()).setVisible(R.id.tv_versions, true).setText(R.id.tv_versions, hisSearch.getRightText()).setTextColor(R.id.tv_versions, Color.parseColor("#666666")).setGone(R.id.iv_right_icon_Three, true).setGone(R.id.iv_right_icon_two, (hisSearch.getTitle().equals(string) || hisSearch.getTitle().equals(string2)) ? false : true).setGone(R.id.tv_versions, hisSearch.getTitle().equals(string));
                if (layoutPosition == 0) {
                    holder.setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.icon_me_qr));
                }
                String rightText = hisSearch.getRightText();
                rightText.hashCode();
                switch (rightText) {
                    case "0":
                        holder.setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.perfect_skin_color_white_false_icon));
                        break;
                    case "1":
                        holder.setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.perfect_skin_color_white_between_yellow_false_icon));
                        break;
                    case "2":
                        holder.setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.perfect_skin_color_yellow_false_icon));
                        break;
                    case "3":
                        holder.setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.perfect_skin_color_brown_false_icon));
                        break;
                    case "4":
                        holder.setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.perfect_skin_color_brownness_false_icon));
                        break;
                    case "5":
                        holder.setImageBitmap(R.id.iv_right_icon_two, this.mAppImageMgr.getBitmap(R.mipmap.perfect_skin_color_black_false_icon));
                        break;
                }
                if (getContext().getString(R.string.me_my_device_more_settings_dnd_mode_title).equals(hisSearch.getTitle())) {
                    layoutPosition = 0;
                } else if (getContext().getString(R.string.me_my_device_more_settings_clock_title).equals(hisSearch.getTitle())) {
                    layoutPosition = 1;
                } else if (getContext().getString(R.string.me_my_device_more_settings_physiological_cycle_title).equals(hisSearch.getTitle())) {
                    layoutPosition = 2;
                } else if (getContext().getString(R.string.me_my_device_more_settings_photograph).equals(hisSearch.getTitle())) {
                    layoutPosition = 3;
                } else if (getContext().getString(R.string.me_my_device_more_settings_units_setup_title).equals(hisSearch.getTitle())) {
                    layoutPosition = 4;
                } else if (getContext().getString(R.string.me_my_device_more_settings_display_setup_title).equals(hisSearch.getTitle())) {
                    layoutPosition = 5;
                } else if (getContext().getString(R.string.me_my_device_more_settings_information_push_title).equals(hisSearch.getTitle())) {
                    layoutPosition = 6;
                } else if (getContext().getString(R.string.permission_tv_contacts_title).equals(hisSearch.getTitle())) {
                    layoutPosition = 7;
                } else if (getContext().getString(R.string.me_my_device_more_settings_ott_services).equals(hisSearch.getTitle())) {
                    layoutPosition = 8;
                } else if (getContext().getString(R.string.visiting_card).equals(hisSearch.getTitle())) {
                    layoutPosition = 16;
                }
            } else if (i2 == 3) {
                holder.setText(R.id.tv_title, hisSearch.getTitle()).setImageBitmap(R.id.iv_left_icon_one, hisSearch.getLeftImagePath()).setImageBitmap(R.id.iv_right_icon_one, hisSearch.getRightImagePath()).setGone(R.id.tv_versions, true).setGone(R.id.iv_right_icon_two, true).setGone(R.id.iv_right_icon_Three, true);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MeListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MeListAdapter.this.mOnItemClickListener != null) {
                    MeListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }
}
