package com.yucheng.smarthealthpro.life.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.ItemLifeBinding;

/* loaded from: classes5.dex */
public class LifeHolder extends RecyclerView.ViewHolder {
    public ItemLifeBinding binding;

    public LifeHolder(View itemView) {
        super(itemView);
        this.binding = ItemLifeBinding.bind(itemView);
    }

    void onBind(LifeData data) {
        if (data.icon == 0) {
            this.binding.ivIcon.setVisibility(8);
        } else {
            this.binding.ivIcon.setVisibility(0);
            this.binding.ivIcon.setImageResource(data.icon);
        }
        if (data.text1 == 0) {
            this.binding.text1.setVisibility(8);
        } else {
            this.binding.text1.setVisibility(0);
            this.binding.text1.setText(data.text1);
        }
        if (data.text2 == 0) {
            this.binding.text2.setVisibility(8);
        } else {
            this.binding.text2.setVisibility(0);
            this.binding.text2.setText(data.text2);
        }
        if (data.text3 == 0) {
            this.binding.text3.setVisibility(8);
        } else {
            this.binding.text3.setVisibility(0);
            this.binding.text3.setText(data.text3);
        }
        if (data.text1 == R.string.wisdwom_sos_setting) {
            this.binding.switchBar.setVisibility(8);
            this.binding.ivRightIconOne.setVisibility(0);
        } else {
            this.binding.switchBar.setVisibility(0);
            this.binding.ivRightIconOne.setVisibility(8);
        }
    }
}
