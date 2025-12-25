package com.yucheng.smarthealthpro.framework.catchexception;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.maps.android.BuildConfig;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
final class SharedPreferencesCollector {
    private final Context mContext;
    private String[] mSharedPrefIds;

    public SharedPreferencesCollector(Context context, String[] strArr) {
        this.mContext = context;
        this.mSharedPrefIds = strArr;
    }

    public String collect() {
        StringBuilder sb = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        treeMap.put("default", PreferenceManager.getDefaultSharedPreferences(this.mContext));
        String[] strArr = this.mSharedPrefIds;
        if (strArr != null) {
            for (String str : strArr) {
                treeMap.put("default", this.mContext.getSharedPreferences(str, 0));
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String str2 = (String) entry.getKey();
            Map<String, ?> all = ((SharedPreferences) entry.getValue()).getAll();
            if (all.isEmpty()) {
                sb.append(str2).append("=empty\\n");
            } else {
                for (Map.Entry<String, ?> entry2 : all.entrySet()) {
                    Object value = entry2.getValue();
                    sb.append(str2).append(".").append(entry2.getKey()).append("=");
                    sb.append(value == null ? BuildConfig.TRAVIS : value.toString()).append("\\n");
                }
                sb.append("\\n");
            }
        }
        return sb.toString();
    }
}
