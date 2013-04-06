package com.example.srt_droid.Menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;

public class DeskripsiMenuActivity extends Activity {

	TextView nama, harga, deskripsi;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deskripsi_menu);
	
		init();
	}
	
	private void init() {
		nama = (TextView) findViewById(R.id.namaMenu);
		nama.setText(Utilities.menu.getNama());
		
		harga = (TextView) findViewById(R.id.hargaMenu);
		harga.setText("Rp " + Utilities.menu.getHarga());
		
		deskripsi = (TextView) findViewById(R.id.deskripsiMenu);
		deskripsi.setText(Utilities.menu.getDeskripsi());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deskripsi_menu, menu);
		return true;
	}

}
