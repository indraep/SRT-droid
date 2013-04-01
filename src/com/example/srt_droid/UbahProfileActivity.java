package com.example.srt_droid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class UbahProfileActivity extends Activity {

	EditText nama, username, passwordBaru, konfirmasiPassword, alamat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_profile);
		
		init();
	}
	
	void init() {
		nama = (EditText) findViewById(R.id.nama);
		nama.setText(Utilities.user.getNama());
		
		username = (EditText) findViewById(R.id.username);
		username.setText(Utilities.user.getUsername());
		
		passwordBaru = (EditText) findViewById(R.id.passwordBaru);
		konfirmasiPassword = (EditText) findViewById(R.id.konfirmasiPassword);
		
		alamat = (EditText) findViewById(R.id.alamat);
		Log.e("LOG", "alamat = " + Utilities.user.getAlamat());
		alamat.setText(Utilities.user.getAlamat());
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
