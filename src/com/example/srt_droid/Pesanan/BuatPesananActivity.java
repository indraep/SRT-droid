package com.example.srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Log.e("debug", "pos = " + pos);
			}
		});
	}

	public void buatPesanan(View v) {		
		for (int i = 0; i < list.getChildCount(); i++) {
			View view = list.getChildAt(i);
			EditText text = (EditText)view.findViewById(R.id.jumlah);
			String contents = text.getText().toString();
			Log.e("jumlah", "jumlah = " + contents);
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