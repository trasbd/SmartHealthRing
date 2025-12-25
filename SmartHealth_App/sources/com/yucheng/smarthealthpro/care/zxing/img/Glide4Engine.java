package com.yucheng.smarthealthpro.care.zxing.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

/* loaded from: classes4.dex */
public class Glide4Engine implements ImageEngine {
    @Override // com.zhihu.matisse.engine.ImageEngine
    public boolean supportAnimatedGif() {
        return true;
    }

    @Override // com.zhihu.matisse.engine.ImageEngine
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context).asBitmap().load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(resize, resize).placeholder(placeholder).centerCrop()).into(imageView);
    }

    @Override // com.zhihu.matisse.engine.ImageEngine
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context).asBitmap().load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(resize, resize).placeholder(placeholder).centerCrop()).into(imageView);
    }

    @Override // com.zhihu.matisse.engine.ImageEngine
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context).load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(resizeX, resizeY).priority(Priority.HIGH).fitCenter()).into(imageView);
    }

    @Override // com.zhihu.matisse.engine.ImageEngine
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context).asGif().load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(resizeX, resizeY).priority(Priority.HIGH).fitCenter()).into(imageView);
    }
}
