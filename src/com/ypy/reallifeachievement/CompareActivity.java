package com.ypy.reallifeachievement;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class CompareActivity extends Activity {

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
//		if (!groupMe.getAcs().isEmpty())
//		{
//			ArrayList<ArrayList<ACItem>> list = new ArrayList<ArrayList<ACItem>>();
//			list.add(groupMe.getAcs());
//			list.add(group.getAcs());
//			final CompareArrayAdapter adapter = new CompareArrayAdapter(this, list);
//			listView.setAdapter(adapter);
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compare, menu);
		return true;
	}

}
