package com.ahmedco;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Utlis {


    public static void setServiceOnOff(Context context,Boolean value){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
       // Boolean statusLocked = prefs.edit().putBoolean("locked", value).commit();
        prefs.edit().putBoolean("locked", value).apply();
    }


    public static Boolean getServiceOnOff(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean yourLocked = prefs.getBoolean("locked", false);
        return  yourLocked;
    }

}
