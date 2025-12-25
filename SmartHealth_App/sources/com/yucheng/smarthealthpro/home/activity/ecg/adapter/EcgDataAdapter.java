package com.yucheng.smarthealthpro.home.activity.ecg.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.ecg.bean.EcgDataBean;
import com.yucheng.smarthealthpro.utils.HRVUtils;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes5.dex */
public class EcgDataAdapter extends BaseQuickAdapter<EcgDataBean, EcgDataViewHolder> {
    public static final int BODY_INDEX = 4;
    public static final int HEAVY_LOAD = 1;
    public static final int HRV_NORM = 2;
    public static final int PRESSURE = 3;
    public static final int RESPIRATORY_RATE = 6;
    public static final int SYMPATHETIC_PARASYMPATHETIC = 5;

    public EcgDataAdapter(int layoutResId) {
        super(layoutResId);
    }

    public EcgDataAdapter(int layoutResId, List<EcgDataBean> data) {
        super(layoutResId, data);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(EcgDataViewHolder holder, EcgDataBean s) {
        String str;
        TextView textView = (TextView) holder.findView(R.id.tvName);
        TextView textView2 = (TextView) holder.findView(R.id.tvData);
        if (textView != null) {
            if (s.getName().length() > 6) {
                textView.setTextSize(12.0f);
            } else {
                textView.setTextSize(18.0f);
            }
        }
        float data = s.getData();
        if (textView2 != null) {
            switch (s.getViewType()) {
                case 1:
                    double d2 = data;
                    if (d2 < 6.0d) {
                        if (d2 >= 4.0d) {
                            textView2.setTextColor(Color.parseColor("#F8DB15"));
                            break;
                        } else {
                            textView2.setTextColor(Color.parseColor("#F3222D"));
                            break;
                        }
                    } else {
                        textView2.setTextColor(Color.parseColor("#15C2C2"));
                        break;
                    }
                case 2:
                    double d3 = data;
                    if (d3 < 6.1d) {
                        if (d3 >= 4.1d) {
                            textView2.setTextColor(Color.parseColor("#F8DB15"));
                            break;
                        } else {
                            textView2.setTextColor(Color.parseColor("#F3222D"));
                            break;
                        }
                    } else {
                        textView2.setTextColor(Color.parseColor("#1890FF"));
                        break;
                    }
                case 3:
                    double d4 = data;
                    if (d4 < 8.1d) {
                        if (d4 < 5.1d) {
                            if (d4 >= 2.6d) {
                                textView2.setTextColor(Color.parseColor("#1890FF"));
                                break;
                            } else {
                                textView2.setTextColor(Color.parseColor("#54C41A"));
                                break;
                            }
                        } else {
                            textView2.setTextColor(Color.parseColor("#F8DB15"));
                            break;
                        }
                    } else {
                        textView2.setTextColor(Color.parseColor("#F3222D"));
                        break;
                    }
                case 4:
                    double d5 = data;
                    if (d5 < 6.1d && d5 >= 4.1d) {
                        textView2.setTextColor(Color.parseColor("#F8DB15"));
                        break;
                    } else {
                        textView2.setTextColor(Color.parseColor("#54C41A"));
                        break;
                    }
                case 5:
                    if (Math.abs(data) < 8.0d) {
                        if (Math.abs(data) >= 4.1d) {
                            textView2.setTextColor(Color.parseColor("#F8DB15"));
                            break;
                        } else {
                            textView2.setTextColor(Color.parseColor("#54C41A"));
                            break;
                        }
                    } else {
                        textView2.setTextColor(Color.parseColor("#F3222D"));
                        break;
                    }
                case 6:
                    if (data < 30.0f) {
                        if (data >= 11.0f) {
                            textView2.setTextColor(Color.parseColor("#54C41A"));
                            break;
                        } else {
                            textView2.setTextColor(Color.parseColor("#1890FF"));
                            break;
                        }
                    } else {
                        textView2.setTextColor(Color.parseColor("#F3222D"));
                        break;
                    }
            }
        }
        if (Objects.equals(s.getName(), getContext().getString(R.string.respiratory_rate))) {
            str = "%.0f";
        } else {
            str = "%.1f";
        }
        holder.setText(R.id.tvName, s.getName()).setText(R.id.tvData, String.format(Locale.getDefault(), str, Float.valueOf(data))).setText(R.id.tvState, HRVUtils.getHRVIndex(getContext(), s.getState()));
    }

    public static class EcgDataViewHolder extends BaseViewHolder {
        public EcgDataViewHolder(View view) {
            super(view);
        }
    }
}
