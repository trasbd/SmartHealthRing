package com.yucheng.smarthealthpro.customchart.utils;

import android.content.res.Resources;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_rcsp.constant.Command;
import com.jieli.jl_rcsp.model.SportHealthConfigure;
import com.yucheng.ycbtsdk.Constants;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes4.dex */
public class ColorTemplate {
    public static final int COLOR_NONE = 1122867;
    public static final int COLOR_SKIP = 1122868;
    public static final int[] LIBERTY_COLORS = {Color.rgb(207, 248, 246), Color.rgb(Opcodes.LCMP, 212, 212), Color.rgb(Opcodes.L2I, 180, Opcodes.NEW), Color.rgb(118, Opcodes.FRETURN, Opcodes.DRETURN), Color.rgb(42, 109, Opcodes.IXOR)};
    public static final int[] JOYFUL_COLORS = {Color.rgb(Command.CMD_GET_DEVICE_CONFIG_INFO, 80, 138), Color.rgb(SportHealthConfigure.CONFIGURE_TYPE_SPORT_MODE, Opcodes.FCMPL, 7), Color.rgb(SportHealthConfigure.CONFIGURE_TYPE_SPORT_MODE, 247, 120), Color.rgb(106, Opcodes.GOTO, Opcodes.I2F), Color.rgb(53, 194, 209)};
    public static final int[] PASTEL_COLORS = {Color.rgb(64, 89, 128), Color.rgb(Opcodes.FCMPL, 165, Opcodes.IUSHR), Color.rgb(Command.CMD_GET_DEVICE_CONFIG_INFO, Opcodes.INVOKESTATIC, 162), Color.rgb(Opcodes.ATHROW, Opcodes.I2F, Opcodes.I2F), Color.rgb(Opcodes.PUTSTATIC, 48, 80)};
    public static final int[] COLORFUL_COLORS = {Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(Constants.SleepType.naps, Opcodes.IFNONNULL, 0), Color.rgb(106, Opcodes.FCMPG, 31), Color.rgb(Opcodes.PUTSTATIC, 100, 53)};
    public static final int[] VORDIPLOM_COLORS = {Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, Command.CMD_NOTIFY_DEVICE_APP_INFO, 140), Color.rgb(140, 234, 255), Color.rgb(255, 140, Opcodes.IFGT)};
    public static final int[] MATERIAL_COLORS = {rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"), rgb("#3498db")};
    public static final int[] PHYSIOTHERAPY_COLORS = {rgb("#7D9FFB"), rgb("#219DFC"), rgb("#00E5FF"), rgb("#004CFF")};

    public static int colorWithAlpha(int color, int alpha) {
        return (color & ViewCompat.MEASURED_SIZE_MASK) | ((alpha & 255) << 24);
    }

    public static int rgb(String hex) {
        int i2 = (int) Long.parseLong(hex.replace(MqttTopic.MULTI_LEVEL_WILDCARD, ""), 16);
        return Color.rgb((i2 >> 16) & 255, (i2 >> 8) & 255, i2 & 255);
    }

    public static int getHoloBlue() {
        return Color.rgb(51, Opcodes.PUTFIELD, 229);
    }

    public static List<Integer> createColors(Resources r, int[] colors) {
        ArrayList arrayList = new ArrayList();
        for (int i2 : colors) {
            arrayList.add(Integer.valueOf(r.getColor(i2)));
        }
        return arrayList;
    }

    public static List<Integer> createColors(int[] colors) {
        ArrayList arrayList = new ArrayList();
        for (int i2 : colors) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }
}
