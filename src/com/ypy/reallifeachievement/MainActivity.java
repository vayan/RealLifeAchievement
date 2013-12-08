package com.ypy.reallifeachievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import android.R.bool;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.app.ActionBar;

public class MainActivity extends NfcEnabledActivity {

	public final static String GROUPNAME = "com.example.myfirstapp.GROUPNAME";

	ArrayList<ACGroup> myGroups = null;
	MyArrayAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_of_group);

	}

	public void addList() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage("Let's give a cute name:");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				ACGroup groupe = new ACGroup(value, "");
				myGroups.add(groupe);
				groupe.saveMe(MainActivity.this);
				MainActivity.this.adapter.notifyDataSetChanged();
				return;
			}
		});

		alert.setNegativeButton("Cancel", null);
		alert.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_compose:
			addList();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void firstFeed() {
		myGroups.add(new ACGroup("Beijing tour", "Let s visit Beijing !"));
		myGroups.get(0).AddACItem(new ACItem("Great Wall", ""));
		myGroups.get(0).AddACItem(new ACItem("Temple of heaven", ""));
		myGroups.get(0).AddACItem(new ACItem("Forbidden City", ""));
		myGroups.get(0).AddACItem(new ACItem("Tiananmen Square", ""));
		myGroups.get(0).AddACItem(new ACItem("Hutongs Tour", ""));
		myGroups.get(0).AddACItem(new ACItem("Mings Tombs", ""));
		myGroups.get(0).AddACItem(new ACItem("Lama temple", ""));
		myGroups.get(0).AddACItem(new ACItem("Benhai park", ""));
		myGroups.get(0).AddACItem(new ACItem("Capital museum", ""));
		myGroups.get(0).saveMe(this);

		myGroups.add(new ACGroup("Beijing's Marathon 2014", ""));
		myGroups.get(1).AddACItem(new ACItem("Let's take a walk !", ""));
		myGroups.get(1).AddACItem(new ACItem("Run 10km in a week", ""));
		myGroups.get(1).AddACItem(new ACItem("I run since 2 weeks !", ""));
		myGroups.get(1).AddACItem(new ACItem("Do it in mountain", ""));
		myGroups.get(1).AddACItem(new ACItem("Run faster than 10km/h for 3km", ""));
		myGroups.get(1).AddACItem(new ACItem("Do the 10km", ""));
		myGroups.get(1).AddACItem(new ACItem("Do the Half marathon", ""));
		myGroups.get(1).AddACItem(new ACItem("Do the marathon", ""));
		myGroups.get(1).saveMe(this);
		
		myGroups.add(new ACGroup("Discover Victor Hugo", ""));
		myGroups.get(2).AddACItem(new ACItem("Watch \"Les Miserables\"", ""));
		myGroups.get(2).AddACItem(new ACItem("Read \"The Last Day of a Condemned Man\"", ""));
		myGroups.get(2).AddACItem(new ACItem("Read \"Notre Dame de Paris\"", ""));
		myGroups.get(2).AddACItem(new ACItem("Read \"Les Miserables\"", ""));
		myGroups.get(2).saveMe(this);

		myGroups.add(new ACGroup("Angelhack Beijing 2013", ""));
		myGroups.get(3).AddACItem(new ACItem("Find a revolutionnary idea", ""));
		myGroups.get(3).AddACItem(new ACItem("Bribe the jury", ""));
		myGroups.get(3).AddACItem(new ACItem("Form a great Team", ""));
		myGroups.get(3).AddACItem(new ACItem("Work", ""));
		myGroups.get(3).AddACItem(new ACItem("Work again", ""));
		myGroups.get(3).AddACItem(new ACItem("Sleep", ""));
		myGroups.get(3).AddACItem(new ACItem("Find good joke for the presentation", ""));
		myGroups.get(3).saveMe(this);

	}

	@Override
	protected void onResume() {
		super.onResume();

		final ListView listview = (ListView) findViewById(R.id.listview);

		new Utils();
		myGroups = Utils.RestoreAllGroup(this);

		if (myGroups.size() == 0) {
			firstFeed();
		}
		adapter = new MyArrayAdapter(this, myGroups);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(view.getContext(),
						GroupActivity.class);
				ACGroup groupe = (ACGroup) parent.getItemAtPosition(position);
				Log.i("SEXE", "avant:" + groupe.getId());
				intent.putExtra(GROUPNAME, groupe.getName());
				startActivity(intent);
				/**
				 * overridePendingTransition(R.anim.animation_leave,
				 * R.anim.animation_leave);
				 */
			}
		});
		listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					final int position, long id) {
				new AlertDialog.Builder(view.getContext())
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setMessage(
								"Do you want to delete this list of Achievement ?")
						.setPositiveButton("Sure!",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										myGroups.get(position).deleteMe(
												MainActivity.this);
										myGroups.remove(position);
										MainActivity.this.adapter
												.notifyDataSetChanged();
									}

								}).setNegativeButton("Oups... nop.", null)
						.show();
				return true;

			}
		});
	}
}
