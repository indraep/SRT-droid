package com.example.srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.srt_droid.PelayanActivity;
import com.example.srt_droid.PemilikRestoranActivity;
import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;
import com.example.srt_droid.Controller.PesananController;
import com.example.srt_droid.Menu.DeskripsiMenuActivity;
import com.example.srt_droid.Menu.MenuResto;

public class UbahPesananActivity extends Activity {

	EditText noMeja;

	PesananController pesananController = new PesananController();
	ArrayList <Integer> jumlah = new ArrayList<Integer>();
	ArrayList<MenuResto> m_data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_pesanan);

		init();
	}

	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
		finish();
	}

	private void init() {
		noMeja = (EditText) findViewById(R.id.noMeja);
		noMeja.setText(Utilities.oldPesanan.getNoMeja() + "");

		m_data = pesananController.getListOfDetailPesanan(Utilities.oldPesanan);
		for (int i = 0; i < m_data.size(); i++)
			jumlah.add(m_data.get(i).getJumlah());

		LinearLayout listMenuLayout = (LinearLayout)findViewById(R.id.listMenuLayout);
		String prevKategori = "";
		for (int i = 0; i < m_data.size(); i++) {
			if (!prevKategori.equals(m_data.get(i).getNamaKategori())) {
				prevKategori = "" + m_data.get(i).getNamaKategori();
				TextView tv = new TextView(this);
				tv.setText(prevKategori);
				listMenuLayout.addView(tv);
			}
			
			final int pos = i;

			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_buat_pesanan_row, null);

			layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Utilities.menu = m_data.get(pos);
					startActivity(new Intent(getApplicationContext(), DeskripsiMenuActivity.class));
				}
			});

			LinearLayout ll = (LinearLayout)layout.getChildAt(0);
			ll = (LinearLayout)ll.getChildAt(0);

			((TextView)ll.getChildAt(0)).setText("Nama: " + m_data.get(i).getNama());
			((TextView)ll.getChildAt(1)).setText("Harga: " + m_data.get(i).getHarga());

			ll = (LinearLayout)layout.getChildAt(0);
			ll = (LinearLayout)ll.getChildAt(1);

			EditText et = (EditText)ll.getChildAt(0);

			et.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// TODO Auto-generated method stub
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub
				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					jumlah.set(pos, Integer.parseInt(s.toString().length() > 0 ? s.toString() : "0"));
				}
			});
			et.setText("" + jumlah.get(pos));

			listMenuLayout.addView(layout);
		}
	}

	public void ubahPesanan(View v) {
		if (noMeja.getText().length() == 0) {
			Toast.makeText(getApplicationContext(), "Masukkan nomor meja!", Toast.LENGTH_LONG).show();
			return;
		}

		ArrayList<MenuResto> pesanan = new ArrayList<MenuResto>();

		for (int i = 0; i < jumlah.size(); i++) {
			if (jumlah.get(i) > 0) {
				MenuResto temp = new MenuResto(m_data.get(i));
				temp.setJumlah(jumlah.get(i));
				pesanan.add(temp);
			}
		}

		if (pesanan.size() == 0) {
			Toast.makeText(getApplicationContext(), "Pastikan pesanan yang anda ubah tidak kosong!", Toast.LENGTH_LONG).show();
		}
		else if (pesananController.ubah(pesanan, noMeja.getText().toString(), Utilities.oldPesanan)) {
			Toast.makeText(getApplicationContext(), "Pesanan berhasil diubah!", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
			finish();
		}
		else {
			Toast.makeText(getApplicationContext(), "Pesanan gagal diubah!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubah_pesanan, menu);
		return true;
	}

}
