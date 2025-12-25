package com.yucheng.smarthealthpro.utils;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class CommonAction {
    private static CommonAction instance;
    private List<Activity> AllActivitites = new ArrayList();

    public static synchronized CommonAction getInstance() {
        if (instance == null) {
            instance = new CommonAction();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        this.AllActivitites.add(activity);
    }

    public void subActivity(Activity activity) {
        this.AllActivitites.remove(activity);
    }

    public void OutSign() {
        for (Activity activity : this.AllActivitites) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public boolean getCurrActivity(Class activity) {
        List<Activity> list = this.AllActivitites;
        if (list == null || list.size() <= 0 || activity == null) {
            return false;
        }
        return activity.getName().equals(this.AllActivitites.get(r0.size() - 1).getClass().getName());
    }

    public void finish() {
        for (Activity activity : this.AllActivitites) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
