package com.example.smartmirror;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
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

/**
 * Created by Grant on 10/26/16.
 */
public class login {
    //Context context;


    public int start(EditText username, EditText password, Context context, TextView pleaseSignIn){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        HttpResponse response;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(MainActivity.serverName + "pass.php");

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

            nameValuePairs.add(new BasicNameValuePair("fname", user));
            nameValuePairs.add(new BasicNameValuePair("fpass", pass));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            return 1;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return 1;
        }

        int status = response.getStatusLine().getStatusCode();

        if (status == 410){
            pleaseSignIn.setText("Incorrect UserName or Password");
            return 1;
        }

        return 0;
    }

}
