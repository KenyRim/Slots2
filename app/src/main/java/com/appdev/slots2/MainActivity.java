package com.appdev.slots2;

import android.os.Bundle;
import java.util.*;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appdev.slots2.adapters.RVAdapter;
import com.appdev.slots2.models.NewsModel;

public class MainActivity extends AppCompatActivity implements
RVAdapter.ItemClickListener, NewsParsing.OnWorkDoneListener
{

	@Override
	public void onItemClick(View view, int position)
	{ 
	Toast.makeText(this,"клик по "+position,Toast.LENGTH_SHORT).show();
	}

private RecyclerView rv;
private RVAdapter adapter; 
private ArrayList<NewsModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
		
		rv = findViewById(R.id.rv_main); 
		data = new ArrayList<>(); 
		
		LinearLayoutManager lm = new LinearLayoutManager(this);
		adapter = new RVAdapter(this,data,this); 
		rv.setLayoutManager(lm);
		rv.setAdapter(adapter); 
		
		
		new NewsParsing(this).execute();
		
		
    }

	@Override
	public void done(ArrayList<NewsModel> list)
	{ 

	adapter.addNewData(list);
		
	}

	
	
}
