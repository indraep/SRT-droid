package com.example.srt_droid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.srt_droid.Controller.AccountController;
import com.example.srt_droid.Pesanan.BuatPesananActivity;

public class LoginActivity extends Activity {
	Spinner roleSpinner;
	EditText username, password;

	AccountController accountController = new AccountController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);

		//startActivity(new Intent(this, BuatPesananActivity.class));
		
		username = (EditText) findViewById(R.id.user_name);
		password = (EditText) findViewById(R.id.password);
	}

	private boolean validate() {
		return username.getText().length() > 0 && password.getText().length() > 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_view, menu);
		return true;
	}

	public void login(View v) {
		if (!validate()) {
			Toast.makeText(getApplicationContext(), "Silahkan lengkapi username dan password anda!", Toast.LENGTH_LONG).show();
			return;
		}
		
		new LoginAsync(getApplicationContext()).execute();
	}
	
	public class LoginAsync extends AsyncTask<String, Integer, String> {
		private ProgressDialog Dialog = new ProgressDialog(LoginActivity.this);
		private Context mContext;

        public LoginAsync(Context context) {
            mContext = context;
        }
		
		@Override
		protected void onPreExecute() {
			Dialog.setMessage("Verifikasi username dan password");  
            Dialog.setTitle("Login...");
            Dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			Utilities.user = accountController.login(username.getText().toString(), password.getText().toString());
			publishProgress();
			return "";
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Dialog.dismiss();
			
			if (Utilities.user.getPeran() == 1) {
				Intent intent = new Intent(LoginActivity.this, PemilikRestoranActivity.class);
				startActivity(intent);
			}
			else if (Utilities.user.getPeran() == 2) {
				Intent intent = new Intent(LoginActivity.this, PelayanActivity.class);
				startActivity(intent);
			}
			else if (Utilities.user.getPeran() == 4) {
				Intent intent = new Intent(LoginActivity.this, KokiActivity.class);
				startActivity(intent);
			}
			else if (Utilities.user.getPeran() == 8) {
				Intent intent = new Intent(LoginActivity.this, KasirActivity.class);
				startActivity(intent);
			}
			else if (Utilities.user.getPeran() > 0) {
				Intent intent = new Intent(LoginActivity.this, PilihPeranActivity.class);
				startActivity(intent);
			}
			else {
				password.setText("");
				Toast.makeText(LoginActivity.this, "Invalid username atau password!", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected void onPostExecute(String result) {
		}

	}
	
	public void onBackPressed() {
	}
}
