package com.srt_droid;

import com.srt_droid.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class LihatSejarahPenjualanActivity extends Activity {

	DatePicker tanggalAwal, tanggalAkhir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lihat_sejarah_penjualan);
	
		init();
	}
	
	void init() {
		tanggalAwal = (DatePicker) findViewById(R.id.tanggalAwal);
		tanggalAkhir = (DatePicker) findViewById(R.id.tanggalAkhir);
	}
	
	public void lihat(View v) {
		Toast.makeText(getApplicationContext(), tanggalAwal.getDayOfMonth() + "/" + tanggalAwal.getMonth() + "/" + tanggalAwal.getYear(), Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), tanggalAkhir.getDayOfMonth() + "/" + tanggalAkhir.getMonth() + "/" + tanggalAkhir.getYear(), Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lihat_sejarah_penjualan, menu);
		return true;
	}

}
