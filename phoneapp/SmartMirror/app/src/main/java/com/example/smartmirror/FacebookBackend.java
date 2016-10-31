package com.example.smartmirror;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacebookBackend extends AppCompatActivity {

    private CallbackManager callbackManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_facebook);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_posts");
        getLoginDetails(loginButton);

        if(isLoggedIn()){
            GraphRequest request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/tagged",                 //me/tagged shows only posts user was tagged in. me/feed shows all posts published on profile
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject jarray = response.getJSONObject();
                            List<NameValuePair> message = new ArrayList<NameValuePair>();
                            List<String> story = new ArrayList<String>();
                            List<NameValuePair> datetime = new ArrayList<NameValuePair>();
                            JSONArray array = null;
                            try {
                                array = jarray.getJSONArray("data");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for(int i = 0 ; i < array.length() ; i++){
                                try {
                                    message.add(new BasicNameValuePair("fpost", array.getJSONObject(i).getString("message")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    story.add(array.getJSONObject(i).getString("story"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    story.add(i,"");
                                }
                                try {
                                    datetime.add(new BasicNameValuePair("fdate",array.getJSONObject(i).getString("created_time")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            HttpClient httpclient = new DefaultHttpClient();
                            HttpPost httppost = new HttpPost("http://jarvis.cse.buffalo.edu/mine/facebook.php");
                            try {
                                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(message.size()*2);
                                for(int i = 0; i < message.size(); i++){

                                    nameValuePairs.add(message.get(i));
                                    nameValuePairs.add(new BasicNameValuePair("fmessage", story.get(i)));

                                    nameValuePairs.add(datetime.get(i));

                                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                                    httpclient.execute(httppost);

                                }
                                Log.i("logged", nameValuePairs.toString());

                            } catch (ClientProtocolException e) {
                                // TODO Auto-generated catch block
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                            }
                        }
                    });

            request.executeAsync();
        }
        else{
            Log.i("logged", "out");
        }

    }
    protected void getLoginDetails(LoginButton login_button){
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/tagged",                 //me/tagged shows only posts user was tagged in. me/feed shows all posts published on profile
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                Log.i("logged", "Success!");
                            }
                        });

                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("logged", "Cancelled");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("logged", "Error");
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
