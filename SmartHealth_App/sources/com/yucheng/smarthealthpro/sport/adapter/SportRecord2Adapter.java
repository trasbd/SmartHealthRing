package com.yucheng.smarthealthpro.sport.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.sport.bean.SportRecord2;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class SportRecord2Adapter extends RecyclerView.Adapter<SportRecord2Holder> {
    private ArrayList<SportRecord2> dataList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setDataList(ArrayList<SportRecord2> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<SportRecord2> getDataList() {
        return this.dataList;
    }

    public SportRecord2 getData(int position) {
        return this.dataList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SportRecord2Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new SportRecord2TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport_record_title, parent, false));
        }
        return new SportRecord2ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport_his_run, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final SportRecord2Holder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportRecord2Adapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (SportRecord2Adapter.this.onItemClickListener != null) {
                    SportRecord2Adapter.this.onItemClickListener.onItemClick(v, holder.getAdapterPosition());
                }
            }
        });
        holder.bind(this.dataList.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.dataList.get(position).getViewType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<SportRecord2> arrayList = this.dataList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void removeAt(int position) {
        if (position >= this.dataList.size()) {
            return;
        }
        this.dataList.remove(position);
        notifyItemRemoved(position);
        compatibilityDataSizeChanged(0);
        notifyItemRangeChanged(position, this.dataList.size() - position);
    }

    private void compatibilityDataSizeChanged(int size) {
        if (this.dataList.size() == size) {
            notifyDataSetChanged();
        }
    }
}
