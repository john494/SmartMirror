package com.example.smartmirror;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
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
        getLoginDetails(loginButton);
    }
    protected void getLoginDetails(LoginButton login_button){
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("work", "Please");
                Log.i("token", loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                Log.i("work", "Please cancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("work", "Please error");
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
