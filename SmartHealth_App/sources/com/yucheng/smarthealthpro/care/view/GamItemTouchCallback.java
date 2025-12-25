package com.yucheng.smarthealthpro.care.view;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public class GamItemTouchCallback extends ItemTouchHelper.Callback {
    private int mCurrentScrollX;
    private int mCurrentScrollXWhenInactive;
    private final int mDefaultScrollX;
    private float mDx;
    private boolean mFirstInactive;
    private float mInitXWhenInactive;
    private final ItemTouchStatus mItemTouchStatus;
    private RecyclerView.ViewHolder mViewHolder;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public float getSwipeEscapeVelocity(float defaultValue) {
        return 2.1474836E9f;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return 2.1474836E9f;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

    public GamItemTouchCallback(ItemTouchStatus itemTouchStatus, int defaultScrollX) {
        this.mItemTouchStatus = itemTouchStatus;
        this.mDefaultScrollX = defaultScrollX;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(3, 12);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas c2, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        this.mViewHolder = viewHolder;
        this.mDx = dX;
        if (dX == 0.0f) {
            this.mCurrentScrollX = viewHolder.itemView.getScrollX();
            this.mFirstInactive = true;
        }
        if (isCurrentlyActive) {
            viewHolder.itemView.scrollTo(this.mCurrentScrollX + ((int) (-dX)), 0);
        } else {
            if (this.mFirstInactive) {
                this.mFirstInactive = false;
                this.mCurrentScrollXWhenInactive = viewHolder.itemView.getScrollX();
                this.mInitXWhenInactive = dX;
            }
            if (viewHolder.itemView.getScrollX() >= this.mDefaultScrollX) {
                viewHolder.itemView.scrollTo(Math.max(this.mCurrentScrollX + ((int) (-dX)), this.mDefaultScrollX), 0);
            } else {
                viewHolder.itemView.scrollTo((int) ((this.mCurrentScrollXWhenInactive * dX) / this.mInitXWhenInactive), 0);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.care.view.GamItemTouchCallback.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Log.i("AAAAAA", "----");
                GamItemTouchCallback.this.mViewHolder.itemView.scrollTo(Math.max(0, 0), 0);
            }
        });
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder.itemView.getScrollX() > this.mDefaultScrollX) {
            viewHolder.itemView.scrollTo(this.mDefaultScrollX, 0);
        } else if (viewHolder.itemView.getScrollX() < 0) {
            viewHolder.itemView.scrollTo(0, 0);
        }
        this.mItemTouchStatus.onSaveItemStatus(viewHolder);
    }
}
