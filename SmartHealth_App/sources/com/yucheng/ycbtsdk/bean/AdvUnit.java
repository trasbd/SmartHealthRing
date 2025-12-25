package com.yucheng.ycbtsdk.bean;

/* loaded from: classes5.dex */
public class AdvUnit {
    private int len;
    private int type;
    private byte[] value;

    public AdvUnit(int i2, int i3, byte[] bArr) {
        this.len = i2;
        this.type = i3;
        this.value = bArr;
    }

    public int getLen() {
        return this.len;
    }

    public int getType() {
        return this.type;
    }

    public byte[] getValue() {
        return this.value;
    }

    public void setLen(int i2) {
        this.len = i2;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setValue(byte[] bArr) {
        this.value = bArr;
    }
}
