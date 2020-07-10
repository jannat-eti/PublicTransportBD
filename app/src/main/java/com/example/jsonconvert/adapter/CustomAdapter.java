package com.example.jsonconvert.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jsonconvert.Bus;
import com.example.jsonconvert.R;
import com.example.jsonconvert.Ticket_Booking_Home;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    public Activity activity;
    public Context context;
    List<Bus> busList;


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        public TextView busNameTV;

        public MyViewHolder(@NonNull View view) {
            super(view);

            busNameTV = (TextView) view.findViewById(R.id.bus_name_TV);

        }
    }

    public CustomAdapter(Activity activity, Context context, List<Bus> busList) {
        this.activity = activity;
        this.context = context;
        this.busList = busList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,final int position) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_bus, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final  int position) {

        final Bus bus = busList.get(position);
        holder.busNameTV.setText(bus.getBusName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Ticket_Booking_Home.class);
                intent.putExtra("bus" ,  bus.getBusName()) ;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

}
