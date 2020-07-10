package com.example.jsonconvert.adapter;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.jsonconvert.Bus;
import com.example.jsonconvert.R;

import java.util.ArrayList;
import java.util.List;

public class SelectBusAdapter extends RecyclerView.Adapter<SelectBusAdapter.MyViewHolder> {

    public Activity activity;
    public Context context;
    List<Bus> busList;
    int flag;
    String stationName, stationOrder, stationDistance;

    static public List<Bus> selectBusList = new ArrayList<>();


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        public CheckBox busNameCB;

        public MyViewHolder(@NonNull View view) {
            super(view);

            busNameCB = (CheckBox) view.findViewById(R.id.bus_name_CB);

        }
    }

    public SelectBusAdapter(Activity activity, Context context, List<Bus> busList,
                            int flag, String stationName, String stationOrder, String stationDistance) {
        this.activity = activity;
        this.context = context;
        this.busList = busList;

        this.flag = flag;
        this.stationName = stationName;
        this.stationOrder = stationOrder;
        this.stationDistance = stationDistance;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_select_bus, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final Bus bus = busList.get(position);
        holder.busNameCB.setText(bus.getBusName());

        holder.busNameCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    Bus myBus = new Bus();
                    myBus.setBusName(holder.busNameCB.getText().toString());
                    myBus.setId(0);
                    selectBusList.add(bus);

                }else {

                    selectBusList.remove(bus);
                }
            }
        });


        if (flag == 1){

            for (int i=0; i<selectBusList.size(); i++){

                Toast.makeText(context, ""+ selectBusList.size(), Toast.LENGTH_SHORT).show();
            }
        }





    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

}

