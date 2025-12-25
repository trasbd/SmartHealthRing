package com.yucheng.ycbtsdk.utils;

import com.yucheng.ycbtsdk.Constants;

/* loaded from: classes5.dex */
public class DeviceSupportFunctionUtil {
    public static int alarmCount() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ALARMCOUNT, 0)).intValue();
    }

    public static boolean isEncryption() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASENCRYPTION, 0)).intValue() == 1;
    }

    public static boolean isHasAntiLost() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASANTILOST, 0)).intValue() == 1;
    }

    public static boolean isHasAppoint() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASAPPOINT, 0)).intValue() == 1;
    }

    public static boolean isHasBadminton() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASBADMINTON, 0)).intValue() == 1;
    }

    public static boolean isHasBasketball() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASBASKETBALL, 0)).intValue() == 1;
    }

    public static boolean isHasBlood() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASBLOOD, 0)).intValue() == 1;
    }

    public static boolean isHasBloodAlarm() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASBLOODALARM, 0)).intValue() == 1;
    }

    public static boolean isHasBloodLevel() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASBLOODLEVEL, 0)).intValue() == 1;
    }

    public static boolean isHasBloodOxygen() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASBLOODOXYGEN, 0)).intValue() == 1;
    }

    public static boolean isHasBloodPressureCalibration() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASBLOODPRESSURECALIBRATION, 0)).intValue() == 1;
    }

    public static boolean isHasCVRR() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASCVRR, 0)).intValue() == 1;
    }

    public static boolean isHasCallPhone() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASCALLPHONE, 0)).intValue() == 1;
    }

    public static boolean isHasContacts() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASCONTACTS, 0)).intValue() == 1;
    }

    public static boolean isHasCustom() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASCUSTOM, 0)).intValue() == 1;
    }

    public static boolean isHasDial() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASDIAL, 0)).intValue() == 1;
    }

    public static boolean isHasECG() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASECGDIAGNOSIS, 0)).intValue() == 1;
    }

    public static boolean isHasECGRightElectrode() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASECGRIGHTELECTRODE, 0)).intValue() == 1;
    }

    public static boolean isHasEcgHistoryUpload() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASECGHISTORYUPLOAD, 0)).intValue() == 1;
    }

    public static boolean isHasEcgRealUpload() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASECGREALUPLOAD, 0)).intValue() == 1;
    }

    public static boolean isHasEmail() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASEMAIL, 0)).intValue() == 1;
    }

    public static boolean isHasFacebook() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFACEBOOK, 0)).intValue() == 1;
    }

    public static boolean isHasFactorySetting() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFACTORYSETTING, 0)).intValue() == 1;
    }

    public static boolean isHasFemalePhysiologicalCycle() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFEMALEPHYSIOLOGICALCYCLE, 0)).intValue() == 1;
    }

    public static boolean isHasFindDevice() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFINDDEVICE, 0)).intValue() == 1;
    }

    public static boolean isHasFindPhone() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFINDPHONE, 0)).intValue() == 1;
    }

    public static boolean isHasFirmwareUpdate() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFIRMWAREUPDATE, 0)).intValue() == 1;
    }

    public static boolean isHasFitness() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFITNESS, 0)).intValue() == 1;
    }

    public static boolean isHasFootball() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASFOOTBALL, 0)).intValue() == 1;
    }

    public static boolean isHasGetUp() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASGETUP, 0)).intValue() == 1;
    }

    public static boolean isHasHRV() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASHRV, 0)).intValue() == 1;
    }

    public static boolean isHasHeartAlarm() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASHEARTALARM, 0)).intValue() == 1;
    }

    public static boolean isHasHeartRate() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASHEARTRATE, 0)).intValue() == 1;
    }

    public static boolean isHasIndoorRuning() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASINDOORRUNING, 0)).intValue() == 1;
    }

    public static boolean isHasIndoorWalking() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASINDOORWALKING, 0)).intValue() == 1;
    }

    public static boolean isHasInformation() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASINFORMATION, 0)).intValue() == 1;
    }

    public static boolean isHasInstagram() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASINSTAGRAM, 0)).intValue() == 1;
    }

    public static boolean isHasLiftBright() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASLIFTBRIGHT, 0)).intValue() == 1;
    }

    public static boolean isHasLine() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASLINE, 0)).intValue() == 1;
    }

    public static boolean isHasLinkedIn() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASLINKEDIN, 0)).intValue() == 1;
    }

    public static boolean isHasLongSitting() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASLONGSITTING, 0)).intValue() == 1;
    }

    public static boolean isHasManyLanguage() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMANYLANGUAGE, 0)).intValue() == 1;
    }

    public static boolean isHasMeeting() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMEETING, 0)).intValue() == 1;
    }

    public static boolean isHasMessage() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMESSAGE, 0)).intValue() == 1;
    }

    public static boolean isHasMessenger() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMESSENGER, 0)).intValue() == 1;
    }

    public static boolean isHasMoreSport() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMORESPORT, 0)).intValue() == 1;
    }

    public static boolean isHasMountainClimbing() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMOUNTAINCLIMBING, 0)).intValue() == 1;
    }

    public static boolean isHasMusic() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMUSIC, 0)).intValue() == 1;
    }

    public static boolean isHasNotitoggle() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASNOTITOGGLE, 0)).intValue() == 1;
    }

    public static boolean isHasOtherMessenger() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASOTHERMESSENGER, 0)).intValue() == 1;
    }

    public static boolean isHasOutdoorRuning() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASOUTDOORRUNING, 0)).intValue() == 1;
    }

    public static boolean isHasOutdoorWalking() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASOUTDOORWALKING, 0)).intValue() == 1;
    }

    public static boolean isHasParty() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASPARTY, 0)).intValue() == 1;
    }

    public static boolean isHasPhoneSupport() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASPHONESUPPORT, 0)).intValue() == 1;
    }

    public static boolean isHasQQ() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASQQ, 0)).intValue() == 1;
    }

    public static boolean isHasRealData() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASREALDATA, 0)).intValue() == 1;
    }

    public static boolean isHasRespiratoryRate() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASRESPIRATORYRATE, 0)).intValue() == 1;
    }

    public static boolean isHasRiding() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASRIDING, 0)).intValue() == 1;
    }

    public static boolean isHasRopeSkipping() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASROPESKIPPING, 0)).intValue() == 1;
    }

    public static boolean isHasRunning() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASRUNNING, 0)).intValue() == 1;
    }

    public static boolean isHasSearcharound() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSEARCHAROUND, 0)).intValue() == 1;
    }

    public static boolean isHasSetInfo() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSETINFO, 0)).intValue() == 1;
    }

    public static boolean isHasSina() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSINA, 0)).intValue() == 1;
    }

    public static boolean isHasSkincolor() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSKINCOLOR, 0)).intValue() == 1;
    }

    public static boolean isHasSkype() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSKYPE, 0)).intValue() == 1;
    }

    public static boolean isHasSleep() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSLEEP, 0)).intValue() == 1;
    }

    public static boolean isHasSnapChat() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSNAPCHAT, 0)).intValue() == 1;
    }

    public static boolean isHasStepCount() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSTEPCOUNT, 0)).intValue() == 1;
    }

    public static boolean isHasSupportFunction(String str) {
        return str != null && ((Integer) SPUtil.get(str, 0)).intValue() == 1;
    }

    public static boolean isHasSwimming() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSWIMMING, 0)).intValue() == 1;
    }

    public static boolean isHasTakeExercise() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTAKEEXERCISE, 0)).intValue() == 1;
    }

    public static boolean isHasTakePhoto() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASSHAKETAKEPHOTO, 0)).intValue() == 1 || ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASMANUALTAKEPHOTO, 0)).intValue() == 1;
    }

    public static boolean isHasTakePills() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTAKEPILLS, 0)).intValue() == 1;
    }

    public static boolean isHasTakeSleep() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTAKESLEEP, 0)).intValue() == 1;
    }

    public static boolean isHasTemp() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTEMP, 0)).intValue() == 1;
    }

    public static boolean isHasTempAlarm() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTEMPALARM, 0)).intValue() == 1;
    }

    public static boolean isHasTempAxillaryTest() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTEMPAXILLARYTEST, 0)).intValue() == 1;
    }

    public static boolean isHasTempCalibration() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTEMPCALIBRATION, 0)).intValue() == 1;
    }

    public static boolean isHasTennis() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASPINGPONG, 0)).intValue() == 1;
    }

    public static boolean isHasTheme() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTHEME, 0)).intValue() == 1;
    }

    public static boolean isHasTodayWeather() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTODAYWEATHER, 0)).intValue() == 1;
    }

    public static boolean isHasTomorrowWeather() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTOMORROWWEATHER, 0)).intValue() == 1;
    }

    public static boolean isHasTwitter() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASTWITTER, 0)).intValue() == 1;
    }

    public static boolean isHasWalking() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASWALKING, 0)).intValue() == 1;
    }

    public static boolean isHasWeChat() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASWECHAT, 0)).intValue() == 1;
    }

    public static boolean isHasWechatsport() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASWECHATSPORT, 0)).intValue() == 1;
    }

    public static boolean isHasWhatsAPP() {
        return ((Integer) SPUtil.get(Constants.FunctionConstant.ISHASWHATSAPP, 0)).intValue() == 1;
    }

    public static Object readDeviceInfo(String str) {
        return SPUtil.get(str, "");
    }
}
