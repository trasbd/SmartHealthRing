package com.yucheng.smarthealthpro.login.normal.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.utils.StringUtilsKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class CodeView extends RelativeLayout {
    private List<String> codes;
    private Context context;
    private EditText et_code;
    private InputMethodManager imm;
    private OnInputListener onInputListener;
    private TextView tv_code1;
    private TextView tv_code2;
    private TextView tv_code3;
    private TextView tv_code4;
    private TextView tv_code5;
    private TextView tv_code6;
    private View v1;
    private View v2;
    private View v3;
    private View v4;
    private View v5;
    private View v6;

    public interface OnInputListener {
        void onInput();

        void onSucess(String code);
    }

    public CodeView(Context context) {
        super(context);
        this.codes = new ArrayList();
        this.context = context;
        loadView();
    }

    public CodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.codes = new ArrayList();
        this.context = context;
        loadView();
    }

    private void loadView() {
        this.imm = (InputMethodManager) this.context.getSystemService("input_method");
        initView(LayoutInflater.from(this.context).inflate(R.layout.view_get_code, this));
        initEvent();
    }

    private void initView(View view) {
        this.tv_code1 = (TextView) view.findViewById(R.id.tv_code1);
        this.tv_code2 = (TextView) view.findViewById(R.id.tv_code2);
        this.tv_code3 = (TextView) view.findViewById(R.id.tv_code3);
        this.tv_code4 = (TextView) view.findViewById(R.id.tv_code4);
        this.tv_code5 = (TextView) view.findViewById(R.id.tv_code5);
        this.tv_code6 = (TextView) view.findViewById(R.id.tv_code6);
        this.et_code = (EditText) view.findViewById(R.id.et_code);
        this.v1 = view.findViewById(R.id.v1);
        this.v2 = view.findViewById(R.id.v2);
        this.v3 = view.findViewById(R.id.v3);
        this.v4 = view.findViewById(R.id.v4);
        this.v5 = view.findViewById(R.id.v5);
        this.v6 = view.findViewById(R.id.v6);
    }

    private void initEvent() {
        this.et_code.addTextChangedListener(new TextWatcher() { // from class: com.yucheng.smarthealthpro.login.normal.view.CodeView.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i1, int i22) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) throws Resources.NotFoundException {
                if (editable == null || editable.length() <= 0) {
                    return;
                }
                CodeView.this.et_code.setText("");
                if (CodeView.this.codes.size() < 6) {
                    String[] strArrSplit = StringUtilsKt.keepOnlyDigits(editable.toString()).split("");
                    if (strArrSplit.length + CodeView.this.codes.size() <= 6) {
                        CodeView.this.codes.addAll(Arrays.asList(strArrSplit));
                    }
                    CodeView.this.showCode();
                }
            }
        });
        this.et_code.setOnKeyListener(new View.OnKeyListener() { // from class: com.yucheng.smarthealthpro.login.normal.view.CodeView.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) throws Resources.NotFoundException {
                if (keyCode != 67 || keyEvent.getAction() != 0 || CodeView.this.codes.size() <= 0) {
                    return false;
                }
                CodeView.this.codes.remove(CodeView.this.codes.size() - 1);
                CodeView.this.showCode();
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCode() throws Resources.NotFoundException {
        String str = this.codes.size() >= 1 ? this.codes.get(0) : "";
        String str2 = this.codes.size() >= 2 ? this.codes.get(1) : "";
        String str3 = this.codes.size() >= 3 ? this.codes.get(2) : "";
        String str4 = this.codes.size() >= 4 ? this.codes.get(3) : "";
        String str5 = this.codes.size() >= 5 ? this.codes.get(4) : "";
        String str6 = this.codes.size() >= 6 ? this.codes.get(5) : "";
        this.tv_code1.setText(str);
        this.tv_code2.setText(str2);
        this.tv_code3.setText(str3);
        this.tv_code4.setText(str4);
        this.tv_code5.setText(str5);
        this.tv_code6.setText(str6);
        setColor();
        callBack();
    }

    private void setColor() throws Resources.NotFoundException {
        int color = getResources().getColor(R.color.text_color, null);
        int color2 = getResources().getColor(R.color.colorAccent, null);
        Color.parseColor("#3F8EED");
        this.v1.setBackgroundColor(color2);
        this.v2.setBackgroundColor(color2);
        this.v3.setBackgroundColor(color2);
        this.v4.setBackgroundColor(color2);
        this.v5.setBackgroundColor(color2);
        this.v6.setBackgroundColor(color2);
        if (this.codes.size() == 0) {
            this.v1.setBackgroundColor(color2);
            this.v2.setBackgroundColor(color);
            this.v3.setBackgroundColor(color);
            this.v4.setBackgroundColor(color);
            this.v5.setBackgroundColor(color);
            this.v6.setBackgroundColor(color);
        }
        if (this.codes.size() == 1) {
            this.v2.setBackgroundColor(color2);
            this.v3.setBackgroundColor(color);
            this.v4.setBackgroundColor(color);
            this.v5.setBackgroundColor(color);
            this.v6.setBackgroundColor(color);
        }
        if (this.codes.size() == 2) {
            this.v3.setBackgroundColor(color2);
            this.v4.setBackgroundColor(color);
            this.v5.setBackgroundColor(color);
            this.v6.setBackgroundColor(color);
        }
        if (this.codes.size() == 3) {
            this.v4.setBackgroundColor(color2);
            this.v5.setBackgroundColor(color);
            this.v6.setBackgroundColor(color);
        }
        if (this.codes.size() == 4) {
            this.v5.setBackgroundColor(color2);
            this.v6.setBackgroundColor(color);
        }
        if (this.codes.size() == 5) {
            this.v6.setBackgroundColor(color2);
        }
        if (this.codes.size() == 6) {
            this.v6.setBackgroundColor(color2);
        }
    }

    private void callBack() {
        if (this.onInputListener == null) {
            return;
        }
        if (this.codes.size() == 6) {
            this.onInputListener.onSucess(getCode());
        } else {
            this.onInputListener.onInput();
        }
    }

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    public void showSoftInput() {
        EditText editText;
        if (this.imm == null || (editText = this.et_code) == null) {
            return;
        }
        editText.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.login.normal.view.CodeView.3
            @Override // java.lang.Runnable
            public void run() {
                CodeView.this.imm.showSoftInput(CodeView.this.et_code, 0);
            }
        }, 200L);
    }

    public String getCode() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it2 = this.codes.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
        }
        return sb.toString();
    }
}
