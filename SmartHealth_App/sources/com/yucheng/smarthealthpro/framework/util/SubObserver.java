package com.yucheng.smarthealthpro.framework.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/* loaded from: classes4.dex */
public class SubObserver extends Observable {
    public static SubObserver observer;
    private Map<String, Observer> list = new HashMap();

    public static SubObserver getInstance() {
        if (observer == null) {
            observer = new SubObserver();
        }
        return observer;
    }

    private SubObserver() {
    }

    public void addObs(Observer observer2) {
        this.list.put(observer2.getClass().getName(), observer2);
    }

    public void delObs(Observer observer2) {
        this.list.remove(observer2.getClass().getName());
    }

    public void putClass(Class<?> cls) {
        try {
            this.list.put(cls.getName(), (Observer) cls.newInstance());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void nodifyObservers(Object obj) {
        Iterator<Observer> it2 = this.list.values().iterator();
        while (it2.hasNext()) {
            it2.next().update(this, obj);
        }
    }

    public void nodifyObservers(Class<?> cls, Object obj) {
        Observer observer2 = this.list.get(cls.getName());
        if (observer2 != null) {
            observer2.update(this, obj);
        }
    }
}
