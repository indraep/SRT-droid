package com.example.srt_droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.srt_droid.Controller.AccountController;

public class LoginActivity extends Activity {
	Spinner roleSpinner;
	EditText username, password;

	AccountController accountController = new AccountController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);
		
		username = (EditText) findViewById(R.id.user_name);
		password = (EditText) findViewById(R.id.password);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_view, menu);
		return true;
	}

	public void login(View v) {
		Utilities.user = accountController.login(username.getText().toString(), password.getText().toString());
		// login successfully
		if (Utilities.user.getPeran() == 1) {
			Intent intent = new Intent(getApplicationContext(), PemilikRestoranActivity.class);
			startActivity(intent);
		}
		else if (Utilities.user.getPeran() == 2) {
			Intent intent = new Intent(getApplicationContext(), PelayanActivity.class);
			startActivity(intent);
		}
		else if (Utilities.user.getPeran() == 4) {
			Intent intent = new Intent(getApplicationContext(), KokiActivity.class);
			startActivity(intent);
		}
		else if (Utilities.user.getPeran() == 8) {
			Intent intent = new Intent(getApplicationContext(), KasirActivity.class);
			startActivity(intent);
		}
		else if (Utilities.user.getPeran() > 0) {
			Intent intent = new Intent(getApplicationContext(), PilihPeranActivity.class);
			startActivity(intent);
		}
		else {
			Toast.makeText(getBaseContext(), "Periksa kembali username dan password anda!", Toast.LENGTH_LONG).show();
			password.setText("");
		}
	}
}
