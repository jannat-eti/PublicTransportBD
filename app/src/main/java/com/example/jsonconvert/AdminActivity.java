package com.example.jsonconvert;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jsonconvert.dialog.ViewDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {

    EditText stationNameET, orderET, distanceET,  busNameET;
    Button insertBTN;
    TextView addBusTV, busListET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        init();
    }

    private void init() {

        stationNameET = (EditText) findViewById(R.id.ET_station_name);
        orderET = (EditText) findViewById(R.id.ET_station_order);
        distanceET = (EditText) findViewById(R.id.ET_station_distance);
        busListET = (TextView) findViewById(R.id.ET_station_bus_list);
        busNameET = (EditText) findViewById(R.id.ET_bus_name);
        insertBTN = (Button) findViewById(R.id.insert_BTN);
        addBusTV = (TextView) findViewById(R.id.TV_add_bus);

        insertBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stationName = stationNameET.getText().toString();
                String stationOrder = orderET.getText().toString();
                String distance = distanceET.getText().toString();
                String busList = busListET.getText().toString();

                Stopage station = new Stopage();
                station.setId(0);
                station.setStationName(stationName);
                station.setBusList(busList);
                station.setOrder(Integer.parseInt(stationOrder));
                station.setDistanceFromUttara(Float.parseFloat(distance));

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Station/All");

              //  StoppageDbHelper stoppageDbHelper = new StoppageDbHelper(AdminActivity.this);
              //  stoppageDbHelper.insertData(station);

                reference.child(stationName).setValue(station);

                Toast.makeText(AdminActivity.this, "Data added successfully.", Toast.LENGTH_SHORT).show();

                stationNameET.setText("");
                orderET.setText("");
                distanceET.setText("");
                busListET.setText("");

            }
        });


        addBusTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String busName = busNameET.getText().toString();
                Bus bus = new Bus();
                bus.setBusName(busName);

             //   BusDbHelper busDbHelper = new BusDbHelper(AdminActivity.this);
              //  busDbHelper.insertData(bus);


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Bus/All");
                reference.child(busName).setValue(bus);

                Toast.makeText(AdminActivity.this, "Bus added successfully", Toast.LENGTH_SHORT).show();
                busNameET.setText("");
            }
        });

        busListET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewDialog dialog = new ViewDialog();
                dialog.viewDialog(AdminActivity.this, busListET, null);

            }
        });
    }
}
