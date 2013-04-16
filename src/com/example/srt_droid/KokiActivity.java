package com.example.srt_droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class KokiActivity extends Activity {
	
	TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_koki);

		init();
	}
	
	void init() {
		title = (TextView)findViewById(R.id.title);
		title.setText("Koki, " + Utilities.user.getNama());
	}
	
	public void lihatPesanan(View v) {
		
	}
	
	public void keluar(View v) {
		startActivity(new Intent(getApplicationContext(), LoginActivity.class));
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.koki, menu);
		return true;
	}
}
