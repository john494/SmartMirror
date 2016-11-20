package com.example.smartmirror;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import static com.example.smartmirror.R.id.button;

/**
 * Created by Grant on 11/20/16.
 */

public class CalendarService extends Service {

    static Context tempMain;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // Code to execute when the service is first created

    }

    @Override
    public void onDestroy() {
        //want to run forever
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }


    public void startServ(Context main, Intent intent){
        tempMain = main;
        intent = new Intent(tempMain, CalendarService.class);
        tempMain.startService(intent);
    }


}
