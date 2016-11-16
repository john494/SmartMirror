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

public class zipcode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zipcode);
    }

    public void getZip(View v){
        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.postal_code);
        String zip = mEdit.getText().toString();
        Log.i("EditText", zip);
        mEdit.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        Snackbar mySnackbar = Snackbar.make(v, "Zip code " + zip + " accepted!", Snackbar.LENGTH_LONG);
        mySnackbar.show();

    }
}
