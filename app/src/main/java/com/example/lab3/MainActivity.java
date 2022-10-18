package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button next;
    EditText name;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.textName);
        next = (Button) findViewById(R.id.button);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("name", "");
        name.setText(s1);

        next.setOnClickListener(click -> {
            Intent myIntent = new Intent(MainActivity.this, NameActivity.class);
            myIntent.putExtra("name", name.getText().toString());

            startActivityForResult(myIntent, 2);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        sharedpreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedpreferences.edit();
        myEdit.putString("name", name.getText().toString());
        myEdit.apply();
        String user = sharedpreferences.getString("name", null);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    //@Override
    //protected void onResume() {
    //    super.onResume();
    //    SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
    //    String s1 = sh.getString("name", "");
    //    name.setText(s1);
    //}
}