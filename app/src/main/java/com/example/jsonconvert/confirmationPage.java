package com.example.jsonconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class confirmationPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent p = new Intent(getApplicationContext() , HomeActivity.class);
                startActivity(p);
                finish();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent p = new Intent(getApplicationContext() , HomeActivity.class);
        startActivity(p);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        Intent p = new Intent(getApplicationContext() , HomeActivity.class);
        startActivity(p);
        finish();
        return super.onKeyDown(keyCode, event);
    }
}