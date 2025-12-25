package com.yucheng.smarthealthpro.perfect;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;
import com.yucheng.smarthealthpro.MainActivity;
import com.yucheng.smarthealthpro.MyApplication;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityLanguageBinding;
import com.yucheng.smarthealthpro.framework.HealthApplication;
import com.yucheng.smarthealthpro.framework.util.SharedPreferencesUtils;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.smarthealthpro.utils.MultiLanguageUtils;
import com.yucheng.smarthealthpro.utils.PhoneUtils;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class LanguageActivity extends BaseVbActivity<ActivityLanguageBinding> {
    private boolean isSystem;
    private LanguageAdapter languageAdapter;
    NavigationBar navigationBar;
    RecyclerView recyclerView;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        initView();
        try {
            if (MyApplication.isInitWebView) {
                return;
            }
            new WebView(getApplicationContext()).destroy();
            MyApplication.isInitWebView = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void initView() throws Resources.NotFoundException {
        String language;
        this.recyclerView = ((ActivityLanguageBinding) this.mBinding).recyclerView;
        this.navigationBar = ((ActivityLanguageBinding) this.mBinding).navigationbar;
        changeTitle(getString(R.string.select_language));
        showBack();
        this.navigationBar.showRightbtn(0);
        this.navigationBar.setRightText(getString(R.string.save), new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.perfect.LanguageActivity.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View btn) {
                if (PhoneUtils.isFastClick()) {
                    return;
                }
                LanguageActivity languageActivity = LanguageActivity.this;
                SharedPreferencesUtils.put(languageActivity, "isSystem", Boolean.valueOf(languageActivity.isSystem));
                Language selectedLanguage = LanguageActivity.this.languageAdapter.getSelectedLanguage();
                MultiLanguageUtils.changeLanguage(LanguageActivity.this, selectedLanguage.getLocale().getLanguage(), selectedLanguage.getLocale().getCountry());
                Locale appLocale = MultiLanguageUtils.getAppLocale(LanguageActivity.this);
                if (LanguageActivity.this.isSystem) {
                    SharedPreferencesUtils.put(LanguageActivity.this, Constant.SYSTEM_LANGUAGE, appLocale.toLanguageTag());
                    SharedPreferencesUtils.put(LanguageActivity.this, Constant.SP_LANGUAGE, "");
                }
                YCBTClient.settingLanguage(MultiLanguageUtils.getLanguage(appLocale), null);
                Intent intent = new Intent(LanguageActivity.this, (Class<?>) MainActivity.class);
                intent.setFlags(268468224);
                intent.putExtra("changeLanguage", true);
                LanguageActivity.this.startActivity(intent);
                LanguageActivity.this.finish();
            }
        });
        this.isSystem = ((Boolean) SharedPreferencesUtils.get(this, "isSystem", true)).booleanValue();
        this.languageAdapter = new LanguageAdapter();
        String[] stringArray = this.context.getResources().getStringArray(R.array.languages);
        Locale appLocale = MultiLanguageUtils.getAppLocale(this);
        int i2 = 0;
        for (String str : stringArray) {
            Language language2 = new Language(str);
            if (!this.isSystem) {
                String country = appLocale.getCountry();
                if (!TextUtils.isEmpty(country)) {
                    language = appLocale.getLanguage() + "_" + country;
                } else {
                    language = appLocale.getLanguage();
                }
                if ("iw".equals(language)) {
                    language = "he";
                }
                if ("zh".equals(language) && "zh_CN".equals(str)) {
                    this.languageAdapter.selectedPosition = i2;
                }
                if (language.equals(str)) {
                    this.languageAdapter.selectedPosition = i2;
                }
            }
            this.languageAdapter.addData((LanguageAdapter) language2);
            i2++;
        }
        this.languageAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.yucheng.smarthealthpro.perfect.LanguageActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                this.f$0.lambda$initView$0(baseQuickAdapter, view, i3);
            }
        });
        this.recyclerView.setAdapter(this.languageAdapter);
        this.recyclerView.addItemDecoration(new DefaultItemDecoration(getColor(com.wevey.selector.dialog.R.color.white_light), 2, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
        this.isSystem = i2 == 0;
        this.languageAdapter.selectedPosition = i2;
        LanguageAdapter languageAdapter = this.languageAdapter;
        languageAdapter.notifyItemRangeChanged(0, languageAdapter.getItemCount());
    }

    static class LanguageAdapter extends BaseQuickAdapter<Language, LanguageViewHolder> {
        private int selectedPosition;

        public LanguageAdapter() {
            this(R.layout.item_language);
        }

        public LanguageAdapter(List<Language> data) {
            super(R.layout.item_language, data);
        }

        public Language getSelectedLanguage() {
            return getItem(this.selectedPosition);
        }

        private LanguageAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void onBindViewHolder(LanguageViewHolder holder, int position) {
            super.onBindViewHolder((LanguageAdapter) holder, position);
            if (this.selectedPosition == position) {
                holder.getView(R.id.ivSelected).setVisibility(0);
            } else {
                holder.getView(R.id.ivSelected).setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.chad.library.adapter.base.BaseQuickAdapter
        public void convert(LanguageViewHolder holder, Language language) {
            if ("Indonesia".equals(language.getDisplayName())) {
                holder.setText(R.id.tv_name, "Bahasa Indonesia");
            } else {
                holder.setText(R.id.tv_name, language.getDisplayName());
            }
        }
    }

    static class LanguageViewHolder extends BaseViewHolder {
        public LanguageViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class Language {
        private String displayName;
        private Locale locale;

        /* JADX INFO: Access modifiers changed from: private */
        public Locale getLocale() {
            return this.locale;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Language(String lang) {
            HealthApplication myApplication;
            myApplication = MyApplication.getInstance();
            lang.hashCode();
            switch (lang) {
                case "System":
                    this.locale = MultiLanguageUtils.getSystemLanguage();
                    this.displayName = myApplication.getString(R.string.system_language);
                    break;
                case "la":
                    this.locale = new Locale("la", "");
                    this.displayName = myApplication.getString(R.string.language_la);
                    break;
                case "zh_CN":
                    this.locale = Locale.SIMPLIFIED_CHINESE;
                    this.displayName = myApplication.getString(R.string.simplified_chinese);
                    break;
                case "zh_HK":
                case "zh_TW":
                    this.locale = Locale.TRADITIONAL_CHINESE;
                    this.displayName = myApplication.getString(R.string.traditional_chinese);
                    break;
                default:
                    Locale locale = new Locale(lang, "");
                    this.locale = locale;
                    this.displayName = locale.getDisplayLanguage(locale);
                    break;
            }
        }
    }
}
