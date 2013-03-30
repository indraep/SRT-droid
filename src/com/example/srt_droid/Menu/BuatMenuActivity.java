package com.example.srt_droid.Menu;

import com.example.srt_droid.R;
import com.example.srt_droid.R.id;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class BuatMenuActivity extends Activity {
	
	EditText nama, harga, deskripsi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buat_menu);
	}
	
	void init() {
		nama = (EditText) findViewById(R.id.nama);
		harga = (EditText) findViewById(R.id.harga);
		deskripsi = (EditText) findViewById(R.id.deskripsi);
	}
	
	public void buat(View v) {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buat_menu, menu);
		return true;
	}

}
