package com.yucheng.smarthealthpro.home.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.ecg.util.NativeListToBList;
import com.yucheng.smarthealthpro.utils.Constant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Cardiograph3View extends View {
    private static final float MULTIPLE = 0.5f;
    public static final int state = 10;
    private String age;
    private String bp;
    private Context context;
    private String heart;
    protected int mGridColor;
    protected float mGridWidth;
    protected int mHeight;
    protected int mInitColor;
    protected int mLineColor;
    protected Paint mPaint;
    protected Path mPath;
    protected int mSGridColor;
    protected float mSGridWidth;
    private int mSecLineColor;
    protected int mWidth;
    String nickName;
    private List<Integer> plist;
    private String sex;
    private String userName;

    public Cardiograph3View(Context context) {
        this(context, null);
    }

    public Cardiograph3View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Cardiograph3View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.plist = new ArrayList();
        this.mInitColor = Color.parseColor("#000000");
        this.mLineColor = Color.parseColor("#FB3159");
        this.mGridColor = Color.parseColor("#D9D9D9");
        this.mSGridColor = Color.parseColor("#F0F0F0");
        this.mSecLineColor = Color.parseColor("#8C8C8C");
        this.context = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = new Path();
    }

    public void setDatas(List<Integer> native_list, String userName, String sex, String age, String heart, String bp) {
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.heart = heart;
        this.bp = bp;
        if (native_list != null) {
            int size = native_list.size();
            if (size > 6250) {
                this.plist.clear();
                this.plist.addAll(NativeListToBList.nativeListToBList(native_list));
                return;
            }
            for (int i2 = 0; i2 < size && size > 250; i2++) {
                if (native_list.get(i2).intValue() > 1000) {
                    this.plist.clear();
                    this.plist.addAll(NativeListToBList.nativeListToBList(native_list));
                    return;
                }
            }
            this.plist.clear();
            this.plist.addAll(NativeListToBList.oldListTobList(native_list));
        }
    }

    public List<Integer> getDatas() {
        return this.plist;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        this.mWidth = w;
        this.mHeight = h2;
        super.onSizeChanged(w, h2, oldw, oldh);
    }

    public void initBackground(Canvas canvas) {
        int i2;
        String str;
        String str2;
        float multiple = getMultiple(this.context) * 10.0f;
        this.mSGridWidth = multiple;
        this.mGridWidth = multiple * 5.0f;
        canvas.drawColor(-1);
        float f2 = this.mWidth;
        float f3 = this.mSGridWidth;
        int i3 = (int) (f2 / f3);
        int i4 = (int) (this.mHeight / f3);
        this.mPaint.setColor(this.mSGridColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(2.0f);
        int i5 = 0;
        while (true) {
            if (i5 >= i3 + 1) {
                break;
            }
            float f4 = i5;
            float f5 = this.mSGridWidth;
            canvas.drawLine(f4 * f5, 0.0f, f4 * f5, this.mHeight, this.mPaint);
            i5++;
        }
        for (int i6 = 0; i6 < i4 + 1; i6++) {
            float f6 = i6;
            float f7 = this.mSGridWidth;
            canvas.drawLine(0.0f, f6 * f7, this.mWidth, f6 * f7, this.mPaint);
        }
        float f8 = this.mWidth;
        float f9 = this.mGridWidth;
        int i7 = (int) (f8 / f9);
        int i8 = (int) (this.mHeight / f9);
        this.mPaint.setColor(this.mGridColor);
        this.mPaint.setStrokeWidth(3.0f);
        for (int i9 = 0; i9 < i8 + 1; i9++) {
            if (i9 == 0 || i9 == i8) {
                this.mPaint.setColor(this.mInitColor);
            } else {
                this.mPaint.setColor(this.mGridColor);
            }
            float f10 = i9;
            float f11 = this.mGridWidth;
            canvas.drawLine(0.0f, f10 * f11, this.mWidth, f10 * f11, this.mPaint);
        }
        for (int i10 = 0; i10 < i7 + 1; i10++) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(3.0f);
            if (i10 == 0 || i10 == i7) {
                this.mPaint.setColor(this.mInitColor);
            } else {
                this.mPaint.setColor(this.mGridColor);
            }
            float f12 = i10;
            float f13 = this.mGridWidth;
            canvas.drawLine(f12 * f13, 0.0f, f12 * f13, this.mHeight, this.mPaint);
            int i11 = i10 - 2;
            if (i11 % 5 == 0) {
                this.mPaint.setColor(this.mSecLineColor);
                float f14 = this.mGridWidth;
                canvas.drawLine(f12 * f14, f14 * 2.0f, f12 * f14, this.mHeight - (f14 * 2.0f), this.mPaint);
                this.mPaint.setColor(this.mInitColor);
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setStrokeWidth(1.0f);
                this.mPaint.setTextSize(getResources().getDisplayMetrics().density * 10.0f);
                float f15 = this.mGridWidth;
                canvas.drawText((i11 / 5) + "s", (f12 * f15) + (this.mSGridWidth * 0.5f), this.mHeight - (f15 * 2.0f), this.mPaint);
            }
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mInitColor);
        this.mPaint.setStrokeWidth(3.0f);
        this.mPath.reset();
        this.mPath.moveTo(0.0f, this.mGridWidth * 4.0f);
        this.mPath.lineTo(this.mSGridWidth * 2.0f, this.mGridWidth * 4.0f);
        this.mPath.lineTo(this.mSGridWidth * 2.0f, this.mGridWidth * 2.0f);
        Path path = this.mPath;
        float f16 = this.mSGridWidth * 2.0f;
        float f17 = this.mGridWidth;
        path.lineTo(f16 + f17, f17 * 2.0f);
        Path path2 = this.mPath;
        float f18 = this.mSGridWidth * 2.0f;
        float f19 = this.mGridWidth;
        path2.lineTo(f18 + f19, f19 * 4.0f);
        Path path3 = this.mPath;
        float f20 = this.mSGridWidth * 4.0f;
        float f21 = this.mGridWidth;
        path3.lineTo(f20 + f21, f21 * 4.0f);
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(1.0f);
        this.mPaint.setTextSize(getResources().getDisplayMetrics().density * 12.0f);
        String string = this.context.getString(R.string.ecg_header_title);
        float f22 = this.mGridWidth;
        canvas.drawText(string, f22 * 2.0f, this.mHeight - f22, this.mPaint);
        String str3 = "--";
        if (Constant.isTechFeel()) {
            StringBuilder sbAppend = new StringBuilder().append(this.context.getString(R.string.me_personal_details_sex)).append(":").append(this.sex).append("    ").append(this.context.getString(R.string.me_personal_details_age)).append(":").append(this.age).append("    ").append(this.context.getString(R.string.function_heart)).append(":").append(("0".equals(this.heart) || (str2 = this.heart) == null || "-1".equals(str2)) ? "--" : this.heart).append("    ").append(this.context.getString(R.string.home_blood_pressure_title)).append(":");
            String str4 = this.bp;
            if (str4 != null && !str4.equals("0/0") && !this.bp.equals("0")) {
                str3 = this.bp;
            }
            String string2 = sbAppend.append(str3).toString();
            float f23 = this.mGridWidth;
            canvas.drawText(string2, f23 * 2.0f, f23, this.mPaint);
        } else {
            StringBuilder sbAppend2 = new StringBuilder().append(this.context.getString(R.string.login_user_name)).append(":").append(this.userName).append("    ").append(this.context.getString(R.string.me_personal_details_sex)).append(":").append(this.sex).append("    ").append(this.context.getString(R.string.me_personal_details_age)).append(":").append(this.age).append("    ").append(this.context.getString(R.string.function_heart)).append(":").append(("0".equals(this.heart) || (str = this.heart) == null || "-1".equals(str)) ? "--" : this.heart).append("    ").append(this.context.getString(R.string.home_blood_pressure_title)).append(":");
            String str5 = this.bp;
            if (str5 != null && !str5.equals("0/0") && !this.bp.equals("0")) {
                str3 = this.bp;
            }
            String string3 = sbAppend2.append(str3).toString();
            float f24 = this.mGridWidth;
            canvas.drawText(string3, f24 * 2.0f, f24, this.mPaint);
        }
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mLineColor);
        this.mPaint.setStrokeWidth(this.context.getResources().getDisplayMetrics().density * 1.25f);
        float f25 = this.mGridWidth * 2.0f;
        float f26 = 0.0f;
        for (i2 = 1; i2 < this.plist.size(); i2++) {
            if (i2 == 833 || i2 == 1666 || i2 == 2500 || i2 == 3333 || i2 == 4166) {
                float multiple2 = (i2 - 1) * 3 * getMultiple(this.context);
                f25 += this.mGridWidth * 4.0f;
                f26 = multiple2;
            } else {
                if (i2 == 5000) {
                    return;
                }
                int i12 = i2 - 1;
                canvas.drawLine(((this.mGridWidth * 2.0f) + ((i12 * 3) * getMultiple(this.context))) - f26, (((this.mGridWidth * 4.0f) / 2.0f) - (((this.plist.get(i12).intValue() * 10) * this.mSGridWidth) / 1000.0f)) + f25, ((this.mGridWidth * 2.0f) + ((i2 * 3) * getMultiple(this.context))) - f26, (((this.mGridWidth * 4.0f) / 2.0f) - (((this.plist.get(i2).intValue() * 10) * this.mSGridWidth) / 1000.0f)) + f25, this.mPaint);
            }
        }
    }

    public static float getMultiple(Context context) {
        return context.getResources().getDisplayMetrics().density * 0.5f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        initBackground(canvas);
    }
}
