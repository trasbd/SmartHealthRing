package com.yucheng.smarthealthpro.settings;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.base.BaseVbActivity;
import com.yucheng.smarthealthpro.databinding.ActivityAboutDeviceBinding;
import com.yucheng.smarthealthpro.framework.view.NavigationBar;
import com.yucheng.smarthealthpro.utils.QrCodeUtils;
import com.yucheng.smarthealthpro.utils.ShareUtils;
import com.yucheng.smarthealthpro.utils.Tools;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.gatt.MyBleService;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AboutDeviceActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\bH\u0002J*\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J0\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/yucheng/smarthealthpro/settings/AboutDeviceActivity;", "Lcom/yucheng/smarthealthpro/base/BaseVbActivity;", "Lcom/yucheng/smarthealthpro/databinding/ActivityAboutDeviceBinding;", "<init>", "()V", "myBleService", "Lcom/yucheng/ycbtsdk/gatt/MyBleService;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "initView", "getRoundedCornerBitmap", "Landroid/graphics/Bitmap;", "bitmap", ViewHierarchyConstants.DIMENSION_WIDTH_KEY, "", "height", "roundPx", "getRoundBitmapByShader", "outWidth", "outHeight", "radius", "boarder", "AboutAdapter", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AboutDeviceActivity extends BaseVbActivity<ActivityAboutDeviceBinding> {
    private MyBleService myBleService;

    @Override // com.yucheng.smarthealthpro.base.BaseVbActivity, com.yucheng.smarthealthpro.framework.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myBleService = MyBleService.getInstance();
        changeTitle(getString(R.string.about_device));
        showBack();
        initView();
    }

    private final void initView() {
        String bindDeviceVersion = YCBTClient.getBindDeviceVersion();
        final String bindDeviceMac = YCBTClient.getBindDeviceMac();
        final String deviceType = Tools.getDeviceType(this.context);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setNestedScrollingEnabled(false);
        AboutAdapter aboutAdapter = new AboutAdapter();
        aboutAdapter.getKeyList().add(getString(R.string.firmware_version));
        aboutAdapter.getDataList().add(bindDeviceVersion);
        aboutAdapter.getKeyList().add(getString(R.string.bluetooth_address));
        aboutAdapter.getDataList().add(bindDeviceMac);
        recyclerView.setAdapter(aboutAdapter);
        final ImageView imageView = (ImageView) findViewById(R.id.ivQr);
        imageView.postDelayed(new Runnable() { // from class: com.yucheng.smarthealthpro.settings.AboutDeviceActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AboutDeviceActivity.initView$lambda$4$lambda$3(this.f$0, deviceType, bindDeviceMac, imageView);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$3(final AboutDeviceActivity aboutDeviceActivity, String str, String str2, ImageView imageView) {
        final Bitmap bitmapCreateQRImage = QrCodeUtils.INSTANCE.createQRImage(aboutDeviceActivity, str + "_" + str2, null, GlMapUtil.DEVICE_DISPLAY_DPI_XHIGH, GlMapUtil.DEVICE_DISPLAY_DPI_XHIGH);
        if (bitmapCreateQRImage != null) {
            imageView.setImageBitmap(bitmapCreateQRImage);
            aboutDeviceActivity.showRightImage(R.mipmap.topbar_ic_share, new NavigationBar.MyOnClickListener() { // from class: com.yucheng.smarthealthpro.settings.AboutDeviceActivity$$ExternalSyntheticLambda0
                @Override // com.yucheng.smarthealthpro.framework.view.NavigationBar.MyOnClickListener
                public final void onClick(View view) throws Throwable {
                    AboutDeviceActivity.initView$lambda$4$lambda$3$lambda$2$lambda$1(this.f$0, bitmapCreateQRImage, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4$lambda$3$lambda$2$lambda$1(AboutDeviceActivity aboutDeviceActivity, Bitmap bitmap, View view) throws Throwable {
        ShareUtils.share(aboutDeviceActivity, bitmap, "QRCode");
    }

    /* compiled from: AboutDeviceActivity.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0014H\u0016R*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR*\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/yucheng/smarthealthpro/settings/AboutDeviceActivity$AboutAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "<init>", "()V", "keyList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getKeyList", "()Ljava/util/ArrayList;", "setKeyList", "(Ljava/util/ArrayList;)V", "dataList", "getDataList", "setDataList", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AboutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<String> keyList = new ArrayList<>();
        private ArrayList<String> dataList = new ArrayList<>();

        public final ArrayList<String> getKeyList() {
            return this.keyList;
        }

        public final void setKeyList(ArrayList<String> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.keyList = arrayList;
        }

        public final ArrayList<String> getDataList() {
            return this.dataList;
        }

        public final void setDataList(ArrayList<String> arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            this.dataList = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            final View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_device_info, parent, false);
            return new RecyclerView.ViewHolder(viewInflate) { // from class: com.yucheng.smarthealthpro.settings.AboutDeviceActivity$AboutAdapter$onCreateViewHolder$1
            };
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.dataList.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            ((TextView) holder.itemView.findViewById(R.id.tvTag)).setText(this.keyList.get(position));
            ((TextView) holder.itemView.findViewById(R.id.tvContent)).setText(this.dataList.get(position));
        }
    }

    private final Bitmap getRoundedCornerBitmap(Bitmap bitmap, int width, int height, int roundPx) {
        int i2 = roundPx * 2;
        int i3 = width + i2;
        int i4 = i2 + height;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(new Rect(0, 0, i3, i4));
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        float f2 = roundPx;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        Bitmap roundBitmapByShader = getRoundBitmapByShader(bitmap, width, height, roundPx, 1);
        if (roundBitmapByShader != null) {
            canvas.drawBitmap(roundBitmapByShader, f2, f2, paint);
        }
        return bitmapCreateBitmap;
    }

    public final Bitmap getRoundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius, int boarder) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        float height = (outHeight * 1.0f) / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale((outWidth * 1.0f) / bitmap.getWidth(), height);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float f2 = boarder;
        RectF rectF = new RectF(f2, f2, outWidth - boarder, outHeight - boarder);
        float f3 = radius;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        if (boarder > 0) {
            Paint paint2 = new Paint(1);
            paint2.setColor(Color.parseColor("#444444"));
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(f2);
            canvas.drawRoundRect(rectF, f3, f3, paint2);
        }
        return bitmapCreateBitmap;
    }
}
