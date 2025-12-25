package com.yucheng.smarthealthpro.sport.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/* loaded from: classes5.dex */
public class TabEntity implements CustomTabEntity {
    public int selectedIcon;
    public String title;
    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override // com.flyco.tablayout.listener.CustomTabEntity
    public String getTabTitle() {
        return this.title;
    }

    @Override // com.flyco.tablayout.listener.CustomTabEntity
    public int getTabSelectedIcon() {
        return this.selectedIcon;
    }

    @Override // com.flyco.tablayout.listener.CustomTabEntity
    public int getTabUnselectedIcon() {
        return this.unSelectedIcon;
    }
}
