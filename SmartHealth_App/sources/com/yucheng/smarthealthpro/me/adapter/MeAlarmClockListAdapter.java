package com.yucheng.smarthealthpro.me.adapter;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.bean.MeAlarmClock;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MeAlarmClockListAdapter extends BaseQuickAdapter<MeAlarmClock, BaseViewHolder> {
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onChecked(MeAlarmClock hisSearch, int position);

        void onClick(MeAlarmClock hisSearch, int position);
    }

    public MeAlarmClockListAdapter(int layoutResId) {
        super(layoutResId);
        this.mOnItemClickListener = null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MeAlarmClock hisSearch) {
        final int layoutPosition = holder.getLayoutPosition();
        if (hisSearch != null) {
            int alarmHour = hisSearch.getAlarmHour();
            int alarmMin = hisSearch.getAlarmMin();
            holder.setText(R.id.tv_time, (alarmHour > 9 ? Integer.valueOf(alarmHour) : "0" + alarmHour) + ":" + (alarmMin > 9 ? Integer.valueOf(alarmMin) : "0" + alarmMin)).setText(R.id.tv_label, hisSearch.getLabel());
            Switch r1 = (Switch) holder.itemView.findViewById(R.id.switch_dnd_mode);
            if (hisSearch.getIsSwitch().equals("1")) {
                r1.setChecked(true);
            } else {
                r1.setChecked(false);
            }
            r1.setOnCheckedChangeListener(new AnonymousClass1(hisSearch, layoutPosition, r1));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MeAlarmClockListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MeAlarmClockListAdapter.this.mOnItemClickListener != null) {
                    MeAlarmClockListAdapter.this.mOnItemClickListener.onClick(hisSearch, layoutPosition);
                }
            }
        });
    }

    /* renamed from: com.yucheng.smarthealthpro.me.adapter.MeAlarmClockListAdapter$1, reason: invalid class name */
    class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ MeAlarmClock val$hisSearch;
        final /* synthetic */ Switch val$mSwitch;
        final /* synthetic */ int val$position;

        AnonymousClass1(final MeAlarmClock val$hisSearch, final int val$position, final Switch val$mSwitch) {
            this.val$hisSearch = val$hisSearch;
            this.val$position = val$position;
            this.val$mSwitch = val$mSwitch;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) throws NumberFormatException {
            int i2 = 0;
            if (isChecked) {
                if (this.val$hisSearch.getAlternativeDate() != null) {
                    char[] charArray = this.val$hisSearch.getAlternativeDate().toCharArray();
                    String string = new StringBuffer(Integer.parseInt(charArray[0] + "") + "" + Integer.parseInt(charArray[1] + "") + "" + Integer.parseInt(charArray[2] + "") + "" + Integer.parseInt(charArray[3] + "") + "" + Integer.parseInt(charArray[4] + "") + "" + Integer.parseInt(charArray[5] + "") + "" + Integer.parseInt(charArray[6] + "") + "1").reverse().toString();
                    i2 = Integer.parseInt(Tools.BinaryToHex(string), 16);
                    Log.i("AAAAA", "开开开开开===reverse===" + string + isChecked + "=====" + this.val$position + "===mWeeks===" + i2 + "======" + this.val$hisSearch.getIsSwitch());
                }
            } else if (this.val$hisSearch.getAlternativeDate() != null) {
                char[] charArray2 = this.val$hisSearch.getAlternativeDate().toCharArray();
                String string2 = new StringBuffer(Integer.parseInt(charArray2[0] + "") + "" + Integer.parseInt(charArray2[1] + "") + "" + Integer.parseInt(charArray2[2] + "") + "" + Integer.parseInt(charArray2[3] + "") + "" + Integer.parseInt(charArray2[4] + "") + "" + Integer.parseInt(charArray2[5] + "") + "" + Integer.parseInt(charArray2[6] + "") + "0").reverse().toString();
                i2 = Integer.parseInt(Tools.BinaryToHex(string2), 16);
                Log.i("AAAAA", "关关关关关===reverse===" + string2 + isChecked + "=====" + this.val$position + "===mWeeks===" + i2 + "======" + this.val$hisSearch.getIsSwitch());
            }
            YCBTClient.settingModfiyAlarm(this.val$hisSearch.getAlarmHour(), this.val$hisSearch.getAlarmMin(), 1, this.val$hisSearch.getAlarmHour(), this.val$hisSearch.getAlarmMin(), i2, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.adapter.MeAlarmClockListAdapter.1.1
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float ratio, HashMap resultMap) {
                    Log.i("AAAAA", "==isChecked==" + isChecked + "==code==" + code + "==position==" + AnonymousClass1.this.val$position + "===resultMap===" + resultMap.toString());
                    if (code == 3) {
                        if (MeAlarmClockListAdapter.this.mOnItemClickListener != null) {
                            MeAlarmClockListAdapter.this.mOnItemClickListener.onChecked(AnonymousClass1.this.val$hisSearch, AnonymousClass1.this.val$position);
                        }
                        new Thread() { // from class: com.yucheng.smarthealthpro.me.adapter.MeAlarmClockListAdapter.1.1.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                if (isChecked) {
                                    AnonymousClass1.this.val$hisSearch.setIsSwitch("1");
                                } else {
                                    AnonymousClass1.this.val$hisSearch.setIsSwitch("0");
                                }
                                AnonymousClass1.this.val$mSwitch.setChecked(!isChecked);
                                super.run();
                            }
                        };
                    }
                }
            });
        }
    }
}
