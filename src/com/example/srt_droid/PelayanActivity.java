package com.example.srt_droid;

import com.example.srt_droid.Account.UbahProfileActivity;
import com.example.srt_droid.Pesanan.BuatPesananActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PelayanActivity extends Activity {

	TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pelayan);
		init();
	}
	
	void init() {
		title = (TextView)findViewById(R.id.title);
		title.setText("Pelayan, " + Utilities.user.getNama());
	}
	
	public void buatPesanan(View v) {
		startActivity(new Intent(getApplicationContext(), BuatPesananActivity.class));
	}
	
	public void lihatPesanan(View v) {
		Toast.makeText(getBaseContext(), "Lihat Pesanan", Toast.LENGTH_LONG).show();
	}
	
	public void ubahProfile(View v) {
		startActivity(new Intent(getApplicationContext(), UbahProfileActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pelayan, menu);
		return true;
	}
}
