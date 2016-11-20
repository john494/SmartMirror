package com.example.smartmirror;

import android.content.SharedPreferences;
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
import static com.example.smartmirror.Constants.CONSUMER_SECRET;
import static com.example.smartmirror.Constants.IEXTRA_OAUTH_VERIFIER;

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
        if(isConnected()){ //Already authorized and connected
            Log.d(TAG, "Connected");
//            doProcessing();
        }
        else if(_requestToken !=null){ //Authorized but not connected
            handleCallback();
        }
        else //Initial time needs to authorize
        {
            authorizeApp();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(!isConnected()){
            handleCallback();
        }
        else{
            startService();
//            doProcessing();
        }
    }

    public static boolean isConnected()
    {
        return mSharedPreferences.getString(Constants.PREF_KEY_TOKEN, null) != null;
    }

    public void Twitter_logout(View v)
    {
        Log.d(TAG, "LOGGING OUT");
        SharedPreferences.Editor e = mSharedPreferences.edit();
        e.remove(Constants.PREF_KEY_TOKEN);
        e.remove(Constants.PREF_KEY_SECRET);
        e.remove(Constants.PREF_KEY_USER);
        e.commit();

        stopService();
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

        try {
            _requestToken = t.getOAuthRequestToken(CALLBACK_URL);
            Twitter_fncts.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse(_requestToken.getAuthenticationURL())));

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }


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
        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
            String verifier = uri.getQueryParameter(IEXTRA_OAUTH_VERIFIER);

            try {
                AccessToken accessToken = t.getOAuthAccessToken(_requestToken, verifier);
                SharedPreferences.Editor e = mSharedPreferences.edit();
                e.putString(Constants.PREF_KEY_TOKEN, accessToken.getToken());
                e.putString(Constants.PREF_KEY_SECRET, accessToken.getTokenSecret());
                e.putString(Constants.PREF_KEY_USER, accessToken.getScreenName());
                e.putString("ConsumerKey", Constants.CONSUMER_KEY);
                e.putString("ConsumerSecret", CONSUMER_SECRET);
                e.commit();
                startService();

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public static void GetTweets(View v)
    {
        doProcessing(mSharedPreferences.getString("oauth_token", "null"),mSharedPreferences.getString("oauth_token_secret", "null"));
    }
    public static void doProcessing(String aTok, String aSec)
    {
        final String tag = "SmartMirrorTwitter";
        Log.d(tag, "Entering do Processing");
        HttpPost httppost = new HttpPost("http://jarvis.cse.buffalo.edu/mine/twitter.php");
        HttpResponse response;
        HttpClient httpclient = new DefaultHttpClient();

        try
        {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthAccessToken(aTok)
                    .setOAuthAccessTokenSecret(aSec);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            twitter.setOAuthConsumer("re9qydT5OuyzZN606kKamGDrP", "8evqR5CvNRxc7LnA4SsHjKymFblvIWOVVGGlCcJF6PgRBD9n9P");

            List<Status> statuses = twitter.getHomeTimeline();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add((new BasicNameValuePair("ffirst", "yes")));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);

            for(int i =0; i< 10; ++i) {
                try {
                    //fusername ffavcount frtcount ftext and ftime
                    nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("ffirst", ""));
                    nameValuePairs.add(new BasicNameValuePair("fusername", statuses.get(i).getUser().getName()));
                    nameValuePairs.add(new BasicNameValuePair("fscrnname", statuses.get(i).getUser().getScreenName()));
                    nameValuePairs.add(new BasicNameValuePair("ffavcount", String.valueOf(statuses.get(i).getFavoriteCount())));
                    nameValuePairs.add(new BasicNameValuePair("frtcount", String.valueOf(statuses.get(i).getRetweetCount())));
                    nameValuePairs.add(new BasicNameValuePair("ftext", statuses.get(i).getText()));
                    nameValuePairs.add(new BasicNameValuePair("ftime", df.format(statuses.get(i).getCreatedAt())));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    response = httpclient.execute(httppost);
                    Log.d(tag, "post num "+ i);
                    Log.d(tag, "Posting\n" + nameValuePairs.get(1) + "\n" +nameValuePairs.get(2)+"\n"+nameValuePairs.get(3)+"\n"+nameValuePairs.get(4)+"\n"+nameValuePairs.get(5));

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    Log.e(tag, "Protocol exception: "+ e.getMessage());

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.e(tag, "IO exception: " + e.getMessage());
                }
            }
        }
        catch (Exception e)
        {
            Log.e(tag, "Status exception " + e.getMessage());
        }
    }

    public void startService()
    {
        startService(new Intent(getBaseContext(), TwitterService.class));
    }

    public void stopService()
    {
        stopService(new Intent(getBaseContext(), TwitterService.class));
    }
}
