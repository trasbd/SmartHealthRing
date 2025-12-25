package com.yucheng.smarthealthpro.me.setting.contacts.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.yucheng.smarthealthpro.me.setting.contacts.bean.MyContacts;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class ContactUtils {

    public static abstract class ContactResponse {
        protected abstract void onContactResponse(ArrayList<MyContacts> contacts);

        protected abstract void onFinish();
    }

    public static ArrayList<MyContacts> getAllContacts(Context context, ContactResponse contactResponse) {
        ArrayList<MyContacts> arrayList = new ArrayList<>();
        Cursor cursorQuery = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursorQuery != null && cursorQuery.moveToNext()) {
            MyContacts myContacts = new MyContacts();
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("_id"));
            myContacts.name = cursorQuery.getString(cursorQuery.getColumnIndex("display_name"));
            Cursor cursorQuery2 = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id=" + string, null, null);
            while (true) {
                if (cursorQuery2 == null || !cursorQuery2.moveToNext()) {
                    break;
                }
                String string2 = cursorQuery2.getString(cursorQuery2.getColumnIndex("data1"));
                if (string2 != null && !"".equals(string2.trim())) {
                    String strReplace = string2.replace("-", "").replace(StringUtils.SPACE, "");
                    myContacts.phone = strReplace;
                    if (myContacts.name == null || "".equals(myContacts.name.trim())) {
                        myContacts.name = strReplace;
                    }
                }
            }
            if (cursorQuery2 != null) {
                cursorQuery2.close();
            }
            Cursor cursorQuery3 = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[]{"_id", "data1"}, "contact_id=? AND mimetype='vnd.android.cursor.item/nickname'", new String[]{string}, null);
            while (true) {
                if (cursorQuery3 != null && cursorQuery3.moveToNext()) {
                    String string3 = cursorQuery3.getString(cursorQuery3.getColumnIndex("data1"));
                    if (string3 != null && !"".equals(string3.trim())) {
                        myContacts.note = string3;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (cursorQuery3 != null) {
                cursorQuery3.close();
            }
            if (myContacts.name != null && myContacts.phone != null) {
                arrayList.add(myContacts);
            }
            if (contactResponse != null && arrayList.size() != 0 && arrayList.size() % 20 == 0) {
                contactResponse.onContactResponse(arrayList);
                arrayList.clear();
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        if (contactResponse != null) {
            contactResponse.onContactResponse(arrayList);
            contactResponse.onFinish();
        }
        return arrayList;
    }
}
