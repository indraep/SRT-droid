package com.example.srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srt_droid.PelayanActivity;
import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;
import com.example.srt_droid.Controller.PesananController;

public class ListPesananActivity extends Activity {

	PesananController pesananController = new PesananController();
	
	ListView list;
	List<String> model=new ArrayList<String>();
	ArrayList<Pesanan> m_data = null;
	PesananAdapter m_adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_pesanan);
	
		init();
	}

	private void init() {
		list = (ListView)findViewById(R.id.listview);
		m_data = pesananController.getListOfPesanan();
		m_adapter = new PesananAdapter(ListPesananActivity.this, R.layout.list_pesanan_row, m_data);
		list.setAdapter(m_adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				Utilities.pesanan = m_data.get(pos);
				startActivity(new Intent(getApplicationContext(), ListDetailPesananActivity.class));
				finish();
			}
		});
		
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				//showDialog(ListPesananActivity.this, "", "Apa yang ingin anda lakukan terhadap pesanan ini?", m_data.get(pos));
				showDialog(m_data.get(pos));
				return true;
			}
		});
		
	}
	
	private void showDialog(final Pesanan pesanan) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_ubah_hapus_kirim);
		dialog.setTitle("Pilihan anda?");
		
		Button ubah = (Button) dialog.findViewById(R.id.ubah);
		ubah.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Utilities.oldPesanan = pesanan;
				startActivity(new Intent(getApplicationContext(), UbahPesananActivity.class));
				dialog.cancel();
				finish();
			}
		});
		
		Button hapus = (Button) dialog.findViewById(R.id.hapus);
		hapus.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showConfirmDialog(pesanan);
				dialog.cancel();				
			}
		});
		
		
		Button kirim = (Button) dialog.findViewById(R.id.kirim);
		kirim.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		
		dialog.show();
	}
	
	private void showConfirmDialog(final Pesanan pesanan) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_ya_tidak);
		dialog.setTitle("Apakah anda yakin?");
	
		Button ya = (Button) dialog.findViewById(R.id.ya);
		ya.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (pesananController.hapus(pesanan)) {
					Toast.makeText(getApplicationContext(), "Pesanan berhasil dihapus!", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
					finish();
				}
				else {
					Toast.makeText(getApplicationContext(), "Gagal menghapus pesanan!", Toast.LENGTH_LONG).show();
				}
				dialog.cancel();
			}
		});
		
		Button tidak = (Button) dialog.findViewById(R.id.tidak);
		tidak.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		
		dialog.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_pesanan, menu);
		return true;
	}
	
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), PelayanActivity.class));
		finish();
	}
	
	class PesananAdapter extends ArrayAdapter<Pesanan> {
		private ArrayList <Pesanan> items;

		public PesananAdapter(Context context, int textViewResourceId, ArrayList<Pesanan> objects) {
			super(context, textViewResourceId, objects);
			items = objects;
		}

		public View getView(int position, View convertView, ViewGroup pattern) {
			View v = convertView;

			if (v == null) {
				LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.list_pesanan_row, null);
			}
			Pesanan o = items.get(position);
			
			
			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.noMeja);
				if (tt != null) {
					tt.setText("No Meja: " + o.getNoMeja());
				}

				tt = (TextView)v.findViewById(R.id.tanggal);
				if (tt != null) {
					tt.setText("Tanggal: " + o.getTanggal());
				}
				
				tt = (TextView)v.findViewById(R.id.totalHarga);
				if (tt != null) {
					tt.setText("Total Biaya: " + o.getTotalHarga());
				}
			}
			return v;
		}

	}
}
