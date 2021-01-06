package com.appdev.slots2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.appdev.slots2.utils.GetImage;
import com.appdev.slots2.R;

import java.util.List;
import java.util.*;

public class SlotsRVAdapter extends RecyclerView.Adapter<SlotsRVAdapter.ViewHolder> {


    private Context context;

    private LayoutInflater mInflater;
    private List<String> data;
    private ArrayList<String> temp = new ArrayList<String>();

    public interface OnScrollResult {
        void getScrollResult(List<String> resultString,int position);
    }

    private OnScrollResult listener;


    // data is passed into the constructor
    public SlotsRVAdapter(Context context,List<String> data,OnScrollResult listener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.listener = listener;
    }

    public void cleanTemp() {
        temp = new ArrayList<String>();
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = mInflater.inflate(R.layout.rv_item_slots,parent,false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {

        Random ran = new Random();
        final int x = ran.nextInt(data.size());
        int realPosition = x % data.size();

        String item = data.get(realPosition);

        holder.photo.setImageResource(GetImage.imageName(item));
        temp.add(GetImage.name(item));
        //Glide.with(context).load(news.getEnclosure()).into(holder.photo);


        listener.getScrollResult(temp,realPosition);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
        //return data.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.iv_item);

        }

    }

}
