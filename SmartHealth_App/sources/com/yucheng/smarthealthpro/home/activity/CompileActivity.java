package com.yucheng.smarthealthpro.home.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.tencent.connect.common.Constants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityCompileBinding;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.home.adapter.CompileItemAddAdapter;
import com.yucheng.smarthealthpro.home.adapter.CompileItemDragAdapter;
import com.yucheng.smarthealthpro.home.bean.HomeFunctionBean;
import com.yucheng.smarthealthpro.utils.AppImageMgr;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.EventBusMessageEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes5.dex */
public class CompileActivity extends BaseVbActivity<ActivityCompileBinding> {
    private CompileItemDragAdapter mAdapter;
    private CompileItemAddAdapter mAddAdapter;
    RecyclerView mAddRecyclerView;
    private AppImageMgr mAppImageMgr;
    private List<HomeFunctionBean> mHomeAddFunctionBean;
    private List<HomeFunctionBean> mHomeFunctionBean;
    private OnItemDragListener mOnItemDragListener;
    private OnItemSwipeListener mOnItemSwipeListener;
    RecyclerView mRecyclerView;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivityCompileBinding) this.mBinding).recycleCompile;
        this.mAddRecyclerView = ((ActivityCompileBinding) this.mBinding).recycleCompileAdd;
        changeTitle(getString(R.string.home_edit_module_text));
        showBack();
    }

    private void initData() {
        String str;
        Integer num;
        Object obj;
        Object obj2;
        String str2;
        Object obj3;
        String str3;
        String str4;
        Object obj4;
        Object obj5;
        Object obj6;
        CompileActivity compileActivity = this;
        compileActivity.mAppImageMgr = new AppImageMgr(compileActivity.context);
        compileActivity.mHomeFunctionBean = new ArrayList();
        compileActivity.mHomeAddFunctionBean = new ArrayList();
        Integer num2 = 0;
        int iIntValue = ((Integer) SharedPreferencesUtils.get(compileActivity.context, Constant.SpConstKey.M_HOME_FUNCTION_BEAN_SIZE, num2)).intValue();
        int i2 = 0;
        while (true) {
            str = "呼吸率";
            num = num2;
            obj = "压力";
            obj2 = "HRV";
            str2 = "心电";
            obj3 = "理疗";
            if (i2 >= iIntValue) {
                break;
            }
            int i3 = iIntValue;
            String str5 = (String) SharedPreferencesUtils.get(compileActivity.context, Constant.SpConstKey.FUNCTION + i2, "");
            if (str5 != null) {
                if (str5.equals("心电")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("", "", compileActivity.getString(R.string.home_ecg_title), "心电", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_ecg), true));
                } else if (str5.equals("血压")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("80/90", "mmHg", compileActivity.getString(R.string.home_blood_pressure_title), "血压", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bp), true));
                } else if (str5.equals("心率")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("085", "bpm", compileActivity.getString(R.string.function_heart), "心率", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
                } else if (str5.equals("呼吸率")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean(Constants.VIA_REPORT_TYPE_SET_AVATAR, "bpm", compileActivity.getString(R.string.home_respiratory_rate_title), "呼吸率", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
                } else if (str5.equals("血氧")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("98", "%", compileActivity.getString(R.string.home_blood_oxygen_title), "血氧", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), true));
                } else if (str5.equals("睡眠")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("08h08", "mins", compileActivity.getString(R.string.home_sleep_title), "睡眠", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), true));
                } else if (str5.equals("温度")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("36.2", Constant.SpConstValue.TEMP_ISO, compileActivity.getString(R.string.function_temp), "温度", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
                } else if (str5.equals("运动") && Constant.isMymon()) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", "steps", compileActivity.getString(R.string.sport_title), "运动", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sport), true));
                } else if (str5.equals("血糖")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("4.8", "mmol/l", compileActivity.getString(R.string.home_blood_sugar_title), "血糖", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_sugar), true));
                } else if (str5.equals("血脂")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", "mmol/l", compileActivity.getString(R.string.blood_fat), "血脂", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), true));
                } else if (str5.equals("尿酸")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", Constant.SpConstValue.URIC_ACID_UMOL, compileActivity.getString(R.string.uric_acid), "尿酸", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_uric_acid), true));
                } else if (str5.equals("血酮")) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.blood_ketones), "血酮", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_blood_ketone), true));
                } else if (str5.equals(obj3)) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.physiotherapy), "理疗", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_physiotherapy), true));
                } else if (str5.equals(obj2)) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.hrv_unit), "HRV", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
                } else if (str5.equals(obj)) {
                    compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.pressure_str), "压力", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), true));
                }
            }
            i2++;
            num2 = num;
            iIntValue = i3;
        }
        Object obj7 = "尿酸";
        Object obj8 = "血酮";
        int iIntValue2 = ((Integer) SharedPreferencesUtils.get(compileActivity.context, Constant.SpConstKey.M_HOME_ADD_FUNCTION_BEAN_SIZE, num)).intValue();
        int i4 = 0;
        while (i4 < iIntValue2) {
            int i5 = iIntValue2;
            Object obj9 = obj7;
            String str6 = (String) SharedPreferencesUtils.get(compileActivity.context, Constant.SpConstKey.HIDE_FUNCTION + i4, "");
            if (str6 != null) {
                if (str6.equals(str2)) {
                    str4 = str2;
                    compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("", "", compileActivity.getString(R.string.home_ecg_title), "心电", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_ecg), true));
                } else {
                    str4 = str2;
                    if (str6.equals("血压")) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("80/90", "mmHg", compileActivity.getString(R.string.home_blood_pressure_title), "血压", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bp), true));
                    } else if (str6.equals("心率")) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("085", "bpm", compileActivity.getString(R.string.function_heart), "心率", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
                    } else if (str6.equals(str)) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean(Constants.VIA_REPORT_TYPE_SET_AVATAR, "bpm", compileActivity.getString(R.string.home_respiratory_rate_title), "呼吸率", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_rr), true));
                    } else if (str6.equals("血氧")) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("98", "%", compileActivity.getString(R.string.home_blood_oxygen_title), "血氧", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_bo), true));
                    } else if (str6.equals("睡眠")) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("08h08", "mins", compileActivity.getString(R.string.home_sleep_title), "睡眠", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sleep), true));
                    } else if (str6.equals("温度")) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("36.26", Constant.SpConstValue.TEMP_ISO, compileActivity.getString(R.string.function_temp), "温度", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_tp), true));
                    } else if (str6.equals("运动") && Constant.isMymon()) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("0", "steps", compileActivity.getString(R.string.sport_title), "运动", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_sport), true));
                    } else if (str6.equals("血糖")) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("4.8", "mmol/l", compileActivity.getString(R.string.home_blood_sugar_title), "血糖", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_sugar), true));
                    } else if (str6.equals("血脂")) {
                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("0", "mmol/l", compileActivity.getString(R.string.blood_fat), "血脂", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_blood_fat), true));
                    } else {
                        obj5 = obj9;
                        if (str6.equals(obj5)) {
                            str3 = str;
                            compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("0", Constant.SpConstValue.URIC_ACID_UMOL, compileActivity.getString(R.string.uric_acid), "尿酸", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_uric_acid), true));
                            obj6 = obj;
                            obj4 = obj8;
                        } else {
                            str3 = str;
                            Object obj10 = obj8;
                            if (str6.equals(obj10)) {
                                obj4 = obj10;
                                compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.blood_ketones), "血酮", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_blood_ketone), true));
                            } else {
                                obj4 = obj10;
                                Object obj11 = obj3;
                                if (str6.equals(obj11)) {
                                    obj3 = obj11;
                                    compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.physiotherapy), "理疗", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_physiotherapy), true));
                                } else {
                                    obj3 = obj11;
                                    Object obj12 = obj2;
                                    if (str6.equals(obj12)) {
                                        obj2 = obj12;
                                        compileActivity.mHomeAddFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.hrv_unit), "HRV", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_hr), true));
                                    } else {
                                        obj2 = obj12;
                                        obj6 = obj;
                                        if (str6.equals(obj6)) {
                                            compileActivity.mHomeFunctionBean.add(new HomeFunctionBean("0", "", compileActivity.getString(R.string.pressure_str), "压力", compileActivity.mAppImageMgr.getBitmap(R.mipmap.home_measure_icon_pressure), true));
                                        }
                                    }
                                }
                            }
                            obj6 = obj;
                        }
                        i4++;
                        compileActivity = this;
                        obj = obj6;
                        obj7 = obj5;
                        iIntValue2 = i5;
                        str2 = str4;
                        str = str3;
                        obj8 = obj4;
                    }
                }
                str3 = str;
            } else {
                str3 = str;
                str4 = str2;
            }
            obj6 = obj;
            obj4 = obj8;
            obj5 = obj9;
            i4++;
            compileActivity = this;
            obj = obj6;
            obj7 = obj5;
            iIntValue2 = i5;
            str2 = str4;
            str = str3;
            obj8 = obj4;
        }
        setItemDragListener();
        setItemSwipeListener();
        setCompileRecycleView();
        setAddRecycleView();
    }

    private void setItemDragListener() {
        this.mOnItemDragListener = new OnItemDragListener() { // from class: com.yucheng.smarthealthpro.home.activity.CompileActivity.1
            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("TAG", "drag start");
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                Log.d("TAG", "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
                Collections.swap(CompileActivity.this.mHomeFunctionBean, from, to);
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("TAG", "drag end");
            }
        };
    }

    private void setItemSwipeListener() {
        this.mOnItemSwipeListener = new OnItemSwipeListener() { // from class: com.yucheng.smarthealthpro.home.activity.CompileActivity.2
            @Override // com.chad.library.adapter.base.listener.OnItemSwipeListener
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
            }

            @Override // com.chad.library.adapter.base.listener.OnItemSwipeListener
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("TAG", "view swiped start: " + pos);
            }

            @Override // com.chad.library.adapter.base.listener.OnItemSwipeListener
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("TAG", "View reset: " + pos);
            }

            @Override // com.chad.library.adapter.base.listener.OnItemSwipeListener
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d("TAG", "View Swiped: " + pos);
                CompileActivity.this.mHomeAddFunctionBean.add(new HomeFunctionBean(((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(pos)).getValue(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(pos)).getUnit(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(pos)).name, ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(pos)).getFunction(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(pos)).getImagePath(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(pos)).getVisible()));
                CompileActivity.this.mHomeFunctionBean.remove(pos);
                CompileActivity.this.mAdapter.replaceData(CompileActivity.this.mHomeFunctionBean);
                CompileActivity.this.mAdapter.notifyDataSetChanged();
                CompileActivity.this.mAddAdapter.replaceData(CompileActivity.this.mHomeAddFunctionBean);
                CompileActivity.this.mAddAdapter.notifyDataSetChanged();
            }
        };
    }

    private void setCompileRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setNestedScrollingEnabled(false);
        CompileItemDragAdapter compileItemDragAdapter = new CompileItemDragAdapter(R.layout.item_home_compile);
        this.mAdapter = compileItemDragAdapter;
        compileItemDragAdapter.addData((Collection) this.mHomeFunctionBean);
        this.mAdapter.getDraggableModule().setSwipeEnabled(true);
        this.mAdapter.getDraggableModule().setDragEnabled(true);
        this.mAdapter.getDraggableModule().setOnItemDragListener(this.mOnItemDragListener);
        this.mAdapter.getDraggableModule().setOnItemSwipeListener(this.mOnItemSwipeListener);
        this.mAdapter.getDraggableModule().getItemTouchHelperCallback().setSwipeMoveFlags(48);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.yucheng.smarthealthpro.home.activity.CompileActivity.3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        this.mAdapter.setOnclick(new CompileItemDragAdapter.ClickInterface() { // from class: com.yucheng.smarthealthpro.home.activity.CompileActivity.4
            @Override // com.yucheng.smarthealthpro.home.adapter.CompileItemDragAdapter.ClickInterface
            public void onShowIvClick(View view, int position) {
                CompileActivity.this.mHomeAddFunctionBean.add(new HomeFunctionBean(((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getValue(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getUnit(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).name, ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getFunction(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getImagePath(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getVisible()));
                CompileActivity.this.mHomeFunctionBean.remove(position);
                CompileActivity.this.mAdapter.replaceData(CompileActivity.this.mHomeFunctionBean);
                CompileActivity.this.mAdapter.notifyDataSetChanged();
                CompileActivity.this.mAddAdapter.replaceData(CompileActivity.this.mHomeAddFunctionBean);
                CompileActivity.this.mAddAdapter.notifyDataSetChanged();
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.CompileItemDragAdapter.ClickInterface
            public void onUpIvClick(View view, int position) {
                if (position == 0) {
                    return;
                }
                CompileActivity.this.mHomeFunctionBean.add(position - 1, new HomeFunctionBean(((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getValue(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getUnit(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).name, ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getFunction(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getImagePath(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(position)).getVisible()));
                CompileActivity.this.mHomeFunctionBean.remove(position + 1);
                CompileActivity.this.mAdapter.replaceData(CompileActivity.this.mHomeFunctionBean);
                CompileActivity.this.mAdapter.notifyDataSetChanged();
            }

            @Override // com.yucheng.smarthealthpro.home.adapter.CompileItemDragAdapter.ClickInterface
            public void onDownIvClick(View view, int position) {
                if (position == CompileActivity.this.mHomeFunctionBean.size() - 1) {
                    return;
                }
                int i2 = position + 1;
                CompileActivity.this.mHomeFunctionBean.add(position, new HomeFunctionBean(((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(i2)).getValue(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(i2)).getUnit(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(i2)).name, ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(i2)).getFunction(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(i2)).getImagePath(), ((HomeFunctionBean) CompileActivity.this.mHomeFunctionBean.get(i2)).getVisible()));
                CompileActivity.this.mHomeFunctionBean.remove(position + 2);
                CompileActivity.this.mAdapter.replaceData(CompileActivity.this.mHomeFunctionBean);
                CompileActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setAddRecycleView() {
        this.mAddRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mAddRecyclerView.setNestedScrollingEnabled(false);
        CompileItemAddAdapter compileItemAddAdapter = new CompileItemAddAdapter(R.layout.item_home_compile_add);
        this.mAddAdapter = compileItemAddAdapter;
        compileItemAddAdapter.addData((Collection) this.mHomeAddFunctionBean);
        this.mAddRecyclerView.setAdapter(this.mAddAdapter);
        this.mAddAdapter.setOnclick(new CompileItemAddAdapter.ClickInterface() { // from class: com.yucheng.smarthealthpro.home.activity.CompileActivity.5
            @Override // com.yucheng.smarthealthpro.home.adapter.CompileItemAddAdapter.ClickInterface
            public void onHideIvClick(View view, int position) {
                CompileActivity.this.mHomeFunctionBean.add(new HomeFunctionBean(((HomeFunctionBean) CompileActivity.this.mHomeAddFunctionBean.get(position)).getValue(), ((HomeFunctionBean) CompileActivity.this.mHomeAddFunctionBean.get(position)).getUnit(), ((HomeFunctionBean) CompileActivity.this.mHomeAddFunctionBean.get(position)).name, ((HomeFunctionBean) CompileActivity.this.mHomeAddFunctionBean.get(position)).getFunction(), ((HomeFunctionBean) CompileActivity.this.mHomeAddFunctionBean.get(position)).getImagePath(), ((HomeFunctionBean) CompileActivity.this.mHomeAddFunctionBean.get(position)).getVisible()));
                CompileActivity.this.mAdapter.replaceData(CompileActivity.this.mHomeFunctionBean);
                CompileActivity.this.mAdapter.notifyDataSetChanged();
                CompileActivity.this.mHomeAddFunctionBean.remove(position);
                CompileActivity.this.mAddAdapter.replaceData(CompileActivity.this.mHomeAddFunctionBean);
                CompileActivity.this.mAddAdapter.notifyDataSetChanged();
            }
        });
    }

    private void saveFunctionBeanSp() {
        for (int i2 = 0; i2 < this.mHomeFunctionBean.size(); i2++) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.FUNCTION + i2, this.mHomeFunctionBean.get(i2).getFunction());
        }
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.M_HOME_FUNCTION_BEAN_SIZE, Integer.valueOf(this.mHomeFunctionBean.size()));
        for (int i3 = 0; i3 < this.mHomeAddFunctionBean.size(); i3++) {
            SharedPreferencesUtils.put(this.context, Constant.SpConstKey.HIDE_FUNCTION + i3, this.mHomeAddFunctionBean.get(i3).getFunction());
        }
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.M_HOME_ADD_FUNCTION_BEAN_SIZE, Integer.valueOf(this.mHomeAddFunctionBean.size()));
        SharedPreferencesUtils.put(this.context, Constant.SpConstKey.IS_COMPILE, Constant.SpConstValue.IS_COMPILE);
        EventBus eventBus = EventBus.getDefault();
        new EventBusMessageEvent().message = Constant.EventBusTags.COMPILE_SAVE_SUCCEED;
        eventBus.post(Constant.EventBusTags.COMPILE_SAVE_SUCCEED);
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        String str = (String) SharedPreferencesUtils.get(this.context, Constant.SpConstKey.IS_CONNECT, "");
        if (str == null || !str.equals(Constant.SpConstValue.IS_CONNECT)) {
            return;
        }
        saveFunctionBeanSp();
    }
}
