package com.ypy.reallifeachievement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

public class achievementAdaptater extends ArrayAdapter<ACItem> {
	private final Context context;
	private final ArrayList<ACItem> achievements;

	public achievementAdaptater(Context context, ArrayList<ACItem> achievements) {
		super(context, R.layout.cell_achievement, achievements);
		this.context = context;
		this.achievements = achievements;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.cell_achievement, parent, false);
		TextView achievement_name = (TextView) rowView.findViewById(R.id.achievement_cell_title);
		CheckBox isDone = (CheckBox) rowView.findViewById(R.id.done);
		achievement_name.setText(achievements.get(position).getName());
		isDone.setChecked(achievements.get(position).getDone());	
	
		/*
		
		ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
		      @Override  
		      public void onItemClick( AdapterView<?> parent, View item,   
		                               int position, long id) {  
		        Planet planet = listAdapter.getItem( position );  
		        planet.toggleChecked();  
		        PlanetViewHolder viewHolder = (PlanetViewHolder) item.getTag();  
		        viewHolder.getCheckBox().setChecked( planet.isChecked() );  
		      }  
		    });  
		
		
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	     @Override
	      public void onItemClick(AdapterView<?> parent,  final View view,
	          int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
	        view.animate().setDuration(2000).alpha(0)
	            .withEndAction(new Runnable() {
	              @Override
	              public void run() {
	                list.remove(item);
	                adapter.notifyDataSetChanged();
	                view.setAlpha(1);
	              }
	            });
	      }
	    }); */
		return rowView;

	}

}
