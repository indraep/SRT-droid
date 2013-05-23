package srt_droid.Menu;

import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import srt_droid.Controller.MenuController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.srt_droid.R;

public class UbahMenuActivity extends Activity {
	
	MenuController menuController = new MenuController();
	
	EditText nama, harga, hargaModal, deskripsi, kategoriBaru;
	RadioGroup kategoriGroup;
	RadioButton kategoriLama, buatKategori;
	Spinner kategori;
	
	KategoriMenu [] listKategori;
	
	int kategoriChoice = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_menu);
		
		init();
	}
	
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), ListMenuActivity.class));
		finish();
	}

	void init() {
		nama = (EditText) findViewById(R.id.nama);
		nama.setText(Utilities.oldMenu.getNama());
		
		harga = (EditText) findViewById(R.id.harga);
		harga.setText("" + Utilities.oldMenu.getHarga());
		
		hargaModal = (EditText) findViewById(R.id.hargaModal);
		hargaModal.setText("" + Utilities.oldMenu.getHargaModal());
		
		deskripsi = (EditText) findViewById(R.id.deskripsi);
		deskripsi.setText(Utilities.oldMenu.getDeskripsi());
	
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
		
		kategoriLama = (RadioButton) findViewById(R.id.kategoriLama);
		buatKategori = (RadioButton) findViewById(R.id.buatKategori);
		kategoriBaru = (EditText) findViewById(R.id.kategoriBaru);
		
		kategoriLama.setChecked(true);
		
		kategori = (Spinner) findViewById(R.id.kategori);
		listKategori = menuController.getListOfKategori();
		ArrayAdapter<KategoriMenu> spinnerArrayAdapter = new ArrayAdapter<KategoriMenu>(this, android.R.layout.simple_spinner_item, listKategori);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
		kategori.setAdapter(spinnerArrayAdapter);
		
		int cnt = 0;
		for (int i = 0; i < listKategori.length; i++) {
			if (listKategori[i].getId() == Utilities.oldMenu.getIdKategori()) {
				cnt = i;
				break;
			}
		}
		kategori.setSelection(cnt);
	}
	
	private boolean validate() {
		return nama.getText().length() > 0 && hargaModal.getText().length() > 0 && harga.getText().length() > 0 
				&& deskripsi.getText().length() > 0;
	}
	
	public void ubah(View v) {
		if (!validate()) {
			Toast.makeText(getApplicationContext(), "Silahkan lengkapi form di atas!", Toast.LENGTH_LONG).show();
			return;
		}
		
		// use available category
		if (kategoriChoice == 1) {
			if (menuController.ubah(listKategori[kategori.getSelectedItemPosition()].getId(), nama.getText().toString(),
					Integer.parseInt(hargaModal.getText().toString()), Integer.parseInt(harga.getText().toString()),
					deskripsi.getText().toString(), 
					Utilities.oldMenu)) {
				Toast.makeText(getApplicationContext(), "Menu berhasil diubah!", Toast.LENGTH_LONG).show();
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
			
			if (menuController.ubah(kategoriBaru.getText().toString(), nama.getText().toString(),
					Integer.parseInt(hargaModal.getText().toString()), Integer.parseInt(harga.getText().toString()),
					deskripsi.getText().toString(), 
					Utilities.oldMenu)) {
				Toast.makeText(getApplicationContext(), "Menu berhasil diubah!", Toast.LENGTH_LONG).show();
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
		getMenuInflater().inflate(R.menu.ubah_menu, menu);
		return true;
	}

}
