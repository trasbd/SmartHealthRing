package com.yucheng.smarthealthpro.framework.util;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.os.SystemClock;
import android.view.InputEvent;
import android.view.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class MusicHandler {
    private static final String CMDNAME = "command";
    private static final String CMDNEXT = "next";
    private static final String CMDPAUSE = "pause";
    private static final String CMDPLAY = "play";
    private static final String CMDPREVIOUS = "previous";
    private static final String CMDSTOP = "stop";
    private static final String SERVICECMD = "com.android.music.musicservicecommand";
    private static AudioManager audioManager;
    private static MusicHandler instance;
    private Context context;

    private MusicHandler(Context context) {
        this.context = context;
        audioManager = (AudioManager) context.getSystemService("audio");
    }

    public static synchronized MusicHandler getInstance(Context context) {
        if (instance == null) {
            instance = new MusicHandler(context);
        }
        return instance;
    }

    public void raise() {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 != null) {
            audioManager2.adjustStreamVolume(3, 1, 1);
        }
    }

    public void lower() {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 != null) {
            audioManager2.adjustStreamVolume(3, -1, 1);
        }
    }

    public void play() {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 != null) {
            if (!audioManager2.isMusicActive()) {
                sendMediaButton(126);
                click(3);
                sendMusicKeyEvent(126, false);
                return;
            }
            pause();
        }
    }

    private void pause() {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 == null || !audioManager2.isMusicActive()) {
            return;
        }
        sendMediaButton(127);
        click(2);
        sendMusicKeyEvent(127, true);
    }

    public void next() {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 != null) {
            sendMusicKeyEvent(87, audioManager2.isMusicActive());
        }
    }

    public void previous() {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 != null) {
            sendMusicKeyEvent(88, audioManager2.isMusicActive());
        }
    }

    public void stop() {
        if (audioManager != null) {
            sendMediaButton(86);
            click(1);
            sendMusicKeyEvent(86, audioManager.isMusicActive());
        }
    }

    private void input(int i2) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("android.hardware.input.InputManager");
            Method method = cls.getMethod("injectInputEvent", InputEvent.class, Integer.TYPE);
            long jUptimeMillis = SystemClock.uptimeMillis();
            method.invoke(InputManager.class, new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i2, 0), 0);
            method.invoke(InputManager.class, new KeyEvent(jUptimeMillis, jUptimeMillis, 1, i2, 0), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void sendMusicKeyEvent(int i2, boolean z) {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 == null || audioManager2.isMusicActive() != z) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i2, 0);
        dispatchMediaKeyToAudioService(keyEvent);
        dispatchMediaKeyToAudioService(KeyEvent.changeAction(keyEvent, 1));
    }

    private void dispatchMediaKeyToAudioService(KeyEvent keyEvent) {
        AudioManager audioManager2 = audioManager;
        if (audioManager2 != null) {
            try {
                audioManager2.dispatchMediaKeyEvent(keyEvent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void sendMediaButton(int i2) {
        if (audioManager.isMusicActive()) {
            KeyEvent keyEvent = new KeyEvent(0, i2);
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
            this.context.sendOrderedBroadcast(intent, null);
            KeyEvent keyEvent2 = new KeyEvent(1, i2);
            Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
            intent2.putExtra("android.intent.extra.KEY_EVENT", keyEvent2);
            this.context.sendOrderedBroadcast(intent2, null);
            return;
        }
        KeyEvent keyEvent3 = new KeyEvent(0, i2);
        Intent intent3 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent3.putExtra("android.intent.extra.KEY_EVENT", keyEvent3);
        this.context.sendOrderedBroadcast(intent3, null);
        KeyEvent keyEvent4 = new KeyEvent(1, i2);
        Intent intent4 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent4.putExtra("android.intent.extra.KEY_EVENT", keyEvent4);
        this.context.sendOrderedBroadcast(intent4, null);
    }

    public void click(int i2) {
        Intent intent = new Intent(SERVICECMD);
        if (i2 == 1) {
            intent.putExtra(CMDNAME, CMDSTOP);
        } else if (i2 == 2) {
            intent.putExtra(CMDNAME, CMDPAUSE);
        } else if (i2 == 3) {
            intent.putExtra(CMDNAME, CMDPLAY);
        } else if (i2 == 4) {
            intent.putExtra(CMDNAME, CMDPREVIOUS);
        } else if (i2 == 5) {
            intent.putExtra(CMDNAME, CMDNEXT);
        }
        this.context.sendBroadcast(intent);
    }
}
