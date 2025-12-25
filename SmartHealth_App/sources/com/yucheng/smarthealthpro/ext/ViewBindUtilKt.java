package com.yucheng.smarthealthpro.ext;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewBindUtil.kt */
@Metadata(d1 = {"\u0000<\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a7\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0006\u0010\r\u001a9\u0010\u000e\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0013\u0012\u0004\u0012\u0002H\u00010\u0012H\u0002¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"inflateBindingWithGeneric", "VB", "Landroidx/viewbinding/ViewBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "layoutInflater", "Landroid/view/LayoutInflater;", "inflateWithGeneric", "(Landroidx/appcompat/app/AppCompatActivity;Landroid/view/LayoutInflater;)Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/Fragment;", "parent", "Landroid/view/ViewGroup;", "attachToParent", "", "(Landroidx/fragment/app/Fragment;Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Z)Landroidx/viewbinding/ViewBinding;", "withGenericBindingClass", "any", "", "block", "Lkotlin/Function1;", "Ljava/lang/Class;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroidx/viewbinding/ViewBinding;", "app_SmartHealthRelease"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ViewBindUtilKt {
    public static final <VB extends ViewBinding> VB inflateWithGeneric(AppCompatActivity appCompatActivity, final LayoutInflater layoutInflater) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(layoutInflater, "layoutInflater");
        return (VB) withGenericBindingClass(appCompatActivity, new Function1() { // from class: com.yucheng.smarthealthpro.ext.ViewBindUtilKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ViewBindUtilKt.inflateBindingWithGeneric$lambda$0(layoutInflater, (Class) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ViewBinding inflateBindingWithGeneric$lambda$0(LayoutInflater layoutInflater, Class clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Object objInvoke = clazz.getMethod("inflate", LayoutInflater.class).invoke(null, layoutInflater);
        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type VB of com.yucheng.smarthealthpro.ext.ViewBindUtilKt.inflateBindingWithGeneric");
        return (ViewBinding) objInvoke;
    }

    public static final <VB extends ViewBinding> VB inflateWithGeneric(Fragment fragment, final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(layoutInflater, "layoutInflater");
        return (VB) withGenericBindingClass(fragment, new Function1() { // from class: com.yucheng.smarthealthpro.ext.ViewBindUtilKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ViewBindUtilKt.inflateBindingWithGeneric$lambda$1(layoutInflater, viewGroup, z, (Class) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ViewBinding inflateBindingWithGeneric$lambda$1(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Class clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Object objInvoke = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.TYPE).invoke(null, layoutInflater, viewGroup, Boolean.valueOf(z));
        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type VB of com.yucheng.smarthealthpro.ext.ViewBindUtilKt.inflateBindingWithGeneric");
        return (ViewBinding) objInvoke;
    }

    private static final <VB extends ViewBinding> VB withGenericBindingClass(Object obj, Function1<? super Class<VB>, ? extends VB> function1) {
        Type genericSuperclass = obj.getClass().getGenericSuperclass();
        for (Class<? super Object> superclass = obj.getClass().getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (genericSuperclass instanceof ParameterizedType) {
                try {
                    Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
                    Intrinsics.checkNotNull(type, "null cannot be cast to non-null type java.lang.Class<VB of com.yucheng.smarthealthpro.ext.ViewBindUtilKt.withGenericBindingClass>");
                    return function1.invoke((Class) type);
                } catch (ClassCastException | NoSuchMethodException unused) {
                    continue;
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                }
            }
            genericSuperclass = superclass.getGenericSuperclass();
        }
        throw new IllegalArgumentException("There is no generic of ViewBinding.");
    }
}
