package com.ypy.reallifeachievement;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
/*import android.support.v4.app.NavUtils;*/

public class GroupActivity extends NfcEnabledActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.GROUPNAME);
		
		ACGroup  groupe = findGroup(message);

		setTitle(groupe.getName());

		// Show the Up button in the action bar.
		setupActionBar();
		
		
		  // Create the text view
	   
		
		setNfcMessage(new Gson().toJson(groupe));
		
	    // Set the text view as the activity layout
		TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(groupe.getName());

	    setContentView(textView);
	}

	private ACGroup findGroup(String intentMessage) {
		return new Gson().fromJson(intentMessage, ACGroup.class);
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

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

}
