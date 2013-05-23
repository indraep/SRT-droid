package srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import srt_droid.KokiActivity;
import srt_droid.PelayanActivity;
import srt_droid.Utilities;
import srt_droid.Controller.PesananController;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srt_droid.R;

public class ListPesananKokiActivity extends Activity {

	PesananController pesananController = new PesananController();

	ArrayList<Pesanan> m_data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_pesanan);

		init();
	}

	private void init() {
		m_data = pesananController.getListOfPesananKoki();

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
					if (m_data.get(pos).getStatus() == 1)
						showDialog2(m_data.get(pos));
					else if (m_data.get(pos).getStatus() == 2)
						showDialog3(m_data.get(pos));
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
	
	private void showDialog3(final Pesanan pesanan) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_tandai_pesanan_sedang_dibuat);
		dialog.setTitle("Pilihan anda?");

		Button ubahStatus = (Button) dialog.findViewById(R.id.ubahStatus);
		ubahStatus.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (pesananController.ubahStatus(pesanan, 3)) {
					dialog.cancel();
					Toast.makeText(getApplicationContext(), "Status pesanan telah diubah!", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getApplicationContext(), ListPesananKokiActivity.class));
					finish();
				}
			}
		});

		dialog.show();
	}
	
	private void showDialog2(final Pesanan pesanan) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_tandai_pesanan_sedang_dibuat);
		dialog.setTitle("Pilihan anda?");

		Button ubahStatus = (Button) dialog.findViewById(R.id.ubahStatus);
		ubahStatus.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (pesananController.ubahStatus(pesanan, 2)) {
					dialog.cancel();
					Toast.makeText(getApplicationContext(), "Status pesanan telah diubah!", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getApplicationContext(), ListPesananKokiActivity.class));
					finish();
				}
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
					startActivity(new Intent(getApplicationContext(), ListPesananKokiActivity.class));
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
/*
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), KokiActivity.class));
		finish();
	}*/
}
