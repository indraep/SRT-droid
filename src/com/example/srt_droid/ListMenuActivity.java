package com.example.srt_droid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srt_droid.Controller.MenuController;

public class ListMenuActivity extends Activity {

	ListView list;
	List<String> model=new ArrayList<String>();
	ArrayList<MenuResto> m_data = null;
	MenuAdapter m_adapter;

	MenuController menuController = new MenuController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_menu);
		init();
	}

	void init() {
		list = (ListView)findViewById(R.id.listview);
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
				showDialog(ListMenuActivity.this, "", "Apa yang ingin anda lakukan terhadap menu ini?", m_data.get(pos));
				return true;
			}
		});
		new ListMenuAsync(getApplicationContext()).execute();		
	}

	public void showDialog(Activity activity, String title, CharSequence message, final MenuResto menu) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		if (title != null)
			builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("Hapus", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				showConfirmDialog(ListMenuActivity.this, "", "Apakah anda yakin?", menu);
			}
		});
		builder.setNegativeButton("Ubah", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	public void showConfirmDialog(Activity activity, String title, CharSequence message, final MenuResto menu) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		if (title != null)
			builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("Ya", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (menuController.hapus(menu)) {
					Toast.makeText(ListMenuActivity.this, "Menu berhasil dihapus!", Toast.LENGTH_LONG).show();
					startActivity(new Intent(ListMenuActivity.this, ListMenuActivity.class));
					finish();
				}
			}
		});
		builder.setNegativeButton("Tidak", null);
		builder.show();
	}

	public void tambahMenu(View v) {
		Toast.makeText(getBaseContext(), "Tambah Menu", 2000).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
	}

	public class ListMenuAsync extends AsyncTask<String, Integer, String> {
		private ProgressDialog Dialog = new ProgressDialog(ListMenuActivity.this);
		private Context mContext;

		public ListMenuAsync(Context context) {
			mContext = context;
		}

		@Override
		protected void onPreExecute() {
			Dialog.setTitle("Harap tunggu...");
			Dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			m_data = menuController.getListOfMenu();
			publishProgress();
			return "";
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Dialog.dismiss();

			m_adapter = new MenuAdapter(ListMenuActivity.this, R.layout.list_menu_row, m_data);
			list.setAdapter(m_adapter);

			list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
					Toast.makeText(ListMenuActivity.this, m_data.get(position).toString(), 2000).show();
				}
			});
		}

		@Override
		protected void onPostExecute(String result) {
		}

	}

}

class MenuAdapter extends ArrayAdapter<MenuResto> {

	private ArrayList <MenuResto> items;

	public MenuAdapter(Context context, int textViewResourceId, ArrayList<MenuResto> objects) {
		super(context, textViewResourceId, objects);
		items = objects;
	}

	public View getView(int position, View convertView, ViewGroup pattern) {
		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_menu_row, null);
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
		}
		return v;
	}

}