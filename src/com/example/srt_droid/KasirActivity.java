package com.example.srt_droid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class KasirActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kasir);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kasir, menu);
		return true;
	}

}
