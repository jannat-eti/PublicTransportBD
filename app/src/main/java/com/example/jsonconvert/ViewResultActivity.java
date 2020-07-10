package com.example.jsonconvert;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jsonconvert.adapter.CustomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewResultActivity extends AppCompatActivity {

    TextView messageTV;
    RecyclerView busRV;
    Button goBackBTN;

    static String fromDistance ;
    static String toDistance ;
     String fromBusList;
     String toBusList;

    final List<Stopage> stopageList =new ArrayList<>();
    final List<Bus> allBus = new ArrayList<>();
    Stopage fromStopage, toStopage;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);


        fromDistance = getIntent().getStringExtra("from");
        toDistance = getIntent().getStringExtra("to");

        Toast.makeText(this, "" + fromDistance + " " + toDistance, Toast.LENGTH_SHORT).show();

        loadData();
        init();





    }

    private void init() {

        messageTV = (TextView) findViewById(R.id.message_TV);
        busRV = (RecyclerView) findViewById(R.id.busRV);
        goBackBTN = (Button) findViewById(R.id.go_back_BTN);



        messageTV.setText("Showing bus list from " + fromDistance + " to " + toDistance );

        messageTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });







        goBackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewResultActivity.super.onBackPressed();
            }
        });
    }

    private void loadData() {

        DatabaseReference stopageRef = FirebaseDatabase.getInstance().getReference("Station/All");
        stopageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Stopage stopage = snapshot.getValue(Stopage.class);
                    stopageList.add(stopage);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        DatabaseReference busRef = FirebaseDatabase.getInstance().getReference("Bus/All");
        busRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Bus bus = snapshot.getValue(Bus.class);
                    allBus.add(bus);

                }

                testFun();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();



    }


    public  void testFun(){

        for (int i=0; i<stopageList.size(); i++){

            Stopage stopage = stopageList.get(i);
            if (stopage.stationName.toUpperCase().equals(fromDistance.toUpperCase())){

                fromStopage = stopage;

            }if (stopage.stationName.toUpperCase().equals(toDistance.toUpperCase())){

                toStopage = stopage;
            }
        }


        try {
            fromBusList = fromStopage.getBusList();
            toBusList = toStopage.getBusList();
        }
        catch (Exception e){


            Toast.makeText(getApplicationContext() , "ERROR " + e.getMessage() , Toast.LENGTH_LONG).show();
        }



        List<Bus> finalBusList = new ArrayList<>();

        for (int i=0; i<allBus.size(); i++){

            Bus bus = allBus.get(i);

            try{

                if ((fromBusList.length()>0 && toBusList.length()>0) &&
                        (fromBusList.toUpperCase().contains(bus.getBusName().toUpperCase())
                                && toBusList.contains(bus.getBusName().toUpperCase()))){

                    finalBusList.add(bus);


                }
            }catch (Exception e){

                Toast.makeText(getApplicationContext() , "ERROR " + e.getMessage() , Toast.LENGTH_LONG).show();

            }
        }





        adapter = new CustomAdapter(ViewResultActivity.this, ViewResultActivity.this, finalBusList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ViewResultActivity.this, 1);
        busRV.setLayoutManager(layoutManager);
        busRV.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));
        busRV.setItemAnimator(new DefaultItemAnimator());
        busRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    public static String  sendFrom(){

        return fromDistance ;
    }

    public static String sendTo()
    {
        return  toDistance  ;
    }

}
