package com.example.srt_droid.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;
import com.example.srt_droid.Controller.MenuController;

public class BuatMenuActivity extends Activity {
	
	MenuController menuController = new MenuController();
	
	EditText nama, harga, deskripsi, kategoriBaru;
	Spinner kategori;
	RadioGroup kategoriGroup;
	KategoriMenu [] listKategori;
	
	int kategoriChoice = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buat_menu);
		
		init();
	}
	
	void init() {		
		nama = (EditText) findViewById(R.id.nama);
		harga = (EditText) findViewById(R.id.harga);
		deskripsi = (EditText) findViewById(R.id.deskripsi);
		kategoriBaru = (EditText) findViewById(R.id.kategoriBaru);
		kategori = (Spinner) findViewById(R.id.kategori);
		kategoriGroup = (RadioGroup) findViewById(R.id.kategoriGroup);
		kategoriGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.kategoriLama) {
					kategoriChoice = 1;
				}
				else {
					kategoriChoice = 2;
				}
			}
		});

		((RadioButton) findViewById(R.id.kategoriLama)).setChecked(true);
		
		listKategori = menuController.getListOfKategori();
		ArrayAdapter<KategoriMenu> spinnerArrayAdapter = new ArrayAdapter<KategoriMenu>(this, android.R.layout.simple_spinner_item, listKategori);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
		kategori.setAdapter(spinnerArrayAdapter);
	}
	
	public void buat(View v) {
		// use available category
		if (kategoriChoice == 1) {
			if (menuController.buat(listKategori[kategori.getSelectedItemPosition()].getId(), nama.getText().toString(), 
					Integer.parseInt(harga.getText().toString()), deskripsi.getText().toString(), Utilities.user.getUsername())) {
				Toast.makeText(getApplicationContext(), "Menu berhasil dibuat!", Toast.LENGTH_LONG).show();
				startActivity(new Intent(getApplicationContext(), ListMenuActivity.class));
				finish();
			}
			else {
				Toast.makeText(getApplicationContext(), "GAGAL!", Toast.LENGTH_LONG).show();
			}
		}
		else {
			int len = listKategori.length;
			for (int i = 0; i < len; i++) {
				if ((kategoriBaru.getText().toString()).equals(listKategori[i].getNama())) {
					Toast.makeText(getApplicationContext(), "Nama kategori sudah tersedia sebelumnya!", Toast.LENGTH_LONG).show();
					return;
				}
			}
			
			if (menuController.buat(kategoriBaru.getText().toString(), nama.getText().toString(), 
					Integer.parseInt(harga.getText().toString()), deskripsi.getText().toString(), Utilities.user.getUsername())) {
				Toast.makeText(getApplicationContext(), "Menu berhasil dibuat!", Toast.LENGTH_LONG).show();
				startActivity(new Intent(getApplicationContext(), ListMenuActivity.class));
				finish();
			}
			else {
				Toast.makeText(getApplicationContext(), "GAGAL!", Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buat_menu, menu);
		return true;
	}

}
