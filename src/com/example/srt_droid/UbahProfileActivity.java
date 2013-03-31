package com.example.srt_droid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class UbahProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_profile);
	}
	
	public void ubah(View v) {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubah_profile, menu);
		return true;
	}

}
