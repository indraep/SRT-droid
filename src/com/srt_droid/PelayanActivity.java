package com.srt_droid;

import com.srt_droid.R;
import com.srt_droid.Account.UbahProfileActivity;
import com.srt_droid.Pesanan.BuatPesananActivity;
import com.srt_droid.Pesanan.ListPesananActivity;

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
		startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
	}
	
	public void ubahProfile(View v) {
		startActivity(new Intent(getApplicationContext(), UbahProfileActivity.class));
	}
	
	public void keluar(View v) {
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pelayan, menu);
		return true;
	}
}