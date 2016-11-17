package com.example.smartmirror;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import twitter4j.Twitter;
import twitter4j.Status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static com.example.smartmirror.Constants.CALLBACK_URL;
import static com.example.smartmirror.Constants.CONSUMER_KEY;
import static com.example.smartmirror.Constants.CONSUMER_SECRET;
import static com.example.smartmirror.Constants.IEXTRA_OAUTH_VERIFIER;
import static com.example.smartmirror.Constants.PREF_KEY_USER;

public class Twitter_fncts extends AppCompatActivity {

    private final String TAG = "SmartMirrorTwitter";
    private static SharedPreferences mSharedPreferences;
    private static RequestToken _requestToken=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_fncts);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mSharedPreferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"ONSTART METHOD");
        if(isConnected()){
            Log.d(TAG, "GOOD");
        }
        else if(_requestToken !=null){
            Log.d(TAG, "Callback");

            handleCallback();
        }
        else
        {
            Log.d(TAG, "Needs Authorize");
            authorizeApp();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "RESUME");
        if(!isConnected()){
            handleCallback();

        }
        else{
            doProcessing();
        }
    }

    private boolean isConnected()
    {
        return mSharedPreferences.getString(Constants.PREF_KEY_TOKEN, null) != null;
    }


    /*
	 * This function helps in authorization
	 */
    private void authorizeApp() {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(Constants.CONSUMER_KEY);
        configurationBuilder.setOAuthConsumerSecret(Constants.CONSUMER_SECRET);
        Configuration configuration = configurationBuilder.build();
        Twitter t = new TwitterFactory(configuration).getInstance();
        if(t==null)
        {
            Log.d(TAG, "NO TWITTER");
        }
        try {
            _requestToken = t.getOAuthRequestToken(CALLBACK_URL);
            Log.d(TAG, "Request token: " + _requestToken.toString());
            Twitter_fncts.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse(_requestToken.getAuthenticationURL())));

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

//
    /*
	 * This function handle callback while authorizing with Twitter
	 */
    protected void handleCallback() {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(Constants.CONSUMER_KEY);
        configurationBuilder.setOAuthConsumerSecret(Constants.CONSUMER_SECRET);
        Configuration configuration = configurationBuilder.build();
        Twitter t = new TwitterFactory(configuration).getInstance();

        Uri uri = getIntent().getData();
        if(uri!=null) {
            Log.d(TAG, "URI TOSTRING: " + uri.toString() + " CallBack URL: " + CALLBACK_URL);
        }
        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
            String verifier = uri.getQueryParameter(IEXTRA_OAUTH_VERIFIER);
            if(verifier!=null)
            {
                Log.d(TAG, "VERIFIER: "+verifier);
            }
            if(_requestToken != null)
            {
                Log.d(TAG, "REQUEST TOKEN: " + _requestToken.toString());
            }
            if(t == null)
            {
                Log.d(TAG, "NO Twitter obj");
            }
            try {
                AccessToken accessToken = t.getOAuthAccessToken(_requestToken, verifier);
                SharedPreferences.Editor e = mSharedPreferences.edit();
                e.putString(Constants.PREF_KEY_TOKEN, accessToken.getToken());
                e.putString(Constants.PREF_KEY_SECRET, accessToken.getTokenSecret());
                e.putString(Constants.PREF_KEY_USER, accessToken.getScreenName());
                e.commit();

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private void doProcessing()
    {
        Log.d(TAG, "Entering do Processing");
        HttpPost httppost = new HttpPost("http://jarvis.cse.buffalo.edu/mine/twitter.php");
        HttpResponse response;
        HttpClient httpclient = new DefaultHttpClient();

        try
        {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthAccessToken(mSharedPreferences.getString(Constants.PREF_KEY_TOKEN, "null"))
                    .setOAuthAccessTokenSecret(mSharedPreferences.getString(Constants.PREF_KEY_SECRET, "null"));
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            twitter.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);



            List<Status> statuses = twitter.getHomeTimeline();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            for(int i =0; i< 10; ++i) {
                Log.d(TAG, "post num "+ i);
                try {
                    //fusername ffavcount frtcount ftext and ftime
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

                    nameValuePairs.add(new BasicNameValuePair("fusername", statuses.get(i).getUser().getName()));
                    nameValuePairs.add(new BasicNameValuePair("fscrnname", statuses.get(i).getUser().getScreenName()));
                    nameValuePairs.add(new BasicNameValuePair("ffavcount", String.valueOf(statuses.get(i).getFavoriteCount())));
                    nameValuePairs.add(new BasicNameValuePair("frtcount", String.valueOf(statuses.get(i).getRetweetCount())));
                    nameValuePairs.add(new BasicNameValuePair("ftext", statuses.get(i).getText()));
                    nameValuePairs.add(new BasicNameValuePair("ftime", df.format(statuses.get(i).getCreatedAt())));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    Log.d(TAG, "Posting");
                    response = httpclient.execute(httppost);
                    Log.d(TAG, "Posting\n" + nameValuePairs.get(1) + "\n" +nameValuePairs.get(2)+"\n"+nameValuePairs.get(3)+"\n"+nameValuePairs.get(4)+"\n"+nameValuePairs.get(5));

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
