package com.example.smartmirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class zipcode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zipcode);
    }

    public String getZip(View v){
        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.postal_code);
        String zip = mEdit.getText().toString().trim();
        Log.i("EditText", zip);
        return zip;

    }
}
