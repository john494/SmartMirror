package com.example.smartmirror;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

public class zipcode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zipcode);
    }

    public void getZip(View v){
        EditText mEdit;
        String zip = "";
        mEdit   = (EditText)findViewById(R.id.postal_code);
        zip = mEdit.getText().toString();
        Log.i("EditText", zip);
        mEdit.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);                                      //kill the keyboard
        if(zip.length() != 5){
            Snackbar mySnackbar = Snackbar.make(v, "Zip code " + zip + " is not valid", Snackbar.LENGTH_LONG);
            mySnackbar.show();

        }
        else {
            Snackbar mySnackbar = Snackbar.make(v, "Zip code " + zip + " accepted!", Snackbar.LENGTH_LONG);
            mySnackbar.show();
            final String server = "http://jarvis.cse.buffalo.edu/mine/zipcode.php";
            Log.d("logged", "Post to server");
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(server);
            try {
                Log.i("logged", "Attempting to post to: "+server);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                nameValuePairs.add(new BasicNameValuePair("fzip", zip));
                Log.i("logged", "Items: " + nameValuePairs);

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                httpclient.execute(httppost);

            } catch (ClientProtocolException e) {

                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }




        //Snackbar mySnackbar = Snackbar.make(v, "Zip code " + zip + " accepted!", Snackbar.LENGTH_LONG);
        //mySnackbar.show();                                                                                  //show a message



    }
}
