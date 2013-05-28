package srt_droid.Pesanan;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import srt_droid.PelayanActivity;
import srt_droid.Utilities;
import srt_droid.Account.User;
import srt_droid.Controller.MenuController;
import srt_droid.Controller.PesananController;
import srt_droid.Menu.DeskripsiMenuActivity;
import srt_droid.Menu.MenuResto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srt_droid.R;

public class BuatPesananActivity extends Activity {
	ArrayList<MenuResto> m_data = null;

	EditText noMeja;

	MenuController menuController = new MenuController();
	PesananController pesananController = new PesananController();
	ArrayList <Integer> jumlah = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buat_pesananan);

		init();
	}

	void init() {	
		noMeja = (EditText) findViewById(R.id.noMeja);

		m_data = menuController.getListOfMenuAktifTersedia(1, 1);
		for (int i = 0; i < m_data.size(); i++)
			jumlah.add(0);

		LinearLayout listMenuLayout = (LinearLayout)findViewById(R.id.listMenuLayout);

		String prevKategori = "";

		for (int i = 0; i < m_data.size(); i++) {
			if (!prevKategori.equals(m_data.get(i).getNamaKategori())) {
				prevKategori = "" + m_data.get(i).getNamaKategori();
				TextView tv = new TextView(this);
				tv.setText(prevKategori.toUpperCase());
				tv.setTextSize(17);
				tv.setTextColor(Color.parseColor("#33b5e5"));
				
				listMenuLayout.addView(tv);
			}

			final int pos = i;

			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_buat_pesanan_row, null);

			LinearLayout ll = (LinearLayout)layout.getChildAt(0);
			ll = (LinearLayout)ll.getChildAt(0);
			ImageView image = (ImageView)ll.getChildAt(0);
			
			ll = (LinearLayout)ll.getChildAt(1);
			
			URL Url;
			try {
				Url = new URL(Utilities.URL + "image/" + m_data.get(i).getImage());
				InputStream content = (InputStream)Url.getContent();
				Drawable d = Drawable.createFromStream(content , "src"); 
				image.setImageDrawable(d);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			((TextView)ll.getChildAt(0)).setText(m_data.get(i).getNama());
			((TextView)ll.getChildAt(1)).setText("Rp. " + m_data.get(i).getHarga());

			ll = (LinearLayout)layout.getChildAt(0);
			ll = (LinearLayout)ll.getChildAt(1);

			final EditText et = (EditText)ll.getChildAt(0);

			et.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// TODO Auto-generated method stub
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub
				}

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					jumlah.set(pos, Integer.parseInt(s.toString().length() > 0 ? s.toString() : "0"));
				}
			});
			
			layout.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					Utilities.menu = m_data.get(pos);
					startActivity(new Intent(getApplicationContext(), DeskripsiMenuActivity.class));
					return false;
				}
			});
			
			layout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String jum = et.getText().toString();
					int jumlah = jum.length() > 0 ? Integer.parseInt(jum) : 0;
					et.setText("" + (jumlah + 1));
				}
			});

			listMenuLayout.addView(layout);
		}
	}

	public void buatPesanan(View v) {
		if (noMeja.getText().length() == 0) {
			Toast.makeText(getApplicationContext(), "Masukkan nomor meja!", Toast.LENGTH_LONG).show();
			return;
		}

		ArrayList<MenuResto> pesanan = new ArrayList<MenuResto>();

		for (int i = 0; i < jumlah.size(); i++) {
			Log.e("debug", "nama = " + m_data.get(i).getNama() + " jumlah = " + m_data.get(i).getJumlah());

			if (jumlah.get(i) > 0) {
				MenuResto temp = new MenuResto(m_data.get(i));
				temp.setJumlah(jumlah.get(i));
				pesanan.add(temp);
			}
		}
		
		String addition = ((EditText)findViewById(R.id.addition)).getText().toString();

		if (pesanan.size() == 0) {
			Toast.makeText(getApplicationContext(), "Pastikan pesanan yang anda buat tidak kosong!", Toast.LENGTH_LONG).show();
		}
		else if (pesananController.buat(pesanan, noMeja.getText().toString(), addition)) {
			Toast.makeText(getApplicationContext(), "Pesanan berhasil dibuat!", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
			finish();
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

	public void onBackPressed() {
		if (!isEmpty()) {
			showConfirmDialog(BuatPesananActivity.this, "", "Data pesanan akan hilang. Apakah anda tetap akan keluar?");
		}
		else {
			startActivity(new Intent(getApplicationContext(), PelayanActivity.class));
			finish();
		}
	}

	public void showConfirmDialog(Activity activity, String title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		if (title != null)
			builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("Ya", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(getApplicationContext(), PelayanActivity.class));
				finish();
			}
		});
		builder.setNegativeButton("Tidak", null);
		builder.show();
	}

	private boolean isEmpty() {
		if (noMeja.getText().length() > 0) {
			return false;
		}

		for (int i = 0; i < jumlah.size(); i++) {	
			if (jumlah.get(i) > 0) {
				return false;
			}
		}

		return true;
	}

}

