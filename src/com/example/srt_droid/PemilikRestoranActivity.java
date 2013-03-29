package com.example.srt_droid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PemilikRestoranActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pemilik_restoran);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pemilik_restoran, menu);
		return true;
	}

}
