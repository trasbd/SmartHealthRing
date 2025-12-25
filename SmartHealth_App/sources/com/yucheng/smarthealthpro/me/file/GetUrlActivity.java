package com.yucheng.smarthealthpro.me.file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import com.yucheng.ycbtsdk.jl.JLOTAManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class GetUrlActivity extends Activity {
    private static String currPath;
    private FileAdapter adapter;
    private List<FileBean> datas;
    private ListView listView;
    private String sdcardDir;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_url);
        init();
        initData();
    }

    private void init() {
        this.listView = (ListView) findViewById(R.id.listview);
    }

    private void initData() {
        try {
            this.sdcardDir = Environment.getExternalStorageDirectory().getCanonicalPath();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        String str = this.sdcardDir;
        if (str != null) {
            this.datas = getFileName(str);
        }
        FileAdapter fileAdapter = new FileAdapter(this, this.datas);
        this.adapter = fileAdapter;
        this.listView.setAdapter((ListAdapter) fileAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.yucheng.smarthealthpro.me.file.GetUrlActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (((FileBean) GetUrlActivity.this.datas.get(position)).type == 0) {
                    GetUrlActivity getUrlActivity = GetUrlActivity.this;
                    getUrlActivity.datas = GetUrlActivity.getFileName(((FileBean) getUrlActivity.datas.get(position)).filePath);
                    GetUrlActivity.this.adapter.setDataChanged(GetUrlActivity.this.datas);
                } else if (((FileBean) GetUrlActivity.this.datas.get(position)).type == 1) {
                    GetUrlActivity.this.setResult(0, new Intent().putExtra("url", ((FileBean) GetUrlActivity.this.datas.get(position)).filePath));
                    GetUrlActivity.this.finish();
                }
            }
        });
    }

    public static List<FileBean> getFileName(String fileAbsolutePath) {
        currPath = fileAbsolutePath;
        ArrayList arrayList = new ArrayList();
        try {
            File[] fileArrListFiles = new File(fileAbsolutePath).listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                for (File file : fileArrListFiles) {
                    String name = file.getName();
                    Logger.d("chong--------fileName==" + name);
                    FileBean fileBean = new FileBean();
                    if (file.isDirectory()) {
                        fileBean.type = 0;
                    } else if ((name.endsWith(".bin") || name.endsWith(JLOTAManager.OTA_ZIP_SUFFIX)) && (name.contains("UI") || name.contains("DFU"))) {
                        fileBean.type = 1;
                    }
                    fileBean.name = file.getName();
                    fileBean.filePath = file.getAbsolutePath();
                    arrayList.add(fileBean);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String str;
        String str2;
        if (keyCode == 4 && (str = this.sdcardDir) != null && (str2 = currPath) != null && !str.equals(str2)) {
            List<FileBean> fileName = getFileName(new File(currPath).getParentFile().getAbsolutePath());
            this.datas = fileName;
            this.adapter.setDataChanged(fileName);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
