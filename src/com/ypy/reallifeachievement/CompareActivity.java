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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CompareActivity extends NfcEnabledActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare);

		TextView textView = (TextView) findViewById(R.id.group_title);
		
		Intent intent = getIntent();
		ACGroup group = new Gson().fromJson(intent.getExtras().getString("message"), ACGroup.class);
		textView.setText(group.getName());
		ListView listView = (ListView)findViewById(R.id.achievement_list);
		
		ACGroup groupMe = new ACGroup(group.getName(), "");
		groupMe.restoreMe(this);
		if (!groupMe.getAcs().isEmpty())
		{
			setNfcMessage(intent.getStringExtra("message"));
			ArrayList<ACCompare> list = new ArrayList<ACCompare>();
			for (int i = 0; i < groupMe.getAcs().size(); i++)
			{
				list.add(new ACCompare(groupMe.getAcs().get(i), group.getAcs().get(i)));
			}
			
			//ArrayList<ArrayList<ACItem>> list = new ArrayList<ArrayList<ACItem>>();
			//list.add(groupMe.getAcs());
			//list.add(group.getAcs());
			final CompareArrayAdapter adapter = new CompareArrayAdapter(this, list);
			listView.setAdapter(adapter);
		}
		else
			addNewList(group);
	}

	private void addNewList(final ACGroup group)
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage("You don't have this list. Add it to your list?");

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				group.saveMe(getApplicationContext());
				CompareActivity.this.finish();
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				CompareActivity.this.finish();
			}
		});
		alert.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compare, menu);
		return true;
	}

}
