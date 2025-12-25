package com.yucheng.smarthealthpro.perfect.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.perfect.ui.ClipView;
import java.io.IOException;

/* loaded from: classes5.dex */
public class ClipViewLayout extends RelativeLayout {
    private static final int DRAG = 1;
    private static final int NONE = 0;
    private static final int ZOOM = 2;
    private ClipView clipView;
    private int height;
    private ImageView imageView;
    private float mHorizontalPadding;
    private float mVerticalPadding;
    private Matrix matrix;
    private final float[] matrixValues;
    private float maxScale;
    private PointF mid;
    private float minScale;
    private int mode;
    private float oldDist;
    private Matrix savedMatrix;
    private PointF start;
    private int width;

    public ClipViewLayout(Context context) {
        this(context, null);
    }

    public ClipViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.matrix = new Matrix();
        this.savedMatrix = new Matrix();
        this.mode = 0;
        this.start = new PointF();
        this.mid = new PointF();
        this.oldDist = 1.0f;
        this.matrixValues = new float[9];
        this.maxScale = 4.0f;
        this.width = 200;
        this.height = 200;
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ClipViewLayout);
        this.mHorizontalPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ClipViewLayout_mHorizontalPadding, (int) TypedValue.applyDimension(1, 50.0f, getResources().getDisplayMetrics()));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ClipViewLayout_clipBorderWidth, (int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()));
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.ClipViewLayout_clipType, 1);
        typedArrayObtainStyledAttributes.recycle();
        ClipView clipView = new ClipView(context);
        this.clipView = clipView;
        clipView.setClipType(i2 == 1 ? ClipView.ClipType.CIRCLE : ClipView.ClipType.RECTANGLE);
        this.clipView.setClipBorderWidth(dimensionPixelSize);
        this.clipView.setmHorizontalPadding(this.mHorizontalPadding);
        this.imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(this.imageView, layoutParams);
        addView(this.clipView, layoutParams);
    }

    public void setImageSrc(final Uri uri) {
        this.imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.yucheng.smarthealthpro.perfect.ui.ClipViewLayout.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ClipViewLayout.this.initSrcPic(uri);
                ClipViewLayout.this.imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00b2 A[PHI: r1
  0x00b2: PHI (r1v11 float) = (r1v10 float), (r1v26 float) binds: [B:16:0x00b0, B:13:0x008b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void initSrcPic(android.net.Uri r10) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.perfect.ui.ClipViewLayout.initSrcPic(android.net.Uri):void");
    }

    public static int getExifOrientation(String filepath) {
        ExifInterface exifInterface;
        int attributeInt;
        try {
            exifInterface = new ExifInterface(filepath);
        } catch (IOException e2) {
            e2.printStackTrace();
            exifInterface = null;
        }
        if (exifInterface != null && (attributeInt = exifInterface.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, -1)) != -1) {
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt == 8) {
                return 270;
            }
        }
        return 0;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & 255;
        if (action == 0) {
            this.savedMatrix.set(this.matrix);
            this.start.set(event.getX(), event.getY());
            this.mode = 1;
        } else if (action == 2) {
            int i2 = this.mode;
            if (i2 == 1) {
                this.matrix.set(this.savedMatrix);
                float x = event.getX() - this.start.x;
                float y = event.getY() - this.start.y;
                this.mVerticalPadding = this.clipView.getClipRect().top;
                this.matrix.postTranslate(x, y);
                checkBorder();
            } else if (i2 == 2) {
                float fSpacing = spacing(event);
                if (fSpacing > 10.0f) {
                    float f2 = fSpacing / this.oldDist;
                    if (f2 < 1.0f) {
                        if (getScale() > this.minScale) {
                            this.matrix.set(this.savedMatrix);
                            this.mVerticalPadding = this.clipView.getClipRect().top;
                            this.matrix.postScale(f2, f2, this.mid.x, this.mid.y);
                            while (getScale() < this.minScale) {
                                this.matrix.postScale(1.01f, 1.01f, this.mid.x, this.mid.y);
                            }
                        }
                        checkBorder();
                    } else if (getScale() <= this.maxScale) {
                        this.matrix.set(this.savedMatrix);
                        this.mVerticalPadding = this.clipView.getClipRect().top;
                        this.matrix.postScale(f2, f2, this.mid.x, this.mid.y);
                    }
                }
            }
            this.imageView.setImageMatrix(this.matrix);
        } else if (action == 5) {
            float fSpacing2 = spacing(event);
            this.oldDist = fSpacing2;
            if (fSpacing2 > 10.0f) {
                this.savedMatrix.set(this.matrix);
                midPoint(this.mid, event);
                this.mode = 2;
            }
        } else if (action == 6) {
            this.mode = 0;
        }
        return true;
    }

    private RectF getMatrixRectF(Matrix matrix) {
        RectF rectF = new RectF();
        if (this.imageView.getDrawable() != null) {
            rectF.set(0.0f, 0.0f, r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    private void checkBorder() {
        float f2;
        RectF matrixRectF = getMatrixRectF(this.matrix);
        int width = this.imageView.getWidth();
        int height = this.imageView.getHeight();
        float f3 = width;
        if (matrixRectF.width() >= f3 - (this.mHorizontalPadding * 2.0f)) {
            f2 = matrixRectF.left > this.mHorizontalPadding ? (-matrixRectF.left) + this.mHorizontalPadding : 0.0f;
            float f4 = matrixRectF.right;
            float f5 = this.mHorizontalPadding;
            if (f4 < f3 - f5) {
                f2 = (f3 - f5) - matrixRectF.right;
            }
        } else {
            f2 = 0.0f;
        }
        float f6 = height;
        if (matrixRectF.height() >= f6 - (this.mVerticalPadding * 2.0f)) {
            f = matrixRectF.top > this.mVerticalPadding ? this.mVerticalPadding + (-matrixRectF.top) : 0.0f;
            float f7 = matrixRectF.bottom;
            float f8 = this.mVerticalPadding;
            if (f7 < f6 - f8) {
                f = (f6 - f8) - matrixRectF.bottom;
            }
        }
        this.matrix.postTranslate(f2, f);
    }

    public final float getScale() {
        this.matrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private void midPoint(PointF point, MotionEvent event) {
        point.set((event.getX(0) + event.getX(1)) / 2.0f, (event.getY(0) + event.getY(1)) / 2.0f);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.clipView.setClipRadiusWidth(width, height);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap clip() {
        /*
            r6 = this;
            android.widget.ImageView r0 = r6.imageView
            r1 = 1
            r0.setDrawingCacheEnabled(r1)
            android.widget.ImageView r0 = r6.imageView
            r0.buildDrawingCache()
            com.yucheng.smarthealthpro.perfect.ui.ClipView r0 = r6.clipView
            android.graphics.Rect r0 = r0.getClipRect()
            r1 = 0
            android.widget.ImageView r2 = r6.imageView     // Catch: java.lang.Exception -> L33
            android.graphics.Bitmap r2 = r2.getDrawingCache()     // Catch: java.lang.Exception -> L33
            int r3 = r0.left     // Catch: java.lang.Exception -> L33
            int r4 = r0.top     // Catch: java.lang.Exception -> L33
            int r5 = r0.width()     // Catch: java.lang.Exception -> L33
            int r0 = r0.height()     // Catch: java.lang.Exception -> L33
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r0)     // Catch: java.lang.Exception -> L33
            int r2 = r6.width     // Catch: java.lang.Exception -> L31
            int r3 = r6.height     // Catch: java.lang.Exception -> L31
            android.graphics.Bitmap r1 = zoomBitmap(r0, r2, r3)     // Catch: java.lang.Exception -> L31
            goto L38
        L31:
            r2 = move-exception
            goto L35
        L33:
            r2 = move-exception
            r0 = r1
        L35:
            r2.printStackTrace()
        L38:
            if (r0 == 0) goto L3d
            r0.recycle()
        L3d:
            android.widget.ImageView r0 = r6.imageView
            r0.destroyDrawingCache()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.smarthealthpro.perfect.ui.ClipViewLayout.clip():android.graphics.Bitmap");
    }

    public static Bitmap decodeSampledBitmap(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i2 = options.outHeight;
        int i3 = options.outWidth;
        if (i2 <= reqHeight && i3 <= reqWidth) {
            return 1;
        }
        int iRound = Math.round(i2 / reqHeight);
        int iRound2 = Math.round(i3 / reqWidth);
        if (iRound < iRound2) {
            iRound2 = iRound;
        }
        if (iRound2 < 3) {
            return iRound2;
        }
        if (iRound2 < 6.5d) {
            return 4;
        }
        if (iRound2 < 8) {
            return 8;
        }
        return iRound2;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(w / width, h2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }
}
