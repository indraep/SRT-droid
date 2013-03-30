package com.example.srt_droid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.srt_droid.Controller.AccountController;

public class UbahAccountActivity extends Activity {

	EditText nama, username, passwordBaru, konfirmasiPassword, alamat;
	int peranId[];
	
	AccountController accountController = new AccountController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_account);
	
		init();
	}
	
	void init() {
		peranId = new int[]{R.id.pemilikRestoran, R.id.pelayan, R.id.koki, R.id.kasir};
		
		nama = (EditText) findViewById(R.id.nama);
		nama.setText(Utilities.oldUser.getNama());
		
		username = (EditText) findViewById(R.id.username);
		username.setText(Utilities.oldUser.getUsername());
		
		alamat = (EditText) findViewById(R.id.alamat);
		alamat.setText(Utilities.oldUser.getAlamat());
		
		for (int i = 0; i < 4; i++) {
			if ((Utilities.oldUser.getPeran() & (1 << i)) > 0) {
				CheckBox cb = (CheckBox) findViewById(peranId[i]);
				cb.setChecked(true);
			}
		}
	}
	
	public void ubah(View v) {
		Toast.makeText(getApplicationContext(), "Ubah", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubah_account, menu);
		return true;
	}

}
