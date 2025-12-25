package com.yucheng.smarthealthpro.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.gyf.immersionbar.components.ImmersionFragment;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import java.text.ParseException;

/* loaded from: classes4.dex */
public abstract class BaseFragment extends ImmersionFragment {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static Toast toast;
    protected NavigationBar bar;
    public Context context;
    protected final String TAG = getClass().getSimpleName();
    long lastClickTime = 0;

    protected abstract void initData(Context context);

    protected abstract int initLayout();

    protected abstract void initView(View view) throws ParseException;

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override // com.gyf.immersionbar.components.ImmersionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(initLayout(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        getBar(view);
        try {
            initView(view);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        initData(this.context);
    }

    public void getBar(View view) {
        if (this.bar == null) {
            this.bar = (NavigationBar) view.findViewById(R.id.navigationbar);
        }
    }

    public void changeTitle(String str) {
        getBar(getView());
        this.bar.setTitle(str);
    }

    public void changeTitle(int i2) {
        getBar(getView());
        this.bar.setTitle(i2);
    }

    public void showBack() {
        if (this.bar == null) {
            getBar(getView());
        }
        this.bar.showLeftbtn(0);
        this.bar.setLeftOnClickListener(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.framework.BaseFragment.1
            @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
            public void onClick(View view) {
                BaseFragment.this.backAction();
            }
        });
    }

    public void showBack(NavigationBar.MyOnClickListener myOnClickListener) {
        this.bar.showLeftbtn(0);
        this.bar.setLeftOnClickListener(myOnClickListener);
    }

    public void showRightText(String str, NavigationBar.MyOnClickListener myOnClickListener) {
        if (this.bar == null) {
            getBar(getView());
        }
        this.bar.setRightText(str);
        this.bar.setRight1OnClickListener(myOnClickListener);
    }

    public void showRightImage(int i2, NavigationBar.MyOnClickListener myOnClickListener) {
        if (this.bar == null) {
            getBar(getView());
        }
        this.bar.setRightImage(i2);
        this.bar.setRight2OnClickListener(myOnClickListener);
    }

    public void showLeftImage(int i2, NavigationBar.MyOnClickListener myOnClickListener) {
        if (this.bar == null) {
            getBar(getView());
        }
        this.bar.setLeftImage(i2);
        this.bar.setLeftOnClickListener(myOnClickListener);
    }

    public void setRightImage(int i2) {
        if (this.bar == null) {
            getBar(getView());
        }
        this.bar.setRightImage(i2);
    }

    public void setRightEnable(boolean z) {
        this.bar.setRightEnable(z);
    }

    public void backAction() {
        getActivity().finish();
    }

    public abstract class OnSingleClickListener implements View.OnClickListener {
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        public OnSingleClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.lastClickTime >= 1000) {
                this.lastClickTime = jCurrentTimeMillis;
                onSingleClick(view);
            }
        }
    }

    public abstract class OnMultiClickListener implements View.OnClickListener {
        public abstract void onMultiClick(View view);

        public OnMultiClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            onMultiClick(view);
        }
    }

    public void showToast(String str) {
        try {
            Toast toast2 = toast;
            if (toast2 == null) {
                toast = Toast.makeText(this.context, str, 0);
            } else {
                toast2.setText(str);
            }
            toast.show();
        } catch (Exception e2) {
            e2.printStackTrace();
            Looper.prepare();
            Toast.makeText(this.context, str, 0).show();
            Looper.loop();
        }
    }

    public boolean checkCanClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastClickTime <= 1000) {
            return false;
        }
        this.lastClickTime = jCurrentTimeMillis;
        return true;
    }
}
