package com.yucheng.smarthealthpro.sport.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.database.room.bean.SportRecord;
import com.yucheng.smarthealthpro.databinding.ActivitySportrecordBinding;
import com.yucheng.smarthealthpro.sport.adapter.SportRecord2Adapter;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.sport.bean.SportRecord2;
import com.yucheng.smarthealthpro.sport.utils.GoogleUtil;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.viewmodel.SportRecordViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class SportRecord2Activity extends BaseVbActivity<ActivitySportrecordBinding> {
    LinearLayout llNoData;
    SwipeRecyclerView mRecyclerView;
    private List<SportRecord> mRunDb;
    private SportRecordViewModel mViewModel;
    ArrayList<SportRecord2> sportList;
    private HashMap<String, ArrayList<SportRecord2>> sportMap;
    private SportRecord2Adapter sportRecord2Adapter;
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRecord2Activity.2
        @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
            if (SportRecord2Activity.this.sportRecord2Adapter.getData(position).getViewType() == 0) {
                return;
            }
            swipeRightMenu.addMenuItem(new SwipeMenuItem(SportRecord2Activity.this.getBaseContext()).setBackground(R.drawable.selector_red).setText(SportRecord2Activity.this.getBaseContext().getString(R.string.delete)).setTextColor(-1).setWidth(SportRecord2Activity.this.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
        }
    };

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initData();
    }

    private void initView() {
        this.mRecyclerView = ((ActivitySportrecordBinding) this.mBinding).rvRecord;
        this.llNoData = ((ActivitySportrecordBinding) this.mBinding).llNoData;
        changeTitle(getString(R.string.sport_running_record_title));
        showBack();
    }

    private void initViewModel() {
        this.mViewModel = (SportRecordViewModel) new ViewModelProvider(this).get(SportRecordViewModel.class);
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSportRecordDataFlow(), new FlowUtils.FlowCollector<HealthDayData<SportRecord>>() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRecord2Activity.1
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<SportRecord> data) {
                SportRecord2Activity.this.onDataLoad(data.getData());
            }
        });
    }

    private void initData() {
        this.sportMap = new HashMap<>();
        this.sportList = new ArrayList<>();
        setRecycleView();
        this.mViewModel.getAllData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataLoad(List<SportRecord> data) {
        this.mRunDb = data;
        if (data.size() != 0) {
            ArrayList<SportRecord2> arrayList = new ArrayList<>();
            int size = this.mRunDb.size() - 1;
            String str = null;
            while (size >= 0) {
                String strDateForStringYearToMonth = TimeStampUtils.dateForStringYearToMonth(TimeStampUtils.longStampForDate(this.mRunDb.get(size).getBeginDate()));
                if (str == null || !str.equals(strDateForStringYearToMonth)) {
                    if (str != null) {
                        this.sportMap.put(str, arrayList);
                        arrayList = new ArrayList<>();
                    }
                    this.sportList.add(new SportRecord2(0, strDateForStringYearToMonth));
                    str = strDateForStringYearToMonth;
                }
                SportRecord2 sportRecord2 = new SportRecord2(1, "", new SportHisListBean(this.mRunDb.get(size).getType(), this.mRunDb.get(size).getBeginDate(), this.mRunDb.get(size).getTimeYearToDay(), this.mRunDb.get(size).getTotalDistance(), this.mRunDb.get(size).getTotalCalories(), this.mRunDb.get(size).getMinkm(), this.mRunDb.get(size).getHeartRate(), this.mRunDb.get(size).getRunDuration(), this.mRunDb.get(size).getKmh(), this.mRunDb.get(size).getStartPoint(), this.mRunDb.get(size).getEndPoint(), this.mRunDb.get(size).getPathLinePoints(), Boolean.valueOf(this.mRunDb.get(size).isUploaded()), this.mRunDb.get(size).getTotalSteps()));
                arrayList.add(sportRecord2);
                this.sportList.add(sportRecord2);
                size--;
                str = str;
            }
            if (str != null) {
                this.sportMap.put(str, arrayList);
                new ArrayList();
            }
        }
        this.sportRecord2Adapter.setDataList(this.sportList);
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setItemViewSwipeEnabled(false);
        SportRecord2Adapter sportRecord2Adapter = new SportRecord2Adapter();
        this.sportRecord2Adapter = sportRecord2Adapter;
        sportRecord2Adapter.setDataList(this.sportList);
        this.mRecyclerView.setSwipeMenuCreator(this.swipeMenuCreator);
        this.mRecyclerView.setOnItemMenuClickListener(new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRecord2Activity.3
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                ArrayList arrayList;
                menuBridge.closeMenu();
                if (SportRecord2Activity.this.sportRecord2Adapter.getData(adapterPosition).getContent() == null) {
                    return;
                }
                SportRecord2 data = SportRecord2Activity.this.sportRecord2Adapter.getData(adapterPosition);
                SportHisListBean sportHisListBean = (SportHisListBean) data.getContent();
                if (menuBridge.getDirection() == -1) {
                    SportRecord2Activity.this.mViewModel.deleteSportRecord(sportHisListBean.getBeginDate());
                    int i2 = adapterPosition - 1;
                    while (true) {
                        if (i2 < 0) {
                            arrayList = null;
                            break;
                        }
                        SportRecord2 data2 = SportRecord2Activity.this.sportRecord2Adapter.getData(i2);
                        if (data2.getViewType() == 0) {
                            arrayList = (ArrayList) SportRecord2Activity.this.sportMap.get(data2.getTitle());
                            if (arrayList != null) {
                                arrayList.remove(data);
                                SportRecord2Activity.this.sportMap.put(data2.getTitle(), arrayList);
                            }
                        } else {
                            i2--;
                        }
                    }
                    SportRecord2Activity.this.sportRecord2Adapter.removeAt(adapterPosition);
                    if (arrayList != null && arrayList.size() == 0) {
                        SportRecord2Activity.this.sportRecord2Adapter.removeAt(i2);
                    }
                    SportRecord2Activity.this.sportRecord2Adapter.notifyDataSetChanged();
                }
            }
        });
        this.sportRecord2Adapter.setOnItemClickListener(new SportRecord2Adapter.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRecord2Activity.4
            @Override // com.yucheng.smarthealthpro.sport.adapter.SportRecord2Adapter.OnItemClickListener
            public void onItemClick(View view, int position) {
                SportRecord2 data = SportRecord2Activity.this.sportRecord2Adapter.getData(position);
                if (data.getViewType() == 0) {
                    if (data.isOpen()) {
                        data.setOpen(false);
                        List list = (List) SportRecord2Activity.this.sportMap.get(data.getTitle());
                        if (list != null) {
                            SportRecord2Activity.this.sportRecord2Adapter.getDataList().removeAll(list);
                        }
                        SportRecord2Activity.this.sportRecord2Adapter.notifyDataSetChanged();
                        return;
                    }
                    data.setOpen(true);
                    int i2 = position + 1;
                    ArrayList arrayList = (ArrayList) SportRecord2Activity.this.sportMap.get(data.getTitle());
                    if (arrayList != null && arrayList.size() > 0) {
                        SportRecord2Activity.this.sportRecord2Adapter.getDataList().addAll(i2, arrayList);
                    }
                    SportRecord2Activity.this.sportRecord2Adapter.notifyDataSetChanged();
                    return;
                }
                if (data.getContent() != null) {
                    SportHisListBean sportHisListBean = (SportHisListBean) data.getContent();
                    if (GoogleUtil.checkGoogleAvailable()) {
                        Intent intent = new Intent(SportRecord2Activity.this.getBaseContext(), (Class<?>) SportRunningHisMapActivity.class);
                        intent.putExtra("hislist", sportHisListBean);
                        intent.putExtra("map", "googleMap");
                        SportRecord2Activity.this.startActivity(intent);
                        return;
                    }
                    Intent intent2 = new Intent(SportRecord2Activity.this.getBaseContext(), (Class<?>) SportRunningHisMapActivity.class);
                    intent2.putExtra("hislist", sportHisListBean);
                    intent2.putExtra("map", "aMap");
                    SportRecord2Activity.this.startActivity(intent2);
                }
            }
        });
        this.mRecyclerView.setAdapter(this.sportRecord2Adapter);
    }
}
