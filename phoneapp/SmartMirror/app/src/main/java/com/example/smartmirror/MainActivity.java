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


public class MainActivity extends AppCompatActivity {

    Button sumbit;
    EditText username;
    EditText password;
    TextView pleaseSignIn;
    final String TAG = "SmartMirror";


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "App started on build version " + Build.VERSION.SDK_INT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        //setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Button button1 = (Button)findViewById(R.id.button);
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        sumbit = button1;
        username = editText;
        password = editText2;

        TextView textView = (TextView) findViewById(R.id.textView);
        pleaseSignIn = textView;

    }

    public void clicked(View v){
        Log.d(TAG, "Button %s has been clicked");
        login login = new login();
        int test1 = login.start(username,password,this,pleaseSignIn);
        if(test1 != 0){ return; }


        int test = post();
        if (test == 0){
            setContentView(R.layout.activity_main);
        }
        else { return; }

        //setContentView(R.layout.activity_main);
        //return 0;
    }

    private int post()
    {
        final String server = "http://jarvis.cse.buffalo.edu/mine/mypage.php";
        Log.d(TAG, "Post to server");
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(server);
        try {
            Log.i(TAG, "Attempting to post to: "+server);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

            nameValuePairs.add(new BasicNameValuePair("fname", "John"));
            nameValuePairs.add(new BasicNameValuePair("fphone", "no #"));
            nameValuePairs.add(new BasicNameValuePair("femail", "mynameisJohn@buffalo.edu"));
            nameValuePairs.add(new BasicNameValuePair("fcomment", "It worked!!!! :)"));
            Log.i(TAG, "Items: " + nameValuePairs);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);

        } catch (ClientProtocolException e) {

            // TODO Auto-generated catch block
            return 1;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return 1;
        }
        return 0;
    }

    private void twitter()
    {
        Intent intent = new Intent(MainActivity.this, Twitter_fncts.class);
        MainActivity.this.startActivity(intent);
    }
}


