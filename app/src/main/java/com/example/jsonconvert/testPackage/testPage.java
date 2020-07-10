package com.example.jsonconvert.testPackage;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonconvert.HomeActivity;
import com.example.jsonconvert.R;
import com.example.jsonconvert.confirmationPage;
import com.example.jsonconvert.model.ticketBookingModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class testPage extends AppCompatActivity implements OnSeatSelected {

    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    RecyclerView recyclerView ;
    List<itemModel> items = new ArrayList<>();
    DatabaseReference m ;
    String form , to , bus , date , time ;
    int COUNT   = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);



        Intent o = getIntent();

//        o.putExtra("from" , from_location_ET.getText().toString()) ;
//        o.putExtra("to" , to_location_ET.getText().toString()) ;
//        o.putExtra("bus" , bus_name) ;
//        o.putExtra("date" , date) ;
//        o.putExtra("time" , time_ET.getText().toString()) ;

        form = o.getStringExtra("from") ;
        to = o.getStringExtra("to") ;
        bus =  o.getStringExtra("bus") ;
        date =  o.getStringExtra("date") ;
        time =  o.getStringExtra("time") ;
        m = FirebaseDatabase.getInstance().getReference("ticketDB").child(bus).child(date);


            GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
            recyclerView = findViewById(R.id.lst_items);
            recyclerView.setLayoutManager(manager);


        txtSeatSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(COUNT == 0 )
                {

                    Toast.makeText(getApplicationContext() , "Please Select Availble Seat" , Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    final ProgressDialog dialog = new ProgressDialog(testPage.this) ;
                    dialog.setMessage("Purchasing Ticket");
                    dialog.show();
                    m.setValue(items).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        DatabaseReference    mdatabase = FirebaseDatabase.getInstance().getReference();
                            // upload  the tikect to the user db .....
                            ticketBookingModel model = new ticketBookingModel(form , to , date , time , bus ,FirebaseAuth.getInstance().getUid() );

                            mdatabase.child("tickets").child(FirebaseAuth.getInstance().getUid()).child(date).setValue(model)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    dialog.dismiss();
                                    Intent p = new Intent(getApplicationContext() , confirmationPage.class);
                                    startActivity(p);
                                    finish();
                                }
                            });





                        }
                    }) ;

                }

            }
        });


        m.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    loadData();
                    Log.d("TAG", "onDataChange:  ONLINE" );
                }
                else {

                    writeData() ;
                    Log.d("TAG", "onDataChange:  OFFLINE" );

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    @Override
    public void onSeatSelected(int count) {

        txtSeatSelected.setText("Book "+count+" seats");

        COUNT = count ;
    }


    @Override
    public void onSeatNewSelected(int count) {

   //    Toast.makeText(getApplicationContext() ,  count + " " , Toast.LENGTH_SHORT).show();

        Log.d("TAG", " Before  id: " + items.get(count).getId()  + " UID : " + items.get(count).getUid()
        + " LABEL " + items.get(count).getLabel());
        // lets update the list
      //  AbstractItem items = new AbstractItem(count , count , "TAKEN")  ;

        if(items.get(count).getUid().equals("not_taken"))
        {
            items.get(count).setUid(FirebaseAuth.getInstance().getUid());
        }
        else
        {
            items.get(count).setUid("not_taken");
        }

        Log.d("TAG", " AFTER   id: " + items.get(count).getId()  + " UID : " + items.get(count).getUid()
                + " LABEL " + items.get(count).getLabel());
    }

    public  void  loadData(){

        m.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                items.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    itemModel    chat = ds.getValue(itemModel.class) ;


                    items.add(chat) ;

                    Log.d("MSGESDSS" , chat.getUid()  );
                }
                //


                // set adapter

                AirplaneAdapter adapter = new AirplaneAdapter(testPage.this, items);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        }) ;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // check for the  data exist or not


    }

    private void writeData() {
        for (int i=0; i<30; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4) {
                items.add(new itemModel(String.valueOf(i) , String.valueOf(i) , "not_taken"  , 1));
            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                items.add(new itemModel(String.valueOf(i), String.valueOf(i) , "not_taken" , 0));
            } else {
                items.add(new itemModel(String.valueOf(i), String.valueOf(i) , "not_taken" ,2));
            }


            AirplaneAdapter adapter = new AirplaneAdapter(testPage.this, items);
            recyclerView.setAdapter(adapter);
        }

    }
}
