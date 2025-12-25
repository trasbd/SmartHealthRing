package com.yucheng.ycbtsdk.gatt.interfaces;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import com.yucheng.ycbtsdk.gatt.model.BleScanInfo;
import java.util.List;
import java.util.UUID;

/* loaded from: classes5.dex */
public interface IBleEventCallback {
    void onAdapterChange(boolean z);

    void onBleConnection(BluetoothDevice bluetoothDevice, int i2);

    void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i2, int i3);

    void onBleDataNotification(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr);

    void onBleNotificationStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, int i2);

    void onBleServiceDiscovery(BluetoothDevice bluetoothDevice, int i2, List<BluetoothGattService> list);

    void onBleWriteStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, int i2);

    void onConnectionUpdated(BluetoothDevice bluetoothDevice, int i2, int i3, int i4, int i5);

    void onDiscoveryBle(BluetoothDevice bluetoothDevice, BleScanInfo bleScanInfo);

    void onDiscoveryBleChange(boolean z);
}
