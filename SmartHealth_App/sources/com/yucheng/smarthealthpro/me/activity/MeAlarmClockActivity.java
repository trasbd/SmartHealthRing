package com.yucheng.smarthealthpro.me.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAlarmClockBinding;
import com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors;
import com.yucheng.smarthealthpro.me.adapter.MeAlarmClockListAdapter;
import com.yucheng.smarthealthpro.me.bean.MeAlarmClock;
import com.yucheng.smarthealthpro.utils.DebouncingClick;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.smarthealthpro.utils.WeekUtil;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class MeAlarmClockActivity extends BaseVbActivity<ActivityAlarmClockBinding> {
    private ArrayList<String> firstHourList = new ArrayList<>();
    private ArrayList<String> firstMinuteList = new ArrayList<>();
    ImageView ivAddAlarmClock;
    private List<MeAlarmClock> mMeAlarmClock;
    private MeAlarmClockListAdapter mMeAlarmClockListAdapter;
    RecyclerView mRecyclerView;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivityAlarmClockBinding) this.mBinding).recycleView;
        ImageView imageView = ((ActivityAlarmClockBinding) this.mBinding).ivAddAlarmClock;
        this.ivAddAlarmClock = imageView;
        imageView.setOnClickListener(DebouncingClick.listener(new DebouncingClick.ViewConsumer() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.utils.DebouncingClick.ViewConsumer
            public final void accept(View view) {
                this.f$0.onViewClicked(view);
            }
        }));
        changeTitle(getString(R.string.me_my_device_more_settings_clock_title));
        showBack();
    }

    private void initData() {
        for (int i2 = 0; i2 < 24; i2++) {
            this.firstHourList.add(i2 + "");
        }
        for (int i3 = 0; i3 < 60; i3++) {
            this.firstMinuteList.add(i3 + "");
        }
        this.mMeAlarmClock = new ArrayList();
        getAllAlarm();
        setRecycleView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllAlarm() {
        YCBTClient.settingGetAllAlarm(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity.1
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float ratio, HashMap resultMap) {
                if (code == 0) {
                    ArrayList arrayList = (ArrayList) resultMap.get("data");
                    if (MeAlarmClockActivity.this.mMeAlarmClock != null) {
                        MeAlarmClockActivity.this.mMeAlarmClock.clear();
                    }
                    if (arrayList.size() != 0) {
                        for (int i2 = 0; i2 < arrayList.size(); i2++) {
                            HashMap map = (HashMap) arrayList.get(i2);
                            int iIntValue = ((Integer) map.get("alarmHour")).intValue();
                            int iIntValue2 = ((Integer) map.get("alarmMin")).intValue();
                            ((Integer) map.get("alarmDelayTime")).intValue();
                            ((Integer) map.get("alarmType")).intValue();
                            String strClockRepeatToValueArray = MeAlarmClockActivity.this.clockRepeatToValueArray(((Integer) map.get("alarmRepeat")).intValue());
                            char[] charArray = strClockRepeatToValueArray.toCharArray();
                            Log.i("AAAAAAAA", "闹钟数量" + arrayList.size() + "----闹钟----" + iIntValue + ":" + iIntValue2);
                            MeAlarmClockActivity.this.mMeAlarmClock.add(new MeAlarmClock(iIntValue, iIntValue2, WeekUtil.getLable(charArray, MeAlarmClockActivity.this.context), strClockRepeatToValueArray, charArray[charArray.length - 1] + ""));
                        }
                    }
                    MeAlarmClockActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MeAlarmClockActivity.this.mMeAlarmClockListAdapter.replaceData(MeAlarmClockActivity.this.mMeAlarmClock);
                            MeAlarmClockActivity.this.mMeAlarmClockListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        MeAlarmClockListAdapter meAlarmClockListAdapter = new MeAlarmClockListAdapter(R.layout.item_alarm_clock);
        this.mMeAlarmClockListAdapter = meAlarmClockListAdapter;
        meAlarmClockListAdapter.addData((Collection) this.mMeAlarmClock);
        this.mRecyclerView.setAdapter(this.mMeAlarmClockListAdapter);
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mMeAlarmClockListAdapter.setOnItemClickListener(new AnonymousClass2());
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity$2, reason: invalid class name */
    class AnonymousClass2 implements MeAlarmClockListAdapter.OnItemClickListener {
        AnonymousClass2() {
        }

        @Override // com.yucheng.smarthealthpro.me.adapter.MeAlarmClockListAdapter.OnItemClickListener
        public void onClick(MeAlarmClock hisSearch, int position) {
            CustomAlarmClockSelectors customAlarmClockSelectors = new CustomAlarmClockSelectors();
            customAlarmClockSelectors.BpLevelPicker(MeAlarmClockActivity.this.firstHourList, MeAlarmClockActivity.this.firstMinuteList, null, hisSearch.getAlarmHour(), hisSearch.getAlarmMin(), 1, "", "", "", false, CustomAlarmClockSelectors.SelectorsDataNum.TWO, true, true, hisSearch.getAlternativeDate(), hisSearch.getIsSwitch(), MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.modify_clock_title));
            customAlarmClockSelectors.setOnTwoSelectorsDataListener(new AnonymousClass1(hisSearch));
        }

        /* renamed from: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity$2$1, reason: invalid class name */
        class AnonymousClass1 implements CustomAlarmClockSelectors.OnTwoSelectorsDataListener {
            final /* synthetic */ MeAlarmClock val$hisSearch;

            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.OnTwoSelectorsDataListener
            public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
            }

            AnonymousClass1(final MeAlarmClock val$hisSearch) {
                this.val$hisSearch = val$hisSearch;
            }

            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.OnTwoSelectorsDataListener
            public void saveClock(String oneValue, String twoValue, String mWeek) throws NumberFormatException {
                String str;
                if (this.val$hisSearch.getIsSwitch().equals("1")) {
                    str = mWeek + "1";
                } else {
                    str = mWeek + "0";
                }
                YCBTClient.settingModfiyAlarm(this.val$hisSearch.getAlarmHour(), this.val$hisSearch.getAlarmMin(), 1, Integer.parseInt(oneValue), Integer.parseInt(twoValue), Integer.parseInt(Tools.BinaryToHex(new StringBuffer(str).reverse().toString()), 16), 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity.2.1.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 3) {
                            MeAlarmClockActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity.2.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    MeAlarmClockActivity.this.getAllAlarm();
                                }
                            });
                        }
                        MeAlarmClockActivity.this.toastResult(code, ((Integer) resultMap.get("code")).intValue());
                    }
                });
            }

            @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.OnTwoSelectorsDataListener
            public void deleteClock() {
                MeAlarmClockActivity.this.getAllAlarm();
            }
        }

        @Override // com.yucheng.smarthealthpro.me.adapter.MeAlarmClockListAdapter.OnItemClickListener
        public void onChecked(MeAlarmClock hisSearch, int position) {
            Log.i("AAAAA", position + "onChecked=====");
        }
    }

    public int getWeek(String mWeek) {
        String[] strArrSplit = mWeek.split(",");
        String str = "";
        int i2 = 0;
        while (true) {
            if (i2 >= 8) {
                break;
            }
            if (i2 == 7) {
                str = "1" + str;
                break;
            }
            if (Integer.parseInt(strArrSplit[i2]) == 1) {
                str = "1" + str;
            } else {
                str = "0" + str;
            }
            i2++;
        }
        return Integer.parseInt(Tools.BinaryToHex(str), 16);
    }

    public String clockRepeatToValueArray(int clockRepeat) {
        String binaryString = Integer.toBinaryString(clockRepeat);
        if (binaryString.length() < 8) {
            int length = 8 - binaryString.length();
            for (int i2 = 0; i2 < length; i2++) {
                binaryString = "0" + binaryString;
            }
        }
        return new StringBuffer(binaryString).reverse().toString();
    }

    public void onViewClicked(View view) {
        CustomAlarmClockSelectors customAlarmClockSelectors = new CustomAlarmClockSelectors();
        customAlarmClockSelectors.BpLevelPicker(this.firstHourList, this.firstMinuteList, null, 1, 1, 1, "", "", "", false, CustomAlarmClockSelectors.SelectorsDataNum.TWO, false, false, null, null, this.context, getString(R.string.add_clock_title));
        customAlarmClockSelectors.setOnTwoSelectorsDataListener(new AnonymousClass3());
    }

    /* renamed from: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity$3, reason: invalid class name */
    class AnonymousClass3 implements CustomAlarmClockSelectors.OnTwoSelectorsDataListener {
        @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.OnTwoSelectorsDataListener
        public void deleteClock() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.OnTwoSelectorsDataListener
        public void getSelectorsDataClick(String oneValue, String twoValue, int optionsOne, int optionsTwo) {
        }

        AnonymousClass3() {
        }

        @Override // com.yucheng.smarthealthpro.home.view.CustomAlarmClockSelectors.OnTwoSelectorsDataListener
        public void saveClock(String oneValue, String twoValue, String mWeek) throws NumberFormatException {
            MeAlarmClockActivity.this.runOnUiThread(new AnonymousClass1(oneValue, twoValue, Integer.parseInt(Tools.BinaryToHex(new StringBuffer(mWeek + "1").reverse().toString()), 16)));
        }

        /* renamed from: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity$3$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ int val$mWeeks;
            final /* synthetic */ String val$oneValue;
            final /* synthetic */ String val$twoValue;

            AnonymousClass1(final String val$oneValue, final String val$twoValue, final int val$mWeeks) {
                this.val$oneValue = val$oneValue;
                this.val$twoValue = val$twoValue;
                this.val$mWeeks = val$mWeeks;
            }

            @Override // java.lang.Runnable
            public void run() {
                YCBTClient.settingAddAlarm(0, Integer.parseInt(this.val$oneValue), Integer.parseInt(this.val$twoValue), this.val$mWeeks, 0, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity.3.1.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(int code, float ratio, HashMap resultMap) {
                        if (code == 1) {
                            MeAlarmClockActivity.this.runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity.3.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    MeAlarmClockActivity.this.getAllAlarm();
                                }
                            });
                        }
                        MeAlarmClockActivity.this.toastResult(code, ((Integer) resultMap.get("code")).intValue());
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toastResult(final int type, final int data) {
        runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.activity.MeAlarmClockActivity.4
            @Override // java.lang.Runnable
            public void run() {
                int i2 = type;
                if (i2 == 1) {
                    int i3 = data;
                    if (i3 == 0) {
                        Toast.makeText(MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.clock_add_success), 0).show();
                        return;
                    }
                    if (i3 == 1) {
                        Toast.makeText(MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.clock_add_failed_out_max), 0).show();
                        return;
                    } else if (i3 == 2) {
                        Toast.makeText(MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.clock_add_failed_already), 0).show();
                        return;
                    } else {
                        if (i3 == 3) {
                            Toast.makeText(MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.clock_add_failed_param_error), 0).show();
                            return;
                        }
                        return;
                    }
                }
                if (i2 == 3) {
                    int i4 = data;
                    if (i4 == 0) {
                        Toast.makeText(MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.clock_modify_success), 0).show();
                        return;
                    }
                    if (i4 == 1) {
                        Toast.makeText(MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.clock_modify_failed_non_existent), 0).show();
                    } else if (i4 != 2 && i4 == 3) {
                        Toast.makeText(MeAlarmClockActivity.this.context, MeAlarmClockActivity.this.getString(R.string.clock_modify_failed_param_error), 0).show();
                    }
                }
            }
        });
    }
}
