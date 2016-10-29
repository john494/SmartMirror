package com.example.smartmirror;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://jarvis.cse.buffalo.edu/mine/mypage.php");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

            nameValuePairs.add(new BasicNameValuePair("fname", "TEST"));
            nameValuePairs.add(new BasicNameValuePair("fphone", "(716) 456-4567"));
            nameValuePairs.add(new BasicNameValuePair("femail", "nope@buffalo.edu"));
            nameValuePairs.add(new BasicNameValuePair("fcomment", "It worked!!!! Fuck yeah! :)"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        Facebook();

    }

    public void Facebook(){
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        Intent intent = new Intent(MainActivity.this, FacebookBackend.class);
        MainActivity.this.startActivity(intent);
    }



}


