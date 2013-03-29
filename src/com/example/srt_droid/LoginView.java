package com.example.srt_droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.srt_droid.Controller.AccountController;

public class LoginView extends Activity {
	Spinner roleSpinner;
	EditText userName, password;

	AccountController accountController = new AccountController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);

		userName = (EditText) findViewById(R.id.user_name);
		password = (EditText) findViewById(R.id.password);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_view, menu);
		return true;
	}

	public void login(View v) {
		Utilities.user = accountController.login(userName.getText().toString(), password.getText().toString());
		if (Utilities.user.getPeran() > 0) {
			Intent intent = new Intent(getApplicationContext(), PilihPeranActivity.class);
			startActivity(intent);
		}
	}
}
