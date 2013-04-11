package com.srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.srt_droid.R;
import com.srt_droid.Utilities;
import com.srt_droid.Controller.MenuController;
import com.srt_droid.Controller.PesananController;
import com.srt_droid.Menu.DeskripsiMenuActivity;
import com.srt_droid.Menu.MenuResto;

public class BuatPesananActivity extends Activity {

	ListView list;
	List<String> model=new ArrayList<String>();
	ArrayList<MenuResto> m_data = null;
	BuatPesananAdapter m_adapter;
	
	EditText noMeja;

	MenuController menuController = new MenuController();
	PesananController pesananController = new PesananController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buat_pesananan);

		init();
	}

	void init() {
		noMeja = (EditText) findViewById(R.id.noMeja);
		
		list = (ListView)findViewById(R.id.listview);
		m_data = menuController.getListOfMenu();
		m_adapter = new BuatPesananAdapter(BuatPesananActivity.this, R.layout.list_buat_pesanan_row, m_data);
		list.setAdapter(m_adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Log.e("debug", "pos = " + pos);
			}
		});
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				Utilities.menu = m_data.get(pos);
				startActivity(new Intent(getApplicationContext(), DeskripsiMenuActivity.class));
			}
		});
	}

	public void buatPesanan(View v) {
		if (noMeja.getText().length() == 0) {
			Toast.makeText(getApplicationContext(), "Masukkan nomor meja!", Toast.LENGTH_LONG).show();
			return;
		}
		
		ArrayList<MenuResto> pesanan = new ArrayList<MenuResto>();
		
		for (int i = 0; i < list.getChildCount(); i++) {
			View view = list.getChildAt(i);
			EditText text = (EditText)view.findViewById(R.id.jumlah);
			String contents = text.getText().toString();
			
			try {
				int jumlah = Integer.parseInt(contents);
				if (jumlah > 0) {
					MenuResto temp = new MenuResto(m_data.get(i));
					temp.setJumlah(jumlah);
					pesanan.add(temp);
				}
			}
			catch(NumberFormatException e) {}
		}
		
		if (pesanan.size() == 0) {
			Toast.makeText(getApplicationContext(), "Pastikan pesanan yang anda buat tidak kosong!", Toast.LENGTH_LONG).show();
		}
		else if (pesananController.buat(pesanan, noMeja.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Pesanan berhasil dibuat!", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
		}
		else {
			Toast.makeText(getApplicationContext(), "Pesanan gagal dibuat!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buat_pesananan, menu);
		return true;
	}

}

class BuatPesananAdapter extends ArrayAdapter<MenuResto> {
	private LayoutInflater inflater;

	// Untuk trik pakai onTouchListener
	private final View.OnTouchListener listener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v instanceof EditText) {
				final EditText et = (EditText) v;
				et.setFocusable(true);
				et.setFocusableInTouchMode(true);
			} else {
				final ViewHolder holder = (ViewHolder) v.getTag();
				holder.jumlah.setFocusable(false);
				holder.jumlah.setFocusableInTouchMode(false);
			}

			return false; // false agar tidak diterusin di superclass
		}
	};

	public BuatPesananAdapter(Context context, int textViewResourceId,
			ArrayList<MenuResto> objects) {
		super(context, textViewResourceId, objects);

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			view = inflater.inflate(R.layout.list_buat_pesanan_row, parent, false);
			holder = new ViewHolder();
			holder.nama = (TextView) view.findViewById(R.id.nama);
			holder.harga = (TextView) view.findViewById(R.id.harga);
			holder.jumlah = (EditText) view.findViewById(R.id.jumlah);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		MenuResto o = getItem(position);
		if (o != null) {
			holder.nama.setText("Nama: " + o.getNama());
			holder.harga.setText("Harga: " + o.getHarga());
			holder.jumlah.setText("0");
		}

		// setOnTouchListener untuk EditText dan untuk View semua
		holder.jumlah.setOnTouchListener(listener);
		view.setOnTouchListener(listener);

		return view;
	}

	static class ViewHolder {
		TextView nama;
		TextView harga;
		EditText jumlah;
	}
}