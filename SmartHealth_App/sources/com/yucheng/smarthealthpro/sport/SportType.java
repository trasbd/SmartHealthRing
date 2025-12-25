package com.yucheng.smarthealthpro.sport;

import android.content.Context;
import com.yucheng.smarthealthpro.R;
import com.yucheng.smarthealthpro.sport.bean.SportTabItem;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class SportType {
    public static final int AEROBICS = 36;
    public static final int BADMINTON = 9;
    public static final int BASKETBALL = 7;
    public static final int DANCE = 34;
    public static final int ELLIPTICAL_MACHINE = 33;
    public static final int FITNESS = 4;
    public static final int FOOTBALL = 10;
    public static final int FREE_MODE = 13;
    public static final int GOLF = 32;
    public static final int INDOOR_CYCLING = 19;
    public static final int KAYAK = 29;
    public static final int LEAPING_MOTION = 24;
    public static final int MOUNTAINEERING = 11;
    public static final int ONFOOT = 27;
    public static final int OTHERSPORTS = 37;
    public static final int PING_PONG = 12;
    public static final int REAL_TIME_MODE = 22;
    public static final int RESERVED = 0;
    public static final int RIDE = 3;
    public static final int ROCK_CLIMBING = 35;
    public static final int ROLLER_SKATING = 30;
    public static final int ROWING_MACHINE = 21;
    public static final int RUN = 1;
    public static final int RUN_INDOORS = 14;
    public static final int RUN_OUTSIDE = 15;
    public static final int SCRAP = 5;
    public static final int SIT_UPS = 23;
    public static final int SKIPPING_ROPE = 6;
    public static final int STEPPER = 20;
    public static final int SWIMMING = 2;
    public static final int TENNIS = 31;
    public static final int VOLLEYBALL = 28;
    public static final int WALKING = 8;
    public static final int WALK_AND_RUN = 18;
    public static final int WALK_INDOOR = 17;
    public static final int WALK_OUTDOOR = 16;
    public static final int WEIGHT_TRAINING = 25;
    public static final int YOGA = 26;

    public static ArrayList<SportTabItem> getAllSportItem(Context context) {
        ArrayList<SportTabItem> arrayList = new ArrayList<>();
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASRUNNING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_running), 1));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASFITNESS)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_fitness), 4));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASRIDING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_riding), 3));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASROPESKIPPING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_rope), 6));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBASKETBALL)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_basketball), 7));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSWIMMING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_swim), 2));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWALKING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_fitness_walking), 8));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASBADMINTON)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_badminton), 9));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASREALTIMEMONITORINGMODE)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_real_time_monitoring_mode), 22));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINDOORWALKING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_indoor_walking), 17));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASOUTDOORWALKING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_outdoor_walking), 16));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINDOORRUNING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_indoor_running), 14));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASOUTDOORRUNING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_outdoor_running), 15));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASPINGPONG)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_ping_pong), 12));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASFOOTBALL)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_football), 10));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASMOUNTAINCLIMBING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_mountain_climbing), 11));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASYOGA)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_yoga), 26));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASWEIGHTTRAINING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_weight_training), 25));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASJUMPING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_jumping), 24));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSITUPS)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_sit_ups), 23));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASROWINGMACHINE)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_rowing_machine), 21));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASSTEPPER)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_stepper), 20));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASINDOORRIDING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_indoor_cycling), 19));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASONFOOT)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_onfoot), 27));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASROCKCLIMBING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_rock_climbing), 35));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASDANCE)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_dance), 34));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASELLIPTICALMACHINE)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_elliptical_machine), 33));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASGOLF)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_golf), 32));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASTENNIS)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_tennis), 31));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASROLLERSKATING)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_roller_skating), 30));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASKAYAK)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_kayak), 29));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASVOLLEYBALL)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_volleyball), 28));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASOTHERSPORTS)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_other_sports), 37));
        }
        if (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASAEROBICS)) {
            arrayList.add(new SportTabItem(context.getString(R.string.sport_type_aerobics), 36));
        }
        return arrayList;
    }

    public static int[] getIds(int type) {
        int[] iArr = new int[4];
        switch (type) {
            case 1:
                iArr[0] = R.string.sport_running;
                iArr[1] = R.mipmap.icon_sport_1;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 2:
                iArr[0] = R.string.sport_swim;
                iArr[1] = R.mipmap.icon_sport_2;
                return iArr;
            case 3:
                iArr[0] = R.string.sport_riding;
                iArr[1] = R.mipmap.icon_sport_3;
                iArr[3] = 1;
                return iArr;
            case 4:
                iArr[0] = R.string.sport_fitness;
                iArr[1] = R.mipmap.icon_sport_4;
                return iArr;
            case 5:
            default:
                iArr[0] = R.string.sport_title;
                iArr[1] = R.mipmap.icon_sport_1;
                return iArr;
            case 6:
                iArr[0] = R.string.sport_rope;
                iArr[1] = R.mipmap.icon_sport_6;
                return iArr;
            case 7:
                iArr[0] = R.string.sport_basketball;
                iArr[1] = R.mipmap.icon_sport_7;
                return iArr;
            case 8:
                iArr[0] = R.string.sport_fitness_walking;
                iArr[1] = R.mipmap.icon_sport_8;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 9:
                iArr[0] = R.string.sport_badminton;
                iArr[1] = R.mipmap.icon_sport_9;
                return iArr;
            case 10:
                iArr[0] = R.string.sport_football;
                iArr[1] = R.mipmap.icon_sport_10;
                return iArr;
            case 11:
                iArr[0] = R.string.sport_mountain_climbing;
                iArr[1] = R.mipmap.icon_sport_11;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 12:
                iArr[0] = R.string.sport_ping_pong;
                iArr[1] = R.mipmap.icon_sport_12;
                return iArr;
            case 13:
                iArr[0] = R.string.sport_free_mode;
                iArr[1] = R.mipmap.icon_sport_13;
                return iArr;
            case 14:
                iArr[0] = R.string.sport_indoor_running;
                iArr[1] = R.mipmap.icon_sport_14;
                iArr[2] = 1;
                return iArr;
            case 15:
                iArr[0] = R.string.sport_outdoor_running;
                iArr[1] = R.mipmap.icon_sport_15;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 16:
                iArr[0] = R.string.sport_outdoor_walking;
                iArr[1] = R.mipmap.icon_sport_16;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 17:
                iArr[0] = R.string.sport_indoor_walking;
                iArr[1] = R.mipmap.icon_sport_17;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 18:
                iArr[0] = R.string.sport_running_mode;
                iArr[1] = R.mipmap.icon_sport_18;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 19:
                iArr[0] = R.string.sport_indoor_cycling;
                iArr[1] = R.mipmap.icon_sport_19;
                return iArr;
            case 20:
                iArr[0] = R.string.sport_stepper;
                iArr[1] = R.mipmap.icon_sport_20;
                iArr[2] = 1;
                return iArr;
            case 21:
                iArr[0] = R.string.sport_rowing_machine;
                iArr[1] = R.mipmap.icon_sport_21;
                return iArr;
            case 22:
                iArr[0] = R.string.sport_real_time_monitoring_mode;
                iArr[1] = R.mipmap.icon_sport_22;
                return iArr;
            case 23:
                iArr[0] = R.string.sport_sit_ups;
                iArr[1] = R.mipmap.icon_sport_23;
                return iArr;
            case 24:
                iArr[0] = R.string.sport_jumping;
                iArr[1] = R.mipmap.icon_sport_24;
                return iArr;
            case 25:
                iArr[0] = R.string.sport_weight_training;
                iArr[1] = R.mipmap.icon_sport_25;
                return iArr;
            case 26:
                iArr[0] = R.string.sport_yoga;
                iArr[1] = R.mipmap.icon_sport_26;
                return iArr;
            case 27:
                iArr[0] = R.string.sport_type_onfoot;
                iArr[1] = R.mipmap.icon_sport_27;
                iArr[2] = 1;
                iArr[3] = 1;
                return iArr;
            case 28:
                iArr[0] = R.string.sport_type_volleyball;
                iArr[1] = R.mipmap.icon_sport_28;
                return iArr;
            case 29:
                iArr[0] = R.string.sport_type_kayak;
                iArr[1] = R.mipmap.icon_sport_29;
                return iArr;
            case 30:
                iArr[0] = R.string.sport_type_roller_skating;
                iArr[1] = R.mipmap.icon_sport_30;
                return iArr;
            case 31:
                iArr[0] = R.string.sport_type_tennis;
                iArr[1] = R.mipmap.icon_sport_31;
                return iArr;
            case 32:
                iArr[0] = R.string.sport_type_golf;
                iArr[1] = R.mipmap.icon_sport_32;
                return iArr;
            case 33:
                iArr[0] = R.string.sport_type_elliptical_machine;
                iArr[1] = R.mipmap.icon_sport_33;
                return iArr;
            case 34:
                iArr[0] = R.string.sport_type_dance;
                iArr[1] = R.mipmap.icon_sport_34;
                return iArr;
            case 35:
                iArr[0] = R.string.sport_type_rock_climbing;
                iArr[1] = R.mipmap.icon_sport_35;
                return iArr;
            case 36:
                iArr[0] = R.string.sport_type_aerobics;
                iArr[1] = R.mipmap.icon_sport_36;
                return iArr;
            case 37:
                iArr[0] = R.string.sport_type_other_sports;
                iArr[1] = R.mipmap.icon_sport_37;
                return iArr;
        }
    }
}
