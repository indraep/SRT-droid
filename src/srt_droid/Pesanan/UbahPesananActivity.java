package srt_droid.Pesanan;

import java.util.ArrayList;
import java.util.List;

import srt_droid.PelayanActivity;
import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import srt_droid.Controller.PesananController;
import srt_droid.Menu.DeskripsiMenuActivity;
import srt_droid.Menu.MenuResto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.srt_droid.R;

public class UbahPesananActivity extends Activity {

	EditText noMeja;

	PesananController pesananController = new PesananController();
	ArrayList <Integer> jumlah = new ArrayList<Integer>();
	ArrayList<MenuResto> m_data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ubah_pesanan);

		init();
	}

	

	private void init() {
		noMeja = (EditText) findViewById(R.id.noMeja);
		noMeja.setText(Utilities.oldPesanan.getNoMeja() + "");

		m_data = pesananController.getListOfDetailPesanan(Utilities.oldPesanan);
		for (int i = 0; i < m_data.size(); i++)
			jumlah.add(m_data.get(i).getJumlah());
		
		((EditText)findViewById(R.id.addition)).setText(Utilities.oldPesanan.getAddition());

		LinearLayout listMenuLayout = (LinearLayout)findViewById(R.id.listMenuLayout);
		String prevKategori = "";
		for (int i = 0; i < m_data.size(); i++) {
			if (!prevKategori.equals(m_data.get(i).getNamaKategori())) {
				prevKategori = "" + m_data.get(i).getNamaKategori();
				TextView tv = new TextView(this);
				tv.setText(prevKategori);
				listMenuLayout.addView(tv);
			}
			
			final int pos = i;

			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_buat_pesanan_row, null);

			layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Utilities.menu = m_data.get(pos);
					startActivity(new Intent(getApplicationContext(), DeskripsiMenuActivity.class));
				}
			});

			LinearLayout ll = (LinearLayout)layout.getChildAt(0);
			ll = (LinearLayout)ll.getChildAt(0);
			ImageView image = (ImageView)ll.getChildAt(0);
			
			ll = (LinearLayout)ll.getChildAt(1);
			
			/*URL Url;
			try {
				Url = new URL(Utilities.URL + "image/" + m_data.get(i).getImage());
				InputStream content = (InputStream)Url.getContent();
				Drawable d = Drawable.createFromStream(content , "src"); 
				image.setImageDrawable(d);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			((TextView)ll.getChildAt(0)).setText(m_data.get(i).getNama());
			((TextView)ll.getChildAt(1)).setText("Rp. " + m_data.get(i).getHarga());

			ll = (LinearLayout)layout.getChildAt(0);
			ll = (LinearLayout)ll.getChildAt(1);

			EditText et = (EditText)ll.getChildAt(0);

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
			et.setText("" + jumlah.get(pos));

			listMenuLayout.addView(layout);
		}
	}

	public void ubahPesanan(View v) {
		if (noMeja.getText().length() == 0) {
			Toast.makeText(getApplicationContext(), "Masukkan nomor meja!", Toast.LENGTH_LONG).show();
			return;
		}

		ArrayList<MenuResto> pesanan = new ArrayList<MenuResto>();

		for (int i = 0; i < jumlah.size(); i++) {
			if (jumlah.get(i) > 0) {
				MenuResto temp = new MenuResto(m_data.get(i));
				temp.setJumlah(jumlah.get(i));
				pesanan.add(temp);
			}
		}

		
		String addition = ((EditText)findViewById(R.id.addition)).getText().toString();
		
		if (pesanan.size() == 0) {
			Toast.makeText(getApplicationContext(), "Pastikan pesanan yang anda ubah tidak kosong!", Toast.LENGTH_LONG).show();
		}
		else if (pesananController.ubah(pesanan, noMeja.getText().toString(), addition, Utilities.oldPesanan)) {
			Toast.makeText(getApplicationContext(), "Pesanan berhasil diubah!", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
			finish();
		}
		else {
			Toast.makeText(getApplicationContext(), "Pesanan gagal diubah!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ubah_pesanan, menu);
		return true;
	}
	
	public void onBackPressed() {
		showConfirmDialog(UbahPesananActivity.this, "", "Perubahan pesanan akan hilang. Apakah anda tetap akan keluar?");
	}
	
	public void showConfirmDialog(Activity activity, String title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		if (title != null)
			builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(getApplicationContext(), ListPesananActivity.class));
				finish();
			}
		});
		builder.setNegativeButton("Tidak", null);
		builder.show();
	}

}
