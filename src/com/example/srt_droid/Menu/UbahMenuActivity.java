package com.example.srt_droid.Menu;

import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;
import com.example.srt_droid.R.id;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UbahMenuActivity extends Activity {
	
	EditText nama, harga, deskripsi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_menu);
		
		init();
	}

	void init() {
		nama = (EditText) findViewById(R.id.nama);
		nama.setText(Utilities.oldMenu.getNama());
		
		harga = (EditText) findViewById(R.id.harga);
		harga.setText("" + Utilities.oldMenu.getHarga());
		
		deskripsi = (EditText) findViewById(R.id.deskripsi);
		deskripsi.setText(Utilities.oldMenu.getDeskripsi());
	}
	
	public void ubah(View v) {
		Toast.makeText(getBaseContext(), "UBAH", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubah_menu, menu);
		return true;
	}

}
