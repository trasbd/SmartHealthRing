package com.yucheng.ycbtsdk.gatt;

import com.yucheng.ycbtsdk.bean.ScanDeviceBean;
import java.util.List;

/* loaded from: classes5.dex */
public interface GattBleResponse {
    void bleDataResponse(int i2, byte[] bArr, String str);

    void bleOnCharacteristicWrite(int i2, byte[] bArr, String str);

    void bleScanListResponse(int i2, List<ScanDeviceBean> list);

    void bleScanResponse(int i2, ScanDeviceBean scanDeviceBean);

    void bleStateResponse(int i2);
}
