package com.example.srt_droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class PilihPeranActivity extends Activity {
	String[] listPeran;

	Spinner peran;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pilih_peran);

		init();
	}

	void init() {
		listPeran = Utilities.user.getListOfPeran();

		peran = (Spinner)findViewById(R.id.peran);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPeran);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
		peran.setAdapter(spinnerArrayAdapter);
	}

	public void masuk(View v) {
		Toast.makeText(getBaseContext(), listPeran[peran.getSelectedItemPosition()], 1000).show();
		Intent intent;

		if (listPeran[peran.getSelectedItemPosition()].equals("Pemilik Restoran")) {
			intent = new Intent(getApplicationContext(), PemilikRestoranActivity.class);
		}
		else if (listPeran[peran.getSelectedItemPosition()].equals("Pelayan")) {
			intent = new Intent(getApplicationContext(), PelayanActivity.class);
		}
		else if (listPeran[peran.getSelectedItemPosition()].equals("Koki")) {
			intent = new Intent(getApplicationContext(), KokiActivity.class);
		}
		else {
			intent = new Intent(getApplicationContext(), KasirActivity.class);
		}

		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pilih_peran, menu);
		return true;
	}

}
