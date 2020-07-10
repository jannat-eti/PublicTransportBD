package com.example.jsonconvert;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



public class viewHolderForOldTickets extends RecyclerView.ViewHolder {


    View mView;


    TextView from , to , bus_name  ,date , time  ;

    public viewHolderForOldTickets(View itemView) {
        super(itemView);

        mView = itemView;


        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());

            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });




    }

    //set details to recycler view row
    public void setDetails(Context ctx, String From , String To ,String Bus_name  ,String Date ,String Time){
        //Views
         from = mView.findViewById(R.id.from);

         to = mView.findViewById(R.id.to);
         date = mView.findViewById(R.id.date);
         bus_name = mView.findViewById(R.id.Bus) ;
         time = mView.findViewById(R.id.time);
         from.setText("From : "+ From);
         to.setText("To : "+ To);
         bus_name.setText("Bus Name : "+ Bus_name);
         date.setText("Date : " + Date);
         time.setText("Time : " + Time);





        //int pos = getAdapterPosition();
    }


    private viewHolderForOldTickets.ClickListener mClickListener;
    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(viewHolderForOldTickets.ClickListener clickListener)
    {
        mClickListener = clickListener;
    }
}
