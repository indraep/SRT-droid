package com.example.srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.example.srt_droid.R;
import com.example.srt_droid.Controller.MenuController;
import com.example.srt_droid.Menu.MenuResto;

public class BuatPesananActivity extends Activity {
	
	ListView list;
	List<String> model=new ArrayList<String>();
	ArrayList<MenuResto> m_data = null;
	BuatPesananAdapter m_adapter;
	
	MenuController menuController = new MenuController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buat_pesananan);
		
		init();
	}
	
	void init() {
		list = (ListView)findViewById(R.id.listview);
		m_data = menuController.getListOfMenu();
		m_adapter = new BuatPesananAdapter(BuatPesananActivity.this, R.layout.list_buat_pesanan_row, m_data);
		list.setAdapter(m_adapter);
	}
	
	public void buatPesanan(View v) {
		for (int i = 0; i < list.getChildCount(); i++) {
			View view = list.getChildAt(i);
			EditText text = (EditText)view.findViewById(R.id.jumlah);
			String contents = text.getText().toString();
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

	private ArrayList <MenuResto> items;

	public BuatPesananAdapter(Context context, int textViewResourceId, ArrayList<MenuResto> objects) {
		super(context, textViewResourceId, objects);
		items = objects;
	}

	public View getView(int position, View convertView, ViewGroup pattern) {
		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_buat_pesanan_row, null);
		}
		MenuResto o = items.get(position);
		if (o != null) {
			TextView tt = (TextView) v.findViewById(R.id.nama);
			if (tt != null) {
				tt.setText("Name: " + o.getNama());
			}

			tt = (TextView)v.findViewById(R.id.harga);
			if (tt != null) {
				tt.setText("Harga: " + o.getHarga());
			}
			
			EditText et = (EditText) v.findViewById(R.id.jumlah);
			if (et != null) {
				et.setText("0");
			}
		}
		return v;
	}

}
