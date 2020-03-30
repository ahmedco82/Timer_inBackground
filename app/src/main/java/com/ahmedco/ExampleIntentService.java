package com.ahmedco;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;



public class ExampleIntentService extends IntentService {
    private static final String TAG = "ExampleIntentService";
    private PowerManager.WakeLock wakeLock;

    public ExampleIntentService() {
        super("ExampleIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate(){
        super.onCreate();
      }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
     Log.i("TAG0:", "onHandleIntent");
     /*
      String input = intent.getStringExtra("inputExtra");
       for(int i = 0; i < 10; i++) {
         Log.i("TAG1", input + " - "+i);
           SystemClock.sleep(1000);
        }

       */
       /*
        AlarmManager service = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, MyStartServiceReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
         service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000 * 1, pending);
        */

}

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
      //  wakeLock.release();
       // Log.d(TAG, "Wakelock released");
    }
}


    /*
        Log.d(TAG, "onCreate");
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ExampleApp:Wakelock");
        wakeLock.acquire();
        Log.d(TAG, "Wakelock acquired");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
             .setContentTitle("XXXXXXXX")
                .setContentText("Running...")
                  .setSmallIcon(R.drawable.ic_end_time_black_24dp)
                   .build();
               startForeground(1, notification);
          }

         */