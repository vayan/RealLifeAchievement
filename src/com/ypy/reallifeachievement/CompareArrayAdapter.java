package com.ypy.reallifeachievement;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CompareArrayAdapter extends ArrayAdapter<ACItem>{
	private final Context context;
	private final ArrayList<ACItem> items;

	public CompareArrayAdapter(Context context, ArrayList<ACItem> items) {
		super(context, R.layout.cell_compare, items);
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.cell_compare, parent, false);
		TextView achievementTitle = (TextView) rowView.findViewById(R.id.achievement_title);
		CheckBox doneYou = (CheckBox) rowView.findViewById(R.id.done_you);
		CheckBox doneOther = (CheckBox) rowView.findViewById(R.id.done_other);
				
		achievementTitle.setText(items.get(position).getName());
		doneYou.setChecked(items.get(position).getDone());
		doneOther.setChecked(items.get(position).getDone());
		return rowView;
	}
}
