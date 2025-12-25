package com.yucheng.smarthealthpro.me.setting.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityContactsLayoutBinding;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.me.setting.contacts.adapter.ContactAdapter;
import com.yucheng.smarthealthpro.me.setting.contacts.animator.SlideInOutLeftItemAnimator;
import com.yucheng.smarthealthpro.me.setting.contacts.bean.MyContacts;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.CommonUtil;
import com.yucheng.smarthealthpro.me.setting.contacts.utils.ContactUtils;
import com.yucheng.smarthealthpro.me.setting.contacts.view.CustomItemDecoration;
import com.yucheng.smarthealthpro.me.setting.contacts.view.SideBar;
import com.yucheng.smarthealthpro.sport.view.CommonDialog;
import com.yucheng.smarthealthpro.utils.DialogUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.ContactsBean;
import com.yucheng.ycbtsdk.response.BleDataResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ContactsActivity extends BaseVbActivity<ActivityContactsLayoutBinding> {
    private static final int PERMISS_CONTACT = 1;
    private CustomItemDecoration decoration;
    private LinearLayoutManager layoutManager;
    private ContactAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressDialog progressDialog;
    private SideBar side_bar;
    private ArrayList<MyContacts> contacts = new ArrayList<>();
    private List<ContactsBean> lists = new ArrayList();
    private List<MyContacts> allContacts = new ArrayList();
    private Handler handler = new Handler(new Handler.Callback() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 0) {
                ContactsActivity.this.contacts = (ArrayList) message.obj;
                ContactsActivity.this.setRecyclerView();
                return false;
            }
            if (message.what != 1) {
                return false;
            }
            ContactsActivity.this.setRecyclerView();
            return false;
        }
    });

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTitle(getString(R.string.permission_tv_contacts_title));
        initViews();
        initListeners();
    }

    private void initViews() {
        getPermission();
        this.mRecyclerView = (RecyclerView) findViewById(R.id.rl_recycle_view);
        this.side_bar = (SideBar) findViewById(R.id.side_bar);
        showBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRecyclerView() {
        if (this.mAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            this.layoutManager = linearLayoutManager;
            this.mRecyclerView.setLayoutManager(linearLayoutManager);
            RecyclerView recyclerView = this.mRecyclerView;
            CustomItemDecoration customItemDecoration = new CustomItemDecoration(this);
            this.decoration = customItemDecoration;
            recyclerView.addItemDecoration(customItemDecoration);
            this.mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(this.mRecyclerView));
            this.mAdapter = new ContactAdapter(this, this.contacts);
            initDatas();
            this.mRecyclerView.setAdapter(this.mAdapter);
            return;
        }
        initDatas();
        this.mAdapter.notifyDataSetChanged();
    }

    private void showContacts() {
        showProgressDialog(R.string.ecg_sync_data);
        new Thread(new Runnable() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.2
            @Override // java.lang.Runnable
            public void run() {
                Log.d("ltf", "获取通讯录时间：" + ((System.currentTimeMillis() / 1000) - (System.currentTimeMillis() / 1000)) + "秒,通讯录数量：" + ContactUtils.getAllContacts(ContactsActivity.this, new ContactUtils.ContactResponse() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.2.1
                    @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.ContactUtils.ContactResponse
                    protected void onContactResponse(ArrayList<MyContacts> tempContacts) {
                        ContactsActivity.this.contacts.addAll(tempContacts);
                        Message message = new Message();
                        message.what = 0;
                        message.obj = ContactsActivity.this.contacts;
                        ContactsActivity.this.handler.sendMessage(message);
                        Log.d("ltf", "onContactResponse：,通讯录数量：" + tempContacts.size());
                    }

                    @Override // com.yucheng.smarthealthpro.me.setting.contacts.utils.ContactUtils.ContactResponse
                    protected void onFinish() {
                        ContactsActivity.this.dismissProgressDialog();
                        ContactsActivity.this.getDeviceContacts();
                        Log.d("ltf", "onFinish：,通讯录数量：" + ContactsActivity.this.contacts.size());
                    }
                }).size());
            }
        }).start();
    }

    private void initListeners() {
        showRightImage(R.mipmap.contacts_sync, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.3
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) throws UnsupportedEncodingException {
                if (YCBTClient.getChipScheme() == 3 && !YCBTClient.getAuthPass()) {
                    Toast.makeText(ContactsActivity.this.getApplication(), ContactsActivity.this.getString(R.string.jl_authing), 0).show();
                } else {
                    ContactsActivity.this.startSyncContacts();
                }
            }
        });
        this.side_bar.setIndexChangeListener(new SideBar.indexChangeListener() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.4
            @Override // com.yucheng.smarthealthpro.me.setting.contacts.view.SideBar.indexChangeListener
            public void indexChanged(String tag) {
                if (TextUtils.isEmpty(tag) || ContactsActivity.this.contacts.size() <= 0) {
                    return;
                }
                for (int i2 = 0; i2 < ContactsActivity.this.contacts.size(); i2++) {
                    if (tag.equals(((MyContacts) ContactsActivity.this.contacts.get(i2)).getNote())) {
                        ContactsActivity.this.layoutManager.scrollToPositionWithOffset(i2, 0);
                        return;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSyncContacts() throws UnsupportedEncodingException {
        ArrayList<MyContacts> arrayList = this.contacts;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        this.lists.clear();
        Iterator<MyContacts> it2 = this.contacts.iterator();
        short s = 0;
        while (it2.hasNext()) {
            MyContacts next = it2.next();
            if (next.isChecked) {
                this.lists.add(new ContactsBean(s, next.getName(), next.getPhone()));
                s = (short) (s + 1);
            }
        }
        appPushContacts(this, this.lists);
    }

    private void appPushContacts(Context context, List<ContactsBean> beans) throws UnsupportedEncodingException {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            this.progressDialog = ProgressDialog.show(context, getString(R.string.prompt), getString(R.string.ecg_sync_data), true, false);
        } else {
            progressDialog.show();
        }
        YCBTClient.appNewPushContacts(context, beans, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.5
            @Override // com.yucheng.ycbtsdk.response.BleDataResponse
            public void onDataResponse(int code, float v, HashMap hashMap) {
                if (ContactsActivity.this.progressDialog != null && ContactsActivity.this.progressDialog.isShowing()) {
                    ContactsActivity.this.progressDialog.dismiss();
                }
                if (code == 0) {
                    Toast.makeText(ContactsActivity.this.getApplication(), ContactsActivity.this.getString(R.string.ecg_sync_data_success), 0).show();
                }
            }
        });
    }

    private void initDatas() {
        CommonUtil.sortData(this.contacts);
        String tags = CommonUtil.getTags(this.contacts);
        this.side_bar.setIndexStr(tags);
        this.decoration.setDatas(this.contacts, tags);
        this.mAdapter.addAll(this.contacts);
    }

    private void getPermission() {
        addPermissByPermissionList(this, new String[]{Permission.READ_CONTACTS, Permission.READ_PHONE_STATE}, 1);
    }

    public void addPermissByPermissionList(final Activity activity, String[] permissions, final int request) {
        final ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < permissions.length; i2++) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i2]) != 0) {
                arrayList.add(permissions[i2]);
            }
        }
        if (arrayList.isEmpty()) {
            showContacts();
        } else {
            final CommonDialog commonDialog = new CommonDialog(this);
            commonDialog.setMessage(getString(R.string.contact_permission_prompt_content)).setTitle(getString(R.string.prompt)).setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.6
                @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                public void onConfirmClick() {
                    commonDialog.dismiss();
                    ArrayList arrayList2 = arrayList;
                    ActivityCompat.requestPermissions(activity, (String[]) arrayList2.toArray(new String[arrayList2.size()]), request);
                }

                @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                public void onCancelClick() {
                    commonDialog.dismiss();
                }

                @Override // com.yucheng.smarthealthpro.sport.view.CommonDialog.OnClickBottomListener
                public void onEditTextConfirmClick(String mEditText) {
                    commonDialog.dismiss();
                }
            }).show();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i2 : grantResults) {
            if (i2 == -1) {
                DialogUtils.showPermissionDialog(this, getString(R.string.push_permission), getString(R.string.contact_permission_prompt_content));
                return;
            }
        }
        showContacts();
    }

    public void dealwithPermiss(final Activity context, String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
            return;
        }
        new AlertDialog.Builder(this).setTitle("操作提示").setMessage("注意：当前缺少必要权限！\n请点击“设置”-“权限”-打开所需权限\n最后点击两次后退按钮，即可返回").setPositiveButton("去授权", new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", context.getApplicationContext().getPackageName(), null));
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ContactsActivity.this, "取消操作", 0).show();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceContacts() {
        if (YCBTClient.getAuthPass()) {
            YCBTClient.getDeviceContacts(this, new BleDataResponse() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.ContactsActivity.9
                @Override // com.yucheng.ycbtsdk.response.BleDataResponse
                public void onDataResponse(int i2, float v, HashMap hashMap) {
                    if (hashMap != null && hashMap.get("data") != null) {
                        for (ContactsBean contactsBean : (ArrayList) hashMap.get("data")) {
                            Iterator it2 = ContactsActivity.this.contacts.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    MyContacts myContacts = (MyContacts) it2.next();
                                    if (myContacts.getPhone() != null && myContacts.getPhone().equals(contactsBean.number)) {
                                        myContacts.isChecked = true;
                                        break;
                                    }
                                }
                            }
                        }
                        ContactsActivity.this.handler.sendEmptyMessage(1);
                    }
                    Logger.d("chong---------contacts--hashmap==" + hashMap.toString());
                }
            });
        }
    }
}
