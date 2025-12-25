package com.yucheng.smarthealthpro.sport.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;

/* compiled from: SportViewModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/yucheng/smarthealthpro/sport/viewmodel/SportViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_sportMode", "Landroidx/lifecycle/MutableLiveData;", "", "sportMode", "Landroidx/lifecycle/LiveData;", "getSportMode", "()Landroidx/lifecycle/LiveData;", "updateSportMode", "", "isSportMode", "app_SmartHealthRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SportViewModel extends ViewModel {
    private MutableLiveData<Boolean> _sportMode = new MutableLiveData<>(false);

    public final LiveData<Boolean> getSportMode() {
        return this._sportMode;
    }

    public final void updateSportMode(boolean isSportMode) {
        this._sportMode.setValue(Boolean.valueOf(isSportMode));
    }
}
