package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemDialBinding implements ViewBinding {
    public final ImageView dialItemIvDelete;
    public final ImageView dialItemIvDownDone;
    public final ImageView dialItemIvHead;
    public final ProgressBar dialItemProgressDown;
    public final TextView dialItemTvName;
    public final TextView dialItemTvProgressDown;
    public final TextView dialItemTvState;
    public final TextView dialItemTvUpdate;
    private final LinearLayout rootView;

    private ItemDialBinding(LinearLayout rootView, ImageView dialItemIvDelete, ImageView dialItemIvDownDone, ImageView dialItemIvHead, ProgressBar dialItemProgressDown, TextView dialItemTvName, TextView dialItemTvProgressDown, TextView dialItemTvState, TextView dialItemTvUpdate) {
        this.rootView = rootView;
        this.dialItemIvDelete = dialItemIvDelete;
        this.dialItemIvDownDone = dialItemIvDownDone;
        this.dialItemIvHead = dialItemIvHead;
        this.dialItemProgressDown = dialItemProgressDown;
        this.dialItemTvName = dialItemTvName;
        this.dialItemTvProgressDown = dialItemTvProgressDown;
        this.dialItemTvState = dialItemTvState;
        this.dialItemTvUpdate = dialItemTvUpdate;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ItemDialBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemDialBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_dial, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemDialBinding bind(View rootView) {
        int i2 = R.id.dial_item_iv_delete;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
        if (imageView != null) {
            i2 = R.id.dial_item_iv_down_done;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView2 != null) {
                i2 = R.id.dial_item_iv_head;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView3 != null) {
                    i2 = R.id.dial_item_progress_down;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, i2);
                    if (progressBar != null) {
                        i2 = R.id.dial_item_tv_name;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, i2);
                        if (textView != null) {
                            i2 = R.id.dial_item_tv_progress_down;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                            if (textView2 != null) {
                                i2 = R.id.dial_item_tv_state;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                if (textView3 != null) {
                                    i2 = R.id.dial_item_tv_update;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, i2);
                                    if (textView4 != null) {
                                        return new ItemDialBinding((LinearLayout) rootView, imageView, imageView2, imageView3, progressBar, textView, textView2, textView3, textView4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
