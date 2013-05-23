package srt_droid;

import srt_droid.Account.ListAccountActivity;
import srt_droid.Account.UbahProfileActivity;
import srt_droid.Menu.ListMenuActivity;

import com.example.srt_droid.R;

import android.app.Activity;
import android.content.Intent;
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
		startActivity(new Intent(getApplicationContext(), LihatSejarahPenjualanActivity.class));
		finish();
	}
	
	public void lihatDaftarMenu(View v) {
		startActivity(new Intent(getApplicationContext(), ListMenuActivity.class));
		finish();
	}
	
	public void lihatDaftarAccount(View v) {
		startActivity(new Intent(getApplicationContext(), ListAccountActivity.class));
		finish();
	}
	
	public void ubahProfile(View v) {
		startActivity(new Intent(getApplicationContext(), UbahProfileActivity.class));
	}
	
	public void keluar(View v) {
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pemilik_restoran, menu);
		return true;
	}
}
