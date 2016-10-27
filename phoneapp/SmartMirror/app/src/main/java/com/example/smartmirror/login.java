package com.example.smartmirror;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grant on 10/26/16.
 */
public class login {
    //Context context;


    public int start(EditText username, EditText password, Context context, TextView pleaseSignIn){

        /*
        EditText editText =  findViewById(R.id.name);
        String message = editText.getText().toString();*/
        String user = username.getText().toString();
        String pass = password.getText().toString();
        HttpResponse response;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://jarvis.cse.buffalo.edu/mine/pass.php");

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

            nameValuePairs.add(new BasicNameValuePair("fname", user));
            nameValuePairs.add(new BasicNameValuePair("fpass", pass));
            //nameValuePairs.add(new BasicNameValuePair("femail", "mynameisgrant@buffalo.edu"));
            //nameValuePairs.add(new BasicNameValuePair("fcomment", "hewwo"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            return 1;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return 1;
        }

        //Log.wtf("", String.valueOf(response));
        //System.out.println(String.valueOf(response));

        int status = response.getStatusLine().getStatusCode();

        /*
        File file = new File("test");
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(file.getName(), Context.MODE_APPEND);
            fos.write(String.valueOf(status).getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        if (status == 410){
            pleaseSignIn.setText("Incorrect UserName or Password");
            return 1;
        }


        /*if(response.equals(404)){
            return 1;
        }*/

        //TextView textView = (TextView) findViewById(R.id.textView2);

        //helloworld.setText("yoyoyo");

        //System.out.print(response);

        return 0;

        //return 0;
    }

}
