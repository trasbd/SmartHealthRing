package com.yucheng.smarthealthpro.utils;

import android.util.Log;

/* loaded from: classes5.dex */
public class AppLogMessageMgr {
    private static boolean isDebug = true;

    public static void isEnableDebug(boolean isDebug2) {
        isDebug = isDebug2;
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.i(tag, msg);
        }
    }

    public static void i(Object object, String msg) {
        if (isDebug) {
            String simpleName = object.getClass().getSimpleName();
            if (msg == null) {
                msg = "";
            }
            Log.i(simpleName, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.i(" [INFO] --- ", msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.d(tag, msg);
        }
    }

    public static void d(Object object, String msg) {
        if (isDebug) {
            String simpleName = object.getClass().getSimpleName();
            if (msg == null) {
                msg = "";
            }
            Log.d(simpleName, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.d(" [DEBUG] --- ", msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.w(tag, msg);
        }
    }

    public static void w(Object object, String msg) {
        if (isDebug) {
            String simpleName = object.getClass().getSimpleName();
            if (msg == null) {
                msg = "";
            }
            Log.w(simpleName, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.w(" [WARN] --- ", msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.e(tag, msg);
        }
    }

    public static void e(Object object, String msg) {
        if (isDebug) {
            String simpleName = object.getClass().getSimpleName();
            if (msg == null) {
                msg = "";
            }
            Log.e(simpleName, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.e(" [ERROR] --- ", msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.v(tag, msg);
        }
    }

    public static void v(Object object, String msg) {
        if (isDebug) {
            String simpleName = object.getClass().getSimpleName();
            if (msg == null) {
                msg = "";
            }
            Log.v(simpleName, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            if (msg == null) {
                msg = "";
            }
            Log.v(" [VERBOSE] --- ", msg);
        }
    }
}
