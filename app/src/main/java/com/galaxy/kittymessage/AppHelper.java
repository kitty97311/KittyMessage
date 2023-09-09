package com.galaxy.kittymessage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppHelper {
    public static String GetCurrentLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(AppConstant.CUR_LANG_KEY, AppConstant.NOT_SET_KEY);
    }

    public static void SetCurrentLanguage(Context context, String curLang) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(AppConstant.CUR_LANG_KEY, curLang).apply();
    }

    public static String GetMessageGroupDate(Context context, Calendar cal) {
        Calendar now = Calendar.getInstance();

        int differenceInDays = now.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        int differenceInHours = now.get(Calendar.HOUR_OF_DAY) - cal.get(Calendar.HOUR_OF_DAY);
        int differenceInMinutes = now.get(Calendar.MINUTE) - cal.get(Calendar.MINUTE);

        if (differenceInDays > 30) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d", Locale.getDefault());
            return dateFormat.format(now.getTimeInMillis());
        } else if (differenceInDays > 0) {
            return context.getResources().getString(R.string.some_days_ago, differenceInDays);
        } else if (differenceInHours > 0) {
            return context.getResources().getString(R.string.some_hours_ago, differenceInHours);
        } else if (differenceInMinutes > 0) {
            return context.getResources().getString(R.string.some_mins_ago, differenceInMinutes);
        } else {
            return context.getResources().getString(R.string.just_now);
        }
    }

    public static String GetMessageDetailDate(Context context, Calendar cal) {
        Calendar now = Calendar.getInstance();

        int differenceInDays = now.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);

        if (differenceInDays > 1) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMM", Locale.getDefault());
            return dateFormat.format(cal.getTimeInMillis());
        } else if (differenceInDays == 1) {
            return context.getResources().getString(R.string.yesterday);
        } else {
            return context.getResources().getString(R.string.today);
        }
    }

    public static String GetMessageDetailTime(Context context, Calendar cal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a", Locale.getDefault());
        return dateFormat.format(cal.getTimeInMillis());
    }

    public static String GetCleanPhoneNumber(String num) {
        return num.replaceAll("[()\\-\\s]", "");
    }

    public static String GetCurrentPassword(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(AppConstant.CUR_PASSWORD_KEY, AppConstant.NOT_SET_KEY);
    }

    public static void SetCurrentPassword(Context context, String curPassword) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(AppConstant.CUR_PASSWORD_KEY, curPassword).apply();
    }

    public static boolean GetShowProfilePhotosSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[0][0], (Boolean) AppConstant.APP_SETTING[0][1]);
    }

    public static void SetShowProfilePhotosSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[0][0], setting).apply();
    }

    public static boolean GetAssignContactPhotosSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[1][0], (Boolean) AppConstant.APP_SETTING[1][1]);
    }

    public static void SetAssignContactPhotosSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[1][0], setting).apply();
    }

    public static boolean GetNotificationGroupSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[2][0], (Boolean) AppConstant.APP_SETTING[2][1]);
    }

    public static void SetNotificationGroupSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[2][0], setting).apply();
    }

    public static boolean GetShowBlockedSmsSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[3][0], (Boolean) AppConstant.APP_SETTING[3][1]);
    }

    public static void SetShowBlockedSmsSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[3][0], setting).apply();
    }

    public static boolean GetShowMessagesSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[4][0], (Boolean) AppConstant.APP_SETTING[4][1]);
    }

    public static void SetShowMessagesSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[4][0], setting).apply();
    }

    public static boolean GetDeliveryStatusSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[5][0], (Boolean) AppConstant.APP_SETTING[5][1]);
    }

    public static void SetDeliveryStatusSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[5][0], setting).apply();
    }

    public static boolean GetDeliverySoundSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[6][0], (Boolean) AppConstant.APP_SETTING[6][1]);
    }

    public static void SetDeliverySoundSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[6][0], setting).apply();
    }

    public static boolean GetSendLongMessagesAsMmsSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[7][0], (Boolean) AppConstant.APP_SETTING[7][1]);
    }

    public static void SetSendLongMessagesAsMmsSetting(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[7][0], setting).apply();
    }

    public static int GetAutoCompressMmsAttachmentsSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt((String) AppConstant.APP_SETTING[8][0], (int) AppConstant.APP_SETTING[8][1]);
    }

    public static void SetAutoCompressMmsAttachmentsSetting(Context context, int setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt((String) AppConstant.APP_SETTING[8][0], setting).apply();
    }

    public static String GetFontSizeSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString((String) AppConstant.APP_SETTING[9][0], context.getResources().getString((int)AppConstant.APP_SETTING[9][1]));
    }

    public static void SetFontSizeSetting(Context context, String setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString((String) AppConstant.APP_SETTING[9][0], setting).apply();
    }

    public static String GetDeleteOldMessagesAutomaticallySetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString((String) AppConstant.APP_SETTING[16][0], context.getResources().getString((int)AppConstant.APP_SETTING[16][1]));
    }

    public static void SetDeleteOldMessagesAutomaticallySetting(Context context, String setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString((String) AppConstant.APP_SETTING[16][0], setting).apply();
    }

    public static boolean GetPopupReply(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[10][0], (Boolean) AppConstant.APP_SETTING[10][1]);
    }

    public static void SetPopupReply(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[10][0], setting).apply();
    }

    public static boolean GetTapToDismiss(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean((String) AppConstant.APP_SETTING[11][0], (Boolean) AppConstant.APP_SETTING[11][1]);
    }

    public static void SetTapToDismiss(Context context, boolean setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean((String) AppConstant.APP_SETTING[11][0], setting).apply();
    }

    public static String GetNotificationPreviewsSetting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString((String) AppConstant.APP_SETTING[12][0], context.getResources().getString((int)AppConstant.APP_SETTING[12][1]));
    }

    public static void SetNotificationPreviewsSetting(Context context, String setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString((String) AppConstant.APP_SETTING[12][0], setting).apply();
    }

    public static String GetButton1Setting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString((String) AppConstant.APP_SETTING[13][0], context.getResources().getString((int)AppConstant.APP_SETTING[13][1]));
    }

    public static void SetButton1Setting(Context context, String setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString((String) AppConstant.APP_SETTING[13][0], setting).apply();
    }

    public static String GetButton2Setting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString((String) AppConstant.APP_SETTING[14][0], context.getResources().getString((int)AppConstant.APP_SETTING[14][1]));
    }

    public static void SetButton2Setting(Context context, String setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString((String) AppConstant.APP_SETTING[14][0], setting).apply();
    }


    public static String GetButton3Setting(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString((String) AppConstant.APP_SETTING[15][0], context.getResources().getString((int)AppConstant.APP_SETTING[15][1]));
    }

    public static void SetButton3Setting(Context context, String setting) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString((String) AppConstant.APP_SETTING[15][0], setting).apply();
    }

}
