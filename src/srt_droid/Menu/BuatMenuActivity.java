package srt_droid.Menu;

import java.io.File;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import srt_droid.Controller.MenuController;
import android.app.Activity;
import android.app.Dialog;
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
import ar.com.daidalos.afiledialog.FileChooserActivity;
import ar.com.daidalos.afiledialog.FileChooserDialog;

import com.example.srt_droid.R;

public class BuatMenuActivity extends Activity {
	
	MenuController menuController = new MenuController();
	
	EditText nama, harga, hargaModal, deskripsi, kategoriBaru, imagePath;
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
		imagePath = (EditText) findViewById(R.id.pathImage);
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
	
	public void openFileChooser(View v) {
		FileChooserDialog dialog = new FileChooserDialog(BuatMenuActivity.this);

		// Assign listener for the select event.
		dialog.addListener(BuatMenuActivity.this.onFileSelectedListener);

		// Define the filter for select images.
		dialog.setFilter(".*jpg|.*png|.*gif|.*JPG|.*PNG|.*GIF");
		dialog.setShowOnlySelectable(false);

		// Show the dialog.
		dialog.show();
	}
	

	private FileChooserDialog.OnFileSelectedListener onFileSelectedListener = new FileChooserDialog.OnFileSelectedListener() {
		public void onFileSelected(Dialog source, File file) {
			source.hide();
			imagePath.setText(file.getPath());
			Toast toast = Toast.makeText(getApplicationContext(), "File selected: " + file.getPath(), Toast.LENGTH_LONG);
			toast.show();
		}
		public void onFileSelected(Dialog source, File folder, String name) {
			source.hide();
			Toast toast = Toast.makeText(getApplicationContext(), "File created: " + folder.getName() + "/" + name, Toast.LENGTH_LONG);
			toast.show();
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			boolean fileCreated = false;
			String filePath = "";

			Bundle bundle = data.getExtras();
			if(bundle != null)
			{
				if(bundle.containsKey(FileChooserActivity.OUTPUT_NEW_FILE_NAME)) {
					fileCreated = true;
					File folder = (File) bundle.get(FileChooserActivity.OUTPUT_FILE_OBJECT);
					String name = bundle.getString(FileChooserActivity.OUTPUT_NEW_FILE_NAME);
					filePath = folder.getAbsolutePath() + "/" + name;
				} else {
					fileCreated = false;
					File file = (File) bundle.get(FileChooserActivity.OUTPUT_FILE_OBJECT);
					filePath = file.getAbsolutePath();
				}
			}

			String message = fileCreated? "File created" : "File opened";
			message += ": " + filePath;
			Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
			toast.show();
		}
	}
	
	private boolean validate() {
		return nama.getText().length() > 0 && hargaModal.getText().length() > 0 && harga.getText().length() > 0 
				&& deskripsi.getText().length() > 0 && imagePath.getText().length() > 0;
	}
	
	public void buat(View v) {
		if (!validate()) {
			Toast.makeText(getApplicationContext(), "Silahkan lengkapi form di atas!", Toast.LENGTH_LONG).show();
			return;
		}
		
		String path = imagePath.getText().toString();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		String imageName = dateFormat.format(new Date()) + path.substring(path.lastIndexOf('.')); ;
		
		// use available category
		if (kategoriChoice == 1) {
			if (listKategori.length == 0) {
				Toast.makeText(getApplicationContext(), "Harap membuat kategori menu yang baru!", Toast.LENGTH_LONG).show();
				return;
			}
			
			if (menuController.buat(listKategori[kategori.getSelectedItemPosition()].getId(), nama.getText().toString(),
					Integer.parseInt(hargaModal.getText().toString()), Integer.parseInt(harga.getText().toString()),
					deskripsi.getText().toString(), imageName)) {
				
				menuController.upload(path, imageName);
				
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
					deskripsi.getText().toString(), imageName)) {
				
				menuController.upload(path, imageName);
				
				Toast.makeText(getApplicationContext(), "Menu berhasil dibuat!", Toast.LENGTH_LONG).show();
				startActivity(new Intent(getApplicationContext(), ListMenuActivity.class));
				finish();
			}
			else {
				Toast.makeText(getApplicationContext(), "GAGAL!", Toast.LENGTH_LONG).show();
			}
		}
	}

	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), ListMenuActivity.class));
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buat_menu, menu);
		return true;
	}

}
