package com.example.srt_droid.Account;

import com.example.srt_droid.R;
import com.example.srt_droid.Controller.AccountController;
import com.example.srt_droid.R.id;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class BuatAccountActivity extends Activity {
	
	EditText nama, username, password, konfirmasiPassword, alamat;
	
	AccountController accountController = new AccountController();
	
	int peranId[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buat_account);
		init();
	}
	
	void init() {
		peranId = new int[]{R.id.pemilikRestoran, R.id.pelayan, R.id.koki, R.id.kasir};
		nama = (EditText)findViewById(R.id.nama);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		konfirmasiPassword = (EditText)findViewById(R.id.konfirmasiPassword);
		alamat = (EditText)findViewById(R.id.alamat);
	}

	public void buat(View v) {
		int peran = 0;
		for (int i = 0; i < 4; i++) {
			CheckBox cb = (CheckBox) findViewById(peranId[i]);
			if (cb.isChecked())
				peran += Math.pow(2, i);
		}
		
		if (nama.getText().length() == 0 || username.getText().length() == 0 || password.getText().length() == 0
				|| konfirmasiPassword.getText().length() == 0 || alamat.getText().length() == 0) {
			Toast.makeText(this, "Harap lengkapi form!", Toast.LENGTH_LONG).show();
			return;
		}
		else if (!((password.getText().toString()).equals(konfirmasiPassword.getText().toString()))) {
			Toast.makeText(this, "Cek kembali konfirmasi password anda!", Toast.LENGTH_LONG).show();
			return;
		}
		else if (peran == 0) {
			Toast.makeText(this, "Harap pilih sekurangnya satu peran!", Toast.LENGTH_LONG).show();
			return;
		}
		
		String status = accountController.buat(nama.getText().toString(), username.getText().toString(), 
				password.getText().toString(), alamat.getText().toString(), peran);
		if (status.charAt(0) == 'U' || status.charAt(0) == 'G') {
			Toast.makeText(this, status, Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this, status, Toast.LENGTH_LONG).show();
			startActivity(new Intent(this, ListAccountActivity.class));
			finish();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buat_account, menu);
		return true;
	}

}
