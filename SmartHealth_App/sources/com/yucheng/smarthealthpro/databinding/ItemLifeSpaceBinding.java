package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ItemLifeSpaceBinding implements ViewBinding {
    private final View rootView;

    private ItemLifeSpaceBinding(View rootView) {
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static ItemLifeSpaceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemLifeSpaceBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.item_life_space, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ItemLifeSpaceBinding bind(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemLifeSpaceBinding(rootView);
    }
}
