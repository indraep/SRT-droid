package com.example.srt_droid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class BuatAccountActivity extends Activity {
	
	int peranId[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buat_account);
		init();
	}
	
	void init() {
		peranId = new int[]{R.id.pemilikRestoran, R.id.pelayan, R.id.koki, R.id.kasir};
	}

	public void buat(View v) {
		int peran = 0;
		for (int i = 0; i < 4; i++) {
			CheckBox cb = (CheckBox) findViewById(peranId[i]);
			if (cb.isChecked())
				peran += Math.pow(2, i);
		}
		
		Toast.makeText(this, "peran = " + peran, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buat_account, menu);
		return true;
	}

}
