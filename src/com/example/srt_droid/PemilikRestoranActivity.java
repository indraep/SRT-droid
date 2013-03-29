package com.example.srt_droid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PemilikRestoranActivity extends Activity {
	TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pemilik_restoran);
		init();
	}
	
	void init() {
		title = (TextView)findViewById(R.id.title);
		title.setText("Pemilik Restoran, " + Utilities.user.getNama());
	}

	public void lihatSejarahPenjualan(View v) {
		
	}
	
	public void lihatDaftarMenu(View v) {
		
	}
	
	public void lihatDaftarAccount(View v) {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pemilik_restoran, menu);
		return true;
	}
}
