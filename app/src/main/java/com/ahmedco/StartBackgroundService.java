package com.ahmedco;


import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.Calendar;

public class StartBackgroundService extends IntentService {

    public StartBackgroundService() {
     super("StartBackgroundService");
      // setIntentRedelivery(true);
       // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           // startForeground(this);
       }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
      // TODO Auto-generated method stub
       Log.i("onHandleIntent:--","Don");
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
            //    mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                Intent intent=new Intent();
                getApplicationContext().sendBroadcast(intent);
            }
        }.start();
        /*
        AlarmManager service = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, MyStartServiceReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000 * 1, pending);
         */
    }
}