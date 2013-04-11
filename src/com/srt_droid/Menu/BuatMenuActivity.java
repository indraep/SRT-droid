package com.srt_droid.Menu;

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

import com.srt_droid.R;
import com.srt_droid.Utilities;
import com.srt_droid.Controller.MenuController;

public class BuatMenuActivity extends Activity {
	
	MenuController menuController = new MenuController();
	
	EditText nama, harga, hargaModal, deskripsi, kategoriBaru;
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
		hargaModal = (EditText) findViewById(R.id.hargaModal);
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
	
	/**
	 * Memvalidasi apakah form terisi lengkap.
	 * 
	 * @return true jika form terisi lengkap, false jika tidak
	 * 
	 */
	private boolean validate() {
		return nama.getText().length() > 0 && hargaModal.getText().length() > 0 && harga.getText().length() > 0 
				&& deskripsi.getText().length() > 0;
	}
	
	public void buat(View v) {
		if (!validate()) {
			Toast.makeText(getApplicationContext(), "Silahkan lengkapi form di atas!", Toast.LENGTH_LONG).show();
			return;
		}
		
		// use available category
		if (kategoriChoice == 1) {
			if (listKategori.length == 0) {
				Toast.makeText(getApplicationContext(), "Harap membuat kategori menu yang baru!", Toast.LENGTH_LONG).show();
				return;
			}
			
			if (menuController.buat(listKategori[kategori.getSelectedItemPosition()].getId(), nama.getText().toString(),
					Integer.parseInt(hargaModal.getText().toString()), Integer.parseInt(harga.getText().toString()),
					deskripsi.getText().toString())) {
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
			
			if (kategoriBaru.getText().length() == 0) {
				Toast.makeText(getApplicationContext(), "Harap isi nama kategori menu yang baru!", Toast.LENGTH_LONG).show();
				return;
			}
			
			if (menuController.buat(kategoriBaru.getText().toString(), nama.getText().toString(),
					Integer.parseInt(hargaModal.getText().toString()), Integer.parseInt(harga.getText().toString()),
					deskripsi.getText().toString())) {
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
