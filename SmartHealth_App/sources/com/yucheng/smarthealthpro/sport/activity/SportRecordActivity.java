package com.yucheng.smarthealthpro.sport.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.data.packed.HealthDayData;
import com.yucheng.smarthealthpro.database.room.bean.SportRecord;
import com.yucheng.smarthealthpro.databinding.ActivitySportrecordBinding;
import com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter;
import com.yucheng.smarthealthpro.sport.bean.SportHisListBean;
import com.yucheng.smarthealthpro.sport.bean.SportMonthNode;
import com.yucheng.smarthealthpro.sport.bean.SportMonthRecordNodeBean;
import com.yucheng.smarthealthpro.utils.FlowUtils;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.viewmodel.SportRecordViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes5.dex */
public class SportRecordActivity extends BaseVbActivity<ActivitySportrecordBinding> {
    private SportMonthNode entity;
    private List<BaseNode> items;
    private List<BaseNode> list;
    LinearLayout llNoData;
    SwipeRecyclerView mRecyclerView;
    List<SportRecord> mRunDb;
    private List<SportHisListBean> mSportHisListBean;
    private SportMonthNodeAdapter mSportRecordAdapter;
    private List<SportMonthRecordNodeBean> mTestBean;
    private SportRecordViewModel mViewModel;

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
        FlowUtils.INSTANCE.collectFlow(this, this.mViewModel.getSportRecordDataFlow(), new FlowUtils.FlowCollector<HealthDayData<SportRecord>>() { // from class: com.yucheng.smarthealthpro.sport.activity.SportRecordActivity.1
            @Override // com.yucheng.smarthealthpro.utils.FlowUtils.FlowCollector
            public void collect(HealthDayData<SportRecord> data) {
                SportRecordActivity.this.onAllData(data.getData());
            }
        });
    }

    private void initData() {
        this.mTestBean = new ArrayList();
        this.mSportHisListBean = new ArrayList();
        this.mViewModel.getAllData();
    }

    private void setRecycleView() {
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setItemViewSwipeEnabled(false);
        SportMonthNodeAdapter sportMonthNodeAdapter = new SportMonthNodeAdapter(R.layout.item_sport_month_node, this.context);
        this.mSportRecordAdapter = sportMonthNodeAdapter;
        sportMonthNodeAdapter.addData((Collection) this.mTestBean);
        this.mRecyclerView.setAdapter(this.mSportRecordAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAllData(List<SportRecord> data) {
        this.mRunDb = data;
        if (data.size() != 0) {
            for (int i2 = 0; i2 < this.mRunDb.size(); i2++) {
                this.mSportHisListBean.add(new SportHisListBean(this.mRunDb.get(i2).getType(), this.mRunDb.get(i2).getBeginDate(), this.mRunDb.get(i2).getTimeYearToDay(), this.mRunDb.get(i2).getTotalDistance(), this.mRunDb.get(i2).getTotalCalories(), this.mRunDb.get(i2).getMinkm(), this.mRunDb.get(i2).getHeartRate(), this.mRunDb.get(i2).getRunDuration(), this.mRunDb.get(i2).getKmh(), this.mRunDb.get(i2).getStartPoint(), this.mRunDb.get(i2).getEndPoint(), this.mRunDb.get(i2).getPathLinePoints(), Boolean.valueOf(this.mRunDb.get(i2).isUploaded()), this.mRunDb.get(i2).getTotalSteps()));
            }
        }
        if (this.mSportHisListBean.size() != 0) {
            this.mRecyclerView.setVisibility(0);
            this.llNoData.setVisibility(8);
        } else {
            this.mRecyclerView.setVisibility(8);
            this.llNoData.setVisibility(0);
        }
        getEntity();
        setRecycleView();
    }

    private List<BaseNode> getEntity() {
        this.list = new ArrayList();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mSportHisListBean.size(); i2++) {
            if (arrayList.size() != 0) {
                if (((String) arrayList.get(i2 - 1)).equals(TimeStampUtils.dateForStringMonthDate(TimeStampUtils.longStampForDate(this.mSportHisListBean.get(i2).getBeginDate())))) {
                    arrayList.add(TimeStampUtils.dateForStringMonthDate(TimeStampUtils.longStampForDate(this.mSportHisListBean.get(i2).getBeginDate())));
                } else {
                    arrayList.add(TimeStampUtils.dateForStringMonthDate(TimeStampUtils.longStampForDate(this.mSportHisListBean.get(i2).getBeginDate())));
                    getMonthRunList(TimeStampUtils.dateForStringMonthDate(TimeStampUtils.longStampForDate(this.mSportHisListBean.get(i2).getBeginDate())));
                }
            } else {
                arrayList.add(TimeStampUtils.dateForStringMonthDate(TimeStampUtils.longStampForDate(this.mSportHisListBean.get(i2).getBeginDate())));
                getMonthRunList(TimeStampUtils.dateForStringMonthDate(TimeStampUtils.longStampForDate(this.mSportHisListBean.get(i2).getBeginDate())));
            }
        }
        return this.list;
    }

    private void getMonthRunList(String month) {
        ArrayList arrayList = new ArrayList();
        this.items = new ArrayList();
        for (int i2 = 0; i2 < this.mSportHisListBean.size(); i2++) {
            if (TimeStampUtils.dateForStringMonthDate(TimeStampUtils.longStampForDate(this.mSportHisListBean.get(i2).getBeginDate())).equals(month)) {
                this.items.add(new SportHisListBean(this.mSportHisListBean.get(i2).getType(), this.mSportHisListBean.get(i2).getBeginDate(), this.mSportHisListBean.get(i2).getTimeYearToDate(), this.mSportHisListBean.get(i2).getDistance(), this.mSportHisListBean.get(i2).getCalorie(), this.mSportHisListBean.get(i2).getMinkm(), this.mSportHisListBean.get(i2).getHeart(), this.mSportHisListBean.get(i2).getRunTime(), this.mSportHisListBean.get(i2).getKmh(), this.mSportHisListBean.get(i2).getStartPoint(), this.mSportHisListBean.get(i2).getEndPoint(), this.mSportHisListBean.get(i2).getPathLinePoints(), this.mSportHisListBean.get(i2).getUpload(), this.mSportHisListBean.get(i2).getSportStep()));
                arrayList.add(new SportHisListBean(this.mSportHisListBean.get(i2).getType(), this.mSportHisListBean.get(i2).getBeginDate(), this.mSportHisListBean.get(i2).getTimeYearToDate(), this.mSportHisListBean.get(i2).getDistance(), this.mSportHisListBean.get(i2).getCalorie(), this.mSportHisListBean.get(i2).getMinkm(), this.mSportHisListBean.get(i2).getHeart(), this.mSportHisListBean.get(i2).getRunTime(), this.mSportHisListBean.get(i2).getKmh(), this.mSportHisListBean.get(i2).getStartPoint(), this.mSportHisListBean.get(i2).getEndPoint(), this.mSportHisListBean.get(i2).getPathLinePoints(), this.mSportHisListBean.get(i2).getUpload(), this.mSportHisListBean.get(i2).getSportStep()));
            }
        }
        this.mTestBean.add(new SportMonthRecordNodeBean(month, arrayList));
        SportMonthNode sportMonthNode = new SportMonthNode(this.items, month + getString(R.string.date_month_unit));
        this.entity = sportMonthNode;
        sportMonthNode.setExpanded(false);
        this.list.add(this.entity);
    }
}
