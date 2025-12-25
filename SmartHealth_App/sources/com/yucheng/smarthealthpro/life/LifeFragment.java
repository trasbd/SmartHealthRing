package com.yucheng.smarthealthpro.life;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gyf.immersionbar.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.FragmentLifeBinding;
import com.yucheng.smarthealthpro.framework.BaseFragment;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.life.adapter.LifeAdapter;
import com.yucheng.smarthealthpro.life.adapter.LifeData;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes5.dex */
public class LifeFragment extends BaseFragment {
    FragmentLifeBinding binding;
    private LifeAdapter lifeAdapter;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initData(Context mContext) {
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    @Deprecated
    protected int initLayout() {
        return 0;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected void initView(View view) throws ParseException {
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentLifeBinding fragmentLifeBindingInflate = FragmentLifeBinding.inflate(getLayoutInflater(), container, false);
        this.binding = fragmentLifeBindingInflate;
        fragmentLifeBindingInflate.getRoot().setVisibility(Constant.isRingTouch() ? 0 : 8);
        getBar(this.binding.getRoot());
        try {
            initView(this.binding.getRoot());
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        EventBus.getDefault().register(this);
        return this.binding.getRoot();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        changeTitle(R.string.wisdom_title);
        LifeAdapter lifeAdapter = new LifeAdapter();
        this.lifeAdapter = lifeAdapter;
        lifeAdapter.add(new LifeData(0));
        this.lifeAdapter.add(new LifeData(2));
        this.lifeAdapter.add(new LifeData(R.mipmap.ic_life_video, R.string.wisdom_short_video, 0, R.string.wisdom_short_video_up_down, false, false, 1, 1));
        this.binding.recyclerView.setAdapter(this.lifeAdapter);
        this.binding.recyclerView.setNestedScrollingEnabled(false);
        init();
        this.lifeAdapter.setOnItemClick(new AnonymousClass1());
    }

    /* renamed from: com.yucheng.smarthealthpro.life.LifeFragment$1, reason: invalid class name */
    class AnonymousClass1 implements LifeAdapter.OnItemClickListener {
        AnonymousClass1() {
        }

        @Override // com.yucheng.smarthealthpro.life.adapter.LifeAdapter.OnItemClickListener
        public void onClick(int position) {
            if (LifeFragment.this.getActivity() != null && LifeFragment.this.lifeAdapter.getItem(position).text1 == R.string.wisdwom_sos_setting) {
                LifeFragment.this.startActivity(new Intent(LifeFragment.this.getActivity(), (Class<?>) SosSettingsActivity.class));
            }
        }

        @Override // com.yucheng.smarthealthpro.life.adapter.LifeAdapter.OnItemClickListener
        public void onChecked(final int i2, final int i3, final boolean z) {
            if (YCBTClient.connectState() == 10) {
                BleDataResponse bleDataResponse = new BleDataResponse() { // from class: com.yucheng.smarthealthpro.life.LifeFragment.1.1
                    @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                    public void onDataResponse(final int code, float v, HashMap hashMap) {
                        LifeFragment.this.requireActivity().runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.life.LifeFragment.1.1.1
                            @Override // java.lang.Runnable
                            public void run() throws Resources.NotFoundException {
                                Logger.d("LifeFragment code =" + code);
                                if (code == 0) {
                                    if (i3 == 5) {
                                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstValue.Wisdom_SOS, Boolean.valueOf(z));
                                    } else if (z) {
                                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstValue.Wisdom_Function, Integer.valueOf(i3));
                                    } else {
                                        SharedPreferencesUtils.put(MyApplication.getInstance().getApplicationContext(), Constant.SpConstValue.Wisdom_Function, -1);
                                    }
                                    ToastUtil.getInstance(MyApplication.getInstance().getApplicationContext()).toast(R.string.save_successfully);
                                    return;
                                }
                                ToastUtil.getInstance(MyApplication.getInstance().getApplicationContext()).toast(R.string.save_failed);
                                LifeFragment.this.init();
                            }
                        });
                    }
                };
                if (i3 != 5) {
                    ArrayList<LifeData> datas = LifeFragment.this.lifeAdapter.getDatas();
                    for (int i4 = 0; i4 < datas.size(); i4++) {
                        LifeData lifeData = datas.get(i4);
                        if (i4 == i2) {
                            lifeData.opened = z;
                        }
                        if (i4 != i2 && lifeData.protocolIndex != 5 && lifeData.opened) {
                            lifeData.opened = false;
                            LifeFragment.this.lifeAdapter.notifyItemChanged(i4);
                        }
                    }
                }
                YCBTClient.setWitOnOff(z ? 1 : 0, i3, bleDataResponse);
                return;
            }
            LifeFragment.this.requireActivity().runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.life.LifeFragment.1.2
                @Override // java.lang.Runnable
                public void run() throws Resources.NotFoundException {
                    LifeFragment.this.lifeAdapter.getItem(i2).opened = !z;
                    LifeFragment.this.lifeAdapter.notifyItemChanged(i2);
                    ToastUtil.getInstance(MyApplication.getInstance().getApplicationContext()).toast(R.string.please_connect_the_device);
                }
            });
        }

        @Override // com.yucheng.smarthealthpro.life.adapter.LifeAdapter.OnItemClickListener
        public void onClickViewId(int viewId) {
            LifeTipDialogFragment lifeTipDialogFragment = new LifeTipDialogFragment();
            if (viewId == R.id.iv_tip) {
                lifeTipDialogFragment.showTipType(1);
                lifeTipDialogFragment.show(LifeFragment.this.getActivity().getSupportFragmentManager(), "android");
            } else if (viewId == R.id.tv_desc) {
                lifeTipDialogFragment.showTipType(2);
                lifeTipDialogFragment.show(LifeFragment.this.getActivity().getSupportFragmentManager(), "android");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        int iIntValue = ((Integer) SharedPreferencesUtils.get(getActivity(), Constant.SpConstValue.Wisdom_Function, -1)).intValue();
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.get(getActivity(), Constant.SpConstValue.Wisdom_SOS, false)).booleanValue();
        ArrayList<LifeData> datas = this.lifeAdapter.getDatas();
        for (int i2 = 0; i2 < datas.size(); i2++) {
            LifeData lifeData = datas.get(i2);
            if (lifeData.protocolIndex == iIntValue) {
                lifeData.opened = true;
            } else if (lifeData.protocolIndex == 5) {
                lifeData.opened = zBooleanValue;
            } else {
                lifeData.opened = false;
            }
            this.lifeAdapter.notifyItemChanged(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCache(HashMap hashMap) {
        int i2 = (hashMap.containsKey("videoState") && ((Integer) hashMap.get("videoState")).intValue() == 1) ? 1 : -1;
        if (hashMap.containsKey("musicState") && ((Integer) hashMap.get("musicState")).intValue() == 1) {
            i2 = 2;
        }
        if (hashMap.containsKey("readingState") && ((Integer) hashMap.get("readingState")).intValue() == 1) {
            i2 = 3;
        }
        if (hashMap.containsKey("photoState") && ((Integer) hashMap.get("photoState")).intValue() == 1) {
            i2 = 4;
        }
        if (hashMap.containsKey("slideshowState") && ((Integer) hashMap.get("slideshowState")).intValue() == 1) {
            i2 = 6;
        }
        boolean z = hashMap.containsKey("videoState") && ((Integer) hashMap.get("videoState")).intValue() == 1;
        SharedPreferencesUtils.put(getActivity(), Constant.SpConstValue.Wisdom_Function, Integer.valueOf(i2));
        SharedPreferencesUtils.put(getActivity(), Constant.SpConstValue.Wisdom_SOS, Boolean.valueOf(z));
    }

    @Override // com.gyf.immersionbar.components.ImmersionOwner
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCompile(EventBusMessageEvent messageEvent) {
        if (YCBTClient.connectState() == 10 && Constant.isRingTouch()) {
            YCBTClient.getWitState(new BleDataResponse() { // from class: com.yucheng.smarthealthpro.life.LifeFragment.2
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int code, float v, final HashMap hashMap) {
                    if (code == 0) {
                        LifeFragment.this.requireActivity().runOnUiThread(new Runnable() { // from class: com.yucheng.smarthealthpro.life.LifeFragment.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LifeFragment.this.updateCache(hashMap);
                                LifeFragment.this.init();
                            }
                        });
                    }
                }
            });
        }
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, com.gyf.immersionbar.components.ImmersionOwner
    public void onVisible() {
        super.onVisible();
        this.binding.getRoot().setVisibility(0);
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, com.gyf.immersionbar.components.ImmersionOwner
    public void onInvisible() {
        super.onInvisible();
        this.binding.getRoot().setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
