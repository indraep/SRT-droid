package com.example.srt_droid.Pesanan;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FormPembayaranActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_pembayaran);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_pembayaran, menu);
		return true;
	}

}
