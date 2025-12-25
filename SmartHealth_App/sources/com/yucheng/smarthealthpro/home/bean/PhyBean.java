package com.yucheng.smarthealthpro.home.bean;

import com.yucheng.smarthealthpro.database.room.bean.Physiotherapy;
import java.util.List;

/* loaded from: classes5.dex */
public class PhyBean {
    private List<Physiotherapy> list;
    public int level1Count = 0;
    public int level2Count = 0;
    public int level3Count = 0;
    public int level4Count = 0;
    public int level1Total = 0;
    public int level2Total = 0;
    public int level3Total = 0;
    public int level4Total = 0;

    public PhyBean(List<Physiotherapy> list) {
        this.list = list;
    }

    public void updateData() {
        if (this.list != null) {
            for (int i2 = 0; i2 < this.list.size(); i2++) {
                Physiotherapy physiotherapy = this.list.get(i2);
                int powerLevel = physiotherapy.getPowerLevel();
                if (powerLevel == 0) {
                    this.level1Total += physiotherapy.getDuration();
                    this.level1Count++;
                } else if (powerLevel == 1) {
                    this.level2Total += physiotherapy.getDuration();
                    this.level2Count++;
                } else if (powerLevel == 2) {
                    this.level3Total += physiotherapy.getDuration();
                    this.level3Count++;
                } else if (powerLevel == 3) {
                    this.level4Total += physiotherapy.getDuration();
                    this.level4Count++;
                }
            }
        }
    }

    public List<Physiotherapy> getList() {
        return this.list;
    }
}
