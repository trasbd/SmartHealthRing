package com.yucheng.smarthealthpro.sport.bean;

import com.yucheng.smarthealthpro.sport.SportType;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class SportTabItem implements Serializable {
    public boolean hasStep;
    public int icon;
    public int sportType;
    public String title;
    public int titleId;

    public SportTabItem(int sportType) {
        this.hasStep = false;
        this.sportType = sportType;
        this.title = "";
        init();
    }

    public SportTabItem(String title, int sportType) {
        this.hasStep = false;
        this.title = title;
        this.sportType = sportType;
        init();
    }

    private void init() {
        int[] ids = SportType.getIds(this.sportType);
        this.titleId = ids[0];
        this.icon = ids[1];
        this.hasStep = ids[2] == 1;
    }
}
