package com.yucheng.smarthealthpro.framework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import java.security.MessageDigest;
import java.util.Locale;

/* loaded from: classes4.dex */
public class BaseActivity extends AppCompatActivity {
    protected NavigationBar bar;
    public Activity context;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        getBar();
    }

    public static String sHA1(Context context) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                String upperCase = Integer.toHexString(b2 & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    sb.append("0");
                }
                sb.append(upperCase);
                sb.append(":");
            }
            String string = sb.toString();
            return string.substring(0, string.length() - 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void getBar() {
        if (this.bar == null) {
            this.bar = (NavigationBar) findViewById(R.id.navigationbar);
        }
        if (this.bar != null) {
            ImmersionBar.with(this).titleBar(this.bar).statusBarDarkFont(true, 0.0f).navigationBarDarkIcon(true, 0.0f).navigationBarColor(R.color.transparent).keyboardEnable(true).keyboardMode(16).init();
        }
    }

    public TextView getTitleTextView() {
        return this.bar.getTitletxt();
    }

    public void showLeftImage(int i2, NavigationBar.MyOnClickListener myOnClickListener) {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setLeftBtnImage(i2);
            this.bar.setLeftOnClickListener(myOnClickListener);
        }
    }

    public ImageButton getNavLeftBtn() {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            return navigationBar.getNavLeftBtn();
        }
        return null;
    }

    public void showBgLine(Boolean bool) {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.showBgLine(bool);
        }
    }

    public void setTitleBarBackgroundColor(String str) {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setBackgroundColor(str);
        }
    }

    public void changeTitle(String str) {
        getBar();
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setTitle(str);
        }
    }

    public void changeTitle(int i2) {
        getBar();
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setTitle(i2);
        }
    }

    public void showBack() {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.showLeftbtn(0);
            this.bar.setLeftOnClickListener(new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.framework.BaseActivity.1
                @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
                public void onClick(View view) {
                    BaseActivity.this.backAction();
                }
            });
        }
    }

    public void showBack(NavigationBar.MyOnClickListener myOnClickListener) {
        if (this.bar == null) {
            getBar();
        }
        this.bar.showLeftbtn(0);
        this.bar.setLeftOnClickListener(myOnClickListener);
    }

    public void showRightText(String str, NavigationBar.MyOnClickListener myOnClickListener) {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setRightText(str);
            this.bar.setRight1OnClickListener(myOnClickListener);
        }
    }

    public void showRightImage(int i2, NavigationBar.MyOnClickListener myOnClickListener) {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setRightImage(i2);
            this.bar.setRight2OnClickListener(myOnClickListener);
        }
    }

    public void showRightImage() {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.showRightImage();
        }
    }

    public void closeRightImage() {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.closeRightImage();
        }
    }

    public void setRightImage(int i2) {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setRightImage(i2);
        }
    }

    public void setRightEnable(boolean z) {
        if (this.bar == null) {
            getBar();
        }
        NavigationBar navigationBar = this.bar;
        if (navigationBar != null) {
            navigationBar.setRightEnable(z);
        }
    }

    public void backAction() {
        finish();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideKeyboard(currentFocus, motionEvent)) {
                hideKeyboard(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean isShouldHideKeyboard(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return motionEvent.getX() <= ((float) i2) || motionEvent.getX() >= ((float) (view.getWidth() + i2)) || motionEvent.getY() <= ((float) i3) || motionEvent.getY() >= ((float) (view.getHeight() + i3));
    }

    public void hideKeyboard(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }
}
