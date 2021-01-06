package com.appdev.slots2;

import android.view.View.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity implements OnClickListener
{


	private Button btnMenu, btnSlots;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		btnMenu = findViewById(R.id.btn_xml);
		btnMenu.setOnClickListener(this);
	
		btnSlots = findViewById(R.id.btn_slots);
		btnSlots.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v){
		switch(v.getId()){

			case R.id.btn_xml: /** Start a new Activity MyCards.java */
				Intent intent = new Intent(this, MainActivity.class);
				this.startActivity(intent);
				break;

			case R.id.btn_slots:
				startActivity(new Intent(this,SlotsActivity.class));
				break;
		}
	}

	

	
} 
