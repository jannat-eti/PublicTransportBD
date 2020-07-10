package com.example.jsonconvert.testPackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.jsonconvert.R;

import java.util.List;

public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;
    private OnSeatSelected onSeatNewSelected ;
    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;
    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;


        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);

        }

    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;

        public CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);


        }

    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<itemModel> mItems;

    public AirplaneAdapter(Context context, List<itemModel> items) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(mItems.get(position).getType() == TYPE_CENTER)
        {
            return TYPE_CENTER ;
        }
        else if (mItems.get(position).getType() == TYPE_EDGE)
        {
            return  TYPE_EDGE ;
        }
       else
        {
            return  TYPE_EMPTY ;

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final  int type = (int) mItems.get(position).getType();
        if (type == TYPE_CENTER) {
      final    itemModel    item =  mItems.get(position);
            final CenterViewHolder holder = (CenterViewHolder) viewHolder;

            if(item.getUid().equals("not_taken"))
            {
                holder.imgSeat.setImageResource(R.drawable.seat_normal);
                holder.imgSeat.setTag("not_booked");
            }
            else
            {
                holder.imgSeat.setImageResource(R.drawable.seat_normal_booked);
                holder.imgSeat.setTag("booked");
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(holder.imgSeat.getTag() == "booked")
                    {
                        Log.d("TAG", "onClick: "+ holder.imgSeat.getTag());
                       // holder.imgSeat.setImageResource(R.drawable.seat_normal);
                    }
                    else
                    {
                       // holder.imgSeat.setImageResource(R.drawable.seat_normal_booked);
                       // toggleSelection(position);
                        if(holder.imgSeat.getTag() == "not_booked" )
                        {
                            Log.d("TAG", "onClick: "+ holder.imgSeat.getTag());
                            holder.imgSeat.setImageResource(R.drawable.seat_normal_selected);
                            holder.imgSeat.setTag("selected");
                        }
                        else
                        {
                            Log.d("TAG", "onClick: "+ holder.imgSeat.getTag());
                            holder.imgSeat.setImageResource(R.drawable.seat_normal);
                            holder.imgSeat.setTag("not_booked");
                        }
                        //   Toast.makeText(mContext ,  item.getLabel() + " " , Toast.LENGTH_SHORT).show();
                        toggleSelection(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                        mOnSeatSelected.onSeatNewSelected(position);
                    }


                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        }
        else if (type == TYPE_EDGE) {
            final    itemModel item =  mItems.get(position);
            final EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            if(item.getUid().equals("not_taken"))
            {
                holder.imgSeat.setImageResource(R.drawable.seat_normal);
                holder.imgSeat.setTag("not_booked");
            }
            else
            {
                holder.imgSeat.setImageResource(R.drawable.seat_normal_booked);
                holder.imgSeat.setTag("booked");
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(holder.imgSeat.getTag() == "booked")
                    {
                     //   holder.imgSeat.setImageResource(R.drawable.seat_normal);
                    }
                    else
                    {
                       // holder.imgSeat.setImageResource(R.drawable.seat_normal_booked);
                      //  toggleSelection(position);
                        if(holder.imgSeat.getTag() == "not_booked" )
                        {
                            Log.d("TAG", "onClick: "+ holder.imgSeat.getTag());
                            holder.imgSeat.setImageResource(R.drawable.seat_normal_selected);
                            holder.imgSeat.setTag("selected");
                        }
                        else
                        {
                            Log.d("TAG", "onClick: "+ holder.imgSeat.getTag());
                            holder.imgSeat.setImageResource(R.drawable.seat_normal);
                            holder.imgSeat.setTag("not_booked");
                        }
                        toggleSelection(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItemCount());
                        mOnSeatSelected.onSeatNewSelected(position);
                    }

                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        }
    }

}
