package com.yucheng.smarthealthpro.sport.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.sport.bean.SportMonthRecordNodeBean;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.DataSyncUtils;
import java.util.Collection;

/* loaded from: classes5.dex */
public class SportMonthNodeAdapter extends BaseQuickAdapter<SportMonthRecordNodeBean, BaseViewHolder> {
    private boolean isAprOpenList;
    private boolean isAugOpenList;
    private boolean isDecOpenList;
    private boolean isFebOpenList;
    private boolean isJanOpenList;
    private boolean isJulOpenList;
    private boolean isJunOpenList;
    private boolean isMarOpenList;
    private boolean isMayOpenList;
    private boolean isNovOpenList;
    private boolean isOctOpenList;
    private boolean isSepOpenList;
    private SportRecoreNodeAdapter mAprAdapter;
    private LinearLayout mAprLinearLayout;
    private OnItemMenuClickListener mAprMenuItemClickListener;
    private SwipeMenuCreator mAprSwipeMenuCreator;
    private SwipeRecyclerView mAprSwipeRecyclerView;
    private SportRecoreNodeAdapter mAugAdapter;
    private LinearLayout mAugLinearLayout;
    private OnItemMenuClickListener mAugMenuItemClickListener;
    private SwipeMenuCreator mAugSwipeMenuCreator;
    private SwipeRecyclerView mAugSwipeRecyclerView;
    private Context mContext;
    private SportRecoreNodeAdapter mDecAdapter;
    private LinearLayout mDecLinearLayout;
    private OnItemMenuClickListener mDecMenuItemClickListener;
    private SwipeMenuCreator mDecSwipeMenuCreator;
    private SwipeRecyclerView mDecSwipeRecyclerView;
    private SportRecoreNodeAdapter mFebAdapter;
    private LinearLayout mFebLinearLayout;
    private OnItemMenuClickListener mFebMenuItemClickListener;
    private SwipeMenuCreator mFebSwipeMenuCreator;
    private SwipeRecyclerView mFebSwipeRecyclerView;
    private SportRecoreNodeAdapter mJanAdapter;
    private LinearLayout mJanLinearLayout;
    private OnItemMenuClickListener mJanMenuItemClickListener;
    private SwipeMenuCreator mJanSwipeMenuCreator;
    private SwipeRecyclerView mJanSwipeRecyclerView;
    private SportRecoreNodeAdapter mJulAdapter;
    private LinearLayout mJulLinearLayout;
    private OnItemMenuClickListener mJulMenuItemClickListener;
    private SwipeMenuCreator mJulSwipeMenuCreator;
    private SwipeRecyclerView mJulSwipeRecyclerView;
    private SportRecoreNodeAdapter mJunAdapter;
    private LinearLayout mJunLinearLayout;
    private OnItemMenuClickListener mJunMenuItemClickListener;
    private SwipeMenuCreator mJunSwipeMenuCreator;
    private SwipeRecyclerView mJunSwipeRecyclerView;
    private SportRecoreNodeAdapter mMarAdapter;
    private LinearLayout mMarLinearLayout;
    private OnItemMenuClickListener mMarMenuItemClickListener;
    private SwipeMenuCreator mMarSwipeMenuCreator;
    private SwipeRecyclerView mMarSwipeRecyclerView;
    private SportRecoreNodeAdapter mMayAdapter;
    private LinearLayout mMayLinearLayout;
    private OnItemMenuClickListener mMayMenuItemClickListener;
    private SwipeMenuCreator mMaySwipeMenuCreator;
    private SwipeRecyclerView mMaySwipeRecyclerView;
    private SportRecoreNodeAdapter mNovAdapter;
    private LinearLayout mNovLinearLayout;
    private OnItemMenuClickListener mNovMenuItemClickListener;
    private SwipeMenuCreator mNovSwipeMenuCreator;
    private SwipeRecyclerView mNovSwipeRecyclerView;
    private SportRecoreNodeAdapter mOctAdapter;
    private LinearLayout mOctLinearLayout;
    private OnItemMenuClickListener mOctMenuItemClickListener;
    private SwipeMenuCreator mOctSwipeMenuCreator;
    private SwipeRecyclerView mOctSwipeRecyclerView;
    private SportRecoreNodeAdapter mSepAdapter;
    private LinearLayout mSepLinearLayout;
    private OnItemMenuClickListener mSepMenuItemClickListener;
    private SwipeMenuCreator mSepSwipeMenuCreator;
    private SwipeRecyclerView mSepSwipeRecyclerView;
    public boolean showDel;

    public SportMonthNodeAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.isDecOpenList = false;
        this.mJanSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.13
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mJanMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.14
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mJanAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mJanAdapter.remove(position);
                    SportMonthNodeAdapter.this.mJanAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mJanAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mJanLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mFebSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.15
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mFebMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.16
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mFebAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mFebAdapter.remove(position);
                    SportMonthNodeAdapter.this.mFebAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mFebAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mFebLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mMarSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.17
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mMarMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.18
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mMarAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mMarAdapter.remove(position);
                    SportMonthNodeAdapter.this.mMarAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mMarAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mMarLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mAprSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.19
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mAprMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.20
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mAprAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mAprAdapter.remove(position);
                    SportMonthNodeAdapter.this.mAprAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mAprAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mAprLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mMaySwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.21
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mMayMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.22
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mMayAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mMayAdapter.remove(position);
                    SportMonthNodeAdapter.this.mMayAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mMayAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mMayLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mJunSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.23
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mJunMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.24
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mJunAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mJunAdapter.remove(position);
                    SportMonthNodeAdapter.this.mJunAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mJunAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mJunLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mJulSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.25
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mJulMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.26
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mJulAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mJulAdapter.remove(position);
                    SportMonthNodeAdapter.this.mJulAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mJulAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mJulLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mAugSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.27
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mAugMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.28
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mAugAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mAugAdapter.remove(position);
                    SportMonthNodeAdapter.this.mAugAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mAugAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mAugLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mSepSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.29
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mSepMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.30
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mSepAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mSepAdapter.remove(position);
                    SportMonthNodeAdapter.this.mSepAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mSepAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mSepLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mOctSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.31
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mOctMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.32
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mOctAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mOctAdapter.remove(position);
                    SportMonthNodeAdapter.this.mOctAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mOctAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mOctLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mNovSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.33
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mNovMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.34
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mNovAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mNovAdapter.remove(position);
                    SportMonthNodeAdapter.this.mNovAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mNovAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mNovLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mDecSwipeMenuCreator = new SwipeMenuCreator() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.35
            @Override // com.yanzhenjie.recyclerview.SwipeMenuCreator
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) throws Resources.NotFoundException {
                swipeRightMenu.addMenuItem(new SwipeMenuItem(SportMonthNodeAdapter.this.mContext).setBackground(R.drawable.selector_red).setText(SportMonthNodeAdapter.this.mContext.getString(R.string.delete)).setTextColor(-1).setWidth(SportMonthNodeAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.item_width)).setHeight(-1));
            }
        };
        this.mDecMenuItemClickListener = new OnItemMenuClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.36
            @Override // com.yanzhenjie.recyclerview.OnItemMenuClickListener
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                menuBridge.getPosition();
                if (direction == -1) {
                    DataSyncUtils.INSTANCE.getInstance(MyApplication.sInstance).deleteSportRecord(SportMonthNodeAdapter.this.mDecAdapter.getItem(position).getBeginDate());
                    SportMonthNodeAdapter.this.mDecAdapter.remove(position);
                    SportMonthNodeAdapter.this.mDecAdapter.notifyDataSetChanged();
                    if (SportMonthNodeAdapter.this.mDecAdapter.getItemCount() == 0) {
                        SportMonthNodeAdapter.this.mDecLinearLayout.setVisibility(8);
                    }
                }
            }
        };
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, SportMonthRecordNodeBean hisSearch) {
        holder.getLayoutPosition();
        if (hisSearch != null) {
            holder.setText(R.id.tv_month, Constant.getMonth(this.mContext.getApplicationContext(), hisSearch.getMonth())).setImageResource(R.id.iv_subordinate, R.mipmap.list_ic_arrow_n);
            String month = hisSearch.getMonth();
            month.hashCode();
            switch (month) {
                case "01":
                    this.mJanSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mJanLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mJanSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isJanOpenList) {
                                imageView.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mJanSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isJanOpenList = true;
                            } else {
                                imageView.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mJanSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isJanOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mJanAdapter = sportRecoreNodeAdapter;
                    setAdapter(sportRecoreNodeAdapter, this.mJanSwipeRecyclerView, hisSearch, this.mJanSwipeMenuCreator, this.mJanMenuItemClickListener);
                    break;
                case "02":
                    this.mFebSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mFebLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mFebSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView2 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isFebOpenList) {
                                imageView2.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mFebSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isFebOpenList = true;
                            } else {
                                imageView2.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mFebSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isFebOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter2 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mFebAdapter = sportRecoreNodeAdapter2;
                    setAdapter(sportRecoreNodeAdapter2, this.mFebSwipeRecyclerView, hisSearch, this.mFebSwipeMenuCreator, this.mFebMenuItemClickListener);
                    break;
                case "03":
                    this.mMarSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mMarLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mMarSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView3 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isMarOpenList) {
                                imageView3.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mMarSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isMarOpenList = true;
                            } else {
                                imageView3.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mMarSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isMarOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter3 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mMarAdapter = sportRecoreNodeAdapter3;
                    setAdapter(sportRecoreNodeAdapter3, this.mMarSwipeRecyclerView, hisSearch, this.mMarSwipeMenuCreator, this.mMarMenuItemClickListener);
                    break;
                case "04":
                    this.mAprSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mAprLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mAprSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView4 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isAprOpenList) {
                                imageView4.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mAprSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isAprOpenList = true;
                            } else {
                                imageView4.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mAprSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isAprOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter4 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mAprAdapter = sportRecoreNodeAdapter4;
                    setAdapter(sportRecoreNodeAdapter4, this.mAprSwipeRecyclerView, hisSearch, this.mAprSwipeMenuCreator, this.mAprMenuItemClickListener);
                    break;
                case "05":
                    this.mMaySwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mMayLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mMaySwipeRecyclerView.setVisibility(8);
                    final ImageView imageView5 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isMayOpenList) {
                                imageView5.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mMaySwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isMayOpenList = true;
                            } else {
                                imageView5.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mMaySwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isMayOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter5 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mMayAdapter = sportRecoreNodeAdapter5;
                    setAdapter(sportRecoreNodeAdapter5, this.mMaySwipeRecyclerView, hisSearch, this.mMaySwipeMenuCreator, this.mMayMenuItemClickListener);
                    break;
                case "06":
                    this.mJunSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mJunLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mJunSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView6 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView6.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.6
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isJunOpenList) {
                                imageView6.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mJunSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isJunOpenList = true;
                            } else {
                                imageView6.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mJunSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isJunOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter6 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mJunAdapter = sportRecoreNodeAdapter6;
                    setAdapter(sportRecoreNodeAdapter6, this.mJunSwipeRecyclerView, hisSearch, this.mJunSwipeMenuCreator, this.mJunMenuItemClickListener);
                    break;
                case "07":
                    this.mJulSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mJulLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mJulSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView7 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView7.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.7
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isJulOpenList) {
                                imageView7.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mJulSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isJulOpenList = true;
                            } else {
                                imageView7.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mJulSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isJulOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter7 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mJulAdapter = sportRecoreNodeAdapter7;
                    setAdapter(sportRecoreNodeAdapter7, this.mJulSwipeRecyclerView, hisSearch, this.mJulSwipeMenuCreator, this.mJulMenuItemClickListener);
                    break;
                case "08":
                    this.mAugSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mAugLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mAugSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView8 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView8.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.8
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isAugOpenList) {
                                imageView8.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mAugSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isAugOpenList = true;
                            } else {
                                imageView8.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mAugSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isAugOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter8 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mAugAdapter = sportRecoreNodeAdapter8;
                    setAdapter(sportRecoreNodeAdapter8, this.mAugSwipeRecyclerView, hisSearch, this.mAugSwipeMenuCreator, this.mAugMenuItemClickListener);
                    break;
                case "09":
                    this.mSepSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mSepLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mSepSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView9 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView9.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.9
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isSepOpenList) {
                                imageView9.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mSepSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isSepOpenList = true;
                            } else {
                                imageView9.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mSepSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isSepOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter9 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mSepAdapter = sportRecoreNodeAdapter9;
                    setAdapter(sportRecoreNodeAdapter9, this.mSepSwipeRecyclerView, hisSearch, this.mSepSwipeMenuCreator, this.mSepMenuItemClickListener);
                    break;
                case "10":
                    this.mOctSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mOctLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mOctSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView10 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView10.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.10
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isOctOpenList) {
                                imageView10.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mOctSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isOctOpenList = true;
                            } else {
                                imageView10.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mOctSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isOctOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter10 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mOctAdapter = sportRecoreNodeAdapter10;
                    setAdapter(sportRecoreNodeAdapter10, this.mOctSwipeRecyclerView, hisSearch, this.mOctSwipeMenuCreator, this.mOctMenuItemClickListener);
                    break;
                case "11":
                    this.mNovSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mNovLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mNovSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView11 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView11.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.11
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isNovOpenList) {
                                imageView11.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mNovSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isNovOpenList = true;
                            } else {
                                imageView11.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mNovSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isNovOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter11 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mNovAdapter = sportRecoreNodeAdapter11;
                    setAdapter(sportRecoreNodeAdapter11, this.mNovSwipeRecyclerView, hisSearch, this.mNovSwipeMenuCreator, this.mNovMenuItemClickListener);
                    break;
                case "12":
                    this.mDecSwipeRecyclerView = (SwipeRecyclerView) holder.itemView.findViewById(R.id.rv_record);
                    this.mDecLinearLayout = (LinearLayout) holder.itemView.findViewById(R.id.ll_sport_month_node);
                    this.mDecSwipeRecyclerView.setVisibility(8);
                    final ImageView imageView12 = (ImageView) holder.itemView.findViewById(R.id.iv_subordinate);
                    imageView12.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.sport.adapter.SportMonthNodeAdapter.12
                        @Override // android.view.View.OnClickListener
                        public void onClick(View v) {
                            if (!SportMonthNodeAdapter.this.isDecOpenList) {
                                imageView12.setImageResource(R.mipmap.list_ic_arrow_s);
                                SportMonthNodeAdapter.this.mDecSwipeRecyclerView.setVisibility(0);
                                SportMonthNodeAdapter.this.isDecOpenList = true;
                            } else {
                                imageView12.setImageResource(R.mipmap.list_ic_arrow_n);
                                SportMonthNodeAdapter.this.mDecSwipeRecyclerView.setVisibility(8);
                                SportMonthNodeAdapter.this.isDecOpenList = false;
                            }
                        }
                    });
                    SportRecoreNodeAdapter sportRecoreNodeAdapter12 = new SportRecoreNodeAdapter(R.layout.item_sport_his_list, this.mContext);
                    this.mDecAdapter = sportRecoreNodeAdapter12;
                    setAdapter(sportRecoreNodeAdapter12, this.mDecSwipeRecyclerView, hisSearch, this.mDecSwipeMenuCreator, this.mDecMenuItemClickListener);
                    break;
            }
        }
    }

    private void setAdapter(SportRecoreNodeAdapter adapter, SwipeRecyclerView mSwipeRecyclerView, SportMonthRecordNodeBean hisSearch, SwipeMenuCreator swipeMenuCreator, OnItemMenuClickListener onItemMenuClickListener) {
        mSwipeRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        mSwipeRecyclerView.setItemViewSwipeEnabled(false);
        mSwipeRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mSwipeRecyclerView.setOnItemMenuClickListener(onItemMenuClickListener);
        adapter.addData((Collection) hisSearch.getData());
        mSwipeRecyclerView.setAdapter(adapter);
    }
}
