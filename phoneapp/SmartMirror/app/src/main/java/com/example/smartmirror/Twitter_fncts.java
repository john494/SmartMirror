package com.example.smartmirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import twitter4j.Twitter;
import twitter4j.Status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.io.IOException;



import static java.lang.Thread.sleep;

public class Twitter_fncts extends AppCompatActivity {

    private final String _consumerKey = "re9qydT5OuyzZN606kKamGDrP";
    private final String _consumerSec = "8evqR5CvNRxc7LnA4SsHjKymFblvIWOVVGGlCcJF6PgRBD9n9P";
    private final String _authorizeURL = "https://api.twitter.com/oauth/authorize";
    private final String _tokenURL = "https://api.twitter.com/oauth/access_token";
    private final String _requestURL = "https://api.twitter.com/oauth/request_token";
    private final String TAG = "SmartMirrorTwitter";
    private RequestToken requestToken = null;
    private ConfigurationBuilder _cb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_fncts);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        _cb = new ConfigurationBuilder();
        _cb.setDebugEnabled(true)
                .setOAuthConsumerKey(_consumerKey)
                .setOAuthConsumerSecret(_consumerSec);


        final Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(_consumerKey, _consumerSec);
        Log.d(TAG, "_consumerKey: " +_consumerKey +"_consumerSec: " + _consumerSec);

        try
        {
            requestToken = twitter.getOAuthRequestToken();
        }
        catch (Exception e)
        {
            Log.e(TAG, "Request token exception "+e.getMessage());
        }
        if(requestToken != null)
        {
            Log.d(TAG, "Request token: " + requestToken.toString());
            Twitter_fncts.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse(requestToken.getAuthenticationURL())));
            setContentView(R.layout.activity_twitter_fncts);
            Button enter = (Button) findViewById(R.id.Submit);
            enter.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // Call login twitter function
                    Log.d(TAG, "Button Clicked");
                    getToken(twitter,requestToken);
                }
            });

        }
        else
        {
            Log.e(TAG, "Request token Null");
        }

    }

    public void getToken(Twitter twitter, RequestToken requestToken){
        AccessToken accessToken = null;

        try {
             Log.d(TAG, "Pin: " + ((EditText) findViewById(R.id.token)).getText().toString());
             if (((EditText) findViewById(R.id.token)).getText().toString().length() > 0) {
                 accessToken = twitter.getOAuthAccessToken(requestToken, ((EditText) findViewById(R.id.token)).getText().toString());
                 _cb.setOAuthAccessToken(accessToken.getToken());
                 _cb.setOAuthAccessTokenSecret(accessToken.getTokenSecret());
             } else {
                 //accessToken = twitter.getOAuthAccessToken();
             }
        } catch (Exception e) {
            Log.e(TAG, "AccessToken exception: " + e.getMessage());
        }
        if (accessToken != null && accessToken.getScreenName() != null) {
            Log.d(TAG, "AccessToken: " + accessToken.toString());
            setContentView(R.layout.activity_main);
            doProcessing(twitter);
        }
        else {
            findViewById(R.id.invalid_pw).setVisibility(View.VISIBLE);
            Log.e(TAG, "No token");
        }
    }

    private void doProcessing(Twitter twitter)
    {
        Log.d(TAG, "Entering do Processing");
        HttpPost httppost = new HttpPost("http://jarvis.cse.buffalo.edu/mine/twitter.php");
        HttpResponse response;
        HttpClient httpclient = new DefaultHttpClient();

        try
        {
            List<Status> statuses = twitter.getHomeTimeline();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            for(int i =0; i< 10; ++i) {
                Log.d(TAG, "post num "+ i);
                try {
                    //fusername ffavcount frtcount ftext and ftime
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

                    nameValuePairs.add(new BasicNameValuePair("fusername", statuses.get(i).getUser().getName()));
                    nameValuePairs.add(new BasicNameValuePair("ffavcount", String.valueOf(statuses.get(i).getFavoriteCount())));
                    nameValuePairs.add(new BasicNameValuePair("frtcount", String.valueOf(statuses.get(i).getRetweetCount())));
                    nameValuePairs.add(new BasicNameValuePair("ftext", statuses.get(i).getText()));
                    nameValuePairs.add(new BasicNameValuePair("ftime", df.format(statuses.get(i).getCreatedAt())));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    Log.d(TAG, "Posting");
                    response = httpclient.execute(httppost);
                    Log.d(TAG, "Posting\n" + nameValuePairs.get(1) + "\n" +nameValuePairs.get(2)+"\n"+nameValuePairs.get(3)+"\n"+nameValuePairs.get(4));

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    Log.e(TAG, "Protocol exception: "+ e.getMessage());

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.e(TAG, "IO exception: " + e.getMessage());
                }
            }
//
        }
        catch (Exception e)
        {
            Log.e(TAG, "Status exception " + e.getMessage());
        }
        Log.d(TAG, "Done ");
    }
}
