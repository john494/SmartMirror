package com.example.smartmirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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



        Log.i("EditText", stock1);
        Log.i("EditText", stock2);
        Log.i("EditText", stock3);
        Log.i("EditText", stock4);
    }
}
