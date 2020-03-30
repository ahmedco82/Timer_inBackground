package com.ahmedco;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class ExampleJobIntentService extends JobIntentService {


     MediaPlayer selectedSound;
     private static final String TAG = "Excute_0:";

    // sound0
     static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ExampleJobIntentService.class, 123, work);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate");
    }


    @Override
    public void setInterruptIfStopped(boolean interruptIfStopped) {
       super.setInterruptIfStopped(interruptIfStopped);
        Log.d(TAG, "setInterruptIfStopped");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
     Log.i(TAG, "onHandleWork");
      String input = intent.getStringExtra("inputExtra");

       boolean loop=true;
       //for(int i = 0; i <1990; i++) {
        while (loop){
           Log.d(TAG, input + " - Don" );
           if(!Utlis.getServiceOnOff(this)){
             Log.d(TAG, "is TrueXXXXXXXXXXX");
              // stopSelf();
               loop= false;
             break;
          }
        //  if(isStopped())return;
       SystemClock.sleep(1000);
     }
        /*
         while(true){
          Log.i(TAG, "onHandleWork00+-+:");
           if(isStopped())return;
            SystemClock.sleep(1000);
         }
      */
    }
    @Override
     public void onDestroy(){
      super.onDestroy();
       Log.d(TAG, "onDestroy");
    }


    @Override
    public boolean onStopCurrentWork(){
        Log.d(TAG, "onStopCurrentWork");
       return super.onStopCurrentWork();
    }


}