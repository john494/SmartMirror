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
            Log.i("logged", "in");
            //Log.i("logged", AccessToken.getCurrentAccessToken().getToken());
            GraphRequest request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/tagged",                 //me/tagged shows only posts user was tagged in. me/feed shows all posts published on profile
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            Log.i("logged", response.toString());
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
                //Log.i("logged", "Please");
                //Log.i("logged", loginResult.getAccessToken().getToken());
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/tagged",                 //me/tagged shows only posts user was tagged in. me/feed shows all posts published on profile
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                Log.i("logged", response.toString());
                            }
                        });

                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("logged", "Please cancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("logged", "Please error");
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
