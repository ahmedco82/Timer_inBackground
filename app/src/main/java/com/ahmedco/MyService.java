package com.ahmedco;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
////https://stackanswers.net/questions/intent-service-not-working-in-doze-mode
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return Service.START_STICKY;
    }
}
