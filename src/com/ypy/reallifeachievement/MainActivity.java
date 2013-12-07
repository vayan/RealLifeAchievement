package com.ypy.reallifeachievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends NfcEnabledActivity {

	public final static String GROUPNAME = "com.example.myfirstapp.GROUPNAME";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_of_group);

		final ListView listview = (ListView) findViewById(R.id.listview);

		final ArrayList<ACGroup> myGroups = new ArrayList<ACGroup>();
		myGroups.add(new ACGroup("Beijing tour", "Let s visit Beijing !"));
		myGroups.get(0).AddACItem(
				new ACItem("Muraille de chine", "Devenez un homme un vrais"));

		myGroups.add(new ACGroup("Marathon 2014",
				"what if you become an athlet next year ?"));
		myGroups.add(new ACGroup("In your bed. Or not",
				"Tell me what happened there..."));

		final MyArrayAdapter adapter = new MyArrayAdapter(this, myGroups);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(view.getContext(), GroupActivity.class);
				intent.putExtra(GROUPNAME, new Gson().toJson(parent.getItemAtPosition(position)));

				startActivity(intent);
			}
		});
	}

}
