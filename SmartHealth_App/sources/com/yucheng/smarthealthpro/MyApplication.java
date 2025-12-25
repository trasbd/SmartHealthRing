package com.yucheng.smarthealthpro;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.multidex.MultiDex;
import com.amap.api.maps.MapsInitializer;
import com.jieli.jl_rcsp.util.JL_Log;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.tencent.bugly.crashreport.CrashReport;
import com.yucheng.smarthealthpro.database.room.AppDatabase;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.http.HttpUtils;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.util.ToastUtil;
import com.yucheng.smarthealthpro.utils.AppLogManager;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.response.BleConnectResponse;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* loaded from: classes3.dex */
public class MyApplication extends HealthApplication {
    public static final String WX_APPID = "wx944dbd119d02c1ff";
    public static String initStep = "";
    public static boolean isInitFinish = false;
    public static boolean isInitWebView = false;
    public static boolean is_auto_upgrade = false;
    public static MyApplication sInstance;
    private AppDatabase appDatabase;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ViewModelStore appViewModelStore = new ViewModelStore();
    BleConnectResponse bleConnectResponse = new BleConnectResponse() { // from class: com.yucheng.smarthealthpro.MyApplication.4
        /* JADX WARN: Removed duplicated region for block: B:30:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00cc  */
        @Override // com.yucheng.ycbtsdk.response.BleConnectResponse
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onConnectResponse(int r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 319
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.MyApplication.AnonymousClass4.onConnectResponse(int):void");
        }
    };

    @Override // com.yucheng.smarthealthpro.framework.HealthApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        if (((Boolean) SharedPreferencesUtils.get(this, Constant.SpConstKey.IS_FIRST_DOWN, false)).booleanValue()) {
            initSdkAfterAgree();
        }
        ToastUtil.release();
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: com.yucheng.smarthealthpro.MyApplication.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String hostname, SSLSession session) {
                return HttpUtils.verifyHostName(hostname);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        initStep = "正在初始化数据库";
        this.appDatabase = AppDatabase.INSTANCE.getInstance(this);
        initStep = "正在初始化配置全局字体";
        ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(new CalligraphyConfig.Builder().setDefaultFontPath(null).setFontAttrId(io.github.inflationx.calligraphy3.R.attr.fontPath).build())).build());
        SharedPreferencesUtils.put(this, Constant.IS_BATTERY_LOW_SHOW, false);
        SharedPreferencesUtils.put(this, Constant.IS_SEND_MESSAGE, true);
        registerActivityLifecycleCallbacks(MultiLanguageUtils.callbacks);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        initStep = "正在初始化MultiDex";
        MultiDex.install(this);
    }

    public void initLog() {
        initStep = "正在初始化杰理log";
        JL_Log.setTagPrefix("health");
        JL_Log.configureLog(this, false, false);
        AppLogManager.init(this);
        initStep = "正在初始化日志框架Logger";
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("initsdk");
    }

    public void initAMap() {
        initStep = "正在初始化高德地图";
        try {
            MapsInitializer.updatePrivacyShow(getApplicationContext(), true, true);
            MapsInitializer.updatePrivacyAgree(getApplicationContext(), true);
        } catch (Exception e2) {
            CrashReport.postCatchedException(e2);
            e2.printStackTrace();
        }
    }

    public void initYcSdk() {
        Logger.d("initYcSdk BuildConfig.DEBUG=false");
        YCBTClient.initClient(getApplicationContext(), true, ((Boolean) SharedPreferencesUtils.get(this, Constant.SpConstKey.OpenLogSwitch, true)).booleanValue());
        YCBTClient.registerBleStateChange(this.bleConnectResponse);
        Logger.d("initYcSdk finish");
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtils.setConfiguration(this);
    }

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() { // from class: com.yucheng.smarthealthpro.MyApplication.2
            @Override // com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(com.yucheng.smarthealthpro.framework.R.color.around_in, R.color.text_color);
                return new ClassicsHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() { // from class: com.yucheng.smarthealthpro.MyApplication.3
            @Override // com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context).setDrawableSize(30.0f);
            }
        });
    }

    public static void checkInitSdkAfterAgree(Activity context) {
        MyApplication myApplication;
        if (context == null || !(context.getApplication() instanceof MyApplication) || (myApplication = (MyApplication) context.getApplication()) == null) {
            return;
        }
        myApplication.initSdkAfterAgree();
    }

    public void initSdkAfterAgree() {
        Log.d("init", "初始化开始");
        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_APP_ID, false);
        initStep = "正在初始化YcSdk";
        initYcSdk();
        this.executorService.execute(new Runnable() { // from class: com.yucheng.smarthealthpro.MyApplication.5
            @Override // java.lang.Runnable
            public void run() {
                MyApplication.this.init();
                MyApplication.this.initLog();
                MyApplication.this.initAMap();
                MyApplication.isInitFinish = true;
                Log.d("init", "初始化结束");
            }
        });
    }

    public <T extends ViewModel> T getAppViewModel(Class<T> cls) {
        return (T) new ViewModelProvider(this.appViewModelStore, ViewModelProvider.AndroidViewModelFactory.getInstance(this)).get(cls);
    }

    public AppDatabase getAppDatabase() {
        return this.appDatabase;
    }
}
