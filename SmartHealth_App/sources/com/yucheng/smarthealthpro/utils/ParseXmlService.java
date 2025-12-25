package com.yucheng.smarthealthpro.utils;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class ParseXmlService {
    public HashMap<String, String> parseXml(InputStream inputStream) throws XmlPullParserException, IOException {
        HashMap<String, String> map = null;
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, "UTF-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 0) {
                    map = new HashMap<>();
                } else if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if ("VERSIONCODE".equalsIgnoreCase(name)) {
                        map.put("versionCode", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("FILENAME".equalsIgnoreCase(name)) {
                        map.put("fileName", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("LOADURL".equalsIgnoreCase(name)) {
                        map.put("loadUrl", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("MECARELOADURL".equalsIgnoreCase(name)) {
                        map.put("meCareLoadUrl", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("ECGUPLOADENABLE".equalsIgnoreCase(name)) {
                        map.put("ecgUploadEnable", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("BPUPLOADENABLE".equalsIgnoreCase(name)) {
                        map.put("bpUploadEnable", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("VERSIONCODECHINA".equalsIgnoreCase(name)) {
                        map.put("versionCodeChina", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("MECAREVERSIONCODE".equalsIgnoreCase(name)) {
                        map.put("meCareVersionCode", xmlPullParserNewPullParser.nextText().trim());
                    } else if ("MECAREVERSIONCODECHINA".equalsIgnoreCase(name)) {
                        map.put("meCareVersionCodeChina", xmlPullParserNewPullParser.nextText().trim());
                    } else {
                        map.put(name, xmlPullParserNewPullParser.nextText().trim());
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
        return map;
    }
}
