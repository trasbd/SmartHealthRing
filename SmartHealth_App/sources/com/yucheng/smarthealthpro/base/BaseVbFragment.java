package com.yucheng.smarthealthpro.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.yucheng.smarthealthpro.ext.ViewBindUtilKt;
import com.yucheng.smarthealthpro.framework.BaseFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseVbFragment.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0012\u0010\b\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0011\u0010\n\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/yucheng/smarthealthpro/base/BaseVbFragment;", "VB", "Landroidx/viewbinding/ViewBinding;", "Lcom/yucheng/smarthealthpro/framework/BaseFragment;", "<init>", "()V", "initLayout", "", "_binding", "Landroidx/viewbinding/ViewBinding;", "mViewBind", "getMViewBind", "()Landroidx/viewbinding/ViewBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseVbFragment<VB extends ViewBinding> extends BaseFragment {
    private VB _binding;

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment
    protected int initLayout() {
        return 0;
    }

    public final VB getMViewBind() {
        VB vb = this._binding;
        Intrinsics.checkNotNull(vb);
        return vb;
    }

    @Override // com.yucheng.smarthealthpro.framework.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = (VB) ViewBindUtilKt.inflateWithGeneric(this, inflater, container, false);
        return getMViewBind().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }
}
