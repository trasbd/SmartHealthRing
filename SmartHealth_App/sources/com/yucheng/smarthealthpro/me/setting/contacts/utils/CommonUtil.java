package com.yucheng.smarthealthpro.me.setting.contacts.utils;

import android.view.View;
import com.github.promeg.pinyinhelper.Pinyin;
import com.yucheng.smarthealthpro.me.setting.contacts.bean.MyContacts;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes5.dex */
public class CommonUtil {
    public static void measureWidthAndHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    public static void sortData(List<MyContacts> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            MyContacts myContacts = list.get(i2);
            String strSubstring = Pinyin.toPinyin(myContacts.getName().substring(0, 1).charAt(0)).substring(0, 1);
            if (strSubstring.matches("[A-Z]")) {
                myContacts.setNote(strSubstring);
            } else {
                myContacts.setNote(MqttTopic.MULTI_LEVEL_WILDCARD);
            }
        }
        Collections.sort(list, new Comparator<MyContacts>() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.utils.CommonUtil.1
            @Override // java.util.Comparator
            public int compare(MyContacts o1, MyContacts o2) {
                if (o1.getNote().equals(o2.getNote())) {
                    return 0;
                }
                if (MqttTopic.MULTI_LEVEL_WILDCARD.equals(o1.getNote())) {
                    return 1;
                }
                if (MqttTopic.MULTI_LEVEL_WILDCARD.equals(o2.getNote())) {
                    return -1;
                }
                return o1.getNote().compareTo(o2.getNote());
            }
        });
    }

    public static String getTags(List<MyContacts> beans) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < beans.size(); i2++) {
            if (!sb.toString().contains(beans.get(i2).getNote())) {
                sb.append(beans.get(i2).getNote());
            }
        }
        return sb.toString();
    }
}
