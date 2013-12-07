package com.ypy.reallifeachievement;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CompareArrayAdapter extends ArrayAdapter<ACCompare>{
	private final Context context;
	private final ArrayList<ACCompare> list;

	public CompareArrayAdapter(Context context, ArrayList<ACCompare> items) {
		super(context, R.layout.cell_compare, items);
		this.context = context;
		this.list = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.cell_compare, parent, false);
		TextView achievementTitle = (TextView) rowView.findViewById(R.id.achievement_title);
		CheckBox doneYou = (CheckBox) rowView.findViewById(R.id.done_you);
		CheckBox doneOther = (CheckBox) rowView.findViewById(R.id.done_other);
		
		achievementTitle.setText(list.get(position).getOther().getName());
		doneYou.setChecked(list.get(position).getYou().getDone());
		doneOther.setChecked(list.get(position).getOther().getDone());
		
		return rowView;
	}
}
