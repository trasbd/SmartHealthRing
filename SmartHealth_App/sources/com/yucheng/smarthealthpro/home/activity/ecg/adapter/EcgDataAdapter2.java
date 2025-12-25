package com.yucheng.smarthealthpro.home.activity.ecg.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgDataBean;
import com.yucheng.smarthealthpro.utils.HRVUtils;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes5.dex */
public class EcgDataAdapter2 extends BaseQuickAdapter<EcgDataBean, EcgDataViewHolder> {
    public static final int BODY_INDEX = 4;
    public static final int HEAVY_LOAD = 1;
    public static final int HRV_NORM = 2;
    public static final int PRESSURE = 3;
    public static final int RESPIRATORY_RATE = 6;
    public static final int SYMPATHETIC_PARASYMPATHETIC = 5;

    public EcgDataAdapter2(int layoutResId) {
        super(layoutResId);
    }

    public EcgDataAdapter2(int layoutResId, List<EcgDataBean> data) {
        super(layoutResId, data);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(EcgDataViewHolder holder, EcgDataBean s) {
        String str;
        TextView textView = (TextView) holder.findView(R.id.tvData);
        SeekBar seekBar = (SeekBar) holder.findView(R.id.sb_point);
        CardView cardView = (CardView) holder.findView(R.id.cv_1);
        CardView cardView2 = (CardView) holder.findView(R.id.cv_2);
        CardView cardView3 = (CardView) holder.findView(R.id.cv_3);
        CardView cardView4 = (CardView) holder.findView(R.id.cv_4);
        CardView cardView5 = (CardView) holder.findView(R.id.cv_5);
        cardView5.setVisibility(8);
        float data = s.getData();
        int color = getContext().getColor(R.color.ecg_diagnose_item_red);
        int color2 = getContext().getColor(R.color.ecg_diagnose_item_yellow);
        int color3 = getContext().getColor(R.color.ecg_diagnose_item_blue);
        int color4 = getContext().getColor(R.color.ecg_diagnose_item_green);
        int color5 = getContext().getColor(R.color.ecg_diagnose_item_blue_light);
        seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.yucheng.smarthealthpro.home.activity.ecg.adapter.EcgDataAdapter2.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        seekBar.setClickable(false);
        seekBar.setOnClickListener(null);
        if (textView != null) {
            switch (s.getViewType()) {
                case 1:
                    if (data >= 6.0f) {
                        textView.setTextColor(color3);
                    } else if (data >= 4.0f) {
                        textView.setTextColor(color2);
                    } else {
                        textView.setTextColor(color);
                    }
                    seekBar.setProgress((int) (data * 10.0f));
                    cardView.setCardBackgroundColor(color);
                    cardView2.setCardBackgroundColor(color2);
                    cardView3.setCardBackgroundColor(color3);
                    cardView.setLayoutParams(getParam(40));
                    cardView2.setLayoutParams(getParam(20));
                    cardView3.setLayoutParams(getParam(40));
                    cardView4.setVisibility(8);
                    break;
                case 2:
                    if (data >= 6.1f) {
                        textView.setTextColor(color3);
                    } else if (data >= 4.1f) {
                        textView.setTextColor(color2);
                    } else {
                        textView.setTextColor(color);
                    }
                    seekBar.setProgress((int) (data * 10.0f));
                    cardView.setCardBackgroundColor(color);
                    cardView2.setCardBackgroundColor(color2);
                    cardView3.setCardBackgroundColor(color3);
                    cardView.setLayoutParams(getParam(4));
                    cardView2.setLayoutParams(getParam(2));
                    cardView3.setLayoutParams(getParam(4));
                    cardView4.setVisibility(8);
                    break;
                case 3:
                    if (data >= 8.1f) {
                        textView.setTextColor(color);
                    } else if (data >= 5.1f) {
                        textView.setTextColor(color2);
                    } else if (data >= 2.6f) {
                        textView.setTextColor(color3);
                    } else {
                        textView.setTextColor(color4);
                    }
                    seekBar.setProgress((int) (data * 10.0f));
                    cardView.setCardBackgroundColor(color4);
                    cardView2.setCardBackgroundColor(color3);
                    cardView3.setCardBackgroundColor(color2);
                    cardView4.setCardBackgroundColor(color);
                    cardView.setLayoutParams(getParam(25));
                    cardView2.setLayoutParams(getParam(26));
                    cardView3.setLayoutParams(getParam(29));
                    cardView4.setLayoutParams(getParam(20));
                    cardView4.setVisibility(0);
                    break;
                case 4:
                    double d2 = data;
                    if (d2 >= 6.1d) {
                        textView.setTextColor(color4);
                    } else if (d2 >= 4.1d) {
                        textView.setTextColor(color2);
                    } else {
                        textView.setTextColor(color);
                    }
                    seekBar.setProgress((int) (data * 10.0f));
                    cardView.setCardBackgroundColor(color);
                    cardView2.setCardBackgroundColor(color2);
                    cardView3.setCardBackgroundColor(color4);
                    cardView.setLayoutParams(getParam(40));
                    cardView2.setLayoutParams(getParam(20));
                    cardView3.setLayoutParams(getParam(40));
                    cardView4.setVisibility(8);
                    break;
                case 5:
                    if (Math.abs(data) >= 8.0f) {
                        textView.setTextColor(color);
                    } else if (Math.abs(data) >= 4.1f) {
                        textView.setTextColor(color2);
                    } else {
                        textView.setTextColor(color5);
                    }
                    seekBar.setProgress((int) (((data + 10.0f) / 20.0f) * 100.0f));
                    cardView.setCardBackgroundColor(color);
                    cardView2.setCardBackgroundColor(color2);
                    cardView3.setCardBackgroundColor(color5);
                    cardView4.setCardBackgroundColor(color2);
                    cardView5.setCardBackgroundColor(color);
                    cardView.setLayoutParams(getParam(10));
                    cardView2.setLayoutParams(getParam(20));
                    cardView3.setLayoutParams(getParam(40));
                    cardView4.setLayoutParams(getParam(20));
                    cardView5.setLayoutParams(getParam(10));
                    cardView4.setVisibility(0);
                    cardView5.setVisibility(0);
                    break;
                case 6:
                    if (data >= 30.0f) {
                        textView.setTextColor(color);
                    } else if (data >= 11.0f) {
                        textView.setTextColor(color4);
                    } else {
                        textView.setTextColor(color3);
                    }
                    seekBar.setProgress((int) (((data - 8.0f) / 27) * 100.0f));
                    cardView.setCardBackgroundColor(color3);
                    cardView2.setCardBackgroundColor(color4);
                    cardView3.setCardBackgroundColor(color);
                    cardView.setLayoutParams(getParam(74));
                    cardView2.setLayoutParams(getParam(TypedValues.TransitionType.TYPE_AUTO_TRANSITION));
                    cardView3.setLayoutParams(getParam(Opcodes.INVOKEINTERFACE));
                    cardView4.setVisibility(8);
                    break;
            }
        }
        if (Objects.equals(s.getName(), getContext().getString(R.string.respiratory_rate))) {
            str = "%.0f";
        } else {
            str = "%.1f";
        }
        holder.setText(R.id.tvName, s.getName()).setText(R.id.tvData, String.format(Locale.getDefault(), str, Float.valueOf(data))).setText(R.id.tvState, HRVUtils.getHRVIndex(getContext(), s.getState()));
    }

    public LinearLayoutCompat.LayoutParams getParam(int weight) {
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(0, (int) getContext().getResources().getDimension(R.dimen.dp_12));
        layoutParams.weight = weight;
        layoutParams.rightMargin = (int) getContext().getResources().getDimension(R.dimen.dp_3);
        return layoutParams;
    }

    public static class EcgDataViewHolder extends BaseViewHolder {
        public EcgDataViewHolder(View view) {
            super(view);
        }
    }
}
