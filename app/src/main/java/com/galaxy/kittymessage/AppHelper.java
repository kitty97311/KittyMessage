package com.galaxy.kittymessage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppHelper {
    public static String GetCurrentLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("current_language", "not_set");
    }

    public static void SetCurrentLanguage(Context context, String curLang) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("current_language", curLang).apply();
    }
}
