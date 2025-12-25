package com.yucheng.smarthealthpro.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.col.p0003sl.jt;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.home.activity.sleep.bean.SleepStageBean;
import com.yucheng.smarthealthpro.utils.MLog;
import com.yucheng.smarthealthpro.utils.TimeStampUtils;
import com.yucheng.smarthealthpro.utils.ViewExtentionsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: SleepQualityView.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 v2\u00020\u0001:\u0003vwxB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010Q\u001a\u00020R2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020&0TJ\u001a\u0010U\u001a\u00020R2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\u0018\u0010V\u001a\u00020R2\u0006\u0010W\u001a\u00020\t2\u0006\u0010X\u001a\u00020\tH\u0014J(\u0010Y\u001a\u00020R2\u0006\u0010Z\u001a\u00020\t2\u0006\u0010[\u001a\u00020\t2\u0006\u0010\\\u001a\u00020\t2\u0006\u0010]\u001a\u00020\tH\u0014J\b\u0010^\u001a\u00020RH\u0014J\u0010\u0010_\u001a\u00020R2\u0006\u0010`\u001a\u00020aH\u0014J\u0010\u0010b\u001a\u00020R2\u0006\u0010`\u001a\u00020aH\u0002J\u0010\u0010c\u001a\u00020+2\u0006\u0010d\u001a\u00020eH\u0016J\u0010\u0010f\u001a\u00020R2\u0006\u0010d\u001a\u00020eH\u0002J\b\u0010g\u001a\u00020RH\u0002J\b\u0010h\u001a\u00020RH\u0002JX\u0010i\u001a\u00020R2\u0006\u0010`\u001a\u00020a2\u0006\u0010j\u001a\u00020\u001c2\u0006\u0010k\u001a\u00020\u00132\u0006\u0010l\u001a\u00020\u00132\u0006\u0010m\u001a\u00020\u00132\u0006\u0010n\u001a\u00020\u00132\u0006\u0010o\u001a\u00020\u00132\u0006\u0010p\u001a\u00020\u00132\u0006\u0010q\u001a\u00020(2\u0006\u0010r\u001a\u00020+H\u0002J\u0006\u0010s\u001a\u00020RJ\u0014\u0010t\u001a\u00020R2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020(0TJ&\u0010u\u001a\u00020R2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020(0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020(0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R\u001a\u00105\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010D\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0012\u0010I\u001a\u00060JR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010K\u001a\u0004\u0018\u00010LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010P¨\u0006y"}, d2 = {"Lcom/yucheng/smarthealthpro/view/SleepQualityView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "abnormalColor", "", "animationTime", "", "deepColor", "moveColor", "shallowColor", "wakeColor", "dashedLineColor", "verticalLineColor", "cornerRadius", "", "repairRadius", "deepYAxis", "moveYAxis", "shallowYAxis", "wakeYAxis", "lineWidth", "roundOffset", "bgPaint", "Landroid/graphics/Paint;", "bgPath", "Landroid/graphics/Path;", "dashedLinePaint", "dashedLPath", "linePaint", "path", "verticalLinePaint", "detailList", "", "Lcom/yucheng/smarthealthpro/home/activity/sleep/bean/SleepStageBean;", "timeArray", "", "timeXArray", "isAddTimeXArray", "", "isClick", "verticalLineX", "getVerticalLineX", "()I", "setVerticalLineX", "(I)V", "copyVerticalLineX", "getCopyVerticalLineX", "setCopyVerticalLineX", "toDayTime", "getToDayTime", "()J", "setToDayTime", "(J)V", "mRectF", "Landroid/graphics/RectF;", "mOtherRectF", "gridPath", "borderPath", "cachedBitmap", "Landroid/graphics/Bitmap;", "cacheValid", "mLastWidthMeasureSpec", "mLastHeightMeasureSpec", MqttServiceConstants.TRACE_DEBUG, "getDebug", "()Z", "setDebug", "(Z)V", "lineAnimator", "Lcom/yucheng/smarthealthpro/view/SleepQualityView$LineAnimator;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/yucheng/smarthealthpro/view/SleepQualityView$OnSleepQualityListener;", "getListener", "()Lcom/yucheng/smarthealthpro/view/SleepQualityView$OnSleepQualityListener;", InAppPurchaseConstants.METHOD_SET_LISTENER, "(Lcom/yucheng/smarthealthpro/view/SleepQualityView$OnSleepQualityListener;)V", "setSleepAllBean", "", "list", "", "initAttrs", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "w", jt.f1391g, "oldw", "oldh", "onDetachedFromWindow", "onDraw", "canvas", "Landroid/graphics/Canvas;", "drawContent", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "handleTouch", "handlerListener", "checkXY", "drawNext", "paint", "sleepType", "endXAxis", "yAxis", "xDistance", "blockHeight", "canvasWidth", "nextValue", "isTop", "clearView", "setDataSource", "setLineColor", "Companion", "OnSleepQualityListener", "LineAnimator", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SleepQualityView extends View {
    private static final String TAG = "SleepQualityView";
    public static final float TYPE_ABNORMAL = 5.0f;
    public static final float TYPE_DEEP = 4.0f;
    public static final float TYPE_MOVE = 2.0f;
    public static final float TYPE_SHALLOW = 3.0f;
    public static final float TYPE_WAKE = 1.0f;
    private int abnormalColor;
    private long animationTime;
    private final Paint bgPaint;
    private final Path bgPath;
    private Path borderPath;
    private boolean cacheValid;
    private Bitmap cachedBitmap;
    private int copyVerticalLineX;
    private final float cornerRadius;
    private final Path dashedLPath;
    private int dashedLineColor;
    private final Paint dashedLinePaint;
    private boolean debug;
    private int deepColor;
    private float deepYAxis;
    private List<SleepStageBean> detailList;
    private Path gridPath;
    private boolean isAddTimeXArray;
    private boolean isClick;
    private final LineAnimator lineAnimator;
    private final Paint linePaint;
    private final float lineWidth;
    private OnSleepQualityListener listener;
    private int mLastHeightMeasureSpec;
    private int mLastWidthMeasureSpec;
    private final RectF mOtherRectF;
    private final RectF mRectF;
    private int moveColor;
    private float moveYAxis;
    private final Path path;
    private final float repairRadius;
    private final float roundOffset;
    private int shallowColor;
    private float shallowYAxis;
    private final List<float[]> timeArray;
    private final List<float[]> timeXArray;
    private long toDayTime;
    private int verticalLineColor;
    private final Paint verticalLinePaint;
    private int verticalLineX;
    private int wakeColor;
    private float wakeYAxis;

    /* compiled from: SleepQualityView.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/yucheng/smarthealthpro/view/SleepQualityView$OnSleepQualityListener;", "", "onSleepQuality", "", "verticalLineX", "", "mode", "intervalTimeStr", "", "hourLong", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface OnSleepQualityListener {
        void onSleepQuality(int verticalLineX, int mode, String intervalTimeStr, long hourLong);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepQualityView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.abnormalColor = Color.parseColor("#00000000");
        this.animationTime = 1000L;
        this.deepColor = Color.parseColor("#10e191");
        this.moveColor = Color.parseColor("#F13636");
        this.shallowColor = Color.parseColor("#398eff");
        this.wakeColor = Color.parseColor("#ffa239");
        this.dashedLineColor = Color.parseColor("#ECECEC");
        this.verticalLineColor = Color.parseColor("#E6E6E6");
        this.cornerRadius = ViewExtentionsKt.getDp(6.0f);
        this.repairRadius = ViewExtentionsKt.getDp(15.0f);
        this.lineWidth = 2.0f;
        this.roundOffset = 10.0f;
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5.0f);
        this.bgPaint = paint;
        this.bgPath = new Path();
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(2.0f);
        paint2.setColor(this.dashedLineColor);
        paint2.setPathEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
        this.dashedLinePaint = paint2;
        this.dashedLPath = new Path();
        Paint paint3 = new Paint(5);
        paint3.setStrokeWidth(2.0f);
        paint3.setStyle(Paint.Style.STROKE);
        this.linePaint = paint3;
        this.path = new Path();
        Paint paint4 = new Paint(5);
        paint4.setStrokeWidth(3.0f);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setColor(this.verticalLineColor);
        this.verticalLinePaint = paint4;
        this.timeArray = new ArrayList();
        this.timeXArray = new ArrayList();
        this.verticalLineX = -1;
        this.copyVerticalLineX = -1;
        this.mRectF = new RectF();
        this.mOtherRectF = new RectF();
        this.gridPath = new Path();
        this.borderPath = new Path();
        this.mLastWidthMeasureSpec = -1;
        this.mLastHeightMeasureSpec = -1;
        this.lineAnimator = new LineAnimator(this, new ValueAnimator.AnimatorUpdateListener() { // from class: com.yucheng.smarthealthpro.view.SleepQualityView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SleepQualityView.lineAnimator$lambda$4(this.f$0, valueAnimator);
            }
        });
        initAttrs(context, attributeSet);
    }

    public final int getVerticalLineX() {
        return this.verticalLineX;
    }

    public final void setVerticalLineX(int i2) {
        this.verticalLineX = i2;
    }

    public final int getCopyVerticalLineX() {
        return this.copyVerticalLineX;
    }

    public final void setCopyVerticalLineX(int i2) {
        this.copyVerticalLineX = i2;
    }

    public final long getToDayTime() {
        return this.toDayTime;
    }

    public final void setToDayTime(long j2) {
        this.toDayTime = j2;
    }

    public final boolean getDebug() {
        return this.debug;
    }

    public final void setDebug(boolean z) {
        this.debug = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lineAnimator$lambda$4(SleepQualityView sleepQualityView, ValueAnimator it2) {
        Intrinsics.checkNotNullParameter(it2, "it");
        sleepQualityView.postInvalidate();
    }

    public final OnSleepQualityListener getListener() {
        return this.listener;
    }

    public final void setListener(OnSleepQualityListener onSleepQualityListener) {
        this.listener = onSleepQualityListener;
    }

    public final void setSleepAllBean(List<SleepStageBean> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        List<SleepStageBean> list2 = this.detailList;
        if (list2 != null) {
            list2.clear();
        }
        this.timeArray.clear();
        if (list.isEmpty()) {
            postInvalidate();
            return;
        }
        this.cacheValid = false;
        this.isAddTimeXArray = false;
        this.detailList = new ArrayList(list);
        long time = ((SleepStageBean) CollectionsKt.last((List) list)).getTime() - ((SleepStageBean) CollectionsKt.first((List) list)).getTime();
        int size = list.size() - 1;
        float f2 = 0.0f;
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 1;
            SleepStageBean sleepStageBean = list.get(i3);
            float f3 = 3.0f;
            switch (list.get(i2).getMode()) {
                case 1:
                    f3 = 4.0f;
                    break;
                case 2:
                case 5:
                    break;
                case 3:
                    f3 = 2.0f;
                    break;
                case 4:
                default:
                    f3 = 1.0f;
                    break;
                case 6:
                    f3 = 5.0f;
                    break;
            }
            float[] fArr = {f3, f2, ((sleepStageBean.getTime() - list.get(i2).getTime()) * 1.0f) / time, i2};
            f2 += fArr[2];
            this.timeArray.add(fArr);
            i2 = i3;
        }
        if (this.debug) {
            List<SleepStageBean> list3 = this.detailList;
            Intrinsics.checkNotNull(list3);
            Iterator<T> it2 = list3.iterator();
            while (it2.hasNext()) {
                MLog.INSTANCE.d(String.valueOf((SleepStageBean) it2.next()));
            }
        }
        postInvalidate();
    }

    private final void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SleepQualityView);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "obtainStyledAttributes(...)");
        this.deepColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SleepQualityView_threeColor, this.deepColor);
        this.shallowColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SleepQualityView_twoColor, this.shallowColor);
        this.wakeColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SleepQualityView_oneColor, this.wakeColor);
        this.moveColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SleepQualityView_fourColor, this.moveColor);
        this.dashedLineColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SleepQualityView_dashedLineColor, this.dashedLineColor);
        this.verticalLineColor = typedArrayObtainStyledAttributes.getColor(R.styleable.SleepQualityView_verticalLineColor, this.verticalLineColor);
        this.isClick = typedArrayObtainStyledAttributes.getBoolean(R.styleable.SleepQualityView_sleepClick, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mLastWidthMeasureSpec == widthMeasureSpec && this.mLastHeightMeasureSpec == heightMeasureSpec) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.debug) {
            MLog.INSTANCE.d("onMeasure");
        }
        if (this.isClick) {
            int size = View.MeasureSpec.getSize(widthMeasureSpec) / 2;
            this.verticalLineX = size;
            this.copyVerticalLineX = size;
        }
        this.mLastWidthMeasureSpec = widthMeasureSpec;
        this.mLastHeightMeasureSpec = heightMeasureSpec;
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h2, int oldw, int oldh) {
        super.onSizeChanged(w, h2, oldw, oldh);
        this.gridPath.reset();
        float paddingTop = ((h2 - getPaddingTop()) - getPaddingBottom()) / 7.0f;
        for (int i2 = 0; i2 < 8; i2++) {
            float paddingTop2 = (i2 * paddingTop) + getPaddingTop();
            this.gridPath.moveTo(getPaddingStart(), paddingTop2);
            this.gridPath.lineTo(w - getPaddingEnd(), paddingTop2);
        }
        this.borderPath.reset();
        this.borderPath.moveTo(getPaddingStart(), getPaddingTop());
        this.borderPath.lineTo(getPaddingStart(), h2 - getPaddingTop());
        this.borderPath.moveTo(w - getPaddingEnd(), getPaddingTop());
        this.borderPath.lineTo(w - getPaddingEnd(), h2 - getPaddingTop());
        this.lineAnimator.start(this.animationTime);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.lineAnimator.release();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }
        this.copyVerticalLineX = this.verticalLineX;
        if (this.lineAnimator.getMPhaseX() >= 1.0f) {
            Bitmap bitmap2 = this.cachedBitmap;
            if (bitmap2 == null || bitmap2 == null || bitmap2.getWidth() != getWidth() || (bitmap = this.cachedBitmap) == null || bitmap.getHeight() != getHeight()) {
                Bitmap bitmap3 = this.cachedBitmap;
                if (bitmap3 != null) {
                    bitmap3.recycle();
                }
                this.cachedBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                this.cacheValid = false;
            }
            if (!this.cacheValid) {
                Bitmap bitmap4 = this.cachedBitmap;
                Intrinsics.checkNotNull(bitmap4);
                drawContent(new Canvas(bitmap4));
                this.isAddTimeXArray = true;
                this.cacheValid = true;
            }
            Bitmap bitmap5 = this.cachedBitmap;
            if (bitmap5 != null) {
                canvas.drawBitmap(bitmap5, 0.0f, 0.0f, (Paint) null);
            }
        } else {
            drawContent(canvas);
        }
        if (this.isClick) {
            canvas.drawLine(this.verticalLineX, getPaddingTop(), this.verticalLineX, getHeight() - getPaddingBottom(), this.verticalLinePaint);
        }
        if (this.isAddTimeXArray) {
            return;
        }
        handlerListener();
    }

    private final void drawContent(Canvas canvas) {
        float[] fArr;
        int i2;
        float f2;
        char c2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        char c3;
        float f11;
        float height = ((getHeight() - getPaddingTop()) - getPaddingBottom()) / 7.0f;
        float paddingTop = getPaddingTop();
        this.wakeYAxis = paddingTop;
        char c4 = 2;
        float f12 = 2;
        float f13 = height * f12;
        float f14 = paddingTop + f13;
        this.moveYAxis = f14;
        float f15 = f14 + f13;
        this.shallowYAxis = f15;
        this.deepYAxis = f15 + f13;
        float width = ((getWidth() * this.lineAnimator.getMPhaseX()) - getPaddingStart()) - getPaddingEnd();
        getPaddingStart();
        canvas.drawPath(this.gridPath, this.dashedLinePaint);
        canvas.drawPath(this.borderPath, this.dashedLinePaint);
        if (!this.isAddTimeXArray) {
            this.timeXArray.clear();
        }
        this.bgPath.reset();
        this.path.reset();
        int i3 = 0;
        for (Object obj : this.timeArray) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            float[] fArr2 = (float[]) obj;
            float f16 = i3 < this.timeArray.size() + (-1) ? this.timeArray.get(i4)[0] : -1.0f;
            if (fArr2.length >= 3) {
                float f17 = fArr2[0];
                float paddingStart = getPaddingStart() + (fArr2[1] * width);
                float f18 = (fArr2[c4] * width) + paddingStart;
                float f19 = this.wakeYAxis;
                if (f17 == 1.0f) {
                    this.linePaint.setColor(this.wakeColor);
                    this.bgPaint.setColor(this.wakeColor);
                } else if (f17 == 3.0f) {
                    f19 = this.shallowYAxis;
                    this.linePaint.setColor(this.shallowColor);
                    this.bgPaint.setColor(this.shallowColor);
                } else if (f17 == 4.0f) {
                    f19 = this.deepYAxis;
                    this.linePaint.setColor(this.deepColor);
                    this.bgPaint.setColor(this.deepColor);
                } else if (f17 == 2.0f) {
                    f19 = this.moveYAxis;
                    this.linePaint.setColor(this.moveColor);
                    this.bgPaint.setColor(this.moveColor);
                } else if (f17 == 5.0f) {
                    f19 = this.moveYAxis;
                    this.linePaint.setColor(this.abnormalColor);
                    this.bgPaint.setColor(this.abnormalColor);
                }
                float f20 = this.lineWidth / f12;
                this.linePaint.setShader(null);
                float f21 = paddingStart - f20;
                float f22 = f18 + f20;
                float f23 = f19 + height;
                this.mOtherRectF.set(f21, f19, f22, f23);
                float f24 = this.repairRadius;
                if (f22 - f21 <= f24) {
                    f24 = 0.0f;
                }
                float f25 = this.cornerRadius;
                if (f16 == -1.0f) {
                    fArr = fArr2;
                    f2 = height;
                    f3 = f12;
                    f7 = f25;
                    f6 = f7;
                } else {
                    this.bgPath.reset();
                    if (f17 > f16) {
                        fArr = fArr2;
                        f3 = f12;
                        this.mRectF.set(f22 - f24, f19 - f24, f22, f19);
                        f2 = height;
                        f4 = 0.0f;
                        this.bgPath.arcTo(this.mRectF, 0.0f, 90.0f);
                        this.bgPath.lineTo(f22, this.roundOffset + f19);
                        this.bgPath.close();
                        f5 = f25;
                    } else {
                        fArr = fArr2;
                        f2 = height;
                        f3 = f12;
                        this.mRectF.set(f22 - f24, f23, f22, f23 + f24);
                        this.bgPath.arcTo(this.mRectF, -90.0f, 90.0f);
                        this.bgPath.lineTo(f22, f23 - this.roundOffset);
                        this.bgPath.close();
                        f4 = f25;
                        f5 = 0.0f;
                    }
                    canvas.drawPath(this.bgPath, this.bgPaint);
                    f6 = f4;
                    f7 = f5;
                }
                if (i3 != 0) {
                    this.bgPath.reset();
                    if (f17 > this.timeArray.get(i3 - 1)[0]) {
                        this.mRectF.set(f21, f19 - f24, f24 + f21, f19);
                        this.bgPath.arcTo(this.mRectF, 90.0f, 90.0f);
                        this.bgPath.lineTo(f21, this.roundOffset + f19);
                        f8 = f22;
                        f11 = 0.0f;
                    } else {
                        this.mRectF.set(f21, f23, f21 + f24, f24 + f23);
                        f8 = f22;
                        this.bgPath.arcTo(this.mRectF, 180.0f, 90.0f);
                        this.bgPath.lineTo(f21, f23 - this.roundOffset);
                        f11 = f25;
                        f25 = 0.0f;
                    }
                    canvas.drawPath(this.bgPath, this.bgPaint);
                    f9 = f25;
                    f25 = f11;
                } else {
                    f8 = f22;
                    f9 = f25;
                }
                if (i3 < this.timeArray.size() - 1) {
                    f10 = f8;
                    c3 = 3;
                    i2 = i4;
                    drawNext(canvas, this.linePaint, f17, f18, f19, getPaddingStart(), f2, width, this.timeArray.get(i4), f17 > f16);
                } else {
                    i2 = i4;
                    f10 = f8;
                    c3 = 3;
                }
                if (!this.isAddTimeXArray) {
                    if (this.timeXArray.isEmpty()) {
                        this.timeXArray.add(new float[]{f21, f10});
                    } else {
                        List<float[]> list = this.timeXArray;
                        list.add(new float[]{((float[]) CollectionsKt.last((List) list))[1], f10});
                    }
                }
                this.path.reset();
                Path path = this.path;
                RectF rectF = this.mOtherRectF;
                float[] fArr3 = new float[8];
                fArr3[0] = f25;
                fArr3[1] = f25;
                c2 = 2;
                fArr3[2] = f6;
                fArr3[c3] = f6;
                fArr3[4] = f7;
                fArr3[5] = f7;
                fArr3[6] = f9;
                fArr3[7] = f9;
                path.addRoundRect(rectF, fArr3, Path.Direction.CW);
                canvas.drawPath(this.path, this.bgPaint);
            } else {
                fArr = fArr2;
                i2 = i4;
                f2 = height;
                c2 = c4;
                f3 = f12;
            }
            float f26 = fArr[1];
            c4 = c2;
            height = f2;
            f12 = f3;
            i3 = i2;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.timeXArray.isEmpty() || !this.isClick) {
            return false;
        }
        int action = event.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            handleTouch(event);
            return true;
        }
        if (action == 1) {
            handleTouch(event);
            getParent().requestDisallowInterceptTouchEvent(false);
            return true;
        }
        if (action == 2) {
            handleTouch(event);
            return true;
        }
        if (action != 3) {
            return false;
        }
        this.verticalLineX = this.copyVerticalLineX;
        invalidate();
        getParent().requestDisallowInterceptTouchEvent(false);
        return true;
    }

    private final void handleTouch(MotionEvent event) {
        int x = (int) event.getX();
        if (Math.abs(x - this.verticalLineX) > 0) {
            this.verticalLineX = x;
            checkXY();
            handlerListener();
            invalidate();
            return;
        }
        if (this.debug) {
            MLog.INSTANCE.d("ignore: " + x);
        }
    }

    private final void handlerListener() {
        int iNextIndex;
        if (this.debug) {
            MLog.INSTANCE.d("verticalLineX: " + this.verticalLineX);
        }
        List<SleepStageBean> list = this.detailList;
        if (list != null) {
            Integer num = null;
            if (list.isEmpty()) {
                list = null;
            }
            if (list != null) {
                List<float[]> list2 = this.timeXArray;
                ListIterator<float[]> listIterator = list2.listIterator(list2.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        iNextIndex = -1;
                        break;
                    }
                    float[] fArrPrevious = listIterator.previous();
                    int i2 = this.verticalLineX;
                    if (i2 > fArrPrevious[0] && i2 <= fArrPrevious[1]) {
                        iNextIndex = listIterator.nextIndex();
                        break;
                    }
                }
                Integer numValueOf = Integer.valueOf(iNextIndex);
                int iIntValue = numValueOf.intValue();
                if (iIntValue >= 0 && iIntValue < this.timeArray.size()) {
                    num = numValueOf;
                }
                int iIntValue2 = num != null ? num.intValue() : -1;
                if (iIntValue2 != -1) {
                    long time = list.get(iIntValue2).getTime();
                    long time2 = list.get(iIntValue2 + 1).getTime();
                    long j2 = time2 - time;
                    String str = TimeStampUtils.formatHourMinute(Long.valueOf(time)) + "-" + TimeStampUtils.formatHourMinute(Long.valueOf(time2));
                    OnSleepQualityListener onSleepQualityListener = this.listener;
                    if (onSleepQualityListener != null) {
                        onSleepQualityListener.onSleepQuality(this.verticalLineX, list.get(iIntValue2).getMode(), str, j2);
                    }
                }
            }
        }
    }

    private final void checkXY() {
        this.verticalLineX = RangesKt.coerceIn(this.verticalLineX, getPaddingStart(), getWidth() - getPaddingEnd());
    }

    private final void drawNext(Canvas canvas, Paint paint, float sleepType, float endXAxis, float yAxis, float xDistance, float blockHeight, float canvasWidth, float[] nextValue, boolean isTop) {
        Pair pair;
        int i2;
        float f2 = nextValue[0];
        float f3 = nextValue[1];
        if (f2 == 1.0f) {
            pair = TuplesKt.to(Integer.valueOf(this.wakeColor), Float.valueOf(this.wakeYAxis));
        } else if (f2 == 3.0f) {
            pair = TuplesKt.to(Integer.valueOf(this.shallowColor), Float.valueOf(this.shallowYAxis));
        } else if (f2 == 4.0f) {
            pair = TuplesKt.to(Integer.valueOf(this.deepColor), Float.valueOf(this.deepYAxis));
        } else if (f2 == 2.0f) {
            pair = TuplesKt.to(Integer.valueOf(this.moveColor), Float.valueOf(this.moveYAxis));
        } else if (f2 == 5.0f) {
            pair = TuplesKt.to(Integer.valueOf(this.abnormalColor), Float.valueOf(this.moveYAxis));
        } else {
            pair = TuplesKt.to(Integer.valueOf(this.wakeColor), Float.valueOf(this.wakeYAxis));
        }
        int iIntValue = ((Number) pair.component1()).intValue();
        float fFloatValue = ((Number) pair.component2()).floatValue();
        if (sleepType == 1.0f) {
            i2 = this.wakeColor;
        } else if (sleepType == 3.0f) {
            i2 = this.shallowColor;
        } else if (sleepType == 4.0f) {
            i2 = this.deepColor;
        } else if (sleepType == 2.0f) {
            i2 = this.moveColor;
        } else if (sleepType == 5.0f) {
            i2 = this.abnormalColor;
        } else {
            i2 = this.wakeColor;
        }
        float f4 = yAxis + blockHeight;
        if (isTop) {
            fFloatValue += blockHeight;
            f4 = yAxis;
        }
        paint.setShader(new LinearGradient(endXAxis, f4, endXAxis, fFloatValue, new int[]{i2, iIntValue}, (float[]) null, Shader.TileMode.MIRROR));
        if (f2 == 5.0f) {
            return;
        }
        canvas.drawLine(endXAxis, f4, endXAxis, fFloatValue, paint);
    }

    public final void clearView() {
        this.timeXArray.clear();
        this.isAddTimeXArray = false;
        this.timeArray.clear();
        postInvalidate();
    }

    public final void setDataSource(List<float[]> timeArray) {
        Intrinsics.checkNotNullParameter(timeArray, "timeArray");
        this.timeXArray.clear();
        this.isAddTimeXArray = false;
        this.timeArray.clear();
        this.timeArray.addAll(timeArray);
        postInvalidate();
    }

    public final void setLineColor(int wakeColor, int shallowColor, int deepColor, int moveColor) {
        this.wakeColor = wakeColor;
        this.shallowColor = shallowColor;
        this.deepColor = deepColor;
        this.moveColor = moveColor;
        invalidate();
    }

    /* compiled from: SleepQualityView.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/yucheng/smarthealthpro/view/SleepQualityView$LineAnimator;", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/animation/ValueAnimator$AnimatorUpdateListener;", "<init>", "(Lcom/yucheng/smarthealthpro/view/SleepQualityView;Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V", "mPhaseX", "", "value", "phaseX", "getPhaseX", "()F", "setPhaseX", "(F)V", "objectAnimator", "Landroid/animation/ObjectAnimator;", "start", "", "durationMillis", "", "release", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class LineAnimator {
        private final ValueAnimator.AnimatorUpdateListener listener;
        private float mPhaseX;
        private ObjectAnimator objectAnimator;
        final /* synthetic */ SleepQualityView this$0;

        public LineAnimator(SleepQualityView sleepQualityView, ValueAnimator.AnimatorUpdateListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.this$0 = sleepQualityView;
            this.listener = listener;
        }

        public final void setPhaseX(float f2) {
            this.mPhaseX = f2;
        }

        /* renamed from: getPhaseX, reason: from getter */
        public final float getMPhaseX() {
            return this.mPhaseX;
        }

        public final void start(long durationMillis) {
            release();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
            objectAnimatorOfFloat.setDuration(durationMillis);
            objectAnimatorOfFloat.addUpdateListener(this.listener);
            objectAnimatorOfFloat.start();
            this.objectAnimator = objectAnimatorOfFloat;
        }

        public final void release() {
            ObjectAnimator objectAnimator = this.objectAnimator;
            if (objectAnimator != null) {
                objectAnimator.end();
                objectAnimator.cancel();
            }
            this.objectAnimator = null;
        }
    }
}
