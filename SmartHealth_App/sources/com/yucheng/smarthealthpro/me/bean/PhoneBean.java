package com.yucheng.smarthealthpro.me.bean;

/* loaded from: classes5.dex */
public class PhoneBean {
    public String message;
    public String name;
    public String phone;

    public PhoneBean(String name, String phone, String message) {
        this.name = name;
        this.phone = phone;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
