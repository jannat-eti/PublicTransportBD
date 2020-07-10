package com.example.jsonconvert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jsonconvert.model.ticketBookingModel;
import com.example.jsonconvert.testPackage.ticketCancelationPage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class oldTicketList extends AppCompatActivity {

    RecyclerView recyclerView ;
    LinearLayoutManager llm  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_ticket_list);

        recyclerView = findViewById(R.id.list) ;
        llm = new LinearLayoutManager(getApplicationContext()) ;
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm) ;

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference().child("tickets").child(FirebaseAuth.getInstance().getUid());



        FirebaseRecyclerAdapter<ticketBookingModel, viewHolderForOldTickets> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ticketBookingModel, viewHolderForOldTickets>(
                        ticketBookingModel.class,
                        R.layout.row_old_ticket,
                        viewHolderForOldTickets.class,
                        mdatabase
                ) {

                    @Override
                    protected void populateViewHolder(viewHolderForOldTickets viewHolder, ticketBookingModel model, int position) {

                       // TextView from , to , bus_name  ,date , time  ;
                        viewHolder.setDetails(getApplicationContext(), model.getFrom_location_ET() , model.getTo_location_ET(), model.getBus_name(), model.getDate_ET() , model.getTime_ET() );

                        Handler handler  = new Handler() ;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {



                            }
                        },2000);


                    }

                    @Override
                    public viewHolderForOldTickets onCreateViewHolder(ViewGroup parent, int viewType) {

                        final viewHolderForOldTickets viewHolder = super.onCreateViewHolder(parent, viewType);


                        viewHolder.setOnClickListener(new viewHolderForOldTickets.ClickListener() {
                            @Override
                            public void onItemClick(View view,final int position) {

                                // getiing  data from the views ;

                                // staterting the click acitvity f

                                //        Toast.makeText(getApplicationContext() , "Resname "+ ResName +Comment+Price + status + orderlist , Toast.LENGTH_SHORT).show();


                             Intent p =   new Intent( getApplicationContext() ,ticketCancelationPage.class);

                             p.putExtra("bus" , getItem(position).getBus_name()) ;
                             p.putExtra("date" , getItem(position).getDate_ET()) ;
                             startActivity( p );
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {




                            }
                        });



                        return viewHolder;
                    }


                };

        //set adapter to recyclerview
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}