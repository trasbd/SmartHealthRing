package com.yucheng.smarthealthpro.me.setting.contacts.bean;

/* loaded from: classes5.dex */
public class MyContacts {
    public boolean isChecked = false;
    public String name;
    public String note;
    public String phone;

    public String toString() {
        return "MyContacts{name='" + this.name + "', phone='" + this.phone + "', note='" + this.note + "'}";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
