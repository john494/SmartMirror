package com.example.smartmirror;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

public class stocks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);
    }

    public void getStocks(View v){
        EditText text1;
        EditText text2;
        EditText text3;
        EditText text4;
        String stock1 = "";
        String stock2 = "";
        String stock3 = "";
        String stock4 = "";
        text1   = (EditText)findViewById(R.id.stock1);
        stock1 = text1.getText().toString();
        text2   = (EditText)findViewById(R.id.stock2);
        stock2 = text2.getText().toString();
        text3   = (EditText)findViewById(R.id.stock3);
        stock3 = text3.getText().toString();
        text4   = (EditText)findViewById(R.id.stock4);
        stock4 = text4.getText().toString();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);                                      //kill the keyboard
        Snackbar mySnackbar = Snackbar.make(v, "Stock ticker symbols accepted!", Snackbar.LENGTH_LONG);
        mySnackbar.show();
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");

        final String server = "http://jarvis.cse.buffalo.edu/mine/stocks.php";
        Log.d("logged", "Post to server");
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(server);
        try {
            Log.i("logged", "Attempting to post to: "+server);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

            nameValuePairs.add(new BasicNameValuePair("fstock1", stock1));
            nameValuePairs.add(new BasicNameValuePair("fstock2", stock2));
            nameValuePairs.add(new BasicNameValuePair("fstock3", stock3));
            nameValuePairs.add(new BasicNameValuePair("fstock4", stock4));
            Log.i("logged", "Items: " + nameValuePairs);

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);

        } catch (ClientProtocolException e) {

            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }
}
