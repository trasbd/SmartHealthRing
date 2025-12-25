package com.yucheng.smarthealthpro.sport.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.model.LatLng;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes5.dex */
public class TraceRePlay implements Runnable {
    private static final int TRACE_FINISH = 2;
    private static final int TRACE_MOVE = 1;
    private int mIntervalMillisecond;
    private boolean mStop = false;
    private TraceRePlayHandler mTraceHandler = new TraceRePlayHandler(this);
    private List<LatLng> mTraceList;
    private TraceRePlayListener mTraceUpdateListener;

    public interface TraceRePlayListener {
        void onTraceUpdateFinish();

        void onTraceUpdating(LatLng latLng);
    }

    public TraceRePlay(List<LatLng> list, int intervalMillisecond, TraceRePlayListener listener) {
        this.mTraceList = list;
        this.mIntervalMillisecond = intervalMillisecond;
        this.mTraceUpdateListener = listener;
    }

    public void stopTrace() {
        this.mStop = true;
    }

    static class TraceRePlayHandler extends Handler {
        WeakReference<TraceRePlay> mTraceRePaly;

        public TraceRePlayHandler(TraceRePlay traceRePlay) {
            super(Looper.getMainLooper());
            this.mTraceRePaly = new WeakReference<>(traceRePlay);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            TraceRePlay traceRePlay = this.mTraceRePaly.get();
            int i2 = message.what;
            if (i2 == 1) {
                LatLng latLng = (LatLng) message.obj;
                if (traceRePlay.mTraceUpdateListener != null) {
                    traceRePlay.mTraceUpdateListener.onTraceUpdating(latLng);
                    return;
                }
                return;
            }
            if (i2 == 2 && traceRePlay.mTraceUpdateListener != null) {
                traceRePlay.mTraceUpdateListener.onTraceUpdateFinish();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() throws InterruptedException {
        if (this.mTraceList != null) {
            for (int i2 = 0; i2 < this.mTraceList.size() && !this.mStop; i2++) {
                LatLng latLng = this.mTraceList.get(i2);
                Message messageObtainMessage = this.mTraceHandler.obtainMessage();
                messageObtainMessage.what = 1;
                messageObtainMessage.obj = latLng;
                this.mTraceHandler.sendMessage(messageObtainMessage);
                try {
                    Thread.sleep(this.mIntervalMillisecond);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            if (this.mStop) {
                return;
            }
            Message messageObtainMessage2 = this.mTraceHandler.obtainMessage();
            messageObtainMessage2.what = 2;
            this.mTraceHandler.sendMessage(messageObtainMessage2);
        }
    }
}
