package com.example.smartmirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.io.IOException;

import java.util.List;

public class NewsHeadlines extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = "SmartMirrorNews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_headlines);

        Spinner spin = (Spinner) findViewById(R.id.news_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.news_arrays, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        Log.d(TAG, parent.getItemAtPosition(pos).toString());
        HttpPost httppost = new HttpPost("http://jarvis.cse.buffalo.edu/mine/news.php");
        HttpResponse response;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            //fusername ffavcount frtcount ftext and ftime
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

            nameValuePairs.add(new BasicNameValuePair("fnews", parent.getItemAtPosition(pos).toString()));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            Log.e(TAG, "Protocol exception: "+ e.getMessage());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e(TAG, "IO exception: " + e.getMessage());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
