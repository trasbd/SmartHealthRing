package com.yucheng.smarthealthpro.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import com.yucheng.smarthealthpro.R;

/* loaded from: classes5.dex */
public class SoundPoolUtil {
    private static Context context = null;
    private static boolean isLoaded = false;
    private static int soundId = -1;
    private static SoundPool soundPool;
    private static SoundPoolUtil soundPoolUtil;
    private static Vibrator vibrator;
    private int currId = -1;

    public static synchronized SoundPoolUtil getInstance(Context context2) {
        if (soundPoolUtil == null) {
            soundPoolUtil = new SoundPoolUtil(context2);
        }
        return soundPoolUtil;
    }

    private SoundPoolUtil(Context context2) {
        context = context2;
        vibrator = (Vibrator) context2.getSystemService("vibrator");
        SoundPool soundPool2 = new SoundPool(5, 3, 0);
        soundPool = soundPool2;
        soundId = soundPool2.load(context2, R.raw.helium, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.yucheng.smarthealthpro.utils.SoundPoolUtil.1
            @Override // android.media.SoundPool.OnLoadCompleteListener
            public void onLoadComplete(SoundPool sound, int sampleId, int status) {
                SoundPoolUtil.isLoaded = true;
            }
        });
    }

    private void playSound(int number) {
        float streamVolume;
        if (((AudioManager) context.getSystemService("audio")) != null) {
            streamVolume = r0.getStreamVolume(3) / r0.getStreamMaxVolume(3);
        } else {
            streamVolume = 1.0f;
        }
        float f2 = streamVolume;
        int i2 = soundId;
        if (i2 != -1) {
            this.currId = soundPool.play(i2, f2, f2, 1, number, 1.0f);
        }
    }

    public void play(int number) {
        if (this.currId != -1) {
            stop();
        }
        if (isLoaded) {
            playSound(number);
        }
        vibrator();
    }

    private void vibrator() {
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(new long[]{100, 400, 600, 800}, 0);
        }
    }

    public void stop() {
        int i2;
        SoundPool soundPool2 = soundPool;
        if (soundPool2 != null && soundId != -1 && isLoaded && (i2 = this.currId) != -1) {
            soundPool2.stop(i2);
        }
        Vibrator vibrator2 = vibrator;
        if (vibrator2 != null) {
            vibrator2.cancel();
        }
    }
}
