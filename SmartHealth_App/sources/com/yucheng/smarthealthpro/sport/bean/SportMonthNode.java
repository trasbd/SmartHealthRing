package com.yucheng.smarthealthpro.sport.bean;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.entity.node.NodeFooterImp;
import java.util.List;

/* loaded from: classes5.dex */
public class SportMonthNode extends BaseExpandNode implements NodeFooterImp {
    private List<BaseNode> childNode;
    private String title;

    @Override // com.chad.library.adapter.base.entity.node.NodeFooterImp
    public BaseNode getFooterNode() {
        return null;
    }

    public SportMonthNode(List<BaseNode> childNode, String title) {
        this.childNode = childNode;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.chad.library.adapter.base.entity.node.BaseNode
    public List<BaseNode> getChildNode() {
        return this.childNode;
    }
}
