package com.ypy.reallifeachievement;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/*import android.support.v4.app.NavUtils;*/

public class GroupActivity extends NfcEnabledActivity {

	ACGroup myGroup = null;
	achievementAdaptater adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.GROUPNAME);

		myGroup = findGroup(message);

		setTitle(myGroup.getName());
		// Show the Up button in the action bar.
		setupActionBar();

		setNfcMessage(new Gson().toJson(myGroup));

		// Set the text view as the activity layout
	/*	TextView textView = (TextView) findViewById(R.id.achievement_list_title);
		textView.setTextSize(40);
		textView.setText(myGroup.getName());
*/
		adapter = new achievementAdaptater(this, myGroup.getAcs());
		final ListView listview = (ListView) findViewById(R.id.achievement_list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ACItem achievement = adapter.getItem(position);
				achievement.toggleDone();
				((CheckBox) ((ViewGroup) view).getChildAt(1))
						.setChecked(achievement.getDone());
				myGroup.saveMe(GroupActivity.this);
				setNfcMessage(new Gson().toJson(myGroup));

			}
		});
		
		listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					final int position, long id) {
				new AlertDialog.Builder(view.getContext())
						.setMessage(
								"Do you want to delete this Achievement ?")
						.setPositiveButton("Sure!",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										myGroup.getAcs().remove(position);
										myGroup.saveMe(GroupActivity.this);
										GroupActivity.this.adapter
												.notifyDataSetChanged();
										setNfcMessage(new Gson().toJson(myGroup));

									}

								}).setNegativeButton("Oups... nop.", null)
						.show();
				return true;

			}
		});

	}

	private ACGroup findGroup(String intentMessage) {
		ACGroup groupe = new ACGroup(intentMessage, "");
		groupe.restoreMe(this);
		return groupe;
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group, menu);
		return true;
	}
	
	public void addAchievement() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage("Let's give a cute name:");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString();
				ACItem achievement = new ACItem(value, "");
				myGroup.AddACItem(achievement);
				/*myGroup.updateHash();*/
				myGroup.saveMe(GroupActivity.this);
				GroupActivity.this.adapter.notifyDataSetChanged();
				setNfcMessage(new Gson().toJson(myGroup));

				return;
			}
		});

		alert.setNegativeButton("Cancel", null);
		alert.show();

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_compose_achievement:
			addAchievement();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case android.R.id.home: // This ID represents the
	 * Home or Up button. In the case of this // activity, the Up button is
	 * shown. Use NavUtils to allow users // to navigate up one level in the
	 * application structure. For // more details, see the Navigation pattern on
	 * Android Design: // //
	 * http://developer.android.com/design/patterns/navigation.html#up-vs-back
	 * // NavUtils.navigateUpFromSameTask(this); return true; } return
	 * super.onOptionsItemSelected(item); }
	 */

}
