package srt_droid;

import java.util.ArrayList;

import srt_droid.Controller.PesananController;
import srt_droid.Pesanan.ListDetailPesananActivity;
import srt_droid.Pesanan.Pesanan;

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
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListPesananSejarahActivity extends Activity {
	String[] month = new String[]{"", "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"};
	
	PesananController pesananController = new PesananController();

	ArrayList<Pesanan> m_data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_pesanan_sejarah);
		
		init();
	}
	
	private void init() {
		String dateAwal = Utilities.date1[2] + "-" + Utilities.date1[1] + "-" + Utilities.date1[0];
		String dateAkhir = Utilities.date2[2] + "-" + Utilities.date2[1] + "-" + Utilities.date2[0];
		
		TextView title = (TextView)findViewById(R.id.title);
		title.setText("Daftar Penjualan\n" + Utilities.date1[0] + " " + month[Utilities.date1[1]] + " " + Utilities.date1[2] + " - " +
				Utilities.date2[0] + " " + month[Utilities.date2[1]] + " " + Utilities.date2[2]);
		
		m_data = pesananController.getListOfPesananLunas(dateAwal, dateAkhir);
		
		LinearLayout listPesananLayout = (LinearLayout)findViewById(R.id.listPesananLayout);
		
		
		for (int i = 0; i < m_data.size(); i++) {
			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_pesanan_sejarah_row, null);
			
			final int pos = i;
			
			layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Utilities.pesanan = m_data.get(pos);
					startActivity(new Intent(getApplicationContext(), ListDetailPesananActivity.class));
				}
			});
			
			TextView noMeja = (TextView)layout.findViewById(R.id.noMeja);
			noMeja.setText("No Meja: " + m_data.get(i).getNoMeja());
		
			TextView tanggal = (TextView)layout.findViewById(R.id.tanggal);
			tanggal.setText("Tanggal: " + m_data.get(i).getTanggal());
			
			TextView totalHarga = (TextView)layout.findViewById(R.id.totalHarga);
			totalHarga.setText("Total Harga: Rp. " + m_data.get(i).getTotalHarga());
			
			TextView pelayan = (TextView)layout.findViewById(R.id.pelayan);
			pelayan.setText("Pelayan\t: " + m_data.get(i).getUsernamePelayan());
			
			TextView koki = (TextView)layout.findViewById(R.id.koki);
			koki.setText("Koki\t\t: " + m_data.get(i).getUsernameKoki());
			
			TextView kasir = (TextView)layout.findViewById(R.id.kasir);
			kasir.setText("Kasir\t\t: " + m_data.get(i).getUsernameKasir());
			
			listPesananLayout.addView(layout);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_pesanan_sejarah, menu);
		return true;
	}

}
