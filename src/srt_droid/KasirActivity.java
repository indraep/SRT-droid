package srt_droid;

import srt_droid.Account.ListAccountActivity;
import srt_droid.Pesanan.ListPesananActivity;
import srt_droid.Pesanan.ListPesananKasirActivity;

import com.example.srt_droid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class KasirActivity extends Activity {

	TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kasir);
		
		init();
	}

	void init() {
		title = (TextView)findViewById(R.id.title);
		title.setText("Kasir, " + Utilities.user.getNama());
	}
	
	public void lihatPesanan(View v) {
		startActivity(new Intent(getApplicationContext(), ListPesananKasirActivity.class));
	}
	
	public void keluar(View v) {
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kasir, menu);
		return true;
	}

}
