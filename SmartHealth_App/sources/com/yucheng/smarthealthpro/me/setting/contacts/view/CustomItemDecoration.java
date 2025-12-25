package com.yucheng.smarthealthpro.me.setting.contacts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.me.setting.contacts.bean.MyContacts;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.ColorUtil;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.DpUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private static final int dividerHeight = 80;
    private List<MyContacts> mBeans;
    private final Rect mBounds = new Rect();
    private Context mContext;
    private Paint mPaint;
    private String tagsStr;

    public void setDatas(List<MyContacts> mBeans, String tagsStr) {
        this.mBeans = mBeans;
        this.tagsStr = tagsStr;
    }

    public CustomItemDecoration(Context mContext) {
        this.mContext = mContext;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        canvas.save();
        int childCount = parent.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = parent.getChildAt(i2);
            int viewLayoutPosition = ((RecyclerView.LayoutParams) childAt.getLayoutParams()).getViewLayoutPosition();
            List<MyContacts> list = this.mBeans;
            if (list != null && list.size() != 0 && this.mBeans.size() > viewLayoutPosition && viewLayoutPosition >= 0) {
                if (viewLayoutPosition == 0) {
                    drawTitleBar(canvas, parent, childAt, this.mBeans.get(viewLayoutPosition), this.tagsStr.indexOf(this.mBeans.get(viewLayoutPosition).getNote()));
                } else if (viewLayoutPosition > 0 && !TextUtils.isEmpty(this.mBeans.get(viewLayoutPosition).getNote()) && !this.mBeans.get(viewLayoutPosition).getNote().equals(this.mBeans.get(viewLayoutPosition - 1).getNote())) {
                    drawTitleBar(canvas, parent, childAt, this.mBeans.get(viewLayoutPosition), this.tagsStr.indexOf(this.mBeans.get(viewLayoutPosition).getNote()));
                }
            }
        }
        canvas.restore();
    }

    private void drawTitleBar(Canvas canvas, RecyclerView parent, View child, MyContacts bean, int position) {
        int width = parent.getWidth();
        parent.getDecoratedBoundsWithMargins(child, this.mBounds);
        int i2 = this.mBounds.top;
        int iRound = this.mBounds.top + Math.round(ViewCompat.getTranslationY(child));
        this.mPaint.setColor(-1);
        canvas.drawRect(0.0f, i2, width, iRound + 80, this.mPaint);
        ColorUtil.setPaintColor(this.mPaint, position);
        this.mPaint.setTextSize(40.0f);
        canvas.drawCircle(DpUtil.dp2px(this.mContext, 42.5f), iRound + 40, 35.0f, this.mPaint);
        this.mPaint.setColor(-1);
        canvas.drawText(bean.getNote(), DpUtil.dp2px(this.mContext, 42.5f), iRound + 54, this.mPaint);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int iFindFirstVisibleItemPosition = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        List<MyContacts> list = this.mBeans;
        if (list == null || list.size() == 0 || this.mBeans.size() <= iFindFirstVisibleItemPosition || iFindFirstVisibleItemPosition < 0) {
            return;
        }
        int paddingTop = parent.getPaddingTop();
        this.mPaint.setColor(-1);
        canvas.drawRect(parent.getLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + 80, this.mPaint);
        ColorUtil.setPaintColor(this.mPaint, this.tagsStr.indexOf(this.mBeans.get(iFindFirstVisibleItemPosition).getNote()));
        this.mPaint.setTextSize(40.0f);
        canvas.drawCircle(DpUtil.dp2px(this.mContext, 42.5f), paddingTop + 40, 35.0f, this.mPaint);
        this.mPaint.setColor(-1);
        canvas.drawText(this.mBeans.get(iFindFirstVisibleItemPosition).getNote(), DpUtil.dp2px(this.mContext, 42.5f), paddingTop + 54, this.mPaint);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        List<MyContacts> list = this.mBeans;
        if (list == null || list.size() == 0 || this.mBeans.size() <= childAdapterPosition || childAdapterPosition < 0) {
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }
        if (childAdapterPosition == 0) {
            outRect.set(0, 80, 0, 0);
        } else {
            if (childAdapterPosition <= 0 || TextUtils.isEmpty(this.mBeans.get(childAdapterPosition).getNote()) || this.mBeans.get(childAdapterPosition).getNote().equals(this.mBeans.get(childAdapterPosition - 1).getNote())) {
                return;
            }
            outRect.set(0, 80, 0, 0);
        }
    }
}
