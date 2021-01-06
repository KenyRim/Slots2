package com.appdev.slots2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.appdev.slots2.models.NewsModel;
import com.appdev.slots2.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>
{ 


private Context context;

    private List<NewsModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RVAdapter(Context context, List<NewsModel> data,ItemClickListener ItemClickListenermClickListener) {
        this.context = context;
		this.mInflater = LayoutInflater.from(context);
        this.mData = data; 
		this.mClickListener = ItemClickListenermClickListener;
		
    } 
	
	
	public void addNewData(List<NewsModel> data){
	mData.clear(); 
	mData = data; 
	notifyDataSetChanged();
	
	}

	
	
    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsModel news = mData.get(position);
        holder.tvTitle.setText(news.getTitle()); 
		holder.tvText.setText(news.getDescription()); 
		holder.tvDate.setText(news.getPubDate()); 
		Glide.with(context).load(news.getEnclosure()).into(holder.photo);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle,tvText,tvDate; 
		ImageView photo;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_title);
			tvText = itemView.findViewById(R.id.item_text); 
			tvDate = itemView.findViewById(R.id.item_date); 
			photo = itemView.findViewById(R.id.iv_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
