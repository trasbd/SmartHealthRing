package com.yucheng.smarthealthpro.me.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Permission;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.me.bean.MePushMessageBean;
import com.yucheng.smarthealthpro.utils.DensityUtils;
import com.yucheng.smarthealthpro.utils.NotificationManagerUtils;
import com.yucheng.smarthealthpro.utils.PermissionUtil;
import com.yucheng.smarthealthpro.utils.Tools;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MePushMessageListAdapter extends BaseQuickAdapter<MePushMessageBean, BaseViewHolder> {
    private Context context;
    private char[] isSwitch;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onCheckedClick(String mPushMessage);

        void onClick(MePushMessageBean hisSearch, int position);
    }

    public MePushMessageListAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.mOnItemClickListener = null;
        this.context = context;
        this.isSwitch = NotificationManagerUtils.getAllSwitchState(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final MePushMessageBean bean) {
        final int layoutPosition = holder.getLayoutPosition();
        if (bean != null) {
            holder.setText(R.id.tv_app_name, bean.getAppName());
            ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.iv_image);
            new RequestOptions();
            Glide.with(this.context).asBitmap().load(bean.getBitmap()).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(DensityUtils.dip2px(this.context, 7.0f)))).into(imageView);
            Switch r1 = (Switch) holder.itemView.findViewById(R.id.switch_push_message);
            r1.setChecked(this.isSwitch[bean.index] == '1');
            r1.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MePushMessageListAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Logger.d("chong---------position===" + bean.index);
                    if ('0' == MePushMessageListAdapter.this.isSwitch[bean.index]) {
                        if ((bean.index == 0 && !MePushMessageListAdapter.this.permission(new String[]{Permission.READ_PHONE_STATE, Permission.CALL_PHONE, "android.permission.ANSWER_PHONE_CALLS", Permission.READ_CALL_LOG})) || (bean.index == 1 && !MePushMessageListAdapter.this.permission(new String[]{Permission.READ_SMS, Permission.RECEIVE_SMS}))) {
                            MePushMessageListAdapter.this.setPushMessageData(bean.index, '0');
                            return;
                        } else {
                            MePushMessageListAdapter.this.setPushMessageData(bean.index, '1');
                            return;
                        }
                    }
                    MePushMessageListAdapter.this.setPushMessageData(bean.index, '0');
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.yucheng.smarthealthpro.me.adapter.MePushMessageListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MePushMessageListAdapter.this.mOnItemClickListener != null) {
                    MePushMessageListAdapter.this.mOnItemClickListener.onClick(bean, layoutPosition);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPushMessageData(int index, char value) {
        char[] cArr = this.isSwitch;
        cArr[index] = value;
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onCheckedClick(String.valueOf(cArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean permission(String[] permissions) {
        Context context = this.context;
        if (context == null || !(context instanceof Activity)) {
            return false;
        }
        return openPermission((Activity) context, permissions);
    }

    private boolean openPermission(Activity context, String[] permissions) {
        ArrayList arrayList = new ArrayList();
        for (String str : permissions) {
            if (!str.isEmpty() && ContextCompat.checkSelfPermission(context, str) != 0) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, str) || !Tools.readBoolean(str, context, false)) {
                    Tools.saveBoolean(str, true, context);
                    arrayList.add(str);
                } else {
                    PermissionUtil.gotoPermission(context);
                    return false;
                }
            }
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(context, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        return false;
    }
}
