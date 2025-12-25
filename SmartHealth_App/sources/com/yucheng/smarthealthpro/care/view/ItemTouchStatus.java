package com.yucheng.smarthealthpro.care.view;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public interface ItemTouchStatus {
    boolean onItemRemove(int position);

    void onSaveItemStatus(RecyclerView.ViewHolder viewHolder);
}
