package srt_droid.Account;

import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import srt_droid.Controller.AccountController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.srt_droid.R;
import com.example.srt_droid.R.id;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

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
	
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), ListAccountActivity.class));
		finish();
	}
	
	void init() {
		peranId = new int[]{R.id.pemilikRestoran, R.id.pelayan, R.id.koki, R.id.kasir};
		
		if (Utilities.oldUser.getUsername().equals(Utilities.user.getUsername())) {
			CheckBox pRestoran = (CheckBox)findViewById(R.id.pemilikRestoran);
			pRestoran.setClickable(false);
		}
		
		nama = (EditText) findViewById(R.id.nama);
		nama.setText(Utilities.oldUser.getNama());
		
		username = (EditText) findViewById(R.id.username);
		username.setText(Utilities.oldUser.getUsername());
		
		alamat = (EditText) findViewById(R.id.alamat);
		alamat.setText(Utilities.oldUser.getAlamat());
		
		passwordBaru = (EditText) findViewById(R.id.passwordBaru);
		konfirmasiPassword = (EditText) findViewById(R.id.konfirmasiPassword);
		
		for (int i = 0; i < 4; i++) {
			if ((Utilities.oldUser.getPeran() & (1 << i)) > 0) {
				CheckBox cb = (CheckBox) findViewById(peranId[i]);
				cb.setChecked(true);
			}
		}
	}
	
	public void ubah(View v) {
		int peran = 0;
		for (int i = 0; i < 4; i++) {
			CheckBox cb = (CheckBox) findViewById(peranId[i]);
			if (cb.isChecked())
				peran += Math.pow(2, i);
		}
		
		if (nama.getText().length() == 0 || username.getText().length() == 0 || alamat.getText().length() == 0) {
			Toast.makeText(this, "Harap lengkapi form!", Toast.LENGTH_LONG).show();
			return;
		}
		else if (!((passwordBaru.getText().toString()).equals(konfirmasiPassword.getText().toString()))) {
			Toast.makeText(this, "Cek kembali konfirmasi password anda!", Toast.LENGTH_LONG).show();
			return;
		}
		else if (peran == 0) {
			Toast.makeText(this, "Harap pilih sekurangnya satu peran!", Toast.LENGTH_LONG).show();
			return;
		}
		
		String status = accountController.ubah(Utilities.oldUser, nama.getText().toString(), username.getText().toString(), 
				passwordBaru.getText().toString(), alamat.getText().toString(), peran);
		if (status.charAt(0) == 'U' || status.charAt(0) == 'G') {
			Toast.makeText(this, status, Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this, status, Toast.LENGTH_LONG).show();
			startActivity(new Intent(this, ListAccountActivity.class));
			
			if (Utilities.oldUser.getUsername().equals(Utilities.user.getUsername()))
				Utilities.user = new User(nama.getText().toString(), username.getText().toString(), passwordBaru.getText().toString(), 
						alamat.getText().toString(), peran);
			
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubah_account, menu);
		return true;
	}

}
