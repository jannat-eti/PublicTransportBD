package com.example.jsonconvert;

import android.content.Intent;
import android.graphics.Color;

import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.jsonconvert.adapter.CustomAdapter;
import com.example.jsonconvert.maps.MapsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AutoCompleteTextView fromLocationET, toLocationET;
    Button searchBTN;
    Button show_on_map_BTN;
    RecyclerView busRV;
    CustomAdapter adapter;

    final List<Bus> allBus = new ArrayList<>();
    final List<Stopage> stopageList =new ArrayList<>();
    Stopage fromStopage, toStopage;

    ArrayList<String> Stations=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



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


        DatabaseReference stopageRef = FirebaseDatabase.getInstance().getReference("Station/All");
        stopageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Stopage stopage = snapshot.getValue(Stopage.class);
                    stopageList.add(stopage);
                    Stations.add(stopage.stationName);
                    Log.d("Station = ",stopage.stationName);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        init();


    }

    private void init() {




        fromLocationET =findViewById(R.id.from_location_ET);
        toLocationET =findViewById(R.id.to_location_ET);
        searchBTN = (Button) findViewById(R.id.search_BTN);
        show_on_map_BTN = (Button) findViewById(R.id.show_on_map_BTN);
        busRV = (RecyclerView) findViewById(R.id.busRV);

        //Creating the instance of ArrayAdapter containing list of fruit names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, Stations);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.from_location_ET);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv2 = (AutoCompleteTextView) findViewById(R.id.to_location_ET);
        actv2.setThreshold(1);//will start working from first character
        actv2.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv2.setTextColor(Color.RED);

        findViewById(R.id.search_BTN2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, oldTicketList.class);
                startActivity(intent);

            }
        });


        findViewById(R.id.aboutUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, aboutPage.class);
                startActivity(intent);

            }
        });


        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValidity()){

                    String fromLocation = fromLocationET.getText().toString();
                    String toLocation = toLocationET.getText().toString();

                    Intent intent = new Intent(HomeActivity.this, ViewResultActivity.class);
                    intent.putExtra("from", fromLocation);
                    intent.putExtra("to", toLocation);
                    startActivity(intent);
                }


//                DatabaseReference fromRef = FirebaseDatabase.getInstance().getReference("Station/All/" + fromLocation);
//                DatabaseReference toRef = FirebaseDatabase.getInstance().getReference("Station/All/" + toLocation);
//
//
//                for (int i=0; i<stopageList.size(); i++){
//
//                    Stopage stopage = stopageList.get(i);
//                    if (stopage.stationName.toUpperCase().equals(fromLocation.toUpperCase())){
//
//                        fromStopage = stopage;
//
//                    }if (stopage.stationName.toUpperCase().equals(toLocation.toUpperCase())){
//
//                        toStopage = stopage;
//                    }
//                }
//
//
//                String fromBusList = fromStopage.getBusList();
//                String toBusList = toStopage.getBusList();
//
//
//
//                List<Bus> finalBusList = new ArrayList<>();
//
//                for (int i=0; i<allBus.size(); i++){
//
//                    Bus bus = allBus.get(i);
//
//                    if (fromBusList.toUpperCase().contains(bus.getBusName().toUpperCase())
//                            && toBusList.contains(bus.getBusName().toUpperCase())){
//
//                        finalBusList.add(bus);
//
//
//                    }
//                }
//
//
//
//
//
//                adapter = new CustomAdapter(HomeActivity.this, HomeActivity.this, finalBusList);
//                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HomeActivity.this, 1);
//                busRV.setLayoutManager(layoutManager);
//                busRV.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));
//                busRV.setItemAnimator(new DefaultItemAnimator());
//                busRV.setAdapter(adapter);
//                adapter.notifyDataSetChanged();



            }
        });





        show_on_map_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkValidity()){

                    String fromLocation = fromLocationET.getText().toString();
                    String toLocation = toLocationET.getText().toString();

                    Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                    intent.putExtra("fromLocation", fromLocation + " , Bangladesh");
                    intent.putExtra("toLocation", toLocation + " , Bangladesh");
                    startActivity(intent);
                }

            }
        });



    }






    public boolean checkValidity(){

        if (fromLocationET.getText().toString().equals("") || (fromLocationET.getText().toString().length() > 0
                && Character.isWhitespace(fromLocationET.getText().toString().charAt(0)))){

            fromLocationET.setError("Please enter valid location name");
            return  false;

        }else if (toLocationET.getText().toString().equals("") || (toLocationET.getText().toString().length() > 0
                && Character.isWhitespace(toLocationET.getText().toString().charAt(0)))){

            toLocationET.setError("Please enter valid location name");
            return  false;

        }else {

            return true;
        }
    }


}
