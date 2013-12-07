package com.ypy.reallifeachievement;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends NfcEnabledActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Utils utils = new Utils();
		ACGroup group = new ACGroup("Success", "je suis un dieu");
		
		String test = utils.serialize(group);
		Log.i("Test", test);
		
		ACGroup newGroup = utils.deserialize(test);
		Log.i("Test", newGroup.getDescr());
		
		setNfcMessage(test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
