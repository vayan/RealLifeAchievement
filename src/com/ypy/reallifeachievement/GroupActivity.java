package com.ypy.reallifeachievement;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
		TextView textView = (TextView) findViewById(R.id.achievement_list_title);
		textView.setTextSize(40);
		textView.setText(myGroup.getName());

		adapter = new achievementAdaptater(this, myGroup.getAcs());
		final ListView listview = (ListView) findViewById(R.id.achievement_list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("SEXE", "Touch triggererd");
				ACItem achievement = adapter.getItem(position);
				achievement.toggleDone();
				((CheckBox) ((ViewGroup) view).getChildAt(1))
						.setChecked(achievement.getDone());
				myGroup.saveMe(GroupActivity.this);
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
