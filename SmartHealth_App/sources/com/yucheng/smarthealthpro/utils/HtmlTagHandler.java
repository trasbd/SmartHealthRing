package com.yucheng.smarthealthpro.utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.xml.sax.XMLReader;

/* loaded from: classes5.dex */
public class HtmlTagHandler implements Html.TagHandler {
    private String tagName;
    private int startIndex = 0;
    private int endIndex = 0;
    final HashMap<String, String> attributes = new HashMap<>();

    public HtmlTagHandler(String tagName) {
        this.tagName = tagName;
    }

    @Override // android.text.Html.TagHandler
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (tag.equalsIgnoreCase(this.tagName)) {
            parseAttributes(xmlReader);
            if (opening) {
                startHandleTag(tag, output, xmlReader);
            } else {
                endEndHandleTag(tag, output, xmlReader);
            }
        }
    }

    public void startHandleTag(String tag, Editable output, XMLReader xmlReader) {
        this.startIndex = output.length();
    }

    public void endEndHandleTag(String tag, Editable output, XMLReader xmlReader) {
        this.endIndex = output.length();
        String str = this.attributes.get(TypedValues.Custom.S_COLOR);
        String str2 = this.attributes.get("size").split("px")[0];
        if (!TextUtils.isEmpty(str)) {
            output.setSpan(new ForegroundColorSpan(Color.parseColor(str)), this.startIndex, this.endIndex, 33);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        output.setSpan(new AbsoluteSizeSpan(Integer.parseInt(str2)), this.startIndex, this.endIndex, 33);
    }

    private void parseAttributes(final XMLReader xmlReader) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Field declaredField = xmlReader.getClass().getDeclaredField("theNewElement");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(xmlReader);
            Field declaredField2 = obj.getClass().getDeclaredField("theAtts");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Field declaredField3 = obj2.getClass().getDeclaredField("data");
            declaredField3.setAccessible(true);
            String[] strArr = (String[]) declaredField3.get(obj2);
            Field declaredField4 = obj2.getClass().getDeclaredField("length");
            declaredField4.setAccessible(true);
            int iIntValue = ((Integer) declaredField4.get(obj2)).intValue();
            for (int i2 = 0; i2 < iIntValue; i2++) {
                int i3 = i2 * 5;
                this.attributes.put(strArr[i3 + 1], strArr[i3 + 4]);
            }
        } catch (Exception unused) {
        }
    }
}
