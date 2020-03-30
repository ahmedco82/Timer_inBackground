package com.ahmedco;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class MyStartServiceReceiver extends BroadcastReceiver {

    MediaPlayer selectedSound;

    @Override
    public void onReceive(Context context, Intent intent) {
     // TODO Auto-generated method stub
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Log.i("getAction()", "ACTION_BOOT_COMPLETED");
        }
        Log.i("onReceive0", "inside timer triggered");
        // Toast.makeText(context, "It's worked", Toast.LENGTH_LONG).show();
        Toast.makeText(context, "اذكر الله", Toast.LENGTH_LONG).show();
        selectedSound = (MediaPlayer) MediaPlayer.create(context, R.raw.a6);
        selectedSound.start();
    }

}