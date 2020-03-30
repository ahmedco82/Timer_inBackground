package com.ahmedco;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Preferences {


    public static void setArrayPrefs(String arrayName, ArrayList<Boolean> array, Context mContext) {
      SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
       SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
         for(int i=0;i<array.size();i++)
         editor.putBoolean(arrayName + "_" + i, array.get(i));
          editor.apply();
    }

    public static ArrayList<Boolean> getArrayPrefs(String arrayName, Context mContext) {
      SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
          ArrayList<Boolean> array = new ArrayList<Boolean>(size);
          for(int i=0;i<size;i++)
        array.add(prefs.getBoolean(arrayName + "_" + i, false));
     return array;
   }

    public static void saveTimes(Context con , Times times){
        Gson gson = new Gson();
        String stringUser = gson.toJson(times);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        prefs.edit().putString("variable", stringUser).apply();
    }

    public static Times getTimes(Context con){
     Times times = new Times();
      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
       String data = prefs.getString("variable", "");
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
                times.setStopTimer(Integer.parseInt(obj.getString("stopTimer")));
                times.setEveryTime(Integer.parseInt(obj.getString("everyTime")));

                /*
                if(times.getStopTimer()==1){
                    times.setHour_start(Integer.parseInt(obj.getString(values.get(0))));
                    times.setHour_end(Integer.parseInt(obj.getString(values.get(1))));
                    times.setMinute_start(Integer.parseInt(obj.getString(values.get(2))));
                    times.setMinute_end(Integer.parseInt(obj.getString(values.get(3))));
                    times.setStart_AM_PM(Integer.parseInt(obj.getString(values.get(4))));
                    times.setEnd_AM_PM(Integer.parseInt(obj.getString(values.get(5))));
                }

                 */
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return times;
    }
}



    /*
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(arrayName +"_size", false);
        for(int i=0;i<array.size();i++)
        editor.putBoolean(arrayName + "_" + i, array.get(i));
        editor.apply();
       */