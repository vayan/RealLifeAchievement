package com.ypy.reallifeachievement;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ACGroup acg = new ACGroup("test grp", "this is the first test grp");
		 Log.i("DEBUGME", acg.getDescr());
		 
		 acg.AddACItem(new ACItem("first item", "descr first item"));
		 
		 Log.i("DEBUGME", "meuuuu");
		 
		 acg.debugAfflist();
		 
		 acg.AddACItem(new ACItem("first item", "descr first item"));
		 acg.AddACItem(new ACItem("first item", "descr first item"));
		 acg.AddACItem(new ACItem("first item", "descr first item"));
		 acg.AddACItem(new ACItem("first item", "descr first item"));
		 acg.AddACItem(new ACItem("first item", "descr first item"));
		 
		 acg.AddACItem(new ACItem("second item", "descr second item"));
		 
		 acg.debugAfflist();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
