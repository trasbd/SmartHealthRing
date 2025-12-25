package com.yucheng.ycbtsdk.bean;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class ContactsBean implements Serializable {
    public short id;
    public String name;
    public String number;

    public ContactsBean() {
    }

    public String toString() {
        return "ContactsBean{id=" + ((int) this.id) + ", name='" + this.name + "', number='" + this.number + "'}";
    }

    public ContactsBean(short s, String str, String str2) {
        this.id = s;
        this.name = str;
        this.number = str2;
    }
}
