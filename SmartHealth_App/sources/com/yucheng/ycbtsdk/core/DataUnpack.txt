package com.yucheng.ycbtsdk.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.internal.ServerProtocol;
import com.google.gson.Gson;
import com.yucheng.smarthealthpro.utils.Constant;
import com.yucheng.ycbtsdk.AITools;
import com.yucheng.ycbtsdk.Constants;
import com.yucheng.ycbtsdk.YCBTClient;
import com.yucheng.ycbtsdk.bean.DialsBean;
import com.yucheng.ycbtsdk.bean.GsensorBean;
import com.yucheng.ycbtsdk.gatt.BleHelper;
import com.yucheng.ycbtsdk.jl.WatchManager;
import com.yucheng.ycbtsdk.utils.ByteUtil;
import com.yucheng.ycbtsdk.utils.InnerUtils;
import com.yucheng.ycbtsdk.utils.SPUtil;
import com.yucheng.ycbtsdk.utils.YCBTLog;
import com.zhihu.matisse.internal.loader.AlbumLoader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class DataUnpack {
    private static ArrayList mBlockArray = new ArrayList();
    private static int totalCount = 0;
    private static int progress = 0;

    public static String byteToBinary(byte b2) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 7; i2 >= 0; i2--) {
            sb.append(((1 << i2) & b2) != 0 ? '1' : '0');
        }
        return sb.toString();
    }

    public static void removeAllFunction() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SPUtil.remove(Constants.FunctionConstant.ISHASSTEPCOUNT);
        SPUtil.remove(Constants.FunctionConstant.ISHASSLEEP);
        SPUtil.remove(Constants.FunctionConstant.ISHASREALDATA);
        SPUtil.remove(Constants.FunctionConstant.ISHASFIRMWAREUPDATE);
        SPUtil.remove(Constants.FunctionConstant.ISHASHEARTRATE);
        SPUtil.remove(Constants.FunctionConstant.ISHASINFORMATION);
        SPUtil.remove(Constants.FunctionConstant.ISHASMANYLANGUAGE);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOOD);
        SPUtil.remove(Constants.FunctionConstant.ISHASHEARTALARM);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOODALARM);
        SPUtil.remove(Constants.FunctionConstant.ISHASECGREALUPLOAD);
        SPUtil.remove(Constants.FunctionConstant.ISHASECGHISTORYUPLOAD);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOODOXYGEN);
        SPUtil.remove(Constants.FunctionConstant.ISHASRESPIRATORYRATE);
        SPUtil.remove(Constants.FunctionConstant.ISHASHRV);
        SPUtil.remove(Constants.FunctionConstant.ISHASMORESPORT);
        SPUtil.remove(Constants.FunctionConstant.ALARMCOUNT);
        SPUtil.remove(Constants.FunctionConstant.ISHASCUSTOM);
        SPUtil.remove(Constants.FunctionConstant.ISHASMEETING);
        SPUtil.remove(Constants.FunctionConstant.ISHASPARTY);
        SPUtil.remove(Constants.FunctionConstant.ISHASAPPOINT);
        SPUtil.remove(Constants.FunctionConstant.ISHASTAKEPILLS);
        SPUtil.remove(Constants.FunctionConstant.ISHASTAKEEXERCISE);
        SPUtil.remove(Constants.FunctionConstant.ISHASTAKESLEEP);
        SPUtil.remove(Constants.FunctionConstant.ISHASGETUP);
        SPUtil.remove(Constants.FunctionConstant.ISHASCALLPHONE);
        SPUtil.remove(Constants.FunctionConstant.ISHASMESSAGE);
        SPUtil.remove(Constants.FunctionConstant.ISHASEMAIL);
        SPUtil.remove(Constants.FunctionConstant.ISHASQQ);
        SPUtil.remove(Constants.FunctionConstant.ISHASWECHAT);
        SPUtil.remove(Constants.FunctionConstant.ISHASSINA);
        SPUtil.remove(Constants.FunctionConstant.ISHASFACEBOOK);
        SPUtil.remove(Constants.FunctionConstant.ISHASTWITTER);
        SPUtil.remove(Constants.FunctionConstant.ISHASWHATSAPP);
        SPUtil.remove(Constants.FunctionConstant.ISHASMESSENGER);
        SPUtil.remove(Constants.FunctionConstant.ISHASINSTAGRAM);
        SPUtil.remove(Constants.FunctionConstant.ISHASLINKEDIN);
        SPUtil.remove(Constants.FunctionConstant.ISHASLINE);
        SPUtil.remove(Constants.FunctionConstant.ISHASSNAPCHAT);
        SPUtil.remove(Constants.FunctionConstant.ISHASSKYPE);
        SPUtil.remove(Constants.FunctionConstant.ISHASOTHERMESSENGER);
        SPUtil.remove(Constants.FunctionConstant.ISHASLONGSITTING);
        SPUtil.remove(Constants.FunctionConstant.ISHASANTILOST);
        SPUtil.remove(Constants.FunctionConstant.ISHASFINDPHONE);
        SPUtil.remove(Constants.FunctionConstant.ISHASFINDDEVICE);
        SPUtil.remove(Constants.FunctionConstant.ISHASFACTORYSETTING);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOODLEVEL);
        SPUtil.remove(Constants.FunctionConstant.ISHASNOTITOGGLE);
        SPUtil.remove(Constants.FunctionConstant.ISHASLIFTBRIGHT);
        SPUtil.remove(Constants.FunctionConstant.ISHASSKINCOLOR);
        SPUtil.remove(Constants.FunctionConstant.ISHASWECHATSPORT);
        SPUtil.remove(Constants.FunctionConstant.ISHASSEARCHAROUND);
        SPUtil.remove(Constants.FunctionConstant.ISHASTODAYWEATHER);
        SPUtil.remove(Constants.FunctionConstant.ISHASTOMORROWWEATHER);
        SPUtil.remove(Constants.FunctionConstant.ISHASECGDIAGNOSIS);
        SPUtil.remove(Constants.FunctionConstant.ISHASPHONESUPPORT);
        SPUtil.remove(Constants.FunctionConstant.ISHASENCRYPTION);
        SPUtil.remove(Constants.FunctionConstant.ISHASTEMPALARM);
        SPUtil.remove(Constants.FunctionConstant.ISHASTEMPAXILLARYTEST);
        SPUtil.remove(Constants.FunctionConstant.ISHASCVRR);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOODPRESSURECALIBRATION);
        SPUtil.remove(Constants.FunctionConstant.ISHASECGRIGHTELECTRODE);
        SPUtil.remove(Constants.FunctionConstant.ISHASTHEME);
        SPUtil.remove(Constants.FunctionConstant.ISHASMUSIC);
        SPUtil.remove(Constants.FunctionConstant.ISHASTEMP);
        SPUtil.remove(Constants.FunctionConstant.ISHASINACCURATEECG);
        SPUtil.remove(Constants.FunctionConstant.ISHASCONTACTS);
        SPUtil.remove(Constants.FunctionConstant.ISHASDIAL);
        SPUtil.remove(Constants.FunctionConstant.ISHASFEMALEPHYSIOLOGICALCYCLE);
        SPUtil.remove(Constants.FunctionConstant.ISHASSHAKETAKEPHOTO);
        SPUtil.remove(Constants.FunctionConstant.ISHASMANUALTAKEPHOTO);
        SPUtil.remove(Constants.FunctionConstant.ISHASSETINFO);
        SPUtil.remove(Constants.FunctionConstant.ISHASTEMPCALIBRATION);
        SPUtil.remove(Constants.FunctionConstant.ISHASREALTIMEMONITORINGMODE);
        SPUtil.remove(Constants.FunctionConstant.ISHASINDOORWALKING);
        SPUtil.remove(Constants.FunctionConstant.ISHASOUTDOORWALKING);
        SPUtil.remove(Constants.FunctionConstant.ISHASINDOORRUNING);
        SPUtil.remove(Constants.FunctionConstant.ISHASOUTDOORRUNING);
        SPUtil.remove(Constants.FunctionConstant.ISHASPINGPONG);
        SPUtil.remove(Constants.FunctionConstant.ISHASFOOTBALL);
        SPUtil.remove(Constants.FunctionConstant.ISHASMOUNTAINCLIMBING);
        SPUtil.remove(Constants.FunctionConstant.ISHASRUNNING);
        SPUtil.remove(Constants.FunctionConstant.ISHASFITNESS);
        SPUtil.remove(Constants.FunctionConstant.ISHASRIDING);
        SPUtil.remove(Constants.FunctionConstant.ISHASROPESKIPPING);
        SPUtil.remove(Constants.FunctionConstant.ISHASBASKETBALL);
        SPUtil.remove(Constants.FunctionConstant.ISHASSWIMMING);
        SPUtil.remove(Constants.FunctionConstant.ISHASWALKING);
        SPUtil.remove(Constants.FunctionConstant.ISHASBADMINTON);
        SPUtil.remove(Constants.FunctionConstant.ISHASONFOOT);
        SPUtil.remove(Constants.FunctionConstant.ISHASYOGA);
        SPUtil.remove(Constants.FunctionConstant.ISHASWEIGHTTRAINING);
        SPUtil.remove(Constants.FunctionConstant.ISHASJUMPING);
        SPUtil.remove(Constants.FunctionConstant.ISHASSITUPS);
        SPUtil.remove(Constants.FunctionConstant.ISHASROWINGMACHINE);
        SPUtil.remove(Constants.FunctionConstant.ISHASSTEPPER);
        SPUtil.remove(Constants.FunctionConstant.ISHASINDOORRIDING);
        SPUtil.remove(Constants.FunctionConstant.ISHASREALEXERCISEDATA);
        SPUtil.remove(Constants.FunctionConstant.ISHATESTHEART);
        SPUtil.remove(Constants.FunctionConstant.ISHASTESTBLOOD);
        SPUtil.remove(Constants.FunctionConstant.ISHASTESTSPO2);
        SPUtil.remove(Constants.FunctionConstant.ISHASTESTTEMP);
        SPUtil.remove(Constants.FunctionConstant.ISHASTESTRESPIRATIONRATE);
        SPUtil.remove(Constants.FunctionConstant.ISHASKINDSINFORMATIONPUSH);
        SPUtil.remove(Constants.FunctionConstant.ISHASCUSTOMDIAL);
        SPUtil.remove(Constants.FunctionConstant.ISHASINFLATED);
        SPUtil.remove(Constants.FunctionConstant.ISHASSOS);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOODOXYGENALARM);
        SPUtil.remove(Constants.FunctionConstant.ISHASUPLOADINFLATEBLOOD);
        SPUtil.remove(Constants.FunctionConstant.ISHASVIBERNOTIFY);
        SPUtil.remove(Constants.FunctionConstant.ISHASOTHRENOTIFY);
        SPUtil.remove(Constants.FunctionConstant.ISFLIPDIALIMAGE);
        SPUtil.remove(Constants.FunctionConstant.WATCHSCREENBRIGHTNESS);
        SPUtil.remove(Constants.FunctionConstant.ISHASVIBRATIONINTENSITY);
        SPUtil.remove(Constants.FunctionConstant.ISHASSETSCREENTIME);
        SPUtil.remove(Constants.FunctionConstant.ISHASWATCHSCREENBRIGHTNESS);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOODSUGAR);
        SPUtil.remove(Constants.FunctionConstant.ISHASPAUSEEXERCISE);
        SPUtil.remove(Constants.FunctionConstant.ISHASDRINKWATERREMINDER);
        SPUtil.remove(Constants.FunctionConstant.ISHASBUSINESSCARD);
        SPUtil.remove(Constants.FunctionConstant.ISHASURICACIDMEASUREMENT);
        SPUtil.remove(Constants.FunctionConstant.ISHASVOLLEYBALL);
        SPUtil.remove(Constants.FunctionConstant.ISHASKAYAK);
        SPUtil.remove(Constants.FunctionConstant.ISHASROLLERSKATING);
        SPUtil.remove(Constants.FunctionConstant.ISHASTENNIS);
        SPUtil.remove(Constants.FunctionConstant.ISHASGOLF);
        SPUtil.remove(Constants.FunctionConstant.ISHASELLIPTICALMACHINE);
        SPUtil.remove(Constants.FunctionConstant.ISHASDANCE);
        SPUtil.remove(Constants.FunctionConstant.ISHASROCKCLIMBING);
        SPUtil.remove(Constants.FunctionConstant.ISHASAEROBICS);
        SPUtil.remove(Constants.FunctionConstant.ISHASOTHERSPORTS);
        SPUtil.remove(Constants.FunctionConstant.ISHASBLOODKETONEMEASUREMENT);
        SPUtil.remove(Constants.FunctionConstant.ISHASALIIOT);
        SPUtil.remove(Constants.FunctionConstant.ISHASCREATEBOND);
        SPUtil.remove(Constants.FunctionConstant.ISHASRESPIRATORYRATEALARM);
        SPUtil.remove(Constants.FunctionConstant.ISHASIMPRECISEBLOODFAT);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_RECORDING_FILE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_PHYSIOTHERAPY);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_BATTERY_INFO_UPLOAD);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_PRESSURE_MEASUREMENT);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_OXYGENINTAKE_MEASUREMENT);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_BLOOD_FAT_MEASUREMENT);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_URIC_ACID_MEASUREMENT);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_START_PRAYER);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_GLUCOSE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_KETONE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_PRECISION_LIPIDS);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_PRECISION_URIC_ACID);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_Sporadic_Naps);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MeasurementFunction);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_AlgorithmicLicense);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_SEDENTARY_REPORT);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_HENGAI_BLOODPRESSURE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_HENGAI_EMOTION_PRESSURE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MF_BLOOD_OXYGEN);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE_ACCURATE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MF_HEART_RATE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MF_TEMPERATURE);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MF_ECG);
        SPUtil.remove(Constants.FunctionConstant.IS_HAS_MF_HRV);
    }

    public static void saveDeviceSupportFunctionData(byte[] bArr) {
        SPUtil.put(Constants.FunctionConstant.ISHASSTEPCOUNT, Integer.valueOf((bArr[0] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSLEEP, Integer.valueOf((bArr[0] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASREALDATA, Integer.valueOf((bArr[0] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFIRMWAREUPDATE, Integer.valueOf((bArr[0] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASHEARTRATE, Integer.valueOf((bArr[0] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASINFORMATION, Integer.valueOf((bArr[0] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMANYLANGUAGE, Integer.valueOf((bArr[0] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASBLOOD, Integer.valueOf(bArr[0] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASHEARTALARM, Integer.valueOf((bArr[1] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASBLOODALARM, Integer.valueOf((bArr[1] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASECGREALUPLOAD, Integer.valueOf((bArr[1] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASECGHISTORYUPLOAD, Integer.valueOf((bArr[1] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASBLOODOXYGEN, Integer.valueOf((bArr[1] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASRESPIRATORYRATE, Integer.valueOf((bArr[1] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASHRV, Integer.valueOf((bArr[1] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMORESPORT, Integer.valueOf(bArr[1] & 1));
        SPUtil.put(Constants.FunctionConstant.ALARMCOUNT, Integer.valueOf(bArr[2] & 255));
        SPUtil.put(Constants.FunctionConstant.ISHASCUSTOM, Integer.valueOf((bArr[3] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMEETING, Integer.valueOf((bArr[3] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASPARTY, Integer.valueOf((bArr[3] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASAPPOINT, Integer.valueOf((bArr[3] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTAKEPILLS, Integer.valueOf((bArr[3] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTAKEEXERCISE, Integer.valueOf((bArr[3] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTAKESLEEP, Integer.valueOf((bArr[3] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASGETUP, Integer.valueOf(bArr[3] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASCALLPHONE, Integer.valueOf((bArr[4] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMESSAGE, Integer.valueOf((bArr[4] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASEMAIL, Integer.valueOf((bArr[4] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASQQ, Integer.valueOf((bArr[4] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASWECHAT, Integer.valueOf((bArr[4] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSINA, Integer.valueOf((bArr[4] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFACEBOOK, Integer.valueOf((bArr[4] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTWITTER, Integer.valueOf(bArr[4] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASWHATSAPP, Integer.valueOf((bArr[5] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMESSENGER, Integer.valueOf((bArr[5] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASINSTAGRAM, Integer.valueOf((bArr[5] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASLINKEDIN, Integer.valueOf((bArr[5] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASLINE, Integer.valueOf((bArr[5] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSNAPCHAT, Integer.valueOf((bArr[5] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSKYPE, Integer.valueOf((bArr[5] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASOTHERMESSENGER, Integer.valueOf(bArr[5] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASLONGSITTING, Integer.valueOf((bArr[6] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASANTILOST, Integer.valueOf((bArr[6] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFINDPHONE, Integer.valueOf((bArr[6] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFINDDEVICE, Integer.valueOf((bArr[6] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFACTORYSETTING, Integer.valueOf((bArr[6] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASBLOODLEVEL, Integer.valueOf((bArr[6] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASNOTITOGGLE, Integer.valueOf((bArr[6] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASLIFTBRIGHT, Integer.valueOf(bArr[6] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSKINCOLOR, Integer.valueOf((bArr[7] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASWECHATSPORT, Integer.valueOf((bArr[7] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSEARCHAROUND, Integer.valueOf((bArr[7] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTODAYWEATHER, Integer.valueOf((bArr[7] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTOMORROWWEATHER, Integer.valueOf((bArr[7] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASECGDIAGNOSIS, Integer.valueOf((bArr[7] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASPHONESUPPORT, Integer.valueOf((bArr[7] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASENCRYPTION, Integer.valueOf(bArr[7] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTEMPALARM, Integer.valueOf((bArr[8] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTEMPAXILLARYTEST, Integer.valueOf((bArr[8] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASCVRR, Integer.valueOf((bArr[8] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASBLOODPRESSURECALIBRATION, Integer.valueOf((bArr[8] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASECGRIGHTELECTRODE, Integer.valueOf((bArr[8] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTHEME, Integer.valueOf((bArr[8] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMUSIC, Integer.valueOf((bArr[8] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTEMP, Integer.valueOf(bArr[8] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASINACCURATEECG, Integer.valueOf((bArr[9] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASCONTACTS, Integer.valueOf((bArr[9] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASDIAL, Integer.valueOf((bArr[9] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFEMALEPHYSIOLOGICALCYCLE, Integer.valueOf((bArr[9] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSHAKETAKEPHOTO, Integer.valueOf((bArr[9] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMANUALTAKEPHOTO, Integer.valueOf((bArr[9] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSETINFO, Integer.valueOf((bArr[9] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASTEMPCALIBRATION, Integer.valueOf(bArr[9] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASREALTIMEMONITORINGMODE, Integer.valueOf((bArr[10] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASINDOORWALKING, Integer.valueOf((bArr[10] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASOUTDOORWALKING, Integer.valueOf((bArr[10] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASINDOORRUNING, Integer.valueOf((bArr[10] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASOUTDOORRUNING, Integer.valueOf((bArr[10] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASPINGPONG, Integer.valueOf((bArr[10] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFOOTBALL, Integer.valueOf((bArr[10] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASMOUNTAINCLIMBING, Integer.valueOf(bArr[10] & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASRUNNING, Integer.valueOf((bArr[11] >> 7) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASFITNESS, Integer.valueOf((bArr[11] >> 6) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASRIDING, Integer.valueOf((bArr[11] >> 5) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASROPESKIPPING, Integer.valueOf((bArr[11] >> 4) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASBASKETBALL, Integer.valueOf((bArr[11] >> 3) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASSWIMMING, Integer.valueOf((bArr[11] >> 2) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASWALKING, Integer.valueOf((bArr[11] >> 1) & 1));
        SPUtil.put(Constants.FunctionConstant.ISHASBADMINTON, Integer.valueOf(bArr[11] & 1));
        if (bArr.length >= 18) {
            if (bArr.length >= 20) {
                SPUtil.put(Constants.FunctionConstant.ISHASONFOOT, Integer.valueOf((bArr[14] >> 7) & 1));
            }
            SPUtil.put(Constants.FunctionConstant.ISHASYOGA, Integer.valueOf((bArr[14] >> 6) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASWEIGHTTRAINING, Integer.valueOf((bArr[14] >> 5) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASJUMPING, Integer.valueOf((bArr[14] >> 4) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASSITUPS, Integer.valueOf((bArr[14] >> 3) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASROWINGMACHINE, Integer.valueOf((bArr[14] >> 2) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASSTEPPER, Integer.valueOf((bArr[14] >> 1) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASINDOORRIDING, Integer.valueOf(bArr[14] & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASREALEXERCISEDATA, Integer.valueOf(bArr[15] & 1));
            SPUtil.put(Constants.FunctionConstant.ISHATESTHEART, Integer.valueOf((bArr[15] >> 1) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASTESTBLOOD, Integer.valueOf((bArr[15] >> 2) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASTESTSPO2, Integer.valueOf((bArr[15] >> 3) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASTESTTEMP, Integer.valueOf((bArr[15] >> 4) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASTESTRESPIRATIONRATE, Integer.valueOf((bArr[15] >> 5) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASKINDSINFORMATIONPUSH, Integer.valueOf((bArr[15] >> 6) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASCUSTOMDIAL, Integer.valueOf((bArr[15] >> 7) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASINFLATED, Integer.valueOf(bArr[16] & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASSOS, Integer.valueOf((bArr[16] >> 1) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASBLOODOXYGENALARM, Integer.valueOf((bArr[16] >> 2) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASUPLOADINFLATEBLOOD, Integer.valueOf((bArr[16] >> 3) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASVIBERNOTIFY, Integer.valueOf((bArr[16] >> 4) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASOTHRENOTIFY, Integer.valueOf((bArr[16] >> 5) & 1));
            SPUtil.put(Constants.FunctionConstant.ISFLIPDIALIMAGE, Integer.valueOf((bArr[16] >> 6) & 1));
            SPUtil.put(Constants.FunctionConstant.WATCHSCREENBRIGHTNESS, Integer.valueOf((bArr[16] >> 7) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASVIBRATIONINTENSITY, Integer.valueOf(bArr[17] & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASSETSCREENTIME, Integer.valueOf((bArr[17] >> 1) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASWATCHSCREENBRIGHTNESS, Integer.valueOf(((bArr[17] >> 2) & 1) ^ 1));
            SPUtil.put(Constants.FunctionConstant.ISHASBLOODSUGAR, Integer.valueOf((bArr[17] >> 3) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASPAUSEEXERCISE, Integer.valueOf((bArr[17] >> 4) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASDRINKWATERREMINDER, Integer.valueOf((bArr[17] >> 5) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASBUSINESSCARD, Integer.valueOf((bArr[17] >> 6) & 1));
            SPUtil.put(Constants.FunctionConstant.ISHASURICACIDMEASUREMENT, Integer.valueOf((bArr[17] >> 7) & 1));
            if (bArr.length >= 20) {
                SPUtil.put(Constants.FunctionConstant.ISHASVOLLEYBALL, Integer.valueOf(bArr[18] & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASKAYAK, Integer.valueOf((bArr[18] >> 1) & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASROLLERSKATING, Integer.valueOf((bArr[18] >> 2) & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASTENNIS, Integer.valueOf((bArr[18] >> 3) & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASGOLF, Integer.valueOf((bArr[18] >> 4) & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASELLIPTICALMACHINE, Integer.valueOf((bArr[18] >> 5) & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASDANCE, Integer.valueOf((bArr[18] >> 6) & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASROCKCLIMBING, Integer.valueOf((bArr[18] >> 7) & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASAEROBICS, Integer.valueOf(bArr[19] & 1));
                SPUtil.put(Constants.FunctionConstant.ISHASOTHERSPORTS, Integer.valueOf((bArr[19] >> 1) & 1));
                if (bArr.length >= 21) {
                    SPUtil.put(Constants.FunctionConstant.ISHASBLOODKETONEMEASUREMENT, Integer.valueOf(bArr[20] & 1));
                    SPUtil.put(Constants.FunctionConstant.ISHASALIIOT, Integer.valueOf((bArr[20] >> 1) & 1));
                    SPUtil.put(Constants.FunctionConstant.ISHASCREATEBOND, Integer.valueOf((bArr[20] >> 2) & 1));
                    SPUtil.put(Constants.FunctionConstant.ISHASRESPIRATORYRATEALARM, Integer.valueOf((bArr[20] >> 3) & 1));
                    SPUtil.put(Constants.FunctionConstant.ISHASIMPRECISEBLOODFAT, Integer.valueOf((bArr[20] >> 4) & 1));
                    SPUtil.put(Constants.FunctionConstant.IS_HAS_INDEPENDENT_AUTOMATIC_TIME_MEASUREMENT, Integer.valueOf((bArr[20] >> 5) & 1));
                    SPUtil.put(Constants.FunctionConstant.IS_HAS_RECORDING_FILE, Integer.valueOf((bArr[20] >> 6) & 1));
                    SPUtil.put(Constants.FunctionConstant.IS_HAS_PHYSIOTHERAPY, Integer.valueOf((bArr[20] >> 7) & 1));
                    if (bArr.length >= 22) {
                        SPUtil.put(Constants.FunctionConstant.ISHASZOOMNOTIFY, Integer.valueOf(bArr[21] & 1));
                        SPUtil.put(Constants.FunctionConstant.ISHASTIKTOKNOTIFY, Integer.valueOf((bArr[21] >> 1) & 1));
                        SPUtil.put(Constants.FunctionConstant.ISHASKAKAOTALKNOTIFY, Integer.valueOf((bArr[21] >> 2) & 1));
                    }
                    if (bArr.length >= 23) {
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_SLEEP_REMIND, Integer.valueOf(bArr[22] & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_DEVICE_SPEC, Integer.valueOf((bArr[22] >> 1) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_LOCAL_SPORT_DATA, Integer.valueOf((bArr[22] >> 2) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_LOGO, Integer.valueOf((bArr[22] >> 3) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_MOTION_DELAY_DISCONNECT, Integer.valueOf((bArr[22] >> 4) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_BATTERY_INFO_UPLOAD, Integer.valueOf((bArr[22] >> 5) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_PRESSURE, Integer.valueOf((bArr[22] >> 6) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MAXIMAL_OXYGEN_INTAKE, Integer.valueOf((bArr[22] >> 7) & 1));
                    }
                    if (bArr.length >= 24) {
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_HRV_MEASUREMENT, Integer.valueOf(bArr[23] & 1));
                        SPUtil.put("isHasBloodSugarMeasurement", Integer.valueOf((bArr[23] >> 1) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_PRESSURE_MEASUREMENT, Integer.valueOf((bArr[23] >> 2) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_OXYGENINTAKE_MEASUREMENT, Integer.valueOf((bArr[23] >> 3) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_BLOOD_FAT_MEASUREMENT, Integer.valueOf((bArr[23] >> 4) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_URIC_ACID_MEASUREMENT, Integer.valueOf((bArr[23] >> 5) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_START_PRAYER, Integer.valueOf((bArr[23] >> 6) & 1));
                    }
                    if (bArr.length >= 25) {
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_GLUCOSE, Integer.valueOf(bArr[24] & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_PRECISION_LIPIDS, Integer.valueOf((bArr[24] >> 1) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_PRECISION_URIC_ACID, Integer.valueOf((bArr[24] >> 2) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_PRECISION_BLOOD_KETONE, Integer.valueOf((bArr[24] >> 3) & 1));
                    }
                    if (bArr.length >= 26) {
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_Sporadic_Naps, Integer.valueOf(bArr[25] & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MeasurementFunction, Integer.valueOf((bArr[25] >> 1) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_AlgorithmicLicense, Integer.valueOf((bArr[25] >> 3) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_SEDENTARY_REPORT, Integer.valueOf((bArr[25] >> 4) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_HENGAI_BLOODPRESSURE, Integer.valueOf((bArr[25] >> 5) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_HENGAI_EMOTION_PRESSURE, Integer.valueOf((bArr[25] >> 6) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MF_BLOOD_OXYGEN, Integer.valueOf((bArr[25] >> 7) & 1));
                    }
                    if (bArr.length >= 27) {
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE, Integer.valueOf(bArr[26] & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MF_HEART_RATE, Integer.valueOf((bArr[26] >> 1) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MF_BLOOD_PRESSURE_ACCURATE, Integer.valueOf((bArr[26] >> 2) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MF_ECG, Integer.valueOf((bArr[26] >> 3) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MF_HRV, Integer.valueOf((bArr[26] >> 4) & 1));
                        SPUtil.put(Constants.FunctionConstant.IS_HAS_MF_TEMPERATURE, Integer.valueOf((bArr[26] >> 5) & 1));
                    }
                }
            }
        }
    }

    public static HashMap unpackAlarmData(byte[] bArr) {
        HashMap map = new HashMap();
        int i2 = 0;
        map.put("code", 0);
        byte b2 = bArr[0];
        int i3 = 3;
        if (b2 < 1 || b2 > 3) {
            byte b3 = bArr[1];
            byte b4 = bArr[2];
            YCBTLog.e("支持闹钟数量" + ((int) b3) + "已设置闹钟数据:" + ((int) b4));
            ArrayList arrayList = new ArrayList();
            if (b4 > 0) {
                while (i2 < b4) {
                    int i4 = bArr[i3] & 255;
                    int i5 = bArr[i3 + 1] & 255;
                    int i6 = bArr[i3 + 2] & 255;
                    int i7 = bArr[i3 + 3] & 255;
                    int i8 = bArr[i3 + 4] & 255;
                    HashMap map2 = new HashMap();
                    map2.put("alarmType", Integer.valueOf(i4));
                    map2.put("alarmHour", Integer.valueOf(i5));
                    map2.put("alarmMin", Integer.valueOf(i6));
                    map2.put("alarmRepeat", Integer.valueOf(i7));
                    map2.put("alarmDelayTime", Integer.valueOf(i8));
                    arrayList.add(map2);
                    i2++;
                    i3 += 5;
                }
            }
            map.put("data", arrayList);
            map.put("tSupportAlarmNum", Integer.valueOf(b3));
            map.put("tSettedAlarmNum", Integer.valueOf(b4));
            map.put("optType", Integer.valueOf(b2));
            map.put("dataType", 257);
        } else {
            byte b5 = bArr[1];
            map.put("optType", Integer.valueOf(b2));
            map.put("code", Integer.valueOf(b5));
            map.put("dataType", 257);
        }
        return map;
    }

    public static HashMap unpackAppEcgPpgStatus(byte[] bArr) {
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        YCBTLog.e("心电电极状态: " + i2 + "光电传感器状态: " + i3);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.AppECGPPGStatus));
        map.put("EcgStatus", Integer.valueOf(i2));
        map.put("PPGStatus", Integer.valueOf(i3));
        return map;
    }

    public static HashMap unpackBodyData(byte[] bArr) {
        float f2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        HashMap map = new HashMap();
        if (bArr != null) {
            int i7 = bArr[0] & 255;
            int i8 = bArr[1] & 255;
            int i9 = bArr[2] & 255;
            int i10 = bArr[3] & 255;
            int i11 = bArr[4] & 255;
            int i12 = bArr[5] & 255;
            int i13 = bArr[6] & 255;
            int i14 = bArr[7] & 255;
            int i15 = bArr[8] & 255;
            int i16 = bArr[9] & 255;
            int i17 = (bArr[10] & 255) + ((bArr[11] & 255) << 8);
            if (bArr.length >= 21) {
                int i18 = bArr[12] & 255;
                i3 = bArr[13] & 255;
                i5 = i18;
                i6 = ((bArr[15] & 255) << 8) + (bArr[14] & 255);
                int i19 = ((bArr[17] & 255) << 8) + (bArr[16] & 255);
                i2 = ((bArr[19] & 255) << 8) + (bArr[18] & 255);
                f2 = (bArr[20] & 255) / 10.0f;
                i4 = i19;
            } else {
                f2 = 0.0f;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
            }
            map.put("loadIndexInteger", Integer.valueOf(i7));
            map.put("loadIndexFloat", Integer.valueOf(i8));
            map.put("hrvInteger", Integer.valueOf(i9));
            map.put("hrvFloat", Integer.valueOf(i10));
            map.put("pressureInteger", Integer.valueOf(i11));
            map.put("pressureFloat", Integer.valueOf(i12));
            map.put("bodyInteger", Integer.valueOf(i13));
            map.put("bodyFloat", Integer.valueOf(i14));
            map.put("sympatheticInteger", Integer.valueOf(i15));
            map.put("sympatheticFloat", Integer.valueOf(i16));
            map.put("sdn", Integer.valueOf(i17));
            map.put("maximalOxygenIntake", Integer.valueOf(i5));
            map.put("pnn50", Integer.valueOf(i3));
            map.put("rmssd", Integer.valueOf(i6));
            map.put("lf", Integer.valueOf(i4));
            map.put("hf", Integer.valueOf(i2));
            map.put("lfHfRate", Float.valueOf(f2));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadBodyData));
        return map;
    }

    public static HashMap unpackCollectSummaryInfo(byte[] bArr) {
        int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
        int i2 = bArr[0] & 255;
        int i3 = (bArr[1] & 255) + ((bArr[2] & 255) << 8);
        long j2 = (bArr[3] & 255) + ((bArr[4] & 255) << 8) + ((bArr[5] & 255) << 16) + ((bArr[6] & 255) << 24);
        long j3 = (946684800 + j2) * 1000;
        int i4 = (bArr[7] & 255) + ((bArr[8] & 255) << 8);
        int i5 = bArr[9] & 255;
        long j4 = (bArr[10] & 255) + ((bArr[11] & 255) << 8) + ((bArr[12] & 255) << 16) + ((bArr[13] & 255) << 24);
        int i6 = (bArr[14] & 255) + ((bArr[15] & 255) << 8);
        YCBTLog.e("SN=" + i3 + " tStartTime=" + j2 + " realTime=" + j3 + " tDataTotalLen=" + j4 + " collectBlockNum=" + i6 + " collectDigits=" + i5 + " samplingRate=" + i4);
        HashMap map = new HashMap();
        map.put("collectType", Integer.valueOf(i2));
        map.put("collectSN", Integer.valueOf(i3));
        map.put("collectSendTime", Long.valueOf(j2));
        map.put("collectStartTime", Long.valueOf(j3 - offset));
        map.put("collectTotalLen", Long.valueOf(j4));
        map.put("collectBlockNum", Integer.valueOf(i6));
        map.put("collectDigits", Integer.valueOf(i5));
        map.put("samplingRate", Integer.valueOf(i4));
        return map;
    }

    public static HashMap unpackContacts(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length > 1) {
            map.put("data", Integer.valueOf(bArr[1] & 255));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.AppPushContacts));
        return map;
    }

    public static HashMap<String, Object> unpackCustomizeCGM(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        try {
            if ((bArr[0] & 255) == 1) {
                byte[] bArr2 = new byte[16];
                System.arraycopy(bArr, 5, bArr2, 0, 16);
                map.put("startTime", Long.valueOf(((((((bArr[1] & 255) + ((bArr[2] & 255) << 8)) + ((bArr[3] & 255) << 16)) + ((bArr[4] & 255) << 24)) + 946684800) * 1000) - TimeZone.getDefault().getOffset(System.currentTimeMillis())));
                map.put("serial", ByteUtil.byteToStr(bArr2));
            }
        } catch (Exception e2) {
            map.put("code", 1);
            e2.printStackTrace();
        }
        return map;
    }

    public static HashMap<String, Object> unpackCustomizeData(byte[] bArr) {
        HashMap<String, Object> map;
        HashMap<String, Object> map2 = new HashMap<>();
        char c2 = 0;
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        map2.put("dataType", 3445);
        map2.put("code", 0);
        map2.put("opcode", Integer.valueOf(i2));
        map2.put("type", Integer.valueOf(i3));
        int i4 = 8;
        if (i2 == 1) {
            int i5 = bArr[2] & 255;
            int i6 = (bArr[3] & 255) + ((bArr[4] & 255) << 8) + ((bArr[5] & 255) << 16) + ((bArr[6] & 255) << 24);
            int i7 = (bArr[7] & 255) + ((bArr[8] & 255) << 8);
            int i8 = (bArr[9] & 255) + ((bArr[10] & 255) << 8) + ((bArr[11] & 255) << 16) + ((bArr[12] & 255) << 24);
            YCBTLog.e(i5 + ":" + i6 + ":" + i7 + ":" + i8);
            map2.put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(i5));
            map2.put(AlbumLoader.COLUMN_COUNT, Integer.valueOf(i6));
            map2.put("packageNum", Integer.valueOf(i7));
            map2.put("total", Integer.valueOf(i8));
            totalCount = i7;
            progress = 0;
            mBlockArray.clear();
            mBlockArray.add(117);
            return map2;
        }
        if (i2 == 2) {
            progress++;
            mBlockArray.add(bArr);
            map2.put("progress", String.format("%.1f", Float.valueOf((progress * 1.0f) / (totalCount * 100.0f))));
            return map2;
        }
        if (i2 == 3) {
            map2.put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(bArr[2] & 255));
            return map2;
        }
        if (i2 != 128) {
            return map2;
        }
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        int i9 = (bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24);
        int i10 = (bArr[8] & 255) + ((bArr[9] & 255) << 8);
        byte[] bArr2 = new byte[i9];
        ((Integer) mBlockArray.get(0)).intValue();
        int i11 = 0;
        int i12 = 1;
        while (i12 < mBlockArray.size()) {
            byte[] bArr3 = (byte[]) mBlockArray.get(i12);
            byte b4 = bArr3[c2];
            byte b5 = bArr3[1];
            int i13 = (bArr3[2] & 255) + ((bArr3[3] & 255) << 8);
            System.arraycopy(bArr3, 4, bArr2, i11, i13);
            i11 += i13;
            i12++;
            c2 = 0;
        }
        if (ByteUtil.crc16_compute(bArr2, i11) == i10) {
            ArrayList arrayList = new ArrayList();
            if (i3 == 1) {
                int i14 = 0;
                while (true) {
                    int i15 = i14 + 6;
                    if (i15 > i9) {
                        break;
                    }
                    HashMap map3 = new HashMap();
                    int i16 = (bArr2[i14] & 255) + ((bArr2[i14 + 1] & 255) << 8);
                    int i17 = (bArr2[i14 + 2] & 255) + ((bArr2[i14 + 3] & 255) << 8);
                    int i18 = (bArr2[i14 + 4] & 255) + ((bArr2[i14 + 5] & 255) << 8);
                    map3.put(TypedValues.CycleType.S_WAVE_OFFSET, Integer.valueOf(i16));
                    map3.put("cgm", Float.valueOf((i17 * 1.0f) / 100.0f));
                    map3.put("ele", Float.valueOf((i18 * 1.0f) / 100.0f));
                    arrayList.add(map3);
                    i14 = i15;
                }
            } else if (i3 == 2) {
                int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
                int i19 = 0;
                while (true) {
                    int i20 = i19 + 12;
                    if (i20 > i9) {
                        break;
                    }
                    HashMap map4 = new HashMap();
                    long j2 = (bArr2[i19] & 255) + ((bArr2[i19 + 1] & 255) << i4) + ((bArr2[i19 + 2] & 255) << 16) + ((bArr2[i19 + 3] & 255) << 24);
                    YCBTLog.e("physiotherapyStartTime=" + j2 + " 946684800");
                    long j3 = (j2 + 946684800) * 1000;
                    int i21 = (bArr2[i19 + 4] & 255) + ((bArr2[i19 + 5] & 255) << i4) + ((bArr2[i19 + 6] & 255) << 16) + ((bArr2[i19 + 7] & 255) << 24);
                    int i22 = bArr2[i19 + 8] & 255;
                    int i23 = bArr2[i19 + 9] & 255;
                    int i24 = bArr2[i19 + 10] & 255;
                    int i25 = bArr2[i19 + 11] & 255;
                    YCBTLog.e("physiotherapyStartTime=" + j3 + StringUtils.SPACE + offset);
                    map4.put("physiotherapyStartTime", Long.valueOf(j3 - offset));
                    map4.put("physiotherapyDuration", Integer.valueOf(i21));
                    map4.put("physiotherapyType", Integer.valueOf(i22));
                    map4.put("physiotherapyStartType", Integer.valueOf(i23));
                    map4.put("physiotherapyPowerLevel", Integer.valueOf(i24));
                    map4.put("physiotherapyDurationLevel", Integer.valueOf(i25));
                    arrayList.add(map4);
                    i19 = i20;
                    bArr2 = bArr2;
                    map2 = map2;
                    i4 = 8;
                }
            }
            map = map2;
            map.put("data", arrayList);
        } else {
            map = map2;
            map.put("code", 1);
        }
        mBlockArray.clear();
        return map;
    }

    public static HashMap unpackDeviceInfoData(byte[] bArr) {
        HashMap map;
        HashMap map2 = new HashMap();
        int i2 = 0;
        map2.put("code", 0);
        int i3 = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
        int i4 = bArr[2] & 255;
        int i5 = bArr[3] & 255;
        int i6 = bArr[4] & 255;
        int i7 = bArr[5] & 255;
        int i8 = bArr[6] & 255;
        int i9 = bArr[7] & 255;
        YCBTLog.e("设备ID " + i3 + " 版本号 " + i5 + "." + i4 + " 电量 " + i7 + "--state==" + i6);
        String str = i4 < 10 ? i5 + ".0" + i4 : i5 + "." + i4;
        SPUtil.saveBindedDeviceVersion(str);
        SPUtil.saveDeviceBatteryState(i6);
        SPUtil.saveDeviceBatteryValue(i7);
        HashMap map3 = new HashMap();
        map3.put("deviceId", Integer.valueOf(i3));
        map3.put(Constant.SpConstKey.deviceVersion, str);
        map3.put("deviceBatteryState", Integer.valueOf(i6));
        map3.put("deviceBatteryValue", Integer.valueOf(i7));
        map3.put("deviceMainVersion", Integer.valueOf(i5));
        map3.put("deviceSubVersion", Integer.valueOf(i4));
        map3.put("devicetBindState", Integer.valueOf(i8));
        map3.put("devicetSyncState", Integer.valueOf(i9));
        if (bArr.length >= 24) {
            int i10 = bArr[8] & 255;
            int i11 = bArr[9] & 255;
            int i12 = bArr[10] & 255;
            int i13 = bArr[11] & 255;
            int i14 = bArr[12] & 255;
            int i15 = bArr[13] & 255;
            int i16 = bArr[14] & 255;
            int i17 = bArr[15] & 255;
            int i18 = bArr[16] & 255;
            int i19 = bArr[17] & 255;
            int i20 = bArr[18] & 255;
            map = map2;
            map3.put("bleAgreementSubVersion", Integer.valueOf(i10));
            map3.put("bleAgreementMainVersion", Integer.valueOf(i11));
            map3.put("bloodAlgoSubVersion", Integer.valueOf(i12));
            map3.put("bloodAlgoMainVersion", Integer.valueOf(i13));
            map3.put("tpSubVersion", Integer.valueOf(i14));
            map3.put("tpMainVersion", Integer.valueOf(i15));
            map3.put("bloodSugarSubVersion", Integer.valueOf(i16));
            map3.put("bloodSugarMainVersion", Integer.valueOf(i17));
            map3.put("uiSubVersion", Integer.valueOf(i18));
            map3.put("uiMainVersion", Integer.valueOf(i19));
            map3.put("hardwareType", Integer.valueOf(i20));
            if (i17 == 0 && i16 == 0) {
                SPUtil.saveBloodSugarVersion("");
            } else {
                SPUtil.saveBloodSugarVersion(i17 + "." + i16);
            }
            i2 = i20;
        } else {
            map = map2;
            SPUtil.saveBloodSugarVersion("");
        }
        SPUtil.saveHardwareType(i2);
        map3.put("hardwareType", Integer.valueOf(i2));
        HashMap map4 = map;
        map4.put("dataType", 512);
        map4.put("data", map3);
        return map4;
    }

    public static HashMap unpackDeviceName(byte[] bArr) {
        int i2 = 0;
        while (true) {
            if (i2 >= bArr.length) {
                i2 = 0;
                break;
            }
            if ((bArr[i2] & 255) == 0) {
                break;
            }
            i2++;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        String str = new String(bArr2);
        YCBTLog.e("DeviceName:".concat(str));
        SPUtil.put(Constants.FunctionConstant.DEVICETYPE, str);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", 515);
        map.put("data", str);
        return map;
    }

    public static HashMap unpackDeviceScreenInfo(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        if (bArr.length >= 8) {
            int i2 = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
            int i3 = (bArr[2] & 255) + ((bArr[3] & 255) << 8);
            int i4 = (bArr[4] & 255) + ((bArr[5] & 255) << 8);
            int i5 = (bArr[6] & 255) + ((bArr[7] & 255) << 8);
            int i6 = (int) ((i3 / i5) * (i2 / i4) * 0.8d);
            map.put(AlbumLoader.COLUMN_COUNT, Integer.valueOf(i6));
            System.out.println("chong------with==" + i2 + "--" + i3 + "--" + i4 + "--" + i5 + "--" + i6);
        }
        map.put("dataType", 523);
        return map;
    }

    public static HashMap unpackDeviceUserConfigData(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", 519);
        if (bArr.length >= 54) {
            HashMap map2 = new HashMap();
            map2.put("stepTarget", Integer.valueOf((bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16)));
            map2.put("calorTarget", Integer.valueOf((bArr[3] & 255) + ((bArr[4] & 255) << 8) + ((bArr[5] & 255) << 16)));
            map2.put("distanceTarget", Integer.valueOf((bArr[6] & 255) + ((bArr[7] & 255) << 8) + ((bArr[8] & 255) << 16)));
            map2.put("sleepTarget", Integer.valueOf((bArr[9] & 255) + ((bArr[10] & 255) << 8)));
            map2.put("userHeight", Integer.valueOf(bArr[11] & 255));
            map2.put("userWeight", Integer.valueOf(bArr[12] & 255));
            map2.put("userSex", Integer.valueOf(bArr[13] & 255));
            map2.put("userAge", Integer.valueOf(bArr[14] & 255));
            map2.put("distanceUnit", Integer.valueOf(bArr[15] & 255));
            map2.put("weightUnit", Integer.valueOf(bArr[16] & 255));
            map2.put("tempUnit", Integer.valueOf(bArr[17] & 255));
            map2.put("timeUnit", Integer.valueOf(bArr[18] & 255));
            map2.put("longSitStartHour1", Integer.valueOf(bArr[19] & 255));
            map2.put("longSitStartMin1", Integer.valueOf(bArr[20] & 255));
            map2.put("longSitEndHour1", Integer.valueOf(bArr[21] & 255));
            map2.put("longSitEndMin1", Integer.valueOf(bArr[22] & 255));
            map2.put("longSitStartHour2", Integer.valueOf(bArr[23] & 255));
            map2.put("longSitStartMin2", Integer.valueOf(bArr[24] & 255));
            map2.put("longSitEndHour2", Integer.valueOf(bArr[25] & 255));
            map2.put("longSitEndMin2", Integer.valueOf(bArr[26] & 255));
            map2.put("longSitInterval", Integer.valueOf(bArr[27] & 255));
            map2.put("longSitRepeat", Integer.valueOf(bArr[28] & 255));
            map2.put("antiLostType", Integer.valueOf(bArr[29] & 255));
            map2.put("antiLostRssi", Integer.valueOf(bArr[30] & 255));
            map2.put("antiLostDelay", Integer.valueOf(bArr[31] & 255));
            map2.put("antiLostDisDelay", Integer.valueOf(bArr[32] & 255));
            map2.put("antiLostRepeat", Integer.valueOf(bArr[33] & 255));
            map2.put("messageTotalSwitch", Integer.valueOf(bArr[34] & 255));
            map2.put("messageSwitch0", Integer.valueOf(bArr[35] & 255));
            map2.put("messageSwitch1", Integer.valueOf(bArr[36] & 255));
            map2.put("heartHand", Integer.valueOf(bArr[37] & 255));
            map2.put("heartAlarmSwitch", Integer.valueOf(bArr[38] & 255));
            map2.put("heartAlarmValue", Integer.valueOf(bArr[39] & 255));
            map2.put("heartMonitorTye", Integer.valueOf(bArr[40] & 255));
            map2.put("heartMonitorInterval", Integer.valueOf(bArr[41] & 255));
            map2.put("language", Integer.valueOf(bArr[42] & 255));
            map2.put("handupswitch", Integer.valueOf(bArr[43] & 255));
            map2.put("screenval", Integer.valueOf(bArr[44] & 255));
            map2.put("skincolour", Integer.valueOf(bArr[45] & 255));
            map2.put("screendown", Integer.valueOf(bArr[46] & 255));
            map2.put("bluebreakswitch", Integer.valueOf(bArr[47] & 255));
            map2.put("datauploadswitch", Integer.valueOf(bArr[48] & 255));
            map2.put("disturbswitch", Integer.valueOf(bArr[49] & 255));
            map2.put("disturbbegintimehour", Integer.valueOf(bArr[50] & 255));
            map2.put("disturbbegintimemin", Integer.valueOf(bArr[51] & 255));
            map2.put("disturbendtimehour", Integer.valueOf(bArr[52] & 255));
            map2.put("disturbendtimemin", Integer.valueOf(bArr[53] & 255));
            if (bArr.length >= 65) {
                map2.put("sleepswitch", Integer.valueOf(bArr[54] & 255));
                map2.put("sleeptimehour", Integer.valueOf(bArr[55] & 255));
                map2.put("sleeptimemin", Integer.valueOf(bArr[56] & 255));
                map2.put("scheduleswitch", Integer.valueOf(bArr[57] & 255));
                map2.put("eventswitch", Integer.valueOf(bArr[58] & 255));
                map2.put("accidentswitch", Integer.valueOf(bArr[59] & 255));
                map2.put("tempswitch", Integer.valueOf(bArr[60] & 255));
            }
            map.put("data", map2);
        }
        return map;
    }

    public static HashMap unpackDialInfo(byte[] bArr) {
        if (bArr.length < 3) {
            return null;
        }
        int i2 = (bArr[0] & 255) - (YCBTClient.isSupportFunction(Constants.FunctionConstant.ISHASCUSTOMDIAL) ? 1 : 0);
        int i3 = bArr[1] & 255;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i4 = 2; i4 < bArr.length - 5; i4 += 9) {
            int i5 = bArr[i4] & 255;
            int i6 = bArr[i4 + 1] & 255;
            int i7 = bArr[i4 + 2] & 255;
            int i8 = bArr[i4 + 3] & 255;
            DialsBean dialsBean = new DialsBean();
            dialsBean.dialplateId = i5 + (i6 << 8) + (i7 << 16) + (i8 << 24);
            dialsBean.blockNumber = (bArr[i4 + 4] & 255) + ((bArr[i4 + 5] & 255) << 8);
            int i9 = i4 + 7;
            dialsBean.isCanDelete = (bArr[i4 + 6] & 255) == 1;
            dialsBean.dialVersion = (bArr[i9] & 255) + ((bArr[i4 + 8] & 255) << 8);
            if (i8 == 127 && i6 == 255 && i7 == 255) {
                arrayList2.add(dialsBean);
            } else {
                arrayList.add(dialsBean);
            }
        }
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", 2307);
        map.put("maxDials", Integer.valueOf(i2));
        map.put("currDials", Integer.valueOf(i3));
        map.put("dials", arrayList);
        map.put("customDials", arrayList2);
        return map;
    }

    public static HashMap unpackEcgLocation(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("ecgLocation", Integer.valueOf(bArr.length > 0 ? bArr[0] & 255 : 0));
        map.put("dataType", 522);
        return map;
    }

    public static HashMap unpackFactoryReport(byte[] bArr) {
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        HashMap map = new HashMap();
        switch (i2) {
            case 1:
                int i4 = bArr[2] & 255;
                int i5 = (bArr[3] & 255) + ((bArr[4] & 255) << 8);
                long j2 = (bArr[5] & 255) + ((bArr[6] & 255) << 8) + ((bArr[7] & 255) << 16) + ((bArr[8] & 255) << 24);
                int i6 = (bArr[9] & 255) + ((bArr[10] & 255) << 8);
                long j3 = (bArr[11] & 255) + ((bArr[12] & 255) << 8) + ((bArr[13] & 255) << 16) + ((bArr[14] & 255) << 24);
                map.put("satelliteCount", Integer.valueOf(i4));
                map.put("gpsLongitudeInteger", Integer.valueOf(i5));
                map.put("gpsLongitudeFloat", Long.valueOf(j2));
                map.put("gpsLatitudeInteger", Integer.valueOf(i6));
                map.put("gpsLatitudeFloat", Long.valueOf(j3));
                map.put("gpsLongitude", i5 + "." + j2);
                map.put("gpsLatitude", i6 + "." + j3);
                break;
            case 2:
                map.put("signal", Long.valueOf((bArr[2] & 255) + ((bArr[3] & 255) << 8) + ((bArr[4] & 255) << 16) + ((bArr[5] & 255) << 24)));
                break;
            case 3:
                long j4 = (bArr[2] & 255) + ((bArr[3] & 255) << 8) + ((bArr[4] & 255) << 16) + ((bArr[5] & 255) << 24);
                long j5 = (bArr[6] & 255) + ((bArr[7] & 255) << 8) + ((bArr[8] & 255) << 16) + ((bArr[9] & 255) << 24);
                long j6 = (bArr[10] & 255) + ((bArr[11] & 255) << 8) + ((bArr[12] & 255) << 16) + ((bArr[13] & 255) << 24);
                map.put("green", Long.valueOf(j4));
                map.put("red", Long.valueOf(j5));
                map.put("infrared", Long.valueOf(j6));
                break;
            case 4:
                int signed16BitLittleEndian = ByteUtil.parseSigned16BitLittleEndian(bArr, 2);
                int signed16BitLittleEndian2 = ByteUtil.parseSigned16BitLittleEndian(bArr, 4);
                int signed16BitLittleEndian3 = ByteUtil.parseSigned16BitLittleEndian(bArr, 6);
                map.put("gSensorX", Integer.valueOf(signed16BitLittleEndian));
                map.put("gSensorY", Integer.valueOf(signed16BitLittleEndian2));
                map.put("gSensorZ", Integer.valueOf(signed16BitLittleEndian3));
                break;
            case 5:
                int i7 = bArr[2] & 255;
                int i8 = bArr[3] & 255;
                int i9 = bArr[4] & 255;
                int i10 = bArr[5] & 255;
                map.put("temperatureInt", Integer.valueOf(i7));
                map.put("temperatureFloat", Integer.valueOf(i8));
                map.put("humidityInt", Integer.valueOf(i9));
                map.put("humidityFloat", Integer.valueOf(i10));
                map.put("temperature", i7 + "." + i8);
                map.put("humidity", i9 + "." + i10);
                break;
            case 6:
                map.put("ecgStatus", Integer.valueOf(bArr[2] & 255));
                break;
            case 7:
                map.put("ambientLight", Integer.valueOf((bArr[2] & 255) + ((bArr[3] & 255) << 8)));
                break;
            case 8:
                map.put("tpStatus", Integer.valueOf(bArr[2] & 255));
                break;
            case 9:
                int i11 = bArr[2] & 255;
                int i12 = bArr[3] & 255;
                map.put("temperatureInt", Integer.valueOf(i11));
                map.put("temperatureFloat", Integer.valueOf(i12));
                map.put("temperature", i11 + "." + i12);
                break;
        }
        map.put("reportType", Integer.valueOf(i2));
        map.put("status", Integer.valueOf(i3));
        return map;
    }

    public static HashMap unpackFactoryTest(byte[] bArr) {
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        HashMap map = new HashMap();
        map.put("opCode", Integer.valueOf(i2));
        map.put("status", Integer.valueOf(i3));
        return map;
    }

    public static HashMap<String, Object> unpackFileCount(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(AlbumLoader.COLUMN_COUNT, Integer.valueOf((bArr[0] & 255) + ((bArr[1] & 255) << 8)));
        return map;
    }

    public static HashMap<String, Object> unpackFileData(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total_size", Integer.valueOf((bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16) + ((bArr[3] & 255) << 24)));
        map.put("total_package", Integer.valueOf((bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24)));
        map.put("verify_code", Integer.valueOf((bArr[8] & 255) + ((bArr[9] & 255) << 8) + ((bArr[10] & 255) << 16) + ((bArr[11] & 255) << 24)));
        return map;
    }

    public static HashMap<String, Object> unpackFileList(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length >= 4) {
            int length = bArr.length / 24;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                HashMap map2 = new HashMap();
                byte[] bArr2 = new byte[16];
                System.arraycopy(bArr, i2, bArr2, 0, 16);
                map2.put("file_name", new String(bArr2));
                map2.put("file_size", Integer.valueOf((bArr[i2 + 16] & 255) + ((bArr[i2 + 17] & 255) << 8) + ((bArr[i2 + 18] & 255) << 16) + ((bArr[i2 + 19] & 255) << 24)));
                int i4 = i2 + 23;
                int i5 = (bArr[i2 + 20] & 255) + ((bArr[i2 + 21] & 255) << 8) + ((bArr[i2 + 22] & 255) << 16);
                i2 += 24;
                map2.put("file_verify", Integer.valueOf(i5 + ((bArr[i4] & 255) << 24)));
                arrayList.add(map2);
            }
        }
        map.put("data", arrayList);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetLaserTreatmentParams));
        return map;
    }

    public static HashMap<String, Object> unpackFileSync(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", bArr);
        return map;
    }

    public static HashMap<String, Object> unpackFileSyncVerify(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("package", Integer.valueOf((bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16) + ((bArr[3] & 255) << 24)));
        map.put("size", Integer.valueOf((bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24)));
        map.put("crc", Integer.valueOf((bArr[8] & 255) + ((bArr[9] & 255) << 8)));
        return map;
    }

    public static HashMap unpackGetALiIOTActivationState(byte[] bArr) {
        HashMap map = new HashMap();
        int i2 = 0;
        if (bArr != null && bArr.length >= 1) {
            i2 = bArr[0] & 255;
        }
        map.put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(i2));
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetALiIOTActivationState));
        return map;
    }

    public static HashMap unpackGetAlgorithmicLicense(byte[] bArr) {
        HashMap map = new HashMap();
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        int i4 = bArr[2] & 255;
        int i5 = bArr[3] & 255;
        byte[] bArr2 = new byte[i5];
        System.arraycopy(bArr, 4, bArr2, 0, i5);
        String str = i3 == 0 ? new String(bArr2, StandardCharsets.UTF_8) : i3 == 1 ? ByteUtil.byteToString(bArr2) : "";
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetAlgorithmicLicense));
        map.put("producer", Integer.valueOf(i2));
        map.put("activated", Integer.valueOf(i4));
        map.put("deviceId", str);
        return map;
    }

    public static HashMap unpackGetAllRealDataFromDevice(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null) {
            int i2 = 21;
            if (bArr.length >= 21) {
                map.put("heartRate", Integer.valueOf(bArr[0] & 255));
                map.put("SBP", Integer.valueOf(bArr[1] & 255));
                map.put("DBP", Integer.valueOf(bArr[2] & 255));
                map.put("bloodOxygen", Integer.valueOf(bArr[3] & 255));
                map.put("respirationRate", Integer.valueOf(bArr[4] & 255));
                map.put("tempIntValue", Integer.valueOf(bArr[5] & 255));
                map.put("tempFloatValue", Integer.valueOf(bArr[6] & 255));
                map.put("realSteps", Integer.valueOf((bArr[7] & 255) + ((bArr[8] & 255) << 8) + ((bArr[9] & 255) << 16)));
                map.put("realCalories", Integer.valueOf((bArr[10] & 255) + ((bArr[11] & 255) << 8)));
                map.put("realDistance", Integer.valueOf((bArr[12] & 255) + ((bArr[13] & 255) << 8)));
                map.put("sportsRealSteps", Integer.valueOf((bArr[14] & 255) + ((bArr[15] & 255) << 8) + ((bArr[16] & 255) << 16)));
                map.put("sportsRealCalories", Integer.valueOf((bArr[17] & 255) + ((bArr[18] & 255) << 8)));
                map.put("sportsRealDistance", Integer.valueOf((bArr[19] & 255) + ((bArr[20] & 255) << 8)));
                if (bArr.length >= 26) {
                    map.put("recordTime", Integer.valueOf((bArr[21] & 255) + ((bArr[22] & 255) << 8) + ((bArr[23] & 255) << 16) + ((bArr[24] & 255) << 24)));
                    i2 = 25;
                }
                if (bArr.length >= 30) {
                    int i3 = (bArr[i2] & 255) + ((bArr[i2 + 1] & 255) << 8);
                    map.put("ppi", Integer.valueOf(i3 + ((bArr[i2 + 2] & 255) << 16) + ((bArr[i2 + 3] & 255) << 24)));
                }
            }
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetAllRealDataFromDevice));
        return map;
    }

    public static HashMap unpackGetCardInfo(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("type", Integer.valueOf(bArr[0] & 255));
        byte[] bArr2 = new byte[bArr.length - 2];
        System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 2);
        try {
            map.put("card", new String(bArr2, StandardCharsets.UTF_8));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetCardInfo));
        return map;
    }

    public static HashMap unpackGetChipScheme(byte[] bArr) {
        int i2;
        HashMap map = new HashMap();
        if (bArr == null || bArr.length < 1 || (i2 = bArr[0] & 255) >= 240) {
            map.put("chipScheme", 0);
            SPUtil.saveChipScheme(0);
        } else {
            map.put("chipScheme", Integer.valueOf(i2));
            SPUtil.saveChipScheme(i2);
            if (YCBTClientImpl.getInstance().connectState() == 9 && InnerUtils.isJieLiChipScheme(i2)) {
                WatchManager.getInstance().initWatchManager(BleHelper.getHelper().getBleContext());
            }
        }
        map.put("dataType", 539);
        return map;
    }

    public static HashMap unpackGetCurrentAmbientLightIntensity(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 3) {
            map.put("ambientLightIntensityIsTest", Integer.valueOf(bArr[0] & 255));
            map.put("ambientLightIntensityValue", Integer.valueOf((bArr[1] & 255) + ((bArr[2] & 255) << 8)));
        }
        map.put("dataType", 530);
        return map;
    }

    public static HashMap unpackGetCurrentAmbientTempAndHumidity(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 5) {
            map.put("ambientTempAndHumidityIsTest", Integer.valueOf(bArr[0] & 255));
            map.put("ambientTempValue", (bArr[1] & 255) + "." + (bArr[2] & 255));
            map.put("ambientHumidityValue", (bArr[3] & 255) + "." + (bArr[4] & 255));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetCurrentAmbientTempAndHumidity));
        return map;
    }

    public static HashMap unpackGetCurrentSystemWorkingMode(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 1) {
            map.put("currentSystemWorkingMode", Integer.valueOf(bArr[0] & 255));
        }
        map.put("dataType", 534);
        return map;
    }

    public static HashMap unpackGetDeviceRemindInfo(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 1) {
            map.put("deviceRemindInfo", Integer.valueOf(bArr[0] & 255));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetDeviceRemindInfo));
        return map;
    }

    public static HashMap unpackGetEcgMode(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("ecgMode", Integer.valueOf(bArr.length >= 1 ? bArr[0] & 255 : 0));
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetEcgMode));
        return map;
    }

    public static HashMap unpackGetEventReminder(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadEventReminder));
        if (bArr != null && bArr.length >= 7) {
            map.put("eventReminderIndex", Integer.valueOf(bArr[0] & 255));
            map.put("eventReminderSwitch", Integer.valueOf(bArr[1] & 255));
            map.put("eventReminderType", Integer.valueOf(bArr[2] & 255));
            map.put("eventReminderHour", Integer.valueOf(bArr[3] & 255));
            map.put("eventReminderMin", Integer.valueOf(bArr[4] & 255));
            map.put("eventReminderRepeat", Integer.valueOf(bArr[5] & 255));
            map.put("eventReminderInterval", Integer.valueOf(bArr[6] & 255));
            if ((bArr[2] & 255) != 1 || bArr.length <= 7) {
                map.put("incidentName", "");
            } else {
                byte[] bArr2 = new byte[bArr.length - 7];
                System.arraycopy(bArr, 7, bArr2, 0, bArr.length - 7);
                try {
                    map.put("incidentName", new String(bArr2, "utf-8"));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return map;
    }

    public static HashMap unpackGetHeavenEarthAndFiveElement(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 1) {
            try {
                map.put("data", new String(bArr, "utf-8"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetHeavenEarthAndFiveElement));
        return map;
    }

    public static HashMap unpackGetHistoryOutline(byte[] bArr) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        HashMap map = new HashMap();
        int i11 = 0;
        map.put("code", 0);
        if (bArr.length > 8) {
            i3 = bArr[0] & 255;
            i4 = (bArr[1] & 255) + ((bArr[2] & 255) << 8);
            i5 = (bArr[3] & 255) + ((bArr[4] & 255) << 8);
            i6 = (bArr[5] & 255) + ((bArr[6] & 255) << 8);
            i7 = (bArr[7] & 255) + ((bArr[8] & 255) << 8);
            if (bArr.length > 16) {
                i11 = (bArr[9] & 255) + ((bArr[10] & 255) << 8);
                i8 = (bArr[11] & 255) + ((bArr[12] & 255) << 8);
                i9 = (bArr[13] & 255) + ((bArr[14] & 255) << 8);
                i10 = (bArr[15] & 255) + ((bArr[16] & 255) << 8);
            } else {
                i8 = 0;
                i9 = 0;
                i10 = 0;
            }
            map.put("supportOk", 1);
            i2 = i11;
            i11 = i10;
        } else {
            map.put("supportOk", 0);
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
            i9 = 0;
        }
        map.put("SleepNum", Integer.valueOf(i3));
        map.put("SleepTotalTime", Integer.valueOf(i4));
        map.put("HeartNum", Integer.valueOf(i5));
        map.put("SportNum", Integer.valueOf(i6));
        map.put("BloodNum", Integer.valueOf(i7));
        map.put("BloodOxygenNum", Integer.valueOf(i2));
        map.put("TempHumidNum", Integer.valueOf(i8));
        map.put("TempNum", Integer.valueOf(i9));
        map.put("AmbientLightNum", Integer.valueOf(i11));
        map.put("dataType", 525);
        return map;
    }

    public static HashMap unpackGetInflatedBlood(byte[] bArr) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length >= 4) {
            int length = bArr.length / 4;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                HashMap map2 = new HashMap();
                map2.put("pressureValue", Integer.valueOf((bArr[i2] & 255) + ((bArr[i2 + 1] & 255) << 8)));
                int i4 = i2 + 3;
                int i5 = bArr[i2 + 2] & 255;
                i2 += 4;
                map2.put("signalValue", Integer.valueOf(i5 + ((bArr[i4] & 255) << 8)));
                arrayList.add(map2);
            }
        }
        map.put("data", arrayList);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadInflatedBlood));
        return map;
    }

    public static HashMap unpackGetInsuranceRelatedInfo(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 2) {
            switch (bArr[0] & 255) {
                case 0:
                    byte[] bArr2 = new byte[bArr.length - 1];
                    System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
                    try {
                        map.put("data", new String(bArr2, "utf-8"));
                        break;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        break;
                    }
                case 1:
                case 2:
                case 3:
                case 4:
                    if (bArr.length >= 5) {
                        map.put("data", Integer.valueOf((bArr[1] & 255) + ((bArr[2] & 255) << 8) + ((bArr[3] & 255) << 16) + ((bArr[4] & 255) << 24)));
                        break;
                    }
                    break;
                case 5:
                case 6:
                    map.put("data", Integer.valueOf(bArr[1] & 255));
                    break;
                case 7:
                    map.put("data", Long.valueOf(((bArr[1] & 255) + ((bArr[2] & 255) << 8) + ((bArr[3] & 255) << 16) + ((bArr[4] & 255) << 24) + YCBTClient.SecFrom30Year) * 1000));
                    break;
            }
            map.put("type", Integer.valueOf(bArr[0] & 255));
        }
        map.put("dataType", 535);
        return map;
    }

    public static HashMap unpackGetLaserTreatmentParams(byte[] bArr) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length >= 10) {
            int length = bArr.length / 10;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                HashMap map2 = new HashMap();
                map2.put("id", Integer.valueOf(bArr[i2] & 255));
                map2.put("onOff", Integer.valueOf(bArr[i2 + 1] & 255));
                map2.put("startHour", Integer.valueOf(bArr[i2 + 2] & 255));
                map2.put("startMin", Integer.valueOf(bArr[i2 + 3] & 255));
                map2.put("endHour", Integer.valueOf(bArr[i2 + 4] & 255));
                map2.put("endMin", Integer.valueOf(bArr[i2 + 5] & 255));
                map2.put("measuringFrequency", Integer.valueOf((bArr[i2 + 6] & 255) + ((bArr[i2 + 7] & 255) << 8)));
                int i4 = i2 + 9;
                map2.put("laserIntensity", Integer.valueOf(bArr[i2 + 8] & 255));
                i2 += 10;
                map2.put("laserDuration", Integer.valueOf(bArr[i4] & 255));
                arrayList.add(map2);
            }
        }
        map.put("data", arrayList);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetLaserTreatmentParams));
        return map;
    }

    public static HashMap unpackGetMeasurementFunction(byte[] bArr) {
        HashMap map = new HashMap();
        byte b2 = bArr[0];
        boolean z = (b2 & 1) == 1;
        boolean z2 = ((b2 >> 1) & 1) == 1;
        boolean z3 = ((b2 >> 2) & 1) == 1;
        boolean z4 = ((b2 >> 3) & 1) == 1;
        boolean z5 = ((b2 >> 4) & 1) == 1;
        boolean z6 = ((b2 >> 5) & 1) == 1;
        boolean z7 = ((b2 >> 6) & 1) == 1;
        boolean z8 = ((b2 >> 7) & 1) == 1;
        byte b3 = bArr[1];
        boolean z9 = (b3 & 1) == 1;
        boolean z10 = ((b3 >> 1) & 1) == 1;
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetMeasurementFunction));
        map.put("bloodSugar", Boolean.valueOf(z));
        map.put("uricAcid", Boolean.valueOf(z2));
        map.put("bloodFat", Boolean.valueOf(z3));
        map.put("heartRate", Boolean.valueOf(z4));
        map.put("bloodOxygen", Boolean.valueOf(z5));
        map.put("temperature", Boolean.valueOf(z6));
        map.put("bpNormal", Boolean.valueOf(z7));
        map.put("bpAccurate", Boolean.valueOf(z8));
        map.put("ecg", Boolean.valueOf(z9));
        map.put("hrv", Boolean.valueOf(z10));
        YCBTLog.e("获取功能：" + map);
        return map;
    }

    public static HashMap unpackGetNowSport(byte[] bArr) {
        int i2;
        int i3;
        HashMap map = new HashMap();
        int i4 = 0;
        map.put("code", 0);
        if (bArr.length > 6) {
            int i5 = (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16);
            i3 = (bArr[3] & 255) + ((bArr[4] & 255) << 8);
            int i6 = ((bArr[6] & 255) << 8) + (bArr[5] & 255);
            YCBTLog.e("tStep " + i5 + " tCal " + i3 + " tDis " + i6);
            map.put("supportOk", 1);
            i4 = i6;
            i2 = i5;
        } else {
            map.put("supportOk", 0);
            i2 = 0;
            i3 = 0;
        }
        map.put("nowStep", Integer.valueOf(i2));
        map.put("nowCalorie", Integer.valueOf(i3));
        map.put("nowDistance", Integer.valueOf(i4));
        map.put("dataType", 524);
        return map;
    }

    public static HashMap unpackGetPowerStatistics(byte[] bArr) {
        int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
        HashMap map = new HashMap();
        long j2 = bArr[28] & 255;
        long j3 = bArr[29] & 255;
        map.put("lastChargingTime", Long.valueOf(((((((bArr[0] & 255) + ((bArr[1] & 255) << 8)) + ((bArr[2] & 255) << 16)) + ((bArr[3] & 255) << 24)) + 946684800) * 1000) - offset));
        map.put("usageTime", Long.valueOf((bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24)));
        map.put("screenDuration", Long.valueOf((bArr[8] & 255) + ((bArr[9] & 255) << 8) + ((bArr[10] & 255) << 16) + ((bArr[11] & 255) << 24)));
        map.put("callDuration", Long.valueOf((bArr[12] & 255) + ((bArr[13] & 255) << 8) + ((bArr[14] & 255) << 16) + ((bArr[15] & 255) << 24)));
        map.put("musicDuration", Long.valueOf((bArr[16] & 255) + ((bArr[17] & 255) << 8) + ((bArr[18] & 255) << 16) + ((bArr[19] & 255) << 24)));
        map.put("healthMeasurementDuration", Long.valueOf((bArr[20] & 255) + ((bArr[21] & 255) << 8) + ((bArr[22] & 255) << 16) + ((bArr[23] & 255) << 24)));
        map.put("messagesNumber", Long.valueOf((bArr[24] & 255) + ((bArr[25] & 255) << 8) + ((bArr[26] & 255) << 16) + ((bArr[27] & 255) << 24)));
        map.put("lastChargingEndBattery", Long.valueOf(j2));
        map.put("batteryLevel", Long.valueOf(j3));
        map.put("aratedBloodPressure", Long.valueOf((bArr[30] & 255) + ((bArr[31] & 255) << 8) + ((bArr[32] & 255) << 16) + ((bArr[33] & 255) << 24)));
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetPowerStatistics));
        return map;
    }

    public static HashMap unpackGetRealBloodOxygen(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 2) {
            map.put("bloodOxygenIsTest", Integer.valueOf(bArr[0] & 255));
            map.put("bloodOxygenValue", Integer.valueOf(bArr[1] & 255));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetRealBloodOxygen));
        return map;
    }

    public static HashMap unpackGetRealTemp(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length > 1) {
            map.put("tempValue", (bArr[0] & 255) + "." + (bArr[1] & 255));
        }
        map.put("dataType", 526);
        return map;
    }

    public static HashMap unpackGetScheduleInfo(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadSchedule));
        if (bArr != null && bArr.length >= 9) {
            map.put("scheduleIndex", Integer.valueOf(bArr[0] & 255));
            map.put("scheduleEnable", Integer.valueOf(bArr[1] & 255));
            map.put("incidentIndex", Integer.valueOf(bArr[2] & 255));
            map.put("incidentEnable", Integer.valueOf(bArr[3] & 255));
            map.put("incidentTime", Long.valueOf(((((((bArr[4] & 255) + ((bArr[5] & 255) << 8)) + ((bArr[6] & 255) << 16)) + ((bArr[7] & 255) << 24)) + YCBTClient.SecFrom30Year) * 1000) - TimeZone.getDefault().getOffset(System.currentTimeMillis())));
            map.put("incidentID", Integer.valueOf(bArr[8] & 255));
            if (bArr.length > 9) {
                byte[] bArr2 = new byte[bArr.length - 9];
                System.arraycopy(bArr, 9, bArr2, 0, bArr.length - 9);
                try {
                    map.put("incidentName", new String(bArr2, "utf-8"));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                map.put("incidentName", "");
            }
        }
        return map;
    }

    public static HashMap unpackGetScreenInfo(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 4) {
            map.put("currentScreenDisplayLevel", Integer.valueOf(bArr[0] & 255));
            map.put("currentScreenOffTime", Integer.valueOf(bArr[1] & 255));
            map.put("currentLanguageSettings", Integer.valueOf(bArr[2] & 255));
            map.put("CurrentWorkingMode", Integer.valueOf(bArr[3] & 255));
        }
        map.put("dataType", 527);
        return map;
    }

    public static HashMap unpackGetScreenParameters(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 7) {
            map.put("screenType", Integer.valueOf(bArr[0] & 255));
            map.put("screenWidth", Integer.valueOf((bArr[1] & 255) + ((bArr[2] & 255) << 8)));
            map.put("screenHeight", Integer.valueOf((bArr[3] & 255) + ((bArr[4] & 255) << 8)));
            map.put("screenCorner", Integer.valueOf((bArr[5] & 255) + ((bArr[6] & 255) << 8)));
            if (bArr.length >= 13) {
                map.put("screenCpWidth", Integer.valueOf((bArr[7] & 255) + ((bArr[8] & 255) << 8)));
                map.put("screenCpHeight", Integer.valueOf((bArr[9] & 255) + ((bArr[10] & 255) << 8)));
                map.put("screenCpCorner", Integer.valueOf((bArr[11] & 255) + ((bArr[12] & 255) << 8)));
            }
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetScreenParameters));
        return map;
    }

    public static HashMap unpackGetSensorSamplingInfo(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 5) {
            map.put("sensorSamplingInfoState", Integer.valueOf(bArr[0] & 255));
            map.put("sensorSamplingInfoDuration", Integer.valueOf((bArr[1] & 255) + ((bArr[2] & 255) << 8)));
            map.put("sensorSamplingInfoInterval", Integer.valueOf((bArr[3] & 255) + ((bArr[4] & 255) << 8)));
        }
        map.put("dataType", 533);
        return map;
    }

    public static HashMap unpackGetSleepStatus(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("sleepStatus", Integer.valueOf(bArr.length >= 1 ? bArr[0] & 255 : 0));
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetSleepStatus));
        return map;
    }

    public static HashMap unpackGetStatusOfManualMode(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 1) {
            map.put("statusOfManualMode", Integer.valueOf(bArr[0] & 255));
        }
        map.put("dataType", 537);
        return map;
    }

    public static HashMap unpackGetUploadConfigurationInfoOfReminder(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 2) {
            map.put("UploadConfigurationInfoOfReminderEnable", Integer.valueOf(bArr[0] & 255));
            map.put("UploadConfigurationInfoOfReminderValue", Integer.valueOf(bArr[1] & 255));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetUploadConfigurationInfoOfReminder));
        return map;
    }

    public static HashMap unpackGetUploadOGA(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null) {
            int i2 = 21;
            if (bArr.length >= 21) {
                map.put("heartRate", Integer.valueOf(bArr[0] & 255));
                map.put("SBP", Integer.valueOf(bArr[1] & 255));
                map.put("DBP", Integer.valueOf(bArr[2] & 255));
                map.put("bloodOxygen", Integer.valueOf(bArr[3] & 255));
                map.put("respirationRate", Integer.valueOf(bArr[4] & 255));
                map.put("tempIntValue", Integer.valueOf(bArr[5] & 255));
                map.put("tempFloatValue", Integer.valueOf(bArr[6] & 255));
                map.put("realSteps", Integer.valueOf((bArr[7] & 255) + ((bArr[8] & 255) << 8) + ((bArr[9] & 255) << 16)));
                map.put("realCalories", Integer.valueOf((bArr[10] & 255) + ((bArr[11] & 255) << 8)));
                map.put("realDistance", Integer.valueOf((bArr[12] & 255) + ((bArr[13] & 255) << 8)));
                map.put("sportsRealSteps", Integer.valueOf((bArr[14] & 255) + ((bArr[15] & 255) << 8) + ((bArr[16] & 255) << 16)));
                map.put("sportsRealCalories", Integer.valueOf((bArr[17] & 255) + ((bArr[18] & 255) << 8)));
                map.put("sportsRealDistance", Integer.valueOf((bArr[19] & 255) + ((bArr[20] & 255) << 8)));
                if (bArr.length >= 26) {
                    map.put("recordTime", Integer.valueOf((bArr[21] & 255) + ((bArr[22] & 255) << 8) + ((bArr[23] & 255) << 16) + ((bArr[24] & 255) << 24)));
                    i2 = 25;
                }
                if (bArr.length >= 30) {
                    int i3 = i2 + 3;
                    int i4 = (bArr[i2] & 255) + ((bArr[i2 + 1] & 255) << 8) + ((bArr[i2 + 2] & 255) << 16);
                    i2 += 4;
                    map.put("ppi", Integer.valueOf(i4 + ((bArr[i3] & 255) << 24)));
                }
                if (bArr.length >= 31) {
                    map.put("maximalOxygenIntake", Integer.valueOf(bArr[i2] & 255));
                }
            }
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadOGA));
        return map;
    }

    public static HashMap unpackGetUploadPrayer(byte[] bArr) {
        int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 8) {
            long j2 = ((((((bArr[0] & 255) + ((bArr[1] & 255) << 8)) + ((bArr[2] & 255) << 16)) + ((bArr[3] & 255) << 24)) + 946684800) * 1000) - offset;
            int i2 = (bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24);
            map.put("time", Long.valueOf(j2));
            map.put(AlbumLoader.COLUMN_COUNT, Integer.valueOf(i2));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadPrayer));
        return map;
    }

    public static HashMap<String, Object> unpackGetWit(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        int i2 = bArr[0] & 255;
        byte b2 = bArr[1];
        int i3 = b2 & 255;
        map.put("opcode", Integer.valueOf(i2));
        map.put("videoState", Integer.valueOf(b2 & 1));
        map.put("musicState", Integer.valueOf((i3 >> 1) & 1));
        map.put("readingState", Integer.valueOf((i3 >> 2) & 1));
        map.put("photoState", Integer.valueOf((i3 >> 3) & 1));
        map.put("sosState", Integer.valueOf((i3 >> 4) & 1));
        map.put("slideshowState", Integer.valueOf(1 & (i3 >> 5)));
        return map;
    }

    public static HashMap unpackHealthData(byte[] bArr, int i2) throws NumberFormatException {
        int i3;
        int i4;
        int i5;
        int i6;
        String str;
        Object obj;
        HashMap map;
        String str2;
        int i7;
        float f2;
        int i8;
        int i9;
        int i10;
        byte[] bArr2 = bArr;
        int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
        HashMap map2 = new HashMap();
        map2.put("code", 0);
        String str3 = "startTime";
        String str4 = "dataType";
        String str5 = "data";
        long j2 = 1000;
        long j3 = 946684800;
        int i11 = 8;
        int i12 = 0;
        switch (i2) {
            case 2:
                byte[] bArr3 = bArr2;
                Object obj2 = "data";
                ArrayList arrayList = new ArrayList();
                while (true) {
                    int i13 = i12 + 14;
                    if (i13 > bArr3.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistorySport));
                        map2.put(obj2, arrayList);
                        return map2;
                    }
                    int i14 = (bArr3[i12 + 8] & 255) + ((bArr3[i12 + 9] & 255) << 8);
                    int i15 = (bArr3[i12 + 10] & 255) + ((bArr3[i12 + 11] & 255) << 8);
                    int i16 = (bArr3[i12 + 12] & 255) + ((bArr3[i12 + 13] & 255) << 8);
                    Object obj3 = obj2;
                    long j4 = offset;
                    long j5 = ((((((bArr3[i12] & 255) + ((bArr3[i12 + 1] & 255) << 8)) + ((bArr3[i12 + 2] & 255) << 16)) + ((bArr3[i12 + 3] & 255) << 24)) + 946684800) * 1000) - j4;
                    long j6 = ((((((bArr3[i12 + 4] & 255) + ((bArr3[i12 + 5] & 255) << 8)) + ((bArr3[i12 + 6] & 255) << 16)) + ((bArr3[i12 + 7] & 255) << 24)) + 946684800) * 1000) - j4;
                    HashMap map3 = new HashMap();
                    map3.put("sportStartTime", Long.valueOf(j5));
                    map3.put("sportEndTime", Long.valueOf(j6));
                    map3.put("sportStep", Integer.valueOf(i14));
                    map3.put("sportCalorie", Integer.valueOf(i16));
                    map3.put("sportDistance", Integer.valueOf(i15));
                    arrayList.add(map3);
                    bArr3 = bArr;
                    i12 = i13;
                    obj2 = obj3;
                }
            case 4:
                ArrayList arrayList2 = new ArrayList();
                int i17 = 0;
                int i18 = 0;
                while (true) {
                    int i19 = i12 + 20;
                    if (i19 > bArr2.length) {
                        break;
                    } else {
                        byte b2 = bArr2[i12];
                        byte b3 = bArr2[i12 + 1];
                        int i20 = (bArr2[i12 + 2] & 255) + ((bArr2[i12 + 3] & 255) << 8);
                        String str6 = str5;
                        int i21 = i17;
                        long j7 = ((bArr2[i12 + 4] & 255) + ((bArr2[i12 + 5] & 255) << 8) + ((bArr2[i12 + 6] & 255) << 16) + ((bArr2[i12 + 7] & 255) << 24) + 946684800) * 1000;
                        HashMap map4 = map2;
                        String str7 = str3;
                        long j8 = ((bArr2[i12 + 8] & 255) + ((bArr2[i12 + 9] & 255) << 8) + ((bArr2[i12 + 10] & 255) << 16) + ((bArr2[i12 + 11] & 255) << 24) + 946684800) * 1000;
                        int i22 = i12 + 14;
                        int i23 = (bArr2[i12 + 12] & 255) + ((bArr2[i12 + 13] & 255) << 8);
                        if (i23 == 65535) {
                            i4 = (bArr2[i22] & 255) + ((bArr2[i12 + 15] & 255) << 8);
                            int i24 = (bArr2[i12 + 16] & 255) + ((bArr2[i12 + 17] & 255) << 8);
                            i3 = (bArr2[i12 + 18] & 255) + ((bArr2[i12 + 19] & 255) << 8);
                            i5 = i24;
                            i6 = i21;
                        } else {
                            int i25 = (bArr2[i22] & 255) + ((bArr2[i12 + 15] & 255) << 8);
                            int i26 = i18;
                            int i27 = ((bArr2[i12 + 16] & 255) + ((bArr2[i12 + 17] & 255) << 8)) * 60;
                            i3 = ((bArr2[i12 + 18] & 255) + ((bArr2[i12 + 19] & 255) << 8)) * 60;
                            i4 = i26;
                            i5 = i27;
                            i6 = i25;
                        }
                        ArrayList arrayList3 = new ArrayList();
                        ArrayList arrayList4 = new ArrayList();
                        int i28 = 0;
                        int i29 = 0;
                        int i30 = i19;
                        while (true) {
                            int i31 = i19;
                            str = str4;
                            if ((i30 - i19) + 8 <= i20 - 20) {
                                int i32 = bArr2[i30] & 255;
                                int i33 = i20;
                                ArrayList arrayList5 = arrayList2;
                                int i34 = i3;
                                long j9 = ((bArr2[i30 + 1] & 255) + ((bArr2[i30 + 2] & 255) << 8) + ((bArr2[i30 + 3] & 255) << 16) + ((bArr2[i30 + 4] & 255) << 24) + 946684800) * 1000;
                                ArrayList arrayList6 = arrayList3;
                                int i35 = i30 + 7;
                                int i36 = i4;
                                int i37 = (bArr2[i30 + 5] & 255) + ((bArr2[i30 + 6] & 255) << 8);
                                i30 += 8;
                                int i38 = i37 + ((bArr2[i35] & 255) << 16);
                                if (i32 == 244) {
                                    i29++;
                                    i28 += i38;
                                }
                                int i39 = i23;
                                long j10 = j9 - offset;
                                if (!arrayList4.contains("" + j10)) {
                                    HashMap map5 = new HashMap();
                                    map5.put("sleepType", Integer.valueOf(i32));
                                    map5.put("sleepStartTime", Long.valueOf(j10));
                                    map5.put("sleepLen", Integer.valueOf(i38));
                                    arrayList6.add(map5);
                                    arrayList4.add("" + j10);
                                }
                                i4 = i36;
                                arrayList3 = arrayList6;
                                str4 = str;
                                i19 = i31;
                                arrayList2 = arrayList5;
                                i3 = i34;
                                i20 = i33;
                                i23 = i39;
                            }
                        }
                        ArrayList arrayList7 = arrayList3;
                        ArrayList arrayList8 = arrayList2;
                        int i40 = i4;
                        HashMap map6 = new HashMap();
                        long j11 = offset;
                        map6.put(str7, Long.valueOf(j7 - j11));
                        map6.put("endTime", Long.valueOf(j8 - j11));
                        map6.put("deepSleepCount", Integer.valueOf(i23));
                        map6.put("lightSleepCount", Integer.valueOf(i6));
                        map6.put("deepSleepTotal", Integer.valueOf(i5));
                        map6.put("lightSleepTotal", Integer.valueOf(i3));
                        map6.put("rapidEyeMovementTotal", Integer.valueOf(i40));
                        map6.put("sleepData", arrayList7);
                        map6.put("wakeCount", Integer.valueOf(i29));
                        map6.put("wakeDuration", Integer.valueOf(i28));
                        arrayList8.add(map6);
                        str4 = str;
                        map4.put(str4, Integer.valueOf(Constants.DATATYPE.Health_HistorySleep));
                        map4.put(str6, arrayList8);
                        str5 = str6;
                        arrayList2 = arrayList8;
                        map2 = map4;
                        i17 = i6;
                        str3 = str7;
                        i12 = i30;
                        i18 = i40;
                    }
                }
                break;
            case 6:
                ArrayList arrayList9 = new ArrayList();
                while (true) {
                    int i41 = i12 + 6;
                    if (i41 > bArr2.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryHeart));
                        map2.put("data", arrayList9);
                        break;
                    } else {
                        int i42 = bArr2[i12 + 4] & 255;
                        int i43 = bArr2[i12 + 5] & 255;
                        HashMap map7 = new HashMap();
                        map7.put("heartStartTime", Long.valueOf(((((((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8)) + ((bArr2[i12 + 2] & 255) << 16)) + ((bArr2[i12 + 3] & 255) << 24)) + 946684800) * 1000) - offset));
                        map7.put("mode", Integer.valueOf(i42));
                        map7.put("heartValue", Integer.valueOf(i43));
                        arrayList9.add(map7);
                        i12 = i41;
                    }
                }
            case 8:
                ArrayList arrayList10 = new ArrayList();
                while (true) {
                    int i44 = i12 + 8;
                    if (i44 > bArr2.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryBlood));
                        map2.put("data", arrayList10);
                        break;
                    } else {
                        long j12 = ((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8) + ((bArr2[i12 + 2] & 255) << 16) + ((bArr2[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i45 = bArr2[i12 + 4] & 255;
                        int i46 = bArr2[i12 + 5] & 255;
                        int i47 = bArr2[i12 + 6] & 255;
                        HashMap map8 = new HashMap();
                        map8.put("bloodStartTime", Long.valueOf(j12 - offset));
                        map8.put("bloodSBP", Integer.valueOf(i46));
                        map8.put("bloodDBP", Integer.valueOf(i47));
                        map8.put("isInflated", Integer.valueOf(i45));
                        arrayList10.add(map8);
                        i12 = i44;
                    }
                }
            case 9:
                byte[] bArr4 = bArr2;
                Object obj4 = "data";
                ArrayList arrayList11 = new ArrayList();
                while (true) {
                    int i48 = i12 + 20;
                    if (i48 > bArr4.length) {
                        map2.put(str4, Integer.valueOf(Constants.DATATYPE.Health_HistoryAll));
                        map2.put(obj4, arrayList11);
                        break;
                    } else {
                        long j13 = ((bArr4[i12] & 255) + ((bArr4[i12 + 1] & 255) << 8) + ((bArr4[i12 + 2] & 255) << 16) + ((bArr4[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i49 = (bArr4[i12 + 4] & 255) + ((bArr4[i12 + 5] & 255) << 8);
                        int i50 = bArr4[i12 + 6] & 255;
                        int i51 = bArr4[i12 + 7] & 255;
                        int i52 = bArr4[i12 + 8] & 255;
                        int i53 = bArr4[i12 + 9] & 255;
                        int i54 = bArr4[i12 + 10] & 255;
                        Object obj5 = obj4;
                        int i55 = bArr4[i12 + 11] & 255;
                        HashMap map9 = map2;
                        int i56 = bArr4[i12 + 12] & 255;
                        String str8 = str4;
                        int i57 = bArr4[i12 + 13] & 255;
                        ArrayList arrayList12 = arrayList11;
                        int i58 = bArr4[i12 + 14] & 255;
                        HashMap map10 = new HashMap();
                        map10.put("startTime", Long.valueOf(j13 - offset));
                        map10.put("stepValue", Integer.valueOf(i49));
                        map10.put("heartValue", Integer.valueOf(i50));
                        map10.put("DBPValue", Integer.valueOf(i52));
                        map10.put("SBPValue", Integer.valueOf(i51));
                        map10.put("OOValue", Integer.valueOf(i53));
                        map10.put("respiratoryRateValue", Integer.valueOf(i54));
                        map10.put("hrvValue", Integer.valueOf(i55));
                        map10.put("cvrrValue", Integer.valueOf(i56));
                        map10.put("tempIntValue", Integer.valueOf(i57));
                        map10.put("tempFloatValue", Integer.valueOf(i58));
                        map10.put("bodyFatIntValue", Integer.valueOf(bArr[i12 + 15] & 255));
                        map10.put("bodyFatFloatValue", Integer.valueOf(bArr[i12 + 16] & 255));
                        map10.put("bloodSugarValue", Integer.valueOf(bArr[i12 + 17] & 255));
                        arrayList11 = arrayList12;
                        arrayList11.add(map10);
                        i12 = i48;
                        bArr4 = bArr;
                        obj4 = obj5;
                        map2 = map9;
                        str4 = str8;
                    }
                }
            case 26:
                ArrayList arrayList13 = new ArrayList();
                while (true) {
                    int i59 = i12 + 6;
                    if (i59 > bArr2.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryBloodOxygen));
                        map2.put("data", arrayList13);
                        break;
                    } else {
                        long j14 = ((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8) + ((bArr2[i12 + 2] & 255) << 16) + ((bArr2[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i60 = bArr2[i12 + 4] & 255;
                        int i61 = bArr2[i12 + 5] & 255;
                        HashMap map11 = new HashMap();
                        map11.put("startTime", Long.valueOf(j14 - offset));
                        map11.put("type", Integer.valueOf(i60));
                        map11.put("value", Integer.valueOf(i61));
                        arrayList13.add(map11);
                        i12 = i59;
                    }
                }
            case 28:
                ArrayList arrayList14 = new ArrayList();
                while (true) {
                    int i62 = i12 + 9;
                    if (i62 > bArr2.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryTempAndHumidity));
                        map2.put("data", arrayList14);
                        break;
                    } else {
                        long j15 = ((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8) + ((bArr2[i12 + 2] & 255) << 16) + ((bArr2[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i63 = bArr2[i12 + 4] & 255;
                        float f3 = Float.parseFloat((bArr2[i12 + 5] & 255) + "." + (bArr2[i12 + 6] & 255));
                        float f4 = Float.parseFloat((bArr2[i12 + 7] & 255) + "." + (bArr2[i12 + 8] & 255));
                        HashMap map12 = new HashMap();
                        map12.put("startTime", Long.valueOf(j15 - offset));
                        map12.put("type", Integer.valueOf(i63));
                        map12.put("tempValue", Float.valueOf(f3));
                        map12.put("humidValue", Float.valueOf(f4));
                        arrayList14.add(map12);
                        i12 = i62;
                    }
                }
            case 30:
                ArrayList arrayList15 = new ArrayList();
                while (true) {
                    int i64 = i12 + 5;
                    if (i64 > bArr2.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryTemp));
                        map2.put("data", arrayList15);
                        break;
                    } else {
                        long j16 = ((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8) + ((bArr2[i12 + 2] & 255) << 16) + ((bArr2[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i65 = bArr2[i12 + 4] & 255;
                        int i66 = i12 + 6;
                        i12 += 7;
                        float f5 = Float.parseFloat((bArr2[i64] & 255) + "." + (bArr2[i66] & 255));
                        HashMap map13 = new HashMap();
                        map13.put("startTime", Long.valueOf(j16 - offset));
                        map13.put("type", Integer.valueOf(i65));
                        map13.put("tempValue", Float.valueOf(f5));
                        arrayList15.add(map13);
                    }
                }
            case 32:
                ArrayList arrayList16 = new ArrayList();
                while (true) {
                    int i67 = i12 + 6;
                    if (i67 > bArr2.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryAmbientLight));
                        map2.put("data", arrayList16);
                        break;
                    } else {
                        long j17 = ((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8) + ((bArr2[i12 + 2] & 255) << 16) + ((bArr2[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i68 = bArr2[i12 + 4] & 255;
                        int i69 = bArr2[i12 + 5] & 255;
                        i12 += 7;
                        int i70 = i69 + ((bArr2[i67] & 255) << 8);
                        HashMap map14 = new HashMap();
                        map14.put("startTime", Long.valueOf(j17 - offset));
                        map14.put("type", Integer.valueOf(i68));
                        map14.put("value", Integer.valueOf(i70));
                        arrayList16.add(map14);
                    }
                }
            case 41:
                ArrayList arrayList17 = new ArrayList();
                while (true) {
                    int i71 = i12 + 5;
                    if (i71 > bArr.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryFall));
                        map2.put("data", arrayList17);
                        break;
                    } else {
                        int i72 = (bArr[i12] & 255) + ((bArr[i12 + 1] & 255) << 8) + ((bArr[i12 + 2] & 255) << 16);
                        int i73 = bArr[i12 + 4] & 255;
                        HashMap map15 = new HashMap();
                        map15.put("startTime", Long.valueOf((((i72 + ((bArr[i12 + 3] & 255) << 24)) + 946684800) * 1000) - offset));
                        map15.put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(i73));
                        arrayList17.add(map15);
                        i12 = i71;
                    }
                }
            case 43:
                byte[] bArr5 = bArr2;
                ArrayList arrayList18 = new ArrayList();
                while (i12 + 30 <= bArr5.length) {
                    long j18 = ((bArr5[i12] & 255) + ((bArr5[i12 + 1] & 255) << 8) + ((bArr5[i12 + 2] & 255) << 16) + ((bArr5[i12 + 3] & 255) << 24) + 946684800) * 1000;
                    long j19 = (bArr5[i12 + 4] & 255) + ((bArr5[i12 + 5] & 255) << 8) + ((bArr5[i12 + 6] & 255) << 16) + ((bArr5[i12 + 7] & 255) << 24);
                    int i74 = bArr5[i12 + 8] & 255;
                    int i75 = bArr5[i12 + 9] & 255;
                    int i76 = bArr5[i12 + 10] & 255;
                    int i77 = bArr5[i12 + 11] & 255;
                    String str9 = str5;
                    int i78 = bArr5[i12 + 12] & 255;
                    HashMap map16 = map2;
                    int i79 = bArr5[i12 + 13] & 255;
                    String str10 = str4;
                    int i80 = bArr5[i12 + 14] & 255;
                    ArrayList arrayList19 = arrayList18;
                    int i81 = bArr5[i12 + 15] & 255;
                    int i82 = bArr5[i12 + 16] & 255;
                    int i83 = bArr5[i12 + 17] & 255;
                    int i84 = bArr5[i12 + 18] & 255;
                    int i85 = (bArr5[i12 + 19] & 255) + ((bArr5[i12 + 20] & 255) << 8);
                    int i86 = bArr5[i12 + 21] & 255;
                    int i87 = (bArr5[i12 + 22] & 255) + ((bArr5[i12 + 23] & 255) << 8);
                    int i88 = bArr5[i12 + 24] & 255;
                    int i89 = i12 + 29;
                    HashMap map17 = new HashMap();
                    map17.put("startTime", Long.valueOf(j18 - offset));
                    map17.put("stepValue", Long.valueOf(j19));
                    map17.put("heartValue", Integer.valueOf(i74));
                    map17.put("DBPValue", Integer.valueOf(i76));
                    map17.put("SBPValue", Integer.valueOf(i75));
                    map17.put("OOValue", Integer.valueOf(i77));
                    map17.put("respiratoryRateValue", Integer.valueOf(i78));
                    map17.put("hrvValue", Integer.valueOf(i79));
                    map17.put("cvrrValue", Integer.valueOf(i80));
                    map17.put("tempIntValue", Integer.valueOf(i81));
                    map17.put("tempFloatValue", Integer.valueOf(i82));
                    map17.put("humidIntValue", Integer.valueOf(i83));
                    map17.put("humidFloatValue", Integer.valueOf(i84));
                    map17.put("ambientLightValue", Integer.valueOf(i85));
                    map17.put("isSprotMode", Integer.valueOf(i86));
                    map17.put("sportCalorie", Integer.valueOf(i87));
                    map17.put("sportDistance", Integer.valueOf(i88));
                    arrayList18 = arrayList19;
                    arrayList18.add(map17);
                    bArr5 = bArr;
                    i12 = i89;
                    str5 = str9;
                    map2 = map16;
                    str4 = str10;
                }
                map2.put(str4, Integer.valueOf(Constants.DATATYPE.Health_HistoryHealthMonitoring));
                map2.put(str5, arrayList18);
                break;
            case 45:
                Object obj6 = "data";
                ArrayList arrayList20 = new ArrayList();
                byte[] bArr6 = bArr;
                if (bArr6.length % 42 == 0) {
                    while (true) {
                        int i90 = i12 + 42;
                        if (i90 <= bArr6.length) {
                            long j20 = (bArr6[i12] & 255) + ((bArr6[i12 + 1] & 255) << 8) + ((bArr6[i12 + 2] & 255) << 16) + ((bArr6[i12 + 3] & 255) << 24);
                            long j21 = ((bArr6[i12 + 4] & 255) + ((bArr6[i12 + 5] & 255) << 8) + ((bArr6[i12 + 6] & 255) << 16) + ((bArr6[i12 + 7] & 255) << 24) + 946684800) * 1000;
                            int i91 = (bArr6[i12 + 8] & 255) + ((bArr6[i12 + 9] & 255) << 8) + ((bArr6[i12 + 10] & 255) << 16) + ((bArr6[i12 + 11] & 255) << 24);
                            int i92 = (bArr6[i12 + 12] & 255) + ((bArr6[i12 + 13] & 255) << 8);
                            int i93 = (bArr6[i12 + 14] & 255) + ((bArr6[i12 + 15] & 255) << 8);
                            Object obj7 = obj6;
                            int i94 = bArr6[i12 + 16] & 255;
                            int i95 = bArr6[i12 + 17] & 255;
                            HashMap map18 = map2;
                            int i96 = bArr6[i12 + 18] & 255;
                            String str11 = str4;
                            ArrayList arrayList21 = arrayList20;
                            long j22 = (bArr6[i12 + 19] & 255) + ((bArr6[i12 + 20] & 255) << 8) + ((bArr6[i12 + 21] & 255) << 16) + ((bArr6[i12 + 22] & 255) << 24);
                            int i97 = bArr6[i12 + 23] & 255;
                            int i98 = bArr6[i12 + 24] & 255;
                            int i99 = bArr6[i12 + 25] & 255;
                            long j23 = (bArr6[i12 + 26] & 255) + ((bArr6[i12 + 27] & 255) << 8) + ((bArr6[i12 + 28] & 255) << 16) + ((bArr6[i12 + 29] & 255) << 24);
                            int i100 = (bArr6[i12 + 30] & 255) + ((bArr6[i12 + 31] & 255) << 8);
                            long j24 = (bArr6[i12 + 32] & 255) + ((bArr6[i12 + 33] & 255) << 8) + ((bArr6[i12 + 34] & 255) << 16) + ((bArr6[i12 + 35] & 255) << 24);
                            int i101 = (bArr6[i12 + 36] & 255) + ((bArr6[i12 + 37] & 255) << 8);
                            long j25 = (bArr6[i12 + 38] & 255) + ((bArr6[i12 + 39] & 255) << 8) + ((bArr6[i12 + 40] & 255) << 16) + ((bArr6[i12 + 41] & 255) << 24);
                            HashMap map19 = new HashMap();
                            long j26 = offset;
                            map19.put("startTime", Long.valueOf(((j20 + 946684800) * 1000) - j26));
                            map19.put("endTime", Long.valueOf(j21 - j26));
                            map19.put("sportSteps", Integer.valueOf(i91));
                            map19.put("sportDistances", Integer.valueOf(i92));
                            map19.put("sportCalories", Integer.valueOf(i93));
                            map19.put("sportMode", Integer.valueOf(i94));
                            map19.put("startMethod", Integer.valueOf(i95));
                            map19.put("sportHeartRate", Integer.valueOf(i96));
                            map19.put("sportTime", Long.valueOf(j22));
                            map19.put("minHeartRate", Integer.valueOf(i97));
                            map19.put("maxHeartRate", Integer.valueOf(i98));
                            map19.put("sportType", Integer.valueOf(i99));
                            map19.put("taskId", Long.valueOf(j23));
                            map19.put("gpsLongitudeInteger", Integer.valueOf(i100));
                            map19.put("gpsLongitudeFloat", Long.valueOf(j24));
                            map19.put("gpsLatitudeInteger", Integer.valueOf(i101));
                            map19.put("gpsLatitudeFloat", Long.valueOf(j25));
                            arrayList20 = arrayList21;
                            arrayList20.add(map19);
                            i12 = i90;
                            obj6 = obj7;
                            map2 = map18;
                            str4 = str11;
                        } else {
                            obj = obj6;
                            map = map2;
                            str2 = str4;
                        }
                    }
                } else {
                    obj = obj6;
                    map = map2;
                    str2 = "dataType";
                    if (bArr6.length % 26 == 0) {
                        while (true) {
                            int i102 = i12 + 26;
                            if (i102 <= bArr6.length) {
                                long j27 = (bArr6[i12] & 255) + ((bArr6[i12 + 1] & 255) << 8) + ((bArr6[i12 + 2] & 255) << 16) + ((bArr6[i12 + 3] & 255) << 24);
                                long j28 = ((bArr6[i12 + 4] & 255) + ((bArr6[i12 + 5] & 255) << 8) + ((bArr6[i12 + 6] & 255) << 16) + ((bArr6[i12 + 7] & 255) << 24) + 946684800) * 1000;
                                int i103 = (bArr6[i12 + 8] & 255) + ((bArr6[i12 + 9] & 255) << 8) + ((bArr6[i12 + 10] & 255) << 16) + ((bArr6[i12 + 11] & 255) << 24);
                                int i104 = (bArr6[i12 + 12] & 255) + ((bArr6[i12 + 13] & 255) << 8);
                                int i105 = (bArr6[i12 + 14] & 255) + ((bArr6[i12 + 15] & 255) << 8);
                                int i106 = bArr6[i12 + 16] & 255;
                                int i107 = bArr6[i12 + 17] & 255;
                                int i108 = bArr6[i12 + 18] & 255;
                                ArrayList arrayList22 = arrayList20;
                                long j29 = (bArr6[i12 + 19] & 255) + ((bArr6[i12 + 20] & 255) << 8) + ((bArr6[i12 + 21] & 255) << 16) + ((bArr6[i12 + 22] & 255) << 24);
                                int i109 = bArr6[i12 + 23] & 255;
                                int i110 = bArr6[i12 + 24] & 255;
                                HashMap map20 = new HashMap();
                                long j30 = offset;
                                map20.put("startTime", Long.valueOf(((j27 + 946684800) * 1000) - j30));
                                map20.put("endTime", Long.valueOf(j28 - j30));
                                map20.put("sportSteps", Integer.valueOf(i103));
                                map20.put("sportDistances", Integer.valueOf(i104));
                                map20.put("sportCalories", Integer.valueOf(i105));
                                map20.put("sportMode", Integer.valueOf(i106));
                                map20.put("startMethod", Integer.valueOf(i107));
                                map20.put("sportHeartRate", Integer.valueOf(i108));
                                map20.put("sportTime", Long.valueOf(j29));
                                map20.put("minHeartRate", Integer.valueOf(i109));
                                map20.put("maxHeartRate", Integer.valueOf(i110));
                                arrayList20 = arrayList22;
                                arrayList20.add(map20);
                                bArr6 = bArr;
                                i12 = i102;
                            }
                        }
                    } else if (bArr6.length % 15 == 0) {
                        long j31 = (bArr6[0] & 255) + ((bArr6[1] & 255) << 8) + ((bArr6[2] & 255) << 16) + ((bArr6[3] & 255) << 24);
                        int i111 = (bArr6[8] & 255) + ((bArr6[9] & 255) << 8);
                        int i112 = (bArr6[10] & 255) + ((bArr6[11] & 255) << 8);
                        int i113 = (bArr6[12] & 255) + ((bArr6[13] & 255) << 8);
                        int i114 = bArr6[14] & 255;
                        HashMap map21 = new HashMap();
                        long j32 = offset;
                        map21.put("startTime", Long.valueOf(((j31 + 946684800) * 1000) - j32));
                        map21.put("endTime", Long.valueOf(((((((bArr6[4] & 255) + ((bArr6[5] & 255) << 8)) + ((bArr6[6] & 255) << 16)) + ((bArr6[7] & 255) << 24)) + 946684800) * 1000) - j32));
                        map21.put("sportSteps", Integer.valueOf(i111));
                        map21.put("sportDistances", Integer.valueOf(i112));
                        map21.put("sportCalories", Integer.valueOf(i113));
                        map21.put("sportMode", Integer.valueOf(i114));
                        arrayList20.add(map21);
                    }
                }
                map2 = map;
                map2.put(str2, Integer.valueOf(Constants.DATATYPE.Health_HistorySportMode));
                map2.put(obj, arrayList20);
                break;
            case 47:
                byte[] bArr7 = bArr2;
                Object obj8 = "data";
                ArrayList arrayList23 = new ArrayList();
                while (true) {
                    int i115 = i12 + 44;
                    if (i115 > bArr7.length) {
                        map2.put(str4, Integer.valueOf(Constants.DATATYPE.Health_HistoryComprehensiveMeasureData));
                        map2.put(obj8, arrayList23);
                        break;
                    } else {
                        long j33 = ((bArr7[i12] & 255) + ((bArr7[i12 + 1] & 255) << 8) + ((bArr7[i12 + 2] & 255) << 16) + ((bArr7[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i116 = bArr7[i12 + 4] & 255;
                        int i117 = bArr7[i12 + 5] & 255;
                        int i118 = bArr7[i12 + 6] & 255;
                        int i119 = bArr7[i12 + 7] & 255;
                        int i120 = (bArr7[i12 + 8] & 255) + ((bArr7[i12 + 9] & 255) << 8);
                        int i121 = bArr7[i12 + 10] & 255;
                        int i122 = bArr7[i12 + 11] & 255;
                        Object obj9 = obj8;
                        int i123 = bArr7[i12 + 12] & 255;
                        HashMap map22 = map2;
                        int i124 = bArr7[i12 + 13] & 255;
                        String str12 = str4;
                        int i125 = bArr7[i12 + 14] & 255;
                        ArrayList arrayList24 = arrayList23;
                        int i126 = bArr7[i12 + 15] & 255;
                        int i127 = bArr7[i12 + 16] & 255;
                        int i128 = bArr7[i12 + 17] & 255;
                        int i129 = bArr7[i12 + 18] & 255;
                        int i130 = bArr7[i12 + 19] & 255;
                        int i131 = bArr7[i12 + 20] & 255;
                        int i132 = bArr7[i12 + 21] & 255;
                        HashMap map23 = new HashMap();
                        map23.put("time", Long.valueOf(j33 - offset));
                        map23.put("bloodSugarModel", Integer.valueOf(i116));
                        map23.put("bloodSugarInteger", Integer.valueOf(i117));
                        map23.put("bloodSugarFloat", Integer.valueOf(i118));
                        map23.put("uricAcidModel", Integer.valueOf(i119));
                        map23.put("uricAcid", Integer.valueOf(i120));
                        map23.put("bloodKetoneModel", Integer.valueOf(i121));
                        map23.put("bloodKetoneInteger", Integer.valueOf(i122));
                        map23.put("bloodKetoneFloat", Integer.valueOf(i123));
                        map23.put("bloodFatModel", Integer.valueOf(i124));
                        map23.put("cholesterolInteger", Integer.valueOf(i125));
                        map23.put("cholesterolFloat", Integer.valueOf(i126));
                        map23.put("highLipoproteinCholesterolInteger", Integer.valueOf(i127));
                        map23.put("highLipoproteinCholesterolFloat", Integer.valueOf(i128));
                        map23.put("lowLipoproteinCholesterolInteger", Integer.valueOf(i129));
                        map23.put("lowLipoproteinCholesterolFloat", Integer.valueOf(i130));
                        map23.put("triglycerideCholesterolInteger", Integer.valueOf(i131));
                        map23.put("triglycerideCholesterolFloat", Integer.valueOf(i132));
                        arrayList24.add(map23);
                        bArr7 = bArr;
                        i12 = i115;
                        arrayList23 = arrayList24;
                        obj8 = obj9;
                        map2 = map22;
                        str4 = str12;
                    }
                }
            case 49:
                ArrayList arrayList25 = new ArrayList();
                while (i12 + 44 <= bArr2.length) {
                    long j34 = ((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8) + ((bArr2[i12 + 2] & 255) << 16) + ((bArr2[i12 + 3] & 255) << 24) + 946684800) * 1000;
                    int i133 = bArr2[i12 + 4] & 255;
                    i12 += 8;
                    HashMap map24 = new HashMap();
                    map24.put("time", Long.valueOf(j34 - offset));
                    map24.put("data", Integer.valueOf(i133));
                    arrayList25.add(map24);
                }
                map2.put("dataType", Integer.valueOf(Constants.DATATYPE.health_BackgroundReminderRecord));
                map2.put("data", arrayList25);
                break;
            case 51:
                int i134 = offset;
                Object obj10 = "data";
                ArrayList arrayList26 = new ArrayList();
                while (i12 + 28 <= bArr2.length) {
                    long j35 = ((((((bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8)) + ((bArr2[i12 + 2] & 255) << 16)) + ((bArr2[i12 + 3] & 255) << 24)) + 946684800) * 1000) - i134;
                    int i135 = bArr2[i12 + 4] & 255;
                    int i136 = bArr2[i12 + 5] & 255;
                    int i137 = bArr2[i12 + 6] & 255;
                    int i138 = bArr2[i12 + 7] & 255;
                    int i139 = bArr2[i12 + 8] & 255;
                    int i140 = bArr2[i12 + 9] & 255;
                    int i141 = bArr2[i12 + 10] & 255;
                    int i142 = i134;
                    int i143 = bArr2[i12 + 11] & 255;
                    Object obj11 = obj10;
                    int i144 = bArr2[i12 + 12] & 255;
                    HashMap map25 = map2;
                    int i145 = bArr2[i12 + 13] & 255;
                    String str13 = str4;
                    ArrayList arrayList27 = arrayList26;
                    int i146 = (bArr2[i12 + 14] & 255) + ((bArr2[i12 + 15] & 255) << 8);
                    int i147 = i12 + 17;
                    int i148 = bArr2[i12 + 16] & 255;
                    int i149 = i12 + 25;
                    if (bArr2.length >= i149) {
                        i8 = bArr2[i147] & 255;
                        i7 = (bArr2[i12 + 18] & 255) + ((bArr2[i12 + 19] & 255) << 8);
                        i9 = (bArr2[i12 + 20] & 255) + ((bArr2[i12 + 21] & 255) << 8);
                        i10 = (bArr2[i12 + 22] & 255) + ((bArr2[i12 + 23] & 255) << 8);
                        f2 = (bArr2[i12 + 24] & 255) / 10.0f;
                        i147 = i149;
                    } else {
                        i7 = 0;
                        f2 = 0.0f;
                        i8 = 0;
                        i9 = 0;
                        i10 = 0;
                    }
                    HashMap map26 = new HashMap();
                    map26.put("time", Long.valueOf(j35));
                    map26.put("loadIndexInteger", Integer.valueOf(i135));
                    map26.put("loadIndexFloat", Integer.valueOf(i136));
                    map26.put("hrvInteger", Integer.valueOf(i137));
                    map26.put("hrvFloat", Integer.valueOf(i138));
                    map26.put("pressureInteger", Integer.valueOf(i139));
                    map26.put("pressureFloat", Integer.valueOf(i140));
                    map26.put("bodyInteger", Integer.valueOf(i141));
                    map26.put("bodyFloat", Integer.valueOf(i143));
                    map26.put("sympatheticInteger", Integer.valueOf(i144));
                    map26.put("sympatheticFloat", Integer.valueOf(i145));
                    map26.put("sdn", Integer.valueOf(i146));
                    map26.put("maximalOxygenIntake", Integer.valueOf(i148));
                    map26.put("pnn50", Integer.valueOf(i8));
                    map26.put("rmssd", Integer.valueOf(i7));
                    map26.put("lf", Integer.valueOf(i9));
                    map26.put("hf", Integer.valueOf(i10));
                    map26.put("lfHfRate", Float.valueOf(f2));
                    arrayList27.add(map26);
                    i12 = i147 + 3;
                    arrayList26 = arrayList27;
                    obj10 = obj11;
                    i134 = i142;
                    map2 = map25;
                    str4 = str13;
                }
                map2.put(str4, Integer.valueOf(Constants.DATATYPE.Health_History_Body_Data));
                map2.put(obj10, arrayList26);
                break;
            case 53:
                Object obj12 = "data";
                ArrayList arrayList28 = new ArrayList();
                while (true) {
                    int i150 = i12 + 44;
                    if (i150 > bArr.length) {
                        map2.put(str4, Integer.valueOf(Constants.DATATYPE.Health_LocationData));
                        map2.put(obj12, arrayList28);
                        break;
                    } else {
                        long j36 = ((bArr[i12] & 255) + ((bArr[i12 + 1] & 255) << 8) + ((bArr[i12 + 2] & 255) << 16) + ((bArr[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i151 = bArr[i12 + 4] & 255;
                        int i152 = (bArr[i12 + 5] & 255) + ((bArr[i12 + 6] & 255) << 8);
                        int i153 = bArr[i12 + 7] & 255;
                        int i154 = (bArr[i12 + 8] & 255) + ((bArr[i12 + 9] & 255) << 8);
                        long j37 = (bArr[i12 + 10] & 255) + ((bArr[i12 + 11] & 255) << 8) + ((bArr[i12 + 12] & 255) << 16) + ((bArr[i12 + 13] & 255) << 24);
                        Object obj13 = obj12;
                        int i155 = (bArr[i12 + 14] & 255) + ((bArr[i12 + 15] & 255) << 8);
                        HashMap map27 = map2;
                        String str14 = str4;
                        long j38 = (bArr[i12 + 16] & 255) + ((bArr[i12 + 17] & 255) << 8) + ((bArr[i12 + 18] & 255) << 16) + ((bArr[i12 + 19] & 255) << 24);
                        int i156 = (bArr[i12 + 20] & 255) + ((bArr[i12 + 21] & 255) << 8);
                        long j39 = (bArr[i12 + 22] & 255) + ((bArr[i12 + 23] & 255) << 8) + ((bArr[i12 + 24] & 255) << 16) + ((bArr[i12 + 25] & 255) << 24);
                        int i157 = (bArr[i12 + 26] & 255) + ((bArr[i12 + 27] & 255) << 8);
                        long j40 = (bArr[i12 + 28] & 255) + ((bArr[i12 + 29] & 255) << 8) + ((bArr[i12 + 30] & 255) << 16) + ((bArr[i12 + 31] & 255) << 24);
                        int i158 = (bArr[i12 + 32] & 255) + ((bArr[i12 + 33] & 255) << 8);
                        long j41 = (bArr[i12 + 34] & 255) + ((bArr[i12 + 35] & 255) << 8) + ((bArr[i12 + 36] & 255) << 16) + ((bArr[i12 + 37] & 255) << 24);
                        int i159 = (bArr[i12 + 38] & 255) + ((bArr[i12 + 39] & 255) << 8);
                        long j42 = (bArr[i12 + 40] & 255) + ((bArr[i12 + 41] & 255) << 8) + ((bArr[i12 + 42] & 255) << 16) + ((bArr[i12 + 43] & 255) << 24);
                        HashMap map28 = new HashMap();
                        map28.put("time", Long.valueOf(j36 - offset));
                        map28.put("type", Integer.valueOf(i151));
                        map28.put("airPressureInteger", Integer.valueOf(i152));
                        map28.put("airPressureFloat", Integer.valueOf(i153));
                        map28.put("gpsLongitudeInteger", Integer.valueOf(i154));
                        map28.put("gpsLongitudeFloat", Long.valueOf(j37));
                        map28.put("gpsLatitudeInteger", Integer.valueOf(i155));
                        map28.put("gpsLatitudeFloat", Long.valueOf(j38));
                        map28.put("wifiLongitudeInteger", Integer.valueOf(i156));
                        map28.put("wifiLongitudeFloat", Long.valueOf(j39));
                        map28.put("wifiLatitudeInteger", Integer.valueOf(i157));
                        map28.put("wifiLatitudeFloat", Long.valueOf(j40));
                        map28.put("baseLongitudeInteger", Integer.valueOf(i158));
                        map28.put("baseLongitudeFloat", Long.valueOf(j41));
                        map28.put("baseLatitudeInteger", Integer.valueOf(i159));
                        map28.put("baseLatitudeFloat", Long.valueOf(j42));
                        arrayList28 = arrayList28;
                        arrayList28.add(map28);
                        i12 = i150;
                        obj12 = obj13;
                        map2 = map27;
                        str4 = str14;
                    }
                }
            case 55:
                int i160 = offset;
                ArrayList arrayList29 = new ArrayList();
                while (true) {
                    int i161 = i12 + 13;
                    int i162 = i160;
                    if (i161 > bArr.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_SedentaryRecords));
                        map2.put("data", arrayList29);
                        break;
                    } else {
                        long j43 = (bArr[i12] & 255) + ((bArr[i12 + 1] & 255) << 8) + ((bArr[i12 + 2] & 255) << 16) + ((bArr[i12 + 3] & 255) << 24);
                        long j44 = (bArr[i12 + 4] & 255) + ((bArr[i12 + 5] & 255) << 8) + ((bArr[i12 + 6] & 255) << 16) + ((bArr[i12 + 7] & 255) << 24);
                        int i163 = (bArr[i12 + 8] & 255) + ((bArr[i12 + 9] & 255) << 8) + ((bArr[i12 + 10] & 255) << 16);
                        long j45 = (i163 + ((bArr[i12 + 11] & 255) << 24) + 946684800) * 1000;
                        int i164 = bArr[i12 + 12] & 255;
                        HashMap map29 = new HashMap();
                        long j46 = i162;
                        map29.put("recordTime", Long.valueOf(((j43 + 946684800) * 1000) - j46));
                        map29.put("startTime", Long.valueOf(((j44 + 946684800) * 1000) - j46));
                        map29.put("endTime", Long.valueOf(j45 - j46));
                        map29.put(AlbumLoader.COLUMN_COUNT, Integer.valueOf(i164));
                        arrayList29.add(map29);
                        i12 = i161;
                        i160 = i162;
                    }
                }
            case 57:
                Object obj14 = "data";
                ArrayList arrayList30 = new ArrayList();
                while (true) {
                    int i165 = i12 + 36;
                    if (i165 > bArr.length) {
                        map2.put(str4, Integer.valueOf(Constants.DATATYPE.Health_JiuleComprehensive));
                        map2.put(obj14, arrayList30);
                        break;
                    } else {
                        long j47 = ((bArr[i12] & 255) + ((bArr[i12 + 1] & 255) << 8) + ((bArr[i12 + 2] & 255) << 16) + ((bArr[i12 + 3] & 255) << 24) + 946684800) * 1000;
                        int i166 = bArr[i12 + 4] & 255;
                        int i167 = bArr[i12 + 5] & 255;
                        int i168 = bArr[i12 + 6] & 255;
                        int i169 = bArr[i12 + 7] & 255;
                        int i170 = bArr[i12 + 8] & 255;
                        int i171 = bArr[i12 + 9] & 255;
                        int i172 = (bArr[i12 + 10] & 255) + ((bArr[i12 + 11] & 255) << 8);
                        Object obj15 = obj14;
                        int i173 = (bArr[i12 + 12] & 255) + ((bArr[i12 + 13] & 255) << 8);
                        HashMap map30 = map2;
                        int i174 = (bArr[i12 + 14] & 255) + ((bArr[i12 + 15] & 255) << 8);
                        String str15 = str4;
                        ArrayList arrayList31 = arrayList30;
                        long j48 = (bArr[i12 + 16] & 255) + ((bArr[i12 + 17] & 255) << 8) + ((bArr[i12 + 18] & 255) << 16) + ((bArr[i12 + 19] & 255) << 24);
                        long j49 = (bArr[i12 + 20] & 255) + ((bArr[i12 + 21] & 255) << 8) + ((bArr[i12 + 22] & 255) << 16) + ((bArr[i12 + 23] & 255) << 24);
                        int i175 = bArr[i12 + 24] & 255;
                        int i176 = bArr[i12 + 25] & 255;
                        int i177 = bArr[i12 + 26] & 255;
                        int i178 = bArr[i12 + 27] & 255;
                        long j50 = (bArr[i12 + 28] & 255) + ((bArr[i12 + 29] & 255) << 8) + ((bArr[i12 + 30] & 255) << 16) + ((bArr[i12 + 31] & 255) << 24);
                        int i179 = bArr[i12 + 32] & 255;
                        int i180 = (bArr[i12 + 33] & 255) + ((bArr[i12 + 34] & 255) << 8);
                        int i181 = bArr[i12 + 35] & 255;
                        HashMap map31 = new HashMap();
                        map31.put("time", Long.valueOf(j47 - offset));
                        map31.put("signalLevel", Integer.valueOf(i166));
                        map31.put("heartRate", Integer.valueOf(i167));
                        map31.put("bloodOxygen", Integer.valueOf(i168));
                        map31.put("respiratoryRateValue", Integer.valueOf(i169));
                        map31.put("tempIntValue", Integer.valueOf(i170));
                        map31.put("tempFloatValue", Integer.valueOf(i171));
                        map31.put("step", Integer.valueOf(i172));
                        map31.put("distance", Integer.valueOf(i173));
                        map31.put("calorie", Integer.valueOf(i174));
                        map31.put("threeAxis", Long.valueOf(j48));
                        map31.put("directionScalar", Long.valueOf(j49));
                        map31.put("wearState", Integer.valueOf(i175));
                        map31.put("sportState", Integer.valueOf(i176));
                        map31.put("percentage", Integer.valueOf(i177));
                        map31.put("physiologicalSignals", Integer.valueOf(i178));
                        map31.put("altitudeInteger", Long.valueOf(j50));
                        map31.put("altitudeFloat", Integer.valueOf(i179));
                        map31.put("airPressureInteger", Integer.valueOf(i180));
                        map31.put("airPressureFloat", Integer.valueOf(i181));
                        arrayList30 = arrayList31;
                        arrayList30.add(map31);
                        i12 = i165;
                        obj14 = obj15;
                        str4 = str15;
                        map2 = map30;
                    }
                }
            case 59:
                ArrayList arrayList32 = new ArrayList();
                while (true) {
                    int i182 = i12 + 56;
                    if (i182 > bArr2.length) {
                        map2.put(str4, Integer.valueOf(Constants.DATATYPE.Health_HistoryWarning));
                        map2.put(str5, arrayList32);
                        break;
                    } else {
                        int i183 = bArr2[i12] & 255;
                        long j51 = ((bArr2[i12 + 1] & 255) + ((bArr2[i12 + 2] & 255) << i11) + ((bArr2[i12 + 3] & 255) << 16) + ((bArr2[i12 + 4] & 255) << 24) + j3) * 1000;
                        long j52 = offset;
                        long j53 = j51 - j52;
                        int i184 = (bArr2[i12 + 5] & 255) + ((bArr2[i12 + 6] & 255) << i11);
                        int i185 = ((bArr2[i12 + 8] & 255) << i11) + (bArr2[i12 + 7] & 255);
                        int i186 = offset;
                        long j54 = ((((((bArr2[i12 + 9] & 255) + ((bArr2[i12 + 10] & 255) << 8)) + ((bArr2[i12 + 11] & 255) << 16)) + ((bArr2[i12 + 12] & 255) << 24)) + 946684800) * 1000) - j52;
                        String str16 = str4;
                        long j55 = ((((((bArr2[i12 + 13] & 255) + ((bArr2[i12 + 14] & 255) << 8)) + ((bArr2[i12 + 15] & 255) << 16)) + ((bArr2[i12 + 16] & 255) << 24)) + 946684800) * 1000) - j52;
                        int i187 = (bArr2[i12 + 17] & 255) + ((bArr2[i12 + 18] & 255) << 8);
                        int i188 = bArr2[i12 + 19] & 255;
                        int i189 = (bArr2[i12 + 20] & 255) + ((bArr2[i12 + 21] & 255) << 8);
                        ArrayList arrayList33 = arrayList32;
                        long j56 = (bArr2[i12 + 22] & 255) + ((bArr2[i12 + 23] & 255) << 8) + ((bArr2[i12 + 24] & 255) << 16) + ((bArr2[i12 + 25] & 255) << 24);
                        int i190 = (bArr2[i12 + 26] & 255) + ((bArr2[i12 + 27] & 255) << 8);
                        long j57 = (bArr2[i12 + 28] & 255) + ((bArr2[i12 + 29] & 255) << 8) + ((bArr2[i12 + 30] & 255) << 16) + ((bArr2[i12 + 31] & 255) << 24);
                        int i191 = (bArr2[i12 + 32] & 255) + ((bArr2[i12 + 33] & 255) << 8);
                        long j58 = (bArr2[i12 + 34] & 255) + ((bArr2[i12 + 35] & 255) << 8) + ((bArr2[i12 + 36] & 255) << 16) + ((bArr2[i12 + 37] & 255) << 24);
                        int i192 = (bArr2[i12 + 38] & 255) + ((bArr2[i12 + 39] & 255) << 8);
                        long j59 = (bArr2[i12 + 40] & 255) + ((bArr2[i12 + 41] & 255) << 8) + ((bArr2[i12 + 42] & 255) << 16) + ((bArr2[i12 + 43] & 255) << 24);
                        int i193 = (bArr2[i12 + 44] & 255) + ((bArr2[i12 + 45] & 255) << 8);
                        long j60 = (bArr2[i12 + 46] & 255) + ((bArr2[i12 + 47] & 255) << 8) + ((bArr2[i12 + 48] & 255) << 16) + ((bArr2[i12 + 49] & 255) << 24);
                        int i194 = (bArr2[i12 + 50] & 255) + ((bArr2[i12 + 51] & 255) << 8);
                        long j61 = (bArr2[i12 + 52] & 255) + ((bArr2[i12 + 53] & 255) << 8) + ((bArr2[i12 + 54] & 255) << 16) + ((bArr2[i12 + 55] & 255) << 24);
                        HashMap map32 = new HashMap();
                        map32.put("alarmType", Integer.valueOf(i183));
                        map32.put("alarmTime", Long.valueOf(j53));
                        map32.put("maxOutliers", Integer.valueOf(i184));
                        map32.put("minOutliers", Integer.valueOf(i185));
                        map32.put("startTime", Long.valueOf(j54));
                        map32.put("endTime", Long.valueOf(j55));
                        map32.put(TypedValues.TransitionType.S_DURATION, Integer.valueOf(i187));
                        map32.put("confirmStatus", Integer.valueOf(i188));
                        map32.put("gpsLongitudeInteger", Integer.valueOf(i189));
                        map32.put("gpsLongitudeFloat", Long.valueOf(j56));
                        map32.put("gpsLatitudeInteger", Integer.valueOf(i190));
                        map32.put("gpsLatitudeFloat", Long.valueOf(j57));
                        map32.put("wifiLongitudeInteger", Integer.valueOf(i191));
                        map32.put("wifiLongitudeFloat", Long.valueOf(j58));
                        map32.put("wifiLatitudeInteger", Integer.valueOf(i192));
                        map32.put("wifiLatitudeFloat", Long.valueOf(j59));
                        map32.put("baseLongitudeInteger", Integer.valueOf(i193));
                        map32.put("baseLongitudeFloat", Long.valueOf(j60));
                        map32.put("baseLatitudeInteger", Integer.valueOf(i194));
                        map32.put("baseLatitudeFloat", Long.valueOf(j61));
                        arrayList33.add(map32);
                        i12 = i182;
                        arrayList32 = arrayList33;
                        str4 = str16;
                        str5 = str5;
                        map2 = map2;
                        offset = i186;
                        j3 = 946684800;
                        i11 = 8;
                        bArr2 = bArr;
                    }
                }
            case 102:
                ArrayList arrayList34 = new ArrayList();
                while (true) {
                    int i195 = i12 + 5;
                    if (i195 > bArr2.length) {
                        map2.put("dataType", Integer.valueOf(Constants.DATATYPE.Health_HistoryWearingStatus));
                        map2.put("data", arrayList34);
                        break;
                    } else {
                        long j62 = (bArr2[i12] & 255) + ((bArr2[i12 + 1] & 255) << 8) + ((bArr2[i12 + 2] & 255) << 16) + ((bArr2[i12 + 3] & 255) << 24);
                        int i196 = bArr2[i12 + 4] & 255;
                        long j63 = ((j62 + 946684800) * j2) - offset;
                        HashMap map33 = new HashMap();
                        map33.put("time", Long.valueOf(j63));
                        map33.put("status", Integer.valueOf(i196));
                        arrayList34.add(map33);
                        i12 = i195;
                        j2 = 1000;
                    }
                }
        }
        return map2;
    }

    public static HashMap unpackHengAiData(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null) {
            int i2 = bArr[0] & 255;
            String strByteToString = ByteUtil.byteToString(ByteUtil.getSubArray(bArr, 1));
            map.put("type", Integer.valueOf(i2));
            map.put("byteStr", strByteToString);
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_HengAiData));
        return map;
    }

    public static HashMap unpackHomeTheme(byte[] bArr) {
        int i2;
        HashMap map = new HashMap();
        int i3 = 0;
        map.put("code", 0);
        if (bArr.length > 1) {
            int i4 = bArr[0] & 255;
            i3 = bArr[1] & 255;
            i2 = i4;
        } else {
            i2 = 0;
        }
        map.put("themeTotal", Integer.valueOf(i2));
        map.put("themeCurrentIndex", Integer.valueOf(i3));
        map.put("dataType", 521);
        return map;
    }

    public static HashMap unpackInsuranceNews(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr != null && bArr.length >= 1) {
            map.put("result", Integer.valueOf(bArr[0] & 255));
            if (bArr.length >= 2) {
                map.put("tpeResult", Integer.valueOf(bArr[1] & 255));
            }
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.AppInsuranceNews));
        return map;
    }

    public static HashMap unpackMulPhotoelectricWaveform(byte[] bArr) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (bArr != null) {
            int i2 = bArr[0] & 255;
            map.put("sampleType", Integer.valueOf(i2));
            int i3 = 1;
            if (i2 == 0) {
                while (i3 < bArr.length) {
                    int i4 = (bArr[i3] & 255) + ((bArr[i3 + 1] & 255) << 8) + ((bArr[i3 + 2] & 255) << 16) + ((bArr[i3 + 3] & 255) << 24);
                    int i5 = (bArr[i3 + 4] & 255) + ((bArr[i3 + 5] & 255) << 8) + ((bArr[i3 + 6] & 255) << 16) + ((bArr[i3 + 7] & 255) << 24);
                    int i6 = i3 + 11;
                    int i7 = (bArr[i3 + 8] & 255) + ((bArr[i3 + 9] & 255) << 8) + ((bArr[i3 + 10] & 255) << 16);
                    i3 += 12;
                    int i8 = i7 + ((bArr[i6] & 255) << 24);
                    arrayList.add(Integer.valueOf(i4));
                    arrayList2.add(Integer.valueOf(i4));
                    arrayList.add(Integer.valueOf(i5));
                    arrayList3.add(Integer.valueOf(i5));
                    arrayList.add(Integer.valueOf(i8));
                    arrayList4.add(Integer.valueOf(i8));
                }
            } else if (i2 == 1) {
                while (i3 < bArr.length) {
                    int i9 = ((bArr[i3] & 255) << 24) + ((bArr[i3 + 1] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 3] & 255);
                    int i10 = i3 + 7;
                    int i11 = ((bArr[i3 + 4] & 255) << 24) + ((bArr[i3 + 5] & 255) << 16) + ((bArr[i3 + 6] & 255) << 8);
                    i3 += 8;
                    int i12 = i11 + (bArr[i10] & 255);
                    arrayList.add(Integer.valueOf(i9));
                    arrayList2.add(Integer.valueOf(i9));
                    arrayList.add(Integer.valueOf(i12));
                    arrayList3.add(Integer.valueOf(i12));
                }
            } else if (i2 == 2) {
                while (i3 < bArr.length) {
                    int i13 = ((bArr[i3] & 255) << 24) + ((bArr[i3 + 1] & 255) << 16) + ((bArr[i3 + 2] & 255) << 8) + (bArr[i3 + 3] & 255);
                    int i14 = i3 + 7;
                    int i15 = ((bArr[i3 + 4] & 255) << 24) + ((bArr[i3 + 5] & 255) << 16) + ((bArr[i3 + 6] & 255) << 8);
                    i3 += 8;
                    int i16 = i15 + (bArr[i14] & 255);
                    arrayList.add(Integer.valueOf(i13));
                    arrayList3.add(Integer.valueOf(i13));
                    arrayList.add(Integer.valueOf(i16));
                    arrayList4.add(Integer.valueOf(i16));
                }
            }
        }
        map.put("green", arrayList2);
        map.put("ir", arrayList3);
        map.put("red", arrayList4);
        map.put("data", arrayList);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadMulPhotoelectricWaveform));
        return map;
    }

    public static HashMap unpackOTAData(byte[] bArr) {
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        HashMap map = new HashMap();
        map.put(ServerProtocol.DIALOG_PARAM_STATE, Integer.valueOf(i2));
        map.put("success", Integer.valueOf(i3));
        map.put("code", 0);
        map.put("dataType", 2560);
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0509  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashMap unpackParseData(byte[] r37, int r38) {
        /*
            Method dump skipped, instructions count: 1332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yucheng.ycbtsdk.core.DataUnpack.unpackParseData(byte[], int):java.util.HashMap");
    }

    public static HashMap unpackRealAmbientlightData(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadAmbientlight));
        return map;
    }

    public static HashMap unpackRealBloodData(byte[] bArr) {
        int i2 = 3;
        if (bArr.length < 3) {
            return null;
        }
        int i3 = bArr[0] & 255;
        int i4 = bArr[1] & 255;
        int i5 = bArr[2] & 255;
        YCBTLog.e("实时血压 DBP " + i4 + " SBP " + i3 + " Heart " + i5);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", 1539);
        map.put("heartValue", Integer.valueOf(i5));
        map.put("bloodDBP", Integer.valueOf(i4));
        map.put("bloodSBP", Integer.valueOf(i3));
        if (bArr.length > 3) {
            map.put("hrv", Integer.valueOf(bArr[3] & 255));
            i2 = 4;
        }
        if (bArr.length > 4) {
            map.put("bloodOxygen", Integer.valueOf(bArr[i2] & 255));
            i2++;
        }
        if (bArr.length > 6) {
            int i6 = i2 + 1;
            int i7 = bArr[i2] & 255;
            int i8 = bArr[i6] & 255;
            map.put("tempInteger", Integer.valueOf(i7));
            map.put("tempFloat", Integer.valueOf(i8));
        }
        return map;
    }

    public static HashMap unpackRealBloodOxygenData(byte[] bArr) {
        int i2 = bArr[0] & 255;
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", 1538);
        map.put("bloodOxygenValue", Integer.valueOf(i2));
        return map;
    }

    public static HashMap unpackRealComprehensiveData(byte[] bArr) {
        if (bArr.length < 20) {
            return null;
        }
        int i2 = (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16);
        int i3 = (bArr[3] & 255) + ((bArr[4] & 255) << 8);
        int i4 = (bArr[5] & 255) + ((bArr[6] & 255) << 8);
        int i5 = bArr[7] & 255;
        int i6 = bArr[8] & 255;
        int i7 = bArr[9] & 255;
        int i8 = bArr[10] & 255;
        int i9 = bArr[11] & 255;
        int i10 = bArr[12] & 255;
        int i11 = bArr[13] & 255;
        int i12 = bArr[14] & 255;
        int i13 = bArr[15] & 255;
        int i14 = (bArr[16] & 255) + ((bArr[17] & 255) << 8) + ((bArr[18] & 255) << 16) + ((bArr[19] & 255) << 24);
        int i15 = bArr[20] & 255;
        int i16 = (bArr[21] & 255) + ((bArr[22] & 255) << 8);
        int i17 = bArr[23] & 255;
        int i18 = bArr[24] & 255;
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadComprehensive));
        map.put("step", Integer.valueOf(i2));
        map.put("dis", Integer.valueOf(i3));
        map.put("kcal", Integer.valueOf(i4));
        map.put("heartRate", Integer.valueOf(i5));
        map.put("SBP", Integer.valueOf(i6));
        map.put("DBP", Integer.valueOf(i7));
        map.put("bloodOxygen", Integer.valueOf(i8));
        map.put("respirationRate", Integer.valueOf(i9));
        map.put("tempInteger", Integer.valueOf(i10));
        map.put("tempFloat", Integer.valueOf(i11));
        map.put("wearingState", Integer.valueOf(i12));
        map.put("electricity", Integer.valueOf(i13));
        map.put("ppi", Integer.valueOf(i14));
        map.put("bloodSugar", Integer.valueOf(i15));
        map.put("uricAcid", Integer.valueOf(i16));
        map.put("bloodLipidsInteger", Integer.valueOf(i17));
        map.put("bloodLipidsFloat", Integer.valueOf(i18));
        return map;
    }

    public static HashMap unpackRealECGData(byte[] bArr) {
        HashMap<String, List<Integer>> mapEcgRealWaveFilteringMap = AITools.getInstance().ecgRealWaveFilteringMap(bArr);
        mapEcgRealWaveFilteringMap.put("code", 0);
        mapEcgRealWaveFilteringMap.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadECG));
        return mapEcgRealWaveFilteringMap;
    }

    public static HashMap unpackRealGsensorData(byte[] bArr) {
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        int i4 = bArr[2] & 255;
        int i5 = bArr[3] & 255;
        ArrayList arrayList = new ArrayList();
        int i6 = 4;
        for (int i7 = 0; i7 < i5; i7++) {
            try {
                GsensorBean gsensorBean = new GsensorBean();
                gsensorBean.x = Short.valueOf((short) ((bArr[i6] & 255) + ((bArr[i6 + 1] & 255) << 8)));
                gsensorBean.y = Short.valueOf((short) ((bArr[i6 + 2] & 255) + ((bArr[i6 + 3] & 255) << 8)));
                int i8 = i6 + 5;
                int i9 = bArr[i6 + 4] & 255;
                i6 += 6;
                gsensorBean.z = Short.valueOf((short) (i9 + ((bArr[i8] & 255) << 8)));
                arrayList.add(gsensorBean);
            } catch (Exception e2) {
                YCBTLog.e("gsensor 数据解析异常:" + e2.getMessage());
                e2.printStackTrace();
            }
        }
        YCBTLog.e("gsensor 操作码" + i2 + " 姿态 " + i3 + " 长度 " + i5);
        YCBTLog.e(new Gson().toJson(arrayList));
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Gsensor));
        map.put("operateCode", Integer.valueOf(i2));
        map.put("posture", Integer.valueOf(i3));
        map.put("dataLength", Integer.valueOf(i4));
        map.put("gsensorBeanList", arrayList);
        return map;
    }

    public static HashMap unpackRealHeartData(byte[] bArr) {
        int i2 = bArr[0] & 255;
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", 1537);
        map.put("heartValue", Integer.valueOf(i2));
        return map;
    }

    public static HashMap unpackRealPPGData(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadPPG));
        map.put("data", bArr);
        return map;
    }

    public static HashMap unpackRealRespiratoryRateData(byte[] bArr) {
        int i2 = bArr[0] & 255;
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadRespiratoryRate));
        map.put("respiratoryRateValue", Integer.valueOf(i2));
        return map;
    }

    public static HashMap unpackRealSensorData(byte[] bArr) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadSensor));
        int i2 = bArr[0] & 255;
        if (i2 == 0) {
            int i3 = 1;
            while (true) {
                int i4 = i3 + 6;
                if (i4 > bArr.length) {
                    break;
                }
                HashMap map2 = new HashMap();
                int signed16BitLittleEndian = ByteUtil.parseSigned16BitLittleEndian(bArr, i3);
                int signed16BitLittleEndian2 = ByteUtil.parseSigned16BitLittleEndian(bArr, i3 + 2);
                int signed16BitLittleEndian3 = ByteUtil.parseSigned16BitLittleEndian(bArr, i3 + 4);
                map2.put("x", Integer.valueOf(signed16BitLittleEndian));
                map2.put("y", Integer.valueOf(signed16BitLittleEndian2));
                map2.put("z", Integer.valueOf(signed16BitLittleEndian3));
                arrayList.add(map2);
                i3 = i4;
            }
        }
        map.put("type", Integer.valueOf(i2));
        map.put("data", arrayList);
        return map;
    }

    public static HashMap unpackRealSportData(byte[] bArr) {
        int i2 = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
        int i3 = (bArr[2] & 255) + ((bArr[3] & 255) << 8);
        int i4 = (bArr[4] & 255) + ((bArr[5] & 255) << 8);
        YCBTLog.e("实时步数 " + i2 + " Dis " + i3 + " Cal " + i4);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadSport));
        map.put("sportStep", Integer.valueOf(i2));
        map.put("sportCalorie", Integer.valueOf(i4));
        map.put("sportDistance", Integer.valueOf(i3));
        return map;
    }

    public static HashMap unpackRealUploadRunData(byte[] bArr) {
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_UploadRun));
        return map;
    }

    public static HashMap unpackRingProductionTestHost(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr.length >= 26) {
            int i2 = bArr[0] & 255;
            byte b2 = bArr[1];
            byte[] bArr2 = new byte[12];
            byte[] bArr3 = new byte[12];
            System.arraycopy(bArr, 2, bArr2, 0, 12);
            System.arraycopy(bArr, 14, bArr3, 0, 12);
            String strByteToStr = ByteUtil.byteToStr(bArr2);
            String strByteToStr2 = ByteUtil.byteToStr(bArr3);
            byte b3 = bArr[26];
            byte b4 = bArr[27];
            byte b5 = bArr[28];
            map.put("mode", Integer.valueOf(i2));
            map.put("rssi", Integer.valueOf(b2));
            map.put("filterName", strByteToStr);
            map.put("replaceName", strByteToStr2);
            map.put("battery", Integer.valueOf(b3));
            map.put("subVersion", Integer.valueOf(b4));
            map.put("mainVersion", Integer.valueOf(b5));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetRingProductionTestHostConfig));
        return map;
    }

    public static HashMap unpackTerminalConf(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr.length >= 10) {
            int i2 = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
            int i3 = (bArr[2] & 255) + ((bArr[3] & 255) << 8);
            int i4 = bArr[4] & 255;
            int i5 = bArr[5] & 255;
            int i6 = bArr[6] & 255;
            int i7 = bArr[7] & 255;
            int i8 = bArr[8] & 255;
            int i9 = bArr[9] & 255;
            map.put("sportsTarget", Integer.valueOf(i2));
            map.put("rehabilitationTarget", Integer.valueOf(i3));
            map.put("deviceMode", Integer.valueOf(i4));
            map.put("fallMode", Integer.valueOf(i5));
            map.put("notDisturbStartHour", Integer.valueOf(i6));
            map.put("notDisturbStartMinute", Integer.valueOf(i7));
            map.put("notDisturbEndHour", Integer.valueOf(i8));
            map.put("notDisturbEndMinute", Integer.valueOf(i9));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.GetTerminalConf));
        return map;
    }

    public static HashMap unpackUIFileBreakInfo(byte[] bArr) {
        HashMap map = new HashMap();
        if (bArr.length > 8) {
            int i2 = (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16) + ((bArr[3] & 255) << 24);
            int i3 = (bArr[4] & 255) + ((bArr[5] & 255) << 8) + ((bArr[6] & 255) << 16) + ((bArr[7] & 255) << 24);
            int i4 = (bArr[8] & 255) + ((bArr[9] & 255) << 8);
            YCBTLog.e("总长度 " + i2 + " 已升级偏移量 " + i3 + " 检验码 " + i4);
            map.put("code", 0);
            map.put("dataType", 32256);
            map.put("uiFileTotalLen", Integer.valueOf(i2));
            map.put("uiFileOffset", Integer.valueOf(i3));
            map.put("uiFileCheckSum", Integer.valueOf(i4));
        } else {
            map.put("code", 1);
        }
        return map;
    }

    public static HashMap unpackWearingStatusData(byte[] bArr) {
        int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis());
        HashMap map = new HashMap();
        if (bArr != null) {
            long j2 = (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16) + ((bArr[3] & 255) << 24);
            int i2 = bArr[4] & 255;
            map.put("time", Long.valueOf(((j2 + 946684800) * 1000) - offset));
            map.put("status", Integer.valueOf(i2));
        }
        map.put("dataType", Integer.valueOf(Constants.DATATYPE.Real_WearingStatus));
        return map;
    }

    public static HashMap<String, Object> unpackWit(byte[] bArr) {
        HashMap<String, Object> map = new HashMap<>();
        int i2 = bArr[0] & 255;
        int i3 = (bArr[1] & 255) << 8;
        map.put("opcode", Integer.valueOf(i2));
        map.put("code", Integer.valueOf(i3));
        return map;
    }
}
