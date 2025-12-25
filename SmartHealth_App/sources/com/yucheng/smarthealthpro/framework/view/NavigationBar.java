package com.yucheng.smarthealthpro.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.yucheng.smarthealthpro.framework.R;

/* loaded from: classes4.dex */
public class NavigationBar extends LinearLayout {
    private View bgline;
    private View bgview;
    private Context context;
    private MyOnClickListener leftlistener;
    private MyOnClickListener leftlistener2;
    private ImageButton nav_left_btn;
    private Button nav_left_btn2;
    private Button nav_right_btn1;
    private ImageView nav_right_btn2;
    private Button nav_right_btn3;
    private MyOnClickListener rightlistener1;
    private MyOnClickListener rightlistener2;
    private MyOnClickListener rightlistener3;
    private TextView titletxt;
    private TextView tv_right;

    public interface MyOnClickListener {
        void onClick(View view);
    }

    public void setShuaxinIcon(boolean z) {
    }

    public NavigationBar(Context context) {
        super(context);
    }

    public NavigationBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.navigationbar, this);
        this.bgview = findViewById(R.id.bgview);
        this.bgline = findViewById(R.id.bgline);
        TextView textView = (TextView) findViewById(R.id.nav_title);
        this.titletxt = textView;
        textView.getPaint();
        ImageButton imageButton = (ImageButton) findViewById(R.id.nav_btn_left);
        this.nav_left_btn = imageButton;
        imageButton.setVisibility(4);
        this.nav_left_btn.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.framework.view.NavigationBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NavigationBar.this.clickleft((ImageButton) view);
            }
        });
        Button button = (Button) findViewById(R.id.nav_btn_left2);
        this.nav_left_btn2 = button;
        button.setVisibility(4);
        this.nav_left_btn2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.framework.view.NavigationBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NavigationBar.this.clickleft2((Button) view);
            }
        });
        Button button2 = (Button) findViewById(R.id.nav_btn_right1);
        this.nav_right_btn1 = button2;
        button2.setVisibility(4);
        this.nav_right_btn1.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.framework.view.NavigationBar.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NavigationBar.this.clickright1((Button) view);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.nav_btn_right2);
        this.nav_right_btn2 = imageView;
        imageView.setVisibility(4);
        this.nav_right_btn2.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.framework.view.NavigationBar.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NavigationBar.this.clickright2((ImageView) view);
            }
        });
        Button button3 = (Button) findViewById(R.id.nav_btn_right3);
        this.nav_right_btn3 = button3;
        button3.setVisibility(4);
        this.nav_right_btn3.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.framework.view.NavigationBar.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NavigationBar.this.clickright3((Button) view);
            }
        });
        this.tv_right = (TextView) findViewById(R.id.tv_right);
    }

    public TextView getTv_right() {
        return this.tv_right;
    }

    public void isLeftBtnClickAble(boolean z) {
        if (z) {
            this.nav_left_btn.setClickable(true);
        } else {
            this.nav_left_btn.setClickable(false);
        }
    }

    public void setBackgroundColor(String str) {
        this.bgview.setBackgroundColor(Color.parseColor(str));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.bgview.setBackgroundColor(i2);
    }

    public void showBarleftbtn(boolean z) {
        if (z) {
            this.nav_left_btn2.setVisibility(0);
        } else {
            this.nav_left_btn2.setVisibility(4);
        }
    }

    public void setTitle(String str) {
        this.titletxt.setText(str);
    }

    public void setTitle(int i2) {
        this.titletxt.setText(i2);
    }

    public void setTitleColor(int i2) {
        this.titletxt.setTextColor(i2);
    }

    public void showLeftBtn(Boolean bool) {
        if (bool.booleanValue()) {
            this.nav_left_btn.setVisibility(0);
        } else {
            this.nav_left_btn.setVisibility(4);
        }
    }

    public void showShareButtonVisualable(boolean z) {
        if (z) {
            this.nav_right_btn2.setVisibility(0);
        } else {
            this.nav_right_btn2.setVisibility(4);
        }
    }

    public void showLeftbtn(int i2) {
        if (i2 == 0) {
            this.nav_left_btn.setVisibility(0);
            this.nav_left_btn2.setVisibility(4);
        } else {
            if (i2 != 1) {
                return;
            }
            this.nav_left_btn2.setVisibility(0);
            this.nav_left_btn.setVisibility(4);
        }
    }

    public void setLeftBtnImage(int i2) {
        this.nav_left_btn.setImageResource(i2);
    }

    public void showRightbtn(int i2) {
        if (i2 == 0) {
            this.nav_right_btn1.setVisibility(0);
            this.nav_right_btn2.setVisibility(4);
            this.nav_right_btn3.setVisibility(4);
            return;
        }
        if (i2 == 1) {
            this.nav_right_btn2.setVisibility(0);
            this.nav_right_btn1.setVisibility(4);
            this.nav_right_btn3.setVisibility(4);
            return;
        }
        if (i2 == 2) {
            this.nav_right_btn2.setVisibility(4);
            this.nav_right_btn1.setVisibility(0);
            this.nav_right_btn3.setVisibility(0);
        } else if (i2 == 3) {
            this.nav_right_btn2.setVisibility(4);
            this.nav_right_btn1.setVisibility(4);
            this.nav_right_btn3.setVisibility(4);
        } else {
            if (i2 != 4) {
                return;
            }
            this.nav_right_btn2.setVisibility(4);
            this.nav_right_btn1.setVisibility(4);
            this.nav_right_btn3.setVisibility(0);
        }
    }

    public void setBackgroundColor1(String str) {
        this.bgview.setBackgroundColor(Color.parseColor(str));
    }

    public void setRightImage(int i2) {
        showRightbtn(1);
        this.nav_right_btn2.setImageResource(i2);
    }

    public void showRightImage() {
        this.nav_right_btn2.setVisibility(0);
    }

    public void closeRightImage() {
        this.nav_right_btn2.setVisibility(4);
    }

    public void setRightText(String str) {
        showRightbtn(0);
        this.nav_right_btn1.setText(str);
    }

    public void setRightText(String str, MyOnClickListener myOnClickListener) {
        showRightbtn(0);
        this.nav_right_btn1.setText(str);
        setRight1OnClickListener(myOnClickListener);
    }

    public void setRightEnable(boolean z) {
        this.nav_right_btn2.setEnabled(z);
        if (!z) {
            this.nav_right_btn1.setTextColor(-3355444);
        } else {
            this.nav_right_btn1.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    public void setLeftBtn(String str) {
        this.nav_left_btn2.setText(str);
    }

    public void clickleft(ImageButton imageButton) {
        this.leftlistener.onClick(imageButton);
    }

    public void setLeftOnClickListener(MyOnClickListener myOnClickListener) {
        this.leftlistener = myOnClickListener;
    }

    public void clickleft2(Button button) {
        this.leftlistener2.onClick(button);
    }

    public void setLeft2OnClickListener(MyOnClickListener myOnClickListener) {
        this.leftlistener2 = myOnClickListener;
    }

    public void clickright1(Button button) {
        this.rightlistener1.onClick(button);
    }

    public void setRight1OnClickListener(MyOnClickListener myOnClickListener) {
        this.rightlistener1 = myOnClickListener;
    }

    public void clickright2(ImageView imageView) {
        this.rightlistener2.onClick(imageView);
    }

    public void setRight2OnClickListener(MyOnClickListener myOnClickListener) {
        this.rightlistener2 = myOnClickListener;
    }

    public void clickright3(Button button) {
        this.rightlistener3.onClick(button);
    }

    public void setRight3OnClickListener(MyOnClickListener myOnClickListener) {
        this.rightlistener3 = myOnClickListener;
    }

    public void showBgLine(Boolean bool) {
        if (bool.booleanValue()) {
            this.bgline.setVisibility(0);
        } else {
            this.bgline.setVisibility(4);
        }
    }

    public void setLeftImage(int i2) {
        showLeftbtn(0);
        this.nav_left_btn.setImageResource(i2);
    }

    public TextView getTitletxt() {
        return this.titletxt;
    }

    public ImageView getNavRightBtn2() {
        return this.nav_right_btn2;
    }

    public ImageButton getNavLeftBtn() {
        return this.nav_left_btn;
    }
}
