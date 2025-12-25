package com.yucheng.smarthealthpro.life;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.ActivitySosSettingsBinding;
import com.yucheng.smarthealthpro.databinding.ItemSosStepBinding;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class SosSettingsActivity extends AppCompatActivity {
    private Adapter adapter;
    private ActivitySosSettingsBinding binding;
    private List<SosStepData> dataList;

    public void showLeftImage(NavigationBar navigationBar, int id, NavigationBar.MyOnClickListener click) {
        navigationBar.setLeftBtnImage(id);
        navigationBar.setLeftOnClickListener(click);
    }

    public void changeTitle(NavigationBar navigationBar, String title) {
        navigationBar.setTitle(title);
    }

    public void showBack(NavigationBar navigationBar) {
        navigationBar.showLeftbtn(0);
        navigationBar.setLeftOnClickListener(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.life.SosSettingsActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                SosSettingsActivity.this.backAction();
            }
        });
    }

    public void backAction() {
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySosSettingsBinding activitySosSettingsBindingInflate = ActivitySosSettingsBinding.inflate(getLayoutInflater());
        this.binding = activitySosSettingsBindingInflate;
        setContentView(activitySosSettingsBindingInflate.getRoot());
        changeTitle(this.binding.navigationBar, getString(R.string.wisdwom_sos_setting));
        showBack(this.binding.navigationBar);
        showLeftImage(this.binding.navigationBar, R.mipmap.topbar_ic_back, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.life.SosSettingsActivity$$ExternalSyntheticLambda0
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0(view);
            }
        });
        ArrayList arrayList = new ArrayList();
        this.dataList = arrayList;
        arrayList.add(new SosStepData(1, getString(R.string.wisdom_sos_setting_guide_step1), getString(R.string.wisdom_sos_setting_guide_step1_detail)));
        this.dataList.add(new SosStepData(2, getString(R.string.wisdom_sos_setting_guide_step2), getString(R.string.wisdom_sos_setting_guide_step2_detail)));
        this.dataList.add(new SosStepData(3, getString(R.string.wisdom_sos_setting_guide_step3), getString(R.string.wisdom_sos_setting_guide_step3_detail)));
        this.adapter = new Adapter(this.dataList);
        this.binding.recyclerView.setAdapter(this.adapter);
        this.binding.recyclerView.setNestedScrollingEnabled(false);
        this.binding.btnSetting.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.life.SosSettingsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SosSettingsActivity.this.startActivity(new Intent("android.settings.SETTINGS"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    static class Adapter extends RecyclerView.Adapter<SosViewHolder> {
        private List<SosStepData> dataList;

        public Adapter(List<SosStepData> dataList) {
            this.dataList = dataList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public SosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SosViewHolder(ItemSosStepBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(SosViewHolder holder, int position) {
            holder.bind(this.dataList.get(position));
            if (position == getItemCount() - 1) {
                holder.binding.line.setVisibility(8);
            } else {
                holder.binding.line.setVisibility(0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.dataList.size();
        }
    }

    static class SosViewHolder extends RecyclerView.ViewHolder {
        ItemSosStepBinding binding;

        public SosViewHolder(ItemSosStepBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SosStepData data) {
            this.binding.tvTitle.setText(data.title);
            this.binding.tvDesc.setText(data.content);
            this.binding.tvSeq.setText("" + data.step);
        }
    }

    static class SosStepData {
        public String content;
        public int step;
        public String title;

        public SosStepData(int step, String title, String content) {
            this.step = step;
            this.title = title;
            this.content = content;
        }
    }
}
