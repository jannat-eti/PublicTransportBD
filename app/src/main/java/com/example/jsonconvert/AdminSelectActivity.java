package com.example.jsonconvert;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AdminSelectActivity extends AppCompatActivity {

    LinearLayout addBusLAYOUT, addStationLAYOUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select);

        init();
    }

    private void init() {

        addBusLAYOUT = (LinearLayout) findViewById(R.id.add_bus_LAYOUT);
        addStationLAYOUT = (LinearLayout) findViewById(R.id.add_station_LAYOUT);

        addBusLAYOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminSelectActivity.this, AdminAddBusActivity.class));
            }
        });


        addStationLAYOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminSelectActivity.this, AdminAddStationActivity.class));
            }
        });
    }
}
