package com.yucheng.ycbtsdk.gatt.interfaces;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import com.yucheng.ycbtsdk.gatt.model.BleScanInfo;
import java.util.List;
import java.util.UUID;

/* loaded from: classes5.dex */
public abstract class BleEventCallback implements IBleEventCallback {
    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onAdapterChange(boolean z) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleConnection(BluetoothDevice bluetoothDevice, int i2) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i2, int i3) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleDataNotification(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleNotificationStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, int i2) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleServiceDiscovery(BluetoothDevice bluetoothDevice, int i2, List<BluetoothGattService> list) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleWriteStatus(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, byte[] bArr, int i2) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onConnectionUpdated(BluetoothDevice bluetoothDevice, int i2, int i3, int i4, int i5) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onDiscoveryBle(BluetoothDevice bluetoothDevice, BleScanInfo bleScanInfo) {
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onDiscoveryBleChange(boolean z) {
    }
}
