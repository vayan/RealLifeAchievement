package com.ypy.reallifeachievement;

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
		
		Intent intent = getIntent();
		String string = intent.getExtras().getString("message");
		
		TextView textView = (TextView) findViewById(R.id.group_title);
		textView.setText(string);
		
//		Intent intent = getIntent();
//		ACGroup group = new Gson().fromJson(intent.getExtras().getString("message"), ACGroup.class);
//		
//		ListView listView = (ListView)findViewById(R.id.achievement_list);
//		final CompareArrayAdapter adapter = new CompareArrayAdapter(this, group.getAcs());
//		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compare, menu);
		return true;
	}

}
