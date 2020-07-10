package com.example.jsonconvert;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jsonconvert.adapter.SelectBusAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminAddStationActivity extends AppCompatActivity {

    EditText stationNameET, stationOrderET, stationDistanceET;
    ImageView busListArrowIV;
    RecyclerView busListRV;
    Button insertStationBTN;

    List<Bus> allBus = new ArrayList<>();

    SelectBusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_station);

        init();
        loadBus();
    }

    private void init() {

        stationNameET = (EditText) findViewById(R.id.ET_station_name);
        stationOrderET = (EditText) findViewById(R.id.ET_station_order);
        stationDistanceET = (EditText) findViewById(R.id.ET_station_distance);

        busListArrowIV = (ImageView) findViewById(R.id.bus_list_arrow_IV);

        busListRV = (RecyclerView) findViewById(R.id.bus_list_RV);

        insertStationBTN = (Button) findViewById(R.id.insert_BTN);


        busListArrowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (busListRV.getVisibility() == View.VISIBLE){

                    busListRV.setVisibility(View.GONE);

                }else {

                    addRecyclerView();
                    busListRV.setVisibility(View.VISIBLE);
                }
            }
        });

        insertStationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertStation();
            }
        });
    }

    private void insertStation() {

        if (checkEmptyValidity()){


            String stationName = stationNameET.getText().toString();
            String stationOrder = stationOrderET.getText().toString();
            String stationDistance = stationDistanceET.getText().toString();
            adapter = new SelectBusAdapter(AdminAddStationActivity.this, AdminAddStationActivity.this, allBus,
                    1, stationName, stationOrder, stationDistance);

            List <Bus> myBusList = new ArrayList<>();
            myBusList = adapter.selectBusList;

            String busString = "";

            for (int i=0; i<myBusList.size(); i++){

                Bus bus = myBusList.get(i);
                busString = busString + bus.getBusName().toUpperCase() + ", ";
            }







            Stopage station = new Stopage();
            station.setId(0);
            station.setStationName(stationName);
            station.setBusList(busString);
            station.setOrder(Integer.parseInt(stationOrder));
            station.setDistanceFromUttara(Float.parseFloat(stationDistance));

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Station/All");

            //  StoppageDbHelper stoppageDbHelper = new StoppageDbHelper(AdminActivity.this);
            //  stoppageDbHelper.insertData(station);

            reference.child(stationName).setValue(station);

            Toast.makeText(AdminAddStationActivity.this, "Data added successfully.", Toast.LENGTH_SHORT).show();

            stationNameET.setText("");
            stationOrderET.setText("");
            stationDistanceET.setText("");

            busString = "";

        }

    }

    private void loadBus(){


        DatabaseReference busRef = FirebaseDatabase.getInstance().getReference("Bus/All");
        busRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Bus bus = snapshot.getValue(Bus.class);
                    allBus.add(bus);

                }






            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addRecyclerView() {

        adapter = new SelectBusAdapter(AdminAddStationActivity.this, AdminAddStationActivity.this, allBus, 0, "", "", "");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(AdminAddStationActivity.this, 1);
        busListRV.setLayoutManager(layoutManager);
        busListRV.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));
        busListRV.setItemAnimator(new DefaultItemAnimator());
        busListRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private boolean checkEmptyValidity(){

        if (stationNameET.getText().toString().equals("")){

            stationNameET.setError("Station name cannot be empty");
            return false;

        }else if (stationNameET.getText().toString().length()>0 && Character.isWhitespace(stationNameET.getText().toString().charAt(0))){

            stationNameET.setError("Station name cannot start with a space");
            return false;

        }else if (stationDistanceET.getText().toString().equals("")){

            stationDistanceET.setError("Station name cannot be empty");
            return false;

        }else if (stationDistanceET.getText().toString().length()>0 && Character.isWhitespace(stationDistanceET.getText().toString().charAt(0))){

            stationDistanceET.setError("Station name cannot start with a space");
            return false;

        }else if (stationOrderET.getText().toString().equals("")){

            stationOrderET.setError("Station name cannot be empty");
            return false;

        }else if (stationOrderET.getText().toString().length()>0 && Character.isWhitespace(stationOrderET.getText().toString().charAt(0))){

            stationOrderET.setError("Station name cannot start with a space");
            return false;

        }else {

            return true;
        }


    }
}
