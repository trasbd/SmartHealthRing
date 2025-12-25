package com.yucheng.smarthealthpro.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes4.dex */
public final class ActivityCamreLayoutBinding implements ViewBinding {
    public final Button cameraCancle;
    public final ImageView cameraFlash;
    public final ImageView cameraPicture;
    public final FrameLayout cameraPreviewLayout;
    public final ImageView cameraSwitch;
    public final ImageView cameraTakePhoto;
    public final RelativeLayout llPhotoLayout;
    private final RelativeLayout rootView;

    private ActivityCamreLayoutBinding(RelativeLayout rootView, Button cameraCancle, ImageView cameraFlash, ImageView cameraPicture, FrameLayout cameraPreviewLayout, ImageView cameraSwitch, ImageView cameraTakePhoto, RelativeLayout llPhotoLayout) {
        this.rootView = rootView;
        this.cameraCancle = cameraCancle;
        this.cameraFlash = cameraFlash;
        this.cameraPicture = cameraPicture;
        this.cameraPreviewLayout = cameraPreviewLayout;
        this.cameraSwitch = cameraSwitch;
        this.cameraTakePhoto = cameraTakePhoto;
        this.llPhotoLayout = llPhotoLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCamreLayoutBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityCamreLayoutBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_camre_layout, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityCamreLayoutBinding bind(View rootView) {
        int i2 = R.id.camera_cancle;
        Button button = (Button) ViewBindings.findChildViewById(rootView, i2);
        if (button != null) {
            i2 = R.id.camera_flash;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, i2);
            if (imageView != null) {
                i2 = R.id.camera_picture;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                if (imageView2 != null) {
                    i2 = R.id.camera_preview_layout;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, i2);
                    if (frameLayout != null) {
                        i2 = R.id.camera_switch;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                        if (imageView3 != null) {
                            i2 = R.id.camera_take_photo;
                            ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(rootView, i2);
                            if (imageView4 != null) {
                                i2 = R.id.ll_photo_layout;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(rootView, i2);
                                if (relativeLayout != null) {
                                    return new ActivityCamreLayoutBinding((RelativeLayout) rootView, button, imageView, imageView2, frameLayout, imageView3, imageView4, relativeLayout);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i2)));
    }
}
