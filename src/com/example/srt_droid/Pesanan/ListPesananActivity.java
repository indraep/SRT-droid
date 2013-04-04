package com.example.srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_pesanan, menu);
		return true;
	}

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
