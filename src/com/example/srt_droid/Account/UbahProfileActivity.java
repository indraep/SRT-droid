package com.example.srt_droid.Account;

import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;
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
import android.widget.EditText;
import android.widget.Toast;

public class UbahProfileActivity extends Activity {

	AccountController accountController = new AccountController();
	EditText nama, username, passwordBaru, konfirmasiPassword, alamat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_profile);
		
		init();
	}
	
	void init() {
		nama = (EditText) findViewById(R.id.nama);
		nama.setText(Utilities.user.getNama());
		
		username = (EditText) findViewById(R.id.username);
		username.setText(Utilities.user.getUsername());
		
		passwordBaru = (EditText) findViewById(R.id.passwordBaru);
		konfirmasiPassword = (EditText) findViewById(R.id.konfirmasiPassword);
		
		alamat = (EditText) findViewById(R.id.alamat);
		alamat.setText(Utilities.user.getAlamat());
	}
	
	private boolean validate() {
		return nama.getText().length() > 0 && username.getText().length() > 0 && alamat.getText().length() > 0;
	}
	
	public void ubah(View v) {
		if (!validate()) {
			Toast.makeText(getApplicationContext(), "Harap lengkapi seluruh form!", Toast.LENGTH_LONG).show();
			return;
		}
		else if (!(passwordBaru.getText().toString()).equals(konfirmasiPassword.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Harap periksa kembali konfirmasi password anda!", Toast.LENGTH_LONG).show();
			return;
		}
		
		String status = accountController.ubah(Utilities.user, nama.getText().toString(), username.getText().toString(), 
				passwordBaru.getText().toString(), alamat.getText().toString(), Utilities.user.getPeran());
		if (status.charAt(0) == 'U' || status.charAt(0) == 'G') {
			Toast.makeText(this, status, Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this, "Profile berhasil diubah!", Toast.LENGTH_LONG).show();
			startActivity(new Intent(this, ListAccountActivity.class));
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubah_profile, menu);
		return true;
	}

}
