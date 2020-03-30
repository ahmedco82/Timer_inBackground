package com.ahmedco;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;

public class SharPref {


    static final String mapKey = "map";
    private Context context;

    public SharPref(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "appname_prefs";

    public void saveOject(Times objTimes){
        SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("MyObject", objectToString(objTimes));
        prefsEditor.commit();
    }

    public Times getfromOject(Times obj){
         SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
         String value= mPrefs.getString("MyObject", "");
         obj = stringToObjectS(value);
        return obj;
    }


    public <T extends Serializable>T stringToObjectS(String string) {
     byte[] bytes = Base64.decode(string, 0);
      T object = null;
       try {
         ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
          object = (T) objectInputStream.readObject();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return object;
    }
    public  String objectToString(Times object){
        String encoded = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            encoded = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoded;
    }
}
