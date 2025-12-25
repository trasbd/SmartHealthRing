package com.yucheng.smarthealthpro.utils;

/* loaded from: classes5.dex */
public class EventBusMessageEvent {
    public static final int CONNECTED = 1;
    public static final int CONNECTING = 2;
    public static final int DISCONNECT = 0;
    public static final int DISCONNECTING = 4;
    public static final int TIMEOUT = 3;
    public int belState;
    public int connectState;
    public String message = "";
}
