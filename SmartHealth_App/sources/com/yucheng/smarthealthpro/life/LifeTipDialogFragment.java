package com.yucheng.smarthealthpro.life;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.databinding.DialogLifeTipBinding;
import com.yucheng.smarthealthpro.databinding.ItemImageBinding;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class LifeTipDialogFragment extends DialogFragment {
    private Adapter adapter;
    private DialogLifeTipBinding binding;
    private int currentPosition;
    private int currentType;
    private final List<Integer> texts = new ArrayList();
    ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.yucheng.smarthealthpro.life.LifeTipDialogFragment.1
        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrollStateChanged(int state) {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
        public void onPageSelected(int position) {
            LifeTipDialogFragment.this.currentPosition = position;
            LifeTipDialogFragment.this.binding.tvDesc.setText(((Integer) LifeTipDialogFragment.this.texts.get(position)).intValue());
        }
    };

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogLifeTipBinding dialogLifeTipBindingInflate = DialogLifeTipBinding.inflate(inflater, container, false);
        this.binding = dialogLifeTipBindingInflate;
        return dialogLifeTipBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.mipmap.icon_sos_tip_1));
        arrayList.add(Integer.valueOf(R.mipmap.icon_sos_tip_2));
        arrayList.add(Integer.valueOf(R.mipmap.icon_sos_tip_3));
        arrayList.add(Integer.valueOf(R.mipmap.icon_sos_tip_4));
        this.texts.add(Integer.valueOf(R.string.wisdom_action));
        this.texts.add(Integer.valueOf(R.string.wisdom_action_long_press));
        this.texts.add(Integer.valueOf(R.string.wisdom_action_double_click));
        this.texts.add(Integer.valueOf(R.string.wisdom_action_up_down));
        this.adapter = new Adapter(arrayList);
        this.binding.viewPage.setAdapter(this.adapter);
        this.binding.viewPage.registerOnPageChangeCallback(this.pageChangeCallback);
        this.binding.indicator.attachToViewPager2(this.binding.viewPage);
        initTypeView();
        this.binding.tvKnow.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.life.LifeTipDialogFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                LifeTipDialogFragment.this.dismiss();
            }
        });
    }

    public void initTypeView() {
        DialogLifeTipBinding dialogLifeTipBinding = this.binding;
        if (dialogLifeTipBinding != null) {
            int i2 = this.currentType;
            if (i2 == 1) {
                dialogLifeTipBinding.viewPage.setVisibility(8);
                this.binding.image.setVisibility(0);
                this.binding.indicator.setVisibility(8);
            } else if (i2 == 2) {
                dialogLifeTipBinding.viewPage.setVisibility(0);
                this.binding.image.setVisibility(8);
                this.binding.indicator.setVisibility(0);
            }
        }
    }

    public void showTipType(int type) {
        this.currentType = type;
        initTypeView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.binding.viewPage.unregisterOnPageChangeCallback(this.pageChangeCallback);
        super.onDestroy();
    }

    private static class Adapter extends RecyclerView.Adapter<ImageHolder> {
        private final List<Integer> images;

        public Adapter(List<Integer> images) {
            this.images = images;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ImageHolder holder, int position) {
            holder.bind(this.images.get(position).intValue());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.images.size();
        }

        private static class ImageHolder extends RecyclerView.ViewHolder {
            ItemImageBinding binding;

            public ImageHolder(ItemImageBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind(int res) {
                this.binding.image.setImageResource(res);
            }
        }
    }
}
