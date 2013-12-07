package com.ypy.reallifeachievement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<ACGroup> {
	private final Context context;
	private final ArrayList<ACGroup> groups;

	public MyArrayAdapter(Context context, ArrayList<ACGroup> groups) {
		super(context, R.layout.cell_group_name, groups);
		this.context = context;
		this.groups = groups;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.cell_group_name, parent, false);
		TextView groupName = (TextView) rowView.findViewById(R.id.groupName);
		TextView score = (TextView) rowView.findViewById(R.id.Score);
		groupName.setText(groups.get(position).getName());
		score.setText(groups.get(position).getScore());		

		return rowView;

	}

}
