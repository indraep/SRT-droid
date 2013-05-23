package srt_droid.Pesanan;

import java.util.ArrayList;

import srt_droid.Utilities;
import srt_droid.Controller.PesananController;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListPesananKasirActivity extends Activity {
	PesananController pesananController = new PesananController();

	ArrayList<Pesanan> m_data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_pesanan);
		
		init();
	}
	
	private void init() {
		m_data = pesananController.getListOfPesananKasir();

		LinearLayout listPesananLayout = (LinearLayout)findViewById(R.id.listPesananLayout);

		int prevStatus = -1;

		for (int i = 0; i < m_data.size(); i++) {
			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_pesanan_row, null);

			if (m_data.get(i).getStatus() != prevStatus) {
				prevStatus = m_data.get(i).getStatus();
				TextView status = new TextView(getApplicationContext());
				status.setText("" + m_data.get(i).getStatusPesanan());
				listPesananLayout.addView(status);
			}

			final int pos = i;

			layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Utilities.pesanan = m_data.get(pos);
					startActivity(new Intent(getApplicationContext(), ListDetailPesananActivity.class));
				}
			});


			layout.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					showDialog(m_data.get(pos));
					return false;
				}
			});

			TextView noMeja = (TextView)layout.getChildAt(0);
			noMeja.setText("No Meja: " + m_data.get(i).getNoMeja());

			TextView tanggal = (TextView)layout.getChildAt(1);
			tanggal.setText("Tanggal: " + m_data.get(i).getTanggal());

			TextView totalHarga = (TextView)layout.getChildAt(2);
			totalHarga.setText("Total Harga: " + m_data.get(i).getTotalHarga());

			listPesananLayout.addView(layout);
		}
	}
	
	private void showDialog(final Pesanan pesanan) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_bayar);
		dialog.setTitle("Pilihan anda?");

		Button bayar = (Button) dialog.findViewById(R.id.bayar);
		bayar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Utilities.oldPesanan = pesanan;
				startActivity(new Intent(getApplicationContext(), FormPembayaranActivity.class));
				finish();
				dialog.cancel();
			}
		});

		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_pesanan_kasir, menu);
		return true;
	}

}
