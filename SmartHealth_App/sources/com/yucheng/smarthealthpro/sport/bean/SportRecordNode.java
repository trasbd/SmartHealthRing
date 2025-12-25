package com.yucheng.smarthealthpro.sport.bean;

import com.chad.library.adapter.base.entity.node.BaseNode;
import java.util.List;

/* loaded from: classes5.dex */
public class SportRecordNode extends BaseNode {
    private float distance;
    private float keepTime;
    private long time;
    private int type;

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return null;
    }

    public SportRecordNode(int type, long time, float distance, float keepTime) {
        this.type = type;
        this.time = time;
        this.distance = distance;
        this.keepTime = keepTime;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getKeepTime() {
        return this.keepTime;
    }

    public void setKeepTime(float keepTime) {
        this.keepTime = keepTime;
    }
}
