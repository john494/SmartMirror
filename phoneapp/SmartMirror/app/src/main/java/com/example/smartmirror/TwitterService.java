package com.example.smartmirror;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TwitterService extends Service {
    private String tok="";
    private String sec="";
    public TwitterService() {
        Log.d("TwitterService", "Creating service");


        new Thread(new Runnable(){
            public void run() {
                // TODO Auto-generated method stub
                while(true)
                {
//                    if(Twitter_fncts.isConnected())
//                    {
                        if(tok !=null && sec !=null &&!tok.equals("") && !sec.equals("")){
//                        Log.d("SERVICE",getSharedPreferences("twitter_oauth", MODE_PRIVATE).getString("oauth_token", "null"));
//                        Log.d("SERVICE", getSharedPreferences("twitter_oauth", MODE_PRIVATE).getString("oauth_token_secret", "null"));
                            Twitter_fncts.doProcessing(tok,sec);
                        }
// }
                    try {
                        Thread.sleep(30000);

                    }
                    catch (Exception e)
                    {

                    }

                    //REST OF CODE HERE//
                }

            }
        }).start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        Log.d("TwitterService", "Starting");
        tok = getSharedPreferences("twitter_oauth", MODE_PRIVATE).getString("oauth_token", "null");
        sec = getSharedPreferences("twitter_oauth", MODE_PRIVATE).getString("oauth_token_secret", "null");
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        Log.d("SERVICE", "Stopping ");
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
