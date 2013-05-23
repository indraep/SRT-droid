package srt_droid;

import com.example.srt_droid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class LihatSejarahPenjualanActivity extends Activity {

	DatePicker tanggalAwal, tanggalAkhir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lihat_sejarah_penjualan);
	
		init();
	}
	
	
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), PemilikRestoranActivity.class));
		finish();
	}
	
	void init() {
		tanggalAwal = (DatePicker) findViewById(R.id.tanggalAwal);
		tanggalAkhir = (DatePicker) findViewById(R.id.tanggalAkhir);
	}
	
	public void lihat(View v) {
		if (isValid()) {
			startActivity(new Intent(getApplicationContext(), ResultSejarahPenjualanActivity.class));
		}
		else {
			Toast.makeText(getApplicationContext(), "Periksa kembali tanggal awal dan tanggal akhir yang anda masukkan!", Toast.LENGTH_LONG).show();
		}
	}
	
	private boolean isValid() {
		int dd1, mm1, yyyy1, dd2, mm2, yyyy2;
		dd1 = tanggalAwal.getDayOfMonth();
		dd2 = tanggalAkhir.getDayOfMonth();
		mm1 = tanggalAwal.getMonth();
		mm2 = tanggalAkhir.getMonth();
		yyyy1 = tanggalAwal.getYear();
		yyyy2 = tanggalAkhir.getYear();
		
		if (yyyy1 > yyyy2)
			return false;
		else if (yyyy1 == yyyy2) {
			if (mm1 > mm2)
				return false;
			else if (mm1 == mm2 && dd1 > dd2)
				return false;
		}
		
		Utilities.date1[0] = dd1; Utilities.date1[1] = mm1 + 1; Utilities.date1[2] = yyyy1;
		Utilities.date2[0] = dd2; Utilities.date2[1] = mm2 + 1; Utilities.date2[2] = yyyy2;
		
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lihat_sejarah_penjualan, menu);
		return true;
	}

}
