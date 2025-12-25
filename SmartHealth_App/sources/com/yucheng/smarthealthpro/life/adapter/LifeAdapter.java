package com.yucheng.smarthealthpro.life.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.databinding.ItemLifeBinding;
import com.yucheng.smarthealthpro.databinding.ItemLifeHeaderBinding;
import com.yucheng.smarthealthpro.databinding.ItemLifeSpaceBinding;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class LifeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public ArrayList<LifeData> dataList = new ArrayList<>();
    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onChecked(int position, int protocolIndex, boolean isOpen);

        void onClick(int position);

        void onClickViewId(int viewId);
    }

    public void add(LifeData data) {
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        this.dataList.add(data);
    }

    public void addAll(List<LifeData> list) {
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
        this.dataList.addAll(list);
    }

    public LifeData getItem(int position) {
        return this.dataList.get(position);
    }

    public ArrayList<LifeData> getDatas() {
        return this.dataList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new SpaceHolder(ItemLifeSpaceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false).getRoot());
        }
        if (viewType == 2) {
            ItemLifeHeaderBinding itemLifeHeaderBindingInflate = ItemLifeHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            itemLifeHeaderBindingInflate.ivTip.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.life.adapter.LifeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    LifeAdapter.this.onItemClickListener.onClickViewId(v.getId());
                }
            });
            itemLifeHeaderBindingInflate.tvDesc.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.life.adapter.LifeAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    LifeAdapter.this.onItemClickListener.onClickViewId(v.getId());
                }
            });
            final LifeHeadHolder lifeHeadHolder = new LifeHeadHolder(itemLifeHeaderBindingInflate.getRoot());
            lifeHeadHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.life.adapter.LifeAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (LifeAdapter.this.onItemClickListener != null) {
                        LifeAdapter.this.onItemClickListener.onClick(lifeHeadHolder.getAdapterPosition());
                    }
                }
            });
            return lifeHeadHolder;
        }
        return new LifeHolder(ItemLifeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false).getRoot());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.dataList.get(position).viewType;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LifeHolder) {
            LifeHolder lifeHolder = (LifeHolder) holder;
            lifeHolder.onBind(this.dataList.get(position));
            lifeHolder.binding.switchBar.setChecked(this.dataList.get(position).opened);
            lifeHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.life.adapter.LifeAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (LifeAdapter.this.onItemClickListener != null) {
                        LifeAdapter.this.onItemClickListener.onClick(holder.getAdapterPosition());
                    }
                }
            });
            lifeHolder.binding.switchBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.yucheng.smarthealthpro.life.adapter.LifeAdapter.5
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (buttonView.isPressed() && LifeAdapter.this.onItemClickListener != null) {
                        LifeAdapter.this.onItemClickListener.onChecked(holder.getAdapterPosition(), LifeAdapter.this.getItem(holder.getAdapterPosition()).protocolIndex, isChecked);
                    }
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<LifeData> arrayList = this.dataList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void setOnItemClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
