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

public class MainActivity extends Activity {

	public final static String GROUPNAME = "com.example.myfirstapp.GROUPNAME";

	ArrayList<ACGroup> myGroups = null;
	MyArrayAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_of_group);

		final ListView listview = (ListView) findViewById(R.id.listview);

		new Utils();
		myGroups = Utils.RestoreAllGroup(this);

		if (myGroups.size() == 0) {

			myGroups.add(new ACGroup("Beijing tour", "Let s visit Beijing !"));
			myGroups.get(0)
					.AddACItem(
							new ACItem("Muraille de chine",
									"Devenez un homme un vrais"));
			myGroups.get(0).saveMe(this);
			myGroups.add(new ACGroup("Marathon 2014",
					"what if you become an athlet next year ?"));
			myGroups.get(1).saveMe(this);

			myGroups.add(new ACGroup("In your bed. Or not",
					"Tell me what happened there..."));
			myGroups.get(2).saveMe(this);

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
}
