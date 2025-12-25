package com.yucheng.smarthealthpro.utils;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/* loaded from: classes5.dex */
public class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) view.getChildAt(0);
        bottomNavigationMenuView.setLabelVisibilityMode(1);
        bottomNavigationMenuView.buildMenuView();
    }
}
