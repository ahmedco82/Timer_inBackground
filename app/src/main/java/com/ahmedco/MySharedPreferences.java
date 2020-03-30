package com.ahmedco;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySharedPreferences {

    public static void saveData(Context con , String variable,Times times){
       Gson gson = new Gson();
       String stringUser = gson.toJson(times);
       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
       prefs.edit().putString(variable, stringUser).apply();
    }


    public static Times getData(Context con, String variable){
      Times times = new Times();
       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        String data = prefs.getString(variable, "");
         List<String> values = new ArrayList<String>();
           try{
             JSONObject obj = new JSONObject(data.toString());
              JSONObject jsonObject = new JSONObject(data);
               Iterator<String> keysItr = jsonObject.keys();
                 for(Iterator<String> iter = jsonObject.keys(); iter.hasNext();) {
                  String key = iter.next();
                   values.add(key);
              }
            if(obj != null){
                times.setEveryTime(Integer.parseInt(obj.getString(values.get(0))));
                times.setStopTimer(Integer.parseInt(obj.getString(values.get(1))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return times;
    }
}
