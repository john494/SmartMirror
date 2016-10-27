package com.example.smartmirror;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.http.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
        Intent intent = new Intent(MainActivity.this, FacebookActivity.class);
        MainActivity.this.startActivity(intent);
    }



}


