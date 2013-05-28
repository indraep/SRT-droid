package srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import srt_droid.PelayanActivity;
import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import srt_droid.Controller.PesananController;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListDetailPesananActivity extends Activity {

	PesananController pesananController = new PesananController();

	ListView list;
	List<String> model=new ArrayList<String>();
	ArrayList<DetailPesanan> m_data = null;
	DetailPesananAdapter m_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_detail_pesanan);

		init();
	}

	private void init() {
		list = (ListView)findViewById(R.id.listview);
		m_data = pesananController.getListOfDetailPesanan(Utilities.pesanan.getId());
		m_data.add(null);
		m_adapter = new DetailPesananAdapter(ListDetailPesananActivity.this, R.layout.list_detail_pesanan_row, m_data);
		list.setAdapter(m_adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_detail_pesanan, menu);
		return true;
	}
	/*
	public void onBackPressed() {
		//startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
		//finish();
	}*/
}

class DetailPesananAdapter extends ArrayAdapter<DetailPesanan> {

	private ArrayList <DetailPesanan> items;

	public DetailPesananAdapter(Context context, int textViewResourceId, ArrayList<DetailPesanan> objects) {
		super(context, textViewResourceId, objects);
		items = objects;
	}

	public View getView(int position, View convertView, ViewGroup pattern) {
		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_detail_pesanan_row, null);
		}
		DetailPesanan o = items.get(position);
		
		if (o != null) {
			TextView tt = (TextView)v.findViewById(R.id.nama);
			if (tt != null) {
				tt.setText(o.getNo() + ". " + o.getNama());
			}
			
			tt = (TextView)v.findViewById(R.id.total);
			tt.setPadding(10, 0, 0, 0);
			if (tt != null) {
				tt.setText("Jumlah: " + o.getJumlah());
			}
		}
		else {
			TextView tt = (TextView)v.findViewById(R.id.nama);
			tt.setText("Tambahan:");
			
			tt = (TextView)v.findViewById(R.id.total);
			tt.setPadding(10, 0, 0, 0);
			tt.setText(Utilities.pesanan.getAddition());
		}
		return v;
	}

}
