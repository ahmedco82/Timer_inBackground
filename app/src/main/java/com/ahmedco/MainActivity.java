package com.ahmedco;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.JobIntentService;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ahmedco.MathWorker.KEY_RESULT;
import static com.ahmedco.MathWorker.KEY_X_ARG;
import static com.ahmedco.MathWorker.KEY_Y_ARG;
import static com.ahmedco.MathWorker.KEY_Z_ARG;


//https://stackoverflow.com/questions/59044532/need-to-have-one-background-task-to-run-for-every-minute-in-android

public class MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity";
    OneTimeWorkRequest oneTimeWorkRequest;
    UUID periodicWorkRequestId;

    WorkManager workManager;
    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = findViewById(R.id.edit_text_input);
    }


    public void enqueueWork(View v)  {
     enqueueWork2();
    }
    
    private void enqueueWork2() {
        Data myData = new Data.Builder()
                // We need to pass three integers: X, Y, and Z
                .putInt(KEY_X_ARG, 1)
                .putInt(KEY_Y_ARG, 3)
                .putInt(KEY_Z_ARG, 2)
                // ... and build the actual Data object:
                .build();

          // ...then create and enqueue a OneTimeWorkRequest that uses those arguments
        OneTimeWorkRequest mathWork = new OneTimeWorkRequest.Builder(MathWorker.class)
                .setInputData(myData)

                .build();
        WorkManager.getInstance(this).enqueue(mathWork);


        WorkManager.getInstance(MainActivity.this).getWorkInfoByIdLiveData(mathWork.getId()).observe(MainActivity.this, info -> {
           if (info != null && info.getState().isFinished()) {
            int myResult = info.getOutputData().getInt(KEY_RESULT, 0);
                // ... do something with the result ...
                        Log.d("toast0 ", "onChanged: "+myResult);
                }
           });
    }






/*
   private void enqueueWork1() {

        Data myData = new Data.Builder()
                .putString("NAME_KEY", "Mohammad")
                .putInt("AGE_KEY", 14)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyJobManager.class)
                .setInputData(myData)
                .setConstraints(constraints)
                .addTag("MY_WORK_MANAGER_TAG_ONE_TIME")
                .build();

        workManager = WorkManager.getInstance(MainActivity.this);
        workManager.enqueue(oneTimeWorkRequest);

        UUID oneTimeWorkRequestId = oneTimeWorkRequest.getId();
        workManager.cancelWorkById(oneTimeWorkRequestId);

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestId).observe(this, new Observer<WorkInfo>() {
          @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
              if(workInfo != null &&  workInfo.getState() ==WorkInfo.State.SUCCEEDED){
                 String result = workInfo.getOutputData().getString("RESULT");
                   Log.d(TAG, "onChanged: " + result);
                 }
               Log.d(TAG, "Non onChanged: ");
            }
         });
      }

 */



    public void enqueueWork0() {
        Utlis.setServiceOnOff(this,true);
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleJobIntentService.class);
        serviceIntent.putExtra("inputExtra", input);
        ExampleJobIntentService.enqueueWork(this, serviceIntent);
        this.finishAffinity();
    }

    private void func2() {
          ArrayList<Integer> mStringList= new ArrayList<Integer>(3);
         // mStringList.add(1);
        mStringList.add(1,1);
       mStringList.add(0,0);
       mStringList.add(2,2);
      // Log.i("PRINT_04","" +mStringList.size());
     for(int i=0; i<mStringList.size(); i++){
    Log.i("PRINT_04","" +mStringList.get(i));
   }
 }

private void func1(){
 Times times = new Times();
  times.setEveryTime(2);
    times.setStopTimer(0);
     Preferences.saveTimes(MainActivity.this,times);
      Log.i("PRINT_t1","" +Preferences.getTimes(MainActivity.this).getEveryTime());
       Log.i("PRINT_t2","" +Preferences.getTimes(MainActivity.this).getStopTimer());
    }
}






           /*
        if(Utlis.getServiceOnOff(this)){
            Utlis.setServiceOnOff(this,false);
            //this.stopService(new Intent(this, StartBackgroundService.class));
        }
        enqueueWork0();
      */
        /*
        Utlis.setServiceOnOff(this,true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            this.startForegroundService(new Intent(this, StartBackgroundService.class));
        }
        else{
            this.startService(new Intent(this, StartBackgroundService.class));
        }
       */
//   Log.i("PRINT2","" +MySharedPreferences.getData(MainActivity.this,"userObject"));
//  JSONObject obj = new JSONObject(MySharedPreferences.getData(MainActivity.this,"userObject"));
/*

if(Utlis.getServiceOnOff(this)){
            this.stopService(new Intent(this, StartBackgroundService.class));
            Utlis.setServiceOnOff(this,false);
  }

 */

// Preferences.setArrayPrefs("a",mStringList,MainActivity.this);
// Log.i("PRINT_tsetBool","" +Preferences.getArrayPrefs("a",this));
        /*
        ArrayList<Integer> mStringList= new ArrayList<Integer>();
        mStringList.add(1);
        mStringList.add(2);
        mStringList.add(3);
        mStringList.add(4);
        Preferences.setArrayPrefs("a",mStringList,MainActivity.this);
        Log.i("PRINT_tasd","" +Preferences.getArrayPrefs("a",this));
      */

// saveTimes
// Put object in SharedPreferences
//Preferences.getTimes()
// MySharedPreferences.saveData(MainActivity.this,"userObject",times);
//Times timesOne =  MySharedPreferences.getData(MainActivity.this,"userObject");
// get data from SharedPreferences;
//Log.i("PRINT_t1","" +timesOne.getStopTimer());
//Log.i("PRINT_t1","" +timesOne.getEveryTime());

    /*
    public void enqueueWork(View v) {
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleJobIntentService.class);
        serviceIntent.putExtra("inputExtra", input);
        ExampleJobIntentService.enqueueWork(this, serviceIntent);
        this.finishAffinity();
    }

     */

        /*
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        Iterator<ActivityManager.RunningAppProcessInfo> iter = runningAppProcesses.iterator();
        while(iter.hasNext()){
            ActivityManager.RunningAppProcessInfo next = iter.next();
            String pricessName=getPackageName()+":service";
            if(next.processName.equals(pricessName)){
               // android.os.Process.killProcess(android.os.Process.myPid());
                android.os.Process.killProcess(next.pid);
                break;
            }
        }
     */


/*
  private void test(){
     Intent intent = new Intent(this, MyStartServiceReceiver.class);
      PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
       // InexactRepeating allows Android to optimize the energy
         // consumption
          // Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
         // PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
         AlarmManager  alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        // alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (1 * 1000), pendingIntent);
        // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), 1 * 60 * 1000, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), 1 * 60 * 1000, pendingIntent);
        this.finishAffinity();
    }
 */












    //   SharedPreferencesHandler handler = new SharedPreferencesHandler((App)App.getAppContext());
    //SharedPreferencesHandler handler = new SharedPreferencesHandler(App.getInstance());

    //   handler.getInt("anIntegerVariableForExample", 1);
    // handler.getInt("anIntegerVariableForExample", 1);



/*

    private void setupAlarm(int seconds) {
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent i = new Intent(getBaseContext(), AlarmManager.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar t = Calendar.getInstance();
        t.setTimeInMillis(System.currentTimeMillis());
        int interval = seconds*1000;
        am.setRepeating(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), interval, pendingIntent);
      //  MainActivity.this.finish();
       // MainActivity.this.finish();
    }


    public static class AlarmReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Log.i("onReceive0", "Receiver0--{}");
           // recreated(context);
            startService0(context);
        }
    }
    public static void startService0(Context context){
        context.startService(new Intent(context, MyService.class));
    }

    public void startService(View view){

      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        // Intent mServiceIntent = new Intent(this, StartBackgroundService.class);
            //this.startForegroundService(mServiceIntent);
           //this.startForegroundService(new Intent(this, StartBackgroundService.class));
          // this.startForegroundService(new Intent(this, ExampleIntentService.class));
        }else {
           // Intent mServiceIntent = new Intent(this, StartBackgroundService.class);
           // this.startService(mServiceIntent);
          // this.startService(new Intent(this, ExampleIntentService.class));
        }
    }
}

*/

        /*
        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.AM_PM) == Calendar.AM){
            // AM
            //System.out.println(""+now.get(Calendar.HOUR)+":AM");
            Log.i("traceTime",""+now.get(Calendar.HOUR)+":AM");
        }else{
            // PM
           // System.out.println(""+now.get(Calendar.HOUR)+":PM");
            Log.i("traceTime",""+now.get(Calendar.HOUR)+":PM");
        }
    }
    */