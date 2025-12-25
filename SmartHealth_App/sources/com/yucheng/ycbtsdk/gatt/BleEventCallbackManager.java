package com.yucheng.ycbtsdk.gatt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.os.Handler;
import android.os.Looper;
import com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback;
import com.yucheng.ycbtsdk.gatt.model.BleScanInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes5.dex */
public class BleEventCallbackManager extends BleEventCallback {
    private final ArrayList<BleEventCallback> mCallbacks = new ArrayList<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private static abstract class BleEventCallbackImpl {
        private BleEventCallbackImpl() {
        }

        public abstract void onCallback(BleEventCallback bleEventCallback);
    }

    private class OnBleEventRunnable implements Runnable {
        private final BleEventCallbackImpl mImpl;

        public OnBleEventRunnable(BleEventCallbackImpl bleEventCallbackImpl) {
            this.mImpl = bleEventCallbackImpl;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BleEventCallbackManager.this.mCallbacks.isEmpty() || this.mImpl == null) {
                return;
            }
            Iterator it2 = new ArrayList(BleEventCallbackManager.this.mCallbacks).iterator();
            while (it2.hasNext()) {
                BleEventCallback bleEventCallback = (BleEventCallback) it2.next();
                if (bleEventCallback != null) {
                    this.mImpl.onCallback(bleEventCallback);
                }
            }
        }
    }

    private void callbackBleEvent(BleEventCallbackImpl bleEventCallbackImpl) {
        if (bleEventCallbackImpl == null) {
            return;
        }
        OnBleEventRunnable onBleEventRunnable = new OnBleEventRunnable(bleEventCallbackImpl);
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            onBleEventRunnable.run();
        } else {
            this.mHandler.post(onBleEventRunnable);
        }
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onAdapterChange(final boolean z) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onAdapterChange(z);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleConnection(final BluetoothDevice bluetoothDevice, final int i2) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onBleConnection(bluetoothDevice, i2);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleDataBlockChanged(final BluetoothDevice bluetoothDevice, final int i2, final int i3) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onBleDataBlockChanged(bluetoothDevice, i2, i3);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleDataNotification(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final byte[] bArr) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onBleDataNotification(bluetoothDevice, uuid, uuid2, bArr);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleNotificationStatus(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final int i2) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onBleNotificationStatus(bluetoothDevice, uuid, uuid2, i2);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleServiceDiscovery(final BluetoothDevice bluetoothDevice, final int i2, final List<BluetoothGattService> list) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onBleServiceDiscovery(bluetoothDevice, i2, list);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onBleWriteStatus(final BluetoothDevice bluetoothDevice, final UUID uuid, final UUID uuid2, final byte[] bArr, final int i2) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onBleWriteStatus(bluetoothDevice, uuid, uuid2, bArr, i2);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onConnectionUpdated(final BluetoothDevice bluetoothDevice, final int i2, final int i3, final int i4, final int i5) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onConnectionUpdated(bluetoothDevice, i2, i3, i4, i5);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onDiscoveryBle(final BluetoothDevice bluetoothDevice, final BleScanInfo bleScanInfo) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onDiscoveryBle(bluetoothDevice, bleScanInfo);
            }
        });
    }

    @Override // com.yucheng.ycbtsdk.gatt.interfaces.BleEventCallback, com.yucheng.ycbtsdk.gatt.interfaces.IBleEventCallback
    public void onDiscoveryBleChange(final boolean z) {
        callbackBleEvent(new BleEventCallbackImpl() { // from class: com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.yucheng.ycbtsdk.gatt.BleEventCallbackManager.BleEventCallbackImpl
            public void onCallback(BleEventCallback bleEventCallback) {
                bleEventCallback.onDiscoveryBleChange(z);
            }
        });
    }

    public void registerBleEventCallback(BleEventCallback bleEventCallback) {
        if (bleEventCallback == null || this.mCallbacks.contains(bleEventCallback)) {
            return;
        }
        this.mCallbacks.add(bleEventCallback);
    }

    public void release() {
        this.mCallbacks.clear();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void unregisterBleEventCallback(BleEventCallback bleEventCallback) {
        if (bleEventCallback == null || this.mCallbacks.isEmpty()) {
            return;
        }
        this.mCallbacks.remove(bleEventCallback);
    }
}
